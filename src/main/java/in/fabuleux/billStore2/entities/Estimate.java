package in.fabuleux.billStore2.entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Estimate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "contact_id")
	private Contact contact;
	
	private String type;
	
	@OneToMany(
	        mappedBy = "estimate",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true)
	private List<EstimateProduct> estimateProducts = new ArrayList<>();

	public List<EstimateProduct> getEstimateProducts() {
		return estimateProducts;
	}

	public void setEstimateProducts(List<EstimateProduct> estimateProducts) {
		this.estimateProducts = estimateProducts;
	}
	
	public void addProduct(Product product) {
        EstimateProduct estimateProduct = new EstimateProduct(this, product);
        estimateProducts.add(estimateProduct);
        product.getEstimateProducts().add(estimateProduct);
    }
 
    public void removeProduct(Product product) {
        for (Iterator<EstimateProduct> iterator = estimateProducts.iterator();
             iterator.hasNext(); ) {
            EstimateProduct postTag = iterator.next();
 
            if (postTag.getProduct().equals(this) &&
                    postTag.getProduct().equals(product)) {
                iterator.remove();
                postTag.getProduct().getEstimateProducts().remove(postTag);
                postTag.setEstimate(null);
                postTag.setProduct(null);
            }
        }
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private String status;
	
	
}
