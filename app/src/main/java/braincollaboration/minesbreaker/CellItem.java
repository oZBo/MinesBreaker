package braincollaboration.minesbreaker;


import android.view.View;

public class CellItem implements View.OnClickListener {
    int i;
    private int height, width;
    private boolean statePressed = false;
    private boolean haveMine = false;
    public int countMines = 0;
    private boolean state = true;

    public CellItem(int i) {
        this.i = i;
    }

    public void onClick(View v) {
        chooseNumber(i);
        if (haveMine)
            GameActivity.buttonMass[i].setBackgroundResource(R.drawable.bomb);
        if (GameActivity.cellMass[i].countMines == 0) {
            findNeighbor(i);
        }
    }

    private void findNeighbor(int j) {
        int currentCell = Constants.WIDTH % 12;
        int cell = 1;

        if (currentCell > 0) {
            cell = j - 1;
            if (cell >= 0)
                if (currentCell - 1 >= 0)
                    if (GameActivity.cellMass[cell].state && GameActivity.cellMass[cell].countMines == 0) {
                        GameActivity.cellMass[j].state = false;
                        chooseNumber(cell);
                        showCell(cell);
                        findNeighbor(cell);
                    }
        }
        if (currentCell < Constants.WIDTH) {
            cell = j + 1;
            if (currentCell + 1 < Constants.WIDTH)
                if (cell < Constants.WIDTH * Constants.HEIGHT)
                    if (GameActivity.cellMass[cell].state && GameActivity.cellMass[cell].countMines == 0) {
                        GameActivity.cellMass[j].state = false;
                        chooseNumber(cell);
                        showCell(cell);
                        findNeighbor(cell);
                    }
        }

        cell = j - Constants.WIDTH;
        if (cell >= 0)
            if (GameActivity.cellMass[cell].state && GameActivity.cellMass[cell].countMines == 0) {
                GameActivity.cellMass[j].state = false;
                chooseNumber(cell);
                showCell(cell);
                findNeighbor(cell);
            }

        cell = j + Constants.WIDTH;
        if (cell < Constants.WIDTH * Constants.HEIGHT)
            if (GameActivity.cellMass[cell].state && GameActivity.cellMass[cell].countMines == 0) {
                GameActivity.cellMass[j].state = false;
                chooseNumber(cell);
                showCell(cell);
                findNeighbor(cell);
            }

    }

    private void showCell(int cell) {
        int currentCell = cell % Constants.WIDTH;

        if (currentCell + 1 < Constants.WIDTH) { // right
            chooseNumber(cell + 1);
        }
        if (currentCell - 1 >= 0) { // left
            chooseNumber(cell - 1);
        }
        if (cell - Constants.WIDTH > 0) {   // top
            chooseNumber(cell - Constants.WIDTH);
        }
        if (cell + Constants.WIDTH < Constants.WIDTH * Constants.HEIGHT) { // down
            chooseNumber(cell + Constants.WIDTH);
        }
        if (cell + Constants.WIDTH - 1 < Constants.WIDTH * Constants.HEIGHT) { //����� �����
            if (currentCell - 1 >= 0)
                chooseNumber(cell + Constants.WIDTH - 1);
        }
        if (cell - Constants.WIDTH - 1 >= 0) { //����� ������
            if (currentCell - 1 >= 0)
                chooseNumber(cell - Constants.WIDTH - 1);
        }
        if (cell - Constants.WIDTH + 1 >= 0) { //������ �����
            if (currentCell + 1 < Constants.HEIGHT)
                chooseNumber(cell - Constants.WIDTH + 1);
        }
        if (cell + Constants.WIDTH + 1 < Constants.WIDTH * Constants.HEIGHT) { //������ ����
            if (currentCell + 1 < Constants.HEIGHT)
                chooseNumber(cell + Constants.WIDTH + 1);
        }
    }

    public void findMines(int side, int height, int width) {
        this.height = height;
        this.width = width;

        switch (side) {
            case 1:
                if (GameActivity.cellMass[(height * Constants.WIDTH) + width + 1].haveMine)
                    countMines++;//������
                if (GameActivity.cellMass[((height - 1) * Constants.WIDTH) + width].haveMine)
                    countMines++;//������
                if (GameActivity.cellMass[((height - 1) * Constants.WIDTH) + width + 1].haveMine)
                    countMines++;//������ ������ �� ����
                break;
            case 2:
                if (GameActivity.cellMass[(height * Constants.WIDTH) + width + 1].haveMine)
                    countMines++;//������
                if (GameActivity.cellMass[((height - 1) * Constants.WIDTH) + width].haveMine)
                    countMines++;//������
                if (GameActivity.cellMass[((height - 1) * Constants.WIDTH) + width + 1].haveMine)
                    countMines++;//������ ������ �� ����
                if (GameActivity.cellMass[((height + 1) * Constants.WIDTH) + width].haveMine)
                    countMines++;//�����
                if (GameActivity.cellMass[((height + 1) * Constants.WIDTH) + width + 1].haveMine)
                    countMines++;//����� ������ �� ����
                break;
            case 3:
                if (GameActivity.cellMass[(height * Constants.WIDTH) + width + 1].haveMine)
                    countMines++;//������
                if (GameActivity.cellMass[((height + 1) * Constants.WIDTH) + width].haveMine)
                    countMines++;//�����
                if (GameActivity.cellMass[((height + 1) * Constants.WIDTH) + width + 1].haveMine)
                    countMines++;//����� ������ �� ����
                break;
            case 4:
                if (GameActivity.cellMass[(height * Constants.WIDTH) + width + 1].haveMine)
                    countMines++;//������
                if (GameActivity.cellMass[((height + 1) * Constants.WIDTH) + width].haveMine)
                    countMines++;//�����
                if (GameActivity.cellMass[((height + 1) * Constants.WIDTH) + width + 1].haveMine)
                    countMines++;//����� ������ �� ����
                if (GameActivity.cellMass[(height * Constants.WIDTH) + width - 1].haveMine)
                    countMines++;//�����
                if (GameActivity.cellMass[((height + 1) * Constants.WIDTH) + width - 1].haveMine)
                    countMines++;//����� ����� �� ����
                break;
            case 5:
                if (GameActivity.cellMass[(height * Constants.WIDTH) + width - 1].haveMine)
                    countMines++;//�����
                if (GameActivity.cellMass[((height + 1) * Constants.WIDTH) + width - 1].haveMine)
                    countMines++;//����� ����� �� ����
                if (GameActivity.cellMass[((height + 1) * Constants.WIDTH) + width].haveMine)
                    countMines++;//�����
                break;
            case 6:
                if (GameActivity.cellMass[((height - 1) * Constants.WIDTH) + width].haveMine)
                    countMines++;//������
                if (GameActivity.cellMass[((height - 1) * Constants.WIDTH) + width - 1].haveMine)
                    countMines++;//����� ������ �� ����
                if (GameActivity.cellMass[(height * Constants.WIDTH) + width - 1].haveMine)
                    countMines++;//�����
                if (GameActivity.cellMass[((height + 1) * Constants.WIDTH) + width - 1].haveMine)
                    countMines++;//����� ����� �� ����
                if (GameActivity.cellMass[((height + 1) * Constants.WIDTH) + width].haveMine)
                    countMines++;//�����
                break;
            case 7:
                if (GameActivity.cellMass[((height - 1) * Constants.WIDTH) + width].haveMine)
                    countMines++;//������
                if (GameActivity.cellMass[((height - 1) * Constants.WIDTH) + width - 1].haveMine)
                    countMines++;//����� ������ �� ����
                if (GameActivity.cellMass[(height * Constants.WIDTH) + width - 1].haveMine)
                    countMines++;//�����
                break;
            case 8:
                if (GameActivity.cellMass[(height * Constants.WIDTH) + width + 1].haveMine)
                    countMines++;//������
                if (GameActivity.cellMass[((height - 1) * Constants.WIDTH) + width + 1].haveMine)
                    countMines++;//������ ������ �� ����
                if (GameActivity.cellMass[((height - 1) * Constants.WIDTH) + width].haveMine)
                    countMines++;//������
                if (GameActivity.cellMass[((height - 1) * Constants.WIDTH) + width - 1].haveMine)
                    countMines++;//����� ������ �� ����
                if (GameActivity.cellMass[(height * Constants.WIDTH) + width - 1].haveMine)
                    countMines++;//�����
                break;
            case 9:
                if (GameActivity.cellMass[(height * Constants.WIDTH) + width + 1].haveMine)
                    countMines++;//������
                if (GameActivity.cellMass[((height - 1) * Constants.WIDTH) + width + 1].haveMine)
                    countMines++;//������ ������ �� ����
                if (GameActivity.cellMass[((height - 1) * Constants.WIDTH) + width].haveMine)
                    countMines++;//������
                if (GameActivity.cellMass[((height - 1) * Constants.WIDTH) + width - 1].haveMine)
                    countMines++;//����� ������ �� ����
                if (GameActivity.cellMass[(height * Constants.WIDTH) + width - 1].haveMine)
                    countMines++;//�����
                if (GameActivity.cellMass[((height + 1) * Constants.WIDTH) + width - 1].haveMine)
                    countMines++;//����� ����� �� ����
                if (GameActivity.cellMass[((height + 1) * Constants.WIDTH) + width].haveMine)
                    countMines++;//�����
                if (GameActivity.cellMass[((height + 1) * Constants.WIDTH) + width + 1].haveMine)
                    countMines++;//����� ������ �� ����

                break;
        }

        // +---------+------------+---------+
        // | ������1 | ������2 | ������3 |
        // | ������4 | �����. ��� | ������6 |
        // | ������7 | ������8 | ������9 |
        // +---------+------------+---------+


    }

    public void chooseNumber(int i) {
        switch (GameActivity.cellMass[i].getCountMines()) {
            case 0:
                GameActivity.buttonMass[i].setBackgroundResource(R.drawable.zero);
                break;
            case 1:
                GameActivity.buttonMass[i].setBackgroundResource(R.drawable.one);
                break;
            case 2:
                GameActivity.buttonMass[i].setBackgroundResource(R.drawable.two);
                break;
            case 3:
                GameActivity.buttonMass[i].setBackgroundResource(R.drawable.three);
                break;
            case 4:
                GameActivity.buttonMass[i].setBackgroundResource(R.drawable.four);
                break;
        }
    }

    public boolean isStatePressed() {
        return statePressed;
    }

    public void setStatePressed(boolean statePressed) {
        this.statePressed = statePressed;
    }

    public boolean isHaveMine() {
        return haveMine;
    }

    public void setHaveMine(boolean haveMine) {
        this.haveMine = haveMine;
    }

    public int getCountMines() {
        return countMines;
    }

    public void setCountMines(int countMines) {
        this.countMines = countMines;
    }
}
