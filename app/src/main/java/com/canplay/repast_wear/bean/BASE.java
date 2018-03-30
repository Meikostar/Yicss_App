package com.canplay.repast_wear.bean;

/**
 * Created by mykar on 17/4/26.
 */
public class BASE {

//    businessId	long	商家id
//    tableNo	string	绑定桌号
//    bound	int	绑定设备情况 0:未绑定台桌，1：已绑定台桌
//    easeToken	string	环信密码
//            userId
//    "menuId": 1,
//            "menuName": "加水",
//            "imgUrl": "http://oud4e96lb.bkt.clouddn.com/%E5%8A%A0%E6%B0%B4.png"
//    "wxPay": "weixin：//wxpay/bizpayurlXXXXX",
//            "tradeNo":"1014147312596811777"
//    "userId":2,
//            "gameFree":0,
//            "qrPayNum":0
//    "gameId":1,
//            "linkUrl":"https://www.baidu.com/",
//            "interval":3000000,
//            "type":0,
//            "money":0.01,
//            "state":0
//    "uploadUrl":"http://oud4e96lb.bkt.clouddn.com/AA",
//            "version":"1.1"
    public boolean result;
    public long businessId;
    public long deviceId;
    public long gameId;
    public double money;
    public long interval;
    public String uploadUrl;

    public String tableNo;

    public int bound;
    public int state;

    public int tableId;
    public int status;
    public int menuId;

    public int gameFree;
    public String easeToken;
    public String gameName;
    public int qrPayNum;
    public String linkUrl;
    public String areaId;
    public String qrCode;
    public String menuName;
    public String imgUrl;
    public String wxPay;
    public String tradeNo;
    public long sleepTime;
    public String userId;
    public String version;

    public int type;
    public String thirdSupport;
    public String appName;
    public String sensitiveUrl;
    public String sensitiveVersion;
    public String apkVersion;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(long businessId) {
        this.businessId = businessId;
    }

    public long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public long getInterval() {
        return interval;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getUploadUrl() {
        return uploadUrl;
    }

    public void setUploadUrl(String uploadUrl) {
        this.uploadUrl = uploadUrl;
    }

    public String getTableNo() {
        return tableNo;
    }

    public void setTableNo(String tableNo) {
        this.tableNo = tableNo;
    }

    public int getBound() {
        return bound;
    }

    public void setBound(int bound) {
        this.bound = bound;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getGameFree() {
        return gameFree;
    }

    public void setGameFree(int gameFree) {
        this.gameFree = gameFree;
    }

    public String getEaseToken() {
        return easeToken;
    }

    public void setEaseToken(String easeToken) {
        this.easeToken = easeToken;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public int getQrPayNum() {
        return qrPayNum;
    }

    public void setQrPayNum(int qrPayNum) {
        this.qrPayNum = qrPayNum;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getWxPay() {
        return wxPay;
    }

    public void setWxPay(String wxPay) {
        this.wxPay = wxPay;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public long getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(long sleepTime) {
        this.sleepTime = sleepTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getThirdSupport() {
        return thirdSupport;
    }

    public void setThirdSupport(String thirdSupport) {
        this.thirdSupport = thirdSupport;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getSensitiveUrl() {
        return sensitiveUrl;
    }

    public void setSensitiveUrl(String sensitiveUrl) {
        this.sensitiveUrl = sensitiveUrl;
    }

    public String getSensitiveVersion() {
        return sensitiveVersion;
    }

    public void setSensitiveVersion(String sensitiveVersion) {
        this.sensitiveVersion = sensitiveVersion;
    }

    public String getApkVersion() {
        return apkVersion;
    }

    public void setApkVersion(String apkVersion) {
        this.apkVersion = apkVersion;
    }
}
