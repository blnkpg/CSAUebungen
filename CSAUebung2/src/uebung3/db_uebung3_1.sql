-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Erstellungszeit: 10. Apr 2015 um 12:43
-- Server Version: 5.6.21
-- PHP-Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

-- 
-- Datenbank: `db_uebung3-1`
--
CREATE DATABASE IF NOT EXISTS `db_uebung3-1` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `db_uebung3-1`;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `futter`
--

CREATE TABLE IF NOT EXISTS `futter` (
`FID` int(10) NOT NULL,
  `name` varchar(30) NOT NULL,
  `color` varchar(6) NOT NULL,
  `nutrition` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `tier`
--

CREATE TABLE IF NOT EXISTS `tier` (
`TID` int(10) NOT NULL,
  `name` varchar(30) NOT NULL,
  `weight` double NOT NULL,
  `height` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `tierfutter`
--

CREATE TABLE IF NOT EXISTS `tierfutter` (
  `FID` int(10) NOT NULL,
  `TID` int(10) NOT NULL,
  `amount` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `futter`
--
ALTER TABLE `futter`
 ADD PRIMARY KEY (`FID`);

--
-- Indizes für die Tabelle `tier`
--
ALTER TABLE `tier`
 ADD PRIMARY KEY (`TID`);

--
-- Indizes für die Tabelle `tierfutter`
--
ALTER TABLE `tierfutter`
 ADD PRIMARY KEY (`FID`,`TID`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `futter`
--
ALTER TABLE `futter`
MODIFY `FID` int(10) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT für Tabelle `tier`
--
ALTER TABLE `tier`
MODIFY `TID` int(10) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
