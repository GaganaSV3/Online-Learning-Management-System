package com.learnpro.lms.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Course {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false)
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String category;
    private String duration;
    private String level;
    private String instructorName;
    private Boolean isPublished = false;
    private Instant createdAt = Instant.now();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Module> modules = new ArrayList<>();

	public Object getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Course> getModules() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setTitle(Object object) {
		// TODO Auto-generated method stub
		
	}

	public Object getCategory() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getInstructorName() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getDescription() {
		// TODO Auto-generated method stub
		return null;
	}
}
