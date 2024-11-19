package proj1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;



class NewsParser implements JSONParser {
	static class JSONParserResults{
		private final String status;
		private final int totalResults;
		private final List<Article> articles;
		@JsonCreator
		JSONParserResults(@JsonProperty("status") String status,
			@JsonProperty("totalResults")int totalResults,
			@JsonProperty("articles")List<Article>articles) {
			this.status = status;
			this.totalResults = totalResults;
			this.articles = articles;
		}
		
	}
	/**
	 * Parses a NewsAPI JSON String into a List of Articles.
	 * Whilst parsing, Articles missing title, description, 
	 * URL, or date are considered errors and logged as such.
	 *
	 * @param content a string containing the JSON content to parse
	 * @param logger a logger object which dictates where the error messages go
	 * @return List of Articles parsed from JSON String, empty if all errors
	 */
	public List<Article> parse(String content, Logger logger) throws JsonProcessingException, IOException {
		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		JSONParserResults articlesList = mapper.readValue(content,JSONParserResults.class);
		List<Article> returnList = new ArrayList<Article>();
		for(Article a: articlesList.articles) {
			if(a.containsNulls()) {
				logger.log(Level.WARNING,"Error encountered: " + a.toString());
			}
			else {
				returnList.add(a);
			}
		}
		
		return returnList;
	}

}
