package microservice.patient.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.patient.dto.*;
//import microservice.patient.entity.Patient.StatutPatientEnum;
import microservice.patient.service.PatientService;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/patients")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Patient Management", description = "API pour la gestion des patients")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200"})
public class PatientController {

    private final PatientService patientService;

    // ========== OPÉRATIONS CRUD ==========

    @PostMapping
    @Operation(summary = "Créer un nouveau patient", description = "Crée un nouveau patient dans le système")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Patient créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Données invalides"),
            @ApiResponse(responseCode = "409", description = "Patient déjà existant")
    })
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEDICAL_STAFF')")
    public ResponseEntity<PatientResponseDto> createPatient(
            @Valid @RequestBody PatientCreateDto createDTO) {

        log.info("Demande de création de patient: {}", createDTO.getEmail());
        PatientResponseDto patient = patientService.createPatient(createDTO);
        return new ResponseEntity<>(patient, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour un patient", description = "Met à jour les informations d'un patient existant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Patient mis à jour avec succès"),
            @ApiResponse(responseCode = "404", description = "Patient non trouvé"),
            @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEDICAL_STAFF')")
    public ResponseEntity<PatientResponseDto> updatePatient(
            @PathVariable Long id,
            @Valid @RequestBody PatientUpdateDto updateDTO) {

        log.info("Demande de mise à jour du patient ID: {}", id);
        PatientResponseDto patient = patientService.updatePatient(id, updateDTO);
        return ResponseEntity.ok(patient);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un patient par ID", description = "Récupère les détails d'un patient par son ID")
    @ApiResponse(responseCode = "200", description = "Patient trouvé")
    @ApiResponse(responseCode = "404", description = "Patient non trouvé")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEDICAL_STAFF') or hasRole('RECEPTIONIST')")
    public ResponseEntity<PatientResponseDto> getPatientById(@PathVariable Long id) {
        PatientResponseDto patient = patientService.getPatientById(id);
        return ResponseEntity.ok(patient);
    }

    @GetMapping("/uuid/{uuid}")
    @Operation(summary = "Récupérer un patient par UUID", description = "Récupère les détails d'un patient par son UUID")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEDICAL_STAFF') or hasRole('RECEPTIONIST')")
    public ResponseEntity<PatientResponseDto> getPatientByUuid(@PathVariable String uuid) {
        PatientResponseDto patient = patientService.getPatientByUuid(uuid);
        return ResponseEntity.ok(patient);
    }

    @GetMapping("/numero/{numeroPatient}")
    @Operation(summary = "Récupérer un patient par numéro", description = "Récupère les détails d'un patient par son numéro")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEDICAL_STAFF') or hasRole('RECEPTIONIST')")
    public ResponseEntity<PatientResponseDto> getPatientByNumero(@PathVariable String numeroPatient) {
        PatientResponseDto patient = patientService.getPatientByNumero(numeroPatient);
        return ResponseEntity.ok(patient);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un patient", description = "Suppression logique d'un patient")
    @ApiResponse(responseCode = "204", description = "Patient supprimé avec succès")
    @ApiResponse(responseCode = "404", description = "Patient non trouvé")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        log.info("Demande de suppression du patient ID: {}", id);
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/restore")
    @Operation(summary = "Restaurer un patient", description = "Restaure un patient supprimé logiquement")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> restorePatient(@PathVariable Long id) {
        log.info("Demande de restauration du patient ID: {}", id);
        patientService.restorePatient(id);
        return ResponseEntity.ok().build();
    }

    // ========== RECHERCHES ET LISTES ==========

    @GetMapping
    @Operation(summary = "Lister tous les patients", description = "Récupère la liste paginée de tous les patients actifs")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEDICAL_STAFF') or hasRole('RECEPTIONIST')")
    public ResponseEntity<Page<PatientResponseDto>> getAllPatients(
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "20") @Min(1) @Max(100) int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir) {

        Page<PatientResponseDto> patients = patientService.getAllPatients(page, size, sortBy, sortDir);
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/search")
    @Operation(summary = "Rechercher des patients", description = "Recherche de patients par nom, prénom, numéro ou téléphone")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEDICAL_STAFF') or hasRole('RECEPTIONIST')")
    public ResponseEntity<Page<PatientResponseDto>> searchPatients(
            @RequestParam(required = false) String query,
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "20") @Min(1) @Max(100) int size) {

        Page<PatientResponseDto> patients = patientService.searchPatients(query, page, size);
        return ResponseEntity.ok(patients);
    }

    @PostMapping("/search/advanced")
    @Operation(summary = "Recherche avancée", description = "Recherche de patients avec filtres multiples")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEDICAL_STAFF') or hasRole('RECEPTIONIST')")
    public ResponseEntity<Page<PatientResponseDto>> searchPatientsAdvanced(
            @Valid @RequestBody PatientSearchDto searchDTO) {

        Page<PatientResponseDto> patients = patientService.searchPatientsWithFilters(searchDTO);
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/age-range")
    @Operation(summary = "Patients par tranche d'âge", description = "Récupère les patients dans une tranche d'âge donnée")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEDICAL_STAFF')")
    public ResponseEntity<List<PatientResponseDto>> getPatientsByAgeRange(
            @RequestParam @Min(0) @Max(150) int ageMin,
            @RequestParam @Min(0) @Max(150) int ageMax) {

        List<PatientResponseDto> patients = patientService.getPatientsByAgeRange(ageMin, ageMax);
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/medecin/{medecinReferent}")
    @Operation(summary = "Patients par médecin", description = "Récupère les patients d'un médecin référent")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEDICAL_STAFF')")
    public ResponseEntity<List<PatientResponseDto>> getPatientsByMedecin(
            @PathVariable String medecinReferent) {

        List<PatientResponseDto> patients = patientService.getPatientsByMedecin(medecinReferent);
        return ResponseEntity.ok(patients);
    }

    // ========== STATISTIQUES ==========

    @GetMapping("/statistics")
    @Operation(summary = "Statistiques des patients", description = "Récupère les statistiques globales des patients")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEDICAL_STAFF')")
    public ResponseEntity<PatientStatisticsDto> getPatientStatistics() {
        PatientStatisticsDto statistics = patientService.getPatientStatistics();
        return ResponseEntity.ok(statistics);
    }

    @GetMapping("/medecins-referents")
    @Operation(summary = "Liste des médecins référents", description = "Récupère la liste de tous les médecins référents")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEDICAL_STAFF')")
    public ResponseEntity<List<String>> getAllMedecinsReferents() {
        List<String> medecins = patientService.getAllMedecinsReferents();
        return ResponseEntity.ok(medecins);
    }

    // ========== OPÉRATIONS SPÉCIALISÉES ==========

    /*@PatchMapping("/{id}/status")
    @Operation(summary = "Mettre à jour le statut", description = "Met à jour le statut d'un patient")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEDICAL_STAFF')")
    public ResponseEntity<PatientResponseDto> updatePatientStatus(
            @PathVariable Long id,
            @RequestParam StatutPatientEnum status) {

        PatientResponseDto patient = patientService.updatePatientStatus(id, status);
        return ResponseEntity.ok(patient);
    }*/

    @GetMapping("/appointments/upcoming")
    @Operation(summary = "Patients avec rendez-vous à venir", description = "Récupère les patients ayant des rendez-vous dans une période donnée")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEDICAL_STAFF')")
    public ResponseEntity<List<PatientResponseDto>> getPatientsWithUpcomingAppointments(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {

        List<PatientResponseDto> patients = patientService.getPatientsWithUpcomingAppointments(startDate, endDate);
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/new-patients")
    @Operation(summary = "Nouveaux patients", description = "Récupère les patients créés dans une période donnée")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEDICAL_STAFF')")
    public ResponseEntity<List<PatientResponseDto>> getNewPatientsByPeriod(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {

        List<PatientResponseDto> patients = patientService.getNewPatientsByPeriod(startDate, endDate);
        return ResponseEntity.ok(patients);
    }

    // ========== VALIDATIONS ==========

    @GetMapping("/validate/numero/{numeroPatient}")
    @Operation(summary = "Vérifier la disponibilité d'un numéro", description = "Vérifie si un numéro de patient est disponible")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEDICAL_STAFF')")
    public ResponseEntity<Map<String, Boolean>> checkPatientNumberAvailability(
            @PathVariable String numeroPatient) {

        boolean available = patientService.isPatientNumberAvailable(numeroPatient);
        return ResponseEntity.ok(Map.of("available", available));
    }

    @GetMapping("/validate/telephone")
    @Operation(summary = "Vérifier la disponibilité d'un téléphone", description = "Vérifie si un numéro de téléphone est disponible")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEDICAL_STAFF')")
    public ResponseEntity<Map<String, Boolean>> checkTelephoneAvailability(
            @RequestParam String telephone,
            @RequestParam(required = false) Long excludeId) {

        boolean available = patientService.isTelephoneAvailable(telephone, excludeId);
        return ResponseEntity.ok(Map.of("available", available));
    }

    @GetMapping("/validate/email")
    @Operation(summary = "Vérifier la disponibilité d'un email", description = "Vérifie si une adresse email est disponible")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEDICAL_STAFF')")
    public ResponseEntity<Map<String, Boolean>> checkEmailAvailability(
            @RequestParam String email,
            @RequestParam(required = false) Long excludeId) {

        boolean available = patientService.isEmailAvailable(email, excludeId);
        return ResponseEntity.ok(Map.of("available", available));
    }

    // ========== GESTION DES ERREURS ==========

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Map<String, String>> handleGenericException(Exception e) {
        log.error("Erreur inattendue dans PatientController: ", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of(
                        "error", "Erreur interne du serveur",
                        "message", e.getMessage(),
                        "timestamp", LocalDateTime.now().toString()
                ));
    }
}