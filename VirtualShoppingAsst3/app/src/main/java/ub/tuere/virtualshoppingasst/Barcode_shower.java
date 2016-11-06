package ub.tuere.virtualshoppingasst;

import java.net.*;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import java.io.*;
import android.widget.Button;

import com.google.zxing.integration.android.IntentIntegrator;

import org.json.JSONException;
import org.json.JSONObject;

public class Barcode_shower extends AppCompatActivity implements View.OnClickListener {

    Button qrgenBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_shower);

            qrgenBtn = (Button)findViewById(R.id.genqr);
            qrgenBtn.setOnClickListener(this);


    }

    public void onClick(View v){

        try {
            if (v.getId() == R.id.genqr) {
                String result_json = PlayWithRawFiles();
                String vcard_data = createVCard(result_json);

                System.out.println("Got here");
                Intent i = new Intent(this, qrcodeshower.class);
                i.putExtra("QRCODEVALUE", vcard_data);
                startActivity(i);
            }
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(),
                    "Problems: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public String PlayWithRawFiles() throws IOException {
        String str="";
        StringBuffer buf = new StringBuffer();
        InputStream is = this.getResources().openRawResource(+R.raw.json_file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        if (is!=null) {
            while ((str = reader.readLine()) != null) {
                buf.append(str + "\n" );
            }
        }
        is.close();

        String result = buf.toString();

        return result;

    }// PlayWithSDFiles


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
}
