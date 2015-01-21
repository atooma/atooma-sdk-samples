package com.atooma.plugin.activity;

import com.atooma.plugin.IModulePlugin;
import com.atooma.sdk.RegisterService;

public class ACTIVITYRegister extends RegisterService {

    @Override
    public IModulePlugin getModuleInstance() {
        return new ACTIVITY(this, ACTIVITY.MODULE_ID, ACTIVITY.MODULE_VERSION);
    }

}
