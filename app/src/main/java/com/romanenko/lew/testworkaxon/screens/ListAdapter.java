package com.romanenko.lew.testworkaxon.screens;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.romanenko.lew.testworkaxon.R;
import com.romanenko.lew.testworkaxon.model.requestPOJO.Result;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListUsersViewHolder> {
    Context context;
    List<Result> listUsers = new ArrayList<>();
    LayoutInflater lInflater;
    private RecyclerViewClickListener mListener;


    public ListAdapter(Context context, RecyclerViewClickListener listener) {
        this.context = context;
        this.mListener = listener;
    }

    public List<Result> getListUsers() {
        return listUsers;
    }

    @Override
    public ListUsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_user_item, parent, false);

        return new ListUsersViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(ListUsersViewHolder holder, int position) {
        holder.bind(listUsers.get(position), mListener);
    }

    @Override
    public int getItemCount() {
        return listUsers.size();
    }

    public void setItems(List<Result> users) {
        listUsers.addAll(users);
        notifyDataSetChanged();

    }

    public void setItem(Result celebrationVOS) {
        listUsers.add(celebrationVOS);
        notifyDataSetChanged();

    }

    public void clearItems() {
        listUsers.clear();
        notifyDataSetChanged();
    }

    public Result getItem(int position) {
        return listUsers.get(position);
    }

    class ListUsersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView userListImageView;
        private TextView name_surname;

        private RecyclerViewClickListener recyclerViewClickListener;


        public void bind(Result result, RecyclerViewClickListener recyclerViewClickListener) {

            this.recyclerViewClickListener = recyclerViewClickListener;

            this.name_surname.setText(result.getName().getFirst() + " " + result.getName().getLast());
            Picasso.get().load(result.getPicture().getMedium()).into(this.userListImageView);

        }

        public ListUsersViewHolder (View itemView, RecyclerViewClickListener recyclerViewClickListener) {
            super(itemView);
            this.recyclerViewClickListener = recyclerViewClickListener;
            userListImageView = itemView.findViewById(R.id.users_list_image_view);
            name_surname = itemView.findViewById(R.id.name_surname);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            recyclerViewClickListener.onClick(view, getAdapterPosition(), getItem(getAdapterPosition()));
        }
    }

    public interface RecyclerViewClickListener {
        void onClick(View view, int position, Result result);
    }

}
