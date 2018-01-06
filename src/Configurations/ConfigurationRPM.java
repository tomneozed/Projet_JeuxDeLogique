package Configurations;

public class ConfigurationRPM extends ConfigurationG
{
	private Integer nbrEssaisRPM, nbrCasesCombiRPM;

	public ConfigurationRPM()
	{
		super();
	}

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

	@Override
	public String toString()
	{
		return "\n\t* nbrEssaisRPM = " + nbrEssaisRPM + "\n\t* nbrCasesCombiRPM = " + nbrCasesCombiRPM
				+ "\n\t* modeDeveloppeur = " + getModeDeveloppeur().booleanValue();
	}

}
