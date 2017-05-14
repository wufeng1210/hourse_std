package com.hourse.web.model;

/**
 * Created by dell on 2017/5/14.
 */
public class ImageInfo {

    private int imageId;
    private int hourseId;
    private String imageUrl;
    private String imagePath;
    private String imageName;
    private String imageType;
    private String imageDesc;

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getHourseId() {
        return hourseId;
    }

    public void setHourseId(int hourseId) {
        this.hourseId = hourseId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public String getImageDesc() {
        return imageDesc;
    }

    public void setImageDesc(String imageDesc) {
        this.imageDesc = imageDesc;
    }
}