package in.fabuleux.billStore2.entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
public class Estimate extends BaseEntity {

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
	
	@OneToMany(
	        mappedBy = "estimate",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true)
	@Column(name = "products")
	private List<EstimateProduct> products = new ArrayList<>();
	
	@Column(name = "invoice_number")
	private String invoice_number;
	
	@Column(name = "total")
	private Double total;

	public String getInvoice_number() {
		return invoice_number;
	}

	public void setInvoice_number(String invoice_number) {
		this.invoice_number = invoice_number;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public List<EstimateProduct> getEstimateProducts() {
		return products;
	}

	public void setEstimateProducts(List<EstimateProduct> products) {
		this.products = products;
	}
	
	public void addProduct(Product product,int quantity) {
        EstimateProduct estimateProduct = new EstimateProduct(this, product,quantity);
        products.add(estimateProduct);
        product.getEstimateProducts().add(estimateProduct);
    }
 
    public void removeProduct(Product product) {
        for (Iterator<EstimateProduct> iterator = products.iterator();
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


	
	
}
