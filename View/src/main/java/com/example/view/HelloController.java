package com.example.view;

import com.example.model.Block;
import com.example.model.DES;
import com.example.model.TripleDES;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.BitSet;

public class HelloController extends Window {
    @FXML
    Button szyfruj;
    @FXML
    Button deszyfruj;
    @FXML
    Button key1GenerateButton;
    @FXML
    Button key2GenerateButton;
    @FXML
    Button key3GenerateButton;
    @FXML
    Button keyAllGenerateButton;
    @FXML
    Button keysSaveButton;
    @FXML
    Button keysLoadButton;
    @FXML
    TextField key1Field;
    @FXML
    TextField key2Field;
    @FXML
    TextField key3Field;

    TripleDES des;

    private void openWarningDialog(String text) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Komunikat");
        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        dialog.setContentText(text);
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.showAndWait();
    }

    @FXML protected int encryptMessage(ActionEvent event) {
//        if(key1Field.getText().isEmpty()) {
//            openWarningDialog("Brak pierwszego klucza");
//            return -1; }
//        if(key2Field.getText().isEmpty()) {
//            openWarningDialog("Brak drugiego klucza");
//            return -1;}
//        if(key3Field.getText().isEmpty()) {
//            openWarningDialog("Brak trzeciego klucza");
//            return -1;}

        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(this);
        try {
            byte[] fileBytes = Files.readAllBytes(selectedFile.toPath());
            String s = "aaaaaaaa";
            byte[] keyBit = s.getBytes(StandardCharsets.UTF_8);
            Block key = new Block(keyBit);

            byte[] encodedBytes = des.encrypt(key,fileBytes);

            File destination = fileChooser.showSaveDialog(this);
            Files.write(destination.toPath(), encodedBytes);

//            notificationEncryptionLabel.setTextFill(Color.web("#00FF00"));
//            notificationEncryptionLabel.setText("File encrypted successfully");
        } catch (IOException e) {
            e.printStackTrace();
//            notificationEncryptionLabel.setTextFill(Color.web("#FF0000"));
//            notificationEncryptionLabel.setText("Error occured :(");
        }
        return 0;
    }

    @FXML protected int decryptMessage(ActionEvent event) {
//        if(key1Field.getText().isEmpty()) {
//            openWarningDialog("Brak pierwszego klucza");
//            return -1; }
//        if(key2Field.getText().isEmpty()) {
//            openWarningDialog("Brak drugiego klucza");
//            return -1;}
//        if(key3Field.getText().isEmpty()) {
//            openWarningDialog("Brak trzeciego klucza");
//            return -1;}

        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(this);
        try {
            byte[] fileBytes = Files.readAllBytes(selectedFile.toPath());
            String s = "aaaaaaaa";
            byte[] keyBit = s.getBytes(StandardCharsets.UTF_8);
            Block key = new Block(keyBit);

            byte[] encodedBytes = des.decrypt(key,fileBytes);

            File destination = fileChooser.showSaveDialog(this);
            Files.write(destination.toPath(), encodedBytes);

//            notificationEncryptionLabel.setTextFill(Color.web("#00FF00"));
//            notificationEncryptionLabel.setText("File encrypted successfully");
        } catch (IOException e) {
            e.printStackTrace();
//            notificationEncryptionLabel.setTextFill(Color.web("#FF0000"));
//            notificationEncryptionLabel.setText("Error occured :(");
        }
        return 0;
    }
}