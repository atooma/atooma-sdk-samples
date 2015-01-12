
package com.atooma.plugin.pebble;

import com.atooma.sdk.RegisterService;
import com.atooma.plugin.Module;

public class PEBBLERegister extends RegisterService {
    @Override
    public Module getModuleInstance() {
        return new PEBBLE(this, PEBBLE.MODULE_ID, PEBBLE.MODULE_VERSION);
    }
}
