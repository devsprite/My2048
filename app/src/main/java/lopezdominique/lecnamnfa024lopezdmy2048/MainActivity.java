package lopezdominique.lecnamnfa024lopezdmy2048;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private int[][] boxId = new int[4][4]; // Tableau des identifiants des TextView
    private int[] colID = new int[21]; // Identifiant des ressources correspondant aux couleurs 0 à 17 couleurs de fond, +3 couleurs de textes
    private int[] color = new int[21];

    private TextView[][] box = new TextView[4][4];// Tableau des TextView en correspondance avec les tuiles de l'UI
    private TextView scoreTV;
    private TextView lastTPV;

    private ImageButton buttonUp;
    private ImageButton buttonDown;
    private ImageButton buttonLeft;
    private ImageButton buttonRight;


    private RatingBar bestTRB;

    private Game2048 game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scoreTV = (TextView) findViewById(R.id.scoreTV);
        lastTPV = (TextView) findViewById(R.id.lastTPV);
        bestTRB = (RatingBar) findViewById(R.id.bestTRB);

        buttonDown = (ImageButton) findViewById(R.id.buttonD);
        buttonUp = (ImageButton) findViewById(R.id.buttonU);
        buttonLeft = (ImageButton) findViewById(R.id.buttonL);
        buttonRight = (ImageButton) findViewById(R.id.buttonR);

        buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tryMove(0);
            }
        });
        buttonUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tryMove(1);
            }
        });
        buttonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tryMove(2);
            }
        });
        buttonDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tryMove(3);
            }
        });


        scoreTV.setText("3 932 000");
        lastTPV.setText("2+4+128");
        bestTRB.setRating(5);




// Initialisation du tableau des couleurs
        colID[0] = R.color.col00;
        colID[1] = R.color.col01;
        colID[2] = R.color.col02;
        colID[3] = R.color.col03;
        colID[4] = R.color.col04;
        colID[5] = R.color.col05;
        colID[6] = R.color.col06;
        colID[7] = R.color.col07;
        colID[8] = R.color.col08;
        colID[9] = R.color.col09;
        colID[10] = R.color.col10;
        colID[11] = R.color.col11;
        colID[12] = R.color.col12;
        colID[13] = R.color.col13;
        colID[14] = R.color.col14;
        colID[15] = R.color.col15;
        colID[16] = R.color.col16;
        colID[17] = R.color.col17;
        colID[18] = R.color.colNT;
        colID[19] = R.color.colDT;
        colID[20] = R.color.colBT;
// Les méthodes comme setBackgroundColor et setTextColor nécessite une valeur de couleur que nous disposerons dans un tableau
        for (int i = 0; i<color.length; i++) {
            color[i] = getResources().getColor(colID[i]);
        }

        initialisationTableau(); // Initialisation tableau d'identifiants des TextView
        game = new Game2048();
        game.init();
        update();
    }

    private View.OnClickListener tryMove(int i) {

        Log.i("ARROW", " = " + i);
        return null;
    }


    /**
     * Initialisation du tableau des identifiants des TexView et affichage dans chaque TextView de "lc="+l+c
     */
    private void initialisationTableau() {
        // Récupération à la main des identifiants des TextView
        // boxId[0][0] = R.id.box00;
        // Dans une boucle 2D;
        boxId[0][0] = R.id.box00;
        boxId[0][1] = R.id.box01;
        boxId[0][2] = R.id.box02;
        boxId[0][3] = R.id.box03;
        boxId[1][0] = R.id.box11;
        boxId[1][1] = R.id.box12;
        boxId[1][2] = R.id.box13;
        boxId[1][3] = R.id.box14;
        boxId[2][0] = R.id.box20;
        boxId[2][1] = R.id.box21;
        boxId[2][2] = R.id.box22;
        boxId[2][3] = R.id.box23;
        boxId[3][0] = R.id.box30;
        boxId[3][1] = R.id.box31;
        boxId[3][2] = R.id.box32;
        boxId[3][3] = R.id.box33;

        for (int l = 0; l<4 ; l++)
            for (int c = 0; c < 4; c++) {
                box[l][c] = (TextView) findViewById(boxId[l][c]);
                box[l][c].setText("lc="+l+c);
            }
    }


    /**
     * Mise à jour de l'affichage dans chaque TextView avec la valeur de 2^r
     */
    public void update(){
        for(int l=0; l < boxId.length; l++ ) {
            for (int c=0; c < boxId[l].length; c++){
                Game2048.Tile tile = game.getTile(l,c);
                box[l][c].setText(tile.toString());
                box[l][c].setBackgroundColor(color[tile.getRank()]);

// Les tuiles de rang inférieur à 3 sont en texte noir, sinon en blanc
                if (tile.getRank()<=3) {
                    box[l][c].setTextColor(color[19]);
                }else{
                    box[l][c].setTextColor(color[20]);
                }
// Les nouvelles tuiles sont en text rouge
                if (tile.isNew()){
                    box[l][c].setTextColor(color[18]);
                }

            }
        }
    }


    /**
     * Méthode pour créer un plateau carré suivant le device
     * @param hasFocus
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        LinearLayout globalLayout;
        RelativeLayout  scoreLayout;
        RelativeLayout  controlLayout;
        TableLayout boardLayout;
        LinearLayout.LayoutParams scoreLayoutParams;
        LinearLayout.LayoutParams controlLayoutParams;
        LinearLayout.LayoutParams boardLayoutParams;

        double lBoard;
        double hGlobal;
        int wBoard;
        int wScore;
        int wControl;

        globalLayout = (LinearLayout)findViewById(R.id.globalLO);
        scoreLayout = (RelativeLayout)findViewById(R.id.scoreLO);
        controlLayout = (RelativeLayout )findViewById(R.id.controlLO);
        boardLayout = (TableLayout )findViewById(R.id.boardLO);

        scoreLayoutParams = (LinearLayout.LayoutParams) scoreLayout.getLayoutParams();
        controlLayoutParams = (LinearLayout.LayoutParams) controlLayout.getLayoutParams();
        boardLayoutParams = (LinearLayout.LayoutParams) boardLayout.getLayoutParams();

        hGlobal = globalLayout.getHeight();
        lBoard = boardLayout.getWidth();

        wBoard = (int)(80*(lBoard/hGlobal));
        wScore = (int)(0.25*(100-wBoard));
        wControl = (int)(0.75*(100-wBoard));

        controlLayoutParams.weight = wControl;
        controlLayout.setLayoutParams(controlLayoutParams);

        scoreLayoutParams.weight = wScore;
        scoreLayout.setLayoutParams(scoreLayoutParams);

        boardLayoutParams.weight = wBoard;
        boardLayout.setLayoutParams(boardLayoutParams);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_new) {
            game.init();
            update();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
