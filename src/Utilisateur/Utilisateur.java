package Utilisateur;

import java.util.Random;

import Tables.Propo;

/**
 * Classe mère des Utilisateur permettant de capitaliser leurs
 * methodes/attributs communs
 * 
 * @author Thomas Pelissier
 * @version 1.0
 *
 */
public class Utilisateur
{
	//Attributs
	private Integer vie;
	private String propositionString;

	protected Integer combiTab[];
	protected Integer propositionTab[];

	protected Propo propoTab;

	protected Integer miniBorneTab[];
	protected Integer maxiBorneTab[];

	protected String comparaisonTab[];

	protected boolean choixSur[];

	//Contructeur
	public Utilisateur()
	{

	}

	//Getters & Setters

	public Integer[] getPropositionTab()
	{
		return propositionTab;
	}

	public Integer getPropositionTab(Integer i)
	{
		return this.propositionTab[i];
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

	//Methodes 

	/**
	 * Initialise les attributs (la taille des tables à i)
	 * 
	 * @param i
	 *            taille tables
	 */
	public void initialisation(Integer i)
	{
		setMiniBorneTab(new Integer[i]);
		setMaxiBorneTab(new Integer[i]);
		setChoixSur(new boolean[i]);
		initMaxiMiniChoixSur(i);

		setCombiTab(new Integer[i]);
		setPropositionTab(new Integer[i]);
	}

	/**
	 * Remplit les tables mini, maxi et choixSur afin de les initialiser
	 * 
	 * @param j
	 *            taille des tables
	 */
	public void initMaxiMiniChoixSur(Integer j)
	{
		for (int i = 0; i < j; i++)
		{
			this.miniBorneTab[i] = 0;
			this.maxiBorneTab[i] = 9;
			this.choixSur[i] = false;
		}
	}

	/**
	 * Retourne un nombre aléatoire entre min et max
	 * 
	 * @param min
	 *            borne minimum
	 * 
	 * @param max
	 *            borne maximum
	 * 
	 * @param hasard
	 *            permet la création d'un nombre au hasard
	 * 
	 * @return nombreHasard
	 */
	public Integer nombreAleatoire(Integer min, Integer max, Random hasard)
	{
		if (min > max)
			throw new IllegalArgumentException("Min > Max");

		long range = (long) max - (long) min + 1;
		long fraction = (long) (range * hasard.nextDouble());
		Integer nombreHasard = (int) (fraction + min);

		return nombreHasard;
	}

	/**
	 * Remplit un tableau de x chiffres aléatoires
	 * 
	 * @param x
	 *            taille du tableau
	 * @return aleatoireTab[]
	 */
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

	/**
	 * Remplit un tableau de x chiffres aleatoires
	 * 
	 * @param x
	 *            taille du tableau
	 * @param mini
	 *            borne mini
	 * @param maxi
	 *            borne maxi
	 * @return aleatoireTab[]
	 */

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

	/**
	 * Demande de création d'une combinaison de taille x
	 * 
	 * @param x
	 *            taille
	 */
	public void combi(Integer x)
	{
		System.out.println("Création de la combinaison à trouver");
	}

	/**
	 * Demande de proposition de réponse
	 * 
	 * @param x
	 *            taille
	 */
	public void cherche(Integer x)
	{
		System.out.println("Proposition de réponse");
	}
}
