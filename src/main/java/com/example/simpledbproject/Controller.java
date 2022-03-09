package com.example.simpledbproject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.util.Callback;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Controller {

    @FXML // fx:id="tbTable";
    private TableView<ObservableList> tableView;

    @FXML // fx:id="btnGo";
    private Button btnGo;

    @FXML // fx:id="taText";
    private TextArea textArea;

    private Database db = new Database();
    private ObservableList<ObservableList> data;

    public void go() throws SQLException {
        tableView.getItems().clear();
        tableView.getColumns().clear();

        data = FXCollections.observableArrayList();
        ResultSet rs = db.getResult(textArea.getText());

        for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
            final int j = i;
            TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
            col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param -> new SimpleStringProperty(param.getValue().get(j).toString()));
            tableView.getColumns().addAll(col);
        }

        while (rs.next()) {
            ObservableList<String> row = FXCollections.observableArrayList();
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                row.add(rs.getString(i));
            }
            data.add(row);
        }

        tableView.setItems(data);
    }
}
