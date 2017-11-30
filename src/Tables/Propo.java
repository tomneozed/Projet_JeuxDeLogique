package Tables;

public class Propo extends Tables<Integer>
{
	int[][] XouXY = new int[2][2];

	public Propo()
	{
		setTaille(0);
		init();
	}

	public Propo(int i)
	{
		setTaille(i);
		init();
	}

	/*----------------------------------------Accesseurs et mutateurs------------------------------------------*/
	/****** GETTERS ******/
	public int[][] getXouXY()
	{
		return XouXY;
	}

	public int getXouXY(int i, int j)
	{
		return this.XouXY[j][i];
	}

	public int getX()
	{
		return this.XouXY[0][0];
	}

	public int getY()
	{
		return this.XouXY[1][0];
	}

	public int getIY()
	{
		return this.XouXY[1][1];
	}

	/****** SETTERS ******/
	public void setXouXY(int[][] xouXY)
	{
		XouXY = xouXY;
	}

	public void setXouXY(int i, int j, int val)
	{
		this.XouXY[j][i] = val;
	}

	public void setX(int x)
	{
		this.XouXY[0][0] = x;
	}

	public void setY(int y)
	{
		this.XouXY[1][0] = y;
	}

	public void setIY(int iy)
	{
		this.XouXY[1][1] = iy;
	}

	/*------------------------------------------------Methodes--------------------------------------------------*/
	/**
	 * Initialise propo à -2 partout
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
	 * Initialise propo à x partout
	 */
	public void init(int x)
	{
		this.t = new Integer[this.getTaille()];
		for (int i = 0; i < this.getTaille(); i++)
		{
			setValeur(i, x);
		}

	}

	/**
	 * Affiche propo
	 */
	public void affichePropo()
	{
		System.out.println("\nPropo : ");
		for (int i = 0; i < this.getTaille(); i++)
		{
			System.out.print(this.getValeur(i) + " | ");
		}
		System.out.println("\n");
	}

	/**
	 * Renvoie un tableau rempli de x
	 * 
	 * @param x
	 * @return propo
	 */
	public Integer[] propoX(int x)
	{
		Integer[] propo = new Integer[this.getTaille()];

		// System.out.println("\n***** PropoX() *****");

		for (int i = 0; i < this.getTaille(); i++)
		{
			propo[i] = x;
			// System.out.print(x + " | ");
		}

		return propo;
	}

	/**
	 * Renvoie un tableau rempli de x et un y à la position jy
	 * 
	 * @param x
	 *            valeur dont on teste l'existance dans la combi
	 * @param y
	 *            valeur dont on sait l'existance et dont on teste la position
	 * @param jy
	 *            position de Y à tester
	 * @return propo
	 */
	public Integer[] propoXY(int x, int y, int jy, int[][] mt)
	{
		Integer[] propo = new Integer[this.getTaille()];

		// System.out.println("\n***** PropoXY() *****");

		for (int i = 0; i < this.getTaille(); i++)
		{
			propo[i] = x;
		}
		propo[jy] = y;
		// for (int cpt = 0; cpt < this.getTaille(); cpt++)
		// {
		// System.out.print(propo[cpt] + " | ");
		// }
		return propo;
	}

	/**
	 * Renvoie la proposition finale via la mastertable
	 * 
	 * @param table
	 * @return
	 */
	public Integer[] propoFinale(int[][] table)
	{
		Integer[] pf = new Integer[table.length - 1];
		int i = 0;
		int j = 1;

		int cpt = 1;

		//Remplissage via les lignes 'indice'
		for (int k = 0; k < table.length - 1; k++)
		{
			i = 0;
			while (table[j][i] != cpt)
			{
				i++;
			}

			if (table[j][i] == cpt)
			{
				pf[cpt - 1] = table[0][i];
				cpt++;

				if (j < table.length - 1)
				{
					j++;
				}

			}
		}

		return pf;
	}

	/**
	 * Renvoie la proposition finale en testant la valeur du dernier y à trouver
	 * 
	 * @param table
	 * @param it
	 * @return
	 */
	public Integer[] propoChercheY(int[][] table, Tables it)
	{
		Integer[] pcy = new Integer[this.getTaille()];
		int i = 0;
		int j = 1;

		while (table[0][i] != -2)
		{
			i++;
		}

		if (table[0][i] == -2)
		{
			int cpt = 1;

			//Remplissage via les lignes 'indice'
			for (int k = 0; k < table.length - 1; k++)
			{
				i = 0;
				while (table[j][i] != cpt)
				{
					i++;
				}

				if (table[j][i] == cpt)
				{
					if (table[0][i] == -2)
					{
						pcy[cpt - 1] = it.cherchePremierNull();
					} else
					{
						pcy[cpt - 1] = table[0][i];
					}
					cpt++;

					if (j < table.length - 1)
					{
						j++;
					}

				}
			}
		}

		return pcy;

	}

	/**
	 * Renvoie un tableau 2x2 contenant : [X,nbX] [Y,nbY]
	 * 
	 */

	@SuppressWarnings("null")
	public void propoXouXY()
	{
		int x = -1;
		int y = -1;
		int idk = 0;
		int chiffresDifferents = 0;
		int j = 0;

		int[] tableComptage = new int[10];
		for (int k = 0; k < 10; k++)
		{
			tableComptage[k] = 0;
		}

		this.XouXY[1][0] = y;
		this.XouXY[1][1] = -1;
		this.XouXY[0][0] = 0;
		this.XouXY[0][1] = 0;

		for (int i = 0; i < 10; i++)
		{
			idk = compteCombien(i, this.t); // On compte le nombre de fois que
			// propo[i] apparaît dans propo
			tableComptage[i] = idk;
			if (idk > 0)
			{
				chiffresDifferents++;
			}
		}

		if (chiffresDifferents == 1)	//PropoX
		{
			XouXY[0][0] = this.getValeur(0); // X
			XouXY[0][1] = this.getTaille(); // nbx

		} else if (chiffresDifferents == 2) // PropoXY
		{
			while (j < 10 && chiffresDifferents > 0)
			{
				if (tableComptage[j] == 1)
				{
					XouXY[1][0] = this.getValeur(j); // Y
					XouXY[1][1] = j; // jy
					chiffresDifferents--;
				} else if (tableComptage[j] > 1)
				{
					XouXY[0][0] = j; // X
					XouXY[0][1] = tableComptage[j]; // nbx
					chiffresDifferents--;
				}
				j++;
			}
		} else if (chiffresDifferents > 2)	//PropoXYZ
		{
			while (j < 10 && chiffresDifferents > 0)
			{
				XouXY[0][0] = this.getValeur(j); // X
				XouXY[0][1] = tableComptage[j]; // nbx
				XouXY[1][0] = this.getValeur(j); // Y
				XouXY[1][1] = tableComptage[j]; // jy

				chiffresDifferents--;
				j++;
			}
		}

		for (int l = 0; l < 10; l++)
		{
			if (tableComptage[l] > 0)
			{
				System.out.println("La proposition contient : " + tableComptage[l] + " fois " + l);
			}
		}
	}

}
