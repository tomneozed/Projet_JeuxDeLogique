package Configurations;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class ConfigG
{
	//Attributs
	static String CHEMIN_FICHIER = "src/config.properties";
	static String MODE_DEVELOPPEUR = "application.configuration.mode.developpeur";

	//Constructeurs
	public ConfigG()
	{

	}

	public static ConfigurationG loadConfigG()
	{
		ConfigurationG configG = new ConfigurationG();
		final Properties prop = new Properties();
		InputStream input = null;

		try
		{
			input = new FileInputStream(CHEMIN_FICHIER);
			prop.load(input);
			configG.setModeDeveloppeur(Boolean.valueOf(prop.getProperty(MODE_DEVELOPPEUR)));

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
		return configG;
	}

	public static void saveConfigG(ConfigurationG configG)
	{
		final Properties prop = new Properties();
		OutputStream output = null;

		try
		{
			output = new FileOutputStream(CHEMIN_FICHIER);
			prop.setProperty(MODE_DEVELOPPEUR, configG.getModeDeveloppeur().toString());
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
