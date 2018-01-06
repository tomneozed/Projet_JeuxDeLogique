package Utilisateur;

import java.util.Random;

import Tables.Propo;

public class Utilisateur
{
	/*******
	 * VARIABLES
	 *****************************************************************************************/
	//Attributs des properties
	private Integer vie;
	private String propositionString;

	protected Integer combiTab[];
	protected Integer propositionTab[];

	protected Propo propoTab;

	protected Integer miniBorneTab[];
	protected Integer maxiBorneTab[];

	protected String comparaisonTab[];

	protected boolean choixSur[];

	/*******
	 * FONCTIONS
	 *****************************************************************************************/
	/********************
	 * Constructeur *
	 *******************/
	public Utilisateur()
	{
		//initialisation();
	}

	/*----------------------------------------Accesseurs et mutateurs------------------------------------------*/
	/****** GETTERS ******/

	public Integer[] getPropositionTab()
	{
		return propositionTab;
	}

	public Integer getPropositionTab(Integer i)
	{
		return this.combiTab[i];
	}

	public String getPropositionString()
	{
		return propositionString;
	}

	public Integer getVie()
	{
		return vie;
	}

	public Integer[] getCombiTab()
	{
		return this.combiTab;
	}

	public Integer getCombiTab(Integer i)
	{
		return this.combiTab[i];
	}

	public Integer[] getMiniBorneTab()
	{
		return this.miniBorneTab;
	}

	public Integer getMiniBorneTab(Integer i)
	{
		return this.miniBorneTab[i];
	}

	public Integer[] getMaxiBorneTab()
	{
		return this.maxiBorneTab;
	}

	public Integer getMaxiBorneTab(Integer i)
	{
		return this.maxiBorneTab[i];
	}

	public String[] getComparaisonTab()
	{
		return this.comparaisonTab;
	}

	public String getComparaisonTab(Integer i)
	{
		return this.comparaisonTab[i];
	}

	public boolean[] getChoixSur()
	{
		return this.choixSur;
	}

	public boolean getChoixSur(Integer i)
	{
		return this.choixSur[i];
	}

	public Propo getPropoTab()
	{
		return propoTab;
	}

	/****** SETTERS ******/

	public void setPropositionTab(Integer[] propositionTab)
	{
		this.propositionTab = propositionTab;
	}

	public void setPropositionTab(Integer indice, Integer valeur)
	{
		this.propositionTab[indice] = valeur;
	}

	public void setPropositionString(String prop)
	{
		this.propositionString = prop;
	}

	public void setVie(Integer vie)
	{
		this.vie = vie;
	}

	public void setCombiTab(Integer[] combTab)
	{
		this.combiTab = combTab;
	}

	public void setCombiTab(Integer indice, Integer valeur)
	{
		this.combiTab[indice] = valeur;
	}

	public void setMiniBorneTab(Integer[] miniB)
	{
		this.miniBorneTab = miniB;
	}

	public void setMiniBorneTab(Integer indice, Integer valeur)
	{
		this.miniBorneTab[indice] = valeur;
	}

	public void setMaxiBorneTab(Integer[] maxiB)
	{
		this.maxiBorneTab = maxiB;
	}

	public void setMaxiBorneTab(Integer indice, Integer valeur)
	{
		this.maxiBorneTab[indice] = valeur;
	}

	public void setComparaisonTab(String[] compaTab)
	{
		this.comparaisonTab = compaTab;
	}

	public void setComparaisonTab(Integer indice, String valeur)
	{
		this.comparaisonTab[indice] = valeur;
	}

	public void setChoixSur(boolean[] cs)
	{
		this.choixSur = cs;
	}

	public void setChoixSur(Integer indice, boolean valeur)
	{
		this.choixSur[indice] = valeur;
	}

	public void setPropoTab(Propo propoTab)
	{
		this.propoTab = propoTab;
	}

	/*------------------------------------------Fonctions commmunes--------------------------------------------*/
	/********************************
	 * Initialisation de l'objet *
	 *******************************/
	public void initialisation(Integer i)
	{
		setMiniBorneTab(new Integer[i]);
		setMaxiBorneTab(new Integer[i]);
		setChoixSur(new boolean[i]);
		initMaxiMiniChoixSur(i);

		setCombiTab(new Integer[i]);
		setPropositionTab(new Integer[i]);
	}

	/****************************************************************************
	 * Initialise les bornes mini et maxi de la création de nombre aléatoire *
	 * Initialise la valeur de "choixSur" *
	 ***************************************************************************/
	public void initMaxiMiniChoixSur(Integer j)
	{
		for (int i = 0; i < j; i++)
		{
			this.miniBorneTab[i] = 0;
			this.maxiBorneTab[i] = 9;
			this.choixSur[i] = false;
		}
	}

	/********************************************************
	 * Retourne un nombre aléatoire entre min et max *
	 * 
	 * @param min
	 *            *
	 * @param max
	 *            *
	 * @param hasard
	 *            *
	 * @return nombreHasard *
	 *******************************************************/
	public Integer nombreAleatoire(Integer min, Integer max, Random hasard)
	{
		if (min > max)
			throw new IllegalArgumentException("Min > Max");

		long range = (long) max - (long) min + 1;
		long fraction = (long) (range * hasard.nextDouble());
		Integer nombreHasard = (int) (fraction + min);

		return nombreHasard;

	}

	/****************************************************
	 * remplit un tableau de x chiffres aléatoirement *
	 * 
	 * @param x
	 *            taille du tableau
	 * @return combiTab[] *
	 ***************************************************/
	public Integer[] decoupageAleatoire(Integer x)
	{
		Integer aleatoireTab[] = new Integer[x];
		Random hasard = new Random();

		for (int i = 0; i < x; i++)
		{
			aleatoireTab[i] = nombreAleatoire(0, 9, hasard);
		}
		return aleatoireTab;
	}

	/****************************************************
	 * remplit un tableau de 4 chiffres aléatoirement *
	 * 
	 * @param mini
	 *            borne mini *
	 * @param maxi
	 *            borne maxi *
	 * @return aleatoireTab[] *
	 ***************************************************/
	public Integer[] decoupageAleatoire(Integer x, Integer[] mini, Integer[] maxi)
	{
		Random hasard = new Random();
		Integer aleatoireTab[] = new Integer[x];

		for (int i = 0; i < x; i++)
		{
			aleatoireTab[i] = nombreAleatoire(mini[0], maxi[0], hasard);
		}
		return aleatoireTab;
	}

	/********************************************************************
	 * Compare les valeurs de 2 tableaux d'entiers * et renvoie la position de
	 * chaque élément * du 1er tableau par rapport au 2eme * et compte le nombre de
	 * valeurs exactes (le jeu est gagné à 4) *
	 * 
	 * @param combi
	 *            *
	 * @param proposition
	 *            *
	 * @return true or false *
	 *******************************************************************/
	public String compareTab(Integer[] combiTab, Integer[] propositionTab)
	{
		String compa = "";
		System.out.println("Comparaison : ");
		for (Integer i = 0; i < 4; i++)
		{
			System.out.print(compare(combiTab[i], propositionTab[i]) + " ");
			compa += compare(combiTab[i], propositionTab[i]);
		}
		return compa;

	}

	/************************************************
	 * Compare 2 entiers et renvoie "+", "-", "=" * en fonction du résultat *
	 * 
	 * @param a
	 *            *
	 * @param b
	 *            *
	 * @return comparaison *
	 ***********************************************/
	public String compare(Integer a, Integer b)
	{
		String comparaison = "";
		if (a > b)
			comparaison = "+";
		else if (a < b)
			comparaison = "-";
		else if (a == b)
			comparaison = "=";

		return comparaison;
	}

	/********************************************
	 * Demande de création d'une combinaison *
	 *******************************************/
	public void combi(Integer x)
	{
		System.out.println("Création de la combinaison à trouver");
	}

	/****************************************
	 * Demande de proposition de réponse *
	 ***************************************/
	public void cherche(Integer x)
	{
		System.out.println("Proposition de réponse");
	}
}
