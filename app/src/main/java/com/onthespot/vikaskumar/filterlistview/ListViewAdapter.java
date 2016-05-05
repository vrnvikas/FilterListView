package com.onthespot.vikaskumar.filterlistview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Vikas Kumar on 04-05-2016.
 */
public class ListViewAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater inflater;
    private List<BookCollection> BookList = null;
    private ArrayList<BookCollection> arraylist;
    String mQuery = null;

    public ListViewAdapter(Context context, List<BookCollection> bookCollections) {
        mContext = context;
        this.BookList = bookCollections;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<>();
        this.arraylist.addAll(bookCollections);
    }


    public class ViewHolder {
        TextView name;
        TextView author;
        TextView publisher;
        ImageView bookCover;
    }

    @Override
    public int getCount() {
        return BookList.size();
    }

    @Override
    public BookCollection getItem(int position) {
        return BookList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.listview_item, null);
            // Locate the TextViews in listview_item.xml
            holder.name = (TextView) view.findViewById(R.id.name);
            holder.author = (TextView) view.findViewById(R.id.author);
            holder.publisher = (TextView) view.findViewById(R.id.publisher);
            holder.bookCover = (ImageView) view.findViewById(R.id.coverlabel);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(BookList.get(position).getName());
        holder.author.setText(BookList.get(position).getAuthor());
        holder.publisher.setText(BookList.get(position).getPublisher());
        holder.bookCover.setImageResource(BookList.get(position).getImage());

        if (mQuery != null) {
            if (BookList.get(position).getName().toLowerCase(Locale.getDefault()).contains(mQuery)) {
                //SpannableStringBuilder builder = new SpannableStringBuilder();
                //SpannableString redSpannable= new SpannableString(BookList.get(position).getName());
                //redSpannable.setSpan(new ForegroundColorSpan(Color.RED), 1,mQuery.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                //builder.append(redSpannable);
                //holder.name.setText(builder, TextView.BufferType.SPANNABLE);

                String notes = BookList.get(position).getName();
                SpannableStringBuilder sb = new SpannableStringBuilder(notes);
                Pattern p = Pattern.compile(mQuery, Pattern.CASE_INSENSITIVE);
                Matcher m = p.matcher(notes);
                while (m.find()) {
                    //String word = m.group();
                    //String word1 = notes.substring(m.start(), m.end());

                    sb.setSpan(new ForegroundColorSpan(Color.rgb(255, 0, 0)), m.start(), m.end(),
                            Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                }
                holder.name.setText(sb);

            }

            if (BookList.get(position).getAuthor().toLowerCase(Locale.getDefault()).contains(mQuery)) {
                //SpannableStringBuilder builder = new SpannableStringBuilder();
                // SpannableString redSpannable= new SpannableString(BookList.get(position).getAuthor());
                //redSpannable.setSpan(new ForegroundColorSpan(Color.RED), 0, mQuery.length(), 0);
                //builder.append(redSpannable);
                // holder.author.setText(builder, TextView.BufferType.SPANNABLE);


                String notes = BookList.get(position).getAuthor();
                SpannableStringBuilder sb = new SpannableStringBuilder(notes);
                Pattern p = Pattern.compile(mQuery, Pattern.CASE_INSENSITIVE);
                Matcher m = p.matcher(notes);
                while (m.find()) {
                    //String word = m.group();
                    //String word1 = notes.substring(m.start(), m.end());

                    sb.setSpan(new ForegroundColorSpan(Color.rgb(255, 0, 0)), m.start(), m.end(),
                            Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                }
                holder.author.setText(sb);

            }

            if (BookList.get(position).getPublisher().toLowerCase(Locale.getDefault()).contains(mQuery)) {
                // SpannableStringBuilder builder = new SpannableStringBuilder();
                //SpannableString redSpannable= new SpannableString(BookList.get(position).getPublisher());
                // redSpannable.setSpan(new ForegroundColorSpan(Color.RED), 0, mQuery.length(), 0);
                // builder.append(redSpannable);
                // holder.publisher.setText(builder, TextView.BufferType.SPANNABLE);


                String notes = BookList.get(position).getPublisher();
                SpannableStringBuilder sb = new SpannableStringBuilder(notes);
                Pattern p = Pattern.compile(mQuery, Pattern.CASE_INSENSITIVE);
                Matcher m = p.matcher(notes);
                while (m.find()) {
                    //String word = m.group();
                    //String word1 = notes.substring(m.start(), m.end());

                    sb.setSpan(new ForegroundColorSpan(Color.rgb(255, 0, 0)), m.start(), m.end(),
                            Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                }
                holder.publisher.setText(sb);

            }


        }


        // Listen for ListView Item Click
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Send single item click data to SingleItemView Class
                //Intent intent = new Intent(mContext, SingleItemView.class);
                // Pass all data bookName
                //intent.putExtra("bookName",(BookList.get(position).getName()));
                // Pass all data bookAuthor
                //intent.putExtra("bookAuthor",(BookList.get(position).getAuthor()));
                // Pass all data publisher
                //intent.putExtra("publisher",(BookList.get(position).getPublisher()));
                // Pass all data flag
                // Start SingleItemView Class
                //mContext.startActivity(intent);
            }
        });

        return view;
    }

    // Filter Class
    public void filter(String query) {
        mQuery = query.toLowerCase(Locale.getDefault());
        BookList.clear();

        if (mQuery.length() == 0) {
            BookList.addAll(arraylist);
        } else {
            for (BookCollection bc : arraylist) {
                if (bc.getName().toLowerCase(Locale.getDefault()).contains(mQuery)) {
                    BookList.add(bc);
                }

            }

            for (BookCollection bc : arraylist) {

                if (bc.getAuthor().toLowerCase(Locale.getDefault()).contains(mQuery)) {

                    if (!BookList.contains(bc)) {
                        BookList.add(bc);
                    }

                }

            }

            for (BookCollection bc : arraylist) {

                if (bc.getPublisher().toLowerCase(Locale.getDefault()).contains(mQuery)) {

                    if (!BookList.contains(bc)) {
                        BookList.add(bc);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

}
