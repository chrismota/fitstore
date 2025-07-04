CREATE TABLE "order_item" (
	id SERIAL NOT NULL,
	created_at timestamp(6) NOT NULL,
	quantity int4 NOT NULL,
	total numeric(38, 2) NOT NULL,
	unity_price numeric(38, 2) NOT NULL,
	updated_at timestamp(6) NOT NULL,
	order_id uuid NOT NULL,
	product_id BIGINT NOT NULL,
	CONSTRAINT order_item_pkey PRIMARY KEY (id),
	CONSTRAINT order_item_products_product_id_fkey FOREIGN KEY (product_id) REFERENCES public.products(id),
	CONSTRAINT order_item_orders_order_id_fkey FOREIGN KEY (order_id) REFERENCES public.orders(id)
);

CREATE SEQUENCE order_item_seq START WITH 1 INCREMENT BY 1;
