package com.example.hyfit_server.domain.user;

import com.example.hyfit_server.domain.BaseTimeEntity;
import com.example.hyfit_server.dto.user.UserDto;
import com.example.hyfit_server.dto.user.UserUpdateDto;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Table(name = "user")
@Getter
@NoArgsConstructor
@Entity
public class UserEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private String name;

    @Column(nullable = false ,unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String nickName;

    private String phone;

    private String birth;

    // man, woman
    private String gender;

    @Column(nullable = true)
    private String profile_img;

    // 0 -> 비활성화, 1 -> 활성화
    @ColumnDefault("1")
    private int user_status;

    @Column(nullable = true)
    private String introduce;

    private String grade;


    @Builder
    public UserEntity(long userId, UserRole role,String email, String name, String password, String nickName, String phone, String birth, String gender, String profile_img, int user_status, String introduce, String grade) {
        this.userId = userId;
        this.role = role;
        this.name = name;
        this.email = email;
        this.password = password;
        this.nickName = nickName;
        this.phone = phone;
        this.birth = birth;
        this.gender = gender;
        this.profile_img = profile_img;
        this.user_status = user_status;
        this.introduce = introduce;
        this.grade = grade;

    }

    /* Entity -> DTO */
    public UserDto toDto() {
        return UserDto.builder()
                .userId(userId)
                .role(role) // 회원가입시 user 권한
                .password(password)
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
    }
    public UserEntity update(UserUpdateDto userUpdateDto) {
        this.nickName = userUpdateDto.getNickName();
        this.phone = userUpdateDto.getPhone();
        this.birth = userUpdateDto.getBirth();
        this.profile_img = userUpdateDto.getProfile_img();
        this.introduce = userUpdateDto.getIntroduce();
        return this;
    }

    public UserEntity updatePassword(String password){
        this.password = password;
        return this;
    }

    public UserEntity updateProfileImage(String imageUrl) {
        this.profile_img = imageUrl;
        return this;
    }

}
