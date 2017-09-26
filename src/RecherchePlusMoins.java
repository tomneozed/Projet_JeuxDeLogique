import java.util.Random;
import java.util.Scanner;

public class RecherchePlusMoins 
{
	/******* VARIABLES *****************************************************************************************/
	private int combiJoueur,combiOrdi, propositionJoueur, propositionOrdi, vie = 2, trouve, mode;
	private int combiOrdiTab[] = new int[4];
	private int combiJoueurTab[] = new int[4];
	private int propositionOrdiTab[] = new int[4];
	private int propositionJoueurTab[] = new int[4];
	private int aleatoireTab[] = new int[4];
	private int miniBorneTab[] = new int[4];
	private int maxiBorneTab[] = new int[4];
	
	private int rejouer=0;
	
	private String comparaisonTab[] = new String[4];
	
	private Boolean gagne;
	
	private boolean choixSur[] = new boolean[4];
	
	Scanner scan = new Scanner(System.in);
	/******* FONCTIONS *****************************************************************************************/
	
	/********************
	 * Constructeur 	*
	 *******************/
	public RecherchePlusMoins()
	{
		initialisation();
	}
	
	
	/*----------------------------------------Accesseurs et mutateurs------------------------------------------*/
	/******GETTERS******/
	
	public int getCombiJoueur()
	{
		return this.combiJoueur;
	}
	
	public int getCombiOrdi()
	{
		return this.combiOrdi;
	}
	
	public int getPropositionJoueur()
	{
		return propositionJoueur;
	}
	
	public int getPropositionOrdi()
	{
		return propositionOrdi;
	}
	
	public int getVie()
	{
		return vie;
	}
	
	public int getTrouve()
	{
		return trouve;
	}
	
	public int getMode()
	{
		return mode;
	}
	
	public int[] getCombiJoueurTab()
	{
		return this.combiJoueurTab;
	}
	
	public int getCombiJoueurTab(int i)
	{
		return this.combiJoueurTab[i];
	}
	
	public int[] getPropositionJoueurTab()
	{
		return this.propositionJoueurTab;
	}
	
	public int getPropositionJoueurTab(int i)
	{
		return this.propositionJoueurTab[i];
	}
	
	public int[] getCombiOrdiTab()
	{
		return this.combiOrdiTab;
	}
	
	public int getCombiOrdiTab(int i)
	{
		return this.combiOrdiTab[i];
	}
	
	public int[] getPropositionOrdiTab()
	{
		return this.propositionOrdiTab;
	}
	
	public int getPropositionOrdiTab(int i)
	{
		return this.propositionOrdiTab[i];
	}
	
	public int[] getAleatoireTab()
	{
		return this.aleatoireTab;
	}
	
	public int getAleatoireTab(int i)
	{
		return this.aleatoireTab[i];
	}
	
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
	
	public int getRejouer()
	{
		return this.rejouer;
	}
	
	public String[] getComparaisonTab()
	{
		return this.comparaisonTab;
	}
	
	public String getComparaisonTab(int i)
	{
		return this.comparaisonTab[i];
	}
	
	public boolean getGagne()
	{
		return this.gagne;
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
	
	public void setCombiOrdi(int comb)
	{
		this.combiOrdi = comb;
	}
	
	public void setCombiJoueur(int comb)
	{
		this.combiJoueur = comb;
	}
	
	public void initCombiOrdi()
	{
		this.combiOrdi = 0;
	}
	
	public void initCombiJoueur()
	{
		this.combiOrdi = 0;
	}
	
	public void setPropositionOrdi(int prop)
	{
		this.propositionOrdi = prop;
	}
	
	public void setPropositionJoueur(int prop)
	{
		this.propositionJoueur = prop;
	}
	
	public void initPropositionJoueur()
	{
		this.propositionJoueur = 0;
	}
	
	public void initPropositionOrdi()
	{
		this.propositionOrdi = 0;
	}
	
	public void setVie(int vie)
	{
		this.vie = vie;
	}
	
	public void setTrouve(int trouve)
	{
		this.trouve = trouve;
	}
	
	public void setMode(int mode)
	{
		this.mode = mode;
	}
	
	public void setCombiJoueurTab(int[] combTab)
	{
		this.combiJoueurTab = combTab;
	}
	
	public void setCombiJoueurTab(int indice, int valeur)
	{
		this.combiJoueurTab[indice] = valeur;
	}
	
	public void setPropositionJoueurTab(int[] propTab)
	{
		this.propositionJoueurTab = propTab;
	}
	
	public void setPropositionJoueurTab(int indice, int valeur)
	{
		this.propositionJoueurTab[indice] = valeur;
	}
	
	public void setCombiOrdiTab(int[] combTab)
	{
		this.combiOrdiTab = combTab;
	}
	
	public void setCombiOrdiTab(int indice, int valeur)
	{
		this.combiOrdiTab[indice] = valeur;
	}
	
	public void setPropositionOrdiTab(int[] propTab)
	{
		this.propositionOrdiTab = propTab;
	}
	
	public void setPropositionOrdiTab(int indice, int valeur)
	{
		this.propositionOrdiTab[indice] = valeur;
	}
	
	public void setAleatoireTab(int[] aleTab)
	{
		this.aleatoireTab = aleTab;
	}
	
	public void setAleatoireTab(int indice, int valeur)
	{
		this.aleatoireTab[indice] = valeur;
	}
	
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
	
	public void setRejouer(int rejoue)
	{
		this.rejouer = rejoue;
	}
	
	public void setComparaisonTab(String[] compaTab)
	{
		this.comparaisonTab = compaTab;
	}
	
	public void setComparaisonTab(int indice, String valeur)
	{
		this.comparaisonTab[indice] = valeur;
	}
	
	public void setGagne(boolean gg)
	{
		this.gagne = gg;
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
		this.gagne = false;
		this.combiOrdi = 0;
		this.propositionOrdi = 0;
		this.combiJoueur = 0;
		this.propositionJoueur = 0;
		this.mode = 0;	
		this.trouve = 0;
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
	 * Fonction d'initialisation de la variable "mode" : 	*
	 * - Challenger											*
	 * - Défenseur											*
	 * - Duel												*						
	********************************************************/
	public void choisirMode()
	{
		do
		{
			System.out.println("********** Choix du mode **********");
			System.out.println("1. Challenger");
			System.out.println("2. Défenseur");
			System.out.println("3. Duel");
			System.out.println("4. Quitter");
			
			setMode(scan.nextInt());
			
			switch(this.mode)
			{
			case 1:
				challengerModePlusMoins();
				break;
				
			case 2 :
				defenseurModePlusMoins();
				break;
			default :
				break;
					
			}
		}while(getMode() != 4);
		
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
	 * remplit un tableau de 4 chiffres aléatoirement	*
	 * @return combiTab[]								*
	 ***************************************************/
	public int[] decoupageAleatoire()
	{
		Random hasard = new Random();
		
		this.aleatoireTab[0] = nombreAleatoire(0, 9, hasard);
		System.out.println("aleatoireTab[0] = " + this.aleatoireTab[0]);
		this.aleatoireTab[1] = nombreAleatoire(0, 9, hasard);
		System.out.println("aleatoireTab[1] = " + this.aleatoireTab[1]);
		this.aleatoireTab[2] = nombreAleatoire(0, 9, hasard);
		System.out.println("aleatoireTab[2] = " + this.aleatoireTab[2]);
		this.aleatoireTab[3] = nombreAleatoire(0, 9, hasard);
		System.out.println("aleatoireTab[3] = " + this.aleatoireTab[3]);
		
		return aleatoireTab;
	}
	
	/****************************************************
	 * remplit un tableau de 4 chiffres aléatoirement	*
	 * @param mini	borne mini							*
	 * @param maxi	borne maxi							*
	 * @return aleatoireTab[]							*
	 ***************************************************/
	public int[] decoupageAleatoire(int[] mini, int[] maxi)
	{
		Random hasard = new Random();
		
		this.aleatoireTab[0] = nombreAleatoire(mini[0], maxi[0], hasard);
		System.out.println("aleatoireTab[0] = " + this.aleatoireTab[0]);
		this.aleatoireTab[1] = nombreAleatoire(mini[1], maxi[1], hasard);
		System.out.println("aleatoireTab[1] = " + this.aleatoireTab[1]);
		this.aleatoireTab[2] = nombreAleatoire(mini[2], maxi[2], hasard);
		System.out.println("aleatoireTab[2] = " + this.aleatoireTab[2]);
		this.aleatoireTab[3] = nombreAleatoire(mini[3], maxi[3], hasard);
		System.out.println("aleatoireTab[3] = " + this.aleatoireTab[3]);
		
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
	public boolean compareTab(int[] combi, int[] proposition)
	{
		setTrouve(0);
		
		for(int i=0; i<4; i++)
		{
			System.out.print(compare(combiTab[i], propositionTab[i]) + " ");
			this.comparaisonTab[i] = compare(combiTab[i], propositionTab[i]); 
			if(compare(combiTab[i], propositionTab[i]) == "=")
			{
				this.trouve++;
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
	
	/****************************************************
	 * Demande à l'utilisateur de créer une combinaison	*
	 ***************************************************/	
	public void joueurCombi()
	{
		System.out.println("Entrez une combinaison de 4 chiffres : ");	
		setCombi(scan.nextInt());
		setCombiTab(decoupage(this.combi));
	}
	
	
	/****************************************************
	 * Demande à l'utilisateur de proposer une réponse	*
	 ***************************************************/
	public void joueurCherche()
	{
		setProposition(scan.nextInt());
		setPropositionTab(decoupage(this.proposition));	
	}
	
	/**
	 * Demande à l'ordinateur de créer une combinaison
	 */
	public void ordiCombi()
	{
		setCombiTab(decoupageAleatoire());
	}
	
	/****************************************************************
	 * Demande à l'ordinateur de proposer une solution (ALEATOIRE)	*
	 ***************************************************************/
	public void ordiCherche()
	{
		Random hasard = new Random();
	
		setGagne(compareTab(getPropositionTab(), getCombiTab()));
		analyse();
		for(int k = 0 ; k < 4 ; k++)
		{
			System.out.println("Limites de proposition[" + k + "] : [" + getMiniBorneTab(k)+ " ; "  + getMaxiBorneTab(k) + "]");
		}
		
		for(int j = 0; j < 4; j++)
		{
			if(choixSur[j] == false)
			{
				setPropositionTab(j, nombreAleatoire(getMiniBorneTab(j), getMaxiBorneTab(j), hasard));
				System.out.println("Nouveau tirage : [" + j + "] = " + getPropositionTab(j));
				
			}
		}
				
		System.out.println("D'après mes calculs, la solution est : " );
		for(int i = 0; i<4; i++)
		{
			System.out.println(getPropositionTab(i));
		}
	}
	
	/*--------------------------------------------Mode Challenger----------------------------------------------*/
	/***************************************
	 * Programme de jeu en mode Challenger *
	 ***************************************/
	public void challengerModePlusMoins()
	{
		System.out.println("Bienvenue dans le Recherche +/- mode Challenger !");
		
		do
		{
			ordiCombi();	//On créé un nombre aléatoire
			
			do
			{
										
				System.out.println("Entrez une combinaison de 4 chiffres : ");	//On demande à l'utilisateur une combi
				
				joueurCherche();
				
				setGagne(compareTab(this.combiTab, this.propositionTab));	//On compare les réponses
				
				if(getGagne() == false)										//Si c'est faux ...
					this.vie --;											//...l'utilisateur perd 1 essai
																	
			}while(!getGagne() && getVie() > 0);	//On tourne jusqu'a ce que l'utilisateur gagne ou perde toutes ses vies	
			
			if(getGagne() == true)
			{
				System.out.println("Bravo ! Vous avez gagné :)");
			}else if(getGagne() == false)
			{
				System.out.println("Dommage, meilleures chances la prochaine fois ! \nLa réponse était : " 
						+ combiTab[0]
						+ combiTab[1]
						+ combiTab[2]
						+ combiTab[3]);
			}
			
			System.out.println("\nVoulez-vous rejouer ? \n\n\t1. oui \t\t2.non");
			setRejouer(scan.nextInt());	
			
		}while(getRejouer() == 1);
	}	
	
	/*--------------------------------------------Mode Defenseur-----------------------------------------------*/
	/****************************************
	 * Programme de jeu en mode Défenseur	*
	 ****************************************/		
	public void defenseurModePlusMoins()
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
	
	public void analyse()
	{		
		for(int cpt = 0; cpt < 4; cpt++)
		{
			if(getComparaisonTab(cpt) == "+")
			{
				if(getPropositionTab(cpt) == 9)
				{
					setMaxiBorneTab(cpt, 9);
				}else
				{
					setMiniBorneTab(cpt, getPropositionTab(cpt) + 1);
				}
				setChoixSur(cpt, false);
			}else if(getComparaisonTab(cpt) == "-")
			{
				if(getPropositionTab(cpt) == 0)
				{
					setMiniBorneTab(cpt, 0);
				}else
				{
					setMaxiBorneTab(cpt, getPropositionTab(cpt) - 1);;
				}
				setChoixSur(cpt, false);
			}else if(getComparaisonTab(cpt) == "=")
			{
				setChoixSur(cpt, true);
			}
		}
	}
	
	/*--------------------------------------------Mode Duel----------------------------------------------*/

	/****************************************
	 * Programme de jeu en mode Défenseur	*
	 ****************************************/		
	public void duelPlusMoins()
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
}
