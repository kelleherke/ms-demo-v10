package ie.citadel.address.events.source;

import ie.citadel.address.events.models.AddressChangeModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;


@Component
public class SimpleSourceBean {
    private Source source;

    private static final Logger logger = LoggerFactory.getLogger(SimpleSourceBean.class);

    @Autowired
    public SimpleSourceBean(Source source){
        this.source = source;
    }

    public void publishAddressChange(String action,String eircode){
       logger.debug("Sending Kafka message {} for Eircode: {}", action, eircode);
        AddressChangeModel change =  new AddressChangeModel(
        		AddressChangeModel.class.getTypeName(),
                action,
                eircode);

        source.output().send(MessageBuilder.withPayload(change).build());
    }
}
