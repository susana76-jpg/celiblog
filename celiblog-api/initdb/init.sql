-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 11-11-2025 a las 18:33:32
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `celiblog`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comentario`
--

CREATE TABLE `comentario` (
  `id_comentario` int(255) NOT NULL,
  `fecha_publicacion` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `contenido` varchar(255) DEFAULT NULL,
  `comentario_url` varchar(255) DEFAULT NULL,
  `id_usuario` int(255) NOT NULL,
  `id_tipo_comentario` int(255) NOT NULL,
  `id_objeto_comentado` int(255) NOT NULL,
  `fecha_validacion` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `valoracion` int(30) NOT NULL,
  `estado` enum('PENDIENTE','APROBADO','RECHAZADO') DEFAULT 'PENDIENTE'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `favoritos`
--

CREATE TABLE `favoritos` (
  `id_favorito` int(255) NOT NULL,
  `id_usuario` int(255) NOT NULL,
  `tipo_referencia` varchar(500) NOT NULL COMMENT 'restaurante, receta, post',
  `id_referencia` int(255) NOT NULL COMMENT ' ID del restaurante, receta o post',
  `fecha` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `favoritos`
--

INSERT INTO `favoritos` (`id_favorito`, `id_usuario`, `tipo_referencia`, `id_referencia`, `fecha`) VALUES
(3, 1, 'receta', 1, '2025-10-29 08:45:43'),
(4, 1, 'receta', 2, '2025-10-29 08:46:02'),
(5, 1, 'receta', 2, '2025-10-29 08:46:53'),
(6, 1, 'receta', 3, '2025-10-29 08:50:11'),
(7, 1, 'receta', 4, '2025-10-29 08:51:55'),
(8, 1, 'receta', 3, '2025-10-29 08:59:20');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ingrediente`
--

CREATE TABLE `ingrediente` (
  `id_ingrediente` int(255) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `unidad` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `ingrediente`
--

INSERT INTO `ingrediente` (`id_ingrediente`, `nombre`, `unidad`) VALUES
(1, 'Harina de arroz', 'gramos'),
(2, 'Harina de almendra', 'gramos'),
(3, 'Trigo sarraceno', 'gramos'),
(4, 'Huevos', 'unidad'),
(5, 'Leche vegetal', 'mililitros'),
(6, 'Aceite de oliva', 'mililitros'),
(7, 'Plátano', 'unidad'),
(8, 'Zanahoria', 'gramos'),
(9, 'Chocolate negro', 'gramos'),
(10, 'Coco rallado', 'gramos'),
(11, 'Quinoa', 'gramos'),
(12, 'Maíz precocido', 'gramos'),
(13, 'Avena sin gluten', 'gramos'),
(14, 'Calabaza', 'gramos'),
(15, 'Lentejas', 'gramos'),
(16, 'Tomate', 'gramos'),
(17, 'Aguacate', 'unidad'),
(18, 'Nueces', 'gramos'),
(19, 'Canela', 'gramos'),
(20, 'Boniato', 'gramos'),
(21, 'Manzana', 'unidad'),
(22, 'Harina sin gluten', 'gramos'),
(23, 'Azúcar', 'gramos'),
(24, 'Huevo', 'unidad'),
(25, 'Canela', 'cucharadita');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `paso_receta`
--

CREATE TABLE `paso_receta` (
  `id_paso` bigint(20) NOT NULL,
  `id_receta` int(255) NOT NULL,
  `orden` int(255) NOT NULL,
  `descripcion` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `paso_receta`
--

INSERT INTO `paso_receta` (`id_paso`, `id_receta`, `orden`, `descripcion`) VALUES
(1, 1, 1, 'En un bol grande, mezcla la harina de trigo sarraceno con una pizca de sal y levadura seca activa. Remueve bien para que los ingredientes secos se integren completamente. Este paso es fundamental para asegurar que el pan tenga una textura uniforme y que la levadura se distribuya correctamente en toda la masa.'),
(2, 1, 2, 'Agrega lentamente agua templada (aproximadamente 250 ml) y 2 cucharadas de aceite de oliva virgen extra. Mezcla con una espátula hasta que se forme una masa pegajosa. Si la masa está demasiado seca, añade un poco más de agua. La consistencia debe ser húmeda pero manejable.'),
(3, 1, 3, 'Amasa la mezcla durante unos 10 minutos sobre una superficie ligeramente enharinada con harina sin gluten. Este proceso activa el almidón del trigo sarraceno y ayuda a que el pan tenga una mejor estructura. Si la masa se pega demasiado, puedes añadir pequeñas cantidades de harina sin gluten.'),
(4, 1, 4, 'Coloca la masa en un bol limpio, cúbrela con un paño húmedo y deja reposar en un lugar cálido durante al menos una hora. Durante este tiempo, la levadura fermentará y hará que la masa aumente su volumen. Este paso es clave para obtener un pan esponjoso y bien aireado.'),
(5, 1, 5, 'Precalienta el horno a 180°C. Coloca la masa en un molde para pan previamente engrasado o forrado con papel vegetal. Hornea durante 40 minutos o hasta que al insertar un palillo salga limpio. Deja enfriar sobre una rejilla antes de cortar para que no se desmorone.'),
(6, 11, 1, 'En un bol grande, mezcla la harina de maíz precocida con agua tibia y una pizca de sal. Remueve con las manos hasta que se forme una masa suave, sin grumos. La textura debe ser moldeable pero no pegajosa. Si está muy seca, añade más agua poco a poco. Este paso es clave para lograr una arepa tierna por dentro y crujiente por fuera.'),
(7, 11, 2, 'Divide la masa en porciones iguales y forma bolas. Luego aplánalas suavemente hasta obtener discos de aproximadamente 1 cm de grosor. Puedes usar papel vegetal para evitar que se peguen. Asegúrate de que los bordes estén bien definidos para que se cocinen de manera uniforme.'),
(8, 11, 3, 'Calienta una sartén antiadherente a fuego medio. Cocina las arepas por ambos lados durante unos 5 minutos cada uno, hasta que estén doradas. Luego, colócalas en el horno precalentado a 180°C durante 10 minutos para que se terminen de cocer por dentro. Sirve calientes con el relleno que prefieras.'),
(9, 12, 1, 'Cuece el arroz en abundante agua con sal hasta que esté bien cocido y ligeramente pegajoso. Escúrrelo y deja enfriar completamente. Este tipo de arroz es ideal para formar croquetas porque su almidón ayuda a que se mantengan compactas sin necesidad de harina.'),
(10, 12, 2, 'En un bol, mezcla el arroz cocido con verduras picadas finamente (como zanahoria, cebolla y pimiento), huevo batido y especias al gusto. Remueve bien hasta que todos los ingredientes estén integrados. La mezcla debe ser firme y fácil de moldear.'),
(11, 12, 3, 'Forma croquetas con las manos, dándoles forma cilíndrica o redonda. Pásalas por pan rallado sin gluten si deseas una textura más crujiente. Fríelas en aceite caliente hasta que estén doradas por fuera. También puedes hornearlas a 200°C durante 20 minutos para una versión más ligera.'),
(12, 13, 1, 'Lava bien la quinoa bajo el grifo para eliminar las saponinas, que pueden dar un sabor amargo. Cuece en agua con sal durante 15 minutos o hasta que esté tierna. Escúrrela y deja enfriar completamente. Este paso garantiza una base ligera y nutritiva para la ensalada.'),
(13, 13, 2, 'Corta el tomate, el aguacate y el pepino en cubos pequeños. Añade también cebolla morada en juliana si deseas un toque más intenso. Mezcla todos los ingredientes en un bol grande junto con la quinoa cocida.'),
(14, 13, 3, 'Aliña con aceite de oliva virgen extra, zumo de limón, sal y pimienta. Puedes añadir hierbas frescas como cilantro o perejil para potenciar el sabor. Remueve bien y sirve fría como entrante o acompañamiento. Esta ensalada es perfecta para días calurosos.'),
(15, 14, 1, 'Bate los huevos con azúcar hasta que la mezcla esté espumosa y de color claro. Añade la ralladura de limón y el zumo recién exprimido. Este paso aporta el sabor cítrico característico de la tarta y ayuda a que el relleno tenga una textura cremosa.'),
(16, 14, 2, 'Incorpora la harina de almendra poco a poco, removiendo con movimientos envolventes para no perder el aire de la mezcla. Si deseas una textura más firme, puedes añadir una cucharada de maicena sin gluten.'),
(17, 14, 3, 'Vierte la mezcla en un molde previamente engrasado o forrado con papel vegetal. Hornea a 180°C durante 30–35 minutos o hasta que al insertar un palillo salga limpio. Deja enfriar antes de desmoldar y decora con rodajas de limón o azúcar glas.'),
(18, 15, 1, 'Tritura los plátanos maduros en un bol grande hasta obtener un puré suave. Añade los huevos, el azúcar y el aceite de oliva. Mezcla bien hasta que todos los ingredientes estén completamente integrados. Esta base húmeda es esencial para lograr un pan jugoso.'),
(19, 15, 2, 'Agrega la harina sin gluten, la levadura y las nueces troceadas. Remueve con una espátula hasta obtener una masa homogénea. Si la mezcla está muy líquida, puedes añadir una cucharada extra de harina.'),
(20, 15, 3, 'Vierte la masa en un molde rectangular previamente engrasado. Hornea a 180°C durante 40 minutos o hasta que al insertar un palillo salga limpio. Deja enfriar sobre una rejilla antes de cortar. Este pan es ideal para desayunos o meriendas.'),
(21, 16, 1, 'En un bol grande, mezcla la avena sin gluten con chips de chocolate, azúcar moreno y una pizca de sal. Añade también una cucharadita de esencia de vainilla si deseas un toque aromático. Remueve bien para que los ingredientes secos se distribuyan de forma homogénea antes de incorporar los líquidos.'),
(22, 16, 2, 'Agrega los huevos batidos y el aceite de coco o mantequilla derretida. Mezcla con una espátula hasta obtener una masa pegajosa pero manejable. Si está demasiado seca, añade una cucharada de leche vegetal. Refrigera la masa durante 30 minutos para que se compacte y sea más fácil de moldear.'),
(23, 16, 3, 'Precalienta el horno a 180°C. Forma bolitas con la masa y colócalas sobre una bandeja forrada con papel vegetal, dejando espacio entre ellas para que no se peguen al expandirse. Hornea durante 12–15 minutos o hasta que los bordes estén dorados y el centro ligeramente blando.'),
(24, 17, 1, 'Pela las patatas y córtalas en láminas finas. Fríelas en abundante aceite de oliva a fuego medio hasta que estén tiernas pero no crujientes. Remueve de vez en cuando para que se cocinen de manera uniforme. Escúrrelas bien sobre papel absorbente para eliminar el exceso de grasa.'),
(25, 17, 2, 'Bate los huevos en un bol grande y añade una pizca de sal. Incorpora las patatas fritas y mezcla con cuidado para no romperlas demasiado. Deja reposar la mezcla unos minutos para que los sabores se integren. Puedes añadir cebolla caramelizada si deseas una versión más sabrosa.'),
(26, 17, 3, 'Calienta una sartén antiadherente con unas gotas de aceite. Vierte la mezcla y cocina a fuego medio durante 5–7 minutos. Da la vuelta con ayuda de un plato y cocina el otro lado. La tortilla debe quedar dorada por fuera y jugosa por dentro. Sirve caliente o a temperatura ambiente.'),
(27, 18, 1, 'Corta las pechugas de pollo en cubos medianos y salpimiéntalos. En una sartén grande, sofríe cebolla picada en aceite de oliva hasta que esté dorada. Añade el pollo y dóralo por todos lados. Este paso es esencial para sellar los jugos y mantener la carne tierna durante la cocción.'),
(28, 18, 2, 'Agrega una cucharada de curry en polvo, una pizca de comino y jengibre rallado. Incorpora leche de coco y remueve bien. Cocina a fuego medio durante 15–20 minutos hasta que la salsa espese y el pollo esté completamente cocido. Puedes añadir verduras como calabacín o pimiento si lo deseas.'),
(29, 18, 3, 'Mientras se cocina el pollo, cuece arroz blanco en agua con sal hasta que esté tierno. Escúrrelo y sírvelo como base en el plato. Coloca el pollo al curry encima y decora con cilantro fresco picado. Este plato es aromático, reconfortante y naturalmente libre de gluten.'),
(30, 19, 1, 'Pela y corta la calabaza en cubos medianos. Haz lo mismo con una zanahoria y una cebolla. En una olla grande, sofríe la cebolla en aceite de oliva hasta que esté transparente. Añade la calabaza y la zanahoria, y rehoga durante unos minutos para potenciar su sabor.'),
(31, 19, 2, 'Cubre las verduras con caldo de verduras sin gluten o agua con sal. Cocina a fuego medio durante 20–25 minutos o hasta que la calabaza esté completamente tierna. Puedes añadir una pizca de nuez moscada o jengibre para darle un toque especiado.'),
(32, 19, 3, 'Tritura todo con una batidora de mano hasta obtener una crema suave y homogénea. Si está muy espesa, añade un poco más de caldo. Rectifica de sal y sirve caliente con un chorrito de aceite de oliva virgen extra y semillas tostadas por encima.'),
(33, 20, 1, 'Lava las lentejas y escúrrelas. En una olla grande, sofríe cebolla, ajo, zanahoria y pimiento en aceite de oliva hasta que estén blandos. Añade las lentejas y remueve bien para que se impregnen del sabor de las verduras. Este paso es clave para una sopa sabrosa.'),
(34, 20, 2, 'Agrega agua o caldo de verduras sin gluten hasta cubrir las lentejas. Añade laurel, comino y sal. Cocina a fuego medio durante 30–40 minutos o hasta que las lentejas estén tiernas. Remueve de vez en cuando para evitar que se peguen al fondo de la olla.'),
(35, 20, 3, 'Cuando las lentejas estén cocidas, puedes triturar una parte para espesar la sopa o dejarla tal cual. Rectifica de sal y sirve caliente. Puedes acompañarla con pan sin gluten o un chorrito de aceite de oliva crudo para realzar el sabor.'),
(36, 21, 1, 'Pela y corta las manzanas en láminas finas, procurando que tengan un grosor uniforme para que se cocinen de manera homogénea. Colócalas en un bol y añade el zumo de medio limón para evitar que se oxiden. Agrega también una cucharadita de canela y dos cucharadas de azúcar moreno. Mezcla bien y deja reposar mientras preparas la base de la tarta.'),
(37, 21, 2, 'En otro bol, mezcla harina sin gluten (puede ser de arroz o almendra), una pizca de sal, levadura en polvo y azúcar. Añade los huevos batidos y leche vegetal o sin lactosa, removiendo hasta obtener una masa suave y sin grumos. Si la mezcla está muy líquida, puedes añadir una cucharada extra de harina.'),
(38, 21, 3, 'Engrasa un molde redondo con aceite de coco o forra con papel vegetal. Vierte la masa en el molde y distribuye las láminas de manzana por encima en forma de espiral o abanico, presionando ligeramente para que se integren con la masa. Espolvorea con más canela y azúcar si deseas un acabado más dorado.'),
(39, 21, 4, 'Hornea en horno precalentado a 180°C durante 35–40 minutos o hasta que la superficie esté dorada y al insertar un palillo en el centro, salga limpio. Deja enfriar sobre una rejilla antes de desmoldar. Puedes servirla sola o acompañada de yogur natural sin gluten o una bola de helado vegano.'),
(40, 21, 1, 'Pela y corta las manzanas en láminas finas, procurando que tengan un grosor uniforme para que se cocinen de manera homogénea. Colócalas en un bol y añade el zumo de medio limón para evitar que se oxiden. Agrega también una cucharadita de canela y dos cucharadas de azúcar moreno. Mezcla bien y deja reposar mientras preparas la base de la tarta.'),
(41, 21, 2, 'En otro bol, mezcla harina sin gluten (puede ser de arroz o almendra), una pizca de sal, levadura en polvo y azúcar. Añade los huevos batidos y leche vegetal o sin lactosa, removiendo hasta obtener una masa suave y sin grumos. Si la mezcla está muy líquida, puedes añadir una cucharada extra de harina.'),
(42, 21, 3, 'Engrasa un molde redondo con aceite de coco o forra con papel vegetal. Vierte la masa en el molde y distribuye las láminas de manzana por encima en forma de espiral o abanico, presionando ligeramente para que se integren con la masa. Espolvorea con más canela y azúcar si deseas un acabado más dorado.'),
(43, 21, 4, 'Hornea en horno precalentado a 180°C durante 35–40 minutos o hasta que la superficie esté dorada y al insertar un palillo en el centro, salga limpio. Deja enfriar sobre una rejilla antes de desmoldar. Puedes servirla sola o acompañada de yogur natural sin gluten o una bola de helado vegano.'),
(44, 23, 1, 'Pela y corta el boniato en cubos pequeños. Cuece en agua con sal durante 15–20 minutos o hasta que esté muy tierno. Escúrrelo bien y tritúralo con un tenedor o procesador hasta obtener un puré suave. Añade una pizca de canela y nuez moscada si deseas un toque aromático.'),
(45, 23, 2, 'Prepara la masa sin gluten para las empanadillas mezclando harina de arroz, agua, sal y aceite. Amasa hasta obtener una textura elástica. Divide en porciones y estira cada una en forma de disco fino. Coloca una cucharada del puré de boniato en el centro.'),
(46, 23, 3, 'Dobla la masa sobre el relleno formando una media luna y sella los bordes presionando con un tenedor. Coloca las empanadillas en una bandeja forrada con papel vegetal. Píntalas con aceite de oliva para que se doren mejor durante el horneado.'),
(47, 23, 4, 'Hornea a 200°C durante 20–25 minutos o hasta que estén doradas y crujientes por fuera. Deja enfriar unos minutos antes de servir. Estas empanadillas son perfectas como entrante o cena ligera, y se pueden acompañar con una salsa de yogur o hummus.'),
(48, 24, 1, 'Calienta las tortillas de maíz en una sartén sin aceite durante unos segundos por cada lado, hasta que estén flexibles y ligeramente tostadas. Esto ayuda a que no se rompan al doblarlas y mejora su sabor. Puedes envolverlas en un paño limpio para mantenerlas calientes.'),
(49, 24, 2, 'Prepara el relleno con carne picada, pollo desmenuzado o verduras salteadas. Cocina con cebolla, ajo y especias como comino, pimentón y orégano. Añade tomate natural o salsa casera para dar jugosidad. Cocina a fuego medio hasta que todo esté bien integrado.'),
(50, 24, 3, 'Rellena cada tortilla con una porción del preparado y añade toppings como lechuga picada, aguacate, cebolla morada, cilantro fresco y un chorrito de limón. También puedes añadir salsa picante o crema de anacardos para darle un toque especial.'),
(51, 24, 4, 'Sirve los tacos inmediatamente para que las tortillas mantengan su textura. Puedes acompañarlos con arroz integral, frijoles negros o una ensalada fresca. Esta receta es versátil, sin gluten y perfecta para compartir en reuniones o cenas informales.'),
(52, 25, 1, 'Corta las berenjenas por la mitad a lo largo y haz cortes superficiales en la pulpa. Colócalas en una bandeja, rocíalas con aceite de oliva y hornea a 180°C durante 20 minutos para ablandarlas. Este paso facilita el vaciado y mejora el sabor del relleno.'),
(53, 25, 2, 'Con una cuchara, retira la pulpa cocida y pícala finamente. En una sartén, sofríe cebolla, ajo y pimiento. Añade carne picada o proteína vegetal y cocina hasta dorar. Incorpora la pulpa de berenjena, tomate triturado y especias como orégano y pimienta.'),
(54, 25, 3, 'Rellena las mitades de berenjena con la mezcla caliente, presionando ligeramente para que quede compacta. Espolvorea con queso rallado sin gluten o levadura nutricional si deseas una versión vegana. Coloca nuevamente en la bandeja de horno.'),
(55, 25, 4, 'Hornea durante 15–20 minutos adicionales o hasta que el queso esté fundido y dorado. Sirve caliente, acompañado de arroz integral, ensalada o pan sin gluten. Esta receta es nutritiva, sabrosa y perfecta como plato principal.'),
(56, 25, 1, 'Corta las berenjenas por la mitad a lo largo y haz cortes superficiales en la pulpa. Colócalas en una bandeja, rocíalas con aceite de oliva y hornea a 180°C durante 20 minutos para ablandarlas. Este paso facilita el vaciado y mejora el sabor del relleno.'),
(57, 25, 2, 'Con una cuchara, retira la pulpa cocida y pícala finamente. En una sartén, sofríe cebolla, ajo y pimiento. Añade carne picada o proteína vegetal y cocina hasta dorar. Incorpora la pulpa de berenjena, tomate triturado y especias como orégano y pimienta.'),
(58, 25, 3, 'Rellena las mitades de berenjena con la mezcla caliente, presionando ligeramente para que quede compacta. Espolvorea con queso rallado sin gluten o levadura nutricional si deseas una versión vegana. Coloca nuevamente en la bandeja de horno.'),
(59, 25, 4, 'Hornea durante 15–20 minutos adicionales o hasta que el queso esté fundido y dorado. Sirve caliente, acompañado de arroz integral, ensalada o pan sin gluten. Esta receta es nutritiva, sabrosa y perfecta como plato principal.'),
(60, 25, 1, 'Corta las berenjenas por la mitad a lo largo y haz cortes superficiales en la pulpa. Colócalas en una bandeja, rocíalas con aceite de oliva y hornea a 180°C durante 20 minutos para ablandarlas. Este paso facilita el vaciado y mejora el sabor del relleno.'),
(61, 25, 2, 'Con una cuchara, retira la pulpa cocida y pícala finamente. En una sartén, sofríe cebolla, ajo y pimiento. Añade carne picada o proteína vegetal y cocina hasta dorar. Incorpora la pulpa de berenjena, tomate triturado y especias como orégano y pimienta.'),
(62, 25, 3, 'Rellena las mitades de berenjena con la mezcla caliente, presionando ligeramente para que quede compacta. Espolvorea con queso rallado sin gluten o levadura nutricional si deseas una versión vegana. Coloca nuevamente en la bandeja de horno.'),
(63, 25, 4, 'Hornea durante 15–20 minutos adicionales o hasta que el queso esté fundido y dorado. Sirve caliente, acompañado de arroz integral, ensalada o pan sin gluten. Esta receta es nutritiva, sabrosa y perfecta como plato principal.'),
(64, 28, 1, 'En un bol grande, mezcla harina sin gluten con levadura seca, sal y una mezcla de semillas como chía, lino, girasol y calabaza. Añade también una cucharadita de azúcar para activar la levadura. Remueve bien para distribuir los ingredientes secos.'),
(65, 28, 2, 'Agrega agua templada y aceite de oliva. Mezcla con una espátula hasta obtener una masa húmeda pero manejable. Amasa durante 10 minutos sobre una superficie ligeramente enharinada. Si la masa está muy pegajosa, añade un poco más de harina sin gluten.'),
(66, 28, 3, 'Coloca la masa en un molde engrasado, cubre con un paño y deja reposar en un lugar cálido durante 1 hora. Precalienta el horno a 180°C y hornea durante 40 minutos o hasta que el pan esté dorado y al golpear la base suene hueco. Deja enfriar antes de cortar.'),
(67, 29, 1, 'En un bol, mezcla la harina de trigo sarraceno con agua, una pizca de sal y una cucharada de aceite de oliva. Remueve bien hasta obtener una masa líquida y sin grumos. Deja reposar durante al menos 30 minutos para que la harina se hidrate y la mezcla se estabilice.'),
(68, 29, 2, 'Calienta una sartén antiadherente a fuego medio y engrásala ligeramente con aceite. Vierte un cucharón de masa y extiéndela girando la sartén para formar una capa fina. Cocina durante 2–3 minutos o hasta que los bordes se despeguen.'),
(69, 29, 3, 'Da la vuelta con una espátula y cocina el otro lado durante 1 minuto más. Repite el proceso con el resto de la masa. Puedes rellenar los crepes con verduras salteadas, queso sin gluten, hummus o incluso opciones dulces como plátano y crema de cacahuete.'),
(70, 30, 1, 'En un bol grande, mezcla la almendra molida con azúcar y una pizca de sal. Añade también ralladura de limón o esencia de vainilla si deseas un toque aromático. Remueve bien para que los ingredientes secos se integren completamente antes de añadir los líquidos.'),
(71, 30, 2, 'Agrega los huevos uno a uno, mezclando con una espátula hasta obtener una masa espesa y ligeramente pegajosa. Si la mezcla está demasiado líquida, puedes añadir una cucharada extra de almendra molida. Refrigera la masa durante 30 minutos para facilitar el formado.'),
(72, 30, 3, 'Precalienta el horno a 180°C. Forma bolitas con la masa y colócalas sobre una bandeja forrada con papel vegetal. Puedes aplastarlas ligeramente con los dedos o un tenedor. Hornea durante 12–15 minutos o hasta que estén doradas por los bordes. Deja enfriar antes de servir.'),
(73, 30, 1, 'En un bol grande, mezcla la almendra molida con azúcar y una pizca de sal. Añade también ralladura de limón o esencia de vainilla si deseas un toque aromático. Remueve bien para que los ingredientes secos se integren completamente antes de añadir los líquidos.'),
(74, 30, 2, 'Agrega los huevos uno a uno, mezclando con una espátula hasta obtener una masa espesa y ligeramente pegajosa. Si la mezcla está demasiado líquida, puedes añadir una cucharada extra de almendra molida. Refrigera la masa durante 30 minutos para facilitar el formado.'),
(75, 30, 3, 'Precalienta el horno a 180°C. Forma bolitas con la masa y colócalas sobre una bandeja forrada con papel vegetal. Puedes aplastarlas ligeramente con los dedos o un tenedor. Hornea durante 12–15 minutos o hasta que estén doradas por los bordes. Deja enfriar antes de servir.'),
(76, 32, 1, 'En un bol grande, mezcla queso crema con azúcar, huevos y una cucharadita de esencia de vainilla. Bate hasta obtener una mezcla suave y sin grumos. Si deseas una textura más firme, puedes añadir una cucharada de maicena sin gluten.'),
(77, 32, 2, 'Vierte la mezcla en un molde redondo previamente engrasado. No necesitas base de galleta, lo que hace esta receta más ligera y apta para celíacos. Alisa la superficie con una espátula para que se hornee de manera uniforme.'),
(78, 32, 3, 'Hornea a 180°C durante 40–45 minutos o hasta que el centro esté firme pero ligeramente tembloroso. Deja enfriar completamente antes de desmoldar. Refrigera al menos 2 horas antes de servir. Puedes decorar con mermelada sin gluten o frutas frescas.'),
(79, 33, 1, 'En un bol grande, mezcla harina sin gluten con semillas de lino y chía, levadura seca, sal y una cucharadita de azúcar. Añade agua templada poco a poco mientras remueves con una espátula. La mezcla debe quedar húmeda pero manejable.'),
(80, 33, 2, 'Amasa durante 10 minutos sobre una superficie ligeramente enharinada. Si la masa está muy pegajosa, añade un poco más de harina sin gluten. Forma una bola y colócala en un bol limpio. Cubre con un paño húmedo y deja reposar en un lugar cálido durante 1 hora.'),
(81, 33, 3, 'Precalienta el horno a 180°C. Coloca la masa en un molde engrasado y espolvorea más semillas por encima. Hornea durante 40 minutos o hasta que esté dorado y al golpear la base suene hueco. Deja enfriar completamente antes de cortar.'),
(82, 34, 1, 'Pela los plátanos maduros y colócalos en un bol. Tritúralos con un tenedor hasta obtener un puré suave. Añade dos huevos batidos y una pizca de canela. Mezcla bien hasta que todos los ingredientes estén completamente integrados. Esta base es naturalmente dulce y no requiere azúcar adicional.'),
(83, 34, 2, 'Calienta una sartén antiadherente a fuego medio y engrásala ligeramente con aceite de coco o de oliva. Vierte pequeñas porciones de la mezcla con una cuchara, formando tortitas de unos 8 cm de diámetro. Cocina durante 2–3 minutos por cada lado o hasta que estén doradas.'),
(84, 34, 3, 'Retira las tortitas y colócalas sobre papel absorbente. Repite el proceso hasta terminar la mezcla. Sirve calientes acompañadas de fruta fresca, yogur natural sin gluten o un chorrito de sirope de arce. Estas tortitas son ideales para desayunos rápidos y nutritivos.'),
(85, 35, 1, 'Pela y corta las zanahorias en rodajas finas. Haz lo mismo con una patata pequeña y media cebolla. En una olla grande, sofríe la cebolla en aceite de oliva hasta que esté transparente. Añade las zanahorias y la patata, y rehoga durante unos minutos para potenciar el sabor.'),
(86, 35, 2, 'Cubre las verduras con agua o caldo de verduras sin gluten. Añade una pizca de sal, pimienta y jengibre rallado si deseas un toque especiado. Cocina a fuego medio durante 25–30 minutos o hasta que las zanahorias estén completamente tiernas.'),
(87, 35, 3, 'Tritura todo con una batidora de mano hasta obtener una crema suave y homogénea. Si está muy espesa, añade un poco más de caldo. Rectifica de sal y sirve caliente con un chorrito de aceite de oliva virgen extra y semillas tostadas por encima.'),
(88, 36, 1, 'En un bol, mezcla harina sin gluten con levadura seca, sal y una cucharadita de azúcar. Añade agua templada y aceite de oliva, y amasa hasta obtener una masa suave y elástica. Deja reposar durante 1 hora en un lugar cálido para que la masa fermente y aumente su volumen.'),
(89, 36, 2, 'Precalienta el horno a 200°C. Estira la masa sobre papel vegetal formando un disco fino. Hornea la base durante 10 minutos para que se precocine y no se humedezca con los ingredientes. Esto ayuda a que la pizza quede crujiente por fuera y tierna por dentro.'),
(90, 36, 3, 'Retira la base del horno y añade salsa de tomate natural, queso rallado sin gluten y los ingredientes que prefieras: verduras, jamón cocido, champiñones, aceitunas, etc. Vuelve a hornear durante 10–15 minutos o hasta que el queso esté fundido y dorado.'),
(91, 37, 1, 'En un bol grande, mezcla avena sin gluten con pasas, azúcar moreno, canela y una pizca de sal. Añade también ralladura de naranja si deseas un toque cítrico. Remueve bien para que los ingredientes secos se distribuyan de forma homogénea.'),
(92, 37, 2, 'Agrega huevos batidos y aceite de oliva o mantequilla derretida. Mezcla con una espátula hasta obtener una masa espesa y ligeramente pegajosa. Si está muy seca, añade una cucharada de leche vegetal. Refrigera la masa durante 30 minutos para facilitar el formado.'),
(93, 37, 3, 'Precalienta el horno a 180°C. Forma bolitas con la masa y colócalas sobre una bandeja forrada con papel vegetal. Aplástalas ligeramente con los dedos. Hornea durante 12–15 minutos o hasta que estén doradas por los bordes. Deja enfriar antes de servir.'),
(94, 38, 1, 'En un bol grande, mezcla la harina de trigo sarraceno con levadura seca, una pizca de sal y una cucharadita de azúcar moreno. Añade también nueces troceadas y, si lo deseas, semillas como lino o chía. Remueve bien para distribuir los ingredientes secos de forma uniforme.'),
(95, 38, 2, 'Agrega agua templada poco a poco mientras mezclas con una espátula o tus manos. Añade también una cucharada de aceite de oliva virgen extra. Amasa durante 10 minutos hasta obtener una masa suave y algo pegajosa. Si está muy seca, añade más agua en pequeñas cantidades.'),
(96, 38, 3, 'Coloca la masa en un bol limpio, cúbrela con un paño húmedo y deja reposar en un lugar cálido durante al menos una hora. La fermentación permitirá que el pan suba y desarrolle sabor. Puedes dejarlo más tiempo si deseas una miga más aireada.'),
(97, 38, 4, 'Precalienta el horno a 180°C. Vierte la masa en un molde engrasado o forrado con papel vegetal. Hornea durante 40 minutos o hasta que la superficie esté dorada y al insertar un palillo salga limpio. Deja enfriar completamente antes de cortar. Este pan es ideal para desayunos o meriendas.'),
(98, 39, 1, 'Pela y corta la calabaza en cubos pequeños. Cocínala al vapor o hiérvela hasta que esté muy tierna. Tritura con un tenedor o procesador hasta obtener un puré suave. Este puré será la base del bizcocho y le dará una textura húmeda y un sabor dulce natural.'),
(99, 39, 2, 'En un bol grande, bate los huevos con azúcar moreno hasta que la mezcla esté espumosa. Añade el puré de calabaza, aceite de oliva suave y una cucharadita de canela. Mezcla bien hasta que todos los ingredientes estén completamente integrados.'),
(100, 39, 3, 'Agrega la harina sin gluten, levadura en polvo y una pizca de sal. Remueve con movimientos envolventes hasta obtener una masa homogénea. Si deseas, puedes añadir nueces troceadas o chips de chocolate para enriquecer la receta.'),
(101, 39, 4, 'Vierte la mezcla en un molde previamente engrasado o forrado con papel vegetal. Hornea a 180°C durante 35–40 minutos o hasta que al insertar un palillo salga limpio. Deja enfriar sobre una rejilla antes de desmoldar. Este bizcocho es perfecto para otoño y meriendas saludables.'),
(102, 2, 1, 'Precalienta el horno a 180°C y prepara un molde redondo engrasado o forrado con papel vegetal. Esto evitará que el bizcocho se pegue y facilitará el desmoldado. Mientras el horno alcanza la temperatura, puedes comenzar con la mezcla de ingredientes.'),
(103, 2, 2, 'Bate los huevos con el azúcar hasta que la mezcla esté espumosa y haya duplicado su volumen. Este paso es clave para lograr un bizcocho aireado y ligero. Puedes usar batidora eléctrica para facilitar el proceso.'),
(104, 2, 3, 'Añade la harina de almendra poco a poco, mezclando con movimientos envolventes para no perder el aire incorporado. Si deseas un toque cítrico, puedes añadir ralladura de limón o naranja. La masa debe quedar suave y homogénea.'),
(105, 2, 4, 'Vierte la mezcla en el molde preparado y alisa la superficie con una espátula. Hornea durante 30–35 minutos o hasta que al insertar un palillo en el centro, salga limpio. Evita abrir el horno antes de tiempo para no afectar el levado.'),
(106, 2, 5, 'Deja enfriar el bizcocho sobre una rejilla antes de desmoldar. Puedes decorarlo con azúcar glas, almendras laminadas o fruta fresca. Este bizcocho es naturalmente sin gluten y perfecto para acompañar con té o café.'),
(107, 3, 1, 'Ralla la coliflor cruda con un rallador grueso o tritúrala en un procesador de alimentos hasta obtener una textura similar al arroz. Colócala en un bol y cocínala al vapor durante 10 minutos. Luego, deja enfriar y exprime bien con un paño limpio para eliminar el exceso de agua.'),
(108, 3, 2, 'Mezcla la coliflor cocida con huevo batido, queso rallado sin gluten (como mozzarella o parmesano), sal y especias al gusto. Remueve bien hasta obtener una masa compacta. Este paso es clave para que la base de la pizza se mantenga firme al hornearse.'),
(109, 3, 3, 'Coloca la masa sobre papel vegetal y extiéndela formando un disco fino. Hornea a 200°C durante 15 minutos o hasta que los bordes estén dorados. Esto precocina la base y evita que se humedezca con los ingredientes del relleno.'),
(110, 3, 4, 'Retira la base del horno y añade salsa de tomate natural, queso rallado y los ingredientes que prefieras: verduras, jamón cocido, champiñones, aceitunas, etc. Vuelve a hornear durante 10–15 minutos o hasta que el queso esté fundido y dorado.'),
(111, 3, 5, 'Sirve caliente y acompaña con una ensalada fresca. Esta pizza es una excelente alternativa sin gluten, rica en fibra y baja en carbohidratos. Puedes experimentar con diferentes tipos de queso y toppings según tus preferencias.'),
(112, 4, 1, 'En un bol, mezcla avena sin gluten con plátano maduro triturado y huevos batidos. Añade una pizca de canela y esencia de vainilla si deseas un sabor más aromático. Remueve bien hasta obtener una masa espesa y homogénea.'),
(113, 4, 2, 'Calienta una sartén antiadherente a fuego medio y engrásala ligeramente con aceite de coco o de oliva. Vierte pequeñas porciones de la mezcla con una cuchara, formando tortitas de unos 8 cm de diámetro. Cocina durante 2–3 minutos por cada lado o hasta que estén doradas.'),
(114, 4, 3, 'Retira las tortitas y colócalas sobre papel absorbente. Repite el proceso hasta terminar la mezcla. Sirve calientes acompañadas de fruta fresca, yogur natural sin gluten o un chorrito de sirope de arce. Estas tortitas son ideales para desayunos rápidos y nutritivos.'),
(115, 5, 1, 'En un bol grande, mezcla harina de arroz con leche vegetal o sin lactosa, huevos batidos y una pizca de sal. Remueve bien hasta obtener una masa líquida y sin grumos. Deja reposar durante 30 minutos para que la harina se hidrate y la mezcla se estabilice.'),
(116, 5, 2, 'Calienta una sartén antiadherente a fuego medio y engrásala ligeramente con aceite. Vierte un cucharón de masa y extiéndela girando la sartén para formar una capa fina. Cocina durante 2–3 minutos o hasta que los bordes se despeguen.'),
(117, 5, 3, 'Da la vuelta con una espátula y cocina el otro lado durante 1 minuto más. Repite el proceso con el resto de la masa. Puedes rellenar los crepes con verduras salteadas, queso sin gluten, hummus o incluso opciones dulces como plátano y crema de cacahuete.'),
(127, 9, 1, 'Pela y ralla las zanahorias finamente. En un bol grande, bate los huevos con azúcar moreno hasta que la mezcla esté espumosa. Añade la zanahoria rallada, aceite de oliva suave y una cucharadita de canela. Mezcla bien hasta que todos los ingredientes estén integrados.'),
(128, 9, 2, 'Agrega harina sin gluten, levadura en polvo y una pizca de sal. Remueve con movimientos envolventes hasta obtener una masa homogénea. Si deseas, puedes añadir nueces troceadas o pasas para enriquecer la receta. La textura debe ser espesa pero fluida.'),
(129, 9, 3, 'Vierte la mezcla en un molde redondo previamente engrasado o forrado con papel vegetal. Hornea a 180°C durante 35–40 minutos o hasta que al insertar un palillo salga limpio. Deja enfriar antes de desmoldar. Puedes decorar con crema de queso sin gluten o azúcar glas.'),
(130, 10, 1, 'Tritura los plátanos maduros en un bol hasta obtener un puré suave. Añade huevos batidos, azúcar moreno y aceite de oliva suave. Mezcla bien hasta que todos los ingredientes estén completamente integrados. Esta base húmeda es esencial para lograr muffins jugosos.'),
(131, 10, 2, 'Agrega harina sin gluten, levadura en polvo y una pizca de sal. Remueve con movimientos envolventes hasta obtener una masa homogénea. Si deseas, puedes añadir chips de chocolate o nueces troceadas. La mezcla debe quedar espesa pero fluida.'),
(132, 10, 3, 'Vierte la masa en moldes individuales para muffins previamente engrasados o con cápsulas de papel. Hornea a 180°C durante 20–25 minutos o hasta que estén dorados y al insertar un palillo salga limpio. Deja enfriar antes de servir. Son ideales para desayunos o meriendas.'),
(133, 40, 1, 'Precalentar el horno a 180°C.'),
(134, 40, 2, 'Pelar y cortar las manzanas en láminas finas.'),
(135, 40, 3, 'Mezclar la harina sin gluten, el azúcar y la canela en un bol.'),
(136, 40, 4, 'Añadir los huevos y batir hasta obtener una masa homogénea.'),
(137, 40, 5, 'Incorporar las manzanas a la mezcla y verter en un molde.'),
(138, 40, 6, 'Hornear durante 40 minutos o hasta que esté dorada.');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `post`
--

CREATE TABLE `post` (
  `id_post` int(255) NOT NULL,
  `fecha_publicacion` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `contenido` varchar(255) DEFAULT NULL,
  `id_usuario` int(255) NOT NULL,
  `url_post` varchar(255) DEFAULT NULL,
  `fecha_validacion` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `valoracion` int(30) NOT NULL,
  `estado` enum('PENDIENTE','APROBADO','RECHAZADO') DEFAULT 'PENDIENTE'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `receta`
--

CREATE TABLE `receta` (
  `id_receta` int(255) NOT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `imagen_url` varchar(255) DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `dificultad` varchar(255) DEFAULT NULL,
  `id_usuario` int(255) NOT NULL,
  `fecha_validacion` timestamp NULL DEFAULT NULL,
  `valoracion` int(30) NOT NULL,
  `estado` enum('PENDIENTE','APROBADO','RECHAZADO') DEFAULT 'PENDIENTE'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `receta`
--

INSERT INTO `receta` (`id_receta`, `titulo`, `descripcion`, `imagen_url`, `fecha_creacion`, `dificultad`, `id_usuario`, `fecha_validacion`, `valoracion`, `estado`) VALUES
(1, 'Pan de trigo sarraceno', 'Pan sin gluten elaborado con trigo sarraceno y semillas.', 'img/pan_trigo_sarraceno.jpg', '2025-10-30 17:01:24', 'media', 1, NULL, 0, 'PENDIENTE'),
(2, 'Bizcocho de almendra', 'Bizcocho esponjoso sin gluten con harina de almendra.', 'img/bizcocho_almendra.jpg', '2025-10-30 17:01:48', 'fácil', 13, NULL, 0, 'PENDIENTE'),
(3, 'Pizza de coliflor', 'Base de pizza hecha con coliflor, sin harinas.', 'img/pizza_coliflor.jpg', '2025-10-30 17:01:56', 'fácil', 13, NULL, 0, 'PENDIENTE'),
(4, 'Tortitas de avena sin gluten', 'Tortitas saludables con avena certificada sin gluten.', 'img/tortitas_avena.jpg', '2025-10-30 17:02:08', 'fácil', 13, NULL, 0, 'PENDIENTE'),
(5, 'Crepes de harina de arroz', 'Crepes ligeros y sin gluten con harina de arroz.', 'img/crepes_arroz.jpg', '2025-10-30 17:02:15', 'fácil', 13, NULL, 0, 'PENDIENTE'),
(6, 'Galletas de coco', 'Galletas crujientes sin gluten con coco rallado.', 'img/galletas_coco.jpg', '2025-10-30 17:02:31', 'fácil', 13, NULL, 0, 'PENDIENTE'),
(7, 'Brownie de garbanzos', 'Brownie sin gluten con base de garbanzos cocidos.', 'img/brownie_garbanzos.jpg', '2025-10-30 17:02:22', 'media', 13, NULL, 0, 'PENDIENTE'),
(8, 'Pan de maíz', 'Pan tradicional sin gluten con harina de maíz.', 'img/pan_maiz.jpg', '2025-10-30 17:02:38', 'media', 13, NULL, 0, 'PENDIENTE'),
(9, 'Tarta de zanahoria', 'Tarta sin gluten con zanahoria y nueces.', 'img/tarta_zanahoria.jpg', '2025-10-30 17:02:48', 'media', 13, NULL, 0, 'PENDIENTE'),
(10, 'Muffins de plátano', 'Muffins sin gluten con plátano maduro.', 'img/muffins_platano.jpg', '2025-10-30 17:02:56', 'fácil', 13, NULL, 0, 'PENDIENTE'),
(11, 'Arepas venezolanas', 'Arepas sin gluten hechas con harina de maíz precocida.', 'img/arepas.jpg', '2025-10-30 17:03:04', 'complicada', 13, NULL, 0, 'PENDIENTE'),
(12, 'Croquetas de arroz', 'Croquetas sin gluten con arroz y verduras.', 'img/croquetas_arroz.jpg', '2025-10-30 17:03:11', 'media', 13, NULL, 0, 'PENDIENTE'),
(13, 'Ensalada de quinoa', 'Ensalada fresca con quinoa, tomate y aguacate.', 'img/ensalada_quinoa.jpg', '2025-10-30 17:03:18', 'fácil', 13, NULL, 0, 'PENDIENTE'),
(14, 'Tarta de limón sin gluten', 'Tarta cítrica con base de almendra.', 'img/tarta_limon.jpg', '2025-10-30 17:03:25', 'complicada', 13, NULL, 0, 'PENDIENTE'),
(15, 'Pan de plátano', 'Pan dulce sin gluten con plátano y nueces.', 'img/pan_platano.jpg', '2025-10-30 17:03:35', 'complicada', 13, NULL, 0, 'PENDIENTE'),
(16, 'Cookies de avena y chocolate', 'Galletas sin gluten con avena y chips de chocolate.', 'img/cookies_avena.jpg', '2025-10-30 17:03:42', 'media', 13, NULL, 0, 'PENDIENTE'),
(17, 'Tortilla de patata', 'Clásica tortilla española sin gluten.', 'img/tortilla_patata.jpg', '2025-10-30 17:03:49', 'fácil', 13, NULL, 0, 'PENDIENTE'),
(18, 'Pollo al curry con arroz', 'Pollo al curry suave acompañado de arroz blanco.', 'img/pollo_curry.jpg', '2025-10-30 17:03:56', 'media', 13, NULL, 0, 'PENDIENTE'),
(19, 'Crema de calabaza', 'Crema suave de calabaza sin gluten.', 'img/crema_calabaza.jpg', '2025-10-30 17:04:03', 'fácil', 13, NULL, 0, 'PENDIENTE'),
(20, 'Sopa de lentejas', 'Sopa nutritiva con lentejas y verduras.', 'img/sopa_lentejas.jpg', '2025-10-30 17:04:10', 'fácil', 13, NULL, 0, 'PENDIENTE'),
(21, 'Tarta de manzana sin gluten', 'Tarta casera con manzana y canela.', 'img/tarta_manzana.jpg', '2025-10-30 17:04:24', 'media', 13, NULL, 0, 'PENDIENTE'),
(22, 'Pan de arroz integral', 'Pan sin gluten con harina de arroz integral.', 'img/pan_arroz_integral.jpg', '2025-10-30 17:04:17', 'complicada', 13, NULL, 0, 'PENDIENTE'),
(23, 'Empanadillas de boniato', 'Empanadillas sin gluten rellenas de boniato.', 'img/empanadillas_boniato.jpg', '2025-10-30 17:04:30', 'fácil', 13, NULL, 0, 'PENDIENTE'),
(24, 'Tacos de maíz', 'Tacos mexicanos con tortillas de maíz sin gluten.', 'img/tacos_maiz.jpg', '2025-10-30 17:04:38', 'media', 13, NULL, 0, 'PENDIENTE'),
(25, 'Berenjenas rellenas', 'Berenjenas al horno rellenas de carne y verduras.', 'img/berenjenas_rellenas.jpg', '2025-10-30 17:04:44', 'media', 13, NULL, 0, 'PENDIENTE'),
(26, 'Albóndigas de lentejas', 'Albóndigas veganas sin gluten con lentejas.', 'img/albondigas_lentejas.jpg', '2025-10-30 17:04:58', 'media', 13, NULL, 0, 'PENDIENTE'),
(27, 'Tarta de chocolate sin gluten', 'Tarta intensa de chocolate sin harinas.', 'img/tarta_chocolate.jpg', '2025-10-30 17:05:05', 'complicada', 13, NULL, 0, 'PENDIENTE'),
(28, 'Pan de semillas', 'Pan sin gluten con mezcla de semillas.', 'img/pan_semillas.jpg', '2025-10-30 17:05:12', 'complicada', 13, NULL, 0, 'PENDIENTE'),
(29, 'Crepes de trigo sarraceno', 'Crepes salados sin gluten.', 'img/crepes_sarraceno.jpg', '2025-10-30 17:05:20', 'media', 13, NULL, 0, 'PENDIENTE'),
(30, 'Galletas de almendra', 'Galletas sin gluten con almendra molida.', 'img/galletas_almendra.jpg', '2025-10-30 17:05:27', 'media', 13, NULL, 0, 'PENDIENTE'),
(31, 'Bizcocho de yogur sin gluten', 'Bizcocho clásico sin gluten con yogur natural.', 'img/bizcocho_yogur.jpg', '2025-10-30 17:05:36', 'media', 13, NULL, 0, 'PENDIENTE'),
(32, 'Tarta de queso sin gluten', 'Tarta cremosa sin base de galleta.', 'img/tarta_queso.jpg', '2025-10-30 17:05:44', 'media', 13, NULL, 0, 'PENDIENTE'),
(33, 'Pan de lino y chía', 'Pan sin gluten con semillas de lino y chía.', 'img/pan_lino_chia.jpg', '2025-10-30 17:05:51', 'complicada', 13, NULL, 0, 'PENDIENTE'),
(34, 'Tortitas de plátano', 'Tortitas sin gluten con plátano y huevo.', 'img/tortitas_platano.jpg', '2025-10-30 17:06:00', 'fácil', 13, NULL, 0, 'PENDIENTE'),
(35, 'Crema de zanahoria', 'Crema suave sin gluten con zanahoria y jengibre.', 'img/crema_zanahoria.jpg', '2025-10-30 17:06:07', 'fácil', 13, NULL, 0, 'PENDIENTE'),
(36, 'Pizza sin gluten', 'Pizza con base de harina sin gluten.', 'img/pizza_singluten.jpg', '2025-10-30 17:06:15', 'fácil', 13, NULL, 0, 'PENDIENTE'),
(37, 'Galletas de avena y pasas', 'Galletas sin gluten con pasas y canela.', 'img/galletas_pasas.jpg', '2025-10-30 17:06:22', 'media', 13, NULL, 0, 'PENDIENTE'),
(38, 'Pan de trigo sarraceno y nueces', 'Pan sin gluten con nueces troceadas.', 'img/pan_sarraceno_nueces.jpg', '2025-10-30 17:06:28', 'complicada', 13, NULL, 0, 'PENDIENTE'),
(39, 'Bizcocho de calabaza', 'Bizcocho sin gluten con puré de calabaza.', 'img/bizcocho_calabaza.jpg', '2025-10-30 17:06:36', 'fácil', 13, NULL, 0, 'PENDIENTE'),
(40, 'Tarta de manzana sin gluten', 'Deliciosa tarta apta para celiacos, con manzanas caramelizadas.', 'https://ejemplo.com/imagenes/tarta.jpg', '2025-10-30 17:06:44', 'media', 13, NULL, 0, 'PENDIENTE');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `receta_ingrediente`
--

CREATE TABLE `receta_ingrediente` (
  `id_receta_ingrediente` int(11) NOT NULL,
  `id_receta` int(255) NOT NULL,
  `id_ingrediente` int(255) NOT NULL,
  `cantidad` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `receta_ingrediente`
--

INSERT INTO `receta_ingrediente` (`id_receta_ingrediente`, `id_receta`, `id_ingrediente`, `cantidad`) VALUES
(10, 1, 3, 200),
(11, 1, 6, 50),
(12, 1, 4, 2),
(13, 2, 2, 150),
(14, 2, 4, 3),
(15, 2, 5, 100),
(16, 3, 4, 2),
(17, 3, 6, 30),
(18, 3, 16, 100),
(19, 4, 13, 100),
(20, 4, 4, 2),
(21, 4, 7, 1),
(22, 5, 1, 120),
(23, 5, 4, 2),
(24, 5, 5, 150),
(25, 6, 10, 100),
(26, 6, 4, 1),
(27, 6, 6, 30),
(28, 7, 9, 100),
(29, 7, 4, 2),
(30, 7, 6, 50),
(31, 8, 12, 200),
(32, 8, 4, 2),
(33, 8, 6, 40),
(34, 9, 8, 150),
(35, 9, 2, 100),
(36, 9, 4, 3),
(37, 10, 7, 2),
(38, 10, 2, 100),
(39, 10, 4, 2),
(40, 11, 12, 150),
(41, 11, 6, 20),
(42, 12, 1, 100),
(43, 12, 8, 50),
(44, 12, 6, 30),
(45, 13, 11, 100),
(46, 13, 16, 50),
(47, 13, 17, 1),
(48, 14, 2, 120),
(49, 14, 4, 3),
(50, 14, 19, 5),
(51, 15, 7, 2),
(52, 15, 2, 100),
(53, 15, 18, 30),
(54, 16, 13, 100),
(55, 16, 9, 50),
(56, 16, 4, 1),
(57, 17, 4, 3),
(58, 17, 6, 20),
(59, 18, 1, 100),
(60, 18, 6, 30),
(61, 19, 14, 150),
(62, 19, 6, 20),
(63, 20, 15, 100),
(64, 20, 8, 50),
(65, 20, 6, 20),
(66, 40, 1, 3),
(67, 40, 2, 200),
(68, 40, 3, 100),
(69, 40, 4, 2),
(70, 40, 5, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `restaurante`
--

CREATE TABLE `restaurante` (
  `id_restaurante` int(255) NOT NULL,
  `fecha_publicacion` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `nombre` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `url_web` varchar(255) DEFAULT NULL,
  `imagen_url` varchar(255) DEFAULT NULL,
  `ubicacion` varchar(255) DEFAULT NULL,
  `telefono` int(9) NOT NULL,
  `email` varchar(100) NOT NULL,
  `id_usuario` int(255) NOT NULL,
  `estado` enum('PENDIENTE','APROBADO','RECHAZADO') DEFAULT 'PENDIENTE',
  `fecha_validacion` timestamp NULL DEFAULT NULL,
  `valoracion` int(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `restaurante`
--

INSERT INTO `restaurante` (`id_restaurante`, `fecha_publicacion`, `nombre`, `direccion`, `descripcion`, `url_web`, `imagen_url`, `ubicacion`, `telefono`, `email`, `id_usuario`, `estado`, `fecha_validacion`, `valoracion`) VALUES
(1, '2025-10-31 06:07:18', 'Vega', 'Calle de la Luna 9, 28004 Madrid', 'Restaurante vegano con cocina de autor y productos ecológicos.', 'https://restaurantevega.com', 'https://restaurantevega.com/wp-content/uploads/2023/vega-plato.jpg', 'Madrid', 0, '', 13, 'PENDIENTE', NULL, 0),
(2, '2025-10-31 06:07:18', 'La Encomienda', 'Calle Encomienda 19, 28012 Madrid', 'Cocina vegana creativa con ingredientes de temporada.', 'https://laencomienda.es', 'https://laencomienda.es/wp-content/uploads/2023/ensalada.jpg', 'Madrid', 0, '', 13, 'PENDIENTE', NULL, 0),
(3, '2025-10-31 06:07:18', 'B13 Bar', 'Calle Ballesta 13, 28004 Madrid', 'Bar vegano con tapas clásicas y ambiente alternativo.', 'https://b13bar.com', 'https://b13bar.com/wp-content/uploads/2023/burger.jpg', 'Madrid', 0, '', 13, 'PENDIENTE', NULL, 0),
(4, '2025-10-31 06:07:18', 'Vegan Rock', 'Calle San Juan de la Cruz 9, 50006 Zaragoza', 'Comida rápida vegana con hamburguesas y bocadillos.', 'https://veganrock.es', 'https://veganrock.es/wp-content/uploads/2023/vegan-burger.jpg', 'Zaragoza', 0, '', 13, 'PENDIENTE', NULL, 0),
(5, '2025-10-31 06:07:18', 'The Green Spot', 'Carrer de la Reina Cristina 12, 08003 Barcelona', 'Cocina vegetal internacional en un espacio elegante.', 'https://www.encompaniadelobos.com/restaurantes/the-green-spot/', 'https://www.encompaniadelobos.com/wp-content/uploads/2023/greenspot.jpg', 'Barcelona', 0, '', 13, 'PENDIENTE', NULL, 0),
(6, '2025-10-31 06:07:18', 'Teresa Carles', 'Carrer de Jovellanos 2, 08001 Barcelona', 'Restaurante vegetariano con opciones veganas y sin gluten.', 'https://www.teresacarles.com', 'https://www.teresacarles.com/images/ensalada.jpg', 'Barcelona', 0, '', 13, 'PENDIENTE', NULL, 0),
(7, '2025-10-31 06:07:18', 'Flax & Kale', 'Carrer dels Tallers 74B, 08001 Barcelona', 'Healthy flexitarian food con muchas opciones veganas.', 'https://flaxandkale.com', 'https://flaxandkale.com/images/bowls.jpg', 'Barcelona', 0, '', 13, 'PENDIENTE', NULL, 0),
(8, '2025-10-31 06:07:18', 'El Vergel', 'Calle Mayor 10, 50001 Zaragoza', 'Restaurante vegano y ecológico con menú diario.', 'https://elvergelrestaurante.com', 'https://elvergelrestaurante.com/wp-content/uploads/2023/paella.jpg', 'Zaragoza', 0, '', 13, 'PENDIENTE', NULL, 0),
(9, '2025-10-31 06:07:18', 'La Tía Carlota', 'Calle Pintor López Mezquita 9, 18002 Granada', 'Cocina vegana tradicional con productos locales.', 'https://latiacarlota.com', 'https://latiacarlota.com/wp-content/uploads/2023/tapas.jpg', 'Granada', 0, '', 13, 'PENDIENTE', NULL, 0),
(10, '2025-10-31 06:07:18', 'El Berenjenal', 'Calle Rosario 15, 41001 Sevilla', 'Restaurante vegano con platos creativos y sin gluten.', 'https://elberenjenal.com', 'https://elberenjenal.com/images/berenjena.jpg', 'Sevilla', 0, '', 13, 'PENDIENTE', NULL, 0),
(11, '2025-10-31 06:07:18', 'Habanera Vegan', 'Calle de la Palma 63, 28015 Madrid', 'Cocina vegana con sabores latinos y caribeños.', 'https://habaneravegan.com', 'https://habaneravegan.com/wp-content/uploads/2023/tacos.jpg', 'Madrid', 0, '', 13, 'PENDIENTE', NULL, 0),
(12, '2025-10-31 06:07:18', 'La Oveja Negra Vegana', 'Calle Buenavista 42, 28012 Madrid', 'Comida vegana urbana y ambiente alternativo.', 'https://ovejanegrarestaurante.com', 'https://ovejanegrarestaurante.com/img/ramen.jpg', 'Madrid', 0, '', 13, 'PENDIENTE', NULL, 0),
(13, '2025-10-31 06:07:18', 'Viva Burger', 'Costanilla de San Andrés 16, 28005 Madrid', 'Hamburguesas veganas gourmet con vistas a La Latina.', 'https://vivaburger.es', 'https://vivaburger.es/wp-content/uploads/2023/burger-vegan.jpg', 'Madrid', 0, '', 13, 'PENDIENTE', NULL, 0),
(14, '2025-10-31 06:07:18', 'La Vegana Vallekas', 'Calle Sierra Carbonera 32, 28053 Madrid', 'Cocina vegana casera y sin gluten en Vallecas.', 'https://laveganavallekas.com', 'https://laveganavallekas.com/images/croquetas.jpg', 'Madrid', 0, '', 13, 'PENDIENTE', NULL, 0),
(15, '2025-10-31 06:07:18', 'Veganoteca', 'Calle Poeta Quintana 20, 03004 Alicante', 'Restaurante y tienda vegana con productos ecológicos.', 'https://veganoteca.es', 'https://veganoteca.es/img/ensalada.jpg', 'Alicante', 0, '', 13, 'PENDIENTE', NULL, 0),
(16, '2025-10-31 06:07:18', 'La Mandrágora', 'Calle San Vicente 45, Valencia', 'Restaurante vegano con opciones sin gluten y sin azúcar.', 'https://lamandragoravalencia.com', 'https://lamandragoravalencia.com/wp-content/uploads/2023/crepes.jpg', 'Valencia', 0, '', 13, 'PENDIENTE', NULL, 0),
(17, '2025-10-31 06:07:18', 'La Vegana de la Cerveza', 'Calle San Andrés 38, 15003 A Coruña', 'Tapas veganas y cervezas artesanas.', 'https://laveganadelacerveza.com', 'https://laveganadelacerveza.com/img/tapas.jpg', 'A Coruña', 0, '', 13, 'PENDIENTE', NULL, 0),
(18, '2025-10-31 06:07:18', 'El Fogón Verde', 'Calle Alameda 4, 28014 Madrid', 'Cocina vegana ecológica con productos de proximidad.', 'https://elfogonverde.es', 'https://elfogonverde.es/wp-content/uploads/2023/ensalada.jpg', 'Madrid', 0, '', 13, 'PENDIENTE', NULL, 0),
(19, '2025-10-31 06:07:18', 'Choose Ristorante Naturale', 'Calle de la Luna 3, 28004 Madrid', 'Restaurante italiano 100% vegano con platos sicilianos.', 'https://chooseristorante.com', 'https://chooseristorante.com/wp-content/uploads/2023/pasta.jpg', 'Madrid', 0, '', 13, 'PENDIENTE', NULL, 0),
(20, '2025-10-31 06:07:18', 'Mahalo Poké', 'Calle Ancha 10, 11001 Cádiz', 'Poké bowls veganos con ingredientes frescos.', 'https://mahalopoke.com', 'https://mahalopoke.com/images/bowl.jpg', 'Cádiz', 0, '', 13, 'PENDIENTE', NULL, 0),
(21, '2025-10-31 06:07:18', 'ECOrigen', 'Calle Ayala 27, Planta 2, 28001 Madrid', 'Restaurante ecológico y vegano con cocina de autor.', 'https://ecorigen.com', 'https://ecorigen.com/wp-content/uploads/2023/plato.jpg', 'Madrid', 0, '', 13, 'PENDIENTE', NULL, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

CREATE TABLE `rol` (
  `id_rol` int(255) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `rol`
--

INSERT INTO `rol` (`id_rol`, `nombre`) VALUES
(1, 'Administrador'),
(2, 'Editor'),
(3, 'Visitor');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tag_post`
--

CREATE TABLE `tag_post` (
  `id_tag_post` int(255) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `id_post` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tag_receta`
--

CREATE TABLE `tag_receta` (
  `id_tag` bigint(20) NOT NULL,
  `id_receta` int(255) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `tag_receta`
--

INSERT INTO `tag_receta` (`id_tag`, `id_receta`, `nombre`) VALUES
(1, 1, 'Pan'),
(2, 1, 'Trigo sarraceno'),
(3, 2, 'Postre'),
(4, 2, 'Almendra'),
(6, 3, 'Verduras'),
(7, 4, 'Desayuno'),
(8, 4, 'Avena'),
(9, 5, 'Desayuno'),
(10, 5, 'Arroz'),
(11, 6, 'Postre'),
(12, 6, 'Coco'),
(13, 7, 'Postre'),
(14, 7, 'Legumbres'),
(15, 8, 'Pan'),
(16, 8, 'Maíz'),
(17, 9, 'Postre'),
(18, 9, 'Zanahoria'),
(19, 10, 'Merienda'),
(20, 10, 'Plátano'),
(21, 11, 'Cena'),
(22, 11, 'Maíz'),
(23, 12, 'Entrante'),
(24, 12, 'Arroz'),
(25, 13, 'Entrante'),
(26, 13, 'Quinoa'),
(27, 14, 'Postre'),
(28, 14, 'Limón'),
(29, 15, 'Pan'),
(30, 15, 'Plátano'),
(31, 16, 'Postre'),
(32, 16, 'Chocolate'),
(33, 17, 'Plato principal'),
(34, 17, 'Tradicional'),
(35, 18, 'Plato principal'),
(36, 18, 'Curry'),
(37, 19, 'Entrante'),
(38, 19, 'Calabaza'),
(39, 20, 'Entrante'),
(40, 20, 'Lentejas'),
(41, 21, 'Postre'),
(42, 21, 'Manzana'),
(43, 22, 'Pan'),
(44, 22, 'Arroz integral'),
(45, 23, 'Cena'),
(46, 23, 'Boniato'),
(47, 24, 'Cena'),
(48, 24, 'Maíz'),
(49, 25, 'Plato principal'),
(50, 25, 'Verduras'),
(51, 26, 'Plato principal'),
(52, 26, 'Vegano'),
(53, 27, 'Postre'),
(54, 27, 'Chocolate'),
(55, 28, 'Pan'),
(56, 28, 'Semillas'),
(57, 29, 'Desayuno'),
(58, 29, 'Trigo sarraceno'),
(59, 30, 'Postre'),
(60, 30, 'Almendra'),
(61, 31, 'Postre'),
(62, 31, 'Yogur'),
(63, 32, 'Postre'),
(64, 32, 'Queso'),
(65, 33, 'Pan'),
(66, 33, 'Lino y chía'),
(67, 34, 'Desayuno'),
(68, 34, 'Plátano'),
(69, 35, 'Entrante'),
(70, 35, 'Zanahoria'),
(71, 36, 'Cena'),
(72, 36, 'Pizza'),
(73, 37, 'Postre'),
(74, 37, 'Pasas'),
(75, 38, 'Pan'),
(76, 38, 'Nueces'),
(77, 39, 'Postre'),
(78, 39, 'Calabaza'),
(79, 1, 'Delicioso'),
(80, 1, 'Saludable'),
(81, 1, 'Postre'),
(82, 40, 'Postre'),
(83, 18, 'pollo'),
(84, 3, 'saludable');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tag_restaurante`
--

CREATE TABLE `tag_restaurante` (
  `id_tag` bigint(20) NOT NULL,
  `id_restaurante` int(255) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `tag_restaurante`
--

INSERT INTO `tag_restaurante` (`id_tag`, `id_restaurante`, `nombre`) VALUES
(1, 1, 'Vegano'),
(2, 2, 'Vegano'),
(3, 3, 'Vegano'),
(4, 4, 'Vegano'),
(5, 5, 'Vegano'),
(6, 6, 'Vegano'),
(7, 7, 'Vegano'),
(8, 8, 'Vegano'),
(9, 9, 'Vegano'),
(10, 10, 'Vegano'),
(11, 11, 'Vegano'),
(12, 12, 'Vegano'),
(13, 13, 'Vegano'),
(14, 14, 'Vegano'),
(15, 15, 'Vegano'),
(16, 16, 'Vegano'),
(17, 17, 'Vegano'),
(18, 18, 'Vegano'),
(19, 19, 'Vegano'),
(20, 20, 'Vegano'),
(21, 1, 'Saludable'),
(22, 1, 'Curioso'),
(23, 1, 'Espacioso'),
(24, 1, 'Confortable');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_comentario`
--

CREATE TABLE `tipo_comentario` (
  `id_tipo_comentario` int(255) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `tema` enum('RESTAURANTE','RECETA','POST','PENDIENTE') NOT NULL DEFAULT 'PENDIENTE'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `tipo_comentario`
--

INSERT INTO `tipo_comentario` (`id_tipo_comentario`, `nombre`, `tema`) VALUES
(1, 'Valoración general', 'RESTAURANTE'),
(2, 'Opinión sobre el sabor', 'RECETA'),
(3, 'Opinión sobre el servicio', 'RESTAURANTE'),
(4, 'Opinión sobre el ambiente', 'RESTAURANTE'),
(5, 'Comentario sobre opciones sin gluten', 'RESTAURANTE'),
(6, 'Sugerencia de mejora', 'RESTAURANTE'),
(7, 'Recomendación de plato', 'RESTAURANTE'),
(8, 'Crítica constructiva', 'RESTAURANTE'),
(9, 'Experiencia personal', 'RESTAURANTE'),
(10, 'Comentario sobre precios', 'RESTAURANTE'),
(11, 'Dificultad de preparación', 'RECETA'),
(12, 'Tiempo real de cocinado', 'RECETA'),
(13, 'Sabor final del plato', 'RECETA'),
(14, 'Sustituciones recomendadas', 'RECETA'),
(15, 'Presentación del plato', 'RECETA'),
(16, 'Valor nutricional', 'RECETA'),
(17, 'Opinión sobre ingredientes', 'RECETA'),
(18, 'Resultado con variantes', 'RECETA'),
(19, 'Apto para niños', 'RECETA'),
(20, 'Comentario sobre alérgenos', 'POST'),
(21, 'Opinión sobre el contenido', 'POST'),
(22, 'Claridad del artículo', 'POST'),
(23, 'Utilidad de la información', 'POST'),
(24, 'Sugerencia de mejora', 'POST'),
(25, 'Corrección de errores', 'POST'),
(26, 'Aporte adicional', 'POST'),
(27, 'Valoración del autor', 'POST'),
(28, 'Interacción con otros lectores', 'POST'),
(29, 'Relevancia del tema', 'POST'),
(30, 'Estilo de redacción', 'POST');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id_usuario` int(255) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `id_rol` int(255) NOT NULL,
  `fecha_alta` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='tabla de usuarios';

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id_usuario`, `nombre`, `email`, `password`, `id_rol`, `fecha_alta`) VALUES
(1, 'Laura Sánchez', 'laura.sanchez@example.com', 'pass123laura', 3, '2025-11-03 14:07:33'),
(3, 'Marta Gómez', 'marta.gomez@example.com', 'pass123marta', 3, '2025-11-03 14:07:33'),
(4, 'Javier Torres', 'javier.torres@example.com', 'pass123javi', 3, '2025-11-03 14:07:33'),
(5, 'Lucía Fernández', 'lucia.fernandez@example.com', 'pass123lucia', 3, '2025-11-03 14:07:33'),
(6, 'Andrés Pérez', 'andres.perez@example.com', 'pass123andres', 3, '2025-11-03 14:07:33'),
(7, 'Sofía Morales', 'sofia.morales@example.com', 'pass123sofia', 3, '2025-11-03 14:07:33'),
(8, 'Diego Navarro', 'diego.navarro@example.com', 'pass123diego', 3, '2025-11-03 14:07:33'),
(9, 'Elena Castro', 'elena.castro@example.com', 'pass123elena', 3, '2025-11-03 14:07:33'),
(10, 'Raúl Romero', 'raul.romero@example.com', 'pass123raul', 3, '2025-11-03 14:07:33'),
(11, 'Nuria Martínez Rodrigo', 'nuria.martinezro@gmail.com', 'XXXX=', 1, '2025-11-03 14:07:33'),
(12, 'Susana Rico Vara', 'susanaricovara@gmail.com', 'XXXX', 1, '2025-11-03 14:07:33'),
(13, 'Maite Rodríguez Jurado', 'rodriguez.maite@gmail.com', 'XXXX=', 1, '2025-11-03 14:07:33');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `comentario`
--
ALTER TABLE `comentario`
  ADD PRIMARY KEY (`id_comentario`),
  ADD KEY `id_usuario` (`id_usuario`),
  ADD KEY `id_tipo_comentario` (`id_tipo_comentario`);

--
-- Indices de la tabla `favoritos`
--
ALTER TABLE `favoritos`
  ADD PRIMARY KEY (`id_favorito`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- Indices de la tabla `ingrediente`
--
ALTER TABLE `ingrediente`
  ADD PRIMARY KEY (`id_ingrediente`);

--
-- Indices de la tabla `paso_receta`
--
ALTER TABLE `paso_receta`
  ADD PRIMARY KEY (`id_paso`),
  ADD KEY `id_receta` (`id_receta`);

--
-- Indices de la tabla `post`
--
ALTER TABLE `post`
  ADD PRIMARY KEY (`id_post`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- Indices de la tabla `receta`
--
ALTER TABLE `receta`
  ADD PRIMARY KEY (`id_receta`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- Indices de la tabla `receta_ingrediente`
--
ALTER TABLE `receta_ingrediente`
  ADD PRIMARY KEY (`id_receta_ingrediente`),
  ADD KEY `id_ingrediente` (`id_ingrediente`),
  ADD KEY `id_receta` (`id_receta`);

--
-- Indices de la tabla `restaurante`
--
ALTER TABLE `restaurante`
  ADD PRIMARY KEY (`id_restaurante`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- Indices de la tabla `rol`
--
ALTER TABLE `rol`
  ADD PRIMARY KEY (`id_rol`),
  ADD UNIQUE KEY `nombre` (`nombre`);

--
-- Indices de la tabla `tag_post`
--
ALTER TABLE `tag_post`
  ADD PRIMARY KEY (`id_tag_post`),
  ADD KEY `id_post` (`id_post`);

--
-- Indices de la tabla `tag_receta`
--
ALTER TABLE `tag_receta`
  ADD PRIMARY KEY (`id_tag`),
  ADD KEY `fk_tagReceta_id_receta` (`id_receta`);

--
-- Indices de la tabla `tag_restaurante`
--
ALTER TABLE `tag_restaurante`
  ADD PRIMARY KEY (`id_tag`),
  ADD KEY `id_restaurante` (`id_restaurante`);

--
-- Indices de la tabla `tipo_comentario`
--
ALTER TABLE `tipo_comentario`
  ADD PRIMARY KEY (`id_tipo_comentario`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_usuario`),
  ADD UNIQUE KEY `uq_email_usuario` (`email`),
  ADD KEY `fk_usuario_id_rol` (`id_rol`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `comentario`
--
ALTER TABLE `comentario`
  MODIFY `id_comentario` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `favoritos`
--
ALTER TABLE `favoritos`
  MODIFY `id_favorito` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `ingrediente`
--
ALTER TABLE `ingrediente`
  MODIFY `id_ingrediente` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT de la tabla `paso_receta`
--
ALTER TABLE `paso_receta`
  MODIFY `id_paso` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=139;

--
-- AUTO_INCREMENT de la tabla `post`
--
ALTER TABLE `post`
  MODIFY `id_post` int(255) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `receta`
--
ALTER TABLE `receta`
  MODIFY `id_receta` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT de la tabla `receta_ingrediente`
--
ALTER TABLE `receta_ingrediente`
  MODIFY `id_receta_ingrediente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=71;

--
-- AUTO_INCREMENT de la tabla `restaurante`
--
ALTER TABLE `restaurante`
  MODIFY `id_restaurante` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT de la tabla `rol`
--
ALTER TABLE `rol`
  MODIFY `id_rol` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `tag_post`
--
ALTER TABLE `tag_post`
  MODIFY `id_tag_post` int(255) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tag_receta`
--
ALTER TABLE `tag_receta`
  MODIFY `id_tag` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=85;

--
-- AUTO_INCREMENT de la tabla `tag_restaurante`
--
ALTER TABLE `tag_restaurante`
  MODIFY `id_tag` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT de la tabla `tipo_comentario`
--
ALTER TABLE `tipo_comentario`
  MODIFY `id_tipo_comentario` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id_usuario` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `comentario`
--
ALTER TABLE `comentario`
  ADD CONSTRAINT `comentario_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `comentario_ibfk_2` FOREIGN KEY (`id_tipo_comentario`) REFERENCES `tipo_comentario` (`id_tipo_comentario`);

--
-- Filtros para la tabla `favoritos`
--
ALTER TABLE `favoritos`
  ADD CONSTRAINT `favoritos_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE CASCADE;

--
-- Filtros para la tabla `paso_receta`
--
ALTER TABLE `paso_receta`
  ADD CONSTRAINT `paso_receta_ibfk_1` FOREIGN KEY (`id_receta`) REFERENCES `receta` (`id_receta`);

--
-- Filtros para la tabla `post`
--
ALTER TABLE `post`
  ADD CONSTRAINT `post_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE CASCADE;

--
-- Filtros para la tabla `receta`
--
ALTER TABLE `receta`
  ADD CONSTRAINT `receta_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`);

--
-- Filtros para la tabla `receta_ingrediente`
--
ALTER TABLE `receta_ingrediente`
  ADD CONSTRAINT `receta_ingrediente_ibfk_1` FOREIGN KEY (`id_ingrediente`) REFERENCES `ingrediente` (`id_ingrediente`),
  ADD CONSTRAINT `receta_ingrediente_ibfk_2` FOREIGN KEY (`id_receta`) REFERENCES `receta` (`id_receta`) ON DELETE CASCADE;

--
-- Filtros para la tabla `restaurante`
--
ALTER TABLE `restaurante`
  ADD CONSTRAINT `restaurante_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`);

--
-- Filtros para la tabla `tag_post`
--
ALTER TABLE `tag_post`
  ADD CONSTRAINT `tag_post_ibfk_1` FOREIGN KEY (`id_post`) REFERENCES `post` (`id_post`);

--
-- Filtros para la tabla `tag_receta`
--
ALTER TABLE `tag_receta`
  ADD CONSTRAINT `fk_tagReceta_id_receta` FOREIGN KEY (`id_receta`) REFERENCES `receta` (`id_receta`) ON DELETE CASCADE;

--
-- Filtros para la tabla `tag_restaurante`
--
ALTER TABLE `tag_restaurante`
  ADD CONSTRAINT `tag_restaurante_ibfk_1` FOREIGN KEY (`id_restaurante`) REFERENCES `restaurante` (`id_restaurante`) ON DELETE CASCADE;

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `fk_usuario_id_rol` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id_rol`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
