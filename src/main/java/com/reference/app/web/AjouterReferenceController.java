package com.reference.app.web;

import com.reference.app.entities.Reference;
import com.reference.app.repository.ReferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AjouterReferenceController {

    private final ReferenceRepository referenceRepository;

    @Autowired
    public AjouterReferenceController(ReferenceRepository referenceRepository) {
        this.referenceRepository = referenceRepository;
    }

    @GetMapping("/ajouter-reference")
    public String ajouterReferenceForm(Model model) {
        // Créez un objet Reference vide pour le formulaire
        Reference reference = new Reference();
        model.addAttribute("reference", reference);
        return "ajouter-reference"; // Le nom de la page d'ajout de référence (ajouter-reference.html)
    }

    @PostMapping("/ajouter-reference")
    public String ajouterReference(@ModelAttribute Reference reference) {
        // Code pour ajouter la référence à la base de données (utilisez le repository)
        // Réalisez l'ajout de la référence à la base de données ici
        referenceRepository.save(reference); // Sauvegardez la référence dans la base de données
        return "/home"; // Redirigez l'utilisateur vers la page d'accueil après l'ajout
    }
}

