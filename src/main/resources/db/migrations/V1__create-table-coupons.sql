CREATE TABLE public.coupons (
	id uuid NOT NULL,
	code varchar(255) NULL,
	created_at timestamp(6) NULL,
	expiration_time timestamp(6) NULL,
	min_value numeric(38, 2) NULL,
	"name" varchar(255) NULL,
	percentage float8 NULL,
	start_time timestamp(6) NULL,
	updated_at timestamp(6) NULL,
	CONSTRAINT coupons_pkey PRIMARY KEY (id),
	CONSTRAINT coupons_code_unique UNIQUE (code)
);