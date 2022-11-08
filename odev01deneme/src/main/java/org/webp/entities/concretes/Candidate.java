package org.webp.entities.concretes;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "candidates")
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "id")
public class Candidate extends User {

    @Column(name = "first_name")
    @NotBlank
    @NotNull
    private String firstName;

    @Column(name = "last_name")
    @NotBlank
    @NotNull
    private String lastName;

    @Column(name = "identity_number")
    @NotBlank
    @NotNull
    private String identityNumber;

    @Column(name = "birth_of_year")
    private int birthOfYear;

    @OneToOne(mappedBy = "candidate")
    private CandidateCv candidateCv;
}