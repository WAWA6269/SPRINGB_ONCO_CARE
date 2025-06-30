package microservice.patient.dto;

import java.time.LocalDateTime;

public class PatientSearchResultDto {
    private Long id;
    private String uuid;
    private String numeroPatient;
    private String nom;
    private String prenom;
    private LocalDateTime dateNaissance;
    private String telephone;
    private String email;
    private String ville;
    private String typePatient;
    private String statut;
    private String medecinReferent;

    // Constructeur par défaut
    public PatientSearchResultDto() {}

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUuid() { return uuid; }
    public void setUuid(String uuid) { this.uuid = uuid; }

    public String getNumeroPatient() { return numeroPatient; }
    public void setNumeroPatient(String numeroPatient) { this.numeroPatient = numeroPatient; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public LocalDateTime getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(LocalDateTime dateNaissance) { this.dateNaissance = dateNaissance; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getVille() { return ville; }
    public void setVille(String ville) { this.ville = ville; }

    public String getTypePatient() { return typePatient; }
    public void setTypePatient(String typePatient) { this.typePatient = typePatient; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

    public String getMedecinReferent() { return medecinReferent; }
    public void setMedecinReferent(String medecinReferent) { this.medecinReferent = medecinReferent; }
}