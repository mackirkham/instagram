package me.mackirkham.instagram;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import me.mackirkham.instagram.model.Post;

public class InstaAdapter extends RecyclerView.Adapter<InstaAdapter.ViewHolder> {


    private List<Post> mPosts;
    Context context;
    //pass in the tweets array in constructor
    public InstaAdapter(List<Post> posts) {
        mPosts = posts;
    }


    // Clean all elements of the recycler
    public void clear() {
        mPosts.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Post> list) {
        mPosts.addAll(list);
        notifyDataSetChanged();
    }


    //for each row, inflate the layout catch reference into viewholder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View postView = inflater.inflate(R.layout.item_post, parent, false);
        ViewHolder viewHolder = new ViewHolder(postView);
        return viewHolder;
    }

    //bind values based on position of element
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //get the data according to position
        Post post = mPosts.get(position);
        //populate the views according to data
        holder.tvUsername.setText(post.getUser().getUsername());
        holder.tvDescription.setText(post.getDescription());
        holder.tvTimeStamp.setText(post.getRelativeTimeAgo());

        Glide.with(context).load(post.getImage().getUrl()).into(holder.ivPicture);
    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    //create ViewHolder class
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivPicture;
        public TextView tvUsername;
        public TextView tvDescription;
        public TextView tvTimeStamp;

        public ViewHolder(View itemView) {
            super(itemView);

            //perform findViewById lookups

            ivPicture = itemView.findViewById(R.id.ivPicture);
            tvUsername = itemView.findViewById(R.id.tvUserName);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvTimeStamp = itemView.findViewById(R.id.tvTimeStamp);

        }
    }



}
