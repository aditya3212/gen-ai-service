package com.example.genaiservice.apis.user;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import lombok.Data;


@Data
@Document(collection = "user")
public class UserEntity {
	
    @Id
    private String _id;
    
    private String name;
    
    
    @Field("user_id")
    private String userId;
    
    private String email;
    
    @Field("phone_no")
    private String phoneNo;
    
    private String password;
    
    @Field("enc_password")
    private String encPassword;
    
    @Field("private_field_url")
    private String profilePicUrl;
    
    private String role;
    
    @Field("last_login")
    private String lastLogin;
        
    @Field("session_id")
    private String sessionId;
    
    @Field("created_at")
    private Date createdAt;

    @Field("updated_at")
    private Date updatedAt;

}

