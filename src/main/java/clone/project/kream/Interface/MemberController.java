package clone.project.kream.Interface;

import clone.project.kream.service.MemberService;
import clone.project.kream.service.dto.request.MemberRequestDto;
import clone.project.kream.service.dto.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseDto<?> createMember(@RequestBody MemberRequestDto memberRequestDto){

        return memberService.createMember(memberRequestDto);
    }
}
