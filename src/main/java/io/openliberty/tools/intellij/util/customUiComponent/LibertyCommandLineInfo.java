package io.openliberty.tools.intellij.util.customUiComponent;

import com.intellij.openapi.externalSystem.service.ui.command.line.CommandLineInfo;
import com.intellij.openapi.externalSystem.service.ui.command.line.CompletionTableInfo;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
public class LibertyCommandLineInfo implements CommandLineInfo {
    @NotNull
    @Override
    public String getDialogTitle() {
        return null;
    }

    @NotNull
    @Override
    public String getDialogTooltip() {
        return " ";
    }

    @NotNull
    @Override
    public String getFieldEmptyState() {
        return " ";
    }

    @NotNull
    @Override
    public List<CompletionTableInfo> getTablesInfo() {
        List<CompletionTableInfo> tablesInfoList = new ArrayList<>();
        tablesInfoList.add(new ArgumentsCompletionTableInfo());
        return tablesInfoList;
    }

    @Nullable
    @Override
    public String getSettingsHint() {
        return null;
    }

    @Nullable
    @Override
    public String getSettingsName() {
        return null;
    }
}