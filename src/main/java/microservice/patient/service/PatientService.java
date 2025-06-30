package microservice.patient.service;

import jakarta.validation.Valid;
import microservice.patient.dto.*;
import microservice.patient.entity.Patient;
import microservice.patient.entity.Patient.StatutPatientEnum;
import microservice.patient.exception.PatientNotFoundException;
import microservice.patient.exception.DuplicatePatientException;
import microservice.patient.exception.PatientNotFoundException;
import microservice.patient.mapper.PatientMapper;
import microservice.patient.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    // ========== OPÉRATIONS CRUD ==========

    @Transactional
    public PatientResponseDto createPatient(@Valid PatientCreateDto createDto) {
        log.info("Création d'un nouveau patient: {}", createDto.getEmail());

        validateUniqueFields(createDto);

        Patient patient = patientMapper.toEntity(createDto);
        patient.setUuid(UUID.randomUUID().toString());
        patient.setNumeroPatient(generatePatientNumber());
        patient.setIsActive(true);
        patient.setCreatedAt(LocalDateTime.now());
        patient.setUpdatedAt(LocalDateTime.now());

        Patient savedPatient = patientRepository.save(patient);
        log.info("Patient créé avec l'ID: {} et le numéro: {}", savedPatient.getId(), savedPatient.getNumeroPatient());

        return patientMapper.toResponseDTO(savedPatient);
    }

    @Transactional
    public PatientResponseDto updatePatient(Long id, PatientUpdateDto updateDto){
        log.info("Mise à jour du patient ID: {}", id);

        Patient existingPatient = findPatientById(id);
        validateUniqueFieldsForUpdate(updateDto, id);

        patientMapper.updateEntityFromDTO(updateDto, existingPatient);
        existingPatient.setUpdatedAt(LocalDateTime.now());

        Patient updatedPatient = patientRepository.save(existingPatient);
        log.info("Patient mis à jour: {}", updatedPatient.getNumeroPatient());

        return patientMapper.toResponseDTO(updatedPatient);
    }

    public PatientResponseDto getPatientById(Long id) {
        Patient patient = findPatientById(id);
        return patientMapper.toResponseDTO(patient);
    }

    public PatientResponseDto getPatientByUuid(String uuid) {
        Patient patient = patientRepository.findActiveByUuid(uuid)
                .orElseThrow(() -> new PatientNotFoundException("Patient non trouvé avec UUID: " + uuid));
        return patientMapper.toResponseDTO(patient);
    }

    public PatientResponseDto getPatientByNumero(String numeroPatient) {
        Patient patient = patientRepository.findActiveByNumeroPatient(numeroPatient)
                .orElseThrow(() -> new PatientNotFoundException("Patient non trouvé avec le numéro: " + numeroPatient));
        return patientMapper.toResponseDTO(patient);
    }

    @Transactional
    public void deletePatient(Long id) {
        log.info("Suppression logique du patient ID: {}", id);

        Patient patient = findPatientById(id);
        int updated = patientRepository.softDeleteById(id);

        if (updated > 0) {
            log.info("Patient supprimé (logiquement): {}", patient.getNumeroPatient());
        } else {
            throw new PatientNotFoundException("Impossible de supprimer le patient ID: " + id);
        }
    }

    @Transactional
    public void restorePatient(Long id) {
        log.info("Restauration du patient ID: {}", id);

        int updated = patientRepository.restoreById(id);

        if (updated > 0) {
            log.info("Patient restauré ID: {}", id);
        } else {
            throw new PatientNotFoundException("Impossible de restaurer le patient ID: " + id);
        }
    }

    // ========== RECHERCHES ==========

    public Page<PatientResponseDto> getAllPatients(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Patient> patients = patientRepository.findByIsActiveTrueOrderByCreatedAtDesc(pageable);

        return patients.map(patientMapper::toResponseDTO);
    }

    public Page<PatientResponseDto> searchPatients(String query, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Patient> patients;

        if (StringUtils.hasText(query)) {
            patients = patientRepository.searchPatients(query.trim(), pageable);
        } else {
            patients = patientRepository.findByIsActiveTrueOrderByCreatedAtDesc(pageable);
        }

        return patients.map(patientMapper::toResponseDTO);
    }

    /*public Page<PatientResponseDto> searchPatientsWithFilters(PatientSearchDto searchDto) {
        Pageable pageable = PageRequest.of(
                searchDto.getPage(),
                searchDto.getSize(),
                Sort.by(searchDto.getSortBy()).ascending()
        );

        Page<Patient> patients = patientRepository.searchPatientsWithFilters(
                searchDto.getQuery(),
                searchDto.getTypePatient(),
                searchDto.getStatut(),
                searchDto.getSexe(),
                pageable
        );

        return patients.map(patientMapper::toResponseDTO);
    }*/

    public List<PatientResponseDto> getPatientsByAgeRange(int ageMin, int ageMax) {
        List<Patient> patients = patientRepository.findByAgeRangeInYears(ageMin, ageMax);
        return patients.stream()
                .map(patientMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

/*    public List<PatientResponseDto> getPatientsByMedecin(String medecinReferent) {
        List<Patient> patients = patientRepository.findByMedecinReferentAndIsActiveTrueOrderByNomAsc(medecinReferent);
        return patients.stream()
                .map(patientMapper::toResponseDTO)
                .collect(Collectors.toList());
    }*/

    // ========== STATISTIQUES ==========

    public PatientStatisticsDto getPatientStatistics() {
        PatientStatisticsDto stats = new PatientStatisticsDto();

        stats.setTotalActivePatients(patientRepository.countActivePatients());
        stats.setTotalInactivePatients(patientRepository.countInactivePatients());

        // Statistiques par type
        List<Object[]> typeStats = patientRepository.countPatientsByType();
        Map<String, Long> patientsByType = typeStats.stream()
                .collect(Collectors.toMap(
                        obj -> obj[0].toString(),
                        obj -> (Long) obj[1]
                ));
        stats.setPatientsByType(patientsByType);

        // Statistiques par statut
        /*List<Object[]> statusStats = patientRepository.countPatientsByStatus();
        Map<String, Long> patientsByStatus = statusStats.stream()
                .collect(Collectors.toMap(
                        obj -> obj[0].toString(),
                        obj -> (Long) obj[1]
                ));
        stats.setPatientsByStatus(patientsByStatus);*/

        // Statistiques par genre
        List<Object[]> genderStats = patientRepository.countPatientsByGender();
        Map<String, Long> patientsByGender = genderStats.stream()
                .collect(Collectors.toMap(
                        obj -> obj[0].toString(),
                        obj -> (Long) obj[1]
                ));
        stats.setPatientsByGender(patientsByGender);

        return stats;
    }

    public List<String> getAllMedecinsReferents() {
        return patientRepository.findAllMedecinsReferents();
    }

    // ========== MÉTHODES UTILITAIRES ==========

    private Patient findPatientById(Long id) {
        return patientRepository.findById(id)
                .filter(Patient::getIsActive)
                .orElseThrow(() -> new PatientNotFoundException("Patient non trouvé avec l'ID: " + id));
    }

    private void validateUniqueFields(PatientCreateDto createDto) {
        if (StringUtils.hasText(createDto.getTelephone()) &&
                patientRepository.findActiveByTelephone(createDto.getTelephone()).isPresent()) {
            throw new DuplicatePatientException("Un patient avec ce téléphone existe déjà: " + createDto.getTelephone());
        }

        if (StringUtils.hasText(createDto.getEmail()) &&
                patientRepository.findActiveByEmail(createDto.getEmail()).isPresent()) {
            throw new DuplicatePatientException("Un patient avec cet email existe déjà: " + createDto.getEmail());
        }
    }

    private void validateUniqueFieldsForUpdate(PatientUpdateDto updateDto, Long id) {
        if (StringUtils.hasText(updateDto.getTelephone()) &&
                patientRepository.existsActiveTelephoneExcludingId(updateDto.getTelephone(), id)) {
            throw new DuplicatePatientException("Un autre patient avec ce téléphone existe déjà: " + updateDto.getTelephone());
        }

        if (StringUtils.hasText(updateDto.getEmail()) &&
                patientRepository.existsActiveEmailExcludingId(updateDto.getEmail(), id)) {
            throw new DuplicatePatientException("Un autre patient avec cet email existe déjà: " + updateDto.getEmail());
        }
    }

    private String generatePatientNumber() {
        String currentYear = String.valueOf(Year.now().getValue());
        String prefix = "PAT" + currentYear;

        Optional<Integer> maxSequence = patientRepository.findMaxSequenceByPrefix(
                prefix, prefix.length() + 1, prefix.length() + 5
        );

        int nextSequence = maxSequence.orElse(0) + 1;
        return prefix + String.format("%05d", nextSequence);
    }

    // ========== MÉTHODES SPÉCIALISÉES ==========

    @Transactional
    public PatientResponseDto updatePatientStatus(Long id, StatutPatientEnum newStatus) {
        Patient patient = findPatientById(id);
        patient.setStatut(newStatus);
        patient.setUpdatedAt(LocalDateTime.now());

        Patient updatedPatient = patientRepository.save(patient);
        log.info("Statut du patient {} mis à jour vers: {}", patient.getNumeroPatient(), newStatus);

        return patientMapper.toResponseDTO(updatedPatient);
    }

    /*  public List<PatientResponseDto> getPatientsWithUpcomingAppointments(LocalDateTime startDate, LocalDateTime endDate) {
        List<Patient> patients = patientRepository.findPatientsWithAppointmentsBetween(startDate, endDate);
        return patients.stream()
                .map(patientMapper::toResponseDTO)
                .collect(Collectors.toList());
    }*/

    public List<PatientResponseDto> getNewPatientsByPeriod(LocalDateTime startDate, LocalDateTime endDate) {
        List<Patient> patients = patientRepository.findByCreationDateRange(startDate, endDate);
        return patients.stream()
                .map(patientMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public boolean isPatientNumberAvailable(String numeroPatient) {
        return patientRepository.findByNumeroPatient(numeroPatient).isEmpty();
    }

    public boolean isTelephoneAvailable(String telephone, Long excludeId) {
        if (excludeId != null) {
            return !patientRepository.existsActiveTelephoneExcludingId(telephone, excludeId);
        }
        return patientRepository.findActiveByTelephone(telephone).isEmpty();
    }

    public boolean isEmailAvailable(String email, Long excludeId) {
        if (excludeId != null) {
            return !patientRepository.existsActiveEmailExcludingId(email, excludeId);
        }
        return patientRepository.findActiveByEmail(email).isEmpty();
    }
}