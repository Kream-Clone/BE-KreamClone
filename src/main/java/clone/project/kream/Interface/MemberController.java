package clone.project.kream.Interface;

import clone.project.kream.domain.entity.Member;
import clone.project.kream.service.MemberService;
import clone.project.kream.service.dto.request.LoginRequestDto;
import clone.project.kream.service.dto.request.MemberRequestDto;
import clone.project.kream.service.dto.response.MemberResponseDto;
import clone.project.kream.service.dto.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseDto<?> createMember(@RequestBody MemberRequestDto memberRequestDto) {

        return memberService.createMember(memberRequestDto);
    }

    @PostMapping("/login")
    public ResponseDto<?> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {

        return memberService.login(loginRequestDto, response);
    }
}
