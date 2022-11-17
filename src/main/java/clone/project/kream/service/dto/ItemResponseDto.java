package clone.project.kream.service.dto;

import clone.project.kream.domain.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemResponseDto {
    private Long id;
    private String brand;
    private String name;
    private String translatedName;
    private int price;
    private String productImage;

    public static ItemResponseDto fromEntity(Item item) {
        return new ItemResponseDto(
                item.getId(),
                item.getBrand(),
                item.getName(),
                item.getTranslatedName(),
                item.getPrice(),
                item.getProductImage()
        );
    }
}
