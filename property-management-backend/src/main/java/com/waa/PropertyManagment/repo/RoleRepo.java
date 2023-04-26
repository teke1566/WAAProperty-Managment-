package com.waa.PropertyManagment.repo;

import com.waa.PropertyManagment.entity.Role;
import com.waa.PropertyManagment.enums.Roles;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepo extends CrudRepository<Role,Long> {

//    Role findByRole(String role);

    Role findByRole(Roles role);
}
