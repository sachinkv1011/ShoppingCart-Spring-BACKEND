package com.example.shoppingCart_backend.Dao;

import com.example.shoppingCart_backend.model.UserRegModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDao extends CrudRepository<UserRegModel,Integer> {

    @Query(value = "SELECT `id`, `address`, `email`, `name`, `password`, `phone` FROM `user` WHERE `email`= :email", nativeQuery = true)
    List<UserRegModel> FindUserLogin(@Param("email") String email);
}
