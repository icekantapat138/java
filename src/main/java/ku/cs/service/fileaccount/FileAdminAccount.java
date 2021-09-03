package ku.cs.service.fileaccount;

import ku.cs.model.User.Account;
import ku.cs.model.User.CustomerAccount;

import java.io.*;

public class FileAdminAccount implements AccountData{

    private String fileDirectoryName;
    private String fileName;
    private Account admin;

    public FileAdminAccount(String fileDirectoryName, String fileName) {
        this.fileDirectoryName = fileDirectoryName;
        this.fileName = fileName;
        checkFileIsExist();
    }

    private void checkFileIsExist() {
        File file = new File(fileDirectoryName);
        if(!file.exists()){
            file.mkdirs();
        }
        String filePath = fileDirectoryName + File.separator + fileName;
        file = new File(filePath);
        if(!file.exists()){
            try {
                file.createNewFile();
            }catch (IOException e){
                System.err.println("Cannot Create " + filePath);
            }
        }
    }

    private void readAdminData() throws IOException {
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        String line = "";
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");

            admin.setPassword(data[2]);
        }
        reader.close();
    }

    @Override
    public CustomerAccount getCustomerData() {
        return null;
    }

    @Override
    public Account getAdminData() {
        try {
            admin = new Account();
            readAdminData();
        } catch (FileNotFoundException e) {
            System.err.println(this.fileName + " not found");
        } catch (IOException e) {
            System.err.println("IOException from reading " + this.fileName);
        }
        return admin;
    }

    @Override
    public void writDataSource(CustomerAccount acc) {
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);

            String line = "admin" + "," + acc.getUsername() + "," + acc.getPassword();

            writer.append(line);
            writer.newLine();

            writer.close();
        } catch (IOException e) {
            System.err.println("Cannot write " + filePath);
        }
    }
}
