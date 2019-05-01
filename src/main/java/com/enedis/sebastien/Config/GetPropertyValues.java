package com.enedis.sebastien.Config;

import com.enedis.sebastien.Jeux.Mastermind;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class GetPropertyValues{
    String result = "";
    InputStream inputStream;


    private static final Logger LOGGER = LogManager.getLogger(Mastermind.class.getName());

    public String getPropValues(String nbMaxEssais) throws IOException {

        try {
            Properties prop = new Properties();
            String propFileName = "config.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            Date time = new Date(System.currentTimeMillis());
            nbMaxEssais = prop.getProperty("nbMaxEssais");

            result = nbMaxEssais;
            LOGGER.info("nbMaxEssais "+result + "\nProgram Ran on " + time);
        } catch (Exception e) {
            LOGGER.error("Exception: " + e);
        } finally {
            inputStream.close();
        }
        return result;
    }

    public String getPropValues2(String nombreEssais) throws IOException {

        try {
            Properties prop = new Properties();
            String propFileName = "config.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            Date time = new Date(System.currentTimeMillis());
            nombreEssais = prop.getProperty("nombreEssais");

            result = nombreEssais;
            LOGGER.info("nombreEssais "+result + "\nProgram Ran on " + time);
        } catch (Exception e) {
            LOGGER.error("Exception: " + e);
        } finally {
            inputStream.close();
        }
        return result;
    }
    public String getPropValues3(String longueurCombinaison) throws IOException {

        try {
            Properties prop = new Properties();
            String propFileName = "config.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            Date time = new Date(System.currentTimeMillis());
            longueurCombinaison = prop.getProperty("longueurCombinaison");

            result = longueurCombinaison;
            LOGGER.info("longueurCombinaison "+result + "\nProgram Ran on " + time);
        } catch (Exception e) {
            LOGGER.error("Exception: " + e);
        } finally {
            inputStream.close();
        }
        return result;
    }
    public String getPropValues4(String devMode) throws IOException {

        try {
            Properties prop = new Properties();
            String propFileName = "config.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            Date time = new Date(System.currentTimeMillis());
            devMode = prop.getProperty("devMode");

            result = devMode;
            LOGGER.info("devMode "+result + "\nProgram Ran on " + time);
        } catch (Exception e) {
            LOGGER.error("Exception: " + e);
        } finally {
            inputStream.close();
        }
        return result;
    }
    public String getPropValues5(String chiffreMinUtilisable) throws IOException {

        try {
            Properties prop = new Properties();
            String propFileName = "config.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            Date time = new Date(System.currentTimeMillis());
            chiffreMinUtilisable = prop.getProperty("chiffreMinUtilisable");

            result = chiffreMinUtilisable;
            LOGGER.info("chiffreMinUtilisable "+result + "\nProgram Ran on " + time);
        } catch (Exception e) {
            LOGGER.error("Exception: " + e);
        } finally {
            inputStream.close();
        }
        return result;
    }    public String getPropValues6(String chiffreMaxUtilisable) throws IOException {

        try {
            Properties prop = new Properties();
            String propFileName = "config.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            Date time = new Date(System.currentTimeMillis());
            chiffreMaxUtilisable = prop.getProperty("chiffreMaxUtilisable");

            result = chiffreMaxUtilisable;
            LOGGER.info("chiffreMaxUtilisable "+result + "\nProgram Ran on " + time);
        } catch (Exception e) {
            LOGGER.error("Exception: " + e);
        } finally {
            inputStream.close();
        }
        return result;
    }
}
