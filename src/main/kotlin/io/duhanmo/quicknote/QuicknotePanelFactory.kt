package io.duhanmo.quicknote

import com.intellij.ui.components.JBScrollPane
import com.intellij.ui.components.JBTextArea
import javax.swing.BoxLayout
import javax.swing.JPanel
import javax.swing.border.EmptyBorder

class QuicknotePanelFactory {
    fun createPanel(textArea: JBTextArea): JPanel {
        val panel = JPanel()
        panel.layout = BoxLayout(panel, BoxLayout.Y_AXIS)

        // Add padding
        panel.border = EmptyBorder(5, 5, 5, 5) // 5 pixels of padding on all sides

        panel.add(JBScrollPane(textArea))
        return panel
    }
}