package Utilisateur;

import java.util.Scanner;

public class Joueur extends Utilisateur
{
	/*******
	 * VARIABLES
	 *****************************************************************************************/

	/*******
	 * FONCTIONS
	 *****************************************************************************************/
	/********************
	 * Constructeur *
	 *******************/
	public Joueur()
	{
		super();
	}

	/********************************************
	 * Demande de création d'une combinaison *
	 *******************************************/
	public void combi(int x)
	{
		super.combi(x);
		Scanner scan = new Scanner(System.in);
		System.out.println("Entrez une combinaison de 4 chiffres (0-9): ");
		setCombi(scan.nextInt());
		setCombiTab(decoupage(getCombi()));
	}

	/****************************************
	 * Demande de proposition de réponse *
	 ***************************************/
	public void cherche()
	{
		System.out.println("Entrez une proposition de 4 chiffres (0-9): ");
		Scanner scan = new Scanner(System.in);
		// setProposition(scan.nextInt());
		// setPropositionTab(decoupage(getProposition()));
	}

	public void chercheMastermind(int x)
	{
		do
		{
			System.out.println("\nEntrez une proposition de 10 chiffres (couleurs) (0-9): ");
			Scanner scan = new Scanner(System.in);
			this.setPropositionString(scan.nextLine());
			if (this.getPropositionString().length() != x)
			{
				System.out.println("\nAttention, vous n'avez pas entré le bon nombre de chiffre ! ");
			}
		} while (this.getPropositionString().length() != x);

		// setPropositionTab(Integer.parseInt(this.getPropositionString()));

	}

}
