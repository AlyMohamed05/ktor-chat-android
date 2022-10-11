package com.silverbullet.ktorchat.data.mappers

import com.silverbullet.ktorchat.data.remote.dto.MessageDto
import com.silverbullet.ktorchat.domain.model.Message
import java.text.DateFormat
import java.util.Date

fun MessageDto.toMessage(): Message {
    val date = Date(timestamp)
    val formattedDate = DateFormat
        .getDateInstance(DateFormat.DEFAULT)
        .format(date)
    return Message(
        text = text,
        username = username,
        formattedTime = formattedDate
    )
}