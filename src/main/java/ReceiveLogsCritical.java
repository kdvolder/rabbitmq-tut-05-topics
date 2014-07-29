
public class ReceiveLogsCritical {

	/**
	 * Receives only the log messages with 'critical' severity.
	 */
	public static void main(String[] args) throws Exception {
		new ReceiveLogsTopic("*.critical").run();
	}
	
}
