package dto;



public class UserGetResponse implements ToJson{
    public UserDataResponse data;
    public SupportResponse support;

    @Override
    public String toString() {
        return "UserGetResponse{" +
                "user=" + data +
                ", support=" + support +
                '}';
    }


}
