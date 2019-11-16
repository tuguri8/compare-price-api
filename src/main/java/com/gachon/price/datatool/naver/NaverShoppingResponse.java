package com.gachon.price.datatool.naver;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NaverShoppingResponse {
    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }


    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Item {
        private String title;
        private String link;
        private String image;
        private String lprice;
        private String hprice;
        private String mallName;
        private String productId;
        private String productType;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getLprice() {
            return lprice;
        }

        public void setLprice(String lprice) {
            this.lprice = lprice;
        }

        public String getHprice() {
            return hprice;
        }

        public void setHprice(String hprice) {
            this.hprice = hprice;
        }

        public String getMallName() {
            return mallName;
        }

        public void setMallName(String mallName) {
            this.mallName = mallName;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getProductType() {
            return productType;
        }

        public void setProductType(String productType) {
            this.productType = productType;
        }
    }
}
