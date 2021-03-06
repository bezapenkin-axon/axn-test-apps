package com.rshtukaraxondevgroup.phototest.view;

import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.rshtukaraxondevgroup.phototest.Constants;
import com.rshtukaraxondevgroup.phototest.repository.DropBoxRepository;
import com.rshtukaraxondevgroup.phototest.view.CustomView.DrawView;
import com.rshtukaraxondevgroup.phototest.R;

import java.io.IOException;
import java.util.Objects;

public class EditActivity extends AppCompatActivity {
    private static final String TAG = DropBoxRepository.class.getCanonicalName();

    private Uri mImageUri;
    private ImageView mImageView;
    private Button mButtonSave;
    private DrawView mDrawView;
    private RelativeLayout mRelativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        mImageView = findViewById(R.id.image_view);
        mButtonSave = findViewById(R.id.button_save);

        Bundle extras = getIntent().getExtras();
        mImageUri = Uri.parse(Objects.requireNonNull(extras).getString(Constants.EXTRA_URI));
        Log.d(TAG, mImageUri.toString());

        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), mImageUri);
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }

        if (bitmap != null) {
            mDrawView = new DrawView(this, bitmap);
            mRelativeLayout = findViewById(R.id.view);
            mRelativeLayout.addView(mDrawView);
        }

        mButtonSave.setOnClickListener(v -> {
            save(mDrawView.save());
        });
    }

    private void save(Bitmap bitmap) {
        mImageView.setImageBitmap(bitmap);
        mRelativeLayout.setVisibility(View.GONE);
        mButtonSave.setVisibility(View.GONE);
    }
}
