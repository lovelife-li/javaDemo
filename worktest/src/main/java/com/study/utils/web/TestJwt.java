package com.study.utils.web;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * todo
 *
 * @author ldb
 * @date 2020/07/20
 */
public class TestJwt {

    public static void main(String[] args) {
        // We need a signing key, so we'll create one just for this example. Usually
        // the key would be read from your application configuration instead.
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        String secretString2 = Encoders.BASE64.encode(key.getEncoded());
        System.out.println(secretString2);

        String secretString = "IF7w5BgPZXeLaTTrBU9TqN3dgdPRQ6pL66C1YgSy2DI=";
        /**
         * IF7w5BgPZXeLaTTrBU9TqN3dgdPRQ6pL66C1YgSy2DI=
         * eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJKb2UifQ.vfCEOpcOxPvRfZfBkbbV7msy3catk-IGaRcp6Omwoig
         * eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJKb2UifQ.vfCEOpcOxPvRfZfBkbbV7msy3catk-IGaRcp6Omwoig
         */

        String jws = Jwts.builder().setSubject("Joe").signWith(key,SignatureAlgorithm.HS256).compact();
        System.out.println(jws);

        String subject = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jws).getBody().getSubject();
        System.out.println(subject);


    }

    public void test(){
        String sharedTokenSecret="hellooauthhellooauthhellooauthhellooauth";//密钥
        Key key = new SecretKeySpec(sharedTokenSecret.getBytes(),
                SignatureAlgorithm.HS256.getJcaName());
        //生成JWT令牌
//        String jwts=
//                Jwts.builder().setHeaderParams(headerMap).setClaims(payloadMap).signWith(key,
//                        SignatureAlgorithm.HS256).compact();
//        //解析JWT令牌
//        Jws<Claims> claimsJws =Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwts);
//        JwsHeader header = claimsJws.getHeader();
//        Claims body = claimsJws.getBody();
    }

    /**
     * 设置header
     * @param header
     * @return
     */
    public static Header createHeader(Map<String,Object> header){
        return Jwts.header(header);
    }

    /**
     * 设置body
     * @param header
     * @return
     */
    public static void setBody(Map<String,Object> header){
        String jws = Jwts.builder()
                .claim("age","12")
                .claim("username","zhangsan")
                .setIssuer("me")
                .setSubject("Bob")
                .setAudience("you")
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*30)) //a java.util.Date
//                .setNotBefore(notBefore) //a java.util.Date
                .setIssuedAt(new Date()) // for example, now
                .setId(UUID.randomUUID().toString()).compact(); //just an example id
    }
}
