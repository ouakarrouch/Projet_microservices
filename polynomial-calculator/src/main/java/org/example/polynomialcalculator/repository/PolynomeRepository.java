package org.example.polynomialcalculator.repository;

import org.example.polynomialcalculator.model.Polynome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PolynomeRepository extends JpaRepository<Polynome, Long> {
    // Ajoutez des méthodes personnalisées si nécessaire
}
