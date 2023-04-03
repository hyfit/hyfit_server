package com.example.hyfit_server.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserPasswordDto {


    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp="[a-zA-Z0-9!?@~*^]{6,12}",
            message = "비밀번호는 영어와 숫자를 포함해서 6~12자리 이내로 입력해주세요.")
    private String password;
}
