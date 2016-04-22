package sample.Controllers;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import sample.Interfaces.Impl.CollectionDeposits;
import sample.Objects.Deposit;

/**
 * Created by Павел on 21.04.2016.
 */
public class DeleteDialogController {
    private CollectionDeposits collectionDepositsImpl = new CollectionDeposits();
    @FXML
    private TextArea areaInfo;
    @FXML
    private ComboBox selectDeposit;
    @FXML
    private Button buttonDelete;
    @FXML
    private Button buttonClose;

    private Deposit deposit;

    public void setDeposit(Deposit deposit){
        this.deposit=deposit;
    }
    public Deposit getDeposit(){
        return  deposit;
    }
    @FXML
    private void initialize(){
        collectionDepositsImpl.fillTestData();
        fillComboBoxDelete(collectionDepositsImpl.getListDeposits());
        areaInfo.setVisible(false);
        buttonClose.setVisible(false);
        buttonDelete.setVisible(false);
        collectionDepositsImpl.getListDeposits().addListener(new ListChangeListener<Deposit>() {
            @Override
            public void onChanged(Change<? extends Deposit> c) {
                selectDeposit.getItems().clear();
                areaInfo.setText("");
                fillComboBoxDelete(collectionDepositsImpl.getListDeposits());
            }
        });

    }
    private void fillComboBoxDelete(ObservableList<Deposit> listDeposits){
        selectDeposit.getItems().add(0, "Выберите вклад");
        int i = 1;
        for (Deposit deposit : listDeposits) {
            selectDeposit.getItems().add(i, deposit.getName());
            i++;
        }
    }
    private void fillAreaInfo(ObservableList<Deposit> allDeposits){
        String s=" ";
        for (Deposit deposit : allDeposits) {
            if (selectDeposit.getValue() == deposit.getName()) {
              s=      "Название вклада: "+deposit.getName()+"\r\n"+
                      "Процкнтная ставка: "+deposit.getInsertRate()+"\r\n"+
                      "Информация о вкладе: "+deposit.getInfo()+"\r\n"+
                      "Минимальная сумма вкладов: "+deposit.getMinSum();
                areaInfo.setText(s);
            }
            s=" ";
        }
    }

    public void showInfo(ActionEvent actionEvent) {
        if(selectDeposit.getValue()=="Выберите вклад"){
            areaInfo.setVisible(false);
            buttonDelete.setVisible(false);
            buttonClose.setVisible(false);


        }
        else{
            fillAreaInfo(collectionDepositsImpl.getListDeposits());
            areaInfo.setVisible(true);
            buttonDelete.setVisible(true);
            buttonClose.setVisible(true);
        }

    }
    public void actionDelete(ObservableList<Deposit>allDeposits){
        for(Deposit dep:allDeposits){
            if(areaInfo.getText().contains(dep.getName())){
                setDeposit(dep);
                break;
            }
        }

    }

    public void deleteDeposit(ActionEvent actionEvent) {
       actionDelete(collectionDepositsImpl.getListDeposits());
        closeDialog(actionEvent);
    }

    public void closeDialog(ActionEvent actionEvent) {
        Node source =(Node) actionEvent.getSource();
        Stage stage=(Stage) source.getScene().getWindow();
        stage.hide();
    }
}