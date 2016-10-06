package xyz.belvi.intentmanip.IntentUtils.Models;

import java.util.List;

/**
 * Created by zone2 on 10/3/16.
 */

public class ResolveCategory {
    List<ResolveIntent> resolveIntents;
    String categoryName;
    int orderId;

    public ResolveCategory(List<ResolveIntent> resolveIntents, String categoryName, int orderId) {
        this.resolveIntents = resolveIntents;
        this.categoryName = categoryName;
        this.orderId = orderId;

    }

    public List<ResolveIntent> getResolveIntents() {
        return this.resolveIntents;
    }



    public void setResolveIntents(List<ResolveIntent> resolveIntents) {
        this.resolveIntents = resolveIntents;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getOrderId() {
        return this.orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
