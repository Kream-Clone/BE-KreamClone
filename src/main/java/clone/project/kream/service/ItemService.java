package clone.project.kream.service;

import clone.project.kream.domain.entity.Item;
import clone.project.kream.domain.repository.ItemRepository;
import clone.project.kream.service.dto.ItemResponseDto;
import clone.project.kream.service.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    public final ItemRepository itemRepository;


    public ResponseDto<?> getAllItems() {
        List<Item> itemList = itemRepository.findAll();
        List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();

        for (Item item : itemList) {
            itemResponseDtoList.add(ItemResponseDto.fromEntity(item));
        }

        return ResponseDto.success(itemResponseDtoList);
    }
}
