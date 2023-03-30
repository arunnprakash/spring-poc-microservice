/**
 * 
 */
package com.example.demo.dto;

import java.time.LocalDateTime;

import com.example.demo.constant.Status;

import lombok.Getter;
import lombok.Setter;

/**
 * @author __ArunPrakash__
 *
 */
@Getter
@Setter
public class BaseDTO {
	private String id;
	private Status status;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;
}
