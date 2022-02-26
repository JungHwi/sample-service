package com.breeze.farmskin.book.persistence.repository.dsl;

import com.breeze.farmskin.book.controller.model.request.SearchBookRequest;
import com.breeze.farmskin.book.persistence.repository.dsl.dto.BookDto;
import com.breeze.farmskin.book.persistence.repository.dsl.dto.QBookDto;
import com.breeze.farmskin.book.persistence.repository.dsl.dto.QCategoryDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.breeze.farmskin.book.persistence.entity.QBookCategoryMapEntity.bookCategoryMapEntity;
import static com.breeze.farmskin.book.persistence.entity.QBookEntity.bookEntity;
import static com.breeze.farmskin.book.persistence.entity.QCategoryEntity.categoryEntity;
import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;

@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<BookDto> searchBooks(SearchBookRequest request) {
        return jpaQueryFactory
                .from(bookEntity)
                .innerJoin(bookCategoryMapEntity).on(bookEntity.id.eq(bookCategoryMapEntity.book.id))
                .innerJoin(categoryEntity).on(categoryEntity.id.eq(bookCategoryMapEntity.category.id))
                .where(searchBookQuery(request))
                .transform(
                        groupBy(bookEntity.id).list(
                                new QBookDto(
                                        bookEntity.id,
                                        bookEntity.author,
                                        bookEntity.title,
                                        bookEntity.status,
                                        list(new QCategoryDto(
                                                categoryEntity.id,
                                                categoryEntity.name
                                                )
                                        )
                                )
                        )
                );
    }

    private BooleanBuilder searchBookQuery(SearchBookRequest request) {
        BooleanBuilder builder = new BooleanBuilder();

        if (request == null) {
            return builder;
        }

        if (request.getCategoryId() != null) {
            builder.and(bookCategoryMapEntity.category.id.eq(request.getCategoryId()));
        }

        if (StringUtils.hasLength(request.getAuthor())) {
            builder.and(bookEntity.author.contains(request.getAuthor()));
        }

        if (StringUtils.hasLength(request.getTitle())) {
            builder.and(bookEntity.title.contains(request.getTitle()));
        }

        return builder;
    }
}