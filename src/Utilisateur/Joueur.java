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
		Scanner scan = new Scanner(System.in);
		System.out.println("Entrez une combinaison de 4 chiffres : ");	
		setCombi(scan.nextInt());
		setCombiTab(decoupage(getCombi()));
	}
	
	/****************************************
	 * Demande de proposition de réponse	*
	 ***************************************/
	public void cherche()
	{
		Scanner scan = new Scanner(System.in);
		setProposition(scan.nextInt());
		setPropositionTab(decoupage(getProposition()));
	}
	
}
