package ie.citadel.pupils.events.handlers;

import ie.citadel.pupil.events.models.AddressChangeModel;
import ie.citadel.pupils.events.CustomChannels;
import ie.citadel.pupils.repository.AddressRedisRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;


@EnableBinding(CustomChannels.class)
public class OrganizationChangeHandler {

    @Autowired
    private AddressRedisRepository addressRedisRepository;

    private static final Logger logger = LoggerFactory.getLogger(OrganizationChangeHandler.class);

    @StreamListener("inboundAddressChanges")
    public void loggerSink(AddressChangeModel addressChangeModel) {
        logger.debug("Received a message of type " + addressChangeModel.getType());
        switch(addressChangeModel.getAction()){
            case "GET":
                logger.debug("Received a GET event from the address service for eircode {}", addressChangeModel.getEircode());
                break;
            case "SAVE":
                logger.debug("Received a SAVE event from the address service for eircode {}", addressChangeModel.getEircode());
                break;
            case "UPDATE":
                logger.debug("Received a UPDATE event from the address service for eircode {}", addressChangeModel.getEircode());
                addressRedisRepository.deleteAddress(addressChangeModel.getEircode());
                break;
            case "DELETE":
                logger.debug("Received a DELETE event from the organization service for organization id {}", addressChangeModel.getEircode());
                addressRedisRepository.deleteAddress(addressChangeModel.getEircode());
                break;
            default:
                logger.error("Received an UNKNOWN event from the organization service of type {}", addressChangeModel.getType());
                break;

        }
    }

}

