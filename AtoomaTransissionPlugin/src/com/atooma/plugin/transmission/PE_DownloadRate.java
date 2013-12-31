package com.atooma.plugin.transmission;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;

import com.atooma.plugin.ParameterBundle;
import com.atooma.plugin.Performer;

public class PE_DownloadRate extends Performer {

	final private String sessionHeader = "X-Transmission-Session-Id";

	public PE_DownloadRate(Context context, String id, int version) {
		super(context, id, version);
	}

	@Override
	public void defineUI() {
		setIcon(R.drawable.plugin_icon_el_normal, R.drawable.plugin_icon_el_pressed);
		setTitle(R.string.trigger_download);
	}

	@Override
	public void declareParameters() {
		addParameter(R.string.download_par_address_name, R.string.download_par_address_name, "ADDRESS", "STRING", true);
		addParameter(R.string.performer_download_par_name, R.string.download_par_name_null, "DOWNLOAD-RATE", "NUMBER", true);
	}

	@Override
	public ParameterBundle onInvoke(String ruleId, ParameterBundle parameters) throws RemoteException {
		Double donwloadRate = (Double) parameters.get("DOWNLOAD-RATE");
		//in Kbps
		donwloadRate = donwloadRate * 8;
		String address = (String) parameters.get("ADDRESS");

		String sessionId = TRANSMISSIONATOOMAMODULE.getSessionHeader(address);

		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost("http://" + address + "/transmission/rpc");

		httppost.addHeader(sessionHeader, sessionId);

		JSONObject data;
		try {
			data = new JSONObject();

			JSONObject arguments = new JSONObject();
			arguments.put("downloadLimit", donwloadRate);
			arguments.put("downloadLimited", "true");
			arguments.put("ids", new JSONArray());
			data.put("arguments", arguments);

			data.put("method", "torrent-set");

			StringEntity se = new StringEntity(data.toString(), "UTF-8");
			httppost.setEntity(se);

			Log.v("ATOOMA", "response =" + TRANSMISSIONATOOMAMODULE.convertStreamToString(httpclient.execute(httppost).getEntity().getContent()));

		} catch (JSONException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new ParameterBundle();
	}

}
