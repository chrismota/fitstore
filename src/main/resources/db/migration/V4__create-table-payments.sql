CREATE TABLE public.payments (
	id uuid NOT NULL,
	created_at timestamp(6) NOT NULL,
	"method" varchar(255) NOT NULL,
	status varchar(255) NOT NULL,
	updated_at timestamp(6) NOT NULL,
	order_id uuid NOT NULL,
	CONSTRAINT payments_method_check CHECK
	(((method)::text = ANY
	((ARRAY['DEBIT_CARD'::character varying, 'CREDIT_CARD'::character varying,
	'PIX'::character varying, 'PAYMENT_SLIP'::character varying])::text[]))),
	CONSTRAINT payments_pkey PRIMARY KEY (id),
	CONSTRAINT payments_status_check CHECK (((status)::text = ANY
	((ARRAY['CREATED'::character varying, 'SUCCESS'::character varying,
	'FAILED'::character varying, 'CANCELLED'::character varying])::text[]))),
	CONSTRAINT payments_orders_order_id_fkey FOREIGN KEY (order_id) REFERENCES public.orders(id)
);
