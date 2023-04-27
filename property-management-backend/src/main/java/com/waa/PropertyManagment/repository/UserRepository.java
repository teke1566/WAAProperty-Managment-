package com.waa.PropertyManagment.repository;

import com.waa.PropertyManagment.entity.User;
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

    @Query("SELECT u FROM User u WHERE u.role = :role")
    List<User> findUserByRole(Enum role);

    @Modifying
    @Query("UPDATE User u SET u.isactive = :value WHERE u.id = :id")
    void activateUser(long id, String value);

    @Modifying
    @Query("UPDATE User u SET u.password = :value WHERE u.id = :id")
    void resetPassword(long id, String value);
}