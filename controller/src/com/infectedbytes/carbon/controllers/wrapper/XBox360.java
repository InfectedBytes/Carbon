package com.infectedbytes.carbon.controllers.wrapper;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.PovDirection;
import com.infectedbytes.carbon.controllers.CarbonAxis;
import com.infectedbytes.carbon.controllers.CarbonController;
import com.infectedbytes.carbon.controllers.CarbonKey;

/**
 * Factory for XBox360 controllers.
 * 
 * @author Henrik
 *
 */
public class XBox360 implements WrapperFactory {

	@Override
	public boolean canWrap(String name) {
		return name.toLowerCase().contains("xbox") && name.contains("360");
	}

	@Override
	public CarbonController wrap(Controller controller) {
		return new Wrapper(controller);
	}

	private static class Wrapper extends ControllerWrapper {
		private CarbonKey[] keyMapping = new CarbonKey[] {
				CarbonKey.A, CarbonKey.B, CarbonKey.X, CarbonKey.Y, CarbonKey.L1, CarbonKey.R1,
				CarbonKey.BACK, CarbonKey.START, CarbonKey.L3, CarbonKey.R3};
		private CarbonAxis[] axisMapping = new CarbonAxis[] {CarbonAxis.LY, CarbonAxis.LX, CarbonAxis.RY, CarbonAxis.RX};// special handling
																															// for trigger

		private float lastTrigger;

		public Wrapper(Controller controller) {
			super(controller);
		}

		@Override
		public String getName() {
			return "XBox360";
		}
		@Override
		public boolean buttonDown(Controller controller, int buttonCode) {
			if (buttonCode < 0 || buttonCode > 9) return false;
			CarbonKey key = keyMapping[buttonCode];
			publish(key, true);
			return super.buttonDown(controller, buttonCode);
		}
		@Override
		public boolean buttonUp(Controller controller, int buttonCode) {
			if (buttonCode < 0 || buttonCode > 9) return false;
			CarbonKey key = keyMapping[buttonCode];
			publish(key, false);
			return super.buttonDown(controller, buttonCode);
		}
		@Override
		public boolean axisMoved(Controller controller, int axisIndex, float value) {
			if (axisIndex < 0 || axisIndex > 4) return false;
			CarbonAxis axis;
			if (axisIndex < 4) {
				axis = axisMapping[axisIndex];
				publish(axis, value);
			} else {
				float lt, rt;
				if (value >= 0f) {
					lt = value;
					rt = 0f;
				} else {
					lt = 0f;
					rt = -value;
				}
				publish(CarbonAxis.LT, lt);
				publish(CarbonAxis.RT, rt);

				if (lastTrigger > 0.5f && value <= 0.5f) {
					publish(CarbonKey.L2, false);
				} else if (lastTrigger < -0.5f && value >= -0.5f) {
					publish(CarbonKey.R2, false);
				}
				if (lastTrigger <= 0.5f && value > 0.5f) {
					publish(CarbonKey.L2, true);
				} else if (lastTrigger >= -0.5f && value < -0.5f) {
					publish(CarbonKey.R2, true);
				}
				lastTrigger = value;
			}
			return false;
		}
		@Override
		public boolean povMoved(Controller controller, int povIndex, PovDirection value) {
			if (povIndex == 0) {
				publish(value);
			}
			return false;
		}
		@Override
		public boolean supports(CarbonAxis axis) {
			return true;
		}
		@Override
		public boolean supports(CarbonKey key) {
			return true;
		}
		@Override
		public boolean supportsPov() {
			return true;
		}
	}
}
