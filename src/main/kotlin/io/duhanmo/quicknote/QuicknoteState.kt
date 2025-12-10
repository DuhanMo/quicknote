package io.duhanmo.quicknote

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage

private const val MAX_CONTENT_LENGTH = 1_000_000

@Service(Service.Level.PROJECT)
@State(name = "QuicknoteState", storages = [Storage("quicknote.xml")])
class QuicknoteState : PersistentStateComponent<QuicknoteState> {
    var draftContent: String = ""
        set(value) {
            field = if (value.length > MAX_CONTENT_LENGTH) {
                value.substring(0, MAX_CONTENT_LENGTH)
            } else {
                value
            }
        }

    override fun getState(): QuicknoteState = this

    override fun loadState(state: QuicknoteState) {
        this.draftContent = state.draftContent
    }
}