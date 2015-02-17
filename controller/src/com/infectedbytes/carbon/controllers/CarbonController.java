package com.infectedbytes.carbon.controllers;

import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;

/**
 * Represents a connected controller. Provides methods to query the state of {@link CarbonKey}, {@link CarbonAxis}, {@link CarbonVector} and
 * a POV on the controller. Additionally one can check if those components are supported by this controller via the different
 * {@code supports(...)}-methods. Multiple {@link CarbonControllerListener} instances can be registered with the Controller to receive
 * events in case the controller's state changes. Listeners will be invoked on the rendering thread.
 * 
 * @author Henrik
 *
 */
public interface CarbonController {
	/**
	 * @param key
	 * @return whether the {@link CarbonKey} is pressed.
	 */
	public boolean getButton(CarbonKey key);
	/**
	 * @param axis
	 * @return the value of the {@link CarbonAxis}, between 0 and 1 for triggers and between -1 and 1 for all other axes.
	 */
	public float getAxis(CarbonAxis axis);
	/**
	 * @return the {@link PovDirection}
	 */
	public PovDirection getPov();
	/**
	 * @param vector
	 * @return the specific {@link CarbonVector}, may be null.
	 */
	public Vector3 getVector(CarbonVector vector);

	/**
	 * Adds a new {@link CarbonControllerListener} to this {@link CarbonController}. The listener will receive calls in case the state of
	 * the controller changes. The listener will be invoked on the rendering thread.
	 * 
	 * @param listener
	 */
	public void addListener(CarbonControllerListener listener);
	/**
	 * removes the given {@link CarbonControllerListener}
	 * 
	 * @param listener
	 */
	public void removeListener(CarbonControllerListener listener);
	/**
	 * @return the name of this {@link CarbonController}
	 */
	public String getName();

	/**
	 * @param key
	 * @return whether the {@link CarbonKey} is supported by this controller.
	 */
	public boolean supports(CarbonKey key);
	/**
	 * @param axis
	 * @return whether the {@link CarbonAxis} is supported by this controller.
	 */
	public boolean supports(CarbonAxis axis);
	/**
	 * @param vector
	 * @return whether the {@link CarbonVector} is supported by this controller.
	 */
	public boolean supports(CarbonVector vector);
	/**
	 * @return whether this controller has a POV.
	 */
	public boolean supportsPov();
}
