package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.BookingRepository;
import com.example.demo.dto.ResponseHeader;
import com.example.demo.dto.BookingDTO;
import com.example.demo.dto.BookingRequest.BookingRequestBody;
import com.example.demo.dto.BookingResponse.BookingResponseBody;
import com.example.demo.dto.BookingResponse.BookingResponseHolder;
import com.example.demo.mapper.BookingMapper;
import com.example.demo.model.Booking;

import lombok.extern.slf4j.Slf4j;

/**
 * @author __ArunPrakash__
 *
 */
@Slf4j
@Service
public class BookingServiceImpl extends BaseServiceImpl implements BookingService {
	
	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private BookingMapper bookingMapper;
	
	@Override
	public BookingResponseHolder getAllBookings() {
		List<Booking> bookings = bookingRepository.findAll();
		List<BookingDTO> appUserList = bookings.stream().map(bookingMapper::toDTO).toList();
		return BookingResponseHolder.builder()
				.header(ResponseHeader.builder().responseId(null).build())
				.body(BookingResponseBody.builder().bookings(appUserList).build())
			.build();
	}

	@Override
	public BookingResponseHolder createBooking(BookingRequestBody body) {
		Booking booking = new Booking();
		booking.setUserName(body.getUsername());
		booking.setPassword(body.getPassword());
		booking.setStatus(body.getStatus());
		booking = bookingRepository.save(booking);
		log.info("Booking Saved with id {}", booking.getId());
		return BookingResponseHolder.builder()
				.header(ResponseHeader.builder().responseId(null).build())
				.body(BookingResponseBody.builder().build())
			.build();
	}

	@Override
	public BookingResponseHolder updateBooking(BookingRequestBody body) {
		// TODO Auto-generated method stub
		return null;
	}

}
