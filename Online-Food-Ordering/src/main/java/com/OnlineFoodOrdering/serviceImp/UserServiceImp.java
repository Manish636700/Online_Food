package com.OnlineFoodOrdering.serviceImp;

import com.OnlineFoodOrdering.config.JwtProvider;
import com.OnlineFoodOrdering.model.User;
import com.OnlineFoodOrdering.repository.UserRepository;
import com.OnlineFoodOrdering.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImp  implements UserService {

    @Autowired
     private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;


    @Override
    public User findUserByJWtToken(String jwtToken) throws Exception {
        String email = jwtProvider.getEmailFromJwtToken(jwtToken);
        User user = userRepository.findByEmail(email);
        return user;


    }

    @Override
    public User findUserByEmail(String email) throws Exception {

        User user = userRepository.findByEmail(email);

        if(user == null){
            throw new Exception("User not found");
        }
        return user;
    }
}
