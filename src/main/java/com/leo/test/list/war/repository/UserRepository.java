package com.leo.test.list.war.repository;

import com.leo.test.list.war.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    public User findOneByUsernameOrEmail(String username, String email);

    public User findOneByUsername(String username);

    public User findOneByEmail(String email);
}
