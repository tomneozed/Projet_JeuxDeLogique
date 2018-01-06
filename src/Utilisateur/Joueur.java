package Utilisateur;

import java.util.Scanner;

import org.apache.log4j.Logger;

public class Joueur extends Utilisateur
{
	private Integer tailleCombi;
	private static Logger logger = Logger.getLogger(Joueur.class);

	/********************
	 * Constructeur *
	 *******************/
	public Joueur(Integer i)
	{
		super();
		initialisation(i);
		setTailleCombi(i);
	}

	//GETTERS & SETTERS
	public Integer getTailleCombi()
	{
		return tailleCombi;
	}

	public void setTailleCombi(Integer tailleCombi)
	{
		this.tailleCombi = tailleCombi;
	}

	/********************************************
	 * Demande de cr�ation d'une combinaison *
	 *******************************************/
	public void combi(Integer x)
	{
		super.combi(x);
		Scanner scan = new Scanner(System.in);
		System.out.println("Entrez une combinaison de " + x + " chiffres (0-9): ");

		do
		{
			this.setPropositionString(scan.nextLine());
			if (this.getPropositionString().length() != x)
			{
				System.out.println("\nAttention, vous n'avez pas entr� le bon nombre de chiffre ! ");
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
	 * Demande de proposition de r�ponse *
	 ***************************************/
	public void cherche(Integer x)
	{
		do
		{
			System.out.println("\nEntrez une proposition de " + x + " chiffres (couleurs) (0-9): ");
			Scanner scan = new Scanner(System.in);
			this.setPropositionString(scan.nextLine());
			if (this.getPropositionString().length() != x)
			{
				System.out.println("\nAttention, vous n'avez pas entr� le bon nombre de chiffre ! ");
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
	}

}
