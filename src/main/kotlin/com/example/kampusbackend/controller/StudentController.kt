package com.example.kampusbackend.controller

import com.example.kampusbackend.dto.response.StudentDataDto
import com.example.kampusbackend.dto.StudentDto
import com.example.kampusbackend.dto.toEntity
import com.example.kampusbackend.entity.StudentEntity
import com.example.kampusbackend.service.db.StudentEntityService
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@SecurityRequirement(name = "JWT")
@Tag(name = "StudentController", description = "Взаимодействие с таблицей 'Student'")
@RestController
@RequestMapping("/api/v1/student")
class StudentController(
	private val studentEntityService: StudentEntityService,
) {

	@SecurityRequirement(name = "JWT")
	@Operation(summary = "Получить пользователя по id", description = "Получить пользователя по id")
	@GetMapping("/getStudentById/{studentId}")
	fun getStudentById(@PathVariable studentId: Long): StudentEntity = studentEntityService.getStudentById(studentId)

	@SecurityRequirement(name = "JWT")
	@Operation(summary = "Получить пользователя по username", description = "Получить пользователя по username")
	@GetMapping("/getStudentByUsername/{username}")
	fun getStudentByUsername(@PathVariable username: String): StudentEntity? = studentEntityService.getStudentByUsername(username)

	@SecurityRequirement(name = "JWT")
	@Operation(summary = "Получить список всех студентов", description = "Получить список всех студентов")
	@GetMapping("/getAllStudents")
	fun getAllStudents(): List<StudentEntity> = studentEntityService.getAllStudents()

	@SecurityRequirement(name = "JWT")
	@Operation(summary = "Получить количество студентов", description = "Получить количество студентов")
	@GetMapping("/getCountStudents")
	fun getCountStudents(): Long = studentEntityService.getCountStudents()

	@SecurityRequirement(name = "JWT")
	@Operation(
		summary = "Получить количество людей для каждого университета",
		description = "Получить количество людей для каждого университета"
	)
	@GetMapping("/getInfoUniversities")
	fun getInfoUniversities(): MutableList<StudentDataDto> = studentEntityService.getInfoUniversities()

	@SecurityRequirement(name = "JWT")
	@Operation(
		summary = "Получить количество людей для каждой формы обучения (бюджет/контракт)",
		description = "Получить количество людей для каждой формы обучения (бюджет/контракт)"
	)
	@GetMapping("/getInfoEducationForm")
	fun getInfoEducationForm(): MutableList<StudentDataDto> = studentEntityService.getInfoEducationForm()

	@SecurityRequirement(name = "JWT")
	@Operation(
		summary = "Получить количество людей для каждого вида высшего образования",
		description = "Получить количество людей для каждого вида высшего образования"
	)
	@GetMapping("/getInfoHighEducation")
	fun getInfoHighEducation(): MutableList<StudentDataDto> = studentEntityService.getInfoHighEducation()

	@SecurityRequirement(name = "JWT")
	@Operation(
		summary = "Получить количество людей для каждого направления обучения",
		description = "Получить количество людей для каждого направления обучения"
	)
	@GetMapping("/getInfoCourseTitle")
	fun getInfoCourseTitle(): MutableList<StudentDataDto> = studentEntityService.getInfoCourseTitle()

	@SecurityRequirement(name = "JWT")
	@Operation(
		summary = "Получить все данные для отображения статистики",
		description = "Получить все данные для отображения статистики типа [[Все студенты][Все данные]]"
	)
	@GetMapping("/getAllData")
	fun getAllData(): String {
		val data = arrayListOf(
			getCountStudents(),
			getInfoUniversities(),
			getInfoEducationForm(),
			getInfoHighEducation(),
			getInfoCourseTitle()
		)
		val objectMapper = ObjectMapper().apply {
			registerModule(JavaTimeModule())
		}
		return objectMapper.writeValueAsString(arrayListOf(getAllStudents(), data))
	}

	@SecurityRequirement(name = "JWT")
	@Operation(summary = "Сохранение данных о студентах", description = "Сохранение данных о студентах")
	@PostMapping("/saveStudent")
	fun saveStudent(@RequestBody studentDto: StudentDto): ResponseEntity<String> {
		studentEntityService.saveStudent(studentDto.toEntity())
		return ResponseEntity.ok(
			"Student successfully added to the table"
		)
	}

	@SecurityRequirement(name = "JWT")
	@Operation(
		summary = "Удалить данные",
		description = "Удаляет студента по его id"
	)
	@PostMapping("/deleteStudent/{studentId}")
	fun deleteStudentById(@PathVariable studentId: Long) {
		studentEntityService.deleteStudentById(studentId)
	}

	@SecurityRequirement(name = "JWT")
	@Operation(
		summary = "Обновить данные",
		description = "Обновляет данные студента по его id"
	)
	@PostMapping("/updateStudent/{studentId}")
	fun updateStudentById(@PathVariable studentId: Long, @RequestBody studentDto: StudentDto) {
		studentEntityService.updateStudent(studentId, studentDto)
	}
}