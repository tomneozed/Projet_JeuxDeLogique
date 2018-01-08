package Configurations;

/**
 * Classe fille de ConfigurationG permettant de créer des objets décrivant le
 * fonctionnement du Mastermind
 * 
 * @author Utilisateur
 * @version 1.0
 */
public class ConfigurationMMD extends ConfigurationG
{
	//Attributs
	private Integer nbrCouleursMMD, nbrEssaisMMD, nbrCasesCombiMMD;

	//Constructeur
	public ConfigurationMMD()
	{
		super();
	}

	//Getters & Setters
	public Integer getNbrCouleursMMD()
	{
		return nbrCouleursMMD;
	}

	public void setNbrCouleursMMD(Integer nbrCouleursMMD)
	{
		this.nbrCouleursMMD = nbrCouleursMMD;
	}

	public Integer getNbrEssaisMMD()
	{
		return nbrEssaisMMD;
	}

	public void setNbrEssaisMMD(Integer nbrEssaisMMD)
	{
		this.nbrEssaisMMD = nbrEssaisMMD;
	}

	public Integer getNbrCasesCombiMMD()
	{
		return nbrCasesCombiMMD;
	}

	public void setNbrCasesCombiMMD(Integer nbrCasesCombiMMD)
	{
		this.nbrCasesCombiMMD = nbrCasesCombiMMD;
	}

	//Methodes

	public String toString()
	{
		return "\n\t* nbrCouleursMMD = " + nbrCouleursMMD + "\n\t* nbrEssaisMMD = " + nbrEssaisMMD
				+ "\n\t* nbrCasesCombiMMD = " + nbrCasesCombiMMD + "\n\t* modeDeveloppeur = "
				+ getModeDeveloppeur().booleanValue();
	}

}
