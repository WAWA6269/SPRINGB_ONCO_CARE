package microservice.patient.repository;

import microservice.patient.entity.Patient;
//import microservice.patient.entity.Patient.StatutPatientEnum;
import microservice.patient.entity.Patient.TypePatientEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>, JpaSpecificationExecutor<Patient> {

    // ========== RECHERCHES DE BASE ==========

    @Query("SELECT p FROM Patient p WHERE p.numeroPatient = :numeroPatient AND p.isActive = true")
    Optional<Patient> findActiveByNumeroPatient(@Param("numeroPatient") String numeroPatient);

    @Query("SELECT p FROM Patient p WHERE p.uuid = :uuid AND p.isActive = true")
    Optional<Patient> findActiveByUuid(@Param("uuid") String uuid);

    @Query("SELECT p FROM Patient p WHERE p.telephone = :telephone AND p.isActive = true")
    Optional<Patient> findActiveByTelephone(@Param("telephone") String telephone);

    @Query("SELECT p FROM Patient p WHERE p.email = :email AND p.isActive = true")
    Optional<Patient> findActiveByEmail(@Param("email") String email);

    // Méthodes sans restriction isActive pour les besoins administratifs
    Optional<Patient> findByNumeroPatient(String numeroPatient);
    Optional<Patient> findByUuid(String uuid);
    Optional<Patient> findByTelephone(String telephone);
    Optional<Patient> findByEmail(String email);

    // ========== RECHERCHES AVEC PAGINATION ==========

    Page<Patient> findByIsActiveTrueOrderByCreatedAtDesc(Pageable pageable);

    Page<Patient> findByIsActiveTrueOrderByNomAsc(Pageable pageable);

    // ========== RECHERCHES PAR CRITÈRES ==========

    @Query("SELECT p FROM Patient p WHERE p.isActive = true AND " +
            "(LOWER(CONCAT(p.nom, ' ', p.prenom)) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(CONCAT(p.prenom, ' ', p.nom)) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "p.numeroPatient LIKE CONCAT('%', :query, '%') OR " +
            "p.telephone LIKE CONCAT('%', :query, '%') OR " +
            "LOWER(p.email) LIKE LOWER(CONCAT('%', :query, '%')))")
    Page<Patient> searchPatients(@Param("query") String query, Pageable pageable);

    @Query("SELECT p FROM Patient p WHERE p.isActive = true " +
            "AND (:query IS NULL OR :query = '' OR " +
            "LOWER(CONCAT(p.nom, ' ', p.prenom)) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(CONCAT(p.prenom, ' ', p.nom)) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "p.numeroPatient LIKE CONCAT('%', :query, '%') OR " +
            "p.telephone LIKE CONCAT('%', :query, '%')) " +
            "AND (:typePatient IS NULL OR p.typePatient = :typePatient) " +
            //"AND (:statut IS NULL OR p.statut = :statut) " +
            "AND (:sexe IS NULL OR p.sexe = :sexe)")
    Page<Patient> searchPatientsWithFilters(
            @Param("query") String query,
            @Param("typePatient") TypePatientEnum typePatient,
            //@Param("statut") StatutPatientEnum statut,
            @Param("sexe") String sexe,
            Pageable pageable
    );

    // ========== RECHERCHES PAR DATES ET ÂGE ==========

    @Query("SELECT p FROM Patient p WHERE p.isActive = true " +
            "AND p.dateNaissance BETWEEN :dateMin AND :dateMax " +
            "ORDER BY p.dateNaissance DESC")
    List<Patient> findByAgeRange(
            @Param("dateMin") LocalDate dateMin,
            @Param("dateMax") LocalDate dateMax
    );

    @Query("SELECT p FROM Patient p WHERE p.isActive = true " +
            "AND YEAR(CURRENT_DATE) - YEAR(p.dateNaissance) BETWEEN :ageMin AND :ageMax")
    List<Patient> findByAgeRangeInYears(
            @Param("ageMin") int ageMin,
            @Param("ageMax") int ageMax
    );

    @Query("SELECT p FROM Patient p WHERE p.isActive = true " +
            "AND p.createdAt BETWEEN :startDate AND :endDate")
    List<Patient> findByCreationDateRange(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );

    // ========== RECHERCHES PAR MÉDECIN ==========

    /*List<Patient> findByMedecinReferentAndIsActiveTrueOrderByNomAsc(String medecinReferent);
    @Query("SELECT p FROM Patient p WHERE p.isActive = true " +
            "AND p.medecinReferent = :medecinReferent " +
            "AND p.statut = :statut")
    List<Patient> findByMedecinReferentAndStatut(
            @Param("medecinReferent") String medecinReferent,
            //@Param("statut") StatutPatientEnum statut
    );*/

    // ========== STATISTIQUES ==========

    @Query("SELECT COUNT(p) FROM Patient p WHERE p.isActive = true")
    long countActivePatients();

    @Query("SELECT COUNT(p) FROM Patient p WHERE p.isActive = false")
    long countInactivePatients();

    @Query("SELECT p.typePatient, COUNT(p) FROM Patient p WHERE p.isActive = true GROUP BY p.typePatient")
    List<Object[]> countPatientsByType();

/*    @Query("SELECT p.statut, COUNT(p) FROM Patient p WHERE p.isActive = true GROUP BY p.statut")
    List<Object[]> countPatientsByStatus();*/

    @Query("SELECT p.sexe, COUNT(p) FROM Patient p WHERE p.isActive = true GROUP BY p.sexe")
    List<Object[]> countPatientsByGender();

    @Query("SELECT p.medecinReferent, COUNT(p) FROM Patient p WHERE p.isActive = true " +
            "AND p.medecinReferent IS NOT NULL GROUP BY p.medecinReferent")
    List<Object[]> countPatientsByMedecin();

    @Query("SELECT YEAR(p.createdAt), MONTH(p.createdAt), COUNT(p) FROM Patient p " +
            "WHERE p.isActive = true GROUP BY YEAR(p.createdAt), MONTH(p.createdAt) " +
            "ORDER BY YEAR(p.createdAt) DESC, MONTH(p.createdAt) DESC")
    List<Object[]> countPatientsByMonth();

    // ========== GÉNÉRATION DE NUMÉROS ==========

    @Query("SELECT p.numeroPatient FROM Patient p WHERE p.numeroPatient LIKE :prefix% " +
            "ORDER BY p.numeroPatient DESC")
    List<String> findLastPatientNumberByPrefix(@Param("prefix") String prefix);

    @Query("SELECT MAX(CAST(SUBSTRING(p.numeroPatient, :startIndex) AS int)) FROM Patient p " +
            "WHERE p.numeroPatient LIKE :prefix% AND " +
            "LENGTH(p.numeroPatient) = :expectedLength")
    Optional<Integer> findMaxSequenceByPrefix(
            @Param("prefix") String prefix,
            @Param("startIndex") int startIndex,
            @Param("expectedLength") int expectedLength
    );

    // ========== VALIDATIONS D'UNICITÉ ==========

    boolean existsByTelephoneAndIdNot(String telephone, Long id);
    boolean existsByEmailAndIdNot(String email, Long id);
    boolean existsByNumeroPatientAndIdNot(String numeroPatient, Long id);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Patient p " +
            "WHERE p.telephone = :telephone AND p.isActive = true " +
            "AND (:id IS NULL OR p.id != :id)")
    boolean existsActiveTelephoneExcludingId(@Param("telephone") String telephone, @Param("id") Long id);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Patient p " +
            "WHERE p.email = :email AND p.isActive = true " +
            "AND (:id IS NULL OR p.id != :id)")
    boolean existsActiveEmailExcludingId(@Param("email") String email, @Param("id") Long id);

    // ========== OPÉRATIONS DE SUPPRESSION LOGIQUE ==========

    @Modifying
    @Transactional
    @Query("UPDATE Patient p SET p.isActive = false, p.updatedAt = CURRENT_TIMESTAMP " +
            "WHERE p.id = :id")
    int softDeleteById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Patient p SET p.isActive = false, p.updatedAt = CURRENT_TIMESTAMP " +
            "WHERE p.uuid = :uuid")
    int softDeleteByUuid(@Param("uuid") String uuid);

    @Modifying
    @Transactional
    @Query("UPDATE Patient p SET p.isActive = true, p.updatedAt = CURRENT_TIMESTAMP " +
            "WHERE p.id = :id")
    int restoreById(@Param("id") Long id);

    // ========== RECHERCHES SPÉCIALISÉES ==========

    @Query("SELECT p FROM Patient p WHERE p.isActive = true " +
            "AND p.updatedAt < :dateLimit")
    List<Patient> findPatientsNotUpdatedSince(@Param("dateLimit") LocalDateTime dateLimit);

/*    @Query("SELECT p FROM Patient p WHERE p.isActive = true " +
            "AND p.prochainRendezVous IS NOT NULL " +
            "AND p.prochainRendezVous BETWEEN :startDate AND :endDate")
    List<Patient> findPatientsWithAppointmentsBetween(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );*/

    @Query("SELECT DISTINCT p.medecinReferent FROM Patient p " +
            "WHERE p.isActive = true AND p.medecinReferent IS NOT NULL " +
            "ORDER BY p.medecinReferent")
    List<String> findAllMedecinsReferents();

    // ========== RECHERCHES POUR RAPPORTS ==========

    @Query("SELECT p FROM Patient p WHERE p.isActive = true " +
            "AND p.typePatient = :typePatient " +
            "AND p.createdAt BETWEEN :startDate AND :endDate")
    List<Patient> findNewPatientsByTypeAndPeriod(
            @Param("typePatient") TypePatientEnum typePatient,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );

    /*@Query("SELECT p FROM Patient p WHERE p.isActive = true " +
            "AND p.statut IN :statuts " +
            "ORDER BY p.updatedAt DESC")
    List<Patient> findByStatutIn(@Param("statuts") List<StatutPatientEnum> statuts);*/
}