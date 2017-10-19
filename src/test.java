import java.util.Scanner;

import Utilisateur.Joueur;
import Utilisateur.Ordi;

public class test
{
	private int BP, MP;

	public test()
	{
		int rejouer = -1;
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
			for (int i = 0; i < 10; i++)
			{
				System.out.print(ordi.getCombiTab(i) + " ");
			}

			// On traduit la combianison en couleurs
			// intToCouleur(ordi.getCombiTab());

			do
			{
				// Le joueur propose une réponse
				joueur.chercheMastermind(10);

				joueur.setPropositionTab(stringToInt(joueur.getPropositionString()));

				bienMalPlace(ordi.getCombiTab(), joueur.getPropositionTab());
				afficheRonds(this.getBP(), this.getMP());
				if (BP != 10)
				{
					joueur.setVie(joueur.getVie() - 1);
				}

			} while (BP != 10 && joueur.getVie() != 0);

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

	/*------------------------------------------Fonctions commmunes--------------------------------------------*/
	/*
	 * 
	 */

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

}
