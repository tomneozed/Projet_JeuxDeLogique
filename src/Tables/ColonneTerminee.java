package Tables;

public class ColonneTerminee extends Tables<Boolean>
{
	public ColonneTerminee()
	{
		setTaille(0);
		init();
	}

	public ColonneTerminee(int t)
	{
		setTaille(t);
		init();
	}

	/*----------------------------------------Accesseurs et mutateurs------------------------------------------*/
	/****** GETTERS ******/
	/****** SETTERS ******/

	/*------------------------------------------------Methodes--------------------------------------------------*/
	/**
	 * Initialisation de CT � false
	 */
	public void init()
	{
		this.t = new Boolean[this.getTaille()];
		for (int i = 0; i < this.t.length; i++)
		{
			this.setValeur(i, false);
		}
	}

	/**
	 * Affiche colonneTerminee
	 */
	public void afficheCT()
	{
		System.out.println("\n\nColonneTerminee : ");
		for (int i = 0; i < this.getTaille(); i++)
		{
			System.out.println(i + " : " + this.getValeur(i));
		}
	}

}
