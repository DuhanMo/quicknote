package io.duhanmo.quicknote

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.components.JBScrollPane
import com.intellij.ui.components.JBTextArea
import com.intellij.ui.content.ContentFactory
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import javax.swing.BoxLayout
import javax.swing.JPanel

class QuicknoteToolWindow : ToolWindowFactory {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val state = project.getService(QuicknoteState::class.java)
        val textArea = getJbTextArea(state)
        autoSaveDraftContent(textArea, state)

        configureTabToSpaces(textArea)
        val panel = getJPanel(textArea)
        addPanelToToolWindow(panel, toolWindow)
    }

    private fun getJbTextArea(state: QuicknoteState): JBTextArea {
        val textArea = JBTextArea(state.draftContent, 10, 40)
        textArea.lineWrap = true
        textArea.wrapStyleWord = true
        textArea.font = textArea.font.deriveFont(14f)
        return textArea
    }

    private fun autoSaveDraftContent(textArea: JBTextArea, state: QuicknoteState) {
        textArea.document.addDocumentListener(object : SimpleDocumentListener {
            override fun update() {
                state.draftContent = textArea.text
            }
        })
    }

    private fun getJPanel(textArea: JBTextArea): JPanel {
        val panel = JPanel()
        panel.layout = BoxLayout(panel, BoxLayout.Y_AXIS)
        panel.add(JBScrollPane(textArea))
        return panel
    }

    private fun addPanelToToolWindow(panel: JPanel, toolWindow: ToolWindow) {
        val contentFactory = ContentFactory.getInstance()
        val content = contentFactory.createContent(panel, "", false)
        toolWindow.contentManager.addContent(content)
    }

    private fun configureTabToSpaces(textArea: JBTextArea) {
        textArea.addKeyListener(object : KeyAdapter() {
            override fun keyPressed(e: KeyEvent) {
                if (e.keyCode == KeyEvent.VK_TAB) {
                    e.consume() // remove default tab operation

                    val position = textArea.caretPosition
                    textArea.document.insertString(position, "    ", null) // tab to 4 spaces
                    textArea.caretPosition = position + 4 // adjust cursor position
                }
            }
        })
    }
}
