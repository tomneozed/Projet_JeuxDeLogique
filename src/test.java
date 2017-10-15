import java.util.Hashtable;

public class test
{
	public test()
	{
		Hashtable hashCombi = new Hashtable();
		Hashtable hashProposition = new Hashtable();
		Hashtable compteTable = new Hashtable();
		Hashtable masterTable = new Hashtable();

		int[] comb =
		{ 3, 0, 1, 5, 2, 6, 4, 7, 9, 0 };
		int[] prop =
		{ 3, 0, 1, 5, 2, 6, 4, 7, 9, 0 };

		for (int i = 0; i < comb.length; i++)
		{
			hashCombi.put(i, comb[i]);
		}

		for (int i = 0; i < prop.length; i++)
		{
			hashProposition.put(i, prop[i]);
		}

		for (int i = 0; i < 10; i++)
		{
			compteTable.put(i, compteCombien(i, comb));
		}

		System.out.println(compteTable);

		for (int i = 0; i < hashCombi.size(); i++)
		{
			if (hashProposition.contains(hashCombi.get(i)))
			{
				if (hashCombi.get(i) == hashProposition.get(i))
				{
					System.out.println("Rond noir ++");
				} else
				{
					System.out.println("Rond blanc ++");
				}
			}
		}
	}

	public int compteCombien(int x, int[] tab)
	{
		int nombre = 0;

		for (int i = 0; i < tab.length; i++)
		{
			if (x == tab[i])
			{
				nombre++;
			}
		}
		return nombre;
	}
}
