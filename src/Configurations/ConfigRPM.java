package Configurations;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class ConfigRPM
{
	static String CHEMIN_FICHIER = "src/config.properties";
	static String ESSAIS = "application.configuration.essais";
	static String NB_CASES_COMBI = "application.configuration.nombre.cases.combi";
	static String MODE_DEVELOPPEUR = "application.configuration.mode.developpeur";

	public static ConfigurationRPM loadConfig()
	{
		ConfigurationRPM configRPM = new ConfigurationRPM();
		final Properties prop = new Properties();
		InputStream input = null;

		try
		{
			input = new FileInputStream(CHEMIN_FICHIER);
			prop.load(input);

			configRPM.setEssais(Integer.valueOf(prop.getProperty(ESSAIS)));
			configRPM.setNombreCasesCombi(Integer.valueOf(prop.getProperty(NB_CASES_COMBI)));
			configRPM.setModeDeveloppeur(Boolean.valueOf(prop.getProperty(MODE_DEVELOPPEUR)));

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
		return configRPM;
	}

	public static void saveConfig(ConfigurationRPM configRPM)
	{
		final Properties prop = new Properties();
		OutputStream output = null;

		try
		{
			output = new FileOutputStream(CHEMIN_FICHIER);

			prop.setProperty(ESSAIS, configRPM.getEssais().toString());
			prop.setProperty(NB_CASES_COMBI, configRPM.getNombreCasesCombi().toString());
			prop.setProperty(MODE_DEVELOPPEUR, configRPM.getModeDeveloppeur().toString());
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
