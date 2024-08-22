CREATE TABLE public.order_item (
	id uuid NOT NULL,
	created_at timestamp(6) NULL,
	quantity int4 NULL,
	total numeric(38, 2) NULL,
	unity_price numeric(38, 2) NULL,
	updated_at timestamp(6) NULL,
	order_id uuid NULL,
	product_id uuid NULL,
	CONSTRAINT order_item_pkey PRIMARY KEY (id),
	CONSTRAINT order_item_products_product_id_fkey FOREIGN KEY (product_id) REFERENCES public.products(id),
	CONSTRAINT order_item_orders_order_id_fkey FOREIGN KEY (order_id) REFERENCES public.orders(id)
);
