package api;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.junit.Test;

import io.restassured.http.ContentType;

public class TestCreatePost {

	private final String TOKEN = "ea9f80cf9c17ab1990ddb6259bc85d85c7f5826f8c71d76d5e76441a5ef5916b";
	private final String pageUrl = "https://gorest.co.in/";
	private final String postTitle = "Here is my title";

	@Test
	public void testCreateUser() {
		String userName = "TestNameAN";
		String userEmail = "test_" + new Random().nextInt(1000) + "@gmail.com";
		String endpointPostUser = "public-api/users";

		Map user = new HashMap<>();
		user.put("name", userName);
		user.put("email", userEmail);
		user.put("gender", "Female");
		user.put("status", "Active");

		// create user
		int userId = given().
				auth().oauth2(TOKEN).
				contentType(ContentType.JSON).
				body(user).
				log().
				all().
			when()
				.post(pageUrl + endpointPostUser).
			then().
			assertThat().
			statusCode(200).
			body("code", is(201)).
			log().
			all().
			extract().path("data.id");

		// create post
		Map post = new HashMap<>();
		post.put("user_id", userId);
		post.put("title", postTitle);
		post.put("body", "Here is the body");

		String endpointPostPosts = "public-api/posts";

		int postId = given().
				auth().oauth2(TOKEN).
				contentType(ContentType.JSON).
				body(post).
				log().all().
			when()
				.post(pageUrl + endpointPostPosts).
			then().assertThat().
			statusCode(200).
			body("code", is(201)).
			log().
			all()
				.extract().path("data.id");

		// get post by user id

		given().
				queryParam("user_id", userId).
				log().
				all().
		when().
				get(pageUrl + endpointPostPosts).
		then().
				assertThat().
				statusCode(200).
				body("code", is(200)).
				body("data", hasSize(1)).
				log().
				all();

		// get post by post id

		given().
			queryParam("id", postId).
			log().
			all().
		when().
			get(pageUrl + endpointPostPosts).
		then().
			assertThat().
			statusCode(200).
			body("code", is(200)).
			body("data", hasSize(1)).
			log().
			all();

		// delete post by post id
		String endpointDeletePost = "public-api/posts/{postId}";

		given().
			auth().
			oauth2(TOKEN).
			pathParam("postId", postId).
			log().
			all().
		when().
			delete(pageUrl + endpointDeletePost).
		then().
			assertThat().
			body("code", is(204)).
			log().
			all();

		// check if post is not retrievable

		given().
			pathParam("postId", postId).
			log().
			all().
		when().
			get(pageUrl + endpointDeletePost).
		then().
			assertThat().
			body("code", is(404)).
			log().
			all();
	}
}