package com.huynhdung.mapper;

import com.huynhdung.dto.request.UserCreationRequest;
import com.huynhdung.dto.request.UserUpdateRequest;
import com.huynhdung.dto.response.UserResponse;
import com.huynhdung.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.web.bind.annotation.RequestMapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

//    @Mapping(source = "lastName", target = "firstName")
    UserResponse toUserResponse(User user);
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
