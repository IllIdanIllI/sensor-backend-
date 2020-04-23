--
-- PostgreSQL database dump
--

-- Dumped from database version 11.5
-- Dumped by pg_dump version 11.5

-- Started on 2020-04-23 18:30:53

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 607 (class 1247 OID 25562)
-- Name: type_enum; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.type_enum AS ENUM (
    'Pressure',
    'Voltage',
    'Temperature',
    'Humidity'
);


ALTER TYPE public.type_enum OWNER TO postgres;

--
-- TOC entry 604 (class 1247 OID 25552)
-- Name: unit_enum; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.unit_enum AS ENUM (
    ' bar',
    'voltage',
    '°С',
    ' %'
);


ALTER TYPE public.unit_enum OWNER TO postgres;

--
-- TOC entry 596 (class 1247 OID 25505)
-- Name: user_role; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.user_role AS ENUM (
    'Administrator',
    'Viewer'
);


ALTER TYPE public.user_role OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 199 (class 1259 OID 25512)
-- Name: sensor; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.sensor (
    id integer NOT NULL,
    name character varying(30) NOT NULL,
    model character varying(15) NOT NULL,
    range int4range,
    location character varying(40),
    description character varying(200),
    type public.type_enum DEFAULT 'Pressure'::public.type_enum,
    unit public.unit_enum DEFAULT 'voltage'::public.unit_enum
);


ALTER TABLE public.sensor OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 25510)
-- Name: sensor_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.sensor_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sensor_id_seq OWNER TO postgres;

--
-- TOC entry 2840 (class 0 OID 0)
-- Dependencies: 198
-- Name: sensor_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.sensor_id_seq OWNED BY public.sensor.id;


--
-- TOC entry 196 (class 1259 OID 25493)
-- Name: user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."user" (
    name character varying(40),
    id integer NOT NULL,
    login character varying(30) NOT NULL,
    password character varying(60) NOT NULL,
    role public.user_role DEFAULT 'Viewer'::public.user_role
);


ALTER TABLE public."user" OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 25496)
-- Name: user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_id_seq OWNER TO postgres;

--
-- TOC entry 2841 (class 0 OID 0)
-- Dependencies: 197
-- Name: user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_id_seq OWNED BY public."user".id;


--
-- TOC entry 2703 (class 2604 OID 25515)
-- Name: sensor id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sensor ALTER COLUMN id SET DEFAULT nextval('public.sensor_id_seq'::regclass);


--
-- TOC entry 2701 (class 2604 OID 25498)
-- Name: user id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user" ALTER COLUMN id SET DEFAULT nextval('public.user_id_seq'::regclass);


--
-- TOC entry 2834 (class 0 OID 25512)
-- Dependencies: 199
-- Data for Name: sensor; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.sensor (id, name, model, range, location, description, type, unit) FROM stdin;
\.


--
-- TOC entry 2831 (class 0 OID 25493)
-- Dependencies: 196
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."user" (name, id, login, password, role) FROM stdin;
\.


--
-- TOC entry 2842 (class 0 OID 0)
-- Dependencies: 198
-- Name: sensor_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.sensor_id_seq', 1, false);


--
-- TOC entry 2843 (class 0 OID 0)
-- Dependencies: 197
-- Name: user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_id_seq', 1, false);


--
-- TOC entry 2709 (class 2606 OID 25520)
-- Name: sensor sensor_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sensor
    ADD CONSTRAINT sensor_pkey PRIMARY KEY (id);


--
-- TOC entry 2707 (class 2606 OID 25503)
-- Name: user user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);


-- Completed on 2020-04-23 18:30:53

--
-- PostgreSQL database dump complete
--

