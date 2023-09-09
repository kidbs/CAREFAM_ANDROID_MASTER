package com.jgy.animal.Adapter;


public class ViewItemData {
//    private String placeName;
//    private String placeAddr1;
//    private String placeAddr2;
//    private String placeInfo;
//    private String placeOtherInfo;
//
//
//    // 1 - 팩토리패턴
////    static ViewItemData create(String placeInfo) {
////        return new ViewItemData(null, null, null, null, placeInfo);
////    }
////    static ViewItemData create(String placeName, String placeAddr1, String placeAddr2, String placeOtherInfo) {
////        return new ViewItemData(placeName, placeAddr1, placeAddr2, placeOtherInfo, null);
////    }
////
////    private ViewItemData(String placeName, String placeAddr1, String placeAddr2, String placeOtherInfo, String placeInfo) {
////        this.placeName = placeName;
////        this.placeAddr1 = placeAddr1;
////        this.placeAddr2 = placeAddr2;
////        this.placeOtherInfo = placeOtherInfo;
////        this.placeInfo = placeInfo;
////    }
//
//    // 2
//    public ViewItemData(String placeName, String placeAddr1, String placeAddr2, String placeOtherInfo) {
//        init(placeName, placeAddr1, placeAddr2, placeOtherInfo,  null);
//    }
//
//    public ViewItemData(String placeInfo) {
//        init(null, null, null, null, placeInfo);
//    }
//
//    public ViewItemData(String placeName, String placeAddr1, String placeAddr2, String placeOtherInfo, String placeInfo) {
//        init(placeName, placeAddr1, placeAddr2, placeOtherInfo, placeInfo);
//    }
//
//    private void init(String placeName, String placeAddr1, String placeAddr2, String placeOtherInfo ,String placeInfo) {
//        this.placeName = placeName;
//        this.placeAddr1 = placeAddr1;
//        this.placeAddr2 = placeAddr2;
//        this.placeInfo = placeInfo;
//        this.placeOtherInfo = placeOtherInfo;
//
//    }
//
//
//
//
//    public String getPlaceInfo() {
//        return placeInfo;
//    }
//
//    public void setPlaceInfo(String placeInfo) {
//        this.placeInfo = placeInfo;
//    }
//
//    public String getPlaceName() {
//        return placeName;
//    }
//
//    public void setPlaceName(String placeName) {
//        this.placeName = placeName;
//    }
//
//    public String getPlaceAddr1() {
//        return placeAddr1;
//    }
//
//    public void setPlaceAddr1(String placeAddr1) {
//        this.placeAddr1 = placeAddr1;
//    }
//
//    public String getPlaceAddr2() {
//        return placeAddr2;
//    }
//
//    public void setPlaceAddr2(String placeAddr2) {
//        this.placeAddr2 = placeAddr2;
//    }
//
//    public String getPlaceOtherInfo() {
//        return placeOtherInfo;
//    }
//
//    public void setPlaceOtherInfo(String placeOtherInfo) {
//        this.placeOtherInfo = placeOtherInfo;
//    }
//
//
//    private String address;
//    private String phoneNumber;
//    private String parking;
//    private String operatingTime;
//    private String website;
//    private String closedDays;
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
//
//    public String getParking() {
//        return parking;
//    }
//
//    public void setParking(String parking) {
//        this.parking = parking;
//    }
//
//    public String getOperatingTime() {
//        return operatingTime;
//    }
//
//    public void setOperatingTime(String operatingTime) {
//        this.operatingTime = operatingTime;
//    }
//
//    public String getWebsite() {
//        return website;
//    }
//
//    public void setWebsite(String website) {
//        this.website = website;
//    }
//
//    public String getClosedDays() {
//        return closedDays;
//    }
//
//    public void setClosedDays(String closedDays) {
//        this.closedDays = closedDays;
//    }

    private String placeName;
    private String placeAddr1;
    private String placeAddr2;
    private String placeInfo;
    private String placeOtherInfo;

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String phoneNumber;
    private String parking;
    private String operatingTime;
    private String website;
    private String closedDays;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getParking() {
        return parking;
    }

    public void setParking(String parking) {
        this.parking = parking;
    }

    public String getOperatingTime() {
        return operatingTime;
    }

    public void setOperatingTime(String operatingTime) {
        this.operatingTime = operatingTime;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getClosedDays() {
        return closedDays;
    }

    public void setClosedDays(String closedDays) {
        this.closedDays = closedDays;
    }
// 1 - 팩토리패턴
//    static ViewItemData create(String placeInfo) {
//        return new ViewItemData(null, null, null, null, placeInfo);
//    }
//    static ViewItemData create(String placeName, String placeAddr1, String placeAddr2, String placeOtherInfo) {
//        return new ViewItemData(placeName, placeAddr1, placeAddr2, placeOtherInfo, null);
//    }
//
//    private ViewItemData(String placeName, String placeAddr1, String placeAddr2, String placeOtherInfo, String placeInfo) {
//        this.placeName = placeName;
//        this.placeAddr1 = placeAddr1;
//        this.placeAddr2 = placeAddr2;
//        this.placeOtherInfo = placeOtherInfo;
//        this.placeInfo = placeInfo;
//    }

    // 2
    public ViewItemData(String placeName, String placeAddr1, String placeAddr2, String placeOtherInfo) {
        init(placeName, placeAddr1, placeAddr2, placeOtherInfo,  null);
    }

    public ViewItemData(String placeInfo) {
        init(null, null, null, null, placeInfo);
    }

    public ViewItemData(String placeName, String placeAddr1, String placeAddr2, String placeOtherInfo, String placeInfo) {
        init(placeName, placeAddr1, placeAddr2, placeOtherInfo, placeInfo);
    }

    private void init(String placeName, String placeAddr1, String placeAddr2, String placeOtherInfo ,String placeInfo) {
        this.placeName = placeName;
        this.placeAddr1 = placeAddr1;
        this.placeAddr2 = placeAddr2;
        this.placeInfo = placeInfo;
        this.placeOtherInfo = placeOtherInfo;

    }




    public String getPlaceInfo() {
        return placeInfo;
    }

    public void setPlaceInfo(String placeInfo) {
        this.placeInfo = placeInfo;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPlaceAddr1() {
        return placeAddr1;
    }

    public void setPlaceAddr1(String placeAddr1) {
        this.placeAddr1 = placeAddr1;
    }

    public String getPlaceAddr2() {
        return placeAddr2;
    }

    public void setPlaceAddr2(String placeAddr2) {
        this.placeAddr2 = placeAddr2;
    }

    public String getPlaceOtherInfo() {
        return placeOtherInfo;
    }

    public void setPlaceOtherInfo(String placeOtherInfo) {
        this.placeOtherInfo = placeOtherInfo;
    }
}
