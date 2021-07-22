package com.example.chatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigInteger;

public class EncActivity extends AppCompatActivity {

    private Button decrypt , showEncKey ;

    private TextView decryptedValue ,encKey;

    BigInteger key ;
    String encrypt ;
    String phoneno ;
    RSA rsa ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enc);

        rsa = new RSA(1024);

        /** Here , To Catch Ciphertext Key */
        Intent Intent = getIntent();
        encrypt = (String) Intent.getSerializableExtra("Encrypt");
        key = new BigInteger(encrypt);
        Toast.makeText(getBaseContext(), "Key: " + key, Toast.LENGTH_SHORT).show();

        encKey = (TextView) findViewById(R.id.encryptedKey);

        showEncKey = (Button) findViewById(R.id.showEncKey);
        showEncKey.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                encKey.setText(key.toString());
            }
        });


        decryptedValue = (TextView) findViewById(R.id.decryptedText);

        decrypt = (Button) findViewById(R.id.decrypt);

        decrypt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                /** Here , To Decrypt Recived Message*/
                BigInteger plaintext = rsa.decrypt(key);
                Toast.makeText(getBaseContext(), "plaintext after Dec. : " + plaintext, Toast.LENGTH_SHORT).show();
                String text2 = new String(plaintext.toByteArray());
                System.out.println("Plaintext: " + text2);
                decryptedValue.setText(text2);
            }
        });
    }

    @Override

    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.main, menu);

        return true;

    }

}