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

import com.intellij.openapi.Disposable;
import com.intellij.openapi.externalSystem.service.ui.command.line.CommandLineField;
import com.intellij.openapi.externalSystem.service.ui.project.path.ExternalProject;
import com.intellij.openapi.externalSystem.service.ui.project.path.WorkingDirectoryField;
import com.intellij.openapi.externalSystem.service.ui.project.path.WorkingDirectoryInfo;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.ui.LabeledComponent;
import com.intellij.openapi.util.Disposer;
import com.intellij.ui.EditorTextField;
import com.intellij.ui.StateRestoringCheckBox;
import io.openliberty.tools.intellij.LibertyModules;
import io.openliberty.tools.intellij.util.customUiComponent.LibertyCommandLineInfo;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.idea.maven.execution.run.configuration.MavenWorkingDirectoryInfo;
import org.jetbrains.plugins.gradle.service.execution.GradleCommandLineInfo;

import javax.swing.*;
import java.util.List;

/**
 * Editor associated with Liberty run & debug configurations. Defines when configuration changes are updated, default values, etc.
 */
public class LibertyRunSettingsEditor extends SettingsEditor<LibertyRunConfiguration> {

    private JPanel root;
    private LabeledComponent<EditorTextField> editableParams;
    private LabeledComponent<ComboBox> libertyModule;
    private StateRestoringCheckBox runInContainerCheckBox;
    private WorkingDirectoryField workingDirectoryField1;
    private CommandLineField commandLineField1;


//    private CommandLineField commandLineField1;

    private Project pro;


    public LibertyRunSettingsEditor(Project project) {

        pro = project;
//        LibertyModules lib = LibertyModules.getInstance();
//        List<String> list = lib.getLibertyBuildFilesAsString(project);
//        DefaultComboBoxModel difc = new DefaultComboBoxModel(list.toArray());

//        libertyModule.getComponent().setModel(difc);


//        WorkingDirectoryInfo wrkinfo = new MavenWorkingDirectoryInfo(project);
//
//        workingDirectoryField1 = new WorkingDirectoryField(project, wrkinfo, null);


        commandLineField1 = new CommandLineField(project, new LibertyCommandLineInfo() , Disposer.newDisposable());

//System.out.println("Hello");
        /*GradleCommandLineInfo gradlinfo = new GradleCommandLineInfo(project,new WorkingDirectoryField(project,new MavenWorkingDirectoryInfo(project),Disposer.newDisposable()));
        commandLineField1 = new CommandLineField(project,gradlinfo,Disposer.newDisposable());*/

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
        commandLineField1 = new CommandLineField(pro, new LibertyCommandLineInfo() , Disposer.newDisposable());
    }
}