package clone.project.kream.domain.repository;

import clone.project.kream.domain.entity.Item;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

import static clone.project.kream.domain.entity.QItem.item;

@Repository
public class QItemRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public QItemRepository(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }


    public Slice<Item> searchBySlice(Long lastItemId, Pageable pageable) {
        List<Item> result = jpaQueryFactory.selectFrom(item)
                .where(isLastItemId(lastItemId))
                .orderBy(item.id.desc())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        return checkLastPage(pageable, result);
    }

    private BooleanExpression isLastItemId(Long lastItemId) {

        if(lastItemId == null) return null;
        return item.id.lt(lastItemId);
    }

    private Slice<Item> checkLastPage(Pageable pageable, List<Item> result) {

        boolean hasNext = false;

        if(result.size() > pageable.getPageSize()) {
            hasNext = true;
            result.remove(pageable.getPageSize());
        }

        return new SliceImpl<>(result, pageable, hasNext);
    }


}
