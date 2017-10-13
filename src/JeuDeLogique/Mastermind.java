package JeuDeLogique;
import java.util.Scanner;

import Utilisateur.Joueur;
import Utilisateur.Ordi;

public class Mastermind extends JeuDeLogique
{
	/******* VARIABLES *****************************************************************************************/
	private int BP, MP;
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
	public int getBP()
	{
		return this.BP;
	}
	
	public int getMP()
	{
		return this.MP;
	}
	
	
	/******SETTERS******/
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
	public void intToCouleur(int[] c)
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
	
	

	
	/*
	 * Renvoie le nombre de fois x contenue dans tab
	 * 
	 */
	public int compteCombien(int x, int[] tab)
	{
		int nombre = 0;
		
		for(int i = 0; i < tab.length; i++)
		{
			if(x == tab[i])
			{
				nombre++;
			}
		}		
		return nombre;
	}
	
	/*
	 * Renvoie un tableau contenant les indices des positions de x dans tab
	 */
	public int[] getPos(int x, int[] tab)
	{
		int[] posTab = new int[compteCombien(x, tab)];
		int j = 0;
		for(int i = 0; i< tab.length; i++)
		{
			if(tab[i] == x)
			{
				posTab[j] = i;
				j++;
			}
		}
		return posTab;
	}
	
	
	
	public void bienMalPlace(int[] pos1, int[] pos2)
	{
		
		for(int j = 0; j < pos1.length; j++)
		{
			int cpt =0;
			for(int k = 0; k < pos2.length; k++)
			{
				if(pos1[j] == pos2[k])
				{
					this.BP++;
					System.out.println(pos1[j] + " est la bonne position ! ");
					break;
				}else if(pos1[j] != pos2[k])
				{
					cpt++;
				}
					
			}
			if(cpt == pos2.length)
			{
				this.MP++;
			}
				
		}
	}
	
	
	public void analyse(Joueur j, Ordi o)
	{
		for(int i = 0; i < j.getPropositionTab().length; i++)
		{
			int[] tabPos = getPos(j.getPropositionTab(i), o.getCombiTab());
			int[] tabPos2 = getPos(j.getPropositionTab(i), j.getPropositionTab());
			bienMalPlace(tabPos, tabPos2);
		}
		
	}
	
	
	
	
	
	
	public int[] stringToInt(String s)
	{
		int[] tab = new int[s.length()];
		for(int i = 0; i < s.length(); i++)
		{
			switch(s.substring(i, i+1))
			{
			case "0" : 
				tab[i] = 0;
				break;
				
			case "1" : 
				tab[i] = 1;
				break;
				
			case "2" : 
				tab[i] = 2;
				break;
				
			case "3" : 
				tab[i] = 3;
				break;
				
			case "4" : 
				tab[i] = 4;
				break;
				
			case "5" : 
				tab[i] = 5;
				break;
				
			case "6" : 
				tab[i] = 6;
				break;
				
			case "7" : 
				tab[i] = 7;
				break;
				
			case "8" : 
				tab[i] = 8;
				break;
				
			case "9" : 
				tab[i] = 9;
				break;
			
			default :
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
		int[] c = {3,0,1,5,2,6,4,7,9,0};
		
		do
		{
			//On initialise les objets joueur et ordi
			ordi.initialisation();
			joueur.initialisation();
			
			//L'ordi créé une combinaison aléatoire
			//ordi.combi(4);
			ordi.setCombiTab(c);
			
			//On traduit la combianison en couleurs
			intToCouleur(ordi.getCombiTab());
			
			//Le joueur propose une réponse
			joueur.chercheMastermind();
			
			joueur.setPropositionTab(stringToInt(joueur.getPropositionString()));
			
			//compareCouleurs(ordi.getCombiTab(), joueur.getPropositionTab());
			
			
			//System.out.println("Nombre de fois " + joueur.getPropositionTab(0) + " dans la combinaison de l'ordi : " + tabPos.length );
			//System.out.println("Nombre de fois " + joueur.getPropositionTab(0) + " dans la combinaison du joueur : " + tabPos2.length );
			analyse(joueur, ordi);
			System.out.println("Nombre de couleurs mal placées : " + this.MP);
			System.out.println("Nombre de couleurs bien placées : " + this.BP);
			
			
			
		}while(getRejouer() == 1);
	}
	
	
	
	
	
	
}
