import java.util.Scanner;

import Utilisateur.Joueur;
import Utilisateur.Ordi;

public class test
{
	private int BP, MP;
	private int[] indiceTab = new int[10];
	int[][] masterTable = new int[4][5];
	int[][] XouXY;
	Ordi ordi = new Ordi();
	Joueur joueur = new Joueur();

	public test()
	{
		int rejouer = -1;

		System.out.println("Bienvenue dans le Recherche +/- mode Challenger !");
		Scanner scan = new Scanner(System.in);

		int[] c = // combinaison de test
		{ 3, 0, 1, 5, 2, 6, 4, 7, 9, 0 };

		do
		{
			// On initialise les objets joueur et ordi
			ordi.initialisation();
			joueur.initialisation();
			joueur.setVie(10);

			// L'ordi créé une combinaison aléatoire
			// ordi.combi(4);
			/*
			 * ordi.setCombiTab(c); System.out.println("\n"); for (int i = 0; i < 10; i++) {
			 * System.out.print(ordi.getCombiTab(i) + " "); }
			 */

			// On traduit la combianison en couleurs
			// intToCouleur(ordi.getCombiTab());

			/*
			 * do { // Le joueur propose une réponse joueur.chercheMastermind(10);
			 * 
			 * joueur.setPropositionTab(stringToInt(joueur.getPropositionString()));
			 * 
			 * XouXY = propoXouXY(joueur.getPropositionTab());
			 * 
			 * bienMalPlace(ordi.getCombiTab(), joueur.getPropositionTab());
			 * afficheRonds(this.getBP(), this.getMP()); if (BP != 10) {
			 * joueur.setVie(joueur.getVie() - 1); }
			 * 
			 * } while (BP != 10 && joueur.getVie() != 0);
			 */

			// le joueur créé une combinaison
			joueur.combi(4);

			System.out.println("\nVoulez-vous rejouer ? \n\n\t1. oui \t\t2.non");
			rejouer = scan.nextInt();

		} while (rejouer == 1);
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
	/*
	 * 
	 */
	public void tourDeLOrdi()
	{
		boolean yat;
		int[] iat;
		int premierNullIT = -1;
		XouXY = propoXouXY(ordi.getPropositionTab());

		// On regarde si la proposition est de type X ou XY
		if (XouXY[1][1] > 0)
		{
			// propoXY

		} else
		{
			// propoX
			if (BP == 0)
			{
				setIT(XouXY[0][0], BP); // On remplit IT à la position X(XouXY[0][0]) 0 fois

				yat = yATrouver(this.masterTable);
				iat = indiceATrouver(masterTable);

				premierNullIT = cherchePremierNull(this.indiceTab);

				ordi.setPropositionTab(propoX(premierNullIT, this.masterTable));
			} else if (BP > 0)
			{
				setIT(XouXY[0][0], BP); // On remplit IT à la position X(XouXY[0][0]) 'BP' fois
				for (int i = 0; i < BP; i++)
				{
					setMT(XouXY[0][0], this.masterTable);
				}

			}

		}

		// premier tour
		if (BP == 0 && MP == 0)
		{
			yat = yATrouver(this.masterTable);
			iat = indiceATrouver(masterTable);

			premierNullIT = cherchePremierNull(this.indiceTab);

			ordi.setPropositionTab(propoX(premierNullIT, this.masterTable));
		} else if ((BP + MP) > 0 && MP == 0) // Si il y a de multiples Bien Placés
		{

		}

	}

	public void initBPMP()
	{
		setBP(0);
		setMP(0);
	}

	public void bienMalPlace(int[] comb, int[] prop)
	{
		initBPMP();
		System.out.println("\n");
		int i = 0, j = 0;
		int r = 0;
		while (i < 10)
		{
			r = 0;
			j = 0;
			while (r == 0 && j < 10)
			{
				if (comb[i] == prop[j])
				{
					if (comb[i] == prop[i])
					{
						// System.out.println("Rond NOIR");
						this.BP++;
					} else
					{
						// System.out.println("Rond BLANC");
						this.MP++;

						// System.out.println("prop[] : ");
						// for (int x = 0; x < 10; x++)
						// {
						// System.out.print(prop[x] + " ");
						// }
						// System.out.println("\n");
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
		int[] propo = new int[mt.length];

		System.out.println("***** PropoX() *****");

		for (int i = 0; i < propo.length; i++)
		{
			propo[i] = x;
			System.out.print(x + " | ");
		}

		return propo;
	}

	/**
	 * Renvoie un tableau rempli de x et un y à la position iY
	 * 
	 * @param x
	 *            valeur dont on teste l'existance dans la combi
	 * @param y
	 *            valeur dont on sait l'existance et dont on teste la position
	 * @param iY
	 *            position de Y à tester
	 * @return propo
	 */
	public int[] propoXY(int x, int y, int iY, int[][] mt)
	{
		int[] propo = new int[mt.length];

		System.out.println("***** PropoXY() *****");

		for (int i = 0; i < propo.length; i++)
		{
			propo[i] = x;
		}
		propo[iY] = y;
		for (int cpt = 0; cpt < propo.length; cpt++)
		{
			System.out.print(cpt + " | ");
		}
		return propo;
	}

	public int compteCombien(int x, int[] tab)
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

	@SuppressWarnings("null")
	public int[][] propoXouXY(int[] propo)
	{
		int[][] XouXY = new int[2][2];
		int x = -1;
		int y = -1;
		int idk;

		XouXY[0][1] = y;

		for (int i = 0; i < propo.length; i++)
		{
			idk = compteCombien(propo[i], propo);

			if (idk == 1)
			{
				y = propo[i];
				XouXY[0][1] = y;
				XouXY[1][1] = 1;
			} else if (idk > 1)
			{
				x = propo[i];
				XouXY[0][0] = x;
				XouXY[1][0] = idk;
			}
		}

		System.out.println("X : " + XouXY[0][0] + " apparait " + XouXY[1][0] + " fois" + "\nY : " + XouXY[0][1]
				+ " apparait " + XouXY[1][1] + " fois");

		return XouXY;
	}

	public int[] stringToInt(String s)
	{
		int[] tab = new int[s.length()];
		for (int i = 0; i < s.length(); i++)
		{
			switch (s.substring(i, i + 1))
			{
			case "0":
				tab[i] = 0;
				break;

			case "1":
				tab[i] = 1;
				break;

			case "2":
				tab[i] = 2;
				break;

			case "3":
				tab[i] = 3;
				break;

			case "4":
				tab[i] = 4;
				break;

			case "5":
				tab[i] = 5;
				break;

			case "6":
				tab[i] = 6;
				break;

			case "7":
				tab[i] = 7;
				break;

			case "8":
				tab[i] = 8;
				break;

			case "9":
				tab[i] = 9;
				break;

			default:
				break;
			}
		}
		return tab;
	}

	public void afficheRonds(int rn, int rb)
	{
		for (int i = 0; i < rn; i++)
		{
			System.out.print("X ");
		}
		for (int i = 0; i < rb; i++)
		{
			System.out.print("O ");
		}
	}

	public int cherchePremierNull(int[] tab)
	{
		int x = 0;

		while (tab[x] != (Integer) null)
		{
			x++;
		}
		return x;
	}

	public void completeIT()
	{
		int total = 0;
		for (int i = 0; i < 10; i++) // 10 correspond à mt.length()
		{
			if (this.indiceTab[i] != (Integer) null)
			{
				total = total + this.indiceTab[i];
			}
		}
		if (total == 10) // 10 correspond à mt.length()
		{
			for (int i = 0; i < 10; i++)
			{
				if (this.indiceTab[i] == (Integer) null)
				{
					this.indiceTab[i] = 0;
				}
			}
		}
	}

	public boolean yATrouver(int[][] mt)
	{
		boolean yAT = false;

		for (int i = 0; i < mt.length; i++)
		{
			if (mt[i][0] == (Integer) null)
			{
				yAT = true;
			}
		}
		return yAT;
	}

	/**
	 * renvoie un couple de valeurs [Y,iy] d'indice à tester
	 * 
	 * @param mt
	 * @return
	 */
	public int[] indiceATrouver(int[][] mt)
	{
		// Si il n'y a pas d'indice à trouver, on renvoie [-1,-1]
		int[] iAT =
		{ -1, -1 };
		int i = 0;
		// Tant que Y est null, on avance
		while (mt[i][0] == (Integer) null)
		{
			i++;
		}
		// Si Y est compris dans [0,9], on cherche le premier "null" de sa colonne
		if (mt[i][0] >= 0 && mt[i][0] <= 9)
		{
			iAT[0] = i;
			iAT[1] = cherchePremierNull(mt[i]);
		}

		return iAT;
	}

	public void indiceARayer(int Y, int iy, int[][] mt)
	{
		mt[Y][iy + 1] = -1;
	}

	public void setMT(int Y, int[][] mt)
	{
		int i = 0;
		while (mt[i][0] != (Integer) null)
		{
			i++;
		}
		if (mt[i][0] == (Integer) null)
		{
			mt[i][0] = Y;
		}
	}

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
				setIndiceBon(cherchePremierNull(mt[j]), j - 1, mt);
			}
		}
	}

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
				setIndiceBon(i, cherchePremierNull(mt[i]) - 1, mt);
			}
		}
	}

	public void setIndiceBon(int Y, int iy, int[][] mt)
	{
		// On "raye" la ligne d'indice j = iy
		for (int i = 0; i < mt.length; i++)
		{
			mt[i][iy] = -1;
		}

		// On "raye" la colonne d'indice i = Y
		for (int j = 1; j < (mt.length + 2); j++)
		{
			mt[Y][j] = -1;
		}

		// On ajoute l'indice bon qui se trouve au croisement de la ligne et la colonne
		// susnomées
		mt[Y][iy] = (iy - 1);
	}

	public void majMT(int[][] mt)
	{
		completeHorizontal(mt);
		completeVertical(mt);
	}
}
