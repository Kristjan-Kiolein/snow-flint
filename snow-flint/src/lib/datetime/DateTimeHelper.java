package lib.datetime;

import java.util.HashMap;
import java.util.Map;

public class DateTimeHelper {
	
	
	public static final Map<String, String> DATE_FORMAT_REGEX = new HashMap<String, String>() {

		private static final long serialVersionUID = 1L;

		{
			put("^\\d{8}$", "yyyyMMdd");
			put("^\\d{1,2}-\\d{1,2}-\\d{4}$", "dd-MM-yyyy");
			put("^\\d{4}-\\d{1,2}-\\d{1,2}$", "yyyy-MM-dd");
			put("^\\d{1,2}\\.\\d{1,2}\\.\\d{4}$", "dd.MM.yyyy");
			put("^\\d{1,2}/\\d{1,2}/\\d{4}$", "MM/dd/yyyy");
			put("^\\d{4}/\\d{1,2}/\\d{1,2}$", "yyyy/MM/dd");
			put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}$", "dd MMM yyyy");
			put("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}$", "dd MMMM yyyy");
			put("^\\d{12}$", "yyyyMMddHHmm");
			put("^\\d{8}\\s\\d{4}$", "yyyyMMdd HHmm");
			put("^\\d{1,2}-\\d{1,2}-\\d{4}\\s\\d{1,2}:\\d{2}$", "dd-MM-yyyy HH:mm");
			put("^\\d{1,2}\\.\\d{1,2}\\.\\d{4}\\s\\d{1,2}:\\d{2}$", "dd.MM.yyyy HH:mm");
			put("^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{2}$", "yyyy-MM-dd HH:mm");
			put("^\\d{1,2}/\\d{1,2}/\\d{4}\\s\\d{1,2}:\\d{2}$", "MM/dd/yyyy HH:mm");
			put("^\\d{4}/\\d{1,2}/\\d{1,2}\\s\\d{1,2}:\\d{2}$", "yyyy/MM/dd HH:mm");
			put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}\\s\\d{1,2}:\\d{2}$", "dd MMM yyyy HH:mm");
			put("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}\\s\\d{1,2}:\\d{2}$", "dd MMMM yyyy HH:mm");
			put("^\\d{14}$", "yyyyMMddHHmmss");
			put("^\\d{8}\\s\\d{6}$", "yyyyMMdd HHmmss");
			put("^\\d{1,2}-\\d{1,2}-\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd-MM-yyyy HH:mm:ss");
			put("^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{2}:\\d{2}$", "yyyy-MM-dd HH:mm:ss");
			put("^\\d{1,2}/\\d{1,2}/\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "MM/dd/yyyy HH:mm:ss");
			put("^\\d{4}/\\d{1,2}/\\d{1,2}\\s\\d{1,2}:\\d{2}:\\d{2}$", "yyyy/MM/dd HH:mm:ss");
			put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd MMM yyyy HH:mm:ss");
			put("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd MMMM yyyy HH:mm:ss");
		}
	};

	public static String getDateFormat(String dateString) {
		for (String regexp : DATE_FORMAT_REGEX.keySet()) {
			if (dateString.toLowerCase().matches(regexp)) {
				return DATE_FORMAT_REGEX.get(regexp);
			}
		}
		return null;
	}
	
}
