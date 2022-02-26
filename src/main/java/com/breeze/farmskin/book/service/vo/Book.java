package com.breeze.farmskin.book.service.vo;

import com.breeze.core.code.BookStatus;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@Getter
@Builder
public class Book {
    private Long id;

    private String author;

    private String title;

    private BookStatus status;

    private List<Category> categoryList;

    public boolean valid() {
        return validAuthor() &&
                validTitle() &&
                validCategories();
    }

    public boolean validAuthor() {
        return StringUtils.hasLength(this.author);
    }

    public boolean validTitle() {
        return StringUtils.hasLength(this.author);
    }

    public boolean validCategories() {
        return !CollectionUtils.isEmpty(this.categoryList);
    }
}
