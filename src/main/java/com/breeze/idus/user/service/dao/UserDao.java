package com.breeze.idus.user.service.dao;

import com.breeze.core.exception.BadRequestException;
import com.breeze.core.exception.EmptyDataException;
import com.breeze.core.wrapper.JpaPageRequest;
import com.breeze.idus.user.controller.model.request.UserRequest;
import com.breeze.idus.user.converter.UserConverter;
import com.breeze.idus.user.persistence.entity.UserEntity;
import com.breeze.idus.user.persistence.repository.UserRepository;
import com.breeze.idus.user.service.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDao {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    @Transactional
    public User save(User user) {
        if (!user.valid()) {
            throw new BadRequestException("잘못된 요청 정보입니다.");
        }

        UserEntity request = userConverter.convertToEntity(user);
        UserEntity userEntity = userRepository.save(request);
        return userConverter.converts(userEntity);
    }

    @Transactional(readOnly = true)
    public User findById(long userId) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new EmptyDataException("Not found user. userId - " + userId));
        return userConverter.converts(userEntity);
    }

    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new EmptyDataException("Not found user. email - " + email));
        return userConverter.converts(userEntity);
    }

    @Transactional(readOnly = true)
    public Page<User> searchUserList(UserRequest request, Pageable pageable) {
        long count = userRepository.searchUserCount(request.getName(), request.getEmail());

        if (count == 0) {
            throw new EmptyDataException("Not found any user. name - " + request.getName() +
                    ", email - " + request.getEmail() +
                    ", limit - " + pageable.getPageSize() +
                    ", offset - " + pageable.getOffset());
        }

        List<UserEntity> userEntityList = userRepository.searchUserList(request.getName(), request.getEmail(), pageable);

        return new PageImpl<>(userConverter.convertsList(userEntityList), pageable, count);
    }
}
