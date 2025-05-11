package xyz.kajih.tenken.internal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import xyz.kajih.tenken.web.User;

import java.util.List;
import java.util.stream.Stream;

@Slf4j
@Component
public class TenkenWebExecute implements CommandLineRunner {

    private final WebClient webClient;

    public TenkenWebExecute() {

        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:8080")
                .build();
    }

    @Override
    public void run(String... args) {
        log.info("Tenken CommandLine");

        List<User> userList = Stream.generate(User::randomUser)
                .limit(10)
                .toList();

//        Flux<User> userFlux = Flux.fromIterable(userList);
        int id = 0;

       /*
        String result = webClient.post()
                .uri("/api/users/{id}", id)
                .bodyValue(userList)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        log.info(result);

        */

    }
}
