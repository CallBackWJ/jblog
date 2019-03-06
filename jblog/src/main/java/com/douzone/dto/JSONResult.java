package com.douzone.dto;


//api 프로토콜을 정하는 클래스
public class JSONResult  {

	private String result;  //'success','fail'
	
	private String message; // 'result 가 fail'이면 에러내용
							//           success 이면 null;
	
	private Object data;    // 'result 가 fail'이면 null
							//           success 이면 객체;
	
	
	private JSONResult(String result,String message, Object data)
	{
		this.result=result;
		this.message=message;
		this.data=data;
	}
	
	
	
	
	public String getResult() {
		return result;
	}
	
	public String getMessage() {
		return message;
	}
	
	public Object getData() {
		return data;
	}




	public static JSONResult success(Object data) {

		return new JSONResult("success", null, data);
	}
	public static JSONResult fail(String message) {

		return new JSONResult("fail", message, null);
	}
	
	
	
}
