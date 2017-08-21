package ie.citadel.address.controllers;


import ie.citadel.address.model.Address;
import ie.citadel.address.services.AddressService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping(value="v1/addresses")
public class AddressServiceController {
    
	@Autowired
    private AddressService addressService;

    @RequestMapping(value="/{eircode}",method = RequestMethod.GET)
    public Address getAddress( @PathVariable("eircode") String eircode) {
        return addressService.getAddressByEircode(eircode);
    }
    
    @RequestMapping(value="/{eircode}",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAddress( @PathVariable("eircode") String eircode,  @RequestBody Address address) {
    	addressService.deleteAddress(address);
    }
    
    
    @RequestMapping(value="/{eircode}",method = RequestMethod.PUT)
    public void updateAddress( @PathVariable("eircode") String orgId,  @RequestBody Address address) {
    	addressService.updateAddress(address);
    }

    @RequestMapping(value="/{eircode}",method = RequestMethod.POST)
    public void createAddress(@RequestBody  Address address) {
    	addressService.createAddress(address);
    }

}
