package com.atooma.plugin.pebble;

import com.atooma.sdk.AtoomaRegistrationReceiver;

public class PEBBLEReceiver extends AtoomaRegistrationReceiver {

	@Override
	public Class getRegisterServiceClass() {
		return PEBBLERegister.class;
	}

}
