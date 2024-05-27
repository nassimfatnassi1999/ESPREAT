package com.saccess.eventAndDonation.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class BadWordsFilterService {
    // Liste de mots inappropriés à filtrer
    private static final List<String> BAD_WORDS = Arrays.asList("badword1", "badword2", "badword3");
    // Méthode pour vérifier si la chaîne de texte contient des mots inappropriés
    public boolean containsBadWords(String text) {
        String lowercaseText = text.toLowerCase();// Convertit la chaîne de texte en minuscules pour la recherche insensible à la casse
        for (String word : BAD_WORDS) {
            if (lowercaseText.contains(word)) { // Vérifie si la chaîne de texte contient le mot inapproprié
                return true; // Retourne vrai dès qu'un mot inapproprié est trouvé
            }
        }
        return false;// Retourne faux sinon

    }
    // Méthode pour filtrer les mots inappropriés dans la chaîne de texte
    public String filterBadWords(String text) {
        String filteredText = text.toLowerCase(); // Convertit la chaîne de texte en minuscules pour la recherche insensible à la casse
        for (String word : BAD_WORDS) {
            filteredText = filteredText.replaceAll(word, "*".repeat(word.length()));     // Remplace chaque occurrence du mot inapproprié par des astérisques (*) de la même longueur
        }
        return filteredText;// Retourne la chaîne de texte filtrée
    }

}
