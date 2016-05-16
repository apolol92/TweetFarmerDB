package farmer_window.scenes.panels;

import custom_tweet.Tweet;
import custom_tweet.TweetHistoryReceiver;
import farmer_file_manager.TwitterConfigData;
import farmer_window.FarmerWindow;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import main.Program;

import java.util.ArrayList;

/**
 * Created by apolol92 on 11.05.2016.
 * Panel for showing all Tweets.. only one at once..
 */
public class TweetViewPanel extends GridPane {
    private Button btIgnore;
    private ArrayList<Tweet> currentTweets;
    private TweetHistoryReceiver tweetHistoryReceiver;
    /**
     * Name of current Farmer
     */
    private String farmername;
    /**
     * Place Holder till Twitter-Pofil-Image
     */
    private Button btImageTest;
    /**
     * ImageView for User Profil-Image
     */
    private ImageView ivProfil;
    /**
     * Label with Username
     */
    private Label lbUsername;
    /**
     * Label Screenname
     */
    private Label lbScreenname;
    /**
     * Label with Date
     */
    private Label lbDate;
    /**
     * Label with Tweet Text
     */
    private Label lbTweetText;

    /**
     * Cronstructor for building TweetViewPanel
     */
    public TweetViewPanel(String farmername) {
        this.farmername = farmername;
        //TODO: REAL HASHTAGS
        String[] hashtags = new String[]{"adidas","nike"};
        //TODO: REAL CLASSES
        String[] classes = new String[]{"positive","negative","neutral"};
        //Setup TweetHistoryReceiver
        this.tweetHistoryReceiver = new TweetHistoryReceiver(TwitterConfigData.getTwitterConfigData().getTwitterTwitterConsumerKey(),
                TwitterConfigData.getTwitterConfigData().getTwitterTwitterConsumerSecret(), TwitterConfigData.getTwitterConfigData().getTwitterAccessToken(),
                TwitterConfigData.getTwitterConfigData().getTwitterAccessTokenSecret(),hashtags);
        //Get Current Tweets
        this.currentTweets = this.tweetHistoryReceiver.getNewTweets();
        //Image img = new Image(getClass().getResource("dummy_profil.png").toExternalForm());
        //this.ivProfil = new ImageView(Program.class.getResource("dummy_profil.png").toExternalForm());
        //this.ivProfil.setImage(img);
        this.btImageTest = new Button("Test\nImage");
        this.btImageTest.setMinWidth(100);
        this.btImageTest.setMinHeight(100);
        this.lbUsername = new Label("Robert");
        this.lbScreenname = new Label("@r87");
        this.lbDate = new Label("02-23.2016");
        this.lbTweetText = new Label(this.currentTweets.get(0).getText());
        //Add to Grid
        this.add(this.btImageTest,0,0,1,3);
        this.add(this.lbUsername,1,0,1,1);
        this.add(this.lbScreenname,2,0,1,1);
        this.add(this.lbDate,3,0,1,1);
        this.add(this.lbTweetText,1,1,3,3);
        //Add real text..
        lbUsername.setText(currentTweets.get(0).getUsername());
        lbScreenname.setText(currentTweets.get(0).getScreenname());
        lbDate.setText(currentTweets.get(0).getDate());
        lbTweetText.setText(currentTweets.get(0).getText());
        //Add Buttons
        int i = 0;
        for(String c : classes) {
            Button btClasses = new Button(c);
            btClasses.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    //TODO: ADD TWEET TO DATABASE
                    //TODO: ADD TWEET TO LOCAL STORAGE
                    //Remove tweet from currentTweets
                    if(currentTweets.size()>1) {
                        currentTweets.remove(0);
                    }
                    else {
                        currentTweets.remove(0);
                        currentTweets = tweetHistoryReceiver.getNewTweets();
                    }
                    //Reload TweetViewPanel
                    lbUsername.setText(currentTweets.get(0).getUsername());
                    lbScreenname.setText(currentTweets.get(0).getScreenname());
                    lbDate.setText(currentTweets.get(0).getDate());
                    lbTweetText.setText(currentTweets.get(0).getText());
                }
            });
            add(btClasses,i,3);
            i++;
        }
        //Add Ignore Button
        this.btIgnore = new Button("Ignore");
        this.btIgnore.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //NO STORAGE
                //Remove tweet from currentTweets
                if(currentTweets.size()>1) {
                    currentTweets.remove(0);
                }
                else {
                    currentTweets.remove(0);
                    currentTweets = tweetHistoryReceiver.getNewTweets();
                }
                //Reload TweetViewPanel
                lbUsername.setText(currentTweets.get(0).getUsername());
                lbScreenname.setText(currentTweets.get(0).getScreenname());
                lbDate.setText(currentTweets.get(0).getDate());
                lbTweetText.setText(currentTweets.get(0).getText());
            }
        });
        add(this.btIgnore,classes.length,3);

    }
}