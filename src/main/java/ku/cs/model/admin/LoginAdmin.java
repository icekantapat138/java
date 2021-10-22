package ku.cs.model.admin;

import ku.cs.model.User.AdminAccount;
import ku.cs.model.User.CustomerAccount;

public class LoginAdmin {
    private static AdminAccount adminAccount;

    public LoginAdmin(AdminAccount adminAccount) {
        this.adminAccount = adminAccount;
    }

    public static AdminAccount getAdminAccount() {
        return adminAccount;
    }
}
