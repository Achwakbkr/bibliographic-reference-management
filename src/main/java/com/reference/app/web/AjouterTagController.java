package com.reference.app.web;

import com.reference.app.entities.Reference;
import com.reference.app.entities.Tag;
import com.reference.app.repository.ReferenceRepository;
import com.reference.app.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AjouterTagController {

    private final TagRepository tagRepository;
    @Autowired
    public AjouterTagController(TagRepository tagRepository){
        this.tagRepository= tagRepository;
    }
    @GetMapping("/ajouter-tag")
    public String ajouterTagForm(Model model) {
        // Créez un objet Reference vide pour le formulaire
        Reference reference = new Reference();
        Tag tag = new Tag();
        model.addAttribute("tag", tag);
        return "ajouter-tag"; // Le nom de la page d'ajout de référence (ajouter-reference.html)
    }

    @PostMapping("/ajouter-tag")
    public String ajouterTag(@ModelAttribute Tag tag) {
        // Code pour ajouter la référence à la base de données (utilisez le repository)
        // Réalisez l'ajout de la référence à la base de données ici
         // Sauvegardez la référence dans la base de données
        tagRepository.save(tag);
        return "/home"; // Redirigez l'utilisateur vers la page d'accueil après l'ajout
    }

    @GetMapping("/afficher-tag")
    public String Affichetag(Model model) {
        List<Tag> tags = tagRepository.findAll();
        model.addAttribute("tagList", tags);
        return "afficher-tag";
    }
    @GetMapping(path = "/deletet")
    public String deletetag(Long id){ tagRepository.deleteById(id);

        return "redirect:/afficher-tag";}



    @GetMapping("/edit-tag")
    public String showEditForm(@RequestParam("id") Long id, Model model) {
        // Récupérez la référence existante en fonction de l'ID

        Tag tag= tagRepository.findById(id).orElse(null);

        if (tag != null) {
            model.addAttribute("tag", tag);
            return "edit-tag-form"; // Affichez le formulaire de mise à jour
        } else {
            // Gérez le cas où la référence n'est pas trouvée (redirection ou message d'erreur)
            return "redirect:/afficher-tag"; // Redirigez vers la page d'affichage des références
        }
    }

    @PostMapping("/edit-tag")
    public String updateTag(@ModelAttribute Tag updatedTag ) {
        // Récupérez la référence existante en fonction de l'ID

        Tag existingTag = tagRepository.findById(updatedTag.getId()).orElse(null);

        if (existingTag  != null) {
            // Mettez à jour les champs modifiés
            existingTag.setId_t(updatedTag.getId_t());
            existingTag.setName(updatedTag.getName());

            // Mettez à jour d'autres champs si nécessaire

            // Enregistrez la référence mise à jour dans la base de données
            tagRepository.save(existingTag);


            return "redirect:/afficher-tag"; // Redirigez vers la page d'affichage des références après la mise à jour
        } else {
            // Gérez le cas où la référence n'est pas trouvée (redirection ou message d'erreur)
            return "redirect:/afficher-tag"; // Redirigez vers la page d'affichage des références
        }
    }

}
