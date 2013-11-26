
package com.atooma.plugin.pebble;

import com.atooma.plugin.Module;
import com.atooma.sdk.RegisterService;

public class PEBBLERegister extends RegisterService {
    @Override
    public Module getModuleInstance() {
        return new PEBBLE(this, PEBBLE.MODULE_ID, PEBBLE.MODULE_VERSION);
    }
}
