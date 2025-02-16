package io.duhanmo.quicknote

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowManager
import com.intellij.ui.components.JBTextArea
import java.awt.Component
import java.awt.Container
import javax.swing.JComponent
import javax.swing.SwingUtilities

class MoveFocusToQuicknoteAction : AnAction() {
    override fun actionPerformed(event: AnActionEvent) {
        val project = event.project ?: return
        val toolWindow: ToolWindow = ToolWindowManager.getInstance(project).getToolWindow("Quicknote") ?: return

        checkIsOpenedQuicknoteWindow(toolWindow)
    }

    private fun checkIsOpenedQuicknoteWindow(toolWindow: ToolWindow) {
        if (toolWindow.isAvailable && toolWindow.isVisible) {
            val content = toolWindow.contentManager.selectedContent ?: return
            val component = content.component
            moveCursorToLastTestPosition(component)
        }
    }

    private fun moveCursorToLastTestPosition(component: JComponent) {
        SwingUtilities.invokeLater {
            val textArea = findJBTextArea(component)
            if (textArea != null) {
                textArea.requestFocus()
                textArea.caretPosition = textArea.document.length
            }
        }
    }

    private fun findJBTextArea(component: Component?): JBTextArea? {
        if (component is JBTextArea) {
            return component
        }
        if (component is Container) {
            for (child in component.components) {
                val found = findJBTextArea(child)
                if (found != null) {
                    return found
                }
            }
        }
        return null
    }
}
