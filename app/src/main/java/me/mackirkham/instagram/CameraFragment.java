package me.mackirkham.instagram;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;
import java.io.File;

import me.mackirkham.instagram.model.Post;

public class CameraFragment extends Fragment {

        // The onCreateView method is called when Fragment should create its View object hierarchy,
        // either dynamically or via XML layout inflation.

        ImageView ivPhoto;
        private Button createButton;
        private EditText descriptionInput;
        File photoFile;


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_camera, parent, false);
            descriptionInput = v.findViewById(R.id.description_et);
            createButton = v.findViewById(R.id.create_btn);

            // Defines the xml file for the fragment
            createButton = v.findViewById(R.id.create_btn);
            ivPhoto = v.findViewById(R.id.image_iv);

            createButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bitmap bitmap = ((BitmapDrawable) ivPhoto.getDrawable()).getBitmap();
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

                    final String description = descriptionInput.getText().toString();
                    final ParseUser user = ParseUser.getCurrentUser();
                    final ParseFile parseFile = new ParseFile(byteArrayOutputStream.toByteArray());

                    parseFile.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                createPost(description, parseFile, user);


                            }
                        }
                    });

                }
            });

            return v;
        }

        // This event is triggered soon after onCreateView().
        // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            // Setup any handles to view objects here
            // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        }


    private void createPost(String description, ParseFile imageFile, ParseUser user) {

        final Post newPost = new Post();
        newPost.setDescription(description);
        newPost.setImage(imageFile);
        newPost.setUser(user);

        newPost.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.d("HomeAvtivity", "Create post success!");

                } else {
                    e.printStackTrace();
                }
            }
        });

    }


}

