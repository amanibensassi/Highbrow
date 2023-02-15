/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Utilisateur;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import typeenumeration.Role;
import utils.MyDB;

/**
 *
 * @author Hamma
 */
public class UserService implements IService<Utilisateur>,IUser<Utilisateur>{

    Connection cnx;

    public UserService() {
        cnx = MyDB.getInstance().getCnx();
    }

    @Override
    public void ajouter(Utilisateur t) throws SQLException {
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

//        String req = "INSERT INTO utilisateur(nom,prenom,mail,num_tel,date_naissance,mot_de_passe,role,photopermis_avant,image,photopermis_arriere) VALUES("
//                + "'" + t.getNom() + "','" + t.getPrenom() + "','" + t.getMail() + "',"+ t.getNum_tel() + ",'" + t.getDate_naissance() + "','" + t.getMot_de_passe()
//                +"','"+ t.getRole().toString()+ "','" + t.getPhotopermis_avant() + "','" + t.getImage() + "','" + t.getPhotopermis_arriere() +
//                ")";
//        Statement st = cnx.createStatement();
//        st.executeUpdate(req);
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
@Override
    public void modifierPassword(Utilisateur t) throws SQLException {
        String req = "UPDATE utilisateur SET mot_de_passe = ? where idutilisateur = ? ";
        PreparedStatement ps = cnx.prepareCall(req);
        ps.setString(1, t.getMot_de_passe());
        ps.setInt(2, t.getIdutilisateur());
        ps.executeUpdate();
    }

    @Override
    public void supprimer(Utilisateur t) throws SQLException {
        String req = "DELETE FROM utilisateur WHERE idutilisateur = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t.getIdutilisateur());
        ps.executeUpdate();

    }

    @Override
    public List<Utilisateur> recuperer(Utilisateur t) throws SQLException {
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
 @Override
    public void desactiver(Utilisateur t) throws SQLException {
        String req = "UPDATE utilisateur SET etat = ? where idutilisateur = ? ";
        PreparedStatement ps = cnx.prepareCall(req);
        ps.setBoolean(1, t.isEtat());
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
    public boolean authenticate(String mail, String password) {
        try {
            String sql = "SELECT * FROM utilisateur WHERE mail = ? AND mot_de_passe = ?";
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setString(1, mail);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                return true;
            }
        } catch (SQLException e) {
        }
        return false;
    }
}

//    private void authentification(Utilisateur u) throws SQLException {
//        int id=0;
//       PreparedStatement ps = null;
//       
//       ResultSet rs = null;
//       try {
//                        ps = cnx.prepareStatement("SELECT idutilisateur FROM utilisateur WHERE mail=? AND mot_de_passe =?");
//                        ps.setString(1,u.getMail());
//                        ps.setString(2, u.getMot_de_passe());
//                        rs = ps.executeQuery();
//                        id = rs.getInt("idutilisatuer");
//                        while (rs.next()) {
//                            u.setMail(rs.getString("mail"));
//                            u.setMot_de_passe(rs.getString("mot_de_passe"));
//                            
////                            if   ((u.setMail(rs.getString("mail")).equals (u.getMail()) && u.setMot_de_passe(rs.getString("mot_de_passe")).equals(u.getMot_de_passe()) )) {
////                                System.out.println("connexion validée");
////                            }
//////                            
//                            
//                            
//                            
//                        
////                        }
// 
//     
//    }catch(SQLException e){}
//       
//    }
//logout

