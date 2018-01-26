package Configurations;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * Classe mère des Configs qui permet de lire/écrire le paramètre
 * "modeDeveloppeur" dans le fichier config.properties
 * 
 * @author Thomas Pelissier
 * @version v 1.0
 * 
 */
public class ConfigG
{
	//Attributs
	static Properties prop = new Properties();

	static String CHEMIN_FICHIER = "src/main/resources/config.properties";
	static String MODE_DEVELOPPEUR = "application.configuration.mode.developpeur";

	//Constructeur
	public ConfigG()
	{
		loadConfigG();
	}

	/**
	 * Récupère la valeur "modeDeveloppeur" du fichier config.properties
	 * 
	 * @return configG
	 * @see ConfigurationG
	 */
	public static ConfigurationG loadConfigG()
	{
		ConfigurationG configG = new ConfigurationG();
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

	/**
	 * Ecrit la valeur "modeDeveloppeur" dans le fichier config.properties
	 * 
	 * @param configG
	 *            objet config générale
	 * @see ConfigurationG
	 */
	public static void saveConfigG(ConfigurationG configG)
	{
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
