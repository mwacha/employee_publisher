CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE OR REPLACE FUNCTION public.trigger_set_updated_at()
    RETURNS TRIGGER AS
$$
BEGIN
    NEW.updated_at = NOW();
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE TABLE public.employee (
    id UUID NOT NULL DEFAULT uuid_generate_v4() NOT NULL ,
    employee_name  text CHECK (char_length(employee_name) <= 100) NOT NULL,
    email text CHECK (char_length(email) <= 100) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at TIMESTAMP WITH TIME ZONE NULL,
    deleted_at TIMESTAMP WITH TIME ZONE,
    CONSTRAINT employee_pk PRIMARY KEY (id)
);

DROP TRIGGER IF EXISTS employee_tgr_bu ON public.employee;

CREATE TRIGGER employee_tgr_bu
    BEFORE UPDATE
    ON public.employee
    FOR EACH ROW
EXECUTE PROCEDURE public.trigger_set_updated_at();