package Configurations;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ConfigRPM extends ConfigG
{
	//static Properties prop = new Properties();
	static String CHEMIN_FICHIER = "src/config.properties";
	static String ESSAIS = "application.configuration.recherchepm.essais";
	static String NB_CASES_COMBI = "application.configuration.recherchepm.nombre.cases.combi";
	static String MODE_DEVELOPPEUR = "application.configuration.mode.developpeur";

	public ConfigRPM()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public static ConfigurationRPM loadConfigRPM()
	{
		ConfigurationRPM configRPM = new ConfigurationRPM();
		//final Properties prop = new Properties();
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

	public static void saveConfigRPM(ConfigurationRPM configRPM)
	{
		//final Properties prop = new Properties();
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
