package ie.citadel.pupils.clients;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import ie.citadel.pupils.model.Address;
import ie.citadel.pupils.repository.AddressRedisRepository;

@Component
public class AddressRestTemplateClient {
    @Autowired
    RestTemplate restTemplate;
    
    @Autowired
    AddressRedisRepository addressRedisRepository;
    
    private static final Logger logger = LoggerFactory.getLogger(AddressRestTemplateClient.class);

    public Address getAddressFromEircode(String eircode){
    	
    	logger.debug("In Address Service.getAddressFromEircode:");

    	Address address = checkRedisCache(eircode);

        if (address!=null){
            logger.debug("I have successfully retrieved an address {} from the redis cache: {}", eircode, address);
            return address;
        }

        logger.debug("Unable to locate address from the redis cache: {}.", eircode);
    	
    	logger.info("About to call restTemplate exchange method");
        ResponseEntity<Address> restExchange =
                restTemplate.exchange(
                		"http://addressservice/v1/addresses/{eircode}",       
                        HttpMethod.GET,
                        null, Address.class, eircode);

        /*Save the record from cache*/
        address = restExchange.getBody();

        if (address!=null) {
            cacheAddressObject(address);
        }

        return address;
    }
    
    private Address checkRedisCache(String eircode) {
        try {
            return addressRedisRepository.findAddressByEircode(eircode);
        }
        catch (Exception ex){
            logger.error("Error encountered while trying to retrieve address {} check Redis Cache.  Exception {}", eircode, ex);
            return null;
        }
    }
    
    private void cacheAddressObject(Address address) {
        try {
        	addressRedisRepository.createAddress(address);
        }catch (Exception ex){
            logger.error("Unable to cache organization {} in Redis. Exception {}", address.getEircode(), ex);
        }
    }
}
