package ie.citadel.pupils.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import ie.citadel.pupils.clients.AddressRestTemplateClient;
import ie.citadel.pupils.model.Address;
import ie.citadel.pupils.model.Pupil;
import ie.citadel.pupils.repository.PupilRepository;

@Service
public class PupilService {
	
	@Autowired
	private PupilRepository pupilRepository;

    @Autowired
    AddressRestTemplateClient addressRestTemplateClient;
    
    private static final Logger logger = LoggerFactory.getLogger(PupilService.class);

    

    @HystrixCommand(  threadPoolKey="pupilThreadPool", commandProperties= {@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",  value="10000")})
    public Pupil getPupil(String pupilId) {
    	
    	logger.info("About to retrieve pupil with Id " +pupilId);
    	
    	Pupil pupil = pupilRepository.findByPupilId(pupilId);

        Address address = retrieveAddressByEircode(pupil.getEircode());

        return pupil
                .withAddress1( address.getAddress1())
                .withAddress2( address.getAddress2())
                .withAddress3( address.getAddress3() )
                .withAddress4( address.getAddress4() );
    }
    
    @HystrixCommand(  threadPoolKey="pupilThreadPool", commandProperties= {@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",  value="7500")})
    private Address retrieveAddressByEircode(String addressId){
    	logger.info("About to make rest template client call");
        return addressRestTemplateClient.getAddressFromEircode(addressId);
    }


}
