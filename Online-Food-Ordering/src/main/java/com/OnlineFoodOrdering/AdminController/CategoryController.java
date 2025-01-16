package com.OnlineFoodOrdering.AdminController;


import com.OnlineFoodOrdering.model.Category;
import com.OnlineFoodOrdering.model.User;
import com.OnlineFoodOrdering.service.CategoryService;
import com.OnlineFoodOrdering.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;


    @PostMapping("/admin/category")
    public ResponseEntity<?> createCategory(@RequestBody Category category, @RequestHeader("Authorization") String jwt) throws Exception {
        try{
            User user = userService.findUserByJWtToken(jwt);
            Category category1 = categoryService.CreateCategory(category.getName(),user.getId());
            return new ResponseEntity<>(category1, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>("duplicate entry",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/category/restaurant/{id}")
    public ResponseEntity<List<Category>> GetRestaurantCategory(
            @PathVariable Long id,
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJWtToken(jwt);
        List<Category> category1 = categoryService.findCategoryByRestaurantId(id);
        return new ResponseEntity<>(category1, HttpStatus.OK);

    }

    @DeleteMapping("/category/Delete/{name}")
    public ResponseEntity<?> DeleteCategory(@PathVariable String name, @RequestHeader("Authorization") String jwt) throws Exception {
        try{
            User user = userService.findUserByJWtToken(jwt);
            categoryService.deleteCategoryByNameAndUserId(name,user.getId());
            return new ResponseEntity<>("category deleted",HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>("category not found",HttpStatus.BAD_REQUEST);
        }

    }
}
