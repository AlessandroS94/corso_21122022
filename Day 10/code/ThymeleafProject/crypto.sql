-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Creato il: Gen 19, 2023 alle 14:06
-- Versione del server: 5.7.34
-- Versione PHP: 7.4.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ForexWebSite`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `crypto`
--

CREATE TABLE `crypto` (
  `id` bigint(20) NOT NULL,
  `amount` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `crypto`
--

INSERT INTO `crypto` (`id`, `amount`, `description`, `name`, `url`, `category_id`) VALUES
(1, 1, 'Crypto currency', 'BTC', 'btc.com', 1),
(2, 10, 'Currency', 'XEM', 'xem.com', 1),
(3, 1000, 'Currency', 'XRP', 'xrp.org', 1),
(4, 10000, 'Currency', 'CELO', 'celo.org', 1);

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `crypto`
--
ALTER TABLE `crypto`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKcjro32rmdoqqt7rpcytakcd0g` (`category_id`);

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `crypto`
--
ALTER TABLE `crypto`
  ADD CONSTRAINT `FKcjro32rmdoqqt7rpcytakcd0g` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
