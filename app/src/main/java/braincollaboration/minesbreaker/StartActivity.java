package braincollaboration.minesbreaker;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends Activity implements View.OnClickListener {

    Button one,two,three;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_window);

        one = (Button)findViewById(R.id.button1);
        one.setOnClickListener(this);
        two = (Button)findViewById(R.id.button2);
        two.setOnClickListener(this);
        three = (Button)findViewById(R.id.button3);
        three.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button1:
                setField(9, 9);
                break;
            case R.id.button2:
                setField(12, 12);
                break;
            case R.id.button3:
                setField(15, 15);
                break;
        }

    }

    private void setField(int i, int mines){
        Constants.WIDTH = i;
        Constants.HEIGHT = i;
        Constants.MINES = mines;
        Intent intent = new Intent(StartActivity.this, GameActivity.class);
        startActivity(intent);
    }

}
