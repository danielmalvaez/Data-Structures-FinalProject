//Package

//Imports
import java.text.Normalizer;

/**
 * Clase de prueba
 */
public class Main2{
    public static String depurar(String texto){
	String documentDepured = "";
	char letter = ' ';
	//Verificar si es letra o no.
	for(int i = 0; i < texto.length(); i++){
	    letter = texto.charAt(i);
	    if(!Character.isLetter(letter)){
		documentDepured = documentDepured + " ";
		continue;
	    }
	    documentDepured = documentDepured + letter;
	}
	
	documentDepured = Normalizer.normalize(documentDepured.toLowerCase(), Normalizer.Form.NFD);
	String noAccents = documentDepured.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
	//Scanner io = new Scanner(documentDepured).useDelimiter("\\s*\\s");
	return noAccents;
    }
    public static void main(String[] args){
	//Un String muy corto.
	String corto = "BBÉÉàáèéìíòóùú hólá éstá és úná prúébá?¿)(/&%$·42435 !&/&(hola";
	//Un String considerable.
	String medio = "Como cualquier otro, TXT tiene sus inconvenientes, por ejemplo, no admite hipervínculos. En tal texto no hay imágenes. Sin embargo, esto absolutamente no interfiere con la mayoría absoluta de los usuarios, porque la legibilidad y la velocidad de descarga son mucho más importantes. Para aquellos que simplemente no pueden imaginar la vida sin leer, y tienen muchos libros en su dispositivo, este formato no es reemplazable en absoluto. En nuestro sitio siempre puede descargar absolutamente cualquier libro intercambiado en TXT. Te ofrecemos elegir libros de texto  De la lista de categorías o utilice la búsqueda. Disfruta tu lectura! Un cuento de hadas divertido del famoso escritor inglés Alan Alexander Milne sobre el maravilloso Winnie the Pooh Bear Cub y sus leales amigos. Este libro puede ser llamado uno de los mejores librosjamás creado. Tales libros son extremadamente raros. Un libro que accidentalmente te encuentras en la infancia ... Durante muchos siglos, los vampiros se consideraron a sí mismos los dueños completos del planeta. Ocultándose en la oscuridad, exterminaron libremente a las personas, despreciándolas como una raza inferior. Pero los tiempos han cambiado. La última tecnología de grabación y seguimiento de video ha puesto en riesgo el poder secreto de los malditos caballeros. Para sobrevivir ... Trabajando en la Administración de la Seguridad Social, Todd ve cada día cómo la basura humana se multiplica libremente, inundando la tierra con una avalancha de desechos, arruinándola con su actitud cruel. Pero si los convence para que no se multipliquen, si convence a todos para que se sometan a una esterilización voluntaria, podrá prevenir ... ¡Kitty Logan siempre ha soñado con una carrera como presentadora de televisión! Pero una vez cometió un error fatal, acusando a una persona absolutamente inocente de un crimen terrible, y todas sus esperanzas cayeron de inmediato. Kitty se convirtió en la llamada charla de la ciudad, un novio la abandonó de inmediato, un viejo amigo acusado de egoísmo, eso es sobre ...El Commonwealth es un mundo de imperios estelares y altas tecnologías. Una multitud de planetas habitados por humanos y no humanoides. El hombre de la tierra tiene que ir por un camino difícil, convirtiéndose en un luchador de un destacamento de mercenarios. Estará en el centro del conflicto interestelar y tocará los secretos de las civilizaciones perdidas. Su nombre es Garth, y él es Odar ... En el lugar más estrecho de la garganta de Katavan hay un puesto de control. Dos pelotones de fuerzas especiales de tropas internas están bloqueando el camino de las caravanas de armas que van de Georgia a Rusia. La cerca es confiable: hay dos vehículos de combate de infantería en los caponiers, se cavan trincheras protectoras, hay un cálculo de mortero. No importa lo duro que los mercenarios trataron de romper con la lucha, eso ... El dramaturgo neoyorquino Gordon Dulquist se despertó famoso, como dicen, de la noche a la mañana, cuando la editorial \"Penguin\" le ofreció un contrato por valor de dos millones de dólares para la novela debut \"Los libros de cristal de los comedores de sueños\" y su futura continuación. Por lo tanto, los personajes: señorita Temple Prib ... Imagina una biblioteca donde los muertos yacen en los estantes en lugar de libros. Cada uno de ellos tiene su propia historia especial, que solo los bibliotecarios pueden leer. Por lo tanto, llaman a los muertos las Historias, y el lugar donde se almacenan las Historias se llama el Archivo. Mackenzie Bishop - Guardian A ... El gordo Jorge es un psicoterapeuta inusual. Ama todo tipo de fábulas, parábolas, cuentos de hadas, frases inteligentes y metáforas exitosas, porque, en su opinión, son cientos de veces mejor recordadas que miles de explicaciones teóricas, interpretaciones psicoanalíticas o enfoques científicos. Cómo aprender a ser tú mismo, p ... Maniac mantiene a la ciudad a raya. Él es un esteta de la muerte, estilizando sus asesinatos como los cuentos más terribles de los hermanos Grimm. \"Hansel y Gretel\", \"La bella durmiente\", \"Rapunzel\" ... Cada \"ilustración\" se crea al costo de la vida humana. El principal sospechoso es el autor de un libro escandaloso en el que afirma ... A principios del siglo dieciocho, cuando las mujeres se sentaban en sus casas haciendo la costura y los hombres navegaban por los mares en busca de aventuras y riquezas, dos chicas inusuales, la hija de la acaudalada comerciante Nancy Kington y su sirvienta Minerva Sharp, suben al barco en el que ondea ... Cada usuario de Internet, al menos una vez, llega a las páginas de las bibliotecas en línea. Quienes permanecieron allí durante mucho tiempo saben qué es un libro electrónico y en qué formatos se pueden descargar. Pero hay personas que recientemente comenzaron a familiarizarse con esta información. Por lo tanto, es difícil para ellos distinguir el formato de un libro electrónico en PDF de DjVu o HTML de TXT. En este artículo nos centraremos en uno de los formatos más populares: TXT. Por supuesto, muchos de los que tuvieron que trabajar con documentos, cartas e informes, entienden que txt es un formato de archivo de texto. Los títulos de dichos libros electrónicos se crean generalmente como texto sin formato al principio. Toda la información adicional sobre el editor y el autor se puede colocar tanto al principio del libro como al final. Cuando necesite esta información, será suficiente ir a la página necesaria y volver al lugar de lectura para que sea rápido y conveniente, ingrese un extracto de texto en la búsqueda o use el sistema de marcadores. Muy a menudo en este formato, el sistema usa notas al pie, que se encuentran al final del libro. Siempre puedes verlos. Y vuelva al lugar de lectura de la misma manera que después de leer la información adicional sobre el autor o editor. Los poemas y citas en formato txt se resaltan con varios espacios. Gracias a esto siempre puedes verlos entre el resto del texto. Descargar libros de texto  Muy simple, conveniente, y lo más importante, rápido. Después de todo, los archivos txt pesan mucho menos libros en otros formatos. Puede descargar fácilmente los libros de texto incluso si tiene Internet de baja velocidad o una pequeña cantidad de disco duro. Con la capacidad de escalar el texto al tamaño de fuente que sea conveniente para usted, puede leer libros cómodamente, olvidándose de la fatiga visual. La capacidad absoluta para editar texto le permitirá tomar notas en los márgenes del archivo y también puede dividirlo, copiarlo o borrarlo, pegarlo en un libro, convertirlo a cualquier otro formato y. etc. Para descargar libros en txt no es necesario tener un capricho. libro electrónicoDespués de todo, los archivos en este formato son leídos por casi todos los dispositivos electrónicos. Puedes leer tus libros favoritos incluso en Mp3. La compatibilidad con todos los dispositivos es una ventaja innegable del formato txt. No tienes que instalar programas especialesporque los libros en txt se pueden leer en casi cualquier editor, por ejemplo: (Word, EPOC Text Editor, RMR Text y otros). Pero si tiene preferencias especiales al leer libros, los libros en formato txt se pueden convertir a cualquier otro, según sus deseos. Si va a utilizar un archivador, por ejemplo, rar o zip, entonces es txt: el formato es adecuado para esto lo mejor posible, lo que no se puede decir sobre otros formatos de libros electrónicos. Por supuesto, el formato txt y otros tienen sus ventajas y desventajas. No admite hipervínculos; desde el contenido no hay posibilidad de ir directamente al capítulo que necesita y el contenido de estos libros generalmente está mal protegido. Pero es fácil olvidarse de estos inconvenientes, dada la facilidad de descarga y edición que proporciona. Como lectores, no debemos preocuparnos por la seguridad de los contenidos de ningún libro. La usabilidad y la velocidad de descarga son más importantes para nosotros. Si amas la literatura y no te imaginas tu vida sin leer, solo tienes que descargar libros en txt para disfrutar de las obras de autores famosos y sumergirte en el mundo de las emocionantes aventuras.Cada usuario de Internet, al menos una vez, llega a las páginas de las bibliotecas en línea. Quienes permanecieron allí durante mucho tiempo saben qué es un libro electrónico y en qué formatos se pueden descargar. Pero hay personas que recientemente comenzaron a familiarizarse con esta información. Por lo tanto, es difícil para ellos distinguir el formato de un libro electrónico en PDF de DjVu o HTML de TXT. En este artículo nos centraremos en uno de los formatos más populares: TXT. Por supuesto, muchos de los que tuvieron que trabajar con documentos, cartas e informes, entienden que txt es un formato de archivo de texto. Los títulos de dichos libros electrónicos se crean generalmente como texto sin formato al principio. Toda la información adicional sobre el editor y el autor se puede colocar tanto al principio del libro como al final. Cuando necesite esta información, será suficiente ir a la página necesaria y volver al lugar de lectura para que sea rápido y conveniente, ingrese un extracto de texto en la búsqueda o use el sistema de marcadores. Muy a menudo en este formato, el sistema usa notas al pie, que se encuentran al final del libro. Siempre puedes verlos. Y vuelva al lugar de lectura de la misma manera que después de leer la información adicional sobre el autor o editor. Los poemas y citas en formato txt se resaltan con varios espacios. Gracias a esto siempre puedes verlos entre el resto del texto.

Descargar libros de texto  Muy simple, conveniente, y lo más importante, rápido. Después de todo, los archivos txt pesan mucho menos libros en otros formatos. Puede descargar fácilmente los libros de texto incluso si tiene Internet de baja velocidad o una pequeña cantidad de disco duro.

Con la capacidad de escalar el texto al tamaño de fuente que sea conveniente para usted, puede leer libros cómodamente, olvidándose de la fatiga visual. La capacidad absoluta para editar texto le permitirá tomar notas en los márgenes del archivo y también puede dividirlo, copiarlo o borrarlo, pegarlo en un libro, convertirlo a cualquier otro formato y. etc.

Para descargar libros en txt no es necesario tener un e-book de lujo, porque casi todos los dispositivos electrónicos leen los archivos en este formato. Puedes leer tus libros favoritos incluso en Mp3. La compatibilidad con todos los dispositivos es una ventaja innegable del formato txt. No tiene que instalar programas especiales, ya que los libros en txt se pueden leer en casi cualquier editor, por ejemplo: (Word, EPOC Text Editor, RMR Text y otros). Pero si tiene preferencias especiales al leer libros, los libros en formato txt se pueden convertir a cualquier otro, según sus deseos.

Si va a utilizar un archivador, por ejemplo, rar o zip, entonces es txt: el formato es adecuado para esto lo mejor posible, lo que no se puede decir sobre otros formatos de libros electrónicos.

Por supuesto, el formato txt y otros tienen sus ventajas y desventajas. No admite hipervínculos; desde el contenido no hay posibilidad de ir directamente al capítulo que necesita y el contenido de estos libros generalmente está mal protegido. Pero es fácil olvidarse de estos inconvenientes, dada la facilidad de descarga y edición que proporciona. Como lectores, no debemos preocuparnos por la seguridad de los contenidos de ningún libro. La usabilidad y la velocidad de descarga son más importantes para nosotros.

Si amas la literatura y no te imaginas tu vida sin leer, solo tienes que descargar libros en txt para disfrutar de las obras de autores famosos y sumergirte en el mundo de las emocionantes aventuras.";
	//Un String muy largo.
	String largo = "";

	//Normalize
	corto = Normalizer.normalize(corto.toLowerCase(), Normalizer.Form.NFD);
	medio = Normalizer.normalize(medio.toLowerCase(), Normalizer.Form.NFD);
	largo = Normalizer.normalize(largo.toLowerCase(), Normalizer.Form.NFD);
	
	//Depurar un String corto.
	long inicioCorto = System.currentTimeMillis();
	String cortoDep = corto.replaceAll("[^a-z\\s*]", "");
	long finCorto = System.currentTimeMillis();
	//Depurar un String medio.
	long inicioMedio = System.currentTimeMillis();
	String cortoDep2 = medio.replaceAll("[^a-z\\s*]", "");
	long finMedio = System.currentTimeMillis();
	//Depurar un String largo.
	long inicioLargo = System.currentTimeMillis();
	String cortoDep3 = largo.replaceAll("[^a-z\\s*]", "");
	long finLargo = System.currentTimeMillis();

	//Depurar un String corto.
	long in1 = System.currentTimeMillis();
	String other1 = depurar(corto);
	long fin1 = System.currentTimeMillis();
	//Depurar un String medio.
	long in2 = System.currentTimeMillis();
	String other2 = depurar(medio);
	long fin2 = System.currentTimeMillis();
	//Depurar un String largo.
	long in3 = System.currentTimeMillis();
	String other3 = depurar(largo);
	long fin3 = System.currentTimeMillis();
	
	System.out.println("--------------");
	System.out.println("con replaceAll");
	System.out.println("--------------\n");
	
	System.out.println("String Corto");
	System.out.println("Tiempo: "+ (finCorto-inicioCorto));

	System.out.println("String Medio");
	System.out.println("Tiempo: "+ (finMedio-inicioMedio));

	System.out.println("String Largo");
	System.out.println("Tiempo: "+ (finLargo-inicioLargo));

	System.out.println("--------------");
	System.out.println("contando cada letra");
	System.out.println("--------------");

	System.out.println("String Corto");
	System.out.println("Tiempo: "+ (fin1-in1));

	System.out.println("String Medio");
	System.out.println("Tiempo: "+ (fin2-in2));

	System.out.println("String Largo");
	System.out.println("Tiempo: "+ (fin3-in3));


    }
}
