package com.example.demo.constant;

/**
 * @author __ArunPrakash__
 *
 */
public enum PnrStatus {

	CONFIRM("C"), WAITING("W");
	
	private String statusCode;
	
	PnrStatus(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusCode() {
		return statusCode;
	}
	
	public boolean isConfirm() {
		return this.equals(CONFIRM);
	}
	
	public boolean isWaiting() {
		return this.equals(WAITING);
	}
	
	public static PnrStatus getStatus(String statusCode) {
		if (statusCode != null) {
			PnrStatus[] statusList = PnrStatus.values();
			for (int i = 0; i < statusList.length; i++) {
				PnrStatus status = statusList[i];
				if (status.getStatusCode().equalsIgnoreCase(statusCode)) {
					return status;
				}
			}
			return null;
		} else {
			return null;
		}
	}
}
