package JeuDeLogique;

import java.util.Scanner;

import org.apache.log4j.Logger;

import Configurations.ConfigMMD;
import Configurations.ConfigurationMMD;
import Tables.ColonneTerminee;
import Tables.IndiceTab;
import Tables.MasterTable;
import Utilisateur.Joueur;
import Utilisateur.Ordi;
import Utilisateur.Utilisateur;

/**
 * Classe fille de JeuDeLogique décrivant le fonctionnement du Mastermind
 * 
 * @author Thomas Pelissier
 * @version 1.0
 */
public class Mastermind extends JeuDeLogique
{
	//Attributs 
	protected ConfigurationMMD configMMD = ConfigMMD.loadConfigMMD();
	protected Integer NB_CASES_COMBI = configMMD.getNbrCasesCombiMMD();
	protected Integer NB_ESSAIS = configMMD.getNbrEssaisMMD();
	protected Integer NB_COULEURS = configMMD.getNbrCouleursMMD();

	private int BPj, MPj, BP, MP;

	//Logger
	private static Logger logger = Logger.getLogger(Mastermind.class);

	//Ajout des objets tables
	protected IndiceTab indiceTable = new IndiceTab(NB_COULEURS.intValue());
	protected ColonneTerminee colonneTerminee = new ColonneTerminee(NB_CASES_COMBI.intValue());
	protected MasterTable masterTable = new MasterTable(NB_CASES_COMBI.intValue());

	//Divers
	protected Integer[] jat = masterTable.jATrouver(colonneTerminee.getT());

	//Constructeur
	public Mastermind()
	{
		super();
		ordi = new Ordi(NB_CASES_COMBI);
		joueur = new Joueur(NB_CASES_COMBI);
	}

	//Getters & Setters
	public int getBPj()
	{
		return this.BPj;
	}

	public int getMPj()
	{
		return this.MPj;
	}

	public int getBPo()
	{
		return BP;
	}

	public int getMPo()
	{
		return MP;
	}

	public void setBPj(int b)
	{
		this.BPj = b;
	}

	public void setMPj(int m)
	{
		this.MPj = m;
	}

	public void setBPo(int bPo)
	{
		BP = bPo;
	}

	public void setMPo(int mPo)
	{
		MP = mPo;
	}

	//Methodes 

	/**
	 * Mode challenger : le joueur doit trouver la combinaison de l'ordinateur
	 */
	public void challengerMode()
	{
		logger.debug("Mode Challenger");
		System.out.println("Bienvenue dans le Mastermind mode Challenger !");

		int tour = 0;
		Scanner scan = new Scanner(System.in);
		do
		{
			// On initialise les objets joueur et ordi
			ordi.initialisation(NB_CASES_COMBI);
			joueur.initialisation(NB_CASES_COMBI);
			joueur.setVie(NB_ESSAIS);

			// L'ordi créé une combinaison aléatoire
			ordi.combi(NB_CASES_COMBI);

			//Si le mode developpeur est activé, on donne la réponse
			reponse(ordi, 1);

			do
			{
				tour++;
				logger.debug("Debut du tour " + tour);
				System.out.println("\n------------------ Tour " + tour + " ------------------\n");
				tourDuJoueur();
				System.out.println("\nNombre d'essais restants : " + joueur.getVie());
			} while (!getGagneJoueur() && joueur.getVie() > 0);

			if (BPj == NB_CASES_COMBI)
			{
				System.out.println("Bravo , vous avez gagné !");
			} else
			{
				System.out.println("Dommage, meilleures chances la prochaine fois ! \nLa réponse était : ");
				reponse(ordi, 0);
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
		logger.debug("Mode Defenseur");
		System.out.println("Bienvenue dans le Mastermind mode Defenseur !");
		Scanner scan = new Scanner(System.in);
		Integer[] tab = new Integer[NB_CASES_COMBI];
		int tour = 0;
		do
		{
			//Initialisation des tables
			initTables();

			// On initialise les objets joueur et ordi
			ordi.initialisation(NB_CASES_COMBI);
			joueur.initialisation(NB_CASES_COMBI);
			ordi.setVie(NB_ESSAIS);

			//On demande à l'utilisateur de créer une combi
			joueur.combi(NB_CASES_COMBI);

			//Si le mode developpeur est activé, on donne la réponse
			reponse(joueur, 1);

			for (int i = 0; i < NB_CASES_COMBI; i++)
			{
				tab[i] = 0;
			}
			ordi.getPropoTab().setT(tab);

			do
			{
				tour++;
				logger.debug("Debut du tour " + tour);
				System.out.println("\n------------------ Tour " + tour + " ------------------\n");
				masterTable.afficheMT();
				indiceTable.afficheIT();
				tourDeLOrdi();

				System.out.println("\n\tNouvelle proposition : ");
				ordi.getPropoTab().affichePropo();
				logger.debug("Fin du tour");
			} while (!getGagneOrdi() && ordi.getVie() > 0);

			if (BP == NB_CASES_COMBI)
			{
				System.out.println("Bravo ordi, vous avez gagné !");
			} else
			{
				System.out.println("Dommage, meilleures chances la prochaine fois ! \nLa réponse était : ");
				reponse(joueur, 0);
			}
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
		logger.debug("Mode Duel");
		System.out.println("Bienvenue dans le Mastermind mode Duel !");
		Scanner scan = new Scanner(System.in);
		Integer[] tab = new Integer[NB_CASES_COMBI];
		int tour = 0;
		do
		{
			//Initialisation des tables
			initTables();

			// On initialise les objets joueur et ordi
			ordi.initialisation(NB_CASES_COMBI);
			joueur.initialisation(NB_CASES_COMBI);
			ordi.setVie(NB_ESSAIS);
			joueur.setVie(NB_ESSAIS);

			//Le joueur entre sa combinaison :
			joueur.combi(NB_CASES_COMBI);

			//L'ordi entre sa combianaison :
			ordi.combi(NB_CASES_COMBI);

			//On regarde les 2 combianaisons :
			reponse(ordi, 1);
			reponse(joueur, 1);

			for (int i = 0; i < NB_CASES_COMBI; i++)
			{
				tab[i] = 0;
			}
			ordi.getPropoTab().setT(tab);

			do
			{
				tour++;
				logger.debug("Debut du tour " + tour);
				System.out.println("\n------------------ Tour " + tour + " ------------------\n");

				if (configMMD.getModeDeveloppeur())
				{
					System.out.println("Mode développeur activé :");
					masterTable.afficheMT();
					indiceTable.afficheIT();
				}

				//Le joueur commence
				System.out.println("************* Tour du joueur *********************");
				tourDuJoueur();

				//Tour de l'ordinateur:
				System.out.println("************* Tour de l'ordi *********************");
				tourDeLOrdi();

				System.out.println("\n\tNouvelle proposition : ");
				ordi.getPropoTab().affichePropo();
				logger.debug("Fin du tour");
			} while (!getGagneJoueur() && !getGagneOrdi() && ordi.getVie() > 0);

			if (getGagneJoueur() && getGagneOrdi())
			{
				System.out.println("Egalité : vous et l'ordinateur avez gagné !");
			} else if (!getGagneJoueur() && getGagneOrdi())
			{
				System.out.println("L'ordi a gagné ! La réponse était : ");
				reponse(ordi, 0);
			} else if (getGagneJoueur() && !getGagneOrdi())
			{
				System.out.println("Bravo ! Vous avez gagné !");
			}
			System.out.println("\nVoulez-vous rejouer ? \n\n\t1. oui \t\t2.non");
			setRejouer(scan.nextInt());
		} while (getRejouer() == 1);
	}

	//Methodes 

	/**
	 * Initialise BP et MP à 0
	 */
	public void initBPMP()
	{
		setBPj(0);
		setMPj(0);
		setMPo(0);
		setBPo(0);
	}

	/**
	 * Initialise les tables
	 */
	public void initTables()
	{
		indiceTable.init();
		masterTable.initMT();
		colonneTerminee.init();
	}

	/**
	 * Met à jour les tables : MasterTable, IndiceTable, ColonneTerminee
	 */
	public void MAJTables()
	{
		masterTable.majMT();
		indiceTable.majIT();
		masterTable.initPremiereLigneMT(indiceTable.getT());
		colonneTerminee.setT(masterTable.majColonneTerminee(colonneTerminee.getT()));
	}

	/**
	 * Tour du joueur
	 */
	public void tourDuJoueur()
	{
		joueur.cherche(NB_CASES_COMBI.intValue());

		bienMalPlace(ordi.getCombiTab(), joueur.getPropositionTab());
		System.out.println("Bien Placés 0 : " + getBPj());
		System.out.println("Mal Placés O : " + getMPj());
		if (BPj == NB_CASES_COMBI)
		{
			setGagneJoueur(true);
		} else
		{
			setGagneJoueur(false);
			joueur.setVie(joueur.getVie() - 1);
		}
	}

	/**
	 * Tour de l'ordi : fonctionnement de la recherche de la réponse
	 */
	public void tourDeLOrdi()
	{
		jat = masterTable.jATrouver(colonneTerminee.getT());
		ordi.getPropoTab().propoXouXY();
		bienMalPlace(joueur.getCombiTab(), ordi.getPropoTab().getT());

		// On regarde si la proposition est de type X ou XY ou chercheY
		//PropoX
		if (ordi.getPropoTab().getStructurePropo() == 1)
		{
			propoX();
		} else
		//PropoXY
		if (ordi.getPropoTab().getStructurePropo() == 2)
		{
			propoXY();

		} else
		//PropoChercheY
		if (ordi.getPropoTab().getY() == ordi.getPropoTab().getX()
				&& ordi.getPropoTab().getXouXY(0, 1) != ordi.getPropoTab().getTaille() - 1)
		{
			propoChercheY();

		}
		MAJTables();

		System.out.println("Bien Placés 0 : " + getBPo());
		System.out.println("Mal Placés O : " + getMPo());
		if (BP == NB_CASES_COMBI)
		{
			setGagneOrdi(true);
		} else
		{
			setGagneOrdi(false);
			ordi.setVie(ordi.getVie() - 1);
		}

	}

	/**
	 * Fonction si propo = propoX
	 */
	public void propoX()
	{
		logger.debug("PropoX");
		if (BP == NB_CASES_COMBI)
		{

		} else if (BP == 0)
		{
			logger.debug("BP = 0");
			indiceTable.setValeur(ordi.getPropoTab().getX(), 0);		// On remplit IT à la position X(XouXY[0][0]) 0 fois

			jat = masterTable.jATrouver(colonneTerminee.getT());

			indiceTable.setPremierNullIT(indiceTable.cherchePremierNull());

			ordi.getPropoTab().setT(ordi.getPropoTab().propoX(indiceTable.getPremierNullIT()));
		} else if (BP > 0)
		{
			logger.debug("BP > 0");
			indiceTable.setValeur(ordi.getPropoTab().getX(), BP);		// On remplit IT à la position X(XouXY[0][0]) 'BP' fois
			this.masterTable.initPremiereLigneMT(indiceTable.getT());

			jat = this.masterTable.jATrouver(colonneTerminee.getT());

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

				//On indique que sa colonne est donc terminée
				colonneTerminee.setT(masterTable.majColonneTerminee(colonneTerminee.getT()));

				//On met à jour la premiere ligne de MT 
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
				// On remplit la premiere ligne de MT 'getIT(X)' fois, càd
				// de '0 à mt.length' fois

				masterTable.indiceARayer(masterTable.indexOfY(ordi.getPropoTab().getX()),
						ordi.getPropoTab().indexOf(ordi.getPropoTab().getY()) + 1);

				int iy2MT = 0;

				if (iy2MT < masterTable.getLongueur())
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
		// 3eme cas : MP = 2
		if (this.MP == 2)
		{
			masterTable.indiceBon(masterTable.indexOfY(ordi.getPropoTab().getX()),
					ordi.getPropoTab().indexOf(ordi.getPropoTab().getY()) + 1);

			if (this.BP == 0)
			{
				logger.debug("MP = 2	BP = 0");
				masterTable.initPremiereLigneMT(indiceTable.getT());
				// On remplit la premiere ligne de MT 'getIT(X)'
				// fois, càd de '0 à mt.length' fois
				masterTable.indiceARayer(jat[0], jat[1]);
			}
		}

		logger.debug("__MAJ TABLES__");

		MAJTables();

		//On teste les tables pour savoir si la MT a été déchiffrée

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
	 * Renvoie 'true' si : la table 'colonneTerminee' est remplie de 'true' ET si il
	 * n'y a plus de Y à trouver (si la 1ere ligne de la MT est complète)
	 * 
	 * @return ct : true si la table colonneTerminee est terminee et si la premiere
	 *         ligne de la masterTable est complète, false sinon
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
	 * Renvoie 'true' si tous les indices ont étés trouvés
	 * 
	 * @return ib
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

	/**
	 * Renvoie le nombre de fois x contenue dans tab
	 * 
	 * @param x
	 *            nombre à compter
	 * @param tab
	 *            table dans laquelle compter
	 * @return nombre
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
	 * 
	 * @param x
	 *            nombre à chercher
	 * @param tab
	 *            table dans laquelle chercher
	 * @return posTab
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
	 * Compare les 2 tables en entrée et incrémente BP(rond noir) et MP(rond blanc)
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

		for (i = 0; i < NB_CASES_COMBI; i++)//check BP
		{
			if (i < NB_CASES_COMBI && copieComb[i] > -1 && copieComb[i] < 10 && copieProp[i] > -1 && copieProp[i] < 10)
			{
				if (copieComb[i] == copieProp[i])
				{
					this.BPj++;
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
						this.MPj++;
						this.MP++;
						copieProp[j] = -1;
						copieComb[i] = -1;
					}
				}
			}
			i++;
		}
	}

	/**
	 * Affiche la réponse
	 * 
	 * @param u
	 *            utilisateur dont la réponse doit etre affichée
	 * @param k
	 *            1 : mode developpeur, 0 : mode normal
	 */
	public void reponse(Utilisateur u, int k)
	{
		if (k == 1)
		{
			super.reponse();
		}

		System.out.print("\t\t");
		for (int i = 0; i < NB_CASES_COMBI; i++)
		{
			System.out.print(u.getCombiTab(i) + " ");
		}
		System.out.println("\n# # # # # # # # # # # # # # # # # # # # # # # # # # # # ");
	}

}
