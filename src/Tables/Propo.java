package Tables;

public class Propo extends Tables<Integer>
{
	int[][] XouXY = new int[2][2];

	public Propo()
	{
		setTaille(0);
		init();
	}

	public Propo(int i)
	{
		setTaille(i);
		init();
	}

	/*----------------------------------------Accesseurs et mutateurs------------------------------------------*/
	/****** GETTERS ******/
	public int[][] getXouXY()
	{
		return XouXY;
	}

	public int getXouXY(int i, int j)
	{
		return this.XouXY[j][i];
	}

	public int getX()
	{
		return this.XouXY[0][0];
	}

	public int getY()
	{
		return this.XouXY[1][0];
	}

	public int getIY()
	{
		return this.XouXY[1][1];
	}

	/****** SETTERS ******/
	public void setXouXY(int[][] xouXY)
	{
		XouXY = xouXY;
	}

	public void setXouXY(int i, int j, int val)
	{
		this.XouXY[j][i] = val;
	}

	public void setX(int x)
	{
		this.XouXY[0][0] = x;
	}

	public void setY(int y)
	{
		this.XouXY[1][0] = y;
	}

	public void setIY(int iy)
	{
		this.XouXY[1][1] = iy;
	}

	/*------------------------------------------------Methodes--------------------------------------------------*/
	/**
	 * Initialise propo à -2 partout
	 */
	public void init()
	{
		this.t = new Integer[this.getTaille()];
		for (int i = 0; i < this.getTaille(); i++)
		{
			setValeur(i, -2);
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
	public void propoXouXY()
	{
		int x = -1;
		int y = -1;
		int idk = 0;

		this.XouXY[1][0] = y;
		this.XouXY[1][1] = -1;

		for (int i = 0; i < this.getTaille(); i++)
		{
			// idk = compteCombien(propo[i],propo); // On compte le nombre de fois que
			// propo[i] // apparaît dans propo

			if (idk == 1) // Si il apparaît 1 fois, alors propo[i] = y
			{
				y = t[i];
				XouXY[1][0] = y; // Y
				XouXY[1][1] = i; // jy
			} else if (idk > 1) // Sinon propo[i] = X
			{
				x = t[i];
				XouXY[0][0] = x; // X
				XouXY[0][1] = idk; // nbx
			}
		}

		System.out.println("X : " + XouXY[0][0] + " apparait " + XouXY[0][1] + " fois" + "\nY : " + XouXY[1][0]
				+ " apparait à la position : " + XouXY[1][1]);

	}

}
