package Tables;

import java.util.Scanner;

public class TestTables
{
	private int BP, MP;
	// int[][] XouXY;
	int[] combi = new int[4];
	// int[] propo = new int[4];

	IndiceTab indiceTable = new IndiceTab();
	ColonneTerminee colonneTerminee = new ColonneTerminee(4);
	MasterTable masterTable = new MasterTable();
	Propo propo = new Propo(4);

	public TestTables()
	{
		int rejouer = -1, tour = 0;

		System.out.println("Bienvenue dans le Recherche +/- mode Challenger !");
		Scanner scan = new Scanner(System.in);

		int[] c = // combinaison de test : Exemple 2
		{ 3, 7, 5, 3 };

		this.combi[0] = 3;
		this.combi[1] = 7;
		this.combi[2] = 5;
		this.combi[3] = 3;

		/*
		 * this.combi[0] = 9; this.combi[1] = 1; this.combi[2] = 3; this.combi[3] = 2;
		 * 
		 */
		indiceTable.setTotal(4);

		propo.init(0);
		masterTable.afficheMT();
		indiceTable.afficheIT();

		do
		{
			System.out.println("\n\n***************************************TOUR " + tour
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
			tour++;

			bienMalPlace(this.combi, propo.getT());

		} while (BP != combi.length && tour < 10);

		if (BP == combi.length)
		{
			System.out.println("Bravo ordi, vous avez gagn� !");
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
			// 1er cas : BP > 0 & MP = 0
			if (this.BP >= 1 && this.MP == 0)
			{
				masterTable.indiceBon(iyMT, jyMT);
				colonneTerminee.setT(masterTable.majColonneTerminee(colonneTerminee.getT()));

				indiceTable.setValeur(X, ((BP + MP) - 1));			// On remplit IT � la position X(XouXY[0][0])
				// '(BP+MP)-1' fois (1 �tant Y dont on sait
				// qu'il
				// existe 1 fois)
				masterTable.initPremiereLigneMT(indiceTable.getT());		// On remplit la premiere ligne de MT
				// 'getIT(X)'
				// fois, c�d de '0 � mt.length' fois
				// masterTable.affiche();
			} else

			// 2eme cas : BP = 0 & MP = 1
			if (this.BP == 0 && this.MP == 1)
			{
				masterTable.indiceARayer(iyMT, jyMT);
				indiceTable.setValeur(X, ((BP + MP) - 1));
				colonneTerminee.setT(masterTable.majColonneTerminee(colonneTerminee.getT()));

			} else

			// 3eme cas : BP = 0 & MP = 2
			if (this.BP == 0 && this.MP == 2)
			{
				indiceTable.setValeur(X, 1);
				masterTable.initPremiereLigneMT(indiceTable.getT());	// On remplit la premiere ligne de MT 'getIT(X)'
				// fois, c�d
				// de '0 � mt.length' fois
				masterTable.indiceARayer(iyMT, jyMT);
				colonneTerminee.setT(masterTable.majColonneTerminee(colonneTerminee.getT()));
				// masterTable.indiceBon(iyMT, jyMT);

			} else

			// 4eme cas : BP = 1 & MP = 1
			// -> a) BP > 1 & MP = 1
			// -> b) BP = 1 & MP > 1 ]2[
			// -> c) BP > 1 & MP > 1 ]2[
			if (BP == 1 && MP == 1)
			{
				indiceTable.setValeur(X, ((BP + MP) - 1));
				masterTable.initPremiereLigneMT(indiceTable.getT());	// On remplit la premiere ligne de MT 'getIT(X)'
				// fois, c�d
				// de '0 � mt.length' fois
				masterTable.indiceARayer(iyMT, jyMT);

				int iy2MT = 0;
				while (this.masterTable.getValeur(iy2MT, 0) > -1 && this.masterTable.getValeur(iy2MT, 0) < 10)
				{
					if (this.masterTable.getValeur(iy2MT, 0) == X)
					{
						masterTable.indiceARayer(iy2MT, jyMT);
					}
					iy2MT++;
				}

				// indiceARayer(X, jy, this.masterTable);
			}

			/*
			 * Mettre une fonction qui teste si la masterTable est termin�� Si oui ->
			 * propoFinale()
			 */
			if (combiTrouvee() == true)
			{
				this.propo.setT(propo.propoFinale(this.masterTable.getMT()));

			} else if (indiceBon() == true)
			{
				this.propo.setT(propo.propoChercheY(masterTable.getMT(), indiceTable));
			} else
			{

				jat = masterTable.jATrouver(colonneTerminee.getT());
				yat = masterTable.yATrouver();

				premierNullIT = indiceTable.cherchePremierNull();
				this.propo.setT(propo.propoXY(premierNullIT, this.masterTable.getValeur(jat[0], 0), (jat[1] - 1),
						this.masterTable.getMT()));
			}

		} else if (Y == X && propo.XouXY[0][1] != propo.getTaille() - 1)	//PropoChercheY
		{
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

				jat = masterTable.jATrouver(colonneTerminee.getT());
				yat = masterTable.yATrouver();

				premierNullIT = indiceTable.cherchePremierNull();
				this.propo.setT(propo.propoXY(premierNullIT, this.masterTable.getValeur(jat[0], 0), (jat[1] - 1),
						this.masterTable.getMT()));
			}

		} else	// propoX
		{
			if (BP == 0)
			{
				indiceTable.setValeur(X, 0);		// On remplit IT � la position X(XouXY[0][0]) 0 fois

				jat = masterTable.jATrouver(colonneTerminee.getT());
				yat = masterTable.yATrouver();

				premierNullIT = indiceTable.cherchePremierNull();

				this.propo.setT(propo.propoX(premierNullIT));
			} else if (BP > 0)
			{
				indiceTable.setValeur(X, BP);		// On remplit IT � la position X(XouXY[0][0]) 'BP' fois
				this.masterTable.initPremiereLigneMT(indiceTable.getT());

				jat = this.masterTable.jATrouver(colonneTerminee.getT());
				System.out.println("jat : " + jat[0] + ", " + jat[1]);
				yat = this.masterTable.yATrouver();

				premierNullIT = indiceTable.cherchePremierNull();
				this.propo.setT(propo.propoXY(premierNullIT, this.masterTable.getValeur(0, jat[0]), (jat[1] - 1),
						this.masterTable.getMT()));
			}
		}
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
	public void bienMalPlace(int[] comb, Integer[] prop)
	{
		Integer[] copieProp = new Integer[prop.length];

		copieProp[0] = prop[0];
		copieProp[1] = prop[1];
		copieProp[2] = prop[2];
		copieProp[3] = prop[3];

		initBPMP();
		System.out.println("\n");
		int i = 0, j = 0;
		int r = 0;
		while (i < comb.length) // i est l'indice de comb[]
		{
			r = 0;
			j = 0;
			while (r == 0 && j < copieProp.length) // j est l'indice de prop[]
			{
				if (comb[i] == copieProp[j]) // Si la valeur de comb � la position i est �gale � une valeur de prop position
				// j
				{
					if (comb[i] == copieProp[i]) // Si i = j
					{
						// System.out.println("Rond NOIR");
						this.BP++;
					} else
					{
						// System.out.println("Rond BLANC");
						this.MP++;
					}
					copieProp[j] = -1;
					r = 1;
				} else
				{
					j++;
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
		return ct;
	}

	/**
	 * Renvoie 'true' si tous les indices ont �t�s trouv�s
	 * 
	 * @return
	 */
	public Boolean indiceBon()
	{
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