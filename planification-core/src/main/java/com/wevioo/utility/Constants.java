package com.wevioo.utility;

/**
 *
 * @author imz
 *
 */
public final class Constants {
	/**
	 * Default constructor to prevent instantiation.
	 */
	private Constants() {

	}

	/**
	 * mail configuration
	 */
	public static final String MAIL_HOST = "mail.host";
	public static final String MAIL_PORT = "mail.port";
	public static final String MAIL_SSL = "mail.ssl";
	public static final String MAIL_ACCOUNT_PASSWORD = "mail.account.password";
	public static final String MAIL_ACCOUNT_MAILADRESS = "mail.account.mailadress";
	public static final String MAIL_ACCOUNT_USERNAME = "mail.account.username";
	public static final String MAIL_TIMEOUT = "mail.timeout";
	
	/**
	 * default class used to open a secured connection over ssl layer.
	 */
	public static final String DEFAULT_SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
	public static final Double ATTACHMENT_MAX_SIZE = 10.0d;
	
	/**
	 * Properties files
	 */
	public static final String PROJECT_FILE_PATH = "/application.properties";
	
	/**
	 * External paths
	 */
	public static final String EXTERNAL_PDF_PATH = "external.file.path.pdf";
	public static final String EXTERNAL_IMG_USER_PATH = "external.file.path.user";

	/**
	 * Jasper paths
	 */
	public static final String JASPER_FILE_PATH = "jasper.file.path";
	public static final String JASPER_FILE_JRXML_OCCUPATION = "file.jrxml.occupation";
	public static final String JASPER_FILE_PDF_OCCUPATION = "file.pdf.occupation";
	public static final String JASPER_FILE_JRXML_PLANNING_DETAILLE = "file.jrxml.planning.detaille";
	public static final String JASPER_FILE_PDF_PLANNING_DETAILLE = "file.pdf.planning.detaille";
	public static final String JASPER_FILE_JRXML_PLANNING_OPERATEUR = "file.jrxml.planning.operateur";
	public static final String JASPER_FILE_PDF_PLANNING_OPERATEUR = "file.pdf.planning.operateur";
	public static final String JASPER_FILE_JASPER_PLANNING_OPERATEUR = "file.jasper.planning.operateur";
	public static final String JASPER_FILE_JASPER_PLANNING_OPERATEUR_EXCEL = "file.jasper.planning.operateur.excel";
	public static final String JASPER_FILE_EXCEL_PLANNING_OPERATEUR = "file.excel.planning.operateur";
	public static final String JASPER_FILE_JRXML_REALISATION = "file.jrxml.realisation";
	public static final String JASPER_FILE_PDF_REALISATION = "file.pdf.realisation";
	public static final String JASPER_FILE_JRXML_PLANNING_CHECKED_OPERATEURS = "file.jrxml.planning.checked.operateurs";
	public static final String JASPER_FILE_JRXML_PLANNING_CHECKED_OPERATEURS_EXCEL = "file.jrxml.planning.checked.operateurs.excel";
	public static final String JASPER_FILE_PDF_PLANNING_CHECKED_OPERATEURS = "file.pdf.planning.checked.operateurs";
	public static final String JASPER_FILE_PDF_PLANNING_CHECKED_OPERATEURS_EXCEL = "file.pdf.planning.checked.operateurs.excel";
	
	/**
	 * Jasper params
	 */
	public static final String JASPER_PLANNING_OPERATEUR_GLOBAL_TABLEAU_MATRICULE = "jasper.planning.operateur.global.tableau.matricule";
	public static final String JASPER_PLANNING_OPERATEUR_GLOBAL_TABLEAU_TAUX_OCCUPATION = "jasper.planning.operateur.global.tableau.tauxoccupation";
	public static final String JASPER_PLANNING_OPERATEUR_GLOBAL_TABLEAU_TAUX_MOYEN = "jasper.planning.operateur.global.tableau.tauxmoyen";
	public static final String JASPER_PLANNING_OPERATEUR_GLOBAL_PARAM_NOM = "jasper.planning.operateur.global.param.nom";
	public static final String JASPER_PLANNING_OPERATEUR_GLOBAL_PARAM_PRENOM = "jasper.planning.operateur.global.param.prenom";
	public static final String JASPER_PLANNING_DETAILLE_TABLEAU_MATRICULE = "jasper.planning.detaille.tableau.matricule";
	public static final String JASPER_PLANNING_DETAILLE_TABLEAU_TAUX_OCCUPATION = "jasper.planning.detaille.tableau.tauxoccupation";
	public static final String JASPER_PLANNING_DETAILLE_TABLEAU_OPERATEUR = "jasper.planning.detaille.tableau.operateur";
	public static final String JASPER_PLANNING_DETAILLE_TABLEAU_REFERENCE = "jasper.planning.detaille.tableau.reference";
	public static final String JASPER_PLANNING_DETAILLE_TABLEAU_TEMPS_GAMME = "jasper.planning.detaille.tableau.tempsgamme";
	public static final String JASPER_PLANNING_DETAILLE_TABLEAU_QUANTITE = "jasper.planning.detaille.tableau.quantite";
	public static final String JASPER_PLANNING_DETAILLE_TABLEAU_TOTAL_HEURES = "jasper.planning.detaille.tableau.totalheures";
	public static final String JASPER_PLANNING_OPERATEUR_TABLEAU_ACTIVITE = "jasper.planning.operateur.tableau.activite";
	public static final String JASPER_PLANNING_OPERATEUR_TABLEAU_DEBUT = "jasper.planning.operateur.tableau.debut";
	public static final String JASPER_PLANNING_OPERATEUR_TABLEAU_FIN = "jasper.planning.operateur.tableau.fin";
	public static final String JASPER_PLANNING_OPERATEUR_TABLEAU_TEMPS_FABRICATION = "jasper.planning.operateur.tableau.tempsdefabrication";
	public static final String JASPER_PLANNING_OPERATEUR_TABLEAU_QUANTITE = "jasper.planning.operateur.tableau.quantite";
	public static final String JASPER_PLANNING_OPERATEUR_TABLEAU_PRIORITE = "jasper.planning.operateur.tableau.priorite";
	public static final String JASPER_REALISATION_TABLEAU_REFERENCE = "jasper.realisation.tableau.reference";
	public static final String JASPER_REALISATION_TABLEAU_TEMPS_GAMME = "jasper.realisation.tableau.tempsgamme";
	public static final String JASPER_REALISATION_TABLEAU_QUANTITE_DEMANDEE = "jasper.realisation.tableau.quantitedemandee";
	public static final String JASPER_REALISATION_TABLEAU_QUANTITE_PLANIFIEE = "jasper.realisation.tableau.quantiteplanifiee";
	public static final String JASPER_REALISATION_TABLEAU_SATISFACTION = "jasper.realisation.tableau.satisfaction";
	
	/**
	 * File integration
	 */
	public static final String ERROR_MESSAGE_FILE_FORMA = "error.message.file.forma";
	public static final String ERROR_MESSAGE_FILE_INTEGRATION = "error.message.file.integration";
	public static final String ERROR_MESSAGE_REFERENCE_MANQUANTE = "error.message.reference.manquante";
	public static final String ERROR_MESSAGE_REFERENCE_NOT_EXIST = "error.message.reference.not.exist";
	public static final String ERROR_MESSAGE_OPERATOR_MATRICULE_NOT_EXIST = "error.message.operator.matricule.not.exist";
	public static final String ERROR_MESSAGE_REFERENCE_NOT_EXIST_IN_CAPA = "error.message.reference.not.exist.in.capa";
	public static final String ERROR_MESSAGE_REFERENCE_NOT_EXIST_IN_CADENCE = "error.message.reference.not.exist.in.cadence";
	public static final String ERROR_MESSAGE_COLONNE_MANQUANTE = "error.message.colonne.manquante";
	public static final String ERROR_MESSAGE_COLONNE_ERRONEE = "error.message.colonne.erronee";
	public static final String ERROR_MESSAGE_COLONNE_DATE_ERRONEE = "error.message.colonne.date.erronee";
	public static final String ERROR_MESSAGE_HEURE_DEBUT_SUPERIEUR_HEURE_FIN = "error.message.heure.debut.superieur.heure.fin";
	public static final String ERROR_MESSAGE_DEGRE_ERRONE = "error.message.colonne.degre.errone";
	/**
	 * External URLs
	 */
	public static final String REPORT_PDF_URL = "/planning/pdf/**";
	public static final String USER_IMG_URL = "/user/**";
	
	/**
	 * User management messages
	 */
	public static final String ERROR_MESSAGE_USER_EXISTING_LOGIN = "error.message.user.existing.login";
	public static final String ERROR_MESSAGE_USER_EXISTING_MAIL = "error.message.user.existing.mail";
	public static final String ERROR_MESSAGE_USER_EXISTING_MATRICULE = "error.message.user.existing.matricule";
	public static final String ERROR_MESSAGE_ADDING_USER = "error.message.adding.user";
	public static final String ERROR_MESSAGE_MODIFYING_USER = "error.message.modifying.user";
	public static final String ERROR_MESSAGE_USER_PROFILE_IMAGE = "error.message.user.profile.photo";
	public static final String ERROR_MESSAGE_GETTING_PROFILE = "error.message.getting.profile";
	public static final String ERROR_MESSAGE_MODIFYING_STATUS = "error.message.modifying.status";
	
	/**
	 * Role management messages
	 */
	public static final String ERROR_MESSAGE_SEARCHING_ROLE = "error.message.searching.role";
	public static final String ERROR_MESSAGE_ROLE_AFFECT = "error.message.role.affect";
	
	/**
	 * Data messages
	 */
	public static final String ERROR_MESSAGE_VERIFYING_DATA = "error.message.verifing.data";
	
	/**
	 * emails
	 */
	public static final String EMAIL_STANDARD_SUBJECT = "email.standard.subject";
	public static final String EMAIL_NEW_USER_BODY = "email.new.user.body";
	
	/**
	 * Unit management messages
	 */
	public static final String ERROR_MESSAGE_EXISTING_UNIT = "error.message.existing.unit";
	
	public static final String ERROR_MESSAGE_EXISTING_SPECIFIC_UNIT = "error.message.existing.specific.unit";
	
	public static final String ERROR_MESSAGE_UNITE_ALREADY_EXIST = "error.message.unite.already.exist";

	public static final String ERROR_MESSAGE_UNITE_ALREADY_ATTACHED = "error.message.unite.already.attached";
	/**
	 * File Reader Exceptions
	 */
	public static final String EXCEPTION_MESSAGE_FILE_READER_COLUMN_ERROR = "exception.message.file.reader.column";
	
}
