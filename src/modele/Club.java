package modele;

/**
 * @version Prototype_1
 * @author GUAY Alexis
 * Class Club correspond à la représentation d'un club sous forme d'objet.
 * Attributs : - code_club (code d'identification d'un club)
 *             - nom_club (le nom du club)
 *             - nb_voix (le nombre de voix dont dispose le club)
 *             - id_porteur (le numéro de licence du porteur qui porte les droits du club (peut etre nul))
 */
public class Club {

    private String code_club;
    private String nom_club;
    private int nb_voix;
    private String id_porteur;

    /**
     * Constructeur : Club
     * @param code_club (le code d'identification du club)
     * @param nom_club  (le nom du club)
     * @param nb_voix   (le nombre de voix du club)
     * @param id_porteur (l'identifiant du porteur qui porte les droits du club)
     * Constructeur d'un objet club
     */
    public Club(String code_club, String nom_club, int nb_voix, String id_porteur) {
        this.code_club = code_club;
        this.nom_club = nom_club;
        this.nb_voix = nb_voix;
        this.id_porteur = id_porteur;
    }

    /**
     * Methode : getCode_club
     * @return le code du club sous forme de String
     * Récupère le code d'un objet Club.
     */
    public String getCode_club() {
        return code_club;
    }

    /**
     * Methode : setCode_club
     * @param code_club (le code d'identification du club)
     * Attribut un code à un objet Club.
     */
    public void setCode_club(String code_club) {
        this.code_club = code_club;
    }


    /**
     * Methode : getNom_club
     * @return le nom du club sous forme de String.
     * Récupère la nom d'un objet Club.
     */
    public String getNom_club() {
        return nom_club;
    }

    /**
     * Methode : setNom_club
     * @param nom_club (le nom du club)
     * Attribut un nom à un objet Club.
     */
    public void setNom_club(String nom_club) {
        this.nom_club = nom_club;
    }

    /**
     * Methode : getNB_voix
     * @return le nombre de voix d'un club sous forme d'entier
     * Récupère le nombre de voix que possède un objet Club.
     */
    public int getNb_voix() {
        return nb_voix;
    }

    /**
     * Methode : setNB_voix
     * @param nb_voix (le nombre de voix du club)
     * Attribut un nombre de voix à un objet Club.
     */
    public void setNb_voix(int nb_voix) {
        this.nb_voix = nb_voix;
    }

    /**
     * Methode : getId_porteur
     * @return le code d'identification du porteur sous forme de String
     * Récupère le code d'identification du porteur qui porte les pouvoirs d'un objet club.
     */
    public String getId_porteur() {
        return id_porteur;
    }

    /**
     * Methode : setId_porteur
     * @param id_porteur (l'identifiant du porteur qui porte les droits du club)
     * Attribut un identifiant de porteur à un objet club.
     */
    public void setId_porteur(String id_porteur) {
        this.id_porteur = id_porteur;
    }

    @Override
    public String toString() {
        return "Club{" + "code_club=" + code_club + ", nom_club=" + nom_club + ", nb_voix=" + nb_voix + ", id_porteur=" + id_porteur + '}';
    }



    
    

    

    

}
