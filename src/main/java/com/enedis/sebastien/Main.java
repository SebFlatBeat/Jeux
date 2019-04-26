package com.enedis.sebastien;

import com.enedis.sebastien.Jeux.Menu;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class Main {

        private static final Logger LOGGER = LogManager.getLogger();

        public static void main(String[] args) {
            LOGGER.info("Démarrage de l'application");

            Properties properties = new Properties();
            properties.setProperty("Jeux","Enedis");
            FileWriter writer = null;
            try{
                writer = new FileWriter("config.properties");
                properties.store(writer,"test");
            }catch (IOException ex){
                ex.printStackTrace();
            }

             Menu menu = new Menu();
             menu.firstMenu();

        }

    }
