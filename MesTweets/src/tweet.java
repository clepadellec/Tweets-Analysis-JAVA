import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.Set;
//
//@SuppressWarnings("serial")
public class tweet implements Comparable<tweet>, Serializable {

	protected String t_pseudo_users="";
	protected LocalDate t_date;
	protected String t_heure="";
	protected String t_contenu="";
	protected String t_lien_dans_contenu="";
	protected String t_hashtag="";
	protected String t_pseudo_mentionne="";
	protected String t_pseudo_retweet="";
	protected int t_nb_hashtag=0;
	protected int t_nb_pseudo_mentionne=0;
	protected String annee_t;
	protected String mois_t;
	//protected String jourSemaine_t;
	protected String jour_t;

	//protected LocalDate date = LocalDate.now();
	//private String date="";

	public tweet(String t_pseudo_users, LocalDate t_date, String t_heure, String t_contenu, String t_lien_dans_contenu,
			String t_hashtag, String t_pseudo_mentionne, String t_pseudo_retweet, int t_nb_hashtag,
			int t_nb_pseudo_mentionne, String annee_t2, String mois_t, /*String jourSemaine_t,*/ String jour_t2) {
		super();
		this.t_pseudo_users = t_pseudo_users;
		this.t_date = t_date;
		this.t_heure = t_heure;
		this.t_contenu = t_contenu;
		this.t_lien_dans_contenu = t_lien_dans_contenu;
		this.t_hashtag = t_hashtag;
		this.t_pseudo_mentionne = t_pseudo_mentionne;
		this.t_pseudo_retweet = t_pseudo_retweet;
		this.t_nb_hashtag = t_nb_hashtag;
		this.t_nb_pseudo_mentionne = t_nb_pseudo_mentionne;
		this.annee_t = annee_t2;
		this.mois_t = mois_t;
		//this.jourSemaine_t = jourSemaine_t;
		this.jour_t = jour_t2;
	}




	public int compareTo(tweet arg0) {
		
		if (getPseudo_users().compareTo(arg0.getPseudo_users())<0) return -1;
		else if(getPseudo_users().compareTo(arg0.getPseudo_users())>0) return 1;
		else
			if (getDate().compareTo(arg0.getDate())<0) return -1;
			else if (getDate().compareTo(arg0.getDate())>0) return 1;
			else
				if (getHeure().compareTo(arg0.getHeure())<0) return -1;
				else if (getHeure().compareTo(arg0.getHeure())>0) return 1;
				else return 0;
	}

	
	public String getPseudo_users() {
		return t_pseudo_users;
	}

	public void setPseudo_users(String pseudo_users) {
		this.t_pseudo_users = pseudo_users;
	}

	public LocalDate getDate() {
		return t_date;
	}

	public void setDate(LocalDate date) {
		this.t_date = date;
	}

	public String getHeure() {
		return t_heure;
	}

	public void setHeure(String heure) {
		this.t_heure = heure;
	}

	public String getcontenu() {
		return t_contenu;
	}

	public void setcontenu(String contenu) {
		this.t_contenu = contenu;
	}

	public String getLien_dans_contenu() {
		return t_lien_dans_contenu;
	}

	public void setLien_dans_contenu(String lien_dans_contenu) {
		this.t_lien_dans_contenu = lien_dans_contenu;
	}

	public String getHashtag() {
		return t_hashtag;
	}

	public void setHashtag(String hashtag) {
		this.t_hashtag = hashtag;
	}

	public String getPseudo_mentionne() {
		return t_pseudo_mentionne;
	}

	public void setPseudo_mentionne(String pseudo_mentionne) {
		this.t_pseudo_mentionne = pseudo_mentionne;
	}

	public String getpseudo_retweet() {
		return t_pseudo_retweet;
	}

	public void setpseudo_retweet(String pseudo_retweet) {
		this.t_pseudo_retweet = pseudo_retweet;
	}

	public int getnb_hashtag() {
		return t_nb_hashtag;
	}


	public void setnb_hashtag(int t_nb_hashtag) {
		this.t_nb_hashtag = t_nb_hashtag;
	}


	public int getnb_pseudo_mentionne() {
		return t_nb_pseudo_mentionne;
	}


	public void setnb_pseudo_mentionne(int t_nb_pseudo_mentionne) {
		this.t_nb_pseudo_mentionne = t_nb_pseudo_mentionne;
	}


	public String getAnnee_t() {
		return annee_t;
	}

	public void setAnnee_t(String annee_t) {
		this.annee_t = annee_t;
	}

	public String getMois_t() {
		return mois_t;
	}

	public void setMois_t(String mois_t) {
		this.mois_t = mois_t;
	}

	

	
	public String getJour_t() {
		return jour_t;
	}

	public void setJour_t(String jour_t) {
		this.jour_t = jour_t;
	}




	
	@Override
	public String toString() {
		return "tweet [t_pseudo_users=" + t_pseudo_users + ", t_date=" + t_date + ", t_heure=" + t_heure
				+ ", t_contenu=" + t_contenu + ", t_lien_dans_contenu=" + t_lien_dans_contenu + ", t_hashtag="
				+ t_hashtag + ", t_pseudo_mentionne=" + t_pseudo_mentionne + ", t_pseudo_retweet=" + t_pseudo_retweet
				+ ", t_nb_hashtag=" + t_nb_hashtag + ", t_nb_pseudo_mentionne=" + t_nb_pseudo_mentionne + ", annee_t="
				+ annee_t + ", mois_t=" + mois_t + ", jourSemaine_t="  + ", jour_t=" + jour_t + "]";
	}

	






	/*
	public News(String t,String a,String s, String d) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String date;
		this.t_titre=t;
		this.t_auteur=a;
		this.t_source=s;
		date=d;

		try {
			this.t_date = LocalDate.parse(date, formatter);
		} catch (DateTimeParseException e) { System.out.println( "erreur, la date sera celle du  jour"); };


	}

	 */


}
