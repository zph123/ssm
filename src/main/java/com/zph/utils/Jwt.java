package com.zph.utils;

import io.jsonwebtoken.*;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

public class Jwt {
    // token秘钥  太短会报错
    public static String SECRET = "qwerasdfdxzvdfajjlkjeiojznvxndjkfaowijeiodlwe";

    /**
     * 生成Jwt的方法
     *
     * @param id
     *            用户ID
     * @param subject
     *            用户所有信息
     * @param ttlMillis
     *            过期时间
     * @return Token String 凭证
     */
    public static String createJWT(String id, String subject, long ttlMillis) {
        // 签名方法 HS256
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        // 生成Jwt的时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // 生成秘钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        // 设置JWT所存储的信息
        JwtBuilder builder = Jwts.builder().setId(id).setIssuedAt(now).setSubject(subject).signWith(signingKey,
                signatureAlgorithm);

        //builder.claim("name", "value"); //存储自定义信息

        // 设置过期时间
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        // 构建JWT并将其序列化为紧凑的URL安全字符串
        return builder.compact();
    }

    /**
     * 解析Jwt字符串
     *
     * @param jwt
     *            Jwt字符串
     * @return Claims 解析后的对象
     */
    public static Claims parseJWT(String jwt) {
        return Jwts.parserBuilder().setSigningKey(DatatypeConverter.parseBase64Binary(SECRET)).build().parseClaimsJws(jwt).getBody();
   }

    /**
     * 验证JWT
     */
    public static Claims validateJWT(String jwtStr) {
        try {
            Claims claims = parseJWT(jwtStr);
            return claims;
//            System.out.println(claims);
        } catch (JwtException e) {
            throw e;
            //don't trust the JWT!
        }
    }
}
