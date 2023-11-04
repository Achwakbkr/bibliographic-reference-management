package com.reference.app.web;

import com.reference.app.entities.Reference;
import com.reference.app.repository.ReferenceRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Controller
public class RechercherParTagController {
    private ReferenceRepository referenceRepository;

    public RechercherParTagController(ReferenceRepository referenceRepository) {
        this.referenceRepository = referenceRepository;
    }

    @GetMapping("/rechercher-par-tag")
    public String searchByTag(@RequestParam("tag") String tagName, Model model)
    {
        List<Reference> references = referenceRepository.findByTags_Name(tagName);
        model.addAttribute("referenceList", references);
        return "rechercher-par-tag";
    }
}
