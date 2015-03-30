package com.atooma.plugin.transmission;

import android.content.Context;
import android.os.RemoteException;

import com.atooma.plugin.ParameterBundle;
import com.atooma.plugin.Performer;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class PE_UploadRate extends Performer {

    final private String sessionHeader = "X-Transmission-Session-Id";

    public PE_UploadRate(Context context, String id, int version) {
        super(context, id, version);
    }

    @Override
    public void defineUI() {
        setIcon(R.drawable.upload_rate);
        setTitle(R.string.trigger_upload);
    }

    @Override
    public void declareParameters() {
        addParameter(R.string.download_par_address_name, R.string.download_par_address_name, "ADDRESS", "STRING", true);
        addParameter(R.string.performer_upload_par_name, R.string.download_par_name_null, "DOWNLOAD-RATE", "NUMBER", true);
    }

    @Override
    public ParameterBundle onInvoke(String ruleId, ParameterBundle parameters) throws RemoteException {
        Double uploadRate = (Double) parameters.get("UPLOAD-RATE");
        //in Kbps
        uploadRate = uploadRate * 8;
        String address = (String) parameters.get("ADDRESS");

        String sessionId = TRANSMISSIONATOOMAMODULE.getSessionHeader(address);

        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://" + address + "/transmission/rpc");

        httppost.addHeader(sessionHeader, sessionId);

        JSONObject data;
        try {
            data = new JSONObject();

            JSONObject arguments = new JSONObject();
            arguments.put("uploadLimit", uploadRate);
            arguments.put("uploadLimited", "true");
            arguments.put("ids", "");
            data.put("arguments", arguments);

            data.put("method", "torrent-set");

            StringEntity se = new StringEntity(data.toString(), "UTF-8");
            httppost.setEntity(se);

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ParameterBundle();
    }
}
