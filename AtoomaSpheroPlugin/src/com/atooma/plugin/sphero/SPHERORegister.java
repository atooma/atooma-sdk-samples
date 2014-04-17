package com.atooma.plugin.sphero;

import com.atooma.plugin.Module;
import com.atooma.sdk.RegisterService;

public class SPHERORegister extends RegisterService {

	@Override
	public Module getModuleInstance() {
		return new SPHERO(this, SPHERO.MODULE_ID, SPHERO.MODULE_VERSION);
	}

}
