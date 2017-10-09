package Utilisateur;
import java.util.Scanner;

public class Joueur extends Utilisateur
{
	/******* VARIABLES *****************************************************************************************/
	
	/******* FONCTIONS *****************************************************************************************/
	/********************
	 * Constructeur 	*
	 *******************/
	public Joueur()
	{
		super();
	}
	
	/********************************************
	 * Demande de création d'une combinaison	*
	 *******************************************/	
	public void combi()
	{
		super.combi();
		Scanner scan = new Scanner(System.in);
		System.out.println("Entrez une combinaison de 4 chiffres (0-9): ");	
		setCombi(scan.nextInt());
		setCombiTab(decoupage(getCombi()));
	}
	
	/****************************************
	 * Demande de proposition de réponse	*
	 ***************************************/
	public void cherche()
	{
		System.out.println("Entrez une combinaison de 4 chiffres (0-9): ");	
		Scanner scan = new Scanner(System.in);
		setProposition(scan.nextInt());
		setPropositionTab(decoupage(getProposition()));
	}
	
}
