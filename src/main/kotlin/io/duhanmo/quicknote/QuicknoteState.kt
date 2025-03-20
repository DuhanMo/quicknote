package io.duhanmo.quicknote

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage

@Service(Service.Level.PROJECT)
@State(name = "QuicknoteState", storages = [Storage("quicknote.xml")])
class QuicknoteState : PersistentStateComponent<QuicknoteState> {
    var draftContent: String = ""

    override fun getState(): QuicknoteState = this

    override fun loadState(state: QuicknoteState) {
        this.draftContent = state.draftContent
    }
}
