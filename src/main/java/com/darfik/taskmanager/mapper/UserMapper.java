package com.darfik.taskmanager.mapper;

import com.darfik.taskmanager.dto.auth.UserSignupPayload;
import com.darfik.taskmanager.dto.auth.UserSignupResponse;
import com.darfik.taskmanager.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    //@Mapping(target = "password", ignore = true)
    UserSignupResponse toDto(User user);

    User toEntity(UserSignupPayload userSignupPayload);

}
