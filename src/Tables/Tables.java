package Tables;

public abstract class Tables<T>
{
	protected int taille;
	protected T[] t;

	public Tables()
	{
		setTaille(4);
	}

	/*----------------------------------------Accesseurs et mutateurs------------------------------------------*/
	/****** GETTERS ******/
	public Integer getTaille()
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

	public Integer getPosition(T x)
	{
		Integer pos = -1;

		for (int i = 0; i < this.getTaille(); i++)
		{
			if (this.getValeur(i) == x)
			{
				pos = i;
			}
		}

		return pos;
	}

	/****** SETTERS ******/
	public void setTaille(Integer t)
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
	public Integer compteCombien(T x, T[] tab)
	{
		Integer nombre = 0;

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
	public Integer cherchePremierNull()
	{
		Integer x = 0;

		while (x < t.length && (int) t[x] != -2)
		{
			//System.out.println(t.length);
			x++;
		}

		if (x > 9)
		{
			x = 9;
		}

		return x;
	}

	/**
	 * Renvoie l'indice du premier a dans tab
	 * 
	 * @return
	 */
	public Integer cherchePremier(Integer a)
	{
		Integer x = 0;

		while (x < t.length && (int) t[x] != a)
		{
			//System.out.println(t.length);
			x++;
		}

		if (x > 9)
		{
			x = 9;
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
	public Integer[] indexOf(T nbr, T[] tab)
	{
		Integer[] index = new Integer[10];
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

		Integer[] indexFinal = new Integer[cpt + 1];
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
