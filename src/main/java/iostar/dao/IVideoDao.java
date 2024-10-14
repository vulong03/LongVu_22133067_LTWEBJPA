package iostar.dao;
import java.util.List;

import iostar.entity.Video;

public interface IVideoDao {
	int count();

    List<Video> findAll(int page, int pageSize);

    List<Video> findByVideoTitle(String title);

    List<Video> findAll();

    Video findById(String videoId);

    void delete(int videoId) throws Exception;

    void update(Video video);

    void insert(Video video);
}
