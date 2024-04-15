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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Editor associated with Liberty run & debug configurations. Defines when configuration changes are updated, default values, etc.
 */
public class LibertyRunSettingsEditor extends SettingsEditor<LibertyRunConfiguration> {

    private JPanel root;
    private LabeledComponent<EditorTextField> editableParams;
    private LabeledComponent<ComboBox> libertyModule;
    private StateRestoringCheckBox runInContainerCheckBox;
    private JComboBox<ComboBoxItem> comboBox1;
    private JTextField textField1;
    private JComboBox comboBox2;
    private  JTextField textField2;
    private  JList list1;
    private  JScrollPane scrollpane1;

    private static List<String> selectedItems = new ArrayList<>();

    public LibertyRunSettingsEditor(Project project) {
        libertyModule.getComponent().setModel(new DefaultComboBoxModel(LibertyModules.getInstance().getLibertyBuildFilesAsString(project).toArray()));
        comboBox1.addItem(new ComboBoxItem("Display Item 1", "Value1"));
        comboBox1.addItem(new ComboBoxItem("Display Item 2", "Value2"));
        comboBox1.addItem(new ComboBoxItem("Display Item 3", "Value3"));


        comboBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox) e.getSource();
                String selectedItem = (String) cb.getSelectedItem();
                // Toggle selection
                if (selectedItems.contains(selectedItem)) {
                    selectedItems.remove(selectedItem);
                } else {
                    selectedItems.add(selectedItem);
                }
                // Update text field
                updateTextField(textField1);
            }
        });

        list1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        textField2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                toggleListVisibility();
            }
        });
        // Update text field when list selection changes and hide list
        list1.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                updateTextField1();
                scrollpane1.setVisible(false);
            }
        });
    }

    private  void toggleListVisibility() {
        scrollpane1.setVisible(!scrollpane1.isVisible());
        // Repack the frame or revalidate the container to adjust layout
        scrollpane1.revalidate();
    }
    private  void updateTextField1() {
        List<String> selectedValuesList = list1.getSelectedValuesList();
        textField2.setText(String.join(", ", selectedValuesList));
    }

    private static void updateTextField(JTextField textField) {
        String text = String.join(", ", selectedItems);
        textField.setText(text);
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
        @Override
        public String toString() {
            return actualValue;
        }
    }
}