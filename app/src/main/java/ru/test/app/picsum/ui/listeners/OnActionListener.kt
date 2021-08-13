package ru.test.app.picsum.ui.listeners

import ru.test.app.picsum.core.network.model.PicsDto

interface OnActionListener {
    fun onActionClick(picsDto: PicsDto)
}