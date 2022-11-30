package conal.hrm_demo.dto.response;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class CustomPage<T> {
    List<T> content;
    CustomPageable pageable;

    public CustomPage(Page<T> page) {
        this.content = page.getContent();
        this.pageable = new CustomPageable(page.getPageable().getPageNumber(),
                page.getPageable().getPageSize(), page.getTotalElements(), page.getTotalPages());
    }

    @Data
    static
    class CustomPageable {
        private int pageNumber;
        private int pageSize;
        private long totalElements;
        private int totalPages;

        public CustomPageable(int pageNumber, int pageSize, long totalElements, int totalPages) {
            this.pageNumber = pageNumber;
            this.pageSize = pageSize;
            this.totalElements = totalElements;
            this.totalPages = totalPages;
        }
    }
}
