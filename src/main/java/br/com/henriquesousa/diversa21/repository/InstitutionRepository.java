package br.com.henriquesousa.diversa21.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.henriquesousa.diversa21.entity.Institution;

public interface InstitutionRepository extends JpaRepository<Institution, Long> {
    boolean existsByEmail(String email);
}
