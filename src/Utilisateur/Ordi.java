package Utilisateur;
import java.util.Random;

public class Ordi extends Utilisateur
{
	/******* VARIABLES *****************************************************************************************/
	
	
	/******* FONCTIONS *****************************************************************************************/
	/********************
	 * Constructeur 	*
	 *******************/
	public Ordi()
	{
		super();
	}
	
	public void analyse()
	{		
		for(int cpt = 0; cpt < 4; cpt++)
		{
			if(getComparaisonTab(cpt) == "+")
			{
				if(getPropositionTab(cpt) == 9)
				{
					this.setMaxiBorneTab(cpt, 9);
				}else
				{
					this.setMiniBorneTab(cpt, getPropositionTab(cpt) + 1);
				}
				setChoixSur(cpt, false);
			}else if(getComparaisonTab(cpt) == "-")
			{
				if(getPropositionTab(cpt) == 0)
				{
					this.setMiniBorneTab(cpt, 0);
				}else
				{
					this.setMaxiBorneTab(cpt, getPropositionTab(cpt) - 1);;
				}
				this.setChoixSur(cpt, false);
			}else if(getComparaisonTab(cpt) == "=")
			{
				this.setChoixSur(cpt, true);
			}
		}
	}
	
	/********************************************
	 * Demande de création d'une combinaison	*
	 *******************************************/	
	public void combi()
	{
		setCombiTab(decoupageAleatoire());
	}
	
	/************************************************
	 * Demande de proposition de réponse ALEATOIRE	*
	 ***********************************************/
	public void cherche()
	{
		Random hasard = new Random();
		
		analyse();
		for(int k = 0 ; k < 4 ; k++)
		{
			System.out.println("Limites de proposition[" + k + "] : [" + this.getMiniBorneTab(k)+ " ; "  + this.getMaxiBorneTab(k) + "]");
		}
		
		for(int j = 0; j < 4; j++)
		{
			if(getChoixSur(j) == false)
			{
				setPropositionTab(j, nombreAleatoire(this.getMiniBorneTab(j), this.getMaxiBorneTab(j), hasard));
				System.out.println("Nouveau tirage : [" + j + "] = " + this.getPropositionTab(j));
				
			}
		}
				
		System.out.println("D'après mes calculs, la solution est : " );
		for(int i = 0; i<4; i++)
		{
			System.out.println(this.getPropositionTab(i));
		}
	}
}
