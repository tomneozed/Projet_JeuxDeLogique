import java.util.Scanner;

import Tables.ColonneTerminee;
import Tables.IndiceTab;
import Tables.MasterTable;
import Tables.Tables;

public class TestMasterTable extends Tables
{
	private int BP, MP;
	private int[] indiceTab = new int[10];
	int[][] masterTable = new int[5][4];
	int[][] XouXY;
	int[] combi = new int[4];
	int[] propo = new int[4];
	boolean[] colonneTerminee = new boolean[4];

	IndiceTab IT = new IndiceTab(10);
	ColonneTerminee CT = new ColonneTerminee();
	MasterTable MT = new MasterTable();

	public TestMasterTable()
	{
		int rejouer = -1, tour = 0;

		System.out.println("Bienvenue dans le Recherche +/- mode Challenger !");
		Scanner scan = new Scanner(System.in);

		int[] c = // combinaison de test : Exemple 2
		{ 3, 7, 5, 3 };

		this.combi[0] = 3;
		this.combi[1] = 7;
		this.combi[2] = 5;
		this.combi[3] = 3;

		initMT();
		initIT();
		initCT();
		for (int i = 0; i < this.propo.length; i++)
		{
			this.propo[i] = 0;
		}

		afficheMT();
		afficheIT();
		do
		{
			System.out.println("\n\n***************************************TOUR " + tour
					+ "***************************************");

			tourDeLOrdi();
			afficheMT();
			afficheIT();

			// System.out.println("\n\n\nVoulez-vous rejouer ? \n\n\t1. oui \t\t2.non");
			// rejouer = scan.nextInt();
			tour++;
		} while (tour < 10);
	}

	/*----------------------------------------Accesseurs et mutateurs------------------------------------------*/
	/****** GETTERS ******/
	public int getBP()
	{
		return this.BP;
	}

	public int getMP()
	{
		return this.MP;
	}

	public int getIT(int i)
	{
		return this.indiceTab[i];
	}

	/****** SETTERS ******/
	public void setBP(int b)
	{
		this.BP = b;
	}

	public void setMP(int m)
	{
		this.MP = m;
	}

	public void setIT(int indice, int nbr)
	{
		this.indiceTab[indice] = nbr;
	}

	/*------------------------------------------Fonctions commmunes--------------------------------------------*/

	/**
	 * Fonction mode defenseur
	 */
	public void tourDeLOrdi()
	{
		boolean yat;
		int[] jat = jATrouver(this.masterTable);
		int premierNullIT = -1;
		XouXY = propoXouXY(this.propo);
		bienMalPlace(this.combi, this.propo);
		int X = XouXY[0][0];
		int Y = XouXY[1][0];
		int jyPropo = XouXY[1][1];
		int iyMT = jat[0];
		int jyMT = jat[1];

		// On regarde si la proposition est de type X ou XY
		if (XouXY[1][0] != -1)	// propoXY
		{
			// 1er cas : BP > 0 & MP = 0
			if (this.BP >= 1 && this.MP == 0)
			{
				indiceBon(iyMT, iyMT, this.masterTable);
				majColonneTerminee();

				setIT(X, ((BP + MP) - 1));						// On remplit IT à la position X(XouXY[0][0])
																	// '(BP+MP)-1' fois (1 étant Y dont on sait qu'il
																	// existe 1 fois)
				initPremiereLigneMT();		// On remplit la premiere ligne de MT 'getIT(X)'
												// fois, càd
				// de '0 à mt.length' fois
				afficheMT();
			} else

			// 2eme cas : BP = 0 & MP = 1
			if (this.BP == 0 && this.MP == 1)
			{
				indiceARayer(iyMT, jyMT, this.masterTable);
				setIT(X, ((BP + MP) - 1));
			} else

			// 3eme cas : BP = 0 & MP = 2
			if (this.BP == 0 && this.MP == 2)
			{
				setIT(X, 1);
				initPremiereLigneMT();		// On remplit la premiere ligne de MT 'getIT(X)'
												// fois, càd
				// de '0 à mt.length' fois
				indiceBon(iyMT, jyMT, this.masterTable);

			} else

			// 4eme cas : BP = 1 & MP = 1
			// -> a) BP > 1 & MP = 1
			// -> b) BP = 1 & MP > 1 ]2[
			// -> c) BP > 1 & MP > 1 ]2[
			if (BP == 1 && MP == 1)
			{
				setIT(X, (BP + MP) - 1);
				initPremiereLigneMT();		// On remplit la premiere ligne de MT 'getIT(X)'
												// fois, càd
				// de '0 à mt.length' fois
				indiceARayer(iyMT, jyMT, this.masterTable);
				// indiceARayer(X, jy, this.masterTable);
			}

			yat = yATrouver(this.masterTable);
			jat = jATrouver(this.masterTable);

			premierNullIT = cherchePremierNull(this.indiceTab);
			this.propo = propoXY(premierNullIT, iyMT, (jat[1] - 1), this.masterTable);

		} else	// propoX
		{

			if (BP == 0)
			{
				setIT(X, 0); 						// On remplit IT à la position X(XouXY[0][0]) 0 fois

				yat = yATrouver(this.masterTable);
				jat = jATrouver(this.masterTable);

				premierNullIT = cherchePremierNull(this.indiceTab);

				this.propo = propoX(premierNullIT, this.masterTable);
			} else if (BP > 0)
			{
				setIT(X, BP); 									// On remplit IT à la position X(XouXY[0][0]) 'BP' fois
				initPremiereLigneMT();

				yat = yATrouver(this.masterTable);
				jat = jATrouver(this.masterTable);

				premierNullIT = cherchePremierNull(this.indiceTab);
				this.propo = propoXY(premierNullIT, this.masterTable[0][iyMT], (jat[1] - 1), this.masterTable);
			}
		}
	}

	public void initBPMP()
	{
		setBP(0);
		setMP(0);
	}

	/**
	 * Initialise la masterTable à -2 partout
	 */
	public void initMT()
	{
		for (int i = 0; i < this.masterTable.length; i++)
		{
			for (int j = 0; j < this.masterTable.length - 1; j++)
			{
				this.masterTable[i][j] = -2; // -2 = null
			}

		}
	}

	/**
	 * Initialise l'indiceTab à -2 partout
	 */
	public void initIT()
	{
		for (int i = 0; i < this.indiceTab.length; i++)
		{
			this.indiceTab[i] = -2; // -2 = null
		}
	}

	/**
	 * Initialise l'indiceTab à false partout
	 */
	public void initCT()
	{
		for (int i = 0; i < this.colonneTerminee.length; i++)
		{
			this.colonneTerminee[i] = false; // -2 = null
		}
	}

	/**
	 * Affiche la masterTable
	 */
	public void afficheMT()
	{
		System.out.println("\n\nMasterTab : " + (this.masterTable.length - 1) + "x" + this.masterTable.length + "\n");
		System.out.print("  ");
		for (int i = 0; i < this.masterTable.length - 1; i++)
		{
			System.out.print("  " + i + " ");
		}
		System.out.print("\n");

		for (int i = 0; i < this.masterTable.length; i++)
		{
			System.out.print(i + " ");
			for (int j = 0; j < this.masterTable.length - 1; j++)
			{
				if (this.masterTable[i][j] == -2)
				{
					System.out.print("|   ");
				} else if (this.masterTable[i][j] == -1)
				{
					System.out.print("| X ");
				} else
				{
					System.out.print("| " + this.masterTable[i][j] + " ");
				}
			}
			System.out.println("|");
		}
	}

	/**
	 * Affiche l'indiceTab
	 */
	public void afficheIT()
	{
		System.out.println("\n\nIndiceTab : ");
		for (int i = 0; i < this.indiceTab.length; i++)
		{
			System.out.println(i + " : " + this.indiceTab[i]);
		}
	}

	public void bienMalPlace(int[] comb, int[] prop)
	{
		initBPMP();
		System.out.println("\n");
		int i = 0, j = 0;
		int r = 0;
		while (i < comb.length) // i est l'indice de comb[]
		{
			r = 0;
			j = 0;
			while (r == 0 && j < prop.length) // j est l'indice de prop[]
			{
				if (comb[i] == prop[j]) // Si la valeur de comb à la position i est égale à une valeur de prop position
										 // j
				{
					if (comb[i] == prop[i]) // Si i = j
					{
						// System.out.println("Rond NOIR");
						this.BP++;
					} else
					{
						// System.out.println("Rond BLANC");
						this.MP++;
					}
					prop[j] = -1;
					r = 1;
				} else
				{
					j++;
				}
			}
			i++;
		}
	}

	/**
	 * Renvoie un tableau rempli de x
	 * 
	 * @param x
	 * @return propo
	 */
	public int[] propoX(int x, int[][] mt)
	{
		int[] propo = new int[mt.length - 1];

		System.out.println("\n***** PropoX() *****");

		for (int i = 0; i < propo.length; i++)
		{
			propo[i] = x;
			System.out.print(x + " | ");
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
	public int[] propoXY(int x, int y, int jy, int[][] mt)
	{
		int[] propo = new int[mt.length - 1];

		System.out.println("\n***** PropoXY() *****");

		for (int i = 0; i < propo.length; i++)
		{
			propo[i] = x;
		}
		propo[jy] = y;
		for (int cpt = 0; cpt < propo.length; cpt++)
		{
			System.out.print(propo[cpt] + " | ");
		}
		return propo;
	}

	/**
	 * Renvoie un tableau 2x2 contenant : [X,nbX] [Y,nbY]
	 * 
	 * @param propo
	 *            tableau à étudier
	 * @return
	 */
	@SuppressWarnings("null")
	public int[][] propoXouXY(int[] propo)
	{
		int[][] XouXY = new int[2][2];
		int x = -1;
		int y = -1;
		int idk;

		XouXY[1][0] = y;
		XouXY[1][1] = -1;

		for (int i = 0; i < propo.length; i++)
		{
			idk = compteCombien(propo[i], propo); // On compte le nombre de fois que propo[i] apparaît dans propo

			if (idk == 1) // Si il apparaît 1 fois, alors propo[i] = y
			{
				y = propo[i];
				XouXY[1][0] = y; // Y
				XouXY[1][1] = i; // jy
			} else if (idk > 1) // Sinon propo[i] = X
			{
				x = propo[i];
				XouXY[0][0] = x; // X
				XouXY[0][1] = idk; // nbx
			}
		}

		System.out.println("X : " + XouXY[0][0] + " apparait " + XouXY[0][1] + " fois" + "\nY : " + XouXY[1][0]
				+ " apparait à la position : " + XouXY[1][1]);

		return XouXY;
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
		} while (this.masterTable[j][colonne] != -2 && j < (this.propo.length + 1));

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
	public int chercheIYMT(int jyPropo)
	{
		int compteur1ereligne = 0;
		int iyMT = -1;
		while (compteur1ereligne < propo.length && iyMT == -1)
		{
			if (this.masterTable[0][compteur1ereligne] == this.propo[jyPropo]
					&& this.colonneTerminee[compteur1ereligne] == false)
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
	 * Complete la table IT si c'est possible
	 */
	public void completeIT()
	{
		int total = 0;
		for (int i = 0; i < 10; i++) // 10 correspond à mt.length()
		{
			if (this.indiceTab[i] != -2)
			{
				total = total + this.indiceTab[i];
			}
		}
		if (total == 10) // 10 correspond à mt.length()
		{
			for (int i = 0; i < 10; i++)
			{
				if (this.indiceTab[i] == -2)
				{
					this.indiceTab[i] = 0;
				}
			}
		}
	}

	/**
	 * Renvoie true si il reste un 'Y' à trouver (un élément de la 1ere ligne de la
	 * masterTable)
	 * 
	 * @param mt
	 * @return
	 */
	@SuppressWarnings("null")
	public boolean yATrouver(int[][] mt)
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
		} while (i < propo.length && yAT == false);
		return yAT;
	}

	/**
	 * renvoie un couple de valeurs [iy,jy] d'indice à tester
	 * 
	 * @param mt
	 * @return
	 */
	public int[] jATrouver(int[][] mt)
	{
		// Si il n'y a pas d'indice à trouver, on renvoie [-1,-1]
		int[] jAT =
		{ -1, -1 };
		int i = 0;

		do
		{
			// Si Y est compris dans [0,9], on cherche le premier "null" de sa colonne

			if (mt[0][i] >= 0 && mt[0][i] <= 9 && this.colonneTerminee[i] == false)
			{
				jAT[0] = i;
				jAT[1] = cherchePremierNullIndiceMT(jAT[0]);
			} else
			{
				i++;
			}
		} while (i < combi.length && jAT[0] == -1);

		return jAT;
	}

	/**
	 * On raye (-1) la case [iy,jy] de la masterTable
	 * 
	 * @param Y
	 * @param jy
	 * @param mt
	 */
	public void indiceARayer(int iyMT, int jyMT, int[][] mt)
	{
		mt[jyMT + 1][iyMT] = -1;
	}

	/**
	 * Remplit la 1ere ligne de la masterTable : on ajoute 'nbr' fois 'Y' dans la
	 * premiere ligne de la MT dans une case vide (-2)
	 * 
	 * @param Y
	 * @param nbr
	 * @param mt
	 */
	public void initPremiereLigneMT()
	{
		for (int i = 0; i < this.propo.length; i++)
		{
			this.masterTable[0][i] = -2;
		}
		// On parcourt la table IT
		for (int cpt = 0; cpt < this.indiceTab.length; cpt++)
		{
			// Si une valeur de l'indiceTable est comprise entre 1 et 9
			if (this.indiceTab[cpt] > 0 && this.indiceTab[cpt] < 10)
			{
				// Alors on remplit 1 à 9 fois la premiere ligne de MT avec la valeur cpt
				for (int cpt2 = 0; cpt2 < this.indiceTab[cpt]; cpt2++)
				{
					// On parcourt donc notre premiere ligne de la masterTable à la recherche du 1er
					// null (-2)
					int i = 0;
					while (this.masterTable[0][i] != -2 && i < this.propo.length)
					{
						i++;
					}
					if (this.masterTable[0][i] == -2)
					{
						this.masterTable[0][i] = cpt;
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
				indiceBon(cherchePremierNull(mt[j]), j - 1, mt);
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
				indiceBon(i, cherchePremierNull(mt[i]) - 1, mt);
			}
		}
	}

	public void indiceBon(int iyMT, int jyMT, int[][] mt)
	{

		// On "raye" la ligne d'indice j = jy
		for (int i = 0; i < propo.length; i++)
		{
			mt[jyMT + 1][i] = -1;
		}

		// On "raye" la colonne d'indice i = ix
		for (int j = 1; j < (propo.length + 1); j++)
		{
			mt[j][iyMT] = -1;
		}

		// On ajoute l'indice bon qui se trouve au croisement de la ligne et la colonne
		// susnomées
		mt[jyMT + 1][iyMT] = (jyMT);
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
	public void majColonneTerminee()
	{
		// On parcourt la premiere ligne de la masterTable ...
		for (int i = 0; i < this.propo.length; i++)
		{
			// ... Si un indice de la premiere ligne est compris entre 0 et 9 ...
			if (this.masterTable[0][i] > -1 && this.masterTable[0][i] < 10)
			{
				// ... On parcourt sa colonne ...
				for (int j = 1; j < this.propo.length; j++)
				{
					// ... et si il a un indice bon (compris entre 0 et 9) ...
					if (this.masterTable[j][i] > -1 && this.masterTable[j][i] < 10)
					{
						// ... alors la colonne est terminée !
						this.colonneTerminee[i] = true;
					}
				}
			}
		}
	}

}
