package me.mackirkham.instagram;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

import me.mackirkham.instagram.model.Post;

public class ParseApp extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        //sets up parse server so that when we go to main activity it wont crash and it will
        //let user login
        ParseObject.registerSubclass(Post.class);
        final Parse.Configuration configuration = new Parse.Configuration.Builder(this)
                .applicationId("instagram")
                .clientKey("swim")
                .server("http://mackirkham-instagram.herokuapp.com/parse")
                .build();
        Parse.initialize(configuration);
    }
}
