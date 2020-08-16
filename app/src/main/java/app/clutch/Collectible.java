package app.clutch;

public class Collectible {
    private String name;
    private String imageUrl;
    private String imageHexStr;

    public Collectible (String name, String imageUrl, String imageHexStr){
        this.name = name;
        this.imageUrl = imageUrl;
        this.imageHexStr = imageHexStr;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String imageHexStr() {
        return imageHexStr;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setImageHexStr(String imageHexStr) {
        this.imageHexStr = imageHexStr;
    }

    @Override
    public String toString() {
        return "Collectible{" +
                "name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", imageHexStr='" + imageHexStr + '\'' +
                '}';
    }
}
