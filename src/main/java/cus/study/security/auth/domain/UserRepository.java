package cus.study.security.auth.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserNameAndPassWord(String name, String passWord);
}
