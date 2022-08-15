package com.snhu.sslserver;

import java.security.MessageDigest;
import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SslServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SslServerApplication.class, args);
	}

}
//FIXME: Add route to enable check sum return of static data example:  String data = "Hello World Check Sum!";
@RestController
class ServerController {
	@RequestMapping("/hash")
	@ResponseBody
	public static String myHash(String data) throws Exception {
		String message = "Apurva Shukla";
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(message.getBytes());
		byte[] digest = md.digest();
		System.out.println(Arrays.toString(digest));
		
		StringBuilder hexString = new StringBuilder();
		for (byte b: digest) {
			hexString.append(Integer.toHexString(0xFF & b));
		}
		String outputString = String.format("<p>Hello %s!", message);
        outputString += "<p>data: This is your First and Last Name";
        outputString += "<p>SHA-256 Checksum Value: ";
        return outputString + hexString;
		
	}
	
}