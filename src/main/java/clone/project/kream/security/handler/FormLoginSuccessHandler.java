package clone.project.kream.security.handler;

import clone.project.kream.security.UserDetailsImpl;
import clone.project.kream.security.jwt.JwtTokenUtils;
import clone.project.kream.service.dto.response.LoginInfoDto;
import clone.project.kream.service.dto.response.ResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class FormLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    public static final String AUTH_HEADER = "Authorization";
    public static final String TOKEN_TYPE = "BEARER";
    public static final String AUTH_REFRESH_HEADER = "Refresh";

    private ObjectMapper mapper = new ObjectMapper();


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {

        final UserDetailsImpl userDetails = ( (UserDetailsImpl) authentication.getPrincipal());
        final String token = JwtTokenUtils.generateJwtToken(userDetails.getUser());
//        final String refreshTokenStr = JwtTokenUtils.generateJwtRefreshToken();
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        // loginInfoDto 객체 생성
        LoginInfoDto loginInfoDto = LoginInfoDto.builder()
                .username(userDetails.getUsername())
                .profileImageUrl(userDetails.getUser().getProfileImageUrl())
                .build();

        // json 형태로 바꾸기
        String result = mapper.writeValueAsString(ResponseDto.success("로그인 성공"));
        response.getWriter().write(result);
        response.addHeader(AUTH_HEADER,TOKEN_TYPE+" "+token);
    }
}
