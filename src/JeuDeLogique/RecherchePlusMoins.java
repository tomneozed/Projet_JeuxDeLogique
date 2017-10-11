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
	
	/*
	 * 
	 */
	public void tourDuJoueur(Ordi o, Joueur j)
	{		
		j.cherche();
		/*
		System.out.println("Combinaison proposée par l'utilisateur : ");
		System.out.println(joueur.getPropositionTab(0)
					+ " " +joueur.getPropositionTab(1)
					+ " " +joueur.getPropositionTab(2)
					+ " " +joueur.getPropositionTab(3));
		*/
		
			
		setGagneJoueur(analyseTrouve(compareTab(o.getCombiTab(), j.getPropositionTab())));	//On compare les réponses
		
		if(getGagneJoueur() == false)														//Si c'est faux ...
			j.setVie(j.getVie()-1);															//...l'utilisateur perd 1 essai
	}
	
	
	/*
	 * 
	 */
	public void tourDeLOrdi(Ordi o, Joueur j)
	{
		System.out.println("Je cherche ...");		
		//if(o.getVie() == 3)
		//{
			// 1ere proposition de l'ordinateur (aleatoire)
			//o.setPropositionTab(o.decoupageAleatoire());		
			
			//System.out.println("1ere proposition : " + o.getPropositionTab(0)
													// + o.getPropositionTab(1)
													// + o.getPropositionTab(2)
													// + o.getPropositionTab(3));
		//}else
		//{
			//Autres propositions de l'ordi
			o.cherche();												
		//}
		o.setComparaisonTab(compareTab(j.getCombiTab(), o.getPropositionTab()));
		setGagneOrdi(analyseTrouve(o.getComparaisonTab()));
		o.analyse();
		if(getGagneOrdi() == false)
			o.setVie(o.getVie() - 1);
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
		
		do
		{
			//On initialise les objets joueur et ordi
			ordi.initialisation();
			joueur.initialisation();
			
			//L'ordi créé un nombre aléatoire
			ordi.combi(4);	
			/*
			System.out.println("Combinaison créée par l'ordi : ");
			System.out.println(ordi.getCombiTab(0)
						+ " " +ordi.getCombiTab(1)
						+ " " +ordi.getCombiTab(2)
						+ " " +ordi.getCombiTab(3));
			*/
			
			do
			{		
				tourDuJoueur(ordi, joueur);
																		
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
			joueur.combi(4);
			
			//C'est le tour de l'ordi	
			do
			{
				tourDeLOrdi(ordi, joueur);
				
			}while(!getGagneOrdi() && ordi.getVie() > 0);  
			
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
		Scanner scan = new Scanner(System.in);
		Ordi ordi = new Ordi();
		Joueur joueur = new Joueur();
		
		do
		{
			ordi.initialisation();
			joueur.initialisation();

			//Le joueur entre sa combinaison :
			joueur.combi(4);
			
			//L'ordi entre sa combianaison :
			ordi.combi(4);
			
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
				do
				{
					//Le joueur commence
					System.out.println("*************Tour du joueur*********************");
					tourDuJoueur(ordi,joueur);
					
					//Tour de l'ordinateur:
					System.out.println("*************Tour de l'ordi*********************");
					tourDeLOrdi(ordi, joueur);
					
				}while(!getGagneOrdi() && !getGagneJoueur());	
				
				if(getGagneJoueur() == false && getGagneOrdi() == true)
				{
					System.out.println("L'ordi a gagné !");
				}else if(getGagneJoueur() == true && getGagneOrdi() == false)
				{
					System.out.println("Vous avez gagné !");
				}else if(getGagneJoueur() == true && getGagneOrdi() == true)
				{
					System.out.println("Egalité : vous et l'ordinateur avez gagné !");
				}
				
				//Fin de la partie, on demande si le joueur veut rejouer			
				System.out.println("\nVoulez-vous rejouer ? \n\n\t1. oui \t\t2.non");
				setRejouer(scan.nextInt());				
			
		}while(getRejouer() == 1);
	}
}
