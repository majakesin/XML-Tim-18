package rent.app.service;

import org.springframework.stereotype.Service;
import rent.app.dto.CommentDTO;

import java.util.List;

@Service
public interface CommentService {

    List<CommentDTO> getAllCommentsOfAd(Long adId);

    boolean addNewComment(CommentDTO newComment);
}