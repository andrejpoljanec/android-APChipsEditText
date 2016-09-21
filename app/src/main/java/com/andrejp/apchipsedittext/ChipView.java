package com.andrejp.apchipsedittext;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Andrej Poljanec on 9/12/2016.
 */
public class ChipView<I> extends LinearLayout {

    private TextView textView;

    private I item;
    private ChipListener<I> listener;

    public interface ChipListener<I> {
        void onChipDismissed(I item);
    }

    public ChipView(Context context) {
        super(context);
        init(context);
    }

    public ChipView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ChipView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View chipLayout = LayoutInflater.from(context).inflate(R.layout.view_chip, this, true);
        textView = (TextView) chipLayout.findViewById(R.id.text_view);

        chipLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (item != null && listener != null) {
                    listener.onChipDismissed(item);
                }
            }
        });
    }

    public void setItem(I item) {
        this.item = item;
        textView.setText(item.toString());
    }

    public void setListener(ChipListener<I> listener) {
        this.listener = listener;
    }
}
