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

/**
 * Editor associated with Liberty run & debug configurations. Defines when configuration changes are updated, default values, etc.
 */
public class LibertyRunSettingsEditor extends SettingsEditor<LibertyRunConfiguration> {

    private JPanel root;
    private LabeledComponent<EditorTextField> editableParams;
    private LabeledComponent<ComboBox> libertyModule;
    private StateRestoringCheckBox runInContainerCheckBox;
    private JComboBox<ComboBoxItem> comboBox1;

    public LibertyRunSettingsEditor(Project project) {
        libertyModule.getComponent().setModel(new DefaultComboBoxModel(LibertyModules.getInstance().getLibertyBuildFilesAsString(project).toArray()));
        comboBox1.addItem(new ComboBoxItem("Display Item 1", "Value1"));
        comboBox1.addItem(new ComboBoxItem("Display Item 2", "Value2"));
        comboBox1.addItem(new ComboBoxItem("Display Item 3", "Value3"));
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
        return root;
    }

    public void createUIComponents() {
        ComboBox comboBox = new ComboBox();
        libertyModule = new LabeledComponent<>();
        libertyModule.setComponent(comboBox);
        editableParams = new LabeledComponent<>();
        editableParams.setComponent(new EditorTextField());
        runInContainerCheckBox = new StateRestoringCheckBox();
       // comboBox1.addItem("test");
        //String[] items = {"Item 1", "Item 2", "Item 3"};
       /* comboBox1 = new JComboBox<>();
        comboBox1.addItem("test2");*/

    }
    class ComboBoxItem {
        private String displayValue;
        private String actualValue;
        public ComboBoxItem(String displayValue, String actualValue) {
            this.displayValue = displayValue;
            this.actualValue = actualValue;
        }
        public String getDisplayValue() {
            return displayValue;
        }
        public String getActualValue() {
            return actualValue;
        }
        // The JComboBox will use this method to determine what text to display for each item
        @Override
        public String toString() {
            return actualValue;
        }
    }
}