package in.fabuleux.billStore2.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;


@Embeddable
public class EstimateProductId implements Serializable {
	
	@Column(name = "estimate_id")
    private Long estimateId;
 
    @Column(name = "product_id")
    private Long productId;
 
    private EstimateProductId() {}
 
    public EstimateProductId(
        Long estimateId,
        Long productId) {
        this.estimateId = estimateId;
        this.productId = productId;
    }
 
    //Getters omitted for brevity
 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        EstimateProductId that = (EstimateProductId) o;
        return Objects.equals(estimateId, that.estimateId) &&
               Objects.equals(productId, that.productId);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(estimateId, productId);
    }
	
}
