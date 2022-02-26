package com.breeze.farmskin.book.persistence.repository.dsl;

import com.breeze.farmskin.book.controller.model.request.SearchBookRequest;
import com.breeze.farmskin.book.persistence.repository.dsl.dto.BookDto;

import java.util.List;

public interface BookRepositoryCustom {

    List<BookDto> searchBooks(SearchBookRequest request);
}
