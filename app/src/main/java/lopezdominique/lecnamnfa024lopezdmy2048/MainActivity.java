package lopezdominique.lecnamnfa024lopezdmy2048;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private TextView[][] box = new TextView[4][4];// Tableau des TextView en correspondance avec les tuiles de l'UI
    private int [][] boxId = new int[4][4]; // Tableau des identifiants des TextView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialisationTableau();
        Game2048.Tile tile = new Game2048.Tile();
    }

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
                // box[l][c].setText("lc="+l+c);
            }
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

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
}
