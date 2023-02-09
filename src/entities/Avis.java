/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import typeenumeration.Note;

/**
 *
 * @author anasm
 */
public class Avis {
    
    private int id,id_vehicule,idsiege,id_Utilisateur;
    private Note note;

    public Avis() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_vehicule() {
        return id_vehicule;
    }

    public void setId_vehicule(int id_vehicule) {
        this.id_vehicule = id_vehicule;
    }

    public int getIdsiege() {
        return idsiege;
    }

    public void setIdsiege(int idsiege) {
        this.idsiege = idsiege;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Avis{" + "id=" + id + ", id_vehicule=" + id_vehicule + ", idsiege=" + idsiege + ", note=" + note + '}';
    }
    
    
    
}
