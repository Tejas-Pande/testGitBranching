package com.framework.request;

import com.framework.constants.Constants;
import com.framework.report.ExtentReportManager;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import java.util.HashMap;

public class SendRequest {

	public Response sendGETRequest(String resource) {

		Response res = given().when().get(resource).then().extract().response();

		return res;

	}

	public Response sendPOSTRequest(String resource, HashMap<String, String> header, Object body) {

		return given().headers(header).body(body).when().post(resource).then().extract().response();

	}

}
