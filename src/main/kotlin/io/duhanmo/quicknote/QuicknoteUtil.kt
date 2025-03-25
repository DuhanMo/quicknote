package io.duhanmo.quicknote

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowManager
import com.intellij.ui.components.JBTextArea
import java.awt.Component
import java.awt.Container

object QuicknoteUtil {
    private const val TOOL_WINDOW_ID = "Quicknote"

    fun getToolWindow(project: Project): ToolWindow? {
        return ToolWindowManager.getInstance(project).getToolWindow(TOOL_WINDOW_ID)
    }

    fun findJBTextArea(component: Component?): JBTextArea? {
        if (component == null) return null

        val queue = java.util.ArrayDeque<Component>()
        queue.add(component)

        while (queue.isNotEmpty()) {
            val current = queue.poll()

            if (current is JBTextArea) {
                return current
            }

            if (current is Container) {
                for (child in current.components) {
                    queue.add(child)
                }
            }
        }

        return null
    }
}
