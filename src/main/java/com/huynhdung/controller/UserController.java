package com.huynhdung.controller;

import com.huynhdung.dto.request.ApiResponse;
import com.huynhdung.dto.request.UserCreationRequest;
import com.huynhdung.dto.request.UserUpdateRequest;
import com.huynhdung.dto.response.UserResponse;
import com.huynhdung.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {


    UserService userService;

    @PostMapping
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request){
        return ApiResponse.<UserResponse>builder()
                .result(userService.createUser(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<UserResponse>> getUsers(){
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("Username: {}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));


        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getUsers())
                .build();
    }
    @GetMapping("/{userId}")
    UserResponse getUser(@PathVariable("userId") Long userId){
        return userService.getUser(userId);
    }

    @PutMapping("/{userId}")
    UserResponse updateUser(@PathVariable Long userId, @RequestBody UserUpdateRequest request){
        return userService.updateUser(userId, request);
    }

    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
        return "User has been deleted";
    }

}
