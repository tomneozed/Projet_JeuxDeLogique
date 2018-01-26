package Configurations;

/**
 * Classe m�re de ConfigurationG permettant de cr�er des objets d�crivant le
 * fonctionnement des differents jeux
 * 
 * @author Utilisateur
 * @version 1.0
 *
 */
public class ConfigurationG
{

	//Attribut
	private Boolean modeDeveloppeur;

	//Constructeur
	public ConfigurationG()
	{
		super();
	}

	//Getters & Setters
	public Boolean getModeDeveloppeur()
	{
		return modeDeveloppeur;
	}

	public void setModeDeveloppeur(Boolean modeDeveloppeur)
	{
		this.modeDeveloppeur = modeDeveloppeur;
	}

	//Methodes

	public String toString()
	{
		return "ConfigurationG [modeDeveloppeur=" + modeDeveloppeur + "]";
	}

}
