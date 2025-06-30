package microservice.patient.dto;

import java.time.LocalDateTime;

public class PatientEnrichedDto {
    private Long id;
    private String uuid;
    private String numeroPatient;
    private String nomComplet;
    private Integer age;
    private String adresseComplete;
    private String statut;
    private String typePatient;
    private String medecinReferent;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructeur par défaut
    public PatientEnrichedDto() {}

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUuid() { return uuid; }
    public void setUuid(String uuid) { this.uuid = uuid; }

    public String getNumeroPatient() { return numeroPatient; }
    public void setNumeroPatient(String numeroPatient) { this.numeroPatient = numeroPatient; }

    public String getNomComplet() { return nomComplet; }
    public void setNomComplet(String nomComplet) { this.nomComplet = nomComplet; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public String getAdresseComplete() { return adresseComplete; }
    public void setAdresseComplete(String adresseComplete) { this.adresseComplete = adresseComplete; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

    public String getTypePatient() { return typePatient; }
    public void setTypePatient(String typePatient) { this.typePatient = typePatient; }

    public String getMedecinReferent() { return medecinReferent; }
    public void setMedecinReferent(String medecinReferent) { this.medecinReferent = medecinReferent; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}