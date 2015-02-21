package com.infectedbytes.carbon.controllers.wrapper;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.PovDirection;
import com.infectedbytes.carbon.controllers.CarbonAxis;
import com.infectedbytes.carbon.controllers.CarbonController;
import com.infectedbytes.carbon.controllers.CarbonKey;

/**
 * Factory four Ouya controller.
 * 
 * @author Henrik
 *
 */
public class Ouya implements WrapperFactory {

	@Override
	public boolean canWrap(String name) {
		return name.toLowerCase().contains("ouya");
	}

	@Override
	public CarbonController wrap(Controller controller) {
		return new Wrapper(controller);
	}

	private static class Wrapper extends ControllerWrapper {
		private CarbonKey[] keyMapping = new CarbonKey[] {
				CarbonKey.A, CarbonKey.B, null, CarbonKey.X, CarbonKey.Y, null,
				CarbonKey.L2, CarbonKey.R2, CarbonKey.L1, CarbonKey.R1, CarbonKey.L3, CarbonKey.R3};
		private CarbonAxis[] axisMapping = new CarbonAxis[] {CarbonAxis.LX, CarbonAxis.LY, CarbonAxis.LT, CarbonAxis.RX, CarbonAxis.RY, CarbonAxis.RT};

		public Wrapper(Controller controller) {
			super(controller);
		}

		@Override
		public String getName() {
			return "XBox360";
		}
		@Override
		public boolean buttonDown(Controller controller, int buttonCode) {
			if (buttonCode < 96) {
				if (buttonCode == 19)
					publish(PovDirection.north);
				else if (buttonCode == 20)
					publish(PovDirection.south);
				else if (buttonCode == 21)
					publish(PovDirection.west);
				else if (buttonCode == 22)
					publish(PovDirection.east);
				else if (buttonCode == 82)
					publish(CarbonKey.START, true);
				return false;
			} else if (buttonCode > 107) {
				return false;
			}

			CarbonKey key = keyMapping[buttonCode];
			publish(key, true);
			return false;
		}
		@Override
		public boolean buttonUp(Controller controller, int buttonCode) {
			if (buttonCode < 96) {
				if (buttonCode == 19)
					publish(PovDirection.center);
				else if (buttonCode == 20)
					publish(PovDirection.center);
				else if (buttonCode == 21)
					publish(PovDirection.center);
				else if (buttonCode == 22)
					publish(PovDirection.center);
				else if (buttonCode == 82)
					publish(CarbonKey.START, false);
				return false;
			} else if (buttonCode > 107) {
				return false;
			}

			CarbonKey key = keyMapping[buttonCode];
			publish(key, false);
			return false;
		}
		@Override
		public boolean axisMoved(Controller controller, int axisIndex, float value) {
			if (axisIndex < 0 || axisIndex > 5) return false;
			CarbonAxis axis = axisMapping[axisIndex];
			publish(axis, value);
			return false;
		}
		@Override
		public boolean supports(CarbonAxis axis) {
			if (axis == CarbonAxis.LZ || axis == CarbonAxis.RZ) return false;
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
