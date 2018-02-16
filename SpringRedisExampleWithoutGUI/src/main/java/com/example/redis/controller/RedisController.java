package com.example.redis.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.redis.model.Product;


@RestController
public class RedisController {

private static final String REDIS_INDEX_KEY = "PRODUCT";
	
	@Autowired
	RedisTemplate<String, Object> redisTemplate;
	
	@RequestMapping(value="/products", method=RequestMethod.POST)
	public String createProduct(@RequestBody Product product) {
		redisTemplate.opsForHash().put(REDIS_INDEX_KEY, product.getProductid(), product.toString());
		return "Product is saved successfully";
	}

	
	@RequestMapping(value="/products", method=RequestMethod.GET)
	public ResponseEntity<Object> getProducts() {
		Logger.getAnonymousLogger().log(Level.INFO, "get products");
		return new ResponseEntity<>(redisTemplate.opsForHash().entries(REDIS_INDEX_KEY), HttpStatus.OK);
	}
	
	@RequestMapping(value="/products/{productid}", method=RequestMethod.PUT)
	public String updateProduct(@PathVariable("productid") String productid, @RequestBody Product product) {
		redisTemplate.opsForHash().put(REDIS_INDEX_KEY, product.getProductid(), product.toString());
		return "Product is updated sucessfully";
	}
	

	@RequestMapping(value="/products/{productid}", method=RequestMethod.DELETE)
	public String deleteProduct(@PathVariable("productid") String productid) {
		redisTemplate.opsForHash().delete(REDIS_INDEX_KEY, productid);
		return "Product is deleted successfully";
	}
	
	@RequestMapping(value="/products/{productid}", method=RequestMethod.GET)
	public Object getProductById(@PathVariable("productid") String productid) {
		return redisTemplate.opsForHash().get(REDIS_INDEX_KEY, productid);
	}
}
