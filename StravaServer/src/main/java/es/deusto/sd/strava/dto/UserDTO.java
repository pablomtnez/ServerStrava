package es.deusto.sd.strava.dto;

public class UserDTO {
	private static final long serialVersionUID = 1L;
	private String username;
	private String email;
	private String bornDate;
	private int peso;
	private int altura;
	public UserDTO(String username, String email, String bornDate, int peso, int altura) {
		super();
		this.username = username;
		this.email = email;
		this.bornDate = bornDate;
		this.peso = peso;
		this.altura = altura;
	}
	
	public UserDTO() {
		super();
		this.username = "";
		this.email = "";
		this.bornDate = "";
		this.peso = 0;
		this.altura = 0;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBornDate() {
		return bornDate;
	}

	public void setBornDate(String bornDate) {
		this.bornDate = bornDate;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	@Override
	public String toString() {
		return "UserDTO [username=" + username + ", email=" + email + ", bornDate=" + bornDate + ", peso=" + peso
				+ ", altura=" + altura + "]";
	}

	
	
	
}
