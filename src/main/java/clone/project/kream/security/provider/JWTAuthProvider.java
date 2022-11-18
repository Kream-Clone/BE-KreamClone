package clone.project.kream.security.provider;


import clone.project.kream.domain.entity.Member;
import clone.project.kream.domain.repository.MemberRepository;
import clone.project.kream.security.UserDetailsImpl;
import clone.project.kream.security.jwt.JwtDecoder;
import clone.project.kream.security.jwt.JwtPreProcessingToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component

public class JWTAuthProvider implements AuthenticationProvider {
    private final JwtDecoder jwtDecoder;

    private final MemberRepository memberRepository;

    @Autowired
    public JWTAuthProvider (JwtDecoder jwtDecoder,MemberRepository memberRepository){
        this.jwtDecoder=jwtDecoder;
        this.memberRepository=memberRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String token = (String) authentication.getPrincipal();
        String username = jwtDecoder.decodeUsername(token);
//         TODO: API 사용시마다 매번 User DB 조회 필요
//          -> 해결을 위해서는 UserDetailsImpl 에 User 객체를 저장하지 않도록 수정
//          ex) UserDetailsImpl 에 userId, username, role 만 저장
//            -> JWT 에 userId, username, role 정보를 암호화/복호화하여 사용
        Member member = memberRepository.findByEmail(username).orElseThrow(()->new AuthenticationCredentialsNotFoundException("해당 회원정보가 없습니다."));
        UserDetailsImpl userDetails = new UserDetailsImpl(member);

        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtPreProcessingToken.class.isAssignableFrom(authentication);
    }
}
