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


    public static void main(String[] args) throws TwitterException {

        String UserAccessToken = null;
        String UserAccessSecret = null;

        try {

            Twitter twitter = new TwitterFactory().getInstance();


             //Set Your Application Consumer Key & Consumer Key Secret
             //twitter.setOAuthConsumer("hoHHVyYgQGlNpVPmeh9MyDaD7","RYl71fv8L0F3lNkOSX4MQRrbynsez57voae0Z8VquhzY26u5aJ");

            try {
                // get request token.
                // this will throw IllegalStateException if access token is already available
                RequestToken requestToken = twitter.getOAuthRequestToken();
                System.out.println("Got request token.");
                System.out.println("Request token: " + requestToken.getToken());
                System.out.println("Request token secret: " + requestToken.getTokenSecret());


                AccessToken accessToken = null;


                // to check this example set you callback URL to empty. If your application 
                // callback URL is defined then URL generated by below code should be redirected
                // to callback URL, so when we set callback URL to empty it shows PIN CODE to access OAUTH

                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                while (null == accessToken) {
                    System.out.println("Open the following URL and grant access to your account:");
                    System.out.println(requestToken.getAuthorizationURL());
                    System.out.print("Enter the PIN(if available) and hit enter after you granted access.[PIN]:");
                    String pin = br.readLine();
                    try {
                        if (pin.length() > 0) {
                            accessToken = twitter.getOAuthAccessToken(requestToken, pin);
                        } else {
                            accessToken = twitter.getOAuthAccessToken(requestToken);
                        }
                    } catch (TwitterException te) {
                        if (401 == te.getStatusCode()) {
                            System.out.println("Unable to get the access token.");
                        } else {
                            te.printStackTrace();
                        }
                    }
                }
                System.out.println("Got access token.");
                UserAccessToken = accessToken.getToken();
                UserAccessSecret = accessToken.getTokenSecret();
                System.out.println("Access token: " + UserAccessToken);
                System.out.println("Access token secret: " + UserAccessSecret);


            } catch (IllegalStateException ie) {
                // access token is already available, or consumer key/secret is not set.
                if (!twitter.getAuthorization().isEnabled()) {
                    System.out.println("OAuth consumer key/secret is not set.");
                    System.exit(-1);
                }
            }

            //Fetching User Information
            //User Information from twitter.
            System.out.println("Connecting.... ");
            System.out.println("Twitter Connected To : ");

            User user = twitter.showUser(twitter.getId());
            System.out.print(user.getScreenName());
            if (user.getStatus() != null) {
                System.out.println("@" + user.getScreenName() + " - " + user.getStatus().getText());
            } else {
                // the user is protected
                System.out.println("@" + user.getScreenName());
            }


            // If you want simple post on status uncomment below code

            //System.out.println("Trying to simple post");      
            //Status status = twitter.updateStatus("Normal Post on Twitter Status");


            // Below code post with image and message           
            System.out.println("Now Try to upload Image");


            // Specify the image path in you system ( windows / unix / mac what ever)
            // ex(windows) : C:\imagepost.png
                    // ex(linux)   : /home/waqas/imagepost.png  
            String ImagePath = null;
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while (null == ImagePath) {
                System.out.println("Please Type Image Path: ");
                String path = br.readLine();
                if (path.length() > 0) {
                    ImagePath = path;
                } else {
                    path = "";
                    System.exit(-1);
                }
            }


            // type your message(status)
            String Message = null;
            br = new BufferedReader(new InputStreamReader(System.in));
            while (null == Message) {
                System.out.println("Please type your message: ");
                String msg = br.readLine();
                if (msg.length() > 0) {
                    Message = msg;
                } else {
                    Message = "";
                    System.exit(-1);
                }
            }


             try {

                    Configuration conf = new ConfigurationBuilder()
                                        .setOAuthConsumerKey("xxxxxx(OAuth Consumer Key)xxxxxx")
                                        .setOAuthConsumerSecret("xxxxxxxxxx(OAuth Consumer Secret)xxxxxxxx")
                                        .setOAuthAccessToken(UserAccessToken)
                                        .setOAuthAccessTokenSecret(UserAccessSecret).build();


                    OAuthAuthorization auth = new OAuthAuthorization(conf);
                    ImageUpload upload = new ImageUploadFactory(conf).getInstance(auth);

                    String url;
                    if (ImagePath.length()>0) {
                        url = upload.upload(new File(ImagePath), Message);
                    } else {
                        url = upload.upload(Message,null);
                    }
                    System.out.println("Successfully uploaded image to Twitpic at " + url);
                    System.exit(0);
                } catch (TwitterException te) {
                    te.printStackTrace();
                    System.out.println("Failed to upload the image: " + te.getMessage());
                    System.exit(-1);
                }

            System.exit(0);


        } catch (TwitterException te) {
            //te.printStackTrace();
            System.out.println("Failed to get timeline: " + te.getMessage());
            System.exit(-1);
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.out.println("Failed to read the system input.");
            System.exit(-1);
        }
    }

}
