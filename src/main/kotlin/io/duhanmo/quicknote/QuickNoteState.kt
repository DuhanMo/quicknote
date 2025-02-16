package io.duhanmo.quicknote

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage

@Service(Service.Level.PROJECT)
@State(name = "QuicknoteState", storages = [Storage("quicknote.xml")])
class QuicknoteState : PersistentStateComponent<QuicknoteState> {
    private var notes: MutableList<Pair<String, String>> = mutableListOf()
    var draftContent: String = ""

    override fun getState(): QuicknoteState = this

    override fun loadState(state: QuicknoteState) {
        this.notes = state.notes
        this.draftContent = state.draftContent
    }
}
