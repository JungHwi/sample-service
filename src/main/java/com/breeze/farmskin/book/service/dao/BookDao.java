package com.breeze.farmskin.book.service.dao;

import com.breeze.core.code.BookStatus;
import com.breeze.core.exception.BadRequestException;
import com.breeze.core.exception.EmptyDataException;
import com.breeze.farmskin.book.controller.model.request.SearchBookRequest;
import com.breeze.farmskin.book.converter.BookConverter;
import com.breeze.farmskin.book.persistence.entity.BookCategoryMapEntity;
import com.breeze.farmskin.book.persistence.entity.BookEntity;
import com.breeze.farmskin.book.persistence.repository.BookCategoryRepository;
import com.breeze.farmskin.book.persistence.repository.BookRepository;
import com.breeze.farmskin.book.persistence.repository.dsl.dto.BookDto;
import com.breeze.farmskin.book.service.vo.Book;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class BookDao {

    private final BookRepository bookRepository;
    private final BookCategoryRepository bookCategoryRepository;
    private final BookConverter bookConverter;

    @Transactional
    public Book save(Book book) {
        if (!book.valid()) {
            throw new BadRequestException("도서 정보가 잘못되었습니다.");
        }

        BookEntity request = bookConverter.convertEntity(book);
        BookEntity entity = bookRepository.save(request);

        List<BookCategoryMapEntity> mapEntityList = new ArrayList<>();
        for (BookCategoryMapEntity map : request.getMappings()) {
            map.setBookId(entity.getId());
            mapEntityList.add(map);
        }

        entity.setMappings(bookCategoryRepository.saveAll(mapEntityList));

        return bookConverter.convert(entity);
    }

    @Transactional
    public int changeStatus(long id, BookStatus status) {
        int result = bookRepository.patchStatus(id, status);
        if (result < 1) {
            throw new BadRequestException("상태 변경된 건이 0건입니다.");
        }
        return result;
    }

    @Transactional
    public int changeBookCategory(long bookId, long mapId, long categoryId) {
        int result = bookCategoryRepository.changeBookCategory(bookId, mapId, categoryId);

        if (result < 1) {
            throw new BadRequestException("카테고리 변경된 건이 0건입니다.");
        }

        return result;
    }

    @Transactional(readOnly = true)
    public List<Book> searchBooks(SearchBookRequest request) {
        List<BookDto> bookDtoList = bookRepository.searchBooks(request);
        return bookConverter.convertList(bookDtoList);
    }

    @Transactional(readOnly = true)
    public Book findById(long bookId) {
        BookEntity entity = bookRepository.findById(bookId)
                .orElseThrow(() -> new EmptyDataException("도서 정보를 찾을 수 없습니다."));

        return bookConverter.convert(entity);
    }
}
