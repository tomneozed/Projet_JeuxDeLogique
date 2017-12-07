package Tables;

public class MasterTable extends Tables<Integer>
{
	private Integer[][] mt;
	private Integer largeur, longueur;

	public MasterTable()
	{
		setLongeur(4);
		setLargeur(4 + 1);
		mt = new Integer[this.getLargeur()][this.getLongueur()];
		initMT();
	}

	public MasterTable(Integer l)
	{
		setLongeur(l);
		setLargeur(l + 1);
		mt = new Integer[this.getLargeur()][this.getLongueur()];
		initMT();
	}

	/*----------------------------------------Accesseurs et mutateurs------------------------------------------*/
	/****** GETTERS ******/
	public Integer getLargeur()
	{
		return this.largeur;
	}

	public Integer getLongueur()
	{
		return this.longueur;
	}

	public Integer getValeur(Integer i, Integer j)
	{
		return this.mt[j][i];
	}

	public Integer[][] getMT()
	{
		return this.mt;
	}

	public Integer[] getMT(Integer ligne)
	{
		return this.mt[ligne];
	}

	/****** SETTERS ******/
	public void setLongeur(Integer l)
	{
		this.longueur = l;
	}

	public void setLargeur(Integer l)
	{
		this.largeur = l;
	}

	public void setValeur(Integer val, Integer i, Integer j)
	{
		this.mt[j][i] = val;
	}

	public void setMT(Integer[][] mt)
	{
		this.mt = mt;
	}

	/*------------------------------------------------Methodes--------------------------------------------------*/

	/**
	 * Initialise la masterTable � -2 partout
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
	 * Renvoie l'indice du premier Y de la premiere ligne de MT
	 * 
	 * @param aTrouver
	 * @return
	 */
	public Integer indexOfY(Integer y)
	{
		Integer i = 0;

		while (i < this.getLongueur() && mt[0][i] != y)
		{
			i++;
		}
		return i;
	}

	/**
	 * Renvoie l'indice du premier null (-2) de la colonne de la masterTable
	 * 
	 * @param mt
	 * @param Y
	 * @return
	 */
	public Integer cherchePremierNullIndiceMT(Integer colonne)
	{
		Integer indiceATester = -1;
		int j = 0;
		do
		{
			j++;
		} while (j < this.getLongueur() && this.mt[j][colonne] != -2);

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
	public Integer chercheIYMT(Integer jyP, Integer[] p, boolean[] colonneTerminee)
	{
		int compteur1ereligne = 0;
		Integer iyMT = -1;
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
	 * Renvoie true si il reste un 'Y' � trouver (un �l�ment de la 1ere ligne de la
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
	 * renvoie un couple de valeurs [iy,jy] d'indice � tester
	 * 
	 * @return jAT
	 */
	public int[] jATrouver(Boolean[] colonneTerminee)
	{
		// Si il n'y a pas d'indice � trouver, on renvoie [-1,-1]
		int[] jAT =
		{ -1, -1 };
		int i = 0;

		do
		{
			// Si Y est compris dans [0,9], on cherche le premier "null" de sa colonne

			if (this.getValeur(i, 0) >= 0 && this.getValeur(i, 0) <= 9 && colonneTerminee[i] == false)
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
	public void indiceARayer(Integer iyMT, Integer jyMT)
	{
		this.setValeur(-1, iyMT, jyMT);
		majMT();
	}

	/**
	 * Remplit la 1ere ligne de la masterTable : on ajoute 'nbr' fois 'Y' dans la
	 * premiere ligne de la MT dans une case vide (-2)
	 * 
	 * @param Y
	 * @param nbr
	 * @param mt
	 */
	public void initPremiereLigneMT(Integer[] integers)
	{
		for (int i = 0; i < this.getLongueur(); i++)
		{
			this.setValeur(-2, i, 0);
		}
		// On parcourt la table IT
		for (int cpt = 0; cpt < 10; cpt++)
		{
			// Si une valeur de l'indiceTable est comprise entre 1 et 9
			if (integers[cpt] > 0 && integers[cpt] < 10)
			{
				// Alors on remplit 1 � 9 fois la premiere ligne de MT avec la valeur cpt
				for (int cpt2 = 0; cpt2 < integers[cpt]; cpt2++)
				{
					// On parcourt donc notre premiere ligne de la masterTable � la recherche du 1er
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
	public void completeHorizontal()
	{
		int compteMoins1 = 0;
		int autre = 0;
		int i = 0;
		for (int j = 1; j < this.getLargeur(); j++)
		{
			compteMoins1 = 0;
			for (i = 0; i < this.getLongueur(); i++)
			{
				if (mt[j][i] == -1)
				{
					compteMoins1++;
				} else
				{
					autre = mt[j][i];
				}
			}
			if (compteMoins1 == (this.getLongueur() - 1))
			{
				if (autre == -2)
				{
					// mt[cherchePremierNull(mt[j])][j] = j - 1;
					indiceBon(cherchePremierNull(mt[j]), j);
				}
			}
		}
	}

	/**
	 * Complete la/les colonnes(s) si possible
	 * 
	 * @param mt
	 */
	public void completeVertical()
	{
		int compteMoins1 = 0;
		int autre = 0;
		for (int i = 0; i < this.getLongueur(); i++)
		{
			compteMoins1 = 0;
			for (int j = 1; j < this.getLargeur(); j++)
			{
				if (mt[j][i] == -1)
				{
					compteMoins1++;
				} else
				{
					autre = mt[j][i];
				}
			}
			if (compteMoins1 == (this.getLongueur() - 1))
			{
				if (autre == -2)
				{
					// mt[i][cherchePremierNull(mt[i])] = cherchePremierNull(mt[i]) - 1;
					indiceBon(i, cherchePremierNullIndiceMT(i));
				}
			}
		}
	}

	/**
	 * D�clare que le nombre mt[jyMT][iyMT] est le bon indice de mt[0][iyMT] et raye
	 * sa colonne ainsi que sa ligne
	 * 
	 * @param iyMT
	 *            colonne de MT
	 * @param jyMT
	 *            ligne de MT
	 */
	public void indiceBon(Integer iyMT, Integer jyMT)
	{
		//On v�rifie que les indices soient possibles (contenus dans [0,10])
		if (iyMT > -1 && iyMT < 11 && jyMT > -1 && jyMT < 11)
		{
			// On "raye" la ligne d'indice j = jy
			for (Integer i = 0; i < this.getLongueur(); i++)
			{
				mt[jyMT][i] = -1;
			}

			// On "raye" la colonne d'indice i = iy
			for (Integer j = 1; j < this.getLargeur(); j++)
			{
				mt[j][iyMT] = -1;
			}

			// On ajoute l'indice bon qui se trouve au croisement de la ligne et la colonne
			// susnom�es
			mt[jyMT][iyMT] = (jyMT);
		}
	}

	/**
	 * Renvoie l'indice du premier null (-2) dans tab
	 * 
	 * @param tab
	 * @return
	 */
	public Integer cherchePremierNull(Integer[] tab)
	{
		Integer x = 0;

		while (x < tab.length && tab[x] != -2)
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
	public void majMT()
	{
		completeHorizontal();
		completeVertical();
	}

	/**
	 * 
	 */
	public Boolean[] majColonneTerminee(Boolean[] colonneTerminee)
	{
		// On parcourt la premiere ligne de la masterTable ...
		for (int i = 0; i < this.getLongueur(); i++)
		{
			// ... Si un indice de la premiere ligne est compris entre 0 et 9 ...
			if (this.getValeur(i, 0) > -1 && this.getValeur(i, 0) < 10)
			{
				// ... On parcourt sa colonne ...
				for (int j = 1; j < this.getLargeur(); j++)
				{
					// ... et si il a un indice bon (compris entre 0 et 9) ...
					if (this.getValeur(i, j) > -1 && this.getValeur(i, j) < 10)
					{
						// ... alors la colonne est termin�e !
						colonneTerminee[i] = true;
					}
				}
			}
		}
		return colonneTerminee;
	}

}
