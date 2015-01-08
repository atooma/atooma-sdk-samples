package com.atooma.plugin.activity;

import com.atooma.sdk.RegisterService;
import com.atooma.sdk.plugin.IModulePlugin;

public class ACTIVITYRegister extends RegisterService {

    @Override
    public IModulePlugin getModuleInstance() {
        return new ACTIVITY(this, ACTIVITY.MODULE_ID, ACTIVITY.MODULE_VERSION);
    }

}
