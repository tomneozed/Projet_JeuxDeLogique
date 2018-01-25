package Configurations;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Classe fille de ConfigG spécialisée dans la lecture/écriture des paramètres
 * du Recherche +/-
 * 
 * @author Thomas Pelissier
 * @version 1.0
 *
 */
public class ConfigRPM extends ConfigG
{

	//Attributs
	static String CHEMIN_FICHIER = "src/config.properties";
	static String ESSAIS = "application.configuration.recherchepm.essais";
	static String NB_CASES_COMBI = "application.configuration.recherchepm.nombre.cases.combi";

	//Constructeur
	public ConfigRPM()
	{
		super();
	}

	//Methodes

	/**
	 * Récupère les valeurs "nombre d'essais", "nombre de cases de la combianison",
	 * "nombre de couleurs" possibles, et "mode développeur" relatives au
	 * Recherche+/- du fichier config.properties
	 * 
	 * @return configRPM
	 * @see ConfigurationRPM
	 */
	public static ConfigurationRPM loadConfigRPM()
	{
		ConfigurationRPM configRPM = new ConfigurationRPM();
		InputStream input = null;

		try
		{
			input = new FileInputStream(CHEMIN_FICHIER);
			prop.load(input);

			configRPM.setNbrEssaisRPM(Integer.valueOf(prop.getProperty(ESSAIS)));
			configRPM.setNbrCasesCombiRPM(Integer.valueOf(prop.getProperty(NB_CASES_COMBI)));
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

	/**
	 * Ecrit les valeurs "nombre d'essais", "nombre de cases de la combianison",
	 * "nombre de couleurs" possibles, et "mode développeur" relatives au
	 * Recherche+/- dans le fichier config.properties
	 * 
	 * @param configRPM
	 *            objet config recherche+/-
	 * @see ConfigurationRPM
	 */
	public static void saveConfigRPM(ConfigurationRPM configRPM)
	{
		OutputStream output = null;

		try
		{
			output = new FileOutputStream(CHEMIN_FICHIER);

			prop.setProperty(ESSAIS, configRPM.getNbrEssaisRPM().toString());
			prop.setProperty(NB_CASES_COMBI, configRPM.getNbrCasesCombiRPM().toString());
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
