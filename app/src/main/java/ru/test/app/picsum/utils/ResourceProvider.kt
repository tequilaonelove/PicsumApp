package ru.tequila.apps.radio.deep.ui.utils

import androidx.annotation.StringRes

/**
 * Can be used for getting resources
 * Should be changed to support different themes
 */
interface ResourceProvider {
  fun string(@StringRes id: Int): String
}