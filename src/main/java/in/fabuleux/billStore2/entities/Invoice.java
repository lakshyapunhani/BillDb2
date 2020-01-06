package in.fabuleux.billStore2.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Invoice extends BaseEntity
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "contact_id")
	//@JsonIgnore
	private Contact contact;
	
	private String type;
	
	private String status;

	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "invoice_products",
            joinColumns = { @JoinColumn(name = "invoice_id") },
            inverseJoinColumns = { @JoinColumn(name = "product_id") })
    private Set<Product> products = new HashSet<>();

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
        this.products = new HashSet<Product>(products); 
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
