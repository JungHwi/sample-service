package com.breeze.farmskin.book.persistence.repository;

import com.breeze.core.code.BookStatus;
import com.breeze.core.config.jpa.DefaultJpaRepository;
import com.breeze.farmskin.book.persistence.entity.BookEntity;
import com.breeze.farmskin.book.persistence.repository.dsl.BookRepositoryCustom;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends DefaultJpaRepository<BookEntity, Long>, BookRepositoryCustom {

    @Modifying
    @Query("UPDATE BookEntity SET status = :status WHERE id = :id")
    int patchStatus(long id, BookStatus status);

}
