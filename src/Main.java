import java.util.Scanner;

import Configurations.MotherOfMothers;
import JeuDeLogique.Mastermind;
import JeuDeLogique.RecherchePlusMoins;

public class Main extends MotherOfMothers
{

	public Main()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args)
	{
		menu();
		// test t = new test();
		// TestMasterTable tmt = new TestMasterTable();
		// MasterTable mt1 = new MasterTable(10);
		// mt1.afficheMT();
		//TestTables tt = new TestTables();

		//System.out.println(ConfigJeu.loadConfig());

		//Configuration c = ConfigJeu.loadConfig();

		//c.setEssais(5);

		//ConfigJeu.saveConfig(c);

		//System.out.println(ConfigJeu.loadConfig());

		//ModeDeveloppeur mdv = new ModeDeveloppeur();

	}

	public static void menu()
	{
		Integer choixMenu = 0;
		Scanner scan = new Scanner(System.in);

		do
		{
			System.out.println("\n\n******************** MENU PRINCIPAL ********************");
			System.out.println("\t1. Recherche +/-");
			System.out.println("\t2. Mastermind");
			System.out.println("\t3. Réglages & Mode Développeur");
			System.out.println("\t4. Quitter");
			System.out.println("\nVotre choix : ");

			choixMenu = scan.nextInt();

			switch (choixMenu)
			{
			case 1:
				RecherchePlusMoins rpm = new RecherchePlusMoins();
				rpm.choisirMode(0);
				break;

			case 2:
				Mastermind m = new Mastermind();
				m.choisirMode(1);
				break;

			case 3:
				ModeDeveloppeur md = new ModeDeveloppeur();
				md.menuMD();
				break;

			case 4:
				break;
			default:
				break;

			}
		} while (choixMenu != 4);

		System.out.println("Au revoir ! :)");
	}

}
