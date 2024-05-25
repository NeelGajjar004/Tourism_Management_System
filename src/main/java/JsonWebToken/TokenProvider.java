/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JsonWebToken;

import static JsonWebToken.Constants.REMEMBERME_VALIDITY_SECONDS;
import io.jsonwebtoken.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.joining;
import javax.annotation.PostConstruct;
import javax.inject.Named;
/**
 *
 * @author Admin
 */
@Named
public class TokenProvider implements Serializable {
    
    private static final Logger LOGGER = Logger.getLogger(TokenProvider.class.getName());
    
    private static final String AUTHORITIES_KEY = "auth";
    
    private String secretKey;
    
    private long tokenValidity;
    private long tokenValidityForRememberMe;
    
    @PostConstruct
    public void init(){
        this.secretKey = "My-Secret-JWT-Key-Is-A-Secret";
        this.tokenValidity = TimeUnit.HOURS.toMillis(10);
        this.tokenValidityForRememberMe = TimeUnit.SECONDS.toMillis(REMEMBERME_VALIDITY_SECONDS);
    }
    
    public String createToken(String username,Set<String> authorities,Boolean rememberMe){
        long now = (new Date()).getTime();
        long validity = rememberMe ? tokenValidityForRememberMe : tokenValidity;
        System.out.println("====> TokenProvider := In Create Token Method");
        return Jwts.builder()
                .setSubject(username)
                .setIssuer("localhost")
                .claim(AUTHORITIES_KEY, authorities.stream().collect(joining(",")))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .setExpiration(new Date(now+validity))
                .compact();
    }

    public JWTCredential getCredential(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
        System.out.println("====> TokenProvider := In Get Credential Method");
        Set<String> authorities = Arrays.asList(claims.get(AUTHORITIES_KEY).toString().split(","))
                                        .stream()
                                        .collect(Collectors.toSet());
        
        return new JWTCredential(claims.getSubject(), authorities);
    }

    public boolean validateToken(String authToken){
        try{
            System.out.println("====> TokenProvider := In Validate Token Method");
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(authToken);
            return true;
        }catch(SignatureException se){
            LOGGER.log(Level.INFO,"Invalid JWT Signature : {0} ",se.getMessage());
            return false;
        }
    }
    
}
