package application.controller;


/**
 * Modèle des Emprunts
 */
public class Emprunt {
	String date_emprunt;
	String date_rendu;
	String titre;
	String auteur;
	String genre;
	int id_reservation;
	String mail;
	public Emprunt(String date_emprunt,String date_rendu,String titre,String auteur,String type,String mail,int id_reservation) {
		super();
		this.date_emprunt=date_emprunt;
		this.date_rendu=date_rendu;
		this.titre=titre;
		this.auteur=auteur;
		this.genre=type;
		this.mail=mail;
		this.id_reservation=id_reservation;
	}
	public int getId_reservation() {return id_reservation;}
	public String getDate_emprunt() { return date_emprunt; }
	public String getMail() {return mail;}
	public String getTitre() { return titre; }
	public String getAuteur() { return auteur; }
	public String getDate_rendu() { return date_rendu; }
	public String getGenre() { return genre; }
	
	public void setId_reservation(int id_reservation) {this.id_reservation=id_reservation;}
	public void setMail(String mail) {this.mail=mail;}
	public void setDate_emprunt(String date_emprunt) {this.date_emprunt=date_emprunt;}
	public void setDate_rendu(String date_rendu) {this.date_rendu=date_rendu;}
	public void setTitre(String titre) {this.titre=titre;}
	public void setAuteur(String auteur) {this.auteur=auteur;}
	public void setGenre(String genre) {this.genre=genre;}
}
