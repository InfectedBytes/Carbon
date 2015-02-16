package com.infectedbytes.carbon.controllers.wrapper;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.infectedbytes.carbon.controllers.CarbonAxis;
import com.infectedbytes.carbon.controllers.CarbonController;
import com.infectedbytes.carbon.controllers.CarbonControllerListener;
import com.infectedbytes.carbon.controllers.CarbonKey;
import com.infectedbytes.carbon.controllers.CarbonVector;

/**
 * Baseclass for all {@link CarbonController}s wrapping around a normal LibGDX {@link Controller}. A {@link WrapperFactory} is used to
 * instantiate a specific {@link ControllerWrapper}.
 * 
 * @author Henrik
 *
 */
public abstract class ControllerWrapper implements CarbonController, ControllerListener {
	private boolean[] keys = new boolean[CarbonKey.values().length];
	private float[] axis = new float[CarbonAxis.values().length];
	private Vector3[] vectors = new Vector3[CarbonVector.values().length];
	private PovDirection pov;
	private Controller baseController;

	private Array<CarbonControllerListener> listener = new Array<CarbonControllerListener>();

	/**
	 * Wraps this {@link ControllerWrapper} around the {@link Controller}.
	 * 
	 * @param controller
	 */
	public ControllerWrapper(Controller controller) {
		controller.addListener(this);
		this.baseController = controller;
	}

	/**
	 * @return the LibGDX {@link Controller] instance of this wrapper.
	 */
	public Controller getController() {
		return baseController;
	}

	/**
	 * Propagate the {@link CarbonControllerListener#buttonDown(CarbonController, CarbonKey)} or
	 * {@link CarbonControllerListener#buttonUp(CarbonController, CarbonKey)} event.
	 * 
	 * @param key
	 * @param pressed
	 */
	protected final void publish(CarbonKey key, boolean pressed) {
		this.keys[key.ordinal()] = pressed;
		if (pressed) {
			for (CarbonControllerListener c : listener)
				if (c.buttonDown(this, key)) break;
		} else {
			for (CarbonControllerListener c : listener)
				if (c.buttonUp(this, key)) break;
		}
	}

	/**
	 * Propagate the {@link CarbonControllerListener#axisMoved(CarbonController, CarbonAxis, float)} event.
	 * 
	 * @param axis
	 * @param value
	 */
	protected final void publish(CarbonAxis axis, float value) {
		this.axis[axis.ordinal()] = value;
		for (CarbonControllerListener c : listener)
			if (c.axisMoved(this, axis, value)) break;
	}

	/**
	 * Propagate the {@link CarbonControllerListener#povMoved(CarbonController, PovDirection)} event.
	 * 
	 * @param pov
	 */
	protected final void publish(PovDirection pov) {
		this.pov = pov;
		for (CarbonControllerListener c : listener)
			if (c.povMoved(this, pov)) break;
	}
	/**
	 * Propagate the {@link CarbonControllerListener#vectorChanged(CarbonController, CarbonVector, Vector3)} event.
	 * 
	 * @param vector
	 * @param newValue
	 */
	protected final void publish(CarbonVector vector, Vector3 newValue) {
		this.vectors[vector.ordinal()] = newValue;
		for (CarbonControllerListener c : listener)
			if (c.vectorChanged(this, vector, newValue)) break;
	}

	@Override
	public final boolean getButton(CarbonKey key) {
		return this.keys[key.ordinal()];
	}

	@Override
	public float getAxis(CarbonAxis axis) {
		return this.axis[axis.ordinal()];
	}

	@Override
	public final PovDirection getPov() {
		return this.pov;
	}

	@Override
	public final Vector3 getVector(CarbonVector vector) {
		return this.vectors[vector.ordinal()];
	}

	@Override
	public final void addListener(CarbonControllerListener listener) {
		this.listener.add(listener);
	}

	@Override
	public final void removeListener(CarbonControllerListener listener) {
		this.listener.removeValue(listener, true);
	}

	@Override
	public abstract String getName();

	@Override
	public boolean supports(CarbonKey key) {
		return false;
	}

	@Override
	public boolean supports(CarbonAxis axis) {
		return false;
	}

	@Override
	public boolean supports(CarbonVector vector) {
		return false;
	}

	@Override
	public boolean supportsPov() {
		return false;
	}

	@Override
	public final void connected(Controller controller) {
		for (CarbonControllerListener l : listener)
			l.connected(this);
	}

	@Override
	public final void disconnected(Controller controller) {
		for (CarbonControllerListener l : listener)
			l.disconnected(this);
	}

	@Override
	public boolean buttonDown(Controller controller, int buttonCode) {
		return false;
	}

	@Override
	public boolean buttonUp(Controller controller, int buttonCode) {
		return false;
	}

	@Override
	public boolean axisMoved(Controller controller, int axisCode, float value) {
		return false;
	}

	@Override
	public boolean povMoved(Controller controller, int povCode, PovDirection value) {
		return false;
	}

	@Override
	public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {
		return false;
	}

	@Override
	public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {
		return false;
	}

	@Override
	public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) {
		return false;
	}

}
