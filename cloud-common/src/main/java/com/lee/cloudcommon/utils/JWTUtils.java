package com.lee.cloudcommon.utils;

import com.lee.cloudcommon.constants.CommonConstants;
import com.lee.cloudcommon.dto.UserToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JWTUtils {
    public static String generateToken(UserToken userToken, int expire) throws  Exception{
        String token = Jwts.builder()
                .setSubject(userToken.getUsername())
                .claim(CommonConstants.CONTEXT_USER_ID, userToken.getUserId())
                .claim(CommonConstants.CONTEXT_NAME, userToken.getName())
                .claim(CommonConstants.RENEWAL_TIME, new Date(System.currentTimeMillis() + expire / 2))
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .signWith(SignatureAlgorithm.HS256, CommonConstants.JWT_PRIVATE_KEy)
                .compact();
        return token;
    }
    public static UserToken getInfoFromToken(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(CommonConstants.JWT_PRIVATE_KEy).parseClaimsJws(token)
                .getBody();
        return new UserToken(claims.getSubject(),claims.get(CommonConstants.CONTEXT_USER_ID).toString(),claims.get(CommonConstants.CONTEXT_NAME).toString());

    }
}
