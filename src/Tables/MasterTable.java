package Tables;

public class MasterTable
{
	private int[][] mt;
	private int largeur, longueur;

	public MasterTable()
	{
		setLongeur(4);
		setLargeur(4 + 1);
		mt = new int[this.getLargeur()][this.getLongueur()];
		initMT();
	}

	public MasterTable(int l)
	{
		setLongeur(l);
		setLargeur(l + 1);
		mt = new int[this.getLargeur()][this.getLongueur()];
		initMT();
	}

	/*----------------------------------------Accesseurs et mutateurs------------------------------------------*/
	/****** GETTERS ******/
	public int getLargeur()
	{
		return this.largeur;
	}

	public int getLongueur()
	{
		return this.longueur;
	}

	/****** SETTERS ******/
	public void setLongeur(int l)
	{
		this.longueur = l;
	}

	public void setLargeur(int l)
	{
		this.largeur = l;
	}

	/*------------------------------------------------Methodes--------------------------------------------------*/

	/**
	 * Initialise la masterTable à -2 partout
	 */
	public void initMT()
	{
		for (int i = 0; i < this.getLargeur(); i++)
		{
			for (int j = 0; j < this.getLongueur(); j++)
			{
				this.mt[i][j] = -2; // -2 = null
			}
		}
	}

	/**
	 * Affiche la masterTable
	 */
	public void afficheMT()
	{
		System.out.println("\n\nMasterTab : " + this.getLongueur() + "x" + this.getLargeur() + "\n");
		System.out.print("  ");
		for (int i = 0; i < this.getLongueur(); i++)
		{
			System.out.print("  " + i + " ");
		}
		System.out.print("\n");

		for (int i = 0; i < this.getLargeur(); i++)
		{
			if (i < 10 && i > -1)
			{
				System.out.print(i + " ");
			} else if (i > 9 && i < 100)
			{
				System.out.print(i);
			}

			for (int j = 0; j < this.getLongueur(); j++)
			{
				if (this.mt[i][j] == -2)
				{
					System.out.print("|   ");
				} else if (this.mt[i][j] == -1)
				{
					System.out.print("| X ");
				} else
				{
					System.out.print("| " + this.mt[i][j] + " ");
				}
			}
			System.out.println("|");
		}
	}

	/**
	 * Renvoie l'indice du premier null (-2) de la colonne de la masterTable
	 * 
	 * @param mt
	 * @param Y
	 * @return
	 */
	public int cherchePremierNullIndiceMT(int colonne)
	{
		int indiceATester = -1;
		int j = 0;
		do
		{
			j++;
		} while (this.mt[j][colonne] != -2 && j <= this.getLargeur());

		indiceATester = j;

		return indiceATester;
	}

	/**
	 * Renvoie la position de Y dans la premiere ligne de MT pour lequel
	 * colonneTerminee est fausse
	 * 
	 * @param jyPropo
	 * @return
	 */
	public int chercheIYMT(int jyP, int[] p, boolean[] colonneTerminee)
	{
		int compteur1ereligne = 0;
		int iyMT = -1;
		while (compteur1ereligne < this.getLargeur() && iyMT == -1)
		{
			if (this.mt[0][compteur1ereligne] == p[jyP] && colonneTerminee[compteur1ereligne] == false)
			{
				iyMT = compteur1ereligne;
			} else
			{
				compteur1ereligne++;
			}
		}

		return iyMT;
	}

}
