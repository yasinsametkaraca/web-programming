package org.webp.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "candidateCv")
public class CandidateCv {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "description")
    @NotBlank
    @NotNull
    private String description;

    @Column(name = "url")
    private String url;

    @Column(name = "github_address")
    @NotBlank
    @NotNull
    private String githubAddress;

    @Column(name = "linkedin_address")
    @NotBlank
    @NotNull
    private String linkedinAddress;

    @OneToOne()
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

}