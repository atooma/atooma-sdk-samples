package com.atooma.plugin.sphero;

import java.util.List;

import orbotix.robot.base.CollisionDetectedAsyncData;
import orbotix.robot.base.Robot;
import orbotix.robot.base.RobotProvider;
import orbotix.robot.sensor.DeviceSensorsData;
import orbotix.sphero.CollisionListener;
import orbotix.sphero.ConnectionListener;
import orbotix.sphero.DiscoveryListener;
import orbotix.sphero.SensorControl;
import orbotix.sphero.SensorFlag;
import orbotix.sphero.SensorListener;
import orbotix.sphero.Sphero;
import android.content.Context;
import android.util.Log;

import com.atooma.plugin.ParameterBundle;
import com.atooma.plugin.Performer;

public class PE_ChangeColor extends Performer {

	private Sphero mRobot;
	private String TAG = "SPHERO";

	public PE_ChangeColor(Context context, String id, int version) {
		super(context, id, version);
	}

	@Override
	public void defineUI() {
		setIcon(R.drawable.plugin_icon_el_normal, R.drawable.plugin_icon_el_pressed);
		setTitle(R.string.pe_change_color_title);
	}

	@Override
	public void declareParameters() {
		//addParameter(R.string.r_label, R.string.module_name, "R", "STRING", true);
		//addParameter(R.string.g_label, R.string.module_name, "G", "STRING", true);
		//addParameter(R.string.b_label, R.string.module_name, "B", "STRING", true);
	}

	private void setLights(int r, int g, int b) {
		final SensorControl control = mRobot.getSensorControl();
		control.addSensorListener(new SensorListener() {
			@Override
			public void sensorUpdated(DeviceSensorsData sensorDataArray) {
				Log.d(TAG, sensorDataArray.toString());
			}
		}, SensorFlag.ACCELEROMETER_NORMALIZED, SensorFlag.GYRO_NORMALIZED);

		control.setRate(1);
		mRobot.enableStabilization(false);
		mRobot.drive(90, 0);
		mRobot.setBackLEDBrightness(.5f);

		mRobot.getCollisionControl().startDetection(255, 255, 255, 255, 255);
		mRobot.getCollisionControl().addCollisionListener(new CollisionListener() {
			public void collisionDetected(CollisionDetectedAsyncData collisionData) {
				Log.d(TAG, collisionData.toString());
			}
		});
		mRobot.setColor(r, g, b);
	}

	@Override
	public ParameterBundle onInvoke(String ruleId, ParameterBundle parameters) {
		RobotProvider.getDefaultProvider().addConnectionListener(new ConnectionListener() {
			@Override
			public void onConnected(Robot robot) {
				mRobot = (Sphero) robot;
				setLights(0, 255, 0);
			}

			@Override
			public void onConnectionFailed(Robot sphero) {
				Log.d(TAG, "Connection Failed: " + sphero);
			}

			@Override
			public void onDisconnected(Robot robot) {
				Log.d(TAG, "Disconnected: " + robot);
				mRobot = null;
			}

		});

		RobotProvider.getDefaultProvider().addDiscoveryListener(new DiscoveryListener() {
			@Override
			public void onBluetoothDisabled() {
				Log.d(TAG, "Bluetooth Disabled");
			}

			@Override
			public void discoveryComplete(List<Sphero> spheros) {
				Log.d(TAG, "Found " + spheros.size() + " robots");
			}

			@Override
			public void onFound(List<Sphero> sphero) {
				Log.d(TAG, "Found: " + sphero);
				RobotProvider.getDefaultProvider().connect(sphero.iterator().next());
			}
		});

		boolean success = RobotProvider.getDefaultProvider().startDiscovery(getContext());
		if (!success) {
			Log.d(TAG, "Discovery failed..");
		}
		return parameters;
	}
}
