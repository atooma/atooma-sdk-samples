
package com.atooma.plugin.transmissionatoomamodule;

import com.atooma.plugin.Module;
import com.atooma.sdk.RegisterService;

public class TRANSMISSIONATOOMAMODULERegister extends RegisterService {
    @Override
    public Module getModuleInstance() {
        return new TRANSMISSIONATOOMAMODULE(this, TRANSMISSIONATOOMAMODULE.MODULE_ID, TRANSMISSIONATOOMAMODULE.MODULE_VERSION);
    }
}
