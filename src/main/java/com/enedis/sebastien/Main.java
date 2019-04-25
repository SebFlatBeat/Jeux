package com.enedis.sebastien;
import com.enedis.sebastien.Jeux.Mastermind;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

        private static final Logger LOGGER = LogManager.getLogger();

        public static void main(String[] args) {
            LOGGER.info("Démarrage de l'application");
            /**
             Menu menu = new Menu();
             menu.devMode();
*/
            Mastermind mastermind = new Mastermind();
            mastermind.defenseMode();
        }

    }
