package clone.project.kream.infra;

import clone.project.kream.domain.entity.Item;
import clone.project.kream.domain.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JsoupComponent {
    static final String itemList = "https://kream.co.kr/search";
    public final ItemRepository itemRepository;

    public void getItemList() {
        Connection conn = Jsoup.connect(itemList);

        try {
            Document document = conn.get();
            Elements result = document.select(".item_inner");
            for (Element element : result) {
                Elements info = element.select(".product_info");
                String priceStr = info.select(".amount").text().replaceAll(",","");
                Item crawledItem = Item.builder()
                        .brand(info.select(".brand").text())
                        .name(info.select(".name").text())
                        .translatedName(info.select(".translated_name").text())
                        .price(Integer.parseInt(priceStr.substring(0, priceStr.length() - 1)))
                        .productImage(element.select(".image").attr("abs:src"))
                        .build();
                itemRepository.save(crawledItem);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
