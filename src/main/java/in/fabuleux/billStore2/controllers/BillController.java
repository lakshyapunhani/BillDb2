package in.fabuleux.billStore2.controllers;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.fabuleux.billStore2.entities.Contact;
import in.fabuleux.billStore2.entities.Estimate;
import in.fabuleux.billStore2.entities.Invoice;
import in.fabuleux.billStore2.entities.Product;
import in.fabuleux.billStore2.entities.User;
import in.fabuleux.billStore2.services.BillService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BillController 
{
	@Autowired
	BillService billService;
	
	@Autowired 
	private PasswordEncoder passwordEncoder;
	
	
	@PostMapping("/user")
	public ResponseEntity addUser(@RequestBody User user)
	{
		return billService.addUser(user);
	}
	
	@GetMapping("/user")
	public List<User> getAllUsers(@RequestParam(defaultValue = "0") Integer pageNo, 
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy)
	{
		return billService.getAllUsers(pageNo,pageSize,sortBy);
	}

	@PostMapping("/login")
	public User getLogin(@RequestBody HashMap<String, String> hashMap)
	{
		return billService.getLoginDetails(hashMap);
	}
	
	@PutMapping("/user/{id}")
	public ResponseEntity updateUserDetails(@PathVariable Long id,@RequestBody User user)
	{
		return billService.updateUser(id, user);
	}
	
	@DeleteMapping("/user/{id}")
	public ResponseEntity deleteUser(@PathVariable Long id)
	{
		return billService.deleteUser(id);
	}
	
	@PostMapping("/product/{id}")
	public ResponseEntity addProduct(@PathVariable Long id,@RequestBody Product product)
	{
		return billService.addProduct(id, product);
	}
	
	@GetMapping("/product/{id}")
	public List<Product> getProducts(@PathVariable Long id)
	{
		return billService.getProducts(id);
	}
	
	@PutMapping("/product/{id}")
	public ResponseEntity updateProduct(@PathVariable Long id,@RequestBody Product product)
	{
		return billService.updateProduct(id, product);
	}
	
	@DeleteMapping("/product/{id}")
	public ResponseEntity deleteProduct(@PathVariable Long id)
	{
		return billService.deleteProduct(id);
	}
	
	@PostMapping("/contact/{id}")
	public ResponseEntity addContact(@PathVariable Long id,@RequestBody Contact contact)
	{
		return billService.addContact(id,contact);
	}
	
	@GetMapping("/contact/{id}")
	public List<Contact> getContacts(@PathVariable Long id)
	{
		return billService.getContacts(id);
	}
	
	@PutMapping("/contact/{id}")
	public ResponseEntity updateContact(@PathVariable Long id,@RequestBody Contact contact)
	{
		return billService.updateContact(id, contact);
	}
	
	@DeleteMapping("/contact/{id}")
	public ResponseEntity deleteContact(@PathVariable Long id)
	{
		return billService.deleteContact(id);
	}
	
	@GetMapping("/test")
	public String testMethod()
	{
		return "Hello world";
	}
	
	@PostMapping("/invoice/{userId}")
	public ResponseEntity createInvoice(@PathVariable Long userId,@RequestBody Invoice invoice)
	{
		return billService.insertInvoice(userId,invoice);
	}
	
	@GetMapping("/invoice/{id}")
	public List<Invoice> getInvoices(@PathVariable Long id)
	{
		return billService.getInvoices(id);
	}
	
	@GetMapping("/invoices/{id}")
	public Invoice getInvoiceById(@PathVariable Long id)
	{
		return billService.getInvoiceById(id);
	}
	
	@PostMapping("/estimate/{id}")
	public ResponseEntity createEstimate(@PathVariable Long id,@RequestBody HashMap<String, Object> hashMap)
	{
		return billService.createEstimate(id, hashMap);
	}
	
	@GetMapping("/estimate/{id}")
	public List<Estimate> getEstimates(@PathVariable Long id)
	{
		return billService.getEstimates(id);
	}
	
}
