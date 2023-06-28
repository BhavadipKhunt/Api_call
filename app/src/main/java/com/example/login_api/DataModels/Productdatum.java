
package com.example.login_api.DataModels;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class Productdatum {

    @SerializedName("ID")
    @Expose
    private String id;
    @SerializedName("UID")
    @Expose
    private String uid;
    @SerializedName("PNAME")
    @Expose
    private String pname;
    @SerializedName("DESCRIPTION")
    @Expose
    private String description;
    @SerializedName("PRICE")
    @Expose
    private String price;
    @SerializedName("IMAGE")
    @Expose
    private String image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
