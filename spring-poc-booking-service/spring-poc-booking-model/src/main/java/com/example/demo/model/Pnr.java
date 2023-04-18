package com.example.demo.model;

import com.example.demo.constant.PnrStatus;

import lombok.Data;

/**
 * @author __ArunPrakash__
 *
 */
@Data
public class Pnr extends BaseModel {
	private String pnrId;
	private PnrStatus pnrStatus;
}
