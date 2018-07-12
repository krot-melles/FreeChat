package ua.ucoz.oldfriends.FreeChat.about;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import ua.ucoz.oldfriends.FreeChat.R;

/**
 * Created by medyo on 3/25/16.
 */
public class AboutPage {
    Context mContext;
    LayoutInflater mInflater;
    String mDescription;
    int mImage = 0;
    boolean mIsRTL = false;
    Typeface mCustomFont;
    private View mView;

    public AboutPage(Context context){
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mView = mInflater.inflate(R.layout.about_page, null);
    }

    public AboutPage setCustomFont(String fontName){
        mCustomFont = Typeface.createFromAsset(mContext.getAssets(), fontName);
        return this;
    }
    /*
        Add Email Element
     */
    public AboutPage addEmail(String email){
        Element emailElement = new Element();
        emailElement.setTitle(mContext.getString(R.string.about_contact_us));
        emailElement.setIcon(R.drawable.about_icon_email);
        emailElement.setColor(ContextCompat.getColor(mContext, R.color.about_item_icon_color));

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
        emailElement.setIntent(intent);

        addItem(emailElement);
        return this;
    }

    /*
        Add Facebook Element
     */
    public AboutPage addFacebook(String id){
        Element facebookElement = new Element();
        facebookElement.setTitle(mContext.getString(R.string.about_facebook));
        facebookElement.setIcon(R.drawable.about_icon_facebook);
        facebookElement.setColor(ContextCompat.getColor(mContext, R.color.facebook_color));
        facebookElement.setValue(id);

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);

        if (AboutPageUtils.isAppInstalled(mContext, "com.facebook.katana")){
            intent.setPackage("com.facebook.katana");
            int versionCode = 0;
            try {
                versionCode = mContext.getPackageManager().getPackageInfo("com.facebook.katana", 0).versionCode;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }

            if (versionCode >= 3002850) {
                Uri uri = Uri.parse("fb://facewebmodal/f?href=" + "http://facebook.com/" + id);
                intent.setData(uri);
            } else {
                Uri uri = Uri.parse("fb://page/"+id);
                intent.setData(uri);
            }
        }else{
            intent.setData( Uri.parse("http://facebook.com/" + id));
        }

        facebookElement.setIntent(intent);

        addItem(facebookElement);
        return this;
    }


    /*
        Add Twitter Element
     */
    public AboutPage addTwitter(String id){
        Element twitterElement = new Element();
        twitterElement.setTitle(mContext.getString(R.string.about_twitter));
        twitterElement.setIcon(R.drawable.about_icon_twitter);
        twitterElement.setColor(ContextCompat.getColor(mContext, R.color.twitter_color));
        twitterElement.setValue(id);

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);

        if (AboutPageUtils.isAppInstalled(mContext, "com.twitter.android")){
            intent.setPackage("com.twitter.android");
            intent.setData(Uri.parse(String.format("twitter://user?user_id=%s",id)));
        }else{
            intent.setData(Uri.parse(String.format("http://twitter.com/%s",id)));
        }

        twitterElement.setIntent(intent);
        addItem(twitterElement);
        return this;
    }

    /*
        Add Play store Element
     */
    public AboutPage addPlayStore(String id){
        Element playStoreElement = new Element();
        playStoreElement.setTitle(mContext.getString(R.string.about_play_store));
        playStoreElement.setIcon(R.drawable.about_icon_google_play);
        playStoreElement.setColor(ContextCompat.getColor(mContext, R.color.play_store_color));
        playStoreElement.setValue(id);

        Uri uri = Uri.parse("market://details?id=" + id);
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        playStoreElement.setIntent(goToMarket);

        addItem(playStoreElement);
        return this;
    }

    /*
        Add Youtube Element
     */
    public AboutPage addYoutube(String id){
        Element youtubeElement = new Element();
        youtubeElement.setTitle(mContext.getString(R.string.about_youtube));
        youtubeElement.setIcon(R.drawable.about_icon_youtube);
        youtubeElement.setColor(ContextCompat.getColor(mContext, R.color.youtube_color));
        youtubeElement.setValue(id);

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(String.format("http://youtube.com/channel/%s", id)));

        if (AboutPageUtils.isAppInstalled(mContext, "com.google.android.youtube")){
            intent.setPackage("com.google.android.youtube");
        }

        youtubeElement.setIntent(intent);
        addItem(youtubeElement);

        return this;
    }

    /*
        Add Instagram Element
     */
    public AboutPage addInstagram(String id){
        Element instagramElement = new Element();
        instagramElement.setTitle(mContext.getString(R.string.about_instagram));
        instagramElement.setIcon(R.drawable.about_icon_instagram);
        instagramElement.setColor(ContextCompat.getColor(mContext, R.color.instagram_color));
        instagramElement.setValue(id);

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://instagram.com/_u/"+id));

        if (AboutPageUtils.isAppInstalled(mContext, "com.instagram.android")){
            intent.setPackage("com.instagram.android");
        }

        instagramElement.setIntent(intent);
        addItem(instagramElement);

        return this;
    }

    /*
        Add GitHub Element
    */
    public AboutPage addGitHub(String id){
        Element gitHubElement = new Element();
        gitHubElement.setTitle(mContext.getString(R.string.about_github));
        gitHubElement.setIcon(R.drawable.about_icon_github);
        gitHubElement.setColor(ContextCompat.getColor(mContext, R.color.github_color));
        gitHubElement.setValue(id);

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse(String.format("https://github.com/%s", id)));

        gitHubElement.setIntent(intent);
        addItem(gitHubElement);

        return this;
    }

    /*
        Add Website Element
    */
    public AboutPage addWebsite(String url){
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "http://" + url;
        }
        Element websiteElement = new Element();
        websiteElement.setTitle(mContext.getString(R.string.about_website));
        websiteElement.setIcon(R.drawable.about_icon_link);
        websiteElement.setColor(ContextCompat.getColor(mContext, R.color.about_item_icon_color));
        websiteElement.setValue(url);

        Uri uri = Uri.parse(url);
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, uri);

        websiteElement.setIntent(browserIntent);
        addItem(websiteElement);

        return this;
    }

    public AboutPage addItem(Element element){
        LinearLayout wrapper = (LinearLayout) mView.findViewById(R.id.about_providers);
        wrapper.addView(createItem(element));
        wrapper.addView(getSeparator(), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mContext.getResources().getDimensionPixelSize(R.dimen.about_separator_height)));
        return this;
    }

    public AboutPage setImage(int resource){
        this.mImage = resource;
        return this;
    }

    public AboutPage addGroup(int name){

        TextView textView = new TextView(mContext);
        textView.setText(name);
        if (Build.VERSION.SDK_INT < 23) {
            textView.setTextAppearance(mContext, R.style.About_GroupTextAppearance);
        } else {
            textView.setTextAppearance(R.style.About_GroupTextAppearance);
        }

        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        if (mCustomFont != null){
            textView.setTypeface(mCustomFont);
        }

        int padding = mContext.getResources().getDimensionPixelSize(R.dimen.about_group_text_padding);
        textView.setPadding(padding,padding,padding,padding);

        if (mIsRTL){
            textView.setGravity(Gravity.RIGHT|Gravity.CENTER_VERTICAL);
            textParams.gravity = Gravity.RIGHT|Gravity.CENTER_VERTICAL;
        }else{
            textView.setGravity(Gravity.LEFT|Gravity.CENTER_VERTICAL);
            textParams.gravity = Gravity.LEFT|Gravity.CENTER_VERTICAL;
        }
        textView.setLayoutParams(textParams);

        ((LinearLayout)mView.findViewById(R.id.about_providers)).addView(textView);
        return this;
    }

    public AboutPage isRTL(boolean value){
        this.mIsRTL = value;
        return this;
    }

    public AboutPage setDescription(String description){
        this.mDescription = description;
        return this;
    }

    public View create(){
        TextView description = (TextView) mView.findViewById(R.id.description);
        ImageView image = (ImageView) mView.findViewById(R.id.image);
        if (mImage > 0){
            image.setImageResource(mImage);
        }

        if (!TextUtils.isEmpty(mDescription)){
            description.setText(mDescription);
        }

        if (mIsRTL){
            description.setGravity(Gravity.RIGHT);
        }else{
            description.setGravity(Gravity.LEFT);
        }

        if (mCustomFont != null){
            description.setTypeface(mCustomFont);
        }

        return mView;
    }

    private View createItem(final Element element){
        LinearLayout wrapper = new LinearLayout(mContext);
        wrapper.setOrientation(LinearLayout.HORIZONTAL);
        wrapper.setClickable(true);

        if (element.getIntent() != null){
            wrapper.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try{
                        mContext.startActivity(element.getIntent());
                    } catch (Exception e){}
                }
            });

        }

        TypedValue outValue = new TypedValue();
        mContext.getTheme().resolveAttribute(R.attr.selectableItemBackground, outValue, true);
        wrapper.setBackgroundResource(outValue.resourceId);

        int padding = mContext.getResources().getDimensionPixelSize(R.dimen.about_text_padding);
        wrapper.setPadding(padding,padding,padding,padding);
        LinearLayout.LayoutParams wrapperParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        wrapper.setLayoutParams(wrapperParams);


        TextView textView = new TextView(mContext);
        if (Build.VERSION.SDK_INT < 23) {
            textView.setTextAppearance(mContext, R.style.About_elementTextAppearance);
        } else {
            textView.setTextAppearance(R.style.About_elementTextAppearance);
        }

        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(textParams);
        if (mCustomFont != null){
            textView.setTypeface(mCustomFont);
        }

        ImageView iconView = null;

        if (element.getIcon() != null){
            iconView = new ImageView(mContext);
            int size = mContext.getResources().getDimensionPixelSize(R.dimen.about_icon_size);
            LinearLayout.LayoutParams iconParams = new LinearLayout.LayoutParams(size, size);
            iconView.setLayoutParams(iconParams);
            int iconPadding = mContext.getResources().getDimensionPixelSize(R.dimen.about_icon_padding);
            iconView.setPadding(iconPadding,0,iconPadding,0);
            iconView.setImageResource(element.getIcon());

            Drawable wrappedDrawable = DrawableCompat.wrap(iconView.getDrawable());
            wrappedDrawable = wrappedDrawable.mutate();

            if (element.getColor() != null){
                DrawableCompat.setTint(wrappedDrawable,element.getColor());
            }else{
                DrawableCompat.setTint(wrappedDrawable, ContextCompat.getColor(mContext, R.color.about_item_icon_color));
            }
        }else{
            int iconPadding = mContext.getResources().getDimensionPixelSize(R.dimen.about_icon_padding);
            textView.setPadding(iconPadding, iconPadding, iconPadding, iconPadding);
        }


        textView.setText(element.getTitle());


        if (mIsRTL){
            wrapper.setGravity(Gravity.RIGHT|Gravity.CENTER_VERTICAL);
            textParams.gravity = Gravity.RIGHT|Gravity.CENTER_VERTICAL;
            wrapper.addView(textView);
            if (element.getIcon() != null){
                wrapper.addView(iconView);
            }

        }else{
            wrapper.setGravity(Gravity.LEFT|Gravity.CENTER_VERTICAL);
            textParams.gravity = Gravity.LEFT|Gravity.CENTER_VERTICAL;
            if (element.getIcon() != null){
                wrapper.addView(iconView);
            }
            wrapper.addView(textView);
        }

        return wrapper;
    }

    private View getSeparator(){
        return mInflater.inflate(R.layout.about_page_separator, null);
    }
}

