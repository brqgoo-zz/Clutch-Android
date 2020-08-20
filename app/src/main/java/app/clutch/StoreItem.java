package app.clutch;

public class StoreItem {
    private String name;
    private String imageUrl;
    private String price_amount;
    private String price_currency;

    public StoreItem (String name, String imageUrl, String price_amount, String price_currency){
        this.name = name;
        this.imageUrl = imageUrl;
        this.price_currency = price_currency;
        this.price_amount = price_amount;
    }

    public String getPrice() {
        if(price_currency=="USD"){
            return "$ " + price_amount;
        }
        return name;

    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "StoreItem{" +
                "name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
