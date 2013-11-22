
package com.atooma.plugin.pebble;

import android.content.Context;

import com.atooma.plugin.Module;
import com.atooma.sdk.AtoomaRegistrationReceiver;

public class PEBBLERegister extends AtoomaRegistrationReceiver {
    @Override
    public Module getInstance(Context context) {
        return new PEBBLE(context, PEBBLE.MODULE_ID, PEBBLE.MODULE_VERSION);
    }
}
