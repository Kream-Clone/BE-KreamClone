package clone.project.kream.service;

import clone.project.kream.security.UserDetailsImpl;
import clone.project.kream.service.dto.request.StyleRequestDto;
import clone.project.kream.service.dto.response.ResponseDto;
import clone.project.kream.service.dto.response.StyleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StyleService {
    public static ResponseDto<?> createStyle(UserDetailsImpl userDetailsImpl, StyleRequestDto styleRequestDto) {


        return ResponseDto.success(styleRequestDto);
    }
}
