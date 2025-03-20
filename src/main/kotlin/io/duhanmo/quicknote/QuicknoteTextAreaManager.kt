package io.duhanmo.quicknote

import com.intellij.ui.components.JBTextArea
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent

class QuicknoteTextAreaManager(private val state: QuicknoteState) {
    fun createTextArea(): JBTextArea {
        val textArea = JBTextArea(state.draftContent, 10, 40)
        configureTextAreaAppearance(textArea)
        setupAutoSave(textArea)
        configureTabToSpaces(textArea)
        return textArea
    }

    private fun configureTextAreaAppearance(textArea: JBTextArea) {
        textArea.lineWrap = true
        textArea.wrapStyleWord = true
        textArea.font = textArea.font.deriveFont(14f)
    }

    private fun setupAutoSave(textArea: JBTextArea) {
        textArea.document.addDocumentListener(object : SimpleDocumentListener {
            override fun update() {
                state.draftContent = textArea.text
            }
        })
    }

    private fun configureTabToSpaces(textArea: JBTextArea) {
        textArea.addKeyListener(object : KeyAdapter() {
            override fun keyPressed(e: KeyEvent) {
                if (e.keyCode == KeyEvent.VK_TAB) {
                    e.consume()

                    val position = textArea.caretPosition
                    textArea.document.insertString(position, "    ", null)
                    textArea.caretPosition = position + 4
                }
            }
        })
    }
}