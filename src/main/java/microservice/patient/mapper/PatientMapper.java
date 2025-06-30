package microservice.patient.mapper;

import microservice.patient.dto.*;
import microservice.patient.entity.Patient;
import org.mapstruct.*;

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

    /**
     * Convertit une entité Patient vers PatientResponseDto
     */
    @Mapping(target = "id", source = "id")
    @Mapping(target = "uuid", source = "uuid")
    @Mapping(target = "numeroPatient", source = "numeroPatient")
    @Mapping(target = "nom", source = "nom")
    @Mapping(target = "prenom", source = "prenom")
    @Mapping(target = "dateNaissance", source = "dateNaissance")
    @Mapping(target = "sexe", source = "sexe")
    @Mapping(target = "telephone", source = "telephone")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "adresse", source = "adresse")
    @Mapping(target = "ville", source = "ville")
    @Mapping(target = "codePostal", source = "codePostal")
    @Mapping(target = "pays", source = "pays")
    @Mapping(target = "typePatient", source = "typePatient")
    @Mapping(target = "statut", source = "statut")
    @Mapping(target = "medecinReferent", source = "medecinReferent")
    @Mapping(target = "groupeSanguin", source = "groupeSanguin")
    @Mapping(target = "allergies", source = "allergies")
    @Mapping(target = "antecedentsMedicaux", source = "antecedentsMedicaux")
    @Mapping(target = "contactUrgence", source = "contactUrgence")
    @Mapping(target = "telephoneUrgence", source = "telephoneUrgence")
    @Mapping(target = "assuranceMaladie", source = "assuranceMaladie")
    @Mapping(target = "numeroAssurance", source = "numeroAssurance")
    @Mapping(target = "notes", source = "notes")
    @Mapping(target = "isActive", source = "isActive")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
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
    @Mapping(target = "sexe", source = "sexe")
    @Mapping(target = "telephone", source = "telephone")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "adresse", source = "adresse")
    @Mapping(target = "ville", source = "ville")
    @Mapping(target = "codePostal", source = "codePostal")
    @Mapping(target = "pays", source = "pays")
    @Mapping(target = "typePatient", source = "typePatient")
    @Mapping(target = "statut", source = "statut")
    @Mapping(target = "medecinReferent", source = "medecinReferent")
    @Mapping(target = "groupeSanguin", source = "groupeSanguin")
    @Mapping(target = "allergies", source = "allergies")
    @Mapping(target = "antecedentsMedicaux", source = "antecedentsMedicaux")
    @Mapping(target = "contactUrgence", source = "contactUrgence")
    @Mapping(target = "telephoneUrgence", source = "telephoneUrgence")
    @Mapping(target = "assuranceMaladie", source = "assuranceMaladie")
    @Mapping(target = "numeroAssurance", source = "numeroAssurance")
    @Mapping(target = "notes", source = "notes")
    Patient toEntity(PatientCreateDto createDto);

    // ========== UPDATE MAPPING ==========

    /**
     * Met à jour une entité Patient existante avec les données de PatientUpdateDto
     * Les champs null dans le DTO ne modifient pas l'entité existante
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "numeroPatient", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(PatientUpdateDto updateDto, @MappingTarget Patient patient);

    // ========== MAPPINGS SPÉCIALISÉS ==========

    /**
     * Convertit Patient vers PatientSummaryDto (version allégée)
     */
    @Mapping(target = "id", source = "id")
    @Mapping(target = "uuid", source = "uuid")
    @Mapping(target = "numeroPatient", source = "numeroPatient")
    @Mapping(target = "nom", source = "nom")
    @Mapping(target = "prenom", source = "prenom")
    @Mapping(target = "dateNaissance", source = "dateNaissance")
    @Mapping(target = "sexe", source = "sexe")
    @Mapping(target = "telephone", source = "telephone")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "typePatient", source = "typePatient")
    @Mapping(target = "statut", source = "statut")
    @Mapping(target = "medecinReferent", source = "medecinReferent")
    PatientSummaryDto toSummaryDTO(Patient patient);

    /**
     * Convertit une liste de Patient vers PatientSummaryDto
     */
    List<PatientSummaryDto> toSummaryDTOList(List<Patient> patients);

    // ========== MÉTHODES UTILITAIRES ==========

    /**
     * Méthode personnalisée pour calculer l'âge si nécessaire
     */
    @Named("calculateAge")
    default Integer calculateAge(LocalDateTime dateNaissance) {
        if (dateNaissance == null) {
            return null;
        }
        return LocalDateTime.now().getYear() - dateNaissance.getYear();
    }

    /**
     * Formatage du nom complet
     */
    @Named("formatFullName")
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
    @Named("formatFullAddress")
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
    @Mapping(target = "id", source = "id")
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
    @Mapping(target = "id", source = "id")
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
    PatientSearchResultDto toSearchResultDTO(Patient patient);

    /**
     * Conversion pour liste de résultats de recherche
     */
    List<PatientSearchResultDto> toSearchResultDTOList(List<Patient> patients);
}