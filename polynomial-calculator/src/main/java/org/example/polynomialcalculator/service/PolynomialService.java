package org.example.polynomialcalculator.service;
import org.example.polynomialcalculator.model.Polynome;
import org.example.polynomialcalculator.repository.PolynomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolynomialService {

    @Autowired
    private PolynomeRepository polynomeRepository;

    // Méthode pour calculer les racines avec la méthode de Newton
    public double[] calculateRoots(Polynome polynome, double initialGuess, int maxIterations, double tolerance) {
        // Exemple de la méthode de Newton pour un polynôme
        double x = initialGuess;
        int iterations = 0;
        double difference = Double.MAX_VALUE;

        while (iterations < maxIterations && difference > tolerance) {
            double fx = polynome.evaluate(x);
            double fxPrime = derivative(polynome, x);

            if (fxPrime == 0) {
                throw new RuntimeException("La dérivée est nulle, la méthode de Newton échoue.");
            }

            double newX = x - fx / fxPrime;
            difference = Math.abs(newX - x);
            x = newX;
            iterations++;
        }

        // Retourne la racine approximée
        return new double[]{x};
    }

    // Méthode pour calculer la dérivée du polynôme à un point x
    private double derivative(Polynome polynome, double x) {
        double derivative = 0;
        for (int i = 0; i < polynome.getCoefficients().size() - 1; i++) {
            derivative += (polynome.getCoefficients().get(i) * (polynome.getCoefficients().size() - i - 1)) * Math.pow(x, polynome.getCoefficients().size() - i - 2);
        }
        return derivative;
    }

    // Sauvegarder le polynôme dans la base de données
    public Polynome savePolynome(Polynome polynome) {
        return polynomeRepository.save(polynome);
    }

    // Récupérer tous les polynômes de la base de données
    public List<Polynome> getAllPolynomes() {
        return polynomeRepository.findAll();
    }
}
