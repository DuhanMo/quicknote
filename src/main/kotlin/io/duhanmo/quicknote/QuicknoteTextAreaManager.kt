package io.duhanmo.quicknote

import com.intellij.openapi.Disposable
import com.intellij.ui.components.JBTextArea
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import javax.swing.event.DocumentListener

class QuicknoteTextAreaManager(private val state: QuicknoteState) : Disposable {
    private var textArea: JBTextArea? = null
    private var documentListener: DocumentListener? = null
    private var keyAdapter: KeyAdapter? = null

    fun createTextArea(): JBTextArea {
        val textArea = JBTextArea(state.draftContent, 10, 40)
        this.textArea = textArea

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
        val listener = SimpleDocumentListener {
            state.draftContent = textArea.text
        }
        textArea.document.addDocumentListener(listener)
        documentListener = listener
    }

    private fun configureTabToSpaces(textArea: JBTextArea) {
        val adapter = object : KeyAdapter() {
            override fun keyPressed(e: KeyEvent) {
                if (e.keyCode == KeyEvent.VK_TAB) {
                    e.consume()
                    val position = textArea.caretPosition
                    textArea.document.insertString(position, "    ", null)
                    textArea.caretPosition = position + 4
                }
            }
        }
        textArea.addKeyListener(adapter)
        keyAdapter = adapter
    }

    override fun dispose() {
        textArea?.let { textArea ->
            documentListener?.let { listener ->
                textArea.document.removeDocumentListener(listener)
            }
            keyAdapter?.let { adapter ->
                textArea.removeKeyListener(adapter)
            }
        }

        documentListener = null
        keyAdapter = null
        textArea = null
    }
}