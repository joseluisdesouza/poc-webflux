package com.api.professional.service;

import com.api.professional.document.Professional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.api.professional.repository.ProfessionalRepository;

@Service
public class ProfessionalService {

    private final ProfessionalRepository repository;

    public ProfessionalService(ProfessionalRepository repository) {
        this.repository = repository;
    }

    public Flux<Professional> findAll() {

        return repository.findAll();
    }

    public Mono<ResponseEntity<Professional>> findById(String id) {

        return repository.findById(id)
                .map((professional -> new ResponseEntity<>(professional, HttpStatus.OK)))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public Mono<Professional> create(Professional professional) {

        return repository.save(professional);
    }

    public Mono<Void> delete(String id) {

        return repository.deleteById(id);
    }
}
