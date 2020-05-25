package com.api.professional.repository;

import com.api.professional.document.Professional;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProfessionalRepository extends ReactiveMongoRepository<Professional, String> {

}
