package io.duhanmo.quicknote

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowManager
import com.intellij.ui.components.JBTextArea
import javax.swing.SwingUtilities

class MoveFocusToQuickNoteAction : AnAction() {
    override fun actionPerformed(event: AnActionEvent) {
        val project = event.project ?: return
        val toolWindow: ToolWindow = ToolWindowManager.getInstance(project).getToolWindow("Quick Note") ?: return

        // Quick Note 창이 열려 있는지 확인
        if (toolWindow.isAvailable && toolWindow.isVisible) {
            val content = toolWindow.contentManager.selectedContent ?: return
            val component = content.component

            // Quick Note 창 내부에서 JBTextArea 찾기
            SwingUtilities.invokeLater {
                val textArea = findTextArea(component)
                if (textArea != null) {
                    textArea.requestFocus()
                    textArea.caretPosition = textArea.document.length // 🔥 커서를 마지막 위치로 이동
                }
            }
        }
    }

    /**
     * Quick Note 창 내부의 JBTextArea를 찾는 함수
     */
    private fun findTextArea(component: java.awt.Component?): JBTextArea? {
        if (component is JBTextArea) return component
        if (component is java.awt.Container) {
            for (child in component.components) {
                val found = findTextArea(child)
                if (found != null) return found
            }
        }
        return null
    }
}
