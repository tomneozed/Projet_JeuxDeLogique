package JeuDeLogique;

import java.util.Scanner;

import Utilisateur.Joueur;
import Utilisateur.Ordi;

public class JeuDeLogique 
{
	/******* VARIABLES *****************************************************************************************/
	private int rejouer = 0;
	private int trouve = 0;
	private int mode = 0;
	
	private Boolean gagneOrdi, gagneJoueur;
	
	
	/******* FONCTIONS *****************************************************************************************/
	/********************
	 * Constructeur 	*
	 *******************/
	public JeuDeLogique()
	{
		setRejouer(0);
		setMode(0);
		setGagneOrdi(null);
		setGagneJoueur(null);
	}
	
	/*----------------------------------------Accesseurs et mutateurs------------------------------------------*/
	/******GETTERS******/
	public int getRejouer()
	{
		return this.rejouer;
	}
	
	public int getTrouve()
	{
		return trouve;
	}
	
	public int getMode()
	{
		return mode;
	}
	
	public Boolean getGagneOrdi()
	{
		return this.gagneOrdi;
	}
	
	public Boolean getGagneJoueur()
	{
		return this.gagneJoueur;
	}
	
	/******SETTERS******/
	public void setRejouer(int rejoue)
	{
		this.rejouer = rejoue;
	}
	
	public void setTrouve(int trouve)
	{
		this.trouve = trouve;
	}
	
	public void setMode(int mode)
	{
		this.mode = mode;
	}
	
	public void setGagneOrdi(Boolean gg)
	{
		this.gagneOrdi = gg;
	}
	
	public void setGagneJoueur(Boolean gg)
	{
		this.gagneJoueur = gg;
	}
	
	/*------------------------------------------Fonctions commmunes--------------------------------------------*/	
	/********************************************************
	 * Fonction d'initialisation de la variable "mode" : 	*
	 * - Challenger											*
	 * - Défenseur											*
	 * - Duel												*						
	********************************************************/
	public void choisirMode()
	{
		Scanner scan = new Scanner(System.in);
		do
		{
			System.out.println("\n********** Choix du mode **********");
			System.out.println("1. Challenger");
			System.out.println("2. Défenseur");
			System.out.println("3. Duel");
			System.out.println("4. Quitter");
			
			setMode(scan.nextInt());
			
			switch(this.mode)
			{
			case 1:
				RecherchePlusMoins rpm = new RecherchePlusMoins();
				rpm.challengerMode();
				break;
				
			case 2 :
				RecherchePlusMoins rpm2 = new RecherchePlusMoins();
				rpm2.defenseurMode();
				break;
				
			case 3 :
				RecherchePlusMoins rpm3 = new RecherchePlusMoins();
				rpm3.duelMode();
				break;
			default :
				break;
					
			}
		}while(getMode() != 4);
		
	}
	
	/********************************************************************
	 * Compare les valeurs de 2 tableaux d'entiers 						*
	 * et renvoie la position de chaque élément 						*
	 * du 1er tableau par rapport au 2eme 								*
	 * et compte le nombre de valeurs exactes (le jeu est gagné à 4)	*
	 * @param combi														*
	 * @param proposition												*
	 * @return true or false											*
	 *******************************************************************/
	public String[] compareTab(int[] tab1, int[] tab2)
	{
		setTrouve(0);
		String[] compa = new String[4];
		for(int i=0; i<4; i++)
		{
			System.out.print(compare(tab1[i], tab2[i]) + " ");
			compa[i]=  compare(tab1[i], tab2[i]); 
		}
		return compa;
		
	}
	
	/**
	 * Analyse si le résultat est correct dans son ensemble (= = = =)
	 * @param comp
	 * @return true or false
	 */
	public boolean analyseTrouve(String[] comp)
	{
		for(int i=0; i < 4; i++)
		{
			if(comp[i] == "=")
			{
				setTrouve(getTrouve() + 1);
			}
		}
		System.out.println("");
		if(getTrouve() == 4)
		{
			return true;
		}else
			return false;
	}
	
	/************************************************
	 * Compare 2 entiers et renvoie "+", "-", "=" 	*
	 * en fonction du résultat						*
	 * @param a 									*								
	 * @param b										*
	 * @return comparaison							*
	 ***********************************************/
	public String compare(int a, int b)
	{
		String comparaison="";
		if(a > b)
			comparaison = "+";
		else if(a < b)
			comparaison = "-";
		else if(a == b)
			comparaison = "=";
		
		return comparaison;
	}
}
