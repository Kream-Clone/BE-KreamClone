package clone.project.kream.service;

import clone.project.kream.domain.entity.Member;
import clone.project.kream.domain.repository.MemberRepository;
import clone.project.kream.service.dto.request.MemberRequestDto;
import clone.project.kream.service.dto.response.MemberResponseDto;
import clone.project.kream.service.dto.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    public ResponseDto<?> createMember(MemberRequestDto memberRequestDto) {
        Member member = new Member(memberRequestDto);
        memberRepository.save(member);

        return ResponseDto.success("회원가입에 성공하였습니다.");
    }
}
