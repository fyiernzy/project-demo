package com.demo.springbootdemo1.shared.user.web;

import com.demo.springbootdemo1.shared.user.application.dto.CreateUserModel;
import com.demo.springbootdemo1.shared.user.application.dto.UserModel;
import com.demo.springbootdemo1.shared.user.application.service.UserService;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserModel register(@RequestBody @Valid CreateUserModel createUserModel) {
        return userService.createUser(createUserModel);
    }

    @GetMapping("/{id}")
    public UserModel getUserById(@PathVariable String id) {
        return userService.findUserById(id);
    }

    @GetMapping
    public PagedModel<@NonNull UserModel> getAllUsers(@PageableDefault Pageable pageable) {
        return new PagedModel<>(userService.findUsers(pageable));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }
}
