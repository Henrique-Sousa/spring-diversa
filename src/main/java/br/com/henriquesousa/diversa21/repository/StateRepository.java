package br.com.henriquesousa.diversa21.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.henriquesousa.diversa21.entity.State;

public interface StateRepository extends JpaRepository<State, Long> {
    Optional<State> findByCode(String code);
}
