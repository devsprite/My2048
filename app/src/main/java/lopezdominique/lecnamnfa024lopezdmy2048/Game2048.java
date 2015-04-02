package lopezdominique.lecnamnfa024lopezdmy2048;

/**
 * Created by dominique on 15/03/15.
 */
public class Game2048 {

    private int[][] board = new int[4][4];

    public Game2048() {
        this.board = board;
    }

    public int getTile(int l, int c){
        return this.board[l][c];
    }


    public static class Tile {
        static int[] pow2 = new int[17];
        int flag; // entier, état de la tuile
        int r; // entier, puissance de 2 (0,1,2,3,4...) non le résultat

        public Tile() {
            this.flag = 0;
            this.r = 0;
            this.pow2 = initPow2();
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
            for (int i = 0; i < 17; i++) {
                pow2[i] = i;
            }
            return pow2;
        }

        /**
         *
         * @return r
         */
        public int getRank() {
            return this.r;
        }

        /**
         *
         * @param r int
         * @return 2^r
         */
        public int value(int r) {
            return (int) Math.pow(2,r);
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
         * @param r int
         * @return String
         */
        public String toString(int r) {
            return (r == 0) ? "" : Integer.toString(value(r));
        }


    }


}
