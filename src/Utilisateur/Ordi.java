package Utilisateur;

import java.util.Random;

import org.apache.log4j.Logger;

import Tables.Propo;

/**
 * Classe fille de Utilisateur qui décrit le fonctionnement de l'ordinateur
 * 
 * @author Thomas Pelissier
 * @version 1.0
 *
 */
public class Ordi extends Utilisateur
{
	//Attributs
	private Integer tailleCombi;
	private static Logger logger = Logger.getLogger(Ordi.class);

	//Constructeur
	public Ordi(Integer i)
	{
		super();
		initialisation(i);
		setTailleCombi(i);
		init(getTailleCombi());
	}

	//Getters & Setters
	public Integer getTailleCombi()
	{
		return tailleCombi;
	}

	public void setTailleCombi(Integer tailleCombi)
	{
		this.tailleCombi = tailleCombi;
	}

	public static Logger getLogger()
	{
		return logger;
	}

	public static void setLogger(Logger logger)
	{
		Ordi.logger = logger;
	}

	//METHODES 

	/**
	 * Définit les nouvelles bornes dans lesquelles chercher la combinaison à
	 * trouver
	 */
	public void analyse()
	{
		for (int cpt = 0; cpt < getTailleCombi(); cpt++)
		{
			if (this.getComparaisonTab(cpt) == "+")
			{
				if (this.getPropositionTab(cpt) == 9)
				{
					this.setMaxiBorneTab(cpt, 9);
				} else
				{
					this.setMiniBorneTab(cpt, this.getPropositionTab(cpt) + 1);
				}
				setChoixSur(cpt, false);
			} else if (this.getComparaisonTab(cpt) == "-")
			{
				if (this.getPropositionTab(cpt) == 0)
				{
					this.setMiniBorneTab(cpt, 0);
				} else
				{
					this.setMaxiBorneTab(cpt, getPropositionTab(cpt) - 1);
					;
				}
				this.setChoixSur(cpt, false);
			} else if (this.getComparaisonTab(cpt) == "=")
			{
				this.setChoixSur(cpt, true);
			}
		}
	}

	/**
	 * Initialise l'objet Ordi
	 * 
	 * @param i
	 *            tailleCombi
	 */
	public void init(Integer i)
	{
		this.propoTab = new Propo(i);
		this.comparaisonTab = new String[i];
	}

	/**
	 * Demande de création d'une combinaison aléatoire de taille x
	 * 
	 * @param x
	 *            taille
	 */
	public void combi(Integer x)
	{
		setCombiTab(decoupageAleatoire(x));
	}

	/**
	 * Demande de proposition de réponse ALEATOIRE (mais bornée)
	 */
	public void cherche()
	{
		Random hasard = new Random();

		//Affichage des limites de propositions dans le fichier de logs
		for (Integer k = 0; k < getTailleCombi(); k++)
		{
			logger.debug("Limites de proposition[" + k + "] : [" + this.getMiniBorneTab(k) + " ; "
					+ this.getMaxiBorneTab(k) + "]");
		}

		//Ajout des nouvelles propositions de réponse
		for (Integer j = 0; j < getTailleCombi(); j++)
		{
			if (getChoixSur(j) == false)
			{
				this.setPropositionTab(j, nombreAleatoire(this.getMiniBorneTab(j), this.getMaxiBorneTab(j), hasard));
			}
		}

		//Affichage de la nouvelle proposition
		System.out.print("Nouvelle proposition : ");
		for (Integer i = 0; i < getTailleCombi(); i++)
		{
			System.out.print(this.getPropositionTab(i));
		}
	}
}
