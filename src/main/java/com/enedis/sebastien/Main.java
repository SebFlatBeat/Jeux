package com.enedis.sebastien;

import com.enedis.sebastien.jeux.Menu;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class Main extends Menu {

        private static final Logger LOGGER = LogManager.getLogger();

        public static void main(String[] args) throws IOException {
            LOGGER.info("Démarrage de l'application");


            if(args.length>0 && args[0].equals("enableDevMode")){
                devMode = true;
            }

             Menu menu = new Menu();
             menu.firstMenu();

        }
}

