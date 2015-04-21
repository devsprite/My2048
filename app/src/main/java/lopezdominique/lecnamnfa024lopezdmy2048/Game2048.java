package lopezdominique.lecnamnfa024lopezdmy2048;

/**
 * Created by dominique on 15/03/15.
 */
public class Game2048 {

    private Tile[][] board;

    /**
     * Constructeur
     */
    public Game2048() {
        board = new Tile[4][4];
    }

    /**
     * Initialisation de game, on ajoute les objets Tile avec le paramêtre r allant de 17 à 2
     */
    public void init(){
        int cp = 17;
        for (int l = 0; l < 4 ; l++ ){
            for (int c = 0; c < 4; c++){
                board[l][c] = new Tile();
                board[l][c].r = cp;
                cp--;
            }
        }
        board[3][3].flag = -1; // Test new tile
    }

    /**
     * getter pour l'objet Tile
     * @param l int numéro de ligne 0 à 3
     * @param c int numéro de colone 0 à 3
     * @return un objet Tile
     */
    public Tile getTile(int l, int c){ return board[l][c]; }


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
         * @return un tableau d'entier pour initialiser pow2
         */
        public int[] initPow2() {
            pow2[0] = 0;
            for (int i = 1; i < 17; i++) {
                pow2[i] = (int) Math.pow(2,i);
            }
            return pow2;
        }

        /**
         * Getter sur r
         * @return int r
         */
        public int getRank() {
            return r;
        }

        /**
         * Retourne 2^r en utilisant le tableau statique
         * @param r int
         * @return int 2^r
         */
        public int value(int r) {
            return pow2[r];
        }

        /**
         * Retourne l'état de la tuile
         * @return boolean
         */
        public boolean isNew() {
            return flag == -1 ? true : false;
        }

        /**
         * Fusion de la tuile ?
         * @return boolean
         */
        public boolean isFusion() {
            return flag == 1 ? true : false;
        }

        /**
         * retourne une chaine vide si r==0 ou la chaine représantant 2^r
         * @return String
         */
        public String toString() {
            return (r == 0) ? "" : Integer.toString((int)Math.pow(2,r));
        }


    }


}
