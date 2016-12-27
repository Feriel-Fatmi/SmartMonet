/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetgr1.smart.money;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;

/**
 *
 * @author fehim
 */
public class Ressource {

    private SimpleDoubleProperty aideFamille;
    private SimpleDoubleProperty job;
    private SimpleDoubleProperty bource;
    private SimpleDoubleProperty autre;
    private SimpleDoubleProperty revenueMois;
    private SimpleDoubleProperty depenceFixeMois;
    private SimpleDoubleProperty restVive;
    private SimpleDoubleProperty loisir;
    private SimpleDoubleProperty weekEnd;
    private SimpleDoubleProperty transport;
    private SimpleDoubleProperty nourriture;
    private SimpleDoubleProperty sante;

    public Ressource(Double aideFamille, Double job, Double bource, Double autre, Double loisir,
            Double weekEnd, Double sante, Double transport, Double nourriture) {

        Revenue r = new Revenue(aideFamille, job, bource, autre);
        this.aideFamille = new SimpleDoubleProperty(aideFamille);
        this.job = new SimpleDoubleProperty(job);
        this.bource = new SimpleDoubleProperty(r.getBourseSemestre());
        this.autre = new SimpleDoubleProperty(autre);
        this.revenueMois = new SimpleDoubleProperty((r.revenuMois()));

        DepenceVariable dv = new DepenceVariable(loisir, weekEnd, sante);
        this.loisir = new SimpleDoubleProperty(dv.getLoisir());
        this.weekEnd = new SimpleDoubleProperty(dv.getWeekEnd());
        this.sante = new SimpleDoubleProperty(dv.getSante());

        DepenceFixe df = new DepenceFixe(transport, nourriture);
        this.transport = new SimpleDoubleProperty(df.getTransportJour());
        this.nourriture = new SimpleDoubleProperty(df.getNouritureJour());
        this.depenceFixeMois = new SimpleDoubleProperty(df.depenseFixeMois());
        this.restVive = new SimpleDoubleProperty((r.resteVivre(df.transportMois(), df.nouritureMois())));

    }

    public Ressource(String user, Double aideFamille, Double job, Double bource, Double autre, Double loisir,
            Double weekEnd, Double sante, Double transport, Double nourriture) {

        try {
            Connection con = ConnectDB.connectDataB();
            Revenue r = new Revenue(aideFamille, job, bource, autre);
            this.aideFamille = new SimpleDoubleProperty(aideFamille);
            this.job = new SimpleDoubleProperty(job);
            this.bource = new SimpleDoubleProperty((r.bourseMois()));
            this.autre = new SimpleDoubleProperty(autre);
            this.revenueMois = new SimpleDoubleProperty(r.revenuMois());
  
            DepenceVariable dv = new DepenceVariable(loisir, weekEnd, sante);
            this.loisir = new SimpleDoubleProperty(dv.getLoisir());
            this.weekEnd = new SimpleDoubleProperty(dv.getWeekEnd());
            this.sante = new SimpleDoubleProperty(dv.getSante());

            DepenceFixe df = new DepenceFixe(transport, nourriture);
            this.transport = new SimpleDoubleProperty(df.transportMois());
            this.nourriture = new SimpleDoubleProperty(df.nouritureMois());
            this.depenceFixeMois = new SimpleDoubleProperty(df.depenseFixeMois());
            this.restVive = new SimpleDoubleProperty(r.resteVivre(df.transportMois(), df.nouritureMois()));
            PreparedStatement pst = con.prepareStatement("INSERT INTO DATA_ACCOUNT(USERNAME,AIDEFAMILLE,JOB,BOURCE,AUTRE,LOISIR,WEEKEND,SANTE,TRANSPORT,NOURRITURE,RESTVIVE,REVENUEMOIS,DEPENCEFIXEMOIS)"
                    + "VALUES('" + user + "','" + aideFamille + "','" + job + "','" + r.bourseMois() + "','" + autre
                    + "','" + loisir + "','" + weekEnd + "','" + sante + "','" + this.transport.get() + "','" + this.nourriture.get() + "','" + restVive.get() + "','" + revenueMois.get() + "','" + depenceFixeMois.get() + "')");
            pst.execute();
            pst.close();
            con.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Double getRevenueMois() {
        return revenueMois.get();
    }

    public void setRevenueMois(Double revenueMois) {
        this.revenueMois.set(revenueMois);
    }

    public Double getDepenceFixeMois() {
        return depenceFixeMois.get();
    }

    public void setDepenceFixeMois(Double depenceFixeMois) {
        this.depenceFixeMois.set(depenceFixeMois);
    }

    public Double getRestVive() {
        return restVive.get();
    }

    public void setRestVive(Double restVive) {
        this.restVive.set(restVive);
    }

    public Double getSante() {
        return sante.get();
    }

    public void setSante(Double sante) {
        this.sante.set(sante);
        ;
    }

    public Double getAideFamille() {
        return aideFamille.get();
    }

    public Double getJob() {
        return job.get();
    }

    public Double getBource() {
        return bource.get();
    }

    public Double getAutre() {
        return autre.get();
    }

    public Double getLoisir() {
        return loisir.get();
    }

    public Double getWeekEnd() {
        return weekEnd.get();
    }

    public Double getTransport() {
        return transport.get();
    }

    public Double getNourriture() {
        return nourriture.get();
    }

    public void setAideFamille(Double aideFamille) {
        this.aideFamille.set(aideFamille);
    }

    public void setJob(Double job) {
        this.job.set(job);
    }

    public void setBource(Double bource) {
        this.bource.set(bource);
    }

    public void setAutre(Double autre) {
        this.autre.set(autre);
    }

    public void setLoisir(Double loisir) {
        this.loisir.set(loisir);
    }

    public void setWeekEnd(Double weekEnd) {
        this.weekEnd.set(weekEnd);
    }

    public void setTransport(Double transport) {
        this.transport.set(transport);
    }

    public void setNourriture(Double nourriture) {
        this.nourriture.set(nourriture);
    }

    public static  ArrayList<Ressource> ListRessource(ArrayList<Ressource> list) {
        try {

            Connection con = ConnectDB.connectDataB();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM DATA_ACCOUNT WHERE USERNAME='" + FXMLDocumentController.user + "'");
            System.out.print(rs.next());
            while (rs.next()&& !rs.getString(1).equals("")) {
                list.add(new Ressource(rs.getDouble(2),
                        rs.getDouble(3),
                        rs.getDouble(4),
                        rs.getDouble(5),
                        rs.getDouble(6),
                        rs.getDouble(7),
                        rs.getDouble(8),
                        rs.getDouble(9),
                        rs.getDouble(10)));

            }
            st.close();
            rs.close();
            con.close();
        } catch (SQLException e) {
        }
        return list;
    }

}
