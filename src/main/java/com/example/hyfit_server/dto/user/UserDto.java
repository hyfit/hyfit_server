package com.example.hyfit_server.dto.user;

import com.example.hyfit_server.domain.user.UserEntity;
import com.example.hyfit_server.domain.user.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {


    private long userId;

    private UserRole role;
    private String name;

    private String email;

//    private String password;

    private String nickName;

    private String phone;

    private String birth;

    private String profile_img;

    private int user_status;

    private String introduce;

    // man, woman
    private String gender;

    private String grade;

    /* DTO -> Entity */
    public UserEntity toEntity() {
        UserEntity userEntity = UserEntity.builder()
                .role(UserRole.ROLE_USER) // 회원가입시 user 권한
//                .password(password)
                .name(name)
                .email(email)
                .birth(birth)
                .nickName(nickName)
                .phone(phone)
                .gender(gender)
                .user_status(user_status) // user_status 활성화
                .profile_img(profile_img)
                .introduce(introduce)
                .grade(grade)
                .build();
        return userEntity;
    }

    @Builder
    public UserDto(long userId, UserRole role,String email,String name, String password,String nickName,String phone, String birth, String gender, String profile_img, int user_status, String introduce, String grade){
        this.userId = userId;
        this.role = role;
        this.email = email;
        this.name = name;
//        this.password = password;
        this.nickName = nickName;
        this.phone = phone;
        this.birth = birth;
        this.gender = gender;
        this.profile_img = profile_img;
        this.user_status = user_status;
        this.introduce = introduce;
        this.grade = grade;

    }


}
