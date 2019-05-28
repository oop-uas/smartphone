-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 28 Bulan Mei 2019 pada 20.08
-- Versi server: 10.1.36-MariaDB
-- Versi PHP: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `uas_oop`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `smartphone`
--

CREATE TABLE `smartphone` (
  `id_smartphone` int(11) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `brand` varchar(255) NOT NULL,
  `spesifikasi` text NOT NULL,
  `harga` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `smartphone`
--

INSERT INTO `smartphone` (`id_smartphone`, `nama`, `brand`, `spesifikasi`, `harga`) VALUES
(2, 'OPPO F7', 'OPPO', 'RAM 512 MB, INTERNAL 1MB', 3000000);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `smartphone`
--
ALTER TABLE `smartphone`
  ADD PRIMARY KEY (`id_smartphone`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `smartphone`
--
ALTER TABLE `smartphone`
  MODIFY `id_smartphone` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
