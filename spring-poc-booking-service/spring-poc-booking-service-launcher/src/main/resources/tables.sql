DROP TABLE IF EXISTS BOOKING;

CREATE TABLE BOOKING (
ID BIGINT AUTO_INCREMENT PRIMARY KEY,

FLIGHT_ID BIGINT NOT NULL,
USER_ID BIGINT NOT NULL,
START_LOCATION BIGINT NOT NULL,
DESTINATION_LOCATION BIGINT NOT NULL,
BOOKING_DATE TIMESTAMP WITH TIME ZONE,

STATUS VARCHAR(1) NOT NULL,
CREATED_DATE TIMESTAMP WITH TIME ZONE,
MODIFIED_DATE TIMESTAMP WITH TIME ZONE
);

DROP TABLE IF EXISTS PNR;

CREATE TABLE PNR (
ID BIGINT AUTO_INCREMENT PRIMARY KEY,

BOOKING BIGINT NOT NULL,
PNR_ID VARCHAR(200) NOT NULL,
PNR_STATUS VARCHAR(1) NOT NULL,

STATUS VARCHAR(1) NOT NULL,
CREATED_DATE TIMESTAMP WITH TIME ZONE,
MODIFIED_DATE TIMESTAMP WITH TIME ZONE
);