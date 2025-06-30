package microservice.patient.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class PatientStatisticsDto {

    // Statistiques générales
    private Long totalPatients;
    private Long patientsActifs;
    private Long patientsInactifs;
    private Long patientsSupprimes;
    private Long nouveauxPatientsCeMois;
    private Long nouveauxPatientsAnneeEnCours;

    // Répartition par sexe
    private Long nombreHommes;
    private Long nombreFemmes;
    private BigDecimal pourcentageHommes;
    private BigDecimal pourcentageFemmes;

    // Répartition par tranches d'âge
    private Map<String, Long> repartitionParAge;

    // Répartition par nationalité
    private Map<String, Long> repartitionParNationalite;

    // Répartition par ville
    private Map<String, Long> repartitionParVille;

    // Répartition par type de patient
    private Map<String, Long> repartitionParTypePatient;

    // Répartition par groupe sanguin
    private Map<String, Long> repartitionParGroupeSanguin;

    // Statistiques médicales
    private Long patientsAvecAssurance;
    private Long patientsSansAssurance;
    private BigDecimal pourcentageAvecAssurance;
    private Map<String, Long> repartitionParAssurance;

    // Médecins référents
    private Long nombreMedecinsReferents;
    private Map<String, Long> patientsParMedecinReferent;

    // Moyennes
    private BigDecimal ageMoyen;
    private BigDecimal poidsMoyen;
    private BigDecimal tailleMoyenne;
    private BigDecimal surfaceCorporelleMoyenne;

    // Statistiques temporelles
    private Map<String, Long> creationsParMois;
    private Map<String, Long> creationsParTrimestre;

    // Contacts d'urgence
    private Long patientsAvecContactUrgence;
    private Long patientsSansContactUrgence;

    // Métadonnées
    private LocalDateTime dateGeneration;
    private String periodeAnalyse;

    // Top des données
    private List<TopStatistic> topVilles;
    private List<TopStatistic> topMedecinsReferents;
    private List<TopStatistic> topAssurances;

    // Classe interne pour les statistiques "top"
    public static class TopStatistic {
        private String nom;
        private Long nombre;
        private BigDecimal pourcentage;

        public TopStatistic() {}

        public TopStatistic(String nom, Long nombre, BigDecimal pourcentage) {
            this.nom = nom;
            this.nombre = nombre;
            this.pourcentage = pourcentage;
        }

        // Getters and Setters
        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public Long getNombre() {
            return nombre;
        }

        public void setNombre(Long nombre) {
            this.nombre = nombre;
        }

        public BigDecimal getPourcentage() {
            return pourcentage;
        }

        public void setPourcentage(BigDecimal pourcentage) {
            this.pourcentage = pourcentage;
        }
    }

    // Getters and Setters
    public Long getTotalPatients() {
        return totalPatients;
    }

    public void setTotalPatients(Long totalPatients) {
        this.totalPatients = totalPatients;
    }

    public Long getPatientsActifs() {
        return patientsActifs;
    }

    public void setPatientsActifs(Long patientsActifs) {
        this.patientsActifs = patientsActifs;
    }

    public Long getPatientsInactifs() {
        return patientsInactifs;
    }

    public void setPatientsInactifs(Long patientsInactifs) {
        this.patientsInactifs = patientsInactifs;
    }

    public Long getPatientsSupprimes() {
        return patientsSupprimes;
    }

    public void setPatientsSupprimes(Long patientsSupprimes) {
        this.patientsSupprimes = patientsSupprimes;
    }

    public Long getNouveauxPatientsCeMois() {
        return nouveauxPatientsCeMois;
    }

    public void setNouveauxPatientsCeMois(Long nouveauxPatientsCeMois) {
        this.nouveauxPatientsCeMois = nouveauxPatientsCeMois;
    }

    public Long getNouveauxPatientsAnneeEnCours() {
        return nouveauxPatientsAnneeEnCours;
    }

    public void setNouveauxPatientsAnneeEnCours(Long nouveauxPatientsAnneeEnCours) {
        this.nouveauxPatientsAnneeEnCours = nouveauxPatientsAnneeEnCours;
    }

    public Long getNombreHommes() {
        return nombreHommes;
    }

    public void setNombreHommes(Long nombreHommes) {
        this.nombreHommes = nombreHommes;
    }

    public Long getNombreFemmes() {
        return nombreFemmes;
    }

    public void setNombreFemmes(Long nombreFemmes) {
        this.nombreFemmes = nombreFemmes;
    }

    public BigDecimal getPourcentageHommes() {
        return pourcentageHommes;
    }

    public void setPourcentageHommes(BigDecimal pourcentageHommes) {
        this.pourcentageHommes = pourcentageHommes;
    }

    public BigDecimal getPourcentageFemmes() {
        return pourcentageFemmes;
    }

    public void setPourcentageFemmes(BigDecimal pourcentageFemmes) {
        this.pourcentageFemmes = pourcentageFemmes;
    }

    public Map<String, Long> getRepartitionParAge() {
        return repartitionParAge;
    }

    public void setRepartitionParAge(Map<String, Long> repartitionParAge) {
        this.repartitionParAge = repartitionParAge;
    }

    public Map<String, Long> getRepartitionParNationalite() {
        return repartitionParNationalite;
    }

    public void setRepartitionParNationalite(Map<String, Long> repartitionParNationalite) {
        this.repartitionParNationalite = repartitionParNationalite;
    }

    public Map<String, Long> getRepartitionParVille() {
        return repartitionParVille;
    }

    public void setRepartitionParVille(Map<String, Long> repartitionParVille) {
        this.repartitionParVille = repartitionParVille;
    }

    public Map<String, Long> getRepartitionParTypePatient() {
        return repartitionParTypePatient;
    }

    public void setRepartitionParTypePatient(Map<String, Long> repartitionParTypePatient) {
        this.repartitionParTypePatient = repartitionParTypePatient;
    }

    public Map<String, Long> getRepartitionParGroupeSanguin() {
        return repartitionParGroupeSanguin;
    }

    public void setRepartitionParGroupeSanguin(Map<String, Long> repartitionParGroupeSanguin) {
        this.repartitionParGroupeSanguin = repartitionParGroupeSanguin;
    }

    public Long getPatientsAvecAssurance() {
        return patientsAvecAssurance;
    }

    public void setPatientsAvecAssurance(Long patientsAvecAssurance) {
        this.patientsAvecAssurance = patientsAvecAssurance;
    }

    public Long getPatientsSansAssurance() {
        return patientsSansAssurance;
    }

    public void setPatientsSansAssurance(Long patientsSansAssurance) {
        this.patientsSansAssurance = patientsSansAssurance;
    }

    public BigDecimal getPourcentageAvecAssurance() {
        return pourcentageAvecAssurance;
    }

    public void setPourcentageAvecAssurance(BigDecimal pourcentageAvecAssurance) {
        this.pourcentageAvecAssurance = pourcentageAvecAssurance;
    }

    public Map<String, Long> getRepartitionParAssurance() {
        return repartitionParAssurance;
    }

    public void setRepartitionParAssurance(Map<String, Long> repartitionParAssurance) {
        this.repartitionParAssurance = repartitionParAssurance;
    }

    public Long getNombreMedecinsReferents() {
        return nombreMedecinsReferents;
    }

    public void setNombreMedecinsReferents(Long nombreMedecinsReferents) {
        this.nombreMedecinsReferents = nombreMedecinsReferents;
    }

    public Map<String, Long> getPatientsParMedecinReferent() {
        return patientsParMedecinReferent;
    }

    public void setPatientsParMedecinReferent(Map<String, Long> patientsParMedecinReferent) {
        this.patientsParMedecinReferent = patientsParMedecinReferent;
    }

    public BigDecimal getAgeMoyen() {
        return ageMoyen;
    }

    public void setAgeMoyen(BigDecimal ageMoyen) {
        this.ageMoyen = ageMoyen;
    }

    public BigDecimal getPoidsMoyen() {
        return poidsMoyen;
    }

    public void setPoidsMoyen(BigDecimal poidsMoyen) {
        this.poidsMoyen = poidsMoyen;
    }

    public BigDecimal getTailleMoyenne() {
        return tailleMoyenne;
    }

    public void setTailleMoyenne(BigDecimal tailleMoyenne) {
        this.tailleMoyenne = tailleMoyenne;
    }

    public BigDecimal getSurfaceCorporelleMoyenne() {
        return surfaceCorporelleMoyenne;
    }

    public void setSurfaceCorporelleMoyenne(BigDecimal surfaceCorporelleMoyenne) {
        this.surfaceCorporelleMoyenne = surfaceCorporelleMoyenne;
    }

    public Map<String, Long> getCreationsParMois() {
        return creationsParMois;
    }

    public void setCreationsParMois(Map<String, Long> creationsParMois) {
        this.creationsParMois = creationsParMois;
    }

    public Map<String, Long> getCreationsParTrimestre() {
        return creationsParTrimestre;
    }

    public void setCreationsParTrimestre(Map<String, Long> creationsParTrimestre) {
        this.creationsParTrimestre = creationsParTrimestre;
    }

    public Long getPatientsAvecContactUrgence() {
        return patientsAvecContactUrgence;
    }

    public void setPatientsAvecContactUrgence(Long patientsAvecContactUrgence) {
        this.patientsAvecContactUrgence = patientsAvecContactUrgence;
    }

    public Long getPatientsSansContactUrgence() {
        return patientsSansContactUrgence;
    }

    public void setPatientsSansContactUrgence(Long patientsSansContactUrgence) {
        this.patientsSansContactUrgence = patientsSansContactUrgence;
    }

    public LocalDateTime getDateGeneration() {
        return dateGeneration;
    }

    public void setDateGeneration(LocalDateTime dateGeneration) {
        this.dateGeneration = dateGeneration;
    }

    public String getPeriodeAnalyse() {
        return periodeAnalyse;
    }

    public void setPeriodeAnalyse(String periodeAnalyse) {
        this.periodeAnalyse = periodeAnalyse;
    }

    public List<TopStatistic> getTopVilles() {
        return topVilles;
    }

    public void setTopVilles(List<TopStatistic> topVilles) {
        this.topVilles = topVilles;
    }

    public List<TopStatistic> getTopMedecinsReferents() {
        return topMedecinsReferents;
    }

    public void setTopMedecinsReferents(List<TopStatistic> topMedecinsReferents) {
        this.topMedecinsReferents = topMedecinsReferents;
    }

    public List<TopStatistic> getTopAssurances() {
        return topAssurances;
    }

    public void setTopAssurances(List<TopStatistic> topAssurances) {
        this.topAssurances = topAssurances;
    }
}