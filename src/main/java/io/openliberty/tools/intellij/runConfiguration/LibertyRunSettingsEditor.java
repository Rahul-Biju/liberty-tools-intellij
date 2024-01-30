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
                    editableParams.getComponent().setText("--DHotTest=true");


                } else {//checkbox has been deselected
                    comboBox1.setEnabled(false);
                    editableParams.getComponent().setText("");
                }
            }

        });

        comboBox1.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Object item = e.getItem();
                    if( item == "true"){
                        editableParams.getComponent().setText("--DHotTest=true");
                    } else if (item == "false") {  // add false to textArea
                        editableParams.getComponent().setText("--DHotTest=false");
                    }
                }
            }
        });

        skipTestsCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) {//checkbox has been selected
                    comboBox2.setEnabled(true);
                    editableParams.getComponent().setText("--skipTests=true");


                } else {//checkbox has been deselected
                    comboBox2.setEnabled(false);
                    editableParams.getComponent().setText("");
                }

            }

        });

        comboBox2.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Object item = e.getItem();
                    if( item == "true"){
                        editableParams.getComponent().setText("--skipTests=true");
                    } else if (item == "false") {
                        editableParams.getComponent().setText("--skipTests=false");
                    }
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
                    editableParams.getComponent().setText("--libertyDebugPort=7777");
                } else {
                    // If the checkbox is deselected, clear textField2
                    editableParams.getComponent().setText("");
                }
            }
        });

        compileWaitCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
                a05TextField.setEnabled(isSelected); // Enable or disable textField1
                if (isSelected) {
                    // If the checkbox is selected, update textField2 with textField1's content
                    editableParams.getComponent().setText("--compileWait=0.5");
                } else {
                    // If the checkbox is deselected, clear textField2
                    editableParams.getComponent().setText("");
                }
            }
        });

        serverStartTimeoutCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
                a90TextField.setEnabled(isSelected); // Enable or disable textField1
                if (isSelected) {
                    // If the checkbox is selected, update textField2 with textField1's content
                    editableParams.getComponent().setText("--serverStartTimeout=90");
                } else {
                    // If the checkbox is deselected, clear textField2
                    editableParams.getComponent().setText("");
                }
            }
        });

        verifyAppStartTimeoutCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
                a30TextField.setEnabled(isSelected); // Enable or disable textField1
                if (isSelected) {
                    // If the checkbox is selected, update textField2 with textField1's content
                    editableParams.getComponent().setText("--verifyAppStartTimeoutCheckBox=30");
                } else {
                    // If the checkbox is deselected, clear textField2
                    editableParams.getComponent().setText("");
                }
            }
        });

        generateFeaturesCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
                comboBox4.setEnabled(isSelected); // Enable or disable textField1
                if (isSelected) {
                    // If the checkbox is selected, update textField2 with textField1's content
                    editableParams.getComponent().setText("--generateFeaturesCheckBox=true");
                } else {
                    // If the checkbox is deselected, clear textField2
                    editableParams.getComponent().setText("");
                }
            }
        });

        comboBox4.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Object item = e.getItem();
                    if( item == "true"){
                        editableParams.getComponent().setText("--generateFeaturesCheckBox=true");
                    } else if (item == "false") {
                        editableParams.getComponent().setText("--generateFeaturesCheckBox=false");
                    }
                }
            }
        });

        skipInstallFeatureCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
                comboBox5.setEnabled(isSelected); // Enable or disable textField1
                if (isSelected) {
                    // If the checkbox is selected, update textField2 with textField1's content
                    editableParams.getComponent().setText("--skipInstallFeatureCheckBox=true");
                } else {
                    // If the checkbox is deselected, clear textField2
                    editableParams.getComponent().setText("");
                }
            }
        });

        comboBox5.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Object item = e.getItem();
                    if( item == "true"){
                        editableParams.getComponent().setText("--skipInstallFeatureCheckBox=true");
                    } else if (item == "false") {
                        editableParams.getComponent().setText("--skipInstallFeatureCheckBox=false");
                    }
                }
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
        root.setPreferredSize(new Dimension(750,210));
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