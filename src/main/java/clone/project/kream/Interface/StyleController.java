package clone.project.kream.Interface;

import clone.project.kream.security.UserDetailsImpl;
import clone.project.kream.service.MemberService;
import clone.project.kream.service.StyleService;
import clone.project.kream.service.dto.request.StyleRequestDto;
import clone.project.kream.service.dto.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class StyleController {

    private final StyleService styleService;

    @PostMapping("/social")
    public ResponseDto<?> createStyle(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                      @RequestBody StyleRequestDto styleRequestDto) {

        return styleService.createStyle(userDetails, styleRequestDto);
    }

    @GetMapping("/social")
    public ResponseDto<?> getAllStyle(@AuthenticationPrincipal UserDetailsImpl userDtails) {

        return styleService.getAllStyle(userDtails);
    }

    @DeleteMapping("/social/{styleId}")
    public ResponseDto<?> deleteStyle(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long styleId) {

        return styleService.deleteStyle(userDetails, styleId);
    }
}
