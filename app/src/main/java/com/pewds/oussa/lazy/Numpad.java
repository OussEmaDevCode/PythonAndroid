package com.pewds.oussa.lazy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Numpad extends AppCompatActivity {
    EditText e;
    public String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numpad);
        Button[] buttons = {findViewById(R.id.one),findViewById(R.id.two),findViewById(R.id.three),findViewById(R.id.four),
                findViewById(R.id.five),findViewById(R.id.six),findViewById(R.id.seven),findViewById(R.id.eight),findViewById(R.id.nine),
                findViewById(R.id.zero),findViewById(R.id.point),findViewById(R.id.semi),};
        e = findViewById(R.id.edit);
        for (final Button button:buttons){
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    send sendcode = new send();
                    message = button.getText().toString();
                    sendcode.execute();
                }
            });
        }
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
