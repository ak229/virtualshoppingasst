package ub.tuere.virtualshoppingasst;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import java.io.*;
import org.json.JSONException;
import org.json.JSONObject;

public class Barcode_shower extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_shower);
        try {
            String result_json = PlayWithRawFiles();
            createVCard(result_json);
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
        Toast.makeText(getBaseContext(),
                buf.toString(), Toast.LENGTH_LONG).show();

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
            String email_fromjson = "";


            JSONObject namer  = reader.getJSONObject("user");
            name_fromjson = namer.getString("name");

            JSONObject telr  = reader.getJSONObject("user");
            tel_fromjson = telr.getString("tel");

            JSONObject emailr  = reader.getJSONObject("user");
            email_fromjson = emailr.getString("country");

            JSONObject notesr  = reader.getJSONObject("user");
            notes_fromjson = notesr.getString("temp");


            result = result + "BEGIN:VCARD\n";
            result = result + "VERSION:4.0\n";
            result = result + "N:"+name_fromjson+";\n";
            result = result + "TEL;TYPE=voice"+";"+"VALUE=uri:tel:"+tel_fromjson+"\n";
            result = result + "EMAIL:"+email_fromjson+"\n";
            result = result + "NOTES:"+notes_fromjson+"\n";
            result = result + "END:VCARD";


        }

        catch(JSONException je){
            Toast.makeText(getApplicationContext(),
                    "Problems: " + je.getMessage(), Toast.LENGTH_LONG).show();
        }

        return result;
    }
}
