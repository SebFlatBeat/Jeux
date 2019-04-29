package com.enedis.sebastien.Config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class GetPropertyValues {
    String result = "";
    InputStream inputStream;

    public String getPropValues() throws IOException {

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

            // get the property value and print it out
            String nbMaxEssais = prop.getProperty("nbMaxEssais");
            String nbChiffreJoueur = prop.getProperty("nbChiffreJoueur");
            String nbChiffreCpu = prop.getProperty("nbChiffreCpu");


            result = "Parametres = " + nbMaxEssais + ", " + nbChiffreJoueur+ ", " + nbChiffreCpu;
            System.out.println(result + "\nProgram Ran on " + time);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
        return result;
    }
}
