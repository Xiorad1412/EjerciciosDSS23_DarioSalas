# EjerciciosDSS23_DarioSalas

# Ejercicio 1

Se adjunta el código completo del ejercicio 1, incluyendo una clase que simula una tarjeta de crédito, una función main para probarla y un fichero CreditCardTest con tests que prueban los métodos de la clase, hecho en junit 4.13.2.

Se ha usado ChatGPT como apoyo e inspiración para el desarrollo del código.

# Ejercicio 2

Para la realización de este ejercicio, he seguido un tutorial otorgado por ChatGPT sobre cómo solucionar el problema de devolver un parámetro de salida de tipo Object haciendo uso de generics de Java, y por tanto haciendo uso de la inversión de dependencias.

El código resultante es el que se encuentra en KnightOfTheRoundTableA.

Para el apartado B, hemos seguido también un tutorial de chatGPT para crear un proyecto Maven con Spring Boot, añadiendo como beans de Spring las clases holyGrailQuest y knightOfTheRoundTable. Hemos hecho esto añadiendo un fichero .xml en KnightOfTheRoundTable\src\main\resources donde se le indica que esas clases son beans. También hemos indicado en el application.PROPERTIES que este era un fichero de contexto para el sistema.

A continuación, hemos creado las interfaces y las clases que implementan por un lado las interfaces del dominio del problema y por otro las que implementan la FactoryBean de Spring, como QuestFactoryBean.

Este proyecto se encuentra en KnightOfTheRoundTableB.

# Ejercicio 3

## Apartado a

  La explicación de la secuencia de acciones que se llevan a cabo para resolver el problema se encuentra en el apartado (b). Recomiendo su lectura antes de proceder con las apreciaciones siguientes.

  De cara a automatizar el inicio de este proceso, existen diversas formas en las que se podría hacer. Por ejemplo, un empleado podría hacer uso del Invoker del patrón mediante una aplicación de front-end, para procesar específicamente los formularios que él o ella quiera, aunque esta solución no automatizaría totalmente el proceso. Es más lógico pensar que cada cierto tiempo se procesarán todos los formularios que se hayan recibido, que mediante algún tipo de evento se activaría el procesamiento de los formularios pendientes, o una combinación de estas opciones. Este comportamiento no se contempla en la solución del ejercicio debido a que no se puede hallar cuál es la opción más adecuada con los datos del enunciado.

  Los comandos específicos tendrán que conocer el contexto de trabajo, que en este caso se trata del formulario que el comando va a procesar. Lo guardaremos como atributo del comando y este se le pasará a través del constructor de la clase. Esta característica de nuestro problema causa dependencias entre los comandos específicos y la clase formulario, pero consideramos que todos los tipos de formularios a procesar tienen el mismo formato y que la clase Formulario que hemos hecho no va a cambiar a lo largo del tiempo, por lo que podemos asumir esta dependencia. Si no fuera así, una posible solución sería que los comandos dependieran de una clase genérica FormularioTemplate, y que de esa clase derivaran los distintos tipos de formulario específicos que necesitaría cada comando.

  De una manera parecida a los formularios, los comandos necesitan saber a quién deben enviar los datos procesados de los formularios. Se ha optado por crear una interfaz Receiver y sus clases específicas que la implementan, para que los comandos solo dependan de esa interfaz, y no de los receivers específicos. Esta información se le da al comando en su constructor, como uno de los parámetros de entrada.
Otra solución sería que el receiver fuese un parámetro del método execute() de la interfaz, se ha decidido declinar esta opción para poder ofrecer la posibilidad de que algunos comandos envíen la información a varias personas (es decir, tengan varios receiver), como se explicará en el párrafo siguiente.

  Queda la posibilidad de que el resultado de procesar un formulario se tenga que enviar a varios componentes logísticos (p.e. al Almacén, a los Empleados de la empresa, a los Clientes…). Por esta razón, los comandos específicos que tengan esta necesidad, tendrán una lista de Receiver(s) en lugar de un solo parámetro de tipo Receiver. El comando específico del diagrama CommandBroadcast representa esta situación.

  Por otro lado, no se ha considerado que un mismo comando pueda procesar múltiples formularios porque no se dice nada sobre eso en el enunciado del problema, y se ha considerado que incluir esta posibilidad en la solución ensuciaría demasiado el diagrama, y desviaría la atención de las partes importantes del ejercicio.

  Es vital recordar que, al igual que indicamos el formulario o el receiver para los comandos específicos, al llamar al método sendResults() de los receiver específicos hay que indicarles los datos que hay que enviar, que se han obtenido al procesar el formulario. Esto se haría pasando estos datos como parámetros de entrada del método. Aunque este parámetro esté representado en el diagrama como una variable de tipo String, a la hora de implementarlo deberíamos de usar un Optional<String> (u Optional<T>, siendo T el tipo que representa los datos contenidos en el formulario), para contemplar la posibilidad de que el formulario no necesite pasarle datos adicionales al componente logístico que recibirá la notificación, sino que con esta notificación ya sería suficiente. En estos casos, el método setResults() del receiver pondría el valor del Optional a “Vacío”.


![EjercicioPatronesDSS23-Página-1](https://github.com/Xiorad1412/EjerciciosDSS23_DarioSalas/assets/101283806/048ad72a-0cb9-4a70-a96c-90db567dff5e)

## Apartado b

  Se va a explicar el flujo de ejecución del sistema diseñado con el ejemplo concreto descrito en el enunciado de una petición de envío de productos a sus clientes:
  
  La ejecución dará inicio con el código cliente, que creará un Formulario que será el que se procesará, un Receiver, que se encargará de notificar y de trasladar los datos del formulario a su componente lógico destino (p.e. al Almacén, a los Empleados de la empresa, a los Clientes…), y un CommandPeticionEnvio, al que se le pasarán los objetos recién creados por el constructor.
  A continuación, se le asignará al Invoker (se asume que ya está creado en el sistema) el comando que recién ha sido creado, y se termina la vida del flujo del cliente de momento.
  
  Pasado un tiempo, se recibe la orden de ejecutar el comando, es decir, el método executeCommand() del Invoker (más sobre de quién se recibe la orden más adelante), y este a su vez llama al método execute() del comando que tenía asignado previamente.
  
  Ahora, el comando realizará el conjunto de acciones para procesar un tipo de formulario específico sobre el Formulario que tiene como atributo. Una vez procesado y obtenido sus datos, el comando llama al Receiver a través del método sendResults(), con los datos obtenidos como parámetros de entrada.
  
  Finalmente, el Receiver notificará a los componentes lógicos que le corresponden, enviándoles además los datos del formulario.
  
  Por último, un par de apreciaciones sobre el comportamiento del sistema:
  
  En el diagrama de secuencia, se ha representado la creación de los distintos objetos que harán falta para mostrar el comportamiento del diseño con el uso de la orden “new”, pero en un entorno real, debería ser implementado mediante patrones creacionales, para aumentar la flexibilidad del código construido.

  El método executeCommand() del Invoker es llamado por un agente externo. De esto se habla en el apartado anterior (párrafo 1), en el que se debate que el proceso se puede iniciar de múltiples formas.

![EjercicioPatronesDSS23-Página-2](https://github.com/Xiorad1412/EjerciciosDSS23_DarioSalas/assets/101283806/a79ef287-2fdc-4d0b-801e-b6e14b13cbba)

## Apartado c

  Se ha optado por utilizar el patrón de diseño Decorator, en el que se muestra por pantalla el formulario con el método execute() de la clase FormularioConcreto, mientras que el resto de decoradores concretos llaman al execute() de su padre (un DecoratorBase) que a su vez llama al execute() de su componente (que podrá ser un decorador concreto o un FormularioConcreto). Esta cadena que hemos formado acabará ejecutando, en primer lugar, la impresión del formulario, y después cada uno de los decoradores en el orden en el que se introdujeron en la estructura recursiva.
  
![EjercicioPatronesDSS23-Página-3](https://github.com/Xiorad1412/EjerciciosDSS23_DarioSalas/assets/101283806/125a7768-166a-4687-9fa2-fac236311255)
