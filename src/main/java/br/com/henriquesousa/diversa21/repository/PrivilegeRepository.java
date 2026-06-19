package br.com.henriquesousa.diversa21.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.henriquesousa.diversa21.entity.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
    Optional<Privilege> findByName(String name);
}
