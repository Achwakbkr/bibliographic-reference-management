package com.reference.app.repository;



import com.reference.app.entities.Reference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReferenceRepository extends JpaRepository<Reference,Long> {
    List<Reference> findByTags_Name(String tagName);
}
