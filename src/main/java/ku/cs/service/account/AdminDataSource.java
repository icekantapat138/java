package ku.cs.service.account;

import ku.cs.model.User.AdminAccount;
import ku.cs.model.User.AdminAccountList;

import java.io.*;

public class AdminDataSource implements AccountDataSource<AdminAccountList> {
    private String directoryname;
    private String filename;

    public AdminDataSource(String directoryname, String filename) {
        this.directoryname = directoryname;
        this.filename = filename;
        createfileIsNotExist();
    }

    public AdminDataSource() {
        this("data", "admin.csv");
    }

    private void createfileIsNotExist() {
        File file = new File(directoryname);
        if ((!file.exists())) {
            file.mkdirs();
        }

        String path = directoryname + File.separator + filename;
        file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public AdminAccountList readData() {
        AdminAccountList adminAccountList = new AdminAccountList();
        String path = directoryname + File.separator + filename;
        File file = new File(path);

        FileReader reader = null;
        BufferedReader buffer = null;

        try{
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);

            String line = ",";
            while ((line = buffer.readLine()) != null){
                String[] data = line.split(",");
                AdminAccount adminAccount = new AdminAccount(data[0].trim(),data[1].trim() );
                    adminAccountList.addAdminAccount(adminAccount);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                buffer.close();
                reader.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return adminAccountList;
    }

    @Override
    public void writeData(AdminAccountList adminAccountList) {
        String path = directoryname + File.separator + filename;
        File file = new File(path);

        FileWriter writer = null;
        BufferedWriter buffer = null;
        try {
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);

            buffer.write(adminAccountList.toCsv());
            buffer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                buffer.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
