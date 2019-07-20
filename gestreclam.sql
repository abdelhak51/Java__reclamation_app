-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  lun. 21 jan. 2019 à 11:19
-- Version du serveur :  5.7.19
-- Version de PHP :  5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `gestreclam`
--

-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `nom` varchar(10) NOT NULL,
  `prenom` varchar(10) NOT NULL,
  `pseudo` varchar(10) NOT NULL,
  `mdp` varchar(8) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `pseudo` (`pseudo`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `admin`
--

INSERT INTO `admin` (`id`, `nom`, `prenom`, `pseudo`, `mdp`) VALUES
(1, 'n1', 'p1', 'ps1', 'azer1234');

-- --------------------------------------------------------

--
-- Structure de la table `enseignant`
--

DROP TABLE IF EXISTS `enseignant`;
CREATE TABLE IF NOT EXISTS `enseignant` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `nom` varchar(10) NOT NULL,
  `prenom` varchar(10) NOT NULL,
  `grad` varchar(5) NOT NULL,
  `mdp` varchar(8) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `mdp` (`mdp`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `enseignant`
--

INSERT INTO `enseignant` (`id`, `nom`, `prenom`, `grad`, `mdp`) VALUES
(6, 'Rahmani', 'Amin', 'MCA', 'AM12'),
(11, 'azer', 'aer', 'MCB', 'AZER123');

-- --------------------------------------------------------

--
-- Structure de la table `etudiant`
--

DROP TABLE IF EXISTS `etudiant`;
CREATE TABLE IF NOT EXISTS `etudiant` (
  `matricule` bigint(20) NOT NULL,
  `nom` varchar(10) NOT NULL,
  `prenom` varchar(20) NOT NULL,
  `specialite` varchar(10) NOT NULL,
  `annee` varchar(2) NOT NULL,
  `section` varchar(1) NOT NULL,
  `groupe` int(2) NOT NULL,
  `tel` bigint(10) DEFAULT NULL,
  `mdp` varchar(20) NOT NULL,
  PRIMARY KEY (`matricule`),
  UNIQUE KEY `matricule` (`matricule`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `etudiant`
--

INSERT INTO `etudiant` (`matricule`, `nom`, `prenom`, `specialite`, `annee`, `section`, `groupe`, `tel`, `mdp`) VALUES
(161631097475, 'Habbou', 'Ayoub', 'SI', 'L3', 'A', 3, 555553605, 'Mike0752');

-- --------------------------------------------------------

--
-- Structure de la table `reclamation`
--

DROP TABLE IF EXISTS `reclamation`;
CREATE TABLE IF NOT EXISTS `reclamation` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `mat` bigint(20) NOT NULL,
  `nomEns` varchar(20) NOT NULL,
  `preEns` varchar(20) NOT NULL,
  `module` varchar(40) NOT NULL,
  `note_aff` float NOT NULL,
  `note_rl` float NOT NULL,
  `nvl_note` float DEFAULT NULL,
  `rem_et` varchar(500) NOT NULL,
  `rem_en` varchar(500) DEFAULT NULL,
  `etat` varchar(3) DEFAULT NULL,
  `notif` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `reclamation`
--

INSERT INTO `reclamation` (`id`, `mat`, `nomEns`, `preEns`, `module`, `note_aff`, `note_rl`, `nvl_note`, `rem_et`, `rem_en`, `etat`, `notif`) VALUES
(3, 161631097475, 'BENGHALIA', 'Abd Raouf', 'GL', 10, 11, NULL, 'hagrona', NULL, 'non', 'non'),
(8, 161631097475, 'ADOUANE', 'Amine', 'SE', 11, 12.25, NULL, 'Hagroona', NULL, 'non', 'non');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
