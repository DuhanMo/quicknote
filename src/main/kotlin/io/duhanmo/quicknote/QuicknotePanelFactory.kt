package io.duhanmo.quicknote

import com.intellij.ui.components.JBScrollPane
import com.intellij.ui.components.JBTextArea
import javax.swing.BoxLayout
import javax.swing.JPanel

class QuicknotePanelFactory {
    fun createPanel(textArea: JBTextArea): JPanel {
        val panel = JPanel()
        panel.layout = BoxLayout(panel, BoxLayout.Y_AXIS)
        panel.add(JBScrollPane(textArea))
        return panel
    }
}