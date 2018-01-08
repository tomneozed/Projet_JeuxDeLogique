package Tables;

import org.apache.log4j.Logger;

/**
 * Classe fille de Tables qui liste les colonnes remplies de la MasterTable
 * 
 * @author Thomas Pelissier
 * @version 1.0
 *
 */
public class ColonneTerminee extends Tables<Boolean>
{
	//Attributs
	private static Logger logger = Logger.getLogger(ColonneTerminee.class);

	//Constructeurs
	public ColonneTerminee()
	{
		setTaille(0);
		init();
	}

	public ColonneTerminee(Integer t)
	{
		setTaille(t);
		init();
	}

	//Methodes

	/**
	 * Initialisation des valeurs de CT à false
	 */
	public void init()
	{
		this.t = new Boolean[this.getTaille()];
		for (Integer i = 0; i < this.t.length; i++)
		{
			this.setValeur(i, false);
		}
	}

	/**
	 * Affiche colonneTerminee
	 */
	public void afficheCT()
	{
		System.out.println("\n\nColonneTerminee : ");
		for (Integer i = 0; i < this.getTaille(); i++)
		{
			System.out.println(i + " : " + this.getValeur(i));
		}
	}

	/**
	 * Retourne true si toutes les cases sont 'true' Retourne false sinon
	 * 
	 * @return t
	 */
	public Boolean terminee()
	{
		Boolean t = false;
		Integer cpt = 0;
		for (Integer i = 0; i < this.getTaille(); i++)
		{
			if (this.getValeur(i) == true)
			{
				cpt++;
			}

			if (cpt == this.getTaille())
			{
				t = true;
			}
		}

		logger.debug("terminee() -> " + t.booleanValue());
		return t;
	}

}
