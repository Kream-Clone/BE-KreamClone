package clone.project.kream.interfaces;

import clone.project.kream.infra.JsoupComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CrawlingController {

    public final JsoupComponent jsoupComponent;

    @GetMapping("/crawling/items")
    public String crawling() {
        jsoupComponent.getItemList();
        return "success";
    }
}
