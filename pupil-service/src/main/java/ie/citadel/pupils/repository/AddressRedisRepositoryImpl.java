package ie.citadel.pupils.repository;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import ie.citadel.pupils.model.Address;

@Repository
public class AddressRedisRepositoryImpl implements AddressRedisRepository {
    private static final String HASH_NAME ="organization";

    private RedisTemplate<String, Address> redisTemplate;
    private HashOperations hashOperations;

    public AddressRedisRepositoryImpl(){
        super();
    }

    @Autowired
    private AddressRedisRepositoryImpl(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }


    @Override
    public void createAddress(Address address) {
        hashOperations.put(HASH_NAME, address.getEircode(), address);
    }

    @Override
    public void updateAddress(Address address) {
        hashOperations.put(HASH_NAME, address.getEircode(), address);
    }

    @Override
    public void deleteAddress(String eircode) {
        hashOperations.delete(HASH_NAME, eircode);
    }

    @Override
    public Address findAddressByEircode(String eircode) {
       return (Address) hashOperations.get(HASH_NAME, eircode);
    }
}

