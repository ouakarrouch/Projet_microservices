package org.example.polynomialcalculator.controller;


import org.example.polynomialcalculator.model.Polynome;
import org.example.polynomialcalculator.service.PolynomialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/polynomial")
public class PolynomialController {

    @Autowired
    private PolynomialService polynomialService;

    // Calculer les racines du polynôme
    @PostMapping("/calculate-roots")
    public ResponseEntity<?> calculateRoots(@RequestBody @Valid Polynome polynome) {
        try {
            double[] roots = polynomialService.calculateRoots(polynome, 0.0, 1000, 0.0001);
            return ResponseEntity.ok(roots);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Erreur dans le calcul des racines : " + e.getMessage());
        }
    }

    // Ajouter un polynôme à la base de données
    @PostMapping("/add")
    public ResponseEntity<Polynome> addPolynome(@RequestBody @Valid Polynome polynome) {
        Polynome savedPolynome = polynomialService.savePolynome(polynome);
        return ResponseEntity.ok(savedPolynome);
    }

    // Récupérer tous les polynômes de la base de données
    @GetMapping("/all")
    public ResponseEntity<List<Polynome>> getAllPolynomes() {
        List<Polynome> polynomes = polynomialService.getAllPolynomes();
        return ResponseEntity.ok(polynomes);
    }
}
