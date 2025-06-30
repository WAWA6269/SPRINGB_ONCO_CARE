package microservice.patient.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class PatientResponseDto {

    private Long id;

    private String numeroPatient;

    private String uuid;

    private String nom;

    private String prenom;

    private String nomJeuneFille;

    private LocalDate dateNaissance;

    private String lieuNaissance;

    private String sexe;

    private String nationalite;

    private String adresse;

    private String ville;

    private String codePostal;

    private String telephone;

    private String telephoneUrgence;

    private String email;

    private BigDecimal surfaceCorporelle;

    private BigDecimal poids;

    private BigDecimal taille;

    private BigDecimal imc;

    private String groupeSanguin;

    private String typePatient;

    private String statut;

    private LocalDate datePremierDiagnostic;

    private String contactNom;

    private String contactRelation;

    private String contactTelephone;

    private String medecinReferent;

    private String assuranceNom;

    private String assuranceNumero;

    private Boolean isActive;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroPatient() {
        return numeroPatient;
    }

    public void setNumeroPatient(String numeroPatient) {
        this.numeroPatient = numeroPatient;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNomJeuneFille() {
        return nomJeuneFille;
    }

    public void setNomJeuneFille(String nomJeuneFille) {
        this.nomJeuneFille = nomJeuneFille;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTelephoneUrgence() {
        return telephoneUrgence;
    }

    public void setTelephoneUrgence(String telephoneUrgence) {
        this.telephoneUrgence = telephoneUrgence;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getSurfaceCorporelle() {
        return surfaceCorporelle;
    }

    public void setSurfaceCorporelle(BigDecimal surfaceCorporelle) {
        this.surfaceCorporelle = surfaceCorporelle;
    }

    public BigDecimal getPoids() {
        return poids;
    }

    public void setPoids(BigDecimal poids) {
        this.poids = poids;
    }

    public BigDecimal getTaille() {
        return taille;
    }

    public void setTaille(BigDecimal taille) {
        this.taille = taille;
    }

    public BigDecimal getImc() {
        return imc;
    }

    public void setImc(BigDecimal imc) {
        this.imc = imc;
    }

    public String getGroupeSanguin() {
        return groupeSanguin;
    }

    public void setGroupeSanguin(String groupeSanguin) {
        this.groupeSanguin = groupeSanguin;
    }

    public String getTypePatient() {
        return typePatient;
    }

    public void setTypePatient(String typePatient) {
        this.typePatient = typePatient;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public LocalDate getDatePremierDiagnostic() {
        return datePremierDiagnostic;
    }

    public void setDatePremierDiagnostic(LocalDate datePremierDiagnostic) {
        this.datePremierDiagnostic = datePremierDiagnostic;
    }

    public String getContactNom() {
        return contactNom;
    }

    public void setContactNom(String contactNom) {
        this.contactNom = contactNom;
    }

    public String getContactRelation() {
        return contactRelation;
    }

    public void setContactRelation(String contactRelation) {
        this.contactRelation = contactRelation;
    }

    public String getContactTelephone() {
        return contactTelephone;
    }

    public void setContactTelephone(String contactTelephone) {
        this.contactTelephone = contactTelephone;
    }

    public String getMedecinReferent() {
        return medecinReferent;
    }

    public void setMedecinReferent(String medecinReferent) {
        this.medecinReferent = medecinReferent;
    }

    public String getAssuranceNom() {
        return assuranceNom;
    }

    public void setAssuranceNom(String assuranceNom) {
        this.assuranceNom = assuranceNom;
    }

    public String getAssuranceNumero() {
        return assuranceNumero;
    }

    public void setAssuranceNumero(String assuranceNumero) {
        this.assuranceNumero = assuranceNumero;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}