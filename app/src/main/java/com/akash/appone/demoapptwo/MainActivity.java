package com.akash.appone.demoapptwo;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MainActivity extends AppCompatActivity {
     TextView tvTextOne;
     Button btn;
     EditText ed_text;
    String fileName="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed_text= (EditText) findViewById(R.id.et_text);
      tvTextOne= (TextView) findViewById(R.id.tvtextone);
        btn= (Button) findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadMetaData();
                readFile();
            }
        });
    }
    void loadMetaData()  {
        PackageManager manager= getPackageManager();
        ApplicationInfo appinfo= null;
        try {
            appinfo = manager.getApplicationInfo("com.akash.appone.demopp1", PackageManager.GET_META_DATA);
            fileName=appinfo.dataDir+"/files/akash.txt";
        } catch (PackageManager.NameNotFoundException e) {
          tvTextOne.setText(e.getMessage());

        }
    }
    void readFile(){
        FileInputStream fileInputStream=null;
        try {
            fileInputStream= new FileInputStream(new File(fileName));
            int read=-1;
            StringBuffer buffer=new StringBuffer();
            while ((read=fileInputStream.read())!=-1)
                buffer.append((char)read);
            ed_text.setText(buffer);
            tvTextOne.setText("success");
        } catch (Exception e) {
            tvTextOne.setText(e.getMessage());
        }

    }


}
