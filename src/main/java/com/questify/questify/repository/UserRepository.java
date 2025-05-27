package com.questify.questify.repository;

import com.questify.questify.domain.user.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByUsernameAndPassword(String username, String password);

  Optional<User> findByUsername(String username);
}
