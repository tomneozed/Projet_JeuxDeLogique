package Configurations;

public class ConfigurationMMD extends MotherOfMothers
{
	//Attributs
	private Integer nombreCouleursMastermind;

	//Constructeur
	public ConfigurationMMD()
	{
		super();
	}

	//Getters & Setters
	public Integer getNombreCouleursMMD()
	{
		return nombreCouleursMastermind;
	}

	public void setNombreCouleursMMD(Integer nombreCouleurs)
	{
		this.nombreCouleursMastermind = nombreCouleurs;
	}

}
