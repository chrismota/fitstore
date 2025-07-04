CREATE TABLE "coupons" (
	id SERIAL NOT NULL,
	code varchar(255) NOT NULL,
	created_at timestamp(6) NOT NULL,
	expiration_time timestamp(6) NOT NULL,
	min_value numeric(38, 2) NOT NULL,
	"name" varchar(255) NOT NULL,
	percentage float8 NOT NULL,
	status varchar(255) NOT NULL,
	start_time timestamp(6) NOT NULL,
	updated_at timestamp(6) NOT NULL,
	CONSTRAINT coupons_pkey PRIMARY KEY (id),
	CONSTRAINT coupons_code_unique UNIQUE (code)
);

CREATE SEQUENCE coupons_seq START WITH 1 INCREMENT BY 1;
