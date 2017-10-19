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
	private int BP, MP;
	private int[] compteTableCombi = new int[10];
	private int[] compteTableProposition = new int[10];
	private int[][] masterTable = new int[10][11];

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

	public void initBPMP()
	{
		setBP(0);
		setMP(0);
	}

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

	public void remplissageMasterTable(Joueur j, Ordi o)
	{
		for (int i = 0; i < 10; i++)
		{
			for (int k = 0; k < 10; k++)
			{
				this.masterTable[i][k] = -1;
				if (BP > 0 || MP > 0)
				{
					this.masterTable[0][0] = j.getPropositionTab(0);
				}
			}
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
