/**
 * 
 */
package com.example.demo.service;

import com.example.demo.dto.BookingRequest.BookingRequestBody;
import com.example.demo.dto.BookingResponse.BookingResponseHolder;

/**
 * @author __ArunPrakash__
 *
 */
public interface BookingService {

	BookingResponseHolder getAllBookings();

	BookingResponseHolder createBooking(BookingRequestBody body);

	BookingResponseHolder updateBooking(BookingRequestBody body);

}
