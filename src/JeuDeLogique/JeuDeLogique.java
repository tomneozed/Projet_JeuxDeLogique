package JeuDeLogique;

import java.util.Scanner;

public class JeuDeLogique
{
	/*******
	 * VARIABLES
	 *****************************************************************************************/
	private Integer rejouer = 0;
	private Integer trouve = 0;
	private Integer mode = 0;

	private Boolean gagneOrdi, gagneJoueur;

	/*******
	 * FONCTIONS
	 *****************************************************************************************/
	/********************
	 * Constructeur *
	 *******************/
	public JeuDeLogique()
	{
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
	 * D�fenseur * - Duel *
	 ********************************************************/
	public void choisirMode(Integer c)
	{
		Scanner scan = new Scanner(System.in);
		do
		{
			System.out.println("\n********** Choix du mode **********");
			System.out.println("1. Challenger");
			System.out.println("2. D�fenseur");
			System.out.println("3. Duel");
			System.out.println("4. Quitter");

			setMode(scan.nextInt());

			switch (this.mode)
			{
			case 1:
				if (c == 0)
				{
					RecherchePlusMoins rpm = new RecherchePlusMoins();
					rpm.challengerMode();
				} else if (c == 1)
				{
					Mastermind m = new Mastermind();
					m.challengerMode();
				}
				break;

			case 2:
				if (c == 0)
				{
					RecherchePlusMoins rpm = new RecherchePlusMoins();
					rpm.defenseurMode();
				} else if (c == 1)
				{
					Mastermind m = new Mastermind();
					//m.defenseurMode();
				}
				break;

			case 3:
				if (c == 0)
				{
					RecherchePlusMoins rpm = new RecherchePlusMoins();
					rpm.duelMode();
				} else if (c == 1)
				{
					Mastermind m = new Mastermind();
					//m.duelMode();
				}
				break;
			default:
				break;

			}
		} while (getMode() != 4);

	}

	/********************************************************************
	 * Compare les valeurs de 2 tableaux d'entiers * et renvoie la position de
	 * chaque �l�ment * du 1er tableau par rapport au 2eme * et compte le nombre de
	 * valeurs exactes (le jeu est gagn� � 4) *
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
		String[] compa = new String[4];
		System.out.println("\nComparaison : ");
		for (Integer i = 0; i < 4; i++)
		{
			System.out.print(compare(combiTab[i], propositionTab[i]) + " ");
			compa[i] = compare(combiTab[i], propositionTab[i]);
		}
		return compa;

	}

	/**
	 * Analyse si le r�sultat est correct dans son ensemble (= = = =)
	 * 
	 * @param comp
	 * @return true or false
	 */
	public boolean analyseTrouve(String[] comp)
	{
		for (Integer i = 0; i < 4; i++)
		{
			if (comp[i] == "=")
			{
				setTrouve(getTrouve() + 1);
			}
		}
		System.out.println("");
		if (getTrouve() == 4)
		{
			return true;
		} else
			return false;
	}

	/************************************************
	 * Compare 2 entiers et renvoie "+", "-", "=" * en fonction du r�sultat *
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
}
