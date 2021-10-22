package ku.cs.service.product;

import ku.cs.model.customer.ProductCheck;
import ku.cs.model.customer.ProductCheckList;

import java.io.*;

public class ProductCheckFileDataSource implements ProductDataSource<ProductCheckList> {

    private String directoryname;
    private String filename;

    public ProductCheckFileDataSource(String directoryname, String filename) {
        this.directoryname = directoryname;
        this.filename = filename;
        createfileIsNotExist();
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
    public ProductCheckList readData() {
        ProductCheckList productCheckList = new ProductCheckList();
        String path = directoryname + File.separator + filename;
        File file = new File(path);

        FileReader reader = null;
        BufferedReader buffer = null;

        try {
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);

            String line = "";
            while ((line = buffer.readLine()) != null) {
                String[] data = line.split(",");
                ProductCheck productCheck = new ProductCheck(data[0].trim(),
                        data[1].trim(),
                        data[2].trim(),
                        data[3].trim());
                productCheckList.addProductCheck(productCheck);
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
        return productCheckList;
    }

    @Override
    public void writeData(ProductCheckList productCheckList) {
        String path = directoryname + File.separator + filename;
        File file = new File(path);

        FileWriter writer = null;
        BufferedWriter buffer = null;
        try {
            writer = new FileWriter(file, false);
            buffer = new BufferedWriter(writer);

            buffer.write(productCheckList.toCsv());
            buffer.newLine();

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
