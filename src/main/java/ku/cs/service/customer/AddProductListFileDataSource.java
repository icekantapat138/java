package ku.cs.service.customer;

import ku.cs.model.customer.AddProductList;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AddProductListFileDataSource implements CustomerDataSource<AddProductList> {

    private String directoryName;
    private String filename;

    public AddProductListFileDataSource(String directoryName, String filename) {
        this.directoryName = directoryName;
        this.filename = filename;
        initialFileIsNotExist();
    }

    private void initialFileIsNotExist() {
        File file = new File(directoryName);
        if (!file.exists()) {
            file.mkdir();
        }

        String path = directoryName + File.separator + filename;
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
    public AddProductList readData() {
        return null;
    }

    @Override
    public void writeData(AddProductList addProductList) {
        String path = directoryName + File.separator + filename;
        File file = new File(path);

        FileWriter writer = null;
        BufferedWriter buffer = null;

        System.out.println(addProductList.toCsv());

        try {
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);
            buffer.write(addProductList.toCsv());
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                buffer.close();
                writer.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
