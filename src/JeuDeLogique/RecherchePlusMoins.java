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
		
		do
		{
			ordi.combi();	//L'ordi créé un nombre aléatoire
			
			do
			{		
				System.out.println("Entrez une combinaison de 4 chiffres : ");	//On demande à l'utilisateur une combi
				
				joueur.cherche();
				
					
				setGagne(analyseTrouve(compareTab(ordi.getCombiTab(), joueur.getPropositionTab())));	//On compare les réponses
				
				if(getGagne() == false)										//Si c'est faux ...
					joueur.setVie(-1);											//...l'utilisateur perd 1 essai
																	
			}while(!getGagne() && joueur.getVie() > 0);	//On tourne jusqu'a ce que l'utilisateur gagne ou perde toutes ses vies	
			
			if(getGagne() == true)
			{
				System.out.println("Bravo ! Vous avez gagné :)");
			}else if(getGagne() == false)
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
	 ****************************************		
	public void defenseurMode()
	{
		System.out.println("Bienvenue dans le Recherche +/- mode Défenseur !");
		
		do
		{
			initMaxiMiniChoixSur();
			joueurCombi();		
			
			do
			{
				if(getGagne() == false)
				{
					System.out.println("Je cherche ...");	
					setPropositionTab(decoupageAleatoire());			// 1er proposition de l'ordinateur (aleatoire) 					
					ordiCherche();
					this.vie --;
				}
			}while(!getGagne() && getVie() > 0);
			
			System.out.println("\nVoulez-vous rejouer ? \n\n\t1. oui \t\t2.non");
			setRejouer(scan.nextInt());	
			
		}while(getRejouer() == 1);
	}	
	

	/*--------------------------------------------Mode Duel----------------------------------------------*/

	/****************************************
	 * Programme de jeu en mode Duel	*
	 ****************************************		
	public void duelMode()
	{
		System.out.println("Bienvenue dans le Recherche +/- mode Duel !");
		
		do
		{
			initMaxiMiniChoixSur();
			
			System.out.println("Entrez une combinaison de 4 chiffres : ");	
			
			setCombi(scan.nextInt());
			setCombiTab(decoupage(this.combi));
			
			System.out.println("Je cherche ...");	
			setPropositionTab(decoupageAleatoire());			// 1er proposition de l'ordinateur (aleatoire) 
			
			do
			{
				setGagne(compareTab(this.propositionTab, this.combiTab));
				
				if(getGagne() == false)
				{
					ordiCherche();
					this.vie --;
				}
			}while(!getGagne() && getVie() > 0);
			
			System.out.println("\nVoulez-vous rejouer ? \n\n\t1. oui \t\t2.non");
			setRejouer(scan.nextInt());	
			
		}while(getRejouer() == 1);
	}
	*/
}
