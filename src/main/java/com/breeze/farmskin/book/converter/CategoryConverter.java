package com.breeze.farmskin.book.converter;

import com.breeze.farmskin.book.persistence.entity.BookCategoryMapEntity;
import com.breeze.farmskin.book.persistence.entity.CategoryEntity;
import com.breeze.farmskin.book.persistence.repository.dsl.dto.CategoryDto;
import com.breeze.farmskin.book.service.vo.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryConverter {

    @Mapping(target = "mappings", ignore = true)
    CategoryEntity convertEntity(Category category);
    Category convert(CategoryEntity entity);

    @Mappings({
            @Mapping(target = "categoryId", source = "id"),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "bookId", ignore = true),
            @Mapping(target = "book", ignore = true),
            @Mapping(target = "category", ignore = true),
    })
    BookCategoryMapEntity convertMap(Category category);
    List<BookCategoryMapEntity> convertMap(List<Category> categoryList);

    @Mappings({
            @Mapping(target = "id", source = "categoryId"),
            @Mapping(target = "name", ignore = true)
    })
    Category convert(BookCategoryMapEntity category);

    Category convert(CategoryDto dto);
    List<Category> convert(List<CategoryDto> dtoList);
}
