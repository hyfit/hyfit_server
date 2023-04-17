package com.example.hyfit_server.config.response;

import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
public enum BaseResponseStatus {

    /**
     * 1000 : 요청 성공
     */
    SUCCESS(true, 1000, "요청에 성공하였습니다."),

    /**
     * 2000 : Request 오류
     */

    // user 관련
    EXIST_USER_EMAIL(false,2001,"중복된 이메일입니다."),
    NO_USER_ERROR(false,2002, "이메일에 해당하는 유저가 없습니다."),
    FAIL_TO_LOGIN(false, 2003, "비밀번호가 틀렸습니다."),
    IS_EXIST_NICKNAME(false,2004,"중복된 닉네임 입니다."),
    VALIDATE_TOKEN_ERROR(false, 2005, "유효하지 않은 토근값입니다."),
    NO_TOKEN_ERROR(false, 2006, "토큰값이 없습니다."),
    PASSWORD_PATTERN_ERROR(false, 2007, "비밀번호는 영어와 숫자를 포함해서 6~12자리 이내로 입력해주세요."),

    // follow 관련
    ALREADY_FOLLOW_USER(false, 2101, "이미 팔로우한 유저입니다."),

    // goal 관련

    EXIST_GOAL_MOUNTAIN(false, 2201, "이미 설정한 목표가 진행중입니다."),
    NO_PROGRESS_GOAL(false, 2202, "진행중인 목표가 없습니다."),
    NO_PROGRESS_DONE(false, 2203, "종료된 목표가 없습니다."),

    // post 관련

    NO_POST_CONTENTS(false, 2301, "내용을 입력해주세요."),
    NO_POST_ERROR(false, 2302, "해당 게시물이 없습니다."),

    // image 관련
    NOT_SUPPORTED_IMAGE_FORMAT(false, 2303, "지원되는 파일 형식이 아닙니다."),



    /**
     * 3000 : Response 오류
     */

    /**
     * 4000 : Database, Server 오류
     */

    DATABASE_ERROR(false, 4000, "데이터베이스 연결에 실패하였습니다."),
    SERVER_ERROR(false, 4001, "서버와의 연결에 실패하였습니다.")

    ;

    private final boolean isSuccess;
    private final int code;
    private final String message;

    private BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}