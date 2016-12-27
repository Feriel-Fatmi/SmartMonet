/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetgr1.smart.money;

/**
 *
 * @author fehim
 */
public class Revenue {

    private double aideFamille;
    private double jobEtudiant;
    private double bourseSemestre;
    private double autre;

    public Revenue(double aideFamille, double jobEtudiant, double bourseSemestre, double autre) {

        this.aideFamille = aideFamille;
        this.jobEtudiant = jobEtudiant;
        this.bourseSemestre = bourseSemestre;
        this.autre = autre;

    }

    public double getAideFamille() {
        return aideFamille;
    }

    public double getJobEtudiant() {
        return jobEtudiant;
    }

    public double getBourseSemestre() {
        return bourseSemestre;
    }

    public double getAutre() {
        return autre;
    }

    public double bourseMois() {
        return bourseSemestre / 3;
    }

    public double revenuMois() {

        return (aideFamille + jobEtudiant + autre + bourseMois());
    }

    public double resteVivre(double transportJour, double nouritureJour) {

        return (revenuMois() - (new DepenceFixe(transportJour, nouritureJour)).depenseFixeMois());
    }
}
