package com.semarslan.projectmanagement.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class JWTLoginSuccessResponse {

    private boolean success;
    private String token;
}
