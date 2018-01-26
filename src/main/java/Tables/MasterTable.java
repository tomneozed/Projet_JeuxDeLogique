package Tables;

import org.apache.log4j.Logger;

/**
 * Classe fille de Tables permettant à l'ordinateur de trouver des réponses au
 * Mastermind
 * 
 * @author Thomas Pelissier
 * @version 1.0
 */
public class MasterTable extends Tables<Integer>
{
	//Attributs
	private Integer[][] mt;
	private Integer largeur, longueur;
	private Integer[] jAT =
	{ -1, -1 };

	private static Logger logger = Logger.getLogger(MasterTable.class);

	//Constructeurs
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

	//Getters & Setters
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

	public Integer[] getjAT()
	{
		return jAT;
	}

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

	public void setjAT(Integer[] jAT)
	{
		this.jAT = jAT;
	}

	//Methodes

	/**
	 * Initialise la masterTable à -2 partout
	 */
	public void initMT()
	{
		logger.debug("InitMT() : -2");
		for (Integer i = 0; i < this.getLargeur(); i++)
		{
			for (Integer j = 0; j < this.getLongueur(); j++)
			{
				this.mt[i][j] = -2;
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
		for (Integer i = 0; i < this.getLongueur(); i++)
		{
			if (i < 10 && i > -1)
			{
				System.out.print("  " + i + " ");
			} else if (i > 9 && i < 100)
			{
				System.out.print("  " + i);
			}

		}
		System.out.print("\n");

		for (Integer i = 0; i < this.getLargeur(); i++)
		{
			if (i < 10 && i > -1)
			{
				System.out.print(i + " ");
			} else if (i > 9 && i < 100)
			{
				System.out.print(i);
			}

			for (Integer j = 0; j < this.getLongueur(); j++)
			{
				if (this.mt[i][j] == -2)
				{
					System.out.print("|   ");
				} else if (this.mt[i][j] == -1)
				{
					System.out.print("| X ");
				} else if (this.mt[i][j] < 10 && this.mt[i][j] > -1)
				{
					System.out.print("| " + this.mt[i][j] + " ");
				} else if (this.mt[i][j] > 9 && this.mt[i][j] < 100)
				{
					System.out.print("| " + this.mt[i][j]);
				}
			}
			System.out.println("|");
		}
	}

	/**
	 * Renvoie l'indice du premier Y de la premiere ligne de MT
	 * 
	 * @param y
	 *            nombre à chercher
	 * @return i
	 */
	public Integer indexOfY(Integer y)
	{
		Integer i = 0;

		while (i < this.getLongueur() && mt[0][i] != y)
		{
			i++;
		}
		logger.debug("indexOfY(" + y.intValue() + ") -> " + i.intValue());

		return i;
	}

	/**
	 * Renvoie l'indice du premier null (-2) de la colonne de la masterTable
	 * 
	 * @param colonne
	 *            indice de la colonne dans laquelle chercher
	 * @return indiceAtester
	 */
	public Integer cherchePremierNullIndiceMT(Integer colonne)
	{
		Integer indiceATester = -1;
		Integer j = 0;
		do
		{
			j++;
		} while (j < this.getLongueur() && this.mt[j][colonne] != -2);

		indiceATester = j;
		logger.debug("cherchePremierNullIndiceMT(" + colonne.intValue() + ") -> " + indiceATester.intValue());
		return indiceATester;
	}

	/**
	 * Renvoie la position de Y dans la premiere ligne de MT pour lequel
	 * colonneTerminee est fausse
	 * 
	 * @param jyP
	 *            position de Y
	 * @param p
	 *            premiere ligne MT
	 * @param colonneTerminee
	 *            table à tester
	 * @return iyMT
	 */
	public Integer chercheIYMT(Integer jyP, Integer[] p, boolean[] colonneTerminee)
	{
		Integer compteur1ereligne = 0;
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
		logger.debug("chercheIYMT() -> " + iyMT.intValue());
		return iyMT;
	}

	/**
	 * Renvoie true si il reste un 'Y' à trouver (un élément de la 1ere ligne de la
	 * masterTable)
	 * 
	 * @return yAT
	 */
	public boolean yATrouver()
	{
		boolean yAT = false;
		Integer i = 0;
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
		logger.debug(" yATrouver() -> " + yAT);
		return yAT;
	}

	/**
	 * Renvoie un couple de valeurs [iy,jy] d'indice à tester
	 * 
	 * @param colonneTerminee
	 *            Table à tester pour connaitre la colonne à tester
	 * 
	 * @return jAT
	 */
	public Integer[] jATrouver(Boolean[] colonneTerminee)
	{
		// Si il n'y a pas d'indice à trouver, on renvoie [-1,-1]
		Integer[] jAT =
		{ -1, -1 };
		Integer i = 0;

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
		logger.debug(" jATrouver() -> " + jAT[0] + ", " + jAT[1]);
		return jAT;
	}

	/**
	 * Raye (-1) la case [iy,jy] de la masterTable
	 * 
	 * @param iyMT
	 *            positon de iy dans MT
	 * @param jyMT
	 *            positon de jy dans MT
	 */
	public void indiceARayer(Integer iyMT, Integer jyMT)
	{
		this.setValeur(-1, iyMT, jyMT);
		majMT();
		logger.debug(" indiceARayer() -> [" + iyMT.intValue() + ", " + jyMT.intValue() + "]");
	}

	/**
	 * Raye (-1) les cases de valeur 'nombre' à l'indice jyMT
	 * 
	 * @param nombre
	 *            nombre à rayer
	 * @param jyMT
	 *            positon de jy dans MT
	 */
	public void nombreARayer(Integer nombre, Integer jyMT)
	{
		for (Integer i = 0; i < this.getLongueur(); i++)
		{
			if (this.getValeur(i, 0) == nombre)
			{
				this.setValeur(-1, i, jyMT);
			}
		}
		logger.debug(" nombreARayer() -> " + nombre.intValue() + " " + jyMT.intValue());
	}

	/**
	 * Remplit la 1ere ligne de la masterTable : on ajoute 'nbr' fois 'Y' dans la
	 * premiere ligne de la MT dans une case vide (-2)
	 * 
	 * @param integers
	 *            indiceTab
	 */
	public void initPremiereLigneMT(Integer[] integers)
	{
		for (Integer i = 0; i < this.getLongueur(); i++)
		{
			this.setValeur(-2, i, 0);
		}
		// On parcourt la table IT
		for (Integer cpt = 0; cpt < 10; cpt++)
		{
			// Si une valeur de l'indiceTable est comprise entre 1 et 9
			if (integers[cpt] > 0 && integers[cpt] < 10)
			{
				// Alors on remplit 1 à 9 fois la premiere ligne de MT avec la valeur cpt
				for (Integer cpt2 = 0; cpt2 < integers[cpt]; cpt2++)
				{
					// On parcourt donc notre premiere ligne de la masterTable à la recherche du 1er
					// null (-2)
					Integer i = 0;
					while (i < this.getLongueur() && this.getValeur(i, 0) != -2)
					{
						i++;
					}
					if (i < this.getLongueur() && this.getValeur(i, 0) == -2)
					{
						this.setValeur(cpt, i, 0);
					}

				}
			}
		}
	}

	/**
	 * Complete la/les ligne(s) si possible
	 */
	public void completeHorizontal()
	{
		Integer compteMoins1 = 0;
		Integer autre = 0;
		Integer i = 0;
		for (Integer j = 1; j < this.getLargeur(); j++)
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
					indiceBon(cherchePremierNull(mt[j]), j);
				}
			}
		}
	}

	/**
	 * Complete la/les colonnes(s) si possible
	 */
	public void completeVertical()
	{
		Integer compteMoins1 = 0;
		Integer autre = 0;
		for (Integer i = 0; i < this.getLongueur(); i++)
		{
			compteMoins1 = 0;
			for (Integer j = 1; j < this.getLargeur(); j++)
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
					indiceBon(i, cherchePremierNullIndiceMT(i));
				}
			}
		}
	}

	/**
	 * Déclare que le nombre mt[jyMT][iyMT] est le bon indice de mt[0][iyMT] et raye
	 * sa colonne ainsi que sa ligne
	 * 
	 * @param iyMT
	 *            colonne de MT
	 * @param jyMT
	 *            ligne de MT
	 */
	public void indiceBon(Integer iyMT, Integer jyMT)
	{
		//On vérifie que les indices soient possibles (contenus dans [0,10])
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
			// susnomées
			mt[jyMT][iyMT] = (jyMT);

			logger.debug(" indiceBon() -> [" + iyMT.intValue() + ", " + jyMT.intValue() + "]");

		}
	}

	/**
	 * Renvoie l'indice du premier null (-2) dans tab
	 * 
	 * @param tab
	 *            table dans laquelle chercher
	 * @return x
	 */
	public Integer cherchePremierNull(Integer[] tab)
	{
		Integer x = 0;

		while (x < tab.length && tab[x] != -2)
		{
			x++;
		}
		logger.debug(" cherchePremierNull() -> [" + x.intValue());
		return x;
	}

	/**
	 * Complete la masterTable si possible
	 */
	public void majMT()
	{
		completeHorizontal();
		completeVertical();
	}

	/**
	 * Met à jour la table colonneTerminee par rapport à la masterTable
	 * 
	 * @param colonneTerminee
	 *            table à mettre à jour
	 * @return colonneTerminee
	 */
	public Boolean[] majColonneTerminee(Boolean[] colonneTerminee)
	{
		// On parcourt la premiere ligne de la masterTable ...
		for (Integer i = 0; i < this.getLongueur(); i++)
		{
			// ... Si un indice de la premiere ligne est compris entre 0 et 9 ...
			if (this.getValeur(i, 0) > -1 && this.getValeur(i, 0) < 10)
			{
				// ... On parcourt sa colonne ...
				for (Integer j = 1; j < this.getLargeur(); j++)
				{
					// ... et si il a un indice bon (compris entre 1 et 10) ...
					if (this.getValeur(i, j) > 0 && this.getValeur(i, j) < 11)
					{
						// ... alors la colonne est terminée !
						colonneTerminee[i] = true;
					}
				}
			}
		}
		logger.debug(" majColonneTerminee() ");
		return colonneTerminee;
	}

}
