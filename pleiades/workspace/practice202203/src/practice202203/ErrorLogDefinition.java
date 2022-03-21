package practice202203;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;;

public class ErrorLogDefinition {

	public static void main(String[] args) {

		Log logger = LogFactory.getLog(ErrorLogDefinition.class);
		if (args.length != 2) {
			logger.error("起動引数の数が異常：" + args.length);
		}
	}

}
