package Entity;

public class Comment {
	String Id;
    String NewsId;  
    String UserId;          
    String content;       
    String CommentDate;
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getNewsId() {
		return NewsId;
	}
	public void setNewsId(String newsId) {
		NewsId = newsId;
	}
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCommentDate() {
		return CommentDate;
	}
	public void setCommentDate(String commentDate) {
		CommentDate = commentDate;
	}
}
