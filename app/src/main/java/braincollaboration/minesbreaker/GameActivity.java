package braincollaboration.minesbreaker;


import android.app.Activity;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.Random;

public class GameActivity extends Activity {

    private LinearLayout nameContainer;
    private LinearLayout parentContainer;
    private static int DISPLAY_WIDTH;

    public static Button[] buttonMass = new Button[Constants.WIDTH * Constants.HEIGHT];
    public static CellItem[] cellMass = new CellItem[Constants.WIDTH * Constants.HEIGHT];
    private LinearLayout[] layoutMass = new LinearLayout[Constants.WIDTH];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        startGame();
        setContentView(parentContainer);
    }

    public void startGame() {
        Display display = getWindowManager().getDefaultDisplay();
        DISPLAY_WIDTH = display.getWidth();
        Constants.CELL_WIDTH = DISPLAY_WIDTH / Constants.WIDTH;

        initMass();
        createNameContainer();
        createParentContainer();

        for (int i = 0; i < Constants.MINES; i++) {
            Random random = new Random();
            int randomSeed = random.nextInt(Constants.WIDTH * Constants.HEIGHT);
            cellMass[randomSeed].setHaveMine(true);
            cellMass[randomSeed].countMines = -1;
        }

        for (int i = 0; i < Constants.HEIGHT; i++)
            for (int j = 0; j < Constants.WIDTH; j++) {
                int sideCounter = 9;
                ///////////////////////////Define angles///////////////////////////////
                if (i == 0 && j == 0)
                    sideCounter = 3;
                if (i == Constants.HEIGHT - 1 && j == Constants.WIDTH - 1)
                    sideCounter = 7;
                if (i == Constants.HEIGHT - 1 && j == 0)
                    sideCounter = 1;
                if (i == 0 && j == Constants.WIDTH - 1)
                    sideCounter = 5;
                /////////////////////////////Define other sides/////////////////////////
                if (i == 0 && (j > 0 && j < Constants.WIDTH - 1))
                    sideCounter = 4;
                if (i == Constants.HEIGHT - 1 && (j > 0 && j < Constants.WIDTH - 1))
                    sideCounter = 8;
                if ((i > 0 && i < Constants.HEIGHT - 1) && j == 0)
                    sideCounter = 2;
                if ((i > 0 && i < Constants.HEIGHT - 1) && j == Constants.WIDTH - 1)
                    sideCounter = 6;
                cellMass[i * Constants.WIDTH + j].findMines(sideCounter, i, j);

            }
    }

    private void initMass() {
        for (int i = 0; i < layoutMass.length; i++) {
            layoutMass[i] = new LinearLayout(this);
        }
    }

    private void createNameContainer() {
        for (int j = 0; j < Constants.HEIGHT; j++) {
            nameContainer = layoutMass[j];
            nameContainer.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, Constants.CELL_WIDTH));
            nameContainer.setOrientation(LinearLayout.HORIZONTAL);
            for (int i = 0; i < Constants.WIDTH; i++) {

                buttonMass[j * Constants.WIDTH + i] = new Button(this);
                buttonMass[j * Constants.WIDTH + i].setLayoutParams(new LinearLayout.LayoutParams(Constants.CELL_WIDTH, Constants.CELL_WIDTH));
                buttonMass[j * Constants.WIDTH + i].setBackgroundResource(R.drawable.grassclose);
                buttonMass[j * Constants.WIDTH + i].setOnClickListener(cellMass[j * Constants.WIDTH + i] = new CellItem(j * Constants.WIDTH + i));
                nameContainer.addView(buttonMass[j * Constants.WIDTH + i]);
            }
        }
    }

    private void createParentContainer() {
        parentContainer = new LinearLayout(this);
        parentContainer.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        parentContainer.setOrientation(LinearLayout.VERTICAL);
        parentContainer.setGravity(Gravity.CENTER_HORIZONTAL);
        for (int i = 0; i < Constants.WIDTH; i++) {
            parentContainer.addView(layoutMass[i]);
        }

    }
}
