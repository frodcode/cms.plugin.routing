package module

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.codehaus.groovy.grails.web.servlet.mvc.GrailsWebRequest;

class Request implements Map {
	
	def full;
	
	def params;
	
	public def getHeader(String headerName)
	{
		return full.getHeader(headerName)
	}

	@Override
	public int size() {
		return params.size();
	}

	@Override
	public boolean isEmpty() {
		return params.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return params.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return params.containsValue(value);
	}

	@Override
	public Object get(Object key) {
		return params.get(key);
	}

	@Override
	public Object put(Object key, Object value) {
		return params.put(key, value);
	}

	@Override
	public Object remove(Object key) {
		return params.remove(key);
	}

	@Override
	public void putAll(Map m) {
		params.putAll(m);
	}

	@Override
	public void clear() {
		params.clear();
	}

	@Override
	public Set keySet() {
		return params.keySet();
	}

	@Override
	public Collection values() {
		return params.values();
	}

	@Override
	public Set entrySet() {
		return params.entrySet();
	}

}
