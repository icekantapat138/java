package ku.cs.model.User;

import java.util.ArrayList;

public class AdminAccountList {
    private ArrayList<AdminAccount> adminList;

    public AdminAccountList() {
        adminList = new ArrayList<>();
    }

    public void addAdminAccount(AdminAccount acc) {
        adminList.add(acc);
    }

    public ArrayList<AdminAccount> getAllAdminAccount() {
        return adminList;
    }

    public int countAdminAccount() {
        return adminList.size();
    }

    public AdminAccount searchUser(String username){
        for(AdminAccount acc : adminList){
            if(acc.checkUsername(username)){
                return acc;
            }
        }
        return null;
    }

    public boolean checkUserandPw(String username,String password){
        AdminAccount acc = searchUser(username);
        if(acc.checkUsername(username) && acc.checkPassword(password)){
            return true;
        }
        return false;
    }

    public String toCsv(){
        String result = "";
        for (AdminAccount adminAccount : this.adminList){
            result += adminAccount.toCsv() + "\n";
        }
        return result;
    }

    @Override
    public String toString() {
        return "AdminAccountListModelClass{" +
                "adminAccount=" + adminList +
                '}';
    }
}
