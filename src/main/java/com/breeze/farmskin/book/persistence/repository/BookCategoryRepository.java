package com.breeze.farmskin.book.persistence.repository;

import com.breeze.core.config.jpa.DefaultJpaRepository;
import com.breeze.farmskin.book.persistence.entity.BookCategoryMapEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookCategoryRepository extends DefaultJpaRepository<BookCategoryMapEntity, Long> {

    @Modifying
    @Query("UPDATE BookCategoryMapEntity SET categoryId = :newCategoryId WHERE id = :mapId AND bookId = :bookId")
    int changeBookCategory(long bookId, long mapId, long newCategoryId);
}
