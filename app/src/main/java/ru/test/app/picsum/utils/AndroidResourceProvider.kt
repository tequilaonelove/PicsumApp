package ru.tequila.apps.radio.deep.ui.utils

import android.content.Context
import javax.inject.Inject

class AndroidResourceProvider @Inject constructor(
  private val context: Context
)  : ResourceProvider {

  override fun string(id: Int): String = context.resources.getString(id)

}