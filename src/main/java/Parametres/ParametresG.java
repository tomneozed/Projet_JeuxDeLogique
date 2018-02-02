package Parametres;

import java.util.Scanner;

import Configurations.ConfigG;
import Configurations.ConfigurationG;

/**
 * Classe m�re des Parametres qui propose un menu permettant de modifier les
 * param�tres du fichier config.properties
 * 
 * @author Thomas Pelissier
 * @version 1.0
 */
public class ParametresG
{
	//Attributs
	ConfigurationG configG = ConfigG.loadConfigG();

	//Consructeur
	public ParametresG()
	{

	}

	//Methodes 

	public String toString()
	{
		return "ParametresG [configG=" + configG + "]";
	}

	/**
	 * Affcihe le menu
	 */
	public void affichageMenu()
	{
		System.out.println("\n\n* * * REGLAGES & MODE DEVELOPPEUR * * *");
		System.out.println("\n**** Changer les param�tres ****");
		System.out.println("  1. Nombre de cases de la combinaison secr�te");
		System.out.println("  2. Nombre d'essais");
		System.out.println("  3. Nombre de couleurs utilisables");
		System.out.println("  4. Mode D�veloppeur");
		System.out.println("  5. Quitter");
		System.out.println("\nVotre choix : ");
	}

	/**
	 * Permet d'activer/d�sactiver le mode D�veloppeur
	 */
	public void changeModeDeveloppeur()
	{
		Scanner scan = new Scanner(System.in);
		Integer modeDev = 0;
		do
		{
			System.out.println("\t* * * Activer le mode D�veloppeur ? * * *");
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

	/**
	 * Permet d'activer/d�sactiver le mode D�veloppeur avec valeur en param�tres
	 */
	public void changeModeDeveloppeur(Integer modeDev)
	{
		Scanner scan = new Scanner(System.in);
		if (modeDev == 1)
		{
			configG.setModeDeveloppeur(true);
		} else
		{
			configG.setModeDeveloppeur(false);
		}
		ConfigG.saveConfigG(configG);
		//scan.close();
	}

}
