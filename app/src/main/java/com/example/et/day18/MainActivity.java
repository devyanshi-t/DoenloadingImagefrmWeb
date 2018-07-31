package com.example.et.day18;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

ImageView img;
    //  https://www.google.co.in/search?q=starry+night+wiki&rlz=1C1DFOC_enIN706IN706&source=lnms&tbm=isch&sa=X&ved=0ahUKEwj_-ebm_MncAhXNWisKHWH-Cf8Q_AUICigB&biw=1366&bih=662#imgrc=HniKylGQ-XY9VM:
    public class Download extends AsyncTask<String,Void,Bitmap>
    {
        @Override
        protected Bitmap doInBackground(String... urls)
        {
            Bitmap img;
            URL url;
            HttpURLConnection urlConnection=null;
            try{
                url=new URL(urls[0]);
                urlConnection=(HttpURLConnection)url.openConnection();
                urlConnection.connect();
                InputStream is=urlConnection.getInputStream();
                Bitmap myBitMap= BitmapFactory.decodeStream(is);


             return myBitMap;
            }
            catch(Exception e)
            {e.printStackTrace();
            }

            return null;
        }
    }


        public void downloadImage(View view)
    {
     Download obj=new Download();
        Bitmap myBit;
        try{
            myBit=obj.execute("https://upload.wikimedia.org/wikipedia/en/a/aa/Bart_Simpson_200px.png").get();
            img.setImageBitmap(myBit);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       img=(ImageView)findViewById(R.id.img);
    }

}
