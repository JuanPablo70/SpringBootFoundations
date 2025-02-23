package com.juan.sanchez.web.rest.utils;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class URIUtils {

	public static URI createURIForPost(Integer id) {
		return ServletUriComponentsBuilder.fromCurrentRequestUri()
										  .path("/{id}")
										  .buildAndExpand(id)
										  .toUri();
	}

	private URIUtils() {

	}

}
