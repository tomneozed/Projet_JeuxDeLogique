import java.util.Scanner;

import JeuDeLogique.Mastermind;
import JeuDeLogique.RecherchePlusMoins;
import Tables.ColonneTerminee;
import Tables.IndiceTab;
import Tables.Propo;

public class Main
{

	public static void main(String[] args)
	{
		// menu();
		// test t = new test();
		// TestMasterTable tmt = new TestMasterTable();
		// MasterTable mt1 = new MasterTable(10);
		// mt1.afficheMT();

		Propo p = new Propo(4);
		IndiceTab IT = new IndiceTab();
		ColonneTerminee CT = new ColonneTerminee(4);

		p.init();

		p.affiche();

		p.setValeur(0, 2);
		p.setValeur(1, 3);
		p.setValeur(2, 4);
		p.setValeur(3, 5);

		p.affiche();

		CT.init();

		CT.affiche();

		CT.setValeur(0, true);
		CT.setValeur(1, true);
		CT.setValeur(2, false);
		CT.setValeur(3, true);

		CT.affiche();

	}

	public static void menu()
	{
		int choixMenu = 0;
		Scanner scan = new Scanner(System.in);

		do
		{
			System.out.println("\n\n************************* MENU *************************");
			System.out.println("\t1. Recherche +/-");
			System.out.println("\t2. Mastermind");
			System.out.println("\t3. Quitter");
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
				break;

			default:
				break;

			}
		} while (choixMenu != 3);

		System.out.println("Au revoir ! :)");
	}

}
