CREATE TABLE users(
    id BIGSERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    user_device TEXT NOT NULL,
    device_token TEXT NOT NULL,
    tags TEXT ARRAY,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT (current_timestamp AT TIME ZONE 'UTC')
);

INSERT INTO
    users (name, user_device, device_token, tags)
VALUES
    (
        'USER 1',
        'ANDROID',
        'ashfkahsfklh293ysfhaksdfh29yrofhkasjhfklasfasdf',
        '{"nepal", "kathmandu"}'
    );

INSERT INTO
    users (name, user_device, device_token, tags)
VALUES
    (
        'USER 2',
        'ANDROID',
        'ashfkahsfklh293ysfhaksdfh29yrofhkasjhfklasfasdf',
        '{"india", "delhi"}'
    );

INSERT INTO
    users (name, user_device, device_token, tags)
VALUES
    (
        'USER 3',
        'IOS',
        'ashfkahsfklh293ysfhaksdfh29yrofhkasjhfklasfasdf',
        '{"usa", "boston"}'
    );

INSERT INTO
    users (name, user_device, device_token, tags)
VALUES
    (
        'USER 4',
        'IOS',
        'ashfkahsfklh293ysfhaksdfh29yrofhkasjhfklasfasdf',
        '{"poland", "warsaw"}'
    );

INSERT INTO
    users (name, user_device, device_token, tags)
VALUES
    (
        'USER 5',
        'ANDROID',
        'ashfkahsfklh293ysfhaksdfh29yrofhkasjhfklasfasdf',
        '{"nepal", "biratnagar"}'
    );