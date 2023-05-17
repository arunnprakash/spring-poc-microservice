/**
 * 
 */
package com.example.demo.dto;

import java.time.LocalDateTime;

import com.example.demo.constant.Status;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * @author __ArunPrakash__
 *
 */
@Getter
@Setter
public class BaseDTO {
	@Schema(description = "id")
	private String id;

	@Schema(description = "Status")
	private Status status;

	@Schema(description = "created date of this record")
	private LocalDateTime createdDate;

	@Schema(description = "modified date of this record")
	private LocalDateTime modifiedDate;
}
