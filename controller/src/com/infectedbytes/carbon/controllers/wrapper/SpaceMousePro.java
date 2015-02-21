package com.infectedbytes.carbon.controllers.wrapper;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.PovDirection;
import com.infectedbytes.carbon.controllers.CarbonAxis;
import com.infectedbytes.carbon.controllers.CarbonController;
import com.infectedbytes.carbon.controllers.CarbonKey;

/**
 * Wrapper for SpaceMouse Pro 3D mouse. It's not a real controller, but with it's six axes it might be useful.
 * 
 * @author Henrik
 *
 */
public class SpaceMousePro implements WrapperFactory {

	@Override
	public boolean canWrap(String name) {
		return name.contains("SpaceMouse Pro") && !name.contains("Receiver");
	}

	@Override
	public CarbonController wrap(Controller controller) {
		return new Wrapper(controller);
	}

	private static class Wrapper extends ControllerWrapper {
		private CarbonKey[] keyMapping = new CarbonKey[] {CarbonKey.START, CarbonKey.R2, null, null, null, null,
				CarbonKey.X, CarbonKey.Y, CarbonKey.B, CarbonKey.A, CarbonKey.BACK, CarbonKey.L1,
				CarbonKey.L2, CarbonKey.L3, CarbonKey.R1};
		private CarbonAxis[] axisMapping = new CarbonAxis[] {CarbonAxis.RX, CarbonAxis.RZ, CarbonAxis.RY, CarbonAxis.LZ, CarbonAxis.LY, CarbonAxis.LX};

		public Wrapper(Controller controller) {
			super(controller);
		}

		@Override
		public String getName() {
			return "XBox360";
		}
		@Override
		public boolean buttonDown(Controller controller, int buttonCode) {
			if (buttonCode < 0 || buttonCode > 14) return false;
			if (buttonCode >= 2 && buttonCode <= 5) {
				switch (buttonCode) {
					case 2:
						publish(PovDirection.north);
						break;
					case 3:
						publish(PovDirection.east);
						break;
					case 4:
						publish(PovDirection.south);
						break;
					case 5:
						publish(PovDirection.west);
						break;
				}
				return false;
			}
			CarbonKey key = keyMapping[buttonCode];
			publish(key, true);
			return super.buttonDown(controller, buttonCode);
		}
		@Override
		public boolean buttonUp(Controller controller, int buttonCode) {
			if (buttonCode < 0 || buttonCode > 14) return false;
			if (buttonCode >= 2 && buttonCode <= 5) {
				publish(PovDirection.center);
				return false;
			}
			CarbonKey key = keyMapping[buttonCode];
			publish(key, false);
			return super.buttonDown(controller, buttonCode);
		}
		@Override
		public boolean axisMoved(Controller controller, int axisIndex, float value) {
			if (axisIndex < 0 || axisIndex > 5) return false;
			CarbonAxis axis = axisMapping[axisIndex];
			if (axis == CarbonAxis.RZ) value = -value;
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
			if (axis == CarbonAxis.LT || axis == CarbonAxis.RT) return false;
			return true;
		}
		@Override
		public boolean supports(CarbonKey key) {
			if (key == CarbonKey.R3) return false;
			return true;
		}
		@Override
		public boolean supportsPov() {
			return true;
		}
	}
}
