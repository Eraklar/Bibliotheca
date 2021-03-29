package application.controller;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.sql.Connection;
import java.lang.Math;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;


/**
 * lien entre java et postgresql permettant les requêtes et la connection à la bdd
 */
public class Bdd_access {
	
    /**
     * url de la bdd
     */
	private String DB_URL;
	
    /**
     * nom de l'utilisateur de la bdd
     */
	private String DB_USER;
	
    /**
     * pwd de la bdd
     */
	private String DB_PASSWORD;
	
    /**
     * format de la date
     */
    final String TIME_FORMAT = "yyyy-MM-dd";
	
    
    /**
     * @author Bryan C.
     * @version 2.0
     */
	Bdd_access(){
		DB_URL = "jdbc:postgresql://localhost:5432/postgres";
		DB_USER = "postgres";
		DB_PASSWORD = "Nancy540";
	}
	

	
    /**
     * recherche un utilisateur et retourne une Map mail et liste d'attribut utilisateur
     * @author Bryan C.
     * @version 2.0
     * @param str liste des paramètres pour la requête SQL
     * @return results liste des attributs d'un Utilisateur
     */
	public Map<String, Object[]> recherche( String[] str ) {
		  Connection c = null;
	      Statement stmt = null;
	      Map<String, Object[]> results = new HashMap<>();
	      try {
	    	  Class.forName("org.postgresql.Driver");
	         c = DriverManager.getConnection(DB_URL,
	            		DB_USER, DB_PASSWORD );

	         c.setAutoCommit(false);
	         System.out.println("Opened database successfully");
	         String add = "';";
	         if (str.length>1 ) {
	        	 add = "' AND mdp = '"+ str[1] + "';";
	         }

	         stmt = c.createStatement();
	         String query = "SELECT * FROM utilisateur WHERE mail = '" + str[0] + add;
	         System.out.println(query);
	         ResultSet rs = stmt.executeQuery( query );
	         while ( rs.next() ) {
	            String mail = rs.getString("mail");
	            String nom = rs.getString("nom");
	            String adr = rs.getString("adr");
	            Date age = rs.getDate("age");
	            String tel = rs.getString("tel");
	            String mdp = rs.getString("mdp");
	            boolean admin = rs.getBoolean("admin");
	            int id_forfait = rs.getInt("id_forfait");
	            int nb_dispol = rs.getInt("nb_dispol");
	            int nb_dispodvd = rs.getInt("nb_dispodvd");
	            String prenom = rs.getString("prenom");
	            
	            Object[] list = { nom,adr,age,tel,mdp,admin,id_forfait,nb_dispol,nb_dispodvd,prenom	};
	            results.put(mail, list);
	         }
	         rs.close();
	         stmt.close();
	         c.close();
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	         System.exit(0);
	      }
	      System.out.println("Operation done successfully");
	      return results;
	   }
	
    /**
     * Recherche tout les medias
     * @author Vincent A.
     * @version 2.0
     * @return results liste des medias
     */
	public Map<String, Object[]> rechercheReservation2() {
		Map<String, Object[]> results = new HashMap<>();
		Connection c = null;
	    Statement stmt = null;
	    try {
	         c = DriverManager.getConnection(DB_URL,
	            		DB_USER, DB_PASSWORD );
	         c.setAutoCommit(false);
	         stmt = c.createStatement();
	         ResultSet rs2= null;
	         
	         System.out.println("Opened database successfully");
	         ResultSet rs = stmt.executeQuery( "SELECT * FROM reservation ;" );
	         while ( rs.next() ) {
	        	 	String type=rs.getString("type");
		        	Date date_emp=rs.getDate("date_emp");
		        	Date date_rendu=rs.getDate("date_rendu");
		        	String mail=rs.getString("mail");
		        	int id_media=rs.getInt("id_media"); 
		        	int id_reservation=rs.getInt("id_reservation");
		            String nom=null;
		        	String auteur=null;
		        	String genre=null;
		        	if (type.equals("livre")) {
						rs2 = stmt.executeQuery("SELECT * FROM livre WHERE id_media='"+String.valueOf(id_media)+"';");
						while(rs2.next()) {
						nom = rs2.getString("oeuvre");
						auteur = rs2.getString("auteur");
						genre= "livre";
						}
					} else {
						rs2 = stmt.executeQuery("SELECT * FROM dvd WHERE id_media='"+String.valueOf(id_media)+"';");
						while(rs2.next()) {
						nom = rs2.getString("oeuvre");
						auteur = rs2.getString("auteur");
						genre = "DVD";
						}
					}
		        	Object[] list= {auteur,genre,date_emp,date_rendu,mail,id_reservation};
		        	results.put(nom,list);
	         }
	    } catch ( Exception e ) {
	         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	         System.exit(0);
	      }
	      System.out.println("Operation done successfully");
	  
	return results;
	}
	
    /**
     * Supprime une réservation
     * @author Vincent A.
     * @version 2.0
     * @param id_reservation l'id unique de la réservation
     */
	public void supp_resa(int id_reservation) {
		
		Connection c = null;
	    PreparedStatement stmt = null;
	    try {
	         c = DriverManager.getConnection(DB_URL,
	            		DB_USER, DB_PASSWORD );
	         c.setAutoCommit(false);       
	         System.out.println("Opened database successfully");
	         stmt=c.prepareStatement("DELETE FROM Reservation WHERE id_reservation= ? ;",PreparedStatement.RETURN_GENERATED_KEYS );
	         stmt.setInt(1, id_reservation);
	         stmt.executeUpdate();
	         c.commit();
	         stmt.close();
	         c.close();         
	      } catch ( Exception e ) {
		         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
		         System.exit(0);
		  }
	     
		  System.out.println("Operation done successfully");
		
	}
	
    /**
     * Recherche les réservation d'un utilisateur en fonction de son mail
     * @author Vincent A.
     * @version 2.0
     * @param email mail de l'utilisateur
     * @return results liste des réservations d'un Utilisateur
     */
	public Map<String, Object[]> rechercheReservation(String email) {
		Map<String, Object[]> results = new HashMap<>();
		Connection c = null;
	    Statement stmt = null;
	      try {
	         c = DriverManager.getConnection(DB_URL,
	            		DB_USER, DB_PASSWORD );
	         c.setAutoCommit(false);
	         stmt = c.createStatement();
	         System.out.println("Opened database successfully");
	         ResultSet rs = stmt.executeQuery( "SELECT * FROM reservation WHERE mail='"+email+"';" );
	         ResultSet rs2= null;
	         while ( rs.next() ) {
	        	
		        
	        	String type=rs.getString("type");
	        	Date date_emp=rs.getDate("date_emp");
	        	Date date_rendu=rs.getDate("date_rendu");
	        	String mail=rs.getString("mail");
	        	int id_reservation=rs.getInt("id_reservation");
	        	String nom=null;
	        	String auteur=null;
	        	String genre=null;
	        	int id_media=rs.getInt("id_media");
	        	//sous requête pour obtenir le nom du média et l'auteur
				
	        	if (type.equals("livre")) {
					rs2 = stmt.executeQuery("SELECT * FROM livre WHERE id_media='"+String.valueOf(id_media)+"';");
					while(rs2.next()) {
					nom = rs2.getString("oeuvre");
					auteur = rs2.getString("auteur");
					genre= "livre";
					}
				} else {
					rs2 = stmt.executeQuery("SELECT * FROM dvd WHERE id_media='"+String.valueOf(id_media)+"';");
					while(rs2.next()) {
					nom = rs2.getString("oeuvre");
					auteur = rs2.getString("auteur");
					genre = "DVD";
					}
				}
				
				
				
				Object[] list= {auteur,genre,date_emp,date_rendu,mail,id_reservation};
				results.put(nom,list);
	         }
	         rs.close();
	         if (rs2!=null ) {
	         rs2.close();
	         }
	         stmt.close();
	         c.close();
	         
	         
	      } catch ( Exception e ) {
		         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
		         System.exit(0);
		      }
		      System.out.println("Operation done successfully");
		return results;
	}
	
    /**
     * Recherche un Media
     * @author Bryan C.
     * @version 2.0
     * @param str Nom du média
     * @return results liste des Medias trouvés
     */
	public Map<String, Object[]> rechercheMedia( String str ) {
		Map<String, Object[]> results = new HashMap<>();
		  Connection c = null;
	      Statement stmt = null;
	      try {
	         c = DriverManager.getConnection(DB_URL,
	            		DB_USER, DB_PASSWORD );

	         c.setAutoCommit(false);
	         System.out.println("Opened database successfully");

	         stmt = c.createStatement();
	         ResultSet rs = stmt.executeQuery( "SELECT * FROM Livre;" );
	         if (str.equals("livre") || str.equals("Livre") || str.equals("")) {
	        	 while ( rs.next() ) {
	        	 String  name = rs.getString("oeuvre");
	        	 String auteur  = rs.getString("auteur");
		         String  edition = rs.getString("maisonEd");
		         String id = Integer.toString(rs.getInt("id_media"));
		         String genre=rs.getString("genre");
		         int id_stock = rs.getInt("nb_stock");
		         Object[] list = { id,auteur,edition,id_stock,"livre",genre };
		         results.put(name, list);
	        	 }
	         } else {
	        	 while ( rs.next() ) {
	        	 String  name = rs.getString("oeuvre");
	        	 if ( this.distance(str.toLowerCase(),name.toLowerCase()) <= 3 ) {
	            String auteur  = rs.getString("auteur");
	            String  edition = rs.getString("maisonEd");
	            String id = Integer.toString(rs.getInt("id_media"));
	            String genre=rs.getString("genre");
	            int id_stock = rs.getInt("nb_stock");
	            Object[] list = { id,auteur,edition,id_stock,"livre",genre };
	            results.put(name, list);
	        	}
	          
	         }
	         }
	        	 rs.close();
		         rs = stmt.executeQuery( "SELECT * FROM DVD;" );
		         if (str.equals("DVD") || str.equals("dvd") || str.equals("Dvd") || str.equals("")) {
		        	 while ( rs.next() ) {
		        	 String  name = rs.getString("oeuvre");
		        	 String auteur  = rs.getString("auteur");
			         
			         String id = Integer.toString(rs.getInt("id_media"));
			         String genre=rs.getString("genre");
			         int id_stock = rs.getInt("nb_stock");
			         Object[] list = { id,auteur,"",id_stock,"dvd",genre };
			         results.put(name, list);
		        	 }
		         } else {
		        	 while ( rs.next() ) {
		        	 String name = rs.getString("oeuvre");
		        	 if ( this.distance(str.toLowerCase(),name.toLowerCase()) <= 3 ) {
		            String auteur  = rs.getString("auteur");
		            String genre=rs.getString("genre");
		            String id = Integer.toString(rs.getInt("id_media"));
		            int id_stock = rs.getInt("nb_stock");
		            Object[] list = { id,auteur,"",id_stock,"dvd",genre};
		            results.put(name, list);
		        	 }
		         }
		         }
	         
	         rs.close();
        	 stmt.close();
	         c.close();
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	         System.exit(0);
	      }
	      System.out.println("Operation done successfully");
	      return results;
	   }
	
    /**
     * ajout d'un dvd
     * @author Vincent A.
     * @version 2.0
     * @param str liste ouvre auteur nb_stock
     * @return check int pour savoir si ça c'est bien passé
     */
	public int ajout_DVD (String [] str) {
		Connection c = null;
	      int check = 1;
	      try {
	         c = DriverManager.getConnection(DB_URL,
	            		DB_USER, DB_PASSWORD );

	         c.setAutoCommit(false);
	         System.out.println("Opened database successfully");
	         PreparedStatement st = c.prepareStatement("INSERT INTO DVD(oeuvre, auteur, nb_stock) VALUES (?, ?, ?)",PreparedStatement.RETURN_GENERATED_KEYS);
	         System.out.println(st);
	         st.setString(1, (String)str[0]);
	         st.setString(2, (String)str[1]);
	         st.setInt(3, Integer.parseInt((String)str[2]));
	        
	         System.out.println(st);
	         st.executeUpdate();
	         c.commit();
	         ResultSet resultSet = st.getGeneratedKeys();
	         resultSet.next();
	     
	         st.close();
		     c.close();
	      } catch ( Exception e ) {
		         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
		         System.exit(0);
		      }
		      System.out.println("Operation done successfully");
		      return check;
	}
	
    /**
     * ajout d'un livre
     * @author Vincent A.
     * @version 2.0
     * @param str liste oeuvre auteur nb_stock maisoned et genre
     * @return check int pour savoir si ça c'est bien passé
     */
	public int ajout_livre (String[] str) {
		  Connection c = null;
	 
	      int check = 1;
	      try {
	         c = DriverManager.getConnection(DB_URL,
	            		DB_USER, DB_PASSWORD );

	         c.setAutoCommit(false);
	         System.out.println("Opened database successfully");

	         
	         PreparedStatement st = c.prepareStatement("INSERT INTO Livre(oeuvre,auteur,maisoned,genre,nb_stock) VALUES (?, ?, ?, ?, ?)",PreparedStatement.RETURN_GENERATED_KEYS);
	         System.out.println(st);
	         st.setString(1, (String)str[0]);
	         st.setString(2, (String) str[1]);
	         st.setString(3, (String)str[3]);
	         st.setString(4, (String)str[2]);
	         st.setInt(5, Integer.parseInt((String)str[4]));
	         
	         System.out.println(st);
	         st.executeUpdate();
	         c.commit();
	         ResultSet resultSet = st.getGeneratedKeys();
	         resultSet.next();
	     
	         st.close();
		     c.close();
	      } catch ( Exception e ) {
		         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
		         System.exit(0);
		      }
		      System.out.println("Operation done successfully");
		      return check;
	}
	
    /**
     * ajout d'un utilisateur
     * @author Vincent A.
     * @version 2.0
     * @param str liste oeuvre auteur nb_stock maisoned et genre
     * @param s date actuelle
     * @param q true : admin sinon utilisateur simple
     * @return check int pour savoir si ça c'est bien passé
     */
	public int ajout_utilisateur( String[] str,LocalDate s,boolean q ) {
		Connection c = null;
		 
	      int check = 1;
	      try {
	         c = DriverManager.getConnection(DB_URL,
	            		DB_USER, DB_PASSWORD );

	         c.setAutoCommit(false);
	         System.out.println("Opened database successfully");
	         //On gï¿½re le format de date
	         long millis=System.currentTimeMillis();  
	         java.sql.Date date=new java.sql.Date(millis);  
	         
	         java.sql.Date datepicker=java.sql.Date.valueOf(s);
        
	         PreparedStatement st = c.prepareStatement("INSERT INTO Utilisateur(mail,nom,prenom,adr,age,tel,mdp,admin,date_arrive) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",PreparedStatement.RETURN_GENERATED_KEYS);
	         System.out.println(st);
	         st.setString(1, (String)str[5]);
	         st.setString(2, (String) str[0]);
	         st.setString(3, (String)str[1]);
	         st.setString(4,(String)str[2]);
	         st.setDate(5,datepicker); 
	         
	         st.setInt(6, Integer.parseInt(str[3]));
	         st.setString(7, (String)str[4]);
	         st.setBoolean(8, q);
	         
	       	         
	         
	         st.setDate(9, date);
	         System.out.println(st);
	         st.executeUpdate();
	         c.commit();
	         ResultSet resultSet = st.getGeneratedKeys();
	         resultSet.next();
	     
	         st.close();
		     c.close();
	      } catch ( Exception e ) {
	    	  	 
		         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
		         System.exit(0);
		      }
		      System.out.println("Operation done successfully");
		      return check;
	}
	
    /**
     * reservation d'un media
     * @author Bryan C.
     * @version 2.0
     * @param o liste des attributs d'un media
     * @return res code d'erreur ou vide si ça c'est bien passé
     */
	public String reserver(Object[] o) {
		  Connection c = null;
	      String res = "";
	      try {
	         c = DriverManager.getConnection(DB_URL,
	            		DB_USER, DB_PASSWORD );

	         c.setAutoCommit(false);
	         System.out.println("Opened database successfully");

	         DateFormat df = new SimpleDateFormat(TIME_FORMAT);
	         Date dateobj = new Date(Calendar.getInstance().getTime().getTime());
	         String dt = df.format(dateobj);
	         Calendar cal = Calendar.getInstance();
	         cal.setTime(df.parse(dt));
	         cal.add(Calendar.DATE, 7);  // number of days to add
	         String add = df.format(cal.getTime());  // dt is now the new date
	         PreparedStatement st = c.prepareStatement("INSERT INTO Reservation(mail,id_media,type,date_emp,date_rendu) VALUES (?, ?, ?, ?, ?)",PreparedStatement.RETURN_GENERATED_KEYS);
	         st.setString(1, (String)o[0]);
	         st.setInt(2,Integer.parseInt((String)o[1]));
	         st.setString(3, (String)o[2]);
	         st.setDate(4, java.sql.Date.valueOf(dt));
	         st.setDate(5, java.sql.Date.valueOf(add));
	         System.out.println(st);
	         st.executeUpdate();
	         c.commit();
	         ResultSet resultSet = st.getGeneratedKeys();
	         resultSet.next();
	         res = Integer.toString(resultSet.getInt(4));
	         st.close();
		     c.close();
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	         return ((SQLException)e).getSQLState();
	      }
	      System.out.println("Operation done successfully");
	      return res;
		
	}
	
    /**
     * Enlever une Réservation
     * @author Bryan C.
     * @version 2.0
     * @param m liste des id_reservation
     * @return res code d'erreur ou code d'opération effectuée
     */
	public String remove( ArrayList<String> m) {
		  Connection c = null;
	      try {
	         c = DriverManager.getConnection(DB_URL,
	            		DB_USER, DB_PASSWORD );

	         c.setAutoCommit(false);
	         System.out.println("Opened database successfully");
	         for ( String id_reservation : m ) {
		         PreparedStatement st = c.prepareStatement("DELETE FROM Reservation WHERE id_reservation = ?");
		         st.setInt(1, Integer.parseInt(id_reservation));
		         System.out.println(st);
		         st.executeUpdate();
		         c.commit();
		         st.close();        	 
	         }

	         c.close();
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	         return "bad";
	      }
	      System.out.println("Operation done successfully");
	      return "good";
		
	}
	
    /**
     * Maj d'abonnement
     * @author Vincent A. Sofiane B. Bryan C.
     * @version 2.0
     */
    public void MAJ_abonnement() {
    	Connection c = null;
	      try {
	    	  c = DriverManager.getConnection(DB_URL,
	            		DB_USER, DB_PASSWORD );

	    	  c.setAutoCommit(true);
	          Statement stmt = null;
	          System.out.println("Opened database successfully");
	          stmt = c.createStatement();
	          String query = "SELECT maj_type_abo();";
	          System.out.println(query);
          ResultSet rs = stmt.executeQuery( query );
          rs.close();
          stmt.close();
          c.close();
          System.out.println("Operation done successfully");
	     } catch (Exception e) {
	    	 e.printStackTrace();
	     }
    }
    
    /**
     * Update un utilisateur avec les nouvelles données
     * @author Bryan C.
     * @version 2.0
     * @param user nouveau Modèle utilisateur
     * @param mail mail de l'utilisateur
     */
    public void UpdateUtilisateur( Utilisateur user, String mail ) {
		  Connection c = null;
	      try {
	         c = DriverManager.getConnection(DB_URL,
	            		DB_USER, DB_PASSWORD );

	         c.setAutoCommit(false);
	         System.out.println("Opened database successfully");
	         PreparedStatement st = c.prepareStatement("UPDATE Utilisateur SET mail = ?, nom = ?, adr = ?, age = ?, tel = ?, mdp = ?, admin = ?, prenom = ? WHERE mail = ?");
	         st.setString(1, user.getMail());
	         st.setString(2, user.getNom());
	         st.setString(3, user.getAdr());
	         st.setDate(4, (Date)user.getDate());
	         st.setInt(5, Integer.parseInt(user.getTel()));
	         st.setString(6, user.getMdp());
	         st.setBoolean(7, user.getAdmin());
	         st.setString(8, user.getPrenom());
	         st.setString(9,mail);
	         st.executeUpdate();
	         c.commit();
	         st.close();
		     c.close();
	      }
	         catch (Exception e) {
		    	 e.printStackTrace();
		    	 System.out.println("lol");
		     }
    }
    
    /**
     * Remove un Utilisateur
     * @author Vincent A.
     * @version 2.0
     * @param mail mail de l'utilisateur
     */
	 public void DeleteUtilisateur( String mail ) {
		  Connection c = null;
	      try {
	         c = DriverManager.getConnection(DB_URL,
	            		DB_USER, DB_PASSWORD );

	         c.setAutoCommit(false);
	         System.out.println("Opened database successfully");
	         PreparedStatement st = c.prepareStatement("DELETE FROM Utilisateur WHERE mail = ?");
	         st.setString(1,mail);
	         st.executeUpdate();
	         c.commit();
	         st.close();
		     c.close();
	      }
	         catch (Exception e) {
		    	 e.printStackTrace();
		     }
	 }
	 
	    /**
	     * Hashage en md5
	     * @author Bryan C. Sofiane B.
	     * @version 2.0
	     * @param pwd String à hashé
	     * @return hashtext mot de passe en md5
	     * @throws Exception exception
	     */
	    public String md5 ( String pwd ) throws Exception {
	        MessageDigest m = MessageDigest.getInstance("MD5");
	        m.reset();
	        m.update(pwd.getBytes());
	        byte[] digest = m.digest();
	        BigInteger bigInt = new BigInteger(1,digest);
	        String hashtext = bigInt.toString(16);
	        // Now we need to zero pad it if you actually want the full 32 chars.
	        while(hashtext.length() < 32 ){
	          hashtext = "0"+hashtext;
	        }
	        return hashtext;
	    }
	 
	 
	    /**
	     * Changer de mdp
	     * @author Vincent A.
	     * @version 2.0
	     * @param pwd nouveau mot de passe
	     * @param mail mail de l'utilisateur
	     */
	    public void ChangePwd( String pwd, String mail ) {
			  Connection c = null;
		      try {
		         c = DriverManager.getConnection(DB_URL,
		            		DB_USER, DB_PASSWORD );

		         c.setAutoCommit(false);
		         System.out.println("Opened database successfully");
		         PreparedStatement st = c.prepareStatement("UPDATE Utilisateur SET mdp = ? WHERE mail = ?");
		         st.setString(1, pwd);
		         st.setString(2,mail);
		         st.executeUpdate();
		         c.commit();
		         st.close();
			     c.close();
		      }
		         catch (Exception e) {
			    	 e.printStackTrace();
			     }
	    }
	    
	    /**
	     * Distande de Levenshtein
	     * @author Bryan C.
	     * @version 2.0
	     * @param s1 mot 1
	     * @param s2 mot 2
	     * @return mat nombre mini d'opération pour partir de s1 et avoir s2
	     */
          public int  distance(String s1, String s2)  {
		  int m = s1.length();
		  int n = s2.length();
		  int mat[][] = new int[m+1][n+1];
		  for (int i = 0; i < m + 1; i++) {
		    mat[i] = new int[n+1];
		    for (int j = 0; j < n + 1; j++) {
		      mat[i][j] = 0;
		    }
		  }
		  int cout = 0;

		  for (int i = 0; i <= m; i++) {
		    mat[i][0] = i;
		  }
		  for (int j = 0; j <= n; j++) {
		    mat[0][j] = j;
		  }
		  for (int i = 1; i <= m; i++) {
			    for (int j = 1; j <= n; j++) {
			      if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
		        cout = 0;
		      } else {
		        cout = 1;
		      }
		      mat[i][j] = Math.min(mat[i - 1][j] + 1,
		        mat[i][j - 1] + 1 );
		      mat[i][j] = Math.min(mat[i][j],mat[i - 1][j - 1] + cout);
		    }
		  }
		  return mat[m][n];

		}
          /**
           * Remove d'un media
           * @author Sofiane B.
           * @version 2.0
           * @param id id du media
           * @param edition edition du media
           */
          public void DeleteMedia( String id, String edition) {
              Connection c = null;
              try {
                 c = DriverManager.getConnection(DB_URL,DB_USER, DB_PASSWORD );

                 c.setAutoCommit(false);
                 System.out.println("Opened database successfully");
                 int idM =Integer.parseInt(id);
                 if (edition.isEmpty()) {
                     PreparedStatement st = c.prepareStatement("DELETE FROM DVD WHERE id_media = ?");
                     st.setInt(1,idM);
                     st.executeUpdate();
                     c.commit();
                     st.close();
                     c.close();
                 }else {
                     PreparedStatement st = c.prepareStatement("DELETE FROM Livre WHERE id_media = ?");
                     st.setInt(1,idM);
                     st.executeUpdate();
                     c.commit();
                     st.close();
                     c.close();     
                 }
                 
              }

                 catch (Exception e) {
                     e.printStackTrace();
                 }
              
         }
          
          /**
           * Ajout Exemplaire
           * @author Vincent A Sofiane B.
           * @version 2.0
           * @param s type du media
           * @param t nb_stock
           * @param id id du media
           */
         public void Add_exemplaire(String s, int t,String id) {
        	 Connection c = null;
   	      try {
   	         c = DriverManager.getConnection(DB_URL,
   	            		DB_USER, DB_PASSWORD );

   	         c.setAutoCommit(false);
   	         PreparedStatement st=null;
   	         if (s.equals("livre")) {
   	        	st = c.prepareStatement("UPDATE Livre SET nb_stock = ? WHERE id_media = ?");
      	        
   	         } else {
   	        	st = c.prepareStatement("UPDATE dvd SET nb_stock = ? WHERE id_media = ?");
      	        
   	         }
   	         System.out.println("Opened database successfully");
   	         st.setInt(1, t+1);
   	         st.setInt(2, Integer.parseInt(id));
   	         
   	         st.executeUpdate();
   	         c.commit();
   	         st.close();
   		     c.close();
   	      }
   	         catch (Exception e) {
   		    	 e.printStackTrace();
   		     }
         }
         
         /**
          * supp Exemplaire
          * @author Vincent A Sofiane B.
          * @version 2.0
          * @param s type du media
          * @param t nb_stock du media
          * @param id id du media
          */
         public void supp_Exemplaire(String s, int t,String id) {
        	 Connection c = null;
   	      try {
   	         c = DriverManager.getConnection(DB_URL,
   	            		DB_USER, DB_PASSWORD );

   	         c.setAutoCommit(false);
   	         PreparedStatement st=null;
   	         if (s.equals("livre")) {
   	        	st = c.prepareStatement("UPDATE Livre SET nb_stock = ? WHERE id_media = ?");
      	        
   	         } else {
   	        	st = c.prepareStatement("UPDATE dvd SET nb_stock = ? WHERE id_media = ?");
      	        
   	         }
   	         System.out.println("Opened database successfully");
   	         st.setInt(1, t-1);
   	         st.setInt(2, Integer.parseInt(id));
   	         
   	         st.executeUpdate();
   	         c.commit();
   	         st.close();
   		     c.close();
   	      }
   	         catch (Exception e) {
   		    	 e.printStackTrace();
   		     }
         }
	}

