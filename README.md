# Notification API

## What is it?

Backend service that exposes a REST API which allows callers to send push notifications asynchronously. Asynchronously
means
that service is using queuing service (RabbitMQ) to eventually send the notifications.

## How to run?

### Separate components

1. RabbitMQ:

    ```
    docker run -d                   \
        -p 5672:5672 -p 15672:15672 \
        --hostname rmq --name rmq   \
        -v "./rmq-data:/var/lib/rabbitmq" \
        rabbitmq:3.12-management
    ```

2. PostgreSQL:

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

3. Spring Boot Application
    ```
    ./mvnw spring-boot:run
    ```

### Altogether (docker-compose)

Create an external network in docker to expose Spring Boot Application and RabbitMQ management interface.

  ```
  docker network create frontend
  ```

Build docker images and start the docker services.

  ```
  docker-compose up --build
  ```

### Access RabbitMQ Management UI

```
http://localhost:15672/
```

## What are the endpoints?

### Swagger UI

```
http://localhost:8080/swagger-ui/index.html
```

## Known issues

- Tries to send push notifications to expired (stale) device tokens.

## Future plan

- Fix some known issues
- Expose Websockets/gRPC endpoints

## Peculiarities

- Note this line in Dockerfile:
    ```
    RUN \
        --mount=type=cache,target=/root/.m2 \
        ./mvnw dependency:resolve-plugins dependency:resolve
    ```
  It utilizes Docker's RUN cache to cache container's .m2 directory. Basically, it avoids a minuscule change in pom.xml
  to
  trigger regeneration of entire .m2 directory.
  Read more about it on
  [Medium](https://medium.com/p/75bd980ddaae) or
  [Dev.to](https://dev.to/ashimregmi/make-your-builds-faster-by-caching-m2-directory-using-dockers-run-cache-2l9g).