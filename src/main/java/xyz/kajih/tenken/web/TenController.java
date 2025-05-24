package xyz.kajih.tenken.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import xyz.kajih.tenken.server.api.UsersApi;
import xyz.kajih.tenken.server.model.User;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class TenController implements UsersApi {

    private static final Set<User> users = ConcurrentHashMap.newKeySet();

    /**
     * POST /users : Create a new user
     *
     * @param userMono (required)
     * @param exchange
     * @return User created successfully (status code 201)
     * or Invalid input (status code 400)
     * or Unauthorized (status code 401)
     */
    @Override
    public Mono<ResponseEntity<User>> createUser(Mono<User> userMono, ServerWebExchange exchange) {
        return userMono
                .map(user -> user.id(UUID.randomUUID())) // assign new UUID
                .doOnNext(users::add)                         // add to in-memory list
                .map(savedUser -> ResponseEntity
                        .status(HttpStatus.ACCEPTED)
                        .body(savedUser));                    // wrap response
    }

    /**
     * GET /users/{id} : Get user by ID
     *
     * @param id       (required)
     * @param exchange
     * @return User found (status code 200)
     * or Invalid ID supplied (status code 400)
     * or Unauthorized (status code 401)
     * or User not found (status code 404)
     */
    @Override
    public Mono<ResponseEntity<User>> getUser(String id, ServerWebExchange exchange) {
        return UsersApi.super.getUser(id, exchange);
    }

    /**
     * GET /users : Get all users
     *
     * @param exchange
     * @return No User found (status code 200)
     * or Invalid ID supplied (status code 400)
     * or Unauthorized (status code 401)
     * or User not found (status code 404)
     */
    @Override
    public Mono<ResponseEntity<Flux<User>>> getUsers(ServerWebExchange exchange) {
        return UsersApi.super.getUsers(exchange);
    }

    /**
     * PUT /users/{id} : Update user by ID
     *
     * @param id       (required)
     * @param user     (required)
     * @param exchange
     * @return User updated successfully (status code 200)
     * or User update accepted but not yet completed (status code 202)
     * or Invalid input (status code 400)
     * or Unauthorized (status code 401)
     * or User not found (status code 404)
     */
    @Override
    public Mono<ResponseEntity<User>> putUser(String id, Mono<User> user, ServerWebExchange exchange) {
        return UsersApi.super.putUser(id, user, exchange);
    }

}
