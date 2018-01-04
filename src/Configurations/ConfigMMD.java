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
	static String ESSAIS = "application.configuration.mastermind.essais";
	static String NB_CASES_COMBI = "application.configuration.mastermind.nombre.cases.combi";
	static String NB_COULEURS = "application.configuration.mastermind.nombre.couleurs";

	public static ConfigurationMMD loadConfigMMD()
	{
		ConfigurationMMD configMMD = new ConfigurationMMD();
		final Properties prop = new Properties();
		InputStream input = null;

		try
		{
			input = new FileInputStream(CHEMIN_FICHIER);
			prop.load(input);

			configMMD.setNbrEssaisMMD(Integer.valueOf(prop.getProperty(ESSAIS)));
			configMMD.setNbrCasesCombiMMD(Integer.valueOf(prop.getProperty(NB_CASES_COMBI)));
			configMMD.setNbrCouleursMMD(Integer.valueOf(prop.getProperty(NB_COULEURS)));

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

	public static void saveConfigMMD(ConfigurationMMD configMMD)
	{
		final Properties prop = new Properties();
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
