package JeuDeLogique;
import java.util.Scanner;

import Utilisateur.Joueur;
import Utilisateur.Ordi;

public class Mastermind extends JeuDeLogique
{
	/******* VARIABLES *****************************************************************************************/
	
	/******* FONCTIONS *****************************************************************************************/
	
	/********************
	 * Constructeur 	*
	 *******************/
	public Mastermind()
	{
		super();
	}
	
	/*----------------------------------------Accesseurs et mutateurs------------------------------------------*/
	/******GETTERS******/
	/******SETTERS******/
	
	/*------------------------------------------Fonctions commmunes--------------------------------------------*/	
	public void tradCouleur(int[] c)
	{
		for(int i=0; i<c.length; i++)
		{
			System.out.print("\n" + c[i] + " : ");
			switch(c[i])
			{
			case 0 :
				System.out.print("Bleu");
				break;
				
			case 1 :
				System.out.print("Rouge");
				break;
				
			case 2 :
				System.out.print("Orange");
				break;
				
			case 3 :
				System.out.print("Jaune");
				break;
				
			case 4 :
				System.out.print("Vert");
				break;
				
			case 5 :
				System.out.print("Noir");
				break;
				
			case 6 :
				System.out.print("Gris");
				break;
				
			case 7 :
				System.out.print("Violet");
				break;
				
			case 8 :
				System.out.print("Blanc");
				break;
				
			case 9 :
				System.out.print("#"); 		//vide
				break;
				
			default : 
				System.out.print("#"); 		//vide
				break;
				
			}
		}
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
			
			//L'ordi créé une combinaison aléatoire
			ordi.combi(4);
			
			//On traduit la combianison en couleurs
			tradCouleur(ordi.getCombiTab());
			
			
			
		}while(getRejouer() == 1);
	}
	
	
	
	
	
	
}
