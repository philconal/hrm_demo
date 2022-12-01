package conal.hrm_demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class CustomPage<T> {
    List<T> content;
    CustomPageable  pageable;

    public CustomPage(Page<T> page) {
        this.content = page.getContent();
        this.pageable = CustomPageable.builder()
                .hasNext(page.hasNext())
                .hasPrevious(page.hasPrevious())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .pageSize(page.getSize())
                .pageNumber(page.getPageable().getPageNumber())
                .build();
    }

    @Data
    @Builder
    @AllArgsConstructor
    static
    class CustomPageable {
        private int pageNumber;
        private int pageSize;
        private long totalElements;
        private int totalPages;
        private boolean hasNext;
        private boolean hasPrevious;
    }
}
