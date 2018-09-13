package com.example.dowkk.apply11streetapi.search;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Product {

    //Search
    private String productCode;
    private String productName;
    private String productImage;
    private String productDetailUrl;
    private String productPrice;
    private String seller;
    private String rating;
    private String salePrice;
    private String delivery;
    private String reviewCount;
    private String buySatisfy;
    private String benefit;
    private String id;

    //Info
    private String price;
    private String lowestPrice;
    private String basicImage;  // 기본 이미지 정보
    private String addImage1;   // 추가 이미지1 정보
    private String addImage2;   // 추가 이미지2 정보
    private String addImage3;   // 추가 이미지3 정보
    private String imageL300;   // 이미지(사이즈 300*300~ 1000*1000) 정보
    private String point;       // 구매 시 제공되는 포인트
    private String chip;        // 칩 정보
    private String installment; // 무이자 할부 정보
    private String shipFee;              //배송비 정보
    private String sellSatisfaction;    // 셀러의 판매 만족도 정보
    private String sellGrade;           //셀러의 판매 등급


    @Override
    public String toString() {
        return "Product [productCode=" + productCode + ", productName=" + productName + ", productImage=" + productImage
                + ", productDetailUrl=" + productDetailUrl + ", productPrice=" + productPrice + ", seller=" + seller
                + ", rating=" + rating + ", salePrice=" + salePrice + ", delivery=" + delivery + ", reviewCount="
                + reviewCount + ", buySatisfy=" + buySatisfy + ", benefit=" + benefit + "]";
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }



    public String getProductDetailUrl() {
        return productDetailUrl;
    }

    public void setProductDetailUrl(String productDetailUrl) {
        this.productDetailUrl = productDetailUrl;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(String reviewCount) {
        this.reviewCount = reviewCount;
    }

    public String getBuySatisfy() {
        return buySatisfy;
    }

    public void setBuySatisfy(String buySatisfy) {
        this.buySatisfy = buySatisfy;
    }

    public String getBenefit() {
        return benefit;
    }

    public void setBenefit(String benefit) {
        this.benefit = benefit;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLowestPrice() {
        return lowestPrice;
    }

    public void setLowestPrice(String lowestPrice) {
        this.lowestPrice = lowestPrice;
    }

    public String getBasicImage() {
        return basicImage;
    }

    public void setBasicImage(String basicImage) {
        this.basicImage = basicImage;
    }

    public String getAddImage1() {
        return addImage1;
    }

    public void setAddImage1(String addImage1) {
        this.addImage1 = addImage1;
    }

    public String getAddImage2() {
        return addImage2;
    }

    public void setAddImage2(String addImage2) {
        this.addImage2 = addImage2;
    }

    public String getAddImage3() {
        return addImage3;
    }

    public void setAddImage3(String addImage3) {
        this.addImage3 = addImage3;
    }

    public String getImageL300() {
        return imageL300;
    }

    public void setImageL300(String imageL300) {
        this.imageL300 = imageL300;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getChip() {
        return chip;
    }

    public void setChip(String chip) {
        this.chip = chip;
    }

    public String getInstallment() {
        return installment;
    }

    public void setInstallment(String installment) {
        this.installment = installment;
    }

    public String getShipFee() {
        return shipFee;
    }

    public void setShipFee(String shipFee) {
        this.shipFee = shipFee;
    }

    public String getSellSatisfaction() {
        return sellSatisfaction;
    }

    public void setSellSatisfaction(String sellSatisfaction) {
        this.sellSatisfaction = sellSatisfaction;
    }

    public String getSellGrade() {
        return sellGrade;
    }

    public void setSellGrade(String sellGrade) {
        this.sellGrade = sellGrade;
    }
}
