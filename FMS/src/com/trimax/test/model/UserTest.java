package com.trimax.test.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="student_test")
public class UserTest {
	
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
	    private int id;
	
		@Column 
	    private String name;
		
		@Column 
	    private String password;
		
		@Column 
	    private int rollno;
	    
	    
	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public int getRollno() {
	        return rollno;
	    }

	    public void setRollno(int rollno) {
	        this.rollno = rollno;
	    }
	    
	    
	}



