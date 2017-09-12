package com.amorais.pokemon.util;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import flexjson.JSONSerializer;

@SuppressWarnings({"rawtypes"})
public class DataTablePage {

	private int draw;
	private int start;
	private int length;
	private int page;
	
	private String search;
	
	private String orderColumn;
	private boolean orderAsc;
	
	private long recordsTotal;
	private long recordsFiltered;
	
	private List data;

	public DataTablePage(String jsonParams) throws JSONException {
		
		JSONObject root = new JSONObject(jsonParams);
		
		this.draw = root.getInt("draw");
		this.start = root.getInt("start");
		this.length = root.getInt("length");
		this.page = start / length;
		
		JSONObject searchJson = root.getJSONObject("search");
		this.search = searchJson.getString("value");
		
		JSONObject orderJson = root.getJSONArray("order").getJSONObject(0);
		int orderColumnIndex = orderJson.getInt("column");
		this.orderAsc = "asc".equals(orderJson.getString("dir"));
		
		JSONObject columnJson = root.getJSONArray("columns").getJSONObject(orderColumnIndex);
		this.orderColumn = columnJson.getString("data");
	}
	
	public String toJson(List data, long recordsTotal) {
		return toJson(data, recordsTotal, null, null);
	}

	public String toJson(List data, long recordsTotal, String[] includes, String[] excludes) {
		this.data = data;
		this.recordsTotal = recordsTotal;
		this.recordsFiltered = recordsTotal;
		
		JSONSerializer serializer = new JSONSerializer();
		serializer.exclude("*.class");
		serializer.exclude("json");
		serializer.exclude("dataFull");
		
		if(excludes != null) {
			for(String exclude : excludes) {
				serializer.exclude(exclude);
			}
		}
		
		serializer.include("data");
		
		if(includes != null) {
			for(String include : includes) {
				serializer.include(include);
			}
		}
		
		return serializer.serialize(this);
	}

	public int getDraw() {
		return draw;
	}

	public int getStart() {
		return start;
	}

	public int getLength() {
		return length;
	}

	public int getPage() {
		return page;
	}

	public long getRecordsTotal() {
		return recordsTotal;
	}

	public long getRecordsFiltered() {
		return recordsFiltered;
	}
	
	public String getSearch() {
		return search;
	}

	public String getOrderColumn() {
		return orderColumn;
	}

	public boolean getOrderAsc() {
		return orderAsc;
	}

	public List getData() {
		return data;
	}
}
