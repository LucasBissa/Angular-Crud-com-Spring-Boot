CREATE TABLE product (
	id BIGSERIAL,
	name VARCHAR(60) not null,
	price DECIMAL(10,2) not null,
	PRIMARY KEY(id)
);