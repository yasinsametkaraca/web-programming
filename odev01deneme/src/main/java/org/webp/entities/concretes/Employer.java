package org.webp.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
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
@Table(name = "employers")
@PrimaryKeyJoinColumn(name = "user_id")
public class Employer extends User {

    @Column(name = "company_name")
    @NotBlank
    @NotNull
    private String companyName;

    @Column(name = "web_address")
    @NotBlank
    @NotNull
    private String webAddress;

    @Column(name = "phone_number")
    @NotBlank
    @NotNull
    private String phoneNumber;

    @Column(name = "is_activated")
    private boolean isActivated;

    @OneToMany(mappedBy = "employer")
    private List<JobAdvert> jobAdverts;

}