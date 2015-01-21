package com.atooma.plugin.transmission;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;

import com.atooma.plugin.AlarmBasedTrigger;
import com.atooma.plugin.ParameterBundle;
import com.atooma.plugin.Schedule;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class TR_DownloadRate extends AlarmBasedTrigger {

    final private String sessionHeader = "X-Transmission-Session-Id";

    public TR_DownloadRate(Context context, String id, int version) {
        super(context, id, version);
    }

    @Override
    public Schedule getScheduleInfo() throws RemoteException {
        long now = System.currentTimeMillis();
        long triggerAtTime = now + 60000L;
        Schedule schedule = new Schedule.Builder().exact(true).triggerAtTime(triggerAtTime).build();
        return schedule;
    }

    @Override
    public void defineUI() {
        setIcon(R.drawable.download_rate);
        setTitle(R.string.trigger_download);
    }

    @Override
    public void declareParameters() {
        addParameter(R.string.download_par_address_name, R.string.download_par_address_name, "ADDRESS", "STRING", true, null);
        addParameter(R.string.download_par_name, R.string.download_par_name_null, "DOWNLOAD-RATE", "NUMBER", false, null);
    }

    @Override
    public void declareVariables() {
        addVariable(R.string.download_var_name, "DOWNLOAD-RATE", "NUMBER");
    }

    @Override
    public void onTimeout(String ruleId, ParameterBundle parameters) {
        Log.v("ATOOMA", "onTimeout");
        Double donwloadFilter = (Double) parameters.get("DOWNLOAD-RATE");
        String address = (String) parameters.get("ADDRESS");

        String sessionId = TRANSMISSIONATOOMAMODULE.getSessionHeader(address);

        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://" + address + "/transmission/rpc");

        httppost.addHeader(sessionHeader, sessionId);

        JSONObject data;
        long rateDownload = 0;
        try {
            data = new JSONObject();

            JSONObject arguments = new JSONObject();
            arguments.put("fields", new JSONArray().put("rateDownload").put("id"));
            data.put("arguments", arguments);

            data.put("method", "torrent-get");

            StringEntity se = new StringEntity(data.toString(), "UTF-8");
            httppost.setEntity(se);

            HttpResponse response = httpclient.execute(httppost);

            JSONObject responseJson = new JSONObject(TRANSMISSIONATOOMAMODULE.convertStreamToString(response.getEntity().getContent()));

            JSONArray torrents = responseJson.getJSONObject("arguments").getJSONArray("torrents");
            for (int i = 0; i < torrents.length(); i++) {
                rateDownload = rateDownload + torrents.getJSONObject(i).getLong("rateDownload");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //in KiloBytes
        rateDownload = rateDownload / 1024;
        if (donwloadFilter == null || rateDownload > donwloadFilter) {
            ParameterBundle variables = new ParameterBundle();
            variables.put("DOWNLOAD-RATE", (double) rateDownload);
            trigger(ruleId, variables);
        }
    }

    @Override
    public void onRevoke(String arg0) {
        // TODO Auto-generated method stub

    }

}
