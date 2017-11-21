package Tables;

public class ColonneTerminee extends Tables
{
	private int taille;
	private boolean[] CT;

	public ColonneTerminee()
	{
		setTaille(0);
		initCT();
	}

	public ColonneTerminee(int t)
	{
		setTaille(t);
		initCT();
	}

	/*----------------------------------------Accesseurs et mutateurs------------------------------------------*/
	/****** GETTERS ******/
	public int getTaille()
	{
		return this.taille;
	}

	public Object getValeur(int i)
	{
		return this.CT[i];
	}

	public boolean[] getCT()
	{
		return this.CT;
	}

	/****** SETTERS ******/
	public void setTaille(int t)
	{
		this.taille = t;
	}

	public void setValeur(boolean val, int i)
	{
		this.CT[i] = val;
	}

	public void setCT(boolean[] ct)
	{
		this.CT = ct;
	}

	/*------------------------------------------------Methodes--------------------------------------------------*/
	/**
	 * Initialisation de CT à false
	 */
	public void initCT()
	{
		for (int i = 0; i < this.CT.length; i++)
		{
			this.setValeur(false, i);
		}
	}

}
