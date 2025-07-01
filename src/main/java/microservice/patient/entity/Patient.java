package microservice.patient.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.UUID;

@Entity
@Table(name = "patients", indexes = {
        @Index(name = "idx_numero_patient", columnList = "numeroPatient"),
        @Index(name = "idx_nom", columnList = "nom"),
        @Index(name = "idx_telephone", columnList = "telephone"),
        @Index(name = "idx_email", columnList = "email")
})
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_patient", unique = true, nullable = false, length = 20)
    private String numeroPatient;

    @Column(name = "uuid", unique = true, nullable = false)
    private String uuid = UUID.randomUUID().toString();

    // Informations personnelles
    @Column(name = "nom", nullable = false, length = 100)
    @NotBlank(message = "Le nom est obligatoire")
    @Size(max = 100, message = "Le nom ne peut pas dépasser 100 caractères")
    private String nom;

    @Column(name = "prenom", nullable = false, length = 100)
    @NotBlank(message = "Le prénom est obligatoire")
    @Size(max = 100, message = "Le prénom ne peut pas dépasser 100 caractères")
    private String prenom;

    @Column(name = "nom_jeune_fille", length = 100)
    private String nomJeuneFille;

    @Column(name = "date_naissance", nullable = false)
    @NotNull(message = "La date de naissance est obligatoire")
    @Past(message = "La date de naissance doit être dans le passé")
    private LocalDate dateNaissance;

    @Column(name = "lieu_naissance", length = 100)
    private String lieuNaissance;

    @Enumerated(EnumType.STRING)
    @Column(name = "sexe", nullable = false)
    @NotNull(message = "Le sexe est obligatoire")
    private SexeEnum sexe;

    @Column(name = "nationalite", length = 50)
    private String nationalite = "Cameroun";

    // Contact
    @Column(name = "adresse", columnDefinition = "TEXT")
    private String adresse;

    @Column(name = "ville", length = 100)
    private String ville;

    @Column(name = "code_postal", length = 10)
    private String codePostal;

    @Column(name = "telephone", length = 20)
    @Pattern(regexp = "^[+]?[0-9\\s\\-()]+$", message = "Format de téléphone invalide")
    private String telephone;

    @Column(name = "telephone_urgence", length = 20)
    private String telephoneUrgence;

    @Column(name = "email", length = 100)
    @Email(message = "Format d'email invalide")
    private String email;

    // Informations médicales
    @Column(name = "surface_corporelle", precision = 5, scale = 2)
    @DecimalMin(value = "0.1", message = "La surface corporelle doit être positive")
    @DecimalMax(value = "5.0", message = "La surface corporelle ne peut pas dépasser 5.0 m²")
    private BigDecimal surfaceCorporelle;

    @Column(name = "poids", precision = 5, scale = 2)
    @DecimalMin(value = "1.0", message = "Le poids doit être supérieur à 1 kg")
    @DecimalMax(value = "500.0", message = "Le poids ne peut pas dépasser 500 kg")
    private BigDecimal poids;

    @Column(name = "taille", precision = 5, scale = 2)
    @DecimalMin(value = "30.0", message = "La taille doit être supérieure à 30 cm")
    @DecimalMax(value = "250.0", message = "La taille ne peut pas dépasser 250 cm")
    private BigDecimal taille;

    @Column(name = "imc", precision = 5, scale = 2)
    private BigDecimal imc;

    @Column(name = "groupe_sanguin", length = 5)
    private String groupeSanguin;

    // Classification oncologique
    @Enumerated(EnumType.STRING)
    @Column(name = "type_patient", nullable = false)
    @NotNull(message = "Le type de patient est obligatoire")
    private TypePatientEnum typePatient;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut")
    private StatutPatientEnum statut = StatutPatientEnum.ACTIF;

    // Personne à contacter
    @Column(name = "contact_nom", length = 100)
    private String contactNom;

    @Column(name = "contact_relation", length = 50)
    private String contactRelation;

    @Column(name = "contact_telephone", length = 20)
    private String contactTelephone;

    // Médecin référent
    @Column(name = "medecin_referent", length = 100)
    private String medecinReferent;

    // Assurance
    @Column(name = "assurance_nom", length = 100)
    private String assuranceNom;

    @Column(name = "assurance_numero", length = 50)
    private String assuranceNumero;

    // Métadonnées
    @Column(name = "is_active")
    private Boolean isActive = true;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Setter
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "created_by", length = 100)
    private String createdBy;

    @Column(name = "updated_by", length = 100)
    private String updatedBy;

    // Enums
    public enum SexeEnum {
        MASCULIN("M"),
        FEMININ("F"),
        AUTRE("A");

        private final String code;

        SexeEnum(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }

    public enum TypePatientEnum {
        RISQUE,
        DIAGNOSTIC,
        DIAGNOSTIQUE,
        TRAITEMENT
    }

    public enum StatutPatientEnum {
        ACTIF,
        INACTIF,
        EN_TRAITEMENT,
        REMISSION,
        DECEDE
    }

    // Constructeurs
    public Patient() {
        this.uuid = UUID.randomUUID().toString();
        this.isActive = true;
    }

    public Patient(String nom, String prenom, LocalDate dateNaissance, SexeEnum sexe, TypePatientEnum typePatient) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.sexe = sexe;
        this.typePatient = typePatient;
        this.uuid = UUID.randomUUID().toString();
        this.isActive = true;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Patient(String nom,
                   String prenom,
                   LocalDate dateNaissance,
                   SexeEnum sexe,
                   TypePatientEnum typePatient,
                   String telephone,
                   String email,
                   String medecinReferent) {
        this(nom, prenom, dateNaissance, sexe, typePatient);
        this.telephone = telephone;
        this.email = email;
        this.medecinReferent = medecinReferent;
    }

    // Méthodes calculées
    public String getNomComplet() {
        return prenom + " " + nom;
    }

    public int getAge() {
        if (dateNaissance == null) {
            return 0;
        }
        return Period.between(dateNaissance, LocalDate.now()).getYears();
    }

    public void calculerSurfaceCorporelle() {
        if (poids != null && taille != null) {
            double sc = 0.007184 * Math.pow(poids.doubleValue(), 0.425) *
                    Math.pow(taille.doubleValue(), 0.725);
            this.surfaceCorporelle = BigDecimal.valueOf(sc).setScale(2, BigDecimal.ROUND_HALF_UP);
        }
    }

    public void calculerImc() {
        if (poids != null && taille != null) {
            double tailleM = taille.doubleValue() / 100;
            double imcValue = poids.doubleValue() / (tailleM * tailleM);
            this.imc = BigDecimal.valueOf(imcValue).setScale(2, BigDecimal.ROUND_HALF_UP);
        }
    }

    @PrePersist
    @PreUpdate
    private void calculateValues() {
        calculerSurfaceCorporelle();
        calculerImc();
    }

    // TOUS LES GETTERS ET SETTERS
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

    public SexeEnum getSexe() {
        return sexe;
    }

    public void setSexe(SexeEnum sexe) {
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

    public TypePatientEnum getTypePatient() {
        return typePatient;
    }

    public void setTypePatient(TypePatientEnum typePatient) {
        this.typePatient = typePatient;
    }

    public StatutPatientEnum getStatut() {
        return statut;
    }

    public void setStatut(StatutPatientEnum statut) {
        this.statut = statut;
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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}