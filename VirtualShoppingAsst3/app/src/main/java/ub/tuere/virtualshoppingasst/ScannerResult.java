package ub.tuere.virtualshoppingasst;

import android.app.DownloadManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.net.*;

public class ScannerResult extends AppCompatActivity {
    String ScanResults;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner_result);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            ScanResults = extras.getString("QRCODEVALUE");
        }


    }
}
