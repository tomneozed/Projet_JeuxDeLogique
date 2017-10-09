package JeuDeLogique;
import java.util.Scanner;

import Utilisateur.Joueur;
import Utilisateur.Ordi;

public class RecherchePlusMoins extends JeuDeLogique
{
	/******* VARIABLES *****************************************************************************************/
	
	/******* FONCTIONS *****************************************************************************************/
	
	/********************
	 * Constructeur 	*
	 *******************/
	public RecherchePlusMoins()
	{
		super();
	}
	
	/*----------------------------------------Accesseurs et mutateurs------------------------------------------*/
	/******GETTERS******/
	/******SETTERS******/
	
	/*------------------------------------------Fonctions commmunes--------------------------------------------*/	
	
	public void tourDeLOrdi(Ordi o, Joueur j)
	{
		do
		{				
			if(getGagneOrdi() == false)
			{
				System.out.println("Je cherche ...");		
				if(o.getVie() == 3)
				{
					o.setPropositionTab(o.decoupageAleatoire());			// 1ere proposition de l'ordinateur (aleatoire)
					System.out.println("1ere proposition : " + o.getPropositionTab(0)
															 + o.getPropositionTab(1)
															 + o.getPropositionTab(2)
															 + o.getPropositionTab(3));
				}else
				{
					o.cherche();												//Autres propositions de l'ordi
				}
				o.setComparaisonTab(compareTab(j.getCombiTab(), o.getPropositionTab()));
				setGagneOrdi(analyseTrouve(o.getComparaisonTab()));
				o.setVie(o.getVie() - 1);;
			}
		}while(!getGagneOrdi() && o.getVie() > 0);
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
		
		ordi.initialisation();
		joueur.initialisation();
		
		do
		{
			ordi.combi();	//L'ordi créé un nombre aléatoire
			/*
			System.out.println("Combinaison créée par l'ordi : ");
			System.out.println(ordi.getCombiTab(0)
						+ " " +ordi.getCombiTab(1)
						+ " " +ordi.getCombiTab(2)
						+ " " +ordi.getCombiTab(3));
			*/
			
			do
			{		
				System.out.println("Entrez une combinaison de 4 chiffres : ");	//On demande à l'utilisateur une combi
				
				joueur.cherche();
				/*
				System.out.println("Combinaison proposée par l'utilisateur : ");
				System.out.println(joueur.getPropositionTab(0)
							+ " " +joueur.getPropositionTab(1)
							+ " " +joueur.getPropositionTab(2)
							+ " " +joueur.getPropositionTab(3));
				*/
				
					
				setGagneJoueur(analyseTrouve(compareTab(ordi.getCombiTab(), joueur.getPropositionTab())));	//On compare les réponses
				
				if(getGagneJoueur() == false)										//Si c'est faux ...
					joueur.setVie(joueur.getVie()-1);						//...l'utilisateur perd 1 essai
																	
			}while(!getGagneJoueur() && joueur.getVie() > 0);	//On tourne jusqu'a ce que l'utilisateur gagne ou perde toutes ses vies	
			
			if(getGagneJoueur() == true)
			{
				System.out.println("Bravo ! Vous avez gagné :)");
			}else if(getGagneJoueur() == false)
			{
				System.out.println("Dommage, meilleures chances la prochaine fois ! \nLa réponse était : " 
						+ ordi.getCombiTab(0)
						+ ordi.getCombiTab(1)
						+ ordi.getCombiTab(2)
						+ ordi.getCombiTab(3));
			}
			
			System.out.println("\nVoulez-vous rejouer ? \n\n\t1. oui \t\t2.non");
			setRejouer(scan.nextInt());	
			
		}while(getRejouer() == 1);
	}	
	
	/*--------------------------------------------Mode Defenseur-----------------------------------------------*/
	/****************************************
	 * Programme de jeu en mode Défenseur	*
	 ****************************************/		
	public void defenseurMode()
	{
		System.out.println("Bienvenue dans le Recherche +/- mode Défenseur !");
		
		//Création des objets Scanner, Ordi et Joueur dont nous auront besoin:
		
		Scanner scan = new Scanner(System.in);
		Ordi ordi = new Ordi();				
		Joueur joueur = new Joueur();
		
		
		//Début de la boucle "Rejouer"
		do
		{
			//On initialialise les attributs de l'ordi et du joueur
			ordi.initialisation();	
			joueur.initialisation();
			
			//Le joueur enre la combianison à trouver
			joueur.combi();
			
			//On initialise gagneOrdi à false
			setGagneOrdi(false);
			
			//C'est le tour de l'ordi			
			tourDeLOrdi(ordi, joueur);
			
			//Fin de la partie, on demande si le joueur veut rejouer			
			System.out.println("\nVoulez-vous rejouer ? \n\n\t1. oui \t\t2.non");
			setRejouer(scan.nextInt());	
			
		}while(getRejouer() == 1);
	}	
	

	/*--------------------------------------------Mode Duel----------------------------------------------*/

	/****************************************
	 * Programme de jeu en mode Duel	*
	 ***************************************/		
	public void duelMode()
	{
		System.out.println("Bienvenue dans le Recherche +/- mode Duel !");
		Ordi ordi = new Ordi();
		Joueur joueur = new Joueur();
		
		ordi.initialisation();
		joueur.initialisation();
		
		do
		{
			
			//Le joueur entre sa combinaison :
			joueur.combi();
			
			
			//L'ordi entre sa combianaison :
			ordi.combi();
			
			//On regarde les 2 combianaisons :
			System.out.println("Combinaison joueur : ");
			for(int i = 0; i<4; i++)
			{
				System.out.print(joueur.getCombiTab(i));
			}
			
			System.out.println("\nCombinaison ordi : ");
			for(int i = 0; i<4; i++)
			{
				System.out.print(ordi.getCombiTab(i));
			}
			
			System.out.println("\n\n");
			
			//Le joueur commence
			joueur.cherche();
			
			setGagneJoueur(analyseTrouve(compareTab(ordi.getCombiTab(), joueur.getPropositionTab())));				
			if(getGagneJoueur() == false)										
				joueur.setVie(joueur.getVie()-1);
			
			//Tour de l'ordinateur:
			
			//!\ ERREUR CAR gagneOrdi NON INITIALISEE A FALSE /!\
			tourDeLOrdi(ordi, joueur);
			
			
		}while(!getGagneOrdi());
	}
}
