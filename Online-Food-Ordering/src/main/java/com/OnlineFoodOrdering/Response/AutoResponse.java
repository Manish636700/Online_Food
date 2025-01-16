package com.OnlineFoodOrdering.Response;

import com.OnlineFoodOrdering.model.USER_ROLE;
import jakarta.persistence.Entity;
import lombok.*;



@Data
public class AutoResponse {

    private String jwt;

    private String message;

    private USER_ROLE role;
}
