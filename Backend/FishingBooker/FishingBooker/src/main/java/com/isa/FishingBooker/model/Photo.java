package com.isa.FishingBooker.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Photo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
    private String url;
    private String title;

    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tutorservice_id",referencedColumnName = "tutor_service_id")
	private TutorService tutorService;
    
    public TutorService getTutorService() {
		return tutorService;
	}

	public void setTutorService(TutorService tutorService) {
		this.tutorService = tutorService;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Photo(String url, String title) {
        this.url = url;
        this.title = title;
    }
	public Photo() {
		// TODO Auto-generated constructor stub
	}

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}