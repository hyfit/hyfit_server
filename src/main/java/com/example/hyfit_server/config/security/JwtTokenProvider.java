package com.example.hyfit_server.config.security;

import com.example.hyfit_server.domain.user.UserRole;
import com.example.hyfit_server.service.redis.RedisService;
import com.example.hyfit_server.service.user.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtTokenProvider {

    private final CustomUserDetailsService customUserDetailsService;
    private final RedisService redisService;

    @Value("${jwt.secret}")
    private String secretKey;

    // access 토큰 유효시간 30분
    private long AccessTokenValidTime = 1 * 60 * 1000L;
    // refresh 토큰 유효시간 4시간
    private long RefreshTokenValidTime = 240 * 60 * 1000L;

    // secretkey를 인코딩 해줌.
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }
    //JWT 토큰 생성
    public String createToken(String email, UserRole role) {

        //payload 설정
        //registered claims
        Date now = new Date();
        Claims claims = Jwts.claims()
                .setSubject(email);
        //private claims
        //claims.put("id", id); // 정보는 key - value 쌍으로 저장.
        claims.put("role", role);
        String accessToken = Jwts.builder()
                .setClaims(claims) // 페이로드
                .setIssuedAt(now) //발행시간
                .setExpiration(new Date(now.getTime() + AccessTokenValidTime)) // 토큰 만료기한
                .signWith(SignatureAlgorithm.HS256, secretKey)  // 서명. 사용할 암호화 알고리즘과 signature 에 들어갈 secretKey 세팅
                .compact();

        String refreshToken = Jwts.builder()
                .setClaims(claims) // 페이로드
                .setIssuedAt(now) //발행시간
                .setExpiration(new Date(now.getTime() + RefreshTokenValidTime)) // 토큰 만료기한
                .signWith(SignatureAlgorithm.HS256, secretKey)  // 서명. 사용할 암호화 알고리즘과 signature 에 들어갈 secretKey 세팅
                .compact();
        // redis에 저장
        redisService.setValues(email,accessToken, Duration.ofMinutes(1));
        redisService.setValues(accessToken,refreshToken, Duration.ofHours(4));
        return accessToken;
    }

    public String reCreateToken(String email, UserRole role){
        //payload 설정
        //registered claims
        Date now = new Date();
        Claims claims = Jwts.claims()
                .setSubject(email);
        //private claims
        //claims.put("id", id); // 정보는 key - value 쌍으로 저장.
        claims.put("role", role);
        String accessToken = Jwts.builder()
                .setClaims(claims) // 페이로드
                .setIssuedAt(now) //발행시간
                .setExpiration(new Date(now.getTime() + AccessTokenValidTime)) // 토큰 만료기한
                .signWith(SignatureAlgorithm.HS256, secretKey)  // 서명. 사용할 암호화 알고리즘과 signature 에 들어갈 secretKey 세팅
                .compact();

        redisService.setValues(email,accessToken, Duration.ofMinutes(1));
        return accessToken;
    }

    //JWT 토큰에서 인증정보 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(this.getUserPk(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // 토큰에서 회원 정보 추출
    public String getUserPk(String token) {
        return (String) Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    // Request의 Header에서 token 값을 가져옴. "Authorization" : "TOKEN값'
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("X-AUTH-TOKEN");
    }
    // 토큰의 유효성 + 만료일자 확인  // -> 토큰이 expire되지 않았는지 True/False로 반환해줌.
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);

            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }


}
