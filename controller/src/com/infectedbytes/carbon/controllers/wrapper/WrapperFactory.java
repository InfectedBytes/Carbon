package com.infectedbytes.carbon.controllers.wrapper;

import com.badlogic.gdx.controllers.Controller;
import com.infectedbytes.carbon.controllers.CarbonController;

/**
 * Factory used to instantiate concrete {@link ControllerWrapper}.
 * 
 * @author Henrik
 *
 */
public interface WrapperFactory {
	/**
	 * @param name
	 * @return whether this factory can create a {@link ControllerWrapper} for a {@link Controller} with the specified name.
	 */
	public boolean canWrap(String name);
	/**
	 * @param controller
	 * @return a {@link CarbonController} wrapper for the {@link Controller}.
	 */
	public CarbonController wrap(Controller controller);
}
