package io.duhanmo.quicknote

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowManager
import com.intellij.ui.components.JBTextArea
import java.awt.Component
import java.awt.Container

object QuicknoteUtil {
    const val TOOL_WINDOW_ID = "Quicknote"

    fun getToolWindow(project: Project): ToolWindow? {
        return ToolWindowManager.getInstance(project).getToolWindow(TOOL_WINDOW_ID)
    }

    fun findJBTextArea(component: Component?): JBTextArea? {
        if (component == null) return null

        if (component is JBTextArea) {
            return component
        }

        if (component is Container) {
            for (child in component.components) {
                val found = findJBTextArea(child)
                if (found != null) {
                    return found
                }
            }
        }
        return null
    }
}
