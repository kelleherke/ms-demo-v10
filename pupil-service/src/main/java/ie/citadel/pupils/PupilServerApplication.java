package ie.citadel.pupils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import ie.citadel.pupil.events.models.AddressChangeModel;
import ie.citadel.pupils.config.ServiceConfig;

import org.springframework.cloud.sleuth.Sampler;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;


@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
@EnableBinding(Sink.class)
public class PupilServerApplication {
	
  private static final Logger logger = LoggerFactory.getLogger(PupilServerApplication.class);
  
  @Autowired
  private ServiceConfig serviceConfig;
	
  @Bean
  public Sampler defaultSampler() {
    return new AlwaysSampler();
  }	

  @LoadBalanced
  @Bean
  public RestTemplate getRestTemplate(){
      return new RestTemplate();
  }
  
  @Bean
  public JedisConnectionFactory jedisConnectionFactory() {
      JedisConnectionFactory jedisConnFactory = new JedisConnectionFactory();
      jedisConnFactory.setHostName( serviceConfig.getRedisServer());
      jedisConnFactory.setPort( serviceConfig.getRedisPort() );
      return jedisConnFactory;
  }

  @Bean
  public RedisTemplate<String, Object> redisTemplate() {
      RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
      template.setConnectionFactory(jedisConnectionFactory());
      return template;
  }
  
  
  //@StreamListener(Sink.INPUT)
  //public void loggerSink(AddressChangeModel addressChange) {
  //  logger.debug("Received an event for eircode {}", addressChange.getEircode());
  //} 

  public static void main(String[] args) {
        SpringApplication.run(PupilServerApplication.class, args);
  }
}
