package Tables;

public class IndiceTab
{
	private int taille = 10;
	private int[] IT = new int[taille];

	public IndiceTab()
	{
		initIT();
	}

	/*----------------------------------------Accesseurs et mutateurs------------------------------------------*/
	/****** GETTERS ******/
	public int getTaille()
	{
		return this.taille;
	}

	/****** SETTERS ******/
	public void setTaille(int t)
	{
		this.taille = t;
	}

	/*------------------------------------------------Methodes--------------------------------------------------*/
	/**
	 * Initialise l'indiceTab à -2 partout
	 */
	public void initIT()
	{
		for (int i = 0; i < this.getTaille(); i++)
		{
			this.IT[i] = -2; // -2 = null
		}
	}

	/**
	 * Affiche l'indiceTab
	 */
	public void afficheIT()
	{
		System.out.println("\n\nIndiceTab : ");
		for (int i = 0; i < this.getTaille(); i++)
		{
			System.out.println(i + " : " + this.IT[i]);
		}
	}

}
