/**
 * @author andre
 *
 */
package com.google.code.magja.model.product;

import com.google.code.magja.model.BaseMagentoModel;
import com.google.code.magja.model.category.Category;

import java.security.InvalidParameterException;
import java.util.*;

public class Product extends BaseMagentoModel {

    public static int TAXABLE = 2;
    public static int NON_TAXABLE = 0;

    private static final long serialVersionUID = -8639442403416399439L;

    private String sku;

    private ProductAttributeSet attributeSet;

    private ProductType type = ProductType.SIMPLE;

    private String name;

    private Double price;

    private Double cost;

    private String shortDescription;

    private String description;

    private Boolean enabled;

    private Double weight;

    private Integer taxClassId = 0;

    private Integer[] websites;

    private List<Category> categories = new ArrayList<Category>();

    private Double qty;

    private Boolean inStock;

    private List<ProductMedia> medias;

    private Set<ProductLink> links;

    private String metaDescription;

    private Boolean googleCheckout;

    private Visibility visibility = Visibility.CATALOG_SEARCH;

    // for use with CONFIGURABLE products

    private Map<String, Map<String, Object>> configurableProductsData;

    private List<ConfigurableProductData> configurableSubProducts;

    private List<ConfigurableAttributeData> configurableAttributesData;

    protected void loadMappings() {
        this.mapping = new Properties();
        mapping.setProperty("product_id", "id");
        mapping.setProperty("sku", "sku");
        mapping.setProperty("name", "name");
        mapping.setProperty("cost", "cost");
        mapping.setProperty("status", "enabled");
        mapping.setProperty("price", "price");
        mapping.setProperty("short_description", "shortDescription");
        mapping.setProperty("description", "description");
        mapping.setProperty("weight", "weight");
        mapping.setProperty("tax_class_id", "taxClassId");
        mapping.setProperty("meta_description", "metaDescription");
        mapping.setProperty("enable_googlecheckout", "googleCheckout");
        mapping.setProperty("configurable_products_data", "configurableProductsData");
    }

    /*
    * (non-Javadoc)
    *
    * @see com.google.code.magja.model.BaseMagentoModel#serializeToApi()
    */
    @Override
    public Object serializeToApi() {

        Integer attributeId;
        // set the attributSet
        if (attributeSet != null) {
            if (attributeSet.getId() != null) {
                attributeId = attributeSet.getId();
            } else {
                attributeId = ProductAttributeSet
                        .getDefaultProductAttributeSet().getId();
            }
        } else {
            attributeId = ProductAttributeSet
                    .getDefaultProductAttributeSet().getId();
        }

        if (this.visibility != null)
            set("visibility", this.visibility.getValue());

        List<Object> newProduct = new LinkedList<Object>();
        newProduct.add(type.getCode());
        newProduct.add(attributeId);
        newProduct.add(sku);

        Map<String, Object> props = getAllProperties();

        newProduct.add(props);

        return newProduct;
    }

    /**
     * @return the sku
     */
    public String getSku() {
        return sku;
    }

    /**
     * @param sku the sku to set
     */
    public void setSku(String sku) {
        this.sku = sku;
    }

    /**
     * @return the attributeSet
     */
    public ProductAttributeSet getAttributeSet() {
        return attributeSet;
    }

    /**
     * @param attributeSet the attributeSet to set
     */
    public void setAttributeSet(ProductAttributeSet attributeSet) {
        this.attributeSet = attributeSet;
    }

    /**
     * @return the type
     */
    public ProductType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(ProductType type) {
        this.type = type;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * @return the shortDescription
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * @param shortDescription the shortDescription to set
     */
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the enabled
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * @param enabled the enabled to set
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return the weight
     */
    public Double getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(Double weight) {
        this.weight = weight;
    }

    /**
     * @return the taxClassId
     */
    public Integer getTaxClassId() {
        return taxClassId;
    }

    /**
     * @param taxClassId the taxClassId to set
     */
    public void setTaxClassId(Integer taxClassId) {
        this.taxClassId = taxClassId;
    }

    /**
     * @return the websites
     */
    public Integer[] getWebsites() {
        return websites;
    }

    /**
     * @param websites the websites to set
     */
    public void setWebsites(Integer[] websites) {
        // FIXME: this.websites = websites;

        String[] array = new String[websites.length];
        for (int i = 0; i < websites.length; i++) {
            array[i] = websites[i] + "";
        }
        set("websites", array);
    }

    /**
     * @return the categories
     */
    public List<Category> getCategories() {
        return categories;
    }

    /**
     * @param categories the categories to set
     */
    public void setCategories(List<Category> categories) {
        this.categories = categories;

        String[] array = new String[categories.size()];
        for (int i = 0; i < categories.size(); i++) {
            array[i] = categories.get(i).getId() + "";
        }
        set("categories", array);
    }

    /**
     * @return the cost
     */
    public Double getCost() {
        return cost;
    }

    /**
     * @param cost the cost to set
     */
    public void setCost(Double cost) {
        this.cost = cost;
    }

    /**
     * @return the qty
     */
    public Double getQty() {
        return qty;
    }

    /**
     * @param qty the qty to set
     */
    public void setQty(Double qty) {
        this.qty = qty;
    }

    /**
     * @return the inStock
     */
    public Boolean getInStock() {
        return inStock;
    }

    /**
     * @param inStock the inStock to set
     */
    public void setInStock(Boolean inStock) {
        this.inStock = inStock;
    }

    /**
     * @return the medias
     */
    public List<ProductMedia> getMedias() {
        return medias;
    }

    /**
     * @param medias the medias to set
     */
    public void setMedias(List<ProductMedia> medias) {
        this.medias = new ArrayList<ProductMedia>();
        for (ProductMedia media : medias)
            addMedia(media);
    }

    /**
     * @param media the media to add
     */
    public void addMedia(ProductMedia media) {
        if (media == null)
            throw new InvalidParameterException(
                    "the product media cannot be null");
        if (this.medias == null)
            this.medias = new ArrayList<ProductMedia>();
        media.setProduct(this);
        this.medias.add(media);
    }

    /**
     * @return the links
     */
    public Set<ProductLink> getLinks() {
        return links;
    }

    /**
     * @param links the links to set
     */
    public void setLinks(Set<ProductLink> links) {
        this.links = links;
    }

    public void addCategory(Category category) {
        if (this.categories == null) {
            this.categories = new ArrayList<Category>();
        }

        this.categories.add(category);
    }

    /**
     * @param link the link to add
     */
    public void addLink(ProductLink link) {
        if (link == null)
            throw new InvalidParameterException(
                    "the product link cannot be null");
        if (this.links == null)
            this.links = new HashSet<ProductLink>();
        this.links.add(link);
    }

    /**
     * @return the metaDescription
     */
    public String getMetaDescription() {
        return metaDescription;
    }

    /**
     * @param metaDescription the metaDescription to set
     */
    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
    }

    /**
     * @return the googleCheckout
     */
    public Boolean getGoogleCheckout() {
        return googleCheckout;
    }

    /**
     * @param googleCheckout the googleCheckout to set
     */
    public void setGoogleCheckout(Boolean googleCheckout) {
        this.googleCheckout = googleCheckout;
    }

    /**
     * @return the configurableProductsData
     */
    public Map<String, Map<String, Object>> getConfigurableProductsData() {
        return configurableProductsData;
    }

    /**
     * @param configurableProductsData the configurableProductsData to set
     */
    public void setConfigurableProductsData(
            Map<String, Map<String, Object>> configurableProductsData) {
        this.configurableProductsData = configurableProductsData;
    }

    /**
     * @return the configurableSubProducts
     */
    public List<ConfigurableProductData> getConfigurableSubProducts() {
        return configurableSubProducts;
    }

    /**
     * @param configurableSubProducts the configurableSubProducts to set
     */
    public void setConfigurableSubProducts(
            List<ConfigurableProductData> configurableSubProducts) {
        this.configurableSubProducts = configurableSubProducts;
    }

    /**
     * @return the configurableAttributesData
     */
    public List<ConfigurableAttributeData> getConfigurableAttributesData() {
        return configurableAttributesData;
    }

    /**
     * @param configurableAttributesData the configurableAttributesData to set
     */
    public void setConfigurableAttributesData(
            List<ConfigurableAttributeData> configurableAttributesData) {
        this.configurableAttributesData = configurableAttributesData;
    }

    /**
     * @return the visibility
     */
    public Visibility getVisibility() {
        return visibility;
    }

    /**
     * @param visibility the visibility to set
     */
    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    /*
      * (non-Javadoc)
      *
      * @see java.lang.Object#hashCode()
      */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result
                + ((attributeSet == null) ? 0 : attributeSet.hashCode());
        result = prime * result
                + ((categories == null) ? 0 : categories.hashCode());
        result = prime * result + ((cost == null) ? 0 : cost.hashCode());
        result = prime * result
                + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((enabled == null) ? 0 : enabled.hashCode());
        result = prime * result
                + ((googleCheckout == null) ? 0 : googleCheckout.hashCode());
        result = prime * result + ((inStock == null) ? 0 : inStock.hashCode());
        result = prime * result + ((links == null) ? 0 : links.hashCode());
        result = prime * result + ((medias == null) ? 0 : medias.hashCode());
        result = prime * result
                + ((metaDescription == null) ? 0 : metaDescription.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((price == null) ? 0 : price.hashCode());
        result = prime * result + ((qty == null) ? 0 : qty.hashCode());
        result = prime
                * result
                + ((shortDescription == null) ? 0 : shortDescription.hashCode());
        result = prime * result + ((sku == null) ? 0 : sku.hashCode());
        result = prime * result
                + ((taxClassId == null) ? 0 : taxClassId.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + Arrays.hashCode(websites);
        result = prime * result + ((weight == null) ? 0 : weight.hashCode());
        return result;
    }

    /*
      * (non-Javadoc)
      *
      * @see java.lang.Object#equals(java.lang.Object)
      */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Product other = (Product) obj;
        if (attributeSet == null) {
            if (other.attributeSet != null)
                return false;
        } else if (!attributeSet.equals(other.attributeSet))
            return false;
        if (categories == null) {
            if (other.categories != null)
                return false;
        } else if (!categories.equals(other.categories))
            return false;
        if (cost == null) {
            if (other.cost != null)
                return false;
        } else if (!cost.equals(other.cost))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (enabled == null) {
            if (other.enabled != null)
                return false;
        } else if (!enabled.equals(other.enabled))
            return false;
        if (googleCheckout == null) {
            if (other.googleCheckout != null)
                return false;
        } else if (!googleCheckout.equals(other.googleCheckout))
            return false;
        if (inStock == null) {
            if (other.inStock != null)
                return false;
        } else if (!inStock.equals(other.inStock))
            return false;
        if (links == null) {
            if (other.links != null)
                return false;
        } else if (!links.equals(other.links))
            return false;
        if (medias == null) {
            if (other.medias != null)
                return false;
        } else if (!medias.equals(other.medias))
            return false;
        if (metaDescription == null) {
            if (other.metaDescription != null)
                return false;
        } else if (!metaDescription.equals(other.metaDescription))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (price == null) {
            if (other.price != null)
                return false;
        } else if (!price.equals(other.price))
            return false;
        if (qty == null) {
            if (other.qty != null)
                return false;
        } else if (!qty.equals(other.qty))
            return false;
        if (shortDescription == null) {
            if (other.shortDescription != null)
                return false;
        } else if (!shortDescription.equals(other.shortDescription))
            return false;
        if (sku == null) {
            if (other.sku != null)
                return false;
        } else if (!sku.equals(other.sku))
            return false;
        if (taxClassId == null) {
            if (other.taxClassId != null)
                return false;
        } else if (!taxClassId.equals(other.taxClassId))
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        if (!Arrays.equals(websites, other.websites))
            return false;
        if (weight == null) {
            if (other.weight != null)
                return false;
        } else if (!weight.equals(other.weight))
            return false;
        return true;
    }

    /*
      * (non-Javadoc)
      *
      * @see java.lang.Object#toString()
      */
    @Override
    public String toString() {
        return "Product [attributeSet=" + attributeSet + ", categories="
                + categories + ", cost=" + cost + ", description="
                + description + ", enabled=" + enabled + ", googleCheckout="
                + googleCheckout + ", inStock=" + inStock + ", links=" + links
                + ", medias=" + medias + ", metaDescription=" + metaDescription
                + ", name=" + name + ", price=" + price + ", qty=" + qty
                + ", shortDescription=" + shortDescription + ", sku=" + sku
                + ", taxClassId=" + taxClassId + ", type=" + type
                + ", websites=" + Arrays.toString(websites) + ", weight="
                + weight + ", id=" + id + ", properties=" + properties + "]";
    }
}
