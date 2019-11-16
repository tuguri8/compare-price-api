package com.gachon.price.datatool.courait;

import java.util.List;

public class CouraitResponse {
    private Boolean success;
    private List<PurchaseInfo> category_list;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<PurchaseInfo> getCategory_list() {
        return category_list;
    }

    public void setCategory_list(List<PurchaseInfo> category_list) {
        this.category_list = category_list;
    }

    public static class PurchaseInfo {
        private Integer id;
        private String email;
        private String item_name;
        private Integer price;
        private String category;
        private String food_category;
        private String purchase_date;

        public String getItem_name() {
            return item_name;
        }

        public void setItem_name(String item_name) {
            this.item_name = item_name;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Integer getPrice() {
            return price;
        }

        public void setPrice(Integer price) {
            this.price = price;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getFood_category() {
            return food_category;
        }

        public void setFood_category(String food_category) {
            this.food_category = food_category;
        }

        public String getPurchase_date() {
            return purchase_date;
        }

        public void setPurchase_date(String purchase_date) {
            this.purchase_date = purchase_date;
        }
    }

}
