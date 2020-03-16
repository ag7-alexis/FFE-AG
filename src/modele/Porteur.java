package modele;

import java.util.ArrayList;

/**
 * @version Prototype_1
 * @author Alexis
 * Class Porteur qui correspond à la représentation d'un porteur sous forme d'objet.
 * Attributs : - licence (le numéro de licence qui permet d'identifier un porteur)
 *             - nom_por (le nom du porteur)
 *             - pre_por (le prenom du porteur)
 *             - club_mandate (le code du club mandateur du porteur)
 *             - porte_pouvoir (la liste des clubs portés par le porteur)
 */
public class Porteur {

    private String licence;
    private String nom_por;
    private String pre_por;
    private String club_mandate;
    private ArrayList<Club> porte_pouvoir;

    /**
     * Constructeur : Porteur
     * @param licence
     * @param nom_por
     * @param pre_por
     * @param club_mandate
     * @param porte_pouvoir
     * Constructeur d'un objet Porteur.
     */
    public Porteur(String licence, String nom_por, String pre_por, String club_mandate, ArrayList<Club> porte_pouvoir) {
        super();
        this.licence = licence;
        this.nom_por = nom_por;
        this.pre_por = pre_por;
        this.club_mandate = club_mandate;
        this.porte_pouvoir = porte_pouvoir;
    }


    /**
     * Methode : setLicence
     * @param licence (le numéro de licence d'un porteur)
     * Attribut un numéro de licence à un objet Porteur.
     */
    public void setLicence(String licence) {
        this.licence = licence;
    }

    /**
     * Methode : setNom_por
     * @param nom_por (le nom d'un porteur)
     * Attribut un nom à un objet Porteur.
     */
    public void setNom_por(String nom_por) {
        this.nom_por = nom_por;
    }

    /**
     * Methode : setPre_por
     * @param pre_por (le prenom d'un porteur)
     * Attribut un prénom à un objet Porteur.
     */
    public void setPre_por(String pre_por) {
        this.pre_por = pre_por;
    }

    /**
     * Methode : setClub_mandate
     * @param club_mandate (le code du club mandateur d'un porteur)
     * Attribut un Club à un objet Porteur.
     */
    public void setClub_mandate(String club_mandate) {
        this.club_mandate = club_mandate;
    }

    /**
     * Methode : setPorte_pouvoir
     * @param porte_pouvoir (une liste de club qui ont leur pouvoir porté par un porteur)
     * Attribut une collection de Club à un objet Porteur.
     */
    public void setPorte_pouvoir(ArrayList<Club> porte_pouvoir) {
        this.porte_pouvoir = porte_pouvoir;
    }

    /**
     * Methode : getLicence
     * @return un numéro de licence sous forme de String
     * Récupère le numéro de licence d'un objet Porteur.
     */
    public String getLicence() {
        return licence;
    }

    /**
     * Methode : getNom_por
     * @return un nom sous forme de String
     * Récupère le nom d'un objet Porteur
     */
    public String getNom_por() {
        return nom_por;
    }

    /**
     * Methode : getPre_por
     * @return un prénom sous forme de String
     * Récupère le prénom d'un objet Porteur.
     */
    public String getPre_por() {
        return pre_por;
    }

    /**
     * Methode : getClub_mandate
     * @return le code du club mandateur sous forme de String
     * Récupère le code du club mandateur d'un objet Porteur.
     */
    public String getClub_mandate() {
        return club_mandate;
    }

    /**
     * Methode : getPorte_pouvoir
     * @return une collection de clubs sous forme de ArrayList
     * Récupère la collection des clubs dont l'objet porteur porte les pouvoirs.
     */
    public ArrayList<Club> getPorte_pouvoir() {
        return porte_pouvoir;
    }
    


    @Override
    public String toString() {
        return "Porteur{" + "licence=" + licence + ", nom_por=" + nom_por + ", pre_por=" + pre_por + ", club_mandate=" + club_mandate + ", porte_pouvoir=" + porte_pouvoir + '}';
    }
    



    
    
    

}
