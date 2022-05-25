package com.example.viewlayer;

import com.example.model.Key;
import com.example.model.StringByteConverter;
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
//    TripleDes tripleDes;
//    Key[] keys;
//    Key first;
//    Key second;
//    Key third;
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
        TripleDes tripleDes = new TripleDes();
        if(key1Field.getText().isEmpty()) {
            openWarningDialog("Brak pierwszego klucza");
            return -1; }
        if(key2Field.getText().isEmpty()) {
            openWarningDialog("Brak drugiego klucza");
            return -1;}
        if(key3Field.getText().isEmpty()) {
            openWarningDialog("Brak trzeciego klucza");
            return -1;}
        Key[] keys = new Key[3];
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
        TripleDes tripleDes = new TripleDes();
        if(key1Field.getText().isEmpty()) {
            openWarningDialog("Brak pierwszego klucza");
            return -1; }
        if(key2Field.getText().isEmpty()) {
            openWarningDialog("Brak drugiego klucza");
            return -1;}
        if(key3Field.getText().isEmpty()) {
            openWarningDialog("Brak trzeciego klucza");
            return -1;}
        Key[] keys = new Key[3];
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
        TripleDes tripleDes = new TripleDes();
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
        Key[] keys = new Key[3];
        keys[0]= new Key(key1Field.getText());
        keys[1] = new Key(key2Field.getText());
        keys[2] = new Key(key3Field.getText());
        cryptogramText = tripleDes.encrypt(plaintextArea.getText(), keys);
        cryptogramArea.setText(cryptogramText);
        return 0;
    }


    public int decryptMessage(ActionEvent actionEvent) throws Exception {
        cryptogramText = cryptogramArea.getText();
        TripleDes tripleDes = new TripleDes();
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
        Key[] keys = new Key[3];
        keys[0]= new Key(key1Field.getText());
        keys[1] = new Key(key2Field.getText());
        keys[2] = new Key(key3Field.getText());
        plainText = tripleDes.decrypt(cryptogramArea.getText(), keys);
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
        byte[] key1 = new byte[8];
        byte[] key2 = new byte[8];
        byte[] key3 = new byte[8];

        FileChooser fileChooser = new FileChooser();
        File selected = fileChooser.showOpenDialog(this);
        byte[] fileContent = new byte[24];
        try {
            fileContent = Files.readAllBytes(Path.of(selected.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int i = 0; i < 8; i++) {
            key1[i] = fileContent[i];
            key2[i] = fileContent[8+i];
            key3[i] = fileContent[16+i];
        }

        key1Field.setText(StringByteConverter.BytesToString(key1));;
        key2Field.setText(StringByteConverter.BytesToString(key2));
        key3Field.setText(StringByteConverter.BytesToString(key3));

    }

    public void saveKeysToFile(ActionEvent actionEvent) throws Exception {
        Key[] keys = new Key[3];
        keys[0] = new Key(key1Field.getText());
        keys[1] = new Key(key2Field.getText());
        keys[2] = new Key(key3Field.getText());

        byte[] outKeys = new byte[keys[0].getBytes().length+keys[1].getBytes().length+keys[2].getBytes().length];
        for(int i = 0; i < outKeys.length; i++) {
            if(i < 8) outKeys[i] = keys[0].getBytes()[i];
            else if (i >= 8 && i < 16) outKeys[i] = keys[1].getBytes()[i-8];
            else if (i >= 16) outKeys[i] = keys[2].getBytes()[i-16];
        }
        FileChooser fileChooser = new FileChooser();
        File destination = fileChooser.showSaveDialog(this);
        Files.write(destination.toPath(), outKeys);
    }

    public void generateFirstKey(){
        Key first = new Key();
        key1Field.setText(first.getKeyText());
    }

    public void generateSecondKey() {
        Key second = new Key();
        key2Field.setText(second.getKeyText());
    }

    public void generateThirdKey() {
        Key third = new Key();
        key3Field.setText(third.getKeyText());
    }

    public void generateAllKeys() {
        generateFirstKey();
        generateSecondKey();
        generateThirdKey();
    }
}