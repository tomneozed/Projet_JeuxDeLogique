package Parametres;

import java.util.Scanner;

import Configurations.ConfigMMD;
import Configurations.ConfigurationMMD;

/**
 * Classe fille de ParametresG qui fait appel aux fonctions relatives aux
 * modifications des parametres du Mastermind
 * 
 * @author Thomas Pelissier
 * @version 1.0
 */
public class ParametresMMD extends ParametresG
{
	//Attributs
	ConfigurationMMD configMMD = ConfigMMD.loadConfigMMD();

	//Constructeur
	public ParametresMMD()
	{
		super();
	}

	//Methodes 

	/**
	 * Menu de choix des actions
	 */
	public void menuMMD()
	{
		Integer choixMenu = 0;
		Scanner scan = new Scanner(System.in);

		do
		{
			System.out.println("\n\n************** MASTERMIND *************");
			afficheConfig();
			affichageMenu();

			choixMenu = scan.nextInt();

			switch (choixMenu)
			{
			case 1:
				changeNombreCombi();
				break;

			case 2:
				changeNombreEssais();
				break;

			case 3:
				changeNombreCouleurs();
				break;

			case 4:
				changeModeDeveloppeur();
				break;
			default:
				break;

			}
		} while (choixMenu != 5);
		//scan.close();
		System.out.println("Au revoir ! :)");
	}

	/**
	 * Affichage configuration courante
	 */
	public void afficheConfig()
	{
		ConfigMMD.loadConfigMMD();
		System.out.println("\n-------- Configuration courante -------");
		System.out.println(ConfigMMD.loadConfigMMD().toString());
	}

	/**
	 * Permet de changer la taille de la combinaison à trouver
	 */
	public void changeNombreCombi()
	{
		Scanner scan = new Scanner(System.in);

		System.out.println("\t* * * Entrez le nouveau nombre de cases de la combinaison secrète * * *");
		Integer nbrCombi = scan.nextInt();
		this.configMMD.setNbrCasesCombiMMD(nbrCombi);
		ConfigMMD.saveConfigMMD(configMMD);
		//scan.close();
	}

	/**
	 * Permet de changer le nombre de coups possibles
	 */
	public void changeNombreEssais()
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("\t* * * Entrez le nouveau nombre d'essais * * *");
		Integer nbrEssais = scan.nextInt();
		this.configMMD.setNbrEssaisMMD(nbrEssais);
		ConfigMMD.saveConfigMMD(configMMD);
		//scan.close();
	}

	/**
	 * Permet de changer le nombre de couleurs disponibles
	 */
	public void changeNombreCouleurs()
	{
		Scanner scan = new Scanner(System.in);
		Integer nbrCouleurs = 0;
		do
		{
			System.out.println("\t* * * Entrez le nouveau nombre de couleurs possibles (4 à 10) * * *");
			nbrCouleurs = scan.nextInt();
		} while (nbrCouleurs < 4 && nbrCouleurs > 10);

		this.configMMD.setNbrCouleursMMD(nbrCouleurs);
		ConfigMMD.saveConfigMMD(configMMD);
		//scan.close();
	}
}
