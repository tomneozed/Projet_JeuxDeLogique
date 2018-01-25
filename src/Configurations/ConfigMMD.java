package Configurations;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Classe fille de ConfigG spécialisée dans la lecture/écriture des paramètres
 * du Mastermind
 * 
 * @author Thomas Pelissier
 * @version 1.0
 *
 */
public class ConfigMMD extends ConfigG
{
	//Attributs
	static String CHEMIN_FICHIER = "src/config.properties";
	static String ESSAIS = "application.configuration.mastermind.essais";
	static String NB_CASES_COMBI = "application.configuration.mastermind.nombre.cases.combi";
	static String NB_COULEURS = "application.configuration.mastermind.nombre.couleurs";

	//Constructeur
	public ConfigMMD()
	{
		super();

	}

	//Methodes 

	@Override
	public String toString()
	{
		return "ConfigMMD [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

	/**
	 * Récupère les valeurs "nombre d'essais", "nombre de cases de la combianison",
	 * "nombre de couleurs" possibles, et "mode développeur" relatives au Mastermind
	 * du fichier config.properties
	 * 
	 * @return configMMD
	 * @see ConfigurationMMD
	 */
	public static ConfigurationMMD loadConfigMMD()
	{
		ConfigurationMMD configMMD = new ConfigurationMMD();
		//final Properties prop = new Properties();
		InputStream input = null;

		try
		{
			input = new FileInputStream(CHEMIN_FICHIER);
			prop.load(input);

			configMMD.setNbrEssaisMMD(Integer.valueOf(prop.getProperty(ESSAIS)));
			configMMD.setNbrCasesCombiMMD(Integer.valueOf(prop.getProperty(NB_CASES_COMBI)));
			configMMD.setNbrCouleursMMD(Integer.valueOf(prop.getProperty(NB_COULEURS)));
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

	/**
	 * Ecrit les valeurs "nombre d'essais", "nombre de cases de la combianison",
	 * "nombre de couleurs" possibles, et "mode développeur" relatives au Mastermind
	 * dans le fichier config.properties
	 * 
	 * @param configMMD
	 *            objet config mastermind
	 * @see ConfigurationMMD
	 */
	public static void saveConfigMMD(ConfigurationMMD configMMD)
	{

		OutputStream output = null;

		try
		{
			output = new FileOutputStream(CHEMIN_FICHIER);

			prop.setProperty(ESSAIS, configMMD.getNbrEssaisMMD().toString());
			prop.setProperty(NB_CASES_COMBI, configMMD.getNbrCasesCombiMMD().toString());
			prop.setProperty(NB_COULEURS, configMMD.getNbrCouleursMMD().toString());
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
