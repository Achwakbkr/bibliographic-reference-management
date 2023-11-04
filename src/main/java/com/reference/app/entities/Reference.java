package com.reference.app.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "Reference")
public class Reference {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "Id_Reference")
    private Long id;

    private String title;
    private String authors;
    private String journal;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date year;
    private String hyperlink;
    private String description;

    @ManyToMany
    @JoinTable(name = "referencetag",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_t"))
    private Set<Tag> tags = new HashSet<>();



    public Reference(Long id, String title, String authors, String journal, Date year, String hyperlink, String description) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.journal = journal;
        this.year = year;
        this.hyperlink = hyperlink;
        this.description = description;
    }
    public Reference() {
        // Constructeur par défaut vide
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public void setHyperlink(String hyperlink) {
        this.hyperlink = hyperlink;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthors() {
        return authors;
    }

    public String getJournal() {
        return journal;
    }

    public Date getYear() {
        return year;
    }

    public String getHyperlink() {
        return hyperlink;
    }

    public String getDescription() {
        return description;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }
}

