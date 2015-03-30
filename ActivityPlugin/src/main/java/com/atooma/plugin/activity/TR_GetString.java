package com.atooma.plugin.activity;

import android.content.Context;
import android.os.RemoteException;

import com.atooma.plugin.AlarmBasedTrigger;
import com.atooma.plugin.ParameterBundle;
import com.atooma.plugin.Schedule;
import com.atooma.sdk.IAtoomaService;

public class TR_GetString extends AlarmBasedTrigger {

    public TR_GetString(Context context, String id, int version) {
        super(context, id, version);
    }

    @Override
    public void defineUI() {
        setIcon(R.drawable.plugin_icon_normal);
        setTitle(R.string.tr_name);
    }

    @Override
    public void declareParameters() {
        addParameter(R.string.parameter_name, R.string.parameter_name_ifnull, "STRINGS", "PLUGIN", true, "com.atooma.plugin.activity.MainActivity");
    }

    @Override
    public void declareVariables() {
        addVariable(R.string.parameter_name, "STRINGS", "PLUGIN");
    }

    @Override
    public void onTimeout(String ruleId, ParameterBundle parameters) {
        //Here I can pass new values
        //but in this example I pass the values inserted by the user
        trigger(ruleId, parameters);
    }

    @Override
    public void onRevoke(String ruleId) {
    }

    @Override
    public Schedule getScheduleInfo() throws RemoteException {
        return new Schedule(true, true, false, System.currentTimeMillis(), (long) (10 * 1000));
    }
}
