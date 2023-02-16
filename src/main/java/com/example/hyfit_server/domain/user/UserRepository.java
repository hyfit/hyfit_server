package com.example.hyfit_server.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);

    UserEntity findById(long userId);

    UserEntity findByNickName(String nickName);


}
