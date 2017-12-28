import java.util.Scanner;

import Configurations.ConfigMMD;
import Configurations.ConfigurationMMD;

public class ModeDeveloppeur
{
	//Attributs
	ConfigurationMMD configMMD = ConfigMMD.loadConfig();

	//Constructeur
	public ModeDeveloppeur()
	{

	}

	//Menu de choix des actions
	public void menuMD()
	{
		Integer choixMenu = 0;
		Scanner scan = new Scanner(System.in);

		do
		{
			ConfigMMD.loadConfig();
			System.out.println("\n\n************** REGLAGES & MODE DEVELOPPEUR *************");
			System.out.println("\n----Configuration courante----");
			System.out.println(ConfigMMD.loadConfig());
			System.out.println("\n------------------------------");
			System.out.println("\n\n**** Changer les paramètres ****");
			System.out.println("  1. Nombre de cases de la combinaison secrète");
			System.out.println("  2. Nombre d'essais");
			System.out.println("  3. Nombre de couleurs utilisables");
			System.out.println("  4. Mode Développeur");
			System.out.println("  5. Quitter");
			System.out.println("\nVotre choix : ");

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

		System.out.println("Au revoir ! :)");
	}

	//Permet de changer la taille de la combinaison à trouver
	public void changeNombreCombi()
	{
		Scanner scan = new Scanner(System.in);

		System.out.println("\t* * * Entrez le nouveau nombre de cases de la combinaison secrète * * *");
		Integer nbrCombi = scan.nextInt();
		this.configMMD.setNombreCasesCombi(nbrCombi);
		ConfigMMD.saveConfig(configMMD);
	}

	//Permet de changer le nombre de coups possibles
	public void changeNombreEssais()
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("\t* * * Entrez le nouveau nombre d'essais * * *");
		Integer nbrEssais = scan.nextInt();
		this.configMMD.setEssais(nbrEssais);
		ConfigMMD.saveConfig(configMMD);
	}

	//Permet de changer le nombre de couleurs disponibles
	public void changeNombreCouleurs()
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("\t* * * Entrez le nouveau nombre de couleurs possibles * * *");
		Integer nbrCouleurs = scan.nextInt();
		this.configMMD.setNombreCouleurs(nbrCouleurs);
		ConfigMMD.saveConfig(configMMD);
	}

	//Permet d'activer/désactiver le mode Développeur
	public void changeModeDeveloppeur()
	{
		Scanner scan = new Scanner(System.in);
		Integer modeDev = 0;
		do
		{
			System.out.println("\t* * * Activer le mode Développeur ? * * *");
			System.out.println("  1. Oui\t2. Non");

			modeDev = scan.nextInt();

			if (modeDev != 1 && modeDev != 2)
			{
				System.out.println("Veuillez entrer 1 ou 2");
			}

		} while (modeDev != 1 && modeDev != 2);
		if (modeDev == 1)
		{
			this.configMMD.setModeDeveloppeur(true);
		} else if (modeDev == 2)
		{
			this.configMMD.setModeDeveloppeur(false);
		}
		ConfigMMD.saveConfig(configMMD);
	}

}
