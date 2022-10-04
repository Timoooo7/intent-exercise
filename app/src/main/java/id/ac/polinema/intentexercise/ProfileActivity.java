package id.ac.polinema.intentexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {
    private CircleImageView imageProfile;
    private TextView about, fullname, email, homepage;
    private Button button_homepage;

    private static final String TAG = ProfileActivity.class.getCanonicalName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Bundle extras = getIntent().getExtras();
        imageProfile = findViewById(R.id.image_profile);
        about = findViewById(R.id.label_about);
        fullname = findViewById(R.id.label_fullname);
        email = findViewById(R.id.label_email);
        homepage = findViewById(R.id.label_homepage);
        button_homepage = findViewById(R.id.button_homepage);


        if (extras!=null) {
            about.setText(getIntent().getExtras().getString("about"));
            fullname.setText(getIntent().getExtras().getString("fullname"));
            email.setText(getIntent().getExtras().getString("email"));
            homepage.setText(getIntent().getExtras().getString("homepage"));

            button_homepage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri uri = Uri.parse("http://" + homepage); // missing 'http://' will cause crashed
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            });
        }else {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
            return;
        }
    }
}
