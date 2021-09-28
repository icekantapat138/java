package ku.cs.service.fileaccount;

import ku.cs.model.User.AdminAccount;

import java.io.*;

public class AdminFileAccountDataSource implements FileAccountDataSource<AdminAccount> {

    private String directoryName;
    private String filename;

    public AdminFileAccountDataSource(String directoryName, String filename) {
        this.directoryName = directoryName;
        this.filename = filename;
        initialFileNotExist();
    }

    private void initialFileNotExist() {
        File file = new File(directoryName);
        if ((!file.exists())){
            file.mkdirs();
        }

        String path = directoryName + File.separator + filename;
        file = new File(path);
        if(!file.exists()){
            try {
                file.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }


    @Override
    public AdminAccount readData() {
        return null;
    }

    @Override
    public void writeData(AdminAccount adminAccount) {

    }
}
