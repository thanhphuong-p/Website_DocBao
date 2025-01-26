package Entity;

public class NewsStatistics {
    private String newsId;
    private String title;
    private int totalViews;
    private int totalComments;

    public NewsStatistics(String newsId, String title, int totalViews, int totalComments) {
        this.newsId = newsId;
        this.title = title;
        this.totalViews = totalViews;
        this.totalComments = totalComments;
    }
    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTotalViews() {
        return totalViews;
    }

    public void setTotalViews(int totalViews) {
        this.totalViews = totalViews;
    }

    public int getTotalComments() {
        return totalComments;
    }

    public void setTotalComments(int totalComments) {
        this.totalComments = totalComments;
    }
}
