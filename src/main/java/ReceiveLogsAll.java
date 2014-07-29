
public class ReceiveLogsAll {
	
	/**
	 * Receives all log messages.
	 */
	public static void main(String[] args) throws Exception {
		new ReceiveLogsTopic("#").run();
	}

}
