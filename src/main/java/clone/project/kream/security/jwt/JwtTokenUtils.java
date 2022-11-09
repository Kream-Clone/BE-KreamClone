package clone.project.kream.security.jwt;

import clone.project.kream.domain.entity.Member;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public final class JwtTokenUtils {

    @Value("${jwt.expiration.access-token}")
    private static Long JWT_TOKEN_VALID_MILLI_SEC;
    public static final String CLAIM_EXPIRED_DATE = "EXPIRED_DATE";
    public static final String CLAIM_USER_NAME = "USER_NAME";

    @Value("${jwt.secret}")
    public static String JWT_SECRET;

    public static String generateJwtToken(Member member){
        String token = null;

        try{
            token= com.auth0.jwt.JWT.create()
                    .withIssuer("KreamClone")
                    .withClaim(CLAIM_USER_NAME,member.getEmail()) //payload에 들어갈 data
                    .withClaim(CLAIM_EXPIRED_DATE,new Date(System.currentTimeMillis() + JWT_TOKEN_VALID_MILLI_SEC))
                    .sign(generateAlgorithm());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return token;
    }

//    public static String generateJwtRefreshToken(){
//        String refreshToken = null;
//
//        try{
//            refreshToken= com.auth0.jwt.JWT.create()
//                    .withClaim(CLAIM_EXPIRED_DATE,new Date(System.currentTimeMillis()+ JWT_REFRESH_TOKEN_VALID_MILLI_SEC))
//                    .sign(generateAlgorithm());
//        }
//        catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//        return refreshToken;
//    }

    private static Algorithm generateAlgorithm(){
        return Algorithm.HMAC256(JWT_SECRET);
    }
}
