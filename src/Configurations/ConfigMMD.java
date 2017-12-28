package Configurations;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class ConfigMMD
{
	static String CHEMIN_FICHIER = "src/config.properties";
	static String ESSAIS = "application.configuration.essais";
	static String NB_CASES_COMBI = "application.configuration.nombre.cases.combi";
	static String NB_COULEURS = "application.configuration.nombre.couleurs";
	static String MODE_DEVELOPPEUR = "application.configuration.mode.developpeur";

	public static ConfigurationMMD loadConfig()
	{
		ConfigurationMMD configMMD = new ConfigurationMMD();
		final Properties prop = new Properties();
		InputStream input = null;

		try
		{
			input = new FileInputStream(CHEMIN_FICHIER);
			prop.load(input);

			configMMD.setEssais(Integer.valueOf(prop.getProperty(ESSAIS)));
			configMMD.setNombreCasesCombi(Integer.valueOf(prop.getProperty(NB_CASES_COMBI)));
			configMMD.setNombreCouleurs(Integer.valueOf(prop.getProperty(NB_COULEURS)));
			configMMD.setModeDeveloppeur(Boolean.valueOf(prop.getProperty(MODE_DEVELOPPEUR)));

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
		return configMMD;
	}

	public static void saveConfig(ConfigurationMMD configMMD)
	{
		final Properties prop = new Properties();
		OutputStream output = null;

		try
		{
			output = new FileOutputStream(CHEMIN_FICHIER);

			prop.setProperty(ESSAIS, configMMD.getEssais().toString());
			prop.setProperty(NB_CASES_COMBI, configMMD.getNombreCasesCombi().toString());
			prop.setProperty(NB_COULEURS, configMMD.getNombreCouleurs().toString());
			prop.setProperty(MODE_DEVELOPPEUR, configMMD.getModeDeveloppeur().toString());
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
