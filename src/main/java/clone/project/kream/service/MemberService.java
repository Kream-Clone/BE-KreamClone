package clone.project.kream.service;

import clone.project.kream.Exception.CustomException;
import clone.project.kream.Exception.ErrorCode;
import clone.project.kream.domain.entity.Member;
import clone.project.kream.domain.repository.MemberRepository;
import clone.project.kream.security.jwt.JwtTokenUtils;
import clone.project.kream.service.dto.request.LoginRequestDto;
import clone.project.kream.service.dto.request.MemberRequestDto;
import clone.project.kream.service.dto.response.LoginResponseDto;
import clone.project.kream.service.dto.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final JwtTokenUtils jwtTokenUtils;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final MemberRepository memberRepository;
    public ResponseDto<?> createMember(MemberRequestDto memberRequestDto) {
        log.info("id = {}", memberRequestDto.getEmail());
        Member member = new Member(memberRequestDto);
        String rawPassword = member.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        member.setPassword(encPassword);
        memberRepository.save(member);

        return ResponseDto.success("회원가입에 성공하였습니다.");
    }

    public ResponseDto<?> login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        Member member = isPresentMember(loginRequestDto.getEmail());
        if (null == member) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }
        if (!member.validatePassword(bCryptPasswordEncoder, loginRequestDto.getPassword())) {
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }
        String token = jwtTokenUtils.generateJwtToken(member);
        tokenToHeaders(token, response);

        return ResponseDto.success(
                LoginResponseDto.builder()
                        .email(member.getEmail())
                        .nickname(member.getNickname())
                        .profileImageUrl(member.getProfileImageUrl())
                        .build()
        );
    }

    @Transactional
    public Member isPresentMember(String email) {
        Optional<Member> optionalMember = memberRepository.findByEmail(email);
        return optionalMember.orElseThrow(null);
    }
    public void tokenToHeaders(String token, HttpServletResponse response) {
        response.addHeader("Access_Token", "Bearer " + token);

    }

}
