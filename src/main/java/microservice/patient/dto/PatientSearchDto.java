package microservice.patient.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import microservice.patient.entity.Patient.StatutPatientEnum;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class PatientSearchDto {

    // Critères de recherche textuelle
    @Size(max = 100, message = "Le nom ne peut pas dépasser 100 caractères")
    private String nom;

    @Size(max = 100, message = "Le prénom ne peut pas dépasser 100 caractères")
    private String prenom;

    @Size(max = 100, message = "Le nom de jeune fille ne peut pas dépasser 100 caractères")
    private String nomJeuneFille;

    @Size(max = 50, message = "Le numéro de patient ne peut pas dépasser 50 caractères")
    private String numeroPatient;

    @Size(max = 20, message = "Le téléphone ne peut pas dépasser 20 caractères")
    private String telephone;

    @Size(max = 100, message = "L'email ne peut pas dépasser 100 caractères")
    private String email;

    // Critères de recherche par filtres
    private String sexe;

    @Size(max = 50, message = "La nationalité ne peut pas dépasser 50 caractères")
    private String nationalite;

    @Size(max = 100, message = "La ville ne peut pas dépasser 100 caractères")
    private String ville;

    @Size(max = 5, message = "Le groupe sanguin ne peut pas dépasser 5 caractères")
    private String groupeSanguin;

    private String typePatient;

    private StatutPatientEnum statutPatient;

    @Size(max = 100, message = "Le nom du médecin référent ne peut pas dépasser 100 caractères")
    private String medecinReferent;

    @Size(max = 100, message = "Le nom de l'assurance ne peut pas dépasser 100 caractères")
    private String assuranceNom;

    // Critères de recherche par dates
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateNaissanceDebut;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateNaissanceFin;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate datePremierDiagnosticDebut;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate datePremierDiagnosticFin;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateCreationDebut;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateCreationFin;

    // Critères de recherche par âge
    @Min(value = 0, message = "L'âge minimum doit être positif")
    @Max(value = 150, message = "L'âge minimum ne peut pas dépasser 150 ans")
    private Integer ageMin;

    @Min(value = 0, message = "L'âge maximum doit être positif")
    @Max(value = 150, message = "L'âge maximum ne peut pas dépasser 150 ans")
    private Integer ageMax;

    // Paramètres de pagination et tri
    @Min(value = 0, message = "Le numéro de page doit être positif")
    private int page = 0;

    @Min(value = 1, message = "La taille de page doit être supérieure à 0")
    @Max(value = 100, message = "La taille de page ne peut pas dépasser 100")
    private int size = 20;

    private String sortBy = "createdAt";

    private String sortDir = "desc";

    // Filtres booléens
    private Boolean includeDeleted = false;

    private Boolean hasAssurance;

    private Boolean hasMedecinReferent;

    private Boolean hasContact;

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

    public String getNumeroPatient() {
        return numeroPatient;
    }

    public void setNumeroPatient(String numeroPatient) {
        this.numeroPatient = numeroPatient;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
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

    public StatutPatientEnum getStatutPatient() {
        return statutPatient;
    }

    public void setStatutPatient(StatutPatientEnum statutPatient) {
        this.statutPatient = statutPatient;
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

    public LocalDate getDateNaissanceDebut() {
        return dateNaissanceDebut;
    }

    public void setDateNaissanceDebut(LocalDate dateNaissanceDebut) {
        this.dateNaissanceDebut = dateNaissanceDebut;
    }

    public LocalDate getDateNaissanceFin() {
        return dateNaissanceFin;
    }

    public void setDateNaissanceFin(LocalDate dateNaissanceFin) {
        this.dateNaissanceFin = dateNaissanceFin;
    }

    public LocalDate getDatePremierDiagnosticDebut() {
        return datePremierDiagnosticDebut;
    }

    public void setDatePremierDiagnosticDebut(LocalDate datePremierDiagnosticDebut) {
        this.datePremierDiagnosticDebut = datePremierDiagnosticDebut;
    }

    public LocalDate getDatePremierDiagnosticFin() {
        return datePremierDiagnosticFin;
    }

    public void setDatePremierDiagnosticFin(LocalDate datePremierDiagnosticFin) {
        this.datePremierDiagnosticFin = datePremierDiagnosticFin;
    }

    public LocalDate getDateCreationDebut() {
        return dateCreationDebut;
    }

    public void setDateCreationDebut(LocalDate dateCreationDebut) {
        this.dateCreationDebut = dateCreationDebut;
    }

    public LocalDate getDateCreationFin() {
        return dateCreationFin;
    }

    public void setDateCreationFin(LocalDate dateCreationFin) {
        this.dateCreationFin = dateCreationFin;
    }

    public Integer getAgeMin() {
        return ageMin;
    }

    public void setAgeMin(Integer ageMin) {
        this.ageMin = ageMin;
    }

    public Integer getAgeMax() {
        return ageMax;
    }

    public void setAgeMax(Integer ageMax) {
        this.ageMax = ageMax;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getSortDir() {
        return sortDir;
    }

    public void setSortDir(String sortDir) {
        this.sortDir = sortDir;
    }

    public Boolean getIncludeDeleted() {
        return includeDeleted;
    }

    public void setIncludeDeleted(Boolean includeDeleted) {
        this.includeDeleted = includeDeleted;
    }

    public Boolean getHasAssurance() {
        return hasAssurance;
    }

    public void setHasAssurance(Boolean hasAssurance) {
        this.hasAssurance = hasAssurance;
    }

    public Boolean getHasMedecinReferent() {
        return hasMedecinReferent;
    }

    public void setHasMedecinReferent(Boolean hasMedecinReferent) {
        this.hasMedecinReferent = hasMedecinReferent;
    }

    public Boolean getHasContact() {
        return hasContact;
    }

    public void setHasContact(Boolean hasContact) {
        this.hasContact = hasContact;
    }
}