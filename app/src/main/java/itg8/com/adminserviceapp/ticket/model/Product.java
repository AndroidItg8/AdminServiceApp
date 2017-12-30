
package itg8.com.adminserviceapp.ticket.model;

import javax.annotation.Generated;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Product implements Parcelable
{

    @SerializedName("pkid")
    @Expose
    private int pkid;
    @SerializedName("brandid")
    @Expose
    private int brandid;
    @SerializedName("categoryid")
    @Expose
    private int categoryid;
    @SerializedName("ItemName")
    @Expose
    private String ItemName;
    @SerializedName("group_fkid")
    @Expose
    private int groupFkid;
    @SerializedName("Unit")
    @Expose
    private Object Unit;
    @SerializedName("openStock")
    @Expose
    private String openStock;
    @SerializedName("Salesprice")
    @Expose
    private double Salesprice;
    @SerializedName("purchaseprice")
    @Expose
    private double purchaseprice;
    @SerializedName("MRP")
    @Expose
    private double MRP;
    @SerializedName("min_salesprice")
    @Expose
    private double minSalesprice;
    @SerializedName("self_price")
    @Expose
    private Object selfPrice;
    @SerializedName("HSNCode")
    @Expose
    private Object HSNCode;
    @SerializedName("lastModifieddate")
    @Expose
    private Object lastModifieddate;
    @SerializedName("prodctImage")
    @Expose
    private Object prodctImage;
    @SerializedName("WarrantyInMonth")
    @Expose
    private Object WarrantyInMonth;
    @SerializedName("mid")
    @Expose
    private Object mid;
    @SerializedName("mdate")
    @Expose
    private Object mdate;
    public final static Parcelable.Creator<Product> CREATOR = new Creator<Product>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Product createFromParcel(Parcel in) {
            Product instance = new Product();
            instance.pkid = ((int) in.readValue((int.class.getClassLoader())));
            instance.brandid = ((int) in.readValue((int.class.getClassLoader())));
            instance.categoryid = ((int) in.readValue((int.class.getClassLoader())));
            instance.ItemName = ((String) in.readValue((String.class.getClassLoader())));
            instance.groupFkid = ((int) in.readValue((int.class.getClassLoader())));
            instance.Unit = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.openStock = ((String) in.readValue((String.class.getClassLoader())));
            instance.Salesprice = ((double) in.readValue((double.class.getClassLoader())));
            instance.purchaseprice = ((double) in.readValue((double.class.getClassLoader())));
            instance.MRP = ((double) in.readValue((double.class.getClassLoader())));
            instance.minSalesprice = ((double) in.readValue((double.class.getClassLoader())));
            instance.selfPrice = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.HSNCode = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.lastModifieddate = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.prodctImage = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.WarrantyInMonth = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.mid = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.mdate = ((Object) in.readValue((Object.class.getClassLoader())));
            return instance;
        }

        public Product[] newArray(int size) {
            return (new Product[size]);
        }

    }
    ;

    /**
     * 
     * @return
     *     The pkid
     */
    public int getPkid() {
        return pkid;
    }

    /**
     * 
     * @param pkid
     *     The pkid
     */
    public void setPkid(int pkid) {
        this.pkid = pkid;
    }

    /**
     * 
     * @return
     *     The brandid
     */
    public int getBrandid() {
        return brandid;
    }

    /**
     * 
     * @param brandid
     *     The brandid
     */
    public void setBrandid(int brandid) {
        this.brandid = brandid;
    }

    /**
     * 
     * @return
     *     The categoryid
     */
    public int getCategoryid() {
        return categoryid;
    }

    /**
     * 
     * @param categoryid
     *     The categoryid
     */
    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }

    /**
     * 
     * @return
     *     The ItemName
     */
    public String getItemName() {
        return ItemName;
    }

    /**
     * 
     * @param ItemName
     *     The ItemName
     */
    public void setItemName(String ItemName) {
        this.ItemName = ItemName;
    }

    /**
     * 
     * @return
     *     The groupFkid
     */
    public int getGroupFkid() {
        return groupFkid;
    }

    /**
     * 
     * @param groupFkid
     *     The group_fkid
     */
    public void setGroupFkid(int groupFkid) {
        this.groupFkid = groupFkid;
    }

    /**
     * 
     * @return
     *     The Unit
     */
    public Object getUnit() {
        return Unit;
    }

    /**
     * 
     * @param Unit
     *     The Unit
     */
    public void setUnit(Object Unit) {
        this.Unit = Unit;
    }

    /**
     * 
     * @return
     *     The openStock
     */
    public String getOpenStock() {
        return openStock;
    }

    /**
     * 
     * @param openStock
     *     The openStock
     */
    public void setOpenStock(String openStock) {
        this.openStock = openStock;
    }

    /**
     * 
     * @return
     *     The Salesprice
     */
    public double getSalesprice() {
        return Salesprice;
    }

    /**
     * 
     * @param Salesprice
     *     The Salesprice
     */
    public void setSalesprice(double Salesprice) {
        this.Salesprice = Salesprice;
    }

    /**
     * 
     * @return
     *     The purchaseprice
     */
    public double getPurchaseprice() {
        return purchaseprice;
    }

    /**
     * 
     * @param purchaseprice
     *     The purchaseprice
     */
    public void setPurchaseprice(double purchaseprice) {
        this.purchaseprice = purchaseprice;
    }

    /**
     * 
     * @return
     *     The MRP
     */
    public double getMRP() {
        return MRP;
    }

    /**
     * 
     * @param MRP
     *     The MRP
     */
    public void setMRP(double MRP) {
        this.MRP = MRP;
    }

    /**
     * 
     * @return
     *     The minSalesprice
     */
    public double getMinSalesprice() {
        return minSalesprice;
    }

    /**
     * 
     * @param minSalesprice
     *     The min_salesprice
     */
    public void setMinSalesprice(double minSalesprice) {
        this.minSalesprice = minSalesprice;
    }

    /**
     * 
     * @return
     *     The selfPrice
     */
    public Object getSelfPrice() {
        return selfPrice;
    }

    /**
     * 
     * @param selfPrice
     *     The self_price
     */
    public void setSelfPrice(Object selfPrice) {
        this.selfPrice = selfPrice;
    }

    /**
     * 
     * @return
     *     The HSNCode
     */
    public Object getHSNCode() {
        return HSNCode;
    }

    /**
     * 
     * @param HSNCode
     *     The HSNCode
     */
    public void setHSNCode(Object HSNCode) {
        this.HSNCode = HSNCode;
    }

    /**
     * 
     * @return
     *     The lastModifieddate
     */
    public Object getLastModifieddate() {
        return lastModifieddate;
    }

    /**
     * 
     * @param lastModifieddate
     *     The lastModifieddate
     */
    public void setLastModifieddate(Object lastModifieddate) {
        this.lastModifieddate = lastModifieddate;
    }

    /**
     * 
     * @return
     *     The prodctImage
     */
    public Object getProdctImage() {
        return prodctImage;
    }

    /**
     * 
     * @param prodctImage
     *     The prodctImage
     */
    public void setProdctImage(Object prodctImage) {
        this.prodctImage = prodctImage;
    }

    /**
     * 
     * @return
     *     The WarrantyInMonth
     */
    public Object getWarrantyInMonth() {
        return WarrantyInMonth;
    }

    /**
     * 
     * @param WarrantyInMonth
     *     The WarrantyInMonth
     */
    public void setWarrantyInMonth(Object WarrantyInMonth) {
        this.WarrantyInMonth = WarrantyInMonth;
    }

    /**
     * 
     * @return
     *     The mid
     */
    public Object getMid() {
        return mid;
    }

    /**
     * 
     * @param mid
     *     The mid
     */
    public void setMid(Object mid) {
        this.mid = mid;
    }

    /**
     * 
     * @return
     *     The mdate
     */
    public Object getMdate() {
        return mdate;
    }

    /**
     * 
     * @param mdate
     *     The mdate
     */
    public void setMdate(Object mdate) {
        this.mdate = mdate;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(pkid);
        dest.writeValue(brandid);
        dest.writeValue(categoryid);
        dest.writeValue(ItemName);
        dest.writeValue(groupFkid);
        dest.writeValue(Unit);
        dest.writeValue(openStock);
        dest.writeValue(Salesprice);
        dest.writeValue(purchaseprice);
        dest.writeValue(MRP);
        dest.writeValue(minSalesprice);
        dest.writeValue(selfPrice);
        dest.writeValue(HSNCode);
        dest.writeValue(lastModifieddate);
        dest.writeValue(prodctImage);
        dest.writeValue(WarrantyInMonth);
        dest.writeValue(mid);
        dest.writeValue(mdate);
    }

    public int describeContents() {
        return  0;
    }

}
