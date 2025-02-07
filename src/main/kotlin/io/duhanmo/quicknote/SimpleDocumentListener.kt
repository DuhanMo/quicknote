package io.duhanmo.quicknote

import javax.swing.event.DocumentEvent
import javax.swing.event.DocumentListener

fun interface SimpleDocumentListener : DocumentListener {
    fun update()
    override fun insertUpdate(e: DocumentEvent?) = update()
    override fun removeUpdate(e: DocumentEvent?) = update()
    override fun changedUpdate(e: DocumentEvent?) = update()
}