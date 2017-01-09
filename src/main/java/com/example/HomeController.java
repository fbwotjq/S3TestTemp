package com.example;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;

@Controller
public class HomeController {

	@GetMapping("/")
	@ResponseBody public Map<String, Object> home() {
		
		AWSCredentials credentials = new BasicAWSCredentials("???", "???");
        ClientConfiguration clientConfig = new ClientConfiguration();
        clientConfig.setProtocol(Protocol.HTTP);
        AmazonS3 conn = new AmazonS3Client(credentials, clientConfig);
        conn.setEndpoint("s3-ap-northeast-2.amazonaws.com");
		Map<String, Object> map = new HashMap<>();
		map.put("result", conn.listBuckets());
		return map;
		
	}
	
	@GetMapping("/makeDir")
	@ResponseBody public Map<String, Object> makeDir() {
		
		AWSCredentials credentials = new BasicAWSCredentials("???", "???");
        ClientConfiguration clientConfig = new ClientConfiguration();
        clientConfig.setProtocol(Protocol.HTTP);
        AmazonS3 conn = new AmazonS3Client(credentials, clientConfig);
        conn.setEndpoint("s3-ap-northeast-2.amazonaws.com");
        String dirName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        conn.putObject("asking-nfs", dirName + "/", new ByteArrayInputStream(new byte[0]), new ObjectMetadata());
		Map<String, Object> map = new HashMap<>();
		map.put("result", dirName);
		return map;
		
	}
	
}
