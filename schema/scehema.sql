---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE USER(
    UUID CHAR(36) NOT NULL DEFAULT(UUID()),
    USERNAME VARCHAR(20) NOT NULL ,
    ROLE VARCHAR(20) NOT NULL DEFAULT USER,
    EMAIL VARCHAR(30) NOT NULL,
    PASSWORD VARCHAR(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO USER (UUID, USERNAME , ROLE ,  EMAIL , PASSWORD) VALUES('1', 'KARTIKEY_1607', 'ADMIN', 'CHOUDHARYKARTIK733@GMAIL.COM','KARTIKEY@123')

----------------------------------------------------------------------------------------------------------------------------------------------------------------------------