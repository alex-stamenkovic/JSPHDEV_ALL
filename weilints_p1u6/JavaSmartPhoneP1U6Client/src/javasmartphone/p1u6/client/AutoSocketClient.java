/* 
 * Author: Wei-Lin Tsai weilints@andrew.cmu.edu
 * 
 * The driver of client part. 
 * Will keep get input from STDIN and do either add model or configure model.
 * Please for each operation, carModelOptionsIO and selectCarOption will
 * create new thread of defaultClientSocket and do the rest tasks.
 */
package javasmartphone.p1u6.client;

import java.io.IOException;
import java.util.Scanner;

public class AutoSocketClient {
	static private Scanner scanner = new Scanner(System.in) ; 
	public static void main(String[] args) throws IOException {
		final int PORT = 4444;
		final String REMOTE_HOST = "localHost";
		
		CarModelOptionsIO carModelOptionsIO = new CarModelOptionsIO(REMOTE_HOST, PORT);
		SelectCarOption selectCarOption = new SelectCarOption(REMOTE_HOST, PORT);
		
		boolean keepRunning = true;
		while (keepRunning) {
			switch (getCatModelCommandOption()) {
			case ADD_MODEL:
				carModelOptionsIO.addModel();
				break;
			case CONFIGURE_MODEL:
				selectCarOption.configureModel();
				break;
			default:
				keepRunning = false;
			}
		}
		
	}
	
	public static CAR_MODEL_COMMAND_OPTION getCatModelCommandOption() {
		System.out.println("\nPlease type mode: ");
		System.out.println("1. Add new model\n2. Configure an model\n3. Exit");
		
		switch (scanner.nextInt()) {
			case 1: return CAR_MODEL_COMMAND_OPTION.ADD_MODEL;
			case 2: return CAR_MODEL_COMMAND_OPTION.CONFIGURE_MODEL;
			default:
				return  CAR_MODEL_COMMAND_OPTION.EXIT;
		}
	}
	
}
