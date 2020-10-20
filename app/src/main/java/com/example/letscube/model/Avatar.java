package model;

import com.google.gson.annotations.SerializedName;

public class Avatar {
    @SerializedName("url") private String url;
    @SerializedName("thumb_url") private String thumbUrl;
    @SerializedName("is_default") private boolean isDefault;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }
}
