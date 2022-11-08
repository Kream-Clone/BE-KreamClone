package clone.project.kream.interfaces;

import clone.project.kream.service.ItemService;
import clone.project.kream.service.dto.ItemResponseDto;
import clone.project.kream.service.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemController {

    public static final int DEFAULT_SIZE = 10;
    public final ItemService itemService;

    @GetMapping("/search")
    public ResponseDto<List<ItemResponseDto>> showItemList(@RequestParam(value = "idx", defaultValue = "0") long cursorId,
//                                                          @RequestParam(value = "search", required = false) String search,
                                                           @PageableDefault(sort = "idx", direction = Sort.Direction.ASC) Pageable pageable) {
        return itemService.getPagination(cursorId, pageable);
    }
}
