package com.waa.PropertyManagment.entity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String accessToken;
    private List<String> roles;
    private long userId;
   // private int id;
  //  private Boolean active;


}
