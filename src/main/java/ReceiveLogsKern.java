
public class ReceiveLogsKern {

	/**
	 * Receives only the log messages from 'kern' module.
	 */
	public static void main(String[] args) throws Exception {
		new ReceiveLogsTopic("kern.*").run();
	}
	
}
