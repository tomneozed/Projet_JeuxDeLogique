import java.util.Scanner;

import JeuDeLogique.Mastermind;
import JeuDeLogique.RecherchePlusMoins;

public class Main
{
	public Main()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args)
	{
		menu();
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
		//scan.close();

		System.out.println("Au revoir ! :)");
	}

}
