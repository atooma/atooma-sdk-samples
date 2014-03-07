package com.atooma.plugin.atoomafrousquareplugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.atooma.plugin.atoomafrousquareplugin.FoursquareDialog.FsqDialogListener;

/**
 * 
 * @author Lorensius W. L. T <lorenz@londatiga.net>
 * 
 */
public class FoursquareApp {
	private FoursquareSession mSession;
	private FoursquareDialog mDialog;
	private FsqAuthListener mListener;
	private ProgressDialog mProgress;
	private String mTokenUrl;
	private String mAccessToken;
	private Handler mHandler;

	/**
	 * Callback url, as set in 'Manage OAuth Costumers' page
	 * (https://developer.foursquare.com/)
	 */
	public static final String CALLBACK_URL = "atooma://foursquare";
	private static final String AUTH_URL = "https://foursquare.com/oauth2/authenticate?response_type=code";
	private static final String TOKEN_URL = "https://foursquare.com/oauth2/access_token?grant_type=authorization_code";
	private static final String API_URL = "https://api.foursquare.com/v2";

	private static final String TAG = "FoursquareApi";

	public FoursquareApp(Context context, String clientId, String clientSecret) {
		mSession = new FoursquareSession(context);

		mAccessToken = mSession.getAccessToken();

		if (!hasAccessToken()) {
			mHandler = new Handler() {
				@Override
				public void handleMessage(Message msg) {
					if (msg.arg1 == 1) {
						if (msg.what == 0) {
							fetchUserName();
						} else {
							mProgress.dismiss();

							mListener.onFail("Failed to get access token");
						}
					} else {
						mProgress.dismiss();

						mListener.onSuccess();
					}
				}
			};

			mTokenUrl = TOKEN_URL + "&client_id=" + clientId + "&client_secret=" + clientSecret
					+ "&redirect_uri=" + CALLBACK_URL;

			String url = AUTH_URL + "&client_id=" + clientId + "&redirect_uri=" + CALLBACK_URL;

			FsqDialogListener listener = new FsqDialogListener() {
				@Override
				public void onComplete(String code) {
					getAccessToken(code);
				}

				@Override
				public void onError(String error) {
					mListener.onFail("Authorization failed");
				}
			};

			mDialog = new FoursquareDialog(context, url, listener);
			mProgress = new ProgressDialog(context);

			mProgress.setCancelable(false);
		}
	}

	private void getAccessToken(final String code) {
		mProgress.setMessage("Getting access token ...");
		mProgress.show();

		new Thread() {
			@Override
			public void run() {
				Log.i(TAG, "Getting access token");

				int what = 0;

				try {
					URL url = new URL(mTokenUrl + "&code=" + code);

					Log.i(TAG, "Opening URL " + url.toString());

					HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

					urlConnection.setRequestMethod("GET");
					urlConnection.setDoInput(true);
					//urlConnection.setDoOutput(true);

					urlConnection.connect();

					JSONObject jsonObj = (JSONObject) new JSONTokener(streamToString(urlConnection.getInputStream())).nextValue();
					mAccessToken = jsonObj.getString("access_token");

					Log.i(TAG, "Got access token: " + mAccessToken);
				} catch (Exception ex) {
					what = 1;

					ex.printStackTrace();
				}

				mHandler.sendMessage(mHandler.obtainMessage(what, 1, 0));
			}
		}.start();
	}

	private void fetchUserName() {
		mProgress.setMessage("Finalizing ...");

		new Thread() {
			@Override
			public void run() {
				Log.i(TAG, "Fetching user name");
				int what = 0;

				try {
					String v = timeMilisToString(System.currentTimeMillis());
					URL url = new URL(API_URL + "/users/self?oauth_token=" + mAccessToken + "&v=" + v);

					Log.d(TAG, "Opening URL " + url.toString());

					HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

					urlConnection.setRequestMethod("GET");
					urlConnection.setDoInput(true);
					//urlConnection.setDoOutput(true);

					urlConnection.connect();

					String response = streamToString(urlConnection.getInputStream());
					JSONObject jsonObj = (JSONObject) new JSONTokener(response).nextValue();

					JSONObject resp = (JSONObject) jsonObj.get("response");
					JSONObject user = (JSONObject) resp.get("user");

					String firstName = user.getString("firstName");
					String lastName = user.getString("lastName");

					Log.i(TAG, "Got user name: " + firstName + " " + lastName);

					mSession.storeAccessToken(mAccessToken, firstName + " " + lastName);
				} catch (Exception ex) {
					what = 1;

					ex.printStackTrace();
				}

				mHandler.sendMessage(mHandler.obtainMessage(what, 2, 0));
			}
		}.start();
	}

	public JSONArray getFriendsCheckin() {
		try {
			String v = timeMilisToString(System.currentTimeMillis());
			URL url = new URL(API_URL + "/checkins/recent?oauth_token=" + mAccessToken + "&v=" + v);

			Log.d(TAG, "Opening URL " + url.toString());

			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

			urlConnection.setRequestMethod("GET");
			urlConnection.setDoInput(true);
			//urlConnection.setDoOutput(true);

			urlConnection.connect();

			String response = streamToString(urlConnection.getInputStream());
			JSONObject jsonObj = (JSONObject) new JSONTokener(response).nextValue();

			JSONObject resp = (JSONObject) jsonObj.get("response");
			JSONArray checkins = (JSONArray) resp.get("recent");

			return checkins;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public boolean hasAccessToken() {
		return (mAccessToken == null) ? false : true;
	}

	public void setListener(FsqAuthListener listener) {
		mListener = listener;
	}

	public String getUserName() {
		return mSession.getUsername();
	}

	public void authorize() {
		mDialog.show();
	}

	private String streamToString(InputStream is) throws IOException {
		String str = "";

		if (is != null) {
			StringBuilder sb = new StringBuilder();
			String line;

			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(is));

				while ((line = reader.readLine()) != null) {
					sb.append(line);
				}

				reader.close();
			} finally {
				is.close();
			}

			str = sb.toString();
		}

		return str;
	}

	private String timeMilisToString(long milis) {
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
		Calendar calendar = Calendar.getInstance();

		calendar.setTimeInMillis(milis);

		return sd.format(calendar.getTime());
	}

	public interface FsqAuthListener {
		public abstract void onSuccess();

		public abstract void onFail(String error);
	}

	public void clearAccessToken() {
		mSession.clearAccessToken();
	}
}
