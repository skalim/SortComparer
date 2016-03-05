package saad.sortcomparer.homescreen;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import saad.sortcomparer.R;

/**
 * Custom container to centralize the common logic for the sliding
 * card views. Includes state accessors that the behavior will
 * access to properly do a layout pass.
 */

@CoordinatorLayout.DefaultBehavior(SlidingCardBehavior.class)
public class SlidingCardLayout extends FrameLayout {
    private int mHeaderViewHeight;

    public SlidingCardLayout(Context context) {
        this(context, null);
    }

    public SlidingCardLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlidingCardLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        /*LayoutInflater.from(context).inflate(R.layout.card_sorts_list, this);

        TextView header = (TextView) findViewById(R.id.header);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SlidingCardLayout, defStyleAttr, 0);
        header.setBackgroundColor(a.getColor(R.styleable.SlidingCardLayout_android_colorBackground, Color.BLACK));
        header.setText(a.getText(R.styleable.SlidingCardLayout_android_text));
        a.recycle();*/
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (w != oldw || h != oldh) {
            mHeaderViewHeight = findViewById(R.id.header).getMeasuredHeight();
        }
    }

    public int getHeaderHeight() {
        return mHeaderViewHeight;
    }
}

