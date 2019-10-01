package co.edu.usbcali.demo.vehiculo;

public class Motor {
	
	private Integer cc;
	private String mark;
	private Boolean electric;
	
	
	public Motor() {
		
	}
	
	
	public Motor(Integer cc, String mark, Boolean electric) {
		super();
		this.cc = cc;
		this.mark = mark;
		this.electric = electric;
	}


	//Setters and Getters
	public Integer getCc() {
		return cc;
	}
	public void setCc(Integer cc) {
		this.cc = cc;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public Boolean getElectric() {
		return electric;
	}
	public void setElectric(Boolean electric) {
		this.electric = electric;
	}
	
	

}
