-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 18-03-2024 a las 12:00:06
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `exameninterfaces`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumno`
--

CREATE TABLE `alumno` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellidos` varchar(50) NOT NULL,
  `ad` double NOT NULL,
  `di` double NOT NULL,
  `eie` double NOT NULL,
  `pmdm` double NOT NULL,
  `psp` double NOT NULL,
  `sge` double NOT NULL,
  `hlc` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `alumno`
--

INSERT INTO `alumno` (`id`, `nombre`, `apellidos`, `ad`, `di`, `eie`, `pmdm`, `psp`, `sge`, `hlc`) VALUES
(1, 'Juan', 'Palomo Cortes', 1.2, 9.1, 7.1, 8.6, 4.3, 2.9, 5.5),
(2, 'Paco', 'Garnido Carrasco', 8.5, 6.2, 6.1, 6.3, 6.3, 7.8, 1.2),
(3, 'Antonio', 'Morales', 8.2, 2.1, 3.5, 2.6, 4.3, 2.9, 5.5),
(4, 'Pablo', 'Postigo', 9.9, 9.9, 5.1, 5.1, 7.5, 7.1, 8.2),
(5, 'Carmen', 'Rosales', 2.2, 8.1, 3.5, 2.6, 7.3, 8.9, 9.3),
(6, 'Antonia', 'Fuentes', 9.2, 9.7, 9.1, 9.8, 8.6, 7.1, 8.2),
(7, 'Pedro', 'Sanchez', 8.5, 7.5, 7.3, 7.5, 9.5, 8.6, 5.8),
(8, 'Leonardo', 'Di Caprio', 4.2, 4.5, 2.2, 7.5, 7.5, 5.4, 1.4);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alumno`
--
ALTER TABLE `alumno`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `alumno`
--
ALTER TABLE `alumno`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
