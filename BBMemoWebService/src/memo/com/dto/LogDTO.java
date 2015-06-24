package memo.com.dto;

public class LogDTO {
	String id;
	String pwd;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;	
	}

	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	@Override
	public String toString() {
		return "id :: "+id+", pwd :: "+pwd;
	}
	

	LogDTO() {
	}
	LogDTO(String id, String pwd){
		this.id = id;
		this.pwd = pwd;
		
	}
	
}
