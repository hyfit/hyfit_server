package com.example.hyfit_server.domain.user;

import com.example.hyfit_server.dto.user.UserProfileDto;

public interface UserCustomRepository {

    UserProfileDto findInfoByEmail(String email);
}
