package com.example.kampusbackend.dto

import com.example.kampusbackend.entity.StudentEntity
import com.example.kampusbackend.utils.LocalDateSerializer
import jakarta.persistence.Column
import kotlinx.serialization.Serializable
import java.time.LocalDate

data class StudentDto(
    var firstName: String? = null,
    var lastName: String? = null,
    var middleName: String? = null,
    @Serializable(LocalDateSerializer::class)
    var dateBirth: LocalDate? = null,
    @Serializable(LocalDateSerializer::class)
    var receiptDate: LocalDate? = null,
    var universityName: String? = null,
    var courseTitle: String? = null,
    var courseNumber: Int? = null,
    var averageGrade: Double? = null,
    var studyForm:  String? = null,
    var educationForm:   String? = null,
    var typeHighEducation: String? = null,
    var localResident:  Boolean? = null,
)

fun StudentDto.toEntity() = StudentEntity(
    firstName = firstName,
    lastName = lastName,
    middleName = middleName,
    dateBirth = dateBirth,
    receiptDate = receiptDate,
    universityName = universityName,
    courseTitle = courseTitle,
    courseNumber = courseNumber,
    studyForm = studyForm,
    educationForm = educationForm,
    typeHighEducation = typeHighEducation,
    localResident = localResident,
)