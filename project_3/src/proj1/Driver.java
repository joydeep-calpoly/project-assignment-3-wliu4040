package proj1;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.fasterxml.jackson.core.JsonProcessingException;

import proj1.Enums.JSONFormat;
import proj1.Enums.UserSource;

class Driver {
	public static void main(String[] args) throws SecurityException, IOException,JsonProcessingException{
		String[] SimplePaths = {"inputs/simple.txt"};
		String[] NewsPaths = {"inputs/newsapi.txt"};
		
		Logger logger = Logger.getLogger("logger");
		Handler[] handlers = logger.getParent().getHandlers();
        for (Handler handler : handlers) {
            if (handler instanceof ConsoleHandler) {
                logger.getParent().removeHandler(handler);
            }
        }
        FileHandler fileHandler = new FileHandler("exceptions.txt",true);
		logger.addHandler(fileHandler);
		SimpleFormatter formatter = new SimpleFormatter();
        fileHandler.setFormatter(formatter);
       
       //Demonstrates validity of SimpleParser
        System.out.println("SimpleParser:");
       for(String s : SimplePaths) {
			FormatInformation format = new FormatInformation(JSONFormat.SIMPLE,UserSource.FILE,s);
			List<Article> articles = format.parse(logger);
			for(Article a : articles) {
				a.print();
			}
       }
       //Demonstrates validity of NewsParser
       System.out.println("NewsParser:");
       for(String s : NewsPaths) {
			FormatInformation format = new FormatInformation(JSONFormat.STANDARD,UserSource.FILE,s);
			List<Article> articles = format.parse(logger);
			for(Article a : articles) {
				a.print();
			}
      }
       //Demonstrates validity of NewsParser on API data
       System.out.println("NewsParserAPI:");
       
       FormatInformation format = new FormatInformation(JSONFormat.STANDARD,UserSource.URL,"https://newsapi.org/v2/top-headlines?country=us&apiKey=cb9c64853a1c457485693ee7f16a68ce");
		List<Article> articles = format.parse(logger);
		for(Article a : articles) {
			a.print();
		}
	}

}
