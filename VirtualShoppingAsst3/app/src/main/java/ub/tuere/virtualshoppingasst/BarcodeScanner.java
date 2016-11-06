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

import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BarcodeScanner extends Activity implements OnClickListener {

    private Button scanBtn, updateBtn, barcodeBtn;


    public String createVCard (String json_input){
        String result = "";

        try {
            JSONObject reader = new JSONObject(json_input);

            String notes_fromjson = "";
            String name_fromjson = "";
            String tel_fromjson = "";


            JSONObject namer  = reader.getJSONObject("user");
            name_fromjson = namer.getString("name");

            JSONObject telr  = reader.getJSONObject("user");
            tel_fromjson = telr.getString("tel");

            JSONObject notesr  = reader.getJSONObject("user");
            notes_fromjson = notesr.getString("notes");

            result = result + "BEGIN:VCARD\n";
            result = result + "VERSION:4.0\n";
            result = result + "N:"+name_fromjson+";\n";
            result = result + "TEL;TYPE=voice"+";"+"VALUE=uri:tel:"+tel_fromjson+"\n";
            result = result + "NOTES:"+notes_fromjson+"\n";
            result = result + "END:VCARD";


        }

        catch(Exception je){
            Toast.makeText(getApplicationContext(),
                    "Problems: " + je.getMessage(), Toast.LENGTH_LONG).show();
        }

        return result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_scanner);
        scanBtn = (Button)findViewById(R.id.scan_button);
        updateBtn = (Button)findViewById(R.id.update_button);
        barcodeBtn = (Button)findViewById(R.id.barcodeshower);
        scanBtn.setOnClickListener(this);
        updateBtn.setOnClickListener(this);
        barcodeBtn.setOnClickListener(this);

    }

    public void onClick(View v){
        if(v.getId()==R.id.scan_button){
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
        if(v.getId() == R.id.update_button){
            Intent i = new Intent(this, HomeProfile.class);
            startActivity(i);
        }
        if(v.getId() == R.id.barcodeshower){
            System.out.println("Got here");
            FileOutputStream writer = null;
            try {
            writer = openFileOutput("profile_home", Context.MODE_PRIVATE);

                String src = " {\"user\":\n" +
                        "    {\n" +
                        "       \"name\":\"GB\",\n" +
                        "       \"tel\":\"1381149604\",\n" +
                        "       \"notes\":\"12414\"\n" +
                        "    }\n" +
                        "}";

                String as = createVCard(src);
                writer.write(as.getBytes());

                writer.flush();
                writer.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }



            try {
                writer = openFileOutput("profile_work", Context.MODE_PRIVATE);

                String src = " {\"user\":\n" +
                        "    {\n" +
                        "       \"name\":\"GB\",\n" +
                        "       \"tel\":\"1381149604\",\n" +
                        "       \"notes\":\"12424\"\n" +
                        "    }\n" +
                        "}";

                String as = createVCard(src);
                writer.write(as.getBytes());

                writer.flush();
                writer.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }



            try {
                writer = openFileOutput("profile_basic", Context.MODE_PRIVATE);

                String src = " {\"user\":\n" +
                        "    {\n" +
                        "       \"name\":\"GB\",\n" +
                        "       \"tel\":\"1381149604\",\n" +
                        "       \"notes\":\"12434\"\n" +
                        "    }\n" +
                        "}";

                String as = createVCard(src);
                writer.write(as.getBytes());

                writer.flush();
                writer.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }



            try {
                writer = openFileOutput("profile_complete", Context.MODE_PRIVATE);

                String src = " {\"user\":\n" +
                        "    {\n" +
                        "       \"name\":\"GB\",\n" +
                        "       \"tel\":\"1381149604\",\n" +
                        "       \"notes\":\"12444\"\n" +
                        "    }\n" +
                        "}";

                String as = createVCard(src);
                writer.write(as.getBytes());

                writer.flush();
                writer.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }


            //Intent i = new Intent(this, Barcode_shower.class);
            //startActivity(i);
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
