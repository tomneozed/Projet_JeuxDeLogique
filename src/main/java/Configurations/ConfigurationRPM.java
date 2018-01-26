package Configurations;

/**
 * Classe fille de ConfiguartionG permettant de créer des objets décrivant le
 * fonctionnement du Recherche+/-
 * 
 * @author Utilisateur
 * @version 1.0
 */
public class ConfigurationRPM extends ConfigurationG
{
	//Attributs
	private Integer nbrEssaisRPM, nbrCasesCombiRPM;

	//Constructeur
	public ConfigurationRPM()
	{
		super();
	}

	//Getters & Setters 

	public Integer getNbrEssaisRPM()
	{
		return nbrEssaisRPM;
	}

	public void setNbrEssaisRPM(Integer nbrEssais)
	{
		this.nbrEssaisRPM = nbrEssais;
	}

	public Integer getNbrCasesCombiRPM()
	{
		return nbrCasesCombiRPM;
	}

	public void setNbrCasesCombiRPM(Integer nbrCasesCombi)
	{
		this.nbrCasesCombiRPM = nbrCasesCombi;
	}

	//Methodes 

	public String toString()
	{
		return "\n\t* nbrEssaisRPM = " + nbrEssaisRPM + "\n\t* nbrCasesCombiRPM = " + nbrCasesCombiRPM
				+ "\n\t* modeDeveloppeur = " + getModeDeveloppeur().booleanValue();
	}

}
