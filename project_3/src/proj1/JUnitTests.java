package proj1;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import proj1.Enums.JSONFormat;
import proj1.Enums.UserSource;

class JUnitTests {

	@Test
	//Tests that valid inputs are correctly returned for the NewsParser
	void NewsParserValidTest() throws IOException {
		String path = "testInputs/good.json";
		String content = Files.readString(Paths.get(path)); 
		Logger logger = Logger.getLogger("testLogger");
		JSONParser parser = new NewsParser();
		List<Article> articlesResults = parser.parse(content, logger);
		List<Article> articlesExpected = new ArrayList<Article>();
		articlesExpected.add(new Article("123","abc","asd","2021-03-24T22:32:00Z"));
		articlesExpected.add(new Article("abc","drm","123","2021-03-24T22:32:00Z"));
		articlesExpected.add(new Article("abc","123","drm","2021-03-24T22:32:00Z"));
		articlesExpected.add(new Article("abc","2021","123","2021-03-24T22:32:00Z"));
		boolean pass = true;
		for(int i = 0; i < articlesResults.size(); i++) {
			if(!articlesResults.get(i).testEqual(articlesExpected.get(i))) {
				pass = false;
			}
		}
		assertTrue(pass);
	}
	
	@Test
	//Tests that a myriad of invalid inputs (Missing fields, combinations of missing fields) are correctly ignored
	//for the NewsParser
	void NewsParserInvalidTest() throws IOException {
		String path = "testInputs/bad.json";
		String content = Files.readString(Paths.get(path)); 
		Logger logger = Logger.getLogger("testLogger");
		JSONParser parser = new NewsParser();
		List<Article> articlesResults = parser.parse(content, logger);
		assertTrue(articlesResults.isEmpty());
	}
	
	@Test
	//Tests that invalid inputs are correctly ignored when in combination with valid inputs for the NewParser
	void NewsParserMixedTest() throws IOException {
		String path = "testInputs/mixed.json";
		String content = Files.readString(Paths.get(path)); 
		Logger logger = Logger.getLogger("testLogger");
		JSONParser parser = new NewsParser();
		List<Article> articlesResults = parser.parse(content, logger);
		List<Article> articlesExpected = new ArrayList<Article>();
		articlesExpected.add(new Article("abc","drm","123","2021-03-24T22:32:00Z"));
		articlesExpected.add(new Article("abc","123","drm","2021-03-24T22:32:00Z"));
		boolean pass = true;
		for(int i = 0; i < articlesResults.size(); i++) {
			if(!articlesResults.get(i).testEqual(articlesExpected.get(i))) {
				pass = false;
			}
		}
		assertTrue(pass);
	}
	
	@Test
	//Tests that valid inputs are correctly returned for the SimpleParser
	void SimpleParserValidTest() throws IOException {
		String path = "testInputs/goodsimple.txt";
		String content = Files.readString(Paths.get(path)); 
		Logger logger = Logger.getLogger("testLogger");
		JSONParser parser = new SimpleParser();
		List<Article> articlesResults = parser.parse(content, logger);
		List<Article> articlesExpected = new ArrayList<Article>();
		articlesExpected.add(new Article("abc","123","drm","321"));
		boolean pass = true;
		for(int i = 0; i < articlesResults.size(); i++) {
			if(!articlesResults.get(i).testEqual(articlesExpected.get(i))) {
				articlesResults.get(i).print();
				articlesExpected.get(i).print();
				pass = false;
			}
		}
		assertTrue(pass);
	}
	
	@Test
	//Tests that SimpleParser correctly ignores an article with no Date
	void SimpleParserNoDateTest() throws IOException {
		String path = "testInputs/SimpleNoDate.txt";
		String content = Files.readString(Paths.get(path)); 
		Logger logger = Logger.getLogger("testLogger");
		JSONParser parser = new SimpleParser();
		List<Article> articlesResults = parser.parse(content, logger);
		assertTrue(articlesResults.isEmpty());
	}
	
	@Test
	//Tests that SimpleParser correctly ignores an article with no Description
	void SimpleParserNoDescriptionTest() throws IOException {
		String path = "testInputs/SimpleNoDescription.txt";
		String content = Files.readString(Paths.get(path)); 
		Logger logger = Logger.getLogger("testLogger");
		JSONParser parser = new SimpleParser();
		List<Article> articlesResults = parser.parse(content, logger);
		assertTrue(articlesResults.isEmpty());
	}
	
	@Test
	//Tests that SimpleParser correctly ignores an article with no Title
	void SimpleParserNoTitleTest() throws IOException {
		String path = "testInputs/SimpleNoTitle.txt";
		String content = Files.readString(Paths.get(path)); 
		Logger logger = Logger.getLogger("testLogger");
		JSONParser parser = new SimpleParser();
		List<Article> articlesResults = parser.parse(content, logger);
		assertTrue(articlesResults.isEmpty());
	}
	
	@Test
	//Tests that SimpleParser correctly ignores an article with no URL
	void SimpleParserNoURLTest() throws IOException {
		String path = "testInputs/SimpleNoURL.txt";
		String content = Files.readString(Paths.get(path)); 
		Logger logger = Logger.getLogger("testLogger");
		JSONParser parser = new SimpleParser();
		List<Article> articlesResults = parser.parse(content, logger);
		assertTrue(articlesResults.isEmpty());
	}
	
	@Test
	//Tests that SimpleParser correctly ignores an article with no Title or Description
	void SimpleParserNoTitleOrDescription() throws IOException {
		String path = "testInputs/SimpleNoTitleOrDescription.txt";
		String content = Files.readString(Paths.get(path)); 
		Logger logger = Logger.getLogger("testLogger");
		JSONParser parser = new SimpleParser();
		List<Article> articlesResults = parser.parse(content, logger);
		assertTrue(articlesResults.isEmpty());
	}
	
	@Test
	//Tests that SimpleParser correctly ignores an article with no URL or Date
	void SimpleParserNoURLOrDate() throws IOException {
		String path = "testInputs/SimpleNoURLOrDate.txt";
		String content = Files.readString(Paths.get(path)); 
		Logger logger = Logger.getLogger("testLogger");
		JSONParser parser = new SimpleParser();
		List<Article> articlesResults = parser.parse(content, logger);
		assertTrue(articlesResults.isEmpty());
	}
	
	@Test
	//Tests that the correct Parser is used when JSONFormat is SIMPLE
	void FormatInformationSimpleFileParser() throws IOException {
		FormatInformation format = new FormatInformation(JSONFormat.SIMPLE,UserSource.FILE,"testInputs/goodsimple.txt");
		List<Article> articlesResults = format.parse(null);
		List<Article> articlesExpected = new ArrayList<Article>();
		articlesExpected.add(new Article("abc","123","drm","321"));
		boolean pass = true;
		for(int i = 0; i < articlesResults.size(); i++) {
			if(!articlesResults.get(i).testEqual(articlesExpected.get(i))) {
				articlesResults.get(i).print();
				articlesExpected.get(i).print();
				pass = false;
			}
		}
		assertTrue(pass);
	}
	
	@Test
	//Tests that the correct Parser is used when JSONFormat is STANDARD
	void FormatInformationNewsFileParser() throws IOException {
		FormatInformation format = new FormatInformation(JSONFormat.STANDARD,UserSource.FILE,"testInputs/good.json");
		List<Article> articlesResults = format.parse(null);
		List<Article> articlesExpected = new ArrayList<Article>();
		articlesExpected.add(new Article("123","abc","asd","2021-03-24T22:32:00Z"));
		articlesExpected.add(new Article("abc","drm","123","2021-03-24T22:32:00Z"));
		articlesExpected.add(new Article("abc","123","drm","2021-03-24T22:32:00Z"));
		articlesExpected.add(new Article("abc","2021","123","2021-03-24T22:32:00Z"));
		boolean pass = true;
		for(int i = 0; i < articlesResults.size(); i++) {
			if(!articlesResults.get(i).testEqual(articlesExpected.get(i))) {
				pass = false;
			}
		}
		assertTrue(pass);
	}

}
