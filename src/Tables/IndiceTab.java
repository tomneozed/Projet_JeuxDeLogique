package Tables;

public class IndiceTab extends Tables<Integer>
{
	private int total;

	public IndiceTab()
	{
		setTaille(10);
		init();
	}

	/*----------------------------------------Accesseurs et mutateurs------------------------------------------*/
	/****** GETTERS ******/

	public int getTotal()
	{
		return this.total;
	}

	/****** SETTERS ******/

	public void setTotal(int t)
	{
		this.total = t;
	}

	public void setValeur(int i, Integer val)
	{
		this.t[i] = val;
		if (val != -2)
		{
			majIT();
		}
	}

	/*------------------------------------------------Methodes--------------------------------------------------*/
	/**
	 * Initialise l'indiceTab à -2 partout
	 */
	public void init()
	{
		this.t = new Integer[this.getTaille()];
		for (int i = 0; i < this.getTaille(); i++)
		{
			setValeur(i, -2);
		}
	}

	/**
	 * Affiche l'indiceTab
	 */
	public void afficheIT()
	{
		System.out.println("\n\nIndiceTab : ");
		for (int i = 0; i < this.getTaille(); i++)
		{
			System.out.println(i + " : " + this.getValeur(i));
		}
	}

	/**
	 * complete IT si possible
	 */
	public void majIT()
	{
		int somme = 0;
		int i = 0;
		while (somme != getTotal() && i < 10)		// tant que la somme est < au total
		{
			if (getValeur(i) > -1 && getValeur(i) < 10)	// si la valeur est comprise dans [0;10]
			{
				somme += getValeur(i);					// On l'ajoute à somme
			}
			i++;
		}

		if (somme == getTotal())						// Si la somme est égale au total
		{
			for (int j = 0; j < getTaille(); j++)
			{
				if (getValeur(j) == -2)
				{
					setValeur(j, 0);				// On remplace tous les -2 par des 0
				}
			}
		}

	}

}
