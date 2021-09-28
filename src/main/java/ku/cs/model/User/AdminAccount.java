package ku.cs.model.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AdminAccount extends Account {

    private String time;

    public AdminAccount(String username, String password, String time) {
        super(username, password);
        this.time = time;
    }

    public void setDate() {
        LocalDateTime currenttime = LocalDateTime.now();
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("dd.MMM.yyyy kk:mm:ss");
        String time = formatTime.format(currenttime);
        this.time = time;
    }

    public String toString() {
        return "Admin" + "," +
                getUsername() + "," +
                getPassword() + "," +
                time;
    }

}
