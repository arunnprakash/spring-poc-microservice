package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.BookingRequest;
import com.example.demo.dto.BookingResponse;
import com.example.demo.dto.BookingResponse.BookingResponseHolder;
import com.example.demo.service.BookingService;


/**
 * @author __ArunPrakash__
 *
 */
@RestController
@RequestMapping(path = "/v1/bookings")
public class BookingController extends BaseController {

	@Autowired
	private BookingService bookingService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public BookingResponse getAllBookings() {
		BookingResponseHolder bookingResponseHolder = bookingService.getAllBookings();
		return BookingResponse.builder().response(bookingResponseHolder).build();
	}

	@PostMapping("/create")
	@ResponseStatus(HttpStatus.OK)
	public BookingResponse createBooking(@RequestBody BookingRequest userRequest) {
		BookingResponseHolder bookingResponseHolder = bookingService.createBooking(userRequest.getRequest().getBody());
		return BookingResponse.builder().response(bookingResponseHolder).build();
	}
	
	@PostMapping("/update")
	@ResponseStatus(HttpStatus.OK)
	public BookingResponse updateBooking(@RequestBody BookingRequest userRequest) {
		BookingResponseHolder bookingResponseHolder = bookingService.updateBooking(userRequest.getRequest().getBody());
		return BookingResponse.builder().response(bookingResponseHolder).build();
	}
}
