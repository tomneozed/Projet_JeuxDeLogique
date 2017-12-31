package JeuDeLogique;

import java.util.Scanner;

import org.apache.log4j.Logger;

import Configurations.ConfigMMD;
import Configurations.ConfigurationMMD;
import Tables.ColonneTerminee;
import Tables.IndiceTab;
import Tables.MasterTable;
import Tables.TestTables;
import Utilisateur.Joueur;
import Utilisateur.Ordi;

/**
 * @author Thomas Pelissier
 *
 */
public class Mastermind extends JeuDeLogique
{
	//Attributs de classe
	private int BP, MP;

	Ordi ordi = new Ordi();
	Joueur joueur = new Joueur();

	//Attributs des properties
	ConfigurationMMD config = ConfigMMD.loadConfig();
	Integer NB_CASES_COMBI = config.getNombreCasesCombi();
	Integer NB_ESSAIS = config.getEssais();
	Integer NB_COULEURS = config.getNombreCouleurs();

	//Logger
	private static Logger logger = Logger.getLogger(TestTables.class);

	//Ajout des objets tables
	IndiceTab indiceTable = new IndiceTab(NB_COULEURS.intValue());
	ColonneTerminee colonneTerminee = new ColonneTerminee(NB_CASES_COMBI.intValue());
	MasterTable masterTable = new MasterTable(NB_CASES_COMBI.intValue());
	//Propo propo = new Propo(NB_CASES_COMBI.intValue());

	//Divers
	Integer[] combi = new Integer[NB_CASES_COMBI.intValue()];
	Integer[] jat = masterTable.jATrouver(colonneTerminee.getT());

	//Constructeur
	public Mastermind()
	{
		super();
	}

	/*----------------------------------------Accesseurs et mutateurs------------------------------------------*/
	/****** GETTERS ******/
	public int getBP()
	{
		return this.BP;
	}

	public int getMP()
	{
		return this.MP;
	}

	/****** SETTERS ******/
	public void setBP(int b)
	{
		this.BP = b;
	}

	public void setMP(int m)
	{
		this.MP = m;
	}

	/*--------------------------------------------Mode Challenger----------------------------------------------*/
	/***************************************
	 * Programme de jeu en mode Challenger *
	 ***************************************/
	public void challengerMode()
	{
		System.out.println("Bienvenue dans le Mastermind mode Challenger !");
		System.out.println("Param�tres : " + config.toString());

		Scanner scan = new Scanner(System.in);
		do
		{
			// On initialise les objets joueur et ordi
			ordi.initialisation();
			joueur.initialisation();
			joueur.setVie(NB_ESSAIS);

			// L'ordi cr�� une combinaison al�atoire
			ordi.combi(NB_CASES_COMBI);
			System.out.println("\n");

			do
			{
				tourDuJoueur(joueur, ordi);
				System.out.println("\nNombre d'essais restants : " + joueur.getVie());
			} while (BP != NB_CASES_COMBI && joueur.getVie() != 0);

			if (BP == NB_CASES_COMBI)
			{
				System.out.println("Bravo , vous avez gagn� !");
			}
			System.out.println("\nVoulez-vous rejouer ? \n\n\t1. oui \t\t2.non");
			setRejouer(scan.nextInt());

		} while (getRejouer() == 1);
	}

	/*--------------------------------------------Mode Defenseur----------------------------------------------*/
	/***************************************
	 * Programme de jeu en mode Defenseur *
	 ***************************************/
	public void defenseurMode()
	{
		System.out.println("Bienvenue dans le Mastermind mode Defenseur !");
		Scanner scan = new Scanner(System.in);
		Integer[] tab = new Integer[NB_CASES_COMBI];
		do
		{
			// On initialise les objets joueur et ordi
			ordi.initialisation();
			joueur.initialisation();
			ordi.setVie(NB_ESSAIS);

			//On demande � l'utilisateur de cr�er une combi
			joueur.combi(NB_CASES_COMBI);

			for (int i = 0; i < NB_CASES_COMBI; i++)
			{
				tab[i] = 0;
			}
			ordi.getPropoTab().setT(tab);

			//masterTable.afficheMT();
			//indiceTable.afficheIT();

			do
			{
				jat = masterTable.jATrouver(colonneTerminee.getT());
				tourDeLOrdi();

				System.out.println("\n\tNouvelle proposition : ");
				ordi.getPropoTab().affichePropo();
				//bienMalPlace(this.combi, propo.getT());
				logger.debug("Fin du tour");
			} while (BP != NB_CASES_COMBI && ordi.getVie() != 0);

			if (BP == NB_CASES_COMBI)
			{
				System.out.println("Bravo ordi, vous avez gagn� !");
			}
			System.out.println("\nVoulez-vous rejouer ? \n\n\t1. oui \t\t2.non");
			setRejouer(scan.nextInt());
		} while (getRejouer() == 1);

	}

	/*------------------------------------------Fonctions commmunes--------------------------------------------*/

	/**
	 * Tour du joueur
	 * 
	 * @param j
	 *            objet joueur
	 * @param o
	 *            objet Ordi
	 */
	public void tourDuJoueur(Joueur j, Ordi o)
	{
		joueur.cherche(NB_CASES_COMBI.intValue());

		bienMalPlace(ordi.getCombiTab(), joueur.getPropositionTab());
		System.out.println("BP : " + getBP());
		System.out.println("MP : " + getMP());
		if (BP != NB_CASES_COMBI)
		{
			joueur.setVie(joueur.getVie() - 1);
		}
	}

	/**
	 * Renvoie le nombre de fois x contenue dans tab
	 */
	public int compteCombien(int x, int[] tab)
	{
		int nombre = 0;

		for (int i = 0; i < tab.length; i++)
		{
			if (x == tab[i])
			{
				nombre++;
			}
		}
		return nombre;
	}

	/**
	 * Renvoie un tableau contenant les indices des positions de x dans tab
	 */
	public int[] getPos(int x, int[] tab)
	{
		int[] posTab = new int[compteCombien(x, tab)];
		int j = 0;
		for (int i = 0; i < tab.length; i++)
		{
			if (tab[i] == x)
			{
				posTab[j] = i;
				j++;
			}
		}
		return posTab;
	}

	/**
	 * Tour de l'ordi
	 */
	public void tourDeLOrdi()
	{

		ordi.getPropoTab().propoXouXY();
		bienMalPlace(joueur.getCombiTab(), ordi.getPropoTab().getT());

		// On regarde si la proposition est de type X ou XY ou chercheY
		if (ordi.getPropoTab().getY() != -1 && ordi.getPropoTab().getXouXY(0, 1) == ordi.getPropoTab().getTaille() - 1)	// propoXY
		{
			propoXY();

		} else if (ordi.getPropoTab().getY() == ordi.getPropoTab().getX()
				&& ordi.getPropoTab().getXouXY(0, 1) != ordi.getPropoTab().getTaille() - 1)	//PropoChercheY
		{
			propoChercheY();

		} else	// propoX
		{
			propoX();
		}
		MAJTables();

		System.out.println("BP : " + getBP());
		System.out.println("MP : " + getMP());
		if (BP != NB_CASES_COMBI)
		{
			ordi.setVie(joueur.getVie() - 1);
		}

	}

	/**
	 * Fonction si propo = propoX
	 */
	public void propoX()
	{
		logger.debug("PropoX");
		if (BP == 0)
		{
			logger.debug("BP = 0");
			indiceTable.setValeur(ordi.getPropoTab().getX(), 0);		// On remplit IT � la position X(XouXY[0][0]) 0 fois

			jat = masterTable.jATrouver(colonneTerminee.getT());

			indiceTable.setPremierNullIT(indiceTable.cherchePremierNull());

			ordi.getPropoTab().setT(ordi.getPropoTab().propoX(indiceTable.getPremierNullIT()));
		} else if (BP > 0)
		{
			logger.debug("BP > 0");
			indiceTable.setValeur(ordi.getPropoTab().getX(), BP);		// On remplit IT � la position X(XouXY[0][0]) 'BP' fois
			this.masterTable.initPremiereLigneMT(indiceTable.getT());

			jat = this.masterTable.jATrouver(colonneTerminee.getT());
			System.out.println("jat : " + jat[0] + ", " + jat[1]);

			indiceTable.setPremierNullIT(indiceTable.cherchePremierNull());
			ordi.getPropoTab().setT(ordi.getPropoTab().propoXY(indiceTable.getPremierNullIT(),
					this.masterTable.getValeur(0, jat[0]), (jat[1] - 1), this.masterTable.getMT()));
		}
	}

	/**
	 * Fonction si propo = propoChercheY
	 */
	public void propoChercheY()
	{
		logger.debug("PropoChercheY");
		if (this.BP == ordi.getPropoTab().getTaille() - 1 && this.MP == 0)
		{
			indiceTable.setValeur(indiceTable.cherchePremierNull(), 0);
		}

		if (combiTrouvee() == true)
		{
			ordi.getPropoTab().setT(ordi.getPropoTab().propoFinale(this.masterTable.getMT()));

		} else if (indiceBon() == true)
		{
			ordi.getPropoTab().setT(ordi.getPropoTab().propoChercheY(masterTable.getMT(), indiceTable));
		} else
		{
			colonneTerminee.setT(masterTable.majColonneTerminee(colonneTerminee.getT()));
			jat = masterTable.jATrouver(colonneTerminee.getT());

			indiceTable.setPremierNullIT(indiceTable.cherchePremierNull());
			ordi.getPropoTab().setT(ordi.getPropoTab().propoXY(indiceTable.getPremierNullIT(),
					this.masterTable.getValeur(jat[0], 0), (jat[1] - 1), this.masterTable.getMT()));
		}
	}

	/**
	 * Fonction si propo = propoXY
	 * 
	 */
	public void propoXY()
	{
		logger.debug("PropoXY");

		indiceTable.setValeur(ordi.getPropoTab().getX(), ((BP + MP) - 1));

		MAJTables();

		// 1er cas : BP > 0 & MP = 0
		if (this.MP == 0)
		{
			if (this.BP >= 1)
			{
				logger.debug("MP = 0	BP >= 1");
				//On indique l'indice bon
				masterTable.indiceBon(jat[0], jat[1]);

				//On indique que sa colonne est donc termin�e
				colonneTerminee.setT(masterTable.majColonneTerminee(colonneTerminee.getT()));

				//On met � jour la premiere ligne de MT 
				masterTable.initPremiereLigneMT(indiceTable.getT());
			}

		} else

		// 2eme cas : BP = 0 & MP = 1
		if (this.MP == 1)
		{
			masterTable.indiceARayer(jat[0], jat[1]);
			masterTable.nombreARayer(ordi.getPropoTab().getY(), jat[1]);
			masterTable.nombreARayer(ordi.getPropoTab().getX(), jat[1]);

			if (this.BP == 1)
			{
				logger.debug("MP = 1	BP = 1");

				masterTable.initPremiereLigneMT(indiceTable.getT());
				// On remplit la premiere ligne de MT 'getIT(X)' fois, c�d
				// de '0 � mt.length' fois

				masterTable.indiceARayer(masterTable.indexOfY(ordi.getPropoTab().getX()),
						ordi.getPropoTab().indexOf(ordi.getPropoTab().getY()) + 1);

				int iy2MT = 0;

				if (iy2MT < masterTable.getTaille())
				{
					while (this.masterTable.getValeur(iy2MT, 0) > -1 && this.masterTable.getValeur(iy2MT, 0) < 10)
					{
						if (this.masterTable.getValeur(iy2MT, 0) == ordi.getPropoTab().getX())
						{
							masterTable.indiceARayer(iy2MT, jat[1]);
						} else
						{
							break;
						}
					}
					iy2MT++;
				}
			}

		} else

		if (this.MP == 2)
		{
			masterTable.indiceBon(masterTable.indexOfY(ordi.getPropoTab().getX()),
					ordi.getPropoTab().indexOf(ordi.getPropoTab().getY()) + 1);

			if (this.BP == 0)
			{
				logger.debug("MP = 2	BP = 0");
				masterTable.initPremiereLigneMT(indiceTable.getT());	// On remplit la premiere ligne de MT 'getIT(X)'
				// fois, c�d de '0 � mt.length' fois
				masterTable.indiceARayer(jat[0], jat[1]);
			}
		}

		logger.debug("__MAJ TABLES__");

		MAJTables();

		//On teste les tables pour savoir si la MT a �t� d�chiffr�e

		if (combiTrouvee() == true)
		{
			ordi.getPropoTab().setT(ordi.getPropoTab().propoFinale(this.masterTable.getMT()));

		} else if (indiceBon() == true)
		{
			ordi.getPropoTab().setT(ordi.getPropoTab().propoChercheY(masterTable.getMT(), indiceTable));
		} else
		{
			colonneTerminee.setT(masterTable.majColonneTerminee(colonneTerminee.getT()));

			jat = masterTable.jATrouver(colonneTerminee.getT());

			indiceTable.setPremierNullIT(indiceTable.cherchePremierNull());
			ordi.getPropoTab().setT(ordi.getPropoTab().propoXY(indiceTable.getPremierNullIT(),
					this.masterTable.getValeur(jat[0], 0), (jat[1] - 1), this.masterTable.getMT()));
		}
	}

	/**
	 * Met � jour les tables : MasterTable, IndiceTable, ColonneTerminee
	 */
	public void MAJTables()
	{
		masterTable.majMT();
		indiceTable.majIT();
		masterTable.initPremiereLigneMT(indiceTable.getT());
		colonneTerminee.setT(masterTable.majColonneTerminee(colonneTerminee.getT()));
	}

	/**
	 * Compare les 2 tables en entr�e et incr�mente BP(rond noir) et MP(rond blanc)
	 * selon la comparaison
	 * 
	 * @param comb
	 *            combinaison
	 * @param prop
	 *            proposition
	 */
	public void bienMalPlace(Integer[] comb, Integer[] prop)
	{
		logger.debug("bienMalPlace()");
		Integer[] copieProp = new Integer[NB_CASES_COMBI];
		Integer[] copieComb = new Integer[NB_CASES_COMBI];

		for (int i = 0; i < NB_CASES_COMBI; i++)
		{
			copieProp[i] = prop[i];
			copieComb[i] = comb[i];
		}

		initBPMP();
		System.out.println("\n");
		int i = 0, j = 0;
		int r = 0;

		for (i = 0; i < NB_CASES_COMBI; i++)//check BP
		{
			if (i < NB_CASES_COMBI && copieComb[i] > -1 && copieComb[i] < 10 && copieProp[i] > -1 && copieProp[i] < 10)
			{
				if (copieComb[i] == copieProp[i])
				{
					//System.out.println("Combi[" + i + "] : " + copieComb[i] + " | Propo[" + i + "] : " + copieProp[i]);
					this.BP++;
					copieProp[i] = -1;
					copieComb[i] = -1;
				}
			}
		}
		i = 0;
		while (i < NB_CASES_COMBI) // i est l'indice de comb[]
		{
			for (j = 0; j < NB_CASES_COMBI; j++)
			{
				if (copieComb[i] > -1 && copieComb[i] < 10 && copieProp[j] > -1 && copieProp[j] < 10)
				{
					if (copieComb[i] == copieProp[j])
					{
						//System.out.println("Combi[" + i + "] : " + copieComb[i] + " | Propo[" + j + "] : " + copieProp[j]);
						this.MP++;
						copieProp[j] = -1;
						copieComb[i] = -1;
					}
				}
			}
			i++;
		}
	}

	public void initBPMP()
	{
		setBP(0);
		setMP(0);
	}

	/**
	 * Renvoie 'true' si : - la table 'colonneTerminee' est remplie de 'true' ET -
	 * il n'y a plus de Y � trouver (si la 1ere ligne de la MT est compl�te)
	 * 
	 */
	public Boolean combiTrouvee()
	{
		Boolean ct = false;

		if (this.colonneTerminee.terminee() == true && this.masterTable.yATrouver() == false)
		{
			ct = true;
		}
		logger.debug("combiTrouvee() -> " + ct);
		return ct;
	}

	/**
	 * Renvoie 'true' si tous les indices ont �t�s trouv�s
	 * 
	 * @return
	 */
	public Boolean indiceBon()
	{
		logger.debug("indiceBon()");
		Boolean ib = false;
		int compte = 0;
		for (int i = 0; i < this.masterTable.getLongueur(); i++)
		{
			for (int j = 1; j < this.masterTable.getLargeur(); j++)
			{
				if (this.masterTable.getValeur(i, j) > 0 && this.masterTable.getValeur(i, j) < 11)
				{
					compte++;
				}
			}
		}

		if (compte == this.masterTable.getLongueur())
		{
			ib = true;
		}

		return ib;

	}

}
