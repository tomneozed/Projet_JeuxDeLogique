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
	public void combi(Integer x)
	{
		super.combi(x);
		Scanner scan = new Scanner(System.in);
		System.out.println("Entrez une combinaison de " + x + " chiffres (0-9): ");
		//setCombi(scan.nextInt());
		//setCombiTab(decoupage(getCombi()));

		do
		{
			this.setPropositionString(scan.nextLine());
			if (this.getPropositionString().length() != x)
			{
				System.out.println("\nAttention, vous n'avez pas entré le bon nombre de chiffre ! ");
			}
		} while (this.getPropositionString().length() != x);

		for (int i = 0; i < this.getPropositionString().length(); i++)
		{
			if (i > -1 && i < this.getPropositionString().length())
			{
				char c = this.getPropositionString().charAt(i);
				this.setCombiTab(i, Character.getNumericValue(c));
			}
		}

	}

	/****************************************
	 * Demande de proposition de réponse *
	 ***************************************/
	public void cherche()
	{
		System.out.println("Entrez une proposition de 4 chiffres (0-9): ");
		Scanner scan = new Scanner(System.in);
		this.setProposition(scan.nextInt());
		this.setPropositionTab(decoupage(this.getProposition()));
	}

	public void chercheMastermind(Integer x)
	{
		do
		{
			System.out.println("\nEntrez une proposition de " + x + " chiffres (couleurs) (0-9): ");
			Scanner scan = new Scanner(System.in);
			this.setPropositionString(scan.nextLine());
			if (this.getPropositionString().length() != x)
			{
				System.out.println("\nAttention, vous n'avez pas entré le bon nombre de chiffre ! ");
			}
		} while (this.getPropositionString().length() != x);

		for (int i = 0; i < this.getPropositionString().length(); i++)
		{
			if (i > -1 && i < this.getPropositionString().length())
			{
				char c = this.getPropositionString().charAt(i);
				this.setPropositionTab(i, Character.getNumericValue(c));
			}
		}

		//this.setProposition(Integer.decode(this.getPropositionString()));
		//this.setPropositionTab(decoupage(this.getProposition()));

	}

}
