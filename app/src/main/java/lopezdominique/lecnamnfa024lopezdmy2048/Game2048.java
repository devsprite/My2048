package lopezdominique.lecnamnfa024lopezdmy2048;

/**
 * Created by dominique on 15/03/15.
 */
public class Game2048 {

    private Tile[][] board;


    public Game2048() {
        board = new Tile[4][4];
    }

    public void init(){
        int cp = 17;
        for (int l = 0; l < 4 ; l++ ){
            for (int c = 0; c < 4; c++){
                board[l][c] = new Tile();
                board[l][c].r = cp;
                cp--;
            }
        }
    }

    public Tile getTile(int l, int c){ return board[l][c]; }





    public static class Tile {
        private int[] pow2 = new int[17];
        private int flag; // entier, état de la tuile
        private int r; // entier, puissance de 2 (0,1,2,3,4...) non le résultat

        public Tile() {
            flag = 0;
            r = 0;
            pow2 = initPow2();
        }

        public Tile(int flag, int r) {
            this.flag = flag;
            this.r = r;
            this.pow2 = initPow2();
        }

        /**
         *
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
         *
         * @return r
         */
        public int getRank() {
            return r;
        }

        /**
         *
         * @param r int
         * @return 2^r
         */
        public int value(int r) {
            return pow2[r];
        }

        /**
         * Retourne true ou false
         * @param flag int
         * @return boolean
         */
        public boolean isNew(int flag) {
            return flag == -1 ? true : false;
        }

        /**
         *
         * @param flag int
         * @return boolean
         */
        public boolean isFusion(int flag) {
            return flag == 1 ? true : false;
        }

        /**
         *
         * @return String
         */
        public String toString() {
            return (r == 0) ? "" : Integer.toString((int)Math.pow(2,r));
        }


    }


}
