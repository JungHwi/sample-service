package com.breeze.farmskin.book.persistence.entity;

import com.breeze.core.code.BookStatus;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "book")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, insertable = false, updatable = false, length = 20)
    private Long id;

    @Column(nullable = false, length = 50)
    private String author;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private BookStatus status;

    @OneToMany(mappedBy = "book", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<BookCategoryMapEntity> mappings;

    @PostPersist
    public void postPersist() {
        if (mappings != null) {
            mappings.forEach(category -> category.setBookId(this.id));
        }
    }
}