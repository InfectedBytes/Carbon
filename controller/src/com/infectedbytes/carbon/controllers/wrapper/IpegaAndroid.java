package com.infectedbytes.carbon.controllers.wrapper;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.PovDirection;
import com.infectedbytes.carbon.controllers.CarbonAxis;
import com.infectedbytes.carbon.controllers.CarbonController;
import com.infectedbytes.carbon.controllers.CarbonKey;

/**
 * Factory for Ipega controllers on Android.
 * 
 * @author Henrik
 *
 */
public class IpegaAndroid implements WrapperFactory {
	private boolean isAndroid;

	public IpegaAndroid() {
		isAndroid = Gdx.app.getType() == ApplicationType.Android;
	}
	@Override
	public boolean canWrap(String name) {
		return isAndroid && name.toLowerCase().contains("ipega");
	}
	@Override
	public CarbonController wrap(Controller controller) {
		return new Wrapper(controller);
	}

	private static class Wrapper extends ControllerWrapper {
		private CarbonKey[] keyMapping = new CarbonKey[] {
				CarbonKey.A, CarbonKey.B, null, CarbonKey.X, CarbonKey.Y, null,
				CarbonKey.L2, CarbonKey.R2, CarbonKey.L1, CarbonKey.R1,
				CarbonKey.L3, CarbonKey.R3, CarbonKey.START, CarbonKey.BACK};
		private CarbonAxis[] axisMapping = new CarbonAxis[] {CarbonAxis.LX, CarbonAxis.LY, CarbonAxis.RX, CarbonAxis.RY, CarbonAxis.RT, CarbonAxis.LT};

		public Wrapper(Controller controller) {
			super(controller);
		}

		@Override
		public String getName() {
			return "ipega Android";
		}

		@Override
		public boolean buttonDown(Controller controller, int buttonIndex) {
			if (buttonIndex < 96 || buttonIndex > 109) return false;
			buttonIndex -= 96;

			CarbonKey key = keyMapping[buttonIndex];
			publish(key, true);
			return false;
		}
		@Override
		public boolean buttonUp(Controller controller, int buttonIndex) {
			if (buttonIndex < 96 || buttonIndex > 109) return false;
			buttonIndex -= 96;

			CarbonKey key = keyMapping[buttonIndex];
			publish(key, false);
			return false;
		}
		@Override
		public boolean axisMoved(Controller controller, int axisIndex, float value) {
			if (axisIndex < 0 || axisIndex > 7) return false;
			if (axisIndex == 6) {
				PovDirection pov;
				if (value > 0)
					pov = PovDirection.east;
				else if (value < 0)
					pov = PovDirection.west;
				else
					pov = PovDirection.center;
				publish(pov);
				return false;
			} else if (axisIndex == 7) {
				PovDirection pov;
				if (value > 0)
					pov = PovDirection.south;
				else if (value < 0)
					pov = PovDirection.north;
				else
					pov = PovDirection.center;
				publish(pov);
				return false;
			}
			CarbonAxis axis = axisMapping[axisIndex];
			publish(axis, value);
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
