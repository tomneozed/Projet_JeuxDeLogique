package Tables;

import org.apache.log4j.Logger;

/**
 * Classe fille de Tables
 * 
 * @author Thomas Pelissier
 * @version 1.0
 */
public class Propo extends Tables<Integer>
{
	//Attributs
	private Integer[][] XouXY = new Integer[2][2];
	private static Logger logger = Logger.getLogger(Propo.class);
	private Integer structurePropo;

	//Constructeurs

	public Propo()
	{
		setTaille(0);
		init();
	}

	public Propo(Integer i)
	{
		setTaille(i);
		init();
	}

	//Getters & Setters 
	public Integer[][] getXouXY()
	{
		return XouXY;
	}

	public Integer getXouXY(Integer i, Integer j)
	{
		return this.XouXY[j][i];
	}

	public Integer getX()
	{
		return this.XouXY[0][0];
	}

	public Integer getY()
	{
		return this.XouXY[1][0];
	}

	public Integer getIY()
	{
		return this.XouXY[1][1];
	}

	public Integer getStructurePropo()
	{
		return structurePropo;
	}

	public void setXouXY(Integer[][] xouXY)
	{
		XouXY = xouXY;
	}

	public void setXouXY(Integer i, Integer j, Integer val)
	{
		this.XouXY[j][i] = val;
	}

	public void setX(Integer x)
	{
		this.XouXY[0][0] = x;
	}

	public void setY(Integer y)
	{
		this.XouXY[1][0] = y;
	}

	public void setIY(Integer iy)
	{
		this.XouXY[1][1] = iy;
	}

	public void setStructurePropo(Integer structurePropo)
	{
		this.structurePropo = structurePropo;
	}

	//Methodes
	/**
	 * Initialise propo � -2 partout
	 */
	public void init()
	{
		logger.debug("Init propo : -2");
		this.t = new Integer[this.getTaille()];
		for (Integer i = 0; i < this.getTaille(); i++)
		{
			setValeur(i, -2);
		}

	}

	/**
	 * Initialise propo � x partout
	 * 
	 * @param x
	 *            nombre qui remplit propo
	 */
	public void init(Integer x)
	{
		logger.debug("Init propo : " + x.intValue());
		this.t = new Integer[this.getTaille()];
		for (Integer i = 0; i < this.getTaille(); i++)
		{
			setValeur(i, x);
		}

	}

	/**
	 * Affiche propo
	 */
	public void affichePropo()
	{
		for (Integer i = 0; i < this.getTaille(); i++)
		{
			System.out.print(this.getValeur(i) + " | ");
		}
		System.out.println("\n");
	}

	/**
	 * Renvoie un tableau rempli de x
	 * 
	 * @param x
	 *            nombre qui remplit propo
	 * @return propo
	 */
	public Integer[] propoX(Integer x)
	{
		Integer[] propo = new Integer[this.getTaille()];
		for (Integer i = 0; i < this.getTaille(); i++)
		{
			propo[i] = x;

		}
		return propo;
	}

	/**
	 * Renvoie un tableau rempli de x et un y � la position jy
	 * 
	 * @param x
	 *            valeur dont on teste l'existance dans la combi
	 * @param y
	 *            valeur dont on sait l'existance et dont on teste la position
	 * @param jy
	 *            position de Y � tester
	 * @param mt
	 *            masterTable
	 * @return propo
	 */
	public Integer[] propoXY(Integer x, Integer y, Integer jy, Integer[][] mt)
	{
		logger.debug("PropoXY -> X = " + x.intValue() + " | Y = " + y.intValue() + " position " + jy.intValue());
		Integer[] propo = new Integer[this.getTaille()];

		for (Integer i = 0; i < this.getTaille(); i++)
		{
			propo[i] = x;
		}
		propo[jy] = y;
		return propo;
	}

	/**
	 * Renvoie la proposition finale via la mastertable
	 * 
	 * @param table
	 *            masterTable
	 * @return pf
	 */
	public Integer[] propoFinale(Integer[][] table)
	{
		Integer[] pf = new Integer[table.length - 1];
		Integer i = 0;
		Integer j = 1;

		Integer cpt = 1;

		//Remplissage via les lignes 'indice'
		for (Integer k = 0; k < table.length - 1; k++)
		{
			i = 0;
			while (table[j][i] != cpt)
			{
				i++;
			}

			if (table[j][i] == cpt)
			{
				pf[cpt - 1] = table[0][i];
				cpt++;

				if (j < table.length - 1)
				{
					j++;
				}

			}
		}

		return pf;
	}

	/**
	 * Renvoie la proposition finale en testant la valeur du dernier y � trouver
	 * 
	 * @param table
	 *            masterTable
	 * @param it
	 *            table
	 * @return pcy
	 */
	public Integer[] propoChercheY(Integer[][] table, Tables it)
	{
		Integer[] pcy = new Integer[this.getTaille()];
		Integer i = 0;
		Integer j = 1;

		while (i < this.getTaille() && table[0][i] != -2)
		{
			i++;
		}

		if (i > 9)
		{
			i = 9;
		}

		if (table[0][i] == -2)
		{
			Integer cpt = 1;

			//Remplissage via les lignes 'indice'
			for (Integer k = 0; k < table.length - 1; k++)
			{
				i = 0;
				while (table[j][i] != cpt)
				{
					i++;
				}

				if (table[j][i] == cpt)
				{
					if (table[0][i] == -2)
					{
						pcy[cpt - 1] = it.cherchePremierNull();
					} else
					{
						pcy[cpt - 1] = table[0][i];
					}
					cpt++;

					if (j < table.length - 1)
					{
						j++;
					}

				}
			}
		}
		return pcy;

	}

	/**
	 * Renvoie un tableau 2x2 contenant : [X,nbX] [Y,iY]
	 * 
	 */
	public void propoXouXY()
	{
		Integer x = -1;
		Integer y = -1;
		Integer idk = 0;
		Integer chiffresDifferents = 0;
		Integer j = 0;

		int[] tableComptage = new int[10];
		for (Integer k = 0; k < 10; k++)
		{
			tableComptage[k] = 0;
		}

		this.XouXY[1][0] = y;
		this.XouXY[1][1] = -1;
		this.XouXY[0][0] = 0;
		this.XouXY[0][1] = 0;

		for (Integer i = 0; i < 10; i++)
		{
			idk = compteCombien(i, this.t); // On compte le nombre de fois que
			// propo[i] appara�t dans propo
			tableComptage[i] = idk;
			if (idk > 0)
			{
				chiffresDifferents++;
			}
		}

		if (chiffresDifferents == 1)	//PropoX
		{
			setStructurePropo(1);
			XouXY[0][0] = this.getValeur(0); // X
			XouXY[0][1] = this.getTaille(); // nbx

		} else if (chiffresDifferents == 2) // PropoXY
		{
			setStructurePropo(2);
			j = 0;
			while (j < 10 && chiffresDifferents > 0)
			{
				if (tableComptage[j] == 1)
				{
					XouXY[1][0] = j; // Y
					XouXY[1][1] = this.getPosition(j); // jy
					chiffresDifferents--;
				} else if (tableComptage[j] > 1)
				{
					XouXY[0][0] = j; // X
					XouXY[0][1] = tableComptage[j]; // nbx
					chiffresDifferents--;
				}
				j++;
			}
		} else if (chiffresDifferents > 2)	//PropoXYZ
		{
			setStructurePropo(3);
			while (j < 10 && chiffresDifferents > 0)
			{
				XouXY[0][0] = j; // X
				XouXY[0][1] = tableComptage[j]; // nbx
				XouXY[1][0] = j; // Y
				XouXY[1][1] = tableComptage[j]; // jy

				chiffresDifferents--;
				j++;
			}
		}
		logger.debug("propoXouXY()");
		logger.debug(XouXY[0][0] + " | " + XouXY[0][1]);
		logger.debug(XouXY[1][0] + " | " + XouXY[1][1]);

		for (Integer l = 0; l < 10; l++)
		{
			if (tableComptage[l] > 0)
			{
				logger.debug("La proposition contient : " + tableComptage[l] + " fois " + l);
			}
		}
	}

	/**
	 * Renvoie l'indice du premier Y de propo
	 * 
	 * @param y
	 *            nomre � rechercher
	 * @return i
	 */
	public Integer indexOf(Integer y)
	{
		Integer i = 0;

		while (i < this.t.length && this.t[i] != y)
		{
			i++;
		}

		logger.debug("indexOf(" + y.intValue() + ") -> " + i.intValue());
		return i;
	}

}
