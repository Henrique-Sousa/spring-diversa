package br.com.henriquesousa.diversa21.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.henriquesousa.diversa21.entity.Institution;

public interface InstitutionRepository extends JpaRepository<Institution, Long> {
    Optional<Institution> findByUid(UUID uid);
    boolean existsByEmail(String email);
}
