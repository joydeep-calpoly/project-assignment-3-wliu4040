package proj1;


import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

class Article {
	private final String title;
	private final String description;
	private final String url;
	private final String date; //Date object?
	@JsonCreator
	Article(@JsonProperty(value = "title") String title, 
			@JsonProperty(value = "description") String description, 
			@JsonProperty(value = "url") String url, 
			@JsonProperty(value = "publishedAt") String date) {
		this.title = title;
		this.description = description;
		this.url = url;
		this.date = date;
	}
	/**
	 * Prints the details of the article including the title, description, 
	 * URL, and date to the console.
	 */
	void print() {
		System.out.println("title: " + title);
		System.out.println("at: " + date);
		System.out.println("url: " + url);
		System.out.println(description + "\n");
		
	}
	/**
	 * Checks if any of the fields in the article (title, description, 
	 * URL, or date) are null.
	 *
	 * @return true if any field is null, false otherwise.
	 */
	boolean containsNulls() {
		
		return (title == null || description == null || url == null || date == null);
	}
	
	/**
	 * Compares this article with another article for equality based on 
	 * the title, description, URL, and date.
	 *
	 * @param a the article to compare with.
	 * @return true if all fields are equal, false otherwise.
	 */
	boolean testEqual(Article a) {
		return (a.title.equals(title) && a.description.equals(description) && a.url.equals(url) && a.date.equals(date));
	}
}
