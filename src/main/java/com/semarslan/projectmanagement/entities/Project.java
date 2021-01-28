package com.semarslan.projectmanagement.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String projectName;

    private String projectIdentifier;

    private String description;

    private Date startDate;

    private Date endDate;

    private Date created_at;

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