package com.andrejp.apchipsedittext;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Andrej Poljanec on 9/12/2016.
 */
public class ChipsAdapter<I> extends BaseAdapter {

    private List<I> itemList;
    private String filter;
    private List<I> filteredList;
    private Set<I> selectedChips;
    private boolean showFilter;
    private Context context;

    public ChipsAdapter(Context context) {
        this.context = context;
        itemList = new ArrayList<>();
        filteredList = new ArrayList<>();
        selectedChips = new HashSet<>();
        filter = "";
        showFilter = false;
    }

    @Override
    public int getCount() {
        return filteredList.size() + (showFilter ? 1 : 0);
    }

    @Override
    public I getItem(int i) {
        if (showFilter) {
            if (i == 0) {
                return null;
            } else {
                return filteredList.get(i - 1);
            }
        } else {
            return filteredList.get(i);
        }
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            //noinspection unchecked
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.setData(getItem(i));
        return view;
    }

    public void setItems(List<I> items) {
        this.itemList = items;
        updateItems();
    }

    public void setFilter(String filter) {
        this.filter = filter.toLowerCase();
        updateItems();
    }

    private void updateItems() {
        filteredList.clear();
        boolean matches = false;
        for (I item : itemList) {
            String lowerCaseItem = item.toString().toLowerCase();
            if (TextUtils.isEmpty(filter) || lowerCaseItem.contains(this.filter)) {
                filteredList.add(item);
            }
            if (lowerCaseItem.equals(this.filter)) {
                matches = true;
            }
        }
        showFilter = !matches && !TextUtils.isEmpty(filter);
        filteredList.removeAll(selectedChips);
        if (showFilter) {
            for (I item : selectedChips) {
                if (item.toString().toLowerCase().equals(filter)) {
                    showFilter = false;
                    break;
                }
            }
        }
        notifyDataSetChanged();
    }

    public void setSelectedChips(Set<I> selectedChips) {
        this.selectedChips = selectedChips;
        updateItems();
    }

    private class ViewHolder {
        private TextView textView;

        public ViewHolder(View rootView) {
            textView = (TextView) rootView.findViewById(android.R.id.text1);
        }

        public void setData(I item) {
            if (item == null) {
                textView.setText(filter);
            } else {
                textView.setText(item.toString());
            }
        }
    }
}
