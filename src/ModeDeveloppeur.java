import java.util.Scanner;

import Tables.ConfigJeu;
import Tables.Configuration;

public class ModeDeveloppeur
{
	Configuration c = ConfigJeu.loadConfig();

	public ModeDeveloppeur()
	{
		int choixMenu = 0;
		Scanner scan = new Scanner(System.in);

		do
		{
			ConfigJeu.loadConfig();
			System.out.println("\n\n----Configuration courante----");
			System.out.println(ConfigJeu.loadConfig());
			System.out.println("\n\n****Changer les paramètres****");
			System.out.println("\t1. Nombre de cases de la combinaison secrète");
			System.out.println("\t2. Nombre d'essais");
			System.out.println("\t3. Nombre de couleurs utilisables");
			System.out.println("\t4. Quitter");
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
				break;

			default:
				break;

			}
		} while (choixMenu != 4);

		System.out.println("Au revoir ! :)");
	}

	public void changeNombreCombi()
	{
		Scanner scan = new Scanner(System.in);

		System.out.println("\t* * * Entrez le nouveau nombre de cases de la combinaison secrète * * *");
		Integer nbrCombi = scan.nextInt();
		this.c.setNombreCasesCombi(nbrCombi);
		ConfigJeu.saveConfig(c);
	}

	public void changeNombreEssais()
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("\t* * * Entrez le nouveau nombre d'essais * * *");
		Integer nbrEssais = scan.nextInt();
		this.c.setEssais(nbrEssais);
		ConfigJeu.saveConfig(c);
	}

	public void changeNombreCouleurs()
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("\t* * * Entrez le nouveau nombre de couleurs possibles * * *");
		Integer nbrCouleurs = scan.nextInt();
		this.c.setNombreCouleurs(nbrCouleurs);
		ConfigJeu.saveConfig(c);
	}

}
