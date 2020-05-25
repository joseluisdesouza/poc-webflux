package com.api.professional.controller;

import com.api.professional.document.Professional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.api.professional.service.ProfessionalService;

@RestController
@RequestMapping("/professionals")
public class ProfessionalController {

    private ProfessionalService service;

    public ProfessionalController(ProfessionalService service) {
        this.service = service;
    }

    @GetMapping
    public Flux<Professional> findAll() {

        return service.findAll();

    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Professional>> findById(@PathVariable String id) {

        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Professional> create(@RequestBody Professional professional) {

        return service.create(professional);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id) {

        return service.delete(id);
    }

}
