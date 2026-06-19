package br.com.henriquesousa.diversa21.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.henriquesousa.diversa21.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
