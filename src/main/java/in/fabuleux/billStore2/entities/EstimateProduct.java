package in.fabuleux.billStore2.entities;

import java.beans.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class EstimateProduct {

	@EmbeddedId
    private EstimateProductId id;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("estimateId")
    @JsonIgnore
    private Estimate estimate;
 
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("productId")
    //@JsonIgnore
    private Product product;
 
    @Column(name = "created_on")
    private Date createdOn = new Date();
 
    public Estimate getEstimate() {
		return estimate;
	}

	public void setEstimate(Estimate estimate) {
		this.estimate = estimate;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	private EstimateProduct() {}
 
    public EstimateProduct(Estimate estimate, Product product) {
        this.estimate = estimate;
        this.product = product;
        this.id = new EstimateProductId(estimate.getId(), product.getId());
    }
 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        EstimateProduct that = (EstimateProduct) o;
        return Objects.equals(estimate, that.estimate) &&
               Objects.equals(product, that.product);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(estimate, product);
    }
}
