package ie.citadel.pupils.repository;

import ie.citadel.pupils.model.Address;

public interface AddressRedisRepository {
	
	void createAddress(Address address);
    void updateAddress(Address address);
    void deleteAddress(String eircode);
    Address findAddressByEircode(String eircode);

}
