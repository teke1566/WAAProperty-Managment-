package com.waa.PropertyManagment.entity.dto.response;

import com.waa.PropertyManagment.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

    private long id;
    private String name;
    private String email;
    private Roles role;

  //  private List<PropertyDto> properties;


}
