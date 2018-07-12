package ua.ucoz.oldfriends.FreeChat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import ua.ucoz.oldfriends.FreeChat.about.AboutPage;
import ua.ucoz.oldfriends.FreeChat.about.Element;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

  //      Element versionElement = new Element();
 //       versionElement.setTitle("Version 6.2");

//        Element adsElement = new Element();
//        adsElement.setTitle("Advertise with us");

        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setImage(R.drawable.dummy_image)
 //               .addItem(versionElement)
//                .addItem(adsElement)
                .addGroup(R.string.connect_group)
                .addEmail("melles.android@gmail.com")
                .addWebsite("http://oldfriends.ucoz.ua/")
//                .addFacebook("the.medy")
//                .addTwitter("medyo80")
                .addYoutube("UCpJ_TOtSt9aPxlfK-9iLkxQ")
//                .addPlayStore("com.ideashower.readitlater.pro")
//                .addInstagram("medyo80")
                .addGitHub("krot-melles/FreeChat")
                .create();

        setContentView(aboutPage);
    }
}
