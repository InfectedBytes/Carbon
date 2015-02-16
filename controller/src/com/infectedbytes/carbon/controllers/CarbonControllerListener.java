package com.infectedbytes.carbon.controllers;

import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;

/**
 * Registered with {@link CarbonControllers} or a specific {@link CarbonController} instance to receive events.
 * 
 * @author Henrik
 *
 */
public interface CarbonControllerListener {
	/**
	 * A {@link CarbonController} got connected.
	 * 
	 * @param controller
	 */
	public void connected(CarbonController controller);
	/**
	 * A {@link CarbonController} got disconnected.
	 * 
	 * @param controller
	 */
	public void disconnected(CarbonController controller);

	/**
	 * The {@link CarbonKey} key was pressed.
	 * 
	 * @param controller
	 * @param key
	 * @return true if this event shouldn't be handed to other listeners.
	 */
	public boolean buttonDown(CarbonController controller, CarbonKey key);
	/**
	 * The {@link CarbonKey} key was released.
	 * 
	 * @param controller
	 * @param key
	 * @return true if this event shouldn't be handed to other listeners.
	 */
	public boolean buttonUp(CarbonController controller, CarbonKey key);
	/**
	 * The {@link CarbonAxis} axis was moved. The axis value of a trigger is in the range [0, 1]. For all other axes the value is in range
	 * [-1, 1].
	 * 
	 * @param controller
	 * @param axis
	 * @param value
	 * @return true if this event shouldn't be handed to other listeners.
	 */
	public boolean axisMoved(CarbonController controller, CarbonAxis axis, float value);
	/**
	 * The POV moved.
	 * 
	 * @param controller
	 * @param value
	 * @return true if this event shouldn't be handed to other listeners.
	 */
	public boolean povMoved(CarbonController controller, PovDirection value);
	/**
	 * The {@link CarbonVector} vector has changed.
	 * 
	 * @param controller
	 * @param vector
	 * @param newVector
	 * @return true if this event shouldn't be handed to other listeners.
	 */
	public boolean vectorChanged(CarbonController controller, CarbonVector vector, Vector3 newVector);
}
