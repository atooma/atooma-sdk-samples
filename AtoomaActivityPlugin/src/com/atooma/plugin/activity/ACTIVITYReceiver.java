package com.atooma.plugin.activity;

import com.atooma.sdk.AtoomaRegistrationReceiver;

public class ACTIVITYReceiver extends AtoomaRegistrationReceiver {

	@Override
	public Class getRegisterServiceClass() {
		return ACTIVITYRegister.class;
	}

}
