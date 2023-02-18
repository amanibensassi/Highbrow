/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Year;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import typeenumeration.Carburant;
import typeenumeration.Etat;
import typeenumeration.NbrPlace;
import typeenumeration.Region;

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
    private Date date_circulation;
    private String image_vehicule;
    public Vehicule() {
    }


    
    public Vehicule(int idvehicule, String marque, int kilometrage, String immatriculation, Carburant carburant, NbrPlace nbr_place, float prix_par_jour, Etat etat, float prix_vente, int id_siege, Date date_circulation,String image_vehicule) {
        this.idvehicule = idvehicule;
        this.kilometrage = kilometrage;
        this.prix_vente = prix_vente;
        this.id_siege = id_siege;
        this.prix_par_jour = prix_par_jour;
        this.marque = marque;
        Pattern pattern = Pattern.compile("^\\d{3}TUN\\d{4}$");
        Matcher matcher = pattern.matcher(immatriculation);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("L'immatriculation saisie n'est pas valide. Elle doit être sous la forme ***TUN**** (les étoiles doivent être des chiffres).");
        }
        this.immatriculation = immatriculation;
        this.carburant = carburant;
        this.nbr_place = nbr_place;
        this.etat = etat;
                // Vérification que la date de circulation est valide
        int annéeActuelle = LocalDate.now().getYear();
        int minAnnee = 1970;
        this.date_circulation = date_circulation;
        this.image_vehicule = image_vehicule;
    }

//    public Vehicule(int i, int i0, Region region, String adresse_7, String mail_6, int i1, int i2) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

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
                Pattern pattern = Pattern.compile("^\\d{3}TUN\\d{4}$");
        Matcher matcher = pattern.matcher(immatriculation);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("L'immatriculation saisie n'est pas valide. Elle doit être sous la forme ***TUN**** (les étoiles doivent être des chiffres).");
        }
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

    public Date getDate_circulation() {
        return date_circulation;
    }

    public void setDate_circulation(Date date_circulation) {
        this.date_circulation = date_circulation;
    }

    public String getImage_vehicule() {
        return image_vehicule;
    }

    public void setImage_vehicule(String image_vehicule) {
        this.image_vehicule = image_vehicule;
    }

    @Override
    public String toString() {
        return " Vehicule { " + "idvehicule = " + idvehicule + ", kilometrage = " + kilometrage + ", id_siege = " + id_siege + ", prix_vente = " + prix_vente + ", prix_par_jour = " + prix_par_jour + ", marque = " + marque + ", immatriculation = " + immatriculation + ", carburant = " + carburant + ", nbr_place = " + nbr_place + ", etat = " + etat + ", date_circulation = " + date_circulation + ", image_vehicule = " + image_vehicule + "}" + "\n";
    }
    
    
   
    
    
    
}
