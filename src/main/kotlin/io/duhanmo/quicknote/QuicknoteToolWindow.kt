package io.duhanmo.quicknote

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.ContentFactory

class QuicknoteToolWindow : ToolWindowFactory {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val state = project.getService(QuicknoteState::class.java)
        val textAreaManager = QuicknoteTextAreaManager(state)
        val textArea = textAreaManager.createTextArea()

        val panelFactory = QuicknotePanelFactory()
        val panel = panelFactory.createPanel(textArea)

        val contentFactory = ContentFactory.getInstance()
        val content = contentFactory.createContent(panel, "", false)
        toolWindow.contentManager.addContent(content)
    }
}
