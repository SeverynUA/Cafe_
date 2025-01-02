package brainacad.org.Serivce;

import brainacad.org.Service.LocalDate.LocalDate_Service;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class LocalDateServiceTest
{
    LocalDate_Service service = new LocalDate_Service();

    @Test
    public void testReturnCloseDateByName_ValidDay()
    {

        // Сьогоднішній день
        LocalDate today = LocalDate.now();

        // Тест для наступного понеділка
        String dayName = "Monday";
        LocalDate closestMonday = service.ReturnCloseDateByName(dayName);
        assertEquals(DayOfWeek.MONDAY, closestMonday.getDayOfWeek());
        assertTrue(closestMonday.isEqual(today) || closestMonday.isAfter(today), "Returned date should be today or in the future");

        // Тест для наступної неділі
        dayName = "Sunday";
        LocalDate closestSunday = service.ReturnCloseDateByName(dayName);
        assertEquals(DayOfWeek.SUNDAY, closestSunday.getDayOfWeek());
        assertTrue(closestSunday.isEqual(today) || closestSunday.isAfter(today), "Returned date should be today or in the future");
    }

    @Test
    public void testReturnCloseDateByName_InvalidDay()
    {
        // Невалідна назва дня
        String invalidDay = "Funday";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> service.ReturnCloseDateByName(invalidDay));
        assertEquals("Invalid day name: " + invalidDay, exception.getMessage());
    }

    @Test
    public void testReturnCloseDateByName_NullDay()
    {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> service.ReturnCloseDateByName(null));
        assertEquals("Name of the day cannot be null or empty.", exception.getMessage());
    }

    @Test
    public void testReturnCloseDateByName_EmptyDay()
    {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> service.ReturnCloseDateByName(""));
        assertEquals("Name of the day cannot be null or empty.", exception.getMessage());
    }
}
