CREATE TABLE product (
	id BIGSERIAL,
	name VARCHAR(60) not null,
	price DECIMAL(10,2) not null,
	stock INTEGER not null,
	PRIMARY KEY(id)
);