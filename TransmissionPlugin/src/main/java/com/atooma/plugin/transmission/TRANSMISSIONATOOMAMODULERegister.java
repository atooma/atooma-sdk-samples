package com.atooma.plugin.transmission;

import com.atooma.plugin.Module;
import com.atooma.sdk.AtoomaPluginService;

public class TRANSMISSIONATOOMAMODULERegister extends AtoomaPluginService {
    @Override
    public Module getModuleInstance() {
        return new TRANSMISSIONATOOMAMODULE(this, TRANSMISSIONATOOMAMODULE.MODULE_ID, TRANSMISSIONATOOMAMODULE.MODULE_VERSION);
    }
}
