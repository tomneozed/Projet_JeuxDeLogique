package Tables;

public class Configuration
{

	private Integer essais, nombreCasesCombi, nombreCouleurs;
	private Boolean modeDeveloppeur;

	public Boolean getModeDeveloppeur()
	{
		return modeDeveloppeur;
	}

	public void setModeDeveloppeur(Boolean modeDeveloppeur)
	{
		this.modeDeveloppeur = modeDeveloppeur;
	}

	@Override
	public String toString()
	{
		return "\n\tNombre de cases de la combinaison : " + nombreCasesCombi + "\n\tEssais : " + essais
				+ "\n\tNombre de couleurs : " + nombreCouleurs;
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
		return nombreCouleurs;
	}

	public void setNombreCouleurs(Integer nombreCouleurs)
	{
		this.nombreCouleurs = nombreCouleurs;
	}

}
