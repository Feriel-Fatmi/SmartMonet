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
public class DepenceFixe {

    private double transportJour;
    private double nouritureJour;

    public DepenceFixe(double transportJour, double nouritureJour) {
        this.transportJour = transportJour;
        this.nouritureJour = nouritureJour;

    }

    public double getTransportJour() {
        return transportJour;
    }

    public double getNouritureJour() {
        return nouritureJour;
    }

    public void setTransportJour(double transportJour) {
        this.transportJour = transportJour;
    }

    public void setNouritureJour(double nouritureJour) {
        this.nouritureJour = nouritureJour;
    }

    public double transportMois() {
        return transportJour * 22;
    }

    public double nouritureMois() {
        return nouritureJour * 22;
    }

    public double depenseFixeMois() {

        return transportMois() + nouritureMois();
    }

}
