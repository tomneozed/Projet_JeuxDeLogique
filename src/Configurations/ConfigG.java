package Configurations;

public class ConfigG
{
	//Attributs
	private Integer essais, nombreCasesCombi, nombreCouleursMastermind;
	private Boolean modeDeveloppeur;

	//Constructeurs
	public ConfigG()
	{

	}

	public ConfigG(Integer essais, Integer nombreCasesCombi, Integer nombreCouleurs, Boolean modeDeveloppeur)
	{
		super();
		this.essais = essais;
		this.nombreCasesCombi = nombreCasesCombi;
		this.nombreCouleursMastermind = nombreCouleurs;
		this.modeDeveloppeur = modeDeveloppeur;
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

	public Integer getEssais()
	{
		return essais;
	}

	public void setEssais(Integer essais)
	{
		this.essais = essais;
	}

	public Integer getNombreCasesCombi()
	{
		return nombreCasesCombi;
	}

	public void setNombreCasesCombi(Integer nombreCasesCombi)
	{
		this.nombreCasesCombi = nombreCasesCombi;
	}

	public Integer getNombreCouleurs()
	{
		return nombreCouleursMastermind;
	}

	public void setNombreCouleurs(Integer nombreCouleurs)
	{
		this.nombreCouleursMastermind = nombreCouleurs;
	}

	//Affichage
	@Override
	public String toString()
	{
		return "\n # Nombre de cases de la combinaison : " + nombreCasesCombi + "\n # Essais : " + essais
				+ "\n # Nombre de couleurs : " + nombreCouleursMastermind + "\n # Mode developpeur : "
				+ modeDeveloppeur;
	}

}
