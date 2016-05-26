package io.github.ianate;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;

import io.github.ianate.ListOutter.Inner;
import junit.framework.TestCase;

class ListOutter{
	private List<Inner> inners;
	
	public List<Inner> getInners() {
		return inners;
	}

	public void setInners(List<Inner> inners) {
		this.inners = inners;
	}

	class Inner{
		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
}

public class InnerListTest extends TestCase{

	public void testSerialize(){
		ListOutter o = new ListOutter();
		Inner i1 = o.new Inner();
		i1.setName("inner1");
		Inner i2 = o.new Inner();
		i2.setName("inner2");
		Inner i3 = o.new Inner();
		List<Inner> is = new ArrayList<ListOutter.Inner>();
		is.add(i1);
		is.add(i2);
		is.add(i3);
		o.setInners(is);
		String json = JSON.toJSONString(o);
		assertEquals("{\"inners\":[{\"name\":\"inner1\"},{\"name\":\"inner2\"},{}]}", json);
	}
	
	public void testDeserialize(){
		String json = "{\"inners\":[{\"name\":\"inner1\"},{\"name\":\"inner2\"},{}]}";
		ListOutter o = JSON.parseObject(json, ListOutter.class);
		List<Inner> inners = o.getInners();
		assertNotNull(inners);
		assertEquals("inner1", inners.get(0).getName());
		assertEquals("inner2", inners.get(1).getName());
	}
	
	public void testGson(){//accordance
		ListOutter o = new ListOutter();
		Inner i1 = o.new Inner();
		i1.setName("inner1");
		Inner i2 = o.new Inner();
		i2.setName("inner2");
		Inner i3 = o.new Inner();
		List<Inner> is = new ArrayList<ListOutter.Inner>();
		is.add(i1);
		is.add(i2);
		is.add(i3);
		o.setInners(is);
		Gson gson = new Gson();
		String json = gson.toJson(o);
		assertEquals("{\"inners\":[{\"name\":\"inner1\"},{\"name\":\"inner2\"},{}]}", json);
		ListOutter no = gson.fromJson(json, ListOutter.class);
		List<Inner> inners = no.getInners();
		assertNotNull(inners);
		assertEquals("inner1", inners.get(0).getName());
		assertEquals("inner2", inners.get(1).getName());
	}
}
