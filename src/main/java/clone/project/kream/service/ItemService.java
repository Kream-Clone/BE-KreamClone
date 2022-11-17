package clone.project.kream.service;

import clone.project.kream.domain.entity.Item;
import clone.project.kream.domain.repository.ItemRepository;
import clone.project.kream.domain.repository.QItemRepository;
import clone.project.kream.service.dto.ItemResponseDto;
import clone.project.kream.service.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    public final ItemRepository itemRepository;
    public final QItemRepository qItemRepository;


    public ResponseDto<List<ItemResponseDto>> getPagination(Long cursorId, Pageable pageable) {
        Slice<Item> items = qItemRepository.searchBySlice(cursorId, pageable);

        List<ItemResponseDto> itemResponseDtos = new ArrayList<>();

        for (Item item : items) {
            itemResponseDtos.add(ItemResponseDto.fromEntity(item));
        }

        return ResponseDto.success(itemResponseDtos);
    }
}
