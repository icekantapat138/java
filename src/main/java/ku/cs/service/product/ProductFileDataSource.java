package ku.cs.service.product;

import ku.cs.model.customer.Product;
import ku.cs.model.customer.ProductList;

import java.io.*;

public class ProductFileDataSource implements ProductDataSource<ProductList> {

    private String directoryname;
    private String filename;

    public ProductFileDataSource(String directoryname, String filename) {
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
    public ProductList readData() {
        ProductList productList = new ProductList();
        String path = directoryname + File.separator + filename;
        File file = new File(path);

        FileReader reader = null;
        BufferedReader buffer = null;

        try{
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);

            String line = "";
            while ((line = buffer.readLine()) != null) {
                String[] data = line.split(",");
                Product product = new Product(data[0].trim(),
                        data[1].trim(),
                        data[2].trim(),
                        data[3].trim(),
                        data[4].trim(),
                        data[5].trim(),
                        data[6].trim(),
                        data[7].trim());
                productList.addProduct(product); }
            }catch (IOException e){
            e.printStackTrace();
            }finally {
                try {
                    buffer.close();
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        return productList;
    }

    @Override
    public void writeData(ProductList productList) {
        String path = directoryname + File.separator + filename;
        File file = new File(path);

        FileWriter writer = null;
        BufferedWriter buffer = null;
        try {
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);

            buffer.write(productList.toCsv());
            buffer.flush();
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
