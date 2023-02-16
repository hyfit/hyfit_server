package com.example.hyfit_server.dto.user;

import com.example.hyfit_server.domain.user.UserEntity;
import com.example.hyfit_server.domain.user.UserRole;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserJoinDto {

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp="[a-zA-Z1-9]{6,12}",
            message = "비밀번호는 영어와 숫자를 포함해서 6~12자리 이내로 입력해주세요.")
    private String password;

    @NotBlank(message = "닉네임은 필수 입력 값입니다.")
    private String nickName;

    @NotBlank(message = "휴대폰 번호는 필수 입력 값입니다.")
    private String phone;

    @NotBlank(message = "생년월일은 필수 입력 값입니다.")
    private String birth;

    // man, woman
    @NotBlank(message = "성별은 필수 입력 값입니다.")
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
