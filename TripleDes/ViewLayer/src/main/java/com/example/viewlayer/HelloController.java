package com.example.viewlayer;

import com.example.model.Key;
import com.example.model.TripleDes;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.BitSet;

public class HelloController extends Window {

    public TextField key1Field;
    public Button key1GenerateButton;
    public Button keysLoadButton;
    public Button keysSaveButton;
    public TextArea plaintextArea;
    public TextArea cryptogramArea;
    public Button encryptButton;
    public Button decryptButton;
    public Button plaintextLoadButton;
    public Button cryptogramLoadButton;
    public Button cryptogramSaveButton;
    public Button plaintextSaveButton;
    public Button plaintextClearButton;
    public Button cryptogramClearButton;
    public TextField key2Field;
    public TextField key3Field;
    public Button key2GenerateButton;
    public Button key3GenerateButton;
    public Button keyAllGenerateButton;

    String plainText;
    String cryptogramText;
    TripleDes tripleDes;
    Key[] keys;
    byte[] byteArray;

    private void openWarningDialog(String text) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Komunikat");
        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        dialog.setContentText(text);
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.showAndWait();
    }

    public int encryptFile(ActionEvent actionEvent) throws Exception {
        tripleDes = new TripleDes();
        if(key1Field.getText().isEmpty()) {
            openWarningDialog("Brak pierwszego klucza");
            return -1; }
        if(key2Field.getText().isEmpty()) {
            openWarningDialog("Brak drugiego klucza");
            return -1;}
        if(key3Field.getText().isEmpty()) {
            openWarningDialog("Brak trzeciego klucza");
            return -1;}
        keys = new Key[3];
        keys[0]= new Key(key1Field.getText());
        keys[1] = new Key(key2Field.getText());
        keys[2] = new Key(key3Field.getText());

        byte[] fileContent;
        byte[] outcome;
        FileChooser fileChooser = new FileChooser();
        File selected = fileChooser.showOpenDialog(this);
        try {
            fileContent = Files.readAllBytes(Path.of(selected.getPath()));
            outcome = tripleDes.encrypt(fileContent, keys);

            File destination = fileChooser.showSaveDialog(this);
            Files.write(destination.toPath(), outcome);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int decryptFile(ActionEvent actionEvent) throws Exception {
        tripleDes = new TripleDes();
        if(key1Field.getText().isEmpty()) {
            openWarningDialog("Brak pierwszego klucza");
            return -1; }
        if(key2Field.getText().isEmpty()) {
            openWarningDialog("Brak drugiego klucza");
            return -1;}
        if(key3Field.getText().isEmpty()) {
            openWarningDialog("Brak trzeciego klucza");
            return -1;}
        keys = new Key[3];
        keys[0]= new Key(key1Field.getText());
        keys[1] = new Key(key2Field.getText());
        keys[2] = new Key(key3Field.getText());

        byte[] fileContent;
        byte[] outcome;
        FileChooser fileChooser = new FileChooser();
        File selected = fileChooser.showOpenDialog(this);
        try {
            fileContent = Files.readAllBytes(Path.of(selected.getPath()));
            outcome = tripleDes.decrypt(fileContent, keys);

            File destination = fileChooser.showSaveDialog(this);
            Files.write(destination.toPath(), outcome);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int encryptMessage(ActionEvent actionEvent) throws Exception {
        plainText = plaintextArea.getText();
        tripleDes = new TripleDes();
        if(key1Field.getText().isEmpty()) {
            openWarningDialog("Brak pierwszego klucza");
            return -1; }
        if(key2Field.getText().isEmpty()) {
            openWarningDialog("Brak drugiego klucza");
            return -1;}
        if(key3Field.getText().isEmpty()) {
            openWarningDialog("Brak trzeciego klucza");
            return -1;}
        if(plaintextArea.toString().isEmpty()) {
            openWarningDialog("Pusta wiadomosc do zakodowania");
            return -1;}
        keys = new Key[3];
        keys[0]= new Key(key1Field.getText());
        keys[1] = new Key(key2Field.getText());
        keys[2] = new Key(key3Field.getText());
        cryptogramText = tripleDes.encrypt(plainText, keys);
        cryptogramArea.setText(cryptogramText);
        return 0;
    }


    public int decryptMessage(ActionEvent actionEvent) throws Exception {
        cryptogramText = cryptogramArea.getText();
        tripleDes = new TripleDes();
        if(key1Field.getText().isEmpty()) {
            openWarningDialog("Brak pierwszego klucza");
            return -1; }
        if(key2Field.getText().isEmpty()) {
            openWarningDialog("Brak drugiego klucza");
            return -1;}
        if(key3Field.getText().isEmpty()) {
            openWarningDialog("Brak trzeciego klucza");
            return -1;}
        if(cryptogramArea.toString().isEmpty()) {
            openWarningDialog("Pusta wiadomosc do zakodowania");
            return -1;}
        keys = new Key[3];
        keys[0]= new Key(key1Field.getText());
        keys[1] = new Key(key2Field.getText());
        keys[2] = new Key(key3Field.getText());
        plainText = tripleDes.decrypt(cryptogramText, keys);
        plaintextArea.setText(plainText);
        return 0;
    }

    public void loadTextFromFile(ActionEvent actionEvent) throws IOException {
        JFileChooser jfc = new JFileChooser();
        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            plainText = Files.readString(Path.of(selectedFile.getPath()));
            plaintextArea.setText(plainText);
        }
    }

    public void loadCryptogramFromFile(ActionEvent actionEvent) throws IOException {
        JFileChooser jfc = new JFileChooser();
        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            cryptogramText = Files.readString(Path.of(selectedFile.getPath()));
            cryptogramArea.setText(cryptogramText);
        }
    }

    public void saveTextFromPlaintextWindow(ActionEvent actionEvent) throws IOException {
        JFileChooser jfc = new JFileChooser();
        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            File file = new File(String.valueOf(selectedFile));
            plainText = plaintextArea.getText();
            Files.writeString(Path.of(file.getPath()), plainText);
        }
    }

    public void saveTextFromCryptogramWindow(ActionEvent actionEvent) throws IOException {
        JFileChooser jfc = new JFileChooser();
        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            File file = new File(String.valueOf(selectedFile));
            cryptogramText = cryptogramArea.getText();
            Files.writeString(Path.of(file.getPath()), cryptogramText);
        }
    }

    public void setPlaintextClearButton(ActionEvent actionEvent) {
        plaintextArea.clear();
        plainText = "";
    }

    public void setCryptogramClearButton(ActionEvent actionEvent) {
        cryptogramArea.clear();
        cryptogramText = "";
    }

    public void loadKeysFromFile(ActionEvent actionEvent) {
    }

    public void saveKeysToFile(ActionEvent actionEvent) {
    }

    public void generateFirstKey(ActionEvent actionEvent) {
    }

    public void generateSecondKey(ActionEvent actionEvent) {
    }

    public void generateThirdKey(ActionEvent actionEvent) {
    }

    public void generateAllKeys(ActionEvent actionEvent) {
    }
}