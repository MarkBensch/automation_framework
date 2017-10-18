package com.example.util

import com.example.util.error.RestException
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import org.apache.commons.codec.binary.Base64
import org.apache.http.HttpResponse
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.client.utils.URIBuilder

class RestUtil {
	HttpClient client = HttpClientBuilder.create().build()
	
	def restPost(def url, def headers = [:], def body =[:]){
	
	}
	def restGet(def url, def headers = [:], def param = [:]){
		String parameter = buildParameters(param)
		String fullUrl = url + parameter
		
		HttpGet request = new HttpGet(fullUrl)
		headers.each{ key, value ->
			request.addHeader(key,value)
		}
		return request
	}
	def executeWithJson(def method){
		return responseToJson(client.execute(method))
	}
	def responseToJson(def resp){
		def body
		def slurper = new JsonSlurper()
		def headers = resp.getHeaders
		def status = ["code":resp.getStatusLine().getStatusCode(),"phrase":resp.getStatusLine().getReasonPhrase() ]
		if(resp.getEntity().getContentType() == "application/json"){
			body = slurper.parseText(resp.getEntity.getContent())
		}else{
			body = resp.getEntity.getContent()
		}
		return ["status":status,"headers":headers,"body":body]
		
	}
	def buildJsonBody(def bodyMap){
		return JsonOutput.toJson(bodyMap)
	}
	
	def buildParameters(def param){
		URIBuilder builder = new URIBuilder()
		param.each { key, value ->
			builder.addParameter(key, value)
		}
	}
	
	def createBasicAuth(user, pass){
		String auth = "$user:$pass"
		//byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")))
		byte[] encodedAuth = null
		return "Basic ${encodedAuth.toString()}"
	}
	// us URLBuilder for .addparameter()
	
}
