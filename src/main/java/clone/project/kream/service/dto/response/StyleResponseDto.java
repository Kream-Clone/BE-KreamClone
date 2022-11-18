package clone.project.kream.service.dto.response;

import clone.project.kream.domain.entity.Member;
import clone.project.kream.domain.entity.Style;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StyleResponseDto {

    private Long styleId;
    private String nickname;
    private String styleImage;
    private String content;
    private int likes;


    public static StyleResponseDto fromEntity(Member member, Style style) {
        StyleResponseDto styleResponseDto =  new StyleResponseDto();
        styleResponseDto.styleId = style.getStyleId();
        styleResponseDto.styleImage = style.getStyleImage();
        styleResponseDto.nickname = member.getNickname();
        styleResponseDto.content = style.getContent();
        styleResponseDto.likes = style.getLikes();

        return styleResponseDto;
    }

}
