package clone.project.kream.service.dto.response;

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
    private String styleImage;
    private String content;
    private int likes;

}
