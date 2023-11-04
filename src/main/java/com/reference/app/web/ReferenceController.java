package com.reference.app.web;

import com.reference.app.entities.Reference;
import com.reference.app.repository.ReferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ReferenceController {
    private ReferenceRepository referenceRepository;

    public ReferenceController(ReferenceRepository referenceRepository) {
        this.referenceRepository = referenceRepository;
    }

    /*@GetMapping(path = "/index")
    public String index(Model model){
        List<Reference> references= referenceRepository.findAll();
        model.addAttribute("referenceList",references);
        return "index";
    } */
    @GetMapping("/search")
    public String searchByTag(@RequestParam("tag") String tagName, Model model) {
        List<Reference> references = referenceRepository.findByTags_Name(tagName);
        model.addAttribute("referenceList", references);
        return "index";
    }

    @GetMapping("/afficher-reference")
    public String AfficheRef(Model model) {
        List<Reference> references = referenceRepository.findAll();
        model.addAttribute("referenceList", references);
        return "afficher-reference";
    }
@GetMapping(path = "/delete")
public String delete(Long id){
        referenceRepository.deleteById(id);
        return "redirect:/afficher-reference";

}
//Mise a jour
    @GetMapping("/edit-reference")
    public String showEditForm(@RequestParam("id") Long id, Model model) {
        // Récupérez la référence existante en fonction de l'ID
        Reference reference = referenceRepository.findById(id).orElse(null);

        if (reference != null) {
            model.addAttribute("reference", reference);
            return "edit-reference-form"; // Affichez le formulaire de mise à jour
        } else {
            // Gérez le cas où la référence n'est pas trouvée (redirection ou message d'erreur)
            return "redirect:/afficher-reference"; // Redirigez vers la page d'affichage des références
        }
    }

    @PostMapping("/edit-reference")
    public String updateReference(@ModelAttribute Reference updatedReference) {
        // Récupérez la référence existante en fonction de l'ID
        Reference existingReference = referenceRepository.findById(updatedReference.getId()).orElse(null);

        if (existingReference != null) {
            // Mettez à jour les champs modifiés
            existingReference.setTitle(updatedReference.getTitle());
            // Mettez à jour d'autres champs si nécessaire

            // Enregistrez la référence mise à jour dans la base de données
            referenceRepository.save(existingReference);

            return "redirect:/afficher-reference"; // Redirigez vers la page d'affichage des références après la mise à jour
        } else {
            // Gérez le cas où la référence n'est pas trouvée (redirection ou message d'erreur)
            return "redirect:/afficher-reference"; // Redirigez vers la page d'affichage des références
        }
    }
//Affichage - Modification - Suppression tag


}