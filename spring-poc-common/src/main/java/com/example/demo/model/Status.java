package com.example.demo.model;

/**
 * @author __ArunPrakash__
 *
 */
public enum Status {

	ACTIVE("A"), INACTIVE("I");
	
	private String statusCode;
	
	Status(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusCode() {
		return statusCode;
	}
	
	public boolean isActive() {
		return this.equals(ACTIVE);
	}
	
	public boolean isInActive() {
		return this.equals(INACTIVE);
	}
	
	public static Status getStatus(String statusCode) {
		if (statusCode != null) {
			Status[] statusList = Status.values();
			for (int i = 0; i < statusList.length; i++) {
				Status status = statusList[i];
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
