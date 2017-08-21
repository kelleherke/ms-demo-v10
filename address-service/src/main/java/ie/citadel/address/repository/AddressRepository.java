package ie.citadel.address.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ie.citadel.address.model.Address;

@Repository
public interface AddressRepository extends CrudRepository<Address,String>  {
    public Address findByEircode(String eircode);

}