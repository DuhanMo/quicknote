package io.duhanmo.quicknote

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

class QuicknoteAction : AnAction() {
    override fun actionPerformed(event: AnActionEvent) {
        val project = event.project ?: return
        val toolWindow = QuicknoteUtil.getToolWindow(project) ?: return

        if (toolWindow.isVisible) {
            toolWindow.hide(null)
        } else {
            toolWindow.activate(null)
        }
    }
}