package com.oriaxx77.oaths;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.oriaxx77.oaths.domain.enity.Oath;
import com.oriaxx77.oaths.domain.enity.User;
import com.oriaxx77.oaths.domain.repository.OathRepository;
import com.oriaxx77.oaths.domain.repository.UserRepository;
import com.relayrides.pushy.apns.ApnsClient;
import com.relayrides.pushy.apns.ApnsClientBuilder;
import com.relayrides.pushy.apns.ClientNotConnectedException;
import com.relayrides.pushy.apns.PushNotificationResponse;
import com.relayrides.pushy.apns.util.ApnsPayloadBuilder;
import com.relayrides.pushy.apns.util.SimpleApnsPushNotification;
import com.relayrides.pushy.apns.util.TokenUtil;



@SpringBootApplication
// @EnableEurekaClient
public class OathsApplication {
	
	
	
	static void sendPushNotification() throws Exception, IOException{
		final ApnsClient apnsClient = new ApnsClientBuilder()
		        .setClientCredentials(new File("/Users/bagyurai/Documents/Apple_Dev_APN_PushNotificationCert.p12"), "chimera7")
		        .build();
		    final Future<Void> connectFuture = apnsClient.connect(ApnsClient.DEVELOPMENT_APNS_HOST);
		    connectFuture.get();
		    
		    final ApnsPayloadBuilder payloadBuilder = new ApnsPayloadBuilder();
		    payloadBuilder.setAlertTitle("Example!");
		    payloadBuilder.setAlertBody("Example!");

		    final String payload = payloadBuilder.buildWithDefaultMaximumLength();
		    final String token = TokenUtil.sanitizeTokenString("41208f8d ccf7132c 4e56ad69 9dd1b649 bf7af12e c59fc484 fb7f8022 0947d8e4");

		    SimpleApnsPushNotification pushNotification = new SimpleApnsPushNotification(token, "com.oriaxx77.MyOaths", payload);
		    final Future<PushNotificationResponse<SimpleApnsPushNotification>> sendNotificationFuture =
		        apnsClient.sendNotification(pushNotification);
		    
		    
		    try {
		    final PushNotificationResponse<SimpleApnsPushNotification> pushNotificationResponse =
		            sendNotificationFuture.get();

		    if (pushNotificationResponse.isAccepted()) {
		        System.out.println("Push notification accepted by APNs gateway.");
		        
		    } else {
		        System.out.println("Notification rejected by the APNs gateway: " +
		                pushNotificationResponse.getRejectionReason());

		        if (pushNotificationResponse.getTokenInvalidationTimestamp() != null) {
		            System.out.println("\t…and the token is invalid as of " +
		                pushNotificationResponse.getTokenInvalidationTimestamp());
		        }
		    }
		} catch (final ExecutionException e) {
		    System.err.println("Failed to send push notification.");
		    e.printStackTrace();

		    if (e.getCause() instanceof ClientNotConnectedException) {
		        System.out.println("Waiting for client to reconnect…");
		        apnsClient.getReconnectionFuture().await();
		        System.out.println("Reconnected.");
		    }
		}

		final Future<Void> disconnectFuture = apnsClient.disconnect();
		disconnectFuture.get();

	}
	
	public static void main(String[] args) throws Exception {
		
		// sendPushNotification();
		
		ConfigurableApplicationContext ctx = SpringApplication.run(OathsApplication.class, args);
		OathRepository oathRepo = ctx.getBean( OathRepository.class );
		UserRepository userRepo = ctx.getBean( UserRepository.class );
		
		IntStream.range( 0, 2 ).forEach( i -> {
			
			User user = new User( "oriaxx@gmail.com" +i, "oriaxx"+i, "authCode"+i, "", "" );
			Oath oath = new Oath(  user.getEmail() + " " + i + " oath " );
			userRepo.save( user );
			oath.setOathTaker( user );
			oathRepo.save( oath );
			System.out.println( "____ Oath saved: " + oath.getOath() );

		});
		
		userRepo.findAll();
				//ctx.getBean( APNService.class ).sendNotificationSync( "0ec7819c 15b34119 a297b3ec 1522d3f8 01af3193 977cd586 34504ed5 2bbe042c" , "From Server", "Hello there");
		

	}
}
