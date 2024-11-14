import java.time.LocalDate;
import java.util.Scanner;

public class ProyectoTickets_MireyaZamira {
    public static void main(String[] args) {

        // DECLARACIÓN DE VARIABLES
        Scanner s = new Scanner(System.in);

        // Variables de estado
        boolean encendido = true, // Estado del sistema
                nombreClienteValido = false, // Validez del nombre del cliente
                apeValido = false, // Validez del apellido
                idValido = false, // Validez del ID
                descuento1 = false,
                descuento2 = false,
                descuento3 = false;

        LocalDate fechaLocal = LocalDate.now(), // Fecha de algún evento
                fechaCriadas = LocalDate.parse("2024-11-20"), // Fecha 1
                fechaConciertoOto = LocalDate.parse("2024-11-28"), // Fecha 2
                fechaConciertoJazz = LocalDate.parse("2024-12-06"); // Fecha 3;

        char eur = '€', por = '%', c, letra, dniLetra;
        double totalEntradaIndividual = 0, // Total a mostrar por entrada
                precioBaseDescuento = 0, // Precio base + IVA
                costeTotalCompraEntradas = 0, // Coste total de la factura con todas las entradas compradas de una vez.;
                ingresoTotlCri = 0, // Ingresos totales de las criadas
                ingresoTotlOto = 0, // Ingresos totales de las criadas
                ingresoTotlJazz = 0; // Ingresos totales de las criadas



        // Opciones de menú y variables de texto
        String op1 = " ", op2 = " ", op3 = " ", op4 = " ", op5 = " ",

                // Datos del admin
                nombre, // Nombre del admin
                userAdmin = "admin", // Usuario admin
                userIntro, // Entrada del usuario
                passAdmin = "1234", // Contraseña del admin
                passIntro, // Contraseña introducida por el usuario

                fechaString = "", //Fecha en String
                codigo = "", //código individual de cada entrada
                entrada = "",
                dinero = "",

                // Datos del cliente
                nombreCliente = "", // Nombre del cliente
                ape = "", // Apellido del cliente
                idCompleto = "", // ID completo del cliente
                listaLetras = "TRWAGMYFPDXBNJZSQVHLCKE", // Letras del DNI
                numerosStr; // Números del DNI como String



        // Constantes
        final int NUM_PLATEAS = 140, NUM_BUTACAS = 200, NUM_ANFITEATROS = 200;

        float total = 0, dineroIntro = 0, cambio;

        // Contadores de entradas
        int
                // Precios de entradas
                precioBase = 0,
                plateasPVP1 = 17, // Precio venta platea tipo 1
                plateasPVP2 = 12, // Precio venta platea tipo 2
                plateasPVP3 = 20, // Precio venta platea tipo 3
                butacasPVP1 = 20, // Precio venta butaca tipo 1
                butacasPVP2 = 15, // Precio venta butaca tipo 2
                butacasPVP3 = 25, // Precio venta butaca tipo 3
                anfiPVP1 = 13, // Precio venta anfiteatro tipo 1
                anfiPVP2 = 8, // Precio venta anfiteatro tipo 2
                anfiPVP3 = 15, // Precio venta anfiteatro tipo 3

                // Fechas
                dias = 0, diaEvento = 0, diferenciaDias = 0, // variables para conocer los días hasta el evento

                // Descuentos
                desc1 = 5, // Descuento tipo 1
                desc2 = 7, // Descuento tipo 2
                desc3 = 3, // Descuento tipo 3

                // Variables relacionadas con la venta de entradas de eventos
                numEntradas = 0, // Número de entradas que compra el usuario

                criadasVend = 0, // entradas vendidas para el evento de criadas
                criadasDispTotal = 540, // entradas disponibles para el evento de criadas
                criadasDispPlat = 140, // entradas disponibles de las criadas en platea
                criadasDispBut = 200, // entradas disponibles de las criadas en butaca
                criadasDispAnfi = 200, // entradas disponibles de las criadas en anfiteatro

                otoVend = 0, // entradas vendidas para el evento de otoño
                otoDispTotal = 540, // entradas disponibles para el evento de otoño
                otoDispPlat = 140, // entradas disponibles del evento de otoño en platea
                otoDispBut = 200, // entradas disponibles del evento de otoño en butaca
                otoDispAnfi = 200, // entradas disponibles del evento de otoño en anfiteatro

                jazzVend = 0, // entradas vendidas para el evento de jazz
                jazzDispTotal = 540, // entradas disponibles para el evento de jazz
                jazzDispPlat = 140, // entradas disponibles del evento de jazz en platea
                jazzDispBut = 200, // entradas disponibles del evento de jazz en butaca
                jazzDispAnfi = 200, // entradas disponibles del evento de jazz en anfiteatro

                // Variables auxiliares
                numId, // Número de identificación (ID)
                restoId, // Resto del ID

                // Variables para los cambios de monedas
                cambioTotal = 0, // Total de cambio a devolver

                // Variables para manejar el efectivo disponible y el cambio a devolver
                // Billetes disponibles
                billete500e = 100, billete200e = 100, billete100e = 100, billete50e = 100,
                billete20e = 100, billete10e = 100, billete5e = 100,

                // Monedas disponibles
                monedas2e = 100, monedas1e = 100, monedas50cent = 100, monedas20cent = 100,
                monedas10cent = 100, monedas5cent = 100, monedas2cent = 100, monedas1cent = 100,

                // Variables para el cambio a devolver
                // Billetes a devolver
                billete500eDevolver = 0, billete200eDevolver = 0, billete100eDevolver = 0,
                billete50eDevolver = 0, billete20eDevolver = 0,
                billete10eDevolver = 1, // Inicializado en 1
                billete5eDevolver = 0,

                // Monedas a devolver
                monedas2eDevolver = 0, monedas1eDevolver = 0, monedas50centDevolver = 0,
                monedas20centDevolver = 0, monedas10centDevolver = 0, monedas5centDevolver = 0,
                monedas2centDevolver = 0, monedas1centDevolver = 0;


        //Muestra el título:
        System.out.println("""
                ████████╗███████╗ █████╗ ████████╗██████╗  ██████╗
                ╚══██╔══╝██╔════╝██╔══██╗╚══██╔══╝██╔══██╗██╔═══██╗
                   ██║   █████╗  ███████║   ██║   ██████╔╝██║   ██║
                   ██║   ██╔══╝  ██╔══██║   ██║   ██╔══██╗██║   ██║
                   ██║   ███████╗██║  ██║   ██║   ██║  ██║╚██████╔╝
                   ╚═╝   ╚══════╝╚═╝  ╚═╝   ╚═╝   ╚═╝  ╚═╝ ╚═════╝
                
                ███╗   ███╗██╗   ██╗███╗   ██╗██╗ ██████╗██╗██████╗  █████╗ ██╗
                ████╗ ████║██║   ██║████╗  ██║██║██╔════╝██║██╔══██╗██╔══██╗██║
                ██╔████╔██║██║   ██║██╔██╗ ██║██║██║     ██║██████╔╝███████║██║
                ██║╚██╔╝██║██║   ██║██║╚██╗██║██║██║     ██║██╔═══╝ ██╔══██║██║
                ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║╚██████╗██║██║     ██║  ██║███████╗
                ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝ ╚═════╝╚═╝╚═╝     ╚═╝  ╚═╝╚══════╝
                
                ███╗   ███╗ █████╗ ██████╗ ████████╗ ██████╗ ███████╗
                ████╗ ████║██╔══██╗██╔══██╗╚══██╔══╝██╔═══██╗██╔════╝
                ██╔████╔██║███████║██████╔╝   ██║   ██║   ██║███████╗
                ██║╚██╔╝██║██╔══██║██╔══██╗   ██║   ██║   ██║╚════██║
                ██║ ╚═╝ ██║██║  ██║██║  ██║   ██║   ╚██████╔╝███████║
                ╚═╝     ╚═╝╚═╝  ╚═╝╚═╝  ╚═╝   ╚═╝    ╚═════╝ ╚══════╝
                """);

        while (encendido) {
            System.out.println(""" 
                    ╔═══════════════════ MENÚ PRINCIPAL ═══════════════════╗
                    ║  A. Venta de entradas para un evento.                ║
                    ║  B. Consultar el estado de un evento.                ║
                    ║  C. Menú de Administrador.                           ║
                    ╚══════════════════════════════════════════════════════╝
                    """);

            System.out.print("Ingrese la opción que desee (A/B/C): ");
            op1 = s.nextLine().toUpperCase();
            System.out.println();

            switch (op1) {
                case "A":
                    op2 = " ";
                    System.out.printf(""" 
                                    ╔══════════ EVENTOS ═════════════ FECHAS ═══════ PVP PLATEAS ══════ PVP BUTACAS ══════ PVP ANFITEATRO ═════ DESC ANTICIPADO ══╗
                                    ║  a. Las criadas.            ║   %s   ║           %dE   ║           %dE   ║              %dE   ║                %d%c   ║
                                    ║  b. II Concierto de Otoño.  ║   %s   ║           %dE   ║           %dE   ║               %dE   ║                %d%c   ║
                                    ║  c. Concierto de Jazz.      ║   %s   ║           %dE   ║           %dE   ║              %dE   ║                %d%c   ║
                                    ╚═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝
                                    """, fechaCriadas.toString(), plateasPVP1, butacasPVP1, anfiPVP1, desc1, por,
                            fechaConciertoOto.toString(), plateasPVP2, butacasPVP2, anfiPVP2, desc2, por,
                            fechaConciertoOto.toString(), plateasPVP3, butacasPVP3, anfiPVP3, desc3, por);
                    System.out.println();
                    while (op2.equals(" ")) {

                        System.out.print("Ingrese el evento que quiere ver (a/b/c): ");
                        op2 = s.nextLine().toLowerCase();
                        System.out.println();

                        switch (op2) {
                            case "a": //opción para las criadas
                            case "b": //opción para II Concierto de Otoño
                            case "c": //opción para el concierto de jazz
                                if (op2.equals("a")) {
                                    System.out.println(""" 
                                            ╔════════ ELIGE UN TIPO DE ENTRADA PARA "LAS CRIADAS" ════════╗
                                            ║  a. Entrada Platea                                          ║
                                            ║  b. Entrada Butaca                                          ║
                                            ║  c. Entrada Anfiteatro                                      ║
                                            ╚═════════════════════════════════════════════════════════════╝
                                            """);
                                } else if (op2.equals("b")) {
                                    System.out.println(""" 
                                            ╔════════ ELIGE UN TIPO DE ENTRADA PARA "II CONCIERTO DE OTOÑO" ════════╗
                                            ║  a. Entrada Platea                                                    ║
                                            ║  b. Entrada Butaca                                                    ║
                                            ║  c. Entrada Anfiteatro                                                ║
                                            ╚═══════════════════════════════════════════════════════════════════════╝
                                            """);
                                } else {
                                    System.out.println(""" 
                                            ╔════════ ELIGE UN TIPO DE ENTRADA PARA "CONCIERTO DE JAZZ" ════════╗
                                            ║  a. Entrada Platea                                                ║
                                            ║  b. Entrada Butaca                                                ║
                                            ║  c. Entrada Anfiteatro                                            ║
                                            ╚═══════════════════════════════════════════════════════════════════╝
                                            """);
                                }

                                do {
                                    System.out.print("Ingresa el tipo de entrada: ");
                                    op3 = s.nextLine().toLowerCase();
                                    if (!op3.equals("a") && !op3.equals("b") && !op3.equals("c")) System.out.println("  · Opción no válida ·");
                                } while (!op3.equals("a") && !op3.equals("b") && !op3.equals("c"));

                                switch (op3) {
                                    case "a":
                                        if (op2.equals("a")){
                                            if (criadasDispPlat < 1) System.out.println("Lo siento. Para 'Las Criadas' no quedan entradas en Plateas.");
                                            else{
                                                do {
                                                    System.out.print("¿Cuántas entradas a plateas quieres? ");
                                                    numEntradas = Integer.parseInt(s.nextLine());
                                                    System.out.println();
                                                    if (numEntradas > criadasDispPlat)
                                                        System.out.println("Número de entradas erróneo, quedan " + criadasDispPlat + " entradas.");
                                                    if (numEntradas < 1) System.out.println("Debes comprar al menos una entrada...");
                                                } while (numEntradas < 1 || numEntradas > criadasDispPlat);

                                                // Operamos con el número de entradas a comprar y actualizamos los datos:
                                                criadasDispTotal -= numEntradas;
                                                criadasDispPlat -= numEntradas;
                                                criadasVend += numEntradas;
                                            }
                                        }
                                        if (op2.equals("b")){
                                            if (otoDispPlat < 1) System.out.println("Lo siento. Para el 'II Concierto de Otoño' no quedan entradas en Plateas.");
                                            else{
                                                do {
                                                    System.out.print("¿Cuántas entradas a plateas quieres? ");
                                                    numEntradas = Integer.parseInt(s.nextLine());
                                                    System.out.println();
                                                    if (numEntradas > otoDispPlat)
                                                        System.out.println("Número de entradas erróneo, quedan " + otoDispPlat + " entradas.");
                                                    if (numEntradas < 1) System.out.println("Debes comprar al menos una entrada...");
                                                } while (numEntradas < 1 || numEntradas > otoDispPlat);

                                                // Operamos con el número de entradas a comprar y actualizamos los datos:
                                                otoDispTotal -= numEntradas;
                                                otoDispPlat -= numEntradas;
                                                otoVend += numEntradas;
                                            }
                                        }
                                        if (op2.equals("c")){
                                            if (jazzDispPlat < 1) System.out.println("Lo siento. Para el 'Concierto de Jazz' no quedan entradas en Plateas.");
                                            else{
                                                do {
                                                    System.out.print("¿Cuántas entradas a plateas quieres? ");
                                                    numEntradas = Integer.parseInt(s.nextLine());
                                                    System.out.println();
                                                    if (numEntradas > jazzDispPlat)
                                                        System.out.println("Número de entradas erróneo, quedan " + jazzDispPlat + " entradas.");
                                                    if (numEntradas < 1) System.out.println("Debes comprar al menos una entrada...");
                                                } while (numEntradas < 1 || numEntradas > jazzDispPlat);

                                                // Operamos con el número de entradas a comprar y actualizamos los datos:
                                                jazzDispTotal -= numEntradas;
                                                jazzDispPlat -= numEntradas;
                                                jazzVend += numEntradas;
                                            }
                                        }
                                        break;

                                    case "b":
                                        if (op2.equals("a")){
                                            if (criadasDispBut < 1) System.out.println("Lo siento. Para 'Las Criadas' no quedan entradas en Butacas.");
                                            else{
                                                do {
                                                    System.out.print("¿Cuántas entradas a butacas quieres? ");
                                                    numEntradas = Integer.parseInt(s.nextLine());
                                                    System.out.println();
                                                    if (numEntradas > criadasDispBut)
                                                        System.out.println("Número de entradas erróneo, quedan " + criadasDispBut + " entradas.");
                                                    if (numEntradas < 1) System.out.println("Debes comprar al menos una entrada...");
                                                } while (numEntradas < 1 || numEntradas > criadasDispBut);

                                                // Operamos con el número de entradas a comprar y actualizamos los datos:
                                                criadasDispTotal -= numEntradas;
                                                criadasDispBut -= numEntradas;
                                                criadasVend += numEntradas;
                                            }
                                        }
                                        if (op2.equals("b")){
                                            if (otoDispBut < 1) System.out.println("Lo siento. Para el 'II Concierto de Otoño' no quedan entradas en Butacas.");
                                            else{
                                                do {
                                                    System.out.print("¿Cuántas entradas a butacas quieres? ");
                                                    numEntradas = Integer.parseInt(s.nextLine());
                                                    System.out.println();
                                                    if (numEntradas > otoDispBut)
                                                        System.out.println("Número de entradas erróneo, quedan " + otoDispBut + " entradas.");
                                                    if (numEntradas < 1) System.out.println("Debes comprar al menos una entrada...");
                                                } while (numEntradas < 1 || numEntradas > otoDispBut);

                                                // Operamos con el número de entradas a comprar y actualizamos los datos:
                                                otoDispTotal -= numEntradas;
                                                otoDispBut -= numEntradas;
                                                otoVend += numEntradas;
                                            }
                                        }
                                        if (op2.equals("c")){
                                            if (jazzDispBut < 1) System.out.println("Lo siento. Para el 'Concierto de Jazz' no quedan entradas en Butacas.");
                                            else{
                                                do {
                                                    System.out.print("¿Cuántas entradas a butacas quieres? ");
                                                    numEntradas = Integer.parseInt(s.nextLine());
                                                    System.out.println();
                                                    if (numEntradas > jazzDispBut)
                                                        System.out.println("Número de entradas erróneo, quedan " + jazzDispBut + " entradas.");
                                                    if (numEntradas < 1) System.out.println("Debes comprar al menos una entrada...");
                                                } while (numEntradas < 1 || numEntradas > jazzDispBut);

                                                // Operamos con el número de entradas a comprar y actualizamos los datos:
                                                jazzDispTotal -= numEntradas;
                                                jazzDispBut -= numEntradas;
                                                jazzVend += numEntradas;
                                            }
                                        }
                                        break;

                                    case "c":
                                        if (op2.equals("a")){
                                            if (criadasDispAnfi < 1) System.out.println("Lo siento. Para 'Las Criadas' no quedan entradas en Anfiteatro.");
                                            else{
                                                do {
                                                    System.out.print("¿Cuántas entradas a anfiteatro quieres? ");
                                                    numEntradas = Integer.parseInt(s.nextLine());
                                                    System.out.println();
                                                    if (numEntradas > criadasDispAnfi)
                                                        System.out.println("Número de entradas erróneo, quedan " + criadasDispAnfi + " entradas.");
                                                    if (numEntradas < 1) System.out.println("Debes comprar al menos una entrada...");
                                                } while (numEntradas < 1 || numEntradas > criadasDispAnfi);

                                                // Operamos con el número de entradas a comprar y actualizamos los datos:
                                                criadasDispTotal -= numEntradas;
                                                criadasDispAnfi -= numEntradas;
                                                criadasVend += numEntradas;
                                            }
                                        }
                                        if (op2.equals("b")){
                                            if (otoDispAnfi < 1) System.out.println("Lo siento. Para el II Concierto de Otoño no quedan entradas en Anfiteatro.");
                                            else{
                                                do {
                                                    System.out.print("¿Cuántas entradas a anfiteatro quieres? ");
                                                    numEntradas = Integer.parseInt(s.nextLine());
                                                    System.out.println();
                                                    if (numEntradas > otoDispAnfi)
                                                        System.out.println("Número de entradas erróneo, quedan " + otoDispAnfi + " entradas.");
                                                    if (numEntradas < 1) System.out.println("Debes comprar al menos una entrada...");
                                                } while (numEntradas < 1 || numEntradas > otoDispAnfi);

                                                // Operamos con el número de entradas a comprar y actualizamos los datos:
                                                otoDispTotal -= numEntradas;
                                                otoDispAnfi -= numEntradas;
                                                otoVend += numEntradas;
                                            }
                                        }
                                        if (op2.equals("c")){
                                            if (jazzDispAnfi < 1) System.out.println("Lo siento. Para el Concierto de Jazz no quedan entradas en Anfiteatro.");
                                            else{
                                                do {
                                                    System.out.print("¿Cuántas entradas a anfiteatro quieres? ");
                                                    numEntradas = Integer.parseInt(s.nextLine());
                                                    System.out.println();
                                                    if (numEntradas > jazzDispAnfi)
                                                        System.out.println("Número de entradas erróneo, quedan " + jazzDispAnfi + " entradas.");
                                                    if (numEntradas < 1) System.out.println("Debes comprar al menos una entrada...");
                                                } while (numEntradas < 1 || numEntradas > jazzDispAnfi);

                                                // Operamos con el número de entradas a comprar y actualizamos los datos:
                                                jazzDispTotal -= numEntradas;
                                                jazzDispAnfi -= numEntradas;
                                                jazzVend += numEntradas;
                                            }
                                        }
                                        break;

                                    default:
                                        System.out.println("    · Opción no válida ·");
                                        System.out.println("Pulse la tecla ENTER para volver la menú principal.");
                                        s.nextLine();
                                        op3 = " ";
                                }

                                System.out.println("\n·····RECOLECCIÓN DE DATOS·····");
                                // Reseteo las booleanas de validación:
                                nombreClienteValido = false;
                                apeValido = false;
                                idValido = false;

                                //comprobamos que no introduzcan ningún valor numérico
                                while (!nombreClienteValido) {
                                    System.out.print("Nombre: ");
                                    nombreCliente = s.nextLine().toUpperCase();
                                    for (int i = 0; i < nombreCliente.length(); i++) {
                                        c = nombreCliente.charAt(i);
                                        nombreClienteValido = true;

                                        if (c <= '9' && c >= '0') {
                                            nombreClienteValido = false;
                                            System.out.println("    · Nombre introducido incorrecto ·");
                                            System.out.println("Vuelva a intentarlo. Pulse ENTER para continuar.");
                                            s.nextLine();
                                        }
                                    }
                                }

                                while (!apeValido) {
                                    System.out.print("Primer apellido: ");
                                    ape = s.nextLine().toUpperCase();
                                    for (int i = 0; i < ape.length(); i++) {
                                        c = ape.charAt(i);
                                        apeValido = true;

                                        if (c <= '9' && c >= '0') {
                                            apeValido = false;
                                            System.out.println("    · Apellido introducido incorrecto ·");
                                            System.out.println("Vuelva a intentarlo. Pulse ENTER para continuar.");
                                            s.nextLine();
                                        }
                                    }
                                }

                                while (!idValido) {//proceso de validación del ID
                                    System.out.print("Introduce tu número de identificación completo: ");
                                    idCompleto = s.nextLine();

                                    numerosStr = idCompleto.substring(0, 8); //obtener los primeros 8 digitos
                                    letra = idCompleto.charAt(8); //obtener la letra

                                    numId = Integer.parseInt(numerosStr);
                                    restoId = numId % 23;
                                    dniLetra = listaLetras.charAt(restoId);

                                    if (letra != dniLetra || idCompleto.length() != 9)
                                        System.out.println("\n     · Número de ID no válido. Vuelve a intentar ·");
                                    else
                                        idValido = true; //si el ID es correcto, el código sigue y no me muestra ningún mensaje
                                    System.out.println();
                                }

                                //reseteamos las variables de los descuentos
                                descuento1 = false;
                                descuento2 = false;
                                descuento3 = false;

                                // Validamos la fecha
                                System.out.print("Ingrese una fecha (YYYY-MM-DD), escriba 'NO' para escoger la fecha de hoy: ");
                                fechaString = s.nextLine().toLowerCase();

                                if (!fechaString.equals("no")) fechaLocal = LocalDate.parse(fechaString);

                                //si ingresa 'no' se pone la fecha actual
                                if (fechaString.equals("no")) fechaLocal = LocalDate.now();

                                //Comprobamos si se aplica descuento dependiendo de la fecha que ha ingresado el usuario:
                                if (op2.equals("a")){
                                    if (fechaLocal.isBefore(fechaCriadas.minusDays(6))) descuento1 = true;
                                    fechaString = fechaLocal.toString();
                                }
                                if (op2.equals("b")){
                                    if (fechaLocal.isBefore(fechaConciertoOto.minusDays(6))) descuento2 = true;
                                    fechaString = fechaLocal.toString();
                                }
                                if (op2.equals("c")){
                                    if (fechaLocal.isBefore(fechaConciertoJazz.minusDays(6))) descuento3 = true;
                                    fechaString = fechaLocal.toString();
                                }

                                // Reseteo variables de costes totales:
                                costeTotalCompraEntradas = 0;
                                totalEntradaIndividual = 0;

                                // CALCULAMOS EL PRECIO DE LAS ENTRADAS:
                                if (op2.equalsIgnoreCase("a")){
                                    if (op3.equalsIgnoreCase("a")){
                                        precioBase = plateasPVP1;
                                        precioBaseDescuento = precioBase;
                                        if (descuento1) precioBaseDescuento -= precioBase * 0.05;
                                        totalEntradaIndividual = precioBaseDescuento + precioBaseDescuento * 0.1;
                                    }
                                    if (op3.equalsIgnoreCase("b")){
                                        precioBase = butacasPVP1;
                                        precioBaseDescuento = precioBase;
                                        if (descuento1) precioBaseDescuento -= precioBase * 0.05;
                                        totalEntradaIndividual = precioBaseDescuento + precioBaseDescuento * 0.1;
                                    }
                                    if (op3.equalsIgnoreCase("c")){
                                        precioBase = anfiPVP1;
                                        precioBaseDescuento = precioBase;
                                        if (descuento1) precioBaseDescuento -= precioBase * 0.05;
                                        totalEntradaIndividual = precioBaseDescuento + precioBaseDescuento * 0.1;
                                    }
                                }
                                if (op2.equalsIgnoreCase("b")){
                                    if (op3.equalsIgnoreCase("a")){
                                        precioBase = plateasPVP2;
                                        precioBaseDescuento = precioBase;
                                        if (descuento2)precioBaseDescuento -= precioBase * 0.07;
                                        totalEntradaIndividual = precioBaseDescuento + precioBaseDescuento * 0.1;
                                    }
                                    if (op3.equalsIgnoreCase("b")){
                                        precioBase = butacasPVP2;
                                        precioBaseDescuento = precioBase;
                                        if (descuento2) precioBaseDescuento -= precioBase * 0.07;
                                        totalEntradaIndividual = precioBaseDescuento + precioBaseDescuento * 0.1;
                                    }
                                    if (op3.equalsIgnoreCase("c")){
                                        precioBase = anfiPVP2;
                                        precioBaseDescuento = precioBase;
                                        if (descuento2) precioBaseDescuento -= precioBase * 0.07;
                                        totalEntradaIndividual = precioBaseDescuento + precioBaseDescuento * 0.1;
                                    }
                                }
                                if (op2.equalsIgnoreCase("c")){
                                    if (op3.equalsIgnoreCase("a")){
                                        precioBase = plateasPVP3;
                                        precioBaseDescuento = precioBase;
                                        if (descuento3) precioBaseDescuento -= precioBase * 0.03;
                                        totalEntradaIndividual = precioBaseDescuento + precioBaseDescuento * 0.1;
                                    }
                                    if (op3.equalsIgnoreCase("b")){
                                        precioBase = butacasPVP3;
                                        precioBaseDescuento = precioBase;
                                        if (descuento3) precioBaseDescuento -= precioBase * 0.03;
                                        totalEntradaIndividual = precioBaseDescuento + precioBaseDescuento * 0.1;
                                    }
                                    if (op3.equalsIgnoreCase("c")){
                                        precioBase = anfiPVP3;
                                        precioBaseDescuento = precioBase;
                                        if (descuento3) precioBaseDescuento -= precioBase * 0.03;
                                        totalEntradaIndividual = precioBaseDescuento + precioBaseDescuento * 0.1;
                                    }
                                }
                                //REALIZAR LAS ENTRADAS
                                for (int i = 1; i <= numEntradas; i++) {
                                    entrada = Integer.toString(i);
                                    entrada = entrada.concat(Integer.toString(numEntradas));
                                    codigo = codigo.concat(entrada).concat("MZ").concat(idCompleto.substring(6,8)
                                                    .concat(nombreCliente.substring(0,1)).concat(ape.substring(0,1))
                                                    .concat(fechaString.substring(5,10))
                                                    .concat((op2.equalsIgnoreCase("a"))? "CR": (op2.equalsIgnoreCase("b"))? "OT" : "JA"))
                                            .concat((op3.equalsIgnoreCase("a"))? "-PL": (op3.equalsIgnoreCase("b"))? "-BU" : "-AN")
                                            .concat((op2.equalsIgnoreCase("a"))? "20": (op2.equalsIgnoreCase("b"))? "28" : "06");


                                    System.out.println();
                                    System.out.printf("""
                                            ╔════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗
                                            ║                                                                                                                                    *
                                            ║            ;             NÚMERO DE ENTRADA: %d / %d   %s                                 CÓDIGO: %s
                                            ║            ;;            TIPO EVENTO: %s.
                                            ║            ;';.          FECHA EVENTO: %s.
                                            ║            ;  ;;         LUGAR: Teatro Maestro Álvarez Alonso | Martos - Jaén.
                                            ║            ;   ;;        TIPO DE ENTRADA: %s.
                                            ║            ;    ;;       Empresa Distribuidora: M&Z Ticket Company S.A.
                                            ║            ;    ;;
                                            ║            ;   ;'
                                            ║            ;  '
                                            ║       ,;;;,;             Precio Entrada Base: %dE.
                                            ║       ;;;;;;             Descuento por anticipación: %s.
                                            ║       `;;;;'       Precio Con Descuento: %dE - %s Descuento = %.2fE (+10%% IVA).      TOTAL ENTRADA Nº%d : %.2fE (IVA incluido)
                                            ║                                                                                                                                    *
                                            ╚════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝
                                            """,i,numEntradas,(numEntradas < 10)? "\t\t" : "  " ,codigo,
                                            (op2.equalsIgnoreCase("a"))? "LAS CRIADAS":
                                                    (op2.equalsIgnoreCase("b"))? "II CONCIERTO DE OTOÑO" : "CONCIERTO DE JAZZ",
                                            fechaString,
                                            (op3.equalsIgnoreCase("a"))? "Platea":
                                                    (op3.equalsIgnoreCase("b"))? "Butaca" : "Anfiteatro",
                                            precioBase,(descuento1)? "5%" : ((descuento2)? "7%" : ((descuento3)? "3%" : "NO")),
                                            precioBase,(descuento1)? "5%" : ((descuento2)? "7%" : ((descuento3)? "3%" : "NO")),
                                            precioBaseDescuento,i,totalEntradaIndividual);

                                    // Hago un sumatorio del coste individual de una entrada por el número total de entradas que se compran.
                                    costeTotalCompraEntradas += totalEntradaIndividual;
                                    System.out.println();
                                    System.out.print("Pulsa Enter para continuar...");
                                    s.nextLine();
                                    codigo = "";

                                }
                                if (op2.equalsIgnoreCase("a")) ingresoTotlCri += costeTotalCompraEntradas;
                                if (op2.equalsIgnoreCase("b")) ingresoTotlOto += costeTotalCompraEntradas;
                                if (op2.equalsIgnoreCase("c")) ingresoTotlJazz += costeTotalCompraEntradas;
                                System.out.println();
                                System.out.println();
                                System.out.printf("""
                                        ╔═══════════════════ FACTURA ════════════════════╗
                                           Nombre: %s.
                                           Apellido: %s.
                                                ·Teatro Maestro Álvarez Alonso.
                                                ·Martos - Jaén.
                                        
                                                               TOTAL: %.2fE
                                        ╚════════════════════════════════════════════════╝\n
                                        """, nombreCliente, ape, costeTotalCompraEntradas);

                                //Petición de dinero y cálculo de cambio
                                do {
                                    do {
                                        System.out.print("Introduzca el dinero para pagar la factura (solo se admite efectivo): ");
                                        dinero = s.nextLine();
                                        if (dinero.isEmpty()) System.out.println("ERROR: Valor vacío. Vuelva a intentarlo.");
                                        else dineroIntro = Float.parseFloat(dinero);
                                    } while (dinero.isEmpty());
                                    if (dineroIntro < costeTotalCompraEntradas) System.out.println("Cantidad insuficiente.");
                                } while (dineroIntro < costeTotalCompraEntradas);

                                cambio = (float) (dineroIntro - costeTotalCompraEntradas);

                                System.out.println();
                                System.out.print("Calculando su cambio ");
                                for (int i = 0; i < 3; i++) {
                                    try {
                                        Thread.sleep(500);
                                    } catch (InterruptedException e) {
                                        throw new RuntimeException(e);
                                    }
                                    System.out.print(". ");
                                }
                                System.out.println();

                                System.out.printf("SU CAMBIO TOTAL ES: %.2fE", cambio);
                                System.out.println();

                                System.out.println("\nPulse una ENTER para volver al menú principal. ");
                                s.nextLine();

                                // Calcular los billetes y monedas a devolver
                                while (cambio >= 500 && billete500e > 0) {
                                    billete500eDevolver++;
                                    billete500e--;
                                    cambio -= 500;
                                }
                                while (cambio >= 200 && billete200e > 0) {
                                    billete200eDevolver++;
                                    billete200e--;
                                    cambio -= 200;
                                }
                                while (cambio >= 100 && billete100e > 0) {
                                    billete100eDevolver++;
                                    billete100e--;
                                    cambio -= 100;
                                }
                                while (cambio >= 50 && billete50e > 0) {
                                    billete50eDevolver++;
                                    billete50e--;
                                    cambio -= 50;
                                }
                                while (cambio >= 20 && billete20e > 0) {
                                    billete20eDevolver++;
                                    billete20e--;
                                    cambio -= 20;
                                }
                                while (cambio >= 10 && billete10e > 0) {
                                    billete10eDevolver++;
                                    billete10e--;
                                    cambio -= 10;
                                }
                                while (cambio >= 5 && billete5e > 0) {
                                    billete5eDevolver++;
                                    billete5e--;
                                    cambio -= 5;
                                }

                                // Cálculo de monedas
                                while (cambio >= 2 && monedas2e > 0) {
                                    monedas2eDevolver++;
                                    monedas2e--;
                                    cambio -= 2;
                                }
                                while (cambio >= 1 && monedas1e > 0) {
                                    monedas1eDevolver++;
                                    monedas1e--;
                                    cambio -= 1;
                                }
                                while (cambio >= 0.50 && monedas50cent > 0) {
                                    monedas50centDevolver++;
                                    monedas50cent--;
                                    cambio -= 0.50f;
                                    cambio = (float) Math.round(cambio * 100) / 100; // Redondeo
                                }
                                while (cambio >= 0.20 && monedas20cent > 0) {
                                    monedas20centDevolver++;
                                    monedas20cent--;
                                    cambio -= 0.20f;
                                    cambio = (float) Math.round(cambio * 100) / 100; // Redondeo
                                }
                                while (cambio >= 0.10 && monedas10cent > 0) {
                                    monedas10centDevolver++;
                                    monedas10cent--;
                                    cambio -= 0.10f;
                                    cambio = (float) Math.round(cambio * 100) / 100; // Redondeo
                                }
                                while (cambio >= 0.05 && monedas5cent > 0) {
                                    monedas5centDevolver++;
                                    monedas5cent--;
                                    cambio -= 0.05f;
                                    cambio = (float) Math.round(cambio * 100) / 100; // Redondeo
                                }
                                while (cambio >= 0.02 && monedas2cent > 0) {
                                    monedas2centDevolver++;
                                    monedas2cent--;
                                    cambio -= 0.02f;
                                    cambio = (float) Math.round(cambio * 100) / 100; // Redondeo
                                }
                                while (cambio >= 0.01 && monedas1cent > 0) {
                                    monedas1centDevolver++;
                                    monedas1cent--;
                                    cambio -= 0.01f;
                                    cambio = (float) Math.round(cambio * 100) / 100; // Redondeo
                                }
                                break;

                            default:
                                System.out.println("\n · Opción introducida incorrecta ·");
                                System.out.println("Pulse la tecla ENTER para volver la menú principal.");
                                s.nextLine();
                                op2 = " ";
                        }
                    }
                    break;
                case "B":
                    op5 = " ";
                    System.out.println(""" 
                            ╔═════════════ ELIGE UN EVENTO PARA CONSULTAR ════════════╗
                            ║  a. Las criadas                                         ║
                            ║  b. II Concierto de Otoño                               ║
                            ║  c. Concierto de Jazz                                   ║
                            ╚═════════════════════════════════════════════════════════╝
                            """);

                    while (op5.equals(" ")) {
                        do {
                            System.out.print("Dime la opción que quieres consultar: ");
                            op5 = s.nextLine().toLowerCase();
                            if (!op5.equals("a") && !op5.equals("b") && !op5.equals("c")) System.out.println("  · Opción no válida ·");
                        } while (!op5.equals("a") && !op5.equals("b") && !op5.equals("c"));
                        System.out.println();

                        // VOLVEMOS A PEDIR LA FECHA:
                        System.out.print("Ingrese una fecha (YYYY-MM-DD), escriba 'NO' para escoger la fecha de hoy: ");
                        fechaString = s.nextLine().toLowerCase();

                        //si ingresa 'no' se pone la fecha actual
                        if (fechaString.equals("no")) fechaLocal = LocalDate.now();
                        else fechaLocal = LocalDate.parse(fechaString);

                        switch (op5) {

                            case "a":
                                //mostramos por pantalla la información sobre LAS CRIADAS
                                System.out.println();
                                System.out.println("Del evento de Las Criadas hay: ");
                                System.out.println("    ·PLATEAS:");
                                System.out.println(" - " + (NUM_PLATEAS - criadasDispPlat) + " entradas vendidas.");
                                System.out.println(" - " + criadasDispPlat + " entradas disponibles.");
                                System.out.println("    ·BUTACAS:");
                                System.out.println(" - " + (NUM_BUTACAS - criadasDispBut) + " entradas vendidas.");
                                System.out.println(" - " + criadasDispBut + " entradas disponibles.");
                                System.out.println("    ·ANFITEATROS:");
                                System.out.println(" - " + (NUM_ANFITEATROS - criadasDispAnfi) + " entradas vendidas.");
                                System.out.println(" - " + criadasDispAnfi + " entradas disponibles.");
                                System.out.println();

                                // CALCULO LOS DIAS QUE FALTAN PARA EL EVENTO:
                                if (fechaLocal.isAfter(fechaCriadas.minusDays(6))) System.out.println("""
                                        ╔══════════════════════════════════════════════════╗
                                              · EL EVENTO "LAS CRIADAS" YA HA SIDO ·
                                        ╚══════════════════════════════════════════════════╝
                                        """);
                                else{
                                    if (fechaLocal.isBefore(fechaCriadas.minusDays(6))) descuento1 = true;
                                    dias = fechaLocal.getDayOfYear();
                                    diaEvento = fechaCriadas.getDayOfYear();
                                    diferenciaDias = diaEvento - dias;
                                    //Ver a continuación cuántos días quedan para el evento:
                                    System.out.printf("""
                                        ╔══════════════════════════════════════════════════╗
                                          · QUEDAN %d DÍAS PARA EL EVENTO "LAS CRIADAS" ·
                                        ╚══════════════════════════════════════════════════╝
                                        """,diferenciaDias);
                                    System.out.println();
                                    if (descuento1) System.out.println("¡Se le aplica un descuento del 5%!");
                                    else System.out.println("Lo sentimos, no se encuentra en periodo de descuento.");

                                    System.out.println();
                                    System.out.println("Pulsa ENTER para continuar.");
                                    s.nextLine();
                                }
                                break;

                            case "b":
                                //mostramos por pantalla la información sobre el CONCIERTO DE OTOÑO
                                System.out.println();
                                System.out.println("Del evento del II Concierto de Otoño hay: ");
                                System.out.println("PLATEAS:");
                                System.out.println(" - " + (NUM_PLATEAS - otoDispPlat) + " entradas vendidas.");
                                System.out.println(" - " + otoDispPlat+ " entradas disponibles.");
                                System.out.println("BUTACAS:");
                                System.out.println(" - " + (NUM_BUTACAS - otoDispBut) + " entradas vendidas.");
                                System.out.println(" - " + otoDispBut + " entradas disponibles.");
                                System.out.println("ANFITEATROS:");
                                System.out.println(" - " + (NUM_ANFITEATROS - otoDispAnfi) + " entradas vendidas.");
                                System.out.println(" - " + otoDispAnfi + " entradas disponibles.");
                                System.out.println();

                                // CALCULO LOS DIAS QUE FALTAN PARA EL EVENTO:
                                if (fechaLocal.isAfter(fechaCriadas.minusDays(6))) System.out.println("""
                                        ╔══════════════════════════════════════════════════╗
                                          · EL EVENTO "II CONCIERTO DE OTOÑO" YA HA SIDO ·
                                        ╚══════════════════════════════════════════════════╝
                                        """);
                                else {
                                    if (fechaLocal.isBefore(fechaConciertoOto.minusDays(6))) descuento2 = true;
                                    dias = fechaLocal.getDayOfYear();
                                    diaEvento = fechaConciertoOto.getDayOfYear();
                                    diferenciaDias = diaEvento - dias;
                                    //Ver a continuación cuántos días quedan para el evento:
                                    System.out.printf("""
                                        ╔════════════════════════════════════════════════════════════╗
                                          · QUEDAN %d DÍAS PARA EL EVENTO "II CONCIERTO DE OTOÑO" ·
                                        ╚════════════════════════════════════════════════════════════╝
                                        """,diferenciaDias);
                                    System.out.println();
                                    if (descuento2) System.out.println("¡Se le aplica un descuento del 7%!");
                                    else System.out.println("Lo sentimos, no se encuentra en periodo de descuento.");



                                    System.out.println();
                                    System.out.println("Pulsa ENTER para continuar.");
                                    s.nextLine();
                                }
                                break;

                            case "c":
                                //mostramos por pantalla la información sobre el CONCIERTO DE JAZZ
                                System.out.println();
                                System.out.println("Del evento del Concierto de Jazz hay: ");
                                System.out.println("PLATEAS:");
                                System.out.println(" - " + (NUM_PLATEAS - jazzDispPlat) + " entradas vendidas.");
                                System.out.println(" - " + jazzDispPlat + " entradas disponibles.");
                                System.out.println("BUTACAS:");
                                System.out.println(" - " + (NUM_BUTACAS - jazzDispBut) + " entradas vendidas.");
                                System.out.println(" - " + jazzDispBut + " entradas disponibles.");
                                System.out.println("ANFITEATROS:");
                                System.out.println(" - " + (NUM_ANFITEATROS - jazzDispAnfi) + " entradas vendidas.");
                                System.out.println(" - " + jazzDispAnfi + " entradas disponibles.");
                                System.out.println();

                                // CALCULO LOS DIAS QUE FALTAN PARA EL EVENTO:
                                if (fechaLocal.isAfter(fechaConciertoJazz.minusDays(6))) System.out.println("""
                                        ╔══════════════════════════════════════════════════╗
                                            · EL EVENTO "CONCIERTO DE JAZZ" YA HA SIDO ·
                                        ╚══════════════════════════════════════════════════╝
                                        """);
                                else {
                                    if (fechaLocal.isBefore(fechaConciertoJazz.minusDays(6))) descuento3 = true;
                                    dias = fechaLocal.getDayOfYear();
                                    diaEvento = fechaConciertoJazz.getDayOfYear();
                                    diferenciaDias = diaEvento - dias;
                                    //Ver a continuación cuántos días quedan para el evento:
                                    System.out.printf("""
                                        ╔════════════════════════════════════════════════════════════╗
                                            · QUEDAN %d DÍAS PARA EL EVENTO "CONCIERTO DE JAZZ" ·
                                        ╚════════════════════════════════════════════════════════════╝
                                        """,diferenciaDias);
                                    System.out.println();
                                    if (descuento3) System.out.println("¡Se le aplica un descuento del 3%!");
                                    else System.out.println("Lo sentimos, no se encuentra en periodo de descuento.");

                                    System.out.println();
                                    System.out.println("Pulsa ENTER para continuar.");
                                    s.nextLine();
                                }
                                break;

                            default:
                                System.out.println("\n · Has introducido un valor incorrecto, vuelve a intentarlo (a/b/c) ·\n");
                                op5 = " ";
                        }
                    }
                    break;


                case "C":
                    System.out.println("····· REGISTRO DE ADMINISTRACIÓN ·····");

                    //comprobamos la contra y el user del admin
                    //el user es "admin" y la contraseña es "1234"
                    System.out.print("Ingrese el usuario: ");
                    userIntro = s.nextLine();
                    System.out.print("Ingrese la contraseña: ");
                    passIntro = s.nextLine();

                    if (userIntro.equals(userAdmin) && (passAdmin.equals(passIntro))) {

                        System.out.print("Introduzca su nombre: ");
                        nombre = s.nextLine();

                        System.out.println();
                        System.out.println("Bienvenid@ administrador/a " + nombre + ".");
                        System.out.println();

                        //Muestra menú Administrador
                        System.out.println("""
                                ╔══════════════════ MENÚ ADMINISTRACIÓN ═════════════════╗
                                ║  i. Consultar los ingresos totales por evento.         ║
                                ║  ii.- Consultar las monedas restantes para el cambio.  ║
                                ║  iii.- Apagar el software.                             ║
                                ╚════════════════════════════════════════════════════════╝
                                """);
                        do {
                            System.out.print("Elija la opción deseada (i/ii/iii): ");
                            op4 = s.nextLine().toLowerCase();
                            if (!op4.equals("i") && !op4.equals("ii") && !op4.equals("iii")) System.out.println("  · Opción no válida ·");
                        } while (!op4.equals("i") && !op4.equals("ii") && !op4.equals("iii"));

                        System.out.println();


                        switch (op4) { //Menú ADMINISTRADOR
                            case "i": //consultar ingresos
                                System.out.println("CONSULTA DE INGRESOS:");
                                System.out.printf(" - Total de Ingresos del Evento de Las Criadas: %.2fE. \n",
                                        ingresoTotlCri);
                                System.out.printf(" - Total de Ingresos del Evento del II Concierto de Otoño: %.2fE. \n",
                                        ingresoTotlOto);
                                System.out.printf(" - Total de Ingresos del Evento del Concierto de Jazz: %.2fE. \n",
                                        ingresoTotlJazz);
                                System.out.println();

                                System.out.println("Pulse la tecla ENTER para volver al menú principal.");
                                s.nextLine();
                                break;

                            case "ii": //consultar monedas
                                System.out.printf("""
                                                ╔══════════════════════════════╗
                                                ║ BILLETES Y MONEDAS RESTANTES ║
                                                ╚══════════════════════════════╝
                                                ║ 500E:\t\t%d
                                                ║ 200E:\t\t%d
                                                ║ 100E:\t\t%d
                                                ║ 50E:\t\t%d
                                                ║ 20E:\t\t%d
                                                ║ 10E:\t\t%d
                                                ║ 5E:\t\t%d
                                                ║ 2E:\t\t%d
                                                ║ 1E:\t\t%d
                                                ║ 0.50E:\t%d
                                                ║ 0.20E:\t%d
                                                ║ 0.10E:\t%d
                                                ║ 0.05E:\t%d
                                                ║ 0.02E:\t%d
                                                ║ 0.01E:\t%d
                                                ╚══════════════════════════════╝
                                                """, billete500e, billete200e, billete100e, billete50e,
                                        billete20e, billete10e, billete5e, monedas2e, monedas1e,
                                        monedas50cent, monedas20cent, monedas10cent, monedas5cent,
                                        monedas2cent, monedas1cent);

                                System.out.println();

                                System.out.println("Pulse la tecla ENTER para volver al menú principal.");
                                s.nextLine();
                                break;

                            case "iii": //apagar el software
                                encendido = false; //boolean para manejar la salida del programa

                                System.out.print("Apagando sistema ");
                                for (int i = 0; i < 4; i++) {
                                    try {
                                        Thread.sleep(500);
                                    } catch (InterruptedException e) {
                                        throw new RuntimeException(e);
                                    }
                                    System.out.print(". ");
                                }
                                System.out.println();

                                //Limpiamos la pantalla haciendo muchos saltos de línea:
                                for (int i = 0; i < 100; i++) {
                                    System.out.println(" ");
                                }
                                System.out.println(" - SISTEMA APAGADO - ");
                                break;

                            default:
                                System.out.println("\n · Opción introducida incorrecta ·");
                                System.out.println("Pulse la tecla ENTER para volver al menú principal.");
                                s.nextLine();
                        }
                    } else {
                        System.out.println("\n · Usuario o contraseña incorrectos ·");
                        System.out.println();
                        System.out.println("Pulse la tecla ENTER para volver al menú principal.");
                        s.nextLine();
                    }
                    break;

                default:
                    System.out.println("\n· Valor incorrecto, introduce A/B/C ·\n");
                    break;
            }
        }
    }
}