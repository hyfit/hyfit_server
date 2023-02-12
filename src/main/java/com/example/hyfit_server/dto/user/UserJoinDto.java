package com.example.hyfit_server.dto.user;

import com.example.hyfit_server.domain.user.UserEntity;
import com.example.hyfit_server.domain.user.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserJoinDto {

    private String name;

    private String email;

    private String password;

    private String nickName;

    private String phone;

    private String birth;

    // man, woman
    private String gender;

    /* DTO -> Entity */
    public UserEntity toEntity() {
        UserEntity userEntity = UserEntity.builder()
                .role(UserRole.ROLE_USER) // 회원가입시 user 권한
                .password(password)
                .name(name)
                .email(email)
                .birth(birth)
                .nickName(nickName)
                .phone(phone)
                .gender(gender)
                .user_status(1) // user_status 활성화
                .build();
        return userEntity;
    }

}
