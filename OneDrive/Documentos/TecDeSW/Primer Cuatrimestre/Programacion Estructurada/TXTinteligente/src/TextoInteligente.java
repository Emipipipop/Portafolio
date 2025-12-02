import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class TextoInteligente {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        int opcion; 
        boolean valido;
        String dato;

        do {  
        System.out.println("------------------------------------------------------");
        System.out.println("(1) para verificar numero de telefino");
        System.out.println("(2) Formatear y estandarizar correos electrónicos ");
        System.out.println("(3) Extraer fichas de un texto");
        System.out.println("(4) Salir");
        System.out.println("------------------------------------------------------");

            valido = true;
            System.out.print(">");
            opcion = sc.nextInt();
            System.out.println("------------------------------------------------------");
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese un numero de telefono:");
                    System.out.print(">");
                    dato = sc.nextLine();
                    valido = validarNumero(dato);
                    if (valido) {
                        System.out.println("Teléfono verificado");
                        valido = false;
                        System.out.println("------------------------------------------------------");
                    } else {
                        System.out.println("Error: ingresar un número válido");
                        System.out.println("------------------------------------------------------");
                    }
                break;

                case 2:    
                    System.out.println("ingrese una lista de correos separando los correos por una coma o espacios usando el siguiente formato\n usuario@tecsw.com");

                        System.out.print(">");
                        String entrada = sc.nextLine();
                        String datos [] = entrada.split("[,\\s\\n]+");

                    System.out.println("------------------------------------------------------");

                    procesarCorreos(datos);

                    for (String correo : datos) {
                        validarFormatoCorreos(correo);
                    }
                    valido = false;
                    System.out.println("------------------------------------------------------");
                break;
                case 3:
                System.out.println("Ingrese un texto con una fecha con el formato DD/MM/YYYY");
                System.out.print(">");
                String fecha = sc.nextLine();
                System.out.println("------------------------------------------------------");
                
                List<String> resultado = extraerFechas(fecha);

                if (!resultado.isEmpty()) {
                     System.out.println("Se encontraron " + resultado.size() + " fechas");
                       /*  for (String R2 : resultado) {
                            System.out.println(R2);
                        }*/
                    } else {
                    System.out.println("No se encontraron fechas en el texto.");
                    }
                    System.out.println("Fechas encontradas: " + resultado);
                    valido = false;
                    System.out.println("------------------------------------------------------");
                break;

                case 4:
                    System.out.println("Fin del programa");
                    valido = true; 
                break;

                default:
                    System.out.println("Opcion no encontrada");
                    valido = false;
                    break;
            }
            
        } while (!valido);
    }

    public static boolean validarNumero(String telefono){

    Pattern p = Pattern.compile("\\d{10}"); 
    Matcher m = p.matcher(telefono);

    if(m.matches()){
        return true;
    } 
    else{
        return false;
      }
    }

    public static void procesarCorreos(String [] correos){
        for (int i = 0; i < correos.length; i++) {
        correos[i] = correos[i].trim().toLowerCase();
       }
    }

    public static boolean validarFormatoCorreos(String correos){
        
        String regex = "[A-Za-z0-9._%+-]+@dominio\\.com$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(correos);

        if(!m.matches()){
            System.out.println("Correo " + correos + " invalido");
            return false;
        }else{
        System.out.println("Correo " + correos + " valido");
        return true;
        }
    }

    //metodo para extraer fechas
     public static List<String> extraerFechas(String texto) {
        String regex = "(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(texto);

        List<String> fechas = new ArrayList<>();

        while (m.find()) {
            fechas.add(m.group());
        }
        return fechas;
     }
}
