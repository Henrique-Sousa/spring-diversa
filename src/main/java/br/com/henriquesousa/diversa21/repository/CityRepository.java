package br.com.henriquesousa.diversa21.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.henriquesousa.diversa21.entity.City;

public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findByUid(UUID uid);
    boolean existsByUid(UUID uid);
}
