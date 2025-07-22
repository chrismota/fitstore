--
-- PostgreSQL database dump
--

-- Dumped from database version 17.5 (Debian 17.5-1.pgdg120+1)
-- Dumped by pg_dump version 17.0

-- Started on 2025-07-21 18:43:04

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 227 (class 1259 OID 16467)
-- Name: coupons; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.coupons (
    id integer NOT NULL,
    code character varying(255) NOT NULL,
    created_at timestamp(6) without time zone NOT NULL,
    expiration_time timestamp(6) without time zone NOT NULL,
    min_value numeric(38,2) NOT NULL,
    name character varying(255) NOT NULL,
    percentage double precision NOT NULL,
    status character varying(255) NOT NULL,
    start_time timestamp(6) without time zone NOT NULL,
    updated_at timestamp(6) without time zone NOT NULL
);


--
-- TOC entry 226 (class 1259 OID 16466)
-- Name: coupons_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.coupons_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3448 (class 0 OID 0)
-- Dependencies: 226
-- Name: coupons_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.coupons_id_seq OWNED BY public.coupons.id;


--
-- TOC entry 228 (class 1259 OID 16477)
-- Name: coupons_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.coupons_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 218 (class 1259 OID 16397)
-- Name: customers; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.customers (
    id uuid NOT NULL,
    street character varying(255) NOT NULL,
    house_number character varying(255) NOT NULL,
    complement character varying(255),
    neighborhood character varying(255) NOT NULL,
    city character varying(255) NOT NULL,
    state character varying(255) NOT NULL,
    cep character varying(255) NOT NULL,
    cpf character varying(255) NOT NULL,
    created_at timestamp(6) without time zone NOT NULL,
    email character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    phone_number character varying(255) NOT NULL,
    role character varying(255) NOT NULL,
    image_path character varying(255),
    updated_at timestamp(6) without time zone NOT NULL,
    CONSTRAINT customers_role_check CHECK (((role)::text = ANY (ARRAY[('CUSTOMER'::character varying)::text, ('ADMIN'::character varying)::text])))
);


--
-- TOC entry 217 (class 1259 OID 16388)
-- Name: flyway_schema_history; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.flyway_schema_history (
    installed_rank integer NOT NULL,
    version character varying(50),
    description character varying(200) NOT NULL,
    type character varying(20) NOT NULL,
    script character varying(1000) NOT NULL,
    checksum integer,
    installed_by character varying(100) NOT NULL,
    installed_on timestamp without time zone DEFAULT now() NOT NULL,
    execution_time integer NOT NULL,
    success boolean NOT NULL
);


--
-- TOC entry 224 (class 1259 OID 16449)
-- Name: order_item; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.order_item (
    id integer NOT NULL,
    created_at timestamp(6) without time zone NOT NULL,
    quantity integer NOT NULL,
    total numeric(38,2) NOT NULL,
    unity_price numeric(38,2) NOT NULL,
    updated_at timestamp(6) without time zone NOT NULL,
    order_id uuid NOT NULL,
    product_id bigint NOT NULL
);


--
-- TOC entry 223 (class 1259 OID 16448)
-- Name: order_item_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.order_item_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3449 (class 0 OID 0)
-- Dependencies: 223
-- Name: order_item_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.order_item_id_seq OWNED BY public.order_item.id;


--
-- TOC entry 225 (class 1259 OID 16465)
-- Name: order_item_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.order_item_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 222 (class 1259 OID 16423)
-- Name: orders; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.orders (
    id uuid NOT NULL,
    created_at timestamp(6) without time zone NOT NULL,
    discount_value numeric(38,2),
    expires_at timestamp(6) without time zone NOT NULL,
    total_value numeric(38,2) NOT NULL,
    status character varying(255) NOT NULL,
    updated_at timestamp(6) without time zone NOT NULL,
    total_with_discount numeric(38,2) NOT NULL,
    customer_id uuid NOT NULL,
    CONSTRAINT orders_status_check CHECK (((status)::text = ANY (ARRAY[('PENDING'::character varying)::text, ('PAID'::character varying)::text, ('FAILED'::character varying)::text])))
);


--
-- TOC entry 230 (class 1259 OID 16479)
-- Name: payment_coupons; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.payment_coupons (
    payment_id uuid NOT NULL,
    coupon_id integer NOT NULL
);


--
-- TOC entry 229 (class 1259 OID 16478)
-- Name: payment_coupons_coupon_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.payment_coupons_coupon_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3450 (class 0 OID 0)
-- Dependencies: 229
-- Name: payment_coupons_coupon_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.payment_coupons_coupon_id_seq OWNED BY public.payment_coupons.coupon_id;


--
-- TOC entry 231 (class 1259 OID 24586)
-- Name: payments; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.payments (
    id uuid NOT NULL,
    created_at timestamp(6) without time zone NOT NULL,
    method character varying(255) NOT NULL,
    status character varying(255) NOT NULL,
    updated_at timestamp(6) without time zone NOT NULL,
    order_id uuid NOT NULL,
    CONSTRAINT payments_method_check CHECK (((method)::text = ANY (ARRAY[('DEBIT_CARD'::character varying)::text, ('CREDIT_CARD'::character varying)::text, ('PIX'::character varying)::text, ('PAYMENT_SLIP'::character varying)::text]))),
    CONSTRAINT payments_status_check CHECK (((status)::text = ANY (ARRAY[('CREATED'::character varying)::text, ('SUCCESS'::character varying)::text, ('FAILED'::character varying)::text, ('CANCELLED'::character varying)::text])))
);


--
-- TOC entry 220 (class 1259 OID 16410)
-- Name: products; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.products (
    id integer NOT NULL,
    brand character varying(255) NOT NULL,
    category character varying(255) NOT NULL,
    created_at timestamp(6) without time zone NOT NULL,
    description text NOT NULL,
    name character varying(255) NOT NULL,
    price numeric(38,2) NOT NULL,
    sku character varying(255) NOT NULL,
    image_path character varying(255),
    sub_category character varying(255) NOT NULL,
    updated_at timestamp(6) without time zone NOT NULL,
    CONSTRAINT products_category_check CHECK (((category)::text = ANY (ARRAY[('AEROBICOS'::character varying)::text, ('ACESSORIOS'::character varying)::text, ('PESOS'::character varying)::text, ('SUPLEMENTOS'::character varying)::text, ('ROUPAS'::character varying)::text]))),
    CONSTRAINT products_sub_category_check CHECK (((sub_category)::text = ANY (ARRAY[('BICICLETA'::character varying)::text, ('ESTEIRA'::character varying)::text, ('ELIPTICO'::character varying)::text, ('BARRA'::character varying)::text, ('SUPORTE'::character varying)::text, ('PUXADOR'::character varying)::text, ('BOLA'::character varying)::text, ('COLCHONETE'::character varying)::text, ('CANELEIRA'::character varying)::text, ('CORDA'::character varying)::text, ('HALTER'::character varying)::text, ('ANILHA'::character varying)::text, ('KETTLEBELL'::character varying)::text, ('PRESILHA'::character varying)::text, ('WHEY'::character varying)::text, ('CREATINA'::character varying)::text, ('PRE_TREINO'::character varying)::text, ('CAMISA'::character varying)::text, ('SHORT'::character varying)::text, ('LEGGING'::character varying)::text])))
);


--
-- TOC entry 219 (class 1259 OID 16409)
-- Name: products_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.products_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3451 (class 0 OID 0)
-- Dependencies: 219
-- Name: products_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.products_id_seq OWNED BY public.products.id;


--
-- TOC entry 221 (class 1259 OID 16422)
-- Name: products_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.products_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3247 (class 2604 OID 16470)
-- Name: coupons id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.coupons ALTER COLUMN id SET DEFAULT nextval('public.coupons_id_seq'::regclass);


--
-- TOC entry 3246 (class 2604 OID 16452)
-- Name: order_item id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.order_item ALTER COLUMN id SET DEFAULT nextval('public.order_item_id_seq'::regclass);


--
-- TOC entry 3248 (class 2604 OID 16482)
-- Name: payment_coupons coupon_id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.payment_coupons ALTER COLUMN coupon_id SET DEFAULT nextval('public.payment_coupons_coupon_id_seq'::regclass);


--
-- TOC entry 3245 (class 2604 OID 16413)
-- Name: products id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.products ALTER COLUMN id SET DEFAULT nextval('public.products_id_seq'::regclass);


--
-- TOC entry 3438 (class 0 OID 16467)
-- Dependencies: 227
-- Data for Name: coupons; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.coupons VALUES (1, 'NAT25', '2025-07-21 16:53:10.253446', '2025-12-25 23:59:59.544', 200.00, 'Natal 2025', 10, 'VALID', '2025-01-01 16:33:50.544', '2025-07-21 16:53:10.253446');
INSERT INTO public.coupons VALUES (2, 'IND25', '2025-07-21 16:53:54.96478', '2025-09-07 23:59:59.544', 80.00, 'Independência 2025', 5, 'VALID', '2025-01-01 16:33:50.544', '2025-07-21 16:53:54.96478');


--
-- TOC entry 3429 (class 0 OID 16397)
-- Dependencies: 218
-- Data for Name: customers; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 3428 (class 0 OID 16388)
-- Dependencies: 217
-- Data for Name: flyway_schema_history; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.flyway_schema_history VALUES (1, '1', 'create-table-customers', 'SQL', 'V1__create-table-customers.sql', -674899390, 'user', '2025-07-09 09:09:31.890109', 41, true);
INSERT INTO public.flyway_schema_history VALUES (2, '2', 'create-table-products', 'SQL', 'V2__create-table-products.sql', 2029709608, 'user', '2025-07-09 09:09:32.110618', 153, true);
INSERT INTO public.flyway_schema_history VALUES (3, '3', 'create-table-orders', 'SQL', 'V3__create-table-orders.sql', -1307931484, 'user', '2025-07-09 09:09:32.364835', 78, true);
INSERT INTO public.flyway_schema_history VALUES (4, '4', 'create-table-payments', 'SQL', 'V4__create-table-payments.sql', -1045366457, 'user', '2025-07-09 09:09:32.550146', 29, true);
INSERT INTO public.flyway_schema_history VALUES (5, '5', 'create-table-order-item', 'SQL', 'V5__create-table-order-item.sql', 2034229483, 'user', '2025-07-09 09:09:32.626991', 21, true);
INSERT INTO public.flyway_schema_history VALUES (6, '6', 'create-table-coupons', 'SQL', 'V6__create-table-coupons.sql', 1372309734, 'user', '2025-07-09 09:09:32.691651', 19, true);
INSERT INTO public.flyway_schema_history VALUES (7, '7', 'create-table-payment-coupons', 'SQL', 'V7__create-table-payment-coupons.sql', 2086741948, 'user', '2025-07-09 09:09:32.762999', 24, true);


--
-- TOC entry 3435 (class 0 OID 16449)
-- Dependencies: 224
-- Data for Name: order_item; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 3433 (class 0 OID 16423)
-- Dependencies: 222
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 3441 (class 0 OID 16479)
-- Dependencies: 230
-- Data for Name: payment_coupons; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 3442 (class 0 OID 24586)
-- Dependencies: 231
-- Data for Name: payments; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 3431 (class 0 OID 16410)
-- Dependencies: 220
-- Data for Name: products; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.products VALUES (1003, 'Rouge', 'ACESSORIOS', '2025-07-03 14:24:54.754618', 'Suporte metálico com capacidade para até 5 pares de halteres.', 'Suporte de Parede para Halteres Rouge', 239.00, 'ACE-002', '1752063590563_Suporte de Parede para Halteres Rouge.png', 'SUPORTE', '2025-07-09 09:19:50.888497');
INSERT INTO public.products VALUES (1004, 'MovePro', 'ROUPAS', '2025-07-03 19:16:06.376299', 'Short de Compressão da marca MovePro. Ideal para treinos intensos.', 'Short de Compressão MovePro', 76.99, 'ROU-SHO-002', '1752063641857_Short de Compressão MovePro.png', 'SHORT', '2025-07-09 09:20:42.128122');
INSERT INTO public.products VALUES (1006, 'Athletix', 'ROUPAS', '2025-07-03 19:18:22.622445', 'Short de Compressão da marca Athletix. Ideal para treinos intensos.', 'Short de Compressão Athletix', 75.37, 'ROU-SHO-005', '1752063783357_Short de Compressão Athletix.jpg', 'SHORT', '2025-07-09 09:23:03.662131');
INSERT INTO public.products VALUES (1007, 'TrainWell', 'ACESSORIOS', '2025-07-03 19:17:13.700667', 'Puxador de Academia da marca TrainWell. Ideal para treinos intensos.', 'Puxador de Academia TrainWell', 85.14, 'ACE-PUX-001', '1752063811672_Puxador de Academia TrainWell.png', 'PUXADOR', '2025-07-09 09:23:32.257437');
INSERT INTO public.products VALUES (1008, 'XtremeBlend', 'SUPLEMENTOS', '2025-07-03 19:14:53.831358', 'Pré-Treino da marca XtremeBlend com 900g. Ideal para treinos intensos.', 'Pré-Treino 900g XtremeBlend', 144.45, 'SUP-PRE-001', '1752064188057_Pré-Treino 900g XtremeBlend.png', 'PRE_TREINO', '2025-07-09 09:29:48.315649');
INSERT INTO public.products VALUES (1009, 'PowerFuel', 'SUPLEMENTOS', '2025-07-03 19:18:44.443057', 'Pré-Treino da marca PowerFuel com 500g. Ideal para treinos intensos.', 'Pré-Treino 500g PowerFuel', 98.46, 'SUP-PRE-002', '1752064380603_Pré-Treino 500g PowerFuel.png', 'PRE_TREINO', '2025-07-09 09:33:00.888154');
INSERT INTO public.products VALUES (1010, 'SteelPump', 'PESOS', '2025-07-03 19:18:12.519113', 'Presilha para Barra da marca SteelPump. Ideal para treinos intensos.', 'Presilha para Barra SteelPump', 37.48, 'PES-PRE-004', '1752064530405_Presilha para Barra SteelPump.png', 'PRESILHA', '2025-07-09 09:35:30.741105');
INSERT INTO public.products VALUES (1011, 'MassForge', 'PESOS', '2025-07-03 19:18:07.373933', 'Presilha para Barra da marca MassForge. Ideal para treinos intensos.', 'Presilha para Barra MassForge', 30.50, 'PES-PRE-003', '1752064550884_Presilha para Barra MassForge.png', 'PRESILHA', '2025-07-09 09:35:51.175448');
INSERT INTO public.products VALUES (1012, 'IronBeast', 'PESOS', '2025-07-03 19:16:28.960867', 'Par de Halteres da marca IronBeast com 8g. Ideal para treinos intensos.', 'Par de Halteres 8kg IronBeast', 240.59, 'PES-HAL-003', '1752064654332_Par de Halteres 8kg IronBeast.png', 'HALTER', '2025-07-09 09:37:34.608305');
INSERT INTO public.products VALUES (1013, 'IronBeast', 'PESOS', '2025-07-03 19:18:49.702605', 'Par de Halteres da marca IronBeast com 6g. Ideal para treinos intensos.', 'Par de Halteres 6kg IronBeast', 174.60, 'PES-HAL-004', '1752064742532_Par de Halteres 6kg IronBeast.png', 'HALTER', '2025-07-09 09:39:02.810149');
INSERT INTO public.products VALUES (1014, 'IronBeast', 'PESOS', '2025-07-03 19:15:17.976862', 'Par de Halteres da marca IronBeast com 4g. Ideal para treinos intensos.', 'Par de Halteres 4kg IronBeast', 123.70, 'PES-HAL-001', '1752064955521_Par de Halteres 4kg IronBeast.png', 'HALTER', '2025-07-09 09:42:35.869589');
INSERT INTO public.products VALUES (1015, 'MassForge', 'PESOS', '2025-07-03 19:15:33.87056', 'Par de Halteres da marca MassForge com 20g. Ideal para treinos intensos.', 'Par de Halteres 20kg MassForge', 177.78, 'PES-HAL-002', '1752065109009_Par de Halteres 20kg MassForge.png', 'HALTER', '2025-07-09 09:45:09.257761');
INSERT INTO public.products VALUES (1016, 'MovePro', 'ROUPAS', '2025-07-03 19:15:28.816711', 'Legging Fitness da marca MovePro. Ideal para treinos intensos.', 'Legging Fitness MovePro', 116.26, 'ROU-LEG-004', '1752065208491_Legging Fitness MovePro.png', 'LEGGING', '2025-07-09 09:46:48.801724');
INSERT INTO public.products VALUES (1017, 'FitWear', 'ROUPAS', '2025-07-03 19:18:18.17491', 'Legging Fitness da marca FitWear. Ideal para treinos intensos.', 'Legging Fitness FitWear', 145.56, 'ROU-LEG-006', '1752065235742_Legging Fitness FitWear.jpg', 'LEGGING', '2025-07-09 09:47:16.127138');
INSERT INTO public.products VALUES (1018, 'Athletix', 'ROUPAS', '2025-07-03 19:14:36.224563', 'Legging Fitness da marca Athletix. Ideal para treinos intensos.', 'Legging Fitness Athletix', 84.23, 'ROU-LEG-003', '1752065322692_Legging Fitness Athletix.jpg', 'LEGGING', '2025-07-09 09:48:43.143085');
INSERT INTO public.products VALUES (1019, 'Dream Fitness', 'AEROBICOS', '2025-07-03 14:24:27.972646', 'Esteira compacta com 12 programas e monitoramento cardíaco.', 'Esteira Elétrica Dream Fitness', 3299.00, 'AER-002', '1752065427192_Esteira Elétrica Dream Fitness.png', 'ESTEIRA', '2025-07-09 09:50:28.360146');
INSERT INTO public.products VALUES (1020, 'Cardionix', 'AEROBICOS', '2025-07-03 19:16:18.926167', 'Esteira Elétrica da marca Cardionix. Ideal para treinos intensos.', 'Esteira Elétrica Cardionix', 4763.27, 'AER-EST-002', '1752065445443_Esteira Elétrica Cardionix.jpg', 'ESTEIRA', '2025-07-09 09:50:45.797968');
INSERT INTO public.products VALUES (1021, 'Aeroflex', 'AEROBICOS', '2025-07-03 19:14:41.702794', 'Esteira Elétrica da marca Aeroflex. Ideal para treinos intensos.', 'Esteira Elétrica Aeroflex', 4737.61, 'AER-EST-001', '1752065478962_Esteira Elétrica Aeroflex.jpg', 'ESTEIRA', '2025-07-09 09:51:19.400583');
INSERT INTO public.products VALUES (1022, 'CycloMax', 'AEROBICOS', '2025-07-03 19:17:56.87189', 'Elíptico Magnético da marca CycloMax. Ideal para treinos intensos.', 'Elíptico Magnético CycloMax', 2467.05, 'AER-ELI-003', '1752065518368_Elíptico Magnético CycloMax.png', 'ELIPTICO', '2025-07-09 09:51:58.72738');
INSERT INTO public.products VALUES (1023, 'Cardionix', 'AEROBICOS', '2025-07-03 19:14:14.103487', 'Elíptico Magnético da marca Cardionix. Ideal para treinos intensos.', 'Elíptico Magnético Cardionix', 2602.81, 'AER-ELI-001', '1752065561453_Elíptico Magnético Cardionix.png', 'ELIPTICO', '2025-07-09 09:52:41.774663');
INSERT INTO public.products VALUES (1024, 'Aeroflex', 'AEROBICOS', '2025-07-03 19:15:39.080525', 'Elíptico Magnético da marca Aeroflex. Ideal para treinos intensos.', 'Elíptico Magnético Aeroflex', 2965.79, 'AER-ELI-002', '1752065589356_Elíptico Magnético Aeroflex.png', 'ELIPTICO', '2025-07-09 09:53:09.635959');
INSERT INTO public.products VALUES (1025, 'PowerFuel', 'SUPLEMENTOS', '2025-07-03 19:14:07.990431', 'Creatina Monohidratada da marca PowerFuel com 1000g. Ideal para treinos intensos.', 'Creatina Monohidratada 1000g PowerFuel', 86.49, 'SUP-CRE-001', '1752065624203_creatina 1000g powerfuel.png', 'CREATINA', '2025-07-09 09:53:44.476666');
INSERT INTO public.products VALUES (1026, 'BodyTools', 'ACESSORIOS', '2025-07-03 19:15:23.748919', 'Corda de Pular da marca BodyTools. Ideal para treinos intensos.', 'Corda de Pular BodyTools', 55.94, 'ACE-COR-001', '1752065646051_Corda de Pular BodyTools.jpg', 'CORDA', '2025-07-09 09:54:06.554451');
INSERT INTO public.products VALUES (1027, 'GripForce', 'ACESSORIOS', '2025-07-03 19:15:42.928197', 'Caneleira com Peso da marca GripForce com 2g. Ideal para treinos intensos.', 'Caneleira com Peso 2kg GripForce', 80.89, 'ACE-CAN-002', '1752065692360_Caneleira com Peso 2kg GripForce.png', 'CANELEIRA', '2025-07-09 09:54:52.589419');
INSERT INTO public.products VALUES (1043, 'PulseGo', 'AEROBICOS', '2025-07-09 12:27:05.462163', 'O produto Bicicleta PulseGo da marca PulseGo foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Bicicleta Ergométrica PulseGo', 2345.06, 'AER-BIC-004', '1752241848478_Bicicleta Ergométrica PulseGo.png', 'BICICLETA', '2025-07-11 10:50:48.837967');
INSERT INTO public.products VALUES (1044, 'Aeroflex', 'AEROBICOS', '2025-07-09 12:27:11.109926', 'O produto Bicicleta Aeroflex da marca Aeroflex foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Bicicleta Ergométrica Aeroflex', 2696.54, 'AER-BIC-005', '1752241868283_Bicicleta Ergométrica Aeroflex.png', 'BICICLETA', '2025-07-11 10:51:09.115712');
INSERT INTO public.products VALUES (1156, 'StrideTech', 'AEROBICOS', '2025-07-09 14:49:57.904373', 'O produto Bicicleta Ergométrica SpinPro da marca StrideTech foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Bicicleta Ergométrica SpinPro', 2850.50, 'AER-BIC-024', '1752241895108_Bicicleta Ergométrica SpinPro.png', 'BICICLETA', '2025-07-11 10:51:35.52215');
INSERT INTO public.products VALUES (1002, 'NutriLab', 'SUPLEMENTOS', '2025-07-03 19:18:29.096444', 'Whey Protein da marca NutriLab com 1000g. Ideal para treinos intensos.', 'Whey Protein 1000g NutriLab', 157.51, 'SUP-WHE-003', '1752756170733_Whey Protein 1000g NutriLab.png', 'WHEY', '2025-07-17 09:42:51.764809');
INSERT INTO public.products VALUES (1161, 'Cardionix', 'AEROBICOS', '2025-07-09 14:51:00.473343', 'O produto Bicicleta Magnética FitCycle da marca Cardionix foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Bicicleta Magnética FitCycle', 2400.90, 'AER-BIC-028', '1752241993842_Bicicleta Magnética FitCycle.png', 'BICICLETA', '2025-07-11 10:53:14.151873');
INSERT INTO public.products VALUES (1046, 'Cardionix', 'AEROBICOS', '2025-07-09 13:59:26.609174', 'O produto Esteira Cardionix da marca Cardionix foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Esteira Elétrica Dobrável Cardionix', 3028.15, 'AER-EST-004', '1752242069076_Esteira Elétrica Dobrável Cardionix.png', 'ESTEIRA', '2025-07-11 10:54:29.474269');
INSERT INTO public.products VALUES (1047, 'CycloMax', 'AEROBICOS', '2025-07-09 13:59:32.745788', 'O produto Esteira CycloMax da marca CycloMax foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Esteira Elétrica Dobrável CycloMax', 3201.94, 'AER-EST-007', '1752242084989_Esteira Elétrica Dobrável CycloMax.png', 'ESTEIRA', '2025-07-11 10:54:45.480831');
INSERT INTO public.products VALUES (1048, 'StrideTech', 'AEROBICOS', '2025-07-09 13:59:39.21129', 'O produto Esteira StrideTech da marca StrideTech foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Esteira Elétrica Dobrável StrideTech', 2781.80, 'AER-EST-009', '1752242102684_Esteira Elétrica Dobrável StrideTech.png', 'ESTEIRA', '2025-07-11 10:55:04.576914');
INSERT INTO public.products VALUES (1039, 'SteelPump', 'PESOS', '2025-07-03 19:17:40.316484', 'Anilha de Ferro da marca SteelPump com 6g. Ideal para treinos intensos.', 'Anilha de Ferro 6kg SteelPump', 131.39, 'PES-ANI-003', '1752066664145_Anilha de Ferro 6kg SteelPump.png', 'ANILHA', '2025-07-09 10:11:04.761083');
INSERT INTO public.products VALUES (1040, 'SteelPump', 'PESOS', '2025-07-03 19:15:14.057025', 'Anilha de Ferro da marca SteelPump com 12g. Ideal para treinos intensos.', 'Anilha de Ferro 12kg SteelPump', 171.59, 'PES-ANI-001', '1752066679300_Anilha de Ferro 12kg SteelPump.png', 'ANILHA', '2025-07-09 10:11:19.663076');
INSERT INTO public.products VALUES (1000, 'NutriLab', 'SUPLEMENTOS', '2025-07-03 19:18:02.848923', 'Whey Protein da marca NutriLab com 900g. Ideal para treinos intensos.', 'Whey Protein 900g NutriLab', 110.76, 'SUP-WHE-002', '1752063684050_Whey Protein 900g NutriLab.png', 'WHEY', '2025-07-09 09:21:24.381598');
INSERT INTO public.products VALUES (1028, 'GripForce', 'ACESSORIOS', '2025-07-03 19:14:47.32274', 'Caneleira com Peso da marca GripForce com 10g. Ideal para treinos intensos.', 'Caneleira com Peso 10kg GripForce', 83.40, 'ACE-CAN-001', '1752065764058_Caneleira com Peso 10kg GripForce.png', 'CANELEIRA', '2025-07-09 09:56:04.387231');
INSERT INTO public.products VALUES (1029, 'BodyTools', 'ACESSORIOS', '2025-07-03 19:17:51.643346', 'Caneleira com Peso da marca BodyTools com 10g. Ideal para treinos intensos.', 'Caneleira com Peso 10kg BodyTools', 59.91, 'ACE-CAN-003', '1752065775726_Caneleira com Peso 10kg BodyTools.png', 'CANELEIRA', '2025-07-09 09:56:16.127006');
INSERT INTO public.products VALUES (1030, 'MovePro', 'ROUPAS', '2025-07-03 19:16:54.404993', 'Camisa Dry Fit da marca MovePro. Ideal para treinos intensos.', 'Camisa Dry Fit MovePro', 98.02, 'ROU-CAM-002', '1752065837520_Camisa Dry Fit MovePro.jpg', 'CAMISA', '2025-07-09 09:57:18.038327');
INSERT INTO public.products VALUES (1031, 'FitWear', 'ROUPAS', '2025-07-03 19:17:18.502345', 'Camisa Dry Fit da marca FitWear. Ideal para treinos intensos.', 'Camisa Dry Fit FitWear', 97.61, 'ROU-CAM-003', '1752065947930_Camisa Dry Fit FitWear.png', 'CAMISA', '2025-07-09 09:59:08.298109');
INSERT INTO public.products VALUES (1032, 'Athletix', 'ROUPAS', '2025-07-03 19:18:33.819507', 'Camisa Dry Fit da marca Athletix. Ideal para treinos intensos.', 'Camisa Dry Fit Athletix', 60.57, 'ROU-CAM-004', '1752066042601_Camisa Dry Fit Athletix.png', 'CAMISA', '2025-07-09 10:00:42.910444');
INSERT INTO public.products VALUES (1033, 'GripForce', 'ACESSORIOS', '2025-07-03 19:15:03.663299', 'Bola de Pilates da marca GripForce. Ideal para treinos intensos.', 'Bola de Pilates GripForce', 74.40, 'ACE-BOL-001', '1752066355922_Bola de Pilates GripForce.png', 'BOLA', '2025-07-09 10:05:56.161891');
INSERT INTO public.products VALUES (1034, 'BodyTools', 'ACESSORIOS', '2025-07-03 19:17:46.155115', 'Bola de Pilates da marca BodyTools. Ideal para treinos intensos.', 'Bola de Pilates BodyTools', 88.68, 'ACE-BOL-002', '1752066421797_Bola de Pilates BodyTools.png', 'BOLA', '2025-07-09 10:07:02.045807');
INSERT INTO public.products VALUES (1035, 'CycloMax', 'AEROBICOS', '2025-07-03 19:16:34.507667', 'Bicicleta Ergométrica da marca CycloMax. Ideal para treinos intensos.', 'Bicicleta Ergométrica CycloMax', 2431.93, 'AER-BIC-002', '1752066455484_Bicicleta Ergométrica CycloMax.jpg', 'BICICLETA', '2025-07-09 10:07:35.836409');
INSERT INTO public.products VALUES (1049, 'Aeroflex', 'AEROBICOS', '2025-07-09 13:59:44.918776', 'O produto Esteira Aeroflex da marca Aeroflex foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Esteira Elétrica Dobrável Aeroflex', 3489.76, 'AER-EST-011', '1752242119442_Esteira Elétrica Dobrável Aeroflex.png', 'ESTEIRA', '2025-07-11 10:55:20.593666');
INSERT INTO public.products VALUES (1050, 'CycloMax', 'AEROBICOS', '2025-07-09 13:59:49.685578', 'O produto Eliptico CycloMax da marca CycloMax foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Elíptico Magnético com Painel Digital CycloMax', 2805.11, 'AER-ELI-004', '1752242131953_Elíptico Magnético com Painel Digital CycloMax.png', 'ELIPTICO', '2025-07-11 10:55:32.256089');
INSERT INTO public.products VALUES (1159, 'Aeroflex', 'AEROBICOS', '2025-07-09 14:50:26.061369', 'O produto Bicicleta Ergométrica PowerRide da marca Aeroflex foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Bicicleta Ergométrica PowerRide', 2650.75, 'AER-BIC-026', '1752241914419_Bicicleta Ergométrica PowerRide.png', 'BICICLETA', '2025-07-11 10:51:54.868357');
INSERT INTO public.products VALUES (1045, 'PulseGo', 'AEROBICOS', '2025-07-09 13:59:17.654829', 'O produto Esteira PulseGo da marca PulseGo foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Esteira Elétrica Dobrável PulseGo', 4133.88, 'AER-EST-003', '1752242032471_Esteira Elétrica Dobrável PulseGo.png', 'ESTEIRA', '2025-07-11 10:53:52.821009');
INSERT INTO public.products VALUES (1036, 'Cardionix', 'AEROBICOS', '2025-07-03 19:14:59.242197', 'Bicicleta Ergométrica da marca Cardionix. Ideal para treinos intensos.', 'Bicicleta Ergométrica Cardionix', 2296.35, 'AER-BIC-001', '1752066470630_Bicicleta Ergométrica Cardionix.png', 'BICICLETA', '2025-07-09 10:07:51.050058');
INSERT INTO public.products VALUES (1037, 'Polimet', 'ACESSORIOS', '2025-07-03 14:24:47.687676', 'Barra cromada para musculação, ideal para bíceps e tríceps.', 'Barra Reta de Musculação 1,20m Polimet', 119.90, 'ACE-001', '1752066591586_Barra Reta 1,20m Polimet.png', 'BARRA', '2025-07-09 10:09:51.954082');
INSERT INTO public.products VALUES (1038, 'BodyTools', 'ACESSORIOS', '2025-07-03 19:17:02.671481', 'Barra para Musculação da marca BodyTools. Ideal para treinos intensos.', 'Barra Reta de Musculação 1,20m BodyTools', 128.80, 'ACE-BAR-001', '1752066624927_Barra para Musculação BodyTools.jpg', 'BARRA', '2025-07-09 10:10:25.290802');
INSERT INTO public.products VALUES (1041, 'IronBeast', 'PESOS', '2025-07-03 19:17:35.191752', 'Anilha de Ferro da marca IronBeast com 10g. Ideal para treinos intensos.', 'Anilha de Ferro 10kg IronBeast', 150.60, 'PES-ANI-002', '1752066690812_Anilha de Ferro 10kg IronBeast.jpg', 'ANILHA', '2025-07-09 10:11:31.227166');
INSERT INTO public.products VALUES (1052, 'StrideTech', 'AEROBICOS', '2025-07-09 14:00:01.329877', 'O produto Eliptico StrideTech da marca StrideTech foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Elíptico Magnético com Painel Digital StrideTech', 1980.89, 'AER-ELI-006', '1752242165760_Elíptico Magnético com Painel Digital StrideTech.png', 'ELIPTICO', '2025-07-11 10:56:06.382398');
INSERT INTO public.products VALUES (1053, 'Cardionix', 'AEROBICOS', '2025-07-09 14:00:06.406263', 'O produto Eliptico Cardionix da marca Cardionix foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Elíptico Magnético com Painel Digital Cardionix', 3054.40, 'AER-ELI-008', '1752242179021_Elíptico Magnético com Painel Digital Cardionix.png', 'ELIPTICO', '2025-07-11 10:56:20.636071');
INSERT INTO public.products VALUES (1054, 'PulseGo', 'AEROBICOS', '2025-07-09 14:00:11.827767', 'O produto Eliptico PulseGo da marca PulseGo foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Elíptico Magnético com Painel Digital PulseGo', 2754.68, 'AER-ELI-019', '1752242240175_Elíptico Magnético com Painel Digital PulseGo.png', 'ELIPTICO', '2025-07-11 10:57:20.463197');
INSERT INTO public.products VALUES (1055, 'GripForce', 'ACESSORIOS', '2025-07-09 14:00:28.036366', 'O produto Barra GripForce da marca GripForce foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Barra Reta de Musculação 1,20m GripForce', 135.48, 'ACE-BAR-003', '1752242271694_Barra Reta de Musculação 1,20m GripForce.png', 'BARRA', '2025-07-11 10:57:52.179629');
INSERT INTO public.products VALUES (1056, 'TrainWell', 'ACESSORIOS', '2025-07-09 14:00:34.688284', 'O produto Barra TrainWell da marca TrainWell foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Barra Reta de Musculação 1,20m TrainWell', 141.43, 'ACE-BAR-006', '1752242286760_Barra Reta de Musculação 1,20m TrainWell.png', 'BARRA', '2025-07-11 10:58:07.261076');
INSERT INTO public.products VALUES (1058, 'MovePlus', 'ACESSORIOS', '2025-07-09 14:00:45.809521', 'O produto Barra MovePlus da marca MovePlus foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Barra Reta de Musculação 1,20m MovePlus', 127.08, 'ACE-BAR-012', '1752242318960_Barra Reta de Musculação 1,20m MovePlus.png', 'BARRA', '2025-07-11 10:58:39.391359');
INSERT INTO public.products VALUES (1059, 'GymEdge', 'ACESSORIOS', '2025-07-09 14:01:02.561424', 'O produto Caneleira GymEdge da marca GymEdge foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Caneleira com Peso Regulável GymEdge', 69.34, 'ACE-CAN-004', '1752242355495_Caneleira com Peso Regulável GymEdge.png', 'CANELEIRA', '2025-07-11 10:59:15.944309');
INSERT INTO public.products VALUES (1164, 'StrideTech', 'AEROBICOS', '2025-07-09 14:51:30.956254', 'O produto Bicicleta Ergométrica Horizontal Comfort da marca StrideTech foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Bicicleta Ergométrica Horizontal Comfort', 3200.40, 'AER-BIC-030', '1752242373489_Bicicleta Ergométrica Horizontal Comfort.png', 'BICICLETA', '2025-07-11 10:59:35.176836');
INSERT INTO public.products VALUES (1060, 'MovePlus', 'ACESSORIOS', '2025-07-09 14:01:21.463689', 'O produto Caneleira MovePlus da marca MovePlus foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Caneleira com Peso Regulável MovePlus', 60.19, 'ACE-CAN-005', '1752242392078_Caneleira com Peso Regulável MovePlus.png', 'CANELEIRA', '2025-07-11 10:59:52.471446');
INSERT INTO public.products VALUES (1061, 'TrainWell', 'ACESSORIOS', '2025-07-09 14:01:38.113637', 'O produto Caneleira TrainWell da marca TrainWell foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Caneleira com Peso Regulável TrainWell', 54.02, 'ACE-CAN-006', '1752242411746_Caneleira com Peso Regulável TrainWell.png', 'CANELEIRA', '2025-07-11 11:00:12.056055');
INSERT INTO public.products VALUES (1063, 'GripForce', 'ACESSORIOS', '2025-07-09 14:01:55.465597', 'O produto Corda GripForce da marca GripForce foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Corda de Pular Profissional com Rolamento GripForce', 26.35, 'ACE-COR-002', '1752242448150_Corda de Pular Profissional com Rolamento GripForce.png', 'CORDA', '2025-07-11 11:00:48.427138');
INSERT INTO public.products VALUES (1064, 'BodyTools', 'ACESSORIOS', '2025-07-09 14:02:03.219787', 'O produto Corda BodyTools da marca BodyTools foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Corda de Pular Profissional com Rolamento BodyTools', 42.83, 'ACE-COR-004', '1752242461216_Corda de Pular Profissional com Rolamento BodyTools.png', 'CORDA', '2025-07-11 11:01:01.527698');
INSERT INTO public.products VALUES (1065, 'MovePlus', 'ACESSORIOS', '2025-07-09 14:04:19.412838', 'O produto Corda MovePlus da marca MovePlus foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Corda de Pular Profissional com Rolamento MovePlus', 32.28, 'ACE-COR-005', '1752242475534_Corda de Pular Profissional com Rolamento MovePlus.png', 'CORDA', '2025-07-11 11:01:15.933067');
INSERT INTO public.products VALUES (1066, 'GymEdge', 'ACESSORIOS', '2025-07-09 14:04:24.908026', 'O produto Corda GymEdge da marca GymEdge foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Corda de Pular Profissional com Rolamento GymEdge', 38.67, 'ACE-COR-007', '1752242487318_Corda de Pular Profissional com Rolamento GymEdge.png', 'CORDA', '2025-07-11 11:01:27.531662');
INSERT INTO public.products VALUES (1067, 'TrainWell', 'ACESSORIOS', '2025-07-09 14:04:31.329844', 'O produto Corda TrainWell da marca TrainWell foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Corda de Pular Profissional com Rolamento TrainWell', 41.11, 'ACE-COR-009', '1752242500527_Corda de Pular Profissional com Rolamento TrainWell.png', 'CORDA', '2025-07-11 11:01:40.829945');
INSERT INTO public.products VALUES (1068, 'BodyTools', 'ACESSORIOS', '2025-07-09 14:04:37.457074', 'O produto Colchonete BodyTools da marca BodyTools foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Colchonete Dobrável para Exercícios BodyTools', 89.20, 'ACE-COL-002', '1752242519223_Colchonete Dobrável para Exercícios BodyTools.png', 'COLCHONETE', '2025-07-11 11:01:59.52897');
INSERT INTO public.products VALUES (1163, 'CycloMax', 'AEROBICOS', '2025-07-09 14:51:20.063342', 'O produto Bicicleta de Spinning Racer da marca CycloMax foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Bicicleta de Spinning Racer', 2950.00, 'AER-BIC-029', '1752242338265_Bicicleta de Spinning Racer CycloMax.png', 'BICICLETA', '2025-07-11 10:58:58.552271');
INSERT INTO public.products VALUES (1071, 'MovePlus', 'ACESSORIOS', '2025-07-09 14:04:56.829929', 'O produto Colchonete MovePlus da marca MovePlus foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Colchonete Dobrável para Exercícios MovePlus', 93.99, 'ACE-COL-006', '1752242554650_Colchonete Dobrável para Exercícios MovePlus.png', 'COLCHONETE', '2025-07-11 11:02:35.796644');
INSERT INTO public.products VALUES (1075, 'TrainWell', 'ACESSORIOS', '2025-07-09 14:11:20.02813', 'O produto Suporte TrainWell da marca TrainWell foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Suporte de Parede para Halteres TrainWell', 277.50, 'ACE-SUP-004', '1752242661338_Suporte de Parede para Halteres TrainWell.png', 'SUPORTE', '2025-07-11 11:04:21.753833');
INSERT INTO public.products VALUES (1166, 'Aeroflex', 'AEROBICOS', '2025-07-09 14:51:52.105126', 'O produto Bicicleta Ergométrica Vertical Advanced da marca Aeroflex foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Bicicleta Ergométrica Vertical Advanced', 2780.00, 'AER-BIC-032', '1752242698348_Bicicleta Ergométrica Vertical Advanced (Aeroflex).png', 'BICICLETA', '2025-07-11 11:04:58.725269');
INSERT INTO public.products VALUES (1076, 'MovePlus', 'ACESSORIOS', '2025-07-09 14:11:27.809326', 'O produto Suporte MovePlus da marca MovePlus foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Suporte de Parede para Halteres MovePlus', 268.96, 'ACE-SUP-005', '1752242671891_Suporte de Parede para Halteres MovePlus.png', 'SUPORTE', '2025-07-11 11:04:32.166337');
INSERT INTO public.products VALUES (1077, 'GymEdge', 'ACESSORIOS', '2025-07-09 14:11:35.028604', 'O produto Suporte GymEdge da marca GymEdge foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Suporte de Parede para Halteres GymEdge', 264.44, 'ACE-SUP-007', '1752242684706_Suporte de Parede para Halteres GymEdge.png', 'SUPORTE', '2025-07-11 11:04:45.013247');
INSERT INTO public.products VALUES (1078, 'LiftPro', 'PESOS', '2025-07-09 14:11:59.181406', 'O produto Anilha LiftPro da marca LiftPro foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Anilha Emborrachada 10kg LiftPro', 105.77, 'PES-ANI-005', '1752242733525_Anilha Emborrachada 10kg LiftPro.png', 'ANILHA', '2025-07-11 11:05:33.817218');
INSERT INTO public.products VALUES (1079, 'SteelPump', 'PESOS', '2025-07-09 14:12:14.762303', 'O produto Anilha SteelPump da marca SteelPump foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Anilha Emborrachada 10kg SteelPump', 149.77, 'PES-ANI-006', '1752242745334_Anilha Emborrachada 10kg SteelPump.png', 'ANILHA', '2025-07-11 11:05:45.684011');
INSERT INTO public.products VALUES (1080, 'MassForge', 'PESOS', '2025-07-09 14:12:28.766201', 'O produto Anilha MassForge da marca MassForge foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Anilha Emborrachada 10kg MassForge', 113.88, 'PES-ANI-007', '1752242758075_Anilha Emborrachada 10kg MassForge.png', 'ANILHA', '2025-07-11 11:05:58.465651');
INSERT INTO public.products VALUES (1081, 'TitanIron', 'PESOS', '2025-07-09 14:12:44.731203', 'O produto Anilha TitanIron da marca TitanIron foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Anilha Emborrachada 10kg TitanIron', 128.53, 'PES-ANI-008', '1752242770754_Anilha Emborrachada 10kg TitanIron.png', 'ANILHA', '2025-07-11 11:06:11.143333');
INSERT INTO public.products VALUES (1082, 'IronBeast', 'PESOS', '2025-07-09 14:12:58.460624', 'O produto Anilha IronBeast da marca IronBeast foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Anilha Emborrachada 10kg IronBeast', 110.15, 'PES-ANI-009', '1752242781642_Anilha Emborrachada 10kg IronBeast.png', 'ANILHA', '2025-07-11 11:06:22.138576');
INSERT INTO public.products VALUES (1165, 'PulseGo', 'AEROBICOS', '2025-07-09 14:51:41.36955', 'O produto Mini Bicicleta Ergométrica PedalPro da marca PulseGo foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Mini Bicicleta Ergométrica PedalPro', 450.60, 'AER-BIC-031', '1752242623716_Mini Bicicleta Ergométrica PedalPro.png', 'BICICLETA', '2025-07-11 11:03:43.973288');
INSERT INTO public.products VALUES (1167, 'Cardionix', 'AEROBICOS', '2025-07-09 14:52:03.445661', 'O produto Bicicleta de Air Bike CrossTornado da marca Cardionix foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Bicicleta de Air Bike CrossTornado', 3500.20, 'AER-BIC-033', '1752242719262_Bicicleta de Air Bike CrossTornado (Cardionix).png', 'BICICLETA', '2025-07-11 11:05:20.55751');
INSERT INTO public.products VALUES (1070, 'GymEdge', 'ACESSORIOS', '2025-07-09 14:04:49.868823', 'O produto Colchonete GymEdge da marca GymEdge foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Colchonete Dobrável para Exercícios GymEdge', 88.58, 'ACE-COL-004', '1752242545163_Colchonete Dobrável para Exercícios GymEdge.png', 'COLCHONETE', '2025-07-11 11:02:25.738518');
INSERT INTO public.products VALUES (1073, 'GripForce', 'ACESSORIOS', '2025-07-09 14:11:08.92525', 'O produto Suporte GripForce da marca GripForce foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Suporte de Parede para Halteres GripForce', 289.47, 'ACE-SUP-002', '1752242636906_Suporte de Parede para Halteres GripForce.png', 'SUPORTE', '2025-07-11 11:03:57.241315');
INSERT INTO public.products VALUES (1074, 'BodyTools', 'ACESSORIOS', '2025-07-09 14:11:13.626579', 'O produto Suporte BodyTools da marca BodyTools foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Suporte de Parede para Halteres BodyTools', 250.72, 'ACE-SUP-003', '1752242650238_Suporte de Parede para Halteres BodyTools.png', 'SUPORTE', '2025-07-11 11:04:10.560222');
INSERT INTO public.products VALUES (1170, 'PulseGo', 'AEROBICOS', '2025-07-09 14:52:35.263965', 'O produto Esteira Residencial WalkFit da marca PulseGo foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Esteira Residencial WalkFit', 3800.50, 'AER-EST-024', '1752242841841_Esteira Residencial WalkFit PulseGo.png', 'ESTEIRA', '2025-07-11 11:07:22.629739');
INSERT INTO public.products VALUES (1171, 'Aeroflex', 'AEROBICOS', '2025-07-09 14:52:41.744444', 'O produto Esteira com Inclinação Eletrônica Summit da marca Aeroflex foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Esteira com Inclinação Eletrônica Summit', 4800.80, 'AER-EST-025', '1752243052323_Esteira com Inclinação Eletrônica Summit Aeroflex.png', 'ESTEIRA', '2025-07-11 11:10:52.738668');
INSERT INTO public.products VALUES (1090, 'LiftPro', 'PESOS', '2025-07-09 14:13:46.932263', 'O produto Halter LiftPro da marca LiftPro foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Par de Halteres Emborrachados LiftPro', 160.00, 'PES-HAL-007', '1752242881471_Par de Halteres Emborrachados LiftPro.png', 'HALTER', '2025-07-11 11:08:01.714374');
INSERT INTO public.products VALUES (1091, 'IronBeast', 'PESOS', '2025-07-09 14:13:58.06937', 'O produto Halter IronBeast da marca IronBeast foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Par de Halteres Emborrachados IronBeast', 204.05, 'PES-HAL-008', '1752242905843_Par de Halteres Emborrachados IronBeast.png', 'HALTER', '2025-07-11 11:08:26.138757');
INSERT INTO public.products VALUES (1093, 'SteelPump', 'PESOS', '2025-07-09 14:14:20.204724', 'O produto Kettlebell SteelPump da marca SteelPump foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Kettlebell Emborrachado de Treinamento SteelPump', 165.85, 'PES-KET-002', '1752242921818_Kettlebell Emborrachado de Treinamento SteelPump.png', 'KETTLEBELL', '2025-07-11 11:08:42.128753');
INSERT INTO public.products VALUES (1094, 'LiftPro', 'PESOS', '2025-07-09 14:14:27.209819', 'O produto Kettlebell LiftPro da marca LiftPro foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Kettlebell Emborrachado de Treinamento LiftPro', 206.87, 'PES-KET-003', '1752242932588_Kettlebell Emborrachado de Treinamento LiftPro.png', 'KETTLEBELL', '2025-07-11 11:08:53.045449');
INSERT INTO public.products VALUES (1095, 'IronBeast', 'PESOS', '2025-07-09 14:14:33.098185', 'O produto Kettlebell IronBeast da marca IronBeast foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Kettlebell Emborrachado de Treinamento IronBeast', 189.60, 'PES-KET-004', '1752242946446_Kettlebell Emborrachado de Treinamento IronBeast.png', 'KETTLEBELL', '2025-07-11 11:09:06.888839');
INSERT INTO public.products VALUES (1097, 'MassForge', 'PESOS', '2025-07-09 14:14:45.528932', 'O produto Kettlebell MassForge da marca MassForge foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Kettlebell Emborrachado de Treinamento MassForge', 166.97, 'PES-KET-006', '1752242973660_Kettlebell Emborrachado de Treinamento MassForge.png', 'KETTLEBELL', '2025-07-11 11:09:33.998715');
INSERT INTO public.products VALUES (1098, 'MassForge', 'PESOS', '2025-07-09 14:16:06.040792', 'O produto Halter ajustavel MassForge da marca MassForge foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Halter Ajustável MassForge', 322.95, 'PES-HLA-002', '1752242983896_Halter Ajustável MassForge.png', 'HALTER', '2025-07-11 11:09:44.177037');
INSERT INTO public.products VALUES (1099, 'SteelPump', 'PESOS', '2025-07-09 14:16:27.473337', 'O produto Halter ajustavel SteelPump da marca SteelPump foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Halter Ajustável SteelPump', 401.30, 'PES-HLA-003', '1752242996055_Halter Ajustável SteelPump.png', 'HALTER', '2025-07-11 11:09:56.367688');
INSERT INTO public.products VALUES (1100, 'LiftPro', 'PESOS', '2025-07-09 14:16:39.655188', 'O produto Halter ajustavel LiftPro da marca LiftPro foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Halter Ajustável LiftPro', 367.65, 'PES-HLA-004', '1752243011526_Halter Ajustável LiftPro.png', 'HALTER', '2025-07-11 11:10:11.86284');
INSERT INTO public.products VALUES (1102, 'TitanIron', 'PESOS', '2025-07-09 14:17:01.024512', 'O produto Halter ajustavel TitanIron da marca TitanIron foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Halter Ajustável TitanIron', 320.00, 'PES-HLA-006', '1752243025647_Halter Ajustável TitanIron.png', 'HALTER', '2025-07-11 11:10:26.044236');
INSERT INTO public.products VALUES (1083, 'MassForge', 'PESOS', '2025-07-09 14:13:24.792868', 'O produto Halter MassForge da marca MassForge foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Par de Halteres Emborrachados MassForge', 178.50, 'PES-HAL-005', '1752242856104_Par de Halteres Emborrachados MassForge.png', 'HALTER', '2025-07-11 11:07:36.891496');
INSERT INTO public.products VALUES (1106, 'SteelPump', 'PESOS', '2025-07-09 14:17:23.711081', 'O produto Presilha SteelPump da marca SteelPump foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Presilha de Segurança para Barra SteelPump', 25.30, 'PES-PRE-005', '1752243077265_Presilha de Segurança para Barra SteelPump.png', 'PRESILHA', '2025-07-11 11:11:17.495416');
INSERT INTO public.products VALUES (1173, 'CycloMax', 'AEROBICOS', '2025-07-09 14:52:58.008626', 'O produto Esteira Elétrica Compacta Slim da marca CycloMax foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Esteira Elétrica Compacta Slim', 3100.70, 'AER-EST-027', '1752243104666_Esteira Elétrica Compacta Slim CycloMax.png', 'ESTEIRA', '2025-07-11 11:11:45.076596');
INSERT INTO public.products VALUES (1107, 'IronBeast', 'PESOS', '2025-07-09 14:17:29.686094', 'O produto Presilha IronBeast da marca IronBeast foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Presilha de Segurança para Barra IronBeast', 42.26, 'PES-PRE-006', '1752243089100_Presilha de Segurança para Barra IronBeast.png', 'PRESILHA', '2025-07-11 11:11:29.523144');
INSERT INTO public.products VALUES (1169, 'StrideTech', 'AEROBICOS', '2025-07-09 14:52:25.96916', 'O produto Esteira Profissional Runner X da marca StrideTech foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Esteira Profissional Runner X', 5500.00, 'AER-EST-023', '1752242828094_Esteira Profissional Runner X StrideTech.png', 'ESTEIRA', '2025-07-11 11:07:08.466368');
INSERT INTO public.products VALUES (1103, 'LiftPro', 'PESOS', '2025-07-09 14:17:07.136267', 'O produto Presilha LiftPro da marca LiftPro foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Presilha de Segurança para Barra LiftPro', 36.23, 'PES-PRE-002', '1752243036904_Presilha de Segurança para Barra LiftPro.png', 'PRESILHA', '2025-07-11 11:10:37.788407');
INSERT INTO public.products VALUES (1174, 'StrideTech', 'AEROBICOS', '2025-07-09 14:53:08.880857', 'O produto Esteira Semi-Profissional Endurance da marca StrideTech foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Esteira Semi-Profissional Endurance', 4250.00, 'AER-EST-028', '1752243162752_Esteira Semi-Profissional Endurance StrideTech.png', 'ESTEIRA', '2025-07-11 11:12:43.07366');
INSERT INTO public.products VALUES (1175, 'PulseGo', 'AEROBICOS', '2025-07-09 14:53:18.839996', 'O produto Esteira com Amortecimento de Impacto da marca PulseGo foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Esteira com Amortecimento de Impacto', 4555.50, 'AER-EST-029', '1752243211867_Esteira com Amortecimento de Impacto PulseGo.png', 'ESTEIRA', '2025-07-11 11:13:32.118062');
INSERT INTO public.products VALUES (1176, 'Aeroflex', 'AEROBICOS', '2025-07-09 14:53:28.003494', 'O produto Esteira Smart com Conexão Bluetooth da marca Aeroflex foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Esteira Smart com Conexão Bluetooth', 5100.25, 'AER-EST-030', '1752243226420_Esteira Smart com Conexão Bluetooth Aeroflex.png', 'ESTEIRA', '2025-07-11 11:13:46.851427');
INSERT INTO public.products VALUES (1177, 'Cardionix', 'AEROBICOS', '2025-07-09 14:53:36.020123', 'O produto Esteira para Caminhada Leve da marca Cardionix foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Esteira para Caminhada Leve', 2900.00, 'AER-EST-031', '1752243239455_Esteira para Caminhada Leve Cardionix.png', 'ESTEIRA', '2025-07-11 11:13:59.766327');
INSERT INTO public.products VALUES (1178, 'CycloMax', 'AEROBICOS', '2025-07-09 14:53:44.612413', 'O produto Esteira Profissional High Performance da marca CycloMax foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Esteira Profissional High Performance', 7500.00, 'AER-EST-032', '1752243254170_Esteira Profissional High Performance CycloMax.png', 'ESTEIRA', '2025-07-11 11:14:14.480782');
INSERT INTO public.products VALUES (1182, 'Aeroflex', 'AEROBICOS', '2025-07-09 14:54:15.680852', 'O produto Elíptico com Assento Regulável da marca Aeroflex foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Elíptico com Assento Regulável', 3450.60, 'AER-ELI-026', '1752243316870_Elíptico com Assento Regulável Aeroflex.png', 'ELIPTICO', '2025-07-11 11:15:17.191612');
INSERT INTO public.products VALUES (1183, 'Cardionix', 'AEROBICOS', '2025-07-09 14:54:23.87753', 'O produto Mini Elíptico Portátil Stepper da marca Cardionix foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Mini Elíptico Portátil Stepper', 890.00, 'AER-ELI-027', '1752243330610_Mini Elíptico Portátil Stepper Cardionix.png', 'ELIPTICO', '2025-07-11 11:15:30.924065');
INSERT INTO public.products VALUES (1185, 'StrideTech', 'AEROBICOS', '2025-07-09 14:54:41.384383', 'O produto Elíptico com Roda de Inércia Frontal da marca StrideTech foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Elíptico com Roda de Inércia Frontal', 3600.00, 'AER-ELI-029', '1752243361451_Elíptico com Roda de Inércia Frontal StrideTech.png', 'ELIPTICO', '2025-07-11 11:16:01.904678');
INSERT INTO public.products VALUES (1186, 'PulseGo', 'AEROBICOS', '2025-07-09 14:54:57.269702', 'O produto Elíptico Magnético Dobrável SpaceSaver da marca PulseGo foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Elíptico Magnético Dobrável SpaceSaver', 3100.50, 'AER-ELI-030', '1752243373947_Elíptico Magnético Dobrável SpaceSaver PulseGo.png', 'ELIPTICO', '2025-07-11 11:16:14.292575');
INSERT INTO public.products VALUES (1187, 'Aeroflex', 'AEROBICOS', '2025-07-09 14:55:01.593359', 'O produto Elíptico Profissional Cross Trainer da marca Aeroflex foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Elíptico Profissional Cross Trainer', 5200.00, 'AER-ELI-031', '1752243390741_Elíptico Profissional Cross Trainer Aeroflex.png', 'ELIPTICO', '2025-07-11 11:16:31.014316');
INSERT INTO public.products VALUES (1188, 'Cardionix', 'AEROBICOS', '2025-07-09 14:55:08.527167', 'O produto Elíptico com Monitor Cardíaco Integrado da marca Cardionix foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Elíptico com Monitor Cardíaco Integrado', 3350.80, 'AER-ELI-032', '1752243404165_Elíptico com Monitor Cardíaco Integrado Cardionix.png', 'ELIPTICO', '2025-07-11 11:16:44.437617');
INSERT INTO public.products VALUES (1189, 'CycloMax', 'AEROBICOS', '2025-07-09 14:55:14.421288', 'O produto Elíptico Silencioso SmoothGlide da marca CycloMax foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Elíptico Silencioso SmoothGlide', 3950.00, 'AER-ELI-033', '1752244924664_Elíptico Silencioso SmoothGlide CycloMax.png', 'ELIPTICO', '2025-07-11 11:42:05.454554');
INSERT INTO public.products VALUES (1190, 'BodyTools', 'ACESSORIOS', '2025-07-09 14:55:19.310901', 'O produto Barra Olímpica 2,20m ProLift da marca BodyTools foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Barra Olímpica 2,20m ProLift', 450.00, 'ACE-BAR-024', '1752244937617_Barra Olímpica 2,20m ProLift BodyTools.png', 'BARRA', '2025-07-11 11:42:17.772567');
INSERT INTO public.products VALUES (1191, 'GripForce', 'ACESSORIOS', '2025-07-09 14:55:24.883862', 'O produto Barra W para Musculação da marca GripForce foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Barra W para Musculação', 180.50, 'ACE-BAR-025', '1752244950520_Barra W para Musculação GripForce.png', 'BARRA', '2025-07-11 11:42:30.702077');
INSERT INTO public.products VALUES (1192, 'TrainWell', 'ACESSORIOS', '2025-07-09 14:55:31.71225', 'O produto Barra H para Tríceps da marca TrainWell foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Barra H para Tríceps', 195.70, 'ACE-BAR-026', '1752244963194_Barra H para Tríceps TrainWell.png', 'BARRA', '2025-07-11 11:42:43.564407');
INSERT INTO public.products VALUES (1193, 'GymEdge', 'ACESSORIOS', '2025-07-09 14:55:38.003536', 'O produto Barra Maciça Cromada 1,50m da marca GymEdge foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Barra Maciça Cromada 1,50m', 160.00, 'ACE-BAR-027', '1752244975050_Barra Maciça Cromada 1,50m GymEdge.png', 'BARRA', '2025-07-11 11:42:55.38727');
INSERT INTO public.products VALUES (1101, 'IronBeast', 'PESOS', '2025-07-09 14:16:50.412502', 'O produto Halter ajustavel IronBeast da marca IronBeast foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Halter Ajustável IronBeast', 409.80, 'PES-HLA-005', '1752243142593_Halter Ajustável IronBeast.png', 'HALTER', '2025-07-11 11:12:23.0185');
INSERT INTO public.products VALUES (1110, 'NutriLab', 'SUPLEMENTOS', '2025-07-09 14:17:58.719054', 'O produto Pré-Treino NutriLab da marca NutriLab foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Suplemento Pré-Treino Energia Extrema NutriLab', 178.11, 'SUP-PRE-003', '1752244999212_Suplemento Pré-Treino Energia Extrema NutriLab.png', 'PRE_TREINO', '2025-07-11 11:43:19.541445');
INSERT INTO public.products VALUES (1196, 'GripForce', 'ACESSORIOS', '2025-07-09 14:55:54.81261', 'O produto Barra Reta Oca 1,80m da marca GripForce foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Barra Reta Oca 1,80m', 130.00, 'ACE-BAR-030', '1752245114932_Barra Reta Oca 1,80m GripForce.png', 'BARRA', '2025-07-11 11:45:15.235814');
INSERT INTO public.products VALUES (1114, 'StrongSupps', 'SUPLEMENTOS', '2025-07-09 14:18:43.688168', 'O produto Pré-Treino StrongSupps da marca StrongSupps foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Suplemento Pré-Treino Energia Extrema StrongSupps', 175.08, 'SUP-PRE-006', '1752245046368_Suplemento Pré-Treino Energia Extrema StrongSupps.png', 'PRE_TREINO', '2025-07-11 11:44:06.758069');
INSERT INTO public.products VALUES (1115, 'XtremeBlend', 'SUPLEMENTOS', '2025-07-09 14:18:52.776221', 'O produto Pré-Treino XtremeBlend da marca XtremeBlend foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Suplemento Pré-Treino Energia Extrema XtremeBlend', 146.05, 'SUP-PRE-007', '1752245057635_Suplemento Pré-Treino Energia Extrema XtremeBlend.png', 'PRE_TREINO', '2025-07-11 11:44:17.995508');
INSERT INTO public.products VALUES (1116, 'VitaCore', 'SUPLEMENTOS', '2025-07-09 14:19:05.453025', 'O produto Pré-Treino VitaCore da marca VitaCore foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Pré-Treino com Cafeína e Beta-Alanina VitaCore', 144.04, 'SUP-PRE-016', '1752245068266_Pré-Treino com Cafeína e Beta-Alanina VitaCore.png', 'PRE_TREINO', '2025-07-11 11:44:28.659289');
INSERT INTO public.products VALUES (1117, 'StrongSupps', 'SUPLEMENTOS', '2025-07-09 14:19:16.662395', 'O produto Pré-Treino StrongSupps da marca StrongSupps foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Pré-Treino com Cafeína e Beta-Alanina StrongSupps', 175.08, 'SUP-PRE-017', '1752245078636_Pré-Treino com Cafeína e Beta-Alanina StrongSupps.png', 'PRE_TREINO', '2025-07-11 11:44:39.872267');
INSERT INTO public.products VALUES (1118, 'NutriLab', 'SUPLEMENTOS', '2025-07-09 14:19:23.991245', 'O produto Pré-Treino NutriLab da marca NutriLab foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Pré-Treino com Cafeína e Beta-Alanina NutriLab', 178.11, 'SUP-PRE-020', '1752245090442_Pré-Treino com Cafeína e Beta-Alanina NutriLab.png', 'PRE_TREINO', '2025-07-11 11:44:50.748395');
INSERT INTO public.products VALUES (1119, 'XtremeBlend', 'SUPLEMENTOS', '2025-07-09 14:19:29.526069', 'O produto Pré-Treino XtremeBlend da marca XtremeBlend foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Pré-Treino com Cafeína e Beta-Alanina XtremeBlend', 146.05, 'SUP-PRE-022', '1752245100784_Pré-Treino com Cafeína e Beta-Alanina XtremeBlend.png', 'PRE_TREINO', '2025-07-11 11:45:01.089693');
INSERT INTO public.products VALUES (1122, 'VitaCore', 'SUPLEMENTOS', '2025-07-09 14:19:48.003309', 'O produto Whey VitaCore da marca VitaCore foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Whey Protein Concentrado VitaCore', 113.67, 'SUP-WHE-004', '1752245139127_Whey Protein Concentrado VitaCore.png', 'WHEY', '2025-07-11 11:45:40.694437');
INSERT INTO public.products VALUES (1113, 'VitaCore', 'SUPLEMENTOS', '2025-07-09 14:18:35.224778', 'O produto Pré-Treino VitaCore da marca VitaCore foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Suplemento Pré-Treino Energia Extrema VitaCore', 144.04, 'SUP-PRE-005', '1752245035501_Suplemento Pré-Treino Energia Extrema VitaCore.png', 'PRE_TREINO', '2025-07-11 11:43:55.762849');
INSERT INTO public.products VALUES (1123, 'StrongSupps', 'SUPLEMENTOS', '2025-07-09 14:20:01.294579', 'O produto Whey StrongSupps da marca StrongSupps foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Whey Protein Concentrado StrongSupps', 232.66, 'SUP-WHE-005', '1752245149640_Whey Protein Concentrado StrongSupps.png', 'WHEY', '2025-07-11 11:45:49.957109');
INSERT INTO public.products VALUES (1198, 'GymEdge', 'ACESSORIOS', '2025-07-09 14:56:11.991375', 'O produto Barra Curvada para Bíceps/Tríceps da marca GymEdge foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Barra Curvada para Bíceps/Tríceps', 185.00, 'ACE-BAR-032', '1752245188719_Barra Curvada para Bíceps_Tríceps GymEdge.png', 'BARRA', '2025-07-11 11:46:29.051059');
INSERT INTO public.products VALUES (1124, 'XtremeBlend', 'SUPLEMENTOS', '2025-07-09 14:20:11.028573', 'O produto Whey XtremeBlend da marca XtremeBlend foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Whey Protein Concentrado XtremeBlend', 106.08, 'SUP-WHE-006', '1752245162567_Whey Protein Concentrado XtremeBlend.png', 'WHEY', '2025-07-11 11:46:02.804671');
INSERT INTO public.products VALUES (1125, 'NutriLab', 'SUPLEMENTOS', '2025-07-09 14:20:25.197018', 'O produto Whey NutriLab da marca NutriLab foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Whey Protein Concentrado NutriLab', 172.56, 'SUP-WHE-007', '1752245173267_Whey Protein Concentrado NutriLab.png', 'WHEY', '2025-07-11 11:46:13.747961');
INSERT INTO public.products VALUES (1127, 'PowerFuel', 'SUPLEMENTOS', '2025-07-09 14:20:39.985872', 'O produto Whey PowerFuel da marca PowerFuel foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Whey Protein Concentrado PowerFuel', 166.18, 'SUP-WHE-008', '1752245203125_Whey Protein Concentrado PowerFuel.png', 'WHEY', '2025-07-11 11:46:43.634563');
INSERT INTO public.products VALUES (1129, 'PowerFuel', 'SUPLEMENTOS', '2025-07-09 14:21:08.637969', 'O produto Creatina PowerFuel da marca PowerFuel foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Creatina Monohidratada 500g PowerFuel', 121.51, 'SUP-CRE-003', '1752245227721_Creatina Monohidratada 500g PowerFuel.png', 'CREATINA', '2025-07-11 11:47:08.070236');
INSERT INTO public.products VALUES (1130, 'VitaCore', 'SUPLEMENTOS', '2025-07-09 14:21:16.135091', 'O produto Creatina VitaCore da marca VitaCore foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Creatina Monohidratada 1kg VitaCore', 132.84, 'SUP-CRE-004', '1752245242976_Creatina Monohidratada 1kg VitaCore.png', 'CREATINA', '2025-07-11 11:47:23.228505');
INSERT INTO public.products VALUES (1223, 'BodyTools', 'ACESSORIOS', '2025-07-09 19:18:37.199734', 'O produto Colchonete de Alta Densidade Pro da marca BodyTools foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Colchonete de Alta Densidade Pro', 110.50, 'ACE-COL-024', '1752245307852_Colchonete de Alta Densidade Pro BodyTools.png', 'COLCHONETE', '2025-07-11 11:48:28.1438');
INSERT INTO public.products VALUES (1201, 'BodyTools', 'ACESSORIOS', '2025-07-09 14:56:27.197869', 'O produto Par de Caneleiras 3kg da marca BodyTools foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Par de Caneleiras 3kg', 85.00, 'ACE-CAN-024', '1752245295983_Par de Caneleiras 3kg BodyTools.png', 'CANELEIRA', '2025-07-11 11:48:16.339523');
INSERT INTO public.products VALUES (1199, 'MovePlus', 'ACESSORIOS', '2025-07-09 14:56:19.137742', 'O produto Barra Fixa de Parede Multi-pegada da marca MovePlus foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Barra Fixa de Parede Multi-pegada', 320.65, 'ACE-BAR-033', '1752245270773_Barra Fixa de Parede Multi-pegada MovePlus.png', 'BARRA', '2025-07-11 11:47:51.070617');
INSERT INTO public.products VALUES (1224, 'TrainWell', 'ACESSORIOS', '2025-07-09 19:18:43.512996', 'O produto Colchonete de Ioga e Pilates Antiderrapante da marca TrainWell foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Colchonete de Ioga e Pilates Antiderrapante', 130.00, 'ACE-COL-025', '1752245318746_Colchonete de Ioga e Pilates Antiderrapante TrainWell.png', 'COLCHONETE', '2025-07-11 11:48:40.298293');
INSERT INTO public.products VALUES (1225, 'GymEdge', 'ACESSORIOS', '2025-07-09 19:18:49.547871', 'O produto Colchonete com Alça para Transporte da marca GymEdge foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Colchonete com Alça para Transporte', 95.80, 'ACE-COL-026', '1752245330171_Colchonete com Alça para Transporte GymEdge.png', 'COLCHONETE', '2025-07-11 11:48:50.712614');
INSERT INTO public.products VALUES (1227, 'GripForce', 'ACESSORIOS', '2025-07-09 19:19:11.49649', 'O produto Tatame EVA 1x1m 20mm da marca GripForce foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Tatame EVA 1x1m 20mm', 75.00, 'ACE-COL-028', '1752245355095_Tatame EVA 1x1m 20mm GripForce.png', 'COLCHONETE', '2025-07-11 11:49:15.385209');
INSERT INTO public.products VALUES (1228, 'BodyTools', 'ACESSORIOS', '2025-07-09 19:19:20.761317', 'O produto Colchonete para Abdominais da marca BodyTools foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Colchonete para Abdominais', 88.90, 'ACE-COL-029', '1752245365226_Colchonete para Abdominais BodyTools.png', 'COLCHONETE', '2025-07-11 11:49:26.387917');
INSERT INTO public.products VALUES (1229, 'TrainWell', 'ACESSORIOS', '2025-07-09 19:19:29.51534', 'O produto Colchonete Dobrável em 3 Partes da marca TrainWell foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Colchonete Dobrável em 3 Partes', 125.40, 'ACE-COL-030', '1752245382982_Colchonete Dobrável em 3 PartesTrainWell.png', 'COLCHONETE', '2025-07-11 11:49:43.37234');
INSERT INTO public.products VALUES (1230, 'GymEdge', 'ACESSORIOS', '2025-07-09 19:19:36.380287', 'O produto Colchonete de Ginástica Olímpica da marca GymEdge foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Colchonete de Ginástica Olímpica', 350.00, 'ACE-COL-031', '1752245398321_Colchonete de Ginástica Olímpica GymEdge.png', 'COLCHONETE', '2025-07-11 11:49:58.536956');
INSERT INTO public.products VALUES (1241, 'GymEdge', 'ACESSORIOS', '2025-07-09 19:21:17.179049', 'O produto Puxador Romano para Pulley da marca GymEdge foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Puxador Romano para Pulley', 140.00, 'ACE-PUX-029', '1752245410963_Puxador Romano para Pulley GymEdge.png', 'PUXADOR', '2025-07-11 11:50:11.352511');
INSERT INTO public.products VALUES (1242, 'MovePlus', 'ACESSORIOS', '2025-07-09 19:21:26.483356', 'O produto Puxador Tornozeleira para Glúteos da marca MovePlus foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Puxador Tornozeleira para Glúteos', 65.50, 'ACE-PUX-030', '1752245424757_Puxador Tornozeleira para Glúteos MovePlus.png', 'PUXADOR', '2025-07-11 11:50:25.845664');
INSERT INTO public.products VALUES (1243, 'TrainWell', 'ACESSORIOS', '2025-07-09 19:21:33.312525', 'O produto Puxador Curvo para Pulley da marca TrainWell foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Puxador Curvo para Pulley', 160.00, 'ACE-PUX-031', '1752245436471_Puxador Curvo para Pulley TrainWell.png', 'PUXADOR', '2025-07-11 11:50:36.647061');
INSERT INTO public.products VALUES (1246, 'GripForce', 'ACESSORIOS', '2025-07-09 19:21:59.315239', 'O produto Suporte de Chão para Anilhas da marca GripForce foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Suporte de Chão para Anilhas', 350.00, 'ACE-SUP-024', '1752245483033_Suporte de Chão para Anilhas GripForce.png', 'SUPORTE', '2025-07-11 11:51:23.237046');
INSERT INTO public.products VALUES (1247, 'BodyTools', 'ACESSORIOS', '2025-07-09 19:22:05.8083', 'O produto Suporte de Parede para Barras da marca BodyTools foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Suporte de Parede para Barras', 180.90, 'ACE-SUP-025', '1752245502623_Suporte de Parede para Barras BodyTools.png', 'SUPORTE', '2025-07-11 11:51:42.787314');
INSERT INTO public.products VALUES (1248, 'TrainWell', 'ACESSORIOS', '2025-07-09 19:22:16.016765', 'O produto Suporte para Agachamento Livre (Rack) da marca TrainWell foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Suporte para Agachamento Livre (Rack)', 1200.00, 'ACE-SUP-026', '1752245517885_Suporte para Agachamento Livre (Rack) TrainWell.png', 'SUPORTE', '2025-07-11 11:51:58.124467');
INSERT INTO public.products VALUES (1271, 'TitanIron', 'PESOS', '2025-07-09 19:25:37.619538', 'O produto Par de Halteres de Vinil 3kg da marca TitanIron foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Par de Halteres de Vinil 3kg', 95.00, 'PES-HAL-028', '1752245571258_Par de Halteres de Vinil 3kg TitanIron.png', 'HALTER', '2025-07-11 11:52:51.403301');
INSERT INTO public.products VALUES (1131, 'StrongSupps', 'SUPLEMENTOS', '2025-07-09 14:21:25.859339', 'O produto Creatina StrongSupps da marca StrongSupps foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Creatina Monohidratada 1kg StrongSupps', 105.77, 'SUP-CRE-007', '1752245284145_Creatina Monohidratada 1kg StrongSupps.png', 'CREATINA', '2025-07-11 11:48:04.410809');
INSERT INTO public.products VALUES (1135, 'Athletix', 'ROUPAS', '2025-07-09 14:21:58.340499', 'O produto Camisa Athletix da marca Athletix foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Camisa Dry Fit Masculina Athletix', 53.00, 'ROU-CAM-006', '1752245628236_Camisa Dry Fit Masculina Athletix.png', 'CAMISA', '2025-07-11 11:53:48.631029');
INSERT INTO public.products VALUES (1136, 'FitWear', 'ROUPAS', '2025-07-09 14:22:04.662267', 'O produto Camisa FitWear da marca FitWear foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Camisa Dry Fit Masculina FitWear', 50.58, 'ROU-CAM-008', '1752245637574_Camisa Dry Fit Masculina FitWear.png', 'CAMISA', '2025-07-11 11:53:58.285228');
INSERT INTO public.products VALUES (1137, 'FlexiGear', 'ROUPAS', '2025-07-09 14:22:11.025007', 'O produto Camisa FlexiGear da marca FlexiGear foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Camisa Dry Fit Masculina FlexiGear', 68.33, 'ROU-CAM-009', '1752245651391_Camisa Dry Fit Masculina FlexiGear.png', 'CAMISA', '2025-07-11 11:54:12.003862');
INSERT INTO public.products VALUES (1140, 'FitWear', 'ROUPAS', '2025-07-09 14:23:11.304586', 'O produto Shorts FitWear da marca FitWear foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Shorts de Treino Masculino FitWear', 70.83, 'ROU-SHO-004', '1752245708604_Shorts de Treino Masculino FitWear.png', 'SHORT', '2025-07-11 11:55:08.82413');
INSERT INTO public.products VALUES (1204, 'TrainWell', 'ACESSORIOS', '2025-07-09 19:05:35.158017', 'O produto Par de Caneleiras 2kg da marca TrainWell foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Par de Caneleiras 2kg', 70.80, 'ACE-CAN-027', '1752245720171_Par de Caneleiras 2kg TrainWell.png', 'CANELEIRA', '2025-07-11 11:55:20.531817');
INSERT INTO public.products VALUES (1142, 'Athletix', 'ROUPAS', '2025-07-09 14:23:36.342053', 'O produto Shorts Athletix da marca Athletix foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Shorts de Treino Masculino Athletix', 69.34, 'ROU-SHO-006', '1752245733894_Shorts de Treino Masculino Athletix.png', 'SHORT', '2025-07-11 11:55:34.556967');
INSERT INTO public.products VALUES (1205, 'GripForce', 'ACESSORIOS', '2025-07-09 19:05:40.974296', 'O produto Par de Caneleiras 4kg da marca GripForce foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Par de Caneleiras 4kg', 98.00, 'ACE-CAN-028', '1752245768589_Par de Caneleiras 4kg GripForce.png', 'CANELEIRA', '2025-07-11 11:56:08.836953');
INSERT INTO public.products VALUES (1143, 'MovePro', 'ROUPAS', '2025-07-09 14:23:48.824448', 'O produto Shorts MovePro da marca MovePro foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Shorts de Treino Masculino MovePro', 54.02, 'ROU-SHO-007', '1752245744957_Shorts de Treino Masculino MovePro.png', 'SHORT', '2025-07-11 11:55:45.337354');
INSERT INTO public.products VALUES (1147, 'Athletix', 'ROUPAS', '2025-07-09 14:24:57.53355', 'O produto Legging Athletix da marca Athletix foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Legging de Compressão com Cintura Alta Athletix', 139.77, 'ROU-LEG-005', '1752245789985_Legging de Compressão com Cintura Alta Athletix.png', 'LEGGING', '2025-07-11 11:56:30.355296');
INSERT INTO public.products VALUES (1150, 'MovePro', 'ROUPAS', '2025-07-09 14:25:17.441302', 'O produto Legging MovePro da marca MovePro foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Legging de Compressão com Cintura Alta MovePro', 149.77, 'ROU-LEG-007', '1752245811756_Legging de Compressão com Cintura Alta MovePro.png', 'LEGGING', '2025-07-11 11:56:52.026476');
INSERT INTO public.products VALUES (1206, 'BodyTools', 'ACESSORIOS', '2025-07-09 19:05:46.763766', 'O produto Par de Caneleiras 8kg da marca BodyTools foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Par de Caneleiras 8kg', 145.60, 'ACE-CAN-029', '1752245778803_Par de Caneleiras 8kg BodyTools.png', 'CANELEIRA', '2025-07-11 11:56:19.123068');
INSERT INTO public.products VALUES (1208, 'MovePlus', 'ACESSORIOS', '2025-07-09 19:06:01.777533', 'O produto Par de Caneleiras 10kg da marca MovePlus foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Par de Caneleiras 10kg', 170.00, 'ACE-CAN-030', '1752245849284_Par de Caneleiras 10kg da marca MovePlus.png', 'CANELEIRA', '2025-07-11 11:57:29.706186');
INSERT INTO public.products VALUES (1151, 'FitWear', 'ROUPAS', '2025-07-09 14:25:27.754784', 'O produto Legging FitWear da marca FitWear foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Legging de Compressão com Cintura Alta FitWear', 113.88, 'ROU-LEG-008', '1752245823787_Legging de Compressão com Cintura Alta FitWear.png', 'LEGGING', '2025-07-11 11:57:04.125021');
INSERT INTO public.products VALUES (1152, 'FlexiGear', 'ROUPAS', '2025-07-09 14:25:41.292165', 'O produto Legging FlexiGear da marca FlexiGear foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Legging de Compressão com Cintura Alta FlexiGear', 128.53, 'ROU-LEG-009', '1752245832799_Legging de Compressão com Cintura Alta FlexiGear.png', 'LEGGING', '2025-07-11 11:57:13.307191');
INSERT INTO public.products VALUES (1203, 'GymEdge', 'ACESSORIOS', '2025-07-09 19:05:28.830424', 'O produto Par de Caneleiras 1kg da marca GymEdge foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Par de Caneleiras 1kg', 55.90, 'ACE-CAN-026', '1752245696114_Par de Caneleiras 1kg GymEdge.png', 'CANELEIRA', '2025-07-11 11:54:56.333482');
INSERT INTO public.products VALUES (1209, 'GymEdge', 'ACESSORIOS', '2025-07-09 19:06:13.660813', 'O produto Kit Caneleiras (1kg a 5kg) da marca GymEdge foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Kit Caneleiras (1kg a 5kg)', 350.00, 'ACE-CAN-031', '1752245864070_Kit Caneleiras (1kg a 5kg) da marca GymEdge.png', 'CANELEIRA', '2025-07-11 11:57:44.63874');
INSERT INTO public.products VALUES (1365, 'GripForce', 'ACESSORIOS', '2025-07-09 19:46:41.421766', 'O Kit de Bolas de Fisioterapia da marca GripForce contém 3 bolas com diferentes densidades para massagem e liberação miofascial em diversas áreas do corpo.', 'Kit de Bolas de Fisioterapia (3 densidades)', 99.90, 'ACE-BOL-012', '1752245883779_Kit de Bolas de Fisioterapia GripForce.png', 'BOLA', '2025-07-11 11:58:04.259255');
INSERT INTO public.products VALUES (1134, 'GymApparel', 'ROUPAS', '2025-07-09 14:21:50.415242', 'O produto Camisa GymApparel da marca GymApparel foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Camisa Dry Fit Masculina GymApparel', 87.70, 'ROU-CAM-005', '1752245615680_Camisa Dry Fit Masculina GymApparel.png', 'CAMISA', '2025-07-11 11:53:36.068234');
INSERT INTO public.products VALUES (1001, 'NutriLab', 'SUPLEMENTOS', '2025-07-03 19:15:59.009364', 'Whey Protein da marca NutriLab com 500g. Ideal para treinos intensos.', 'Whey Protein 500g NutriLab', 142.94, 'SUP-WHE-001', '1752063537073_Whey Protein 500g NutriLab.png', 'WHEY', '2025-07-09 09:18:58.657198');
INSERT INTO public.products VALUES (1214, 'BodyTools', 'ACESSORIOS', '2025-07-09 19:06:48.590651', 'O produto Corda de Pular com Contador Digital da marca BodyTools foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Corda de Pular com Contador Digital', 65.00, 'ACE-COR-025', '1752246188721_Corda de Pular com Contador Digital BodyTools.jpg', 'CORDA', '2025-07-11 12:03:08.916238');
INSERT INTO public.products VALUES (1215, 'MovePlus', 'ACESSORIOS', '2025-07-09 19:06:58.602562', 'O produto Corda de Pular de Couro da marca MovePlus foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Corda de Pular de Couro', 55.75, 'ACE-COR-026', '1752246211039_Corda de Pular de Couro MovePlus.jpg', 'CORDA', '2025-07-11 12:03:31.211978');
INSERT INTO public.products VALUES (1216, 'GymEdge', 'ACESSORIOS', '2025-07-09 19:07:05.569071', 'O produto Corda Naval (Rope Training) da marca GymEdge foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Corda Naval (Rope Training)', 250.00, 'ACE-COR-027', '1752246224307_Corda Naval (Rope Training) GymEdge.jpg', 'CORDA', '2025-07-11 12:03:44.965121');
INSERT INTO public.products VALUES (1218, 'GripForce', 'ACESSORIOS', '2025-07-09 19:07:18.074332', 'O produto Corda de Pular com Peso nas Manoplas da marca GripForce foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Corda de Pular com Peso nas Manoplas', 75.50, 'ACE-COR-029', '1752246244049_Corda de Pular com Peso nas Manoplas GripForce.jpg', 'CORDA', '2025-07-11 12:04:04.325171');
INSERT INTO public.products VALUES (1221, 'GymEdge', 'ACESSORIOS', '2025-07-09 19:07:59.623351', 'O produto Kit Corda de Pular + Elástico Extensor da marca GymEdge foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Kit Corda de Pular + Elástico Extensor', 99.99, 'ACE-COR-032', '1752246279009_Kit Corda de Pular + Elástico Extensor GymEdge.jpg', 'CORDA', '2025-07-11 12:04:39.139006');
INSERT INTO public.products VALUES (1222, 'TrainWell', 'ACESSORIOS', '2025-07-09 19:08:10.469027', 'O produto Corda de Pular de Alta Velocidade Crossfit da marca TrainWell foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Corda de Pular de Alta Velocidade Crossfit', 79.00, 'ACE-COR-033', '1752246294248_Corda de Pular de Alta Velocidade Crossfit TrainWell.jpg', 'CORDA', '2025-07-11 12:04:54.436975');
INSERT INTO public.products VALUES (1231, 'MovePlus', 'ACESSORIOS', '2025-07-09 19:19:44.966696', 'O produto Colchonete Aglomerado de Espuma D80 da marca MovePlus foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Colchonete Aglomerado de Espuma D80', 115.75, 'ACE-COL-032', '1752246425975_Colchonete Aglomerado de Espuma D80 MovePlus.jpg', 'COLCHONETE', '2025-07-11 12:07:06.421827');
INSERT INTO public.products VALUES (1236, 'GymEdge', 'ACESSORIOS', '2025-07-09 19:20:34.615783', 'O produto Puxador Reto para Tríceps com Giro da marca GymEdge foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Puxador Reto para Tríceps com Giro', 115.00, 'ACE-PUX-024', '1752246467910_Puxador Reto para Tríceps com Giro GymEdge.jpg', 'PUXADOR', '2025-07-11 12:07:48.244893');
INSERT INTO public.products VALUES (1239, 'GripForce', 'ACESSORIOS', '2025-07-09 19:20:59.422491', 'O produto Puxador Estribo com Pegada Emborrachada da marca GripForce foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Puxador Estribo com Pegada Emborrachada', 75.00, 'ACE-PUX-027', '1752252852838_Puxador Estribo com Pegada Emborrachada GripForce.jpg', 'PUXADOR', '2025-07-11 13:54:13.426734');
INSERT INTO public.products VALUES (1132, 'XtremeBlend', 'SUPLEMENTOS', '2025-07-09 14:21:35.102024', 'O produto Creatina XtremeBlend da marca XtremeBlend foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Creatina Monohidratada 500g XtremeBlend', 149.77, 'SUP-CRE-008', '1752245909276_Creatina Monohidratada 500g XtremeBlend.png', 'CREATINA', '2025-07-11 11:58:29.780938');
INSERT INTO public.products VALUES (1212, 'GripForce', 'ACESSORIOS', '2025-07-09 19:06:29.078482', 'O produto Caneleira de Peso Unidade 7kg da marca GripForce foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Caneleira de Peso Unidade 7kg', 130.40, 'ACE-CAN-033', '1752245932815_Caneleira de Peso Unidade 7kg GripForce.jpg', 'CANELEIRA', '2025-07-11 11:58:52.974957');
INSERT INTO public.products VALUES (1213, 'GripForce', 'ACESSORIOS', '2025-07-09 19:06:36.12024', 'O produto Corda de Aço com Revestimento PVC da marca GripForce foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Corda de Aço com Revestimento PVC', 45.90, 'ACE-COR-024', '1752245944069_Corda de Aço com Revestimento PVC GripForce.jpg', 'CORDA', '2025-07-11 11:59:04.362891');
INSERT INTO public.products VALUES (1217, 'TrainWell', 'ACESSORIOS', '2025-07-09 19:07:11.485466', 'O produto Corda de Pular Speed Rope Alumínio da marca TrainWell foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Corda de Pular Speed Rope Alumínio', 89.90, 'ACE-COR-028', '1752246234051_Corda de Pular Speed Rope Alumínio TrainWell.jpg', 'CORDA', '2025-07-11 12:03:54.262699');
INSERT INTO public.products VALUES (1251, 'GripForce', 'ACESSORIOS', '2025-07-09 19:22:39.921734', 'O produto Suporte para Flexão de Braço da marca GripForce foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Suporte para Flexão de Braço', 79.90, 'ACE-SUP-029', '1752246555215_Suporte para Flexão de Braço GripForce.jpg', 'SUPORTE', '2025-07-11 12:09:16.181489');
INSERT INTO public.products VALUES (1252, 'BodyTools', 'ACESSORIOS', '2025-07-09 19:22:49.879372', 'O produto Suporte para Abdominal (Prancha) da marca BodyTools foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Suporte para Abdominal (Prancha)', 95.00, 'ACE-SUP-030', '1752252883670_Suporte para Abdominal (Prancha) BodyTools.jpg', 'SUPORTE', '2025-07-11 13:54:44.057483');
INSERT INTO public.products VALUES (1253, 'TrainWell', 'ACESSORIOS', '2025-07-09 19:22:56.541448', 'O produto Banco Regulável para Musculação da marca TrainWell foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Banco Regulável para Musculação', 750.80, 'ACE-SUP-031', '1752252906734_Banco Regulável para Musculação TrainWell.jpg', 'SUPORTE', '2025-07-11 13:55:06.962438');
INSERT INTO public.products VALUES (1254, 'MovePlus', 'ACESSORIOS', '2025-07-09 19:23:02.890189', 'O produto Suporte para Tríceps Mergulho (Paralelas) da marca MovePlus foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Suporte para Tríceps Mergulho (Paralelas)', 450.00, 'ACE-SUP-032', '1752252919839_Suporte para Tríceps Mergulho (Paralelas) MovePlus.jpg', 'SUPORTE', '2025-07-11 13:55:20.280735');
INSERT INTO public.products VALUES (1255, 'GymEdge', 'ACESSORIOS', '2025-07-09 19:23:08.90071', 'O produto Suporte Organizador de Acessórios da marca GymEdge foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Suporte Organizador de Acessórios', 250.60, 'ACE-SUP-033', '1752252932576_Suporte Organizador de Acessórios GymEdge.jpg', 'SUPORTE', '2025-07-11 13:55:32.916146');
INSERT INTO public.products VALUES (1256, 'LiftPro', 'PESOS', '2025-07-09 19:23:15.853521', 'O produto Anilha Emborrachada 1kg da marca LiftPro foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Anilha Emborrachada 1kg', 25.50, 'PES-ANI-024', '1752252944071_Anilha Emborrachada 1kg LiftPro.jpg', 'ANILHA', '2025-07-11 13:55:44.315654');
INSERT INTO public.products VALUES (1257, 'SteelPump', 'PESOS', '2025-07-09 19:23:22.504547', 'O produto Anilha Emborrachada 2kg da marca SteelPump foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Anilha Emborrachada 2kg', 45.00, 'PES-ANI-025', '1752252956588_Anilha Emborrachada 2kg SteelPump.jpg', 'ANILHA', '2025-07-11 13:55:57.053512');
INSERT INTO public.products VALUES (1258, 'MassForge', 'PESOS', '2025-07-09 19:23:29.058221', 'O produto Anilha Emborrachada 3kg da marca MassForge foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Anilha Emborrachada 3kg', 60.80, 'PES-ANI-026', '1752252971134_Anilha Emborrachada 3kg MassForge.jpg', 'ANILHA', '2025-07-11 13:56:11.43357');
INSERT INTO public.products VALUES (1259, 'TitanIron', 'PESOS', '2025-07-09 19:23:36.31763', 'O produto Anilha Emborrachada 5kg da marca TitanIron foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Anilha Emborrachada 5kg', 90.00, 'PES-ANI-027', '1752252982770_Anilha Emborrachada 5kg TitanIron.jpg', 'ANILHA', '2025-07-11 13:56:23.134519');
INSERT INTO public.products VALUES (1260, 'IronBeast', 'PESOS', '2025-07-09 19:24:02.376677', 'O produto Anilha Emborrachada 15kg da marca IronBeast foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Anilha Emborrachada 15kg', 165.70, 'PES-ANI-028', '1752252999495_Anilha Emborrachada 15kg IronBeast.jpg', 'ANILHA', '2025-07-11 13:56:39.911505');
INSERT INTO public.products VALUES (1262, 'SteelPump', 'PESOS', '2025-07-09 19:24:26.571013', 'O produto Anilha Emborrachada 25kg da marca SteelPump foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Anilha Emborrachada 25kg', 260.50, 'PES-ANI-030', '1752254970631_Anilha Emborrachada 25kg SteelPump.jpg', 'ANILHA', '2025-07-11 14:29:30.737214');
INSERT INTO public.products VALUES (1264, 'TitanIron', 'PESOS', '2025-07-09 19:24:45.877196', 'O produto Anilha Fracionada 0.5kg da marca TitanIron foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Anilha Fracionada 0.5kg', 18.90, 'PES-ANI-032', '1752253053549_Anilha Fracionada 0.5kg TitanIron.jpg', 'ANILHA', '2025-07-11 13:57:33.869086');
INSERT INTO public.products VALUES (1265, 'IronBeast', 'PESOS', '2025-07-09 19:24:53.324421', 'O produto Kit de Anilhas Emborrachadas (22kg Total) da marca IronBeast foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Kit de Anilhas Emborrachadas (22kg Total)', 250.00, 'PES-ANI-033', '1752253065854_Kit de Anilhas Emborrachadas (22kg Total) IronBeast.jpg', 'ANILHA', '2025-07-11 13:57:46.00777');
INSERT INTO public.products VALUES (1266, 'MassForge', 'PESOS', '2025-07-09 19:25:00.307196', 'O produto Par de Halteres Sextavados 5kg da marca MassForge foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Par de Halteres Sextavados 5kg', 180.00, 'PES-HAL-024', '1752253111750_Par de Halteres Sextavados 5kg MassForge.jpg', 'HALTER', '2025-07-11 13:58:32.072153');
INSERT INTO public.products VALUES (1267, 'SteelPump', 'PESOS', '2025-07-09 19:25:07.032699', 'O produto Par de Halteres Sextavados 10kg da marca SteelPump foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Par de Halteres Sextavados 10kg', 350.60, 'PES-HAL-025', '1752253125639_Par de Halteres Sextavados 10kg SteelPump.jpg', 'HALTER', '2025-07-11 13:58:45.850466');
INSERT INTO public.products VALUES (1269, 'LiftPro', 'PESOS', '2025-07-09 19:25:18.875631', 'O produto Halter Emborrachado Unidade 1kg da marca LiftPro foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Halter Emborrachado Unidade 1kg', 30.00, 'PES-HAL-026', '1752253140625_Halter Emborrachado Unidade 1kg LiftPro.jpg', 'HALTER', '2025-07-11 13:59:00.840292');
INSERT INTO public.products VALUES (1263, 'MassForge', 'PESOS', '2025-07-09 19:24:33.345938', 'O produto Anilha Olímpica Bumper 10kg da marca MassForge foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Anilha Olímpica Bumper 10kg', 180.00, 'PES-ANI-031', '1752253043167_Anilha Olímpica Bumper 10kg MassForge.jpg', 'ANILHA', '2025-07-11 13:57:23.582967');
INSERT INTO public.products VALUES (1250, 'GymEdge', 'ACESSORIOS', '2025-07-09 19:22:29.869954', 'O produto Suporte para Kettlebells da marca GymEdge foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Suporte para Kettlebells', 600.70, 'ACE-SUP-028', '1752246540985_Suporte para Kettlebells GymEdge.jpg', 'SUPORTE', '2025-07-11 12:09:01.157677');
INSERT INTO public.products VALUES (1277, 'SteelPump', 'PESOS', '2025-07-09 19:26:32.201985', 'O produto Kettlebell de Ferro Fundido 4kg da marca SteelPump foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Kettlebell de Ferro Fundido 4kg', 85.00, 'PES-KET-024', '1752253234231_Kettlebell de Ferro Fundido 4kg SteelPump.jpg', 'KETTLEBELL', '2025-07-11 14:00:34.594852');
INSERT INTO public.products VALUES (1278, 'LiftPro', 'PESOS', '2025-07-09 19:26:40.389934', 'O produto Kettlebell de Ferro Fundido 8kg da marca LiftPro foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Kettlebell de Ferro Fundido 8kg', 150.80, 'PES-KET-025', '1752253246825_Kettlebell de Ferro Fundido 8kg LiftPro.jpg', 'KETTLEBELL', '2025-07-11 14:00:47.127387');
INSERT INTO public.products VALUES (1279, 'IronBeast', 'PESOS', '2025-07-09 19:26:48.312955', 'O produto Kettlebell de Ferro Fundido 12kg da marca IronBeast foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Kettlebell de Ferro Fundido 12kg', 210.60, 'PES-KET-026', '1752253265446_Kettlebell de Ferro Fundido 12kg IronBeast.jpg', 'KETTLEBELL', '2025-07-11 14:01:05.688016');
INSERT INTO public.products VALUES (1280, 'TitanIron', 'PESOS', '2025-07-09 19:26:55.598584', 'O produto Kettlebell de Ferro Fundido 16kg da marca TitanIron foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Kettlebell de Ferro Fundido 16kg', 270.00, 'PES-KET-027', '1752253282068_Kettlebell de Ferro Fundido 16kg TitanIron.jpg', 'KETTLEBELL', '2025-07-11 14:01:22.463844');
INSERT INTO public.products VALUES (1281, 'MassForge', 'PESOS', '2025-07-09 19:27:02.475144', 'O produto Kettlebell de Ferro Fundido 20kg da marca MassForge foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Kettlebell de Ferro Fundido 20kg', 330.90, 'PES-KET-028', '1752253300668_Kettlebell de Ferro Fundido 20kg MassForge.jpg', 'KETTLEBELL', '2025-07-11 14:01:40.915604');
INSERT INTO public.products VALUES (1283, 'LiftPro', 'PESOS', '2025-07-09 19:27:19.156064', 'O produto Kettlebell Emborrachado 6kg da marca LiftPro foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Kettlebell Emborrachado 6kg', 135.50, 'PES-KET-030', '1752253338325_Kettlebell Emborrachado 6kg LiftPro.jpg', 'KETTLEBELL', '2025-07-11 14:02:18.626853');
INSERT INTO public.products VALUES (1284, 'IronBeast', 'PESOS', '2025-07-09 19:27:26.64491', 'O produto Kettlebell de Vinil 5kg da marca IronBeast foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Kettlebell de Vinil 5kg', 95.70, 'PES-KET-031', '1752253353339_Kettlebell de Vinil 5kg IronBeast.jpg', 'KETTLEBELL', '2025-07-11 14:02:33.883405');
INSERT INTO public.products VALUES (1285, 'TitanIron', 'PESOS', '2025-07-09 19:27:37.26233', 'O produto Kit Kettlebells de Ferro (4, 8, 12 kg) da marca TitanIron foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Kit Kettlebells de Ferro (4, 8, 12 kg)', 430.00, 'PES-KET-032', '1752253364990_Kit Kettlebells de Ferro (4, 8, 12 kg) TitanIron.jpg', 'KETTLEBELL', '2025-07-11 14:02:45.282935');
INSERT INTO public.products VALUES (1286, 'MassForge', 'PESOS', '2025-07-09 19:27:44.877566', 'O produto Kettlebell Ajustável 3 a 18kg da marca MassForge foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Kettlebell Ajustável 3 a 18kg', 550.00, 'PES-KET-033', '1752253376684_Kettlebell Ajustável 3 a 18kg MassForge.jpg', 'KETTLEBELL', '2025-07-11 14:02:56.985486');
INSERT INTO public.products VALUES (1287, 'LiftPro', 'PESOS', '2025-07-09 19:28:30.142142', 'O produto Presilha de Mola para Barra Padrão (Par) da marca LiftPro foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Presilha de Mola para Barra Padrão (Par)', 25.00, 'PES-PRE-024', '1752253392032_Presilha de Mola para Barra Padrão (Par) LiftPro.jpg', 'PRESILHA', '2025-07-11 14:03:12.28066');
INSERT INTO public.products VALUES (1288, 'MassForge', 'PESOS', '2025-07-09 19:28:37.549525', 'O produto Presilha Lock-Jaw para Barra Olímpica (Par) da marca MassForge foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Presilha Lock-Jaw para Barra Olímpica (Par)', 85.90, 'PES-PRE-025', '1752253404247_Presilha Lock-Jaw para Barra Olímpica (Par) MassForge.jpg', 'PRESILHA', '2025-07-11 14:03:24.541005');
INSERT INTO public.products VALUES (1290, 'SteelPump', 'PESOS', '2025-07-09 19:28:52.543302', 'O produto Presilha de Pressão para Barra (Par) da marca SteelPump foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Presilha de Pressão para Barra (Par)', 29.90, 'PES-PRE-027', '1752253433006_Presilha de Pressão para Barra (Par) SteelPump.jpg', 'PRESILHA', '2025-07-11 14:03:53.423909');
INSERT INTO public.products VALUES (1293, 'MassForge', 'PESOS', '2025-07-09 19:29:18.021997', 'O produto Presilha de Plástico ABS Rígido (Par) da marca MassForge foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Presilha de Plástico ABS Rígido (Par)', 65.00, 'PES-PRE-030', '1752253492043_Presilha de Plástico ABS Rígido (Par) MassForge.jpg', 'PRESILHA', '2025-07-11 14:04:52.265381');
INSERT INTO public.products VALUES (1274, 'LiftPro', 'PESOS', '2025-07-09 19:26:03.36658', 'O produto Halter de Neoprene 2kg da marca LiftPro foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Halter de Neoprene 2kg', 45.90, 'PES-HAL-031', '1752253193923_Halter de Neoprene 2kg LiftPro.jpg', 'HALTER', '2025-07-11 13:59:54.404097');
INSERT INTO public.products VALUES (1272, 'MassForge', 'PESOS', '2025-07-09 19:25:45.146598', 'O produto Kit Halteres Emborrachados (1 a 5 kg) da marca MassForge foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Kit Halteres Emborrachados (1 a 5 kg)', 550.00, 'PES-HAL-029', '1752254958811_Kit Halteres Emborrachados (1 a 5 kg) MassForge.jpg', 'HALTER', '2025-07-11 14:29:18.927991');
INSERT INTO public.products VALUES (1276, 'TitanIron', 'PESOS', '2025-07-09 19:26:22.489866', 'O produto Halter Bola (Ball) 6kg da marca TitanIron foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Halter Bola (Ball) 6kg', 110.00, 'PES-HAL-033', '1752253221369_Halter Bola (Ball) 6kg TitanIron.jpg', 'HALTER', '2025-07-11 14:00:23.132471');
INSERT INTO public.products VALUES (1296, 'IronBeast', 'PESOS', '2025-07-09 19:29:43.168439', 'O produto Kit com 4 Presilhas de Mola da marca IronBeast foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Kit com 4 Presilhas de Mola', 45.90, 'PES-PRE-033', '1752253583885_Kit com 4 Presilhas de Mola IronBeast.jpg', 'PRESILHA', '2025-07-11 14:06:24.12482');
INSERT INTO public.products VALUES (1302, 'NutriLab', 'SUPLEMENTOS', '2025-07-09 19:34:33.134954', 'O produto Pré-Treino Sem Cafeína (Pump) da marca NutriLab foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Pré-Treino Sem Cafeína (Pump)', 155.40, 'SUP-PRE-029', '1752253681376_Pré-Treino Sem Cafeína (Pump) NutriLab.jpg', 'PRE_TREINO', '2025-07-11 14:08:01.668272');
INSERT INTO public.products VALUES (1303, 'PowerFuel', 'SUPLEMENTOS', '2025-07-09 19:34:45.635537', 'O produto Pré-Treino com Palatinose da marca PowerFuel foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Pré-Treino com Palatinose', 148.50, 'SUP-PRE-030', '1752253695239_Pré-Treino com Palatinose PowerFuel.jpg', 'PRE_TREINO', '2025-07-11 14:08:15.712603');
INSERT INTO public.products VALUES (1304, 'VitaCore', 'SUPLEMENTOS', '2025-07-09 19:34:53.890525', 'O produto Pré-Treino D-Vaster da marca VitaCore foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Pré-Treino D-Vaster', 159.00, 'SUP-PRE-031', '1752253709484_Pré-Treino D-Vaster VitaCore.jpg', 'PRE_TREINO', '2025-07-11 14:08:29.721419');
INSERT INTO public.products VALUES (1307, 'StrongSupps', 'SUPLEMENTOS', '2025-07-09 19:35:26.336636', 'O produto Whey Protein Hidrolisado 900g da marca StrongSupps foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Whey Protein Hidrolisado 900g', 350.50, 'SUP-WHE-025', '1752253751882_Whey Protein Hidrolisado 900g StrongSupps.jpg', 'WHEY', '2025-07-11 14:09:12.127049');
INSERT INTO public.products VALUES (1309, 'XtremeBlend', 'SUPLEMENTOS', '2025-07-09 19:35:37.225736', 'O produto Whey Protein 3W (Concentrado, Isolado, Hidrolisado) da marca XtremeBlend foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Whey Protein 3W (Concentrado, Isolado, Hidrolisado)', 240.80, 'SUP-WHE-026', '1752253766608_Whey Protein 3W (Concentrado, Isolado, Hidrolisado) XtremeBlend.jpg', 'WHEY', '2025-07-11 14:09:27.641889');
INSERT INTO public.products VALUES (1305, 'XtremeBlend', 'SUPLEMENTOS', '2025-07-09 19:35:09.896093', 'O produto Shot Pré-Treino Líquido da marca XtremeBlend foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Shot Pré-Treino Líquido', 15.00, 'SUP-PRE-033', '1752253722781_Shot Pré-Treino Líquido XtremeBlend.jpg', 'PRE_TREINO', '2025-07-11 14:08:43.679955');
INSERT INTO public.products VALUES (1306, 'VitaCore', 'SUPLEMENTOS', '2025-07-09 19:35:17.842011', 'O produto Whey Protein Isolado 900g da marca VitaCore foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Whey Protein Isolado 900g', 280.00, 'SUP-WHE-024', '1752253735028_Whey Protein Isolado 900g VitaCore.jpg', 'WHEY', '2025-07-11 14:08:55.196104');
INSERT INTO public.products VALUES (1294, 'TitanIron', 'PESOS', '2025-07-09 19:29:24.83563', 'O produto Presilha Magnética para Halteres (Par) da marca TitanIron foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Presilha Magnética para Halteres (Par)', 99.80, 'PES-PRE-031', '1752253505270_Presilha Magnética para Halteres (Par) TitanIron.jpg', 'PRESILHA', '2025-07-11 14:05:05.440109');
INSERT INTO public.products VALUES (1297, 'NutriLab', 'SUPLEMENTOS', '2025-07-09 19:33:41.917074', 'O produto Pré-Treino C4 Original da marca NutriLab foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Pré-Treino C4 Original', 185.00, 'SUP-PRE-024', '1752253596714_Pré-Treino C4 Original NutriLab.jpg', 'PRE_TREINO', '2025-07-11 14:06:37.940399');
INSERT INTO public.products VALUES (1298, 'PowerFuel', 'SUPLEMENTOS', '2025-07-09 19:33:52.990088', 'O produto Pré-Treino Évora PW da marca PowerFuel foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Pré-Treino Évora PW', 135.90, 'SUP-PRE-025', '1752253609684_Pré-Treino Évora PW PowerFuel.jpg', 'PRE_TREINO', '2025-07-11 14:06:49.903436');
INSERT INTO public.products VALUES (1299, 'VitaCore', 'SUPLEMENTOS', '2025-07-09 19:34:07.833257', 'O produto Pré-Treino Hórus da marca VitaCore foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Pré-Treino Hórus', 142.80, 'SUP-PRE-026', '1752253648794_Pré-Treino Hórus VitaCore.jpg', 'PRE_TREINO', '2025-07-11 14:07:29.392769');
INSERT INTO public.products VALUES (1301, 'XtremeBlend', 'SUPLEMENTOS', '2025-07-09 19:34:24.575573', 'O produto Pré-Treino Insane Clown da marca XtremeBlend foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Pré-Treino Insane Clown', 179.90, 'SUP-PRE-028', '1752253665275_Pré-Treino Insane Clown XtremeBlend.jpg', 'PRE_TREINO', '2025-07-11 14:07:45.697778');
INSERT INTO public.products VALUES (1312, 'VitaCore', 'SUPLEMENTOS', '2025-07-09 19:36:01.765059', 'O produto Whey Protein Baunilha 1.8kg da marca VitaCore foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Whey Protein Baunilha 1.8kg', 310.00, 'SUP-WHE-029', '1752253813977_Whey Protein Baunilha 1.8kg VitaCore.jpg', 'WHEY', '2025-07-11 14:10:14.228573');
INSERT INTO public.products VALUES (1313, 'StrongSupps', 'SUPLEMENTOS', '2025-07-09 19:36:09.50036', 'O produto Whey Protein Morango 900g da marca StrongSupps foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Whey Protein Morango 900g', 169.50, 'SUP-WHE-030', '1752253827287_Whey Protein Morango 900g StrongSupps.jpg', 'WHEY', '2025-07-11 14:10:28.259971');
INSERT INTO public.products VALUES (1314, 'XtremeBlend', 'SUPLEMENTOS', '2025-07-09 19:36:21.328146', 'O produto Whey Protein sem Lactose da marca XtremeBlend foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Whey Protein sem Lactose', 295.00, 'SUP-WHE-031', '1752253841342_Whey Protein sem Lactose XtremeBlend.jpg', 'WHEY', '2025-07-11 14:10:41.517465');
INSERT INTO public.products VALUES (1315, 'NutriLab', 'SUPLEMENTOS', '2025-07-09 19:36:29.80379', 'O produto Hipercalórico com Whey Protein da marca NutriLab foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Hipercalórico com Whey Protein', 150.75, 'SUP-WHE-032', '1752253854133_Hipercalórico com Whey Protein NutriLab.jpg', 'WHEY', '2025-07-11 14:10:54.349489');
INSERT INTO public.products VALUES (1316, 'PowerFuel', 'SUPLEMENTOS', '2025-07-09 19:36:36.688988', 'O produto Whey Protein em Sachê Dose Única da marca PowerFuel foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Whey Protein em Sachê Dose Única', 12.00, 'SUP-WHE-033', '1752253867615_Whey Protein em Sachê Dose Única PowerFuel.jpg', 'WHEY', '2025-07-11 14:11:07.78246');
INSERT INTO public.products VALUES (1317, 'NutriLab', 'SUPLEMENTOS', '2025-07-09 19:36:45.838971', 'O produto Creatina Pura (Selo Creapure) 150g da marca NutriLab foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Creatina Pura (Selo Creapure) 150g', 110.00, 'SUP-CRE-024', '1752253883809_Creatina Pura (Selo Creapure) 150g NutriLab.jpg', 'CREATINA', '2025-07-11 14:11:24.083612');
INSERT INTO public.products VALUES (1318, 'PowerFuel', 'SUPLEMENTOS', '2025-07-09 19:36:53.475642', 'O produto Creatina Hardcore 300g da marca PowerFuel foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Creatina Hardcore 300g', 95.90, 'SUP-CRE-025', '1752253975425_Creatina Hardcore 300g PowerFuel.jpg', 'CREATINA', '2025-07-11 14:12:55.5929');
INSERT INTO public.products VALUES (1319, 'VitaCore', 'SUPLEMENTOS', '2025-07-09 19:37:00.276698', 'O produto Creatina em Cápsulas 120 un. da marca VitaCore foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Creatina em Cápsulas 120 un.', 85.80, 'SUP-CRE-026', '1752253988591_Creatina em Cápsulas 120 un. VitaCore.jpg', 'CREATINA', '2025-07-11 14:13:08.776818');
INSERT INTO public.products VALUES (1321, 'XtremeBlend', 'SUPLEMENTOS', '2025-07-09 19:37:17.030959', 'O produto Creatina HCL (Cloridrato) da marca XtremeBlend foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Creatina HCL (Cloridrato)', 145.00, 'SUP-CRE-028', '1752254013374_Creatina HCL (Cloridrato) XtremeBlend.jpg', 'CREATINA', '2025-07-11 14:13:33.661742');
INSERT INTO public.products VALUES (1322, 'NutriLab', 'SUPLEMENTOS', '2025-07-09 19:37:24.689482', 'O produto Combo Creatina + BCAA da marca NutriLab foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Combo Creatina + BCAA', 160.40, 'SUP-CRE-029', '1752254026788_Combo Creatina + BCAA NutriLab.jpg', 'CREATINA', '2025-07-11 14:13:47.091915');
INSERT INTO public.products VALUES (1323, 'PowerFuel', 'SUPLEMENTOS', '2025-07-09 19:37:33.122328', 'O produto Creatina Micronizada 150g da marca PowerFuel foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Creatina Micronizada 150g', 79.90, 'SUP-CRE-030', '1752254039843_Creatina Micronizada 150g PowerFuel.jpg', 'CREATINA', '2025-07-11 14:14:00.430812');
INSERT INTO public.products VALUES (1325, 'StrongSupps', 'SUPLEMENTOS', '2025-07-09 19:37:53.55522', 'O produto Creatina Pote Econômico 500g da marca StrongSupps foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Creatina Pote Econômico 500g', 189.90, 'SUP-CRE-032', '1752254068421_Creatina Pote Econômico 500g StrongSupps.jpg', 'CREATINA', '2025-07-11 14:14:29.234075');
INSERT INTO public.products VALUES (1324, 'VitaCore', 'SUPLEMENTOS', '2025-07-09 19:37:41.436604', 'O produto Creatina com Sabor Uva da marca VitaCore foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Creatina com Sabor Uva', 99.00, 'SUP-CRE-031', '1752254057721_Creatina com Sabor Uva VitaCore.jpg', 'CREATINA', '2025-07-11 14:14:18.172889');
INSERT INTO public.products VALUES (1326, 'XtremeBlend', 'SUPLEMENTOS', '2025-07-09 19:38:02.133529', 'O produto Creatina Líquida da marca XtremeBlend foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Creatina Líquida', 115.00, 'SUP-CRE-033', '1752254080959_Creatina Líquida XtremeBlend.jpg', 'CREATINA', '2025-07-11 14:14:41.205714');
INSERT INTO public.products VALUES (1327, 'MovePro', 'ROUPAS', '2025-07-09 19:38:08.655415', 'O produto Camisa de Compressão Manga Longa da marca MovePro foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Camisa de Compressão Manga Longa', 120.00, 'ROU-CAM-023', '1752254093228_Camisa de Compressão Manga Longa MovePro.jpg', 'CAMISA', '2025-07-11 14:14:53.48181');
INSERT INTO public.products VALUES (1329, 'Athletix', 'ROUPAS', '2025-07-09 19:38:24.447374', 'O produto Camisa Térmica Segunda Pele da marca Athletix foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Camisa Térmica Segunda Pele', 110.50, 'ROU-CAM-025', '1752254122172_Camisa Térmica Segunda Pele Athletix.jpg', 'CAMISA', '2025-07-11 14:15:22.453054');
INSERT INTO public.products VALUES (1330, 'FitWear', 'ROUPAS', '2025-07-09 19:38:31.160193', 'O produto Camisa Polo Dry Fit da marca FitWear foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Camisa Polo Dry Fit', 130.00, 'ROU-CAM-026', '1752254138080_Camisa Polo Dry Fit FitWear.jpg', 'CAMISA', '2025-07-11 14:15:38.453309');
INSERT INTO public.products VALUES (1310, 'NutriLab', 'SUPLEMENTOS', '2025-07-09 19:35:44.94084', 'O produto Whey Protein Vegano (Ervilha e Arroz) da marca NutriLab foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Whey Protein Vegano (Ervilha e Arroz)', 190.00, 'SUP-WHE-027', '1752253792609_Whey Protein Vegano (Ervilha e Arroz) NutriLab.jpg', 'WHEY', '2025-07-11 14:09:52.817233');
INSERT INTO public.products VALUES (1333, 'GymApparel', 'ROUPAS', '2025-07-09 19:38:55.55281', 'O produto Camisa Regata Machão da marca GymApparel foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Camisa Regata Machão', 75.80, 'ROU-CAM-029', '1752254205809_Camisa Regata Machão GymApparel.jpg', 'CAMISA', '2025-07-11 14:16:46.16637');
INSERT INTO public.products VALUES (1334, 'Athletix', 'ROUPAS', '2025-07-09 19:39:02.517227', 'O produto Camisa de Time de Basquete (Estilo) da marca Athletix foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Camisa de Time de Basquete (Estilo)', 150.00, 'ROU-CAM-030', '1752254219030_Camisa de Time de Basquete (Estilo) Athletix.jpg', 'CAMISA', '2025-07-11 14:17:00.400963');
INSERT INTO public.products VALUES (1335, 'FitWear', 'ROUPAS', '2025-07-09 19:39:13.20037', 'O produto Camisa Dry Fit com Zíper no Peito da marca FitWear foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Camisa Dry Fit com Zíper no Peito', 115.60, 'ROU-CAM-031', '1752254249418_Camisa Dry Fit com Zíper no Peito FitWear.jpg', 'CAMISA', '2025-07-11 14:17:29.562247');
INSERT INTO public.products VALUES (1338, 'FitWear', 'ROUPAS', '2025-07-09 19:40:20.618199', 'O produto Shorts de Moletom Confortável da marca FitWear foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Shorts de Moletom Confortável', 95.90, 'ROU-SHO-025', '1752254295732_Shorts de Moletom Confortável FitWear.jpg', 'SHORT', '2025-07-11 14:18:15.913557');
INSERT INTO public.products VALUES (1339, 'GymApparel', 'ROUPAS', '2025-07-09 19:40:32.963419', 'O produto Shorts 2 em 1 com Bermuda Térmica da marca GymApparel foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Shorts 2 em 1 com Bermuda Térmica', 130.60, 'ROU-SHO-026', '1752254307779_Shorts 2 em 1 com Bermuda Térmica GymApparel.jpg', 'SHORT', '2025-07-11 14:18:27.920594');
INSERT INTO public.products VALUES (1340, 'Athletix', 'ROUPAS', '2025-07-09 19:40:42.921264', 'O produto Shorts de Tactel Estampado da marca Athletix foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Shorts de Tactel Estampado', 79.90, 'ROU-SHO-027', '1752254318992_Shorts de Tactel Estampado Athletix.jpg', 'SHORT', '2025-07-11 14:18:39.171144');
INSERT INTO public.products VALUES (1341, 'MovePro', 'ROUPAS', '2025-07-09 19:40:54.454662', 'O produto Shorts de Basquete Longo da marca MovePro foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Shorts de Basquete Longo', 110.00, 'ROU-SHO-028', '1752254332909_Shorts de Basquete Longo MovePro.jpg', 'SHORT', '2025-07-11 14:18:53.033314');
INSERT INTO public.products VALUES (1342, 'FlexiGear', 'ROUPAS', '2025-07-09 19:41:05.586868', 'O produto Shorts de Ciclismo com Forro de Gel da marca FlexiGear foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Shorts de Ciclismo com Forro de Gel', 150.80, 'ROU-SHO-029', '1752254368633_Shorts de Ciclismo com Forro de Gel FlexiGear.jpg', 'SHORT', '2025-07-11 14:19:28.846186');
INSERT INTO public.products VALUES (1343, 'FitWear', 'ROUPAS', '2025-07-09 19:41:17.588778', 'O produto Shorts de Compressão da marca FitWear foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Shorts de Compressão', 99.50, 'ROU-SHO-030', '1752254391539_Shorts de CompressãoFitWear.jpg', 'SHORT', '2025-07-11 14:19:51.745321');
INSERT INTO public.products VALUES (1344, 'GymApparel', 'ROUPAS', '2025-07-09 19:41:27.152246', 'O produto Shorts de Praia/Treino Híbrido da marca GymApparel foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Shorts de Praia/Treino Híbrido', 125.00, 'ROU-SHO-031', '1752254410628_Shorts de Praia_Treino Híbrido GymApparel.jpg', 'SHORT', '2025-07-11 14:20:10.856676');
INSERT INTO public.products VALUES (1346, 'GymApparel', 'ROUPAS', '2025-07-09 19:42:02.866621', 'O produto Legging com Bolso Lateral para Celular da marca GymApparel foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Legging com Bolso Lateral para Celular', 155.00, 'ROU-LEG-024', '1752254450851_Legging com Bolso Lateral para Celular GymApparel.jpg', 'LEGGING', '2025-07-11 14:20:51.129082');
INSERT INTO public.products VALUES (1348, 'MovePro', 'ROUPAS', '2025-07-09 19:42:19.828512', 'O produto Legging com Efeito Push-Up da marca MovePro foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Legging com Efeito Push-Up', 160.80, 'ROU-LEG-026', '1752254466976_Legging com Efeito Push-Up MovePro.jpg', 'LEGGING', '2025-07-11 14:21:07.16668');
INSERT INTO public.products VALUES (1349, 'FitWear', 'ROUPAS', '2025-07-09 19:42:26.324346', 'O produto Legging de Cirrê Brilhante da marca FitWear foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Legging de Cirrê Brilhante', 130.00, 'ROU-LEG-027', '1752254485276_Legging de Cirrê Brilhante FitWear.jpg', 'LEGGING', '2025-07-11 14:21:25.427501');
INSERT INTO public.products VALUES (1352, 'Athletix', 'ROUPAS', '2025-07-09 19:42:52.128398', 'O produto Legging Capri (3/4) da marca Athletix foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Legging Capri (3/4)', 110.50, 'ROU-LEG-030', '1752254630811_Legging Capri (3_4) Athletix.jpg', 'LEGGING', '2025-07-11 14:23:51.013351');
INSERT INTO public.products VALUES (1353, 'MovePro', 'ROUPAS', '2025-07-09 19:42:59.998312', 'O produto Legging de Poliamida sem Costura da marca MovePro foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Legging de Poliamida sem Costura', 175.00, 'ROU-LEG-031', '1752254645798_Legging de Poliamida sem Costura MovePro.jpg', 'LEGGING', '2025-07-11 14:24:05.948707');
INSERT INTO public.products VALUES (1354, 'FitWear', 'ROUPAS', '2025-07-09 19:43:07.986984', 'O produto Legging Corsário (abaixo do joelho) da marca FitWear foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Legging Corsário (abaixo do joelho)', 99.90, 'ROU-LEG-032', '1752254660396_Legging Corsário (abaixo do joelho) FitWear.jpg', 'LEGGING', '2025-07-11 14:24:20.739197');
INSERT INTO public.products VALUES (1332, 'MovePro', 'ROUPAS', '2025-07-09 19:38:44.037575', 'O produto Camisa com Proteção UV 50+ da marca MovePro foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Camisa com Proteção UV 50+', 140.00, 'ROU-CAM-028', '1752254167646_Camisa com Proteção UV 50+ MovePro.jpg', 'CAMISA', '2025-07-11 14:16:07.924556');
INSERT INTO public.products VALUES (1337, 'FlexiGear', 'ROUPAS', '2025-07-09 19:40:12.336975', 'O produto Shorts de Corrida com Bolso Interno da marca FlexiGear foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Shorts de Corrida com Bolso Interno', 85.00, 'ROU-SHO-024', '1752254673567_Shorts de Corrida com Bolso InternoFlexiGear.jpg', 'SHORT', '2025-07-11 14:24:34.157268');
INSERT INTO public.products VALUES (1347, 'Athletix', 'ROUPAS', '2025-07-09 19:42:13.138195', 'O produto Legging Estampada Galáxia da marca Athletix foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Legging Estampada Galáxia', 145.90, 'ROU-LEG-025', '1752254687825_Legging Estampada Galáxia Athletix.jpg', 'LEGGING', '2025-07-11 14:24:48.231775');
INSERT INTO public.products VALUES (1356, 'BodyTools', 'ACESSORIOS', '2025-07-09 19:45:00.471495', 'A Bola de Pilates Suíça 65cm da marca BodyTools foi desenvolvida para oferecer versatilidade e segurança em seus treinos de pilates, ioga e funcional. Ideal para fortalecimento do core e melhora do equilíbrio.', 'Bola de Pilates Suíça 65cm', 89.90, 'ACE-BOL-003', '1752254701760_Bola de Pilates Suíça 65cm BodyTools.jpg', 'BOLA', '2025-07-11 14:25:01.93164');
INSERT INTO public.products VALUES (1363, 'GymEdge', 'ACESSORIOS', '2025-07-09 19:46:21.819588', 'A Bola de Reação da marca GymEdge é utilizada para treinar agilidade, tempo de reação e coordenação. Seus quiques imprevisíveis desafiam o atleta.', 'Bola de Reação (Reaction Ball)', 55.00, 'ACE-BOL-010', '1752254717929_Bola de Reação GymEdge.jpg', 'BOLA', '2025-07-11 14:25:18.332058');
INSERT INTO public.products VALUES (1364, 'TrainWell', 'ACESSORIOS', '2025-07-09 19:46:31.82389', 'A Bola Suíça para Escritório com Base da marca TrainWell ajuda a melhorar a postura enquanto trabalha. Acompanha base para estabilidade.', 'Bola Suíça para Escritório com Base', 150.00, 'ACE-BOL-011', '1752254855001_Bola Suíça para Escritório com Base TrainWell.jpg', 'BOLA', '2025-07-11 14:27:35.113537');
INSERT INTO public.products VALUES (1359, 'TrainWell', 'ACESSORIOS', '2025-07-09 19:45:29.64857', 'A Medicine Ball 5kg da marca TrainWell é uma ferramenta essencial para treinos de força e potência. Revestida em material resistente para maior durabilidade.', 'Medicine Ball 5kg', 180.00, 'ACE-BOL-006', '1752254894270_Medicine Ball 5kg TrainWell.jpg', 'BOLA', '2025-07-11 14:28:14.390202');
INSERT INTO public.products VALUES (1360, 'GripForce', 'ACESSORIOS', '2025-07-09 19:45:36.921715', 'A Slam Ball 10kg da marca GripForce foi projetada para arremessos de alto impacto, sem quicar. Ideal para treinos de Crossfit e condicionamento metabólico.', 'Slam Ball 10kg', 250.75, 'ACE-BOL-007', '1752254905895_Slam Ball 10kg GripForce.jpg', 'BOLA', '2025-07-11 14:28:26.03634');
INSERT INTO public.products VALUES (1361, 'BodyTools', 'ACESSORIOS', '2025-07-09 19:45:52.169074', 'A Wall Ball 8kg da marca BodyTools é perfeita para exercícios de arremesso na parede. Possui costuras reforçadas e toque macio para maior segurança.', 'Wall Ball 8kg', 210.00, 'ACE-BOL-008', '1752254916902_Wall Ball 8kg BodyTools.jpg', 'BOLA', '2025-07-11 14:28:37.134048');
INSERT INTO public.products VALUES (1362, 'MovePlus', 'ACESSORIOS', '2025-07-09 19:46:01.601899', 'A Mini Bola de Pilates (Soft Ball) 25cm da marca MovePlus é ótima para exercícios de fortalecimento e alongamento em aulas de pilates e ioga.', 'Mini Bola de Pilates (Soft Ball) 25cm', 35.90, 'ACE-BOL-009', '1752254930801_Mini Bola de Pilates (Soft Ball) 25cm MovePlus.jpg', 'BOLA', '2025-07-11 14:28:50.909097');
INSERT INTO public.products VALUES (1057, 'GymEdge', 'ACESSORIOS', '2025-07-09 14:00:40.43051', 'O produto Barra GymEdge da marca GymEdge foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Barra Reta de Musculação 1,20m GymEdge', 138.07, 'ACE-BAR-007', '1752242305122_Barra Reta de Musculação 1,20m GymEdge.png', 'BARRA', '2025-07-11 10:58:25.445323');
INSERT INTO public.products VALUES (1062, 'GripForce', 'ACESSORIOS', '2025-07-09 14:01:46.657991', 'O produto Caneleira GripForce da marca GripForce foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Caneleira com Peso Regulável GripForce', 58.74, 'ACE-CAN-009', '1752242428383_Caneleira com Peso Regulável GripForce.png', 'CANELEIRA', '2025-07-11 11:00:28.711068');
INSERT INTO public.products VALUES (1069, 'TrainWell', 'ACESSORIOS', '2025-07-09 14:04:44.158949', 'O produto Colchonete TrainWell da marca TrainWell foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Colchonete Dobrável para Exercícios TrainWell', 75.32, 'ACE-COL-003', '1752242535191_Colchonete Dobrável para Exercícios TrainWell.png', 'COLCHONETE', '2025-07-11 11:02:15.557449');
INSERT INTO public.products VALUES (1072, 'GripForce', 'ACESSORIOS', '2025-07-09 14:05:03.641993', 'O produto Colchonete GripForce da marca GripForce foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Colchonete Dobrável para Exercícios GripForce', 79.50, 'ACE-COL-012', '1752242569674_Colchonete Dobrável para Exercícios GripForce.png', 'COLCHONETE', '2025-07-11 11:02:50.917823');
INSERT INTO public.products VALUES (1084, 'SteelPump', 'PESOS', '2025-07-09 14:13:36.704528', 'O produto Halter SteelPump da marca SteelPump foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Par de Halteres Emborrachados SteelPump', 145.42, 'PES-HAL-006', '1752242868094_Par de Halteres Emborrachados SteelPump.png', 'HALTER', '2025-07-11 11:07:48.577774');
INSERT INTO public.products VALUES (1092, 'TitanIron', 'PESOS', '2025-07-09 14:14:10.074641', 'O produto Halter TitanIron da marca TitanIron foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Par de Halteres Emborrachados TitanIron', 182.02, 'PES-HAL-009', '1752243128119_Par de Halteres Emborrachados TitanIron.png', 'HALTER', '2025-07-11 11:12:08.406881');
INSERT INTO public.products VALUES (1096, 'TitanIron', 'PESOS', '2025-07-09 14:14:39.296758', 'O produto Kettlebell TitanIron da marca TitanIron foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Kettlebell Emborrachado de Treinamento TitanIron', 194.47, 'PES-KET-005', '1752242959839_Kettlebell Emborrachado de Treinamento TitanIron.png', 'KETTLEBELL', '2025-07-11 11:09:20.788052');
INSERT INTO public.products VALUES (1139, 'FlexiGear', 'ROUPAS', '2025-07-09 14:22:55.702325', 'O produto Shorts FlexiGear da marca FlexiGear foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Shorts de Treino Masculino FlexiGear', 72.84, 'ROU-SHO-003', '1752245257649_Shorts de Treino Masculino FlexiGear.png', 'SHORT', '2025-07-11 11:47:37.937974');
INSERT INTO public.products VALUES (1168, 'CycloMax', 'AEROBICOS', '2025-07-09 14:52:15.885452', 'O produto Bicicleta Ergométrica Dobrável Compact da marca CycloMax foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Bicicleta Ergométrica Dobrável Compact', 1800.99, 'AER-BIC-034', '1752242807943_Bicicleta Ergométrica Dobrável Compact CycloMax.png', 'BICICLETA', '2025-07-11 11:06:48.216772');
INSERT INTO public.products VALUES (1172, 'Cardionix', 'AEROBICOS', '2025-07-09 14:52:50.023543', 'O produto Esteira Curva Mecânica SpeedRunner da marca Cardionix foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Esteira Curva Mecânica SpeedRunner', 6200.00, 'AER-EST-026', '1752243064799_Esteira Curva Mecânica SpeedRunner Cardionix.png', 'ESTEIRA', '2025-07-11 11:11:05.181432');
INSERT INTO public.products VALUES (1179, 'StrideTech', 'AEROBICOS', '2025-07-09 14:53:52.087424', 'O produto Elíptico Magnético Transport L10 da marca StrideTech foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Elíptico Magnético Transport L10', 3200.00, 'AER-ELI-024', '1752243267205_Elíptico Magnético Transport L10 StrideTech.png', 'ELIPTICO', '2025-07-11 11:14:27.54562');
INSERT INTO public.products VALUES (1194, 'MovePlus', 'ACESSORIOS', '2025-07-09 14:55:44.747027', 'O produto Barra de Montagem para Halteres da marca MovePlus foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Barra de Montagem para Halteres', 85.90, 'ACE-BAR-028', '1752244987591_Barra de Montagem para Halteres MovePlus.png', 'BARRA', '2025-07-11 11:43:07.945');
INSERT INTO public.products VALUES (1226, 'MovePlus', 'ACESSORIOS', '2025-07-09 19:18:59.568821', 'O produto Colchonete de Espuma NBR Extra Espesso da marca MovePlus foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Colchonete de Espuma NBR Extra Espesso', 145.00, 'ACE-COL-027', '1752245340510_Colchonete de Espuma NBR Extra Espesso MovePlus.png', 'COLCHONETE', '2025-07-11 11:49:01.026244');
INSERT INTO public.products VALUES (1358, 'GymEdge', 'ACESSORIOS', '2025-07-09 19:45:21.156006', 'A Bola de Fisioterapia Cravo da marca GymEdge é ideal para massagem e alívio de tensões musculares. Perfeita para uso em fisioterapia e automassagem.', 'Bola de Fisioterapia Cravo (Bola Massageadora)', 45.80, 'ACE-BOL-005', '1752254883732_Bola de Fisioterapia Cravo GymEdge.jpg', 'BOLA', '2025-07-11 14:28:03.843784');
INSERT INTO public.products VALUES (1270, 'IronBeast', 'PESOS', '2025-07-09 19:25:26.221223', 'O produto Halter Emborrachado Unidade 8kg da marca IronBeast foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Halter Emborrachado Unidade 8kg', 145.80, 'PES-HAL-027', '1752253169063_Kit Halteres Emborrachados (1 a 5 kg) MassForge.jpg', 'HALTER', '2025-07-11 13:59:29.273366');
INSERT INTO public.products VALUES (1273, 'SteelPump', 'PESOS', '2025-07-09 19:25:55.089658', 'O produto Par de Halteres Cromados 4kg da marca SteelPump foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Par de Halteres Cromados 4kg', 220.70, 'PES-HAL-030', '1752253183746_Par de Halteres Cromados 4kg SteelPump.jpg', 'HALTER', '2025-07-11 13:59:43.975697');
INSERT INTO public.products VALUES (1128, 'NutriLab', 'SUPLEMENTOS', '2025-07-09 14:21:01.553397', 'O produto Creatina NutriLab da marca NutriLab foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Creatina Monohidratada 500g NutriLab', 145.73, 'SUP-CRE-002', '1752245898818_Creatina Monohidratada 500g NutriLab.png', 'CREATINA', '2025-07-11 11:58:18.977246');
INSERT INTO public.products VALUES (1202, 'MovePlus', 'ACESSORIOS', '2025-07-09 19:05:21.817494', 'O produto Par de Caneleiras 5kg da marca MovePlus foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Par de Caneleiras 5kg', 110.50, 'ACE-CAN-025', '1752245584351_Par de Caneleiras 5kg MovePlus.png', 'CANELEIRA', '2025-07-11 11:53:04.603483');
INSERT INTO public.products VALUES (1211, 'TrainWell', 'ACESSORIOS', '2025-07-09 19:06:22.443104', 'O produto Par de Caneleiras de Neoprene 2.5kg da marca TrainWell foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Par de Caneleiras de Neoprene 2.5kg', 79.90, 'ACE-CAN-032', '1752245921706_Par de Caneleiras de Neoprene 2.5kg TrainWell.png', 'CANELEIRA', '2025-07-11 11:58:43.276652');
INSERT INTO public.products VALUES (1219, 'BodyTools', 'ACESSORIOS', '2025-07-09 19:07:24.735291', 'O produto Corda de Pular de Sisal da marca BodyTools foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Corda de Pular de Sisal', 35.80, 'ACE-COR-030', '1752246258122_Corda de Pular de Sisal BodyTools.jpg', 'CORDA', '2025-07-11 12:04:18.454688');
INSERT INTO public.products VALUES (1249, 'MovePlus', 'ACESSORIOS', '2025-07-09 19:22:22.751532', 'O produto Suporte Torre para Halteres (Dumbbell Rack) da marca MovePlus foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Suporte Torre para Halteres (Dumbbell Rack)', 850.50, 'ACE-SUP-027', '1752246498637_Suporte Torre para Halteres (Dumbbell Rack) MovePlus.jpg', 'SUPORTE', '2025-07-11 12:08:19.088066');
INSERT INTO public.products VALUES (1261, 'LiftPro', 'PESOS', '2025-07-09 19:24:09.982184', 'O produto Anilha Emborrachada 20kg da marca LiftPro foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Anilha Emborrachada 20kg', 210.00, 'PES-ANI-029', '1752253024402_Anilha Emborrachada 25kg SteelPump.jpg', 'ANILHA', '2025-07-11 13:57:04.738997');
INSERT INTO public.products VALUES (1275, 'IronBeast', 'PESOS', '2025-07-09 19:26:11.684108', 'O produto Par de Halteres Reguláveis 12kg da marca IronBeast foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Par de Halteres Reguláveis 12kg', 480.00, 'PES-HAL-032', '1752253208170_Par de Halteres Reguláveis 12kg IronBeast.jpg', 'HALTER', '2025-07-11 14:00:08.538461');
INSERT INTO public.products VALUES (1282, 'SteelPump', 'PESOS', '2025-07-09 19:27:09.740161', 'O produto Kettlebell de Competição 10kg da marca SteelPump foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Kettlebell de Competição 10kg', 220.00, 'PES-KET-029', '1752253315886_Kettlebell de Competição 10kg SteelPump.jpg', 'KETTLEBELL', '2025-07-11 14:01:56.067315');
INSERT INTO public.products VALUES (1289, 'TitanIron', 'PESOS', '2025-07-09 19:28:45.232912', 'O produto Presilha de Rosca para Barra (Par) da marca TitanIron foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Presilha de Rosca para Barra (Par)', 35.50, 'PES-PRE-026', '1752253419104_Presilha de Rosca para Barra (Par) TitanIron.jpg', 'PRESILHA', '2025-07-11 14:03:40.965078');
INSERT INTO public.products VALUES (1300, 'StrongSupps', 'SUPLEMENTOS', '2025-07-09 19:34:16.877379', 'O produto Pré-Treino Bone Crusher da marca StrongSupps foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Pré-Treino Bone Crusher', 165.00, 'SUP-PRE-027', '1752253779259_Pré-Treino Bone Crusher StrongSupps.jpg', 'PRE_TREINO', '2025-07-11 14:09:39.725076');
INSERT INTO public.products VALUES (1311, 'PowerFuel', 'SUPLEMENTOS', '2025-07-09 19:35:54.103651', 'O produto Whey Protein Sabor Cookies & Cream da marca PowerFuel foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Whey Protein Sabor Cookies & Cream', 175.90, 'SUP-WHE-028', '1752253804187_Whey Protein Sabor Cookies & CreamPowerFuel.jpg', 'WHEY', '2025-07-11 14:10:04.583816');
INSERT INTO public.products VALUES (1320, 'StrongSupps', 'SUPLEMENTOS', '2025-07-09 19:37:10.03424', 'O produto Creatina Alcalina (Kre-Alkalyn) da marca StrongSupps foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Creatina Alcalina (Kre-Alkalyn)', 130.00, 'SUP-CRE-027', '1752254001469_Creatina Alcalina (Kre-Alkalyn) StrongSupps.jpg', 'CREATINA', '2025-07-11 14:13:21.716966');
INSERT INTO public.products VALUES (1328, 'GymApparel', 'ROUPAS', '2025-07-09 19:38:17.070966', 'O produto Camisa de Algodão Estampada ''No Pain No Gain'' da marca GymApparel foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Camisa de Algodão Estampada ''No Pain No Gain''', 89.90, 'ROU-CAM-024', '1752254106089_Camisa de Algodão Estampada ''No Pain No Gain'' GymApparel.jpg', 'CAMISA', '2025-07-11 14:15:06.333148');
INSERT INTO public.products VALUES (1331, 'FlexiGear', 'ROUPAS', '2025-07-09 19:38:37.353515', 'O produto Camisa Oversized para Treino da marca FlexiGear foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Camisa Oversized para Treino', 99.90, 'ROU-CAM-027', '1752254148880_Camisa Oversized para Treino FlexiGear.jpg', 'CAMISA', '2025-07-11 14:15:49.359649');
INSERT INTO public.products VALUES (1336, 'FlexiGear', 'ROUPAS', '2025-07-09 19:39:20.81616', 'O produto Camisa Sem Manga com Capuz da marca FlexiGear foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Camisa Sem Manga com Capuz', 105.00, 'ROU-CAM-032', '1752254267152_Camisa Sem Manga com Capuz FlexiGear.jpg', 'CAMISA', '2025-07-11 14:17:47.30101');
INSERT INTO public.products VALUES (1042, 'StrideTech', 'AEROBICOS', '2025-07-09 12:26:56.26932', 'O produto Bicicleta StrideTech da marca StrideTech foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Bicicleta Ergométrica StrideTech', 2705.44, 'AER-BIC-011', '1752241798497_Bicicleta Ergométrica StrideTech.png', 'BICICLETA', '2025-07-11 10:50:00.273106');
INSERT INTO public.products VALUES (1051, 'Aeroflex', 'AEROBICOS', '2025-07-09 13:59:55.452845', 'O produto Eliptico Aeroflex da marca Aeroflex foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Elíptico Magnético com Painel Digital Aeroflex', 2861.58, 'AER-ELI-005', '1752242151996_Elíptico Magnético com Painel Digital Aeroflex.png', 'ELIPTICO', '2025-07-11 10:55:52.4197');
INSERT INTO public.products VALUES (1112, 'PowerFuel', 'SUPLEMENTOS', '2025-07-09 14:18:24.287442', 'O produto Pré-Treino PowerFuel da marca PowerFuel foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Suplemento Pré-Treino Energia Extrema PowerFuel', 146.05, 'SUP-PRE-004', '1752245024122_Suplemento Pré-Treino Energia Extrema PowerFuel.png', 'PRE_TREINO', '2025-07-11 11:43:44.46863');
INSERT INTO public.products VALUES (1144, 'GymApparel', 'ROUPAS', '2025-07-09 14:24:37.012093', 'O produto Legging GymApparel da marca GymApparel foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Legging de Compressão com Cintura Alta GymApparel', 105.77, 'ROU-LEG-002', '1752245754980_Legging de Compressão com Cintura Alta GymApparel.png', 'LEGGING', '2025-07-11 11:55:55.376319');
INSERT INTO public.products VALUES (1184, 'CycloMax', 'AEROBICOS', '2025-07-09 14:54:32.842659', 'O produto Elíptico Híbrido (Elíptico + Bicicleta) da marca CycloMax foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Elíptico Híbrido (Elíptico + Bicicleta)', 3800.75, 'AER-ELI-028', '1752243348643_Elíptico Híbrido (Elíptico + Bicicleta) CycloMax.png', 'ELIPTICO', '2025-07-11 11:15:48.874048');
INSERT INTO public.products VALUES (1197, 'TrainWell', 'ACESSORIOS', '2025-07-09 14:56:04.011087', 'O produto Barra para Puxada Costas com Pegada Neutra da marca TrainWell foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Barra para Puxada Costas com Pegada Neutra', 210.40, 'ACE-BAR-031', '1752245128137_Barra para Puxada Costas com Pegada Neutra TrainWell.png', 'BARRA', '2025-07-11 11:45:29.102168');
INSERT INTO public.products VALUES (1355, 'FlexiGear', 'ROUPAS', '2025-07-09 19:43:18.851475', 'O produto Legging com Detalhes Refletivos da marca FlexiGear foi desenvolvido para oferecer desempenho, durabilidade e conforto em seus treinos. Ideal para iniciantes e atletas experientes, possui design moderno e funcional.', 'Legging com Detalhes Refletivos', 158.60, 'ROU-LEG-033', '1752254866674_Legging com Detalhes Refletivos FlexiGear.jpg', 'LEGGING', '2025-07-11 14:27:46.823113');


--
-- TOC entry 3452 (class 0 OID 0)
-- Dependencies: 226
-- Name: coupons_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.coupons_id_seq', 1, false);


--
-- TOC entry 3453 (class 0 OID 0)
-- Dependencies: 228
-- Name: coupons_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.coupons_seq', 2, true);


--
-- TOC entry 3454 (class 0 OID 0)
-- Dependencies: 223
-- Name: order_item_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.order_item_id_seq', 1, false);


--
-- TOC entry 3455 (class 0 OID 0)
-- Dependencies: 225
-- Name: order_item_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.order_item_seq', 7, true);


--
-- TOC entry 3456 (class 0 OID 0)
-- Dependencies: 229
-- Name: payment_coupons_coupon_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.payment_coupons_coupon_id_seq', 1, false);


--
-- TOC entry 3457 (class 0 OID 0)
-- Dependencies: 219
-- Name: products_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.products_id_seq', 48, true);


--
-- TOC entry 3458 (class 0 OID 0)
-- Dependencies: 221
-- Name: products_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.products_seq', 365, true);


--
-- TOC entry 3273 (class 2606 OID 16476)
-- Name: coupons coupons_code_unique; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.coupons
    ADD CONSTRAINT coupons_code_unique UNIQUE (code);


--
-- TOC entry 3275 (class 2606 OID 16474)
-- Name: coupons coupons_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.coupons
    ADD CONSTRAINT coupons_pkey PRIMARY KEY (id);


--
-- TOC entry 3259 (class 2606 OID 16406)
-- Name: customers customers_cpf_unique; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.customers
    ADD CONSTRAINT customers_cpf_unique UNIQUE (cpf);


--
-- TOC entry 3261 (class 2606 OID 16408)
-- Name: customers customers_email_unique; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.customers
    ADD CONSTRAINT customers_email_unique UNIQUE (email);


--
-- TOC entry 3263 (class 2606 OID 16404)
-- Name: customers customers_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.customers
    ADD CONSTRAINT customers_pkey PRIMARY KEY (id);


--
-- TOC entry 3256 (class 2606 OID 16395)
-- Name: flyway_schema_history flyway_schema_history_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.flyway_schema_history
    ADD CONSTRAINT flyway_schema_history_pk PRIMARY KEY (installed_rank);


--
-- TOC entry 3271 (class 2606 OID 16454)
-- Name: order_item order_item_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.order_item
    ADD CONSTRAINT order_item_pkey PRIMARY KEY (id);


--
-- TOC entry 3269 (class 2606 OID 16428)
-- Name: orders orders_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (id);


--
-- TOC entry 3277 (class 2606 OID 24594)
-- Name: payments payments_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.payments
    ADD CONSTRAINT payments_pkey PRIMARY KEY (id);


--
-- TOC entry 3265 (class 2606 OID 16419)
-- Name: products products_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_pkey PRIMARY KEY (id);


--
-- TOC entry 3267 (class 2606 OID 16421)
-- Name: products products_unique_sku; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_unique_sku UNIQUE (sku);


--
-- TOC entry 3257 (class 1259 OID 16396)
-- Name: flyway_schema_history_s_idx; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX flyway_schema_history_s_idx ON public.flyway_schema_history USING btree (success);


--
-- TOC entry 3279 (class 2606 OID 16460)
-- Name: order_item order_item_orders_order_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.order_item
    ADD CONSTRAINT order_item_orders_order_id_fkey FOREIGN KEY (order_id) REFERENCES public.orders(id);


--
-- TOC entry 3280 (class 2606 OID 16455)
-- Name: order_item order_item_products_product_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.order_item
    ADD CONSTRAINT order_item_products_product_id_fkey FOREIGN KEY (product_id) REFERENCES public.products(id);


--
-- TOC entry 3278 (class 2606 OID 16429)
-- Name: orders orders_customers_customer_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_customers_customer_id_fkey FOREIGN KEY (customer_id) REFERENCES public.customers(id);


--
-- TOC entry 3281 (class 2606 OID 16488)
-- Name: payment_coupons payment_coupons_coupons_coupon_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.payment_coupons
    ADD CONSTRAINT payment_coupons_coupons_coupon_id_fkey FOREIGN KEY (coupon_id) REFERENCES public.coupons(id);


--
-- TOC entry 3282 (class 2606 OID 24595)
-- Name: payments payments_orders_order_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.payments
    ADD CONSTRAINT payments_orders_order_id_fkey FOREIGN KEY (order_id) REFERENCES public.orders(id);


-- Completed on 2025-07-21 18:43:05

--
-- PostgreSQL database dump complete
--

