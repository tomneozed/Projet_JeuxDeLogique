package Tables;

public class IndiceTab extends Tables<Integer>
{
	public IndiceTab()
	{
		setTaille(10);
		init();
	}

	/*----------------------------------------Accesseurs et mutateurs------------------------------------------*/
	/****** GETTERS ******/

	/****** SETTERS ******/

	/*------------------------------------------------Methodes--------------------------------------------------*/
	/**
	 * Initialise l'indiceTab à -2 partout
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
