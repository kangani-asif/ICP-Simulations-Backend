package com.dtt.simulations;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ug.daes.DAESService;
import ug.daes.PKICoreServiceException;
import ug.daes.Result;

@SpringBootApplication
public class SimulationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimulationsApplication.class, args);
	}


	@Bean
	public String signatueServiceInitilize() {
		try {
			Result result = DAESService.initPKINativeUtils();
			if (result.getStatus() == 0) {
				System.out.println(new String(result.getStatusMessage()));
			} else {
				System.out.println(new String(result.getResponse()));
			}

			return "";
		} catch (PKICoreServiceException e) {
			// Exception caught, but not logged
			return "";

		}
	}

	@Bean("jasyptStringEncryptor")
	public StringEncryptor stringEncryptor() {
		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		SimpleStringPBEConfig config = new SimpleStringPBEConfig();
		config.setPassword("$DttKycImplEngin@@r");
		config.setAlgorithm("PBEWithHMACSHA512AndAES_256");
		config.setKeyObtentionIterations("1000");
		config.setPoolSize("1");
		config.setProviderName("SunJCE");
		config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
		config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
		config.setStringOutputType("base64");
		encryptor.setConfig(config);
		return encryptor;
	}
}


