package com.medisync.service;

import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AppointmentServiceTest {
    private AppointmentService appointmentService;

    @BeforeMethod
    public void setUp(){
        appointmentService = new AppointmentService();
    }

    @Test
    public void testF(){
        Assert.assertEquals(appointmentService.f(), "some" );
    }

    @Test
    public void testCreate() {
    }

    @Test
    public void testUpdate() {
    }

    @Test
    public void testGetAll() {
    }

    @Test
    public void testGetNextAvailableForDoctor() {
    }

    @Test
    public void testGetByPatientId() {
    }

    @Test
    public void testCopyProperties() {
    }
}