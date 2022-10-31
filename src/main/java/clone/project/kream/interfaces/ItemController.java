package clone.project.kream.interfaces;

import clone.project.kream.service.ItemService;
import clone.project.kream.service.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ItemController {

    public final ItemService itemService;

    @GetMapping("/products")
    public ResponseDto<?> showItemList() {
        return itemService.getAllItems();
    }
}
