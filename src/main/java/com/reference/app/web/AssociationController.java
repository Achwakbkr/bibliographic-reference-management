package com.reference.app.web;

import com.reference.app.entities.Reference;
import com.reference.app.entities.Tag;
import com.reference.app.repository.ReferenceRepository;
import com.reference.app.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AssociationController {
    @Autowired
    private ReferenceRepository referenceRepository;
    @Autowired
    private TagRepository tagRepository;
    @GetMapping("/associate-reference-tag")
    public String showAssociationForm(Model model) {
        // Chargez la liste des références et des tags depuis la base de données
        List<Reference> references = referenceRepository.findAll();
        List<Tag> tags = tagRepository.findAll();

        model.addAttribute("referenceList", references);
        model.addAttribute("tagList", tags);

        return "associate-reference-tag"; // Affichez la page d'association
    }

    @PostMapping("/associate-reference-tag")
    public String associateReferenceWithTag(@RequestParam("id") Long id,
                                            @RequestParam("id_t") Long id_t) {
        // Chargez la référence et le tag sélectionnés depuis la base de données
        Reference reference = referenceRepository.findById(id).orElse(null);
        Tag tag = tagRepository.findById(id_t).orElse(null);

        if (reference != null && tag != null) {
            // Associez la référence au tag en ajoutant la référence au set de tags du tag
            reference.getTags().add(tag);
            referenceRepository.save(reference);

            return "redirect:/associate-reference-tag?success"; // Redirigez vers la page d'association avec un message de succès
        }

        return "redirect:/associate-reference-tag?error"; // Redirigez vers la page d'association avec un message d'erreur
    }

}
