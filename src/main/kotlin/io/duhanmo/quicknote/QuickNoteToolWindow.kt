package io.duhanmo.quicknote

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.components.JBScrollPane
import com.intellij.ui.components.JBTextArea
import com.intellij.ui.content.ContentFactory
import javax.swing.BoxLayout
import javax.swing.JPanel

class QuickNoteToolWindow : ToolWindowFactory {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val state = project.getService(QuickNoteState::class.java)

        // 노트 입력 필드 - 앱 재시작 시 마지막 입력 내용 유지
        val textArea = JBTextArea(state.draftContent, 10, 40)
        textArea.lineWrap = true
        textArea.wrapStyleWord = true
        textArea.font = textArea.font.deriveFont(14f)

        // 입력할 때마다 draftContent 자동 저장
        textArea.document.addDocumentListener(object : SimpleDocumentListener {
            override fun update() {
                state.draftContent = textArea.text
            }
        })

        // 패널 설정
        val panel = JPanel()
        panel.layout = BoxLayout(panel, BoxLayout.Y_AXIS)
        panel.add(JBScrollPane(textArea))

        // 툴 윈도우에 추가
        val contentFactory = ContentFactory.getInstance()
        val content = contentFactory.createContent(panel, "", false)
        toolWindow.contentManager.addContent(content)
    }
}
