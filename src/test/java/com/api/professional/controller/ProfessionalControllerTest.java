package com.api.professional.controller;

import com.api.professional.document.Professional;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.api.professional.repository.ProfessionalRepository;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;


@SpringBootTest
@AutoConfigureWebTestClient
@RunWith(SpringRunner.class)
@DirtiesContext
@ActiveProfiles("test")
public class ProfessionalControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    ProfessionalRepository professionalRepository;

    public List<Professional> professionals() {

        return Arrays.asList(
                new Professional(null, "Pedro Augusto", "pedro123@gmail.com",
                        "9999-8989", 2.000, "Sicredi"),

                new Professional(null, "Roberto Maia", "roberto1234@gmail.com",
                        "7895-4545", 1.500, "Itau"),

                new Professional(null, "João Gustavo", "joaogustavo@gmail.com",
                        "7676-4646", 5.000, "MultiBank"),

                new Professional("AB2C2D", "Paulo Silva", "paulo123@gmail.com",
                        "2323-2323", 4.800, "GetNet")

        );

    }

    @Before
    public void setUp() {

        professionalRepository.deleteAll()
                .thenMany(Flux.fromIterable(professionals()))
                .flatMap(professionalRepository::save)
                .doOnNext((professional -> {
                    System.out.println("Professional list: " + professional);
                })).blockLast();
    }

    @Test
    public void getAll() {

        webTestClient.get().uri("/professionals")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(Professional.class)
                .hasSize(4);
    }

    @Test
    public void getById() {

        webTestClient.get().uri("/professionals/{id}", "AB2C2D")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.name", "GetNet");

    }

    @Test
    public void create() {

        Professional newProfessional = new Professional(null, "Felipe Junior", "felipe2323@gmail.com",
                "4545-4545", 3.800, "Itau");

        webTestClient.post().uri("/professionals")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(newProfessional), Professional.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.name").isEqualTo("Felipe Junior");

    }

    @Test
    public void delete() {

        webTestClient.delete().uri("/professionals/{ID}", "AB2C2D")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Void.class);
    }

}
