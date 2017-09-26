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
	
	private Boolean gagne;
	
	
	/******* FONCTIONS *****************************************************************************************/
	/********************
	 * Constructeur 	*
	 *******************/
	public JeuDeLogique()
	{
		setRejouer(0);
		setMode(0);
		setGagne(null);
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
	
	public Boolean getGagne()
	{
		return this.gagne;
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
	
	public void setGagne(Boolean gg)
	{
		this.gagne = gg;
	}
	
	/*------------------------------------------Fonctions commmunes--------------------------------------------*/	
	
	
	
	
	/********************************************************
	 * Fonction d'initialisation de la variable "mode" : 	*
	 * - Challenger											*
	 * - D�fenseur											*
	 * - Duel												*						
	********************************************************/
	public void choisirMode()
	{
		Scanner scan = new Scanner(System.in);
		do
		{
			System.out.println("********** Choix du mode **********");
			System.out.println("1. Challenger");
			System.out.println("2. D�fenseur");
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
				//rpm2.defenseurMode();
				break;
			default :
				break;
					
			}
		}while(getMode() != 4);
		
	}
	
	/********************************************************************
	 * Compare les valeurs de 2 tableaux d'entiers 						*
	 * et renvoie la position de chaque �l�ment 						*
	 * du 1er tableau par rapport au 2eme 								*
	 * et compte le nombre de valeurs exactes (le jeu est gagn� � 4)	*
	 * @param combi														*
	 * @param proposition												*
	 * @return true or false											*
	 *******************************************************************/
	public String compareTab(int[] combiTab, int[] propositionTab)
	{
		setTrouve(0);
		String compa = "";
		for(int i=0; i<4; i++)
		{
			System.out.print(compare(combiTab[i], propositionTab[i]) + " ");
			compa +=  compare(combiTab[i], propositionTab[i]); 
		}
		return compa;
		
	}
	
	public boolean analyseTrouve(String comp)
	{
		for(int i=0; i < comp.length(); i++)
		{
			if(comp.charAt(i) == '=')
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
	 * en fonction du r�sultat						*
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