package Tables;

public class ColonneTerminee extends Tables<Boolean>
{
	public ColonneTerminee()
	{
		setTaille(0);
		init();
	}

	public ColonneTerminee(int t)
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
		for (int i = 0; i < this.t.length; i++)
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
		for (int i = 0; i < this.getTaille(); i++)
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
		int cpt = 0;
		for (int i = 0; i < this.getTaille(); i++)
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
		return t;
	}

}
