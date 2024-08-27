package com.darfik.taskmanager.mapper;

import com.darfik.taskmanager.dto.UserDto;
import com.darfik.taskmanager.user.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);
    User toEntity(UserDto userDto);

}
