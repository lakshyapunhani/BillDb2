package in.fabuleux.billStore2.services;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import in.fabuleux.billStore2.entities.Contact;
import in.fabuleux.billStore2.entities.Invoice;
import in.fabuleux.billStore2.entities.Product;
import in.fabuleux.billStore2.entities.User;
import in.fabuleux.billStore2.repos.ContactRepository;
import in.fabuleux.billStore2.repos.InvoiceRepository;
import in.fabuleux.billStore2.repos.ProductRepository;
import in.fabuleux.billStore2.repos.UserRepository;
import in.fabuleux.billStore2.responses.BadRequestException;
import in.fabuleux.billStore2.responses.NotFoundException;
import in.fabuleux.billStore2.responses.UnAuthorizedException;

@Service
public class BillService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ContactRepository contactRepository;
	
	@Autowired
	InvoiceRepository invoiceRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public ResponseEntity addUser(User user)
	{
		Optional<User> info = userRepository.findByUsername(user.getUsername());
		if(info.isPresent())
		{
			throw new BadRequestException("User already exists");
		}
		User user2 = userRepository.save(user);
		URI uri =  ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(user2.getId())
				.toUri();
				
		return ResponseEntity.created(uri).build();
	}
	
	public List<User> getAllUsers()
	{
		return userRepository.findAll();
	}
	
	public ResponseEntity updateUser(Long id,User user)
	{
		Optional<User> info = userRepository.findById(id);
		if(!info.isPresent())
		{
			throw new NotFoundException("User doesn't exist");
		}
		User user2 = info.get();
		user2.setPassword(passwordEncoder.encode(user.getPassword()));
		user2.setAddress(user.getAddress());
		user2.setEmail(user.getEmail());
		user2.setName(user.getName());
		userRepository.save(user2);
		
		URI uri =  ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(user2.getId())
				.toUri();
				
		return ResponseEntity.ok().build();
	}
	
	public ResponseEntity deleteUser(Long id)
	{
		Optional<User> info = userRepository.findById(id);
		if(!info.isPresent())
		{
			throw new NotFoundException("User doesn't exist");
		}
		userRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	
	public User getLoginDetails(HashMap<String, String> hashMap)
	{
		String username = hashMap.get("username");
		String password = hashMap.get("password");
		Optional<User> info = userRepository.findByUsername(username);
		if(!info.isPresent())
		{
			throw new NotFoundException("User doesn't exist");
		}
		if (passwordEncoder.matches(password, info.get().getPassword())) {
			return info.get();	
		}
		else {
			throw new UnAuthorizedException("Password doesn't match");
		}
	}
	
	public ResponseEntity addProduct(Long id,Product product)
	{
		Optional<User> info = userRepository.findById(id);
		if(!info.isPresent())
		{
			throw new NotFoundException("User doesn't exist");
		}
		product.setUser(info.get());
		Product product2 = productRepository.save(product);
		URI uri =  ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(product2.getId())
				.toUri();
				
		return ResponseEntity.created(uri).build();
	}
	
	public List<Product> getProducts(Long id)
	{
		Optional<User> info = userRepository.findById(id);
		if(!info.isPresent())
		{
			throw new NotFoundException("User doesn't exist");
		}
		return info.get().getProducts();
	}
	
	public ResponseEntity updateProduct(Long id,Product product)
	{
		Optional<Product> info = productRepository.findById(id);
		if(!info.isPresent())
		{
			throw new NotFoundException("Product doesn't exist");
		}
		Product product2 = info.get();
		
		product2.setDescription(product.getDescription());
		product2.setName(product.getName());
		product2.setQuantity(product.getQuantity());
		product2.setRate(product.getRate());
		productRepository.save(product2);
		
		URI uri =  ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(product2.getId())
				.toUri();
				
		return ResponseEntity.ok().build();
	}
	
	public ResponseEntity deleteProduct(Long id)
	{
		Optional<Product> info = productRepository.findById(id);
		if(!info.isPresent())
		{
			throw new NotFoundException("Product doesn't exist");
		}
		productRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	public ResponseEntity addContact(Long id,Contact contact)
	{
		Contact adderContact = new Contact();
		adderContact.setAddress(contact.getAddress());
		adderContact.setEmail(contact.getEmail());
		adderContact.setName(contact.getName());
		adderContact.setUsername(contact.getUsername());
		adderContact.setIs_active(true);
		
		Contact receiverContact = contact;
		receiverContact.setIs_active(false);
		
		Optional<User> info = userRepository.findById(id);
		if(!info.isPresent())
		{
			throw new NotFoundException("User doesn't exist");
		}
		User contactAddingUser = info.get();
		adderContact.setUser(contactAddingUser);
		Contact contact2 =  contactRepository.save(adderContact);
		Optional<User> user = userRepository.findByUsername(contact.getUsername());
		if(user.isPresent())
		{
			User contactReceivingUser = user.get();
			receiverContact.setUser(contactReceivingUser);
			contactRepository.save(receiverContact);
		}
		
		URI uri =  ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(contact2.getId())
				.toUri();
				
		return ResponseEntity.created(uri).build();	
	}
	
	public List<Contact> getContacts(Long id)
	{
		Optional<User> info = userRepository.findById(id);
		if(!info.isPresent())
		{
			throw new NotFoundException("User doesn't exist");
		}
		return info.get().getContacts();
	}
	
	public ResponseEntity deleteContact(Long id)
	{
		Optional<Contact> info = contactRepository.findById(id);
		if(!info.isPresent())
		{
			throw new NotFoundException("Contact doesn't exist");
		}
		contactRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	public ResponseEntity insertInvoice(Long senderId,Long receiverId)
	{
		Optional<User> senderUser = userRepository.findById(senderId);
		if(!senderUser.isPresent())
		{
			throw new NotFoundException("User doesn't exist");
		}
		
		Contact contact = contactRepository.findById(receiverId).get();
		if (contact.getUser() != null)
		{
			Invoice receiveInvoice = new Invoice();
			receiveInvoice.setUser(contact.getUser());
		}
		
		Invoice invoice = new Invoice();
		invoice.setUser(senderUser.get());
		invoice.setContact(contact);
		invoice.setType("Sales");
		invoice.setStatus("Due");
		Invoice invoice2 = invoiceRepository.save(invoice);
		
		URI uri =  ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(invoice2.getId())
				.toUri();
				
		return ResponseEntity.created(uri).build();

	}
	
	

}
