package com.oriaxx77.oaths.domain

import com.relayrides.pushy.apns.util.SimpleApnsPushNotification

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.net.ssl.SSLException;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.relayrides.pushy.apns.ApnsClient;
import com.relayrides.pushy.apns.ApnsClientBuilder;
import com.relayrides.pushy.apns.ClientNotConnectedException;
import com.relayrides.pushy.apns.PushNotificationResponse;
import com.relayrides.pushy.apns.util.ApnsPayloadBuilder;
import com.relayrides.pushy.apns.util.SimpleApnsPushNotification;
import com.relayrides.pushy.apns.util.TokenUtil;
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

@Component
class APNService{
  
  var apnsClient: ApnsClient = _;
  
  // TODO: Make it auto-connect in the sendNotification
  
  @PostConstruct
  def init() = {
    apnsClient = new ApnsClientBuilder()
		        .setClientCredentials(new File("/Users/bagyurai/Documents/Apple_Dev_APN_PushNotificationCert.p12"), "chimera7")
		        .build();
		val connectFuture = apnsClient.connect(ApnsClient.DEVELOPMENT_APNS_HOST);
		connectFuture.get();
  }
  
  @PreDestroy
  def destroy() = {
    val disconnectFuture = apnsClient.disconnect();
		disconnectFuture.get();    
  }
  
  def sendNotificationSync( deviceToken:String , messageTitle: String, messageBody: String ) = {
    // TODO: invariants: ScalaZ
    
    // TODO: make it thread safe 
    // TODO: clean code!!!!
    if ( !apnsClient.isConnected() ){
      apnsClient.getReconnectionFuture().await();
    }
    
    val payload = new ApnsPayloadBuilder()
                                  .setAlertBody(messageBody)
                                  .setAlertTitle( messageTitle )
		                              .buildWithDefaultMaximumLength(); 
    
    println( "Device token used: " + deviceToken ); 
    val pushNotification = new SimpleApnsPushNotification( deviceToken , "com.oriaxx77.MyOaths", payload); // TODO:remove constant
		val sendNotificationFuture = apnsClient.sendNotification( pushNotification );

		try {
		    val pushNotificationResponse = sendNotificationFuture.get();

		    if (pushNotificationResponse.isAccepted()) {
		        println("Push notification accepted by APNs gateway.");
		    } else {
		        println("Notification rejected by the APNs gateway: " + pushNotificationResponse.getRejectionReason());
		        if (pushNotificationResponse.getTokenInvalidationTimestamp() != null) {
		            println("\t…and the token is invalid as of " + pushNotificationResponse.getTokenInvalidationTimestamp());
		        }
		    }
		} catch {
  		  case e: ExecutionException => {
  		    println("Failed to send push notification.");
  		    e.printStackTrace();
  		    if ( e.getCause().isInstanceOf[ClientNotConnectedException]) {
  		        println("Waiting for client to reconnect…");
  		        apnsClient.getReconnectionFuture().await();
  		        println("Reconnected.");
  		   }}
		}

    
  }
  
}