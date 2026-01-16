package com.demo.springbootdemo1.shared.user.application.service;

import com.demo.springbootdemo1.shared.user.application.dto.CreateUserModel;
import com.demo.springbootdemo1.shared.user.application.dto.UserModel;
import com.demo.springbootdemo1.shared.user.application.mapper.UserMapper;
import com.demo.springbootdemo1.shared.user.domain.User;
import com.demo.springbootdemo1.shared.user.domain.UserRepository;
import com.demo.springbootdemo1.shared.utils.AssertUtils;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public @NonNull UserModel createUser(CreateUserModel createUserModel) {
        AssertUtils.isTrue(!userRepository.existsByUsername(createUserModel.username()),
            () -> new IllegalArgumentException("Username already exists"));
        AssertUtils.isTrue(!userRepository.existsByEmail(createUserModel.email()),
            () -> new IllegalArgumentException("Email already exists"));
        User user = userMapper.toEntity(createUserModel);
        user.setPassword(passwordEncoder.encode(createUserModel.password()));
        return userMapper.toModel(userRepository.save(user));
    }

    @Override
    @Cacheable(value = "users", key = "#id")
    public @Nullable UserModel findUserById(String id) {
        return userRepository.findById(id).map(userMapper::toModel).orElse(null);
    }

    @Override
    public @Nullable UserModel findUserByUsername(String username) {
        return userRepository.findByUsername(username).map(userMapper::toModel).orElse(null);
    }

    @Override
    public @NonNull Page<@NonNull UserModel> findUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(userMapper::toModel);
    }

    @Override
    @Transactional
    @CacheEvict(value = "users", key = "#id")
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
