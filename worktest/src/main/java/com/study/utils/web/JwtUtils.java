package com.study.utils.web;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * todo
 *
 * @author ldb
 * @date 2020/07/21
 */
@Slf4j
public class JwtUtils {

    /**
     * 密钥
     */
    public static final String SECRET = "hellooauthhellooauthhellooauthhellooauth";

    /**
     * jwt生存时间 30 分钟
     */
    public static final long TTL = 30 * 60 * 1000;

    public static final String ISSUSER = "ldb";



    /**
     * 创建JWT令牌
     *
     * @param headerMap
     * @param payloadMap
     * @return
     */
    public static String createJWT(Map<String, Object> headerMap, Map<String, String> payloadMap) {
        //生成JWT令牌
        String jwts =
                Jwts.builder().setHeaderParams(headerMap).setClaims(payloadMap).signWith(getKey(),
                        SignatureAlgorithm.HS256).compact();
        return jwts;
    }

    public static String createJWT(Map<String, String> payloadMap) {
        long now = System.currentTimeMillis();
        log.info("{}", now);
        //生成JWT令牌
        String jwts =
                Jwts.builder()
                        .setClaims(payloadMap)
                        .setId(UUID.randomUUID().toString())
                        .setHeaderParam("typ", "JWT")
                        .setHeaderParam("alg","HS256")
                        .setIssuer(ISSUSER) // 签发者
                        .setIssuedAt(new Date(now)) // 签发时间
                        .setExpiration(new Date(now + TTL)) // 过期时间
                        .signWith(getKey(), SignatureAlgorithm.HS256).compact();
        return jwts;
    }

    public static Key getKey() {
        Key key = new SecretKeySpec(SECRET.getBytes(), SignatureAlgorithm.HS256.getJcaName());
        return key;
    }

    /**
     * 解析JWT令牌
     *
     * @param jwts
     * @return
     */
    public static Jws<Claims> parseJWT(String jwts) {
        Jws<Claims> claimsJws = null;
        try {
            claimsJws = Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(jwts);
        } catch (Exception e) {
            log.info("jwt解析失败:{}", e.getMessage());
        }
        return claimsJws;
    }

    public static void parseHeaer(String jwts) {
        Jws<Claims> claimsJws = parseJWT(jwts);

        System.out.println(claimsJws.getHeader().get("a"));
    }

    public static void parseBody(String jwts) {
        Jws<Claims> claimsJws = parseJWT(jwts);
        System.out.println(claimsJws.getBody().get("nickname", String.class));
        System.out.println(claimsJws.getBody().getExpiration().getTime());

    }


    public static void main(String[] args) {
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("a", "123");
        Map<String, String> payloadMap = new HashMap<>();
        payloadMap.put("nickname", "xiaoxiao");
        payloadMap.put("age", "26");
        payloadMap.put("hobby", "ball");

        String jwt2 = createJWT(payloadMap);
        System.out.println("jwt:" + jwt2);
//        parseHeaer(jwt2);
        System.out.println("-------------------------------");
        parseBody(jwt2);


    }

}
