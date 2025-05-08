SELECT *
FROM appointments
WHERE doctor_id = :doctorId
AND status = :status
AND appointment_date >= :currentTime
ORDER BY appointment_date ASC;