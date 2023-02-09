/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;
import typeenumeration.Carburant;
import typeenumeration.Etat;
import typeenumeration.NbrPlace;

/**
 *
 * @author anasm
 */
public class Vehicule {
    
    private int idvehicule,kilometrage,id_siege;
    private float prix_vente,prix_par_jour;
    private String marque,immatriculation;
    private Carburant carburant;
    private NbrPlace nbr_place;
    private Etat etat;
    private Date annee_circulation;

    public Vehicule() {
    }

    public Vehicule(int idvehicule, int kilometrage, float prix_vente, int id_siege, float prix_par_jour, String marque, String immatriculation, Carburant carburant, NbrPlace nbr_place, Etat etat, Date annee_circulation) {
        this.idvehicule = idvehicule;
        this.kilometrage = kilometrage;
        this.prix_vente = prix_vente;
        this.id_siege = id_siege;
        this.prix_par_jour = prix_par_jour;
        this.marque = marque;
        this.immatriculation = immatriculation;
        this.carburant = carburant;
        this.nbr_place = nbr_place;
        this.etat = etat;
        this.annee_circulation = annee_circulation;
    }

    public int getIdvehicule() {
        return idvehicule;
    }

    public void setIdvehicule(int idvehicule) {
        this.idvehicule = idvehicule;
    }

    public int getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(int kilometrage) {
        this.kilometrage = kilometrage;
    }

    public float getPrix_vente() {
        return prix_vente;
    }

    public void setPrix_vente(float prix_vente) {
        this.prix_vente = prix_vente;
    }

    public int getId_siege() {
        return id_siege;
    }

    public void setId_siege(int id_siege) {
        this.id_siege = id_siege;
    }

    public float getPrix_par_jour() {
        return prix_par_jour;
    }

    public void setPrix_par_jour(float prix_par_jour) {
        this.prix_par_jour = prix_par_jour;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public Carburant getCarburant() {
        return carburant;
    }

    public void setCarburant(Carburant carburant) {
        this.carburant = carburant;
    }

    public NbrPlace getNbr_place() {
        return nbr_place;
    }

    public void setNbr_place(NbrPlace nbr_place) {
        this.nbr_place = nbr_place;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public Date getAnnee_circulation() {
        return annee_circulation;
    }

    public void setAnnee_circulation(Date annee_circulation) {
        this.annee_circulation = annee_circulation;
    }

    @Override
    public String toString() {
        return "Vehicule{" + "idvehicule=" + idvehicule + ", kilometrage=" + kilometrage + ", prix_vente=" + prix_vente + ", id_siege=" + id_siege + ", prix_par_jour=" + prix_par_jour + ", marque=" + marque + ", immatriculation=" + immatriculation + ", carburant=" + carburant + ", nbr_place=" + nbr_place + ", etat=" + etat + ", annee_circulation=" + annee_circulation + '}';
    }
    
    
    
}
