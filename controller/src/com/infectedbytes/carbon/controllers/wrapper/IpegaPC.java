package com.infectedbytes.carbon.controllers.wrapper;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.PovDirection;
import com.infectedbytes.carbon.controllers.CarbonAxis;
import com.infectedbytes.carbon.controllers.CarbonController;
import com.infectedbytes.carbon.controllers.CarbonKey;

/**
 * Factory for Ipega controllers on a PC.
 * 
 * @author Henrik
 *
 */
public class IpegaPC implements WrapperFactory {
	private boolean isPC;

	public IpegaPC() {
		isPC = Gdx.app.getType() != ApplicationType.Android;
	}

	@Override
	public boolean canWrap(String name) {
		return isPC && name.toLowerCase().contains("ipega");
	}

	@Override
	public CarbonController wrap(Controller controller) {
		return new Wrapper(controller);
	}

	private static class Wrapper extends ControllerWrapper {
		private CarbonKey[] keyMapping = new CarbonKey[] {
				CarbonKey.A, CarbonKey.B, null, CarbonKey.X, CarbonKey.Y, null,
				CarbonKey.L2, CarbonKey.R2, CarbonKey.L1, CarbonKey.R1,
				CarbonKey.BACK, CarbonKey.START, null, CarbonKey.L3, CarbonKey.R3};
		private CarbonAxis[] axisMapping = new CarbonAxis[] {CarbonAxis.RY, CarbonAxis.RX, CarbonAxis.LY, CarbonAxis.LX};

		public Wrapper(Controller controller) {
			super(controller);
		}

		@Override
		public String getName() {
			return "ipega PC";
		}

		@Override
		public boolean buttonDown(Controller controller, int buttonIndex) {
			if (buttonIndex < 0 || buttonIndex > 14) return false;

			CarbonKey key = keyMapping[buttonIndex];
			publish(key, true);
			if (buttonIndex == 6)
				publish(CarbonAxis.LT, 1f);
			else if (buttonIndex == 7)
				publish(CarbonAxis.RT, 1f);
			return false;
		}
		@Override
		public boolean buttonUp(Controller controller, int buttonIndex) {
			if (buttonIndex < 0 || buttonIndex > 14) return false;

			CarbonKey key = keyMapping[buttonIndex];
			publish(key, false);
			if (buttonIndex == 6)
				publish(CarbonAxis.LT, 0f);
			else if (buttonIndex == 7)
				publish(CarbonAxis.RT, 0f);
			return false;
		}
		@Override
		public boolean axisMoved(Controller controller, int axisIndex, float value) {
			if (axisIndex < 0 || axisIndex > 3) return false;
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
