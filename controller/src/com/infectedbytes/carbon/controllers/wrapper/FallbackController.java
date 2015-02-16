package com.infectedbytes.carbon.controllers.wrapper;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.PovDirection;
import com.infectedbytes.carbon.controllers.CarbonAxis;
import com.infectedbytes.carbon.controllers.CarbonController;
import com.infectedbytes.carbon.controllers.CarbonKey;

/**
 * Default controller, which is used if no better {@link ControllerWrapper} was found.
 * 
 * @author Henrik
 *
 */
public class FallbackController implements WrapperFactory {
	@Override
	public boolean canWrap(String name) {
		return true;
	}
	@Override
	public CarbonController wrap(Controller controller) {
		return new Wrapper(controller);
	}

	public static class Wrapper extends ControllerWrapper {
		private CarbonKey[] keyMapping = new CarbonKey[] {
				CarbonKey.A, CarbonKey.B, CarbonKey.X, CarbonKey.Y, CarbonKey.L1, CarbonKey.R1,
				CarbonKey.BACK, CarbonKey.START, CarbonKey.L2, CarbonKey.R2, CarbonKey.L3, CarbonKey.R3};
		private CarbonAxis[] axisMapping = new CarbonAxis[] {
				CarbonAxis.LY, CarbonAxis.LX, CarbonAxis.RY, CarbonAxis.RX, CarbonAxis.LT, CarbonAxis.RT};

		public Wrapper(Controller controller) {
			super(controller);
		}

		@Override
		public String getName() {
			return "Default Controller";
		}

		@Override
		public boolean buttonDown(Controller controller, int buttonIndex) {
			if (buttonIndex < 0 || buttonIndex > 11) return false;

			CarbonKey key = keyMapping[buttonIndex];
			publish(key, true);
			return false;
		}
		@Override
		public boolean buttonUp(Controller controller, int buttonIndex) {
			if (buttonIndex < 0 || buttonIndex > 11) return false;

			CarbonKey key = keyMapping[buttonIndex];
			publish(key, false);
			return false;
		}
		@Override
		public boolean axisMoved(Controller controller, int axisIndex, float value) {
			if (axisIndex < 0 || axisIndex > 6) return false;
			CarbonAxis axis = axisMapping[axisIndex];
			publish(axis, value);
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
