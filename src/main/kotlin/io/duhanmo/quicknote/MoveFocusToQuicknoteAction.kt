package io.duhanmo.quicknote

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.wm.ToolWindow
import javax.swing.JComponent
import javax.swing.SwingUtilities

class MoveFocusToQuicknoteAction : AnAction() {
    override fun actionPerformed(event: AnActionEvent) {
        val project = event.project ?: return
        val toolWindow = QuicknoteUtil.getToolWindow(project) ?: return

        toolWindow.activate {
            focusQuicknoteWindow(toolWindow)
        }
    }

    private fun focusQuicknoteWindow(toolWindow: ToolWindow) {
        val content = toolWindow.contentManager.selectedContent ?: return
        val component = content.component

        SwingUtilities.invokeLater {
            findAndFocusTextArea(component)
        }
    }

    private fun findAndFocusTextArea(component: JComponent) {
        val textArea = QuicknoteUtil.findJBTextArea(component)
        if (textArea != null) {
            textArea.requestFocusInWindow()
            textArea.caretPosition = textArea.document.length
        }
    }
}