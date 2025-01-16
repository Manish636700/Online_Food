package com.OnlineFoodOrdering.AdminController;

import com.OnlineFoodOrdering.Response.AutoResponse;

import com.OnlineFoodOrdering.config.JwtProvider;
import com.OnlineFoodOrdering.model.Cart;
import com.OnlineFoodOrdering.model.USER_ROLE;
import com.OnlineFoodOrdering.model.User;
import com.OnlineFoodOrdering.repository.CartRepository;
import com.OnlineFoodOrdering.repository.UserRepository;
import com.OnlineFoodOrdering.request.LoginRequest;
import com.OnlineFoodOrdering.serviceImp.CustomerUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;

    @Autowired
    private CartRepository cartRepository;


    @PostMapping("/signup")
    public ResponseEntity<AutoResponse>createUserHandler(@RequestBody User user) throws Exception {
        User isEmailExists = userRepository.findByEmail(user.getEmail());
        if (isEmailExists != null) {
            throw new Exception("Email is already used with another account");
        }
        User createdUser = new User();
        createdUser.setEmail(user.getEmail());
        createdUser.setFullName(user.getFullName());
        createdUser.setRole(user.getRole());
        createdUser.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepository.save(createdUser);


        Cart cart = new Cart();
        cart.setCustomer(savedUser);
        cartRepository.save(cart);

        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);

        AutoResponse autoResponse = new AutoResponse();
        autoResponse.setJwt(jwt);
        autoResponse.setMessage("Register successfully");
        autoResponse.setRole(savedUser.getRole());

        return new ResponseEntity<>(autoResponse, HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<AutoResponse>signin(@RequestBody LoginRequest loginRequest) throws Exception {

        String Username = loginRequest.getEmail();
        String Password = loginRequest.getPassword();

        try{
            Authentication authentication = authenticate(Username, Password);
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            String role = authorities.isEmpty() ? null : authorities.iterator().next().getAuthority();

            String jwt = jwtProvider.generateToken(authentication);

            AutoResponse autoResponse = new AutoResponse();

            autoResponse.setJwt(jwt);
            autoResponse.setMessage("Login successfully");

            try{
                autoResponse.setRole(USER_ROLE.valueOf(role));
            }
            catch(IllegalArgumentException e){
                throw new Exception("Invalid role");
            }
            return new ResponseEntity<>(autoResponse, HttpStatus.OK);
        }catch(Exception e){
            AutoResponse errorResponse = new AutoResponse();
            errorResponse.setMessage("Invalid username or password");
            return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
        }

    }

    private Authentication authenticate(String username, String password) throws Exception {
        try {

            UserDetails userDetails = customerUserDetailsService.loadUserByUsername(username);

            if (userDetails == null) {

                throw new BadCredentialsException("Invalid username");
            }

            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("Invalid password");
            }

            return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        } catch (BadCredentialsException ex) {
            throw new BadCredentialsException("Invalid username or password", ex);
        } catch (Exception ex) {
            throw new Exception("Authentication failed", ex);
        }
    }


}
