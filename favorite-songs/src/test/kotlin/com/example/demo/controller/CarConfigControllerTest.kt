//package com.example.demo.controller
//
//import com.example.demo.DemoApplication
//import com.example.demo.model.CarConfig
//import com.example.demo.model.CarExtra.Leder
//import com.example.demo.model.CarExtra.Panoramadach
//import com.example.demo.model.CarType
//import com.example.demo.repository.CarConfigRepository
//import org.junit.jupiter.api.AfterEach
//import org.junit.jupiter.api.Assertions.assertEquals
//import org.junit.jupiter.api.Assertions.assertNotNull
//import org.junit.jupiter.api.BeforeEach
//import org.junit.jupiter.api.Test
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.boot.test.web.client.TestRestTemplate
//import org.springframework.http.*
//import org.springframework.http.HttpMethod.*
//import org.springframework.http.HttpStatus.NO_CONTENT
//import org.springframework.http.HttpStatus.OK
//import org.springframework.test.context.ActiveProfiles
//
//@ActiveProfiles("integration-test")
//@SpringBootTest(
//    classes = [DemoApplication::class],
//    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class CarConfigControllerTest {
//    private val path = "/api/configurations"
//
//    @Autowired
//    lateinit var restTemplate: TestRestTemplate
//
//    @Autowired
//    lateinit var carConfigRepository: CarConfigRepository
//
//    private val requestHeaders = HttpHeaders().apply { setBasicAuth("user", "password") }
//
//    @BeforeEach
//    fun setup() {
//        carConfigRepository.deleteAll()
//    }
//
//    @AfterEach
//    fun cleanup() {
//        carConfigRepository.deleteAll()
//    }
//
//    @Test
//    fun `endpoint needs authentication`() {
//        val request = HttpEntity<String>(HttpHeaders())
//        val result = restTemplate.exchange(path, GET, request, List::class.java)
//
//        assertNotNull(result)
//        assertEquals(HttpStatus.UNAUTHORIZED, result.statusCode)
//    }
//
//    @Test
//    fun `return a car configuration`() {
//        val carConfig = dummyCarConfig()
//        val request = HttpEntity(carConfig, requestHeaders)
//        restTemplate.postForEntity(path, request, CarConfig::class.java)
//
//        val result = restTemplate.exchange("$path/${carConfig.id}", GET, HttpEntity<String>(requestHeaders), CarConfig::class.java)
//
//        assertNotNull(result)
//        assertEquals(carConfig, result.body)
//
//        assertEquals(OK, result.statusCode)
//    }
//
//    @Test
//    fun `update a car configuration`() {
//        val carConfig = dummyCarConfig()
//        val request = HttpEntity(carConfig, requestHeaders)
//        restTemplate.postForEntity(path, request, CarConfig::class.java)
//
//        val updatedCarConfig = carConfig.copy(extras = setOf(Leder, Panoramadach))
//        restTemplate.exchange("$path/${carConfig.id}", PUT, HttpEntity(updatedCarConfig, requestHeaders), CarConfig::class.java)
//
//        val result = restTemplate.exchange("$path/${carConfig.id}", GET, HttpEntity<String>(requestHeaders), CarConfig::class.java)
//
//        assertNotNull(result)
//        assertEquals(updatedCarConfig, result.body)
//
//        assertEquals(OK, result.statusCode)
//    }
//
//    @Test
//    fun `delete a car configuration`() {
//        val carConfig = dummyCarConfig()
//        val request = HttpEntity(carConfig, requestHeaders)
//        restTemplate.postForEntity(path, request, CarConfig::class.java)
//
//        val resultBeforeDelete = restTemplate.exchange(path, GET, request, List::class.java)
//        assertEquals(1, resultBeforeDelete.body!!.size)
//
//        val result = restTemplate.exchange("$path/${carConfig.id}", DELETE, HttpEntity<String>(requestHeaders), Unit::class.java)
//
//        assertNotNull(result)
//        assertEquals(NO_CONTENT, result.statusCode)
//
//        val resultAfterDelete = restTemplate.exchange(path, GET, request, List::class.java)
//        assertEquals(0, resultAfterDelete.body!!.size)
//    }
//}
//
//fun dummyCarConfig() = CarConfig(
//    type = CarType.Cabriolet,
//    klasse = "C-Klasse",
//    enginePower = 150,
//    color = "green",
//    extras = setOf(Leder),
//)