package br.com.henriquesousa.diversa21.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.henriquesousa.diversa21.entity.State;

public interface StateRepository extends JpaRepository<State, Long> {
    boolean existsByCode(String code);
}
