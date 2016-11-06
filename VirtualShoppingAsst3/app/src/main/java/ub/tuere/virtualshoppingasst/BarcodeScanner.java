package ub.tuere.virtualshoppingasst;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BarcodeScanner extends Activity implements OnClickListener {

    private Button scanBtn, updateBtn, barcodeBtn, createContactBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_scanner);
        scanBtn = (Button)findViewById(R.id.scan_button);
        updateBtn = (Button)findViewById(R.id.update_button);
        barcodeBtn = (Button)findViewById(R.id.barcodeshower);
        createContactBtn = (Button)findViewById(R.id.contact_add);
        scanBtn.setOnClickListener(this);
        updateBtn.setOnClickListener(this);
        barcodeBtn.setOnClickListener(this);
        createContactBtn.setOnClickListener(this);

    }

    public void onClick(View v){
        if(v.getId()==R.id.scan_button){
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
        if(v.getId() == R.id.update_button){
            System.out.println("Got here");
            Intent i = new Intent(this, ShareQR.class);
            startActivity(i);
        }
        if(v.getId() == R.id.barcodeshower){
            System.out.println("Got here");
            Intent i = new Intent(this, Barcode_shower.class);
            startActivity(i);
        }
        if(v.getId() == R.id.contact_add){
            System.out.println("Got here");


            String FILENAME = "profile_home";


            FileInputStream fis = null;

            try {
                fis = openFileInput(FILENAME);

                byte[] asf = null;
                byte[] ffa = null;

                System.out.println(""+fis.read(asf));

            } catch (FileNotFoundException e) {
                Intent i = new Intent(this, RegistrationForm.class);
                i.putExtra("Filename",FILENAME);
                startActivity(i);

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();
            Intent i = new Intent(this, ScannerResult.class);
            i.putExtra("VCardInfo",scanContent);
            startActivity(i);
        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

}
