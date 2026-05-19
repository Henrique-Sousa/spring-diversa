package br.com.henriquesousa.diversa21.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.henriquesousa.diversa21.entity.City;

public interface CityRepository extends JpaRepository<City, Long> {
}
