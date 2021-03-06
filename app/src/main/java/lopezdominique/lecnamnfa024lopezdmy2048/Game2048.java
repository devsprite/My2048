package lopezdominique.lecnamnfa024lopezdmy2048;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by dominique on 15/03/15.
 */
public class Game2048 {

    private int score;
    private int bestR;
    private int nbv;

    private Random rand;

    private String lastP;

    private Tile[][] board;


    /**
     * Constructeur
     */
    public Game2048() {
        board = new Tile[4][4];
        score = 0;
        bestR = 0;
        lastP = "";

    }

    /**
     * Methode d'initialisation de la grille avec des tuiles pré-déterminés
     */
    public void iniTest() {

        // Rempli le board avec des valeurs de rang pré-déterminées
        int tabPreRempli[][] = {
                {1, 3, 0, 1},
                {0, 1, 1, 0},
                {1, 1, 1, 1},
                {1, 1, 2, 2}
        };

        for (int l = 0; l < 4; l++) {
            for (int c = 0; c < 4; c++) {
                board[l][c] = new Tile();
                getTile(l, c).set(tabPreRempli[l][c], -1);
                bestR = Math.max(board[l][c].r, bestR);
                if (tabPreRempli[l][c] != 0) nbv--;
            }
        }
    }

    /**
     * Initialisation de game, on ajoute les objets Tile avec le paramêtre r allant de 17 à 2
     */
    public void init() {

        for (int l = 0; l < 4; l++) {
            for (int c = 0; c < 4; c++) {
                board[l][c] = new Tile();
            }
        }

        nbv = 16;

        addTile();
        addTile();

    }

    /**
     * getter pour l'objet Tile
     *
     * @param l int numéro de ligne 0 à 3
     * @param c int numéro de colone 0 à 3
     * @return un objet Tile
     */
    public Tile getTile(int l, int c) {
        return board[l][c];
    }

    public int getScore() {
        return score;
    }

    public int getBestR() {
        return bestR;
    }

    public String getLastP() {
        return lastP;
    }

    private void addTile() {
        int nbrCaseAleatoire;
        int nbrRangAleatoire;
        int cp = 0;
        int rang;
        rand = new Random();

        nbrCaseAleatoire = rand.nextInt(nbv) + 1;
        nbrRangAleatoire = rand.nextInt(100);

        for (int l = 0; l < 4; l++) {
            for (int c = 0; c < 4; c++) {

                if (this.getTile(l, c).flag == 0) {
                    cp++;

                    if (nbrCaseAleatoire == cp) {

                        if (nbrRangAleatoire < 10) {
                            rang = 1;
                        } else {
                            rang = 2;
                        }

                        getTile(l, c).set(rang, -1);
                        nbv--;
                        bestR = Math.max(board[l][c].r, bestR);

                        Log.i("INFO2048 ", "Tile[" + l + "][" + c + "] : r = " + rang + " ; flag = -1");
                        Log.i("INFO2048 ", "nbv = " + nbv + "; nbrAléatoire = " + nbrCaseAleatoire);
                        Log.i("INFO2048 ", "bestR = " + bestR);

                    }
                }
            }
        }

    }

    public void move(Boolean croiss, Boolean vert) {
        Log.i("INFO2048", "move ( croiss = " + croiss + ", vert = " + vert);
        ArrayList<Integer> pile = new ArrayList<>(4);

        String infos =" ";
        for (int lc=0;lc<4;lc++){
            pile.clear();
            for (int i=0;i<4;i++){
                if (getTile(lc,i,croiss,vert).getRank()!=0){
                    pile.add(getTile(lc,i,croiss,vert).getRank());
                }
            }
            for (int ip=0; ip<pile.size();ip++){
                infos +=pile.get(ip);
            }
            infos += "\n";
        }
        Log.i("INFO2048", "Board = \n"+infos);


    }


    /**
     * Retourne la bonne tuile en focntion du parcours demandé
     *
     * @param lc     int : position de la ligne ou de la colonne
     * @param i      int : position dans ArrayList<>
     * @param croiss Boolean : true parcours croissant ( de gauche à droite ), false parcours décroissant de droite à gauche
     * @param vert   Boolean : true parcours vertical, false parcours par ligne
     * @return
     */
    public Tile getTile(int lc, int i, Boolean croiss, Boolean vert) {

        int b_lc = 0; // Ligne ou colonne en fonction de croiss et vert
        int b_i  = 0; // Position dans la liste en fonction de croiss et vert

        // Direction haut
        if (croiss && vert){
            b_lc = lc;
            b_i = i;
        }

        // Direction bas
        if (!croiss && vert){
            b_lc = lc;
            b_i = 3-i;
        }

        // Direction gauche
        if (croiss && !vert) {
            b_lc = i;
            b_i = lc;
        }

        // Direction droite
        if (!croiss && !vert) {
            b_lc = 3-i;
            b_i = lc;
        }

       // Log.i("INFO2048 getTile()", "Board["+b_lc+"]["+b_i+"]. rang = "+board[b_lc][b_i].getRank() );
        return board[b_i][b_lc];

    }


    //*************************************************************************************

    /**
     * Classe Tile intégrer à la classe Game2038 car elle ne peut exister sans Game2048
     */
    public static class Tile {
        private int[] pow2 = new int[17]; // tableau de valeurs croissantes des puissances de 2 à partir de  2^1 jusqu'à 2^17
        private int flag; // entier, état de la tuile, new ou old ?

        private int r; // entier, puissance de 2 (0,1,2,3,4...) non le résultat

        public Tile() {
            flag = 0;
            r = 0;
            pow2 = initPow2();
        }

        public Tile(int flag, int r) {
            flag = flag;
            r = r;
            pow2 = initPow2();
        }

        /**
         * Initialise le tableau avec les valeurs de puissances de 2, de 2^1 jusqu'à 2^17
         *
         * @return un tableau d'entier pour initialiser pow2
         */
        public int[] initPow2() {
            pow2[0] = 0;
            for (int i = 1; i < 17; i++) {
                pow2[i] = (int) Math.pow(2, i);
            }
            return pow2;
        }

        /**
         * Getter sur r
         *
         * @return int r
         */
        public int getRank() {
            return r;
        }

        /**
         * Retourne 2^r en utilisant le tableau statique
         *
         * @param r int
         * @return int 2^r
         */
        public int value(int r) {
            return pow2[r];
        }

        /**
         * Retourne l'état de la tuile
         *
         * @return boolean
         */
        public boolean isNew() {
            return flag == -1 ? true : false;
        }

        /**
         * Fusion de la tuile ?
         *
         * @return boolean
         */
        public boolean isFusion() {
            return flag == 1 ? true : false;
        }

        /**
         * retourne une chaine vide si r==0 ou la chaine représantant 2^r
         *
         * @return String
         */
        public String toString() {
            return (r == 0) ? "" : Integer.toString((int) Math.pow(2, r));
        }

        /**
         * Modifie le rang et le flag d'une tuile
         *
         * @param rk valeur du rang
         * @param fl valeur du flag
         */
        private void set(int rk, int fl) {
            flag = fl;
            r = rk;
        }
    }
}








