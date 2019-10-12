package com.example.gpsmemories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gpsmemories.Database.Memory;
import com.example.gpsmemories.Database.MemoryDatabase;
import com.example.gpsmemories.FullScreen;
import com.example.gpsmemories.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class Display extends AppCompatActivity {





    public static Object data;
    private TextView TitleTextViewDisplay,DescriptionTextViewDisplay,TimeTextViewDisplay;
    private ImageView imageViewDisplay;

    //private boolean isImageFitToScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);



        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Display.this,Show.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

            }
        });



        String Title = getIntent().getExtras().getString("Title");
        String Description = getIntent().getExtras().getString("Description");
        String Time = getIntent().getExtras().getString("Time");
        final Bitmap bmp = (Bitmap) data;

        TitleTextViewDisplay = findViewById(R.id.TextViewTitleDisplay);
        DescriptionTextViewDisplay = findViewById(R.id.TextViewDescriptionDisplay);
        TimeTextViewDisplay = findViewById(R.id.TextViewTimeDisplay);
        imageViewDisplay=findViewById(R.id.imageviewdisplay);


        TitleTextViewDisplay.setText(Title);
        DescriptionTextViewDisplay.setText(Description);
        TimeTextViewDisplay.setText(Time);

        if(bmp!=null){



            imageViewDisplay.setImageBitmap(bmp);



            imageViewDisplay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    FullScreen.data=bmp;
                    Intent intent = new Intent(Display.this, FullScreen.class);
/*
                        Bundle extras = new Bundle();
                        extras.putParcelable("imagebitmap", imgbytes);
                        intent.putExtras(extras);


 */

                    startActivity(intent);








                }


                /*

                if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH ){
                    imageViewDisplay.setSystemUiVisibility( View.SYSTEM_UI_FLAG_HIDE_NAVIGATION );

                }
                else if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB )
                    imageViewDisplay.setSystemUiVisibility( View.SCREEN_STATE_OFF );
                else{}
                }

                 */

            });





        }else{

            imageViewDisplay.setVisibility(View.GONE);

            imageViewDisplay.setImageBitmap(null);

        }



    }













}