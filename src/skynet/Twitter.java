package skynet;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.OAuthAuthorization;
import twitter4j.auth.RequestToken;
import twitter4j.User;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.media.ImageUpload;
import twitter4j.media.ImageUploadFactory;
import twitter4j.media.MediaProvider;

import java.io.File;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public final class twitter {
	
	//Hey Michael and Zachary, read this!!!!!!!
	//Below values represent authentication for the twitter account.
	//Find values at https://apps.twitter.com/app under "Skynet Astronomy" over "keys and access tokens"
	
	//ALWAYS REMOVE BELOW VALUES BEFORE POSTING TO GITHUB, FOR SECURITY
	private String consumerKey = "********";
	private String consumerSecret = "********";
	private String accessToken = "********";
	private String accessSecret = "********";
	//ALWAYS REMOVE ABOVE VALUES BEFORE POSTING TO GITHUB, FOR SECURITY
		
	private Configuration conf;
	private OAuthAuthorization auth;
	
		public twitter(){
			
			ConfigurationBuilder cb = new ConfigurationBuilder();
			cb.setDebugEnabled(true).setOAuthConsumerKey(consumerKey)
			.setOAuthConsumerSecret(consumerSecret)
			.setOAuthAccessToken(accessToken)
			.setOAuthAccessTokenSecret(accessSecret);
			conf = cb.build();
			auth = new OAuthAuthorization(conf);
           
	}
		public String postImageWithMessage(String imagePath, String message) throws TwitterException{
			
			ImageUpload upload = new ImageUploadFactory(conf).getInstance(auth);
            return upload.upload(new File(imagePath), message);
           
			
		}
		
}