package ex.controller;

public class AdminFormResponse {
private String authToken;
    
    public AdminFormResponse(String authToken) {
        this.authToken = authToken;
    }
    
    // Getter e Setter
    public String getAuthToken() {
        return authToken;
    }
    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
