package iostar.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="videos")
@NamedQuery(name="Video.findAll", query="SELECT v FROM Video v")
public class Video implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	 @Column(name="VideoId")
	 private String videoId;
	 
	@Column(name="Active")
	 private int active;

	 @Column(name="Description",columnDefinition = "NVARCHAR(500)")
	 private String description;

	 @Column(name="Poster")
	 private String poster;

	 @Column(name="Title",columnDefinition = "NVARCHAR(500)")
	 private String title;

	 @Column(name="Views")
	 private int views;
	//bi-directional many-to-one association to Favorite


//	 @OneToMany(mappedBy="video")
//	 private List<Favorite> favorites;
//
//	 //bi-directional many-to-one association to Share
//	 @OneToMany(mappedBy="video")
//	 private List<Share> shares;



	 //bi-directional many-to-one association to Category
	 @ManyToOne
	 @JoinColumn(name="CategoryId")
	 private Category category;
	 
	public Video() {
	
	}
	public String getVideoId() {
		return videoId;
	}
	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	 
}
