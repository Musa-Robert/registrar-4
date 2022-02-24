package com.geturgently.lindquist.registrar;

import com.geturgently.lindquist.registrar.students.StudentDTO;
import java.sql.*;
import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
class RegistrarApplicationTests {

	private RestTemplate restTemplate;
	private Connection con;

	private static final String BASE_URL = "http://localhost:8080";

//	@BeforeTestClass
//	public void beforeTestClass() throws Exception {
//		Class.forName("org.h2.Driver");
//	}

	@BeforeEach
	public void init() throws Exception {
		restTemplate = new RestTemplate();
		//import java.sql.*;
		//// Importing required classes
		//importjava.util.*;
		String url = "jdbc:h2:mem:testdb";
		String user = "root";
		String pass = "root";
		Class.forName("org.h2.Driver");
		con = DriverManager.getConnection(url, user, pass);
		System.out.println("Here's the catalog: " + con.getCatalog());
		System.out.println("Here's the schema: " + con.getSchema());
	}

//	@Test
//	void contextLoads() {
//	}

	@Test
	void fullCRUDTest() {

		// Step 1: Connect to Database
		// Note: did this in the init() method

		// Step 2: Create Student through API
		StudentDTO studentX = createStudent("Musa", "Moylam", "8115551234", "mmoylam@geturgently.com");
		assertEquals("Musa", studentX.getFirstName());

		// Step 3: Retrieve (GET) Student through API
		StudentDTO studentXretrieved = retrieveStudent(studentX.getId());
		assertEquals(studentX, studentXretrieved);

		// Step 4: Update Student through JDBC
		updateStudentFirstName(studentX.getId(), "David");

		// Step 5: Retrieve (GET) Student through API
		StudentDTO studentXUpdated = retrieveStudent(studentX.getId());
		assertNotEquals(studentX.getFirstName(), studentXUpdated.getFirstName());
		assertEquals(studentX.getLastName(), studentXUpdated.getLastName());
		assertEquals(studentX.getId(), studentXUpdated.getId());

		// Step 6: Delete Student through JDBC
		deleteStudent(studentX.getId());

		// Step 7: Try to Retrieve (GET) Student through API
		boolean found = true;
		try {
			retrieveStudent(studentX.getId());
		} catch(Exception e) {
			if(e.toString().contains("not found")) {
				found = false;
			}
		}

		assertFalse(found);
	}

	private StudentDTO retrieveStudent(Long id) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(BASE_URL)
			.path("/students")
			.path("/" + id);

		String url = builder.build().toString();

		HttpMethod method = HttpMethod.GET;
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		HttpEntity<Void> entity = new HttpEntity<>(headers);
		ResponseEntity<StudentDTO> response = restTemplate.exchange(url, method, entity, StudentDTO.class);
		if(response.getStatusCodeValue() >= 400) {
			throw new RuntimeException("Student " + id + " not found.");
		}
		return response.getBody();
	}

	private StudentDTO createStudent(String firstName, String lastName, String phoneNumber, String email) {

		//POST localhost:8080/students?firstName=Robert&lastName=Lindquist&emailAddress=rlindquist@geturgently.com&phoneNumber=8115554567

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(BASE_URL)
				.path("/students")
				.queryParam("firstName", firstName)
				.queryParam("lastName", lastName)
				.queryParam("phoneNumber", phoneNumber)
				.queryParam("emailAddress", email);

		String url = builder.build().toString();

		HttpMethod method = HttpMethod.POST;
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		HttpEntity<Void> entity = new HttpEntity<>(headers);

		ResponseEntity<StudentDTO> response = restTemplate.exchange(url, method, entity, StudentDTO.class);
		if(response.getStatusCodeValue() >= 400) {
			throw new RuntimeException("Error creating student with first name " + firstName + " and last name " + lastName + ".");
		}
		return response.getBody();
	}

	private void updateStudentFirstName(Long id, String firstName) {
		try {
			// Creating a statement
			Statement st = con.createStatement();

			// Executing query
			int m = st.executeUpdate("update students set first_name = '" + firstName + "' where id = " + id);
			System.out.println("Number of rows updated when updating student " + id + ": " + m);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void deleteStudent(Long id) {
		try {
			// Creating a statement
			Statement st = con.createStatement();

			// Executing query
			int m = st.executeUpdate("delete from students where id = " + id);
			System.out.println("Number of rows updated when deleting student " + id + ": " + m);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
