package com.hourse.web.model;

import java.util.List;

/**
 * Created by dell on 2017/4/10.
 */
public class Hourse {
    private int hourseId;
    private int userId;
    private String province;
    private String city;
    private String area;
    private String residentialQuarters;
    private int roomNum;
    private int toiletNum;
    private int hallNum;
    private int kitchenNum;
    private int monthly;
    private String parkingLot;
    private String rentingWay;
    private String brokerMobile;
    private String brokerCode;
    private String brokerName;
    private int areaCovered;
    private boolean refrigerator;
    private boolean heater;
    private boolean bed;
    private boolean desk;
    private boolean airConditioner;
    private String cabinet;
    private String state;
    private String description;
    private List<ImageInfo> imageInfoList;

    private String imageType;

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public List<ImageInfo> getImageInfoList() {
        return imageInfoList;
    }

    public void setImageInfoList(List<ImageInfo> imageInfoList) {
        this.imageInfoList = imageInfoList;
    }

    public int getHourseId() {
        return hourseId;
    }

    public void setHourseId(int hourseId) {
        this.hourseId = hourseId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getResidentialQuarters() {
        return residentialQuarters;
    }

    public void setResidentialQuarters(String residentialQuarters) {
        this.residentialQuarters = residentialQuarters;
    }

    public int getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
    }

    public int getToiletNum() {
        return toiletNum;
    }

    public void setToiletNum(int toiletNum) {
        this.toiletNum = toiletNum;
    }

    public int getHallNum() {
        return hallNum;
    }

    public void setHallNum(int hallNum) {
        this.hallNum = hallNum;
    }

    public int getKitchenNum() {
        return kitchenNum;
    }

    public void setKitchenNum(int kitchenNum) {
        this.kitchenNum = kitchenNum;
    }

    public int getMonthly() {
        return monthly;
    }

    public void setMonthly(int monthly) {
        this.monthly = monthly;
    }

    public String getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(String parkingLot) {
        this.parkingLot = parkingLot;
    }

    public String getRentingWay() {
        return rentingWay;
    }

    public void setRentingWay(String rentingWay) {
        this.rentingWay = rentingWay;
    }

    public String getBrokerMobile() {
        return brokerMobile;
    }

    public void setBrokerMobile(String brokerMobile) {
        this.brokerMobile = brokerMobile;
    }

    public String getBrokerCode() {
        return brokerCode;
    }

    public void setBrokerCode(String brokerCode) {
        this.brokerCode = brokerCode;
    }

    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }

    public int getAreaCovered() {
        return areaCovered;
    }

    public void setAreaCovered(int areaCovered) {
        this.areaCovered = areaCovered;
    }

    public boolean isRefrigerator() {
        return refrigerator;
    }

    public void setRefrigerator(boolean refrigerator) {
        this.refrigerator = refrigerator;
    }

    public boolean isHeater() {
        return heater;
    }

    public void setHeater(boolean heater) {
        this.heater = heater;
    }

    public boolean isBed() {
        return bed;
    }

    public void setBed(boolean bed) {
        this.bed = bed;
    }

    public boolean isDesk() {
        return desk;
    }

    public void setDesk(boolean desk) {
        this.desk = desk;
    }

    public boolean isAirConditioner() {
        return airConditioner;
    }

    public void setAirConditioner(boolean airConditioner) {
        this.airConditioner = airConditioner;
    }

    public String getCabinet() {
        return cabinet;
    }

    public void setCabinet(String cabinet) {
        this.cabinet = cabinet;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
