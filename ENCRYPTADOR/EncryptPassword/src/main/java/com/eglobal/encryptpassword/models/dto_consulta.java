package com.eglobal.encryptpassword.models;

public class dto_consulta {
	
	private String json;
	private String script;
	
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	public String getScript() {
		return script;
	}
	public void setScript(String script) {
		this.script = script;
	}
	public dto_consulta(String json, String script) {
		super();
		this.json = json;
		this.script = script;
	}
	public dto_consulta() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
