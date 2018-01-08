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

/**
 * Classe mère des jeux permettant de capitaliser leurs attributs communs
 * 
 * @author Thomas Pelissier
 * @version 1.0
 */
public class JeuDeLogique
{
	//Attributs
	private Integer rejouer;
	private Integer trouve;
	private Integer mode;

	private Boolean gagneOrdi, gagneJoueur;

	protected Ordi ordi;
	protected Joueur joueur;

	protected ConfigurationMMD configMMD = ConfigMMD.loadConfigMMD();
	protected ConfigurationG configG = ConfigG.loadConfigG();

	//Constructeur
	public JeuDeLogique()
	{
		setRejouer(0);
		setMode(0);
		setGagneOrdi(null);
		setGagneJoueur(null);
	}

	//Getters & Setters
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
		return gagneOrdi;
	}

	public Boolean getGagneJoueur()
	{
		return gagneJoueur;
	}

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

	public void setGagneOrdi(Boolean gagneOrdi)
	{
		this.gagneOrdi = gagneOrdi;
	}

	public void setGagneJoueur(Boolean gagneJoueur)
	{
		this.gagneJoueur = gagneJoueur;
	}

	//Methodes

	public String toString()
	{
		return "JeuDeLogique [rejouer=" + rejouer + ", trouve=" + trouve + ", mode=" + mode + ", gagneOrdi=" + gagneOrdi
				+ ", gagneJoueur=" + gagneJoueur + ", ordi=" + ordi + ", joueur=" + joueur + ", configMMD=" + configMMD
				+ ", configG=" + configG + "]";
	}

	/**
	 * Menu permettant de choisir le mode de jeu
	 * 
	 * @param c
	 *            qui sert à différencier les 2 jeux
	 */
	public void choisirMode(Integer c)
	{
		Integer choixMode = -1;
		Scanner scanjdl = new Scanner(System.in);

		do
		{
			System.out.println("\n********** Choix du mode **********");
			System.out.println("1. Challenger");
			System.out.println("2. Défenseur");
			System.out.println("3. Duel");
			System.out.println("4. Parametres");
			System.out.println("5. Quitter");
			System.out.println("\nVotre choix : ");

			choixMode = scanjdl.nextInt();
			setMode(choixMode);

			switch (c)
			{
			//RecherchePlusMoins
			case 0:
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

			//Mastermind
			case 1:
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
					m.duelMode();
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
		//scanjdl.close();
	}

	/**
	 * Compare les valeurs de 2 tableaux d'entiers et renvoie la position de chaque
	 * élément du 1er tableau par rapport au 2eme et compte le nombre de valeurs
	 * exactes
	 * 
	 * @param combiTab
	 *            table à tester
	 * 
	 * @param propositionTab
	 *            table à tester
	 * 
	 * @param j
	 *            0 : n'afiche pas la comparaison, 1 : affiche la comparaison
	 * 
	 * @return compa
	 */
	public String[] compareTab(Integer[] combiTab, Integer[] propositionTab, Integer j)
	{
		setTrouve(0);
		String[] compa = new String[configMMD.getNbrCasesCombiMMD()];

		if (j == 1)
		{
			System.out.println("\nComparaison : ");

			afficheTab(propositionTab);
			for (Integer i = 0; i < configMMD.getNbrCasesCombiMMD(); i++)
			{
				System.out.print(compare(combiTab[i], propositionTab[i]) + " ");
				compa[i] = compare(combiTab[i], propositionTab[i]);
			}
		} else if (j == 0)
		{
			for (Integer i = 0; i < configMMD.getNbrCasesCombiMMD(); i++)
			{
				compa[i] = compare(combiTab[i], propositionTab[i]);
			}
		}

		return compa;

	}

	/**
	 * Analyse si le résultat est correct dans son ensemble (= = = = ...)
	 * 
	 * @param comp
	 *            comparaison à analyser
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

	/**
	 * Compare 2 entiers et renvoie "+", "-", "=" * en fonction du résultat
	 * 
	 * @param a
	 *            operande 1
	 * 
	 * @param b
	 *            operande 2
	 * 
	 * @return comparaison
	 */
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
	 * Affiche la table passée en paramètres
	 * 
	 * @param tab
	 *            table à afficher
	 * 
	 */
	public void afficheTab(Integer[] tab)
	{
		for (Integer i = 0; i < tab.length; i++)
		{
			System.out.print(tab[i] + " ");
		}
		System.out.print("\n");
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
