package skynet;
import twitter4j.TwitterException;
import twitter4j.auth.OAuthAuthorization;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.media.ImageUpload;
import twitter4j.media.ImageUploadFactory;

import java.io.File;

public final class twitter {
	
	private Configuration conf;
	private OAuthAuthorization auth;
	
	public twitter(Settings s){
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setOAuthConsumerKey(s.settings.get("consumerKey"))
		.setOAuthConsumerSecret(s.settings.get("consumerSecret"))
		.setOAuthAccessToken(s.settings.get("accessToken"))
		.setOAuthAccessTokenSecret(s.settings.get("accessSecret"));
		conf = cb.build();
		auth = new OAuthAuthorization(conf);
	}
	
	public String postImageWithMessage(String imagePath, String message) throws TwitterException {
		
		ImageUpload upload = new ImageUploadFactory(conf).getInstance(auth);
        return upload.upload(new File(imagePath), message);
	}
		
}
