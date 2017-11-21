package Tables;

public class IndiceTab extends Tables
{
	private int taille;
	private int[] IT = new int[taille];

	public IndiceTab()
	{
		setTaille(0);
		initIT();
	}

	public IndiceTab(int i)
	{
		setTaille(i);
		initIT();
	}

	/*----------------------------------------Accesseurs et mutateurs------------------------------------------*/
	/****** GETTERS ******/

	/****** SETTERS ******/

	/*------------------------------------------------Methodes--------------------------------------------------*/
	/**
	 * Initialise l'indiceTab à -2 partout
	 */
	public void initIT()
	{
		for (int i = 0; i < this.getTaille(); i++)
		{
			setValeur(-2, i);
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
			System.out.println(i + " : " + this.getValeur(i));
		}
	}

}
