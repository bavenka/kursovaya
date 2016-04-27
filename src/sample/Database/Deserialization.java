package sample.Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Objects.Deposit;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Created by Павел on 10.04.2016.
 */
public class Deserialization {
    public static ObservableList<Deposit> readBD(String fileName){
        ArrayList<Deposit> deposits= new ArrayList();
        try{
            XMLDecoder reader=new XMLDecoder(new BufferedInputStream(new FileInputStream(fileName)));
            deposits=( ArrayList<Deposit>)reader.readObject();
            reader.close();
        }catch (FileNotFoundException e){
            System.out.println("Файл не найден!");
        }
        ObservableList<Deposit>rezult=FXCollections.observableArrayList(deposits);
        return rezult;
    }
}
