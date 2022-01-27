CREATE TABLE IF NOT EXISTS public.notifications
(
    id                uuid                        NOT NULL,
    body              text                        not null,
    notification_type character varying(8)        not null default 'EVENT',
    status            character varying(8)        not null default 'WAITING',
    created_at        timestamp without time zone NOT null default now(),
    updated_at        timestamp without time zone,
    deleted_at        timestamp without time zone,
    CONSTRAINT pk_notifications PRIMARY KEY (id)
);

CREATE INDEX IF NOT EXISTS idx_notifications_status
    ON public.notifications
        USING btree (status) WHERE status = 'WAITING';

CREATE TABLE IF NOT EXISTS public.notification_received
(
    id         uuid                        NOT NULL,
    body       text                        not null,
    status     character varying(8)        not null default 'PROGRESS',
    created_at timestamp without time zone NOT null default now(),
    updated_at timestamp without time zone,
    deleted_at timestamp without time zone,
    CONSTRAINT pk_notification_received PRIMARY KEY (id)
);


DROP TRIGGER IF EXISTS notification_received_tgr_bu ON public.notification_received;

CREATE TRIGGER notification_received_tgr_bu
    BEFORE UPDATE
    ON public.notification_received
    FOR EACH ROW
EXECUTE PROCEDURE public.trigger_set_updated_at();