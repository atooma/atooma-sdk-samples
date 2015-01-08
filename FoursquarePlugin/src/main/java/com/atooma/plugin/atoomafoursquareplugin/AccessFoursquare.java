package com.atooma.plugin.atoomafoursquareplugin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.atooma.plugin.atoomafoursquareplugin.FoursquareApp.FsqAuthListener;
import com.atooma.sdk.plugin.AtoomaParams;

public class AccessFoursquare extends Activity {

    public static String CLIENT_ID = "HYH5320X0R5KZP2ZNRCJVA4BSQT13MXZRKOBVU0OOMIDYWDF";
    public static String CLIENT_SECRET = "W2QC2BWAOUNFGDOZXXMBEDRGBGNC55A1GLYPMW3B3Q0FNEQR";
    private FoursquareApp mFsqApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFsqApp = new FoursquareApp(this, CLIENT_ID, CLIENT_SECRET);

        if (mFsqApp.hasAccessToken()) {
            Intent result = new Intent();
            result.putExtra(AtoomaParams.ACTIVITY_RESULT_KEY, mFsqApp.getUserName());
            SharedPreferences sp = getSharedPreferences("Prefs", Context.MODE_MULTI_PROCESS);
            sp.edit().putString("AutenticatedText", mFsqApp.getUserName()).commit();
            setResult(RESULT_OK, result);
            finish();
        } else {
            FsqAuthListener listener = new FsqAuthListener() {
                @Override
                public void onSuccess() {
                    Toast.makeText(AccessFoursquare.this, "Connected as " + mFsqApp.getUserName(), Toast.LENGTH_SHORT).show();
                    Intent result = new Intent();
                    result.putExtra(AtoomaParams.ACTIVITY_RESULT_KEY, mFsqApp.getUserName());
                    SharedPreferences sp = getSharedPreferences("Prefs", Context.MODE_MULTI_PROCESS);
                    sp.edit().putString("AutenticatedText", mFsqApp.getUserName()).commit();
                    setResult(RESULT_OK, result);
                    finish();
                }

                @Override
                public void onFail(String error) {
                    Toast.makeText(AccessFoursquare.this, error, Toast.LENGTH_SHORT).show();
                }
            };

            mFsqApp.setListener(listener);
            mFsqApp.authorize();
        }
    }

}
