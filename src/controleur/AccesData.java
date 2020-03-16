
package controleur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modele.*;

/**
 *
 * @author GUAY Alexis
 * @version Prototype_1
 * 
 * Class AccesData qui comporte tous les methodes relatives à la gestion de la base de données.
 */
public class AccesData {

    private final String url = "jdbc:postgresql://localhost/ffe_agt";
    private final String user = "ffe_user";
    private final String password = "P@ssw0rdsio";

    /**
     * Methode : connect
     * @return une connection à la base de données
     * @throws SQLException
     */
    public Connection connect() throws SQLException {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url, user, password);

        } catch (SQLException e) {
            System.out.println("Connection DB --> " + e.getMessage());
        }

        return conn;
    }

    /**
     * Methode : getLesClubs
     * @return une collection de club
     * Methode qui va récupéré tous les clubs présents en base de données afin de les ajouter à la collection de clubs.
     */
    public ArrayList<Club> getLesClubs() {
        ArrayList<Club> lesClubs = new ArrayList<>();

        try (Connection conn = connect()) {
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM CLUB ORDER BY cod_clu");
            while (rs.next()) {
                String code_club = rs.getString("cod_clu");
                String nom_club = rs.getString("nom_clu");
                int nbr_voi = rs.getInt("nbr_voi");
                String por_clu = rs.getString("por_clu");
                lesClubs.add(new Club(code_club, nom_club, nbr_voi, por_clu));
            }
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Table Club --> " + e.getMessage());
        }

        return lesClubs;
    }

    /**
     * Methode : getLesClubsPortes
     * @param num_porteur ( le numéro de licence qui identifie un porteur)
     * @param id_club_mandateur (le code du club mandateur de ce porteur)
     * @return une collection des clubs portés par le porteur
     * Methode qui permet de récupérer la liste des clubs portés par un porteur sans que le club mandateur soit ajouté 
     * dans cette liste.
     */
    public ArrayList<Club> getLesClubsPortes(String num_porteur, String id_club_mandateur) {

        ArrayList<Club> mesClubs = new ArrayList<>();

        for (Club club : getLesClubs()) {
            if (club.getId_porteur() != null) {
                if (club.getId_porteur().equals(num_porteur) && !(club.getCode_club().equals(id_club_mandateur))) {
                    mesClubs.add(club);
                }

            }
        }
        return mesClubs;

    }

    /**
     * Methode : getLesPorteurs
     * @return une collection de porteurs
     * Methode qui va récupérer tous les porteurs présent dans la base de données afin de les ajouter dans la collection de porteurs.
     */
    public ArrayList<Porteur> getLesPorteurs() {
        ArrayList<Porteur> lesPorteurs = new ArrayList<>();
        Connection conn = null;
        try (Connection con = connect()) {
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM PORTEUR ORDER BY num_por");
            while (rs.next()) {
                String num_porteur = rs.getString("num_por");
                String nom_por = rs.getString("nom_por");
                String pre_por = rs.getString("pre_por");
                String clu_mandat = rs.getString("clu_mandat");
                lesPorteurs.add(new Porteur(num_porteur, nom_por, pre_por, clu_mandat, getLesClubsPortes(num_porteur, clu_mandat)));
            }
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Table Porteur --> " + e.getMessage());
        }

        return lesPorteurs;
    }

    /**
     * Methode : insertPorteur
     * @param p ( un objet porteur)
     * Methode qui permet l'insertion d'un porteur dans la base de données, avec tous les traitement nécessaire au niveau des clubs.
     */
    public void insertPorteur(Porteur p) {
        try (Connection conn = connect()) {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO Porteur"
                    + " VALUES ('" + p.getLicence() + "','" + p.getNom_por() + "','" + p.getPre_por() + "','" + p.getClub_mandate() + "');"
            );
            for (Club c : p.getPorte_pouvoir()) {
                stmt.executeUpdate("UPDATE Club SET por_clu = '" + p.getLicence() + "' WHERE cod_clu = '" + c.getCode_club() + "';");
            }

            stmt.executeUpdate("UPDATE Club SET por_clu = '" + p.getLicence() + "' WHERE cod_clu = '" + p.getClub_mandate() + "';");
            stmt.close();

            conn.close();
        } catch (SQLException ex) {
            System.out.println("" + ex.getMessage());
        }

    }

    /**
     * Methode : updatePorteur
     * @param p (un objet porteur qui va etre modifié)
     * @param nom_p (le nouveau nom du porteur(qui peut etre identique))
     * @param prenom_p (le nouveau prenom du porteur(qui peut etre identique))
     * @param clu_mand (le nouveau du club mandateur(qui peut etre identique))
     * @param clu_port (la nouvelle liste de club porté(qui peut etre identique))
     * Methode qui permet de traiter la modification d'un porteur dans la base de données, avec tous les traitements nécessaires au niveau des clubs.
     */
    public void updatePorteur(Porteur p, String nom_p, String prenom_p, String clu_mand, ArrayList<Club> clu_port) {
        try (Connection conn = connect()) {
            Statement stmt = conn.createStatement();
            for (Club club : p.getPorte_pouvoir()) {
                stmt.executeUpdate("UPDATE Club SET por_clu = null WHERE cod_clu ='"+club.getCode_club()+"';");
            }
            for (Club club : clu_port) {
                stmt.executeUpdate("UPDATE Club SET por_clu = '"+p.getLicence()+"' WHERE cod_clu ='"+club.getCode_club()+"';");
            }
            stmt.executeUpdate("UPDATE Club SET por_clu = null WHERE cod_clu ='"+p.getClub_mandate()+"';");

            stmt.executeUpdate("UPDATE Club SET por_clu = '"+p.getLicence()+"' WHERE cod_clu ='"+clu_mand+"';");

            stmt.executeUpdate("UPDATE Porteur SET nom_por = '"+nom_p+"', pre_por ='"+prenom_p+"', clu_mandat='"+clu_mand+"' WHERE num_por ='"+p.getLicence()+"';");
             
            stmt.close();

            conn.close();
        } catch (SQLException ex) {
            System.out.println("" + ex.getMessage());
        }

    }

}
