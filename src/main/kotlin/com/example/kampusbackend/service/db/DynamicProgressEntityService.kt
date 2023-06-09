package com.example.kampusbackend.service.db

import com.example.kampusbackend.entity.DynamicProgressEntity
import com.example.kampusbackend.exception.DynamicProgressNotFoundException
import com.example.kampusbackend.repository.DynamicProgressRepository
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class DynamicProgressEntityService(
	private val dynamicProgressRepository: DynamicProgressRepository
) {
	fun getAllDynamicProgress(): List<DynamicProgressEntity> = dynamicProgressRepository.findAll()

	fun getDynamicProgressByDate(date: LocalDate): DynamicProgressEntity {
		try {
			return dynamicProgressRepository.findByProgressDate(date)
		}
		catch (e: EmptyResultDataAccessException) {
			throw DynamicProgressNotFoundException("It was not possible to find statistics for $date")
		}
	}
}