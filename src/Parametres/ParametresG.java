package Parametres;

import java.util.Scanner;

import Configurations.ConfigG;
import Configurations.ConfigurationG;

public class ParametresG
{
	ConfigurationG configG = ConfigG.loadConfigG();

	//Consructeur
	public ParametresG()
	{

	}

	//Affcihage menu
	public void affichageMenu()
	{
		System.out.println("\n\n************** REGLAGES & MODE DEVELOPPEUR *************");
		System.out.println("\n\n**** Changer les paramètres ****");
		System.out.println("  1. Nombre de cases de la combinaison secrète");
		System.out.println("  2. Nombre d'essais");
		System.out.println("  3. Nombre de couleurs utilisables");
		System.out.println("  4. Mode Développeur");
		System.out.println("  5. Quitter");
		System.out.println("\nVotre choix : ");
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
			configG.setModeDeveloppeur(true);
		} else if (modeDev == 2)
		{
			configG.setModeDeveloppeur(false);
		}
		ConfigG.saveConfigG(configG);
		//scan.close();
	}

}
