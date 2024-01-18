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
    private JCheckBox generateFeaturesCheckBox1;
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
                };
            }

        });

        comboBox1.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Object item = e.getItem();
                    if( item == "true"){
                        editableParams.getComponent().setText("--DHotTest=true");
                    } else if (item == "false") {
                        editableParams.getComponent().setText("--DHotTest=false");
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
        root.setPreferredSize(new Dimension(1000,210));
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