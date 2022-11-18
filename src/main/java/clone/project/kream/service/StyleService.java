package clone.project.kream.service;

import clone.project.kream.domain.entity.Member;
import clone.project.kream.domain.entity.Style;
import clone.project.kream.domain.repository.MemberRepository;
import clone.project.kream.domain.repository.StyleRepository;
import clone.project.kream.security.UserDetailsImpl;
import clone.project.kream.service.dto.request.StyleRequestDto;
import clone.project.kream.service.dto.response.ResponseDto;
import clone.project.kream.service.dto.response.StyleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StyleService {

    private final MemberRepository memberRepository;
    private final StyleRepository styleRepository;

    public ResponseDto<?> createStyle(UserDetailsImpl userDetails, StyleRequestDto styleRequestDto) {
        Member member = memberRepository.findByEmail(userDetails.getUsername()).orElseThrow(
                RuntimeException::new
        );
        Style style = Style.builder()
                .styleImage(styleRequestDto.getStyleImg())
                .content(styleRequestDto.getContent())
                .member(member)
                .build();
        styleRepository.save(style);
        StyleResponseDto styleResponseDto = StyleResponseDto.fromEntity(member, style);
        return ResponseDto.success(styleResponseDto);
    }

    public ResponseDto<?> getAllStyle(UserDetailsImpl userDetails) {
        Member member = memberRepository.findByEmail(userDetails.getUsername()).orElseThrow(
                RuntimeException::new
        );
        List<Style> styleList = styleRepository.findAll();

        List<StyleResponseDto> styleResponseDtos = new ArrayList<>();
        for (Style style : styleList) {
            styleResponseDtos.add(StyleResponseDto.fromEntity(member, style));
        }
        return ResponseDto.success(styleResponseDtos);
    }

    public ResponseDto<?> deleteStyle(UserDetailsImpl userDetails, Long styleId) {
        Member member = memberRepository.findByEmail(userDetails.getUsername()).orElseThrow(
                RuntimeException::new
        );
        Style style = styleRepository.findById(styleId).orElseThrow(
                RuntimeException::new
        );
        if(member.getEmail().equals(style.getMember().getEmail())) {
            styleRepository.deleteById(style.getStyleId());
            return ResponseDto.success("삭제가 완료되었습니다.");
        }
        throw new RuntimeException();
    }
}
