package com.infectedbytes.carbon.controllers;

/**
 * Enumeration of the different analog axes.
 * 
 * @author Henrik
 *
 */
public enum CarbonAxis {
	/**
	 * Y axis of the left analog stick. The axis value should be in range [-1, 1], where -1 is up and +1 is down.
	 */
	LY,
	/**
	 * X axis of the left analog stick. The axis value should be in range [-1, 1], where -1 is left and +1 is right.
	 */
	LX,
	/**
	 * Y axis of the right analog stick. The axis value should be in range [-1, 1], where -1 is up and +1 is down.
	 */
	RY,
	/**
	 * X axis of the right analog stick. The axis value should be in range [-1, 1], where -1 is left and +1 is right.
	 */
	RX,
	/**
	 * Left trigger. Typically an analog shoulder button. The axis value should be in range [0, 1], where 0 is not pressed and 1 is fully
	 * pressed.
	 */
	LT,
	/**
	 * Right trigger. Typically an analog shoulder button. The axis value should be in range [0, 1], where 0 is not pressed and 1 is fully
	 * pressed.
	 */
	RT,
	/**
	 * Z axis of the left analog stick. Often not supported. The axis value should be in range [-1, 1], where -1 is up and +1 is down.
	 */
	LZ,
	/**
	 * Z axis of the right analog stick. Often not supported. The axis value should be in range [-1, 1], where -1 is up and +1 is down.
	 */
	RZ
}
