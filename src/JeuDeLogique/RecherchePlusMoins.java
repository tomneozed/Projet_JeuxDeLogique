package JeuDeLogique;

import java.util.Scanner;

import Configurations.ConfigRPM;
import Configurations.ConfigurationRPM;
import Utilisateur.Joueur;
import Utilisateur.Ordi;
import Utilisateur.Utilisateur;

/**
 * Classe fille de JeuDeLogique décrivant le fonctionnement du Recherche+/-
 * 
 * @author Thomas Pelissier
 * @version 1.0
 *
 */
public class RecherchePlusMoins extends JeuDeLogique
{
	//Attributs
	protected ConfigurationRPM configRPM = ConfigRPM.loadConfigRPM();
	protected Integer NB_CASES_COMBI = configRPM.getNbrCasesCombiRPM();
	protected Integer NB_ESSAIS = configRPM.getNbrEssaisRPM();

	//Constructeur
	public RecherchePlusMoins()
	{
		super();
		ordi = new Ordi(NB_CASES_COMBI);
		joueur = new Joueur(NB_CASES_COMBI);
	}

	/**
	 * Mode challenger : le joueur doit trouver la combinaison de l'ordinateur
	 */
	public void challengerMode()
	{
		System.out.println("Bienvenue dans le Recherche +/- mode Challenger !");
		Scanner scan = new Scanner(System.in);
		int tour = 0;
		do
		{
			tour = 0;
			//On initialise les objets joueur et ordi
			ordi.initialisation(NB_CASES_COMBI);
			joueur.initialisation(NB_CASES_COMBI);

			joueur.setEssais(NB_ESSAIS);

			//L'ordi créé un nombre aléatoire
			ordi.combi(NB_CASES_COMBI);

			//Si le mode developpeur est activé, on donne la réponse
			if (configRPM.getModeDeveloppeur())
			{
				reponse(ordi);
			}

			do
			{
				tour++;
				System.out.println("\n--------------- Tour " + tour + " ---------------\n");
				tourDuJoueur(ordi, joueur);
				System.out.println("\nNombre d'essais restants : " + joueur.getEssais());

			} while (!getGagneJoueur() && joueur.getEssais() > 0);	//On tourne jusqu'a ce que l'utilisateur gagne ou perde toutes ses vies	

			if (getGagneJoueur() == true)
			{
				System.out.println("Bravo ! Vous avez gagné :)");
			} else if (getGagneJoueur() == false)
			{
				System.out.println("Dommage, meilleures chances la prochaine fois ! \nLa réponse était : ");
				reponse(ordi);
			}

			System.out.println("\nVoulez-vous rejouer ? \n\n\t1. oui \t\t2.non");
			setRejouer(scan.nextInt());

		} while (getRejouer() == 1);
		//scan.close();
	}

	/**
	 * Mode defenseur : l'ordinateur doit trouver la combinaison du joueur
	 */
	public void defenseurMode()
	{
		System.out.println("Bienvenue dans le Recherche +/- mode Défenseur !");
		Scanner scan = new Scanner(System.in);
		int tour = 0;

		//Début de la boucle "Rejouer"
		do
		{
			tour = 0;
			//On initialialise les attributs de l'ordi et du joueur
			ordi.initialisation(NB_CASES_COMBI);
			joueur.initialisation(NB_CASES_COMBI);
			ordi.setEssais(NB_ESSAIS);

			//Le joueur enre la combianison à trouver
			joueur.combi(NB_CASES_COMBI);

			//Si le mode developpeur est activé, on donne la réponse
			if (configRPM.getModeDeveloppeur())
			{
				reponse(joueur);
			}

			//C'est le tour de l'ordi	
			do
			{
				tour++;
				System.out.println("\n--------------- Tour " + tour + " ---------------\n");
				tourDeLOrdi(ordi, joueur, 1);
				try
				{
					Thread.sleep(1000);
				} catch (Exception e)
				{
					System.out.println(e);
				}

			} while (!getGagneOrdi() && ordi.getEssais() > 0);

			if (getGagneOrdi() == true)
			{
				System.out.println("Bravo ordinateur, vous avez gagné :)");
			} else if (getGagneOrdi() == false)
			{
				System.out.println("Dommage, meilleures chances la prochaine fois ! \nLa réponse était : ");
				reponse(ordi);
			}

			//Fin de la partie, on demande si le joueur veut rejouer			
			System.out.println("\nVoulez-vous rejouer ? \n\n\t1. oui \t\t2.non");
			setRejouer(scan.nextInt());

		} while (getRejouer() == 1);
		//scan.close();
	}

	/**
	 * Mode duel : le joueur et l'ordinateur jouent l'un contre l'autre
	 */
	public void duelMode()
	{
		System.out.println("Bienvenue dans le Recherche +/- mode Duel !");
		Scanner scan = new Scanner(System.in);
		int tour = 0;
		do
		{
			tour = 0;
			// On initialise les objets joueur et ordi
			ordi.initialisation(NB_CASES_COMBI);
			joueur.initialisation(NB_CASES_COMBI);

			ordi.setEssais(NB_ESSAIS);
			joueur.setEssais(NB_ESSAIS);

			//Le joueur entre sa combinaison :
			joueur.combi(NB_CASES_COMBI);

			//L'ordi entre sa combianaison :
			ordi.combi(NB_CASES_COMBI);

			//On regarde les 2 combianaisons :
			if (configRPM.getModeDeveloppeur())
			{
				reponse(ordi);
				reponse(joueur);
			}

			System.out.println("\n\n");
			do
			{
				tour++;
				System.out.println("\n------------------- Tour " + tour + " -------------------\n");

				//Le joueur commence
				System.out.println("\t* * * Tour du joueur * * *\n");
				tourDuJoueur(ordi, joueur);

				//Tour de l'ordinateur:
				System.out.println("\n\t* * * Tour de l'ordi * * *\n");
				tourDeLOrdi(ordi, joueur, 0);

			} while (!getGagneOrdi() && !getGagneJoueur());

			System.out.println("\n\n\n------------------- Fin de partie -------------------");
			if (getGagneJoueur() == false && getGagneOrdi() == true)
			{
				System.out.println("L'ordi a gagné ! La réponse était : ");
				reponse(ordi);
			} else if (getGagneJoueur() == true && getGagneOrdi() == false)
			{
				System.out.println("Vous avez gagné !");
			} else if (getGagneJoueur() == true && getGagneOrdi() == true)
			{
				System.out.println("Egalité : vous et l'ordinateur avez gagné !");
			}

			//Fin de la partie, on demande si le joueur veut rejouer			
			System.out.println("\nVoulez-vous rejouer ? \n\n\t1. oui \t\t2.non");
			setRejouer(scan.nextInt());

		} while (getRejouer() == 1);
		//scan.close();
	}

	//Methodes

	/**
	 * Tour du joueur
	 * 
	 * @param o
	 *            ordinateur
	 * @param j
	 *            joueur
	 */
	public void tourDuJoueur(Ordi o, Joueur j)
	{
		j.cherche(NB_CASES_COMBI);

		setGagneJoueur(analyseTrouve(compareTab(o.getCombiTab(), j.getPropositionTab(), 1)));	//On compare les réponses

		if (getGagneJoueur() == false)														//Si c'est faux ...
			j.setEssais(j.getEssais() - 1);															//...l'utilisateur perd 1 essai
	}

	/**
	 * Tour de l'ordinateur : fonctionnement de recherche de la réponse
	 * 
	 * @param o
	 *            ordinateur
	 * @param j
	 *            joueur
	 * @param i
	 *            0 : n'affiche pas la comparaison, 1 : affiche la comparaison
	 */
	public void tourDeLOrdi(Ordi o, Joueur j, Integer i)
	{
		System.out.println("Je cherche ...");

		o.cherche();
		o.setComparaisonTab(compareTab(j.getCombiTab(), o.getPropositionTab(), i));
		setGagneOrdi(analyseTrouve(o.getComparaisonTab()));
		o.analyse();
		if (getGagneOrdi() == false)
			o.setEssais(o.getEssais() - 1);
	}

	/**
	 * Affiche la combinaison de l'utilisateur
	 * 
	 * @param u
	 *            utilisateur
	 * @param k
	 *            0 : mode normal, 1 : mode développeur
	 */
	public void reponse(Utilisateur u)
	{
		super.reponse();

		System.out.print("\t\t");
		for (int i = 0; i < NB_CASES_COMBI; i++)
		{
			System.out.print(u.getCombiTab(i) + " ");
		}
		System.out.println("\n# # # # # # # # # # # # # # # # # # # # # # # # # # # # ");
	}
}
