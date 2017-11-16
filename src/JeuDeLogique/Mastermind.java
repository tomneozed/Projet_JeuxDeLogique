package JeuDeLogique;

import java.util.Scanner;

import Utilisateur.Joueur;
import Utilisateur.Ordi;

/**
 * @author Thomas Pelissier
 *
 */
public class Mastermind extends JeuDeLogique
{
	/*******
	 * VARIABLES
	 *****************************************************************************************/
	private int BP, MP, longueurMT = 11, largeurMT = 10;
	// private int[] compteTableCombi = new int[largeurMT];
	// private int[] compteTableProposition = new int[largeurMT];
	private int[][] masterTable = new int[largeurMT][longueurMT];
	private int[] indiceTab = new int[10];
	Boolean colonneTerminee[] = new Boolean[largeurMT];
	Boolean indiceBon[] = new Boolean[largeurMT];

	/*******
	 * FONCTIONS
	 *****************************************************************************************/

	/********************
	 * Constructeur *
	 *******************/
	public Mastermind()
	{
		super();
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

	/*------------------------------------------Fonctions commmunes--------------------------------------------*/
	/*
	 * 
	 */

	public void initialisation()
	{
		initBPMP();
		initMasterTable();
		initIndiceTab();
		initIndiceBon();
		initColonneTerminee();
	}

	public void initBPMP()
	{
		setBP(0);
		setMP(0);
	}

	/**
	 * Fonction qui initialise la masterTable à null
	 */
	@SuppressWarnings("null")
	public void initMasterTable()
	{
		for (int i = 0; i < this.largeurMT; i++)
		{
			for (int j = 0; j < this.longueurMT; j++)
			{
				this.masterTable[i][j] = (Integer) null;
			}
		}
	}

	/**
	 * Fonction qui initialise indiceTab à null
	 */
	@SuppressWarnings("null")
	public void initIndiceTab()
	{
		for (int i = 0; i < this.indiceTab.length; i++)
		{
			this.indiceTab[i] = (Integer) null;
		}
	}

	/**
	 * Fonction qui initialise indiceBon à null
	 */
	public void initIndiceBon()
	{
		for (int i = 0; i < this.indiceBon.length; i++)
		{
			this.indiceBon[i] = false;
		}
	}

	/**
	 * Fonction qui initialise colonneTerminee à null
	 */
	public void initColonneTerminee()
	{
		for (int i = 0; i < this.colonneTerminee.length; i++)
		{
			this.colonneTerminee[i] = false;
		}
	}

	/**
	 * 
	 * @param c
	 */

	public void intToCouleur(int[] c)
	{
		for (int i = 0; i < c.length; i++)
		{
			System.out.print("\n" + c[i] + " : ");
			switch (c[i])
			{
			case 0:
				System.out.print("Bleu");
				break;

			case 1:
				System.out.print("Rouge");
				break;

			case 2:
				System.out.print("Orange");
				break;

			case 3:
				System.out.print("Jaune");
				break;

			case 4:
				System.out.print("Vert");
				break;

			case 5:
				System.out.print("Noir");
				break;

			case 6:
				System.out.print("Gris");
				break;

			case 7:
				System.out.print("Violet");
				break;

			case 8:
				System.out.print("Blanc");
				break;

			case 9:
				System.out.print("#"); // vide
				break;

			default:
				System.out.print("#"); // vide
				break;

			}
		}
	}

	/**
	 * Renvoie le nombre de fois x contenue dans tab
	 */
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

	public int[] remplirCompteTable(int[] tableACompter)
	{
		int[] cTable = new int[10];
		for (int i = 0; i < 10; i++)
		{
			cTable[i] = compteCombien(i, tableACompter);
		}
		return cTable;
	}

	/**
	 * Renvoie un tableau contenant les indices des positions de x dans tab
	 */
	public int[] getPos(int x, int[] tab)
	{
		int[] posTab = new int[compteCombien(x, tab)];
		int j = 0;
		for (int i = 0; i < tab.length; i++)
		{
			if (tab[i] == x)
			{
				posTab[j] = i;
				j++;
			}
		}
		return posTab;
	}

	/**
	 * Indique le nombre de ronds noirs(bien placé) et de ronds blancs(mal placés)
	 * 
	 * @param combinaison
	 *            la combinaison à trouver
	 * 
	 * @param proposition
	 *            une proposition de réponse
	 */
	public void bienMalPlace(int[] combinaison, int[] proposition)
	{
		initBPMP();
		int i = 0, j = 0;
		int r = 0;
		while (i < 10)
		{
			r = 0;
			j = 0;
			while (r == 0 && j < 10)
			{
				if (combinaison[i] == proposition[j])
				{
					if (combinaison[i] == proposition[i])
					{
						// System.out.println("Rond NOIR");
						this.BP++;
					} else
					{
						// System.out.println("Rond BLANC");
						this.MP++;

						/*
						 * System.out.println("prop[] : "); for (int x = 0; x < 10; x++) {
						 * System.out.print(proposition[x] + " "); }
						 */
						// System.out.println("\n");
					}
					proposition[j] = -1;
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
	public int[] propoX(int x)
	{
		int[] propo = new int[this.largeurMT];

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
	public int[] propoXY(int x, int y, int iY)
	{
		int[] propo = new int[this.largeurMT];

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

	/**
	 * Renvoie un tableau de 2x2 : X | nbre de X (9) Y | nbre de Y (1)
	 * 
	 * @param propo
	 * @return XouXY
	 */
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
				XouXY[0][1] = y; // valeur du Y
				XouXY[1][1] = 1; // nombre de Y (1)
			} else if (idk > 1)
			{
				x = propo[i];
				XouXY[0][0] = x; // valeur du X
				XouXY[1][0] = idk; // nombre de X (9)
			}
		}

		System.out.println("X : " + XouXY[0][0] + " apparait " + XouXY[1][0] + " fois" + "\nY : " + XouXY[0][1]
				+ " apparait " + XouXY[1][1] + " fois");

		return XouXY;
	}

	/**
	 * Renvoie le premier indice pour lequel la valeur est (Integer)"null" dans un
	 * tableau indiceTab
	 * 
	 * @param tab
	 *            tableau dans lequel on cherche
	 * @return x
	 */
	public int cherchePremierNull(int[] tab)
	{
		int x = 0;

		while (tab[x] != (Integer) null)
		{
			x++;
		}
		return x;
	}

	/**
	 * Renvoie true si la proposition ne contient pas de null et false dans l'autre
	 * cas
	 * 
	 * @param propo
	 * @return pt
	 */
	public Boolean propoTerminee(int[] propo)
	{
		Boolean pt = null;
		for (int i = 0; i < propo.length; i++)
		{
			if (propo[i] == (Integer) null)
			{
				pt = false;
			} else
			{
				pt = true;
			}
		}
		return pt;
	}

	public int[] indiceColonneYMasterTable(int y)
	{
		int combien = 0;

		for (int j = 0; j < this.largeurMT; j++)
		{
			if (y == this.masterTable[j][0])
			{
				combien++;
			}
		}

		int[] posTab = new int[combien];
		int j = 0;
		for (int i = 0; i < this.largeurMT; i++)
		{
			if (this.masterTable[i][0] == y)
			{
				posTab[j] = i;
				j++;
			}
		}
		return posTab;
	}

	/**
	 * Traite la masterTable et l'indiceTab pour créer une proposition
	 * 
	 * @return propo
	 */
	public int[] creerProposition()
	{
		int[] propo = new int[this.largeurMT];
		int cpt = 0;
		Boolean propoTermine = null;
		propoX((Integer) null);

		do
		{
			if (this.masterTable[cpt][0] == (Integer) null)
			{
				int x = cherchePremierNull(this.indiceTab);
				propo = propoX(x);
				cpt = this.indiceTab.length;
			} else if (this.masterTable[cpt][0] >= 0 && this.masterTable[cpt][0] <= 9)
			{
				if (colonneTerminee[cpt] == false)
				{
					int x = cherchePremierNull(this.indiceTab);
					int y = this.masterTable[cpt][0];
					int iY = cherchePremierNull(this.masterTable[cpt]);
					propo = propoXY(x, y, iY);
				} else if (colonneTerminee[cpt] == true)
				{
					cpt++;
				}
			}
			propoTermine = propoTerminee(propo);

		} while (cpt < this.indiceTab.length && propoTermine == false);

		return propo;
	}

	public void remplissageMasterTable(int[] propo)
	{
		int XouXY = -1, i = 0, j = 0;
		int[][] XouXYTab = propoXouXY(propo);

		// Cette condition permet de savoir si la propo est en X ou en XY
		if (XouXYTab[0][1] == -1)
		{
			// PropoX
			XouXY = 1;

		} else
		{
			// PropoXY
			XouXY = 2;
		}

		// Remplissage de la premiere ligne
		if (XouXY == 1 && BP > 0)
		{
			while (i < BP)
			{
				if (this.masterTable[j][0] == (Integer) null)
				{
					this.masterTable[j][0] = propo[0];
					i++;
					j++;
				} else
				{
					j++;
				}
			}
		} else if (XouXY == 2 && BP == 1 && MP == 0)
		{
			int[] indiceYPropo = getPos(XouXYTab[0][1], propo); // pos[0] = indice de Y (BON)
			int indiceYMasterTable = -1;
			for (int cpt = 0; cpt < this.largeurMT; cpt++)
			{
				if (this.masterTable[cpt][0] == XouXYTab[0][1])
				{
					indiceYMasterTable = cpt;
				}
			}
			int cpt2 = 0;
			while (this.masterTable[cpt2][0] != (Integer) null)
			{
				cpt2++;
			}
			indiceYMasterTable = cpt2;

		}

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

	/*--------------------------------------------Mode Challenger----------------------------------------------*/
	/***************************************
	 * Programme de jeu en mode Challenger *
	 ***************************************/
	public void challengerMode()
	{
		System.out.println("Bienvenue dans le Recherche +/- mode Challenger !");
		Scanner scan = new Scanner(System.in);
		Ordi ordi = new Ordi();
		Joueur joueur = new Joueur();
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
			ordi.setCombiTab(c);
			System.out.println("\n");

			// On traduit la combianison en couleurs
			intToCouleur(ordi.getCombiTab());

			do
			{
				// Le joueur propose une réponse
				joueur.chercheMastermind(10);

				joueur.setPropositionTab(stringToInt(joueur.getPropositionString()));

				bienMalPlace(ordi.getCombiTab(), joueur.getPropositionTab());
				System.out.println("BP : " + getBP());
				System.out.println("MP : " + getMP());
				if (BP != 10)
				{
					joueur.setVie(joueur.getVie() - 1);
				}

			} while (BP != 10 && joueur.getVie() != 0);

			System.out.println("\nVoulez-vous rejouer ? \n\n\t1. oui \t\t2.non");
			setRejouer(scan.nextInt());

		} while (getRejouer() == 1);
	}

	/*--------------------------------------------Mode Defenseur----------------------------------------------*/
	/***************************************
	 * Programme de jeu en mode Defenseur *
	 ***************************************/
	// public void defenseurMode()
	// {
	//
	// }

	/*
	 * System.out.println(this.hashCombi); System.out.println(this.hashProposition);
	 * 
	 * remplissageMasterTable(joueur, ordi);
	 * 
	 * for (int i = 0; i < 10; i++) { for (int j = 0; j < 10; j++) {
	 * System.out.print(this.masterTable[i][j]); } System.out.println(""); }
	 */

}
