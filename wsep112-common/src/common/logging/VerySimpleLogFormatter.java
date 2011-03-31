package common.logging;

import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Avi Digmi
 *
 * This class extends the class SimpleFormatter by overriding the method format(),
 * in order to change the format of the log which will be printed.
 */
public class VerySimpleLogFormatter extends SimpleFormatter {

	/**
	 * an instance of a DateFormatter that is used for formatting the time of a log record into a human-readable
	 * string
	 */
	private final DateFormat _dateFormat;

	/**
	 * the character sequence that is used to separate lines in the generated stream
	 */
	private final String _lineSep;

	 /**
	  * The constructor of VerySimpleFormatter (contains call to the super's constructor)
	  */
	public VerySimpleLogFormatter() {

		super();
		this._dateFormat = new SimpleDateFormat ("dd/MM/yyyy HH:mm:ss");
		this._lineSep = System.getProperty("line.separator");
	}

	/**
	 * Formats a log record into a String.
	 *
	 * @param record the log record to be formatted.
	 * @return human-readable message which will be printed by the logger
	 */
	public String format(LogRecord record) {

		String message = "";

		message += this._dateFormat.format(new Date());
		message += " ";
		message += this.formatMessage(record);
		message += this._lineSep;

		return message;
	}
}
