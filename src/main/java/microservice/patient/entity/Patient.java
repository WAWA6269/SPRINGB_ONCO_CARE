package microservice.patient.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @Column(name = "date_premier_diagnostic")
    private LocalDate datePremierDiagnostic;

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

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "created_by", length = 100)
    private String createdBy;

    @Column(name = "updated_by", length = 100)
    private String updatedBy;

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
            // Formule de Dubois: SC = 0.007184 × Poids^0.425 × Taille^0.725
            double sc = 0.007184 * Math.pow(poids.doubleValue(), 0.425) *
                    Math.pow(taille.doubleValue(), 0.725);
            this.surfaceCorporelle = BigDecimal.valueOf(sc).setScale(2, BigDecimal.ROUND_HALF_UP);
        }
    }

    public void calculerImc() {
        if (poids != null && taille != null) {
            double tailleM = taille.doubleValue() / 100; // Conversion cm en m
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
    } //IL y a une enumération similaire dan le patientservice



    // Constructeurs, getters et setters
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

    // Constructeur surchargé avec plus de paramètres
    public Patient(String nom,
                   String prenom,
                   LocalDate dateNaissance,
                   SexeEnum sexe,
                   TypePatientEnum typePatient,
                   String telephone,
                   String email,
                   String medecinReferent) {
        this(nom, prenom, dateNaissance, sexe, typePatient); // Appel du constructeur principal
        this.telephone = telephone;
        this.email = email;
        this.medecinReferent = medecinReferent;
    }

    // Constructeur par défaut (déjà présent dans votre code)
    public Patient() {
        this.uuid = UUID.randomUUID().toString();
        this.isActive = true;
    }
    // Getters et Setters (générés automatiquement par l'IDE)

    // Getters et Setters basiques
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMedecinReferent() {
        return medecinReferent;
    }

    public void setMedecinReferent(String medecinReferent) {
        this.medecinReferent = medecinReferent;
    }

    public StatutPatientEnum getStatut() {
        return statut;
    }

    public void setStatut(StatutPatientEnum statut) {
        this.statut = statut;
    }

    public TypePatientEnum getTypePatient() {
        return typePatient;
    }

    public void setTypePatient(TypePatientEnum typePatient) {
        this.typePatient = typePatient;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    // Ajout de l'énumération StatutPatientEnum qui était commentée
    public enum StatutPatientEnum {
        ACTIF,
        INACTIF,
        EN_TRAITEMENT,
        REMISSION,
        DECEDE
    }

    // ... (tous les getters et setters pour chaque champ)
}