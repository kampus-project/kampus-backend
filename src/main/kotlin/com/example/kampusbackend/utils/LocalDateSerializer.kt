package com.example.kampusbackend.utils

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import mu.KLogger
import mu.KotlinLogging
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalSerializationApi::class)
@Serializer(forClass = LocalDate::class)
object LocalDateSerializer : KSerializer<LocalDate?> {
    private val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    private val outputFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

    override fun serialize(encoder: Encoder, value: LocalDate?) {
        if (value == null) {
            encoder.encodeNull()
        } else {
            encoder.encodeString(value.format(outputFormatter))
        }
    }

    override val descriptor: SerialDescriptor
        = PrimitiveSerialDescriptor("LocalDate", PrimitiveKind.STRING)


    override fun deserialize(decoder: Decoder): LocalDate? {
        val dateString = decoder.decodeString()
        return if (dateString.isEmpty()) {
            null
        } else {
            LocalDate.parse(dateString, inputFormatter)
        }
    }
}