package com.waa.PropertyManagment.repository;

import com.waa.PropertyManagment.entity.Role;
import com.waa.PropertyManagment.entity.User;
import com.waa.PropertyManagment.enums.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query("SELECT u FROM User u WHERE u.role = 'CUSTOMER'")
    List<User> findAllCustomers();
    User findByEmail(String email);

//    @Query("SELECT u FROM User u WHERE u.role = :role")
//    @Query("SELECT u FROM User u JOIN u.role r  WHERE r.role = :role")
//    List<User> findUserByRole(Roles role);

    @Modifying
    @Query("UPDATE User u SET u.isactive = :value WHERE u.id = :id")
    void activateUser(long id, String value);

    @Modifying
    @Query("UPDATE User u SET u.password = :value WHERE u.id = :id")
    void resetPassword(long id, String value);

    @Query("select u from User u join u.role r where r.role <> 'ADMIN'")
    List<User> findAllExceptAdmin();

    @Query("select u from User u join u.role r where r.role = 'OWNER'")
    List<User> findAllUserByRoleOwner();

}