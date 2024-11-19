package proj1;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import proj1.Enums.JSONFormat;
import proj1.Enums.UserSource;

class FormatInformation {
	private final JSONFormat format;
	private final UserSource source;
	private final String location;
	
	
	FormatInformation(JSONFormat format, UserSource source,String location) {
		this.format = format;
		this.source = source;
		this.location = location;
	}
	
	/**
	 * Parses the source into a List of Articles.
	 * Whilst parsing, Articles missing title, description, 
	 * URL, or date are considered errors and logged as such.
	 *
	 * @param logger a logger object which dictates where the error messages go
	 * @return List of Articles parsed from JSON String, empty if all errors
	 */
	
	List<Article> parse(Logger logger) throws IOException {	
		JSONParser parser = getParser();
		return accept(parser,logger);
	}
	
	private List<Article> accept(JSONParser parser, Logger logger) throws IOException {
		return parser.visit(this, logger);
	}
	/**
	 * Retrieves the content at the specified location.
	 *
	 * @return String containing the contents retrieved from FormatInformation
	 */
	String getContent() throws IOException {
		if(source == UserSource.FILE) {
			return Files.readString(Paths.get(location));
		}
		if(source == UserSource.URL) { 
			URL url = new URL(location);
			Scanner sc = new Scanner(url.openStream());
			StringBuffer sb = new StringBuffer();
			while(sc.hasNext()) {
				sb.append(sc.next());
			}
			sc.close();
			return sb.toString();
		}
		return "";
	}
	
	
	private JSONParser getParser() {
		if(format == JSONFormat.SIMPLE) { 
			return new SimpleParser();
		}
		if(format == JSONFormat.STANDARD) { 
			return new NewsParser();
		}
		return null;
	}
	
}