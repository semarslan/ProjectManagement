package com.semarslan.projectmanagement.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Project name is required")
    private String projectName;

    @NotBlank(message = "Project Identifier is required")
    @Size(min = 4, max = 5, message = "Please use 4 to 5 characters")
    @Column(updatable = false, unique = true)
    private String projectIdentifier;

    @NotBlank(message = "Project description is required")
    private String description;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date startDate;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date endDate;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date created_at;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date updated_at;

    @PrePersist //Entity kayıt edilmeden önce çalıştırılacak metodu ifade eder.
    protected void onCreate(){
        this.created_at = new Date();
    }

    @PreUpdate //Entity güncellenmeden önce çalıştırılacak metodu ifade eder.
    protected void onUpdate(){
        this.updated_at = new Date();
    }
}


//jdbc:h2:mem:testdb