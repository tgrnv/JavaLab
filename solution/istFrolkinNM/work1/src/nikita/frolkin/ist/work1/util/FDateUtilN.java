package nikita.frolkin.ist.work1.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * @author tervaskanto on 25.02.15
 */
public class FDateUtilN {
    public static final String fDATE_PATTERNn = "dd-MM-yyyy";
    private static final DateTimeFormatter fformattern = DateTimeFormatter.ofPattern(fDATE_PATTERNn);

    public static String fFormatN(LocalDate fdaten) {
        return fdaten != null ? fformattern.format(fdaten) : null;
    }

    public static LocalDate fParseN(String fdateStringn) {
        LocalDate fdaten;
        try {
            fdaten = fformattern.parse(fdateStringn, LocalDate::from);
        } catch (DateTimeParseException e) {
            fdaten = null;
        }
        return fdaten;
    }

    public static boolean fValidDateN(String fdateStringn) {
        return FDateUtilN.fParseN(fdateStringn) != null;
    }
}
