package com.springboot.urlShortener;

import java.nio.charset.StandardCharsets;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.hash.Hashing;

@RestController
@RequestMapping("/url")
public class UrlController {
	
	private UrlRepository urlRepository;
	@Autowired
	private Environment env;
	public UrlController(UrlRepository urlRepository) {
		this.urlRepository = urlRepository;
	}

	@GetMapping("/{id}")
	public String getUrl(@PathVariable String id) {
		UrlObject urlObject = this.urlRepository.findByshortid(id);
		if(urlObject==null) {
			throw new RuntimeException("No Record found in the Database");
		}
		return urlObject.getUrl();
	}
	
	@PostMapping()
	public String create(@RequestBody String url) {
		
		UrlValidator urlValidator = new UrlValidator(new String[] {"http","https"});
		String shorturl;
		if(urlValidator.isValid(url)) {
			shorturl = Hashing.murmur3_32().hashString(url, StandardCharsets.UTF_8).toString();
			System.out.println("shortUrl:" + shorturl);
			UrlObject urlObject =new UrlObject(shorturl,url);
			this.urlRepository.save(urlObject);
			
		}
		else {
			throw new RuntimeException("Invalid Url");
		}
		return "http://localhost:" + env.getProperty("server.port") + "/url/" + shorturl;
	}

}
