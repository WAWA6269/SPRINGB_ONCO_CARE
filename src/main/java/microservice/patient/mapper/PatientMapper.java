package microservice.patient.mapper;

import microservice.patient.dto.*;
import microservice.patient.entity.*;
import org.mapstruct.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface PatientMapper {

    // ========== CONVERSION ENTITY -> DTO ==========
    // ========== CONVERSION ENTITY -> DTO ==========
    /*
     * Convertit une entité Patient vers PatientResponseDto
     */
    @Mapping(target = "id", source = "id")
    @Mapping(target = "uuid", source = "uuid")
    @Mapping(target = "numeroPatient", source = "numeroPatient")
    @Mapping(target = "nom", source = "nom")
    @Mapping(target = "prenom", source = "prenom")
    @Mapping(target = "nomJeuneFille", source = "nomJeuneFille")
    @Mapping(target = "dateNaissance", source = "dateNaissance")
    @Mapping(target = "lieuNaissance", source = "lieuNaissance")
    @Mapping(target = "sexe", source = "sexe")
    @Mapping(target = "nationalite", source = "nationalite")
    @Mapping(target = "adresse", source = "adresse")
    @Mapping(target = "ville", source = "ville")
    @Mapping(target = "codePostal", source = "codePostal")
    @Mapping(target = "telephone", source = "telephone")
    @Mapping(target = "telephoneUrgence", source = "telephoneUrgence")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "surfaceCorporelle", source = "surfaceCorporelle")
    @Mapping(target = "poids", source = "poids")
    @Mapping(target = "taille", source = "taille")
    @Mapping(target = "imc", source = "imc")
    @Mapping(target = "groupeSanguin", source = "groupeSanguin")
    @Mapping(target = "typePatient", source = "typePatient")
    @Mapping(target = "statut", source = "statut")
    @Mapping(target = "contactNom", source = "contactNom")
    @Mapping(target = "contactRelation", source = "contactRelation")
    @Mapping(target = "contactTelephone", source = "contactTelephone")
    @Mapping(target = "medecinReferent", source = "medecinReferent")
    @Mapping(target = "assuranceNom", source = "assuranceNom")
    @Mapping(target = "assuranceNumero", source = "assuranceNumero")
    @Mapping(target = "isActive", source = "isActive")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
// Champs présents dans PatientResponseDto mais absents de l'entité Patient - doivent être mappés à null ou ignorés
    @Mapping(target = "datePremierDiagnostic", ignore = true)
    PatientResponseDto toResponseDTO(Patient patient);


    /**
     * Convertit une liste d'entités Patient vers une liste de PatientResponseDto
     */
    List<PatientResponseDto> toResponseDTOList(List<Patient> patients);

    // ========== CONVERSION DTO -> ENTITY ==========

    /**
     * Convertit PatientCreateDto vers une entité Patient
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "numeroPatient", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "nom", source = "nom")
    @Mapping(target = "prenom", source = "prenom")
    @Mapping(target = "dateNaissance", source = "dateNaissance")
    @Mapping(target = "lieuNaissance", source = "lieuNaissance")
    @Mapping(target = "sexe", source = "sexe")
    @Mapping(target = "nationalite", source = "nationalite")
    @Mapping(target = "adresse", source = "adresse")
    @Mapping(target = "ville", source = "ville")
    @Mapping(target = "telephone", source = "telephone")
    @Mapping(target = "telephoneUrgence", source = "telephoneUrgence")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "surfaceCorporelle", source = "surfaceCorporelle")
    @Mapping(target = "poids", source = "poids")
    @Mapping(target = "taille", source = "taille")
    @Mapping(target = "groupeSanguin", source = "groupeSanguin")
    //@Mapping(target = "pays", source = "pays")
    @Mapping(target = "typePatient", source = "typePatient")
    @Mapping(target = "contactNom", source = "contactNom")
    @Mapping(target = "contactRelation", source = "contactRelation")
    @Mapping(target = "contactTelephone", source = "contactTelephone")
    //@Mapping(target = "statut", source = "statut")
    @Mapping(target = "medecinReferent", source = "medecinReferent")
    //@Mapping(target = "allergies", source = "allergies")
    //@Mapping(target = "antecedentsMedicaux", source = "antecedentsMedicaux")
    //@Mapping(target = "contactUrgence", source = "contactUrgence")
    @Mapping(target = "assuranceNom", source = "assuranceNom")
    @Mapping(target = "assuranceNumero", source = "assuranceNumero")
    //@Mapping(target = "notes", source = "notes")
    Patient toEntity(PatientCreateDto createDto);

    // ========== UPDATE MAPPING ==========

    /*
     * Met à jour une entité Patient existante avec les données de PatientUpdateDto
     * Les champs null dans le DTO ne modifient pas l'entité existante
     */

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "numeroPatient", ignore = true)
    @Mapping(target = "sexe", source = "sexe", qualifiedByName = "stringToSexeEnum")
    @Mapping(target = "typePatient", source = "typePatient", qualifiedByName = "stringToTypePatientEnum")
    @Mapping(target = "statut", source = "statut", qualifiedByName = "stringToStatutPatientEnum")
    //@Mapping(target = "datePremierDiagnostic", ignore = true) // Ce champ n'existe pas dans Patient
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "imc", ignore = true) // Calculé automatiquement
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(PatientUpdateDto updateDto, @MappingTarget Patient patient);

    // ========== MAPPINGS SPÉCIALISÉS ==========

    /*
     * Convertit Patient vers PatientSummaryDto (version allégée)
     */

    // Mapper pour le summary
    @Mapping(target = "id", source = "id")
    @Mapping(target = "uuid", source = "uuid")
    @Mapping(target = "numeroPatient", source = "numeroPatient")
    @Mapping(target = "nom", source = "nom")
    @Mapping(target = "prenom", source = "prenom")
    @Mapping(target = "dateNaissance", source = "dateNaissance", qualifiedByName = "localDateToLocalDateTime")
    @Mapping(target = "sexe", source = "sexe", qualifiedByName = "sexeEnumToString")
    @Mapping(target = "telephone", source = "telephone")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "typePatient", source = "typePatient", qualifiedByName = "typePatientEnumToString")
    //@Mapping(target = "statut", source = "statut", qualifiedByName = "statutPatientEnumToString")
    @Mapping(target = "medecinReferent", source = "medecinReferent")
    PatientSummaryDto toSummaryDTO(Patient patient);

    /*
     * Convertit une liste de Patient vers PatientSummaryDto
     */
//    List<PatientSummaryDto> toSummaryDTOList(List<Patient> patients);

    // ========== MÉTHODES UTILITAIRES ==========

    /*
     * Méthode personnalisée pour calculer l'âge si nécessaire
     */
/*    @Named("calculateAge")
    default Integer calculateAge(LocalDateTime dateNaissance) {
        if (dateNaissance == null) {
            return null;
        }
        return LocalDateTime.now().getYear() - dateNaissance.getYear();
    }

    /**
     * Formatage du nom complet
     */
/*    @Named("formatFullName")
    default String formatFullName(String nom, String prenom) {
        if (nom == null && prenom == null) {
            return null;
        }
        if (nom == null) {
            return prenom;
        }
        if (prenom == null) {
            return nom;
        }
        return prenom + " " + nom.toUpperCase();
    }

    /**
     * Formatage de l'adresse complète
     */
/*    @Named("formatFullAddress")
    default String formatFullAddress(String adresse, String ville, String codePostal, String pays) {
        StringBuilder fullAddress = new StringBuilder();

        if (adresse != null && !adresse.trim().isEmpty()) {
            fullAddress.append(adresse);
        }

        if (ville != null && !ville.trim().isEmpty()) {
            if (fullAddress.length() > 0) fullAddress.append(", ");
            fullAddress.append(ville);
        }

        if (codePostal != null && !codePostal.trim().isEmpty()) {
            if (fullAddress.length() > 0) fullAddress.append(" ");
            fullAddress.append(codePostal);
        }

        if (pays != null && !pays.trim().isEmpty()) {
            if (fullAddress.length() > 0) fullAddress.append(", ");
            fullAddress.append(pays);
        }

        return fullAddress.length() > 0 ? fullAddress.toString() : null;
    }

    // ========== MAPPING AVEC CALCULS AVANCÉS ==========

    /**
     * Mapping avec informations calculées (pour les DTOs enrichis)
     */
/*    @Mapping(target = "id", source = "id")
    @Mapping(target = "uuid", source = "uuid")
    @Mapping(target = "numeroPatient", source = "numeroPatient")
    @Mapping(target = "nomComplet", source = ".", qualifiedByName = "formatFullName")
    @Mapping(target = "age", source = "dateNaissance", qualifiedByName = "calculateAge")
    @Mapping(target = "adresseComplete", source = ".", qualifiedByName = "formatFullAddress")
    @Mapping(target = "statut", source = "statut")
    @Mapping(target = "typePatient", source = "typePatient")
    @Mapping(target = "medecinReferent", source = "medecinReferent")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    PatientEnrichedDto toEnrichedDTO(Patient patient);

    // ========== MAPPINGS POUR RECHERCHE ==========

    /**
     * Conversion spécifique pour les résultats de recherche
     */
/*    @Mapping(target = "id", source = "id")
    @Mapping(target = "uuid", source = "uuid")
    @Mapping(target = "numeroPatient", source = "numeroPatient")
    @Mapping(target = "nom", source = "nom")
    @Mapping(target = "prenom", source = "prenom")
    @Mapping(target = "dateNaissance", source = "dateNaissance")
    @Mapping(target = "telephone", source = "telephone")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "ville", source = "ville")
    @Mapping(target = "typePatient", source = "typePatient")
    @Mapping(target = "statut", source = "statut")
    @Mapping(target = "medecinReferent", source = "medecinReferent")
    PatientSearchResultDto toSearchResultDTO(Patient patient);*/

    /**
     * Conversion pour liste de résultats de recherche
     */
//    List<PatientSearchResultDto> toSearchResultDTOList(List<Patient> patients);

    // Méthodes de conversion pour les enums
    @Named("stringToSexeEnum")
    default Patient.SexeEnum stringToSexeEnum(String sexe) {
        if (sexe == null) return null;
        try {
            return Patient.SexeEnum.valueOf(sexe.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @Named("stringToTypePatientEnum")
    default Patient.TypePatientEnum stringToTypePatientEnum(String typePatient) {
        if (typePatient == null) return null;
        try {
            return Patient.TypePatientEnum.valueOf(typePatient.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @Named("stringToStatutPatientEnum")
    default Patient.StatutPatientEnum stringToStatutPatientEnum(String statut) {
        if (statut == null) return null;
        try {
            return Patient.StatutPatientEnum.valueOf(statut.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @Named("sexeEnumToString")
    default String sexeEnumToString(Patient.SexeEnum sexe) {
        return sexe != null ? sexe.name() : null;
    }

    @Named("typePatientEnumToString")
    default String typePatientEnumToString(Patient.TypePatientEnum typePatient) {
        return typePatient != null ? typePatient.name() : null;
    }

    @Named("statutPatientEnumToString")
    default String statutPatientEnumToString(Patient.StatutPatientEnum statut) {
        return statut != null ? statut.name() : null;
    }

    @Named("localDateToLocalDateTime")
    default LocalDateTime localDateToLocalDateTime(LocalDate date) {
        return date != null ? date.atStartOfDay() : null;
    }
}