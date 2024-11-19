package proj1;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;


interface JSONParser {
	
	/**
	 * Parses a JSON String into a List of Articles.
	 * Whilst parsing, Articles missing title, description, 
	 * URL, or date are considered errors and logged as such.
	 *
	 * @param content a string containing the JSON content to parse
	 * @param logger a logger object which dictates where the error messages go
	 * @return List of Articles parsed from JSON String
	 * @throws IOException 
	 * @throws JsonProcessingException 
	 */
	List<Article> parse(String content, Logger logger) throws JsonProcessingException, IOException;
	
	/**
	 * Parses a JSON String into a List of Articles.
	 * Whilst parsing, Articles missing title, description, 
	 * URL, or date are considered errors and logged as such.
	 * 
	 * @param format a FormatInformation object containing source and format information.
	 * @param logger a logger object which dictates where the error messages go
	 * @return List of Articles parsed from JSON String
	 * @throws IOException 
	 * @throws JsonProcessingException 
	 */
	
	default List<Article> visit(FormatInformation format,Logger logger) throws IOException {
		String content = format.getContent();
		return parse(content,logger);
	}

}