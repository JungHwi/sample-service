package com.breeze.farmskin.book.service;

import com.breeze.core.code.BookStatus;
import com.breeze.farmskin.book.controller.model.request.SearchBookRequest;
import com.breeze.farmskin.book.service.dao.BookDao;
import com.breeze.farmskin.book.service.vo.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookDao bookDao;

    public Book save(Book book) {
        return bookDao.save(book);
    }

    public int changeStatus(long id, BookStatus status) {
        return bookDao.changeStatus(id, status);
    }

    public int changeBookCategory(long bookId, long mapId, long categoryId) {
        return bookDao.changeBookCategory(bookId, mapId, categoryId);
    }

    public List<Book> searchBooks(SearchBookRequest request) {
        return bookDao.searchBooks(request);
    }
}
