/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Chauffeur;
import java.sql.Connection;
import java.sql.SQLException;
import utils.MyDB;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import typeenumeration.Region;
import entities.Location;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.TreeMap;

/**
 *
 * @author eya
 */
public class ChauffeurService implements IService<Chauffeur>, IChauffeur<Chauffeur> {

    Connection cnx;

    public ChauffeurService() {
        cnx = MyDB.getInstance().getCnx();
    }

    public TreeMap<Integer, Integer> nombrechauffeurBysiege() throws SQLException {

        TreeMap<Integer, Integer> chauffeurs = new TreeMap<Integer, Integer>();
        String req = "SELECT id_siege, COUNT(idchauffeur) as nb_chauffeur\n"
                + "FROM chauffeur\n"
                + "GROUP BY id_siege;";
        PreparedStatement ps = cnx.prepareStatement(req);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            chauffeurs.put(rs.getInt("id_siege"), rs.getInt("nb_chauffeur"));

        }

        return chauffeurs;
    }

    @Override

    public void ajouter(Chauffeur c) throws SQLException {

        System.out.println(c);
        String req = "INSERT INTO chauffeur(region,contact,cin,adresse,permis,image,prix_par_jour,nom,prenom,permis_arriere,id_siege)VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, c.getRegion().toString());
        ps.setInt(2, c.getContact());
        ps.setInt(3, c.getCin());
        ps.setString(4, c.getAdresse());
        ps.setString(5, c.getPermis());
        ps.setString(6, c.getImage());
        ps.setFloat(7, c.getPrix_par_jour());
        ps.setString(8, c.getNom());
        ps.setString(9, c.getPrenom());
        ps.setString(10, c.getPermis_arriere());
        ps.setInt(11, c.getId_siege());

        ps.executeUpdate();

    }

    @Override
    public void modifier(Chauffeur c) throws SQLException {
        String req = "UPDATE chauffeur SET region=?,contact=?,cin=?,adresse=?,"
                + "permis=?,image=?,prix_par_jour=?,nom=?,"
                + "prenom=?,permis_arriere =?,id_siege =? where Idchauffeur = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, c.getRegion().toString());
        ps.setInt(2, c.getContact());
        ps.setInt(3, c.getCin());
        ps.setString(4, c.getAdresse());
        ps.setString(5, c.getPermis());
        ps.setString(6, c.getImage());
        ps.setFloat(7, c.getPrix_par_jour());
        ps.setString(8, c.getNom());
        ps.setString(9, c.getPrenom());
        ps.setString(10, c.getPermis_arriere());
        ps.setInt(11, c.getId_siege());
        ps.setInt(12, c.getIdchauffeur());
        ps.executeUpdate();
    }

    @Override
    public void supprimer(Chauffeur c) throws SQLException {
        String req2 = "Update location SET id_chauffeur=null where id_chauffeur =?";
        PreparedStatement ps2 = cnx.prepareStatement(req2);
        ps2.setInt(1, c.getIdchauffeur());
        ps2.executeUpdate();
        String req = "DELETE FROM chauffeur WHERE Idchauffeur = ?";
        PreparedStatement ps = cnx.prepareStatement(req);

        ps.setInt(1, c.getIdchauffeur());

        ps.executeUpdate();

    }

    @Override
    public List<Chauffeur> recuperer() throws SQLException {
        List<Chauffeur> Chauffeurs = new ArrayList<>();
        String s = "select * from chauffeur";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(s);
        while (rs.next()) {
            Chauffeur c = new Chauffeur();
            c.setIdchauffeur(rs.getInt("idchauffeur"));
            c.setRegion(Region.valueOf(rs.getString("region")));
            c.setContact(rs.getInt("contact"));
            c.setCin(rs.getInt("cin"));
            c.setAdresse(rs.getString("adresse"));
            c.setPermis(rs.getString("permis"));
            c.setImage(rs.getString("image"));
            c.setPrix_par_jour(rs.getInt("Prix_par_jour"));
            c.setNom(rs.getString("nom"));
            c.setPrenom(rs.getString("prenom"));
            c.setPermis_arriere(rs.getString("permis_arriere"));
            c.setId_siege(rs.getInt("Id_siege"));
            Chauffeurs.add(c);

        }
        return Chauffeurs;
    }

    @Override
    public Chauffeur recupererById(int t) throws SQLException {
        Chauffeur c = new Chauffeur();
        String req = "select * from chauffeur where Idchauffeur = ? ";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            int id = rs.getInt("Idchauffeur");
            Region Reg = Region.valueOf(rs.getString("region"));
            int Contact = rs.getInt("contact");
            int Cin = rs.getInt("cin");
            String Adresse = rs.getString("adresse");
            String Permis = rs.getString("permis");
            String Image = rs.getString("image");
            float Prix_par_jour = rs.getInt("Prix_par_jour");
            String Nom = rs.getString("nom");
            String Prenom = rs.getString("prenom");
            String Permis_arriere = rs.getString("permis_arriere");
            int id_siege = rs.getInt("id_siege");

            c.setIdchauffeur(id);
            c.setRegion(Reg);
            c.setContact(Contact);
            c.setCin(Cin);
            c.setAdresse(Adresse);
            c.setPermis(Permis);
            c.setImage(Image);
            c.setPrix_par_jour(Prix_par_jour);
            c.setNom(Nom);
            c.setPrenom(Prenom);
            c.setPermis_arriere(Permis_arriere);
            c.setId_siege(id_siege);
        }
        return c;
    }

    @Override
    public List<Chauffeur> recupererChauffeursDisponibles(Location l) throws SQLException {
        String req = "SELECT DISTINCT c.* from chauffeur c , location l \n"
                + "where l.id_chauffeur is null\n"
                + "AND c.idchauffeur not IN(SELECT DISTINCT l.id_chauffeur FROM chauffeur c , location l\n"
                + "where l.id_chauffeur = c.idchauffeur \n"
                + "and l.date_debut BETWEEN ? AND ? \n"
                + "or l.date_fin BETWEEN ? AND ? \n"
                + "and  l.id_chauffeur is not null);";

        PreparedStatement ps = cnx.prepareStatement(req);

        List<Chauffeur> Chauffeurs = new ArrayList<>();

        ps.setTimestamp(1, new Timestamp(l.getDate_debut().getTime()));
        ps.setTimestamp(2, new Timestamp(l.getDate_fin().getTime()));
        ps.setTimestamp(3, new Timestamp(l.getDate_debut().getTime()));
        ps.setTimestamp(4, new Timestamp(l.getDate_fin().getTime()));

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            Chauffeur c = new Chauffeur();
            c.setIdchauffeur(rs.getInt("idchauffeur"));
            c.setRegion(Region.valueOf(rs.getString("region")));
            c.setContact(rs.getInt("contact"));
            c.setCin(rs.getInt("cin"));
            c.setAdresse(rs.getString("adresse"));
            c.setPermis(rs.getString("permis"));
            c.setImage(rs.getString("image"));
            c.setPrix_par_jour(rs.getInt("Prix_par_jour"));
            c.setNom(rs.getString("nom"));
            c.setPrenom(rs.getString("prenom"));
            c.setPermis_arriere(rs.getString("permis_arriere"));
            c.setId_siege(rs.getInt("Id_siege"));
            Chauffeurs.add(c);

        }

        return Chauffeurs;
    }

    @Override
    public List<Chauffeur> recupererChauffeurBYidSiege(int i) throws SQLException {
        List<Chauffeur> Chauffeurs = new ArrayList<>();
        String req = "select * from chauffeur where id_siege = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, i);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Chauffeur c = new Chauffeur();
            c.setIdchauffeur(rs.getInt("idchauffeur"));
            c.setRegion(Region.valueOf(rs.getString("region")));
            c.setContact(rs.getInt("contact"));
            c.setCin(rs.getInt("cin"));
            c.setAdresse(rs.getString("adresse"));
            c.setPermis(rs.getString("permis"));
            c.setImage(rs.getString("image"));
            c.setPrix_par_jour(rs.getInt("Prix_par_jour"));
            c.setNom(rs.getString("nom"));
            c.setPrenom(rs.getString("prenom"));
            c.setPermis_arriere(rs.getString("permis_arriere"));
            c.setId_siege(rs.getInt("Id_siege"));
            Chauffeurs.add(c);

        }
        return Chauffeurs;
    }
}
