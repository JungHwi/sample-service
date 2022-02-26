package com.breeze.farmskin.book.persistence.entity.id;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class BookCategoryId implements Serializable {

    @Column(name = "book_id")
    private long bookId;

    @Column(name = "category_id")
    private long categoryId;
}
