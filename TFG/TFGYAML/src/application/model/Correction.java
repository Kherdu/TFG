package application.model;

import java.util.List;

public class Correction {
	private String message;
	private List<String> hints;
	
	public Correction(){
		
	}
	public Correction(String m, List<String> h){
		this.message=m;
		this.hints=h;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getHints() {
		return hints;
	}

	public void setHints(List<String> hints) {
		this.hints = hints;
	}
}
