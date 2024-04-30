package io.openliberty.tools.intellij.util.customUiComponent;

import com.intellij.openapi.externalSystem.service.ui.command.line.CompletionTableInfo;
import com.intellij.openapi.externalSystem.service.ui.completion.TextCompletionInfo;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.List;

public class PhasesAndGoalsCompletionTableInfo implements CompletionTableInfo {


    @Nullable
    @Override
    public Object collectCompletionInfo(@NotNull Continuation<? super List<TextCompletionInfo>> continuation) {
        return null;
    }

    @Nullable
    @Override
    public Object collectTableCompletionInfo(@NotNull Continuation<? super List<TextCompletionInfo>> continuation) {
        return null;
    }

    @Nullable
    @Override
    public Icon getDataColumnIcon() {
        return null;
    }

    @NotNull
    @Override
    public String getDataColumnName() {
        return null;
    }

    @Nullable
    @Override
    public Icon getDescriptionColumnIcon() {
        return null;
    }

    @NotNull
    @Override
    public String getDescriptionColumnName() {
        return null;
    }

    @NotNull
    @Override
    public String getEmptyState() {
        return null;
    }
}
