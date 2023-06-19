package com.example.login_api;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Userdata {
        @SerializedName("ID")
        @Expose
        private String id;
        @SerializedName("NAME")
        @Expose
        private String name;
        @SerializedName("EMAIL")
        @Expose
        private String email;
        @SerializedName("PASSWORD")
        @Expose
        private String password;
        @SerializedName("CONFIRMPASSWORD")
        @Expose
        private String confirmpassword;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getConfirmpassword() {
            return confirmpassword;
        }

        public void setConfirmpassword(String confirmpassword) {
            this.confirmpassword = confirmpassword;
        }
}
