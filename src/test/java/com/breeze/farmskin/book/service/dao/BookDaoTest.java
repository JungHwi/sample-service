package com.breeze.farmskin.book.service.dao;

import com.breeze.core.code.BookStatus;
import com.breeze.core.exception.BadRequestException;
import com.breeze.farmskin.book.controller.model.request.SearchBookRequest;
import com.breeze.farmskin.book.service.vo.Book;
import com.breeze.farmskin.book.service.vo.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = "spring.profiles.active:local")
class BookDaoTest {
    @Autowired
    private BookDao bookDao;

    @Test
    void saveExceptionTest() {
        Book book = Book.builder()
                .author("브리즈")
                .title("카테고리 없는 도서")
                .status(BookStatus.UNAVAILABLE)
                .build();
        assertThrows(BadRequestException.class, () -> bookDao.save(book));
    }

    @Test
    void save() {
        List<Category> categoryList = new ArrayList<>();
        Category category = Category.builder()
                .id(1L)
                .build();
        categoryList.add(category);
        Book book = Book.builder()
                .author("브리즈")
                .title("도서")
                .status(BookStatus.AVAILABLE)
                .categoryList(categoryList)
                .build();

        Book result = bookDao.save(book);

        assertNotEquals(result.getId(), null);
    }

    @Test
    void changeStatus() {
        long bookId = 1;
        BookStatus bookStatus = BookStatus.UNAVAILABLE;

        bookDao.changeStatus(bookId, bookStatus);
        Book book = bookDao.findById(bookId);

        assertEquals(book.getStatus(), bookStatus);
    }

    @Test
    void changeBookCategory() {
        long bookId = 1;
        long bookCategoryMapId = 1;
        long categoryId = 3;

        bookDao.changeBookCategory(bookId, bookCategoryMapId, categoryId);
        Book book = bookDao.findById(bookId);
        Category result = book.getCategoryList().stream()
                .filter(category -> category.getId() == categoryId)
                .findFirst()
                .orElse(Category.builder().id(0L).build());

        assertEquals(result.getId(), categoryId);
    }

    @Test
    void searchBooks() {
        long categoryId = 4L;
        String author = "승";
        String title = "리더";

        SearchBookRequest request = SearchBookRequest.builder()
                .categoryId(categoryId)
                .author(author)
                .title(title)
                .build();

        List<Book> bookList = bookDao.searchBooks(request);

        assertEquals(bookList.size(), 1);
    }
}