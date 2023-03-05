/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Utilisateur;
import java.io.IOException;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import typeenumeration.Role;
import utils.MyDB;

/**
 *
 * @author Hamma
 */
public class UserService implements IService<Utilisateur>, IUser<Utilisateur> {

    Connection cnx;

    public UserService() {
        cnx = MyDB.getInstance().getCnx();
    }

    @Override
    public void ajouter(Utilisateur t) throws SQLException {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
        Matcher matcher = pattern.matcher(t.getMail());
        Pattern p = Pattern.compile("^\\d{8}$");
        Matcher m = p.matcher(Integer.toString(t.getNum_tel()));
        if (!matcher.matches() | !m.matches()) {
            throw new IllegalArgumentException("L'adresse e-mail saisie n'est pas valide.");
        } else {
            String req = "INSERT INTO utilisateur (nom,prenom,mail,num_tel,date_naissance,mot_de_passe,role,photopermis_avant,image,photopermis_arriere,etat) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareCall(req);
            ps.setString(1, t.getNom());
            ps.setString(2, t.getPrenom());
            ps.setString(3, t.getMail());
            ps.setInt(4, t.getNum_tel());
            ps.setTimestamp(5, new Timestamp(t.getDate_naissance().getTime()));
            ps.setString(6, t.getMot_de_passe());
            ps.setString(7, t.getRole().toString());
            ps.setString(8, t.getPhotopermis_avant());
            ps.setString(9, t.getImage());
            ps.setString(10, t.getPhotopermis_arriere());
            ps.setBoolean(11, t.isEtat());

            ps.executeUpdate();
        }

    }

    @Override
    public void modifier(Utilisateur t) throws SQLException {
        String req = "UPDATE  utilisateur SET nom = ?,prenom = ? ,mail = ? ,num_tel = ?,date_naissance = ?,mot_de_passe = ?,role = ? ,photopermis_avant = ?,image = ?,photopermis_arriere=? where idutilisateur = ? ";
        PreparedStatement ps = cnx.prepareCall(req);
        ps.setString(1, t.getNom());
        ps.setString(2, t.getPrenom());
        ps.setString(3, t.getMail());
        ps.setInt(4, t.getNum_tel());
        ps.setTimestamp(5, new Timestamp(t.getDate_naissance().getTime()));
        ps.setString(6, t.getMot_de_passe());
        ps.setString(7, t.getRole().toString());
        ps.setString(8, t.getPhotopermis_avant());
        ps.setString(9, t.getImage());
        ps.setString(10, t.getPhotopermis_arriere());
        ps.setInt(11, t.getIdutilisateur());
        ps.executeUpdate();
    }

    public void modifierPassword(String email, String pwd) throws SQLException {
        Utilisateur u = new Utilisateur();
        try {
            String req = "UPDATE utilisateur SET mot_de_passe=? WHERE mail=?";
            PreparedStatement pst = cnx.prepareStatement(req);

            pst.setString(1, pwd);
            pst.setString(2, email);
            pst.executeUpdate();
            System.out.println("The password was updated successfully!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Utilisateur t) throws SQLException {
        String req = "DELETE FROM utilisateur WHERE idutilisateur = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t.getIdutilisateur());
        ps.executeUpdate();

    }

    @Override
    public List<Utilisateur> recuperer() throws SQLException {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        String s = "select * from utilisateur";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(s);
        while (rs.next()) {
            Utilisateur u = new Utilisateur();
            u.setIdutilisateur(rs.getInt("idutilisateur"));
            u.setNom(rs.getString("nom"));
            u.setPrenom(rs.getString("prenom"));
            u.setMail(rs.getString("mail"));
            u.setNum_tel(rs.getInt("num_tel"));
            u.setDate_naissance(rs.getDate("date_naissance"));
            u.setMot_de_passe(rs.getString("mot_de_passe"));
            u.setRole(Role.valueOf(rs.getString("role")));
            u.setPhotopermis_avant(rs.getString("photopermis_avant"));
            u.setImage(rs.getString("image"));
            u.setPhotopermis_arriere(rs.getString("photopermis_arriere"));
            u.setEtat(rs.getBoolean("etat"));
            utilisateurs.add(u);

        }
        return utilisateurs;
    }

    //getuserbyid utilisateur
    @Override
    public Utilisateur recupererById(int id) throws SQLException {
        String req = "select * from utilisateur where idutilisateur = ?";
        PreparedStatement ps = cnx.prepareCall(req);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        Utilisateur u = new Utilisateur();

        if (rs.next()) {
            u.setIdutilisateur(rs.getInt("idutilisateur"));
            u.setNom(rs.getString("nom"));
            u.setPrenom(rs.getString("prenom"));
            u.setMail(rs.getString("mail"));
            u.setNum_tel(rs.getInt("num_tel"));
            u.setDate_naissance(rs.getDate("date_naissance"));
            u.setMot_de_passe(rs.getString("mot_de_passe"));
            u.setRole(Role.valueOf(rs.getString("role")));
            u.setPhotopermis_avant(rs.getString("photopermis_avant"));
            u.setImage(rs.getString("image"));
            u.setPhotopermis_arriere(rs.getString("photopermis_arriere"));
            u.setEtat(rs.getBoolean("etat"));

        }
        return u;
    }

    public List<Utilisateur> recupererByEtat() throws SQLException {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        String s = "select * from utilisateur where etat=0";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(s);
        while (rs.next()) {
            Utilisateur u = new Utilisateur();
            u.setIdutilisateur(rs.getInt("idutilisateur"));
            u.setNom(rs.getString("nom"));
            u.setPrenom(rs.getString("prenom"));
            u.setMail(rs.getString("mail"));
            u.setNum_tel(rs.getInt("num_tel"));
            u.setDate_naissance(rs.getDate("date_naissance"));
            u.setMot_de_passe(rs.getString("mot_de_passe"));
            u.setRole(Role.valueOf(rs.getString("role")));
            u.setPhotopermis_avant(rs.getString("photopermis_avant"));
            u.setImage(rs.getString("image"));
            u.setPhotopermis_arriere(rs.getString("photopermis_arriere"));
            u.setEtat(rs.getBoolean("etat"));
            utilisateurs.add(u);

        }
        return utilisateurs;
    }

    @Override
    public void desactiver(Utilisateur t) throws SQLException {
        String req = "UPDATE utilisateur SET etat = ? where idutilisateur = ? ";
        PreparedStatement ps = cnx.prepareCall(req);
        ps.setBoolean(1, false);
        ps.setInt(2, t.getIdutilisateur());
        ps.executeUpdate();
    }

    @Override

    public void approuver(Utilisateur t) throws SQLException {
        String req = "UPDATE utilisateur SET etat = ? where idutilisateur = ? ";
        PreparedStatement ps = cnx.prepareCall(req);
        ps.setBoolean(1, true);
        ps.setInt(2, t.getIdutilisateur());
        ps.executeUpdate();
    }

    @Override
    public Utilisateur authenticate(String mail, String password) {
        Utilisateur u = new Utilisateur();
        try {

            String sql = "SELECT * FROM utilisateur WHERE mail = ? AND mot_de_passe = ?";
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setString(1, mail);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

//                 
//                         Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                            alert.setTitle("Welcome");
//                            alert.setHeaderText("WELCOME TO FastRent");
//                            alert.setContentText("You're connected");
//                            alert.show();
//                            Stage primaryStage=new Stage();
                u.setIdutilisateur(rs.getInt("idutilisateur"));
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                u.setMail(rs.getString("mail"));
                u.setNum_tel(rs.getInt("num_tel"));
                u.setDate_naissance(rs.getDate("date_naissance"));
                u.setMot_de_passe(rs.getString("mot_de_passe"));
                u.setRole(Role.valueOf(rs.getString("role")));
                u.setPhotopermis_avant(rs.getString("photopermis_avant"));
                u.setImage(rs.getString("image"));
                u.setPhotopermis_arriere(rs.getString("photopermis_arriere"));
                u.setEtat(rs.getBoolean("etat"));

                //METTRE LES VARIABLES STATIQUES!!!!!!!!!!!!!!!!!!!!!
                
                UserConn.role = Role.valueOf(rs.getString("role"));
                UserConn.idutilisateur = rs.getInt("idutilisateur");
                UserConn.num_tel = rs.getInt("num_tel");
                UserConn.nom = rs.getString("nom");
                UserConn.prenom = rs.getString("prenom");
                UserConn.mail = rs.getString("mail");
                UserConn.mot_de_passe = rs.getString("mot_de_passe");
                UserConn.photopermis_avant = rs.getString("photopermis_avant");
                UserConn.photopermis_arriere = rs.getString("photopermis_arriere");
                UserConn.image = rs.getString("image");
                UserConn.date_naissance = rs.getDate("date_naissance");
                UserConn.etat = rs.getBoolean("etat");

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Echec de connexion");
                alert.setHeaderText("Attention !!");
                alert.setContentText("Veuilliez verifier votre email ou votre mot de passe ");
                alert.show();
            }

        } catch (SQLException e) {

        }
        return u;
    }

    public String role_selection(String mail) throws SQLException {

        String req = "select * from utilisateur where mail = ?";
        PreparedStatement ps = cnx.prepareCall(req);
        ps.setString(1, mail);
        ResultSet rs = ps.executeQuery();
        String role = "";

        if (rs.next()) {

            role = rs.getString("role");

        }
        return role;
    }
}
