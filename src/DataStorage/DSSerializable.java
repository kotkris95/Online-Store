package DataStorage;

import java.io.*;

public class DSSerializable implements DataStorage, Serializable {
    @Override
    public void saveTo(String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(this);
        } catch (IOException e) {
            System.out.println("IOException");
        }

    }

    @Override
    public DataStorage loadFrom(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            DataStorage dataStorage = (DataStorage) in.readObject();
            return dataStorage;
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException");
        } catch (IOException ex) {
            System.out.println("IOException");
        }
        return null;
    }
}
