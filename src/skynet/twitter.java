package skynet;
import twitter4j.TwitterException;
import twitter4j.auth.OAuthAuthorization;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.media.ImageUpload;
import twitter4j.media.ImageUploadFactory;

import java.io.File;

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
