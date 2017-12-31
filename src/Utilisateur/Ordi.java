package Utilisateur;

import java.util.Random;

import org.apache.log4j.Logger;

public class Ordi extends Utilisateur
{

	private static Logger logger = Logger.getLogger(Ordi.class);

	/********************
	 * Constructeur *
	 *******************/
	public Ordi()
	{
		super();
	}

	public void analyse()
	{
		for (int cpt = 0; cpt < NB_CASES_COMBI; cpt++)
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

	/********************************************
	 * Demande de création d'une combinaison *
	 * 
	 * @param x
	 *            taille de la combinaison *
	 *******************************************/
	public void combi(Integer x)
	{
		setCombiTab(decoupageAleatoire(x));
	}

	/************************************************
	 * Demande de proposition de réponse ALEATOIRE *
	 ***********************************************/
	public void cherche()
	{
		Random hasard = new Random();

		//Affichage des limites de propositions dans le fichier de logs
		for (Integer k = 0; k < NB_CASES_COMBI; k++)
		{
			logger.debug("Limites de proposition[" + k + "] : [" + this.getMiniBorneTab(k) + " ; "
					+ this.getMaxiBorneTab(k) + "]");
		}

		//Ajout des nouvelles propositions de réponse
		for (Integer j = 0; j < NB_CASES_COMBI; j++)
		{
			if (getChoixSur(j) == false)
			{
				setPropositionTab(j, nombreAleatoire(this.getMiniBorneTab(j), this.getMaxiBorneTab(j), hasard));
			}
		}

		//Affichage de la nouvelle proposition
		System.out.println("Nouvelle proposition : ");
		for (Integer i = 0; i < NB_CASES_COMBI; i++)
		{
			System.out.print(this.getPropositionTab(i));
		}
	}
}
