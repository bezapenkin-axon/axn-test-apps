package com.rshtukaraxondevgroup.bookstest.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.rshtukaraxondevgroup.bookstest.model.BookModel;
import com.rshtukaraxondevgroup.bookstest.R;

import java.util.ArrayList;
import java.util.List;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.ViewHolder> {
    private List<BookModel> bookModelList;
    private MainScreen mainScreen;
    private Context mContext;

    public BookListAdapter(final MainScreen mainScreen, Context context) {
        bookModelList = new ArrayList<>();
        this.mContext = context;
        this.mainScreen = mainScreen;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BookListAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_book, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.textViewBookName.setText("Book name: " + bookModelList.get(position).getName());
        holder.textViewNumberOfPages.setText("Number of pages: " + bookModelList.get(position).getNumberOfPages().toString());
        holder.textViewPublisher.setText("Publisher: " + bookModelList.get(position).getPublisher());
        holder.textViewCountry.setText("Country: " + bookModelList.get(position).getCountry());
        holder.itemView.setOnClickListener(view -> mainScreen.onItemSelected(bookModelList.get(position)));
        holder.itemView.setOnLongClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(mContext, holder.itemView);
            popupMenu.inflate(R.menu.menu_del);
            popupMenu.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()) {
                    case R.id.delete:
                        mainScreen.onLongClick(bookModelList.get(position));
                        notifyDataSetChanged();
                        break;
                    default:
                        break;
                }
                return false;
            });
            popupMenu.show();
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return bookModelList.size();
    }

    public void setList(List<BookModel> list) {
        this.bookModelList = list;
        notifyDataSetChanged();
    }

    void addItems(List<BookModel> items) {
        bookModelList.addAll(items);
        notifyDataSetChanged();
    }

    void deleteList() {
        bookModelList = new ArrayList<>();
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewBookName;
        TextView textViewNumberOfPages;
        TextView textViewPublisher;
        TextView textViewCountry;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewBookName = itemView.findViewById(R.id.bookName);
            textViewNumberOfPages = itemView.findViewById(R.id.numberOfPages);
            textViewPublisher = itemView.findViewById(R.id.publisher);
            textViewCountry = itemView.findViewById(R.id.country);
        }
    }
}
