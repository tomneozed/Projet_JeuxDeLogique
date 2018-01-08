package Utilisateur;

import java.util.Scanner;

import org.apache.log4j.Logger;

/**
 * Classe fille de Utilisateur qui décrit le fonctionnement d'un joueur
 * 
 * @author Thomas Pelissier
 * @version 1.0
 *
 */
public class Joueur extends Utilisateur
{
	//Atributs
	private Integer tailleCombi;
	private static Logger logger = Logger.getLogger(Joueur.class);

	//Constructeur
	public Joueur(Integer i)
	{
		super();
		initialisation(i);
		setTailleCombi(i);
	}

	//Getters & Setters
	public Integer getTailleCombi()
	{
		return tailleCombi;
	}

	public void setTailleCombi(Integer tailleCombi)
	{
		this.tailleCombi = tailleCombi;
	}

	/**
	 * Demande de création d'une combinaison de taille x
	 * 
	 * @param x
	 *            taille
	 */
	public void combi(Integer x)
	{

		Scanner scan = new Scanner(System.in);
		System.out.println("Entrez une combinaison de " + x + " chiffres (0-9): ");

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

	/**
	 * Demande de proposition de réponse de taille x
	 * 
	 * @param x
	 *            taille
	 */
	public void cherche(Integer x)
	{
		do
		{
			System.out.println("\nEntrez une proposition de " + x + " chiffres (0-9): ");
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
	}

}
