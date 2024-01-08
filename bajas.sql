-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 28, 2022 at 03:11 AM
-- Server version: 10.4.20-MariaDB
-- PHP Version: 8.0.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bajas`
--

-- --------------------------------------------------------

--
-- Table structure for table `altas`
--

CREATE TABLE `altas` (
  `numEmpleado` int(5) NOT NULL,
  `nommbre` varchar(50) NOT NULL,
  `altaAnio` varchar(20) NOT NULL,
  `edad` varchar(3) NOT NULL,
  `fechaNacimiento` varchar(20) NOT NULL,
  `actaNacimiento` varchar(20) NOT NULL,
  `ine` varchar(20) NOT NULL,
  `recidencia` varchar(50) NOT NULL,
  `CURP` varchar(18) NOT NULL,
  `RFC` varchar(22) NOT NULL,
  `constanciaDomiciliaria` varchar(20) NOT NULL,
  `cartillaMilitar` varchar(20) NOT NULL,
  `gradoEstudios` varchar(20) NOT NULL,
  `licenciaturaOficio` varchar(20) NOT NULL,
  `tituloProfesional` varchar(20) NOT NULL,
  `cedulaProfesional` varchar(20) NOT NULL,
  `actualizacionesCertificacion` varchar(20) NOT NULL,
  `certificadoAntecedentesNoPenales` varchar(20) NOT NULL,
  `certificadoMedico` varchar(20) NOT NULL,
  `cartaRecomendacion` varchar(20) NOT NULL,
  `numIssemym` varchar(15) NOT NULL,
  `constanciaValidez` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `altas`
--

INSERT INTO `altas` (`numEmpleado`, `nommbre`, `altaAnio`, `edad`, `fechaNacimiento`, `actaNacimiento`, `ine`, `recidencia`, `CURP`, `RFC`, `constanciaDomiciliaria`, `cartillaMilitar`, `gradoEstudios`, `licenciaturaOficio`, `tituloProfesional`, `cedulaProfesional`, `actualizacionesCertificacion`, `certificadoAntecedentesNoPenales`, `certificadoMedico`, `cartaRecomendacion`, `numIssemym`, `constanciaValidez`) VALUES
(1, 'cesar izquiedo m', '2021-07-12', '21', '2008-07-10', 'Si', 'Si', 'texcalyacac', 'wkmnhbcxswhch', 'kcbcdsbkjbK N', 'Si', 'Si', ' CX XC ', 'xsaxasx', 'Si', 'Si', 'Si', 'Si', 'Si', 'Si', '3213213', 'Si'),
(3, 'roberto ndfncjidnc', '2022-01-01', '25', '2022-01-27', 'Si', 'Si', 'Texcayacac', 'fvfv', 'revev', 'Si', 'Si', 'vfvfvv', ' f f f ', 'Si', 'Si', 'Si', 'Si', 'Si', 'Si', '324433', 'Si');

-- --------------------------------------------------------

--
-- Table structure for table `isse`
--

CREATE TABLE `isse` (
  `numEmpleado` int(5) NOT NULL,
  `nommbre` varchar(50) NOT NULL,
  `altaDia` varchar(20) NOT NULL,
  `altaMes` varchar(20) NOT NULL,
  `altaAnio` varchar(20) NOT NULL,
  `eadPersona` varchar(3) NOT NULL,
  `edad` varchar(3) NOT NULL,
  `fechaBaja` varchar(20) NOT NULL,
  `fechaNacimiento` varchar(20) NOT NULL,
  `actaNacimiento` varchar(20) NOT NULL,
  `ine` varchar(20) NOT NULL,
  `recidencia` varchar(50) NOT NULL,
  `CURP` varchar(20) NOT NULL,
  `RFC` varchar(20) NOT NULL,
  `constanciaDomiciliaria` varchar(20) NOT NULL,
  `cartillaMilitar` varchar(20) NOT NULL,
  `gradoEstudios` varchar(20) NOT NULL,
  `licenciaturaOficio` varchar(20) NOT NULL,
  `tituloProfesional` varchar(20) NOT NULL,
  `cedulaProfesional` varchar(20) NOT NULL,
  `actualizacionesCertificacion` varchar(20) NOT NULL,
  `certificadoAntecedentesNoPenales` varchar(20) NOT NULL,
  `certificadoMedico` varchar(20) NOT NULL,
  `cartaRecomendacion` varchar(20) NOT NULL,
  `numIssemym` varchar(15) NOT NULL,
  `constanciaValidez` varchar(20) NOT NULL,
  `bajaIssemyn` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `isse`
--

INSERT INTO `isse` (`numEmpleado`, `nommbre`, `altaDia`, `altaMes`, `altaAnio`, `eadPersona`, `edad`, `fechaBaja`, `fechaNacimiento`, `actaNacimiento`, `ine`, `recidencia`, `CURP`, `RFC`, `constanciaDomiciliaria`, `cartillaMilitar`, `gradoEstudios`, `licenciaturaOficio`, `tituloProfesional`, `cedulaProfesional`, `actualizacionesCertificacion`, `certificadoAntecedentesNoPenales`, `certificadoMedico`, `cartaRecomendacion`, `numIssemym`, `constanciaValidez`, `bajaIssemyn`) VALUES
(1, 'royer', '0', '0', '2021-12-23', '0', '33', '2021-12-22', '2021-12-07', 'Si', 'Si', 'Texcalyacac', 'CCWCW', 'BBJHB', 'Si', 'Si', 'HCKCB', 'VFVVDF', 'Si', 'Si', 'Si', 'Si', 'Si', 'Si', '23313', 'Si', ''),
(2, 'cwq', '0', '0', '2021-12-15', '0', '21', '2021-12-31', '2021-12-22', 'Si', 'Si', 'dcdwc', 'hjbhjh', 'jhbbuhbub', 'Si', 'Si', 'bjhbjk', 'njknjk', 'Si', 'Si', 'Si', 'Si', 'Si', 'Si', '3123131', 'Si', '2021-12-31'),
(3, 'jor', '', '', '2020-08-06', '', '33', '2021-08-17', '1985-01-09', 'Si', 'Si', 'ccc', 'ffee', 'fddfcdc', 'Si', 'Si', 'vfddvv', 'fecefc', 'Si', 'Si', 'Si', 'Si', 'Si', 'Si', '34242', 'Si', '2022-01-06'),
(4, 'jose h m', '0', '0', '2021-12-09', '0', '33', '2022-03-26', '2022-01-04', 'Si', 'Si', 'fefe', 'ecece', 'vrvr', 'Si', 'Si', 'gvrgvr', 'ceccedce', 'Si', 'Si', 'Si', 'Si', 'Si', 'Si', '31313', 'Si', '2022-03-26'),
(5, 'ever samano', '0', '0', '2022-06-07', '0', '21', '2022-06-07', '2000-12-14', 'Si', 'Si', 'texca', 'sssqsqs', '2323sqsqs', 'Si', 'Si', 'sqwsqs', 'sqsqsq', 'Si', 'Si', 'Si', 'Si', 'Si', 'Si', '2313131', 'Si', '2022-06-08');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `altas`
--
ALTER TABLE `altas`
  ADD PRIMARY KEY (`numEmpleado`);

--
-- Indexes for table `isse`
--
ALTER TABLE `isse`
  ADD PRIMARY KEY (`numEmpleado`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
