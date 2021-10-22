package ku.cs.service.account;

import ku.cs.model.User.CustomerAccount;
import ku.cs.model.User.CustomerAccountList;

import java.io.*;

public class CustomerDataSource implements AccountDataSource<CustomerAccountList> {
    private String directoryname;
    private String filename;

    public CustomerDataSource(String directoryname, String filename) {
        this.directoryname = directoryname;
        this.filename = filename;
        createfileIsNotExist();
    }

    public CustomerDataSource() {
        this("data", "customer.csv");
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
    public CustomerAccountList readData() {
        CustomerAccountList customerAccountList = new CustomerAccountList();
        String path = directoryname + File.separator + filename;
        File file = new File(path);

        FileReader reader = null;
        BufferedReader buffer = null;

        try{
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);

            String line = "";
            while ((line = buffer.readLine()) != null){
                String[] data = line.split(",");
                CustomerAccount css = new CustomerAccount(data[0].trim(),
                        data[1].trim(),
                        data[2].trim(),
                        data[3].trim(),
                        data[4].trim(),
                        data[5].trim(),
                        data[6].trim(),
                        data[7].trim(),
                        data[8].trim());
            customerAccountList.addCustomerAccount(css);}

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
        return customerAccountList;
    }

    @Override
    public void writeData(CustomerAccountList customerAccountList) {
        String path = directoryname + File.separator + filename;
        File file = new File(path);

        FileWriter writer = null;
        BufferedWriter buffer = null;
        try {
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);

            buffer.write(customerAccountList.toCsv());
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
