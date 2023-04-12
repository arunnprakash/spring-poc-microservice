package com.example.demo.mapper;

import org.mapstruct.Mapper;

import com.example.demo.dto.BookingDTO;
import com.example.demo.model.Booking;

@Mapper(componentModel="spring")
public interface BookingMapper extends BaseMapper<BookingDTO, Booking> {

}
