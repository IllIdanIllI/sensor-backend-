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

CREATE TYPE public.sensor_types AS ENUM (
'Pressure',
'Voltage',
'Temperature',
'Humidity'
);


ALTER TYPE public.sensor_types OWNER TO postgres;

CREATE TYPE public.units AS ENUM (
'bar',
'voltage',
'°С',
'%'
);


ALTER TYPE public.units OWNER TO postgres;


CREATE TYPE public.user_role AS ENUM (
'ADMINISTRATOR',
'VIEWER'
);


ALTER TYPE public.user_role OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

CREATE TABLE public.sensor (
    id integer NOT NULL,
    name character varying(30) NOT NULL,
    model character varying(15) NOT NULL,
    range int4range,
    location character varying(40),
    description character varying(200),
    unit public.units DEFAULT 'voltage'::public.units,
    type public.sensor_types DEFAULT 'Pressure'::public.sensor_types NOT NULL
);


ALTER TABLE public.sensor OWNER TO postgres;

CREATE SEQUENCE public.sensor_id_seq
AS integer
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;


ALTER TABLE public.sensor_id_seq OWNER TO postgres;

ALTER SEQUENCE public.sensor_id_seq OWNED BY public.sensor.id;

CREATE TABLE public."user" (
    id integer NOT NULL,
    login character varying(30) NOT NULL,
    password character varying(60) NOT NULL,
    role public.user_role DEFAULT 'VIEWER'::public.user_role NOT NULL
);


ALTER TABLE public."user" OWNER TO postgres;

CREATE SEQUENCE public.user_id_seq
AS integer
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;


ALTER TABLE public.user_id_seq OWNER TO postgres;

ALTER SEQUENCE public.user_id_seq OWNED BY public."user".id;

ALTER TABLE ONLY public.sensor ALTER COLUMN id SET DEFAULT nextval('public.sensor_id_seq'::regclass);

ALTER TABLE ONLY public."user" ALTER COLUMN id SET DEFAULT nextval('public.user_id_seq'::regclass);

SELECT pg_catalog.setval('public.sensor_id_seq', (SELECT MAX(id) FROM public.sensor) + 1, false);

SELECT pg_catalog.setval('public.user_id_seq', (SELECT MAX(id) FROM public.user) + 1, true);

ALTER TABLE ONLY public.sensor
ADD CONSTRAINT sensor_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public."user"
ADD CONSTRAINT user_pkey PRIMARY KEY (id);