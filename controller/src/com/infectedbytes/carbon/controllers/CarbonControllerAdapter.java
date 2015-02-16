package com.infectedbytes.carbon.controllers;

import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;

/**
 * A base implementation for {@link CarbonControllerListener}. Subclass this if you are only interested in a few specific events.
 * 
 * @author Henrik
 *
 */
public class CarbonControllerAdapter implements CarbonControllerListener {

	@Override
	public void connected(CarbonController controller) {}

	@Override
	public void disconnected(CarbonController controller) {}

	@Override
	public boolean buttonDown(CarbonController controller, CarbonKey key) {
		return false;
	}

	@Override
	public boolean buttonUp(CarbonController controller, CarbonKey key) {
		return false;
	}

	@Override
	public boolean axisMoved(CarbonController controller, CarbonAxis axis, float value) {
		return false;
	}

	@Override
	public boolean povMoved(CarbonController controller, PovDirection value) {
		return false;
	}

	@Override
	public boolean vectorChanged(CarbonController controller, CarbonVector vector, Vector3 newVector) {
		return false;
	}

}
