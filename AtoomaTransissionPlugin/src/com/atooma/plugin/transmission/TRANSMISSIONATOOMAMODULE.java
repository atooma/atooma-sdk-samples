package com.atooma.plugin.transmission;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;

import com.atooma.plugin.Module;

public class TRANSMISSIONATOOMAMODULE extends Module {

	public static final String MODULE_ID = "TRANSMISSIONATOOMAMODULE";
	public static final int MODULE_VERSION = 1;

	final private static String sessionHeader = "X-Transmission-Session-Id";

	public TRANSMISSIONATOOMAMODULE(Context context, String id, int version) {
		super(context, id, version);
	}

	@Override
	public void registerComponents() {
		registerTrigger(new TR_DownloadRate(getContext(), "DOWNLOAD", 1));
		registerTrigger(new TR_UploadRate(getContext(), "UPLOAD", 1));
		registerPerformer(new PE_DownloadRate(getContext(), "DOWNLOAD", 1));
		registerPerformer(new PE_UploadRate(getContext(), "UPLOAD", 1));

	}

	@Override
	public void defineUI() {
		setIcon(R.drawable.plugin_icon_normal);
		setTitle(R.string.module_name);
	}

	//Utils method

	public static String convertStreamToString(java.io.InputStream is) {
		java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
		return s.hasNext() ? s.next() : "";
	}

	public static String getSessionHeader(String address) {
		DefaultHttpClient httpclient = new DefaultHttpClient();

		String sessionToken = "";

		HttpPost httppost = new HttpPost("http://" + address + "/transmission/rpc");
		HttpResponse response;
		try {
			response = httpclient.execute(httppost);
			sessionToken = response.getFirstHeader(sessionHeader).getValue();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sessionToken;
	}

	@Override
	public void clearCredentials() {
	}

	@Override
	public void defineAuth() {
	}

}