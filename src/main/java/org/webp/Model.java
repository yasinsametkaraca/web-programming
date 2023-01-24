package org.webp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Model {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Size(max = 128)
    private String name;

    @ManyToOne
    @NotNull
    private Brand parent;

    public Model(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Brand getParent() {
        return parent;
    }

    public void setParent(Brand parent) {
        this.parent = parent;
    }
}
