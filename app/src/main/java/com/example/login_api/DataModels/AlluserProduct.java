package com.example.login_api.DataModels;



import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class AlluserProduct {

@SerializedName("connection")
@Expose
private Integer connection;
@SerializedName("result")
@Expose
private Integer result;
@SerializedName("productdata")
@Expose
private List<Productdatum> productdata;

public Integer getConnection() {
return connection;
}

public void setConnection(Integer connection) {
this.connection = connection;
}

public Integer getResult() {
return result;
}

public void setResult(Integer result) {
this.result = result;
}

public List<Productdatum> getProductdata() {
return productdata;
}

public void setProductdata(List<Productdatum> productdata) {
this.productdata = productdata;
}

}