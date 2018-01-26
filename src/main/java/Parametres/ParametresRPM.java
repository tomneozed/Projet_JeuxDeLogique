package Parametres;

import java.util.Scanner;

import Configurations.ConfigRPM;
import Configurations.ConfigurationRPM;

/**
 * Classe fille de ParametresG qui fait appel aux fonctions relatives aux
 * modifications des parametres du Recherche+/-
 * 
 * @author Thomas Pelissier
 * @version 1.0
 */
public class ParametresRPM extends ParametresG
{
	//Attributs
	ConfigurationRPM configRPM = ConfigRPM.loadConfigRPM();

	//Constructeur
	public ParametresRPM()
	{
		super();
	}

	//Methodes

	/**
	 * Menu de choix des actions
	 */
	public void menuRPM()
	{
		Integer choixMenu = 0;
		Scanner scan = new Scanner(System.in);

		do
		{
			System.out.println("\n\n********************* RECHERCHE +/- ********************");

			afficheConfig();
			affichageMenuRPM();

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
				changeModeDeveloppeur();
				break;
			default:
				break;

			}
		} while (choixMenu != 4);
		//scan.close();
		System.out.println("Au revoir ! :)");
	}

	/**
	 * Affiche le menu des paramètres du Recherche+/-
	 */
	public void affichageMenuRPM()
	{
		System.out.println("\n\n************** REGLAGES & MODE DEVELOPPEUR *************");
		System.out.println("\n\n**** Changer les paramètres ****");
		System.out.println("  1. Nombre de cases de la combinaison secrète");
		System.out.println("  2. Nombre d'essais");
		System.out.println("  3. Mode Développeur");
		System.out.println("  4. Quitter");
		System.out.println("\nVotre choix : ");
	}

	/**
	 * Affichage configuration courante
	 */
	public void afficheConfig()
	{
		ConfigRPM.loadConfigRPM();
		System.out.println("\n---------------- Configuration courante ----------------");
		System.out.println(ConfigRPM.loadConfigRPM().toString());
	}

	/**
	 * Permet de changer la taille de la combinaison à trouver
	 */
	public void changeNombreCombi()
	{
		Scanner scan = new Scanner(System.in);

		System.out.println("* * * Entrez le nouveau nombre de cases de la combinaison secrète (Ancien : "
				+ configRPM.getNbrCasesCombiRPM() + ")* * *");
		Integer nbrCombi = scan.nextInt();
		this.configRPM.setNbrCasesCombiRPM(nbrCombi);
		ConfigRPM.saveConfigRPM(configRPM);
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
		this.configRPM.setNbrEssaisRPM(nbrEssais);
		ConfigRPM.saveConfigRPM(configRPM);
		//scan.close();
	}
}
