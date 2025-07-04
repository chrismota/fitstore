CREATE TABLE public.orders (
	id uuid NOT NULL,
	created_at timestamp(6) NOT NULL,
	discount_value numeric(38, 2) NULL,
	expires_at timestamp(6) NOT NULL,
	total_value numeric(38, 2) NOT NULL,
	status varchar(255) NOT NULL,
	updated_at timestamp(6) NOT NULL,
	total_with_discount numeric(38, 2) NOT NULL,
	customer_id uuid NOT NULL,
	CONSTRAINT orders_pkey PRIMARY KEY (id),
	CONSTRAINT orders_status_check CHECK
	(((status)::text = ANY
	((ARRAY['PENDING'::character varying, 'PAID'::character varying, 'FAILED'::character varying])::text[]))),
	CONSTRAINT orders_customers_customer_id_fkey FOREIGN KEY (customer_id) REFERENCES public.customers(id)
);
