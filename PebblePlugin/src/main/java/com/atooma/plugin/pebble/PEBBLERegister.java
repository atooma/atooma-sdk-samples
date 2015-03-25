
package com.atooma.plugin.pebble;

import com.atooma.plugin.Module;
import com.atooma.sdk.AtoomaPluginService;

public class PEBBLERegister extends AtoomaPluginService {
    @Override
    public Module getModuleInstance() {
        return new PEBBLE(this, PEBBLE.MODULE_ID, PEBBLE.MODULE_VERSION);
    }
}
