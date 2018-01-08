package Tables;

/**
 * Classe Mère des Tables
 * 
 * @author Thomas Pelissier
 * @version 1.0
 * @param <T>
 *            type de la table
 */
public abstract class Tables<T>
{
	//Attributs
	protected Integer taille;
	protected T[] t;

	//Constructeur
	public Tables()
	{

	}

	//Getters & Setters 
	public Integer getTaille()
	{
		return this.taille;
	}

	public T getValeur(Integer i)
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

		for (Integer i = 0; i < this.getTaille(); i++)
		{
			if (this.getValeur(i) == x)
			{
				pos = i;
			}
		}

		return pos;
	}

	public void setTaille(Integer t)
	{
		this.taille = t;
	}

	public void setValeur(Integer i, T val)
	{
		this.t[i] = val;
	}

	public void setT(T[] it)
	{
		this.t = it;
	}

	//Methodes

	/**
	 * Renvoie le nombre de x dans tab
	 * 
	 * @param x
	 *            nombre à compter
	 * @param tab
	 *            table dans laquelle compter
	 * @return le nombre de x dans tab
	 */
	public Integer compteCombien(T x, T[] tab)
	{
		Integer nombre = 0;

		for (Integer i = 0; i < tab.length; i++)
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
	 * @return x
	 */
	public Integer cherchePremierNull()
	{
		Integer x = 0;

		while (x < t.length && (int) t[x] != -2)
		{
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
	 * @param a
	 *            nombre à trouver
	 * @return x
	 */
	public Integer cherchePremier(Integer a)
	{
		Integer x = 0;

		while (x < t.length && (int) t[x] != a)
		{
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
	 *            nombre à chercher
	 * @param tab
	 *            table dans laquelle cherche
	 * @return indexFinal
	 */
	public Integer[] indexOf(T nbr, T[] tab)
	{
		Integer[] index = new Integer[10];
		Integer cpt = 0;

		// On remplit index
		for (Integer j = 0; j < 10; j++)
		{
			if (tab[j] == nbr)
			{
				index[cpt] = j;
				cpt++;
			}
		}

		Integer[] indexFinal = new Integer[cpt + 1];
		for (Integer cpt2 = 0; cpt2 < cpt; cpt2++)
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
		for (Integer i = 0; i < this.getTaille(); i++)
		{
			System.out.println(i + " : " + this.getValeur(i));
		}
	}

}
