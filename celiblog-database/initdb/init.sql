-- phpMyAdmin SQL Dump
-- version 5.2.3
-- https://www.phpmyadmin.net/
--
-- Servidor: celiblog_db:3306
-- Tiempo de generación: 11-12-2025 a las 11:55:50
-- Versión del servidor: 8.0.44
-- Versión de PHP: 8.3.26

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
  `id_comentario` int NOT NULL,
  `fecha_publicacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `titulo` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL,
  `subtitulo` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL,
  `contenido` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `comentario_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `id_usuario` int NOT NULL,
  `id_objeto_comentado` int NOT NULL,
  `objeto_comentado` enum('RECETA','RESTAURANTE','POST','PENDIENTE') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'PENDIENTE',
  `fecha_validacion` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `valoracion` int NOT NULL,
  `estado` enum('PENDIENTE','APROBADO','RECHAZADO') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT 'PENDIENTE'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `comentario`
--

INSERT INTO `comentario` (`id_comentario`, `fecha_publicacion`, `titulo`, `subtitulo`, `contenido`, `comentario_url`, `id_usuario`, `id_objeto_comentado`, `objeto_comentado`, `fecha_validacion`, `valoracion`, `estado`) VALUES
(32, '2025-11-18 20:43:04', '', '', '¡El Pan de trigo sarraceno (Receta 1) me quedó con un sabor a campo delicioso! Muy satisfecho.', NULL, 13, 1, 'RECETA', '2025-11-18 20:43:04', 5, 'PENDIENTE'),
(33, '2025-11-18 20:43:04', '', '', 'El Bizcocho de almendra (Receta 2) es suave y no tiene ese sabor a \"harina rara\". Un 10 en sabor.', NULL, 3, 2, 'RECETA', '2025-11-18 20:43:04', 5, 'PENDIENTE'),
(34, '2025-11-18 20:43:04', '', '', 'Sabor final excelente en la Tarta de zanahoria (Receta 9). No se nota que es sin gluten.', NULL, 5, 9, 'RECETA', '2025-11-18 20:43:04', 5, 'PENDIENTE'),
(35, '2025-11-18 20:43:04', '', '', 'La Tarta de limón (Receta 14) tiene un sabor cítrico muy vibrante. ¡Perfecto equilibrio!', NULL, 8, 14, 'RECETA', '2025-11-18 20:43:04', 5, 'PENDIENTE'),
(36, '2025-11-18 20:43:04', '', '', 'El sabor del Pollo al curry (Receta 18) quedó muy auténtico. Lo hice un poco más picante.', NULL, 1, 18, 'RECETA', '2025-11-18 20:43:04', 4, 'PENDIENTE'),
(37, '2025-11-18 20:43:04', '', '', 'La Dificultad de las Arepas (Receta 11) es media, no \"complicada\", si tienes práctica con masas.', NULL, 11, 11, 'RECETA', '2025-11-18 20:43:04', 3, 'PENDIENTE'),
(38, '2025-11-18 20:43:04', '', '', 'La Pizza de coliflor (Receta 3) es fácil, pero la preparación de la base toma su tiempo. Cuidado con el paso a paso.', NULL, 13, 3, 'RECETA', '2025-11-18 20:43:04', 4, 'PENDIENTE'),
(39, '2025-11-18 20:43:04', '', '', 'La Tarta de queso (Receta 32) es sorprendentemente fácil, incluso para principiantes. ¡El horneado fue clave!', NULL, 3, 32, 'RECETA', '2025-11-18 20:43:04', 5, 'PENDIENTE'),
(40, '2025-11-18 20:43:04', '', '', 'El Pan de arroz integral (Receta 22) sí es complicado. Me costó mucho conseguir la consistencia de la masa.', NULL, 5, 22, 'RECETA', '2025-11-18 20:43:04', 2, 'RECHAZADO'),
(41, '2025-11-18 20:43:04', '', '', 'Las Tortitas de avena (Receta 4) son las más rápidas que he hecho. Perfectas si no tienes tiempo.', NULL, 8, 4, 'RECETA', '2025-11-18 20:43:04', 5, 'PENDIENTE'),
(42, '2025-11-18 20:43:04', '', '', 'Sustituí el coco rallado por nueces en las Galletas de coco (Receta 6). Quedaron muy ricas, pero más densas.', NULL, 1, 6, 'RECETA', '2025-11-18 20:43:04', 4, 'PENDIENTE'),
(43, '2025-11-18 20:43:04', '', '', 'Recomiendo usar harina de garbanzos en lugar de arroz para los Crepes (Receta 5) si buscas un sabor más proteico.', NULL, 11, 5, 'RECETA', '2025-11-18 20:43:04', 4, 'PENDIENTE'),
(44, '2025-11-18 20:43:04', '', '', 'He usado edulcorante en vez de azúcar en los Muffins de plátano (Receta 10) y el resultado fue excelente.', NULL, 13, 10, 'RECETA', '2025-11-18 20:43:04', 5, 'PENDIENTE'),
(45, '2025-11-18 20:43:04', '', '', 'Para el Brownie de garbanzos (Receta 7), la gente puede usar judías negras si no tiene garbanzos cocidos. ¡Funciona!', NULL, 3, 7, 'RECETA', '2025-11-18 20:43:04', 5, 'PENDIENTE'),
(46, '2025-11-18 20:43:04', '', '', 'Sustituí las pasas por trocitos de manzana en las Galletas de avena y pasas (Receta 37). ¡Un acierto!', NULL, 5, 37, 'RECETA', '2025-11-18 20:43:04', 5, 'PENDIENTE'),
(47, '2025-11-18 20:43:04', '', '', 'La Presentación de las Croquetas de arroz (Receta 12) es buena, quedan muy bonitas si las rebozas bien.', NULL, 8, 12, 'RECETA', '2025-11-18 20:43:04', 4, 'PENDIENTE'),
(48, '2025-11-18 20:43:04', '', '', 'La Tarta de manzana (Receta 21) se ve muy rústica y casera, ideal para una reunión familiar.', NULL, 1, 21, 'RECETA', '2025-11-18 20:43:04', 5, 'PENDIENTE'),
(49, '2025-11-18 20:43:04', '', '', 'Las Berenjenas rellenas (Receta 25) quedan muy bien en el plato. Un toque de perejil fresco las realza.', NULL, 11, 25, 'RECETA', '2025-11-18 20:43:04', 4, 'PENDIENTE'),
(50, '2025-11-18 20:43:04', '', '', 'El Pan de semillas (Receta 28) tiene una presentación espectacular por el aspecto rústico de las semillas.', NULL, 13, 28, 'RECETA', '2025-11-18 20:43:04', 5, 'PENDIENTE'),
(51, '2025-11-18 20:43:04', '', '', 'Me gustó mucho cómo quedan los Tacos de maíz (Receta 24), muy coloridos con los toppings.', NULL, 3, 24, 'RECETA', '2025-11-18 20:43:04', 5, 'PENDIENTE'),
(52, '2025-11-18 20:43:04', '', '', 'El tiempo de cocinado de la Crema de calabaza (Receta 19) es menor si se usa olla a presión.', NULL, 5, 19, 'RECETA', '2025-11-18 20:43:04', 4, 'PENDIENTE'),
(53, '2025-11-18 20:43:04', '', '', 'El tiempo de reposo del Pan de plátano (Receta 15) es demasiado largo. Me llevó 20 minutos más.', NULL, 8, 15, 'RECETA', '2025-11-18 20:43:04', 3, 'PENDIENTE'),
(54, '2025-11-18 20:43:04', '', '', 'El Pollo al curry (Receta 18) se hace en 25 minutos, no en 45. Receta muy rápida.', NULL, 1, 18, 'RECETA', '2025-11-18 20:43:04', 5, 'PENDIENTE'),
(55, '2025-11-18 20:43:04', '', '', 'Las Empanadillas de boniato (Receta 23) tardaron un poco más en dorarse de lo que indica la receta.', NULL, 11, 23, 'RECETA', '2025-11-18 20:43:04', 4, 'PENDIENTE'),
(56, '2025-11-18 20:43:04', '', '', 'El Bizcocho de yogur (Receta 31) estuvo listo en el tiempo justo. Muy preciso el tiempo de horneado.', NULL, 13, 31, 'RECETA', '2025-11-18 20:43:04', 5, 'PENDIENTE'),
(57, '2025-11-18 20:43:04', '', '', 'El Valor nutricional de la Ensalada de quinoa (Receta 13) es muy alto. Una comida súper completa.', NULL, 3, 13, 'RECETA', '2025-11-18 20:43:04', 5, 'PENDIENTE'),
(58, '2025-11-18 20:43:04', '', '', 'Las Albóndigas de lentejas (Receta 26) son Aptas para niños y una forma genial de introducir legumbres.', NULL, 5, 26, 'RECETA', '2025-11-18 20:43:04', 5, 'PENDIENTE'),
(59, '2025-11-18 20:43:04', '', '', 'La Tarta de chocolate (Receta 27) no es muy apta para niños pequeños por el amargor del cacao puro.', NULL, 8, 27, 'RECETA', '2025-11-18 20:43:04', 3, 'PENDIENTE'),
(60, '2025-11-18 20:43:04', '', '', 'Apto para niños: el Pan de maíz (Receta 8) es ideal para sándwiches escolares.', NULL, 1, 8, 'RECETA', '2025-11-18 20:43:04', 5, 'PENDIENTE'),
(61, '2025-11-18 20:43:04', '', '', 'El Valor nutricional de las Tortitas de plátano (Receta 34) es excelente para antes de hacer deporte.', NULL, 11, 34, 'RECETA', '2025-11-18 20:43:04', 5, 'PENDIENTE'),
(62, '2025-11-18 20:43:04', '', '', '¡El Pan de trigo sarraceno (Receta 1) me quedó con un sabor a campo delicioso! Muy satisfecho.', NULL, 13, 1, 'RECETA', '2025-11-18 20:43:04', 5, 'PENDIENTE'),
(63, '2025-11-18 20:43:04', '', '', 'El Bizcocho de almendra (Receta 2) es suave y no tiene ese sabor a \"harina rara\". Un 10 en sabor.', NULL, 3, 2, 'RECETA', '2025-11-18 20:43:04', 5, 'PENDIENTE'),
(64, '2025-11-18 20:43:04', '', '', 'Sabor final excelente en la Tarta de zanahoria (Receta 9). No se nota que es sin gluten.', NULL, 5, 9, 'RECETA', '2025-11-18 20:43:04', 5, 'PENDIENTE'),
(65, '2025-11-18 20:43:04', '', '', 'La Tarta de limón (Receta 14) tiene un sabor cítrico muy vibrante. ¡Perfecto equilibrio!', NULL, 8, 14, 'RECETA', '2025-11-18 20:43:04', 5, 'PENDIENTE'),
(66, '2025-11-18 20:43:04', '', '', 'El sabor del Pollo al curry (Receta 18) quedó muy auténtico. Lo hice un poco más picante.', NULL, 1, 18, 'RECETA', '2025-11-18 20:43:04', 4, 'PENDIENTE'),
(67, '2025-11-18 20:43:04', '', '', 'La Dificultad de las Arepas (Receta 11) es media, no \"complicada\", si tienes práctica con masas.', NULL, 11, 11, 'RECETA', '2025-11-18 20:43:04', 3, 'PENDIENTE'),
(68, '2025-11-18 20:43:04', '', '', 'La Pizza de coliflor (Receta 3) es fácil, pero la preparación de la base toma su tiempo. Cuidado con el paso a paso.', NULL, 13, 3, 'RECETA', '2025-11-18 20:43:04', 4, 'PENDIENTE'),
(69, '2025-11-18 20:43:04', '', '', 'La Tarta de queso (Receta 32) es sorprendentemente fácil, incluso para principiantes. ¡El horneado fue clave!', NULL, 3, 32, 'RECETA', '2025-11-18 20:43:04', 5, 'PENDIENTE'),
(70, '2025-11-18 20:43:04', '', '', 'El Pan de arroz integral (Receta 22) sí es complicado. Me costó mucho conseguir la consistencia de la masa.', NULL, 5, 22, 'RECETA', '2025-11-18 20:43:04', 2, 'RECHAZADO'),
(71, '2025-11-18 20:43:04', '', '', 'Las Tortitas de avena (Receta 4) son las más rápidas que he hecho. Perfectas si no tienes tiempo.', NULL, 8, 4, 'RECETA', '2025-11-18 20:43:04', 5, 'PENDIENTE'),
(72, '2025-11-18 20:43:04', '', '', 'Sustituí el coco rallado por nueces en las Galletas de coco (Receta 6). Quedaron muy ricas, pero más densas.', NULL, 1, 6, 'RECETA', '2025-11-18 20:43:04', 4, 'PENDIENTE'),
(73, '2025-11-18 20:43:04', '', '', 'Recomiendo usar harina de garbanzos en lugar de arroz para los Crepes (Receta 5) si buscas un sabor más proteico.', NULL, 11, 5, 'RECETA', '2025-11-18 20:43:04', 4, 'PENDIENTE'),
(74, '2025-11-18 20:43:04', '', '', 'He usado edulcorante en vez de azúcar en los Muffins de plátano (Receta 10) y el resultado fue excelente.', NULL, 13, 10, 'RECETA', '2025-11-18 20:43:04', 5, 'PENDIENTE'),
(75, '2025-11-18 20:43:04', '', '', 'Para el Brownie de garbanzos (Receta 7), la gente puede usar judías negras si no tiene garbanzos cocidos. ¡Funciona!', NULL, 3, 7, 'RECETA', '2025-11-18 20:43:04', 5, 'PENDIENTE'),
(76, '2025-11-18 20:43:04', '', '', 'Sustituí las pasas por trocitos de manzana en las Galletas de avena y pasas (Receta 37). ¡Un acierto!', NULL, 5, 37, 'RECETA', '2025-11-18 20:43:04', 5, 'PENDIENTE'),
(77, '2025-11-18 20:43:04', '', '', 'La Presentación de las Croquetas de arroz (Receta 12) es buena, quedan muy bonitas si las rebozas bien.', NULL, 8, 12, 'RECETA', '2025-11-18 20:43:04', 4, 'PENDIENTE'),
(78, '2025-11-18 20:43:04', '', '', 'La Tarta de manzana (Receta 21) se ve muy rústica y casera, ideal para una reunión familiar.', NULL, 1, 21, 'RECETA', '2025-11-18 20:43:04', 5, 'PENDIENTE'),
(79, '2025-11-18 20:43:04', '', '', 'Las Berenjenas rellenas (Receta 25) quedan muy bien en el plato. Un toque de perejil fresco las realza.', NULL, 11, 25, 'RECETA', '2025-11-18 20:43:04', 4, 'PENDIENTE'),
(80, '2025-11-18 20:43:04', '', '', 'El Pan de semillas (Receta 28) tiene una presentación espectacular por el aspecto rústico de las semillas.', NULL, 13, 28, 'RECETA', '2025-11-18 20:43:04', 5, 'PENDIENTE'),
(81, '2025-11-18 20:43:04', '', '', 'Me gustó mucho cómo quedan los Tacos de maíz (Receta 24), muy coloridos con los toppings.', NULL, 3, 24, 'RECETA', '2025-11-18 20:43:04', 5, 'PENDIENTE'),
(82, '2025-11-18 20:43:04', '', '', 'El tiempo de cocinado de la Crema de calabaza (Receta 19) es menor si se usa olla a presión.', NULL, 5, 19, 'RECETA', '2025-11-18 20:43:04', 4, 'PENDIENTE'),
(83, '2025-11-18 20:43:04', '', '', 'El tiempo de reposo del Pan de plátano (Receta 15) es demasiado largo. Me llevó 20 minutos más.', NULL, 8, 15, 'RECETA', '2025-11-18 20:43:04', 3, 'PENDIENTE'),
(84, '2025-11-18 20:43:04', '', '', 'El Pollo al curry (Receta 18) se hace en 25 minutos, no en 45. Receta muy rápida.', NULL, 1, 18, 'RECETA', '2025-11-18 20:43:04', 5, 'PENDIENTE'),
(85, '2025-11-18 20:43:04', '', '', 'Las Empanadillas de boniato (Receta 23) tardaron un poco más en dorarse de lo que indica la receta.', NULL, 11, 23, 'RECETA', '2025-11-18 20:43:04', 4, 'PENDIENTE'),
(86, '2025-11-18 20:43:04', '', '', 'El Bizcocho de yogur (Receta 31) estuvo listo en el tiempo justo. Muy preciso el tiempo de horneado.', NULL, 13, 31, 'RECETA', '2025-11-18 20:43:04', 5, 'PENDIENTE'),
(87, '2025-11-18 20:43:04', '', '', 'El Valor nutricional de la Ensalada de quinoa (Receta 13) es muy alto. Una comida súper completa.', NULL, 3, 13, 'RECETA', '2025-11-18 20:43:04', 5, 'PENDIENTE'),
(88, '2025-11-18 20:43:04', '', '', 'Las Albóndigas de lentejas (Receta 26) son Aptas para niños y una forma genial de introducir legumbres.', NULL, 5, 26, 'RECETA', '2025-11-18 20:43:04', 5, 'PENDIENTE'),
(89, '2025-11-18 20:43:04', '', '', 'La Tarta de chocolate (Receta 27) no es muy apta para niños pequeños por el amargor del cacao puro.', NULL, 8, 27, 'RECETA', '2025-11-18 20:43:04', 3, 'PENDIENTE'),
(90, '2025-11-18 20:43:04', '', '', 'Apto para niños: el Pan de maíz (Receta 8) es ideal para sándwiches escolares.', NULL, 1, 8, 'RECETA', '2025-11-18 20:43:04', 5, 'PENDIENTE'),
(91, '2025-11-18 20:43:04', '', '', 'El Valor nutricional de las Tortitas de plátano (Receta 34) es excelente para antes de hacer deporte.', NULL, 11, 34, 'RECETA', '2025-11-18 20:43:04', 5, 'PENDIENTE');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `favoritos`
--

CREATE TABLE `favoritos` (
  `id_favorito` int NOT NULL,
  `id_usuario` int NOT NULL,
  `tipo_referencia` enum('RECETA','POST','RESTAURANTE','COMENTARIO') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `id_referencia` int NOT NULL COMMENT ' ID del restaurante, receta o post',
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `favoritos`
--

INSERT INTO `favoritos` (`id_favorito`, `id_usuario`, `tipo_referencia`, `id_referencia`, `fecha`) VALUES
(3, 1, 'RECETA', 1, '2025-10-29 08:45:43'),
(4, 1, 'RECETA', 2, '2025-10-29 08:46:02'),
(5, 1, 'RECETA', 2, '2025-10-29 08:46:53'),
(6, 1, 'RECETA', 3, '2025-10-29 08:50:11'),
(7, 1, 'RECETA', 4, '2025-10-29 08:51:55'),
(8, 1, 'RECETA', 3, '2025-10-29 08:59:20'),
(9, 13, 'POST', 1, '2025-11-17 17:13:47'),
(10, 11, 'POST', 4, '2025-11-17 17:13:47'),
(12, 5, 'POST', 15, '2025-11-17 17:13:47'),
(13, 8, 'POST', 11, '2025-11-17 17:13:47'),
(14, 1, 'POST', 22, '2025-11-17 17:13:47'),
(15, 4, 'POST', 20, '2025-11-17 17:13:47'),
(16, 13, 'RECETA', 3, '2025-11-17 17:13:47'),
(17, 11, 'RECETA', 5, '2025-11-17 17:13:47'),
(18, 3, 'RECETA', 14, '2025-11-17 17:13:47'),
(19, 6, 'RECETA', 25, '2025-11-17 17:13:47'),
(20, 10, 'RECETA', 19, '2025-11-17 17:13:47'),
(21, 15, 'RECETA', 8, '2025-11-17 17:13:47'),
(22, 16, 'RECETA', 37, '2025-11-17 17:13:47'),
(23, 13, 'RESTAURANTE', 1, '2025-11-17 17:13:47'),
(24, 11, 'RESTAURANTE', 6, '2025-11-17 17:13:47'),
(25, 3, 'RESTAURANTE', 13, '2025-11-17 17:13:47'),
(26, 5, 'RESTAURANTE', 40, '2025-11-17 17:13:47'),
(27, 8, 'RESTAURANTE', 31, '2025-11-17 17:13:47'),
(28, 1, 'RESTAURANTE', 22, '2025-11-17 17:13:47'),
(29, 25, 'RECETA', 1, '2025-12-04 09:05:44'),
(30, 25, 'RECETA', 2, '2025-12-04 09:11:31'),
(31, 25, 'POST', 15, '2025-12-06 09:05:49'),
(32, 25, 'POST', 15, '2025-12-06 09:05:49'),
(33, 25, 'RESTAURANTE', 15, '2025-12-06 09:06:03'),
(34, 25, 'RESTAURANTE', 15, '2025-12-06 09:06:03'),
(35, 25, 'RESTAURANTE', 1, '2025-12-06 09:07:48'),
(36, 25, 'RESTAURANTE', 1, '2025-12-06 09:07:48'),
(37, 36, 'RECETA', 1, '2025-12-08 08:55:00'),
(38, 36, 'RECETA', 2, '2025-12-08 08:55:00'),
(39, 36, 'RECETA', 3, '2025-12-08 08:55:00'),
(40, 36, 'RECETA', 4, '2025-12-08 08:55:00'),
(41, 36, 'RECETA', 5, '2025-12-08 08:55:00'),
(42, 36, 'RECETA', 6, '2025-12-08 08:55:00'),
(43, 36, 'RESTAURANTE', 1, '2025-12-08 08:55:00'),
(44, 36, 'RESTAURANTE', 6, '2025-12-08 08:55:00'),
(45, 36, 'RESTAURANTE', 13, '2025-12-08 08:55:00'),
(46, 36, 'RESTAURANTE', 22, '2025-12-08 08:55:00'),
(47, 36, 'RESTAURANTE', 31, '2025-12-08 08:55:00'),
(48, 36, 'RESTAURANTE', 40, '2025-12-08 08:55:00'),
(49, 36, 'POST', 1, '2025-12-08 08:55:00'),
(50, 36, 'POST', 2, '2025-12-08 08:55:00'),
(51, 36, 'POST', 3, '2025-12-08 08:55:00'),
(52, 36, 'POST', 4, '2025-12-08 08:55:00'),
(53, 36, 'POST', 5, '2025-12-08 08:55:00'),
(54, 36, 'POST', 6, '2025-12-08 08:55:00'),
(55, 25, 'RECETA', 15, '2025-12-11 07:42:01'),
(56, 25, 'RECETA', 15, '2025-12-11 07:42:01');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ingrediente`
--

CREATE TABLE `ingrediente` (
  `id_ingrediente` int NOT NULL,
  `nombre` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `unidad` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL
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
(25, 'Canela', 'cucharadita'),
(26, 'Harina de garbanzo', 'gramos'),
(27, 'Levadura nutricional', 'gramos'),
(28, 'Bebida de arroz', 'mililitros'),
(29, 'Semillas de sésamo', 'gramos'),
(30, 'Pimentón dulce', 'gramos'),
(31, 'Cúrcuma', 'gramos'),
(32, 'Jengibre fresco', 'gramos'),
(33, 'Leche de coco en lata', 'mililitros'),
(34, 'Tofu firme', 'gramos'),
(35, 'Seitán', 'gramos'),
(36, 'Caldo de verduras', 'mililitros'),
(37, 'Vinagre de manzana', 'mililitros'),
(38, 'Miel de maple', 'mililitros'),
(39, 'Salsa de soja sin gluten', 'mililitros'),
(40, 'Alga nori', 'unidad'),
(41, 'Champiñones', 'gramos'),
(42, 'Espinacas frescas', 'gramos'),
(43, 'Tomates secos', 'gramos'),
(44, 'Pistachos', 'gramos'),
(45, 'Vainilla en polvo', 'gramos'),
(46, 'Aceite de coco', 'mililitros'),
(47, 'Crema de cacahuete natural', 'gramos'),
(48, 'Dátiles', 'gramos'),
(49, 'Remolacha', 'gramos'),
(50, 'Puerro', 'unidad'),
(51, 'Guisantes', 'gramos'),
(52, 'Brócoli', 'gramos'),
(53, 'Huevo de lino', 'unidad'),
(54, 'Harina de tapioca', 'gramos'),
(55, 'Goma xantana', 'gramos'),
(56, 'Psyllium husk', 'gramos'),
(57, 'Coliflor', 'gramos'),
(58, 'Yuca (Mandioca)', 'gramos'),
(59, 'Patata dulce (Boniato)', 'gramos'),
(60, 'Harina de teff', 'gramos'),
(61, 'Jengibre en polvo', 'gramos'),
(62, 'Castañas de cajú (Anacardos)', 'gramos'),
(63, 'Semillas de linaza (Lino)', 'gramos'),
(64, 'Agar-agar', 'gramos'),
(65, 'Harina de trigo sarraceno', 'gramos'),
(66, 'Puré de calabaza', 'gramos'),
(67, 'Arroz Arborio', 'gramos'),
(68, 'Vino blanco (sin gluten)', 'mililitros'),
(69, 'Calabacín', 'unidad'),
(70, 'Hojas de albahaca fresca', 'gramos'),
(71, 'Aceite de trufa', 'mililitros'),
(72, 'Mango maduro', 'unidad'),
(73, 'Tapioca perla', 'gramos'),
(74, 'Cerezas frescas', 'gramos'),
(75, 'Aceite de coco virgen', 'mililitros'),
(76, 'Coco rallado', 'gramos'),
(77, 'Alga kombu', 'gramos'),
(78, 'Setas shiitake', 'gramos'),
(79, 'Edamame', 'gramos'),
(80, 'Salsa de pescado (sin gluten)', 'mililitros'),
(81, 'Leche evaporada', 'mililitros'),
(82, 'Leche condensada', 'mililitros'),
(83, 'Crema de leche (nata)', 'mililitros'),
(84, 'Huevos de codorniz', 'unidad'),
(85, 'Patata', 'unidad'),
(86, 'Harina de maíz precocida', 'gramos'),
(87, 'Garbanzos', 'gramos'),
(88, 'Harina de arroz', 'gramos'),
(89, 'Aguacate', 'unidad'),
(90, 'Frijoles', 'gramos'),
(91, 'Comino', 'gramos'),
(92, 'Harina de almendra', 'gramos'),
(93, 'Naranja', 'unidad'),
(94, 'Pescado blanco', 'gramos'),
(95, 'Limón', 'unidad'),
(96, 'Eneldo', 'gramos'),
(97, 'Cacao en polvo', 'gramos'),
(98, 'Lentejas rojas', 'gramos'),
(99, 'Queso', 'gramos'),
(100, 'Harina de quinoa', 'gramos'),
(101, 'Semillas de chía', 'gramos'),
(102, 'Carne picada', 'gramos'),
(103, 'Tomate triturado', 'gramos'),
(104, 'Salmón', 'gramos'),
(105, 'Pera', 'unidad'),
(106, 'Pollo', 'gramos'),
(107, 'Lechuga romana', 'unidad'),
(108, 'Harina de avena', 'gramos'),
(109, 'Nueces', 'gramos'),
(110, 'Plátano', 'unidad'),
(111, 'Canela en polvo', 'gramos'),
(112, 'Espárragos verdes', 'gramos'),
(113, 'Huevo', 'unidad'),
(114, 'Pan sin gluten', 'gramos'),
(115, 'Pimiento', 'unidad'),
(116, 'Manzana', 'unidad'),
(117, 'Bacalao', 'gramos'),
(118, 'Ajo', 'gramos'),
(119, 'Mozzarella fresca', 'gramos'),
(120, 'Queso crema', 'gramos'),
(121, 'Azúcar', 'gramos'),
(122, 'Miso', 'gramos'),
(124, 'Semilla chia', 'gramos');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `paso_receta`
--

CREATE TABLE `paso_receta` (
  `id_paso` bigint NOT NULL,
  `id_receta` int NOT NULL,
  `orden` int NOT NULL,
  `descripcion` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `paso_receta`
--

INSERT INTO `paso_receta` (`id_paso`, `id_receta`, `orden`, `descripcion`) VALUES
(1, 1, 1, 'Mezcla la harina de trigo sarraceno con sal y levadura en un bol grande.'),
(2, 1, 2, 'Agrega lentamente agua templada y aceite de oliva, mezcla hasta formar masa pegajosa.'),
(3, 1, 3, 'Amasa sobre superficie enharinada durante 10 minutos hasta conseguir textura homogénea.'),
(4, 1, 4, 'Deja reposar la masa tapada con un paño durante 1 hora hasta que doble su tamaño.'),
(5, 1, 5, 'Forma el pan y colócalo en un molde enharinado, realiza cortes superficiales en la masa.'),
(6, 11, 6, 'Hornea a 200°C durante 35-40 minutos hasta que la corteza esté dorada.'),
(7, 11, 7, 'Saca el pan del horno y deja enfriar sobre una rejilla al menos 30 minutos.'),
(8, 11, 8, 'Corta y sirve acompañado de mantequilla, mermelada o ingredientes al gusto.'),
(9, 12, 1, 'Precalienta el horno a 180°C y engrasa un molde con aceite o mantequilla.'),
(10, 12, 2, 'Mezcla harina de almendra, azúcar, polvo de hornear y sal en un bol.'),
(11, 12, 3, 'Bate los huevos y agrégalos junto con aceite y vainilla a los ingredientes secos.'),
(12, 13, 4, 'Mezcla hasta obtener una masa homogénea sin grumos.'),
(13, 13, 5, 'Vierte la masa en el molde y alisa la superficie con espátula.'),
(14, 13, 6, 'Hornea 25-30 minutos o hasta que al insertar palillo, salga limpio.'),
(15, 14, 7, 'Deja enfriar 10 minutos antes de desmoldar con cuidado.'),
(16, 14, 8, 'Sirve solo o acompañado de frutas, miel o crema al gusto.'),
(17, 14, 1, 'Precalienta el horno a 200°C y prepara bandeja con papel de hornear.'),
(18, 15, 2, 'Ralla la coliflor y cocina en microondas 5-6 minutos hasta blanda.'),
(19, 15, 3, 'Tritura la coliflor y mezcla con huevo, queso rallado y sal hasta formar masa.'),
(20, 15, 4, 'Extiende la masa en la bandeja con grosor uniforme.'),
(21, 16, 5, 'Hornea la base 15-20 minutos hasta dorar y estar firme.'),
(22, 16, 6, 'Añade salsa de tomate y toppings al gusto.'),
(23, 16, 7, 'Hornea 10-15 minutos más hasta que el queso se derrita.'),
(24, 17, 8, 'Saca la pizza y deja reposar 5 minutos antes de cortar.'),
(25, 17, 1, 'Mezcla avena molida, huevo, leche y sal hasta obtener masa líquida homogénea.'),
(26, 17, 2, 'Calienta sartén antiadherente a fuego medio y engrásala ligeramente.'),
(27, 18, 3, 'Vierte un cucharón de masa y cocina hasta que aparezcan burbujas.'),
(28, 18, 4, 'Voltea la tortita y cocina 1-2 minutos más.'),
(29, 18, 5, 'Repite con el resto de la masa.'),
(30, 19, 6, 'Sirve caliente acompañado de fruta, miel o sirope.'),
(31, 19, 7, 'Opcional: añade canela o esencia de vainilla a la masa.'),
(32, 19, 8, 'Disfruta como desayuno nutritivo o merienda saludable.'),
(33, 20, 1, 'Mezcla harina de arroz, huevos, leche y sal hasta obtener masa homogénea.'),
(34, 20, 2, 'Deja reposar la masa 10-15 minutos.'),
(35, 20, 3, 'Calienta sartén antiadherente y engrásala ligeramente.'),
(36, 21, 4, 'Vierte un cucharón de masa y extiéndela finamente.'),
(37, 21, 5, 'Cocina 1-2 minutos por cada lado hasta dorar ligeramente.'),
(38, 21, 6, 'Repite con el resto de la masa.'),
(39, 21, 7, 'Rellena con ingredientes dulces o salados al gusto.'),
(40, 21, 8, 'Enrolla o dobla los crepes y sirve caliente.'),
(41, 21, 1, 'Precalienta el horno a 180°C y cubre una bandeja con papel de hornear.'),
(42, 21, 2, 'Mezcla coco rallado, harina sin gluten, azúcar y una pizca de sal en un bol.'),
(43, 21, 3, 'Añade huevos y mantequilla derretida, mezcla hasta obtener masa homogénea.'),
(44, 23, 4, 'Forma pequeñas bolitas con la masa y colócalas en la bandeja separadas.'),
(45, 23, 5, 'Aplasta ligeramente cada bolita con un tenedor para dar forma de galleta.'),
(46, 23, 6, 'Hornea 12-15 minutos hasta que los bordes estén dorados.'),
(47, 23, 7, 'Deja enfriar sobre rejilla antes de manipularlas.'),
(48, 24, 8, 'Sirve y conserva en recipiente hermético para mantener la textura crujiente.'),
(49, 24, 1, 'Precalienta el horno a 180°C y engrasa un molde cuadrado.'),
(50, 24, 2, 'En un procesador de alimentos tritura garbanzos cocidos hasta obtener puré.'),
(51, 24, 3, 'Agrega cacao en polvo, azúcar, aceite y esencia de vainilla, mezcla bien.'),
(52, 25, 4, 'Incorpora huevos y bate hasta conseguir masa homogénea.'),
(53, 25, 5, 'Vierte la mezcla en el molde y alisa la superficie.'),
(54, 25, 6, 'Hornea durante 25-30 minutos hasta que al insertar un palillo salga limpio.'),
(55, 25, 7, 'Deja enfriar 10 minutos y desmolda con cuidado.'),
(56, 25, 8, 'Corta en cuadrados y sirve como postre saludable.'),
(57, 25, 1, 'Precalienta el horno a 190°C y engrasa un molde para pan.'),
(58, 25, 2, 'Mezcla harina de maíz, harina sin gluten, azúcar, sal y polvo de hornear.'),
(59, 25, 3, 'Bate huevos y agrégalos junto con leche y mantequilla derretida a los ingredientes secos.'),
(60, 25, 4, 'Mezcla hasta obtener masa homogénea y ligeramente espesa.'),
(61, 25, 5, 'Vierte la masa en el molde preparado y alisa la superficie.'),
(62, 25, 6, 'Hornea durante 30-35 minutos hasta que un palillo salga limpio.'),
(63, 25, 7, 'Deja enfriar sobre rejilla antes de cortar.'),
(64, 28, 8, 'Sirve solo o acompañado de mantequilla o mermelada.'),
(65, 28, 1, 'Precalienta el horno a 180°C y engrasa un molde para tarta.'),
(66, 28, 2, 'Mezcla harina sin gluten, azúcar, polvo de hornear, canela y sal.'),
(67, 29, 3, 'Ralla zanahorias y agrégalas a la mezcla junto con nueces picadas.'),
(68, 29, 4, 'Bate huevos y aceite, agrégalos y mezcla hasta obtener masa homogénea.'),
(69, 29, 5, 'Vierte la masa en el molde y alisa la superficie.'),
(70, 30, 6, 'Hornea 35-40 minutos o hasta que al insertar palillo salga limpio.'),
(71, 30, 7, 'Deja enfriar y desmolda con cuidado.'),
(72, 30, 8, 'Decora con crema de queso o espolvorea nuez molida antes de servir.'),
(73, 30, 1, 'Precalienta el horno a 180°C y coloca cápsulas en molde para muffins.'),
(74, 30, 2, 'Mezcla harina sin gluten, polvo de hornear, bicarbonato y sal.'),
(75, 30, 3, 'Machaca plátanos maduros y añade huevos, aceite y esencia de vainilla.'),
(76, 32, 4, 'Incorpora los ingredientes secos a la mezcla húmeda y mezcla suavemente.'),
(77, 32, 5, 'Vierte la masa en las cápsulas, llenando 2/3 de su capacidad.'),
(78, 32, 6, 'Hornea 20-25 minutos hasta que al insertar palillo salga limpio.'),
(79, 33, 7, 'Deja enfriar 10 minutos en el molde y luego traslada a rejilla.'),
(80, 33, 8, 'Sirve como desayuno o merienda saludable.'),
(81, 33, 1, 'Mezcla harina de maíz precocida con sal en un bol grande.'),
(82, 34, 2, 'Agrega agua poco a poco mientras amasas hasta obtener masa suave y manejable.'),
(83, 34, 3, 'Forma bolas del tamaño deseado y aplánalas hasta formar discos de 1-2 cm de grosor.'),
(84, 34, 4, 'Calienta sartén a fuego medio y engrásala ligeramente con aceite.'),
(85, 35, 5, 'Cocina las arepas 5-7 minutos por cada lado hasta que estén doradas.'),
(86, 35, 6, 'Opcional: hornea las arepas 10 minutos adicionales para un interior más firme.'),
(87, 35, 7, 'Abre las arepas con un cuchillo y rellena al gusto con queso, jamón o vegetales.'),
(88, 36, 8, 'Sirve calientes como desayuno, merienda o acompañamiento.'),
(89, 36, 1, 'Cocina arroz hasta que esté tierno y reserva.'),
(90, 36, 2, 'Pica finamente verduras al gusto y sofríelas en aceite hasta que estén tiernas.'),
(91, 37, 3, 'Mezcla arroz cocido con verduras, añade sal, pimienta y queso rallado si deseas.'),
(92, 37, 4, 'Forma pequeñas croquetas con la mezcla y pásalas por pan rallado sin gluten.'),
(93, 37, 5, 'Fríe en aceite caliente hasta dorar uniformemente o hornea a 200°C por 15-20 minutos.'),
(94, 38, 6, 'Escurre sobre papel absorbente y deja enfriar ligeramente.'),
(95, 38, 7, 'Sirve como aperitivo o acompañamiento de comidas principales.'),
(96, 38, 8, 'Opcional: acompaña con salsa de yogur o tomate.'),
(97, 38, 1, 'Enjuaga la quinoa bajo agua fría para eliminar el sabor amargo.'),
(98, 39, 2, 'Cocina la quinoa en agua con sal durante 12-15 minutos hasta que esté tierna.'),
(99, 39, 3, 'Escurre la quinoa y deja enfriar a temperatura ambiente.'),
(100, 39, 4, 'Corta tomate, pepino y aguacate en cubos y mezcla con la quinoa.'),
(101, 39, 5, 'Añade sal, pimienta, aceite de oliva y jugo de limón al gusto.'),
(102, 2, 6, 'Opcional: agrega hierbas frescas como cilantro o perejil picado.'),
(103, 2, 7, 'Mezcla suavemente para integrar todos los ingredientes sin aplastar el aguacate.'),
(104, 2, 8, 'Sirve fría como plato principal ligero o acompañamiento.'),
(105, 2, 1, 'Precalienta horno a 180°C y prepara molde para tarta con mantequilla y harina sin gluten.'),
(106, 2, 2, 'Mezcla harina de almendra, azúcar y mantequilla hasta formar migas.'),
(107, 3, 3, 'Presiona la mezcla en el molde para formar base uniforme.'),
(108, 3, 4, 'Hornea la base 12-15 minutos hasta dorar ligeramente.'),
(109, 3, 5, 'Mientras tanto, mezcla jugo de limón, huevos y azúcar para el relleno.'),
(110, 3, 6, 'Vierte el relleno sobre la base horneada y hornea 20 minutos más hasta cuajar.'),
(111, 3, 7, 'Deja enfriar a temperatura ambiente y luego refrigera al menos 2 horas.'),
(112, 4, 8, 'Decora con ralladura de limón antes de servir.'),
(113, 4, 1, 'Precalienta horno a 175°C y engrasa molde para pan.'),
(114, 4, 2, 'Machaca plátanos maduros y mezcla con huevos, aceite y esencia de vainilla.'),
(115, 5, 3, 'Incorpora harina sin gluten, polvo de hornear y canela, mezcla hasta integrar.'),
(116, 5, 4, 'Vierte la masa en el molde preparado y alisa la superficie.'),
(117, 5, 5, 'Hornea 50-60 minutos o hasta que al insertar palillo salga limpio.'),
(127, 9, 7, 'Hornea 12-15 minutos hasta que los bordes estén dorados.'),
(128, 9, 8, 'Deja enfriar sobre rejilla antes de servir.'),
(129, 9, 1, 'Pela y corta las patatas en rodajas finas.'),
(130, 10, 2, 'Fríe las patatas en aceite hasta que estén tiernas pero no doradas.'),
(131, 10, 3, 'Bate huevos en un bol y añade sal al gusto.'),
(132, 10, 4, 'Mezcla patatas con huevos batidos y deja reposar 5 minutos.'),
(133, 40, 5, 'Calienta sartén antiadherente con un poco de aceite y vierte la mezcla.'),
(134, 40, 6, 'Cocina a fuego medio-bajo, moviendo suavemente la sartén para evitar que se pegue.'),
(135, 40, 7, 'Cuando esté cuajada por debajo, da la vuelta con ayuda de un plato.'),
(136, 40, 8, 'Cocina unos minutos más hasta dorar por ambos lados y sirve caliente.'),
(137, 40, 1, 'Corta el pollo en cubos uniformes y salpimenta al gusto.'),
(138, 40, 2, 'Calienta aceite en sartén y sofríe cebolla y ajo picados.');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `post`
--

CREATE TABLE `post` (
  `id_post` int NOT NULL,
  `fecha_publicacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `titulo` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL,
  `subtitulo` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL,
  `contenido` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `id_usuario` int NOT NULL,
  `url_post` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `fecha_validacion` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `valoracion` int NOT NULL,
  `estado` enum('PENDIENTE','APROBADO','RECHAZADO') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT 'PENDIENTE'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `post`
--

INSERT INTO `post` (`id_post`, `fecha_publicacion`, `titulo`, `subtitulo`, `contenido`, `id_usuario`, `url_post`, `fecha_validacion`, `valoracion`, `estado`) VALUES
(1, '2025-12-07 06:11:27', 'Guía Completa para Empezar una Dieta Sin Gluten: Consejos Esenciales y Errores Comunes', 'Descubre el camino hacia el bienestar digestivo y la vitalidad. Aprende a identificar el gluten oculto, planificar tus comidas y evitar las trampas más frecuentes al adoptar este estilo de vida.', 'El cambio a una dieta sin gluten es mucho más que una simple moda; para millones de personas en todo el mundo, es una necesidad médica y una puerta hacia una mejor calidad de vida. Ya sea que estés abordando la enfermedad celíaca, una sensibilidad al gluten no celíaca, o simplemente buscando optimizar tu digestión, este cambio requiere conocimiento, planificación y paciencia. Esta guía de 1000 palabras está diseñada para ser tu mapa de ruta, cubriendo los consejos esenciales que te llevarán al éxito y, crucialmente, señalando los errores comunes que debes evitar.\r\n\r\nSección I: Los Primeros Pasos Esenciales\r\nAntes de vaciar tu despensa, es fundamental establecer una base sólida.\r\n\r\n1. Obtén un Diagnóstico y Asesoramiento Profesional\r\nEste es el paso más importante. Si sospechas que tienes un problema con el gluten, consulta a un médico o un gastroenterólogo antes de eliminarlo de tu dieta. Para diagnosticar correctamente la enfermedad celíaca o la sensibilidad al gluten, los médicos necesitan ver cómo reacciona tu cuerpo al ingerir gluten. Si lo eliminas antes de la prueba, los resultados pueden ser falsamente negativos. Una vez diagnosticado, busca la orientación de un dietista-nutricionista registrado con experiencia en dietas sin gluten. Ellos pueden asegurar que tu nueva dieta sea nutricionalmente completa y equilibrada.\r\n\r\n2. Limpia y Reorganiza tu Cocina\r\nLa contaminación cruzada es el enemigo número uno de la dieta sin gluten, especialmente para los celíacos.\r\n\r\nIdentifica Zonas de Riesgo: Tostadoras, coladores de pasta, tablas de cortar de madera y contenedores de plástico rayados pueden albergar restos de gluten. Lo ideal es tener equipos separados o limpiarlos a fondo.\r\n\r\nSeparación de Ingredientes: Designa estantes específicos para productos sin gluten. Guarda la harina de trigo, si se sigue usando en casa, en un contenedor bien sellado y lejos de los alimentos sin gluten.\r\n\r\n3. Aprende a Leer Etiquetas: El Juego de Detectives\r\nEl gluten se esconde en muchos lugares inesperados. La ley en muchos países exige que se declare el gluten, pero debes conocer sus otros nombres.\r\n\r\nBusca Alérgenos: Identifica la declaración \"Contiene trigo, centeno, cebada o avena\" (si no está certificada sin gluten).\r\n\r\nIngredientes Ocultos Comunes: La malta (cebada), el extracto de malta, la levadura de cerveza, los almidones modificados, algunas salsas de soja, aditivos y saborizantes artificiales.\r\n\r\nEl Sello de Certificación: Prioriza los productos que llevan la etiqueta de certificación \"Sin Gluten\" (a menudo un círculo o un logo reconocido), ya que cumplen con estándares estrictos de menos de 20 partes por millón (ppm) de gluten.\r\n\r\nSección II: Consejos para el Éxito a Largo Plazo\r\nUna dieta sin gluten no tiene por qué ser restrictiva o aburrida; debe ser nutricionalmente densa y sabrosa.\r\n\r\n1. Céntrate en Alimentos Naturalmente Sin Gluten\r\nEl error más grande es reemplazar todos los productos con gluten por versiones procesadas \"sin gluten\". Estos a menudo son altos en azúcares, grasas y calorías vacías, y bajos en fibra y vitaminas.\r\n\r\nBasa tu Dieta en lo Básico: Carnes magras, pescado, huevos, frutas, verduras, legumbres (frijoles, lentejas), frutos secos y semillas.\r\n\r\nGranos Seguros: Arroz (integral, blanco), maíz, quinoa, mijo, trigo sarraceno (alforfón), amaranto y avena certificada sin gluten. Estos son fuentes cruciales de fibra y micronutrientes.\r\n\r\n2. Planificación de Comidas y Preparación de Alimentos\r\nLa espontaneidad puede llevar a elecciones rápidas y arriesgadas.\r\n\r\nCocina en Casa: Controlar los ingredientes es la mejor manera de asegurar que tu comida esté libre de gluten. Prepara grandes cantidades de granos y proteínas al comienzo de la semana.\r\n\r\nMeriendas Inteligentes: Siempre lleva contigo snacks sin gluten (fruta, frutos secos, barras certificadas) para evitar caer en la tentación cuando tienes hambre y no encuentras opciones seguras.\r\n\r\n3. Mantente Hidratado y Prioriza la Fibra\r\nAl eliminar el trigo, muchas personas reducen inadvertidamente su ingesta de fibra, lo que puede provocar estreñimiento u otros problemas digestivos.\r\n\r\nAumenta la Fibra: Asegúrate de consumir muchas verduras, legumbres y cereales integrales sin gluten (quinoa, arroz integral).\r\n\r\nAgua: Una hidratación adecuada es esencial para ayudar a la fibra a hacer su trabajo de manera efectiva.\r\n\r\nSección III: Errores Comunes y Cómo Evitarlos\r\nEl camino sin gluten tiene sus trampas. Estar preparado puede ahorrarte malestares y frustraciones.\r\n\r\n1. Error: Confiar en el Gluten de la Avena\r\nLa avena es naturalmente sin gluten, pero casi siempre está contaminada por el proceso de cultivo, cosecha o molienda con trigo, centeno o cebada.\r\n\r\nSolución: Compra únicamente avena con la etiqueta \"Certificada Sin Gluten\". Esto indica que se cultivó y procesó en condiciones estrictas para evitar la contaminación cruzada.\r\n\r\n2. Error: Descuidar la Contaminación Cruzada en Restaurantes\r\nLa \"opción sin gluten\" en un menú no siempre significa que el plato sea seguro, especialmente si la cocina no tiene protocolos estrictos.\r\n\r\nSolución: Sé proactivo y pregunta. Pregunta específicamente sobre la preparación: \"¿Usan la misma freidora para las patatas y el pollo empanizado?\", \"¿La salsa lleva harina como espesante?\", \"¿Se usa una tabla o utensilios separados para el pan sin gluten?\". Si tienes dudas sobre la seguridad de la cocina, es mejor elegir una opción naturalmente simple y segura, como un corte de carne o pescado a la parrilla con verduras simples.\r\n\r\n3. Error: Olvidar los Artículos No Alimentarios\r\nEl gluten puede encontrarse en productos que entran en contacto con la boca o la piel.\r\n\r\nProductos a Revisar: Lápices labiales, bálsamos labiales, pasta de dientes, medicamentos y suplementos. Aunque la absorción a través de la piel es mínima, si se ingieren, pueden causar problemas. Prioriza versiones certificadas o pregunta a tu farmacéutico.\r\n\r\n4. Error: No Abordar las Deficiencias Nutricionales\r\nLos alimentos de trigo a menudo están enriquecidos con vitaminas del grupo B (tiamina, niacina, riboflavina, folato) y hierro. Al eliminarlos, puedes crear vacíos nutricionales.\r\n\r\nSolución: Trabaja con tu dietista para asegurar una ingesta adecuada. Considera fuentes ricas en folato (vegetales de hoja verde), hierro (carnes rojas, lentejas), y calcio y vitamina D. La suplementación puede ser necesaria, pero siempre bajo supervisión profesional.\r\n\r\nConclusión\r\nAdoptar una dieta sin gluten es un compromiso que ofrece grandes recompensas en términos de salud y bienestar. Aunque al principio puede parecer abrumador, al seguir estos consejos esenciales y ser consciente de los errores comunes, transformarás este desafío en un estilo de vida empoderador y delicioso. Recuerda que la clave es la educación, la planificación y una comunicación clara, ya sea con los profesionales de la salud, los camareros o tus anfitriones. Tu cuerpo te lo agradecerá.', 13, 'blog/guia-sin-gluten', '2025-12-07 06:11:27', 150, 'PENDIENTE'),
(3, '2025-12-04 04:39:07', 'Ruta Gastronómica Segura: Los 5 Mejores Restaurantes Veganos y Sin Gluten de Barcelona', 'Una guía esencial para explorar la Ciudad Condal sin restricciones, disfrutando de cocina plant-based innovadora, platos tradicionales y la máxima seguridad para celíacos.', '💚 Sabores de Barcelona: Los 5 Mejores Restaurantes Veganos y 100% Sin Gluten\r\nTítulo (11 palabras)\r\nRuta Gastronómica Segura: Los 5 Mejores Restaurantes Veganos y Sin Gluten de Barcelona\r\n\r\nSubtítulo (27 palabras)\r\nUna guía esencial para explorar la Ciudad Condal sin restricciones, disfrutando de cocina plant-based innovadora, platos tradicionales y la máxima seguridad para celíacos.\r\n\r\nEl Paraíso Vegano y Sin Gluten: Barcelona a Prueba de Alérgenos\r\nBarcelona se ha consolidado como una de las capitales gastronómicas más vibrantes del Mediterráneo, destacando no solo por su herencia culinaria, sino también por su vanguardia en dietas inclusivas. Para quienes combinan el estilo de vida vegano con la necesidad de evitar el gluten –ya sea por enfermedad celíaca o sensibilidad–, la ciudad ofrece un refugio de sabor, creatividad y, lo más importante, seguridad alimentaria. Ya no es necesario conformarse con una ensalada; Barcelona invita a disfrutar de croquetas, pizzas, paellas e incluso sushi, todo libre de sufrimiento animal y de trigo.\r\n\r\nEsta guía presenta los cinco templos culinarios que han dominado el arte de fusionar la ética plant-based con la rigurosidad del gluten-free, creando experiencias memorables para todos.\r\n\r\n1. Pötstot: El Santuario de la Cocina Inclusiva (100% Vegano y 100% Sin Gluten)\r\nPötstot, cuyo nombre en catalán (\"pots tot\") significa \"puedes todo\", es más que un restaurante: es una declaración de principios. Ubicado en el Eixample, este establecimiento ha conquistado a celíacos y veganos por igual al eliminar cualquier rastro de gluten, lactosa y productos de origen animal de su cocina, garantizando la ausencia total de contaminación cruzada. Su filosofía es la de la \"Welcome Food\", donde las etiquetas dietéticas desaparecen y lo único que importa es compartir la mesa sin limitaciones.\r\n\r\nEl Alma de su Cocina: Su propuesta se centra en reinterpretar el recetario tradicional catalán y mediterráneo, un nicho a menudo vedado para quienes siguen esta dieta. En Pötstot, los comensales pueden revivir platos caseros sin miedo.\r\n\r\nPlatos Estrella y Sabores:\r\n\r\nLas Croquetas: Ofrecen variedades como las de setas y trufa negra, o las de espinacas a la catalana, rebozadas en panko de guisante para lograr una textura crujiente y cremosa que hace olvidar la versión original.\r\n\r\nEl Canelón Trufado: Elaborado con harina de quinoa, relleno de setas de temporada y gratinado con almendra. Un plato sofisticado y reconfortante.\r\n\r\nLos Arroces: Imprescindibles, ya que todos se cocinan al momento. Destacan la Paella Mar y Montaña vegana, que sustituye los mariscos por algas y shiitake, y el cremoso Arroz Negro con arroz Venere y veganesa ajada.\r\n\r\nPostres: La carta incluye creaciones como el Chocolate en cuatro texturas, una experiencia que equilibra dulzor y riqueza sin lácteos.\r\n\r\nPötstot es la elección perfecta para quienes buscan seguridad absoluta y desean degustar los clásicos locales en una versión saludable y ética.\r\n\r\n2. Veganashi: Fusión Japonesa Creativa y sin Azúcar\r\nSi tu deseo es disfrutar del sushi sin preocuparte por el trigo, la contaminación o los ingredientes de origen animal, Veganashi, en el barrio de Poble Sec, es tu destino. Este restaurante se especializa en sushi plant-based con un enfoque en la alimentación consciente y natural. Además, tiene el mérito de ser uno de los pocos lugares que elabora su sushi sin azúcares añadidos.\r\n\r\nEl Alma de su Cocina: Veganashi rompe con la tradición japonesa para innovar con ingredientes frescos y locales, creando rolls vibrantes que utilizan arroz integral o arroz negro Venere.\r\n\r\nPlatos Estrella y Sabores:\r\n\r\nUramaki Tupipera: Un roll icónico que combina arroz negro venere, berenjena jugosa en salsa unagi, aguacate, chucrut y un crujiente de tupinambo. Es una explosión de sabor y texturas.\r\n\r\nNigiris de Degustación: Reemplazan el pescado con alternativas ingeniosas como el tomate marinado, las setas salteadas o el aguacate con cebolla caramelizada, demostrando que el sabor umami puede obtenerse 100% de las plantas.\r\n\r\nRolls en Tempura: Utilizan rebozados sin gluten para ofrecer la experiencia crujiente que muchos anhelan, manteniendo la esencia vegan y gluten-free.\r\n\r\nAmbiente: El local es acogedor y moderno, ideal para una cena íntima o una comida especial.\r\n\r\nVeganashi transforma un clásico internacional en un plato seguro y ético, reinventando el concepto del sushi para el siglo XXI.\r\n\r\n3. The Green Spot: Elegancia y Cocina Internacional Vegana (Opciones Sin Gluten)\r\nUbicado cerca de la Barceloneta, The Green Spot es conocido por su ambiente chic, su impresionante diseño interior y una cocina que demuestra que la comida vegetariana (con multitud de opciones veganas) puede ser sofisticada y global. Aunque no es 100% sin gluten, su carta está meticulosamente marcada, ofreciendo una amplia selección de platos seguros y de alta cocina. Es la elección ideal para una ocasión especial.\r\n\r\nEl Alma de su Cocina: Una vuelta al mundo culinaria, con influencias mexicanas, indias, mediterráneas y tailandesas, siempre con el foco en la verdura de calidad.\r\n\r\nPlatos Estrella y Sabores (SG/V):\r\n\r\nLa Pizza Vegana Sin Gluten: Utiliza harina de sarraceno para su masa y está coronada con queso de anacardos orgánicos y un \"chorizo\" de calabaza, ofreciendo una experiencia de pizza completa y segura.\r\n\r\nTacos de Jackfruit: Una alternativa vegana y sin gluten a la cochinita pibil, servidos con pico de gallo y aguacate.\r\n\r\nTempeh Orgánico a la Brasa: Un plato que combina tempeh casero con arroz basmati y ensalada de pepino, acompañado de una rica salsa de cacahuete, marcando una influencia asiática clara y profunda.\r\n\r\nChips de Kale y Yuca Brava: Perfectos como aperitivos, ambos son opciones naturalmente sin gluten y llenas de sabor.\r\n\r\nAl ser un local que maneja gluten, se recomienda siempre consultar con el personal sobre los protocolos de contaminación cruzada, especialmente si se padece celiaquía grave, pero su amplio menú marcado lo convierte en una opción fiable y de alto nivel.\r\n\r\n4. Flax & Kale: Flexitarianismo con Foco Saludable y Plant-Based\r\nFlax & Kale, con múltiples ubicaciones en Barcelona (como el de Carrer dels Tallers o el de Passatge de les Manufactures), se define como un restaurante flexiteriano, pero destaca por el 80% de su carta que es plant-based y su gran variedad de opciones crudas (raw), sin lácteos y, crucialmente, sin gluten. Es un espacio luminoso y moderno, perfecto para un brunch saludable o una comida revitalizante.\r\n\r\nEl Alma de su Cocina: Se enfoca en la comida funcional (Healthy & Happy Kitchen), utilizando \"superalimentos\" y técnicas de cocción que preservan los nutrientes. Utilizan harinas alternativas como las de arroz y quinoa.\r\n\r\nPlatos Estrella y Sabores (Veganos y Sin Gluten):\r\n\r\nRaw Vegan Lasagna: Una de sus creaciones más famosas. Hecha con láminas finas de calabacín, salsa de tomate fresco y un \"queso\" cremoso a base de anacardos y macadamia.\r\n\r\nDouble Smashed Burger: Utiliza un pan de harina de arroz y quinoa (brioche sin gluten) y una hamburguesa casera plant-based, servida con mayonesa trufada vegana.\r\n\r\nRamen Sin Gluten: Ofrecen opciones de ramen con fideos a base de trigo sarraceno o arroz, cocinados en un caldo concentrado de setas y algas.\r\n\r\nPostres: Su selección de tartas y postres, como la Chocolate & Hazelnut Mousse Cake (sin gluten y plant-based), es muy aplaudida por su equilibrio entre indulgencia y salud.\r\n\r\nFlax & Kale es ideal para aquellos que priorizan una dieta de alto valor nutritivo, con platos coloridos y una atmósfera juvenil y dinámica.\r\n\r\n5. En Ville: Cocina Catalana y Seguridad 100% Sin Gluten\r\nEn Ville, ubicado discretamente en el centro del Casco Antiguo (Ciutat Vella), se distingue por ser un restaurante 100% Sin Gluten, aunque su oferta no es exclusivamente vegana. Sin embargo, su compromiso con los alérgenos lo convierte en un destino principal para cualquier celíaco, y su menú incluye opciones veganas muy bien elaboradas que garantizan la máxima seguridad.\r\n\r\nEl Alma de su Cocina: Cocina mediterránea y catalana con un toque de fusión, manteniendo la autenticidad del sabor sin necesidad de gluten. El hecho de que toda la cocina esté libre de gluten elimina el riesgo de contaminación cruzada, un factor fundamental para la comunidad celíaca.\r\n\r\nPlatos Estrella y Opciones Veganas Seguras:\r\n\r\nBowl Vegano: Un plato completo y nutritivo que incluye tofu bio salteado, garbanzos al curry con vegetales, arroz integral, aguacate y boniato al horno.\r\n\r\nHamburguesa Vegan: Preparada con la hamburguesa Beyond Meat o similar, servida en un panecillo seguro con cebolla confitada, aguacate y un suave alioli de ajos asados.\r\n\r\nTapas (Opción Vegana): Las Patatas Bravas y el Hummus de zanahoria con pistachos son naturalmente sin gluten y pueden adaptarse fácilmente a la dieta vegana, sirviendo como excelentes entrantes para compartir.\r\n\r\nPostres: Pregunta por sus postres del día, ya que suelen tener opciones sin gluten que son o pueden ser adaptadas a veganas, como el cremoso de chocolate.\r\n\r\nEn Ville ofrece tranquilidad y sabor. Es el lugar ideal para cenar en un ambiente elegante, disfrutar de platos típicamente españoles y catalanes, y saber que cada ingrediente ha sido tratado con el máximo respeto a las sensibilidades alimentarias.\r\n\r\nConclusión: La Inclusión es el Sabor del Futuro\r\nBarcelona ha demostrado que las restricciones dietéticas no tienen por qué ser sinónimo de sacrificio. Los cinco restaurantes aquí destacados no solo ofrecen opciones veganas y sin gluten, sino que las elevan a una categoría de alta cocina creativa e inclusiva.\r\n\r\nDesde la fidelidad a la tradición catalana y la seguridad total de Pötstot y En Ville, pasando por la elegante fusión global de The Green Spot y la alta cocina saludable de Flax & Kale, hasta la reinvención del sushi en Veganashi, la Ciudad Condal garantiza una aventura gastronómica tan diversa como segura. Planifica tu ruta, disfruta de la libertad de elegir y prueba el sabor de un futuro culinario que abraza a todos los comensales.', 13, 'blog/veganos-barcelona', '2025-12-04 04:39:07', 210, 'PENDIENTE'),
(4, '2025-12-04 04:40:18', 'Trigo Sarraceno Desmitificado: Un Superalimento Sin Gluten y sus Sorprendentes Beneficios', 'Descubre por qué este \'pseudocereal\' es un tesoro nutricional y desmiente la creencia popular. Guía completa de sus propiedades, usos culinarios y la clave de su seguridad para celíacos.', 'Fagopyrum Esculentum: El Rey Falso del Cerealero y la Dieta Sin Gluten\r\nEl trigo sarraceno, conocido científicamente como Fagopyrum esculentum, es uno de los alimentos más malentendidos y subestimados del panorama nutricional. A pesar de su nombre, no tiene ninguna relación con el trigo común (Triticum aestivum) ni con la familia de las gramíneas, que incluye el trigo, el centeno y la cebada. Este hecho, a menudo pasado por alto, es la clave de su creciente popularidad, especialmente entre quienes buscan una dieta libre de gluten. Clasificado botánicamente como un pseudocereal, al igual que la quinoa y el amaranto, el sarraceno es una semilla de la familia del ruibarbo. Comprender su verdadera naturaleza es el primer paso para desmitificarlo y aprovechar su impresionante perfil nutricional.\r\n\r\nSección I: Mitos Comunes y sus Verdades Científicas\r\nLa confusión inherente a su nombre ha dado lugar a varios errores de concepto que es crucial aclarar.\r\n\r\nMito 1: El Trigo Sarraceno Contiene Gluten.\r\nVerdad: Totalmente falso. El sarraceno no es trigo y, por lo tanto, no contiene gluten. Es seguro para personas con enfermedad celíaca o sensibilidad al gluten no celíaca. El riesgo solo existe si el producto ha sido procesado en instalaciones que manejan trigo (contaminación cruzada), por lo que siempre se debe buscar el sello de \"Certificado Sin Gluten\". La proteína principal del sarraceno es la fagopirina, la cual no genera la reacción inmunológica asociada al gluten.\r\n\r\nMito 2: Es un Cereal Integral Más, Similar al Trigo Común.\r\nVerdad: Si bien se utiliza de forma similar a los cereales, su perfil nutricional es superior en muchos aspectos clave. El sarraceno contiene una calidad de proteína más alta que la mayoría de los cereales, ya que aporta todos los nueve aminoácidos esenciales, lo que lo convierte en una proteína completa, un rasgo poco común en el mundo vegetal (al igual que la quinoa). Además, es significativamente más rico en ciertos micronutrientes y antioxidantes.\r\n\r\nMito 3: Solo se Puede Usar para Hacer Gachas o Platos Rústicos.\r\nVerdad: Si bien el grano tostado (Kasha) es un plato tradicional de Europa del Este, el sarraceno es increíblemente versátil. Su harina es excelente para hacer crêpes (las famosas galettes de Bretaña), panes, noodles (soba japoneses, aunque a veces llevan una mezcla de harina de trigo), y productos de repostería. Su grano entero puede sustituir al arroz o la quinoa en ensaladas, guisos y como base para platos principales.\r\n\r\nMito 4: Su Sabor Intenso y Terroso Dificulta su Consumo.\r\nVerdad: El sabor del sarraceno varía mucho según su estado.\r\n\r\nGrano crudo/verde: Sabor muy suave, casi neutro, ideal para repostería o licuados.\r\n\r\nGrano tostado (Kasha): Adquiere un sabor intenso, terroso y a nuez, que a algunos puede parecer fuerte, pero es muy apreciado en platos salados y guisos de sabor potente. Basta con elegir la versión cruda si se desea un sabor más discreto.\r\n\r\nSección II: Las Verdades Nutricionales del Sarraceno\r\nLa verdadera maravilla del trigo sarraceno reside en su denso perfil nutricional, que lo convierte en un verdadero \"superalimento\".\r\n\r\n1. Alto Contenido de Proteínas Completas\r\nComo ya se mencionó, el sarraceno proporciona una fuente excepcional de proteínas vegetales de alto valor biológico, ya que contiene lisina, un aminoácido que suele ser limitante en otros cereales como el arroz. Esto lo hace esencial en dietas veganas y vegetarianas para asegurar una ingesta proteica completa.\r\n\r\n2. Potente Fuente de Antioxidantes\r\nEl sarraceno supera a muchos cereales en su contenido de compuestos bioactivos, destacando especialmente la Rutina.\r\n\r\nRutina: Es un flavonoide que actúa como un potente antioxidante y tiene propiedades antiinflamatorias. Es conocida por su capacidad para fortalecer los capilares y mejorar la circulación sanguínea, siendo muy beneficiosa para la salud cardiovascular y la prevención de venas varicosas.\r\n\r\nQuercetina: Otro antioxidante presente que contribuye a la salud celular y tiene efectos antihistamínicos naturales.\r\n\r\n3. Rico en Fibra Soluble e Insoluble\r\nAporta una cantidad significativa de fibra que es fundamental para la salud digestiva.\r\n\r\nSalud Intestinal: La fibra ayuda a regular el tránsito intestinal (combatiendo el estreñimiento) y actúa como un prebiótico, alimentando las bacterias beneficiosas del colon.\r\n\r\nControl del Azúcar: La fibra, especialmente la soluble, ayuda a ralentizar la absorción de la glucosa, lo que resulta en un índice glucémico más bajo que muchos otros cereales. Esto lo hace un alimento recomendado para personas con diabetes o para quienes buscan estabilizar sus niveles de azúcar en sangre.\r\n\r\n4. Minerales Clave para la Energía\r\nEs una fuente excelente de diversos minerales esenciales, a menudo deficitarios en las dietas modernas:\r\n\r\nMagnesio: Crucial para la función muscular y nerviosa, y vital para la producción de energía (ATP).\r\n\r\nManganeso: Importante para el metabolismo y la función antioxidante del cuerpo.\r\n\r\nHierro: Contribuye al transporte de oxígeno en la sangre.\r\n\r\nZinc: Esencial para la función inmunológica y la cicatrización.\r\n\r\nSección III: Consejos de Uso y Preparación\r\nIntegrar el trigo sarraceno en tu dieta es sencillo, pero requiere conocer los diferentes formatos en los que se encuentra.\r\n\r\n1. Trigo Sarraceno en Grano (Crudo/Verde)\r\nPreparación Básica: El sarraceno se cocina de forma similar al arroz o la quinoa. Se recomienda enjuagarlo antes de cocinarlo. Utiliza una proporción de 1 parte de sarraceno por 2 partes de agua o caldo. Cocinar a fuego lento hasta que el agua se absorba (aproximadamente 15-20 minutos). Quedará tierno pero ligeramente firme (al dente).\r\n\r\nActivado: Para mejorar su digestibilidad y absorción de nutrientes, se puede activar remojándolo durante 6-8 horas y luego enjuagándolo.\r\n\r\n2. Kasha (Grano Tostado)\r\nUso: Ideal para añadir textura y sabor a sopas, ensaladas y guisos. Por su intenso sabor a nuez, funciona muy bien como sustituto del arroz en platos fuertes o como base de un pilaf. Su tiempo de cocción es similar al del grano crudo.\r\n\r\n3. Harina de Trigo Sarraceno\r\nRepostería: La harina de sarraceno es densa y carece de la elasticidad que proporciona el gluten. Para hornear panes o bizcochos que requieren levantamiento, a menudo se combina con otras harinas sin gluten (como la de arroz o tapioca) o se utiliza con agentes aglutinantes como la goma xantana.\r\n\r\nPanqueques/Crêpes: Es la harina ideal para hacer tortitas y crepes ligeros y sabrosos sin necesidad de mezcla. La famosa galette bretona utiliza harina de sarraceno y agua, resultando en una base perfectamente adaptable a rellenos dulces o salados.\r\n\r\n4. Brotes de Sarraceno\r\nMicrogreens: Las semillas de sarraceno son fáciles de germinar. Los brotes y las hojas jóvenes son comestibles y se pueden añadir a ensaladas o sándwiches. Al germinar, el contenido de vitamina C y otros antioxidantes aumenta significativamente.\r\n\r\nConclusión: La Importancia de Mirar Más Allá del Nombre\r\nEl trigo sarraceno es un claro ejemplo de cómo una simple confusión de nombres puede oscurecer la verdad nutricional. No solo es un alimento seguro para la comunidad celíaca y para quienes evitan el gluten, sino que es un potente aliado para la salud general. Su combinación única de proteína completa, fibra de bajo índice glucémico y el elevado contenido de rutina lo posicionan muy por encima de muchos de los cereales más comunes.\r\n\r\nPara aprovecharlo al máximo, es fundamental ser consciente de los dos estados de grano (crudo/verde vs. Kasha/tostado) y recordar siempre priorizar los productos etiquetados como \"Certificado Sin Gluten\" para garantizar la seguridad total. Al incorporar el sarraceno en la dieta, no solo estás eligiendo una alternativa libre de alérgenos, sino que estás invirtiendo en un alimento con una densidad nutricional superior.', 11, 'blog/mitos-trigo-sarraceno', '2025-12-04 04:40:18', 88, 'PENDIENTE'),
(5, '2025-12-04 04:43:30', 'Guía de Detección: Cómo Prevenir y Eliminar la Contaminación Cruzada en tu Cocina', 'Aprende a identificar las fuentes invisibles de alérgenos y gluten, desde utensilios hasta superficies. Estrategias prácticas para crear un entorno de cocina seguro y saludable en casa.', 'El Enemigo Invisible: La Contaminación Cruzada y la Seguridad Alimentaria\r\nLa contaminación cruzada se produce cuando alérgenos o patógenos se transfieren de un alimento o superficie a otro que, de otro modo, sería seguro. En el contexto de las dietas restrictivas, como la dieta sin gluten o aquellas por alergias graves (maní, lácteos, huevo), esta transferencia, aunque sea en cantidades minúsculas, puede ser suficiente para desencadenar síntomas severos, daños intestinales (en el caso de la celiaquía) o incluso una reacción anafiláctica. En casa, donde a menudo conviven personas con diferentes necesidades dietéticas, saber cómo detectar y, más importante aún, cómo prevenir la contaminación cruzada es la piedra angular de una cocina segura y saludable.\r\n\r\nEsta guía profundiza en los puntos críticos de riesgo, las técnicas de detección y las estrategias de prevención para blindar tu espacio culinario.\r\n\r\nSección I: Los Puntos Críticos de Riesgo y Detección\r\nPara detectar la contaminación cruzada, debemos entender dónde se esconde. A menudo, el riesgo no está en el ingrediente principal, sino en la herramienta o el proceso utilizado.\r\n\r\n1. Utensilios y Herramientas Porosas\r\nLos utensilios que tienen rasguños o están hechos de materiales porosos son trampas perfectas para partículas de alérgenos.\r\n\r\nDetección de Riesgo:\r\n\r\nTablas de Cortar de Madera o Plástico Rayado: La madera es porosa, y las hendiduras profundas en el plástico pueden atrapar restos de gluten o alérgenos. Una tabla rayada o manchada que se ha usado para cortar pan de trigo, por ejemplo, es un riesgo.\r\n\r\nUtensilios de Plástico o Silicona Vieja: Las espátulas o cucharas de plástico que están muy usadas y han perdido su brillo pueden haber absorbido residuos.\r\n\r\nColadores y Escurridores: La malla fina de los coladores puede retener fácilmente el polvo de la harina de trigo o los restos de pasta con gluten, incluso después de un lavado superficial.\r\n\r\nSolución Práctica: Designa utensilios exclusivos (con un color o etiqueta diferente) para la preparación de alimentos libres de alérgenos. Desecha inmediatamente las tablas de madera muy rayadas y opta por acero inoxidable o plástico liso y nuevo para las herramientas exclusivas.\r\n\r\n2. Electrodomésticos y Pequeños Aparatos\r\nLos aparatos que producen calor o que se utilizan para procesar granos o migas representan un peligro extremo.\r\n\r\nDetección de Riesgo:\r\n\r\nLa Tostadora: Este es el contaminante cruzado número uno para el gluten. Las migas de pan con gluten caen al fondo y el calor las aerosoliza, contaminando el pan sin gluten al tostarlo.\r\n\r\nBatidoras y Procesadores de Alimentos: Si se usan para moler granos (como avena o trigo) y luego se utilizan sin una limpieza exhaustiva para hacer una salsa, los restos de polvo fino se transfieren.\r\n\r\nSolución Práctica: La tostadora debe ser exclusiva para el pan sin gluten, o se deben usar bolsas tostadoras especiales dentro de la tostadora común. En cuanto a procesadores, si es posible, ten una jarra o cuchillas exclusivas.\r\n\r\n3. Superficies y Manipulación\r\nLas encimeras y las manos son vehículos de transferencia constantes.\r\n\r\nDetección de Riesgo:\r\n\r\nSuperficies de Trabajo: Si se amasa pan con gluten en una encimera y luego se limpia solo con un paño húmedo, los residuos de proteína pueden permanecer.\r\n\r\nEsponjas y Paños de Cocina: Una esponja que se usa para limpiar un plato con residuos de un alérgeno (como mantequilla de maní o una salsa con gluten) y luego se usa para limpiar utensilios \"seguros\" es un vector de contaminación.\r\n\r\nSolución Práctica: Limpieza en dos etapas: Usa detergente y agua caliente, seguido de un desinfectante o una solución de vinagre/agua para las superficies. Utiliza paños o toallas de papel exclusivos para limpiar el área de preparación de alimentos seguros.\r\n\r\nSección II: Estrategias de Prevención y Protocolos de Seguridad\r\nLa prevención requiere establecer protocolos claros y una jerarquía en la preparación de alimentos.\r\n\r\n1. Jerarquía de Preparación (El Principio \"Primero lo Seguro\")\r\nCuando en una cocina se preparan simultáneamente alimentos con y sin alérgenos, siempre se debe priorizar.\r\n\r\nRegla de Oro: Prepara primero el alimento sin alérgenos. Esto asegura que las manos, superficies y utensilios estén en su estado más limpio. Una vez que el alimento seguro está listo y guardado, puedes pasar a preparar los alimentos que contienen el alérgeno.\r\n\r\nLavado de Manos: Lávate las manos (y cambia de guantes, si los usas) antes de comenzar la preparación del plato seguro, y cada vez que toques un ingrediente o superficie de riesgo.\r\n\r\n2. Organización de la Despensa y el Frigorífico\r\nIncluso el almacenamiento puede ser una fuente de contaminación.\r\n\r\nAlmacenamiento Vertical: En el frigorífico, guarda los alimentos sin alérgenos (especialmente harinas, quesos o dips) en estantes superiores. Los alimentos con alérgenos deben ir en estantes inferiores. Esto evita que los derrames o goteos de un alimento contaminen el alimento seguro que está debajo.\r\n\r\nContenedores Herméticos: Asegúrate de que todos los ingredientes a granel (harinas, granos, pastas) estén guardados en contenedores cerrados e identificados claramente. Nunca uses el mismo medidor para sacar harina de trigo y harina sin gluten.\r\n\r\n3. Uso de Ingredientes Comunes: Mantequilla, Mermeladas y Salsas\r\nLos contenedores de alimentos que se utilizan repetidamente son un gran foco de contaminación por contacto directo.\r\n\r\nLa Trampa del Cuchillo Común: Si usas un cuchillo para untar mantequilla en un pan de trigo y luego ese mismo cuchillo vuelve al recipiente de mantequilla, has contaminado todo el recipiente.\r\n\r\nSolución: Utiliza contenedores individuales o mantequilleras separadas (una para el pan con alérgenos, otra para el pan seguro). O, mejor aún, usa una cuchara limpia para transferir una porción del alimento (mantequilla, mermelada) a un plato individual antes de usarla con cualquier utensilio.\r\n\r\n4. La Regla del \"Lavar Dos Veces\" (Limpieza Exhaustiva)\r\nLa detección se complementa con una limpieza rigurosa. Para la celiaquía o alergias graves, la limpieza debe ser metódica.\r\n\r\nSuperficies: Usa agua caliente con jabón, limpia el área y luego vuelve a limpiar con toallas de papel desechables.\r\n\r\nUtensilios: Lava primero los utensilios de riesgo (los que se usaron con alérgenos) por separado, y luego los utensilios seguros. Lo ideal es usar un lavavajillas de alta temperatura.\r\n\r\nConclusión: La Inversión en Seguridad\r\nDetectar y prevenir la contaminación cruzada no es una tarea excesivamente compleja, sino un cambio en la mentalidad y en los hábitos de la cocina. Se trata de ser consciente de los vehículos de transferencia y de establecer barreras físicas (utensilios exclusivos, almacenamiento separado) y protocolarias (la jerarquía de la preparación, la limpieza metódica).\r\n\r\nLa inversión en una tostadora exclusiva, un set de utensilios de un color distintivo o simplemente en toallas de papel desechables para la limpieza de superficies, es un pequeño precio a pagar por la tranquilidad y la salud de los miembros del hogar que requieren una dieta segura. Al aplicar estas estrategias, transformas tu cocina de un potencial campo de minas en un espacio de confianza, haciendo que la vida con restricciones dietéticas sea mucho más fácil y placentera.', 11, 'blog/contaminacion-cruzada', '2025-12-04 04:43:30', 130, 'PENDIENTE'),
(7, '2025-12-04 04:46:23', 'Goma Xantana: El Secreto de la Estructura y Elasticidad del Pan Sin Gluten', 'El Vínculo Elástico: Análisis del Impacto de la Goma Xantana en la Estructura y Calidad del Pan Casero', 'La repostería y la panadería sin gluten presentan uno de los mayores desafíos técnicos para los panaderos caseros: la ausencia de la red proteica que el gluten proporciona. El gluten no es solo un aglutinante; es la estructura, la elasticidad, la capacidad de retención de gas y la textura final del pan. Cuando se eliminan harinas como el trigo, el centeno o la cebada, el resultado inicial suele ser una miga densa, quebradiza y que se desmorona fácilmente. Aquí es donde interviene la goma xantana, un aditivo alimentario clasificado como polisacárido, producido por la fermentación de azúcares por la bacteria Xanthomonas campestris. Aunque su nombre pueda sonar industrial, su función es fundamentalmente biomimética: actúa como un sustituto del gluten, reestructurando la masa y transformando la panificación sin gluten.El impacto primario de la goma xantana se observa en la rheología de la masa, es decir, cómo se comporta la masa cuando se le aplica fuerza. Las harinas sin gluten (arroz, maíz, mijo, etc.) carecen de visco-elasticidad, por lo que una masa hecha solo con ellas y agua sería rígida y no retendría las burbujas de dióxido de carbono creadas por la levadura. Al añadir goma xantana, esta, al hidratarse, crea una solución de alta viscosidad. Esta solución polimérica forma una red tridimensional que imita la estructura del gluten. Esta red tiene dos efectos cruciales en la fase de fermentación:Primero, mejora la capacidad de retención de gas. Cuando la levadura consume azúcares y produce $\\text{CO}_2$, la red de xantana atrapa eficazmente estas burbujas dentro de la masa, permitiendo que esta se hinche y logre un volumen significativamente mayor. Sin la xantana, el gas escaparía y el pan colapsaría, resultando en una miga pesada y baja. Segundo, aporta la elasticidad necesaria. Esta elasticidad es lo que permite que la masa se estire sin romperse durante el amasado y, crucialmente, durante la expansión en el horno (el oven spring). La xantana proporciona cohesión y un grado de \'pegajosidad\' que une los gránulos de almidón y otras harinas sin gluten.El impacto se extiende a las propiedades del pan cocido. En la textura de la miga, la xantana influye directamente en la suavidad y el tamaño de los alvéolos. La miga de un pan sin gluten con xantana es notablemente más suave, menos granulada y con una estructura de poros más abierta que su contraparte sin el aditivo. La dosis es crítica: una cantidad insuficiente resultará en un pan seco y desmoronable; un exceso, en un pan gomoso, excesivamente húmedo y con una textura desagradable, casi gelatinosa. La proporción típica varía, pero generalmente se sitúa entre $0.5\\%$ y $1\\%$ del peso total de la harina.Otro beneficio clave es su influencia en la vida útil y frescura del pan. La goma xantana, al ser un hidrocoloide altamente eficaz, tiene una excelente capacidad para retener agua. Esta retención de humedad ralentiza el proceso de retrogradación del almidón, que es el principal mecanismo de endurecimiento y envejecimiento del pan (staling). En la panificación sin gluten, donde los almidones puros tienden a endurecerse rápidamente, la adición de xantana mantiene el pan tierno y fresco por períodos considerablemente más largos, mejorando la aceptabilidad y reduciendo el desperdicio en la panadería casera.Finalmente, la facilidad de manipulación de la masa mejora drásticamente. Las masas de pan sin gluten a menudo se asemejan a una papilla espesa o a una mezcla de galletas antes de la adición de la xantana. Una vez incorporada, el polisacárido otorga a la masa una consistencia más cercana a la de la masa de trigo, permitiendo que sea amasada, moldeada (aunque con menos firmeza) e incluso doblada, facilitando técnicas que son imposibles de realizar con mezclas de harinas sin gluten puras. Por lo tanto, el uso de goma xantana en el pan casero sin gluten trasciende la mera corrección de una deficiencia; es el elemento que permite la viabilidad, la calidad estructural y la experiencia sensorial esperada de un pan bien horneado.', 13, 'blog/goma-xantana-pan', '2025-12-04 04:46:23', 50, 'PENDIENTE'),
(8, '2025-12-04 04:54:53', 'Los 10 Mejores Panes de Molde Sin Gluten: Análisis Completo de Sabor y Textura', 'Una evaluación rigurosa de las opciones más populares en supermercados, comparando textura, tostado, valor nutricional y la relación calidad-precio para celíacos y sensibles al gluten.', 'Más Allá del Almidón: La Búsqueda del Pan de Molde Sin Gluten Perfecto\r\nEn la vida de una persona que sigue una dieta sin gluten, ya sea por celiaquía o sensibilidad, el pan de molde es un elemento básico. Sin embargo, encontrar una opción que replique la esponjosidad, el sabor neutro y la capacidad de tostado del pan de trigo es una tarea compleja. La panadería sin gluten se basa en una intrincada mezcla de almidones, harinas alternativas e hidrocoloides (como la goma xantana) para simular la estructura del gluten, lo que a menudo resulta en un producto seco, insípido o con una textura gomosa.\r\n\r\nAfortunadamente, el mercado español ha madurado, ofreciendo una amplia gama de panes de molde disponibles en grandes superficies. Este análisis se centra en los 10 productos más destacados y accesibles, evaluando su desempeño en los aspectos más críticos: Textura, Sabor, Tostado, Ingredientes y Relación Calidad-Precio (RC-P).\r\n\r\nEl Top 10: Panes de Molde Sin Gluten Bajo la Lupa\r\n1. Schär Pan de Molde Clásico (El Referente)\r\nTextura y Miga: Es el benchmark por excelencia. Suave, esponjosa y con un tamaño de rebanada considerable. No se desmorona fácilmente.\r\n\r\nSabor: Neutro y ligeramente dulce, haciéndolo ideal tanto para sándwiches salados como para desayunos dulces.\r\n\r\nTostado: Mejora notablemente al tostarse, logrando una corteza crujiente sin volverse quebradizo en el interior.\r\n\r\nIngredientes Clave: Base de almidón de maíz y masa madre.\r\n\r\nRC-P: Media-Alta. Es de los más caros, pero la calidad constante justifica el precio para muchos consumidores.\r\n\r\n2. Hacendado Pan de Molde Sin Gluten (El Imbatible en Precio)\r\nTextura y Miga: Ha mejorado con los años. Es más denso que Schär, pero más grande y con buena cohesión, lo que lo hace ideal para bocadillos robustos.\r\n\r\nSabor: Generalmente neutro. Algunas formulaciones recientes incorporan harina de arroz o sarraceno, lo que le confiere un sabor más característico y menos artificial.\r\n\r\nTostado: Aceptable. Tiende a endurecerse si se tuesta demasiado.\r\n\r\nIngredientes Clave: Harina de arroz, almidón de maíz y aditivos básicos.\r\n\r\nRC-P: Excelente. Su disponibilidad en Mercadona y su bajo precio lo convierten en la opción más accesible para el consumo diario.\r\n\r\n3. Schär Cereale del Mastro Panettiere (El de la Miga Oscura)\r\nTextura y Miga: Más compacto y húmedo que la versión clásica, gracias a su alto contenido de granos y semillas. Miga más oscura y rústica.\r\n\r\nSabor: Intenso, con notas a nuez y un ligero regusto a masa madre (de harina de arroz). Las semillas (lino, girasol) aportan un valor añadido.\r\n\r\nTostado: Funciona excelentemente en tostadas; las semillas se caramelizan ligeramente, potenciando el sabor.\r\n\r\nIngredientes Clave: Harina de sarraceno y de mijo, masa madre, fibra de psyllium.\r\n\r\nRC-P: Alta. Es la elección premium para quienes buscan pan de molde con mejor perfil nutricional sin renunciar al sabor.\r\n\r\n4. Proceli Pan de Molde Rústico (La Textura de Pan de Pueblo)\r\nTextura y Miga: Una miga más gruesa y abierta, con la irregularidad propia de un pan casero. Es tierno y ligeramente menos uniforme que las grandes marcas industriales.\r\n\r\nSabor: Un sabor más cercano al pan tradicional, con un ligero toque ácido propio de su proceso de fermentación.\r\n\r\nTostado: Muy buen desempeño, logra una costra dorada y crujiente manteniendo la esponjosidad interior.\r\n\r\nIngredientes Clave: Frecuentemente utiliza arroz y almidón de tapioca.\r\n\r\nRC-P: Media. Un producto de calidad constante que se encuentra en la mayoría de grandes supermercados (Carrefour, Alcampo).\r\n\r\n5. El Granero Integral Pan de Molde Trigo Sarraceno Bio (El Saludable)\r\nTextura y Miga: Típicamente más denso y menos esponjoso, reflejando su composición de harinas integrales y menos aditivos de volumen. No se desmorona.\r\n\r\nSabor: Fuerte sabor a sarraceno (terroso y a nuez), lo que lo hace menos versátil que los panes neutros, pero excelente para tostadas con aguacate o hummus.\r\n\r\nTostado: Óptimo. La corteza se vuelve firme y el sabor a sarraceno se intensifica.\r\n\r\nIngredientes Clave: 100% harina de trigo sarraceno (a menudo Bio), agua y sal. Gran perfil de fibra.\r\n\r\nRC-P: Media-Alta. Ideal para consumidores que priorizan la calidad nutricional sobre la textura de pan blanco.\r\n\r\n6. Airos Pan de Molde Clásico (Alternativa Fresca)\r\nTextura y Miga: Suele destacar por una humedad ligeramente mayor, lo que lo mantiene más tierno que otros panes sin gluten que se resecan rápidamente.\r\n\r\nSabor: Sabor suave y agradable, muy adecuado para sándwiches.\r\n\r\nTostado: Mantiene su suavidad incluso tostado; no llega a ser tan crujiente como Schär, pero evita volverse duro.\r\n\r\nIngredientes Clave: Mezcla de almidones.\r\n\r\nRC-P: Media. Una marca sólida que ofrece un buen equilibrio de frescura y sabor a un precio competitivo.\r\n\r\n7. Nutrifree Pan de Molde con Semillas (El Importado de Calidad)\r\nTextura y Miga: Muy buena miga, con una estructura firme pero no pesada. Las rebanadas son uniformes y suelen ser de buen tamaño.\r\n\r\nSabor: Sabor bien equilibrado entre la neutralidad de la base y las notas ricas de las semillas (lino, girasol, sésamo).\r\n\r\nTostado: Excelente. La presencia de semillas facilita una corteza muy crujiente.\r\n\r\nIngredientes Clave: A menudo libre de lactosa y aceite de palma. Base de almidón de maíz.\r\n\r\nRC-P: Media-Alta. Una opción fiable que compite con las marcas líderes en calidad sensorial.\r\n\r\n8. Schnitzer Pan de Molde Rústico (El Ecológico y Alemán)\r\nTextura y Miga: De miga densa y compacta, típico de la panadería alemana. Funciona mejor como pan de acompañamiento o tostada que como pan de sándwich esponjoso.\r\n\r\nSabor: Sabor potente y complejo, derivado de sus harinas integrales y semillas. Ideal para sabores fuertes (quesos curados, embutidos).\r\n\r\nTostado: Se tuesta muy bien, resultando en una rebanada muy resistente y consistente.\r\n\r\nIngredientes Clave: Harina de arroz integral, mijo, teff, y a menudo amaranto. Certificado ecológico (Bio).\r\n\r\nRC-P: Alta. Dirigido a un nicho que busca productos orgánicos y recetas con harinas de alto valor nutricional.\r\n\r\n9. Carrefour No Gluten! Pan de Molde (El Económico Alternativo)\r\nTextura y Miga: Miga tierna al abrir, pero tiende a endurecerse y desmoronarse si no se consume o congela rápidamente. Las rebanadas suelen ser más pequeñas.\r\n\r\nSabor: Sabor muy neutro, incluso algo insípido, lo que requiere de un relleno potente.\r\n\r\nTostado: Se vuelve crujiente, pero puede volverse seco rápidamente.\r\n\r\nIngredientes Clave: Principalmente almidón de maíz y agua.\r\n\r\nRC-P: Buena. Es una opción económica que cumple la función básica de una tostada diaria.\r\n\r\n10. Zealia Pan de Molde Multicereales (El Polifacético Español)\r\nTextura y Miga: Un buen punto medio entre la esponjosidad y la densidad. Sus rebanadas son de buen tamaño.\r\n\r\nSabor: Sabor suave a cereales y semillas, que no enmascara los ingredientes del sándwich.\r\n\r\nTostado: Correcto. Funciona bien tanto en frío como tostado.\r\n\r\nIngredientes Clave: Mezcla de harinas (a menudo sarraceno y arroz), semillas y una buena proporción de fibra.\r\n\r\nRC-P: Media. Una marca española de gran calidad que ofrece una alternativa equilibrada a las opciones industriales más grandes.\r\n\r\nConclusión: La Elección Ideal para Cada Necesidad\r\nLa era de los panes sin gluten incomibles ha quedado atrás. La variedad actual permite al consumidor elegir según sus prioridades:\r\n\r\nSi buscas Textura Clásica y Versatilidad: Schär Pan de Molde Clásico sigue siendo el líder indiscutible por su replicación de la esponjosidad tradicional.\r\n\r\nSi priorizas el Precio y el Tamaño: Hacendado ofrece el mejor valor, ideal para familias y consumo diario.\r\n\r\nSi buscas Nutrición y Sabor Integral: Las opciones de El Granero Integral y Schär Cereale destacan por sus harinas de sarraceno y su alto contenido de fibra.\r\n\r\nSi necesitas Seguridad y Sabor Tradicional: Proceli Pan Rústico es una apuesta segura que recuerda al pan de pueblo.\r\n\r\nAl elegir, se recomienda revisar siempre el listado de ingredientes para optar por aquellos con una mayor proporción de harinas nutritivas (sarraceno, mijo, quinoa) frente a los que se basan exclusivamente en almidones. El pan de molde sin gluten ideal no es solo el que no contiene gluten; es aquel que se integra perfectamente en tu dieta, ofreciendo sabor, seguridad y un buen aporte nutricional.', 15, 'blog/mejores-panes-molde', '2025-12-04 04:54:53', 190, 'PENDIENTE');
INSERT INTO `post` (`id_post`, `fecha_publicacion`, `titulo`, `subtitulo`, `contenido`, `id_usuario`, `url_post`, `fecha_validacion`, `valoracion`, `estado`) VALUES
(9, '2025-12-04 04:56:43', 'Fibra en la Dieta Celíaca: Clave para la Salud Intestinal y Nutrición Completa', 'Descubre por qué la eliminación del trigo integral crea un déficit nutricional y cómo los pseudocereales, legumbres y verduras son esenciales para restaurar la función digestiva y el equilibrio.', 'El Eslabón Perdido: Cómo Garantizar la Fibra Necesaria Tras Eliminar el GlutenLa adopción de una dieta estricta sin gluten es el único tratamiento para la enfermedad celíaca. Si bien esta eliminación es curativa para el intestino, a menudo introduce un riesgo nutricional significativo: la deficiencia de fibra dietética. Históricamente, el trigo integral, el centeno y la cebada han sido fuentes primarias y convenientes de fibra en la dieta occidental. Al eliminarlos, si no se reemplazan conscientemente con alternativas ricas en fibra, el celíaco corre el riesgo de sufrir problemas de salud que van más allá de la simple digestión.El problema se agrava porque muchos productos comerciales \"sin gluten\" se elaboran a base de almidones refinados (maíz, patata, tapioca), que son bajos en fibra, vitaminas del grupo B y hierro. Esta guía analiza por qué la fibra es crucial para el celíaco y ofrece estrategias prácticas para asegurar su ingesta adecuada.Sección I: Los Múltiples Roles de la Fibra en el Organismo CelíacoLa fibra, que se compone de partes de alimentos vegetales que el cuerpo no puede digerir ni absorber, desempeña funciones vitales, especialmente cuando el intestino ya ha estado comprometido por la enfermedad.1. Restauración de la Función Intestinal y Prevención del EstreñimientoLa enfermedad celíaca activa a menudo se manifiesta con diarrea, pero una vez establecida la dieta sin gluten, el riesgo de estreñimiento aumenta debido a la baja ingesta de fibra.Función de Volumen: La fibra insoluble añade volumen a las heces, estimulando el movimiento intestinal (peristaltismo).Función de Hidratación: La fibra soluble absorbe agua, lo que suaviza las heces y facilita su paso, actuando como un laxante natural y suave. Esto es crucial para un colon que está en proceso de recuperación.2. El Efecto Prebiótico y la MicrobiotaLa recuperación de la mucosa intestinal dañada es solo la mitad de la batalla; la otra es restaurar el equilibrio de la microbiota, que a menudo se altera en el celíaco.Alimento para la Flora: La fibra soluble fermentable actúa como un prebiótico, es decir, alimento para las bacterias beneficiosas (como Bifidobacterias y Lactobacillus) que residen en el colon.Producción de Ácidos Grasos de Cadena Corta (AGCC): Al fermentar la fibra, estas bacterias producen AGCC (como el butirato), que son la principal fuente de energía para las células del colon, ayudando a reparar y mantener la integridad de la barrera intestinal.3. Regulación del Azúcar y el Peso CorporalLa tendencia del celíaco a consumir productos ricos en almidones refinados (pan blanco sin gluten, galletas) puede provocar picos de glucosa e insulinemia.Control Glucémico: La fibra (especialmente la soluble) ralentiza la digestión y la absorción de carbohidratos, ayudando a estabilizar los niveles de azúcar en sangre, previniendo la fatiga post-comida y reduciendo el riesgo de resistencia a la insulina a largo plazo.Saciedad: Al ocupar espacio y ralentizar el vaciado gástrico, la fibra proporciona una mayor sensación de saciedad, lo que es útil para el control del peso.Sección II: La Trampa de los Alimentos Procesados Sin GlutenUno de los principales errores en la dieta celíaca es sustituir directamente el trigo integral por panes, pastas y bollería comerciales sin gluten, que son a menudo nutricionalmente pobres.La Fórmula de la Densidad CalóricaMuchos productos sin gluten de supermercado dependen de una mezcla de almidones de bajo costo (tapioca, maíz, patata) y grasas/azúcares para mejorar el sabor y la textura, ya que la fibra de las harinas integrales tiende a hacer que los productos se sequen y se desmoronen.Bajo Contenido de Fibra: El contenido de fibra de un pan de molde sin gluten puede ser significativamente menor (a menudo menos de 2 gramos por 100g) que un pan de trigo integral (7-10 gramos por 100g).Deficiencia de Micronutrientes: Además de la fibra, estos productos carecen de vitaminas del grupo B, niacina, tiamina y hierro, que suelen ser micronutrientes añadidos a las harinas de trigo.Sección III: Fuentes de Fibra Seguras y Nutricionalmente DensasLa solución no es suplementar, sino reestructurar la dieta para incluir fuentes de fibra naturalmente libres de gluten.1. Pseudocereales y Cereales SegurosEstos deberían ser la base de la dieta celíaca, reemplazando el arroz blanco y los almidones.Trigo Sarraceno (Alforfón): Excelente fuente de fibra y contiene el flavonoide Rutina. Se puede usar el grano cocido como guarnición o su harina para panes y panqueques.Quinoa: Una proteína completa y rica en fibra. Ideal para ensaladas, como base para bowls o como sustituto del cuscús.Mijo y Teff: Granos integrales menos conocidos pero ricos en fibra y minerales, perfectos para gachas o para mezclar con otras harinas en panadería.Avena Certificada Sin Gluten: (Crucial: siempre debe estar certificada). Es una fuente inigualable de beta-glucanos, un tipo de fibra soluble que ha demostrado ser beneficiosa para el colesterol y la salud cardíaca.2. Legumbres (Frijoles, Lentejas, Garbanzos)Las legumbres son una de las fuentes de fibra (soluble e insoluble) más densas y baratas.Uso: Incorpóralas diariamente en guisos, ensaladas o purés (hummus). La harina de lentejas o garbanzos es excelente para mejorar el perfil nutricional de la panadería casera sin gluten.3. Frutas y Verduras FrescasPrioriza las que ofrecen la mayor cantidad de fibra por porción.Verduras: Hojas verdes (espinacas, acelgas), brócoli, coles de Bruselas y boniato (batata).Frutas: Bayas (frambuesas, moras), manzanas y peras (consumidas con piel), y frutas secas (higos, ciruelas, albaricoques) para un aporte concentrado.4. Semillas y Frutos SecosSemillas de Chía y Lino: Son excelentes fuentes de fibra soluble y ácidos grasos omega-3. Dos cucharadas de chía proporcionan más de 10 gramos de fibra. Se pueden añadir a yogures, smoothies o usarse como espesantes en repostería.Frutos Secos: Las almendras y las nueces son snacks ricos en fibra y grasas saludables.Conclusión: La Fibra como Agente Curativo ContinuoPara una persona celíaca, la fibra no es solo un nutriente para evitar el estreñimiento; es un agente curativo continuo que sustenta la recuperación intestinal, modula la microbiota y previene deficiencias secundarias. La clave para el éxito en la dieta sin gluten reside en alejarse de los productos sustitutivos basados en almidones y volver a una dieta basada en alimentos naturalmente libres de gluten: granos integrales seguros, legumbres y abundantes frutas y verduras. Asegurando una ingesta diaria de $25$ a $30$ gramos de fibra, el celíaco puede no solo manejar su condición, sino también optimizar su salud metabólica y digestiva a largo plazo.', 16, 'blog/fibra-celiaca', '2025-12-04 04:56:43', 65, 'PENDIENTE'),
(10, '2025-12-04 04:58:44', 'Potencia tu Mañana: 10 Ideas Creativas para Desayunos Sin Gluten y Ricos en Proteína', 'Explora opciones deliciosas y sencillas que van más allá del huevo revuelto. Recetas innovadoras con pseudocereales, lácteos de alta proteína y toppings funcionales para una saciedad duradera.', 'Para quienes siguen una dieta sin gluten, el desayuno puede convertirse rápidamente en una rutina de opciones limitadas, a menudo centrada en carbohidratos de bajo valor nutricional (panes y cereales de almidón). Sin embargo, iniciar el día con una ingesta significativa de proteínas es esencial. La proteína no solo ayuda a reparar y construir tejido muscular, sino que es el macronutriente con mayor poder de saciedad, lo que ayuda a evitar los antojos a media mañana y a estabilizar los niveles de glucosa.\r\n\r\nUna dieta sin gluten bien planificada debe ser una oportunidad para introducir ingredientes más nutritivos. Presentamos 10 ideas creativas, organizadas por su base, que garantizan un comienzo de día potente, delicioso y seguro.\r\n\r\nSección I: Bases de Pseudocereales y Granos Seguros\r\nLos pseudocereales son la base perfecta, ya que son naturalmente libres de gluten y contienen más proteína y fibra que las harinas refinadas.\r\n\r\n1. Gachas (Porridge) de Quinoa Proteico\r\nDescripción: Sustituye la avena (si no es certificada) por quinoa cocida en leche o bebida vegetal.\r\n\r\nAporte Proteico: La quinoa es una proteína completa. Para un extra de proteína, usa leche entera o bebida de soja y añade proteína en polvo (whey o vegana) al final de la cocción.\r\n\r\nToppings Clave: Semillas de chía (fibra), nueces picadas y una cucharada de mantequilla de almendras.\r\n\r\n2. Waffles o Panqueques de Harina de Sarraceno (Alforfón)\r\nDescripción: Utiliza harina de trigo sarraceno para la masa, mezclada con huevos. La harina de sarraceno aporta un sabor terroso y es rica en proteína.\r\n\r\nAporte Proteico: Bate la masa con claras de huevo adicionales (un par de claras por cada taza de harina).\r\n\r\nToppings Clave: Yogur griego natural o queso batido (Skyr) en lugar de sirope, y bayas frescas.\r\n\r\n3. Muesli Tostado con Teff\r\nDescripción: El teff es un grano pequeño, rico en hierro y proteína. Úsalo como base de un muesli casero, tostándolo con frutos secos.\r\n\r\nAporte Proteico: Se sirve con leche de alta proteína (como la bebida de soja) y se le añade una cucharada de cáñamo pelado (hemp seeds).\r\n\r\nSección II: Opciones Basadas en Huevo y Vegetales\r\nEl huevo es el estándar de oro, pero se puede innovar al integrarlo en preparaciones más completas y variadas.\r\n\r\n4. Tostada de Huevo y Salmón Ahumado (en Pan Seguro)\r\nDescripción: Utiliza un pan de molde sin gluten de alta calidad (idealmente de masa madre de arroz o sarraceno).\r\n\r\nAporte Proteico: Dos huevos (escalfados o a la plancha) + salmón ahumado. Esta combinación aporta grasas saludables y una gran dosis de proteína completa.\r\n\r\nToppings Clave: Aguacate, eneldo fresco y un poco de zumo de limón.\r\n\r\n5. Revuelto de Tofu o Huevo con Verduras y Legumbres\r\nDescripción: Un \"revuelto\" (scramble) hecho con tofu firme desmenuzado y salteado (o huevos) y verduras.\r\n\r\nAporte Proteico: Tofu (plant-based) o 3 huevos. Añade media taza de lentejas rojas cocidas (que casi se desintegran) para un aporte extra de fibra y proteína indetectable.\r\n\r\nToppings Clave: Cúrcuma (para el color), levadura nutricional (sabor a queso) y cebolleta.\r\n\r\n6. Tortilla Francesa Rellena de Queso Batido\r\nDescripción: Prepara una tortilla de dos huevos y claras, y rellénala antes de doblarla.\r\n\r\nAporte Proteico: Los huevos más el relleno de queso batido o requesón bajo en grasa (casi puro caseína, proteína de absorción lenta).\r\n\r\nToppings Clave: Pimientos asados picados o espinacas frescas.\r\n\r\nSección III: Opciones Rápidas y Frías (Licuados y Postres)\r\nIdeales para los días sin tiempo, utilizando la proteína en polvo o lácteos como base.\r\n\r\n7. Smoothie Verde con Proteína en Polvo\r\nDescripción: La forma más rápida de obtener nutrientes.\r\n\r\nAporte Proteico: Mezcla leche o yogur líquido con una dosis de proteína en polvo (vainilla o neutra), espinacas frescas, plátano congelado y semillas de chía.\r\n\r\nIngredientes Clave: Una cucharada de mantequilla de cacahuete natural sin azúcar para grasas saludables y sabor.\r\n\r\n8. \"Avena\" Nocturna de Chía y Proteína (Overnight Protein Oats)\r\nDescripción: Sustituye la base de avena por semillas de chía o de lino.\r\n\r\nAporte Proteico: Mezcla las semillas con leche y media dosis de proteína en polvo, y déjalo reposar toda la noche en el refrigerador. La chía absorbe el líquido y crea una consistencia de pudín.\r\n\r\nToppings Clave: Cacao puro o ralladura de limón.\r\n\r\n9. Yogur Griego o Skyr con Granola Casera Sin Gluten\r\nDescripción: Es la opción más rápida de lácteos altos en proteína.\r\n\r\nAporte Proteico: Una tarrina de yogur griego (o Skyr, más denso y proteico) aporta entre 15 y 20 gramos de proteína.\r\n\r\nToppings Clave: Granola casera (hecha con quinoa inflada, nueces y miel) y semillas de calabaza. Evita la granola comercial, ya que a menudo está contaminada con gluten.\r\n\r\n10. Tostada de Garbanzos (Tipo Tosta-Hummus)\r\nDescripción: Una alternativa salada, sin huevos.\r\n\r\nAporte Proteico: Utiliza pan sin gluten tostado y cúbrelo con una capa generosa de hummus casero (rico en proteína de garbanzo) con tahini.\r\n\r\nToppings Clave: Pimentón dulce, aceite de oliva virgen extra y germinados de alfalfa o sarraceno.\r\n\r\nConclusión: La Proteína como Prioridad en la Mañana\r\nLa clave para un desayuno sin gluten exitoso es ver la eliminación del trigo no como una limitación, sino como una invitación a explorar alimentos naturalmente ricos en nutrientes. Al priorizar bases como la quinoa, el sarraceno, los huevos, el tofu y el yogur griego, y al usar la proteína en polvo como un refuerzo versátil, es posible construir desayunos deliciosos, seguros y que cumplen con las demandas de energía y saciedad hasta la hora del almuerzo. Dejar de depender del pan de molde procesado en la mañana es la estrategia más efectiva para un bienestar sostenido.', 17, 'blog/desayunos-proteina', '2025-12-04 04:58:44', 112, 'PENDIENTE'),
(11, '2025-12-04 05:01:06', 'El Secreto Visco-elástico', 'La Fibra Mágica: Descubriendo el Psyllium Husk y su Función Estructural en la Panificación Sin Gluten', 'El Psyllium Husk, o cáscara de psilio, es un ingrediente que ha revolucionado el mundo de la panadería sin gluten, pasando de ser un simple suplemento de fibra a un agente estructural indispensable. Procede de las semillas de la planta Plantago ovata, originaria de la India, y es esencialmente una forma pura de fibra soluble. Se comercializa generalmente en dos formatos: la cáscara entera (más gruesa) o el polvo molido (más fino y potente). Comprender qué es y cómo interactúa con el agua es la clave para transformarlo en una herramienta poderosa en la cocina.La función del psyllium husk en las masas sin gluten es única y crucial: actúa como un aglutinante hidrofílico altamente eficiente, reemplazando la red elástica y plástica del gluten. Cuando el psyllium entra en contacto con el agua, absorbe el líquido de forma espectacular, llegando a hincharse hasta diez o veinte veces su volumen original. Esta absorción masiva crea un gel mucilaginoso muy espeso y cohesivo. Al incorporar este gel a una masa hecha de harinas sin gluten —que son típicamente inertes y secas, como el arroz o el maíz—, se produce una matriz gelatinosa que liga las partículas de almidón y harina. Esta matriz tiene una calidad esencial: simula la visco-elasticidad que se pierde al eliminar el trigo.La forma de usarlo en masas depende del formato elegido. Si se utiliza la cáscara entera, la recomendación general es hidratarla previamente. La hidratación previa se logra mezclando la cáscara con el líquido de la receta (agua, leche o bebida vegetal) y dejándola reposar durante unos cinco a diez minutos. Esto permite que el mucílago se forme por completo antes de que se añada a las harinas secas. Si no se hidrata previamente, el psyllium intentará robar la humedad del resto de los ingredientes durante el amasado, lo que puede resultar en una masa seca y difícil de manejar.En cambio, si se utiliza el psyllium en polvo, que es mucho más concentrado, la cantidad requerida se reduce a la mitad (se utiliza aproximadamente un $50\\%$ menos de polvo que de cáscara entera). El psyllium en polvo tiene una tasa de absorción tan rápida que, en muchas recetas, se recomienda mezclarlo directamente con los ingredientes secos y luego añadir el líquido de golpe, batiendo rápidamente para evitar la formación de grumos grandes. El psyllium en polvo proporciona una red más fina y una textura de miga potencialmente más suave, pero exige mayor precisión y velocidad en el manejo de la masa.La proporción de psyllium husk es vital para el éxito. En las masas de pan sin gluten, el psyllium generalmente representa entre el $2\\%$ y el $5\\%$ del peso total de las harinas. Esta cantidad no solo dota a la masa de la estructura interna necesaria para atrapar el dióxido de carbono producido por la levadura —lo que permite el desarrollo de un buen volumen y altura—, sino que también facilita el manejo de la masa. Una masa con psyllium es menos pegajosa, más moldeable y permite técnicas de panificación como el doblado o el boleado, que son imposibles de realizar con mezclas de almidones puros. Además, gracias a su gran capacidad de retención de agua, el psyllium contribuye significativamente a la frescura del pan, retrasando el endurecimiento o staling de la miga y manteniendo la humedad por más tiempo. En resumen, el psyllium husk es el ingrediente funcional que proporciona la estructura, la elasticidad, el volumen y la longevidad que definen un pan sin gluten de alta calidad.', 18, 'blog/que-es-psyllium', '2025-12-04 05:01:06', 78, 'PENDIENTE'),
(12, '2025-12-04 05:02:51', 'Postres Sin Horno: 10 Delicias Rápidas, Frescas y Fáciles para el Verano', 'Una colección de recetas sencillas y vibrantes que minimizan el tiempo en la cocina. Desde mousses cremosas hasta tartas frías, el placer dulce sin encender el horno.', '🍦 Sin Calor, Máximo Sabor: Postres Sin Horno para Días Calurosos\r\nTítulo (11 palabras)\r\nPostres Sin Horno: 10 Delicias Rápidas, Frescas y Fáciles para el Verano\r\n\r\nSubtítulo (28 palabras)\r\nUna colección de recetas sencillas y vibrantes que minimizan el tiempo en la cocina. Desde mousses cremosas hasta tartas frías, el placer dulce sin encender el horno.\r\n\r\n☀️ El Arte de la Repostería Fría: Velocidad y Frescura\r\nEn los meses de calor o cuando simplemente se busca una solución dulce y rápida sin el proceso laborioso del horneado, los postres sin horno emergen como la opción perfecta. Estos se basan en la magia de la refrigeración y la solidificación, utilizando ingredientes aglutinantes como la gelatina, el agar-agar, o las grasas que se endurecen (como el coco o la mantequilla) y las bases de galleta triturada.\r\n\r\nLa belleza de estos postres radica en su sencillez, su rápida ejecución y el potente factor de frescura que ofrecen. Aquí exploramos una variedad de ideas creativas, organizadas por el tiempo de preparación y el tipo de textura, ideales para cualquier ocasión.\r\n\r\nSección I: Postres de Cuchara y Mousses (La Textura Cremosa)\r\nEstos postres son los más rápidos de preparar, ya que su éxito depende del batido y la refrigeración para adquirir la consistencia perfecta.\r\n\r\n1. Tiramisú Rápido en Vaso\r\nPreparación: En lugar de los bizcochos de soletilla tradicionales, usa galletas de champaña o ladyfingers mojadas en café fuerte.\r\n\r\nCapas: Alterna capas de galletas mojadas, crema de mascarpone batida con yemas y un toque de azúcar, y finaliza con una generosa capa de cacao en polvo puro.\r\n\r\nEnfriado: Mínimo 2 horas para que las capas se asienten. Ideal para improvisar.\r\n\r\n2. Mousse de Chocolate Negro y Aguacate (Opción Vegana)\r\nPreparación: Se bate en licuadora pulpa de aguacate (que aporta cremosidad y grasas saludables), cacao puro en polvo, sirope de arce o dátil, y un poco de leche vegetal fría.\r\n\r\nTextura: El aguacate da una textura increíblemente sedosa, mientras que el cacao enmascara totalmente su sabor.\r\n\r\nEnfriado: 1 hora. Se sirve en copas individuales y se decora con frambuesas.\r\n\r\n3. Panna Cotta de Vainilla y Frutos Rojos\r\nPreparación: Se calienta nata (crema de leche) con azúcar y vainilla. Se hidrata gelatina neutra y se añade a la mezcla caliente.\r\n\r\nTextura: La gelatina proporciona esa textura temblorosa, firme pero fundente, clásica de la panna cotta.\r\n\r\nEnfriado: 4 horas. Desmoldar o servir directamente en el molde, cubierto con una salsa de frutos rojos cocida a fuego lento.\r\n\r\nSección II: Tartas y Postres con Base de Galleta (La Estructura)\r\nEstos postres requieren una base triturada (generalmente de galletas y mantequilla) que se presiona y se enfría, y luego se rellena con una crema sólida.\r\n\r\n4. Cheesecake de Limón sin Horno (No-Bake Cheesecake)\r\nBase: Galletas tipo Digestive trituradas y mezcladas con mantequilla derretida. Se presiona en el molde y se refrigera.\r\n\r\nRelleno: Queso crema (tipo Philadelphia) batido con azúcar glas, zumo y ralladura de limón, y estabilizado con gelatina o agar-agar.\r\n\r\nEnfriado: Mínimo 6 horas (mejor toda la noche). Cubrir con gelatina de limón o curd frío.\r\n\r\n5. Tarta de Leche Condensada y Lima (Key Lime Pie Rápida)\r\nBase: La misma base de galleta y mantequilla.\r\n\r\nRelleno: Una mezcla rápida de leche condensada, queso crema y el zumo ácido de limas. Esta mezcla se solidifica naturalmente en el frío.\r\n\r\nEnfriado: 4 horas. Decorar con nata montada justo antes de servir.\r\n\r\n6. Tarta de Tres Chocolates Express\r\nPreparación: Capas de chocolate negro, con leche y blanco. Cada capa de chocolate derretido se mezcla con leche y gelatina hidratada, y se vierte sobre la capa anterior, permitiendo que se enfríe antes de añadir la siguiente.\r\n\r\nAceleración: El secreto está en usar la cantidad adecuada de gelatina y enfriar cada capa brevemente en el congelador para ganar tiempo.\r\n\r\nSección III: Frutas y Helados Instantáneos (El Factor Frío Extremo)\r\nEstas ideas aprovechan el poder del congelador para crear texturas de helado o sorbete sin necesidad de máquinas.\r\n\r\n7. Nice Cream de Plátano y Cacao (Helado Saludable)\r\nPreparación: Se congelan rodajas de plátano maduro durante al menos 8 horas.\r\n\r\nTextura: Se licúan los plátanos congelados con un chorrito de leche vegetal y cacao puro. El resultado es un \"helado\" de textura suave y cremosa (de ahí el nombre \"Nice Cream\").\r\n\r\nSabor: Funciona con cualquier fruta congelada: mango, frutos rojos o piña.\r\n\r\n8. Brochetas de Fruta con Dipping de Yogur Helado\r\nPreparación: Enhebrar frutas variadas (fresas, uvas, kiwi) en brochetas.\r\n\r\nAporte Proteico/Frío: Sumergir parcialmente en yogur griego endulzado y colocarlas en una bandeja de horno cubierta con papel sulfurizado.\r\n\r\nEnfriado: Congelar durante 1 hora. El yogur se convierte en una capa crujiente y fría.\r\n\r\n9. Triffle de Bizcocho Desmenuzado y Crema Pastelera Fría\r\nPreparación: Un postre de origen británico montado en un bol o copa. Se utiliza bizcocho de soletilla o bizcocho comprado desmenuzado, o migas de bizcocho sin gluten.\r\n\r\nCapas: Alterna migas de bizcocho (humedecidas con zumo de naranja o licor), crema pastelera fría (hecha con maicena y cuajada en la nevera) y mucha fruta fresca o en conserva.\r\n\r\n10. Tabletas de Chocolate y Frutos Secos (Básicos de Refrigerador)\r\nPreparación: Se derrite chocolate negro de buena calidad al baño maría o en microondas.\r\n\r\nMontaje: Se vierte el chocolate derretido sobre papel de horno, formando una capa fina. Antes de que se enfríe, se esparcen por encima frutos secos, semillas y sal marina.\r\n\r\nEnfriado: 30 minutos en el refrigerador. Una vez duro, se rompe en trozos irregulares.', 19, 'blog/postres-sin-horno', '2025-12-04 05:02:51', 145, 'PENDIENTE'),
(13, '2025-12-07 06:13:44', 'Vivir con Celiaquía y Viajar: Estrategias, Desafíos y Destinos Gluten-Free Globales', 'Hablamos con Sofía Márquez, un explorador incansable, sobre cómo superar la ansiedad alimentaria, planificar rutas seguras y las claves para disfrutar de la gastronomía mundial sin riesgos.', 'Diálogo con el Aventurero: La Libertad de Viajar Sin Gluten\r\nLa enfermedad celíaca impone restricciones dietéticas rigurosas, haciendo que actividades cotidianas como comer fuera o viajar se conviertan en actos de planificación meticulosa. Para muchos, la idea de viajar por países con culturas culinarias muy diferentes genera una profunda ansiedad por el riesgo de contaminación cruzada. Sin embargo, hay quienes demuestran que la celiaquía no es una sentencia de confinamiento, sino un desafío logístico superable.\r\n\r\nHemos tenido el placer de hablar con Sofía Márquez, una experimentada viajera y celíaca diagnosticada hace más de una década, quien ha recorrido Asia, Europa y Latinoamérica, demostrando que la aventura y la seguridad alimentaria pueden ir de la mano.\r\n\r\nI: Sofía, gracias por recibirnos. Para empezar, ¿cómo enfrentaste la idea de viajar tras tu diagnóstico? ¿Hubo un período de miedo o resignación?\r\n\r\nS: Gracias a ustedes. Absolutamente. Al principio, fue una pared gigantesca. Mi diagnóstico coincidió con un período en el que solía viajar mucho por trabajo. La primera reacción fue de pánico: \"Nunca más podré probar la comida local\" o \"Voy a enfermarme en medio de la nada\". El miedo no era solo por la molestia, sino por el daño intestinal a largo plazo. Tuve un par de viajes fallidos donde mi ansiedad era tal que terminé comiendo solo fruta y arroz blanco. Pero luego me di cuenta de que la resignación no era una opción. La clave fue cambiar el enfoque: dejar de ver la celiaquía como un obstáculo y empezar a verla como una herramienta de planificación. Me obligó a ser más organizada y a investigar las culturas locales de una manera mucho más profunda.\r\n\r\nI: La planificación es clave. ¿Cuáles son tus tres pasos esenciales antes de poner un pie en un nuevo país?\r\n\r\nS: Mis tres pilares son investigación, comunicación y equipamiento.\r\n\r\nInvestigación Geográfica y Comunitaria: Antes de reservar vuelos, busco si hay una asociación de celíacos local (como FACE en España o Coeliac UK). Estas asociaciones suelen tener listados de restaurantes y tiendas seguros. También uso grupos de Facebook o foros de viajeros celiacos para obtener recomendaciones actualizadas de otros que ya han estado allí. Busco supermercados grandes y cadenas que manejen productos sin gluten.\r\n\r\nLa Tarjeta de Chef: Esto es fundamental, especialmente en países donde no hablo el idioma. Utilizo tarjetas de traducción de alerta de celiaquía profesionales y certificadas. Estas tarjetas no solo dicen \"no gluten\", sino que explican las fuentes ocultas (salsa de soja, espesantes, contaminación cruzada en la freidora). Siempre llevo varias copias laminadas en el idioma local.\r\n\r\nEl Kit de Supervivencia: Aunque viaje a países con mucha oferta, siempre llevo mis imprescindibles: sobres de avena certificada, barritas energéticas sin gluten para el día a día y, crucialmente, una pequeña bolsa con sal, pimienta y alguna especia. Si me toca comer solo arroz simple o patatas cocidas en un lugar dudoso, al menos le doy algo de sabor sin tener que confiar en las salsas locales.\r\n\r\nI: Hablemos de la parte social y el día a día. ¿Cómo manejas las comidas en restaurantes y, sobre todo, la temida contaminación cruzada?\r\n\r\nS: La comunicación es directa y respetuosa. Cuando entro a un restaurante, no pregunto \"¿tienen opciones sin gluten?\", sino \"¿podría hablar con el chef o el encargado para explicar una alergia grave?\". Usar la palabra \"alergia grave\" (severe allergy) eleva la seriedad del asunto.\r\n\r\nUna vez con el encargado, les muestro la tarjeta de chef y pregunto sobre cuatro puntos clave:\r\n\r\nEl uso de la misma freidora para alimentos con y sin gluten.\r\n\r\nEl uso de salsa de soja tradicional (con trigo).\r\n\r\nLa limpieza de las tablas de cortar y utensilios.\r\n\r\nLa seguridad de las especias o bases de caldo.\r\n\r\nSi veo duda o inseguridad en la respuesta, simplemente me levanto. Prefiero un mercado local o una fruta a arriesgar mi salud. En los mercados, elijo alimentos naturalmente seguros: frutas, verduras, tubérculos cocidos (si puedo ver cómo los cocinan) y huevos.\r\n\r\nI: ¿Hay algún accesorio o \"gadget\" que consideres esencial en tu maleta de viajera celiaca?\r\n\r\nS: Sí, hay un par de cosas que me han salvado la vida. Primero, una mini-cazuela eléctrica plegable. Pesa muy poco, cabe en el equipaje de mano y me permite cocinar arroz, huevos o verduras de forma segura en la habitación del hotel o hostel. Esto es oro puro en países donde la seguridad en la calle es baja.\r\n\r\nSegundo, mis propias bolsas tostadoras. Las llevo a cualquier hotel que ofrezca desayuno. Son bolsas de teflón que permiten tostar mi propio pan sin gluten en la tostadora común del bufé, aislando completamente las migas de trigo.\r\n\r\nY, por supuesto, llevo siempre medicamentos básicos: un protector gástrico y un antihistamínico fuerte. No es una cura, pero da tranquilidad saber que puedes manejar una reacción leve mientras llegas a un lugar seguro.\r\n\r\nI: En tu experiencia, ¿cuáles han sido los destinos más fáciles o \"amigables\" para los celiacos, y cuáles los más difíciles?\r\n\r\nS: El premio a los más fáciles se lo lleva, sin duda, Italia y España. Italia, por su cultura de la comida y la alta incidencia de celiaquía; es fácil encontrar pizzas, pastas y postres certificados incluso en pueblos pequeños. España, por la regulación de FACE y la existencia de tapas naturalmente sin gluten como patatas bravas (preguntando por la freidora) y jamón.\r\n\r\nOtros fáciles son Estados Unidos y Reino Unido por la estricta regulación de etiquetado y la clara conciencia de \"alergias\".\r\n\r\nEl más difícil para mí fue China. El gluten (en forma de trigo o fideos) y la salsa de soja tradicional (con trigo) están en todo. La barrera del idioma es enorme y, aunque muestres la tarjeta, la contaminación cruzada en los woks es casi inevitable. Vietnam fue similar; aunque el arroz es la base, a menudo usan caldos con potenciadores de sabor que contienen trigo. En estos casos, recurrir a los restaurantes de comida internacional (como la francesa o la india) que tienen protocolos más globales, o cocinar en el hostel, fue la mejor estrategia.\r\n\r\nI: Más allá de la comida, ¿cómo gestionas el peso mental de estar siempre alerta?\r\n\r\nS: Es el desafío más grande, la fatiga de la vigilancia constante. Aprendí a aceptar que no todas las comidas serán una experiencia gastronómica local. A veces, mi comida local es una bolsa de patatas fritas (leyendo bien la etiqueta, claro) o un plato de arroz simple.\r\n\r\nPara mitigar el estrés, siempre me permito un \"día libre de investigación\". Después de tres o cuatro días intensos buscando restaurantes, me doy un día donde solo como mis provisiones seguras en la tranquilidad de mi alojamiento. Esto recarga la batería mental y evita que la ansiedad domine el viaje. También utilizo aplicaciones de planificación de comidas para celíacos que mapean lugares seguros a mi alrededor.\r\n\r\nI: Para cerrar, ¿qué mensaje le darías a un recién diagnosticado que cree que sus sueños de viajar se han acabado?\r\n\r\nS: Les diría: Tu diagnóstico no es el fin del viaje; es el inicio de una nueva forma de explorar. Sí, requiere más esfuerzo, pero te enseña a conectarte con las personas de una manera más profunda. Tienes que interactuar, explicar, preguntar. Te acercas al chef, al vendedor, a la familia que te aloja.\r\n\r\nLa celiaquía te fuerza a ir más lento, a ser más consciente de lo que consumes y a valorar inmensamente las comidas seguras y deliciosas que encuentras. La sensación de éxito al comer de forma segura en un lugar remoto es una de las mayores satisfacciones del viaje. Solo prepárate, sé amable, sé firme y lleva siempre tu pan de repuesto en la maleta. ¡El mundo te espera!', 20, 'blog/entrevista-viajes', '2025-12-07 06:13:44', 35, 'PENDIENTE'),
(14, '2025-12-04 05:06:24', 'Croquetas Sin Gluten Perfectas: La Técnica Infalible para una Fritura Cremosa y Segura', 'Descubre la combinación mágica de harinas y almidones que reemplaza al gluten. El control de la textura de la bechamel y el rebozado en dos fases para evitar roturas.', ' La Estabilidad Imposible: Dominando la Croqueta Sin GlutenLa croqueta es, sin duda, una de las tapas más emblemáticas y queridas de la gastronomía. Su éxito radica en el contraste: una cubierta exterior crujiente y dorada que encierra una bechamel interior, cremosa y casi líquida. Para el celiaco, este plato es a menudo una fuente de frustración, no solo por el uso tradicional de harina de trigo en la bechamel y el rebozado, sino por la dificultad de lograr la estabilidad estructural necesaria sin gluten.Las croquetas sin gluten tienden a romperse en la sartén por dos razones fundamentales: una bechamel inestable que se vuelve demasiado líquida con el calor, y un rebozado débil que no sella correctamente la bemosca (la masa de croqueta) y permite que el relleno escape al aceite. Lograr la perfección requiere una sustitución inteligente del gluten y un protocolo estricto en el enfriamiento y la fritura.Sección I: El Secreto de la Bechamel Sin Gluten (La Estructura Interna)El gluten en la bechamel tradicional aporta cuerpo y evita que la masa se separe o licúe durante la cocción. Al eliminarlo, debemos recurrir a agentes espesantes que proporcionen una viscosidad estable.1. La Mezcla de Espesantes (El Vínculo Mágico)No basta con sustituir la harina de trigo por una sola harina sin gluten; se necesita una combinación que aporte almidón para la textura y un hidrocoloide para la elasticidad.Harina Base: Utiliza harina de arroz o almidón de maíz (Maicena). Estas aportan la base de almidón necesaria, pero por sí solas dan una textura \"gomosa\" y se licúan fácilmente.El Estabilizador (La Goma Xantana): El secreto definitivo es añadir una pequeña cantidad de goma xantana (aproximadamente $1/4$ de cucharadita por cada 500 ml de leche). La xantana forma una matriz gelatinosa que liga los ingredientes, imitando la red del gluten. Esta matriz mantiene la forma de la croqueta y evita que la bechamel se desmorone o se licúe excesivamente con el calor de la fritura.La Textura (Psyllium Husk): Algunas recetas más avanzadas sustituyen parte de la harina con una cucharadita de Psyllium Husk en polvo. El psyllium, al hidratarse, crea un gel que proporciona cuerpo sin endurecer la bechamel, permitiendo ese relleno cremoso característico.2. El Amasado Riguroso y el Enfriamiento CrucialUna vez que la bechamel está lista, la cocción y el enfriamiento son tan importantes como los ingredientes.Cocción Prolongada: La bechamel debe cocinarse a fuego lento durante más tiempo del habitual (al menos 15-20 minutos después de agregar la leche) para que los almidones gelatinicen por completo y el sabor de las harinas crudas desaparezca.Enfriamiento a Conciencia: La masa de croquetas sin gluten necesita un enfriamiento riguroso. Debe extenderse sobre una bandeja (máximo 2-3 cm de grosor), cubrirse con film transparente (tocando la masa para evitar costra) y refrigerarse por un mínimo de 12 horas (idealmente 24 horas). Esto permite que los hidrocoloides y los almidones se asienten y la bechamel adquiera la firmeza necesaria para ser manipulada sin romperse.Sección II: El Rebozado Doble (El Blindaje Antirotura)Una bechamel perfecta no sirve de nada si el rebozado se abre o se despega. El rebozado sin gluten debe ser más grueso y cohesivo que el tradicional.1. El Rebozado en Dos Fases: Harina y HuevoEl rebozado tradicional sin gluten (harina-huevo-pan rallado) a menudo falla porque las harinas sin gluten (generalmente de arroz) no se adhieren bien.Fase 1: La Primera Capa Seca: Tras dar forma a la croqueta, pásala por harina de arroz o maicena pura. Esta primera capa seca asegura que la superficie grasa de la croqueta se seque y el huevo posterior se adhiera de manera uniforme.Fase 2: El Sello de Huevo Grueso: Bate los huevos con un chorrito de leche o agua y un poco de sal. La croqueta debe quedar totalmente cubierta y \"sellada\" por el huevo.2. El Pan Rallado (La Cobertura Crujiente)El pan rallado debe ser específicamente sin gluten.Tipos de Pan Rallado: El panko sin gluten es la mejor opción, ya que su forma de escama grande absorbe menos aceite y proporciona una textura crujiente y ligera. Si utilizas pan rallado normal sin gluten, asegúrate de que sea lo suficientemente fino para cubrir bien, o incluso reboza una segunda vez (huevo-pan rallado) para crear un doble rebozado.Sección III: La Fritura a Prueba de RoturasIncluso la croqueta mejor rebozada puede romperse si se fríe incorrectamente.1. La Temperatura y la Cantidad de AceiteAceite Abundante: La croqueta debe estar completamente sumergida en aceite (fritura por inmersión). Si solo se fríe la mitad, el lado superior se calentará lentamente, se ablandará y el peso de la masa hará que se rompa al intentar darle la vuelta.Temperatura Constante y Alta (pero no excesiva): La temperatura ideal es de $175^\\circ\\text{C}$ a $180^\\circ\\text{C}$. Una temperatura inferior hace que la croqueta absorba demasiado aceite y se hunda (aumentando la probabilidad de rotura); una temperatura superior quema el exterior antes de que el interior se caliente y solidifique. Utiliza un termómetro de cocina.2. Croquetas Congeladas (El Máximo de Seguridad)La técnica más segura para evitar roturas es congelar las croquetas ya rebozadas.Proceso: Una vez rebozadas y selladas, colócalas en una bandeja separadas y congélalas durante al menos 4 horas.Fritura: Fríe las croquetas directamente desde el congelador (sin descongelar) en aceite a $180^\\circ\\text{C}$. El congelamiento instantáneo asegura que la bechamel interna se mantenga fría y firme durante el primer minuto de fritura, permitiendo que el rebozado se endurezca y se selle completamente antes de que el calor pueda licuar el centro.La croqueta sin gluten que no se rompe es el resultado de un proceso de ingeniería culinaria: la bechamel debe ser un gel estable (gracias a la xantana o el psyllium), el rebozado debe ser un sello hermético de dos fases, y la fritura debe ser rápida, en inmersión y a la temperatura precisa. Dominando estos secretos, se puede disfrutar de esta joya de la tapa con total seguridad y el máximo sabor.', 21, 'blog/croquetas-sin-romper', '2025-12-04 05:06:24', 99, 'PENDIENTE'),
(15, '2025-12-04 05:08:34', 'Aliados Digitales: Tu Guía Definitiva de Recursos y Apps para Encontrar Restaurantes Sin Gluten Seguros', 'Recursos y apps útiles para encontrar restaurantes seguros.', 'Para la persona celíaca o con sensibilidad alimentaria, la tecnología se ha convertido en el mejor seguro de vida a la hora de comer fuera de casa. Navegar por el mundo gastronómico sin el temor constante a la contaminación cruzada o a la falta de opciones requiere de información precisa, actualizada y, a menudo, verificada por otros usuarios. Afortunadamente, existe una sólida colección de recursos digitales y aplicaciones móviles diseñadas específicamente para esta comunidad, transformando la ansiedad de comer fuera en una oportunidad para la exploración culinaria segura.\r\n\r\nUno de los recursos más valiosos a nivel global es la aplicación Gluten Free Passport, aunque su uso varía según la región. Esta app es famosa por sus \"Tarjetas de Chef\" traducidas a decenas de idiomas, un recurso vital para comunicar la restricción dietética de manera inequívoca en países donde el inglés no es dominante. Aunque no es una base de datos de restaurantes en sí misma, su enfoque en la comunicación precisa es la primera línea de defensa en cualquier viaje internacional.\r\n\r\nEn el mundo de las bases de datos colaborativas, Find Me Gluten Free es el líder indiscutible, especialmente potente en Estados Unidos, Canadá y Europa occidental, pero con una cobertura creciente a nivel mundial. Funciona al estilo Yelp o TripAdvisor, permitiendo a los usuarios buscar restaurantes cercanos, filtrarlos por nivel de seguridad (desde \"celiac-friendly\" hasta \"opciones disponibles\") y, crucialmente, leer reseñas detalladas sobre la experiencia sin gluten, incluyendo notas sobre la contaminación cruzada y la capacitación del personal. Su modelo crowdsourcing lo mantiene actualizado y relevante. De manera similar, aunque con un foco más local, las bases de datos gestionadas por las Asociaciones de Celiacos nacionales (como la Federación de Asociaciones de Celiacos de España, FACE) ofrecen listados verificados que garantizan un estándar de seguridad mucho más alto, ya que los establecimientos suelen pasar por una auditoría o un proceso de acreditación. Estas guías, a menudo disponibles como aplicaciones o listados en PDF/web, son el estándar de oro para la seguridad en cada país.\r\n\r\nMás allá de las herramientas específicas, las plataformas de reservas y reseñas de restaurantes de propósito general también han evolucionado. Al utilizar Google Maps o TripAdvisor, la clave está en usar filtros específicos o en realizar búsquedas con términos exactos como \"sin gluten certificado\" o \"celiac-safe\" dentro de las reseñas. El volumen de datos de estas plataformas asegura que siempre habrá menciones de las experiencias de otros comensales. Otra estrategia muy efectiva es buscar en Instagram y TikTok a través de hashtags geolocalizados como #singlutenbarcelona o #glutenfreevienna; muchos restaurantes pequeños o pop-ups que ofrecen opciones seguras anuncian sus menús inclusivos directamente en estas redes sociales antes de ser listados en las apps principales.\r\n\r\nFinalmente, para la planificación de viajes más largos y complejos, Gluten Free Travel Site actúa como un agregador de información, ofreciendo consejos, guías de destinos y una comunidad de foros donde se pueden intercambiar experiencias y advertencias sobre restaurantes y supermercados específicos. En resumen, la combinación de las tarjetas de comunicación (Gluten Free Passport), las bases de datos de usuarios (Find Me Gluten Free) y las listas verificadas por asociaciones (FACE) proporciona al celíaco un kit de herramientas digital robusto, permitiendo que la alimentación segura sea un proceso proactivo y no una reacción ansiosa ante cada nueva ciudad.', 22, 'blog/apps-restaurantes', '2025-12-04 05:08:34', 180, 'PENDIENTE'),
(16, '2025-12-04 05:10:43', '¿Por Qué la Hinchazón? Intolerancias Comunes y Alimentos a Evitar para un Vientre Plano', 'Una guía para identificar las causas subyacentes de la distensión abdominal crónica, analizando la fermentación de FODMAPs y el rol de las alergias e intolerancias más comunes.', 'El Misterio de la Hinchazón Crónica: Identificando a los Culpables\r\nLa hinchazón o distensión abdominal es una queja digestiva extremadamente común, a menudo caracterizada por una sensación de plenitud, presión y un aumento visible del perímetro abdominal, lo que puede causar dolor y malestar significativos. Si bien la hinchazón ocasional puede deberse simplemente a tragar aire al comer rápido o a un exceso de gas por un plato rico en fibra, la hinchazón crónica y recurrente suele ser un indicio de que el cuerpo está reaccionando negativamente a ciertos alimentos.\r\n\r\nPara abordar la hinchazón de manera efectiva, es crucial ir más allá de los remedios superficiales y explorar las dos causas principales relacionadas con la alimentación: las intolerancias y alergias digestivas y la fermentación excesiva de carbohidratos no digeridos.\r\n\r\nSección I: Intolerancias y Alergias Digestivas\r\nEstas condiciones implican una incapacidad del organismo para descomponer o procesar ciertos componentes alimentarios.\r\n\r\n1. La Intolerancia a la Lactosa\r\nCausa: Deficiencia de la enzima lactasa, necesaria para descomponer la lactosa (el azúcar presente en la leche) en glucosa y galactosa.\r\n\r\nMecanismo de Hinchazón: La lactosa no digerida pasa al intestino grueso, donde las bacterias intestinales la fermentan rápidamente. Este proceso produce una gran cantidad de gases (hidrógeno, dióxido de carbono y metano), que causan distensión, calambres y, a menudo, diarrea.\r\n\r\nAlimentos a Evitar: Leche (especialmente entera), helados, yogures no fermentados (algunos tienen menos lactosa) y quesos frescos. Los quesos curados y la mantequilla suelen ser mejor tolerados debido a su bajo contenido de lactosa.\r\n\r\n2. El Gluten y la Celiaquía/Sensibilidad\r\nCausa: Reacción inmunológica (celiaquía) o no inmunológica (sensibilidad al gluten no celíaca, SGNC) a las proteínas del trigo, el centeno y la cebada.\r\n\r\nMecanismo de Hinchazón: En la celiaquía, el gluten daña las vellosidades del intestino, provocando una mala absorción de nutrientes y una inflamación crónica. La hinchazón es uno de los síntomas cardinales, debido a la inflamación y la fermentación de alimentos mal digeridos. En la SGNC, la hinchazón es una respuesta inflamatoria directa que ocurre poco después de la ingesta.\r\n\r\nAlimentos a Evitar: Pan, pasta, bollería, cerveza, sémola, cuscús y muchos alimentos procesados que contienen trigo.\r\n\r\n3. Intolerancia a la Fructosa y Sacarosa\r\nCausa: Problemas con los transportadores de azúcar en el intestino delgado (GLUT-5 para la fructosa).\r\n\r\nMecanismo de Hinchazón: Cuando el intestino no puede absorber completamente estos azúcares, llegan al colon, donde las bacterias los descomponen, generando gases. Es particularmente común con la fructosa.\r\n\r\nAlimentos a Evitar (si se sospecha): Frutas de alto contenido en fructosa (manzana, pera, mango, sandía), miel y sirope de agave.\r\n\r\nSección II: La Fermentación de Carbohidratos (FODMAPs)\r\nPara muchas personas, la hinchazón se debe a la ingestión de carbohidratos de cadena corta que son fermentables, oligosacáridos, disacáridos, monosacáridos y polioles (FODMAPs). Estos son pobremente absorbidos por todas las personas, pero causan problemas solo en aquellas con un intestino sensible, como las personas con Síndrome del Intestino Irritable (SII).\r\n\r\nMecanismo de Hinchazón: Los FODMAPs no se absorben completamente en el intestino delgado. Llegan al intestino grueso y son devorados por las bacterias, creando rápidamente una gran cantidad de gas. Además, son osmóticamente activos, lo que significa que atraen agua al intestino, contribuyendo aún más a la distensión.\r\n\r\nAlimentos Ricos en FODMAPs a Considerar Limitar:\r\nOligosacáridos (Fructanos y Galactanos):\r\n\r\nTrigo: Sí, además del gluten, el trigo es alto en fructanos.\r\n\r\nLegumbres: Frijoles, lentejas, garbanzos (debido a los galactanos).\r\n\r\nVerduras: Cebolla, ajo, espárragos, puerros.\r\n\r\nDisacáridos (Lactosa):\r\n\r\nLeche y derivados (ya mencionado).\r\n\r\nMonosacáridos (Fructosa en exceso):\r\n\r\nMiel, jarabe de maíz de alta fructosa y algunas frutas.\r\n\r\nPolioles (Alcoholes de Azúcar):\r\n\r\nEdulcorantes artificiales como sorbitol, xilitol y manitol (comunes en chicles, caramelos sin azúcar y algunos suplementos).\r\n\r\nCiertas frutas y verduras como champiñones, coliflor y aguacate.\r\n\r\nSección III: Otras Causas Dietéticas y Hábitos a Revisar\r\nAparte de las intolerancias específicas, ciertos hábitos y alimentos pueden exacerbar la hinchazón.\r\n\r\n1. Alimentos que Producen Gases Directamente\r\nVerduras Crucíferas: Brócoli, coliflor, repollo y coles de Bruselas. Contienen rafinosa, un azúcar que fermenta en el colon.\r\n\r\nBebidas Carbonatadas: El dióxido de carbono atrapado en estas bebidas se libera en el tracto digestivo, causando distensión inmediata.\r\n\r\nGoma de Mascar y Pajas: Masticar chicle y beber con pajita introduce aire excesivo en el estómago.\r\n\r\n2. Fibra y Volumen de Comida\r\nAumento Brusco de Fibra: Si la dieta es históricamente baja en fibra y se aumenta rápidamente, las bacterias del colon se encuentran con un festín, produciendo gas masivo. El aumento de la fibra debe ser gradual.\r\n\r\nComidas Copiosas: Comer demasiado rápido o en grandes volúmenes a la vez sobrecarga el sistema digestivo, lo que puede ralentizar el vaciado gástrico y provocar hinchazón.\r\n\r\nConclusión\r\nSi la hinchazón es un problema persistente, el camino más efectivo hacia el alivio comienza con la identificación y la eliminación temporal de los alimentos culpables. Esto generalmente implica un proceso de descarte gradual, comenzando por las intolerancias más comunes (lactosa y gluten), seguido de una posible dieta de eliminación de FODMAPs bajo supervisión profesional. La clave no está solo en evitar lo que causa el gas, sino en sanar y calmar el intestino para reducir su sensibilidad a la fermentación normal y restaurar una digestión cómoda.', 23, 'blog/hinchazon-alimentos', '2025-12-04 05:10:43', 45, 'PENDIENTE');
INSERT INTO `post` (`id_post`, `fecha_publicacion`, `titulo`, `subtitulo`, `contenido`, `id_usuario`, `url_post`, `fecha_validacion`, `valoracion`, `estado`) VALUES
(17, '2025-12-04 05:13:07', 'Sustitutos del Huevo: Ingredientes Clave para Cohesión y Textura en Recetas Sin Gluten', 'Descubre las alternativas funcionales que replican el poder aglutinante, la humedad y el volumen del huevo, desde semillas mucilaginosas hasta purés de frutas y almidones especiales', 'El huevo es una potencia funcional en la cocina y, especialmente, en la repostería. Cumple cuatro roles esenciales: aglutinante (mantiene unidos los ingredientes), leudante (aporta volumen y esponjosidad gracias a las proteínas batidas), humectante (añade humedad y grasa) y emulsionante (une grasas y líquidos). Al eliminar el huevo de una receta sin gluten (por alergia o veganismo), el desafío se duplica, ya que se están sustituyendo dos aglutinantes clave: el gluten y el huevo. Sin embargo, existen numerosos sustitutos que, combinados o por sí solos, pueden replicar estas funciones de manera efectiva. El secreto está en entender qué función del huevo es la más importante para cada receta. Por ejemplo, en galletas planas, se necesita más cohesión y humedad; en un bizcocho, se requiere más capacidad leudante.Para la función de aglutinante y humectante (ideal para galletas, panes densos y muffins):Semillas de Chía o Lino Molidas: Las semillas de lino (linaza) o chía, al mezclarse con agua, liberan un mucílago gelatinoso que imita la consistencia del huevo batido. Se utiliza la proporción de 1 cucharada de semillas molidas + 3 cucharadas de agua = 1 huevo. Esta mezcla, conocida como \"huevo de lino\" o \"huevo de chía,\" es excelente para ligar masas.Purés de Fruta: El puré de manzana sin azúcar, el plátano machacado o el puré de calabaza aportan humedad, dulzor y aglutinación. $1/4$ de taza (unos 60 gramos) de puré = 1 huevo. Son ideales para recetas dulces, ya que su sabor puede ser dominante.Tofu Seda (Silken Tofu): Al licuarlo, se obtiene una crema muy fina que puede reemplazar el huevo, aportando humedad y proteína. Es excelente para cheesecakes veganos y quiches.Para la función de leudante y emulsionante (esencial para bizcochos esponjosos y panes ligeros):Almidones y Bicarbonato: Una mezcla de 1 cucharada de harina de tapioca o maicena junto con 1 cucharadita de bicarbonato de sodio y 1 cucharada de vinagre de manzana puede ayudar a la aireación. La reacción ácido-base genera burbujas que simulan el efecto aireador del huevo.Aquafaba: El líquido de cocción de los garbanzos (o de la lata) es un emulsionante extraordinario gracias a las proteínas que contiene. Batir 3 cucharadas de aquafaba = 1 huevo puede crear merengues, mousses y bizcochos ligeros.Proteína Vegetal en Polvo: Algunas proteínas veganas (como la proteína de guisante o arroz) pueden usarse en muffins y panes, aportando estructura y evitando que la masa se colapse. Se recomienda mezclarla con una pequeña cantidad de líquido antes de añadirla a la receta seca.Al trabajar en recetas sin gluten, donde ya hay una deficiencia estructural, se recomienda no sustituir más de tres huevos por estos métodos. Además, si la receta original requiere varios huevos, a menudo es mejor usar una combinación de sustitutos (por ejemplo, puré para humedad y aquafaba para levantar) para abordar todas las funciones necesarias.', 24, 'blog/sustituir-huevo', '2025-12-04 05:13:07', 60, 'PENDIENTE'),
(18, '2025-12-04 05:14:45', 'Elaboración Casera: Aprende a Hacer Tu Propia Harina de Arroz de Manera Sencilla', 'Una guía práctica para moler arroz en casa utilizando una batidora o molinillo. Consejos para elegir el grano perfecto y lograr una textura fina sin esfuerzo.', 'La harina de arroz es un ingrediente básico en la panadería y repostería sin gluten, así como en muchas cocinas asiáticas. Es neutra en sabor, fácil de digerir y proporciona una textura ligera y tierna a los productos horneados. Aunque se encuentra fácilmente en tiendas, hacerla en casa ofrece varias ventajas: control total sobre la frescura, el tipo de grano utilizado (blanco, integral, glutinoso) y, a menudo, un ahorro significativo. El proceso es sorprendentemente sencillo y solo requiere un ingrediente (el arroz) y un electrodoméstico potente.Paso 1: Selección del Grano (La Base de la Harina)La calidad y el tipo de arroz determinarán la funcionalidad de la harina resultante.Harina de Arroz Blanco Estándar: Utiliza arroz de grano medio o largo (tipo Basmati o Jazmín). Estos arroces son bajos en almidón amilopectina, lo que resulta en una harina más fluida, ideal para repostería y para espesar salsas.Harina de Arroz Integral: Utiliza arroz integral. El resultado será una harina mucho más nutritiva y con un sabor más intenso. Debido al aceite y la fibra de la cáscara, esta harina es más densa y se estropea más rápido; se recomienda guardarla en el refrigerador.Harina de Arroz Glutinoso (Mochiko): Utiliza arroz glutinoso (también conocido como sweet rice o arroz pegajoso). Esta harina es esencial para postres de textura elástica y chiclosa (como el mochi), ya que contiene una alta concentración de amilopectina.Paso 2: Preparación del Grano (Limpieza y Secado)Antes de moler, es crucial asegurarse de que el arroz esté perfectamente limpio y completamente seco.Lavado: Opcionalmente, puedes enjuagar el arroz bajo el grifo para eliminar el exceso de almidón y cualquier impureza superficial. Si lo lavas, déjalo escurrir en un colador.Secado (Paso Crucial): El arroz debe estar completamente seco antes de molerlo. Extiende el arroz en una capa fina sobre un paño de cocina limpio o una bandeja de horno y déjalo secar al aire durante varias horas, o déjalo en el horno a la temperatura más baja posible (unos $50^\\circ\\text{C}$ o menos) durante 30 a 45 minutos. La presencia de humedad arruinará el proceso, ya que el arroz se convertirá en una pasta espesa en lugar de un polvo fino.Paso 3: El Proceso de Molienda (Transformación)Necesitarás un molinillo de café o una licuadora de alta potencia (tipo Vitamix o Thermomix).Muele en Tandas Pequeñas: No sobrecargues el aparato. Muele solo $1/2$ taza o 1 taza de arroz a la vez. Esto garantiza que las cuchillas puedan moler uniformemente y evita que el motor se sobrecaliente.Molienda en Pulsos: Tritura el arroz a alta velocidad usando ciclos cortos (pulsos) de 30 a 45 segundos. Si usas una licuadora o procesador, la fricción genera calor, lo que puede cocinar ligeramente el almidón y afectar la textura. Detén la máquina, agita el recipiente y repite el proceso.Inspección: Continúa moliendo hasta que el arroz se haya transformado en un polvo fino y uniforme. Sabrás que está lista cuando la textura sea similar a la harina de trigo o al azúcar glas. La molienda completa puede tardar entre 2 y 4 minutos en total, dependiendo de la potencia del electrodoméstico.Paso 4: Tamizado y Almacenamiento (La Perfección Final)El tamizado es la clave para eliminar cualquier gránulo de arroz que no se haya molido por completo.Tamizado: Pasa la harina por un tamizador o un colador de malla muy fina. Los trozos grandes que queden en el tamiz se pueden volver a moler o desechar. Este paso asegura la consistencia ligera que necesita la repostería.Enfriamiento: Deja que la harina repose durante unos minutos antes de guardarla. Estará ligeramente caliente por la fricción.Almacenamiento: Guarda la harina de arroz en un recipiente hermético. La harina de arroz blanco se conserva bien a temperatura ambiente en la despensa, mientras que la harina de arroz integral debe guardarse en el refrigerador para evitar que las grasas de la cáscara se enrancien.', 1, 'blog/harina-arroz-casera', '2025-12-04 05:14:45', 82, 'PENDIENTE'),
(19, '2025-12-04 05:17:40', 'Más Allá del Puré: 10 Recetas Creativas, Saladas y Dulces con Calabaza', 'Explora el potencial culinario de la calabaza con platos innovadores. Desde curries cremosos hasta gnocchis caseros, ideas para aprovechar su sabor y textura únicos.', 'La calabaza (ya sea calabaza de invierno, butternut o la clásica pumpkin) es la reina indiscutible del otoño y el invierno. Su dulzor natural, su textura sedosa y su vibrante color naranja, indicativo de su riqueza en betacarotenos y vitamina A, la convierten en un ingrediente versátil. Sin embargo, su uso a menudo se limita al puré, la crema de sopa o el tradicional pumpkin pie. Explorar la calabaza más allá de estas preparaciones revela un mundo de posibilidades en platos que van de lo salado a lo dulce, demostrando su capacidad para aportar cuerpo, humedad y un sabor inigualable.\r\n\r\nA continuación, se presentan diez ideas creativas para incorporar la calabaza en tu cocina, elevándola de simple guarnición a protagonista del plato.\r\n\r\nSección I: Platos Salados (Estructura y Sabor)\r\nLa calabaza, al ser asada o cocida, aporta una riqueza y una cremosidad natural que puede reemplazar la nata o el queso en muchas recetas, ideal para platos vegetarianos y plant-based.\r\n\r\n1. Gnocchi Casero de Calabaza y Salvia\r\nDescripción: Sustituye parte de la patata por puré de calabaza asada en la masa de los gnocchi. Esto les da un color naranja hermoso y una textura sorprendentemente suave.\r\n\r\nToque Final: Servir salteados en mantequilla clarificada o aceite de oliva con salvia fresca y nueces tostadas.\r\n\r\n2. Curry Tailandés de Calabaza y Garbanzos\r\nDescripción: La calabaza es la base de la cremosidad. Se cocina a fuego lento en leche de coco con pasta de curry rojo o verde.\r\n\r\nPotencia: La calabaza, una vez cocida, se deshace ligeramente en la salsa, aportando dulzor natural que equilibra el picante del curry y el sabor terroso de los garbanzos.\r\n\r\n3. Tostada Rústica con Hummus de Calabaza\r\nDescripción: Se prepara un hummus clásico (garbanzos, tahini, limón) y se le añade una porción generosa de calabaza asada.\r\n\r\nUso: Ideal para untar en tostadas sin gluten o como dip en una tabla de aperitivos. Se espolvorea con semillas de calabaza tostadas.\r\n\r\n4. Chili Vegano de Calabaza y Frijoles Negros\r\nDescripción: Un guiso robusto donde la calabaza cortada en cubos se cocina junto a los frijoles negros, tomates y maíz.\r\n\r\nTextura: La calabaza se ablanda, aportando una riqueza y espesor al chili sin necesidad de espesantes, creando un plato principal sustancioso.\r\n\r\n5. Risotto Cremoso de Calabaza y Queso de Cabra\r\nDescripción: La calabaza asada se tritura y se añade al arroz Arborio a mitad de la cocción.\r\n\r\nResultado: El almidón de la calabaza contribuye a la cremosidad del risotto junto con el queso rallado. Se finaliza con un toque de queso de cabra desmenuzado y pimienta negra.\r\n\r\nSección II: Opciones Dulces y Repostería (Humedad y Sabor)\r\nEn la repostería, la calabaza funciona como un reemplazo de grasas y huevos, aportando humedad sin gluten ni lácteos.\r\n\r\n6. Muffins Sin Gluten de Calabaza y Especias\r\nDescripción: El puré de calabaza reemplaza gran parte del aceite o la mantequilla, aportando una humedad increíble a la masa sin gluten (hecha con harinas como la de arroz, almendra o sarraceno).\r\n\r\nSabor: Requiere una mezcla generosa de especias (pumpkin spice): canela, nuez moscada, jengibre y clavo.\r\n\r\n7. Trufas de Calabaza y Chocolate Blanco\r\nDescripción: Una mezcla densa de galletas sin gluten trituradas, puré de calabaza, especias y un poco de azúcar. Se forman bolas y se congelan.\r\n\r\nToque Final: Se bañan en chocolate blanco derretido. Son un postre elegante y rápido, ideal para fiestas.\r\n\r\n8. Flan o Natillas de Calabaza Veganas\r\nDescripción: Se utiliza puré de calabaza, leche de coco o almendras, y un agente cuajante como el agar-agar o la maicena.\r\n\r\nResultado: Una natilla suave y sedosa, perfecta para servir fría, espolvoreada con canela y caramelo de dátil.\r\n\r\n9. Budín de Chía y Calabaza (Overnight Pudding)\r\nDescripción: Las semillas de chía se hidratan durante la noche en leche vegetal con puré de calabaza y especias.\r\n\r\nDesayuno/Postre: Una opción saludable y alta en fibra, lista para comer por la mañana o como postre ligero.\r\n\r\n10. Tostadas Francesas de Calabaza (Pumpkin French Toast)\r\nDescripción: Se mezcla el puré de calabaza con huevo (o un sustituto vegano como el huevo de lino), leche y especias.\r\n\r\nUso: Se sumerge pan de molde sin gluten en esta mezcla y se fríe en la sartén. El resultado es un desayuno o postre decadente, con el sabor inconfundible de la calabaza especiada.', 3, 'blog/recetas-calabaza', '2025-12-04 05:17:40', 55, 'PENDIENTE'),
(20, '2025-12-04 05:18:29', 'El Placer Seguro: Claves para Elegir las Mejores Marcas de Chocolate Sin Gluten Certificado', 'Aprende a decodificar el etiquetado, a diferenciar entre declaraciones de seguridad y a priorizar el cacao de alta calidad para disfrutar sin riesgo de contaminación cruzada.', 'El chocolate, en su forma más pura (cacao, manteca de cacao y azúcar), es intrínsecamente libre de gluten. Sin embargo, en el mundo de los chocolates comerciales y procesados, las posibilidades de contaminación cruzada o la adición de ingredientes que contienen gluten (como malta de cebada, galletas, oblea) son extremadamente altas. Para el consumidor celíaco o sensible al gluten, la elección no se basa únicamente en el sabor, sino en la seguridad.\r\n\r\nSeleccionar la mejor marca de chocolate sin gluten requiere un ojo crítico para el etiquetado y una comprensión de las prácticas de fabricación. Aquí están las claves esenciales para garantizar que tu elección sea deliciosa y, sobre todo, segura.\r\n\r\n1. La Máxima Prioridad: Buscar la Certificación Oficial\r\nLa clave más importante para la seguridad es ir más allá de la simple lista de ingredientes.\r\n\r\nEl Sello de Garantía: La mejor marca será aquella que ostente un sello de certificación oficial (como la Espiga Barrada \"Gluten-Free\" de las asociaciones nacionales o el símbolo internacional). Este sello indica que el producto ha sido probado y cumple con los estándares legales de contenido de gluten (generalmente menos de 20 ppm) y, crucialmente, que la fábrica sigue protocolos para evitar la contaminación cruzada.\r\n\r\nDiferencia vs. Etiquetado: No te conformes solo con la frase \"Sin Gluten\" (Gluten-Free) impresa en la parte trasera. Aunque es un requisito legal, un sello certificado implica un nivel de auditoría externa y rigor en la producción que va más allá de la simple declaración del fabricante.\r\n\r\n2. Analizar el Etiquetado de Advertencia (Contaminación Cruzada)\r\nSi el chocolate no está certificado, el siguiente paso es analizar el etiquetado de alérgenos.\r\n\r\nLa Bandera Roja: Evita cualquier chocolate que contenga las frases de advertencia: \"Puede contener trazas de trigo/gluten\" o \"Fabricado en líneas donde se procesa trigo/gluten\". Estas frases son una admisión directa de que el riesgo de contaminación cruzada en la maquinaria compartida es real.\r\n\r\nLa Declaración Nula: Si la etiqueta simplemente no menciona nada sobre gluten (ni como ingrediente ni como advertencia), es una señal de peligro, ya que el fabricante no ha evaluado el riesgo de contaminación cruzada.\r\n\r\n3. Priorizar el Chocolate Negro y Puro\r\nLa simplicidad de los ingredientes está directamente correlacionada con la seguridad.\r\n\r\nRegla del Mínimo Ingrediente: Cuantos menos ingredientes tenga el chocolate, menor será la probabilidad de que se introduzca gluten accidentalmente. Las marcas de calidad superior (con un alto porcentaje de cacao, 70% o más) a menudo solo contienen pasta de cacao, manteca de cacao y azúcar.\r\n\r\nCuidado con los Aditivos: El riesgo aumenta exponencialmente en los chocolates con rellenos, toppings o aditivos:\r\n\r\nChocolate con Leche: Mayor riesgo de aditivos estabilizantes o espesantes.\r\n\r\nRellenos: Barquillos, galletas, malta de cebada, cereales crujientes (a menudo contienen gluten).\r\n\r\nSaborizantes: Algunos aromas artificiales pueden usar el gluten como portador.\r\n\r\n4. Evaluar la Base del Cacao (Calidad y Sabor)\r\nUna vez confirmada la seguridad, la \"mejor\" marca es aquella que ofrece la mejor experiencia organoléptica.\r\n\r\nPorcentaje de Cacao: Elige un porcentaje de cacao que se adapte a tu paladar, pero recuerda que a mayor porcentaje, menor azúcar y, generalmente, mayor intensidad de sabor.\r\n\r\nOrigen y Procesamiento: Las mejores marcas de chocolate a menudo indican el origen del grano (Perú, Venezuela, Ecuador) y el tiempo de conchado. Una buena marca de chocolate sin gluten será, inherentemente, una buena marca de chocolate de calidad, que utiliza granos finos de aroma y no rellenos.\r\n\r\n5. Considerar Marcas Especializadas o de Alto Nivel\r\nLas pequeñas marcas artesanales o las grandes marcas que tienen líneas de producción dedicadas a productos free-from suelen ser la apuesta más segura.\r\n\r\nFábricas Dedicadas: Algunas marcas especializadas en el segmento free-from operan en instalaciones donde nunca entra el trigo. Esto elimina el riesgo de contaminación cruzada en el origen.\r\n\r\nEl Factor Precio: Generalmente, los chocolates de alta calidad y certificados sin gluten tienen un precio superior. Este costo adicional refleja la pureza del cacao y la inversión en protocolos de seguridad y pruebas de laboratorio para la certificación.\r\n\r\nAl final, la elección de la mejor marca de chocolate sin gluten implica un proceso de eliminación: primero, eliminar el riesgo de la contaminación cruzada mediante la certificación; y luego, elegir la opción que ofrece la mejor calidad de cacao, sabor y consistencia.', 4, 'blog/chocolate-sin-gluten', '2025-12-04 05:18:29', 105, 'PENDIENTE'),
(21, '2025-12-04 05:19:20', '🧀 El Potencial Umami: La Levadura Nutricional como Sustituto Versátil del Queso', 'El uso de la levadura nutricional como sustituto del queso.', 'La levadura nutricional, conocida cariñosamente como \"nooch\" en las comunidades veganas y de dietas especiales, es mucho más que un suplemento vitamínico. Se trata de una forma desactivada de la levadura Saccharomyces cerevisiae, que se cultiva específicamente para el consumo humano y se comercializa en forma de escamas amarillentas y ligeras. Su impacto en la cocina, particularmente como sustituto del queso, reside en su perfil de sabor y sus propiedades nutricionales únicas. El secreto de su éxito radica en su intenso sabor umami natural, que proporciona un toque salado, sabroso y ligeramente \"quesoso\" sin la necesidad de lácteos, grasas o, crucialmente, gluten.\r\n\r\nEl uso de la levadura nutricional como sustituto del queso se articula en torno a dos funciones primarias: el sabor y la textura. En términos de sabor, es la presencia de altos niveles de ácido glutámico libre lo que le confiere su potente sabor umami, replicando el perfil de sabor rico y complejo de los quesos madurados como el parmesano o el cheddar. Esto la hace indispensable para espolvorear directamente sobre pastas, palomitas de maíz o ensaladas, donde actúa como un topping seco y sabroso.\r\n\r\nEn cuanto a la textura, la levadura nutricional es clave en la creación de \"salsas de queso\" veganas. Al licuar las escamas con ingredientes cremosos como anacardos remojados, tofu sedoso o incluso patatas y zanahorias cocidas, se obtiene una base espesa y untuosa que, gracias al sabor del nooch, imita la riqueza y la profundidad de una salsa cheddar o de una salsa para nachos. Esta técnica permite la creación de mac-and-cheese veganos, salsas para pizza o rellenos de quiches sin utilizar lácteos ni espesantes que contengan gluten, lo que la convierte en una herramienta doblemente valiosa para quienes manejan restricciones dietéticas múltiples. Su versatilidad y su riqueza en vitaminas del grupo B, incluyendo a menudo la B12 (en versiones fortificadas), solidifican su posición no solo como un ingrediente funcional, sino como un elemento nutritivo esencial en la cocina inclusiva.', 5, 'blog/levadura-nutricional', '2025-12-04 05:19:20', 90, 'PENDIENTE'),
(22, '2025-12-04 05:22:44', 'Un Año Sin Gluten: Transformación, Desafíos Ocultos y la Vida Después del Diagnóstico', 'Relato honesto sobre la transición a la dieta estricta. La mejora sintomática, el impacto social, los errores de principiante y las lecciones aprendidas después de doce meses.', 'La decisión de eliminar el gluten de mi dieta, hace exactamente un año, no fue una elección de moda ni un experimento nutricional. Fue una necesidad impuesta por un diagnóstico médico (ya sea de enfermedad celíaca o de sensibilidad severa al gluten no celíaca) después de años de malestar crónico, fatiga y problemas digestivos inexplicables. Lo que comenzó como un cambio forzado pronto se convirtió en una transformación profunda que afectó no solo mi plato, sino mi salud mental, mis hábitos sociales y mi relación con la comida. Este es un relato honesto de mi experiencia durante este primer año comiendo 100% sin gluten.\r\n\r\nLos Primeros Tres Meses: Caos Logístico y Alivio Sintomático\r\nEl primer trimestre fue una mezcla confusa de frustración y asombro. La frustración venía de la limpieza exhaustiva de la cocina para eliminar la contaminación cruzada: deshacerme de la tostadora vieja, comprar tablas de cortar nuevas y leer cada etiqueta del supermercado con lupa. El factor tiempo se disparó; una simple compra de alimentos ahora me llevaba el doble de tiempo. Los errores de principiante eran inevitables: ese día que comí unas patatas fritas de bolsa que resultaron tener malta de cebada, o cuando confié en un aderezo de ensalada que contenía gluten oculto. Cada error se pagaba con una recaída sintomática que reforzaba la necesidad de ser estricto.\r\n\r\nSin embargo, el asombro llegó con la rápida mejoría física. La fatiga crónica que me había acompañado durante años empezó a disiparse. El \"cerebro nublado\" (brain fog) desapareció en gran medida, y mi distensión abdominal, que solía hacerme parecer embarazada al final del día, se redujo drásticamente. Entender que mis síntomas estaban directamente relacionados con una proteína tan omnipresente fue liberador, pero también abrumador.\r\n\r\nEl Semestre Intermedio: Desafíos Sociales y Aprendizaje Culinario\r\nEntre el tercer y el noveno mes, el enfoque cambió de la supervivencia al manejo social. Las salidas a restaurantes se convirtieron en un ejercicio de investigación previa y comunicación asertiva. Aprendí que la frase \"opción sin gluten\" no siempre significaba \"seguro para celíacos\", y que preguntar sobre la contaminación cruzada era vital, aunque a veces resultara incómodo. Las bodas, las cenas de empresa y las reuniones informales se convirtieron en campos minados logísticos.\r\n\r\nEsta etapa me obligó a ser un mejor cocinero. Dejé de depender de los sustitutos procesados (panes de almidón) y me volqué en alimentos naturalmente sin gluten. Descubrí el poder de los pseudocereales (quinoa, trigo sarraceno) y el psyllium husk para crear mis propios panes y postres con una calidad nutricional superior. Esto no solo mejoró mi dieta, sino que me hizo reconectar con la comida de una manera más consciente y gratificante. Aprendí a llevar siempre mi \"kit de emergencia\" (barritas proteicas seguras) para evitar caer en tentaciones o pasar hambre.\r\n\r\nEl Último Trimestre: Aceptación y Transformación a Largo Plazo\r\nAl llegar al año, la dieta sin gluten ya no se siente como una restricción, sino como mi forma normal de comer. La vigilancia sigue ahí, pero se ha automatizado. Las lecciones clave de este último trimestre son:\r\n\r\nPaz Mental: El mayor beneficio es la tranquilidad. Saber que estoy protegiendo mi cuerpo y mi intestino ha eliminado la ansiedad constante sobre mi salud.\r\n\r\nComunidad: Conectarse con otros celiacos, a través de redes sociales o asociaciones locales, fue crucial. Compartir recetas, marcas seguras y consejos de viaje hizo que el camino se sintiera menos solitario.\r\n\r\nNutrición: Dejé de comer de forma pasiva. Ahora priorizo la fibra (legumbres, frutas, verduras) para compensar la pérdida de cereales integrales y evitar los problemas comunes de deficiencia de hierro o vitamina B.\r\n\r\nMi experiencia de un año comiendo 100% sin gluten ha sido un viaje de autodescubrimiento. Me ha enseñado disciplina, paciencia y a no dar por sentado el bienestar digestivo. El gluten se ha ido, pero el bienestar, la energía y la conciencia nutricional han llegado para quedarse.', 6, 'blog/experiencia-un-año', '2025-12-04 05:22:44', 4, 'PENDIENTE'),
(23, '2025-12-08 08:47:00', 'Los 5 lugares donde el gluten se esconde y no lo sabes: Guía para celíacos', 'Salsas, cosméticos, medicamentos y otros productos inesperados que pueden arruinar tu dieta. Aprende a leer la etiqueta como un experto.', 'El gluten no siempre se presenta en forma de pan o pasta. Para el celíaco, es crucial convertirse en un detective de etiquetas. Más allá de la harina de trigo, debes buscar el **Extracto de Malta** (derivado de cebada), el **Almidón Modificado** (si no especifica su origen seguro, como maíz o patata) y la **Levadura de Cerveza**. Además, es vital revisar los productos de uso diario: algunos lápices labiales, bálsamos, pasta de dientes y, lo más importante, medicamentos y suplementos pueden contener gluten como excipiente o aglutinante. Siempre consulta con tu farmacéutico sobre la composición de cualquier pastilla nueva y prioriza los productos no alimentarios etiquetados como \"Sin Gluten\".', 10, 'blog/gluten-oculto-celiacos', '2025-12-08 08:47:00', 50, 'PENDIENTE'),
(24, '2025-12-08 08:47:00', 'Cómo mantener una dieta sin gluten rica en fibra y vitaminas del grupo B', 'Evita la trampa de los almidones refinados. Sustituye el trigo por pseudocereales (quinoa, sarraceno) y legumbres para la salud intestinal.', 'Al eliminar el trigo y otros cereales enriquecidos, el celíaco corre el riesgo de sufrir deficiencias de fibra, hierro y vitaminas del grupo B (Folato, Tiamina, Niacina). La solución no es depender de los productos procesados sin gluten (que a menudo son bajos en estos nutrientes), sino basar la dieta en alimentos naturalmente ricos: **1. Fibra:** Incorpora legumbres (lentejas, garbanzos) diariamente, frutas y verduras frescas, y pseudocereales como la quinoa y el trigo sarraceno. **2. Vitaminas B y Hierro:** Las carnes rojas magras, las lentejas y los vegetales de hoja verde son cruciales para el hierro y el folato. Consulta a un dietista-nutricionista para evaluar si necesitas suplementación.', 10, 'blog/fibra-vitaminas-singluten', '2025-12-08 08:47:00', 50, 'PENDIENTE'),
(25, '2025-12-08 08:47:00', 'El arte de comer fuera: 5 frases clave para asegurar un plato sin gluten en cualquier restaurante', 'De la contaminación cruzada a los ingredientes ocultos: consejos prácticos para comunicarte con el chef y disfrutar sin ansiedad.', 'Disfrutar de un restaurante requiere comunicación. Sé proactivo y claro: **1. Pregunta por la preparación:** \"¿Se utiliza una freidora separada para las patatas fritas?\". **2. Sé específico con los espesantes:** \"¿La salsa o el guiso lleva harina de trigo como espesante?\". **3. Menciona la celiaquía:** No digas solo \"dieta\", di \"Necesito un plato totalmente libre de gluten por una condición médica (celiaquía)\". **4. El pan es clave:** Pide que el pan sin gluten se tueste en un horno limpio o se sirva sin calentar, nunca en la tostadora común. **5. Simplifica:** Si tienes dudas, elige la opción naturalmente segura y simple, como carne o pescado a la plancha con verduras cocidas.', 10, 'blog/comer-fuera-seguro', '2025-12-08 08:47:00', 50, 'PENDIENTE'),
(26, '2025-12-08 08:47:00', 'La Tostadora y el Colador: 3 reglas de oro para evitar la contaminación cruzada en casa', 'Protocolos de seguridad esenciales para cocinas compartidas. Designa utensilios, establece jerarquías de preparación y almacena correctamente los alimentos.', 'En una cocina donde conviven alimentos con y sin gluten, la contaminación cruzada es el mayor peligro. **Regla 1: Utensilios Exclusivos:** Designa una tostadora (o bolsas tostadoras), un colador y una tabla de cortar (preferiblemente de plástico o acero) solo para alimentos sin gluten. **Regla 2: Almacenamiento Vertical:** En la despensa y el frigorífico, guarda los alimentos sin gluten en los estantes superiores para evitar que las migas o goteos de los productos con gluten contaminen los de abajo. **Regla 3: Jerarquía y Limpieza:** Lava primero los utensilios y limpia las superficies de trabajo (con paños o papel exclusivos) antes de empezar a manipular los alimentos seguros.', 10, 'blog/contaminacion-cruzada-casa', '2025-12-08 08:47:00', 50, 'PENDIENTE'),
(27, '2025-12-08 08:47:00', 'Sustitutos Estructurales: Goma Xantana y Psyllium Husk para pan sin gluten perfecto', 'La clave para evitar panes desmoronados. Cómo usar estos hidrogeles para darle elasticidad y volumen a tus masas caseras.', 'El gluten proporciona la estructura y elasticidad esencial para que el pan suba y no se desmorone. En la panadería sin gluten, estos dos ingredientes funcionales son esenciales: **1. Psyllium Husk (Cáscara de Zaragatona):** Al hidratarse, forma un gel viscoelástico que imita la red del gluten. Aporta volumen, una miga más suave y facilita el manejo de la masa. Debe prehidratarse en líquido antes de mezclar con las harinas. **2. Goma Xantana:** Actúa como estabilizador y emulsionante, ligando los ingredientes secos con los líquidos y proporcionando cohesión. Una pequeña cantidad es suficiente para mejorar la textura de cualquier bizcocho o pan.', 10, 'blog/psyllium-xantana-pan', '2025-12-08 08:47:00', 50, 'PENDIENTE'),
(28, '2025-12-10 17:20:12', '¿Cómo se diagnostica la enfermedad celiaca?', 'Para realizar un diagnóstico de forma correcta es necesario que el paciente no retire el gluten de su dieta. En caso contrario las pruebas pueden arrojar resultados falsamente negativos.', 'Para confirmar un diagnóstico positivo es necesario cumplir al menos cuatro de estos cinco criterios diagnósticos:\n\nSospecha clínica. Se valora la existencia de síntomas y signos característicos de la enfermedad y el historial médico del paciente.\nSerología. Mediante un análisis de sangre se estudia la existencia de los indicadores serológicos (anticuerpos antigliadina, antiendomisio y antitransglutaminasa tisular) característicos de esta enfermedad\nPruebas genéticas. El estudio genético analiza la presencia de los genes que informan sobre la predisposición a padecer la enfermedad celiaca. Son útiles para el descarte de esta patología.\nBiopsia. Es la prueba definitiva para establecer el diagnóstico de la enfermedad celiaca. Se realiza una biopsia del duodeno: se extrae una muestra de tejido del intestino delgado superior para ver si tiene lesiones intestinales.\nDieta sin gluten. Es el estudio del efecto de la dieta sin gluten y si el paciente experimenta mejoría.\nSe estima que la prevalencia entre la población europea y sus descendientes es del 1 %, siendo más frecuente en las mujeres con una proporción 2:1, aunque un porcentaje importante de pacientes (75 %) está sin diagnosticar.\n\n', 36, 'https://celiacos.org/enfermedad-celiaca/como-se-diagnostica-la-enfermedad-celiaca/', NULL, 5, 'PENDIENTE');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `receta`
--

CREATE TABLE `receta` (
  `id_receta` int NOT NULL,
  `id_usuario` int NOT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `titulo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `subtitulo` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `tipo_comida` enum('DESAYUNO','BRUNCH','ALMUERZO','MERIENDA','CENA','TAPAS','RACIONES','POSTRE','SALADO','DULCE') COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `descripcion` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `dificultad` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `valoracion` int DEFAULT NULL,
  `tiempo_preparacion` int NOT NULL,
  `valor_energetico` int NOT NULL,
  `comensales` int NOT NULL,
  `imagen_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `estado` enum('PENDIENTE','APROBADO','RECHAZADO') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT 'PENDIENTE',
  `fecha_validacion` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `receta`
--

INSERT INTO `receta` (`id_receta`, `id_usuario`, `fecha_creacion`, `titulo`, `subtitulo`, `tipo_comida`, `descripcion`, `dificultad`, `valoracion`, `tiempo_preparacion`, `valor_energetico`, `comensales`, `imagen_url`, `estado`, `fecha_validacion`) VALUES
(1, 1, '2025-12-04 05:26:59', 'Pan de trigo sarraceno con semillas y textura artesanal', 'Pan artesanal nutritivo con sabor intenso y tradición culinaria milenaria', 'POSTRE', 'El pan de trigo sarraceno con semillas es una de las elaboraciones más representativas dentro de la panadería sin gluten, donde la tradición artesanal y la nutrición equilibrada se combinan para dar forma a un alimento saludable, denso y lleno de sabor. El trigo sarraceno, a pesar de su nombre, no es un cereal, sino un pseudocereal de gran historia en Europa y Asia. Su harina aporta una textura singular, ligeramente terrosa, con notas profundas que recuerdan a la nuez tostada. Cuando se combina con semillas como lino, chía o girasol, el resultado es un pan robusto, bien estructurado y con un carácter marcado que lo diferencia de cualquier pan convencional elaborado con trigo.\r\n\r\nA nivel nutricional, este tipo de pan sobresale por su alto contenido en proteínas vegetales y su aporte equilibrado de carbohidratos de lenta absorción. Su índice glucémico es más bajo que el de los panes tradicionales, lo que ayuda a mantener niveles de energía estables a lo largo del día. Además, el trigo sarraceno contiene minerales esenciales como magnesio, fósforo e hierro, junto con antioxidantes que contribuyen al bienestar general. Las semillas incorporadas no solo mejoran la textura y el sabor, sino que aumentan el valor nutricional gracias a sus grasas saludables, fibra y micronutrientes esenciales.\r\n\r\nEn su preparación, este pan requiere técnicas cuidadas, ya que la ausencia de gluten implica trabajar con masas más húmedas y menos elásticas. La mezcla adecuada de harina, semillas, agentes leudantes y reposos prolongados permite obtener una miga compacta pero suave, ideal para tostadas, acompañamientos o para consumir solo con aceite de oliva. Su corteza suele quedar crujiente y dorada, un contraste que resalta aún más cuando se tosta ligeramente.\r\n\r\nCulturalmente, el trigo sarraceno ha sido un alimento clave en distintas gastronomías. En Rusia se utiliza para preparar gachas; en Japón forma parte de los fideos soba; y en Bretaña se emplea para elaborar las famosas galettes. En los últimos años, su uso se ha extendido en la cocina saludable moderna gracias a su pureza natural y su total ausencia de gluten, haciéndolo apto para celíacos y para quienes buscan alternativas más nutritivas.\r\n\r\nEl pan de trigo sarraceno con semillas representa un equilibrio perfecto entre tradición y modernidad. Es un pan que invita a disfrutar de ingredientes naturales, técnicas artesanales y un sabor único que perdura en el paladar. Su versatilidad lo convierte en un alimento ideal para desayunos, meriendas o acompañamientos, y su riqueza nutricional lo posiciona como una opción excelente dentro de una alimentación consciente.', 'media', 3, 60, 320, 4, 'pan_trigo_sarraceno.png', 'RECHAZADO', NULL),
(2, 13, '2025-12-04 05:26:59', 'Bizcocho de almendra', 'Bizcocho esponjoso y aromático elaborado únicamente con harina pura de almendra', 'SALADO', 'El bizcocho de almendra es una de las elaboraciones más apreciadas dentro de la repostería sin gluten, gracias a su textura suave, su sabor delicado y su capacidad para mantenerse jugoso durante varios días. Elaborado exclusivamente con harina de almendra, este bizcocho combina simplicidad e intensidad aromática en cada bocado. La harina de almendra aporta una humedad característica y un dulzor natural, además de una estructura más densa y agradable que la de los bizcochos tradicionales elaborados con trigo.\r\n\r\nLa almendra, además de ser un ingrediente versátil y nutritivo, es rica en grasas saludables, proteínas vegetales, minerales como el magnesio y el calcio, y vitamina E, un antioxidante natural. Esto convierte al bizcocho no solo en un postre delicioso, sino también en una opción más equilibrada dentro de la repostería casera. Al no contener gluten, la masa requiere técnicas cuidadosas de mezcla y aireado, especialmente para conseguir una textura esponjosa sin necesidad de harina de trigo. El batido prolongado de huevos y azúcar, junto con la incorporación suave de la harina de almendra, permite obtener un resultado ligero y bien integrado.\r\n\r\nAromáticamente, este bizcocho destaca por su fragancia natural, que se puede potenciar con ralladura de cítricos, extracto de vainilla o un toque de canela. Su sabor combina perfectamente con ingredientes como frutas frescas, chocolate, miel o compotas naturales. Estas combinaciones permiten crear versiones más complejas para ocasiones especiales o mantener la receta básica para un desayuno o merienda nutritiva.\r\n\r\nEn cuanto a su preparación, se trata de un proceso accesible incluso para cocineros principiantes. La clave está en no sobrebatir la masa tras añadir la harina de almendra y en controlar adecuadamente la cocción para mantener el interior húmedo. La almendra tiende a dorarse rápidamente, por lo que es recomendable vigilar el horno y cubrir ligeramente la parte superior si es necesario. Una vez horneado, el bizcocho desarrolla una corteza suave y un interior tierno que se mantiene esponjoso incluso al día siguiente.\r\n\r\nDesde un punto de vista gastronómico y cultural, los bizcochos de almendra están presentes en diferentes tradiciones europeas, como la repostería mediterránea y la francesa. Su popularidad se ha mantenido por generaciones gracias a la sencillez de sus ingredientes y a la nobleza del sabor de la almendra. En la repostería moderna sin gluten, este bizcocho se ha convertido en un básico indispensable, ideal para quienes buscan alternativas más saludables y naturales.\r\n\r\nEl bizcocho de almendra es, sin duda, una preparación que combina nutrición, sabor y tradición. Su textura suave, su aroma delicado y su versatilidad lo convierten en un clásico que nunca pasa de moda y que se adapta fácilmente a cualquier mesa o celebración.', 'fácil', 4, 35, 420, 6, 'bizcocho_almendra.png', 'PENDIENTE', NULL),
(3, 13, '2025-12-04 05:26:59', 'Pizza de coliflor', 'Base ligera de coliflor para una pizza saludable libre de harinas tradicionales', 'DULCE', 'La pizza de coliflor se ha convertido en una de las alternativas más populares dentro de la cocina saludable y sin gluten, ya que permite disfrutar de un plato tradicionalmente asociado al placer gastronómico, pero con una base ligera, nutritiva y vegetal. Esta preparación parte de la coliflor fresca rallada o triturada, que al cocinarse y combinarse con huevo, queso o ingredientes aglutinantes naturales, se transforma en una masa sorprendentemente firme y sabrosa, capaz de soportar todo tipo de toppings sin perder estabilidad.\r\n\r\nLa base de coliflor destaca por su bajo contenido en carbohidratos, lo que la convierte en una opción ideal para personas que buscan reducir la ingesta calórica o evitar harinas refinadas. Su sabor suave y ligeramente dulce actúa como un lienzo neutro que realza los ingredientes utilizados en la superficie, ya sean vegetales, proteínas o quesos. Además, la coliflor aporta fibra, vitamina C, antioxidantes y compuestos naturales beneficiosos para la salud digestiva.\r\n\r\nLa preparación de la base requiere algunos pasos esenciales para obtener un buen resultado. El primero es eliminar la mayor cantidad de humedad posible después de cocinar la coliflor, ya que esto es fundamental para lograr una textura firme y crujiente. Posteriormente, se mezcla con huevo u otro aglutinante adecuado y se extiende sobre una bandeja formando un círculo fino. Tras un horneado inicial, la base adquiere consistencia y permite añadir salsa de tomate, queso y los ingredientes deseados para terminar de dorar en el horno.\r\n\r\nUna de las mayores virtudes de esta pizza es su versatilidad. Se pueden preparar versiones vegetarianas, veganas, altas en proteínas o incluso gourmet, dependiendo de los ingredientes que se utilicen como cobertura. Tomates cherry, espinacas, champiñones, pollo, jamón, mozzarella o queso vegano son solo algunas opciones que combinan perfectamente con la base vegetal. Además, se puede ajustar el grosor de la masa según la preferencia: más fina para un resultado crujiente o más gruesa para una textura más tierna.\r\n\r\nEn términos gastronómicos, la pizza de coliflor representa una reinterpretación moderna de un clásico italiano. Aunque no busca replicar exactamente la textura del trigo, ofrece una experiencia diferente y deliciosa que ha conquistado a quienes buscan alternativas más saludables. Su creciente popularidad en redes sociales y restaurantes especializados confirma su lugar dentro de la cocina contemporánea.\r\n\r\nEsta receta es perfecta para personas que siguen dietas sin gluten, bajas en carbohidratos o simplemente desean incorporar más vegetales en su alimentación sin renunciar a platos sabrosos. Además, su elaboración es sencilla y puede involucrar a toda la familia, especialmente a quienes buscan una manera divertida de comer verduras.\r\n\r\nLa pizza de coliflor demuestra que es posible disfrutar de recetas clásicas de manera más equilibrada y natural, conservando todo el placer del plato original mientras se suman beneficios nutricionales al menú diario.', 'fácil', 3, 40, 280, 2, 'pizza_coliflor.png', 'PENDIENTE', NULL),
(4, 13, '2025-12-04 05:26:59', 'Tortitas de avena sin gluten', 'Tortitas suaves preparadas con avena certificada para un desayuno saludable diario', 'POSTRE', 'Las tortitas de avena sin gluten se han consolidado como una opción ideal para quienes buscan un desayuno equilibrado, nutritivo y fácil de preparar. Su base principal es la avena certificada sin gluten, un alimento rico en fibra, vitaminas del grupo B y minerales esenciales como hierro y magnesio. Estas tortitas ofrecen una textura suave y esponjosa, acompañada de un sabor ligeramente dulce y natural, que las hace perfectas tanto para recetas sencillas como para versiones más elaboradas.\r\n\r\nLa avena es conocida por su capacidad para aportar energía de liberación sostenida gracias a los betaglucanos, un tipo de fibra soluble que contribuye a mantener niveles estables de azúcar en sangre. Por esta razón, las tortitas de avena son una excelente opción para comenzar el día con vitalidad. Además, la avena es un ingrediente saciante, lo que ayuda a reducir la sensación de hambre durante la mañana y favorece una alimentación más consciente.\r\n\r\nEn su preparación, estas tortitas suelen elaborarse mezclando copos o harina de avena con ingredientes como huevo, leche vegetal o yogur, formando una masa homogénea y ligeramente espesa. Dependiendo de las preferencias culinarias, se puede añadir plátano triturado, canela, vainilla o incluso cacao puro para personalizar el sabor. La masa se cocina a fuego medio en una sartén antiadherente, permitiendo obtener tortitas doradas por fuera y tiernas por dentro.\r\n\r\nUna de las mayores ventajas de esta receta es su versatilidad. Las tortitas pueden acompañarse con frutas frescas, miel, crema de frutos secos, yogur o chocolate negro. También es posible añadir semillas, frutos secos o proteínas en polvo para incrementar su valor nutricional. Esta adaptabilidad convierte a las tortitas de avena en una opción ideal tanto para deportistas como para personas que buscan alternativas saludables en su rutina diaria.\r\n\r\nEn el plano cultural, la avena ha sido un alimento tradicional en muchas regiones del norte de Europa, donde se ha consumido en forma de gachas durante siglos. Su incorporación a recetas modernas como las tortitas refleja la evolución de la cocina contemporánea, que busca combinar tradición, salud y practicidad. Gracias a su perfil nutricional y su versatilidad, la avena ha adquirido un papel protagonista en la alimentación saludable actual.\r\n\r\nLas tortitas de avena sin gluten representan una forma deliciosa de integrar ingredientes naturales en la dieta. Su sencillez, su equilibrio nutricional y su capacidad para adaptarse a distintos estilos de vida las convierten en un desayuno perfecto para cualquier persona que desee comenzar el día con energía, bienestar y sabor.', 'fácil', 1, 20, 250, 2, 'tortitas_avena.png', 'PENDIENTE', NULL),
(5, 13, '2025-12-04 05:26:59', 'Crepes de harina de arroz', 'Crepes finos y ligeros elaborados exclusivamente con suave harina de arroz', 'ALMUERZO', 'Los crepes de harina de arroz representan una alternativa sin gluten extremadamente versátil, ligera y agradable para cualquier momento del día. Esta preparación destaca por su textura fina y suave, que recuerda a los crepes tradicionales franceses, pero utilizando únicamente harina de arroz como base. La harina de arroz es conocida por su sabor neutro y su capacidad para generar masas ligeras, lo que permite obtener crepes muy maleables, perfectos para rellenar tanto con ingredientes dulces como salados.\r\n\r\nA nivel nutricional, la harina de arroz aporta una cantidad moderada de carbohidratos complejos, baja cantidad de grasas y una digestibilidad excelente, lo que la convierte en un ingrediente ideal para personas con sensibilidad digestiva o intolerancia al gluten. Su perfil nutricional equilibrado permite que los crepes sean una opción ligera pero energética, adecuada para desayunos, meriendas o cenas rápidas. Además, la harina de arroz es naturalmente libre de gluten y suele ser muy bien tolerada por celíacos.\r\n\r\nLa preparación de estos crepes es sencilla y rápida. Se elabora una mezcla fluida combinando harina de arroz, huevos, leche —ya sea de origen animal o vegetal— y una pizca de sal. La textura de la masa debe ser lo suficientemente ligera como para extenderse con facilidad en la sartén. El secreto para obtener crepes perfectos consiste en calentar bien la sartén antiadherente, verter una fina capa de masa y distribuirla con movimientos circulares para lograr un grosor uniforme. Una cocción breve por cada lado es suficiente para obtener crepes flexibles, suaves y ligeramente dorados.\r\n\r\nEstos crepes son altamente personalizables. En su versión dulce, se pueden acompañar con frutas frescas, miel, sirope natural, chocolate fundido o crema de frutos secos. En su versión salada, son excelentes con jamón, queso, verduras salteadas, hummus o cualquier relleno ligero. Su versatilidad los convierte en una opción perfecta para familias, ya que cada persona puede adaptar su crepe al gusto en tan solo unos minutos.\r\n\r\nCulturalmente, los crepes forman parte de la tradición gastronómica francesa, en especial en la región de Bretaña. La adaptación sin gluten utilizando harina de arroz conserva la esencia del crepe tradicional al tiempo que abre la puerta a quienes no pueden consumir gluten. Este tipo de preparación demuestra cómo es posible preservar las técnicas culinarias clásicas adaptándolas a las necesidades actuales.\r\n\r\nLos crepes de harina de arroz son un ejemplo claro de cómo un plato sencillo puede transformarse en una opción deliciosa, ligera y saludable. Su textura delicada y su sabor neutro permiten disfrutar de una infinidad de combinaciones, convirtiéndolos en un recurso culinario imprescindible para quienes desean recetas rápidas y aptas para todos los públicos.', 'fácil', 3, 25, 230, 3, 'crepes_arroz.png', 'PENDIENTE', NULL),
(6, 13, '2025-12-04 05:26:59', 'Galletas de coco', 'Galletas crujientes de coco ideales para un dulce ligero y natural', 'CENA', 'Las galletas de coco sin gluten son una de esas preparaciones sencillas, aromáticas y rápidas que conquistan a cualquiera que disfrute de los sabores tropicales. ... (texto completo aquí) ...', 'fácil', 1, 18, 320, 4, 'galletas_coco.png', 'PENDIENTE', NULL),
(7, 13, '2025-12-04 05:26:59', 'Brownie de garbanzos', 'Brownie húmedo y denso elaborado con nutritivos garbanzos cocidos', 'MERIENDA', 'El brownie de garbanzos sin gluten es uno de los ejemplos más sorprendentes de la repostería saludable moderna. ... (texto completo aquí) ...', 'media', 4, 30, 410, 4, 'brownie_garbanzos.png', 'PENDIENTE', NULL),
(8, 13, '2025-12-04 05:26:59', 'Pan de maíz', 'Pan casero de maíz con miga tierna y un inconfundible aroma tradicional', 'ALMUERZO', 'El pan de maíz sin gluten es una preparación tradicional que forma parte de la cocina de numerosos países, especialmente en América y en regiones rurales donde el maíz ha sido un cultivo esencial durante siglos. Este pan se distingue por su sabor ligeramente dulce y su color dorado intenso, propio de la harina de maíz. Su textura es más densa que la del pan de trigo, pero extremadamente tierna y con una miga que se deshace suavemente al comerla. El aroma cálido del maíz recién horneado crea una experiencia sensorial que evoca recetas caseras y métodos tradicionales de elaboración.\r\n\r\nLa harina de maíz es un ingrediente naturalmente libre de gluten, lo que convierte a este pan en una opción ideal para personas celíacas o que buscan alternativas a las harinas convencionales. Aporta carbohidratos complejos, fibra y micronutrientes como hierro, zinc y vitaminas del grupo B. Su bajo contenido en grasas y su aporte energético moderado lo convierten en un alimento equilibrado, apto para acompañar comidas ligeras o formar parte de desayunos y meriendas.\r\n\r\nLa preparación del pan de maíz es sencilla, pero requiere seguir ciertos pasos clave para obtener una textura óptima. Se mezclan harina de maíz, una pequeña cantidad de harina sin gluten (opcional), huevos, leche o bebida vegetal, aceite y un agente leudante. La mezcla resultante suele ser algo más espesa que la masa de un bizcocho, pero más ligera que la de un pan tradicional. Tras verterla en un molde y hornearla, se obtiene un pan esponjoso, ligeramente húmedo y con una corteza dorada y firme.\r\n\r\nEste pan combina a la perfección con recetas saladas como sopas, guisos y platos de carne, pero también puede disfrutarse con ingredientes dulces como miel, mantequilla o mermelada. En muchas regiones, es habitual acompañarlo de queso fresco, aguacate o preparaciones con huevo.\r\n\r\nCulturalmente, el pan de maíz tiene un valor especial en la gastronomía americana, donde ha sido un alimento básico desde tiempos precolombinos. Cada región ha desarrollado su propia versión: panes más dulces, más salados, más densos o más esponjosos. La versión sin gluten retoma esa tradición, adaptándola a las necesidades modernas.\r\n\r\nEn resumen, el pan de maíz sin gluten es un alimento nutritivo, aromático y lleno de historia. Su facilidad de preparación lo convierte en una receta fundamental para quienes buscan panes alternativos, saludables y llenos de sabor.', 'media', 4, 40, 360, 6, 'pan_maiz.jpg', 'PENDIENTE', NULL),
(9, 13, '2025-12-04 05:26:59', 'Tarta de zanahoria', 'Tarta húmeda de zanahoria con nueces y un suave dulzor natural', 'SALADO', 'La tarta de zanahoria sin gluten es un clásico de la repostería saludable, conocida por su textura esponjosa, su dulzor natural y su profundidad aromática. La zanahoria aporta humedad y suavidad a la masa, mientras que las nueces y las especias —como canela y nuez moscada— crean un perfil de sabor cálido y reconfortante. Esta versión sin gluten mantiene todo el encanto de la receta original, pero adaptada para quienes requieren evitar harinas tradicionales.\r\n\r\nLa zanahoria, rica en betacarotenos, fibra y antioxidantes, aporta color, jugosidad y nutrientes esenciales. Gracias a su dulzor natural, permite reducir la cantidad de azúcar en la receta. Las nueces, por su parte, aportan grasas saludables, proteína vegetal y una textura crujiente que contrasta con la suavidad del bizcocho. El resultado es una tarta equilibrada, nutritiva y deliciosa.\r\n\r\nLa preparación consiste en mezclar zanahorias ralladas con huevos, aceite, azúcar o edulcorante, harina sin gluten y especias. La masa resultante se hornea hasta obtener una tarta húmeda, con un color anaranjado intenso y un aroma irresistible. Muchas versiones incluyen un frosting suave de queso crema, aunque esta receta puede disfrutarse igualmente sola.\r\n\r\nEn cuanto a su historia, la tarta de zanahoria tiene raíces europeas, especialmente en épocas donde el azúcar era escaso y se utilizaban verduras dulces para endulzar postres. En la actualidad, es uno de los pasteles más populares en todo el mundo gracias a su sabor equilibrado y su versatilidad.\r\n\r\nEsta tarta sin gluten es perfecta para celebraciones, meriendas o como postre equilibrado, y destaca por su capacidad para permanecer tierna durante varios días sin perder su textura ni su aroma.', 'media', 3, 50, 480, 8, 'tarta_zanahoria.png', 'PENDIENTE', NULL),
(10, 13, '2025-12-04 05:26:59', 'Muffins de plátano', 'Muffins tiernos de plátano maduro con aroma dulce y textura esponjosa', 'POSTRE', 'Los muffins de plátano sin gluten son una opción deliciosa, nutritiva y perfecta para aprovechar plátanos maduros. Estos muffins destacan por su textura extremadamente esponjosa y por el dulzor natural que aporta la fruta, lo que permite reducir significativamente la cantidad de azúcar añadido. Su aroma es cálido y envolvente, convirtiéndolos en una elección ideal para desayunos, meriendas o snacks saludables.\r\n\r\nEl plátano maduro es un ingrediente muy apreciado en la repostería sin gluten por su capacidad para aportar humedad, ligar la masa y potenciar el sabor. Además, es rico en potasio, fibra y vitaminas como la B6 y la C. La combinación con harina sin gluten, huevo, aceite suave y alguna esencia aromática da como resultado unos muffins ligeros, húmedos y muy agradables al paladar.\r\n\r\nLa preparación es sencilla: se trituran los plátanos maduros, se mezclan con los ingredientes líquidos y luego se incorpora la mezcla seca. Tras unos minutos de horneado, los muffins adquieren una superficie dorada y un interior esponjoso con un sabor naturalmente dulce.\r\n\r\nCulturalmente, los muffins de plátano han sido populares en América desde el siglo XX, especialmente como receta de aprovechamiento para evitar desperdiciar fruta madura. Su adaptación sin gluten mantiene ese espíritu práctico y accesible, ofreciendo un postre saludable y apto para todos.\r\n\r\nEstos muffins pueden enriquecerse con nueces, chocolate negro, coco rallado o semillas, lo que permite crear versiones más complejas dependiendo del gusto. Además, su tamaño individual los hace ideales para llevar y consumir fuera de casa.', 'fácil', 1, 22, 290, 6, 'muffins_platano.png', 'PENDIENTE', NULL),
(11, 13, '2025-12-04 05:26:59', 'Arepas venezolanas', 'Arepas tradicionales venezolanas elaboradas con maíz precocido sin gluten', 'CENA', 'Las arepas venezolanas son uno de los alimentos más representativos de la gastronomía de Venezuela, y forman parte de la identidad cultural del país desde hace siglos. Su preparación, basada en harina de maíz precocida, las convierte en una opción naturalmente libre de gluten, apta para celíacos y para quienes buscan una alternativa nutritiva y versátil al pan tradicional. Estas arepas destacan por su textura firme por fuera y suave por dentro, con un aroma característico a maíz tostado que las hace irresistibles a cualquier hora del día.\r\n\r\nLa harina de maíz precocida utilizada para su elaboración es rica en carbohidratos complejos y libre de aditivos innecesarios, lo que aporta energía sostenida y facilita la digestión. Además, contiene pequeñas cantidades de fibra y micronutrientes como hierro y vitamina A. A diferencia del pan de trigo, que suele requerir levadura y tiempos de fermentación, las arepas pueden prepararse en pocos minutos mezclando la harina con agua y sal hasta obtener una masa suave y moldeable.\r\n\r\nEl proceso de cocción es un aspecto clave para obtener una arepa perfecta. Primero se cocina en una sartén o plancha, lo que permite crear una costra ligeramente dorada y un aroma característico. Luego, muchas versiones se terminan al horno, lo que permite que la masa se abra naturalmente y quede lista para rellenar. Este equilibrio entre crujiente y tierno hace que las arepas sean un alimento extremadamente satisfactorio y adaptable a cualquier preferencia culinaria.\r\n\r\nLa versatilidad es, sin duda, una de las mayores virtudes de las arepas. Se pueden rellenar con una amplia variedad de ingredientes, desde opciones simples como queso fresco, aguacate o mantequilla, hasta preparaciones más complejas como carne mechada, pollo con aguacate, perico o caraotas negras. En Venezuela, cada región ha desarrollado su propia manera de servirlas, lo que enriquece aún más su diversidad gastronómica.\r\n\r\nCulturalmente, las arepas tienen un valor inmenso. Forman parte del desayuno y la cena en innumerables hogares, aunque también se consumen como almuerzo o merienda. Representan tradición, unión familiar y la historia agrícola del país, basada en el cultivo del maíz. Su preparación es tan habitual que prácticamente todas las familias venezolanas han desarrollado sus propias técnicas y preferencias: más gruesas, más delgadas, más tostadas o más suaves.\r\n\r\nHoy en día, las arepas han trascendido fronteras y se han convertido en un símbolo de la cocina latinoamericana en el mundo. Su naturaleza sin gluten y su increíble versatilidad han contribuido a su popularidad internacional. Prepararlas en casa es una excelente forma de disfrutar de un alimento tradicional, nutritivo, sencillo y cargado de sabor e historia.', 'difícil', 1, 25, 330, 4, 'arepas.png', 'PENDIENTE', NULL),
(12, 13, '2025-12-04 05:26:59', 'Croquetas de arroz', 'Croquetas doradas de arroz y verduras con textura cremosa interior', 'POSTRE', 'Las croquetas de arroz sin gluten son una alternativa ligera, económica y sumamente versátil dentro de la cocina casera. Esta receta aprovecha ingredientes básicos para transformar el arroz, un alimento presente en prácticamente todas las culturas, en un aperitivo delicioso, crujiente por fuera y cremoso por dentro. Son ideales para quienes buscan una opción sin gluten que mantenga el sabor casero y la satisfacción de una croqueta tradicional.\r\n\r\nEl arroz es un ingrediente naturalmente libre de gluten, fácil de digerir y muy accesible. Cuando se combina con verduras, especias y una base cremosa, se convierte en un relleno perfecto para croquetas. Su capacidad para absorber sabores y mantener una textura suave facilita la creación de piezas compactas y bien formadas. Además, el arroz aporta energía en forma de carbohidratos complejos, vitaminas del grupo B y minerales como fósforo y magnesio.\r\n\r\nLa preparación de estas croquetas comienza con la elaboración de un arroz meloso, que puede mezclarse con zanahoria, puerro, calabacín u otras verduras al gusto. Luego, la mezcla se une con huevo o un sustituto vegetal, y se forman pequeñas porciones que se rebozan con pan rallado sin gluten o harina de arroz. Tras freírlas o cocinarlas al horno, adquieren una textura dorada y crujiente que contrasta con el interior suave y aromático.\r\n\r\nLas croquetas de arroz pueden adaptarse a múltiples estilos culinarios: añadir cúrcuma aporta color y propiedades antiinflamatorias; integrar queso crea una versión más cremosa; utilizar hierbas frescas como perejil o cilantro intensifica el sabor. También se pueden preparar versiones veganas sustituyendo los ingredientes de origen animal por alternativas vegetales.\r\n\r\nEn cuanto a su historia, aunque las croquetas son originarias de la cocina francesa, han sido adoptadas por numerosos países que han adaptado la receta según sus ingredientes locales. La versión hecha con arroz recuerda a preparaciones mediterráneas como los arancini italianos, aunque esta adaptación sin gluten ofrece un enfoque más ligero y accesible.\r\n\r\nEstas croquetas son perfectas como entrante, acompañamiento o incluso plato principal si se sirven junto a una ensalada fresca o una salsa suave. Su facilidad de preparación las convierte en una excelente opción para cocinar en grandes cantidades y congelarlas para utilizarlas en otro momento.\r\n\r\nEn resumen, las croquetas de arroz sin gluten son una receta práctica, nutritiva y llena de sabor que demuestra cómo ingredientes simples pueden convertirse en un plato delicioso y apto para todos.', 'media', 3, 35, 380, 4, 'croquetas_arroz.png', 'PENDIENTE', NULL),
(13, 13, '2025-12-04 05:26:59', 'Ensalada de quinoa', 'Ensalada fresca de quinoa, tomate y aguacate con sabor equilibrado', 'SALADO', 'La ensalada de quinoa sin gluten es una receta saludable, refrescante y llena de color que combina ingredientes naturales con un alto valor nutricional. La quinoa, un pseudocereal originario de los Andes, es famosa por su perfil nutricional excepcional: contiene todos los aminoácidos esenciales, es rica en fibra, minerales como magnesio y hierro, y es naturalmente libre de gluten. Su textura ligera y su suave sabor la convierten en una base ideal para ensaladas equilibradas y fáciles de preparar.\r\n\r\nLa combinación de quinoa con tomate y aguacate crea un plato lleno de contrastes agradables. El tomate aporta frescura, jugosidad y antioxidantes como el licopeno; el aguacate añade cremosidad y grasas saludables; mientras que la quinoa otorga estructura y saciedad. El resultado es una ensalada completa, perfecta para almuerzos ligeros, cenas rápidas o como acompañamiento nutritivo.\r\n\r\nLa preparación es sencilla: se cocina la quinoa tras enjuagarla para eliminar la saponina que recubre sus granos, lo que evita sabores amargos. Una vez lista y enfriada, se mezcla con tomate picado, aguacate, aceite de oliva, limón y sal. Opcionalmente, se pueden añadir ingredientes adicionales como pepino, cebolla morada, cilantro, semillas o frutos secos, que incrementan el sabor y el valor nutricional.\r\n\r\nA nivel gastronómico, la quinoa ha sido considerada un alimento sagrado por culturas andinas durante miles de años, utilizada tanto en platos cotidianos como en ceremonias tradicionales. Su popularidad actual en todo el mundo responde a su versatilidad y a su papel como alternativa saludable a los cereales convencionales.\r\n\r\nEsta ensalada destaca por su frescura, su sabor equilibrado y su capacidad para adaptarse a cualquier estación del año. Es ligera pero saciante, ideal para quienes buscan opciones nutritivas sin renunciar al sabor.', 'fácil', 4, 15, 290, 2, 'ensalada_quinoa.png', 'PENDIENTE', NULL),
(14, 13, '2025-12-04 05:26:59', 'Tarta de limón sin gluten', 'Tarta cítrica de limón con base de almendra y textura delicada', 'CENA', 'La tarta de limón sin gluten es un postre elegante, refrescante y lleno de sabor, ideal para quienes buscan un equilibrio entre dulzura y acidez. Su base de almendra aporta un sabor ligeramente tostado y una textura crujiente que contrasta con la suavidad de la crema de limón. Esta combinación hace que la tarta sea perfecta para ocasiones especiales o para disfrutar como postre diario sin renunciar al placer de un dulce exquisito.\r\n\r\nEl limón es el ingrediente estrella de la receta, proporcionando frescura, aroma intenso y un toque ácido que realza el sabor de los demás componentes. Además, el limón es una fuente natural de vitamina C y antioxidantes, lo que aporta beneficios adicionales a la preparación. La crema de limón se elabora generalmente con huevos, azúcar, jugo y ralladura de limón, logrando una textura sedosa y un sabor equilibrado entre dulzor y acidez.\r\n\r\nLa base de almendra, elaborada con harina de almendra y mantequilla o aceite, garantiza que la receta sea libre de gluten y que tenga un sabor profundo y natural. Su textura ligeramente arenosa complementa la suavidad de la crema, mientras que su aroma a frutos secos le da un toque sofisticado. Muchas versiones incorporan un ligero glaseado o ralladura adicional de limón para intensificar el aroma y la presentación.\r\n\r\nLa preparación requiere precisión en las proporciones y en la cocción para asegurar que la crema de limón no se corte y que la base permanezca crujiente. Se recomienda hornear la base parcialmente antes de agregar la crema, lo que garantiza que mantenga su estructura y textura. Tras el horneado, la tarta se deja enfriar y se puede decorar con rodajas finas de limón o azúcar glas.\r\n\r\nA nivel gastronómico, la tarta de limón tiene raíces europeas, con versiones famosas en Francia y Reino Unido, donde los postres cítricos han sido parte de la tradición repostera durante siglos. Su adaptación sin gluten la hace accesible a todos y mantiene la elegancia y frescura del postre original.\r\n\r\nEsta tarta es perfecta para acompañar té o café, para celebraciones familiares o simplemente para disfrutar de un momento dulce sin excesos. Su textura delicada y su sabor equilibrado la convierten en un postre que agrada tanto a adultos como a niños, y que deja un recuerdo memorable por su aroma y frescura.', 'difícil', 5, 60, 420, 6, 'tarta_limon.png', 'PENDIENTE', NULL),
(15, 13, '2025-12-04 05:16:58', 'Pan de plátano', 'Pan dulce de plátano maduro con nueces y textura húmeda y tierna', 'DESAYUNO', 'El pan de plátano sin gluten es un clásico de la repostería casera que combina la dulzura natural del plátano maduro con la riqueza de las nueces. Su textura húmeda y tierna, junto con su aroma dulce y reconfortante, lo convierten en un alimento perfecto para desayunos, meriendas o simplemente para disfrutar con un café o té. La receta sin gluten permite que cualquier persona pueda disfrutar de este pan sin comprometer la calidad o el sabor.\r\n\r\nEl ingrediente principal, el plátano maduro, aporta azúcar natural, humedad y sabor intenso. Además, es una fuente excelente de potasio, fibra y vitaminas del grupo B, lo que convierte al pan en un alimento nutritivo y saciante. Las nueces, por su parte, agregan un contraste crujiente, grasas saludables y proteínas vegetales, aportando un equilibrio ideal entre textura y sabor.\r\n\r\nLa preparación del pan de plátano requiere triturar los plátanos maduros hasta obtener un puré suave, mezclarlo con huevos, aceite y harina sin gluten, y añadir las nueces picadas. La mezcla se vierte en un molde y se hornea hasta que la superficie esté dorada y un palillo insertado en el centro salga limpio. El resultado es un pan esponjoso, húmedo y aromático que mantiene su frescura durante varios días si se conserva correctamente.\r\n\r\nEste pan es extremadamente versátil. Puede consumirse solo, con mantequilla, crema de frutos secos o incluso acompañado de un poco de chocolate. También puede enriquecerse con especias como canela, jengibre o nuez moscada para intensificar su sabor. La receta puede adaptarse a dietas veganas sustituyendo los huevos por puré de manzana o semillas de lino hidratadas.\r\n\r\nEl pan de plátano tiene una historia interesante, especialmente en Estados Unidos, donde se popularizó en el siglo XX como receta de aprovechamiento de plátanos maduros. Su versión sin gluten ha ganado relevancia en la actualidad, combinando tradición y necesidades dietéticas modernas sin perder sabor ni textura.\r\n\r\nEs un pan que transmite calidez y hogar, perfecto para reuniones familiares, desayunos nutritivos y meriendas reconfortantes. Su dulzura natural lo hace atractivo para niños y adultos, y su valor nutricional lo convierte en una opción más saludable que otros panes dulces comerciales.', 'difícil', 2, 45, 400, 6, 'pan_platano.png', 'PENDIENTE', NULL),
(16, 13, '2025-12-04 05:26:59', 'Cookies de avena y chocolate', 'Galletas crujientes de avena con chips de chocolate sin gluten', 'SALADO', 'Las cookies de avena y chocolate sin gluten son un clásico de la repostería casera, perfectas para disfrutar en cualquier momento del día. Su combinación de avena integral y trozos de chocolate crea un contraste delicioso entre lo crujiente y lo suave, aportando además un alto valor nutricional y energía sostenida. Son ideales para desayunos, meriendas o como snack saludable.\r\n\r\nLa avena utilizada en esta receta es certificada sin gluten, rica en fibra soluble e insoluble, lo que ayuda a la digestión y a mantener la sensación de saciedad. Contiene vitaminas del grupo B, hierro y antioxidantes, mientras que el chocolate aporta un toque de indulgencia con flavonoides beneficiosos. La combinación de ambos ingredientes genera un producto equilibrado que satisface tanto al paladar como a la necesidad nutricional.\r\n\r\nLa preparación de estas cookies es sencilla: se mezclan avena, harina sin gluten, azúcar, mantequilla o aceite vegetal, y se incorporan los chips de chocolate. La masa se moldea en pequeñas porciones y se hornea hasta obtener bordes dorados y centro ligeramente blando. El resultado es una galleta que mantiene su forma, textura y sabor durante varios días si se conserva en un recipiente hermético.\r\n\r\nEstas cookies son versátiles y admiten muchas adaptaciones: se pueden añadir frutos secos, coco rallado, semillas de chía o incluso ralladura de naranja para intensificar el aroma. También se pueden preparar versiones veganas usando puré de manzana o plátano en lugar de huevo, y aceite en lugar de mantequilla.\r\n\r\nHistóricamente, las galletas de avena surgieron como una opción energética para deportistas y trabajadores, y hoy se han convertido en un postre o snack universal. Su adaptación sin gluten responde a la creciente demanda de productos aptos para celíacos o personas con sensibilidad al gluten.\r\n\r\nEl sabor, aroma y textura de estas cookies las hacen irresistibles para niños y adultos. Son ideales para acompañar un vaso de leche, café o té, y su elaboración en casa permite controlar la calidad de los ingredientes y el contenido de azúcar, convirtiéndolas en una alternativa más saludable a las versiones industriales.', 'media', 3, 25, 380, 4, 'cookies_avena.png', 'PENDIENTE', NULL),
(17, 13, '2025-12-04 05:26:59', 'Tortilla de patata', 'Clásica tortilla española sin gluten con textura jugosa y suave', 'MERIENDA', 'La tortilla de patata, también conocida como tortilla española, es un plato emblemático de la cocina española, apreciado por su sabor reconfortante y su sencillez. Esta versión sin gluten mantiene la autenticidad del plato original, combinando patatas, huevos y cebolla para crear una preparación jugosa, tierna y sabrosa, ideal para desayunos, almuerzos o cenas ligeras.\r\n\r\nEl ingrediente principal, la patata, aporta carbohidratos complejos, fibra y vitaminas del grupo B. Combinada con huevos, fuente de proteínas de alta calidad y grasas saludables, la tortilla ofrece un equilibrio nutricional completo. La cebolla añade sabor, aroma y compuestos antioxidantes beneficiosos, mientras que el aceite de oliva, tradicional en esta receta, contribuye con ácidos grasos monoinsaturados que favorecen la salud cardiovascular.\r\n\r\nLa preparación consiste en pelar y cortar las patatas en rodajas finas, freírlas lentamente hasta que estén tiernas y mezclar con los huevos batidos. La mezcla se cocina a fuego medio, volteando la tortilla cuidadosamente para lograr que se cocine uniformemente. El resultado es una tortilla con el interior jugoso y el exterior ligeramente dorado, con un sabor equilibrado y textura suave.\r\n\r\nLa tortilla de patata admite numerosas variaciones, como añadir pimientos, calabacín o jamón, siempre manteniendo su esencia y textura característica. Su versatilidad la hace adecuada tanto para platos principales como para tapas o aperitivos.\r\n\r\nEste plato tiene una historia que se remonta al siglo XIX en España, convirtiéndose rápidamente en un símbolo de la gastronomía popular. Se consume en hogares, bares y restaurantes, y es reconocida internacionalmente por su sencillez y sabor reconfortante.\r\n\r\nNutricionalmente, la tortilla es un plato equilibrado que aporta energía, proteínas y micronutrientes esenciales, siendo saciante y accesible para todos. Su versión sin gluten la hace apta para celíacos, sin alterar su sabor ni su textura.\r\n\r\nIdeal para cualquier ocasión, la tortilla de patata es un ejemplo perfecto de cómo ingredientes simples pueden convertirse en un plato emblemático y delicioso, que agrada a toda la familia y se adapta a diferentes momentos del día.', 'fácil', 5, 40, 350, 4, 'tortilla_patata.png', 'PENDIENTE', NULL),
(18, 13, '2025-12-04 05:26:59', 'Pollo al curry con arroz', 'Pollo al curry suave acompañado de arroz blanco esponjoso y aromático', 'ALMUERZO', 'El pollo al curry con arroz es un plato lleno de sabor, color y aroma, que combina la suavidad de la carne de pollo con la intensidad de las especias orientales. Esta receta es ideal para quienes buscan un plato completo, nutritivo y equilibrado, con un perfil de sabor exótico y agradable al paladar. Su versión sin gluten lo hace apto para cualquier persona sin comprometer el gusto ni la textura.\r\n\r\nEl pollo aporta proteínas de alta calidad, vitaminas del grupo B y minerales esenciales como fósforo y selenio. El curry, mezcla de especias como cúrcuma, comino, cilantro y jengibre, no solo proporciona aroma y color característicos, sino también beneficios antioxidantes y antiinflamatorios. El arroz blanco es un acompañamiento neutro que equilibra el sabor intenso del curry y aporta carbohidratos de digestión rápida, proporcionando energía inmediata.\r\n\r\nLa preparación consiste en dorar el pollo en trozos uniformes, añadir cebolla, ajo y especias, y cocinar lentamente con leche de coco o caldo para crear una salsa cremosa y fragante. El arroz se cocina aparte hasta obtener granos sueltos y esponjosos. El resultado es un plato jugoso, lleno de sabor y perfectamente equilibrado entre proteína, carbohidratos y grasas saludables.\r\n\r\nEste plato admite múltiples variaciones: se puede añadir verduras como zanahoria, pimiento o calabacín, ajustar el nivel de picante al gusto, o incluso preparar versiones vegetarianas usando tofu o garbanzos en lugar de pollo. La combinación de ingredientes frescos y especias permite que cada preparación tenga un aroma y sabor únicos, que evocan la cocina tradicional india y del sudeste asiático.\r\n\r\nNutricionalmente, el pollo al curry con arroz es una comida completa y saciante. Proporciona energía, proteínas y micronutrientes esenciales, siendo adecuado para adultos, adolescentes y niños. Su preparación en casa permite controlar la cantidad de sal y aceite, garantizando un plato más saludable que las versiones comerciales.\r\n\r\nEs un plato ideal para comidas familiares, cenas entre amigos o como receta de batch cooking para varios días. Su combinación de sabores, colores y texturas lo hace apetitoso y elegante, apto tanto para ocasiones informales como para presentaciones más sofisticadas.', 'media', 1, 50, 550, 4, 'pollo_curry.png', 'PENDIENTE', NULL),
(19, 13, '2025-12-04 05:26:59', 'Crema de calabaza', 'Crema de calabaza suave, nutritiva y reconfortante para cualquier estación', 'ALMUERZO', 'La crema de calabaza es una receta ligera, reconfortante y nutritiva, perfecta para cualquier época del año. Su sabor dulce y suave, combinado con su textura aterciopelada, la hace ideal para entrantes, cenas ligeras o como acompañamiento de otros platos. La versión sin gluten permite que sea apta para todas las personas, manteniendo todo su sabor y propiedades nutritivas.\r\n\r\nEl ingrediente principal, la calabaza, es rica en vitamina A, antioxidantes y fibra, lo que favorece la salud ocular, digestiva y general del organismo. La combinación con cebolla, zanahoria y caldo de verduras potencia el sabor y aporta un equilibrio nutritivo, mientras que un toque de aceite de oliva añade grasas saludables y mejora la absorción de vitaminas liposolubles.\r\n\r\nLa preparación consiste en cortar la calabaza y verduras en trozos uniformes, cocerlas en agua o caldo hasta que estén tiernas y triturarlas hasta obtener una textura cremosa y homogénea. Se puede añadir un poco de nata o leche vegetal para suavizar aún más la crema. El resultado es un plato con sabor equilibrado, aroma intenso y textura aterciopelada que se derrite en el paladar.\r\n\r\nEsta crema admite variaciones: se pueden incorporar especias como nuez moscada, jengibre o curry suave, semillas tostadas, o incluso añadir un toque de queso crema o yogur para darle más suavidad. Es una receta versátil, económica y fácil de preparar, que se adapta a dietas veganas o vegetarianas.\r\n\r\nHistóricamente, las sopas y cremas de calabaza se han consumido en diversas culturas como alimento de temporada, valorando su sabor, textura y propiedades nutritivas. Su popularidad ha crecido gracias a su facilidad de preparación y la gran disponibilidad de calabazas en otoño e invierno.\r\n\r\nNutricionalmente, la crema de calabaza es baja en calorías, rica en fibra, antioxidantes y micronutrientes esenciales. Es saciante, ligera y adecuada para cualquier persona que busque una comida saludable, reconfortante y sabrosa.', 'fácil', 2, 35, 180, 4, 'crema_calabaza.png', 'PENDIENTE', NULL),
(20, 13, '2025-12-04 05:26:59', 'Sopa de lentejas', 'Sopa nutritiva de lentejas con verduras frescas y especias aromáticas', 'TAPAS', 'La sopa de lentejas es un plato clásico, nutritivo y reconfortante, ideal para cualquier temporada del año. Combina lentejas, verduras y especias en un caldo aromático, ofreciendo un equilibrio perfecto entre sabor, textura y valor nutricional. La versión sin gluten permite que sea apta para todos, manteniendo el sabor tradicional y las propiedades beneficiosas de los ingredientes.\r\n\r\nLas lentejas son una fuente excelente de proteínas vegetales, fibra, hierro, fósforo y vitaminas del grupo B. Su combinación con verduras como zanahoria, cebolla y apio aporta vitaminas, minerales y antioxidantes, mientras que las especias como pimentón, comino o laurel intensifican el sabor y aroma del plato. Esta sopa proporciona energía sostenida, mejora la digestión y contribuye a la salud general.\r\n\r\nLa preparación consiste en sofreír las verduras, añadir las lentejas y cubrir con agua o caldo. Se cocinan hasta que las lentejas estén tiernas y los sabores se hayan mezclado completamente. La textura puede ser más líquida o espesa según el gusto, y se puede triturar parcialmente para obtener una crema más homogénea.\r\n\r\nLa sopa de lentejas es versátil y admite muchas variaciones: se puede agregar chorizo o jamón para un sabor más intenso, incorporar patatas o arroz, o enriquecerla con hierbas frescas como cilantro o perejil. Es económica, fácil de preparar y perfecta para batch cooking, ya que mejora su sabor al día siguiente.\r\n\r\nHistóricamente, las lentejas han sido un alimento fundamental en muchas culturas, especialmente en la dieta mediterránea y del Medio Oriente, apreciadas por su alto valor nutritivo y facilidad de almacenamiento. Esta sopa es un ejemplo de comida sencilla, accesible y saludable.\r\n\r\nNutricionalmente, la sopa de lentejas aporta proteínas, carbohidratos complejos, fibra, minerales y vitaminas, siendo saciante y adecuada para adultos, adolescentes y niños. Su preparación casera permite controlar sal, grasa y especias, garantizando un plato más saludable que las versiones envasadas.', 'fácil', 5, 45, 320, 4, 'sopa_lentejas.png', 'PENDIENTE', NULL),
(21, 13, '2025-12-04 05:26:13', 'Tarta de manzana sin gluten', 'Tarta de manzana casera, ideal para el desayuno o merienda sin gluten.', 'POSTRE', 'Tarta casera con manzana y canela.', 'media', 4, 30, 250, 6, 'tarta_manzana.png', 'PENDIENTE', NULL),
(22, 13, '2025-12-04 05:26:13', 'Pan de arroz integral', 'Pan artesanal denso con alta fibra, perfecto para tostadas saludables.', 'SALADO', 'Pan sin gluten con harina de arroz integral.', 'difícil', 5, 90, 1500, 8, 'pan_arroz_integral.png', 'PENDIENTE', NULL),
(23, 13, '2025-12-04 05:26:13', 'Empanadillas de boniato', 'Bocaditos dulces y salados, una alternativa original a las empanadas.', 'TAPAS', 'Empanadillas sin gluten rellenas de boniato.', 'fácil', 3, 25, 280, 4, 'empanadillas_boniato.png', 'PENDIENTE', NULL),
(24, 13, '2025-12-04 05:26:13', 'Tacos de maíz', 'Receta mexicana versátil, perfecta para rellenar al gusto.', 'SALADO', 'Tacos mexicanos con tortillas de maíz sin gluten.', 'media', 4, 15, 350, 4, 'tacos_maiz.png', 'PENDIENTE', NULL),
(25, 13, '2025-12-04 05:26:13', 'Berenjenas rellenas', 'Plato completo, vegetariano, lleno de sabor y nutrientes.', 'ALMUERZO', 'Berenjenas al horno rellenas de carne y verduras.', 'media', 4, 45, 420, 2, 'berenjenas_rellenas.png', 'PENDIENTE', NULL),
(26, 13, '2025-12-04 05:26:13', 'Albóndigas de lentejas', 'Albóndigas veganas, ricas en proteínas y aptas para todas las dietas.', 'CENA', 'Albóndigas veganas sin gluten con lentejas.', 'media', 5, 35, 300, 4, 'albondigas_lentejas.png', 'PENDIENTE', NULL),
(27, 13, '2025-12-04 05:26:13', 'Tarta de chocolate sin gluten', 'Postre indulgente e intenso, para los amantes del cacao puro.', 'POSTRE', 'Tarta intensa de chocolate sin harinas.', 'difícil', 5, 40, 480, 8, 'tarta_chocolate.png', 'PENDIENTE', NULL),
(28, 13, '2025-12-04 05:26:13', 'Pan de semillas', 'Pan casero con costra crujiente y múltiples semillas saludables.', 'SALADO', 'Pan sin gluten con mezcla de semillas.', 'difícil', 4, 120, 1600, 10, 'pan_semillas.png', 'PENDIENTE', NULL),
(29, 13, '2025-12-04 05:26:13', 'Crepes de trigo sarraceno', 'Elaboradas con alforfón, ideales para rellenos salados y dulces ligeros.', 'SALADO', 'Crepes salados sin gluten.', 'media', 3, 20, 180, 4, 'crepes_sarraceno.png', 'PENDIENTE', NULL),
(30, 13, '2025-12-04 05:26:13', 'Galletas de almendra', 'Galletas crujientes y aromáticas con harina de almendra.', 'MERIENDA', 'Galletas sin gluten con almendra molida.', 'media', 4, 25, 220, 6, 'galletas_almendra.png', 'PENDIENTE', NULL),
(31, 13, '2025-12-04 05:26:13', 'Bizcocho de yogur sin gluten', 'Clásico bizcocho muy esponjoso y fácil de preparar.', 'POSTRE', 'Bizcocho clásico sin gluten con yogur natural.', 'media', 5, 45, 260, 8, 'bizcocho_yogur.png', 'PENDIENTE', NULL);
INSERT INTO `receta` (`id_receta`, `id_usuario`, `fecha_creacion`, `titulo`, `subtitulo`, `tipo_comida`, `descripcion`, `dificultad`, `valoracion`, `tiempo_preparacion`, `valor_energetico`, `comensales`, `imagen_url`, `estado`, `fecha_validacion`) VALUES
(32, 13, '2025-12-04 05:23:38', 'Tarta de queso sin gluten', 'Tarta cremosa y suave con un toque cítrico, sin base de galleta.', 'DESAYUNO', 'Tarta cremosa sin base de galleta.', 'media', 4, 50, 320, 6, 'tarta_queso.png', 'PENDIENTE', NULL),
(33, 13, '2025-12-04 05:26:13', 'Pan de lino y chía', 'Máximo aporte de fibra en un pan suave, ideal para sándwiches.', 'SALADO', 'Pan sin gluten con semillas de lino y chía.', 'difícil', 5, 120, 1550, 10, 'pan_lino_chia.png', 'PENDIENTE', NULL),
(34, 13, '2025-12-04 05:23:38', 'Tortitas de plátano', 'Tortitas dulces de dos ingredientes, rápidas y muy nutritivas.', 'DESAYUNO', 'Tortitas sin gluten con plátano y huevo.', 'fácil', 4, 10, 150, 2, 'tortitas_platano.png', 'PENDIENTE', NULL),
(35, 13, '2025-12-04 05:26:13', 'Crema de zanahoria', 'Crema suave de invierno con notas de jengibre fresco.', 'ALMUERZO', 'Crema suave sin gluten con zanahoria y jengibre.', 'fácil', 4, 20, 120, 4, 'crema_zanahoria.png', 'PENDIENTE', NULL),
(36, 13, '2025-12-04 05:26:13', 'Pizza sin gluten', 'Masa fácil de trabajar, base para tu pizza favorita sin gluten.', 'SALADO', 'Pizza con base de harina sin gluten.', 'fácil', 5, 30, 400, 4, 'pizza_singluten.png', 'PENDIENTE', NULL),
(37, 13, '2025-12-04 05:26:13', 'Galletas de avena y pasas', 'Un clásico de la repostería sin gluten, con canela y pasas.', 'MERIENDA', 'Galletas sin gluten con pasas y canela.', 'media', 3, 25, 200, 6, 'galletas_pasas.png', 'PENDIENTE', NULL),
(38, 13, '2025-12-04 05:26:13', 'Pan de trigo sarraceno y nueces', 'Pan rústico y nutritivo con trozos de nuez para un extra de sabor.', 'SALADO', 'Pan sin gluten con nueces troceadas.', 'difícil', 4, 100, 1650, 8, 'pan_sarraceno_nueces.png', 'PENDIENTE', NULL),
(39, 13, '2025-12-04 05:26:13', 'Bizcocho de calabaza', 'Bizcocho dulce con especias de otoño, muy jugoso.', 'POSTRE', 'Bizcocho sin gluten con puré de calabaza.', 'fácil', 4, 40, 270, 6, 'bizcocho_calabaza.png', 'PENDIENTE', NULL),
(40, 13, '2025-12-04 05:26:13', 'Tarta de manzana sin gluten', 'Deliciosa tarta apta para celíacos, con manzanas caramelizadas.', 'POSTRE', 'Deliciosa tarta apta para celiacos, con manzanas caramelizadas.', 'media', 5, 35, 300, 6, 'https://ejemplo.com/imagenes/tarta.jpg', 'PENDIENTE', NULL),
(41, 13, '2025-12-04 05:26:13', 'Albóndigas de lentejas', 'Albóndigas veganas, ricas en proteínas y aptas para todas las dietas.', 'CENA', 'Albóndigas veganas sin gluten con lentejas.', 'media', 5, 35, 300, 4, 'albondigas_lentejas.png', 'PENDIENTE', NULL),
(42, 13, '2025-12-04 05:23:38', 'Tarta de chocolate sin gluten', 'Postre indulgente e intenso, para los amantes del cacao puro.', 'DESAYUNO', 'Tarta intensa de chocolate sin harinas.', 'difícil', 5, 40, 480, 8, 'tarta_chocolate.png', 'PENDIENTE', NULL),
(43, 13, '2025-12-04 05:26:13', 'Pan de semillas', 'Pan casero con costra crujiente y múltiples semillas saludables.', 'SALADO', 'Pan sin gluten con mezcla de semillas.', 'difícil', 4, 120, 1600, 10, 'pan_semillas.png', 'PENDIENTE', NULL),
(44, 13, '2025-12-04 05:23:38', 'Crepes de trigo sarraceno', 'Elaboradas con alforfón, ideales para rellenos salados y dulces ligeros.', 'DESAYUNO', 'Crepes salados sin gluten.', 'media', 3, 20, 180, 4, 'crepes_sarraceno.png', 'PENDIENTE', NULL),
(45, 13, '2025-12-04 05:23:38', 'Galletas de almendra', 'Galletas crujientes y aromáticas con harina de almendra.', 'DESAYUNO', 'Galletas sin gluten con almendra molida.', 'media', 4, 25, 220, 6, 'galletas_almendra.png', 'PENDIENTE', NULL),
(46, 13, '2025-12-04 05:26:13', 'Bizcocho de yogur sin gluten', 'Clásico bizcocho muy esponjoso y fácil de preparar.', 'POSTRE', 'Bizcocho clásico sin gluten con yogur natural.', 'media', 5, 45, 260, 8, 'bizcocho_yogur.png', 'PENDIENTE', NULL),
(47, 13, '2025-12-04 05:23:38', 'Tarta de queso sin gluten', 'Tarta cremosa y suave con un toque cítrico, sin base de galleta.', 'DESAYUNO', 'Tarta cremosa sin base de galleta.', 'media', 4, 50, 320, 6, 'tarta_queso.png', 'PENDIENTE', NULL),
(48, 13, '2025-12-04 05:26:13', 'Pan de lino y chía', 'Máximo aporte de fibra en un pan suave, ideal para sándwiches.', 'SALADO', 'Pan sin gluten con semillas de lino y chía.', 'difícil', 5, 120, 1550, 10, 'pan_lino_chia.png', 'PENDIENTE', NULL),
(49, 13, '2025-12-04 05:23:38', 'Tortitas de plátano', 'Tortitas dulces de dos ingredientes, rápidas y muy nutritivas.', 'DESAYUNO', 'Tortitas sin gluten con plátano y huevo.', 'fácil', 4, 10, 150, 2, 'tortitas_platano.png', 'PENDIENTE', NULL),
(50, 13, '2025-12-04 05:26:13', 'Crema de zanahoria', 'Crema suave de invierno con notas de jengibre fresco.', 'ALMUERZO', 'Crema suave sin gluten con zanahoria y jengibre.', 'fácil', 4, 20, 120, 4, 'crema_zanahoria.png', 'PENDIENTE', NULL),
(51, 13, '2025-12-04 05:23:38', 'Pizza sin gluten', 'Masa fácil de trabajar, base para tu pizza favorita sin gluten.', 'DESAYUNO', 'Pizza con base de harina sin gluten.', 'fácil', 5, 30, 400, 4, 'pizza_singluten.png', 'PENDIENTE', NULL),
(52, 13, '2025-12-04 05:23:38', 'Galletas de avena y pasas', 'Un clásico de la repostería sin gluten, con pasas y canela.', 'DESAYUNO', 'Galletas sin gluten con pasas y canela.', 'media', 3, 25, 200, 6, 'galletas_pasas.png', 'PENDIENTE', NULL),
(53, 13, '2025-12-04 05:26:13', 'Pan de trigo sarraceno y nueces', 'Pan rústico y nutritivo con trozos de nuez para un extra de sabor.', 'SALADO', 'Pan sin gluten con nueces troceadas.', 'difícil', 4, 100, 1650, 8, 'pan_sarraceno_nueces.png', 'PENDIENTE', NULL),
(54, 13, '2025-12-04 05:26:13', 'Bizcocho de calabaza', 'Bizcocho dulce con especias de otoño, muy jugoso.', 'POSTRE', 'Bizcocho sin gluten con puré de calabaza.', 'fácil', 4, 40, 270, 6, 'bizcocho_calabaza.png', 'PENDIENTE', NULL),
(55, 13, '2025-12-04 05:26:13', 'Magdalenas de limón y semillas', 'Bocaditos esponjosos con ralladura de limón y semillas de amapola.', 'MERIENDA', 'Magdalenas esponjosas con ralladura de limón y semillas de amapola.', 'fácil', 5, 30, 280, 6, 'magdalenas_limon.png', 'PENDIENTE', NULL),
(56, 13, '2025-12-04 05:26:13', 'Gnocchis de patata sin gluten', 'Pasta de patata casera, fácil de combinar con cualquier salsa.', 'ALMUERZO', 'Pasta de patata casera, apta para celíacos.', 'media', 4, 35, 350, 2, 'gnocchis_patata.png', 'PENDIENTE', NULL),
(57, 13, '2025-12-04 05:26:13', 'Pan de molde con psyllium', 'Pan ideal para sándwiches, suave y con cáscara de psyllium para mejorar la textura.', 'SALADO', 'Pan suave, ideal para sándwiches, con cáscara de psyllium.', 'difícil', 5, 120, 1500, 10, 'pan_psyllium.png', 'PENDIENTE', NULL),
(58, 13, '2025-12-04 05:23:38', 'Muffins de arándanos y avena', 'Postre rápido y saludable, ideal para el desayuno o merienda.', 'DESAYUNO', 'Postre rápido y saludable, con avena certificada sin gluten.', 'fácil', 4, 25, 190, 6, 'muffins_arandanos.png', 'PENDIENTE', NULL),
(59, 13, '2025-12-04 05:26:13', 'Lasagna de verduras sin pasta', 'Capas de verduras asadas en lugar de láminas de pasta, más ligera y sabrosa.', 'ALMUERZO', 'Capas de verduras asadas en lugar de láminas de pasta.', 'media', 3, 60, 450, 4, 'lasagna_verduras.png', 'PENDIENTE', NULL),
(60, 13, '2025-12-04 05:26:13', 'Falafel al horno', 'Bolitas de garbanzo especiadas, cocinadas sin freír, opción saludable.', 'CENA', 'Bolitas de garbanzo especiadas, cocinadas sin freír.', 'fácil', 4, 30, 310, 4, 'falafel_horno.png', 'PENDIENTE', NULL),
(61, 13, '2025-12-04 05:26:13', 'Pimientos rellenos de quinoa', 'Plato principal completo con pimientos rojos rellenos de quinoa y vegetales.', 'ALMUERZO', 'Plato principal completo con pimientos rojos y mezcla de quinoa.', 'media', 4, 50, 410, 2, 'pimientos_quinoa.png', 'PENDIENTE', NULL),
(62, 13, '2025-12-04 05:26:13', 'Crema de brócoli y queso', 'Crema suave y nutritiva de brócoli con un toque de queso.', 'ALMUERZO', 'Crema suave y nutritiva, sin espesantes con gluten.', 'fácil', 4, 20, 150, 4, 'crema_brocoli.png', 'PENDIENTE', NULL),
(63, 13, '2025-12-04 05:26:13', 'Buñuelos de viento sin gluten', 'Postre tradicional, frito o al horno, con harina especial para celíacos.', 'POSTRE', 'Postre tradicional frito, con harina especial para celíacos.', 'difícil', 4, 50, 380, 6, 'bunuelos_viento.png', 'PENDIENTE', NULL),
(64, 13, '2025-12-04 05:26:13', 'Tarta de calabacín y queso', 'Quiche sin base de masa (opcional) o con masa de arroz, ideal para cenar.', 'CENA', 'Quiche sin base de masa (opcional) o con masa de arroz.', 'media', 3, 40, 370, 4, 'tarta_calabacin.png', 'PENDIENTE', NULL),
(65, 13, '2025-12-04 05:26:13', 'Sopa de pescado sin gluten', 'Caldo claro y reconfortante con pescado y marisco, sin potenciadores con gluten.', 'ALMUERZO', 'Caldo claro con pescado y marisco, sin potenciadores con gluten.', 'fácil', 4, 30, 290, 2, 'sopa_pescado.png', 'PENDIENTE', NULL),
(66, 13, '2025-12-04 05:23:38', 'Tostadas francesas sin gluten', 'Desayuno o brunch dulce usando pan de molde sin gluten.', 'DESAYUNO', 'Desayuno dulce usando pan de molde sin gluten.', 'fácil', 5, 15, 240, 2, 'tostadas_francesas.png', 'PENDIENTE', NULL),
(67, 13, '2025-12-04 05:26:13', 'Pollo a la cerveza sin gluten', 'Plato de carne jugoso cocinado lentamente con cerveza sin gluten.', 'CENA', 'Plato de carne cocinado con cerveza sin gluten.', 'media', 4, 50, 430, 4, 'pollo_cerveza.png', 'PENDIENTE', NULL),
(68, 13, '2025-12-04 05:26:13', 'Mousse de chocolate y aguacate', 'Postre cremoso sin lácteos y sin harinas, ideal para dietas especiales.', 'POSTRE', 'Postre cremoso sin lácteos y sin harinas.', 'fácil', 5, 15, 210, 4, 'mousse_aguacate.png', 'PENDIENTE', NULL),
(69, 13, '2025-12-04 05:26:13', 'Pan pita de lentejas rojas', 'Pan plano de legumbres, rico en proteínas, ideal para rellenar con lo que prefieras.', 'SALADO', 'Pan plano de legumbres, ideal para rellenar.', 'media', 3, 35, 270, 4, 'pan_pita.png', 'PENDIENTE', NULL),
(70, 13, '2025-12-04 05:23:38', 'Bowl de desayuno con chía', 'Tazón de chía y fruta, perfecto para empezar el día con energía.', 'DESAYUNO', 'Bowl de chía y fruta, perfecto para empezar el día.', 'fácil', 5, 5, 160, 1, 'bowl_chia.png', 'PENDIENTE', NULL),
(71, 13, '2025-12-04 05:26:13', 'Cuscús de coliflor con verduras', 'Alternativa al cuscús tradicional usando coliflor rallada, bajo en carbohidratos.', 'SALADO', 'Alternativa al cuscús tradicional usando coliflor rallada.', 'fácil', 4, 20, 250, 2, 'cuscus_coliflor.png', 'PENDIENTE', NULL),
(72, 13, '2025-12-04 05:23:38', 'Waffles de yuca', 'Gofres crujientes sin gluten hechos con harina de yuca, perfectos para el brunch.', 'DESAYUNO', 'Gofres crujientes sin gluten hechos con harina de yuca (tapioca).', 'media', 4, 25, 330, 4, 'waffles_yuca.png', 'PENDIENTE', NULL),
(73, 13, '2025-12-04 05:26:13', 'Pasta de calabacín (Zoodles) al pesto', 'Fideos de calabacín crudos con salsa pesto casera (sin lácteos), opción fresca.', 'ALMUERZO', 'Fideos de calabacín crudos con salsa pesto casera (sin lácteos).', 'fácil', 5, 15, 190, 2, 'zoodles_pesto.png', 'PENDIENTE', NULL),
(74, 13, '2025-12-04 05:26:13', 'Muffins de boniato y canela', 'Muffins dulces sin gluten con puré de boniato, húmedos y especiados.', 'MERIENDA', 'Muffins dulces sin gluten con puré de boniato.', 'media', 4, 30, 260, 6, 'muffins_boniato.png', 'PENDIENTE', NULL),
(75, 13, '2025-12-04 05:26:13', 'Hummus de remolacha', 'Hummus de garbanzos con remolacha, color vibrante y sabor terroso, para dippear.', 'TAPAS', 'Hummus de garbanzos con remolacha, color vibrante y sabor terroso.', 'fácil', 3, 10, 180, 4, 'hummus_remolacha.png', 'PENDIENTE', NULL),
(76, 13, '2025-12-04 05:26:13', 'Pan de sésamo y arroz', 'Pan denso sin gluten a base de harina de arroz y semillas de sésamo, con cuerpo.', 'SALADO', 'Pan denso sin gluten a base de harina de arroz y semillas de sésamo.', 'difícil', 4, 120, 1500, 8, 'pan_sesamo_arroz.png', 'PENDIENTE', NULL),
(77, 13, '2025-12-04 05:26:13', 'Sopa fría de aguacate y pepino', 'Sopa refrescante, cremosa y sin cocción, ideal para el verano.', 'TAPAS', 'Sopa refrescante, cremosa y sin cocción.', 'fácil', 5, 10, 140, 2, 'sopa_aguacate.png', 'PENDIENTE', NULL),
(78, 13, '2025-12-04 05:26:13', 'Chili con carne sin gluten', 'Guiso de carne y frijoles, espeso y sin espesantes con gluten, plato único.', 'ALMUERZO', 'Guiso de carne y frijoles, espeso y sin espesantes con gluten.', 'media', 4, 60, 470, 4, 'chili_carne.png', 'PENDIENTE', NULL),
(79, 13, '2025-12-04 05:26:13', 'Biscotti de almendra y naranja', 'Galletas italianas duras, sin gluten, ideales para mojar en café o vino dulce.', 'DULCE', 'Galletas italianas duras, sin gluten, ideales para mojar.', 'difícil', 4, 45, 300, 8, 'biscotti_naranja.png', 'PENDIENTE', NULL),
(80, 13, '2025-12-04 05:26:13', 'Bizcocho de naranja y aceite de oliva', 'Bizcocho jugoso sin gluten con naranja entera y aceite de oliva, muy aromático.', 'POSTRE', 'Bizcocho jugoso sin gluten con naranja entera y aceite de oliva.', 'media', 5, 45, 350, 8, 'bizcocho_naranja.png', 'PENDIENTE', NULL),
(81, 13, '2025-12-04 05:26:13', 'Pescado al papillote con verduras', 'Pescado cocinado en papel de horno con limón y eneldo, receta muy limpia.', 'CENA', 'Salmón o merluza cocinado en papel de horno con limón y eneldo.', 'fácil', 4, 20, 280, 2, 'pescado_papillote.png', 'PENDIENTE', NULL),
(82, 13, '2025-12-04 05:26:13', 'Donuts de patata y cacao', 'Donuts horneados sin gluten, usando puré de patata para la base, ¡sorprendente!', 'POSTRE', 'Donuts horneados sin gluten, usando puré de patata para la base.', 'media', 4, 40, 390, 6, 'donuts_patata.png', 'PENDIENTE', NULL),
(83, 13, '2025-12-04 05:26:13', 'Crema de lentejas rojas y coco', 'Curry de lentejas rojas suave, espeso con leche de coco, toque exótico.', 'ALMUERZO', 'Curry de lentejas rojas suave, espeso con leche de coco.', 'fácil', 5, 25, 230, 4, 'crema_lentejas_rojas.png', 'PENDIENTE', NULL),
(84, 13, '2025-12-04 05:26:13', 'Quesadillas de harina de garbanzo', 'Tortillas hechas con harina de garbanzo, rellenas de queso y verduras, para un almuerzo rápido.', 'SALADO', 'Tortillas hechas con harina de garbanzo, rellenas de queso y verduras.', 'media', 3, 20, 360, 2, 'quesadillas_garbanzo.png', 'PENDIENTE', NULL),
(85, 13, '2025-12-04 05:26:13', 'Pan de quinoa y linaza', 'Pan nutritivo sin gluten, muy rico en fibra, ideal para un estilo de vida activo.', 'SALADO', 'Pan nutritivo sin gluten, muy rico en fibra.', 'difícil', 5, 120, 1450, 8, 'pan_quinoa_linaza.png', 'PENDIENTE', NULL),
(86, 13, '2025-12-04 05:26:13', 'Natillas de chía y vainilla', 'Postre tipo natilla, sin huevo ni lácteos, espeso con semillas de chía, muy sano.', 'POSTRE', 'Postre tipo natilla, sin huevo ni lácteos, espeso con semillas de chía.', 'fácil', 4, 5, 170, 4, 'natillas_chia.png', 'PENDIENTE', NULL),
(87, 13, '2025-12-04 05:26:13', 'Pastel de carne y patata (Cottage Pie)', 'Pastel cubierto con puré de patata en lugar de masa de harina, reconfortante.', 'CENA', 'Pastel cubierto con puré de patata en lugar de masa de harina.', 'media', 4, 60, 490, 4, 'cottage_pie.png', 'PENDIENTE', NULL),
(88, 13, '2025-12-04 05:26:13', 'Focaccia de harina de teff', 'Pan plano italiano sin gluten, esponjoso, hecho con harina de teff, perfecto para acompañar.', 'SALADO', 'Pan plano italiano sin gluten, esponjoso, hecho con harina de teff.', 'difícil', 4, 120, 1550, 8, 'focaccia_teff.png', 'PENDIENTE', NULL),
(89, 13, '2025-12-04 05:26:13', 'Sopa de tomate y albahaca', 'Sopa de tomate cremosa, sin nata ni espesantes de harina, un clásico ligero.', 'ALMUERZO', 'Sopa de tomate cremosa, sin nata ni espesantes de harina.', 'fácil', 5, 15, 130, 4, 'sopa_tomate.png', 'PENDIENTE', NULL),
(90, 13, '2025-12-04 05:26:13', 'Rollitos de sushi sin arroz', 'Ensalada envuelta en alga nori, usando pepino o boniato en lugar de arroz, bajo en carbohidratos.', 'TAPAS', 'Ensalada envuelta en alga nori, usando pepino o boniato en lugar de arroz.', 'media', 3, 30, 220, 2, 'sushi_sinarroz.png', 'PENDIENTE', NULL),
(91, 13, '2025-12-04 05:26:13', 'Tarta de pera y jengibre', 'Tarta especiada sin gluten con rodajas de pera, ideal para la sobremesa.', 'POSTRE', 'Tarta especiada sin gluten con rodajas de pera.', 'media', 4, 45, 310, 6, 'tarta_pera_jengibre.png', 'PENDIENTE', NULL),
(92, 13, '2025-12-04 05:26:13', 'Fajitas de lechuga rellenas', 'Carne o verduras envueltas en hojas de lechuga crujientes, muy refrescante y ligero.', 'ALMUERZO', 'Carne o verduras envueltas en hojas de lechuga crujientes.', 'fácil', 5, 15, 200, 2, 'fajitas_lechuga.png', 'PENDIENTE', NULL),
(93, 13, '2025-12-04 05:26:13', 'Pan de molde de avena y nueces', 'Pan suave sin gluten, ideal para tostadas, con avena y nueces para un extra de sabor.', 'SALADO', 'Pan suave sin gluten, ideal para tostadas.', 'difícil', 4, 120, 1500, 8, 'pan_molde_avena.png', 'PENDIENTE', NULL),
(94, 13, '2025-12-04 05:26:13', 'Tarta de plátano y nueces', 'Variación de pan de plátano con forma de tarta, un clásico para la merienda.', 'MERIENDA', 'Variación de pan de plátano con forma de tarta.', 'media', 4, 40, 290, 6, 'tarta_platano_nueces.png', 'PENDIENTE', NULL),
(95, 13, '2025-12-04 05:26:13', 'Puding de mango y tapioca', 'Postre cremoso de tapioca perla sin gluten con pulpa de mango, muy tropical.', 'POSTRE', 'Postre cremoso de tapioca perla sin gluten con pulpa de mango.', 'fácil', 5, 20, 230, 4, 'puding_tapioca.png', 'PENDIENTE', NULL),
(96, 13, '2025-12-04 05:26:13', 'Curry de garbanzos y espinacas', 'Curry vegano, plato principal nutritivo y especiado, lleno de sabor.', 'ALMUERZO', 'Curry vegano, plato principal nutritivo y especiado.', 'media', 4, 35, 370, 4, 'curry_garbanzos.png', 'PENDIENTE', NULL),
(97, 13, '2025-12-04 05:26:13', 'Palitos de queso y yuca', 'Bocaditos salados de queso y almidón de yuca (tipo Pandebono), adictivos.', 'TAPAS', 'Bocaditos salados de queso y almidón de yuca (tipo Pandebono).', 'media', 3, 25, 320, 4, 'palitos_yuca.png', 'PENDIENTE', NULL),
(98, 13, '2025-12-04 05:26:13', 'Mousse de limón y coco', 'Mousse ligera y ácida de limón y coco, sin lácteos, ideal para el verano.', 'POSTRE', 'Mousse ligera y ácida, sin lácteos.', 'fácil', 5, 15, 190, 4, 'mousse_limon_coco.png', 'PENDIENTE', NULL),
(99, 13, '2025-12-04 05:26:13', 'Hamburguesas de champiñones', 'Hamburguesas vegetarianas sin pan, usando champiñones portobello, muy sabroso.', 'CENA', 'Hamburguesas vegetarianas sin pan, usando champiñones portobello.', 'media', 4, 30, 340, 2, 'hamburguesas_champi.png', 'PENDIENTE', NULL),
(100, 13, '2025-12-04 05:26:13', 'Tarta de cerezas y almendra', 'Tarta de frutas sin gluten con cubierta de crumble de almendra, un postre elegante.', 'POSTRE', 'Tarta de frutas sin gluten con cubierta de crumble de almendra.', 'difícil', 5, 40, 380, 6, 'tarta_cerezas_almendra.png', 'PENDIENTE', NULL),
(101, 13, '2025-12-04 05:26:13', 'Bollos de canela sin gluten', 'Rollos de canela dulces y pegajosos, hechos con mezcla de harinas sin gluten, un capricho.', 'DULCE', 'Rollos de canela dulces y pegajosos, hechos con mezcla de harinas sin gluten.', 'difícil', 4, 120, 1600, 8, 'rollos_canela.png', 'PENDIENTE', NULL),
(102, 13, '2025-12-04 05:26:13', 'Crema de espárragos verdes', 'Sopa de espárragos licuados, cremosa por la propia verdura, muy depurativa.', 'ALMUERZO', 'Sopa de espárragos licuados, cremosa por la propia verdura.', 'fácil', 4, 20, 110, 4, 'crema_esparragos.png', 'PENDIENTE', NULL),
(103, 13, '2025-12-04 05:23:38', 'Tostadas de aguacate con huevo', 'Tostada con pan sin gluten, aguacate y huevo poché, desayuno de moda.', 'DESAYUNO', 'Tostada con pan sin gluten, aguacate y huevo poché.', 'fácil', 5, 10, 240, 2, 'tostadas_aguacate.png', 'PENDIENTE', NULL),
(104, 13, '2025-12-04 05:26:13', 'Risotto de champiñones y trufa', 'Risotto cremoso hecho con arroz Arborio (naturalmente sin gluten), plato italiano auténtico.', 'ALMUERZO', 'Risotto cremoso hecho con arroz Arborio (naturalmente sin gluten).', 'media', 4, 40, 460, 4, 'risotto_champi.png', 'PENDIENTE', NULL),
(105, 13, '2025-12-04 05:26:13', 'Wraps de lechuga con pollo tailandés', 'Pollo salteado con salsa de cacahuete, servido en hojas de lechuga, ligero y oriental.', 'TAPAS', 'Pollo salteado con salsa de cacahuete, servido en hojas de lechuga.', 'media', 4, 30, 330, 2, 'wraps_pollo.png', 'PENDIENTE', NULL),
(106, 13, '2025-12-04 05:26:13', 'Pan de patata dulce', 'Pan hecho con harina y patata dulce cocida, muy suave y ligeramente dulce.', 'SALADO', 'Pan hecho con harina y patata dulce cocida, muy suave.', 'difícil', 5, 120, 1450, 8, 'pan_patata_dulce.png', 'PENDIENTE', NULL),
(107, 13, '2025-12-04 05:26:13', 'Cupcakes de red velvet sin gluten', 'Muffins de terciopelo rojo con harina de arroz y queso crema, perfectos para celebraciones.', 'POSTRE', 'Muffins de terciopelo rojo con harina de arroz y queso crema.', 'media', 4, 45, 360, 6, 'cupcakes_redvelvet.png', 'PENDIENTE', NULL),
(108, 13, '2025-12-04 05:26:13', 'Gazpacho andaluz tradicional', 'Sopa fría de tomate, pepino y pimiento (se omite el pan), un básico del verano.', 'TAPAS', 'Sopa fría de tomate, pepino y pimiento (se omite el pan).', 'fácil', 5, 15, 120, 4, 'gazpacho.png', 'PENDIENTE', NULL),
(109, 13, '2025-12-04 05:26:13', 'Pinchos de pollo yakitori', 'Brochetas de pollo glaseado con salsa de soja sin gluten, aperitivo japonés.', 'TAPAS', 'Brochetas de pollo glaseado con salsa de soja sin gluten.', 'media', 4, 35, 390, 4, 'pinchos_yakitori.png', 'PENDIENTE', NULL),
(110, 13, '2025-12-04 05:26:13', 'Tarta de tres leches sin gluten', 'Bizcocho bañado en tres leches, hecho con harina de arroz, postre latinoamericano.', 'POSTRE', 'Bizcocho bañado en tres leches, hecho con harina de arroz.', 'difícil', 5, 50, 450, 8, 'tarta_tresleches.png', 'PENDIENTE', NULL),
(111, 13, '2025-12-04 05:23:38', 'Batido verde desintoxicante', 'Bebida de espinacas, manzana, plátano y jengibre, para empezar el día depurando.', 'DESAYUNO', 'Bebida de espinacas, manzana, plátano y jengibre.', 'fácil', 5, 5, 100, 1, 'batido_verde.png', 'PENDIENTE', NULL),
(112, 13, '2025-12-04 05:26:13', 'Buñuelos de bacalao y yuca', 'Buñuelos fritos de bacalao, utilizando yuca como base, crujientes y sabrosos.', 'TAPAS', 'Buñuelos fritos de bacalao, utilizando yuca como base.', 'media', 4, 40, 410, 4, 'bunuelos_bacalao.png', 'PENDIENTE', NULL),
(113, 13, '2025-12-04 05:26:13', 'Quiche de espinacas sin base', 'Quiche solo de relleno (huevo, queso, espinacas) sin masa de harina, bajo en carbohidratos.', 'CENA', 'Quiche solo de relleno (huevo, queso, espinacas) sin masa de harina.', 'fácil', 4, 35, 300, 4, 'quiche_sinbase.png', 'PENDIENTE', NULL),
(114, 13, '2025-12-04 05:26:13', 'Pan de molde de avena y chía', 'Pan de sándwich con alto contenido en fibra, con avena y chía, muy nutritivo.', 'SALADO', 'Pan de sándwich con alto contenido en fibra.', 'difícil', 5, 120, 1400, 8, 'pan_molde_chia.png', 'PENDIENTE', NULL),
(115, 13, '2025-12-04 05:26:13', 'Crema de coliflor y ajo asado', 'Sopa cremosa con coliflor y sabor intenso a ajo asado, muy reconfortante.', 'ALMUERZO', 'Sopa cremosa con coliflor y sabor intenso a ajo asado.', 'media', 4, 25, 160, 4, 'crema_coliflor.png', 'PENDIENTE', NULL),
(116, 13, '2025-12-04 05:26:13', 'Ensalada caprese con mozzarella', 'Ensalada clásica de tomate, mozzarella y albahaca, fresca y mediterránea.', 'TAPAS', 'Ensalada clásica de tomate, mozzarella y albahaca.', 'fácil', 5, 10, 180, 2, 'ensalada_caprese.png', 'PENDIENTE', NULL),
(117, 13, '2025-12-04 05:26:13', 'Cheesecake de calabaza sin base', 'Tarta de queso horneada con sabor a calabaza, sin base de galleta, para el otoño.', 'POSTRE', 'Tarta de queso horneada con sabor a calabaza, sin base de galleta.', 'media', 4, 45, 350, 6, 'cheesecake_calabaza.png', 'PENDIENTE', NULL),
(118, 13, '2025-12-04 05:26:13', 'Galletas de mantequilla de cacahuete', 'Galletas simples, sin harina, solo con cacahuete, azúcar y huevo, ideales para alérgicos al gluten.', 'MERIENDA', 'Galletas simples, sin harina, solo con cacahuete, azúcar y huevo.', 'fácil', 5, 15, 250, 6, 'galletas_cacahuete.png', 'PENDIENTE', NULL),
(119, 13, '2025-12-04 05:26:13', 'Sopa de miso con tofu y verduras', 'Caldo japonés ligero, naturalmente sin gluten (usando miso certificado), muy digestivo.', 'ALMUERZO', 'Caldo japonés ligero, naturalmente sin gluten (usando miso certificado).', 'fácil', 4, 10, 90, 2, 'sopa_miso.png', 'PENDIENTE', NULL),
(120, 13, '2025-12-04 05:26:13', 'Tarta de nueces y caramelo', 'Tarta con harina sin gluten, cubierta de nueces y sirope de caramelo, muy golosa.', 'POSTRE', 'Tarta con harina sin gluten, cubierta de nueces y sirope de caramelo.', 'difícil', 4, 50, 420, 6, 'tarta_nueces_caramelo.png', 'PENDIENTE', NULL),
(121, 25, '2025-12-11 00:00:00', '🍚 Ensalada de Arroz con Frutas Tropicales y Aderezo Cítrico 🥭🍍', 'Un festival de sabor y color: la ensalada perfecta, ligera y refrescante, ideal para el verano.', 'CENA', 'Esta ensalada de arroz con frutas es una opción fantástica, sin gluten y vegana, perfecta para servir como entrante, guarnición o un almuerzo ligero. Combina la neutralidad y la textura del arroz de grano largo con la dulzura vibrante y los toques ácidos de una selección de frutas tropicales y de temporada. El aderezo a base de cítricos y jengibre no solo realza los sabores naturales de las frutas, sino que también aporta una nota fresca y picante que equilibra perfectamente el plato. Es increíblemente adaptable, fácil de preparar con antelación y visualmente muy atractiva gracias a la mezcla de colores. ¡Una explosión de sabor y energía saludable!\n🛒 Ingredientes\n🥣 Para la Ensalada\nArroz: 200 g de arroz de grano largo (o basmati)\n\nFrutas:\n\n1 mango grande, maduro, cortado en cubos\n\n1 taza de piña fresca, cortada en cubos\n\n1 taza de uvas rojas o verdes, cortadas por la mitad\n\n1/2 taza de arándanos frescos\n\n1 manzana verde, cortada en cubos pequeños (opcional)\n\nVerduras/Hierbas:\n\n1/2 pimiento rojo, cortado en dados pequeños\n\n1/4 taza de cebolleta o cebollino fresco, picado finamente\n\nUn puñado de hojas de menta fresca, picadas (para decorar/mezclar)\n\nToque Crujiente (Opcional): 1/4 taza de nueces o anacardos, ligeramente tostados y picados.\n\n🍋 Para el Aderezo Cítrico y de Jengibre\nAceite: 3 cucharadas de aceite de oliva virgen extra\n\nÁcido: 2 cucharadas de jugo de limón recién exprimido\n\nDulce: 1 cucharada de miel de maple (si es vegano) o miel de abeja\n\nSabor: 1 cucharadita de jengibre fresco, rallado\n\nSazón: Sal marina y pimienta negra recién molida al gusto\n\n', 'BAJA', 0, 20, 400, 4, '', 'APROBADO', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `receta_ingrediente`
--

CREATE TABLE `receta_ingrediente` (
  `id_receta_ingrediente` int NOT NULL,
  `id_receta` int NOT NULL,
  `id_ingrediente` int NOT NULL,
  `cantidad` int NOT NULL
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
(70, 40, 5, 1),
(71, 21, 21, 3),
(72, 21, 22, 150),
(73, 22, 1, 250),
(74, 22, 6, 30),
(75, 23, 20, 200),
(76, 23, 4, 1),
(77, 24, 12, 300),
(78, 24, 16, 150),
(79, 25, 16, 100),
(80, 25, 6, 40),
(81, 26, 15, 150),
(82, 26, 8, 50),
(83, 27, 9, 150),
(84, 27, 2, 100),
(85, 28, 3, 200),
(86, 28, 6, 30),
(87, 29, 3, 120),
(88, 29, 4, 2),
(89, 30, 2, 200),
(90, 30, 23, 100),
(91, 31, 22, 200),
(92, 31, 5, 150),
(93, 32, 5, 200),
(94, 32, 4, 3),
(95, 33, 1, 200),
(96, 33, 6, 20),
(97, 34, 7, 2),
(98, 34, 24, 1),
(99, 35, 8, 200),
(100, 35, 5, 100),
(101, 71, 57, 500),
(102, 71, 36, 100),
(103, 71, 49, 100),
(104, 71, 32, 5),
(105, 72, 58, 300),
(106, 72, 54, 50),
(107, 72, 28, 100),
(108, 72, 38, 50),
(109, 73, 69, 2),
(110, 73, 70, 50),
(111, 73, 44, 50),
(112, 73, 46, 50),
(113, 74, 59, 200),
(114, 74, 45, 2),
(115, 74, 55, 5),
(116, 74, 61, 5),
(117, 75, 49, 200),
(118, 75, 87, 300),
(119, 75, 29, 30),
(120, 75, 37, 15),
(121, 76, 88, 300),
(122, 76, 56, 10),
(123, 76, 29, 50),
(124, 76, 55, 3),
(125, 77, 69, 1),
(126, 77, 89, 2),
(127, 77, 37, 10),
(128, 77, 33, 100),
(129, 78, 36, 500),
(130, 78, 30, 10),
(131, 78, 90, 200),
(132, 78, 91, 10),
(133, 79, 92, 200),
(134, 79, 93, 50),
(135, 79, 75, 10),
(136, 79, 45, 1),
(137, 80, 93, 1),
(138, 80, 46, 100),
(139, 80, 55, 5),
(140, 80, 45, 1),
(141, 81, 94, 1),
(142, 81, 36, 50),
(143, 81, 95, 1),
(144, 81, 96, 5),
(145, 82, 85, 200),
(146, 82, 97, 50),
(147, 82, 55, 3),
(148, 82, 38, 50),
(149, 83, 33, 200),
(150, 83, 98, 300),
(151, 83, 36, 500),
(152, 83, 31, 5),
(153, 84, 26, 150),
(154, 84, 99, 100),
(155, 84, 46, 10),
(156, 84, 30, 2),
(157, 85, 100, 300),
(158, 85, 63, 50),
(159, 85, 56, 15),
(160, 85, 55, 4),
(161, 86, 101, 50),
(162, 86, 45, 3),
(163, 86, 28, 300),
(164, 86, 38, 20),
(165, 87, 85, 500),
(166, 87, 36, 200),
(167, 87, 102, 300),
(168, 87, 50, 1),
(169, 88, 60, 300),
(170, 88, 46, 50),
(171, 88, 56, 10),
(172, 88, 55, 3),
(173, 89, 103, 500),
(174, 89, 70, 20),
(175, 89, 36, 300),
(176, 89, 50, 1),
(177, 90, 40, 5),
(178, 90, 69, 1),
(179, 90, 104, 100),
(180, 90, 39, 10),
(181, 91, 105, 2),
(182, 91, 32, 10),
(183, 91, 55, 5),
(184, 91, 92, 150),
(185, 92, 106, 300),
(186, 92, 107, 1),
(187, 92, 30, 5),
(188, 92, 39, 10),
(189, 93, 108, 300),
(190, 93, 109, 50),
(191, 93, 56, 15),
(192, 93, 55, 5),
(193, 94, 110, 2),
(194, 94, 109, 50),
(195, 94, 55, 4),
(196, 94, 45, 2),
(197, 95, 73, 50),
(198, 95, 72, 1),
(199, 95, 33, 200),
(200, 95, 38, 20),
(201, 96, 87, 300),
(202, 96, 42, 100),
(203, 96, 33, 250),
(204, 96, 31, 5),
(205, 97, 58, 200),
(206, 97, 99, 100),
(207, 97, 54, 50),
(208, 98, 95, 1),
(209, 98, 76, 50),
(210, 98, 33, 150),
(211, 98, 38, 30),
(212, 99, 41, 300),
(213, 99, 26, 50),
(214, 99, 43, 20),
(215, 99, 39, 10),
(216, 100, 74, 150),
(217, 100, 92, 200),
(218, 100, 55, 4),
(219, 100, 45, 2),
(220, 101, 55, 5),
(221, 101, 111, 5),
(222, 101, 28, 150),
(223, 101, 56, 10),
(224, 102, 112, 300),
(225, 102, 36, 400),
(226, 102, 33, 50),
(227, 102, 50, 1),
(228, 103, 89, 1),
(229, 103, 113, 2),
(230, 103, 114, 100),
(231, 103, 37, 5),
(232, 104, 67, 200),
(233, 104, 41, 150),
(234, 104, 71, 10),
(235, 104, 36, 500),
(236, 105, 107, 1),
(237, 105, 106, 200),
(238, 105, 47, 50),
(239, 105, 39, 20),
(240, 106, 59, 250),
(241, 106, 56, 10),
(242, 106, 55, 3),
(243, 106, 60, 100),
(244, 107, 88, 200),
(245, 107, 45, 2),
(246, 107, 55, 4),
(247, 107, 37, 10),
(248, 108, 103, 500),
(249, 108, 69, 1),
(250, 108, 115, 1),
(251, 108, 37, 10),
(252, 109, 106, 300),
(253, 109, 39, 30),
(254, 109, 38, 20),
(255, 109, 32, 5),
(256, 110, 88, 200),
(257, 110, 81, 150),
(258, 110, 82, 100),
(259, 110, 83, 100),
(260, 111, 42, 50),
(261, 111, 110, 1),
(262, 111, 32, 5),
(263, 111, 116, 1),
(264, 112, 58, 300),
(265, 112, 117, 200),
(266, 112, 113, 1),
(267, 112, 30, 2),
(268, 113, 42, 150),
(269, 113, 99, 100),
(270, 113, 113, 3),
(271, 113, 83, 100),
(272, 114, 108, 300),
(273, 114, 101, 30),
(274, 114, 56, 15),
(275, 114, 55, 5),
(276, 115, 57, 400),
(277, 115, 118, 50),
(278, 115, 36, 300),
(279, 115, 33, 50),
(280, 116, 103, 200),
(281, 116, 119, 150),
(282, 116, 70, 10),
(283, 116, 37, 10),
(284, 117, 66, 200),
(285, 117, 120, 300),
(286, 117, 45, 2),
(287, 117, 111, 5),
(288, 118, 47, 200),
(289, 118, 113, 1),
(290, 118, 121, 100),
(291, 119, 122, 20),
(292, 119, 34, 150),
(293, 119, 36, 500),
(294, 119, 78, 50),
(295, 120, 109, 100),
(296, 120, 55, 4),
(297, 120, 38, 50),
(298, 120, 92, 200),
(300, 1, 124, 50);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `restaurante`
--

CREATE TABLE `restaurante` (
  `id_restaurante` int NOT NULL,
  `fecha_publicacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `nombre` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `direccion` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `titulo` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL,
  `subtitulo` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL,
  `descripcion` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `url_web` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `imagen_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ubicacion` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `codigo_postal` int NOT NULL,
  `latitud` decimal(10,8) NOT NULL,
  `longitud` decimal(11,8) NOT NULL,
  `telefono` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `id_usuario` int NOT NULL,
  `estado` enum('PENDIENTE','APROBADO','RECHAZADO') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT 'PENDIENTE',
  `fecha_validacion` timestamp NULL DEFAULT NULL,
  `valoracion` int NOT NULL,
  `tipo_restaurante` enum('MEXICANA','MEDITERRANEA','ASIATICA','VEGANO','SIN_GLUTEN') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT 'SIN_GLUTEN'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `restaurante`
--

INSERT INTO `restaurante` (`id_restaurante`, `fecha_publicacion`, `nombre`, `direccion`, `titulo`, `subtitulo`, `descripcion`, `url_web`, `imagen_url`, `ubicacion`, `codigo_postal`, `latitud`, `longitud`, `telefono`, `email`, `id_usuario`, `estado`, `fecha_validacion`, `valoracion`, `tipo_restaurante`) VALUES
(1, '2025-11-24 11:40:12', 'Vega', 'Calle Luna, 9', 'Vega', 'Restaurante vegano y sin gluten en el centro de Madrid, especializado en cocina sostenible.', '\"Restaurante vegano y ecológico pionero en cocina creativa y fusión gastronómica. Un espacio cercano, moderno y único donde disfrutar de alimentación consciente.Somos \"Activistas del sabor\"©\"', 'https://www.govega.es/', 'https://restaurantevega.com/wp-content/uploads/2023/vega-plato.jpg', 'Madrid, España', 28004, 52.19612460, -8.39395910, '+34 915123456', 'hola@lgovega.es', 13, 'APROBADO', NULL, 4, NULL),
(2, '2025-10-31 06:07:18', 'La Encomienda', 'Calle Encomienda 19', '', '', 'Cocina vegana creativa con ingredientes de temporada.', 'https://laencomienda.es', 'https://laencomienda.es/wp-content/uploads/2023/ensalada.jpg', 'Madrid', 28012, 38.78537360, -6.24913150, '0', '', 13, 'PENDIENTE', NULL, 0, NULL),
(3, '2025-10-31 06:07:18', 'B13 Bar', 'Calle Ballesta 13, 28004 Madrid', '', '', 'Bar vegano con tapas clásicas y ambiente alternativo.', 'https://b13bar.com', 'https://b13bar.com/wp-content/uploads/2023/burger.jpg', 'Madrid', 0, 40.42190660, -3.70347230, '0', '', 13, 'PENDIENTE', NULL, 0, NULL),
(4, '2025-10-31 06:07:18', 'Vegan Rock', 'Calle San Juan de la Cruz 9, 50006 Zaragoza', '', '', 'Comida rápida vegana con hamburguesas y bocadillos.', 'https://veganrock.es', 'https://veganrock.es/wp-content/uploads/2023/vegan-burger.jpg', 'Zaragoza', 0, 41.64147810, -0.89293440, '0', '', 13, 'PENDIENTE', NULL, 0, NULL),
(5, '2025-10-31 06:07:18', 'The Green Spot', 'Carrer de la Reina Cristina 12, 08003 Barcelona', '', '', 'Cocina vegetal internacional en un espacio elegante.', 'https://www.encompaniadelobos.com/restaurantes/the-green-spot/', 'https://www.encompaniadelobos.com/wp-content/uploads/2023/greenspot.jpg', 'Barcelona', 0, 41.38191030, 2.18368140, '0', '', 13, 'PENDIENTE', NULL, 0, NULL),
(6, '2025-10-31 06:07:18', 'Teresa Carles', 'Carrer de Jovellanos 2, 08001 Barcelona', '', '', 'Restaurante vegetariano con opciones veganas y sin gluten.', 'https://www.teresacarles.com', 'https://www.teresacarles.com/images/ensalada.jpg', 'Barcelona', 0, 41.38508460, 2.16800740, '0', '', 13, 'PENDIENTE', NULL, 0, NULL),
(7, '2025-10-31 06:07:18', 'Flax & Kale', 'Carrer dels Tallers 74B, 08001 Barcelona', '', '', 'Healthy flexitarian food con muchas opciones veganas.', 'https://flaxandkale.com', 'https://flaxandkale.com/images/bowls.jpg', 'Barcelona', 0, 41.38513210, 2.16547410, '0', '', 13, 'PENDIENTE', NULL, 0, NULL),
(8, '2025-10-31 06:07:18', 'El Vergel', 'Calle Mayor 10, 50001 Zaragoza', '', '', 'Restaurante vegano y ecológico con menú diario.', 'https://elvergelrestaurante.com', 'https://elvergelrestaurante.com/wp-content/uploads/2023/paella.jpg', 'Zaragoza', 0, 41.65380150, -0.87690290, '0', '', 13, 'PENDIENTE', NULL, 0, NULL),
(9, '2025-10-31 06:07:18', 'La Tía Carlota', 'Calle Pintor López Mezquita 9, 18002 Granada', '', '', 'Cocina vegana tradicional con productos locales.', 'https://latiacarlota.com', 'https://latiacarlota.com/wp-content/uploads/2023/tapas.jpg', 'Granada', 0, 37.17633610, -3.60779640, '0', '', 13, 'PENDIENTE', NULL, 0, NULL),
(10, '2025-10-31 06:07:18', 'El Berenjenal', 'Calle Rosario 15, 41001 Sevilla', '', '', 'Restaurante vegano con platos creativos y sin gluten.', 'https://elberenjenal.com', 'https://elberenjenal.com/images/berenjena.jpg', 'Sevilla', 0, 37.38994540, -5.99622350, '0', '', 13, 'PENDIENTE', NULL, 0, NULL),
(11, '2025-10-31 06:07:18', 'Habanera Vegan', 'Calle de la Palma 63, 28015 Madrid', '', '', 'Cocina vegana con sabores latinos y caribeños.', 'https://habaneravegan.com', 'https://habaneravegan.com/wp-content/uploads/2023/tacos.jpg', 'Madrid', 0, 40.42668480, -3.70787050, '0', '', 13, 'PENDIENTE', NULL, 0, NULL),
(12, '2025-10-31 06:07:18', 'La Oveja Negra Vegana', 'Calle Buenavista 42, 28012 Madrid', '', '', 'Comida vegana urbana y ambiente alternativo.', 'https://ovejanegrarestaurante.com', 'https://ovejanegrarestaurante.com/img/ramen.jpg', 'Madrid', 0, 40.40931330, -3.69987100, '0', '', 13, 'PENDIENTE', NULL, 0, NULL),
(13, '2025-10-31 06:07:18', 'Viva Burger', 'Costanilla de San Andrés 16, 28005 Madrid', '', '', 'Hamburguesas veganas gourmet con vistas a La Latina.', 'https://vivaburger.es', 'https://vivaburger.es/wp-content/uploads/2023/burger-vegan.jpg', 'Madrid', 0, 40.41322140, -3.71166980, '0', '', 13, 'PENDIENTE', NULL, 0, NULL),
(14, '2025-10-31 06:07:18', 'La Vegana Vallekas', 'Calle Sierra Carbonera 32, 28053 Madrid', '', '', 'Cocina vegana casera y sin gluten en Vallecas.', 'https://laveganavallekas.com', 'https://laveganavallekas.com/images/croquetas.jpg', 'Madrid', 0, 40.39187980, -3.66436570, '0', '', 13, 'PENDIENTE', NULL, 0, NULL),
(15, '2025-10-31 06:07:18', 'Veganoteca', 'Calle Poeta Quintana 20, 03004 Alicante', '', '', 'Restaurante y tienda vegana con productos ecológicos.', 'https://veganoteca.es', 'https://veganoteca.es/img/ensalada.jpg', 'Alicante', 0, 38.34786540, -0.48895820, '0', '', 13, 'PENDIENTE', NULL, 0, NULL),
(16, '2025-10-31 06:07:18', 'La Mandrágora', 'Calle San Vicente 45, Valencia', '', '', 'Restaurante vegano con opciones sin gluten y sin azúcar.', 'https://lamandragoravalencia.com', 'https://lamandragoravalencia.com/wp-content/uploads/2023/crepes.jpg', 'Valencia', 0, 39.98549000, -0.04179500, '0', '', 13, 'PENDIENTE', NULL, 0, NULL),
(17, '2025-10-31 06:07:18', 'La Vegana de la Cerveza', 'Calle San Andrés 38, 15003 A Coruña', '', '', 'Tapas veganas y cervezas artesanas.', 'https://laveganadelacerveza.com', 'https://laveganadelacerveza.com/img/tapas.jpg', 'A Coruña', 0, 43.37045010, -8.40239690, '0', '', 13, 'PENDIENTE', NULL, 0, NULL),
(18, '2025-10-31 06:07:18', 'El Fogón Verde', 'Calle Alameda 4, 28014 Madrid', '', '', 'Cocina vegana ecológica con productos de proximidad.', 'https://elfogonverde.es', 'https://elfogonverde.es/wp-content/uploads/2023/ensalada.jpg', 'Madrid', 0, 40.41236450, -3.69397490, '0', '', 13, 'PENDIENTE', NULL, 0, NULL),
(19, '2025-10-31 06:07:18', 'Choose Ristorante Naturale', 'Calle de la Luna 3, 28004 Madrid', '', '', 'Restaurante italiano 100% vegano con platos sicilianos.', 'https://chooseristorante.com', 'https://chooseristorante.com/wp-content/uploads/2023/pasta.jpg', 'Madrid', 0, 40.42214300, -3.70582880, '0', '', 13, 'PENDIENTE', NULL, 0, NULL),
(20, '2025-10-31 06:07:18', 'Mahalo Poké', 'Calle Ancha 10, 11001 Cádiz', '', '', 'Poké bowls veganos con ingredientes frescos.', 'https://mahalopoke.com', 'https://mahalopoke.com/images/bowl.jpg', 'Cádiz', 0, 36.77862060, -6.35372030, '0', '', 13, 'PENDIENTE', NULL, 0, NULL),
(21, '2025-10-31 06:07:18', 'ECOrigen', 'Calle Ayala 27, Planta 2, 28001 Madrid', '', '', 'Restaurante ecológico y vegano con cocina de autor.', 'https://ecorigen.com', 'https://ecorigen.com/wp-content/uploads/2023/plato.jpg', 'Madrid', 0, 40.42783340, -3.68460480, '0', '', 13, 'PENDIENTE', NULL, 0, NULL),
(22, '2025-11-17 17:02:43', 'Pura Vida Bio', 'Avenida de la Gran Vía, 5, 48001 Bilbao', '', '', 'Cocina saludable, flexitariana con muchas opciones sin gluten.', 'https://puravidabio.es', 'img/puravida_bilbao.jpg', 'Bilbao', 0, 43.26270000, -2.92530000, '944123456', 'contacto@puravidabio.es', 13, 'PENDIENTE', NULL, 0, NULL),
(23, '2025-11-17 17:02:43', 'The Juice House', 'Carrer del Parlament, 8, 08015 Barcelona', '', '', 'Brunch, zumos naturales y bowls saludables con opciones GF.', 'https://thejuicehouse.es', 'img/juicehouse_bcn.jpg', 'Barcelona', 0, 41.37556140, 2.16153410, '931234567', 'info@thejuicehouse.es', 13, 'PENDIENTE', NULL, 0, NULL),
(24, '2025-11-17 17:02:43', 'Restaurante Pleno', 'Calle Jardines, 10, 48005 Bilbao', '', '', 'Restaurante ecológico con enfoque en la alimentación consciente.', 'https://plenobilbao.com', 'img/plenobilbao.jpg', 'Bilbao', 0, 43.25828860, -2.92472310, '944654321', 'contacto@plenobilbao.com', 13, 'PENDIENTE', NULL, 0, NULL),
(25, '2025-11-17 17:02:43', 'Gilda Tapas Veganas', 'Calle de San Vicente Ferrer, 28, 28004 Madrid', '', '', 'Tapas veganas y creativas en el corazón de Malasaña.', 'https://gildatapas.es', 'img/gilda_tapas.jpg', 'Madrid', 0, 40.42578570, -3.70369210, '915432109', 'reservas@gildatapas.es', 13, 'PENDIENTE', NULL, 0, NULL),
(26, '2025-11-17 17:02:43', 'AlmaZEN', 'Calle Fuenterrabía, 2, 20005 San Sebastián', '', '', 'Cocina vegetariana y macrobiótica, muchas opciones aptas para celíacos.', 'https://restaurantealmazen.com', 'img/almazen_ss.jpg', 'San Sebastián', 0, 43.31913640, -1.98249320, '943123456', 'contacto@almazen.com', 13, 'PENDIENTE', NULL, 0, NULL),
(27, '2025-11-17 17:02:43', 'El Jardín de las Delicias', 'Plaza de la Merced, 1, 29012 Málaga', '', '', 'Comida natural y vegetariana con terraza, ambiente tranquilo.', 'https://eljardindelicias.es', 'img/jardin_malaga.jpg', 'Málaga', 0, 36.72284590, -4.41766250, '952123456', 'info@jardindelicias.es', 13, 'PENDIENTE', NULL, 0, NULL),
(28, '2025-11-17 17:02:43', 'Gofre Canario Sin Gluten', 'C/ Triana 45, 35002 Las Palmas', '', '', 'Especialidad en gofres y crepes 100% sin gluten.', 'https://gofrecanario.com', 'img/gofre_canario.jpg', 'Las Palmas', 0, 28.10490780, -15.41596950, '928765432', 'info@gofrecanario.com', 13, 'PENDIENTE', NULL, 0, NULL),
(29, '2025-11-17 17:02:43', 'Bio Tapería', 'Calle Viana, 12, 38201 San Cristóbal de La Laguna', '', '', 'Tapas ecológicas y cervezas artesanales sin gluten.', 'https://biotaperia.com', 'img/bio_taperia.jpg', 'Tenerife', 0, 28.49242150, -16.31313910, '922112233', 'contacto@biotaperia.com', 13, 'PENDIENTE', NULL, 0, NULL),
(30, '2025-11-17 17:02:43', 'Vegetalia', 'Carrer dels Escudellers, 54, 08002 Barcelona', '', '', 'Gran variedad de platos veganos e internacionales.', 'https://vegetalia.es', 'img/vegetalia_bcn.jpg', 'Barcelona', 0, 41.38039730, 2.17769520, '934567890', 'info@vegetalia.es', 13, 'PENDIENTE', NULL, 0, NULL),
(31, '2025-11-17 17:02:43', 'Rayen', 'Calle de Lope de Vega, 8, 28014 Madrid', '', '', 'Cocina de mercado saludable, con foco en el producto local.', 'https://rayenmadrid.com', 'img/rayen_madrid.jpg', 'Madrid', 0, 40.41376580, -3.69837330, '917654321', 'reservas@rayenmadrid.com', 13, 'PENDIENTE', NULL, 0, NULL),
(32, '2025-11-17 17:02:43', 'La Huerta de Murcia', 'Plaza de las Flores, 5, 30001 Murcia', '', '', 'Platos tradicionales murcianos adaptados a dieta vegana/sin gluten.', 'https://lahuerta.es', 'img/huerta_murcia.jpg', 'Murcia', 0, 37.98464340, -1.13313020, '968112233', 'info@lahuerta.es', 13, 'PENDIENTE', NULL, 0, NULL),
(33, '2025-11-17 17:02:43', 'Eco-Gourmet', 'Avenida de Menéndez Pelayo, 18, 41004 Sevilla', '', '', 'Comida gourmet con ingredientes ecológicos y de comercio justo.', 'https://ecogourmet.es', 'img/ecogourmet_sevilla.jpg', 'Sevilla', 0, 37.38708010, -5.98528960, '954234567', 'contacto@ecogourmet.es', 13, 'PENDIENTE', NULL, 0, NULL),
(34, '2025-11-17 17:02:43', 'El Arbol de la Vida', 'Rúa de San Pedro, 30, 15703 Santiago de Compostela', '', '', 'Especialistas en cocina natural y opciones para intolerancias.', 'https://elarboldelavida.com', 'img/arbol_vida_santiago.jpg', 'Santiago de Compostela', 0, 42.88137550, -8.53889800, '981765432', 'info@elarboldelavida.com', 13, 'PENDIENTE', NULL, 0, NULL),
(35, '2025-11-17 17:02:43', 'Mandala', 'Calle San Agustín, 15, 30005 Oviedo', '', '', 'Cocina vegana con influencias asiáticas y mediterráneas.', 'https://mandalaoviedo.com', 'img/mandala_oviedo.jpg', 'Oviedo', 0, 43.36029000, -5.84476000, '985123456', 'reservas@mandalaoviedo.com', 13, 'PENDIENTE', NULL, 0, NULL),
(36, '2025-11-17 17:02:43', 'Go! Sushing', 'Calle Colón, 10, 46004 Valencia', '', '', 'Sushi con muchas opciones veganas y arroces sin gluten.', 'https://gosushing.com', 'img/gosushing_vlc.jpg', 'Valencia', 0, 39.46739330, -0.37403430, '963123456', 'contacto@gosushing.com', 13, 'PENDIENTE', NULL, 0, NULL),
(37, '2025-11-17 17:02:43', 'El Huerto de Juan Fernandez', 'Calle de San Francisco, 25, 09003 Burgos', '', '', 'Restaurante con productos de su propia huerta y menú celiaco.', 'https://elhuertojf.com', 'img/huerto_burgos.jpg', 'Burgos', 0, 42.34691790, -3.70148970, '947123456', 'info@elhuertojf.com', 13, 'PENDIENTE', NULL, 0, NULL),
(38, '2025-11-17 17:02:43', 'Green Life', 'Calle San Miguel, 1, 07001 Palma de Mallorca', '', '', 'Cafetería y restaurante vegano y sin gluten en el centro.', 'https://greenlifemallorca.com', 'img/greenlife_mallorca.jpg', 'Palma de Mallorca', 0, 39.57171290, 2.65186600, '971123456', 'contacto@greenlife.com', 13, 'PENDIENTE', NULL, 0, NULL),
(39, '2025-11-17 17:02:43', 'La Vida es Bella', 'Calle de las Damas, 3, 05001 Ávila', '', '', 'Comida casera con ingredientes frescos y menú especial.', 'https://lavidaesbella.es', 'img/vidaesbella_avila.jpg', 'Ávila', 0, 40.65054320, -4.69805920, '920123456', 'info@lavidaesbella.es', 13, 'PENDIENTE', NULL, 0, NULL),
(40, '2025-11-17 17:02:43', 'El Colmado de Vegueta', 'Calle Mendizábal 35, 35001 Las Palmas', '', '', 'Tienda y cafetería con productos canarios sin gluten.', 'https://elcolmadovegueta.com', 'img/colmado_vegueta.jpg', 'Las Palmas', 0, 28.10138950, -15.41266020, '928123456', 'tienda@elcolmadovegueta.com', 13, 'PENDIENTE', NULL, 0, NULL),
(41, '2025-11-17 17:02:43', 'Restaurante Vegano Tenerife', 'Av. de Colón, 5, 38660 Tenerife', '', '', 'Alta cocina vegana con vistas al mar.', 'https://veganotenerife.com', 'img/vegano_tenerife.jpg', 'Tenerife', 0, 28.07814910, -16.73446820, '922987654', 'reservas@veganotenerife.com', 13, 'PENDIENTE', NULL, 0, NULL),
(42, '2025-11-21 04:56:33', 'Celicioso', 'Calle de Barquillo, 19, 28004 Madrid', '', '', 'Pastelería y cafetería 100% sin gluten con opciones dulces y saladas.', 'https://celicioso.es', 'img/celicioso_madrid.jpg', 'Madrid', 0, 40.41650000, -3.70260000, '915321689', 'info@celicioso.es', 13, 'PENDIENTE', NULL, 0, NULL),
(43, '2025-11-21 04:56:33', 'Flax & Kale Passage', 'Passatge de les Manufactures, 4, 08003 Barcelona', '', '', 'Restaurante flexitariano, healthy, con extensa carta sin gluten y opciones veganas.', 'https://flaxandkale.com', 'img/flaxandkale_bcn.jpg', 'Barcelona', 0, 41.38839820, 2.17658640, '933187215', 'info@flaxandkale.com', 13, 'PENDIENTE', NULL, 0, NULL),
(44, '2025-11-21 04:56:33', 'El Miracle', 'Carrer de Campoamor, 42, 46022 Valencia', '', '', 'Cocina mediterránea con toques sirios y egipcios, 100% sin gluten (certificado ACECOVA).', 'https://miraclerestaurant.es', 'img/elmiracle_vlc.jpg', 'Valencia', 0, 39.47095390, -0.34642530, '963818769', 'info@elmiraclerestaurant.com', 13, 'PENDIENTE', NULL, 0, NULL),
(45, '2025-11-21 04:56:33', 'Grosso Napoletano (SG)', 'C/ Hermosilla, 85, 28001 Madrid', '', '', 'Pizzería napoletana con opciones Sin Gluten (SG) que cuidan la no contaminación cruzada.', 'https://grossonapoletano.com', 'img/grosso_sg_mad.jpg', 'Madrid', 0, 40.42605500, -3.67690330, '910291931', 'info@grossonapoletano.com', 13, 'PENDIENTE', NULL, 0, NULL),
(46, '2025-11-21 04:56:33', 'Okashi Sanda', 'Calle de San Vicente Ferrer, 22, 28004 Madrid', '', '', 'Japonés 100% sin gluten y sin lactosa. Ofrecen gyozas, ramen y mochis.', 'https://okashisanda.com', 'img/okashi_sanda.jpg', 'Madrid', 0, 40.42577570, -3.70399260, '913654402', 'okashisanda@gmail.com', 13, 'PENDIENTE', NULL, 0, NULL),
(47, '2025-11-21 04:56:33', 'En Ville Restaurante', 'Carrer del Doctor Dou, 14, 08001 Barcelona', '', '', 'Cocina catalana con menú diario y carta 100% sin gluten en el corazón de Barcelona.', 'https://www.envillebarcelona.es', 'img/enville_bcn.jpg', 'Barcelona', 0, 41.38292760, 2.16855530, '933017253', 'info@envillebarcelona.es', 13, 'PENDIENTE', NULL, 0, NULL),
(48, '2025-11-21 04:56:33', 'La Nona Carmela', 'Callejón de Antonino, 2, 18001 Granada', '', '', 'Restaurante con amplias opciones sin gluten, reconocido en la zona.', 'https://lanonacarmela.es', 'img/lanona_granada.jpg', 'Granada', 0, 37.18820000, -3.60670000, '958223344', 'reservas@lanonacarmela.es', 13, 'PENDIENTE', NULL, 0, NULL),
(49, '2025-11-21 04:56:33', 'Naked and Sated', 'Calle de Serrano, 110, 28006 Madrid', '', '', 'Comida saludable sin gluten, sin azúcares añadidos ni harinas refinadas (varias ubicaciones).', 'https://nakedandsated.com', 'img/naked_sated_mad.jpg', 'Madrid', 0, 40.43524260, -3.68631630, '914357722', 'contacto@nakedandsated.com', 13, 'PENDIENTE', NULL, 0, NULL),
(50, '2025-11-21 04:56:33', 'As de Bastos', 'Calle Castilla, 62, 28039 Madrid', '', '', 'Cocina tradicional madrileña 100% sin gluten, institución para celíacos.', 'https://asdebastos.es', 'img/asdebastos_mad.jpg', 'Madrid', 0, 40.45318260, -3.70791610, '915354415', 'reservas@asdebastos.es', 13, 'PENDIENTE', NULL, 0, NULL),
(51, '2025-11-21 04:59:21', 'Freiduría Poseidón', 'Calle Almirante Lobo, 2, 41001 Sevilla', '', '', 'Pescaito frito y frituras 100% sin gluten en el centro de Sevilla.', 'https://freiduriaposeidon.es', 'img/poseidon_sevilla.jpg', 'Sevilla', 0, 37.38178030, -5.99508750, '955123456', 'contacto@freiduriaposeidon.es', 13, 'PENDIENTE', NULL, 0, NULL),
(52, '2025-11-21 04:59:21', 'Taquería Mano de Santo', 'Alameda de Hércules, 90, 41002 Sevilla', '', '', 'Auténtica taquería mexicana con amplias opciones sin gluten en su carta.', 'https://manodesanto.es', 'img/manodesanto_sev.jpg', 'Sevilla', 0, 37.39751590, -5.99347150, '954121212', 'reservas@manodesanto.es', 13, 'PENDIENTE', NULL, 0, NULL),
(53, '2025-11-21 04:59:21', 'Postigo 10', 'Calle Almirantazgo, 10, 41001 Sevilla', '', '', 'Elegante restaurante con cocina de mercado y un enfoque especial en la dieta sin gluten.', 'https://postigo10.es', 'img/postigo10_sev.jpg', 'Sevilla', 0, 37.38530650, -5.99463480, '954567890', 'info@postigo10.es', 13, 'PENDIENTE', NULL, 0, NULL),
(54, '2025-11-21 04:59:21', 'La Nona Carmela', 'Callejón de Antonino, 2, 18001 Granada', '', '', 'Restaurante con comida italiana y mediterránea con muchas opciones aptas para celíacos.', 'https://lanonacarmela.es', 'img/nonacarmela_gra.jpg', 'Granada', 0, 37.18820000, -3.60670000, '958112233', 'info@lanonacarmela.es', 13, 'PENDIENTE', NULL, 0, NULL),
(55, '2025-11-21 04:59:21', 'El Pescaíto de Carmela', 'Calle Marqués de Larios, 5, 18001 Granada', '', '', 'Pescado y marisco con opciones sin gluten, incluyendo frituras seguras.', 'https://elpescaitodecarme.es', 'img/pescaito_gra.jpg', 'Granada', 0, 37.18820000, -3.60670000, '958223344', 'contacto@elpescaito.es', 13, 'PENDIENTE', NULL, 0, NULL),
(56, '2025-11-21 04:59:21', 'El Rincón de Sas', 'Calle de la Cuchillería, 15, 22002 Huesca', '', '', 'Cocina tradicional aragonesa y de mercado con una carta amplia para celíacos.', 'https://elrincondesas.com', 'img/rincondesas_huesca.jpg', 'Huesca', 0, 42.13620000, -0.40870000, '974123456', 'info@elrincondesas.com', 13, 'PENDIENTE', NULL, 0, NULL),
(57, '2025-11-21 04:59:21', 'El Fartuquín', 'Calle Carpio, 19, 33008 Oviedo', '', '', 'Cocina asturiana tradicional 100% sin gluten. Muy valorado por la comunidad celíaca.', 'https://elfartuquin.com', 'img/fartuquin_oviedo.jpg', 'Oviedo', 0, 43.35976440, -5.84364130, '985223344', 'reservas@elfartuquin.com', 13, 'PENDIENTE', NULL, 0, NULL),
(58, '2025-11-21 04:59:21', 'Meraki', 'Plaza del Paraguas, 5, 33009 Oviedo', '', '', 'Restaurante de cocina fusión con una carta extensa y adaptada sin gluten.', 'https://merakioviedo.es', 'img/meraki_oviedo.jpg', 'Oviedo', 0, 43.36075150, -5.84278510, '985112233', 'info@merakioviedo.es', 13, 'PENDIENTE', NULL, 0, NULL),
(59, '2025-11-21 04:59:21', 'El Palace', 'Calle Palacio Valdés, 2, 33202 Gijón', '', '', 'Elegante restaurante con cocina de autor y menú degustación apto para celíacos.', 'https://elpalacegijon.com', 'img/elpalace_gijon.jpg', 'Gijón', 0, 43.53851900, -5.66593100, '985334455', 'reservas@elpalace.com', 13, 'PENDIENTE', NULL, 0, NULL),
(60, '2025-11-21 04:59:21', 'Restaurante Verdelima', 'Calle San Agustín, 3, 24001 León', '', '', 'Cocina creativa y de temporada con opciones sin gluten y vegetarianas.', 'https://verdelimaleon.es', 'img/verdelima_leon.jpg', 'León', 0, 42.66504560, -5.55382070, '987123456', 'contacto@verdelimaleon.es', 13, 'PENDIENTE', NULL, 0, NULL),
(61, '2025-11-21 04:59:21', 'Lalola Restaurante', 'Carrer del Cotoner, 14, 46001 Valencia', '', '', 'Cocina mediterránea tradicional con opciones sin gluten certificadas por ACECOVA.', 'https://lalolarestaurante.com', 'img/lalola_vlc.jpg', 'Valencia', 0, 39.46980000, -0.37740000, '963567890', 'reservas@lalolarestaurante.com', 13, 'PENDIENTE', NULL, 0, NULL),
(62, '2025-11-21 04:59:21', 'Mey Chen', 'Carrer dAlicante, 10, 46001 Valencia', '', '', 'Restaurante chino especializado en cocina 100% sin gluten.', 'https://meychen.es', 'img/meychen_vlc.jpg', 'Valencia', 0, 39.46980000, -0.37740000, '963789012', 'info@meychen.es', 13, 'PENDIENTE', NULL, 0, NULL),
(63, '2025-11-21 04:59:21', 'Carlotes Café', 'Carrer de Literat Azorín, 10, 46006 Valencia', '', '', 'Cafetería y pastelería 100% libre de gluten con opciones saladas y dulces.', 'https://carlotescafe.es', 'img/carlotes_vlc.jpg', 'Valencia', 0, 39.58830280, -0.30523790, '963112233', 'hola@carlotescafe.es', 13, 'PENDIENTE', NULL, 0, NULL),
(64, '2025-11-21 04:59:21', 'Kuki Cookie Bakery', 'C/ Colón de Larreátegui, 34, 48001 Bilbao', '', '', 'Obrador y cafetería 100% sin gluten con cookies y repostería americana.', 'https://kukicookiebakery.com', 'img/kuki_bilbao.jpg', 'Bilbao', 0, 43.26400600, -2.93410700, '944112233', 'info@kukicookiebakery.com', 13, 'PENDIENTE', NULL, 0, NULL),
(65, '2025-11-21 04:59:21', 'BAUSK', 'García Rivero Maisuaren Kalea, 8, 48011 Bilbao', '', '', 'Restaurante de cocina internacional donde toda la carta es sin gluten.', 'https://bausk.es', 'img/bausk_bilbao.jpg', 'Bilbao', 0, 43.26207850, -2.93919800, '944556677', 'contacto@bausk.es', 13, 'PENDIENTE', NULL, 0, NULL),
(66, '2025-11-21 04:59:21', 'Urregin', 'K. Barria, 4, 48005 Bilbao', '', '', 'Cocina vasca tradicional 100% sin gluten en el Casco Viejo.', 'https://restauranteurregin.com', 'img/urregin_bilbao.jpg', 'Bilbao', 0, 43.27441960, -2.95145770, '944667788', 'info@urregin.com', 13, 'PENDIENTE', NULL, 0, NULL),
(67, '2025-11-21 04:59:21', 'Avocado Mallorca', 'Avinguda de Joan Miró, 279, 07015 Palma de Mallorca', '', '', 'Restaurante 100% sin gluten, ecológico y saludable. Especializado en brunch y bowls.', 'https://avocadomallorca.com', 'img/avocado_mca.jpg', 'Palma de Mallorca', 0, 39.55368790, 2.60556360, '871123456', 'info@avocadomallorca.com', 13, 'PENDIENTE', NULL, 0, NULL),
(68, '2025-11-21 04:59:21', 'Infineathai', 'Carrer d\'Alfons el Magnanim, 65, 07004 Palma de Mallorca', '', '', 'Restaurante tailandés con excelente adaptación y amplias opciones sin gluten.', 'https://infineathai.es', 'img/infineathai_mca.jpg', 'Palma de Mallorca', 0, 39.59146800, 2.65327270, '971234567', 'reservas@infineathai.es', 13, 'PENDIENTE', NULL, 0, NULL),
(69, '2025-11-21 04:59:21', 'Izakaya Mallorca', 'Carrer de la Volta de la Mercè, 2, 07002 Palma de Mallorca', '', '', 'Restaurante japonés con opciones sin gluten, incluyendo sushi y ramen.', 'https://izakayamallorca.com', 'img/izakaya_mca.jpg', 'Palma de Mallorca', 0, 39.56940000, 2.65020000, '971345678', 'contacto@izakayamallorca.com', 13, 'PENDIENTE', NULL, 0, NULL),
(70, '2025-11-21 04:59:21', 'Restaurante Gran Tarajal', 'Avenida Marítima, 10, 38390 Puerto de la Cruz, Tenerife', '', '', 'Restaurante 100% sin gluten en las Islas Canarias.', 'https://grantarajal.com', 'img/grantarajal_tf.jpg', 'Tenerife', 0, 28.23470570, -16.84143030, '922123456', 'reservas@grantarajal.com', 13, 'PENDIENTE', NULL, 0, NULL),
(71, '2025-11-21 04:59:21', 'Casa Paché', 'Calle El Sol, 3, 38400 Puerto de la Cruz, Tenerife', '', '', 'Cocina canaria con opciones seguras sin gluten. Ambiente tradicional.', 'https://casapache.es', 'img/casapache_tf.jpg', 'Tenerife', 0, 28.41400000, -16.54870000, '922234567', 'info@casapache.es', 13, 'PENDIENTE', NULL, 0, NULL),
(72, '2025-11-21 04:59:21', 'Minelis Safe Food', 'Calle Iriarte, 2, 38430 Icod de los Vinos, Tenerife', '', '', 'Cafetería y repostería 100% segura y sin gluten.', 'https://minelissafefood.com', 'img/minelis_tf.jpg', 'Tenerife', 0, 28.37100000, -16.72150000, '922345678', 'contacto@minelis.com', 13, 'PENDIENTE', NULL, 0, NULL),
(73, '2025-11-21 04:59:21', 'Pizz-End-Gluten', 'Calle de los Mártires Concepcionistas, 5, 28006 Madrid', '', '', 'Pizzería y restaurante 100% sin gluten, también con opciones veganas.', 'https://pizzendgluten.com', 'img/pizzend_mad.jpg', 'Madrid', 0, 40.42770890, -3.67076080, '910123456', 'info@pizzendgluten.com', 13, 'PENDIENTE', NULL, 0, NULL),
(74, '2025-11-21 04:59:21', 'Casa San Juan', 'Calle de las Huertas, 10, 28014 Madrid', '', '', 'Comida mediterránea 100% sin gluten en el barrio de Huertas.', 'https://casasanjuan.es', 'img/casasanjuan_mad.jpg', 'Madrid', 0, 40.41390170, -3.70080980, '917890123', 'reservas@casasanjuan.es', 13, 'PENDIENTE', NULL, 0, NULL),
(75, '2025-11-21 04:59:21', 'Artemisa Huertas', 'Calle de Ventura de la Vega, 4, 28014 Madrid', '', '', 'Restaurante vegetariano 100% sin gluten con menú del día.', 'https://restauranteartemisa.com', 'img/artemisa_mad.jpg', 'Madrid', 0, 40.41600570, -3.69904920, '914295982', 'info@restauranteartemisa.com', 13, 'PENDIENTE', NULL, 0, NULL),
(76, '2025-11-21 04:59:21', 'Corporé Sano', 'Calle Amadeo I, 34, 12003 Castellón de la Plana', '', '', 'Restaurante 100% sin gluten con cocina creativa, fresca y sana.', 'https://corporesano.es', 'img/corporesano_castellon.jpg', 'Castellón', 0, 39.98655180, -0.04218180, '964112233', 'contacto@corporesano.es', 13, 'PENDIENTE', NULL, 0, NULL),
(77, '2025-11-21 04:59:21', 'Al Solito Posto', 'Alameda de Hércules, 16, 41002 Sevilla', '', '', 'Restaurante italiano con cocina separada para evitar la contaminación cruzada de platos sin gluten.', 'https://alsolitoposto.org', 'img/alsolitoposto_sev.jpg', 'Sevilla', 0, 37.39888370, -5.99373560, '954667788', 'info@alsolitoposto.es', 13, 'PENDIENTE', NULL, 0, NULL),
(78, '2025-11-21 04:59:21', 'Larruzz Bilbao', 'Calle Uribitarte, 24, 48001 Bilbao', '', '', 'Restaurante especializado en arroces, con una amplia oferta de paellas sin gluten.', 'https://larruzzbilbao.com', 'img/larruzz_bilbao.jpg', 'Bilbao', 0, 43.26643450, -2.92962320, '944778899', 'reservas@larruzzbilbao.com', 13, 'PENDIENTE', NULL, 0, NULL),
(79, '2025-11-21 04:59:21', 'Casa Leotta', 'C/ Juan de Ajuriaguerra, 14, 48009 Bilbao', '', '', 'Especialistas en Pinsa (pizza romana) con masa 100% sin gluten.', 'https://casaleotta.com', 'img/casaleotta_bilbao.jpg', 'Bilbao', 0, 43.26270000, -2.92530000, '944889900', 'hola@casaleotta.com', 13, 'PENDIENTE', NULL, 0, NULL),
(80, '2025-11-21 04:59:21', 'Il Capriccio', 'C. de Costa i Llobera, 24, 07181 Palmanova, Mallorca', '', '', 'Restaurante italiano con gran variedad de pasta y pizzas sin gluten.', 'https://ilcapricciooficial.com', 'img/ilcapriccio_mca.jpg', 'Palma de Mallorca', 0, 39.61362000, 3.02004000, '971998877', 'info@ilcapriccio.com', 13, 'PENDIENTE', NULL, 0, NULL),
(84, '2025-12-10 04:37:05', 'grema', 'calle francisco ribera 2', 'grema', 'restaurante con opciones sin gluten', 'calle francisco ribera 2', 'www.grema.es', '', 'leganes', 28913, 40.33632620, -3.77879290, NULL, 'gremaII@gmail.com', 36, 'PENDIENTE', NULL, 3, 'SIN_GLUTEN'),
(85, '2025-12-10 05:36:34', 'grema', 'calle francisco ribera, 3', 'grema', 'restaurante sin gluten', 'calle francisco ribera, 3', 'www.grema.es', '', 'LEGANÉS', 28913, 40.33632620, -3.77879290, NULL, 'grema@gmail.com', 36, 'PENDIENTE', NULL, 0, 'SIN_GLUTEN');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

CREATE TABLE `rol` (
  `id_rol` int NOT NULL,
  `nombre` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `rol`
--

INSERT INTO `rol` (`id_rol`, `nombre`) VALUES
(1, 'ADMINISTRADOR'),
(2, 'EDITOR'),
(3, 'VISITOR');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tag_post`
--

CREATE TABLE `tag_post` (
  `id_tag_post` int NOT NULL,
  `nombre` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `id_post` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tag_receta`
--

CREATE TABLE `tag_receta` (
  `id_tag` bigint NOT NULL,
  `id_receta` int NOT NULL,
  `nombre` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `tag_receta`
--

INSERT INTO `tag_receta` (`id_tag`, `id_receta`, `nombre`) VALUES
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
(84, 3, 'saludable'),
(85, 1, 'Cereales'),
(86, 2, 'Desayuno'),
(87, 3, 'Bajo en carbohidratos'),
(88, 4, 'Fitness'),
(89, 5, 'Merienda'),
(90, 6, 'Repostería'),
(91, 7, 'Chocolate'),
(92, 8, 'Amasado'),
(93, 9, 'Especias'),
(94, 10, 'Fruta'),
(95, 11, 'Latino'),
(96, 12, 'Fritura'),
(97, 13, 'Verano'),
(98, 14, 'Cítrico'),
(99, 15, 'Dulce'),
(100, 16, 'Merienda'),
(101, 17, 'Cena'),
(102, 18, 'Especias'),
(103, 19, 'Invierno'),
(104, 20, 'Olla'),
(105, 21, 'Canela'),
(106, 22, 'Integral'),
(107, 23, 'Horneado'),
(108, 24, 'Mexicano'),
(109, 25, 'Relleno'),
(110, 26, 'Vegano'),
(111, 27, 'Sin azúcar'),
(112, 28, 'Fibra'),
(113, 29, 'Salado'),
(114, 30, 'Horno'),
(115, 31, 'Clásico'),
(116, 32, 'Frío'),
(117, 33, 'Keto'),
(118, 34, 'Sin huevo'),
(119, 35, 'Jengibre'),
(120, 36, 'Cena rápida'),
(121, 37, 'Navidad'),
(122, 38, 'Gourmet'),
(123, 39, 'Otoño'),
(124, 40, 'Manzana caramelizada'),
(125, 2, 'Merienda'),
(126, 3, 'Fácil y rápido'),
(127, 4, 'Dieta'),
(128, 5, 'Sin lactosa'),
(129, 6, 'Receta básica'),
(130, 7, 'Sin lácteos'),
(131, 8, 'Sin levadura'),
(132, 9, 'Frutos secos'),
(133, 10, 'Aprovechamiento'),
(134, 11, 'Sin horno');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tag_restaurante`
--

CREATE TABLE `tag_restaurante` (
  `id_tag` bigint NOT NULL,
  `id_restaurante` int NOT NULL,
  `nombre` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL
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
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id_usuario` int NOT NULL,
  `nombre` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `id_rol` int NOT NULL,
  `fecha_alta` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='tabla de usuarios';

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id_usuario`, `nombre`, `email`, `password`, `id_rol`, `fecha_alta`) VALUES
(1, 'Laura Sánchez', 'laura.sanchez@example.com', 'pass123laura', 3, '2025-11-29 21:11:57'),
(3, 'Marta Gómez', 'marta.gomez@example.com', 'pass123marta', 3, '2025-11-29 21:11:57'),
(4, 'Javier Torres', 'javier.torres@example.com', 'pass123javi', 3, '2025-11-29 21:11:57'),
(5, 'Lucía Fernández', 'lucia.fernandez@example.com', 'pass123lucia', 3, '2025-11-29 21:11:57'),
(6, 'Andrés Pérez', 'andres.perez@example.com', 'pass123andres', 3, '2025-11-29 21:11:57'),
(7, 'Sofía Morales', 'sofia.morales@example.com', 'pass123sofia', 3, '2025-11-29 21:11:57'),
(8, 'Diego Navarro', 'diego.navarro@example.com', 'pass123diego', 3, '2025-11-29 21:11:57'),
(9, 'Elena Castro', 'elena.castro@example.com', 'pass123elena', 3, '2025-11-29 21:11:57'),
(10, 'Raúl Romero', 'raul.romero@example.com', 'pass123raul', 3, '2025-11-29 21:11:57'),
(11, 'Nuria Martínez Rodrigo', 'nuria.martinezro@gmail.com', 'XXXX=', 1, '2025-11-29 21:11:57'),
(13, 'Maite Rodríguez Jurado', 'rodriguez.maite@gmail.com', 'XXXX=', 1, '2025-11-29 21:11:57'),
(15, 'Antonio Ruiz', 'antonio.ruiz@ejemplo.com', 'pass123antonio', 3, '2025-11-29 21:11:57'),
(16, 'Carmen Gil', 'carmen.gil@ejemplo.com', 'pass123carmen', 3, '2025-11-29 21:11:57'),
(17, 'Pedro López', 'pedro.lopez@ejemplo.com', 'pass123pedro', 3, '2025-11-29 21:11:57'),
(18, 'Isabel Cano', 'isabel.cano@ejemplo.com', 'pass123isabel', 3, '2025-11-29 21:11:57'),
(19, 'Carlos Rey', 'carlos.rey@ejemplo.com', 'pass123carlos', 3, '2025-11-29 21:11:57'),
(20, 'Eva Sanz', 'eva.sanz@ejemplo.com', 'pass123eva', 3, '2025-11-29 21:11:57'),
(21, 'Marcos Vidal', 'marcos.vidal@ejemplo.com', 'pass123marcos', 3, '2025-11-29 21:11:57'),
(22, 'Ana Soto', 'ana.soto@ejemplo.com', 'pass123ana', 3, '2025-11-29 21:11:57'),
(23, 'Félix Rueda', 'felix.rueda@ejemplo.com', 'pass123felix', 3, '2025-11-29 21:11:57'),
(24, 'Gloria Vivas', 'gloria.vivas@ejemplo.com', 'pass123gloria', 3, '2025-11-29 21:11:57'),
(25, 'Susana Rico Vara', 'susanaricovara@gmail.com', '$2a$10$I7kike41n2YCGGJ6ywX3lOCYW5e1gusn6Lo/GIOtCQxWFI30WIw1K', 1, '2025-12-10 11:24:59'),
(29, 'prueba prueba prueba', 'prueba2@gmail.com', '$2a$10$D6ZJgiKfte7nHYWf0/PRYecb.XOYNLZvEa805004uAfH3xjNQaNNG', 3, '2025-11-29 22:19:01'),
(30, 'prueba prueba prueb2a', 'prueba22@gmail.com', '$2a$10$6DNKfebZ8bZn/zpoNwev5eP60TGu2B6BFqiJRBM0pxkhvxOoj29hG', 3, '2025-11-29 22:29:59'),
(31, 'PRUEBA4 PRUEBA4 PRUEBA4', 'PRUEBA4@GMAIL.COM', '$2a$10$jCiiWKt2qxkw29DS9PPEW.3p0ye2awI.5s/PmbJRuOg9ww8qzA4t.', 3, NULL),
(32, 'hola hola hola', 'hola@hola.com', '$2a$10$SP0Eg1fBzs6ScKsWQ5x1vu3JbkQcvqlaVRHCqGf4r76COpNJsVP1e', 3, NULL),
(33, 'Nuria', 'nuria@gmail.com', '$2a$10$8OsmB30OGOXG6MPJtKUvNuJIX582Oenuw3wR8cieRHnIoo/gdVXaC', 1, NULL),
(34, 'raul', 'raul@gmail.com', '$2a$10$DUVKZKfIBcbd7N9jxiOx3.j0XonBGcQp2EPuhdpGQg4O2gRGVZyg2', 3, NULL),
(35, 'luis', 'luis@gmail.com', '$2a$10$HOFIhAB2wh3Go/IfcYtEVO/EXCJbA3IMs77JoEY3ojgPTcTVqaVRW', 3, '2025-12-04 16:09:19'),
(36, 'vera', 'vera@gmail.com', '$2a$10$1VwQ7WOL9jusa2VvkeCkDuCVh/.rXzEpwbvVFOPbmXRI.GrBpTiBK', 2, '2025-12-07 06:57:02');

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `vista_recetas`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `vista_recetas` (
`cantidad` int
,`comensales` int
,`descripcion` mediumtext
,`dificultad` varchar(255)
,`estado` enum('PENDIENTE','APROBADO','RECHAZADO')
,`fecha_creacion` timestamp
,`fecha_validacion` timestamp
,`id` bigint unsigned
,`id_ingrediente` int
,`id_receta` int
,`id_usuario` int
,`imagen_url` varchar(255)
,`nombre` varchar(255)
,`subtitulo` varchar(500)
,`tiempo_preparacion` int
,`tipo_comida` enum('DESAYUNO','BRUNCH','ALMUERZO','MERIENDA','CENA','TAPAS','RACIONES','POSTRE','SALADO','DULCE')
,`titulo` varchar(255)
,`unidad` varchar(255)
,`valor_energetico` int
,`valoracion` int
);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `vista_receta_ingredientes`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `vista_receta_ingredientes` (
`cantidad` int
,`id_ingrediente` int
,`id_receta` int
,`id_receta_ingrediente` int
,`nombre` varchar(255)
,`unidad` varchar(255)
);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `comentario`
--
ALTER TABLE `comentario`
  ADD PRIMARY KEY (`id_comentario`),
  ADD KEY `id_usuario` (`id_usuario`);

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
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_usuario`),
  ADD UNIQUE KEY `uq_email_usuario` (`email`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `fk_usuario_id_rol` (`id_rol`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `comentario`
--
ALTER TABLE `comentario`
  MODIFY `id_comentario` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=92;

--
-- AUTO_INCREMENT de la tabla `favoritos`
--
ALTER TABLE `favoritos`
  MODIFY `id_favorito` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57;

--
-- AUTO_INCREMENT de la tabla `ingrediente`
--
ALTER TABLE `ingrediente`
  MODIFY `id_ingrediente` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=125;

--
-- AUTO_INCREMENT de la tabla `paso_receta`
--
ALTER TABLE `paso_receta`
  MODIFY `id_paso` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=139;

--
-- AUTO_INCREMENT de la tabla `post`
--
ALTER TABLE `post`
  MODIFY `id_post` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT de la tabla `receta`
--
ALTER TABLE `receta`
  MODIFY `id_receta` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=122;

--
-- AUTO_INCREMENT de la tabla `receta_ingrediente`
--
ALTER TABLE `receta_ingrediente`
  MODIFY `id_receta_ingrediente` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=301;

--
-- AUTO_INCREMENT de la tabla `restaurante`
--
ALTER TABLE `restaurante`
  MODIFY `id_restaurante` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=86;

--
-- AUTO_INCREMENT de la tabla `rol`
--
ALTER TABLE `rol`
  MODIFY `id_rol` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `tag_post`
--
ALTER TABLE `tag_post`
  MODIFY `id_tag_post` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tag_receta`
--
ALTER TABLE `tag_receta`
  MODIFY `id_tag` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=135;

--
-- AUTO_INCREMENT de la tabla `tag_restaurante`
--
ALTER TABLE `tag_restaurante`
  MODIFY `id_tag` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id_usuario` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

-- --------------------------------------------------------

--
-- Estructura para la vista `vista_recetas`
--
DROP TABLE IF EXISTS `vista_recetas`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `vista_recetas`  AS SELECT DISTINCT row_number() OVER (ORDER BY `r`.`id_receta` ) AS `id`, `r`.`id_receta` AS `id_receta`, `r`.`id_usuario` AS `id_usuario`, `r`.`fecha_creacion` AS `fecha_creacion`, `r`.`titulo` AS `titulo`, `r`.`subtitulo` AS `subtitulo`, `r`.`tipo_comida` AS `tipo_comida`, `r`.`descripcion` AS `descripcion`, `r`.`dificultad` AS `dificultad`, `r`.`valoracion` AS `valoracion`, `r`.`tiempo_preparacion` AS `tiempo_preparacion`, `r`.`valor_energetico` AS `valor_energetico`, `r`.`comensales` AS `comensales`, `r`.`imagen_url` AS `imagen_url`, `r`.`estado` AS `estado`, `r`.`fecha_validacion` AS `fecha_validacion`, `ri`.`cantidad` AS `cantidad`, `i`.`id_ingrediente` AS `id_ingrediente`, `i`.`nombre` AS `nombre`, `i`.`unidad` AS `unidad` FROM ((`receta` `r` left join `receta_ingrediente` `ri` on((`r`.`id_receta` = `ri`.`id_receta`))) join `ingrediente` `i` on((`ri`.`id_ingrediente` = `i`.`id_ingrediente`))) ;

-- --------------------------------------------------------

--
-- Estructura para la vista `vista_receta_ingredientes`
--
DROP TABLE IF EXISTS `vista_receta_ingredientes`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `vista_receta_ingredientes`  AS SELECT `ri`.`id_receta` AS `id_receta`, `ri`.`id_receta_ingrediente` AS `id_receta_ingrediente`, `ri`.`id_ingrediente` AS `id_ingrediente`, `i`.`nombre` AS `nombre`, `ri`.`cantidad` AS `cantidad`, `i`.`unidad` AS `unidad` FROM (`ingrediente` `i` join `receta_ingrediente` `ri` on((`ri`.`id_ingrediente` = `i`.`id_ingrediente`))) ORDER BY `ri`.`id_receta` ASC ;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `comentario`
--
ALTER TABLE `comentario`
  ADD CONSTRAINT `comentario_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE;

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
