package in.fabuleux.billStore2.entities;

import java.beans.Transient;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;

@Entity
@AssociationOverrides({
		@AssociationOverride(name = "estimate", 
			joinColumns = @JoinColumn(name = "estimate_id")),
		@AssociationOverride(name = "product", 
			joinColumns = @JoinColumn(name = "product_id")) })
public class EstimateProduct {

	private EstimateProductId pk = new EstimateProductId();
	private double quantity;
	
	@EmbeddedId
	public EstimateProductId getPk() {
		return pk;
	}

	public void setPk(EstimateProductId pk) {
		this.pk = pk;
	}
	
	@Transient
	public Estimate getEstimate() {
		return getPk().getEstimate();
	}

	public void setEstimate(Estimate estimate) {
		getPk().setEstimate(estimate);
	}

	@Transient
	public Product getProduct() {
		return getPk().getProduct();
	}

	public void setProduct(Product product) {
		getPk().setProduct(product);
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
}
