/*******************************************************************************
 * Copyright (c) 2022 IBM Corporation.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 *  SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package io.openliberty.tools.intellij.runConfiguration;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.ui.LabeledComponent;
import com.intellij.ui.EditorTextField;
import com.intellij.ui.StateRestoringCheckBox;
import io.openliberty.tools.intellij.LibertyModules;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Editor associated with Liberty run & debug configurations. Defines when configuration changes are updated, default values, etc.
 */
public class LibertyRunSettingsEditor extends SettingsEditor<LibertyRunConfiguration> {

    private JPanel root;
    private LabeledComponent<EditorTextField> editableParams;
    private LabeledComponent<ComboBox> libertyModule;
    private StateRestoringCheckBox runInContainerCheckBox;
    private JCheckBox skipTestsCheckBox;
    private JPanel checkboxpannel;
    private JPanel panelcheck;
    private JButton sbutton;
    private JCheckBox libertyDebugCheckBox;
    private JCheckBox libertyDebugPortCheckBox;
    private JTextField a7777TextField;
    private JCheckBox compileWaitCheckBox;
    private JTextField a05TextField;
    private JCheckBox serverStartTimeoutCheckBox;
    private JTextField a90TextField;
    private JCheckBox verifyAppStartTimeoutCheckBox;
    private JTextField a30TextField;
    private JCheckBox generateFeaturesCheckBox;
    private JCheckBox skipInstallFeatureCheckBox;
    private JComboBox comboBox1;
    private JCheckBox hotTestCheckBox;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JComboBox comboBox5;
    private JCheckBox otherCheckBox;
    private JTextField textField1;

    JButton bt2 = new JButton();

    boolean isVisible = false;

    public LibertyRunSettingsEditor(Project project) {
        libertyModule.getComponent().setModel(new DefaultComboBoxModel(LibertyModules.getInstance().getLibertyBuildFilesAsString(project).toArray()));
        sbutton.addActionListener(e -> selectionButtonPressed());

        hotTestCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) {//checkbox has been selected
                    comboBox1.setEnabled(true);
                    editableParams.getComponent().setText(editableParams.getComponent().getText() + " " +"--DHotTest=" + comboBox1.getSelectedItem());


                } else {//checkbox has been deselected
                    comboBox1.setEnabled(false);
                    String editableParamValue = editableParams.getComponent().getText();
                    if (editableParamValue.contains("DHotTest")) {
                        String regex = "\\s*--DHotTest=\\w*\\s*";
                        String result = editableParamValue.replaceAll(regex, " ").trim();
                        editableParams.getComponent().setText(result);
                    }

                }
            }

        });

        comboBox1.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String editableParamValue = editableParams.getComponent().getText();
                    if (editableParamValue.contains("DHotTest")) {
                        String regex = "\\s*--DHotTest=\\w*\\s*";
                        String result = editableParamValue.replaceAll(regex, " ").trim();
                        editableParams.getComponent().setText(result);
                    }
                    editableParams.getComponent().setText(editableParams.getComponent().getText() + " " + "--DHotTest=" + e.getItem().toString());
                }
            }
        });

        skipTestsCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) {//checkbox has been selected
                    comboBox2.setEnabled(true);
                    editableParams.getComponent().setText(editableParams.getComponent().getText() + " " +"--skipTest=" + comboBox1.getSelectedItem());


                } else {//checkbox has been deselected
                    comboBox2.setEnabled(false);
                   // editableParams.getComponent().setText("");
                    String editableParamValue = editableParams.getComponent().getText();
                    if (editableParamValue.contains("DHotTest")) {
                        String regex = "\\s*--skipTest=\\w*\\s*";
                        String result = editableParamValue.replaceAll(regex, " ").trim();
                        editableParams.getComponent().setText(result);
                    }
                }

            }

        });

        comboBox2.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String editableParamValue = editableParams.getComponent().getText();
                    if (editableParamValue.contains("skipTest")) {
                        String regex = "\\s*--skipTest=\\w*\\s*";
                        String result = editableParamValue.replaceAll(regex, " ").trim();
                        editableParams.getComponent().setText(result);
                    }
                    editableParams.getComponent().setText(editableParams.getComponent().getText() + " " + "--skipTest=" + e.getItem().toString());
                }
            }
        });

        libertyDebugCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) {//checkbox has been selected
                    comboBox3.setEnabled(true);
                    editableParams.getComponent().setText(editableParams.getComponent().getText() + " " +"--libertyDebug=" + comboBox3.getSelectedItem());


                } else {//checkbox has been deselected
                    comboBox3.setEnabled(false);
                    // editableParams.getComponent().setText("");
                    String editableParamValue = editableParams.getComponent().getText();
                    if (editableParamValue.contains("libertyDebug")) {
                        String regex = "\\s*--libertyDebug=\\w*\\s*";
                        String result = editableParamValue.replaceAll(regex, " ").trim();
                        editableParams.getComponent().setText(result);
                    }
                }

            }

        });

        comboBox3.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String editableParamValue = editableParams.getComponent().getText();
                    if (editableParamValue.contains("libertyDebug")) {
                        String regex = "\\s*--libertyDebug=\\w*\\s*";
                        String result = editableParamValue.replaceAll(regex, " ").trim();
                        editableParams.getComponent().setText(result);
                    }
                    editableParams.getComponent().setText(editableParams.getComponent().getText() + " " + "--libertyDebug=" + e.getItem().toString());
                }
            }
        });

        libertyDebugPortCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
                a7777TextField.setEnabled(isSelected); // Enable or disable textField1
                if (isSelected) {
                    // If the checkbox is selected, update textField2 with textField1's content
                    editableParams.getComponent().setText(editableParams.getComponent().getText() + " " + "--libertyDebugPort=" + a7777TextField.getText());
                } else {

                    String editableParamValue = editableParams.getComponent().getText();
                    if (editableParamValue.contains("libertyDebugPort")) {
                        String regex = "\\s*--libertyDebugPort=\\d*\\s*";
                        String result = editableParamValue.replaceAll(regex, " ").trim();
                        editableParams.getComponent().setText(result);
                    }
                }
            }
        });

        a7777TextField.getDocument().addDocumentListener(new DocumentListener() {

            public void changedUpdate(DocumentEvent e){
                String editableParamValue = editableParams.getComponent().getText();
                if (editableParamValue.contains("libertyDebugPort")) {
                    //String regex = Pattern.quote("--libertyDebugPort=") + ".*?" + Pattern.quote(" ");
                    //String editableParamValue1 = editableParamValue.replaceAll(regex, "");
                    String regex = "\\s*--libertyDebugPort=\\d*\\s*";
                    String result = editableParamValue.replaceAll(regex, " ").trim();
                    editableParams.getComponent().setText(result);

                }
                // This method is called when more text is added to the text field.
                editableParams.getComponent().setText(editableParams.getComponent().getText() + " " + "--libertyDebugPort=" + a7777TextField.getText());
            }
            public void removeUpdate(DocumentEvent e) {
                String editableParamValue = editableParams.getComponent().getText();
                if (editableParamValue.contains("libertyDebugPort")) {
                    String regex = "\\s*--libertyDebugPort=\\d*\\s*";
                    String result = editableParamValue.replaceAll(regex, " ").trim();
                    editableParams.getComponent().setText(result);
                }
                // This method is called when more text is added to the text field.
                editableParams.getComponent().setText(editableParams.getComponent().getText() + " " + "--libertyDebugPort=" + a7777TextField.getText());
            }
            public void insertUpdate(DocumentEvent e) {
                String editableParamValue = editableParams.getComponent().getText();
                if (editableParamValue.contains("libertyDebugPort")) {
                    String regex = "\\s*--libertyDebugPort=\\d*\\s*";
                    String result = editableParamValue.replaceAll(regex, " ").trim();
                    editableParams.getComponent().setText(result);
                }
                // This method is called when more text is added to the text field.
                editableParams.getComponent().setText(editableParams.getComponent().getText() + " " + "--libertyDebugPort=" + a7777TextField.getText());
            }
        });


        compileWaitCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
                a05TextField.setEnabled(isSelected); // Enable or disable textField1
                if (isSelected) {
                    // If the checkbox is selected, update textField2 with textField1's content
                    editableParams.getComponent().setText(editableParams.getComponent().getText() + " " + "--compileWait=" + a05TextField.getText());
                } else {

                    String editableParamValue = editableParams.getComponent().getText();
                    if (editableParamValue.contains("compileWait")) {
                        String regex = "\\s*--compileWait=\\d*\\s*";
                        String result = editableParamValue.replaceAll(regex, " ").trim();
                        editableParams.getComponent().setText(result);
                    }
                }
            }
        });

        a05TextField.getDocument().addDocumentListener(new DocumentListener() {

            public void changedUpdate(DocumentEvent e){
                String editableParamValue = editableParams.getComponent().getText();
                if (editableParamValue.contains("compileWait")) {
                    String regex = "\\s*--compileWait=\\d*\\s*";
                    String result = editableParamValue.replaceAll(regex, " ").trim();
                    editableParams.getComponent().setText(result);

                }
                // This method is called when more text is added to the text field.
                editableParams.getComponent().setText(editableParams.getComponent().getText() + " " + "--compileWait=" + a05TextField.getText());
            }
            public void removeUpdate(DocumentEvent e) {
                String editableParamValue = editableParams.getComponent().getText();
                if (editableParamValue.contains("compileWait")) {
                    String regex = "\\s*--compileWait=\\d*\\s*";
                    String result = editableParamValue.replaceAll(regex, " ").trim();
                    editableParams.getComponent().setText(result);
                }
                // This method is called when more text is added to the text field.
                editableParams.getComponent().setText(editableParams.getComponent().getText() + " " + "--compileWait=" + a05TextField.getText());
            }
            public void insertUpdate(DocumentEvent e) {
                String editableParamValue = editableParams.getComponent().getText();
                if (editableParamValue.contains("compileWait")) {
                    String regex = "\\s*--compileWait=\\d*\\s*";
                    String result = editableParamValue.replaceAll(regex, " ").trim();
                    editableParams.getComponent().setText(result);
                }
                // This method is called when more text is added to the text field.
                editableParams.getComponent().setText(editableParams.getComponent().getText() + " " + "--compileWait=" + a05TextField.getText());
            }
        });

        serverStartTimeoutCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
                a90TextField.setEnabled(isSelected); // Enable or disable textField1
                if (isSelected) {
                    // If the checkbox is selected, update textField2 with textField1's content
                    editableParams.getComponent().setText(editableParams.getComponent().getText() + " " + "--serverTimeout=" + a90TextField.getText());
                } else {

                    String editableParamValue = editableParams.getComponent().getText();
                    if (editableParamValue.contains("serverTimeout")) {
                        String regex = "\\s*--serverTimeout=\\d*\\s*";
                        String result = editableParamValue.replaceAll(regex, " ").trim();
                        editableParams.getComponent().setText(result);
                    }
                }
            }
        });

        a90TextField.getDocument().addDocumentListener(new DocumentListener() {

            public void changedUpdate(DocumentEvent e){
                String editableParamValue = editableParams.getComponent().getText();
                if (editableParamValue.contains("serverTimeout")) {
                    String regex = "\\s*--serverTimeout=\\d*\\s*";
                    String result = editableParamValue.replaceAll(regex, " ").trim();
                    editableParams.getComponent().setText(result);

                }
                // This method is called when more text is added to the text field.
                editableParams.getComponent().setText(editableParams.getComponent().getText() + " " + "--serverTimeout=" + a90TextField.getText());
            }
            public void removeUpdate(DocumentEvent e) {
                String editableParamValue = editableParams.getComponent().getText();
                if (editableParamValue.contains("serverTimeout")) {
                    String regex = "\\s*--serverTimeout=\\d*\\s*";
                    String result = editableParamValue.replaceAll(regex, " ").trim();
                    editableParams.getComponent().setText(result);
                }
                // This method is called when more text is added to the text field.
                editableParams.getComponent().setText(editableParams.getComponent().getText() + " " + "--serverTimeout=" + a90TextField.getText());
            }
            public void insertUpdate(DocumentEvent e) {
                String editableParamValue = editableParams.getComponent().getText();
                if (editableParamValue.contains("serverTimeout")) {
                    String regex = "\\s*--serverTimeout=\\d*\\s*";
                    String result = editableParamValue.replaceAll(regex, " ").trim();
                    editableParams.getComponent().setText(result);
                }
                // This method is called when more text is added to the text field.
                editableParams.getComponent().setText(editableParams.getComponent().getText() + " " + "--serverTimeout=" + a90TextField.getText());
            }
        });


        verifyAppStartTimeoutCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
                a30TextField.setEnabled(isSelected); // Enable or disable textField1
                if (isSelected) {
                    // If the checkbox is selected, update textField2 with textField1's content
                    editableParams.getComponent().setText(editableParams.getComponent().getText() + " " + "--verifyTimeout=" + a30TextField.getText());
                } else {

                    String editableParamValue = editableParams.getComponent().getText();
                    if (editableParamValue.contains("verifyTimeout")) {
                        String regex = "\\s*--verifyTimeout=\\d*\\s*";
                        String result = editableParamValue.replaceAll(regex, " ").trim();
                        editableParams.getComponent().setText(result);
                    }
                }
            }
        });

        a30TextField.getDocument().addDocumentListener(new DocumentListener() {

            public void changedUpdate(DocumentEvent e){
                String editableParamValue = editableParams.getComponent().getText();
                if (editableParamValue.contains("verifyTimeout")) {
                    String regex = "\\s*--verifyTimeout=\\d*\\s*";
                    String result = editableParamValue.replaceAll(regex, " ").trim();
                    editableParams.getComponent().setText(result);

                }
                // This method is called when more text is added to the text field.
                editableParams.getComponent().setText(editableParams.getComponent().getText() + " " + "--verifyTimeout=" + a30TextField.getText());
            }
            public void removeUpdate(DocumentEvent e) {
                String editableParamValue = editableParams.getComponent().getText();
                if (editableParamValue.contains("verifyTimeout")) {
                    String regex = "\\s*--verifyTimeout=\\d*\\s*";
                    String result = editableParamValue.replaceAll(regex, " ").trim();
                    editableParams.getComponent().setText(result);
                }
                // This method is called when more text is added to the text field.
                editableParams.getComponent().setText(editableParams.getComponent().getText() + " " + "--verifyTimeout=" + a30TextField.getText());
            }
            public void insertUpdate(DocumentEvent e) {
                String editableParamValue = editableParams.getComponent().getText();
                if (editableParamValue.contains("verifyTimeout")) {
                    String regex = "\\s*--verifyTimeout=\\d*\\s*";
                    String result = editableParamValue.replaceAll(regex, " ").trim();
                    editableParams.getComponent().setText(result);
                }
                // This method is called when more text is added to the text field.
                editableParams.getComponent().setText(editableParams.getComponent().getText() + " " + "--verifyTimeout=" + a30TextField.getText());
            }
        });

        generateFeaturesCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) {//checkbox has been selected
                    comboBox4.setEnabled(true);
                    editableParams.getComponent().setText(editableParams.getComponent().getText() + " " +"--generateFeature=" + comboBox4.getSelectedItem());


                } else {//checkbox has been deselected
                    comboBox4.setEnabled(false);
                    // editableParams.getComponent().setText("");
                    String editableParamValue = editableParams.getComponent().getText();
                    if (editableParamValue.contains("generateFeature")) {
                        String regex = "\\s*--generateFeature=\\w*\\s*";
                        String result = editableParamValue.replaceAll(regex, " ").trim();
                        editableParams.getComponent().setText(result);
                    }
                }

            }

        });

        comboBox4.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String editableParamValue = editableParams.getComponent().getText();
                    if (editableParamValue.contains("generateFeature")) {
                        String regex = "\\s*--generateFeature=\\w*\\s*";
                        String result = editableParamValue.replaceAll(regex, " ").trim();
                        editableParams.getComponent().setText(result);
                    }
                    editableParams.getComponent().setText(editableParams.getComponent().getText() + " " + "--generateFeature=" + e.getItem().toString());
                }
            }
        });

        skipInstallFeatureCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) {//checkbox has been selected
                    comboBox5.setEnabled(true);
                    editableParams.getComponent().setText(editableParams.getComponent().getText() + " " +"--skipInstall=" + comboBox5.getSelectedItem());


                } else {//checkbox has been deselected
                    comboBox5.setEnabled(false);
                    // editableParams.getComponent().setText("");
                    String editableParamValue = editableParams.getComponent().getText();
                    if (editableParamValue.contains("skipInstall")) {
                        String regex = "\\s*--skipInstall=\\w*\\s*";
                        String result = editableParamValue.replaceAll(regex, " ").trim();
                        editableParams.getComponent().setText(result);
                    }
                }

            }

        });

        comboBox5.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String editableParamValue = editableParams.getComponent().getText();
                    if (editableParamValue.contains("skipInstall")) {
                        String regex = "\\s*--skipInstall=\\w*\\s*";
                        String result = editableParamValue.replaceAll(regex, " ").trim();
                        editableParams.getComponent().setText(result);
                    }
                    editableParams.getComponent().setText(editableParams.getComponent().getText() + " " + "--skipInstall=" + e.getItem().toString());
                }
            }
        });

        otherCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
                textField1.setEnabled(isSelected); // Enable or disable textField1
                if (isSelected) {
                    // If the checkbox is selected, update textField2 with textField1's content
                    editableParams.getComponent().setText(textField1.getText());
                } /*else {

                    String editableParamValue = editableParams.getComponent().getText();
                    if (editableParamValue.contains("verifyTimeout")) {
                        String regex = "\\s*--verifyTimeout=\\d*\\s*";
                        String result = editableParamValue.replaceAll(regex, " ").trim();
                        editableParams.getComponent().setText(result);
                    }
                }*/
            }
        });


   }
   
    @Override
    protected void resetEditorFrom(@NotNull LibertyRunConfiguration configuration) {

        String buildFile = configuration.getBuildFile();
        for (int i = 0; i < libertyModule.getComponent().getItemCount(); i++) {
            String libertyModuleBuildFile = libertyModule.getComponent().getItemAt(i).toString();
            if (buildFile.equals(libertyModuleBuildFile)) {
                // need to use setSelectedIndex instead of setSelectedItem, setSelectedItem(buildFile) results in no behaviour change
                libertyModule.getComponent().setSelectedIndex(i);
                break;
            }
        }
        runInContainerCheckBox.setSelected(configuration.runInContainer());
        editableParams.getComponent().setText(configuration.getParams());
        comboBox1.setEnabled(false);
    }

    @Override
    protected void applyEditorTo(@NotNull LibertyRunConfiguration configuration) throws ConfigurationException {
        configuration.setParams(editableParams.getComponent().getText());
        configuration.setBuildFile(String.valueOf(libertyModule.getComponent().getSelectedItem()));
        configuration.setRunInContainer(runInContainerCheckBox.isSelected());
    }

    @NotNull
    @Override
    protected JComponent createEditor() {
      //  root.setSize(new Dimension(2000, 2000));
        root.setPreferredSize(new Dimension(750,300));
        return root;
    }

    public void createUIComponents() {
        ComboBox comboBox = new ComboBox();
        libertyModule = new LabeledComponent<>();
        libertyModule.setComponent(comboBox);
        editableParams = new LabeledComponent<>();
        editableParams.setComponent(new EditorTextField());
        runInContainerCheckBox = new StateRestoringCheckBox();
    }

    public void selectionButtonPressed() {
      isVisible = !isVisible;
      checkboxpannel.setVisible(isVisible);
    }
}