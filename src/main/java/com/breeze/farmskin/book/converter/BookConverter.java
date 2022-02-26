package com.breeze.farmskin.book.converter;

import com.breeze.farmskin.book.controller.model.BookModel;
import com.breeze.farmskin.book.persistence.entity.BookEntity;
import com.breeze.farmskin.book.persistence.repository.dsl.dto.BookDto;
import com.breeze.farmskin.book.service.vo.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryConverter.class})
public interface BookConverter {

    BookModel convertModel(Book book);
    List<BookModel> convertModel(List<Book> bookList);

    Book convert(BookModel model);

    @Mapping(target = "categoryList", source = "mappings")
    Book convert(BookEntity entity);

   @Mapping(target = "mappings", source = "categoryList")
    BookEntity convertEntity(Book book);


    Book convert(BookDto dto);
    List<Book> convertList(List<BookDto> dtoList);
}
