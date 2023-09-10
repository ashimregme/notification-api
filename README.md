# Notification API

## How to run?

### Run locally using docker

- RabbitMQ:

    ```
    docker run -d                   \
        -p 5672:5672 -p 15672:15672 \
        --hostname rmq --name rmq   \
        -v "/home/ashim/Code/notification-api/rmq-data:/var/lib/rabbitmq" \
        rabbitmq:3.12-management
    ```

- PostgreSQL:

    ```
    docker build ./psql -t psql:local
    ```

  Run the image:

    ```
    docker run -d                               \
        -p 5432:5432                            \
        --name psql                             \
        -e "POSTGRES_USER=notif_user"           \
        -e "POSTGRES_PASSWORD=notif_pass"       \
        -e "POSTGRES_DB=notification-db"        \
        -v "./psql-data:/bitnami/postgresql"    \
        psql:local
    ```

### Run locally using docker-compose

```
docker-compose up --build
```

## What are the endpoints?

### Swagger UI

```
http://localhost:8080/swagger-ui/index.html
```