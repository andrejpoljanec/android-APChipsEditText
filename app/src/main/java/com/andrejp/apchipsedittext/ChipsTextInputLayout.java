package com.andrejp.apchipsedittext;

import android.content.Context;
import android.graphics.Canvas;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.ListPopupWindow;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Andrej Poljanec on 9/11/2016.
 */
public abstract class ChipsTextInputLayout<I> extends TextInputLayout implements ChipView.ChipListener<I> {

    private LinearLayout chipsLayout;

    private Map<I, ChipView> chipViewMap;
    private ChipsAdapter<I> chipsAdapter;
    private ListPopupWindow listPopupWindow;

    public ChipsTextInputLayout(Context context) {
        super(context);
        init(context);
    }

    public ChipsTextInputLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ChipsTextInputLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(final Context context) {
        final TextInputEditText editText = new TextInputEditText(context);
        addView(editText, Utils.getMPHorizontalWCVerticalParams());
        chipsLayout = new LinearLayout(context);
        chipsLayout.setOrientation(HORIZONTAL);
        addView(chipsLayout, Utils.getMPHorizontalWCVerticalParams());

        chipViewMap = new HashMap<>();

        listPopupWindow = new ListPopupWindow(context);
        chipsAdapter = new ChipsAdapter<>(context);
        listPopupWindow.setAdapter(chipsAdapter);
        listPopupWindow.setAnchorView(getEditText());
        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                ChipView<I> chip = new ChipView<>(context);
                I item = chipsAdapter.getItem(index);
                if (item == null) {
                    item = getItemFromFilter();
                }
                if (item == null) {
                    return;
                }
                chip.setItem(item);
                chip.setListener(ChipsTextInputLayout.this);
                chipsLayout.addView(chip, Utils.getWCParams());
                listPopupWindow.dismiss();
                chipViewMap.put(item, chip);
                updateSelectedChips();
                editText.setText("");
                editText.requestFocus();
            }
        });

        editText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!listPopupWindow.isShowing()) {
                    listPopupWindow.show();
                }
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                chipsAdapter.setFilter(editable.toString());
                if (chipsAdapter.getCount() == 0 && listPopupWindow.isShowing()) {
                    listPopupWindow.dismiss();
                } else if (chipsAdapter.getCount() > 0 && !listPopupWindow.isShowing() && editable.toString().length() > 0) {
                    listPopupWindow.show();
                }
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public String getText() {
        EditText editText = getEditText();
        if (editText != null) {
            return editText.getText().toString();
        } else {
            return "";
        }
    }

    @Override
    public void onChipDismissed(I item) {
        ChipView chipView = chipViewMap.get(item);
        chipsLayout.removeView(chipView);
        chipViewMap.remove(item);
        updateSelectedChips();
    }

    public void setItems(List<I> items) {
        chipsAdapter.setItems(items);
    }

    public Set<I> getSelectedChips() {
        return chipViewMap.keySet();
    }

    private void updateSelectedChips() {
        chipsAdapter.setSelectedChips(chipViewMap.keySet());
    }

    protected abstract I getItemFromFilter();

}
