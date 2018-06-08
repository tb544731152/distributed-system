package com.itmuch.cloud.study.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "oo_public_num")
public class PublicNum {
    private static final long serialVersionUID = 1L;

    private String id;
    private String serviceName;
    private String shopId;
    private String accessToken;
    private String appId;
    private String appSecret;
    private String shopKey;
    private String partnerKey;
    private String path;
    private String urlPath;
    private String wxNum;
    private String jsApiTicket;
    private String deployment;
    private String isDelete="0";  //是否删除 0未删除1删除

    @Id
    @GenericGenerator(name="systemUUID",strategy="uuid")
    @GeneratedValue(generator="systemUUID")
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    @Column(name="serviceName")
    public String getServiceName() {
        return serviceName;
    }
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    @Column(name="shopId")
    public String getShopId() {
        return shopId;
    }
    public void setShopId(String shopId) {
        this.shopId = shopId;
    }
    @Column(name="appId")
    public String getAppId() {
        return appId;
    }
    public void setAppId(String appId) {
        this.appId = appId;
    }
    @Column(name="appSecret")
    public String getAppSecret() {
        return appSecret;
    }
    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    @Column(name="urlPath")
    public String getUrlPath() {
        return urlPath;
    }
    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }
    @Column(name="shopKey")
    public String getShopKey() {
        return shopKey;
    }
    public void setShopKey(String shopKey) {
        this.shopKey = shopKey;
    }
    @Column(name="partnerKey")
    public String getPartnerKey() {
        return partnerKey;
    }
    public void setPartnerKey(String partnerKey) {
        this.partnerKey = partnerKey;
    }
    @Column(name="accessToken")
    public String getAccessToken() {
        return accessToken;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    public String getWxNum() {
        return wxNum;
    }
    public void setWxNum(String wxNum) {
        this.wxNum = wxNum;
    }
    @Column(name="jsApiTicket")
    public String getJsApiTicket() {
        return jsApiTicket;
    }
    public void setJsApiTicket(String jsApiTicket) {
        this.jsApiTicket = jsApiTicket;
    }
    public String getDeployment() {
        return deployment;
    }
    public void setDeployment(String deployment) {
        this.deployment = deployment;
    }
    @Column(name="isDelete",length=5)
    public String getIsDelete() {
        return isDelete;
    }
    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

}
