package me.mackirkham.instagram;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.parse.FindCallback;
import com.parse.ParseException;

import java.util.ArrayList;
import java.util.List;

import me.mackirkham.instagram.model.Post;

public class TimelineActivity extends AppCompatActivity {

    private Button refreshButton;
    InstaAdapter instaAdapter;
    ArrayList<Post> posts;
    RecyclerView rvPosts;
    SwipeRefreshLayout swipeLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

//        Post newPost = getIntent().getParcelableExtra("post");

       /* refreshButton = findViewById(R.id.refresh_btn);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadTopPosts();
            }
        });*/


        //find RecyclerView
        rvPosts = (RecyclerView) findViewById(R.id.rvPost);
        posts = new ArrayList<>();
        //construct the adapter from this datasource
        instaAdapter = new InstaAdapter(posts);
        //RecyclerView setup
        rvPosts.setLayoutManager(new LinearLayoutManager(this));
        //set adapter
        rvPosts.setAdapter(instaAdapter);



        loadTopPosts();

    }

    private void loadTopPosts() {

      final Post.Query postQuery = new Post.Query();
        postQuery.getTop().withUser();

        postQuery.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> objects, ParseException e) {
                if (e==null) {
                    posts.addAll(objects);
                    instaAdapter.notifyDataSetChanged();

                } else {
                    e.printStackTrace();
                }
           }
        });

    }

}
