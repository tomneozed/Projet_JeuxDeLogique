package Tables;

import java.util.Scanner;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;

public class TestTables
{
	static Logger logger = Logger.getLogger(TestTables.class);
	ConsoleAppender appender = (ConsoleAppender) logger.getAppender("console");

	Configuration c = ConfigJeu.loadConfig();

	Integer NB_CASES_COMBI = c.getNombreCasesCombi();
	Integer NB_ESSAIS = c.getEssais();
	Integer NB_COULEURS = c.getNombreCouleurs();

	private int BP, MP;
	// int[][] XouXY;
	int[] combi = new int[NB_CASES_COMBI];
	// int[] propo = new int[4];

	IndiceTab indiceTable = new IndiceTab();
	ColonneTerminee colonneTerminee = new ColonneTerminee(NB_CASES_COMBI);
	MasterTable masterTable = new MasterTable(NB_CASES_COMBI);
	Propo propo = new Propo(NB_CASES_COMBI);

	public TestTables()
	{

		logger.addAppender(appender);

		int rejouer = -1, essai = 0;

		System.out.println("Bienvenue dans le Recherche +/- mode Challenger !");
		Scanner scan = new Scanner(System.in);

		int[] c;// combinaison de test : Exemple 2

		this.combi[0] = 9;
		this.combi[1] = 7;
		this.combi[2] = 8;
		this.combi[3] = 2;
		this.combi[4] = 2;
		this.combi[5] = 2;
		this.combi[6] = 1;
		this.combi[7] = 1;
		this.combi[8] = 8;

		/*
		 * this.combi[0] = 9; this.combi[1] = 8; this.combi[2] = 7; this.combi[3] = 6;
		 */
		indiceTable.setTotal(NB_CASES_COMBI);

		propo.init(0);
		masterTable.afficheMT();
		indiceTable.afficheIT();

		do
		{
			System.out.println("\n\n***************************************TOUR " + essai
					+ "***************************************");

			propo.affichePropo();
			tourDeLOrdi();
			masterTable.afficheMT();
			indiceTable.afficheIT();
			colonneTerminee.afficheCT();

			System.out.println("\n\tNouvelle proposition : ");
			propo.affichePropo();

			// System.out.println("\n\n\nVoulez-vous rejouer ? \n\n\t1. oui \t\t2.non");
			// rejouer = scan.nextInt();
			essai++;

			bienMalPlace(this.combi, propo.getT());
			logger.debug("Fin du tour");
		} while (BP != combi.length && essai != NB_ESSAIS);

		if (BP == combi.length)
		{
			System.out.println("Bravo ordi, vous avez gagné !");
		}

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

	/*------------------------------------------Fonctions commmunes--------------------------------------------*/

	/**
	 * Fonction mode defenseur
	 */
	public void tourDeLOrdi()
	{
		boolean yat;
		int[] jat = masterTable.jATrouver(colonneTerminee.getT());
		int premierNullIT = -1;
		propo.propoXouXY();
		bienMalPlace(this.combi, propo.getT());
		int X = propo.getX();
		int Y = propo.getY();
		int iyPropo = propo.getIY();
		int iyMT = jat[0];
		int jyMT = jat[1];

		// On regarde si la proposition est de type X ou XY
		if (Y != -1 && propo.XouXY[0][1] == propo.getTaille() - 1)	// propoXY
		{
			logger.debug("PropoXY");

			indiceTable.setValeur(X, ((BP + MP) - 1));

			masterTable.majMT();
			indiceTable.majIT();
			masterTable.initPremiereLigneMT(indiceTable.getT());
			colonneTerminee.setT(masterTable.majColonneTerminee(colonneTerminee.getT()));

			// 1er cas : BP > 0 & MP = 0
			if (this.MP == 0)
			{
				if (this.BP >= 1)
				{
					logger.debug("MP = 0	BP >= 1");
					masterTable.indiceBon(iyMT, jyMT);
					colonneTerminee.setT(masterTable.majColonneTerminee(colonneTerminee.getT()));

					// On remplit IT à la position X(XouXY[0][0])
					// '(BP+MP)-1' fois (1 étant Y dont on sait
					// qu'il
					// existe 1 fois)
					masterTable.initPremiereLigneMT(indiceTable.getT());
					// On remplit la premiere ligne de MT 'getIT(X)'
					// fois, càd de '0 à mt.length' fois
				}

			} else

			// 2eme cas : BP = 0 & MP = 1
			if (this.MP == 1)
			{
				masterTable.indiceARayer(iyMT, jyMT);
				masterTable.nombreARayer(Y, jyMT);
				masterTable.nombreARayer(X, jyMT);

				if (this.BP == 1)
				{
					logger.debug("MP = 1	BP = 1");

					masterTable.initPremiereLigneMT(indiceTable.getT());
					// On remplit la premiere ligne de MT 'getIT(X)' fois, càd
					// de '0 à mt.length' fois

					masterTable.indiceARayer(masterTable.indexOfY(X), propo.indexOf(Y) + 1);

					int iy2MT = 0;

					if (iy2MT < masterTable.getTaille())
					{
						while (this.masterTable.getValeur(iy2MT, 0) > -1 && this.masterTable.getValeur(iy2MT, 0) < 10)
						{
							if (this.masterTable.getValeur(iy2MT, 0) == X)
							{
								masterTable.indiceARayer(iy2MT, jyMT);
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
				masterTable.indiceBon(masterTable.indexOfY(X), propo.indexOf(Y) + 1);

				if (this.BP == 0)
				{
					logger.debug("MP = 2	BP = 0");
					masterTable.initPremiereLigneMT(indiceTable.getT());	// On remplit la premiere ligne de MT 'getIT(X)'
					// fois, càd
					// de '0 à mt.length' fois
					masterTable.indiceARayer(iyMT, jyMT);
				}
			}

			logger.debug("__MAJ TABLES__");

			masterTable.majMT();
			indiceTable.majIT();
			masterTable.initPremiereLigneMT(indiceTable.getT());
			colonneTerminee.setT(masterTable.majColonneTerminee(colonneTerminee.getT()));
			//On teste les tables pour savoir si la MT a été déchiffrée

			if (combiTrouvee() == true)
			{
				this.propo.setT(propo.propoFinale(this.masterTable.getMT()));

			} else if (indiceBon() == true)
			{
				this.propo.setT(propo.propoChercheY(masterTable.getMT(), indiceTable));
			} else
			{
				colonneTerminee.setT(masterTable.majColonneTerminee(colonneTerminee.getT()));

				jat = masterTable.jATrouver(colonneTerminee.getT());
				yat = masterTable.yATrouver();

				premierNullIT = indiceTable.cherchePremierNull();
				this.propo.setT(propo.propoXY(premierNullIT, this.masterTable.getValeur(jat[0], 0), (jat[1] - 1),
						this.masterTable.getMT()));
			}

		} else if (Y == X && propo.XouXY[0][1] != propo.getTaille() - 1)	//PropoChercheY
		{
			logger.debug("PropoChercheY");
			if (this.BP == propo.getTaille() - 1 && this.MP == 0)
			{
				indiceTable.setValeur(indiceTable.cherchePremierNull(), 0);
			}

			if (combiTrouvee() == true)
			{
				this.propo.setT(propo.propoFinale(this.masterTable.getMT()));

			} else if (indiceBon() == true)
			{
				this.propo.setT(propo.propoChercheY(masterTable.getMT(), indiceTable));
			} else
			{
				colonneTerminee.setT(masterTable.majColonneTerminee(colonneTerminee.getT()));
				jat = masterTable.jATrouver(colonneTerminee.getT());
				yat = masterTable.yATrouver();

				premierNullIT = indiceTable.cherchePremierNull();
				this.propo.setT(propo.propoXY(premierNullIT, this.masterTable.getValeur(jat[0], 0), (jat[1] - 1),
						this.masterTable.getMT()));
			}
		} else	// propoX
		{

			logger.debug("PropoX");
			if (BP == 0)
			{
				logger.debug("BP = 0");
				indiceTable.setValeur(X, 0);		// On remplit IT à la position X(XouXY[0][0]) 0 fois

				jat = masterTable.jATrouver(colonneTerminee.getT());
				yat = masterTable.yATrouver();

				premierNullIT = indiceTable.cherchePremierNull();

				this.propo.setT(propo.propoX(premierNullIT));
			} else if (BP > 0)
			{
				logger.debug("BP > 0");
				indiceTable.setValeur(X, BP);		// On remplit IT à la position X(XouXY[0][0]) 'BP' fois
				this.masterTable.initPremiereLigneMT(indiceTable.getT());

				jat = this.masterTable.jATrouver(colonneTerminee.getT());
				System.out.println("jat : " + jat[0] + ", " + jat[1]);
				yat = this.masterTable.yATrouver();

				premierNullIT = indiceTable.cherchePremierNull();
				this.propo.setT(propo.propoXY(premierNullIT, this.masterTable.getValeur(0, jat[0]), (jat[1] - 1),
						this.masterTable.getMT()));
			}
		}

		logger.debug("__MAJ TABLES__");

		masterTable.majMT();
		indiceTable.majIT();
		masterTable.initPremiereLigneMT(indiceTable.getT());
		colonneTerminee.setT(masterTable.majColonneTerminee(colonneTerminee.getT()));
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
	public void bienMalPlace(int[] comb, Integer[] prop)
	{
		logger.debug("bienMalPlace()");
		Integer[] copieProp = new Integer[prop.length];
		Integer[] copieComb = new Integer[prop.length];

		for (int i = 0; i < prop.length; i++)
		{
			copieProp[i] = prop[i];
			copieComb[i] = comb[i];
		}

		initBPMP();
		System.out.println("\n");
		int i = 0, j = 0;
		int r = 0;

		for (i = 0; i < comb.length; i++)//check BP
		{
			if (copieComb[i] > -1 && copieComb[i] < 10 && copieProp[i] > -1 && copieProp[i] < 10)
			{
				if (copieComb[i] == copieProp[i])
				{
					System.out.println("Combi[" + i + "] : " + copieComb[i] + " | Propo[" + i + "] : " + copieProp[i]);
					this.BP++;
					copieProp[i] = -1;
					copieComb[i] = -1;
				}
			}
		}
		i = 0;
		while (i < comb.length) // i est l'indice de comb[]
		{
			//r = 0;
			//j = 0;

			for (j = 0; j < copieComb.length; j++)
			{
				if (copieComb[i] > -1 && copieComb[i] < 10 && copieProp[j] > -1 && copieProp[j] < 10)
				{
					if (copieComb[i] == copieProp[j])
					{
						System.out.println(
								"Combi[" + i + "] : " + copieComb[i] + " | Propo[" + j + "] : " + copieProp[j]);
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
	 * il n'y a plus de Y à trouver (si la 1ere ligne de la MT est complète)
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
	 * Renvoie 'true' si tous les indices ont étés trouvés
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
