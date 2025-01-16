package com.OnlineFoodOrdering.service;

import com.OnlineFoodOrdering.model.User;

public interface UserService {

    public User findUserByJWtToken(String jwtToken) throws Exception;

    public User findUserByEmail(String email) throws Exception;
}
