package dto;

public class UsersListResponse {

    public int page;
    public int per_page;
    public int total;
    public int total_pages;
    public UserDataResponse [] data;
    public SupportResponse support;
}
