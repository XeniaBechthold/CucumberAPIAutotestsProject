package dto;

public class UserDataResponse implements ToJson{
    public int id;
    public String email;
    public String first_name;
    public String last_name;
    public String avatar;

    @Override
    public String toString() {
        return "UserDataResponse{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
