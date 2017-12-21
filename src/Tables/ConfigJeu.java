package Tables;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class ConfigJeu
{
	static String CHEMIN_FICHIER = "src/config.properties";
	static String ESSAIS = "application.configuration.essais";
	static String NB_CASES_COMBI = "application.configuration.nombre.cases.combi";
	static String NB_COULEURS = "application.configuration.nombre.couleurs";
	static String MODE_DEVELOPPEUR = "application.configuration.mode.developpeur";

	public static Configuration loadConfig()
	{
		Configuration c = new Configuration();
		final Properties prop = new Properties();
		InputStream input = null;

		try
		{
			input = new FileInputStream(CHEMIN_FICHIER);
			prop.load(input);

			c.setEssais(Integer.valueOf(prop.getProperty(ESSAIS)));
			c.setNombreCasesCombi(Integer.valueOf(prop.getProperty(NB_CASES_COMBI)));
			c.setNombreCouleurs(Integer.valueOf(prop.getProperty(NB_COULEURS)));

		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			if (input != null)
			{
				try
				{
					input.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		return c;
	}

	public static void saveConfig(Configuration c)
	{
		final Properties prop = new Properties();
		OutputStream output = null;

		try
		{
			output = new FileOutputStream(CHEMIN_FICHIER);

			prop.setProperty(ESSAIS, c.getEssais().toString());
			prop.setProperty(NB_CASES_COMBI, c.getNombreCasesCombi().toString());
			prop.setProperty(NB_COULEURS, c.getNombreCouleurs().toString());
			prop.store(output, null);

		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			if (output != null)
			{
				try
				{
					output.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
}
