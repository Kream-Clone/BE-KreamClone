package clone.project.kream.Interface;

import clone.project.kream.security.UserDetailsImpl;
import clone.project.kream.service.StyleService;
import clone.project.kream.service.dto.request.StyleRequestDto;
import clone.project.kream.service.dto.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class StyleController {

    @PostMapping("/social")
    public ResponseDto<?> createStyle(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
                                      @RequestBody StyleRequestDto styleRequestDto){
        return StyleService.createStyle(userDetailsImpl, styleRequestDto);
    }
}
