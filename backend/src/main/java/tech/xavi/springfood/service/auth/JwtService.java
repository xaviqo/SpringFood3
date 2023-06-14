package tech.xavi.springfood.service.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

@Service
public class JwtService {

    private final String SECRET_KEY;
    private final String ISSUER;
    private final int ACCESS_TKN_EXP_SEC;
    private final int REFRESH_TKN_EXP_SEC;

    public JwtService(
            @Value("${springfood.jwt.secret}") String secretKey,
            @Value("${springfood.jwt.issuer}") String issuer,
            @Value("${springfood.jwt.access-tkn-exp-sec}") int accessTknExpSec,
            @Value("${springfood.jwt.refresh-tkn-exp-sec}") int refreshTknExpSec
    ) {
        this.SECRET_KEY = secretKey;
        this.ISSUER = issuer;
        this.ACCESS_TKN_EXP_SEC = accessTknExpSec;
        this.REFRESH_TKN_EXP_SEC = refreshTknExpSec;
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        return extractUsername(token)
                .equals(userDetails.getUsername())
                && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token){
        return extractExpiration(token)
                .before(new Date());
    }

    private Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }


    public String generateRefreshToken(UserDetails userDetails) {
        return Jwts.builder()
                .setIssuer(ISSUER)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TKN_EXP_SEC))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateAccessToken(
            UserDetails userDetails,
            List<String> roles
    ) {
        return Jwts.builder()
                .claim("roles",roles)
                .setIssuer(ISSUER)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TKN_EXP_SEC))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Collection<? extends GrantedAuthority> extractAuthorities(String token) {
        return (Collection<? extends GrantedAuthority>)
                extractClaim(token, claims -> claims.get("roles", Collection.class))
                .stream()
                .map(str -> new SimpleGrantedAuthority((String) str))
                .toList();
    }

    public String extractUsername(String token){
        return extractClaim(token,Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver){
        return claimResolver.apply(
                extractAllClaims(token)
        );
    }

    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(SECRET_KEY)
        );
    }
}

