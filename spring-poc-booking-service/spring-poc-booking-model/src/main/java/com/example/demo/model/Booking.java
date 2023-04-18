package com.example.demo.model;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * @author __ArunPrakash__
 *
 */
@Data
public class Booking extends BaseModel {
	
	private Long flightId;
	private Long userId;
	private Long startLocation;
	private Long destinationLocation;
	private LocalDateTime bookingDate;
	private Pnr pnr;

}
