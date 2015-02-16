package com.infectedbytes.carbon;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector3;
import com.infectedbytes.carbon.controllers.CarbonAxis;
import com.infectedbytes.carbon.controllers.CarbonController;
import com.infectedbytes.carbon.controllers.CarbonControllerListener;
import com.infectedbytes.carbon.controllers.CarbonControllers;
import com.infectedbytes.carbon.controllers.CarbonKey;
import com.infectedbytes.carbon.controllers.CarbonVector;

public class Main extends ApplicationAdapter {
	@Override
	public void create() {
		CarbonControllers.addListener(new CarbonControllerListener() {
			@Override
			public boolean vectorChanged(CarbonController controller, CarbonVector vector, Vector3 newVector) {
				System.out.printf("%s %s : %s\n", controller.getName(), vector.toString(), newVector.toString());
				return false;
			}

			@Override
			public boolean povMoved(CarbonController controller, PovDirection value) {
				System.out.printf("%s pov : %s\n", controller.getName(), value.toString());
				return false;
			}

			@Override
			public void disconnected(CarbonController controller) {}
			@Override
			public void connected(CarbonController controller) {}

			@Override
			public boolean buttonUp(CarbonController controller, CarbonKey key) {
				System.out.printf("%s released %s\n", controller.getName(), key.toString());
				return false;
			}

			@Override
			public boolean buttonDown(CarbonController controller, CarbonKey key) {
				System.out.printf("%s pressed %s\n", controller.getName(), key.toString());
				return false;
			}

			@Override
			public boolean axisMoved(CarbonController controller, CarbonAxis axis, float value) {
				System.out.printf("%s axis %s : %f\n", controller.getName(), axis.toString(), value);
				return false;
			}
		});
	}
	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
}