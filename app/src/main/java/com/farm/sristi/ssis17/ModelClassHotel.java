package com.farm.sristi.ssis17;

/**
 * Created by pc on 6/1/2017.
 */

public class ModelClassHotel {
    String price,image,title,phone,image1,image2,image3,image4,image5;

    public ModelClassHotel(String price, String image, String title, String phone,String image1,String image2,String image3,String image4,String image5) {
        this.price = price;
        this.image = image;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.image4 = image4;
        this.image5 = image5;
        this.title = title;
        this.phone = phone;
    }
    public ModelClassHotel()
    {}

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

    public String getImage1() {
        return image1;
    }
    public void setImage1(String image1) {
        this.image1 = image1;
    }
    public String getImage2() {
        return image2;
    }
    public void setImage2(String image2) {
        this.image2 = image2;
    }
    public String getImage3() {
        return image3;
    }
    public void setImage3(String image3) {
        this.image3 = image3;
    }
    public String getImage4() {
        return image4;
    }
    public void setImage4(String image4) {
        this.image4 = image4;
    }
    public String getImage5() {
        return image5;
    }
    public void setImage5(String image5) {
        this.image5 = image5;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
}


