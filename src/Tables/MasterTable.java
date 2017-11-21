package Tables;

public class MasterTable extends Tables
{
	private int[][] mt;
	private int largeur, longueur;

	public MasterTable()
	{
		setLongeur(4);
		setLargeur(4 + 1);
		mt = new int[this.getLargeur()][this.getLongueur()];
		initMT();
	}

	public MasterTable(int l)
	{
		setLongeur(l);
		setLargeur(l + 1);
		mt = new int[this.getLargeur()][this.getLongueur()];
		initMT();
	}

	/*----------------------------------------Accesseurs et mutateurs------------------------------------------*/
	/****** GETTERS ******/
	public int getLargeur()
	{
		return this.largeur;
	}

	public int getLongueur()
	{
		return this.longueur;
	}

	public int getValeur(int i, int j)
	{
		return this.mt[j][i];
	}

	/****** SETTERS ******/
	public void setLongeur(int l)
	{
		this.longueur = l;
	}

	public void setLargeur(int l)
	{
		this.largeur = l;
	}

	public void setValeur(int val, int i, int j)
	{
		this.mt[j][i] = val;
	}

	/*------------------------------------------------Methodes--------------------------------------------------*/

	/**
	 * Initialise la masterTable à -2 partout
	 */
	public void initMT()
	{
		for (int i = 0; i < this.getLargeur(); i++)
		{
			for (int j = 0; j < this.getLongueur(); j++)
			{
				this.mt[i][j] = -2; // -2 = null
			}
		}
	}

	/**
	 * Affiche la masterTable
	 */
	public void afficheMT()
	{
		System.out.println("\n\nMasterTab : " + this.getLongueur() + "x" + this.getLargeur() + "\n");
		System.out.print("  ");
		for (int i = 0; i < this.getLongueur(); i++)
		{
			System.out.print("  " + i + " ");
		}
		System.out.print("\n");

		for (int i = 0; i < this.getLargeur(); i++)
		{
			if (i < 10 && i > -1)
			{
				System.out.print(i + " ");
			} else if (i > 9 && i < 100)
			{
				System.out.print(i);
			}

			for (int j = 0; j < this.getLongueur(); j++)
			{
				if (this.mt[i][j] == -2)
				{
					System.out.print("|   ");
				} else if (this.mt[i][j] == -1)
				{
					System.out.print("| X ");
				} else
				{
					System.out.print("| " + this.mt[i][j] + " ");
				}
			}
			System.out.println("|");
		}
	}

	/**
	 * Renvoie l'indice du premier null (-2) de la colonne de la masterTable
	 * 
	 * @param mt
	 * @param Y
	 * @return
	 */
	public int cherchePremierNullIndiceMT(int colonne)
	{
		int indiceATester = -1;
		int j = 0;
		do
		{
			j++;
		} while (this.mt[j][colonne] != -2 && j <= this.getLargeur());

		indiceATester = j;

		return indiceATester;
	}

	/**
	 * Renvoie la position de Y dans la premiere ligne de MT pour lequel
	 * colonneTerminee est fausse
	 * 
	 * @param jyPropo
	 * @return
	 */
	public int chercheIYMT(int jyP, int[] p, boolean[] colonneTerminee)
	{
		int compteur1ereligne = 0;
		int iyMT = -1;
		while (compteur1ereligne < this.getLargeur() && iyMT == -1)
		{
			if (this.mt[0][compteur1ereligne] == p[jyP] && colonneTerminee[compteur1ereligne] == false)
			{
				iyMT = compteur1ereligne;
			} else
			{
				compteur1ereligne++;
			}
		}

		return iyMT;
	}

	/**
	 * Renvoie true si il reste un 'Y' à trouver (un élément de la 1ere ligne de la
	 * masterTable)
	 * 
	 * @return yAT
	 */
	@SuppressWarnings("null")
	public boolean yATrouver()
	{
		boolean yAT = false;
		int i = 0;
		do
		{
			if (mt[0][i] == -2)
			{
				yAT = true;
			} else
			{
				i++;
			}
		} while (i < this.getLongueur() && yAT == false);
		return yAT;
	}

	/**
	 * renvoie un couple de valeurs [iy,jy] d'indice à tester
	 * 
	 * @return jAT
	 */
	public int[] jATrouver(boolean[] colonneTerminee)
	{
		// Si il n'y a pas d'indice à trouver, on renvoie [-1,-1]
		int[] jAT =
		{ -1, -1 };
		int i = 0;

		do
		{
			// Si Y est compris dans [0,9], on cherche le premier "null" de sa colonne

			if (mt[0][i] >= 0 && mt[0][i] <= 9 && colonneTerminee[i] == false)
			{
				jAT[0] = i;
				jAT[1] = cherchePremierNullIndiceMT(jAT[0]);
			} else
			{
				i++;
			}
		} while (i < this.getLongueur() && jAT[0] == -1);

		return jAT;
	}

	/**
	 * On raye (-1) la case [iy,jy] de la masterTable
	 * 
	 * @param Y
	 * @param jy
	 * @param mt
	 */
	public void indiceARayer(int iyMT, int jyMT)
	{
		this.setValeur(-1, iyMT, jyMT + 1);
	}

	/**
	 * Remplit la 1ere ligne de la masterTable : on ajoute 'nbr' fois 'Y' dans la
	 * premiere ligne de la MT dans une case vide (-2)
	 * 
	 * @param Y
	 * @param nbr
	 * @param mt
	 */
	public void initPremiereLigneMT(int[] IT)
	{
		for (int i = 0; i < this.getLongueur(); i++)
		{
			this.setValeur(-2, i, 0);
		}
		// On parcourt la table IT
		for (int cpt = 0; cpt < 10; cpt++)
		{
			// Si une valeur de l'indiceTable est comprise entre 1 et 9
			if (IT[cpt] > 0 && IT[cpt] < 10)
			{
				// Alors on remplit 1 à 9 fois la premiere ligne de MT avec la valeur cpt
				for (int cpt2 = 0; cpt2 < IT[cpt]; cpt2++)
				{
					// On parcourt donc notre premiere ligne de la masterTable à la recherche du 1er
					// null (-2)
					int i = 0;
					while (this.getValeur(i, 0) != -2 && i < this.getLongueur())
					{
						i++;
					}
					if (this.getValeur(i, 0) == -2)
					{
						this.setValeur(cpt, i, 0);
					}

				}
			}
		}
	}

	/**
	 * Complete la/les ligne(s) si possible
	 * 
	 * @param mt
	 */
	public void completeHorizontal(int[][] mt)
	{
		int compteMoins1 = 0;
		int i = 0;
		for (int j = 1; j < (mt.length + 2); j++)
		{
			for (i = 0; i < mt.length; i++)
			{
				if (mt[i][j] == -1)
				{
					compteMoins1++;
				}
			}
			if (compteMoins1 == (mt.length - 1))
			{
				// mt[cherchePremierNull(mt[j])][j] = j - 1;
				indiceBon(cherchePremierNull(mt[j]), j - 1);
			}
		}
	}

	/**
	 * Complete la/les colonnes(s) si possible
	 * 
	 * @param mt
	 */
	public void completeVertical(int[][] mt)
	{
		int compteMoins1 = 0;
		for (int i = 0; i < mt.length; i++)
		{
			for (int j = 1; j < (mt.length + 2); j++)
			{
				if (mt[i][j] == -1)
				{
					compteMoins1++;
				}
			}
			if (compteMoins1 == (mt.length - 2))
			{
				// mt[i][cherchePremierNull(mt[i])] = cherchePremierNull(mt[i]) - 1;
				indiceBon(i, cherchePremierNull(mt[i]) - 1);
			}
		}
	}

	public void indiceBon(int iyMT, int jyMT)
	{

		// On "raye" la ligne d'indice j = jy
		for (int i = 0; i < this.getLongueur(); i++)
		{
			mt[jyMT + 1][i] = -1;
		}

		// On "raye" la colonne d'indice i = ix
		for (int j = 1; j < this.getLargeur(); j++)
		{
			mt[j][iyMT] = -1;
		}

		// On ajoute l'indice bon qui se trouve au croisement de la ligne et la colonne
		// susnomées
		mt[jyMT + 1][iyMT] = (jyMT);
	}

	/**
	 * Renvoie l'indice du premier null (-2) dans tab
	 * 
	 * @param tab
	 * @return
	 */
	public int cherchePremierNull(int[] tab)
	{
		int x = 0;

		while (tab[x] != -2)
		{
			x++;
		}
		return x;
	}

	/**
	 * Complete la masterTable si possible
	 * 
	 * @param mt
	 */
	public void majMT(int[][] mt)
	{
		completeHorizontal(mt);
		completeVertical(mt);
	}

	/**
	 * 
	 */
	public boolean[] majColonneTerminee()
	{
		boolean[] colonneTerminee = new boolean[this.getLongueur()];

		// On parcourt la premiere ligne de la masterTable ...
		for (int i = 0; i < this.getLongueur(); i++)
		{
			// ... Si un indice de la premiere ligne est compris entre 0 et 9 ...
			if (this.getValeur(i, 0) > -1 && this.getValeur(i, 0) < 10)
			{
				// ... On parcourt sa colonne ...
				for (int j = 1; j < this.getLongueur(); j++)
				{
					// ... et si il a un indice bon (compris entre 0 et 9) ...
					if (this.getValeur(i, j) > -1 && this.getValeur(i, j) < 10)
					{
						// ... alors la colonne est terminée !
						colonneTerminee[i] = true;
					}
				}
			}
		}
		return colonneTerminee;
	}

}
