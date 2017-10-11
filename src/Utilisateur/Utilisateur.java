package Utilisateur;
import java.util.Random;
import java.util.Scanner;

public class Utilisateur 
{
	/******* VARIABLES *****************************************************************************************/
	private int vie, combi, proposition;
	
	private int combiTab[] = new int[4];
	private int propositionTab[] = new int[4];
	//private int aleatoireTab[] = new int[4];
	private int miniBorneTab[] = new int[4];
	private int maxiBorneTab[] = new int[4];
	
	private String comparaisonTab[] = new String[4];
	
	
	private boolean choixSur[] = new boolean[4];
	

	/******* FONCTIONS *****************************************************************************************/
	/********************
	 * Constructeur 	*
	 *******************/
	public Utilisateur()
	{
		initialisation();
	}
	
	/*----------------------------------------Accesseurs et mutateurs------------------------------------------*/
	/******GETTERS******/
	
	public int getCombi()
	{
		return this.combi;
	}
	
	public int getProposition()
	{
		return proposition;
	}
	
	public int getVie()
	{
		return vie;
	}
	
	public int[] getCombiTab()
	{
		return this.combiTab;
	}
	
	public int getCombiTab(int i)
	{
		return this.combiTab[i];
	}
	
	public int[] getPropositionTab()
	{
		return this.propositionTab;
	}
	
	public int getPropositionTab(int i)
	{
		return this.propositionTab[i];
	}
	
	/*public int[] getAleatoireTab()
	{
		return this.aleatoireTab;
	}
	
	public int getAleatoireTab(int i)
	{
		return this.aleatoireTab[i];
	}*/
	
	public int[] getMiniBorneTab()
	{
		return this.miniBorneTab;
	}
	
	public int getMiniBorneTab(int i)
	{
		return this.miniBorneTab[i];
	}
	
	public int[] getMaxiBorneTab()
	{
		return this.maxiBorneTab;
	}
	
	public int getMaxiBorneTab(int i)
	{
		return this.maxiBorneTab[i];
	}
	
	public String[] getComparaisonTab()
	{
		return this.comparaisonTab;
	}
	
	public String getComparaisonTab(int i)
	{
		return this.comparaisonTab[i];
	}
	
	public boolean[] getChoixSur()
	{
		return this.choixSur;
	}
	
	public boolean getChoixSur(int i)
	{
		return this.choixSur[i];
	}
	
	/******SETTERS******/
	
	public void setCombi(int comb)
	{
		this.combi = comb;
	}
	
	public void setProposition(int prop)
	{
		this.proposition = prop;
	}
	
	public void setVie(int vie)
	{
		this.vie = vie;
	}
	
	public void setCombiTab(int[] combTab)
	{
		this.combiTab = combTab;
	}
	
	public void setCombiTab(int indice, int valeur)
	{
		this.combiTab[indice] = valeur;
	}
	
	public void setPropositionTab(int[] propTab)
	{
		this.propositionTab = propTab;
	}
	
	public void setPropositionTab(int indice, int valeur)
	{
		this.propositionTab[indice] = valeur;
	}
	
	/*public void setAleatoireTab(int[] aleTab)
	{
		this.aleatoireTab = aleTab;
	}
	
	public void setAleatoireTab(int indice, int valeur)
	{
		this.aleatoireTab[indice] = valeur;
	}*/
	
	public void setMiniBorneTab(int[] miniB)
	{
		this.miniBorneTab = miniB;
	}
	
	public void setMiniBorneTab(int indice, int valeur)
	{
		this.miniBorneTab[indice] = valeur;
	}
	
	public void setMaxiBorneTab(int[] maxiB)
	{
		this.maxiBorneTab = maxiB;
	}
	
	public void setMaxiBorneTab(int indice, int valeur)
	{
		this.maxiBorneTab[indice] = valeur;
	}
	
	public void setComparaisonTab(String[] compaTab)
	{
		this.comparaisonTab = compaTab;
	}
	
	public void setComparaisonTab(int indice, String valeur)
	{
		this.comparaisonTab[indice] = valeur;
	}
	
	public void setChoixSur(boolean[] cs)
	{
		this.choixSur = cs;
	}
	
	public void setChoixSur(int indice, boolean valeur)
	{
		this.choixSur[indice] = valeur;
	}
	
	/*------------------------------------------Fonctions commmunes--------------------------------------------*/	
	/********************************
	 * Initialisation de l'objet	*
	 *******************************/
	public void initialisation()
	{
		initMaxiMiniChoixSur();
		setCombi(0);
		setProposition(0);
		setVie(3);
	}
	
	/****************************************************************************
	 * Initialise les bornes mini et maxi de la création de nombre aléatoire	*
	 * Initialise la valeur de "choixSur"										*
	 ***************************************************************************/
	public void initMaxiMiniChoixSur()
	{
		for(int i = 0; i<4 ; i++)
		{
			this.miniBorneTab[i] = 0;
			this.maxiBorneTab[i] = 9;
			this.choixSur[i] = false;
		}
	}
	
	/********************************************************
	 * Retourne un nombre aléatoire entre min et max		*
	 * @param min											*	
	 * @param max											*
	 * @param hasard										*
	 * @return nombreHasard									*
	 *******************************************************/
	public int nombreAleatoire(int min, int max, Random hasard)
	{
		if(min > max)
			throw new IllegalArgumentException("Min > Max");
		
		long range = (long)max - (long)min +1;
		long fraction = (long)(range * hasard.nextDouble());
		int nombreHasard = (int)(fraction + min);
		
		return nombreHasard;
		
	}
	
	/****************************************************************
	 * Décompose un nombre à 4 chiffres pour en remplir un tableau 	*
	 * @param combi													*
	 * @return combiTab[]											*
	 ***************************************************************/
	public static int[] decoupage(int combi)
	{
		int a, b, c, d;
		int combiTab[] = new int[4];
		
		a = combi/1000;
		b = (combi%1000)/100;
		c = (combi%1000)%100/10;
		d = (((combi%1000)%100)%10);
		
		//2System.out.println("Combi : " + combi + "\na = " + a + "\tb = " + b + "\tc = " + c + "\td = " + d);
		
		combiTab[0] = a;
		combiTab[1] = b;
		combiTab[2] = c;
		combiTab[3] = d;
		
		return combiTab;
	}
	
	/****************************************************
	 * remplit un tableau de x chiffres aléatoirement	*
	 * @param x taille du tableau
	 * @return combiTab[]								*
	 ***************************************************/
	public int[] decoupageAleatoire(int x)
	{
		int aleatoireTab[] = new int[x];
		Random hasard = new Random();
		
		for(int i=0; i<x;i++)
		{
			aleatoireTab[i] = nombreAleatoire(0, 9, hasard);
		}		
		return aleatoireTab;
	}
	
	/****************************************************
	 * remplit un tableau de 4 chiffres aléatoirement	*
	 * @param mini	borne mini							*
	 * @param maxi	borne maxi							*
	 * @return aleatoireTab[]							*
	 ***************************************************/
	public int[] decoupageAleatoire(int x, int[] mini, int[] maxi)
	{
		Random hasard = new Random();
		int aleatoireTab[] = new int[x];
		
		for(int i=0; i<x;i++)
		{
			aleatoireTab[i] = nombreAleatoire(mini[0], maxi[0], hasard);
		}		
		return aleatoireTab;
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
	public String compareTab(int[] combiTab, int[] propositionTab)
	{
		String compa = "";
		System.out.println("Comparaison : ");
		for(int i=0; i<4; i++)
		{
			System.out.print(compare(combiTab[i], propositionTab[i]) + " ");
			compa +=  compare(combiTab[i], propositionTab[i]); 
		}
		return compa;
		
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
	
	/********************************************
	 * Demande de création d'une combinaison	*
	 *******************************************/	
	public void combi(int x)
	{
		System.out.println("Création de la combinaison à trouver");
	}
	
	
	/****************************************
	 * Demande de proposition de réponse	*
	 ***************************************/
	public void cherche()
	{
		System.out.println("Proposition de réponse");
	}
}
