CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE OR REPLACE FUNCTION public.trigger_set_updated_at()
    RETURNS TRIGGER AS
$$
BEGIN
    NEW.updated_at = NOW();
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE TABLE public.users (
    id UUID NOT NULL DEFAULT uuid_generate_v4() NOT NULL ,
    login text CHECK (char_length(login) <= 100) NOT NULL,
    email text CHECK (char_length(email) <= 100) NOT NULL,
    password  text CHECK (char_length(password) <= 200) NOT NULL,
    "name"  text CHECK (char_length("name") <= 100) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at TIMESTAMP WITH TIME ZONE NULL,
    deleted_at TIMESTAMP WITH TIME ZONE,
    CONSTRAINT users_pk PRIMARY KEY (id)
);

DROP TRIGGER IF EXISTS users_tgr_bu ON public.users;

CREATE TRIGGER users_tgr_bu
    BEFORE UPDATE
    ON public.users
    FOR EACH ROW
EXECUTE PROCEDURE public.trigger_set_updated_at();