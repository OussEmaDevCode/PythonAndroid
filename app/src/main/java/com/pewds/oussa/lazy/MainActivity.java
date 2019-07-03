package com.pewds.oussa.lazy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {
    public String message;
    EditText e;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageView clickable = findViewById(R.id.run);
        e = findViewById(R.id.edit);
        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Numpad.class));
            }
        });
        clickable.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                send sendcode = new send();
                message = "go";
                sendcode.execute();
            }
        });
        findViewById(R.id.stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send sendcode = new send();
                message = "stop";
                sendcode.execute();
            }
        });
    }
    class send extends AsyncTask<Void,Void,Void> {
        Socket s;
        PrintWriter pw;
        @Override
        protected Void doInBackground(Void...params){
            try {
                s = new Socket(e.getText().toString(),8000);
                pw = new PrintWriter(s.getOutputStream());
                pw.write(message);
                pw.flush();
                pw.close();
                s.close();
            } catch (UnknownHostException e) {
                System.out.println("Fail");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("Fail");
                e.printStackTrace();
            }
            return null;
        }
    }
}