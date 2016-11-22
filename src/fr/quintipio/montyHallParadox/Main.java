package fr.quintipio.montyHallParadox;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Random r = new Random();

        //boucle infinie sur le programme pour le relancer à chaque fois qu'il finit
        do {

            boolean changerChoix;
            int partieEnCours = 1;
            int nombreDePartie = 1000000;
            int compteurGagnant = 0;

            System.out.println("------------Problème de monty Hall--------------");
            System.out.println("Simulation sur " + nombreDePartie + " de partie.");
            System.out.println("Je choisis : ");
            System.out.println("1 - De toujours changer de porte ");
            System.out.println("2 - De ne jamais changer de porte ");
            changerChoix = lireChiffreConsole() == 1;

            do {
                //création des portes
                List<Porte> listePorte = Arrays.asList(new Porte(1), new Porte(2), new Porte(3));
                int choixJoueur;
                int numCacheVoiture;

                //sélection d'une porte au hasard ou on place la voiture. les deux autres possèderont les chèvres
                numCacheVoiture = SelectPorte(r);
                for(Porte porte : listePorte)
                {
                    porte.setObjet(porte.getNumeroPorte() == numCacheVoiture ? ObjetCacheEnum.VOITURE : ObjetCacheEnum.CHEVRE);
                }

                //sélection d'une porte au hasard par le joueur
                choixJoueur = SelectPorte(r);
                final int choixJoueurFinal = choixJoueur;

                //choix de la porte avec la chèvre restante par le présentateur
                int selectPorteOuvertePresentateur = listePorte.stream()
                        .filter(x -> x.getNumeroPorte() != choixJoueurFinal && x.getNumeroPorte() != numCacheVoiture)
                        .findFirst()
                        .get()
                        .getNumeroPorte();

                //si le joueur change de choix ou non
                if (changerChoix)
                {
                    choixJoueur = listePorte.stream()
                                .filter(x -> x.getNumeroPorte() != choixJoueurFinal && x.getNumeroPorte() != selectPorteOuvertePresentateur)
                                .findFirst()
                                .get()
                                .getNumeroPorte();
                }

                //ouverture du choix du joueur
                final int choixJoueurFinalB = choixJoueur;
                if (listePorte.stream()
                                .filter(x -> x.getNumeroPorte() == choixJoueurFinalB)
                                .findFirst()
                                .get()
                                .ouvrirPorte() == ObjetCacheEnum.VOITURE)
                {
                    compteurGagnant++;
                }
                partieEnCours++;

            } while (partieEnCours <= nombreDePartie);

            System.out.println("Taux de réussite : " + (compteurGagnant * 100) / nombreDePartie + " %");
            System.out.println("--------------------------------------------");
        }while(true);
    }


    /**
     * Lit un chiffre 1 ou 2 à partir de la console
     * @return le nombre entré
     */
    private static int lireChiffreConsole() {

        boolean ok = false;
        int input = 0;
        do {
            try {
                Scanner scanner = new Scanner(System.in);
                input = scanner.nextInt();
                ok = input == 1 || input == 2;
            }
            catch(Exception e) {
                ok = false;
            }

            if(!ok) {
                System.out.println("Erreur lors de la saisie, recommencez...");
            }
        }while(!ok);
        return input;
    }

    /**
     * Sélectionne une porte au hasard
     * @param r le générateur aléatoire
     * @return le numéro de la porte
     */
    private static int SelectPorte(Random r)
    {
        int nb = r.nextInt(1000000);

        if (nb <= 333333)
        {
            return 1;
        }

        if (nb > 333333 && nb <= 666666)
        {
            return 2;
        }

        return 3;
    }
}
