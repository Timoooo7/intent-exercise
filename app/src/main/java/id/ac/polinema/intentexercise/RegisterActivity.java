package id.ac.polinema.intentexercise;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends AppCompatActivity {

    private CircleImageView image_profile;
    private TextInputEditText text_fullname,text_email,text_password,text_confirm_password,text_homepage,text_about;
    private Button button_ok, button_image;
    private Bitmap bitmap;
    private Uri imageUri;
    private static final int GALLERY_REQUEST_CODE = 1;
    private static final String TAG = RegisterActivity.class.getCanonicalName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        text_fullname = findViewById(R.id.text_fullname);
        text_email = findViewById(R.id.text_email);
        text_password = findViewById(R.id.text_password);
        text_confirm_password = findViewById(R.id.text_confirm_password);
        text_homepage = findViewById(R.id.text_homepage);
        text_about = findViewById(R.id.text_about);
        button_ok = findViewById(R.id.button_ok);
        button_image = findViewById(R.id.btnImage);
        image_profile = findViewById(R.id.image_profile);

        button_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), GALLERY_REQUEST_CODE);
            }
        });

        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(text_password.getText().toString().equals(text_confirm_password.getText().toString())) {
                    Intent move = new Intent(RegisterActivity.this, ProfileActivity.class);
                    move.putExtra("fullname", text_fullname.getText().toString());
                    move.putExtra("email", text_email.getText().toString());
                    move.putExtra("homepage", text_homepage.getText().toString());
                    move.putExtra("about", text_about.getText().toString());
                    startActivity(move);
                }else{
                    Toast.makeText(RegisterActivity.this, "Password not match "+text_password.getText().toString()+" dan "+text_confirm_password.getText().toString(), Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_CANCELED){
            Toast.makeText(this, "Cancel input Image", Toast.LENGTH_SHORT).show();
            return;
        }else if(requestCode == GALLERY_REQUEST_CODE){
            if ( data != null){
                try {
                    imageUri = data.getData();
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    image_profile.setImageBitmap(bitmap);
                }
                catch (IOException e){
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
    }
}
