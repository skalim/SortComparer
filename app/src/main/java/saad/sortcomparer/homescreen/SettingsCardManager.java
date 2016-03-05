package saad.sortcomparer.homescreen;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import saad.sortcomparer.Animator;
import saad.sortcomparer.R;
import saad.sortcomparer.Settings;

/**
 * Created by Saad on 05-Mar-16.
 */
public class SettingsCardManager {
    final int VALUE_MIN = 1;
    final int VALUE_MAX = 500000;

    // Views
    AppCompatActivity activity;
    SlidingCardLayout cardSettings;
    SeekBar seekBar;
    EditText editText;
    CardView arrayCard;
    CardView listCard;
    Animator animator;
    ImageView checkCircleArray;
    ImageView checkCircleList;

    boolean isArrayChecked;
    boolean isListChecked;

    public SettingsCardManager(Context context) {
        this.activity = ((AppCompatActivity)context);
        bind();

        animator = new Animator();
        isArrayChecked = false;
        isListChecked = false;
        setSeekBarSettings();
        setCardSettings();
    }

    public void bind(){
        cardSettings = (SlidingCardLayout) activity.findViewById(R.id.card_settings);
        seekBar = (SeekBar) cardSettings.findViewById(R.id.seekBar);
        editText = (EditText) cardSettings.findViewById(R.id.size);
        arrayCard = (CardView) cardSettings.findViewById(R.id.array_card);
        listCard = (CardView) cardSettings.findViewById(R.id.list_card);
        checkCircleArray = (ImageView) cardSettings.findViewById(R.id.check_circle_array);
        checkCircleList = (ImageView) cardSettings.findViewById(R.id.check_circle_list);
        TextView headerSettings = (TextView) cardSettings.findViewById(R.id.header);
        headerSettings.setText(activity.getResources().getString(R.string.header_settings));
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
                Settings.listSelected = isListChecked;

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
                Settings.listSelected = isListChecked;

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
        seekBar.setMax(VALUE_MAX - VALUE_MIN);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress = progress + VALUE_MIN;
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

    public boolean noDataStructureSelected(){
        return !isArrayChecked && !isListChecked;
    }

    public int dataStructureSize(){
        return Integer.parseInt(editText.getText().toString().replace(",", ""));
    }
}
