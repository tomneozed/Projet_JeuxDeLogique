package Tables;

import org.apache.log4j.Logger;

public class ColonneTerminee extends Tables<Boolean>
{

	private static Logger logger = Logger.getLogger(ColonneTerminee.class);

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

	/*----------------------------------------Accesseurs et mutateurs------------------------------------------*/
	/****** GETTERS ******/
	/****** SETTERS ******/

	/*------------------------------------------------Methodes--------------------------------------------------*/
	/**
	 * Initialisation de CT à false
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
