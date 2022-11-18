package clone.project.kream.service.dto.response;

import clone.project.kream.domain.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class LoginResponseDto {
    private String email;
    private String nickname;
    private String profileImageUrl;

    public LoginResponseDto(Member member) {
        this.email = member.getEmail();
        this.nickname = member.getNickname();
        this.profileImageUrl = member.getProfileImageUrl();
    }

}
