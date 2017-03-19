package com.test.themobilebakerytest.main;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.bumptech.glide.Glide;
import com.test.themobilebakerytest.R;
import com.test.themobilebakerytest.user.User;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by mmc on 16/3/17.
 */

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {

    private List<User> users;
    private Context context;
    private UserListItemClick userListItemClick;

    public UserListAdapter(@NonNull Context context, List<User> users, UserListItemClick userListItemClick) {
        this.context = context;
        this.users = users;
        this.userListItemClick = userListItemClick;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        User user = users.get(position);
        holder.tvNameFull.setText(user.getName().toString());
        holder.tvAddress.setText(user.getLocation().getFullLocation());
        holder.ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, holder.ivMenu);
                popupMenu.getMenuInflater().inflate(R.menu.menu_user_list, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch(item.getItemId()) {
                            case R.id.menu_delete:
                                userListItemClick.onDeleteClick(holder.getAdapterPosition());
                                return true;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

        Glide
                .with(context)
                .load(user.getPicture().getThumbnail())
                .placeholder(getImageWithInitial(user))
                .crossFade()
                .bitmapTransform(new CropCircleTransformation(context))
                .into(holder.ivUserImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userListItemClick.onUserClick(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public User getItem(int position) {
        return users.get(position);
    }

    private Drawable getImageWithInitial(User user) {
        ColorGenerator generator = ColorGenerator.MATERIAL;
        int color = generator.getColor(user.getEmail());
        String firstName = user.getName().getFirst();
        String initial = firstName.length() > 0 ? firstName.substring(0, 1) : "?";
        return TextDrawable.builder().buildRound(initial, color);
    }

    public void removeAt(int position) {
        if (position >= 0 && position < users.size()) {
            users.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, users.size());
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivUserImage, ivMenu;
        public TextView tvNameFull, tvAddress;

        public ViewHolder(View itemView) {
            super(itemView);
            this.ivUserImage = (ImageView) itemView.findViewById(R.id.civImage);
            this.tvNameFull = (TextView) itemView.findViewById(R.id.tvTitle);
            this.tvAddress = (TextView) itemView.findViewById(R.id.tvDescription);
            this.ivMenu = (ImageView) itemView.findViewById(R.id.ivMenu);
        }

    }

    public interface UserListItemClick {
        void onUserClick(int position);
        void onDeleteClick(int position);
    }

}
