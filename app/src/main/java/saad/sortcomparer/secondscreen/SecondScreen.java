package saad.sortcomparer.secondscreen;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import saad.sortcomparer.firstscreen.Animator;
import saad.sortcomparer.R;
import saad.sortcomparer.Settings;
import saad.sortcomparer.thirdscreen.ThirdScreen;

public class SecondScreen extends Activity {
    SeekBar seekBar;
    EditText editText;
    CardView arrayCard;
    CardView listCard;
    Animator animator;
    ImageView checkCircleArray;
    ImageView checkCircleList;
    RelativeLayout nextScreenButton;
    boolean isArrayChecked;
    boolean isListChecked;
    int minValue = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        for(int i = 0; i < Settings.algorithmsSelected.size(); i++){
            System.out.println(Settings.algorithmsSelected.get(i));
        }
        animator = new Animator();
        isArrayChecked = false;
        isListChecked = false;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        editText = (EditText) findViewById(R.id.size);
        arrayCard = (CardView) findViewById(R.id.array_card);
        listCard = (CardView) findViewById(R.id.list_card);
        checkCircleArray = (ImageView) findViewById(R.id.check_circle_array);
        checkCircleList = (ImageView) findViewById(R.id.check_circle_list);
        nextScreenButton = (RelativeLayout) findViewById(R.id.start_button);

        setSeekBarSettings();
        setCardSettings();
    }

    public void nextScreen(View view){
        if( !isArrayChecked && !isListChecked ){
            return;
        }

        Settings.listSelected = isListChecked;
        System.out.println("Size: " + editText.getText().toString().replace(",", ""));
        Settings.size = Integer.parseInt( editText.getText().toString().replace(",", "") );
        Intent intent = new Intent(this, ThirdScreen.class);
        startActivity(intent);
    }


    public void setCardSettings() {
        arrayCard.setOnClickListener(new CardView.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!isArrayChecked) {
                    checkCircleArray.setVisibility(View.VISIBLE);
                    animator.animateIn(checkCircleArray);
                    if (isListChecked) {
                        animator.animateOut(checkCircleList);
                        checkCircleList.setVisibility(View.INVISIBLE);
                    }
                }

                isArrayChecked = true;
                isListChecked = false;
                cardSelected(arrayCard);
                cardUnselected(listCard);
            }
        });

        listCard.setOnClickListener(new CardView.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!isListChecked) {
                    checkCircleList.setVisibility(View.VISIBLE);
                    animator.animateIn(checkCircleList);
                    if (isArrayChecked) {
                        animator.animateOut(checkCircleArray);
                        checkCircleArray.setVisibility(View.INVISIBLE);
                    }
                }
                isArrayChecked = false;
                isListChecked = true;
                cardSelected(listCard);
                cardUnselected(arrayCard);
            }
        });

    }

    public void cardSelected(CardView cardView) {
        cardView.setCardBackgroundColor(Color.parseColor("#37474F"));
    }

    public void cardUnselected(CardView cardView) {
        cardView.setCardBackgroundColor(Color.parseColor("#263238"));
    }


    public void setSeekBarSettings() {
        seekBar.setProgress(1);
        seekBar.incrementProgressBy(1);
        seekBar.setMax(26214400
                - minValue);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress = progress + minValue;
                String s = String.format("%,d", progress);
                editText.setText(s);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
