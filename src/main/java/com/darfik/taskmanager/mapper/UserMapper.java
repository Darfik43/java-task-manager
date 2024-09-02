package com.darfik.taskmanager.mapper;

import com.darfik.taskmanager.dto.UserDto;
import com.darfik.taskmanager.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "password", ignore = true)
    UserDto toDto(User user);

    User toEntity(UserDto userDto);

}
