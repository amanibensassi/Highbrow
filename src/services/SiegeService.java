/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Siege;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.omg.CORBA.NameValuePair;
import utils.MyDB;
import typeenumeration.Region;
import java.util.List;

/**
 *
 * @author Trabelsi Mohamed
 */
public class SiegeService implements IService<Siege>, ISiege<Siege> {

    Connection cnx;

    public SiegeService() {
        cnx = MyDB.getInstance().getCnx();
    }

    @Override
    public void ajouter(Siege t) throws SQLException, MessagingException {
        String req = "INSERT INTO siege(nom_siege,region,adresse,mail,num_tel_siege,id_utilisateur) VALUES("
                + "'" + t.getNom_siege() + "','" + t.getRegion() + "','" + t.getAdresse() + "','"
                + t.getMail() + "','" + t.getNum_tel_siege() + "','" + t.getId_utilisateur() + "'" + ")";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);

        final String username = "mouhamedtrabelsi.28@gmail.com"; // Votre adresse e-mail
        final String password = "vrbbphxzfhhzhxzk"; // Votre mot de passe

        String to = t.getMail(); // Adresse e-mail du destinataire
        String subject = "Bienvenue chez nous !"; // Sujet de l'e-mail
        String body = "Bonjour " + t.getNom_siege() + ",\n\nBienvenue chez nous, FastRent ya dawla  !"; // Corps de l'e-mail

        // Configuration des propriétés pour l'envoi de l'e-mail
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        // Création de la session de messagerie avec l'authentification de l'utilisateur
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Création du message de l'e-mail
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            // Envoi de l'e-mail
            Transport.send(message);

            System.out.println("L'e-mail de bienvenue a été envoyé à " + to);
        } catch (MessagingException e) {
            System.err.println("error" + e.getMessage());
        }

        System.out.println("siege ajouté avec succés");
    }

    @Override
    public void modifier(Siege t) throws SQLException {
        String req = "UPDATE siege SET nom_siege = ?,region = ?,adresse = ?,mail = ?,num_tel_siege = ?,id_utilisateur = ? where idsiege = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getNom_siege());
        ps.setString(2, t.getRegion().toString());
        ps.setString(3, t.getAdresse());
        ps.setString(4, t.getMail());
        ps.setInt(5, t.getNum_tel_siege());
        ps.setInt(6, t.getId_utilisateur());
        ps.setInt(7, t.getIdsiege());
        ps.executeUpdate();
        System.out.println("siege modifié avec succés");
    }

    @Override
    public void supprimer(Siege t) throws SQLException {
        String req = "DELETE FROM siege where idsiege = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t.getIdsiege());
        ps.executeUpdate();
        System.out.println("siege supprimé avec succés");
        //return true;

    }

    @Override
    public List<Siege> recuperer() throws SQLException {
        List<Siege> sieges = new ArrayList<>();
        String s = "select * from siege";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(s);
        while (rs.next()) {
            Siege p = new Siege();
            p.setNom_siege(rs.getString("nom_siege"));
            p.setRegion(Region.valueOf(rs.getString("region")));
            p.setAdresse(rs.getString("adresse"));
            p.setMail(rs.getString("mail"));
            p.setNum_tel_siege(rs.getInt("num_tel_siege"));
            p.setId_utilisateur(rs.getInt("id_utilisateur"));
            p.setIdsiege(rs.getInt("idsiege"));

            sieges.add(p);
        }
        return sieges;
    }

    @Override
    public List<Siege> recupererSiegeByRegion(Region r) throws SQLException {
        List<Siege> sieges = new ArrayList<>();
        String s = "select * from siege WHERE region = ?";
        PreparedStatement ps = cnx.prepareStatement(s);
        ps.setString(1, r.toString());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Siege p = new Siege();
            p.setNom_siege(rs.getString("nom_siege"));
            p.setRegion(Region.valueOf(rs.getString("region")));
            p.setAdresse(rs.getString("adresse"));
            p.setMail(rs.getString("mail"));
            p.setNum_tel_siege(rs.getInt("num_tel_siege"));
            p.setId_utilisateur(rs.getInt("id_utilisateur"));
            p.setIdsiege(rs.getInt("idsiege"));

            sieges.add(p);
        }
        return sieges;
    }

    @Override
    public List<Siege> recupererSiegeByUtilisateur(int x) throws SQLException {
        List<Siege> sieges = new ArrayList<>();
        String s = "select * from siege WHERE id_utilisateur = ?";
        PreparedStatement ps = cnx.prepareStatement(s);
        ps.setInt(1, x);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Siege p = new Siege();
            p.setNom_siege(rs.getString("nom_siege"));
            p.setRegion(Region.valueOf(rs.getString("region")));
            p.setAdresse(rs.getString("adresse"));
            p.setMail(rs.getString("mail"));
            p.setNum_tel_siege(rs.getInt("num_tel_siege"));
            p.setId_utilisateur(rs.getInt("id_utilisateur"));
            p.setIdsiege(rs.getInt("idsiege"));

            sieges.add(p);
        }
        return sieges;
    }

    public Siege recupererById(int id) throws SQLException {

        Siege p = new Siege();
        String req = "select * from siege where idsiege = ? ";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            p.setNom_siege(rs.getString("nom_siege"));
            p.setRegion(Region.valueOf(rs.getString("region")));
            p.setAdresse(rs.getString("adresse"));
            p.setMail(rs.getString("mail"));
            p.setNum_tel_siege(rs.getInt("num_tel_siege"));
            p.setId_utilisateur(rs.getInt("id_utilisateur"));
            p.setIdsiege(rs.getInt("idsiege"));

        }
        return p;

    }
}
