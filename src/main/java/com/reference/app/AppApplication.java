package com.reference.app;

import com.reference.app.entities.Reference;
import com.reference.app.entities.Tag;
import com.reference.app.repository.ReferenceRepository;
import com.reference.app.repository.TagRepository;
import com.sun.xml.bind.v2.runtime.unmarshaller.TagName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

@SpringBootApplication
public class AppApplication implements CommandLineRunner {
	@Autowired
	private ReferenceRepository referenceRepository;
	@Autowired
	private TagRepository tagRepository;

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Scanner scanner = new Scanner(System.in);

		System.out.println("Voulez-vous effectuer une insertion (oui) ou un affichage (non) ?");
		String choice = scanner.nextLine();

		if ("oui".equalsIgnoreCase(choice)) {
			// Création d'une référence
			Reference reference = new Reference(null, "Refernce info", "martin", "info pratique", new Date(), "httpinfo.com", "Education ing");
			referenceRepository.save(reference);

			// Création d'un tag
			Tag tag = new Tag();
			tag.setName("important");
			//TagRepository.save(tag);
			tagRepository.save(tag);


			// Association de la référence au tag
			reference.getTags().add(tag);
			tag.getReferences().add(reference);


			referenceRepository.save(reference);
			tagRepository.save(tag);

			//reference.getTags().add(tag);


			System.out.println("Référence ajoutée et associée au tag !");
		} else if ("non".equalsIgnoreCase(choice)) {
			// ... reste du code pour l'affichage ...
System.out.println("donner le nom de tag :");
			String tagName = scanner.nextLine();
			List<Reference> references = referenceRepository.findByTags_Name(tagName);
			System.out.println("Références avec le tag '" + tagName + "' : ");
			for (Reference reference : references) {
				System.out.println(reference.getDescription());
			}


		} else {
			System.out.println("Choix invalide. Veuillez choisir 'oui' pour insertion ou 'non' pour affichage.");
		}

		scanner.close();
	}
}
