package JeuDeLogique;

import java.util.Scanner;

import Configurations.ConfigRPM;
import Configurations.ConfigurationRPM;
import Utilisateur.Joueur;
import Utilisateur.Ordi;
import Utilisateur.Utilisateur;

public class RecherchePlusMoins extends JeuDeLogique
{
	/*******
	 * VARIABLES
	 *****************************************************************************************/
	ConfigurationRPM configRPM = ConfigRPM.loadConfigRPM();

	/*******
	 * FONCTIONS
	 *****************************************************************************************/

	/********************
	 * Constructeur *
	 *******************/
	public RecherchePlusMoins()
	{
		super();
		ordi = new Ordi(configRPM.getNbrCasesCombiRPM());

	}

	/*----------------------------------------Accesseurs et mutateurs------------------------------------------*/
	/****** GETTERS ******/
	/****** SETTERS ******/

	/*------------------------------------------Fonctions commmunes--------------------------------------------*/

	/*
	 * Tour du joueur
	 */
	public void tourDuJoueur(Ordi o, Joueur j)
	{
		j.cherche(configRPM.getNbrCasesCombiRPM());

		setGagneJoueur(analyseTrouve(compareTab(o.getCombiTab(), j.getPropositionTab())));	//On compare les réponses

		if (getGagneJoueur() == false)														//Si c'est faux ...
			j.setVie(j.getVie() - 1);															//...l'utilisateur perd 1 essai
	}

	/*
	 * Tour de l'ordinateur
	 */
	public void tourDeLOrdi(Ordi o, Joueur j)
	{
		System.out.println("Je cherche ...");

		o.cherche();
		o.setComparaisonTab(compareTab(j.getCombiTab(), o.getPropositionTab()));
		setGagneOrdi(analyseTrouve(o.getComparaisonTab()));
		o.analyse();
		if (getGagneOrdi() == false)
			o.setVie(o.getVie() - 1);
	}

	/**
	 * Affiche la réponse si le mode développeur est activé
	 */
	public void reponse(Utilisateur u)
	{
		super.reponse();
		System.out.print("\t\t");
		for (int i = 0; i < configRPM.getNbrCasesCombiRPM(); i++)
		{
			System.out.print(u.getCombiTab(i) + " ");
		}
		System.out.println("\n# # # # # # # # # # # # # # # # # # # # # # # # # # # # ");
	}

	/*--------------------------------------------Mode Challenger----------------------------------------------*/
	/***************************************
	 * Programme de jeu en mode Challenger *
	 ***************************************/
	public void challengerMode()
	{
		System.out.println("Bienvenue dans le Recherche +/- mode Challenger !");
		Scanner scan = new Scanner(System.in);
		//Ordi ordi = new Ordi();
		//Joueur joueur = new Joueur();

		do
		{
			//On initialise les objets joueur et ordi
			ordi.initialisation();
			joueur.initialisation();

			//L'ordi créé un nombre aléatoire
			ordi.combi(configRPM.getNbrCasesCombiRPM());

			reponse(ordi);

			do
			{
				tourDuJoueur(ordi, joueur);

			} while (!getGagneJoueur() && joueur.getVie() > 0);	//On tourne jusqu'a ce que l'utilisateur gagne ou perde toutes ses vies	

			if (getGagneJoueur() == true)
			{
				System.out.println("Bravo ! Vous avez gagné :)");
			} else if (getGagneJoueur() == false)
			{
				System.out.println("Dommage, meilleures chances la prochaine fois ! \nLa réponse était : ");
				for (int i = 0; i < configRPM.getNbrCasesCombiRPM(); i++)
				{
					System.out.print(ordi.getCombiTab(i));
				}
			}

			System.out.println("\nVoulez-vous rejouer ? \n\n\t1. oui \t\t2.non");
			setRejouer(scan.nextInt());

		} while (getRejouer() == 1);
	}

	/*--------------------------------------------Mode Defenseur-----------------------------------------------*/
	/****************************************
	 * Programme de jeu en mode Défenseur *
	 ****************************************/
	public void defenseurMode()
	{
		System.out.println("Bienvenue dans le Recherche +/- mode Défenseur !");

		//Création des objets Scanner, Ordi et Joueur dont nous auront besoin:

		Scanner scan = new Scanner(System.in);
		//Ordi ordi = new Ordi();
		//Joueur joueur = new Joueur();
		int x = 0;

		//Début de la boucle "Rejouer"
		do
		{
			//On initialialise les attributs de l'ordi et du joueur
			ordi.initialisation();
			joueur.initialisation();

			//Le joueur enre la combianison à trouver
			joueur.combi(configRPM.getNbrCasesCombiRPM());

			//C'est le tour de l'ordi	
			do
			{
				x++;
				System.out.println("\n---- Tour " + x + " ----\n");
				tourDeLOrdi(ordi, joueur);

			} while (!getGagneOrdi() && ordi.getVie() > 0);

			//Fin de la partie, on demande si le joueur veut rejouer			
			System.out.println("\nVoulez-vous rejouer ? \n\n\t1. oui \t\t2.non");
			setRejouer(scan.nextInt());

		} while (getRejouer() == 1);
	}

	/*--------------------------------------------Mode Duel----------------------------------------------*/

	/****************************************
	 * Programme de jeu en mode Duel *
	 ***************************************/
	public void duelMode()
	{
		System.out.println("Bienvenue dans le Recherche +/- mode Duel !");
		Scanner scan = new Scanner(System.in);
		//Ordi ordi = new Ordi();
		//Joueur joueur = new Joueur();

		do
		{
			ordi.initialisation();
			joueur.initialisation();

			//Le joueur entre sa combinaison :
			joueur.combi(configRPM.getNbrCasesCombiRPM());

			//L'ordi entre sa combianaison :
			ordi.combi(configRPM.getNbrCasesCombiRPM());

			//On regarde les 2 combianaisons :
			reponse(ordi);

			System.out.println("\n\n");
			do
			{
				//Le joueur commence
				System.out.println("*************Tour du joueur*********************");
				tourDuJoueur(ordi, joueur);

				//Tour de l'ordinateur:
				System.out.println("*************Tour de l'ordi*********************");
				tourDeLOrdi(ordi, joueur);

			} while (!getGagneOrdi() && !getGagneJoueur());

			if (getGagneJoueur() == false && getGagneOrdi() == true)
			{
				System.out.println("L'ordi a gagné !");
			} else if (getGagneJoueur() == true && getGagneOrdi() == false)
			{
				System.out.println("Vous avez gagné !");
			} else if (getGagneJoueur() == true && getGagneOrdi() == true)
			{
				System.out.println("Egalité : vous et l'ordinateur avez gagné !");
			}

			//Fin de la partie, on demande si le joueur veut rejouer			
			System.out.println("\nVoulez-vous rejouer ? \n\n\t1. oui \t\t2.non");
			setRejouer(scan.nextInt());

		} while (getRejouer() == 1);
	}
}
