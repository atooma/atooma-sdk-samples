
package com.atooma.plugin.sphero;

import android.content.Context;

import com.atooma.plugin.Module;
import com.atooma.sdk.AtoomaRegistrationReceiver;

public class SPHERORegister extends AtoomaRegistrationReceiver {
    @Override
    public Module getInstance(Context context) {
        return new SPHERO(context, SPHERO.MODULE_ID, SPHERO.MODULE_VERSION);
    }
}
