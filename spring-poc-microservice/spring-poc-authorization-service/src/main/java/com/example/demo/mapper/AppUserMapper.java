package com.example.demo.mapper;

import org.mapstruct.Mapper;

import com.example.demo.dto.AppUserDTO;
import com.example.demo.model.AppUser;

@Mapper(componentModel="spring")
public interface AppUserMapper extends BaseMapper<AppUserDTO, AppUser> {

}
