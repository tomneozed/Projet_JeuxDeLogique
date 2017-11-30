package Tables;

public abstract class Tables<T>
{
	protected int taille;
	protected T[] t;

	public Tables()
	{

	}

	/*----------------------------------------Accesseurs et mutateurs------------------------------------------*/
	/****** GETTERS ******/
	public int getTaille()
	{
		return this.taille;
	}

	public T getValeur(int i)
	{
		return this.t[i];
	}

	public T[] getT()
	{
		return this.t;
	}

	/****** SETTERS ******/
	public void setTaille(int t)
	{
		this.taille = t;
	}

	public void setValeur(int i, T val)
	{
		this.t[i] = val;
	}

	public void setT(T[] it)
	{
		this.t = it;
	}

	/*------------------------------------------------Methodes--------------------------------------------------*/
	public void init()
	{

	}

	/**
	 * Renvoie le nombre de x dans tab
	 * 
	 * @param x
	 * @param tab
	 * @return
	 */
	public int compteCombien(T x, T[] tab)
	{
		int nombre = 0;

		for (int i = 0; i < tab.length; i++)
		{
			if (x == tab[i])
			{
				nombre++;
			}
		}
		return nombre;
	}

	/**
	 * Renvoie l'indice du premier null (-2) dans tab
	 * 
	 * @return
	 */
	public int cherchePremierNull()
	{
		int x = 0;

		while (x < t.length && (int) t[x] != -2)
		{
			x++;
		}
		return x;
	}

	/**
	 * Renvoie le ou les indice(s) de nbr dans tab
	 * 
	 * @param nbr
	 * @param tab
	 * @return
	 */
	public int[] indexOf(T nbr, T[] tab)
	{
		int[] index = new int[10];
		int cpt = 0;

		// On remplit index
		for (int j = 0; j < 10; j++)
		{
			if (tab[j] == nbr)
			{
				index[cpt] = j;
				cpt++;
			}
		}

		int[] indexFinal = new int[cpt + 1];
		for (int cpt2 = 0; cpt2 < cpt; cpt2++)
		{
			indexFinal[cpt2] = index[cpt2];
		}
		return indexFinal;
	}

	/**
	 * Affiche la table
	 */
	public void affiche()
	{
		System.out.println("\n\n" + this.getClass().getSimpleName() + " : ");
		for (int i = 0; i < this.getTaille(); i++)
		{
			System.out.println(i + " : " + this.getValeur(i));
		}
	}

}
