package dto;

public class ResourceListResponse {

    public int page;
    public int per_page;
    public int total;
    public int total_pages;
    public ResourceResponse[] resourceResponses;
    public SupportResponse support;
}
