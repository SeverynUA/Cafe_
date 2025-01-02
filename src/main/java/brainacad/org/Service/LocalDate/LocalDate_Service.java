package brainacad.org.Service.LocalDate;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Locale;

public class LocalDate_Service
{
    public LocalDate ReturnCloseDateByName(String nameDay)
    {
        if (nameDay == null || nameDay.isEmpty())
        {
            throw new IllegalArgumentException("Name of the day cannot be null or empty.");
        }

        // Отримуємо сьогоднішню дату
        LocalDate today = LocalDate.now();
        DayOfWeek todayDay = today.getDayOfWeek();

        // Різниця між сьогоднішнім днем і цільовим днем
        int daysDifference = DayParseToWeek(nameDay).getValue() - todayDay.getValue();

        if (daysDifference < 0)
        {
            daysDifference += 7; // Якщо цільовий день уже пройшов, додаємо тиждень
        }

        return today.plusDays(daysDifference);
    }

    private DayOfWeek DayParseToWeek(String nameDay)
    {
        DayOfWeek targetDay;
        try
        {
            targetDay = DayOfWeek.valueOf(nameDay.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException e)
        {
            throw new IllegalArgumentException("Invalid day name: " + nameDay);
        }
        return targetDay;
    }
}
