package com.atooma.plugin.sphero;

import android.content.Context;

import com.atooma.plugin.ParameterBundle;
import com.atooma.plugin.Performer;

public class PE_ChangeColor extends Performer {

    public PE_ChangeColor(Context context, String id, int version) {
        super(context, id, version);
    }

    @Override
    public void defineUI() {
        setIcon(R.drawable.color_sphero);
        setTitle(R.string.pe_change_color_title);
    }

    @Override
    public void declareParameters() {
        addParameter(R.string.r_label, R.string.module_name, "R", "NUMBER", true);
        addParameter(R.string.g_label, R.string.module_name, "G", "NUMBER", true);
        addParameter(R.string.b_label, R.string.module_name, "B", "NUMBER", true);
        addParameter(R.string.duration_label, R.string.module_name, "DURATION", "NUMBER", true);
    }

    @Override
    public ParameterBundle onInvoke(String ruleId, ParameterBundle parameters) {
        SpheroBot sphero = SpheroSingleBot.getInstance(this.getContext());
        while (sphero.isRunning()) {
        }
        int r = doubleToInt((Double) parameters.get("R"));
        int g = doubleToInt((Double) parameters.get("G"));
        int b = doubleToInt((Double) parameters.get("B"));
        sphero.setColor(r, g, b, doubleToInt((Double) parameters.get("DURATION")));
        sphero.start();
        return parameters;
    }

    private int doubleToInt(Double dblObj) {
        double dbl = dblObj;
        return (int) dbl;
    }
}
