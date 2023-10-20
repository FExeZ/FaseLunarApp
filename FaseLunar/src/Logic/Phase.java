package Logic;

public class Phase {
    public static Phase LunaNueva = new Phase("lunanueva");
    public static Phase CrecienteIluminante = new Phase("crecienteiluminante");
    public static Phase CuartoCreciente = new Phase("cuartocreciente");
    public static Phase GibosaIluminante = new Phase("gibosailuminante");
    public static Phase LunaLlena = new Phase("lunallena");
    public static Phase GibosaMenguante = new Phase("gibosamenguante");
    public static Phase CuartoMenguante = new Phase("cuartomenguante");
    public static Phase CrecienteMenguante = new Phase("crecientemenguante");

    private String name;

    private Phase(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getImagePath() {
        // Devuelve la ruta de la imagen basándose en el nombre de la fase
        return name.toLowerCase() + ".jpg";
    }



}
