package xyz.kajih.tenken.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import xyz.kajih.tenken.server.api.UsersApi;
import xyz.kajih.tenken.server.model.User;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class TenController implements UsersApi {
    /**
     * POST /users : Create a new user
     *
     * @param user     (required)
     * @param exchange
     * @return User created successfully (status code 201)
     * or Invalid input (status code 400)
     * or Unauthorized (status code 401)
     */
    @Override
    public Mono<ResponseEntity<User>> createUser(Mono<User> user, ServerWebExchange exchange) {
        return UsersApi.super.createUser(user, exchange);
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
