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

	private String entreeUtil;

	private boolean entreeCorrecte = false;

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

	public boolean getEntreeCorrecte()
	{
		return entreeCorrecte;
	}

	public String getEntreeUtil()
	{
		return entreeUtil;
	}

	public void setTailleCombi(Integer tailleCombi)
	{
		this.tailleCombi = tailleCombi;
	}

	public void setEntreeCorrecte(boolean entree)
	{
		this.entreeCorrecte = entree;
	}

	public void setEntreeUtil(String entreeUtil)
	{
		this.entreeUtil = entreeUtil;
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
		do
		{
			System.out.println("Entrez une combinaison de " + x + " chiffres (0-9): ");

			setEntreeUtil(scan.nextLine());
			setEntreeCorrecte(isNumeric(getEntreeUtil()));

		} while (!getEntreeCorrecte() || !testLongueur(getEntreeUtil(), x));

		setPropositionString(getEntreeUtil());

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
			setEntreeUtil(scan.nextLine());
			setEntreeCorrecte(isNumeric(getEntreeUtil()));
		} while (!getEntreeCorrecte() || !testLongueur(getEntreeUtil(), x));

		setPropositionString(getEntreeUtil());

		for (int i = 0; i < this.getPropositionString().length(); i++)
		{
			if (i > -1 && i < this.getPropositionString().length())
			{
				char c = this.getPropositionString().charAt(i);
				this.setPropositionTab(i, Character.getNumericValue(c));
			}
		}
	}

	/**
	 * Renvoie true si le string est composé uniquement de chiffres/nombre, else
	 * sinon
	 * 
	 * @param str
	 *            cdc à tester
	 * @return true or false
	 */
	public boolean isNumeric(String str)
	{
		try
		{
			double d = Double.parseDouble(str);
		} catch (NumberFormatException nfe)
		{
			System.out.println("\nAttention, vous n'avez pas entré les bons caractères , il faut des chiffres ! ");
			return false;
		}
		return true;
	}

	/**
	 * Teste la longueur de str et renvoie true si elle est égale à taille, false
	 * sinon
	 * 
	 * @param str
	 *            cdc à tester
	 * @param taille
	 *            taille attendue
	 * @return true or false
	 */
	public boolean testLongueur(String str, Integer taille)
	{
		if (str.length() == taille)
		{
			return true;
		} else
		{
			System.out.println("\nAttention, vous n'avez pas entré le bon nombre de chiffre ! ");
			return false;
		}
	}

}
