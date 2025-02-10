package io.duhanmo.quicknote

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage

@Service(Service.Level.PROJECT)
@State(name = "QuickNoteState", storages = [Storage("quicknotes.xml")])
class QuickNoteState : PersistentStateComponent<QuickNoteState> {
    private var notes: MutableList<Pair<String, String>> = mutableListOf()
    var draftContent: String = ""

    override fun getState(): QuickNoteState = this

    override fun loadState(state: QuickNoteState) {
        this.notes = state.notes
        this.draftContent = state.draftContent
    }
}
