package ex.controller;


import ex.model.Admin;

public class AdminFormRequest {
	private Long id;
	private String username;
    private String senha;
    private String authToken;
    
    
    public AdminFormRequest(Long id, String username, String senha, String authToken) {
		super();
		this.id = id;
		this.username = username;
		this.senha = senha;
		this.authToken = authToken;
	}
    
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public static AdminFormRequest fromModel(Admin admin) {
		return new AdminFormRequest(admin.getId(),admin.getUsername(),admin.getSenha(),admin.getAuthToken());
	}
}
