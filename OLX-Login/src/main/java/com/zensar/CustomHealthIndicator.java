package com.zensar;

import java.util.Random;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health.Builder;

public class CustomHealthIndicator extends AbstractHealthIndicator{

	@Override
	protected void doHealthCheck(Builder builder) throws Exception {
		Random random = new Random();
		int ranNo = random.nextInt(100);
		if(ranNo%2==0) 
			builder.up();
		else
			builder.down();	
	}

}
