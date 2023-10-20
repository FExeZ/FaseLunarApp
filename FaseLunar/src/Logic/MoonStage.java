package Logic;

import java.util.Calendar;
import java.util.Date;

public class MoonStage {
    /**
     * Calcula la posición de la luna
     */
    private int determinarPosicion(Date d) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        int day = cal.get(Calendar.DATE);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        double c, e, jd = 0.0;
        int b = 0;
        if (day < 3) {
            year--;
            month += 12;
        }
        ++month;
        c = 365.25 * year;
        e = 30.6 * month;
        jd = c + e + day - 694039.09;
        jd /= 29.5305882;
        b = (int) jd;
        jd -= b;
        b = (int)Math.round(jd * 8);
        if (b >= 8)
            b = 0;
        return b;
    }
    /**
     * Establece la Fase lunar
     */
    public Phase calcularFase(Date d) {
        switch (determinarPosicion(d) ) {
            case 0:
                return Phase.LunaNueva;
            case 1:
                return Phase.CrecienteIluminante;
            case 2:
                return Phase.CuartoCreciente;
            case 3:
                return Phase.GibosaIluminante;
            case 4:
                return Phase.LunaLlena;
            case 5:
                return Phase.GibosaMenguante;
            case 6:
                return Phase.CuartoMenguante;
            case 7:
                return Phase.CrecienteMenguante;
        }
        throw new RuntimeException("Error al determinar la fase de la luna.");
    }
}
