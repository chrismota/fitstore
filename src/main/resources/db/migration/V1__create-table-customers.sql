CREATE TABLE public.customers (
	id uuid NOT NULL,
	address varchar(255) NULL,
	cpf varchar(255) NULL,
	created_at timestamp(6) NULL,
	email varchar(255) NULL,
	"name" varchar(255) NULL,
	"password" varchar(255) NULL,
	phone_number varchar(255) NULL,
	"role" varchar(255) NULL,
	image_path varchar(255) NULL,
	updated_at timestamp(6) NULL,
	CONSTRAINT customers_pkey PRIMARY KEY (id),
	CONSTRAINT customers_role_check CHECK (((role)::text = ANY ((ARRAY['CUSTOMER'::character varying, 'ADMIN'::character varying])::text[]))),
	CONSTRAINT customers_cpf_unique UNIQUE (cpf),
	CONSTRAINT customers_email_unique UNIQUE (email)
);