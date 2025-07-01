package microservice.patient.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class PatientUpdateDto {

    @Size(max = 100, message = "Le nom ne peut pas dépasser 100 caractères")
    private String nom;

    @Size(max = 100, message = "Le prénom ne peut pas dépasser 100 caractères")
    private String prenom;

    @Size(max = 100, message = "Le nom de jeune fille ne peut pas dépasser 100 caractères")
    private String nomJeuneFille;

    @Past(message = "La date de naissance doit être dans le passé")
    private LocalDate dateNaissance;

    @Size(max = 100, message = "Le lieu de naissance ne peut pas dépasser 100 caractères")
    private String lieuNaissance;

    private String sexe;

    @Size(max = 50, message = "La nationalité ne peut pas dépasser 50 caractères")
    private String nationalite;

    private String adresse;

    @Size(max = 100, message = "La ville ne peut pas dépasser 100 caractères")
    private String ville;

    @Size(max = 10, message = "Le code postal ne peut pas dépasser 10 caractères")
    private String codePostal;

    @Pattern(regexp = "^[+]?[0-9\\s\\-()]+$", message = "Format de téléphone invalide")
    @Size(max = 20, message = "Le téléphone ne peut pas dépasser 20 caractères")
    private String telephone;

    @Size(max = 20, message = "Le téléphone d'urgence ne peut pas dépasser 20 caractères")
    private String telephoneUrgence;

    @Email(message = "Format d'email invalide")
    @Size(max = 100, message = "L'email ne peut pas dépasser 100 caractères")
    private String email;

    @DecimalMin(value = "0.1", message = "La surface corporelle doit être positive")
    @DecimalMax(value = "5.0", message = "La surface corporelle ne peut pas dépasser 5.0 m²")
    private BigDecimal surfaceCorporelle;

    @DecimalMin(value = "1.0", message = "Le poids doit être supérieur à 1 kg")
    @DecimalMax(value = "500.0", message = "Le poids ne peut pas dépasser 500 kg")
    private BigDecimal poids;

    @DecimalMin(value = "30.0", message = "La taille doit être supérieure à 30 cm")
    @DecimalMax(value = "250.0", message = "La taille ne peut pas dépasser 250 cm")
    private BigDecimal taille;

    @Size(max = 5, message = "Le groupe sanguin ne peut pas dépasser 5 caractères")
    private String groupeSanguin;

    private String typePatient;

    private String statut;

    private LocalDate datePremierDiagnostic;

    @Size(max = 100, message = "Le nom du contact ne peut pas dépasser 100 caractères")
    private String contactNom;

    @Size(max = 50, message = "La relation du contact ne peut pas dépasser 50 caractères")
    private String contactRelation;

    @Size(max = 20, message = "Le téléphone du contact ne peut pas dépasser 20 caractères")
    private String contactTelephone;

    @Size(max = 100, message = "Le nom du médecin référent ne peut pas dépasser 100 caractères")
    private String medecinReferent;

    @Size(max = 100, message = "Le nom de l'assurance ne peut pas dépasser 100 caractères")
    private String assuranceNom;

    @Size(max = 50, message = "Le numéro de l'assurance ne peut pas dépasser 50 caractères")
    private String assuranceNumero;

    private Boolean isActive;

    // Getters and Setters
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


}