import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class Main {
    public static void main(String[] args) {

        // INSTANCIANDO DATA-HORA
        //   https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html

        DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter fmt2 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        DateTimeFormatter fmt3 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").withZone(ZoneId.systemDefault());
        DateTimeFormatter fmt4 = DateTimeFormatter.ISO_DATE_TIME;
        DateTimeFormatter fmt5 = DateTimeFormatter.ISO_INSTANT;

        LocalDate d01 = LocalDate.now();
        System.out.println("d01: " + d01);
        LocalDateTime d02 = LocalDateTime.now();
        System.out.println("d02: " + d02);
        Instant d03 = Instant.now();
        System.out.println("d03: " + d03);
        LocalDate d04 = LocalDate.parse("2020-04-20");
        System.out.println("d04: " + d04);
        LocalDateTime d05 = LocalDateTime.parse("2020-04-20T10:30:30");
        System.out.println("d05: " + d05);
        Instant d06 = Instant.parse("2020-04-20T01:30:00Z"); // Ao usar o INSTANT é obrigatório usar o "Z" ou o "-03:00"
        System.out.println("d06: " + d06);
        Instant d07 = Instant.parse("2020-04-20T10:30:30-03:00");
        System.out.println("d07: " + d07);
        LocalDate  d08 = LocalDate.parse("12/01/2026",fmt1);
        System.out.println("d08: " + d08);
        LocalDateTime do09 = LocalDateTime.parse("12/01/2026 01:30",fmt2);
        System.out.println("do09: " + do09);
        LocalDate d10 = LocalDate.of(2013, 10, 1);
        System.out.println("d10: " + d10);
        LocalDateTime d11 = LocalDateTime.of(2020, 10, 10, 10, 10);
        System.out.println("d11: " + d11);
        System.out.println(" ");
        System.out.println(" ");


        // FORMATANDO DATA-HORA

        System.out.println("D04 = " + d04.format(fmt1));
        System.out.println("D04 = " + fmt1.format(d04));
        System.out.println("D04 = " + d04.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        System.out.println("D05 = " + d05.format(fmt1));
        System.out.println("D05 = " + d05.format(fmt2));
        System.out.println("D05 = " + d05.format(fmt4));

        System.out.println("D06 = " + fmt3.format(d06));
        System.out.println("D06 = " + fmt5.format(d06));

        // CONVERTENDO DATA-HORA GLOBAL PARA LOCAL

        LocalDate r1 = LocalDate.ofInstant(d06, ZoneId.systemDefault()); //Sempre que transformar uma data INSTANT para local irá sofrer a conversão para o fuso local
        System.out.println("r1 = " + r1);
        LocalDate r2 = LocalDate.ofInstant(d06, ZoneId.of("Portugal"));
        System.out.println("r2 = " + r2);
        LocalDateTime r3 = LocalDateTime.ofInstant(d06, ZoneId.systemDefault());
        System.out.println("r3 = " + r3);
        LocalDateTime r4 = LocalDateTime.ofInstant(d06, ZoneId.of("Portugal"));
        System.out.println("r4 = " + r4);

        System.out.println("D04 Dia = "  + d04.getDayOfMonth());
        System.out.println("D04 Mês = "+ d04.getMonth());
        System.out.println("D04 Ano = "+ d04.getYear());
        System.out.println("D04 Dia do ano = "+ d04.getDayOfYear());
        System.out.println("D04 Mes = "+ d04.getMonthValue());
        System.out.println();
        System.out.println();

        // CÁLCULOS COM DATA-HORA

        LocalDate pastWeekLocalDate = LocalDate.now().minusDays(7);
        System.out.println("pastWeekLocalDate: " + pastWeekLocalDate);
        LocalDate nextWeekLocaldate = LocalDate.now().plusDays(7);
        System.out.println("nextWeekLocaldate: " + nextWeekLocaldate);

        LocalDateTime pastWeekLocalDateTime = LocalDateTime.now().minusDays(7);
        System.out.println("pastWeekLocalDateTime: " + pastWeekLocalDateTime);
        LocalDateTime nextWeekLocalDateTime = LocalDateTime.now().plusDays(7);
        System.out.println("nextWeekLocalDateTime: " + nextWeekLocalDateTime);

        Instant pastWeekInstant = d06.minus(7, ChronoUnit.DAYS);
        System.out.println("pastWeekInstant: " + pastWeekInstant);
        Instant nextWeekInstant = d06.plus(7, ChronoUnit.DAYS);
        System.out.println("nextWeekInstant: " + nextWeekInstant);

        Duration t1 = Duration.between(pastWeekLocalDate.atStartOfDay(), nextWeekLocaldate.atTime(0,0)); //LocalDate não pode ser calculado, pois não tem horário, add hora "atTime(0, 0)" ou usar ".atStartOfDay()"
        System.out.println("t1 DIAS= " + t1.toDays());
        System.out.println("t1 HORAS= " + t1.toHours());

        Duration t2 = Duration.between(pastWeekLocalDateTime, nextWeekLocalDateTime);
        System.out.println("t2 DIAS= " + t2.toDays());
        System.out.println("t2 HORAS= " + t2.toHours());

        Duration t3 = Duration.between(pastWeekInstant, d06);
        System.out.println("t3 DIAS= " + t3.toDays());
        System.out.println("t3 HORAS= " + t3.toHours());

        Duration t4 = Duration.between(d06, pastWeekInstant);
        System.out.println("t4 DIAS= " + t4.toDays());
        System.out.println("t4 HORAS= " + t4.toHours());

    }
}

