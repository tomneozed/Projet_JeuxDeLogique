package Tables;

public class Propo extends Tables
{
	private int taille;
	private int[] propo = new int[taille];
	int[][] XouXY = new int[2][2];

	public Propo()
	{
		setTaille(0);
		initPropo();
	}

	public Propo(int i)
	{
		setTaille(i);
		initPropo();
	}

	/*----------------------------------------Accesseurs et mutateurs------------------------------------------*/
	/****** GETTERS ******/

	/****** SETTERS ******/

	/*------------------------------------------------Methodes--------------------------------------------------*/
	/**
	 * Initialise propo à -2 partout
	 */
	public void initPropo()
	{
		for (int i = 0; i < this.getTaille(); i++)
		{
			setValeur(-2, i);
		}
	}

	/**
	 * Affiche propo
	 */
	public void affichePropo()
	{
		System.out.println("\n\nPropo : ");
		for (int i = 0; i < this.getTaille(); i++)
		{
			System.out.println(i + " : " + this.getValeur(i));
		}
	}

	/**
	 * Renvoie un tableau 2x2 contenant : [X,nbX] [Y,nbY]
	 * 
	 * @param propo
	 *            tableau à étudier
	 * @return
	 */
	@SuppressWarnings("null")
	public int[][] propoXouXY(int[] propo)
	{
		int[][] XouXY = new int[2][2];
		int x = -1;
		int y = -1;
		int idk;

		XouXY[1][0] = y;
		XouXY[1][1] = -1;

		for (int i = 0; i < propo.length; i++)
		{
			idk = compteCombien(propo[i], propo); // On compte le nombre de fois que propo[i] apparaît dans propo

			if (idk == 1) // Si il apparaît 1 fois, alors propo[i] = y
			{
				y = propo[i];
				XouXY[1][0] = y; // Y
				XouXY[1][1] = i; // jy
			} else if (idk > 1) // Sinon propo[i] = X
			{
				x = propo[i];
				XouXY[0][0] = x; // X
				XouXY[0][1] = idk; // nbx
			}
		}

		System.out.println("X : " + XouXY[0][0] + " apparait " + XouXY[0][1] + " fois" + "\nY : " + XouXY[1][0]
				+ " apparait à la position : " + XouXY[1][1]);

		return XouXY;
	}

}
