package com.hourse.web.model;

import java.util.List;

/**
 * Created by dell on 2017/4/10.
 */
public class Hourse {
    private String hourseId;
    private String userId;
    private String hourseAddr; // 房屋地址
    private String province;
    private String city;
    private String area;
    private String residentialQuarters; // 小区名称
    private int roomNum; // 房间数量
    private int toiletNum; // 卫生间数量
    private int hallNum; // 大厅数量
    private int kitchenNum; // 厨房数量
    private int monthly; // 月租
    private String parkingLot;
    private String rentingWay; // 租房方式
    private String brokerMobile;
    private String brokerCode;
    private String brokerName;
    private int areaCovered;
    private int squarePrice; // 价格每平方
    private String orientations; // 朝向
    private String floor; // 楼层
    private String furniture; // 家具
    private String cabinet;
    private String state; // 房屋信息审核状态
    private String description; // 房屋简介
    private String isLend; // 是否已出租
    private String traffic; // 交通
    private String near; // 附近
    private String fixtureType; //装修方式
    private String limitType; // 限制方式（限制男生还是女生还是不限制）

    public String getLimitType() {
        return limitType;
    }

    public void setLimitType(String limitType) {
        this.limitType = limitType;
    }

    public String getHourseAddr() {
        return hourseAddr;
    }

    public void setHourseAddr(String hourseAddr) {
        this.hourseAddr = hourseAddr;
    }

    public String getTraffic() {
        return traffic;
    }

    public void setTraffic(String traffic) {
        this.traffic = traffic;
    }

    public String getNear() {
        return near;
    }

    public void setNear(String near) {
        this.near = near;
    }

    public String getIsLend() {
        return isLend;
    }

    public void setIsLend(String isLend) {
        this.isLend = isLend;
    }

    private List<ImageInfo> imageInfoList;
    private String distance;
    private double longitude; // 房屋经度
    private double latitude; //房屋纬度
    private String hourseNum;

    public int getSquarePrice() {
        return squarePrice;
    }

    public void setSquarePrice(int squarePrice) {
        this.squarePrice = squarePrice;
    }

    public String getHourseNum() {
        return hourseNum;
    }

    public void setHourseNum(String hourseNum) {
        this.hourseNum = hourseNum;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

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

    public String getHourseId() {
        return hourseId;
    }

    public void setHourseId(String hourseId) {
        this.hourseId = hourseId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
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

    public String getOrientations() {
        return orientations;
    }

    public void setOrientations(String orientations) {
        this.orientations = orientations;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
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

    public String getFurniture() {
        return furniture;
    }

    public void setFurniture(String furniture) {
        this.furniture = furniture;
    }

    public String getFixtureType() {
        return fixtureType;
    }

    public void setFixtureType(String fixtureType) {
        this.fixtureType = fixtureType;
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
