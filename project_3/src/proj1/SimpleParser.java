package proj1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;


class SimpleParser implements JSONParser{
	
	/**
	 * Parses a SimpleFormat JSON String into an Article.
	 * If the Article is missing title, description, URL, or date, 
	 * it is considered an error and logged as such. 
	 * @param content a string containing the JSON content to parse
	 * @param logger a logger object which dictates where the error messages go
	 * @return List of Articles parsed from JSON String, empty if all errors
	 */
	public List<Article> parse(String content, Logger logger) throws JsonProcessingException, IOException {
		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Article article = mapper.readValue(content,Article.class);
		List<Article> returnList = new ArrayList<Article>();
		if(article.containsNulls()) {
			logger.log(Level.WARNING,"Error encountered: " + article.toString());
		}
		else {
			returnList.add(article);
		}
		
		return returnList;
	}
	
}
