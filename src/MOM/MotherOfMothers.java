package MOM;

import Configurations.ConfigMMD;
import Configurations.ConfigurationMMD;

public class MotherOfMothers
{
	//Attributs généraux

	ConfigurationMMD config = ConfigMMD.loadConfig();
	protected Integer NB_CASES_COMBI = config.getNombreCasesCombi();
	protected Integer NB_ESSAIS = config.getEssais();
	protected Integer NB_COULEURS = config.getNombreCouleurs();
	protected Boolean MODE_DEVELOPPEUR = config.getModeDeveloppeur();

	public Integer getNB_CASES_COMBI()
	{
		return NB_CASES_COMBI;
	}

	public void setNB_CASES_COMBI(Integer nB_CASES_COMBI)
	{
		NB_CASES_COMBI = nB_CASES_COMBI;
	}

	public Integer getNB_ESSAIS()
	{
		return NB_ESSAIS;
	}

	public void setNB_ESSAIS(Integer nB_ESSAIS)
	{
		NB_ESSAIS = nB_ESSAIS;
	}

	public Integer getNB_COULEURS()
	{
		return NB_COULEURS;
	}

	public void setNB_COULEURS(Integer nB_COULEURS)
	{
		NB_COULEURS = nB_COULEURS;
	}

	public Boolean getMODE_DEVELOPPEUR()
	{
		return MODE_DEVELOPPEUR;
	}

	public void setMODE_DEVELOPPEUR(Boolean mODE_DEVELOPPEUR)
	{
		MODE_DEVELOPPEUR = mODE_DEVELOPPEUR;
	}
}
