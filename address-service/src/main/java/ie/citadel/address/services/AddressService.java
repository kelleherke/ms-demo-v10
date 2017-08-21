package ie.citadel.address.services;

import ie.citadel.address.events.source.SimpleSourceBean;
import ie.citadel.address.model.Address;
import ie.citadel.address.repository.AddressRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class AddressService {
	
	 @Autowired
	 private AddressRepository addressRepository;
	 
	 @Autowired
	 SimpleSourceBean simpleSourceBean;

	private static final Logger logger = LoggerFactory.getLogger(AddressService.class);
	
	@HystrixCommand(  threadPoolKey="addressThreadPool", fallbackMethod = "buildFallbackAddress", commandProperties= {@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",  value="5000")}) 
    public Address getAddressByEircode(String eircode) {
    	
    	logger.info("Getting address for eircode " + eircode + "...");
    	
    	Address address = addressRepository.findByEircode(eircode);
    	
    	logger.info("About to return address for eircode " + eircode + "..." + address.toString());
    	
    	return address;
    	
    	
    }
	
    private Address buildFallbackAddress(String eircode) {
    	
    	logger.info("Getting fallback address for eircode " + eircode + "...");
        
    	//TODO : Real world, use eircode to pull back address from repository.
    	return  new Address().withEircode("D20AB01").withAddress1("Any House").withAddress2("Any Street")
    			             .withAddress3("Any Town").withAddress4("Anywhere");
    }
    
    
    public void deleteAddress(Address address){
    	addressRepository.delete( address.getEircode());
    	simpleSourceBean.publishAddressChange("DELETE", address.getEircode());
    }
    
    
    public void updateAddress(Address address){
    	addressRepository.save(address);
    	simpleSourceBean.publishAddressChange("UPDATE", address.getEircode());
    }
    
    public void createAddress(Address address){

    	addressRepository.save(address);
    	simpleSourceBean.publishAddressChange("CREATE", address.getEircode());

    }
    

}
