package JeuDeLogique;

import java.util.Scanner;

import Configurations.ConfigG;
import Configurations.ConfigMMD;
import Configurations.ConfigurationG;
import Configurations.ConfigurationMMD;
import Parametres.ParametresMMD;
import Parametres.ParametresRPM;
import Utilisateur.Joueur;
import Utilisateur.Ordi;

public class JeuDeLogique
{
	/*******
	 * VARIABLES
	 *****************************************************************************************/
	private Integer rejouer = 0;
	private Integer trouve = 0;
	private Integer mode = 0;

	private Boolean gagneOrdi, gagneJoueur;

	Ordi ordi;
	Joueur joueur = new Joueur();

	ConfigurationMMD configMMD = ConfigMMD.loadConfigMMD();
	ConfigurationG configG = ConfigG.loadConfigG();

	/*******
	 * FONCTIONS
	 *****************************************************************************************/
	/********************
	 * Constructeur *
	 *******************/
	public JeuDeLogique()
	{
		ordi = new Ordi();
		setRejouer(0);
		setMode(0);
		setGagneOrdi(null);
		setGagneJoueur(null);
	}

	/*----------------------------------------Accesseurs et mutateurs------------------------------------------*/
	/****** GETTERS ******/
	public Integer getRejouer()
	{
		return this.rejouer;
	}

	public Integer getTrouve()
	{
		return trouve;
	}

	public Integer getMode()
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

	/****** SETTERS ******/
	public void setRejouer(Integer rejoue)
	{
		this.rejouer = rejoue;
	}

	public void setTrouve(Integer trouve)
	{
		this.trouve = trouve;
	}

	public void setMode(Integer mode)
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
	 * Fonction d'initialisation de la variable "mode" : * - Challenger * -
	 * Défenseur * - Duel *
	 ********************************************************/
	public void choisirMode(Integer c)
	{
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		do
		{
			System.out.println("\n********** Choix du mode **********");
			System.out.println("1. Challenger");
			System.out.println("2. Défenseur");
			System.out.println("3. Duel");
			System.out.println("4. Parametres");
			System.out.println("5. Quitter");

			setMode(scan.nextInt());

			switch (c)
			{
			case 0:									//RecherchePlusMoins
				if (getMode() == 1)
				{
					RecherchePlusMoins rpm = new RecherchePlusMoins();
					rpm.challengerMode();
				} else if (getMode() == 2)
				{
					RecherchePlusMoins rpm = new RecherchePlusMoins();
					rpm.defenseurMode();
				} else if (getMode() == 3)
				{
					RecherchePlusMoins rpm = new RecherchePlusMoins();
					rpm.duelMode();
				} else if (getMode() == 4)
				{
					ParametresRPM pRPM = new ParametresRPM();
					pRPM.menuRPM();
				}
				break;

			case 1:									//Mastermind
				if (getMode() == 1)
				{
					Mastermind m = new Mastermind();
					m.challengerMode();

				} else if (getMode() == 2)
				{
					Mastermind m = new Mastermind();
					m.defenseurMode();
				} else if (getMode() == 3)
				{
					Mastermind m = new Mastermind();
					//m.duelMode();
				} else if (getMode() == 4)
				{
					ParametresMMD pMMD = new ParametresMMD();
					pMMD.menuMMD();
				}
				break;

			default:
				break;

			}
		} while (getMode() != 4);
		scan.close();
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
	public String[] compareTab(Integer[] combiTab, Integer[] propositionTab)
	{
		setTrouve(0);
		String[] compa = new String[configMMD.getNbrCasesCombiMMD()];
		System.out.println("\nComparaison : ");
		for (Integer i = 0; i < configMMD.getNbrCasesCombiMMD(); i++)
		{
			System.out.print(compare(combiTab[i], propositionTab[i]) + " ");
			compa[i] = compare(combiTab[i], propositionTab[i]);
		}
		return compa;

	}

	/**
	 * Analyse si le résultat est correct dans son ensemble (= = = =)
	 * 
	 * @param comp
	 * @return true or false
	 */
	public boolean analyseTrouve(String[] comp)
	{
		for (Integer i = 0; i < configMMD.getNbrCasesCombiMMD(); i++)
		{
			if (comp[i] == "=")
			{
				setTrouve(getTrouve() + 1);
			}
		}
		System.out.println("");
		if (getTrouve() == configMMD.getNbrCasesCombiMMD())
		{
			return true;
		} else
			return false;
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

	/**
	 * Affiche la réponse si le mode développeur est activé
	 */
	public void reponse()
	{
		if (configG.getModeDeveloppeur() == true)
		{
			System.out.println("\n# # # # # Mode développeur activé , réponse : # # # # #");
		}
	}

}
