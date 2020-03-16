
package controleur;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import modele.*;
import vue.*;


/**
 * @version Prototype_1
 * @author GUAY Alexis
 * 
 * Class Main qui comporte toute les methodes relatives à la partie Back-End de l'application et qui est le point d'entrée de celle-ci.
 */
public class Main {
    
    private final Accueil screen;
    private final AccesData con;
    private final ArrayList<Club> lesClubs;
    private final ArrayList<Porteur> lesPorteurs;
    
    
    /**
     * Constructeur de la class Main avec instantiation de la base de données, récupération des collections de clubs et de porteurs
     * à partir de la base de données, et affichage de la frame Accueil.
     */
    public Main(){
        con = new AccesData();  
        lesClubs = con.getLesClubs();        
        lesPorteurs = con.getLesPorteurs();        
        screen = new Accueil(this);
        screen.setVisible(true);     
    }
    
    /**
     * Methode : getLesClubs
     * @return la collection de clubs
     */
    public ArrayList<Club> getLesClubs (){
        return lesClubs;
    }
    
    /**
     * Methode : getLesPorteurs
     * @return la collection de porteurs
     */
    public ArrayList<Porteur> getLesPorteurs(){
        return lesPorteurs;
    }
    
    /**
     * Methode : getClub
     * @param cod_clu (code d'identification d'un club)
     * @return le club correspondant au code passé en paramètre
     */
    public Club getClub(String cod_clu){        
        for (Club leClub : lesClubs) {
            if(leClub.getCode_club().equals(cod_clu)){
                return leClub; 
            }
        }
        return null;
    }
    
    /**
     * Methode : getPorteur
     * @param num_licence (le numéro de licence qui identifie un porteur)
     * @return le porteur correspondant au numéro passé en paramètre
     */
    public Porteur getPorteur(String num_licence){
        for (Porteur lePorteur : lesPorteurs) {
            if(lePorteur.getLicence().equals(num_licence)){
                return lePorteur;
            }
        }
        return null;
    }
    
    /**
     * Methode : adPorteur
     * @param p (un objet porteur)
     * Ajoute le porteur entré en paramètre dans la collection de porteur.
     */
    public void adPorteur(Porteur p){
        lesPorteurs.add(p);
    }
    
    /**
     * Methode : getcodeClub
     * @return un tableau qui comporte tous les codes des clubs qui n'ont pas de porteur qui les représentent
     * Methode utilisé pour remplir le combo-box de la frame AccueilPorteur.
     */
    public String[] getcodeClub() {
        String lesCodesClubs[] = new String[0];        
        for (Club leClub : lesClubs) {
            if (leClub.getId_porteur() == null){
                lesCodesClubs = Arrays.copyOf(lesCodesClubs, lesCodesClubs.length+1);
                String code = leClub.getCode_club();
                lesCodesClubs[lesCodesClubs.length-1] = code;
            }
        }
        return lesCodesClubs;
    }
    
    /**
     * Methode : enregistrementPorteur
     * @param p ( un objet porteur)
     * Methode utilisé lors de l'enregistrement d'un nouveau porteur comportant tous les traitements nécessaires dans 
     * la liste et la base de données.
     */
    public void enregistrementPorteur(Porteur p){
        con.insertPorteur(p);
        adPorteur(p);
        for (Club c : p.getPorte_pouvoir()) {
            c.setId_porteur(p.getLicence());
        }
        getClub(p.getClub_mandate()).setId_porteur(p.getLicence());
    }
    
    /**
     * Methode : modifPorteur
     * @param p (un objet porteur qui va etre modifié)
     * @param nom_p (le nouveau nom du porteur(qui peut etre identique))
     * @param prenom_p (le nouveau prenom du porteur(qui peut etre identique))
     * @param clu_mand (le nouveau du club mandateur(qui peut etre identique))
     * @param clu_port (la nouvelle liste de club porté(qui peut etre identique))
     * Methode qui permet de traiter la modification d'un porteur au niveau de la liste et de la base de données.
     */
    public void modifPorteur(Porteur p, String nom_p, String prenom_p, String clu_mand, ArrayList<Club> clu_port){
        con.updatePorteur(p, nom_p, prenom_p, clu_mand, clu_port);
        p.setNom_por(nom_p);
        p.setPre_por(prenom_p);
        p.setClub_mandate(clu_mand);
        p.setPorte_pouvoir(clu_port);
    }

    /**
     * Methode : getNBvoix
     * @param p (un objet porteur)
     * @return un entier 
     * Récupère le nombre de voix total que possède un porteur.
     */
    public int getNBvoix(Porteur p){        
        int nbVoixTotal = getClub(p.getClub_mandate()).getNb_voix();
        
        for (Club c : p.getPorte_pouvoir()){
            nbVoixTotal=nbVoixTotal + c.getNb_voix();
        }        
        return nbVoixTotal;
    }
    
    /**
     * Methode : isCode
     * @param code (une chaine de caractère)
     * @return true si le code entré en paramètre correspond au critère et false si ce n'est pas le cas
     * Methode qui test la validité d'un code, le premier caractère est une lettre en majuscule, suivi d'une suite de chiffre,
     * et qui doit comporter exactement 6 caractères.
     * 
     */
    public boolean isCode(String code){
        Pattern Initiale = Pattern.compile("[A-Z]");
        Pattern Num = Pattern.compile("[0-9]");
        Matcher testIni = Initiale.matcher(code.substring(0));
        // test si le code est fait de 6 caractères
        if(code.length()!=6){
            return false;
        }
        // test si le premier caractère est une lettre en majuscule
        else if(!testIni.find()){
            return false;
        }
        for (int i = 1; i < 6; i++) {
            Matcher testNum = Num.matcher(code.substring(i));
            // test si les 5 derniers caractères sont des chiffres
            if(!testNum.find()){
                return false;
            }
        }       
        return true;
    }
    
    

    /**
     * Methode : main
     * @param args
     * Point d'entrée du programme.
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        
        Main create_main = new Main(); 
        AccesData con = new AccesData();
        con.connect();
        
        
        
    }
}
