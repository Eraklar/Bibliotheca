package application.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javafx.beans.property.SimpleObjectProperty;

/**
 * Modèle des Utilisateurs
 */
public class Utilisateur {
	String mail;
	String nom;
	String prenom;
	String adr;
	Date age;
	String tel;
	String mdp;
	String old_mail;
	boolean admin;
	int id_forfait;
	int nb_dispol;
	int nb_dispocd;
	SimpleObjectProperty<Date> date;
	
	public Utilisateur(String mail,String nom,String adr,Date age,String tel,String mdp,boolean admin,int id_forfait,int nb_dispol,int nb_dispocd,String prenom) {
		this.mail = mail;
		this.nom = nom;
		this.adr = adr;
		this.age = age;
		this.date = new SimpleObjectProperty<Date>(age);
		this.tel = tel;
		this.mdp = mdp;
		this.admin = admin;
		this.id_forfait = id_forfait;
		this.nb_dispol = nb_dispol;
		this.nb_dispocd = nb_dispocd;
		this.prenom = prenom;
}
	public void setMail( String mail ) { old_mail = this.mail; this.mail = mail; }
	public void setNom( String nom ) { this.nom = nom; }
	public void setAdr( String adr ) { this.adr = adr; }
	public void setAge( Date age ) { this.age = age; }
	public void setTel( String tel ) { this.tel = tel; }
	public void setMdp( String mdp ) { this.mdp = mdp; }
	public void setAdmin(boolean admin ) { this.admin = admin; }
	public void setId_forfait( int id_forfait ) { this.id_forfait = id_forfait; }
	public void setNb_dispol( int nb_dispol ) { this.nb_dispol = nb_dispol; }
	public void setNb_dispocd( int nb_dispocd ) { this.nb_dispocd = nb_dispocd; }
	public void setOld_mail( String old_mail ) { this.old_mail = old_mail; }
	public void setPrenom( String prenom ) { this.prenom = prenom; }
	
	
    public Date getDate() {
        return (Date) date.get();
    }
    public String getDateAsString() {
        SimpleDateFormat smp = new SimpleDateFormat("dd MMMMM yyyy");
        String strDate = (null == date || null == date.get())
                ? "" : smp.format(date.get());
       
        return strDate;
    }

    public void setDate(Date date) {
        this.date.set(date);
    }

	public String getOld_mail() { return old_mail; }
	public String getMail( ) { return mail; }
	public String getNom( ) { return nom; }
	public String getAdr(  ) { return adr; }
	public Date getAge(  ) {return age; }
	public String getTel( ) { return tel; }
	public String getMdp(  ) { return mdp; }
	public boolean getAdmin( ) { return admin; }
	public int getId_forfait(  ) { return id_forfait; }
	public int getNb_dispol(  ) { return nb_dispol; }
	public int getNb_dispocd( ) { return nb_dispocd; }
	public String getPrenom() { return prenom; }
	
	
}
