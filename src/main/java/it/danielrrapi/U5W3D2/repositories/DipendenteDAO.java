package it.danielrrapi.U5W3D2.repositories;

import it.danielrrapi.U5W3D2.entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DipendenteDAO extends JpaRepository<Dipendente, Long> {
    Optional<Dipendente> findByEmail(String email);
}
