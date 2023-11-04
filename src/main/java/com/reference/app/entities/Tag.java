package com.reference.app.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Tag")
public class Tag {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id_t;

    private String name;

    @ManyToMany(mappedBy = "tags")
    private Set<Reference> references = new HashSet<>();

    public Set<Reference> getReferences() {
        return references;
    }

    public void setReferences(Set<Reference> references) {
        this.references = references;
    }

    public Long getId_t() {
        return id_t;
    }

    public void setId_t(Long id_t) {
        this.id_t = id_t;
    }

    public Tag() {

    }
    public Tag(Long id, String name) {
        this.id_t = id;
        this.name = name;
    }

    public void setId(Long id) {
        this.id_t = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id_t;
    }

    public String getName() {
        return name;
    }

}
