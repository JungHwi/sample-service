package com.breeze.core.wrapper;

import org.springframework.data.domain.Page;

public class SimpleJpaPageContents<T> extends JpaPageContents<T, T> {

    public SimpleJpaPageContents(Page<T> page) {
        super(page);
    }

    @Override
    public T converts(T content) {
        return content;
    }
}