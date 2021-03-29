package application.controller;

/**
 * Modèle des Medias
 */
public class Media { // 	(1,'Les cahiers de Douai','Rimbaud','Nathan','1','2020-12-01'),
	String id;
	int id_stock;
	String titre;
	String auteur;
	String edition;
	String type;
	String genre;
	public Media(String id,String type , String titre, String auteur, String edition,int id_stock,String genre) {
		super();
		this.id = id;
		this.id_stock = id_stock;
		this.titre = titre;
		this.auteur = auteur;
		this.edition = edition;
		this.type = type;
		this.genre=genre;
	}
	public String getGenre() {return genre;}
	public String getId() { return id; }
	public int getId_stock() { return id_stock; }
	public String getTitre() { return titre; }
	public String getAuteur() { return auteur; }
	public String getEdition() { return edition; }
	public String getType() { return type; }
	
	public void setGenre(String genre) {this.genre=genre;}
	public void setId(String id) {this.id = id; }
	public void setIdstock(int id_stock) { this.id_stock = id_stock; }
	public void setTitre(String titre) { this.titre = titre; }
	public void setAuteur(String auteur) { this.auteur = auteur; }
	public void setEdition(String edition) { this.edition = edition; }
	public void setType( String type ) { this.type = type; }
}



