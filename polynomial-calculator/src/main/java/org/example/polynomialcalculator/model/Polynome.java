package org.example.polynomialcalculator.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Polynome {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private List<Double> coefficients; // Liste des coefficients du polynôme

    public Polynome() {}

    public Polynome(List<Double> coefficients) {
        this.coefficients = coefficients;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Double> getCoefficients() {
        return coefficients;
    }

    public void setCoefficients(List<Double> coefficients) {
        this.coefficients = coefficients;
    }

    // Méthode pour évaluer un polynôme à un point x
    public double evaluate(double x) {
        double result = 0;
        for (int i = 0; i < coefficients.size(); i++) {
            result += coefficients.get(i) * Math.pow(x, coefficients.size() - i - 1);
        }
        return result;
    }
}
