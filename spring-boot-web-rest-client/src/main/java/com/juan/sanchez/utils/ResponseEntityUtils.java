package com.juan.sanchez.utils;

import com.juan.sanchez.domain.Author;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

public class ResponseEntityUtils {

	public static void reportFindById(ResponseEntity<Author> response) {
		System.out.println("  Status :");
		System.out.println("   " + response.getStatusCode());
		System.out.println("  Headers:");
		response.getHeaders().forEach((key, value) -> {
		    System.out.println("   Key: " + key + " Value: " + value);
		});
		System.out.println("  Body   :");
		System.out.println("    " + response.getBody().toString());//
		System.out.println();
	}

	public static void reportFindAll(ResponseEntity<Collection<Author>> response) {
		System.out.println("  Status :");
		System.out.println("   " + response.getStatusCode());
		System.out.println("  Headers:");
		response.getHeaders().forEach((key, value) -> {
		    System.out.println("   Key: " + key + " Value: " + value);
		});
		System.out.println("  Body   :");
		System.out.println("    " + response.getBody().toString());
		response.getBody().forEach(t -> System.out.println("  " + t));
		System.out.println();
	}

	public static void reportSave(ResponseEntity<Void> response) {
		System.out.println("  Status :");
		System.out.println("   " + response.getStatusCode());
		System.out.println("  Headers:");
		response.getHeaders().forEach((key, value) -> {
		    System.out.println("   Key: " + key + " Value: " + value);
		});
		System.out.println("  Body   :");
		System.out.println("    " + response.getBody());//
		System.out.println();
	}

	public static void reportUpdate(ResponseEntity<Void> response) {
		reportSave(response);
	}

	public static void reportDelete(ResponseEntity<Void> response) {
		reportSave(response);
	}

	private ResponseEntityUtils() {

	}

}
