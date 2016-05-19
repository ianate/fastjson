package io.github.ianate;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;

class Outter{
	
	private String name = "outter";
	private Inner inner = new Inner();
	
	class Inner{
		private String name = "inner";
        private innerInner ii = new innerInner();
        
        class InnerInner{
            private String name = "innerInner";
        }
	}
}

public class InnerSerializeTest {
	
    //easy test
	public static void main(String[] args){
		Outter obj = new Outter();
		Gson gson = new Gson();
		String jsonFromGson = gson.toJson(obj);
		System.out.println(jsonFromGson);
		String jsonFromFastJson = JSON.toJSONString(obj);
		System.out.println(jsonFromFastJson);
	}
	
}
