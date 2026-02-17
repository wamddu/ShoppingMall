package com.asdf.shoppingmall.User.repository;

import com.asdf.shoppingmall.User.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends JpaRepository<User,Long> {
}
