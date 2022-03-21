package practice202203;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;;

public class Error {

	public static Log logger = LogFactory.getLog(Error.class);
	public static void main(String[] args) {

		if (args.length != 2) {
			logger.info("起動引数の数が異常：" + args.length);
		}
	}

}
