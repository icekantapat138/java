package ku.cs.service.fileaccount;

import ku.cs.model.User.CustomerAccount;

import java.io.*;

public class CustomerFileAccountDataSource implements FileAccountDataSource<CustomerAccount> {

    private String directoryName;
    private String filename;

    public CustomerFileAccountDataSource(String directoryName, String filename) {
        this.directoryName = directoryName;
        this.filename = filename;
        createfileIsNotExist();
    }

    private void createfileIsNotExist() {
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
    public CustomerAccount readData() {
        CustomerAccount customer = new CustomerAccount();

        String path = directoryName + File.separator + filename;
        File file = new File(path);

        FileReader reader = null;
        BufferedReader buffrr = null;

        try {
            reader = new FileReader(file);
            buffrr = new BufferedReader(reader);
            String line = "";
            while ((line = buffrr.readLine()) != null){
                String[] data = line.split(",");
                CustomerAccount customerAccount = new CustomerAccount(
                        data[1],
                        data[2],
                        data[3],
                        data[4],
                        data[5],
                        data[6],
                        data[7],
                        data[8]
                );

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                buffrr.close();
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return customer;
    }

    @Override
    public void writeData(CustomerAccount customerAccount) {
        String path = directoryName + File.separator + filename;
        File file = new File(path);

        FileWriter writer = null;
        BufferedWriter buffer = null;

        System.out.println(customerAccount.toString());

        try {
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);
            buffer.write(customerAccount.toString());
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
