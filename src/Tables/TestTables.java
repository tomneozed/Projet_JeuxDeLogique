package Tables;

import java.util.Scanner;

public class TestTables
{
	private int BP, MP;
	// int[][] XouXY;
	int[] combi = new int[4];
	// int[] propo = new int[4];

	IndiceTab indiceTable = new IndiceTab();
	ColonneTerminee colonneTerminee = new ColonneTerminee();
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

		masterTable.afficheMT();
		indiceTable.afficheIT();

		do
		{
			System.out.println("\n\n***************************************TOUR " + tour
					+ "***************************************");

			tourDeLOrdi();
			masterTable.afficheMT();
			indiceTable.afficheIT();

			// System.out.println("\n\n\nVoulez-vous rejouer ? \n\n\t1. oui \t\t2.non");
			// rejouer = scan.nextInt();
			tour++;
		} while (tour < 10);
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
		bienMalPlace(this.combi, (int[]) propo.getT());
		int X = XouXY[0][0];
		int Y = XouXY[1][0];
		int jyPropo = XouXY[1][1];
		int iyMT = jat[0];
		int jyMT = jat[1];

		// On regarde si la proposition est de type X ou XY
		if (XouXY[1][0] != -1)	// propoXY
		{
			// 1er cas : BP > 0 & MP = 0
			if (this.BP >= 1 && this.MP == 0)
			{
				MasterTable.indiceBon(iyMT, iyMT);
				majColonneTerminee();

				setIT(X, ((BP + MP) - 1));						// On remplit IT à la position X(XouXY[0][0])
																	// '(BP+MP)-1' fois (1 étant Y dont on sait qu'il
																	// existe 1 fois)
				initPremiereLigneMT();		// On remplit la premiere ligne de MT 'getIT(X)'
												// fois, càd
				// de '0 à mt.length' fois
				afficheMT();
			} else

			// 2eme cas : BP = 0 & MP = 1
			if (this.BP == 0 && this.MP == 1)
			{
				indiceARayer(iyMT, jyMT, this.masterTable);
				setIT(X, ((BP + MP) - 1));
			} else

			// 3eme cas : BP = 0 & MP = 2
			if (this.BP == 0 && this.MP == 2)
			{
				setIT(X, 1);
				initPremiereLigneMT();		// On remplit la premiere ligne de MT 'getIT(X)'
												// fois, càd
				// de '0 à mt.length' fois
				indiceBon(iyMT, jyMT, this.masterTable);

			} else

			// 4eme cas : BP = 1 & MP = 1
			// -> a) BP > 1 & MP = 1
			// -> b) BP = 1 & MP > 1 ]2[
			// -> c) BP > 1 & MP > 1 ]2[
			if (BP == 1 && MP == 1)
			{
				setIT(X, (BP + MP) - 1);
				initPremiereLigneMT();		// On remplit la premiere ligne de MT 'getIT(X)'
												// fois, càd
				// de '0 à mt.length' fois
				indiceARayer(iyMT, jyMT, this.masterTable);
				// indiceARayer(X, jy, this.masterTable);
			}

			yat = yATrouver(this.masterTable);
			jat = jATrouver(this.masterTable);

			premierNullIT = cherchePremierNull(this.indiceTab);
			this.propo = propoXY(premierNullIT, iyMT, (jat[1] - 1), this.masterTable);

		} else	// propoX
		{

			if (BP == 0)
			{
				setIT(X, 0); 						// On remplit IT à la position X(XouXY[0][0]) 0 fois

				yat = yATrouver(this.masterTable);
				jat = jATrouver(this.masterTable);

				premierNullIT = cherchePremierNull(this.indiceTab);

				this.propo = propoX(premierNullIT, this.masterTable);
			} else if (BP > 0)
			{
				setIT(X, BP); 									// On remplit IT à la position X(XouXY[0][0]) 'BP' fois
				initPremiereLigneMT();

				yat = yATrouver(this.masterTable);
				jat = jATrouver(this.masterTable);

				premierNullIT = cherchePremierNull(this.indiceTab);
				this.propo = propoXY(premierNullIT, this.masterTable[0][iyMT], (jat[1] - 1), this.masterTable);
			}
		}
	}

	public void bienMalPlace(int[] comb, int[] prop)
	{
		initBPMP();
		System.out.println("\n");
		int i = 0, j = 0;
		int r = 0;
		while (i < comb.length) // i est l'indice de comb[]
		{
			r = 0;
			j = 0;
			while (r == 0 && j < prop.length) // j est l'indice de prop[]
			{
				if (comb[i] == prop[j]) // Si la valeur de comb à la position i est égale à une valeur de prop position
										 // j
				{
					if (comb[i] == prop[i]) // Si i = j
					{
						// System.out.println("Rond NOIR");
						this.BP++;
					} else
					{
						// System.out.println("Rond BLANC");
						this.MP++;
					}
					prop[j] = -1;
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

}
