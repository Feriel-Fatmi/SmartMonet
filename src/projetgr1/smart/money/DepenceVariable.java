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
public class DepenceVariable {

    private double loisir;
    private double weekEnd;
    private double sante;

    public DepenceVariable(double loisir, double weekEnd, double sante) {

        this.loisir = loisir;
        this.weekEnd = weekEnd;
        this.sante = sante;
    }

    public double getLoisir() {
        return loisir;
    }

    public void setLoisir(double loisir) {
        this.loisir = loisir;
    }

    public double getWeekEnd() {
        return weekEnd;
    }

    public void setWeekEnd(double weekEnd) {
        this.weekEnd = weekEnd;
    }

    public double getSante() {
        return sante;
    }

    public void setSante(double sante) {
        this.sante = sante;
    }
}
