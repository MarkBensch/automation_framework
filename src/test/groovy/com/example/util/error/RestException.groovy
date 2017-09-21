package com.example.util.error


class RestException extends Exception{
	String str1;
	RestException(java.lang.String str) {
		str1 = str
	}
	String toString(){
		return("RestException Occurred: $str1")
	}
}
