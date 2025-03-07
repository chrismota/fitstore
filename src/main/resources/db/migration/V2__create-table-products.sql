CREATE TABLE public.products (
	id SERIAL NOT NULL,
	brand varchar(255) NULL,
	category varchar(255) NULL,
	created_at timestamp(6) NULL,
	description text NULL,
	"name" varchar(255) NULL,
	price numeric(38, 2) NULL,
	sku varchar(255) NULL,
	image_path varchar(255) NULL,
	sub_category varchar(255) NULL,
	updated_at timestamp(6) NULL,
	CONSTRAINT products_category_check CHECK (((category)::text = ANY ((ARRAY['AEROBICOS'::character varying, 'ACESSORIOS'::character varying, 'PESOS'::character varying, 'SUPLEMENTOS'::character varying, 'ROUPAS'::character varying])::text[]))),
	CONSTRAINT products_pkey PRIMARY KEY (id),
	CONSTRAINT products_sub_category_check CHECK (((sub_category)::text = ANY ((ARRAY['BICICLETA'::character varying, 'ESTEIRA'::character varying, 'ELIPTICO'::character varying, 'BARRA'::character varying, 'SUPORTE'::character varying, 'PUXADOR'::character varying, 'BOLA'::character varying, 'COLCHONETE'::character varying, 'CANELEIRA'::character varying,'CORDA'::character varying, 'HALTER'::character varying, 'ANILHA'::character varying, 'KETTLEBELL'::character varying, 'PRESILHA'::character varying, 'WHEY'::character varying, 'CREATINA'::character varying, 'PRE_TREINO'::character varying, 'CAMISA'::character varying,  'SHORT'::character varying,  'LEGGING'::character varying])::text[]))),
	CONSTRAINT products_unique_sku UNIQUE (sku)
);

CREATE SEQUENCE products_seq START WITH 1 INCREMENT BY 1;
