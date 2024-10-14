package iostar.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")

@NamedQuery(name = "Category.findALL", query = "SELECT c FROM Category c")
	
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CategoryId")
	private int categoryid;
	@Column(name = "Categoryname", columnDefinition = "nvarchar(50) NOT NULL")
	private String categoryname;
	@Column(name = "Images", columnDefinition = "nvarchar(500) NULL")
	private String images;
	@Column(name = "Status")
	private int status;
	@OneToMany(mappedBy = "category")
	private List<Video> videos;

	public Category() {
		super();
	}

	public int getCategoryid() {
		return categoryid;
	}

	public void setCategoryId(int categoryid) {
		this.categoryid = categoryid;
	}

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<Video> getVideos() {
		return videos;
	}

	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}

	public Video addVideo(Video video) {
		getVideos().add(video);
		video.setCategory(this);
		return video;
	}

	public Video removeVideo(Video video) {
		getVideos().remove(video);
		video.setCategory(null);
		return video;

	}

}
