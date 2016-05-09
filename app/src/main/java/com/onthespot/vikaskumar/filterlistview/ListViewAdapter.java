package com.onthespot.vikaskumar.filterlistview;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Vikas Kumar on 04-05-2016.
 */
public class ListViewAdapter extends BaseAdapter implements Filterable {

    Context mContext;
    LayoutInflater inflater;
    private ArrayList<BookCollection> bookList = null;
    private ArrayList<BookCollection> allBooklist;
    String mQuery = null;
    BookFilter bookFilter;

    public ListViewAdapter(Context context, List<BookCollection> bookCollections) {
        mContext = context;
        this.bookList = (ArrayList<BookCollection>) bookCollections;
        this.allBooklist = (ArrayList<BookCollection>) bookCollections;
    }

    @Override
    public Filter getFilter() {
        if(bookFilter == null){
            bookFilter = new BookFilter();
        }
        return bookFilter;
    }

    @Override
    public int getCount() {

        if(bookList == null){
            return 0;
        }else {
            return bookList.size();
        }
    }

    @Override
    public BookCollection getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {

        TextView name;
        TextView author;
        TextView publisher;
        ImageView bookCover;

        if (view == null) {
            inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(R.layout.listview_item, null);
        }

        name = (TextView) view.findViewById(R.id.name);
        author = (TextView) view.findViewById(R.id.author);
        publisher = (TextView) view.findViewById(R.id.publisher);
        bookCover = (ImageView) view.findViewById(R.id.coverlabel);

        name.setText(bookList.get(position).getName());
        author.setText(bookList.get(position).getAuthor());
        publisher.setText(bookList.get(position).getPublisher());
        bookCover.setImageResource(bookList.get(position).getImage());

        // high lighting search query
        if (mQuery != null) {

            String bookName = bookList.get(position).getName().toLowerCase();
            String bookAuthor = bookList.get(position).getAuthor().toLowerCase();
            String bookPublisher = bookList.get(position).getPublisher().toLowerCase();

            if (bookName.contains(mQuery)) {

                SpannableStringBuilder colouredText = highLightText(bookName);
                name.setText(colouredText);

            }

            if (bookAuthor.contains(mQuery)) {


                SpannableStringBuilder colouredText = highLightText(bookAuthor);
                author.setText(colouredText);
            }

            if (bookPublisher.contains(mQuery)) {

                SpannableStringBuilder colouredText = highLightText(bookPublisher);
                publisher.setText(colouredText);
            }

        }

        return view;
    }

    private SpannableStringBuilder highLightText(String text) {

        SpannableStringBuilder sb = new SpannableStringBuilder(text);
        Pattern p = Pattern.compile(mQuery, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(text);
        while (m.find()) {

            sb.setSpan(new ForegroundColorSpan(Color.rgb(255, 0, 0)), m.start(), m.end(),
                    Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        }

        return sb;
    }

    private class BookFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults results = new FilterResults();
            mQuery = constraint.toString().toLowerCase();

            if(constraint == null || constraint.length() == 0){
                results.values = allBooklist;
                results.count = allBooklist.size();
            }else{

                ArrayList<BookCollection> filteredList = new ArrayList<>();

                // first sorting by name is added
                for (BookCollection bc : allBooklist) {
                    if (bc.getName().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        filteredList.add(bc);
                    }

                }

                // sorting by author
                for (BookCollection bc : allBooklist) {

                    if (bc.getAuthor().toLowerCase().contains(constraint.toString().toLowerCase())) {

                        if (!filteredList.contains(bc)) {
                            filteredList.add(bc);
                        }

                    }

                }
                // sorting by publisher
                for (BookCollection bc : allBooklist) {

                    if (bc.getPublisher().toLowerCase().contains(constraint.toString().toLowerCase())) {

                        if (!filteredList.contains(bc)) {
                            filteredList.add(bc);
                        }
                    }
                }


                results.values = filteredList;
                results.count = filteredList.size();

            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            bookList = (ArrayList<BookCollection>) results.values;
            notifyDataSetChanged();
        }
    }
}
